/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.service.external;

import static it.csi.epay.epayfeapi.util.Constants.ERROR_CAMPO_NOTE_OBBLIGATORIO;
import static it.csi.epay.epayfeapi.util.Constants.ERROR_CODICE_VERSAMENTO_NOT_SPONTANEO;
import static it.csi.epay.epayfeapi.util.Constants.ERROR_ENTE_NOT_FOUND;
import static it.csi.epay.epayfeapi.util.Constants.ERROR_IUV_GENERATION;
import static it.csi.epay.epayfeapi.util.Constants.ERROR_PDF_GENERATION;
import static it.csi.epay.epayfeapi.util.Constants.ERROR_TIPO_PAGAMENTO_NOT_FOUND;
import static it.csi.epay.epayfeapi.util.Constants.ERROR_TIPO_PAGAMENTO_NOT_UNIQUE;
import static it.csi.epay.epayfeapi.util.Constants.MAX_IMPORTO;
import static it.csi.epay.epayfeapi.util.Constants.PAGAMENTO_SPONTANEO_CODE;
import static it.csi.epay.epayfeapi.util.Constants.REGEX_EMAIL;
import static it.csi.epay.epayfeapi.util.Constants.REGEX_IUV_CF;
import static it.csi.epay.epayfeapi.util.Constants.REGEX_NORMAL_TEXT;
import static it.csi.epay.epayfeapi.util.Constants.REGEX_PAYMENT_TYPE;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_DEBT_POSITION;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_FIELDS_DEBT_POSITION__CITIZEN_FISCAL_CODE;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_FIELDS_DEBT_POSITION__ORGANIZATION_FISCAL_CODE;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_FIELDS_DEBT_POSITION__PAYMENT_DATA;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_FIELDS_DEBT_POSITION__PAYMENT_DATA_COGNOME;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_FIELDS_DEBT_POSITION__PAYMENT_DATA_EMAIL;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_FIELDS_DEBT_POSITION__PAYMENT_DATA_IMPORTO;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_FIELDS_DEBT_POSITION__PAYMENT_DATA_NOME;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_FIELDS_DEBT_POSITION__PAYMENT_DATA_NOTE;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_FIELDS_DEBT_POSITION__PAYMENT_TYPE;
import static it.csi.epay.epayfeapi.util.ResponseUtil.generateBusinessErrorResponse;
import static it.csi.epay.epayfeapi.util.ResponseUtil.generateForbiddenResponse;
import static it.csi.epay.epayfeapi.util.ResponseUtil.generateInternalErrorResponse;
import static it.csi.epay.epayfeapi.util.ResponseUtil.generateNotFoundErrorResponse;
import static it.csi.epay.epayfeapi.util.ResponseUtil.generateUnauthorizedResponse;
import static it.csi.epay.epayfeapi.util.ResponseUtil.generateValidationErrorResponse;

import java.util.LinkedList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;
import org.openapitools.model.CreatedDebtPosition;
import org.openapitools.model.Error;
import org.openapitools.model.PaymentData;

import io.quarkus.logging.Log;
import it.csi.epay.epayfeapi.dto.CodiceAvvisoDTO;
import it.csi.epay.epayfeapi.dto.PagamentoDTO;
import it.csi.epay.epayfeapi.entity.EpayDChiamanteEsterno;
import it.csi.epay.epayfeapi.entity.EpayTEnti;
import it.csi.epay.epayfeapi.entity.EpayTTipoPagamento;
import it.csi.epay.epayfeapi.entity.EpayTTracciabilitaChiamanteEsterno;
import it.csi.epay.epayfeapi.exception.MdpException;
import it.csi.epay.epayfeapi.security.AuthenticationContext;
import it.csi.epay.epayfeapi.security.Scopes;
import it.csi.epay.epayfeapi.security.User;
import it.csi.epay.epayfeapi.service.ChiamanteAutorizzazioneChiamanteService;
import it.csi.epay.epayfeapi.service.ChiamanteEsternoService;
import it.csi.epay.epayfeapi.service.ChiamataEsternaNonValidaService;
import it.csi.epay.epayfeapi.service.CreaAvvisoPagamentoSpontaneoService;
import it.csi.epay.epayfeapi.service.EnteService;
import it.csi.epay.epayfeapi.service.PagamentoService;
import it.csi.epay.epayfeapi.service.TipoPagamentoService;
import it.csi.epay.epayfeapi.service.TracciabilitaChiamanteEsternoService;
import it.csi.epay.epayfeapi.soap.client.mdpmultiiuv.IuvComplex;
import it.csi.epay.epayfeapi.util.MultiIuv;


/*
 * Servizio 2 - PDF relativo ad un avviso di pagamento
 */
@ApplicationScoped
@Transactional
public class CreateDebtPositionService {

	@Inject
	AuthenticationContext authenticationContext;

	@Inject
	ChiamanteEsternoService chiamanteEsternoService;

	@Inject
	ChiamataEsternaNonValidaService chiamataEsternaNonValidaService;

	@Inject
	ChiamanteAutorizzazioneChiamanteService chiamanteAutorizzazioneChiamanteService;

	@Inject
	EnteService enteService;

	@Inject
	TipoPagamentoService tipoPagamentoService;

	@Inject
	PagamentoService pagamentoService;

	@Inject
	TracciabilitaChiamanteEsternoService tracciabilitaChiamanteEsternoService;

	@Inject
	MultiIuv multiIuv;

	@Inject
	CreaAvvisoPagamentoSpontaneoService creaAvvisoPagamentoSpontaneoService;

	public Response createDebtPosition ( String organizationFiscalCode, String citizenFiscalCode, String paymenttype, PaymentData paymentData ) {
		String methodName = "[createDebtPosition] ";

		Log.info ( methodName + "BEGIN" );
		Log.info ( methodName + "param organizationFiscalCode:" + organizationFiscalCode );
		Log.info ( methodName + "param citizenFiscalCode:" + citizenFiscalCode );
		Log.info ( methodName + "param paymenttype:" + paymenttype );
		Log.info ( methodName + "param PaymentData:" + paymentData.toString () );

		User user = authenticationContext.getCurrentUser ();
		Log.info ( methodName + "user:" + user );

		/* --- validazione --- */
		// 1. ottiene il chiamante esterno per il tracciamento della chiamata e la validazione dell'autorizzazione
		EpayDChiamanteEsterno chiamanteEsternoEntity = chiamanteEsternoService.findByCodiceChiamante ( user.getName () );
		if ( chiamanteEsternoEntity == null ) {
			Response response = generateForbiddenResponse ( SERVICE_DEBT_POSITION, user.getName () );
			chiamataEsternaNonValidaService.track ( null, user, organizationFiscalCode, null, ( (Error) response.getEntity () ).getDetail () );
			return response;
		}
		Log.info ( methodName + "chiamanteEsternoEntity:" + chiamanteEsternoEntity );
		//
		// 2. tracciamento della chiamata
		EpayTTracciabilitaChiamanteEsterno track
						= tracciabilitaChiamanteEsternoService.trackExternalCall ( null, organizationFiscalCode, chiamanteEsternoEntity, null, user, null );
		Log.info ( methodName + "chiamanteEsterno tracciato" );
		//
		// 3. validazione autorizzazione
		if ( chiamanteAutorizzazioneChiamanteService.countByCodiceChiamanteAndCodiceAutorizzazioneChiamante ( user.getName (),
						Scopes.CREAZIONE_IUV.name () ) < 1
						|| chiamanteAutorizzazioneChiamanteService.countByCodiceChiamanteAndCodiceAutorizzazioneChiamante ( user.getName (),
						Scopes.STAMPA_AVVISO_PAGAMENTO.name () ) < 1 ) {
			Response response = generateUnauthorizedResponse ( SERVICE_DEBT_POSITION );
			chiamataEsternaNonValidaService.track ( null, user, organizationFiscalCode, null, ( (Error) response.getEntity () ).getDetail () );
			return response;
		}
		Log.info ( methodName + "authorization OK" );

		// validazione input
		List<String> notValids = getNotValidInputs ( organizationFiscalCode, citizenFiscalCode, paymenttype, paymentData );
		if ( !notValids.isEmpty () ) {
			Response response = generateValidationErrorResponse ( SERVICE_DEBT_POSITION, notValids );
			chiamataEsternaNonValidaService.track ( null, user, organizationFiscalCode, null, ( (Error) response.getEntity () ).getDetail () );
			return response;
		}
		Log.info ( methodName + "validation OK" );

		/* --- logica di business --- */

		// ottiene l'ente corrispondente al codice fiscale
		EpayTEnti enteEntity = enteService.findByCodiceFiscale ( organizationFiscalCode );
		if ( enteEntity == null ) {
			Response response = generateNotFoundErrorResponse ( SERVICE_DEBT_POSITION, ERROR_ENTE_NOT_FOUND, organizationFiscalCode );
			chiamataEsternaNonValidaService.track ( null, user, organizationFiscalCode, null, ( (Error) response.getEntity () ).getDetail () );
			return response;
		}
		Log.info ( methodName + "ente:" + enteEntity );

		// recupera tipo pagamento
		List<EpayTTipoPagamento> tipoPagamentoList = tipoPagamentoService.findByEnteAndCodiceVersamento ( enteEntity, paymenttype );
		if ( null == tipoPagamentoList || tipoPagamentoList.isEmpty () ) {
			Response response = generateNotFoundErrorResponse ( SERVICE_DEBT_POSITION, ERROR_TIPO_PAGAMENTO_NOT_FOUND, paymenttype, organizationFiscalCode );
			chiamataEsternaNonValidaService.track ( null, user, organizationFiscalCode, null, ( (Error) response.getEntity () ).getDetail () );
			return response;
		}
		if ( tipoPagamentoList.size () > 1 ) {
			Response response = generateBusinessErrorResponse ( SERVICE_DEBT_POSITION, ERROR_TIPO_PAGAMENTO_NOT_UNIQUE, paymenttype, organizationFiscalCode );
			chiamataEsternaNonValidaService.track ( null, user, organizationFiscalCode, null, ( (Error) response.getEntity () ).getDetail () );
			return response;
		}
		EpayTTipoPagamento tipoPagamento = tipoPagamentoList.get ( 0 );

		// valida campo note, deve essere obbligatorio se il campo compilazione note del tipo pagamento non null
		if ( !StringUtils.isEmpty ( tipoPagamento.getCompilazioneNote () ) && StringUtils.isEmpty ( paymentData.getNote () ) ) {
			chiamataEsternaNonValidaService.track ( null, user, organizationFiscalCode, null, String.format ( ERROR_CAMPO_NOTE_OBBLIGATORIO, paymenttype ) );
			notValids.add ( SERVICE_FIELDS_DEBT_POSITION__PAYMENT_DATA_NOTE );
			return generateValidationErrorResponse ( SERVICE_DEBT_POSITION, notValids );
		}

		// controlla che il tipo pagamento sia spontaneo
		if ( !Boolean.TRUE.equals ( tipoPagamento.getPagamentoSpontaneo () )
						|| ( tipoPagamento.getTipologiaPagamento () != null && !PAGAMENTO_SPONTANEO_CODE.equals (
						tipoPagamento.getTipologiaPagamento ().getCodice () ) ) ) {
			Response response = generateBusinessErrorResponse ( SERVICE_DEBT_POSITION, ERROR_CODICE_VERSAMENTO_NOT_SPONTANEO, paymenttype );
			chiamataEsternaNonValidaService.track ( null, user, organizationFiscalCode, null, ( (Error) response.getEntity () ).getDetail () );
			return response;
		}

		// RICHIAMA MDP - MDPMULTIIUVSRV - getIUVComplex
		// chiamata Soap web service MDP
		IuvComplex iuvComplex;
		try {
			List<IuvComplex> iuvList = multiIuv.generateNewIuv ( tipoPagamento, 1 );
			iuvComplex = iuvList.get ( 0 );
			Log.info ( methodName + "generato IUV:" + iuvComplex + " - iuv.iuv:" + ( iuvComplex != null ? iuvComplex.getIuv () : "null" ) );

		} catch ( MdpException e ) {
			Response response = generateInternalErrorResponse ( SERVICE_DEBT_POSITION, ERROR_IUV_GENERATION, tipoPagamento.getIdApplicazione (),
							tipoPagamento.getCodiceVersamento () );
			chiamataEsternaNonValidaService.track ( null, user, organizationFiscalCode, null, ( (Error) response.getEntity () ).getDetail () );
			return response;
		}

		// altro tracciamento (update record di prima)
		assert iuvComplex != null;
		tracciabilitaChiamanteEsternoService.trackExternalCall ( track, organizationFiscalCode, chiamanteEsternoEntity, iuvComplex.getIuvOttico (), user,
						null );
		Log.info ( methodName + "call tracked, id caller:" + track.getIdChiamata () );

		// costruzione pagamento
		PagamentoDTO pagamento;
		try {
			CodiceAvvisoDTO codiceAvviso
							= new CodiceAvvisoDTO ( iuvComplex.getAuxDigit (), tipoPagamento.getIdApplicazione (), iuvComplex.getIuvOttico (), Boolean.TRUE );
			Log.info ( methodName + "codiceAvviso:" + codiceAvviso );
			pagamento = pagamentoService.buildPayment ( citizenFiscalCode, paymentData.getNome (), paymentData.getCognome (), paymentData.getRagioneSociale (),
							paymentData.getImporto (), paymentData.getEmail (), paymentData.getNote (), tipoPagamento, iuvComplex, organizationFiscalCode,
							codiceAvviso );

		} catch ( Exception e ) {
			Response response = generateInternalErrorResponse ( SERVICE_DEBT_POSITION, e.getMessage () );
			chiamataEsternaNonValidaService.track ( null, user, citizenFiscalCode, null, e.getMessage () );
			return response;
		}

		CreatedDebtPosition result = new CreatedDebtPosition ();

		try {
			result.setPdf ( creaAvvisoPagamentoSpontaneoService.buildAvvisoPagamentoSpontaneo ( pagamento ) );
			result.setIuv ( pagamento.getIuv () );
			result.setCodiceAvviso ( pagamento.getCodiceAvviso () );

		} catch ( Exception e ) {
			Response response = generateNotFoundErrorResponse ( SERVICE_DEBT_POSITION, ERROR_PDF_GENERATION, pagamento.getIdPagamento () );
			chiamataEsternaNonValidaService.track ( null, user, citizenFiscalCode, iuvComplex.getIuvOttico (), ( (Error) response.getEntity () ).getDetail () );
			return response;
		}

		Log.info ( methodName + "END" );
		return Response.status ( Response.Status.CREATED ).entity ( result ).build ();
	}

	private List<String> getNotValidInputs ( String organizationFiscalCode, String citizenFiscalCode, String paymenttype, PaymentData paymentData ) {
		List<String> notValids = new LinkedList<> ();

		if ( StringUtils.isBlank ( organizationFiscalCode ) ) {
			notValids.add ( SERVICE_FIELDS_DEBT_POSITION__ORGANIZATION_FISCAL_CODE );
		} else if ( !organizationFiscalCode.matches ( REGEX_IUV_CF ) ) {
			notValids.add ( SERVICE_FIELDS_DEBT_POSITION__ORGANIZATION_FISCAL_CODE );
		}

		if ( StringUtils.isBlank ( citizenFiscalCode ) ) {
			notValids.add ( SERVICE_FIELDS_DEBT_POSITION__CITIZEN_FISCAL_CODE );
		} else if ( !citizenFiscalCode.matches ( REGEX_IUV_CF ) ) {
			notValids.add ( SERVICE_FIELDS_DEBT_POSITION__CITIZEN_FISCAL_CODE );
		}

		if ( StringUtils.isBlank ( paymenttype ) ) {
			notValids.add ( SERVICE_FIELDS_DEBT_POSITION__PAYMENT_TYPE );
		} else if ( !paymenttype.matches ( REGEX_PAYMENT_TYPE ) ) {
			notValids.add ( SERVICE_FIELDS_DEBT_POSITION__PAYMENT_TYPE );
		}

		if ( paymentData == null ) {
			notValids.add ( SERVICE_FIELDS_DEBT_POSITION__PAYMENT_DATA );
		} else {
			if ( !StringUtils.isBlank ( paymentData.getNote () ) && !paymentData.getNote ().matches ( REGEX_NORMAL_TEXT ) ) {
				notValids.add ( SERVICE_FIELDS_DEBT_POSITION__PAYMENT_DATA_NOTE );
			}

			if ( paymentData.getImporto () == null || paymentData.getImporto ().doubleValue () <= 0
				|| paymentData.getImporto ().doubleValue () > MAX_IMPORTO ) {
				notValids.add ( SERVICE_FIELDS_DEBT_POSITION__PAYMENT_DATA_IMPORTO );
			}

			if ( !StringUtils.isBlank ( paymentData.getEmail () ) && !paymentData.getEmail ().matches ( REGEX_EMAIL ) ) {
				notValids.add ( SERVICE_FIELDS_DEBT_POSITION__PAYMENT_DATA_EMAIL );
			}

			if ( StringUtils.isBlank ( paymentData.getRagioneSociale () ) ) {
				if ( StringUtils.isBlank ( paymentData.getNome () ) ) {
					notValids.add ( SERVICE_FIELDS_DEBT_POSITION__PAYMENT_DATA_NOME );
				} else if ( !paymentData.getNome ().matches ( REGEX_NORMAL_TEXT ) ) {
					notValids.add ( SERVICE_FIELDS_DEBT_POSITION__PAYMENT_DATA_NOME );
				}

				if ( StringUtils.isBlank ( paymentData.getCognome () ) ) {
					notValids.add ( SERVICE_FIELDS_DEBT_POSITION__PAYMENT_DATA_COGNOME );
				} else if ( !paymentData.getCognome ().matches ( REGEX_NORMAL_TEXT ) ) {
					notValids.add ( SERVICE_FIELDS_DEBT_POSITION__PAYMENT_DATA_COGNOME );
				}
			}
		}

		return notValids;
	}
}
