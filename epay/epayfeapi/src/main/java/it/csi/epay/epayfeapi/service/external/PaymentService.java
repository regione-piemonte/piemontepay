/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.service.external;

import static it.csi.epay.epayfeapi.util.Constants.CONFIG_ENDPOINT_SERVICE_TASSONOMIA;
import static it.csi.epay.epayfeapi.util.Constants.ERROR_CAMPO_NOTE_OBBLIGATORIO;
import static it.csi.epay.epayfeapi.util.Constants.ERROR_CODICE_VERSAMENTO_MULTIBENEFICIARIO;
import static it.csi.epay.epayfeapi.util.Constants.ERROR_CODICE_VERSAMENTO_NOT_ENABLED_YET;
import static it.csi.epay.epayfeapi.util.Constants.ERROR_CODICE_VERSAMENTO_NOT_SPONTANEO;
import static it.csi.epay.epayfeapi.util.Constants.ERROR_CODICE_VERSAMENTO_NOT_VALID;
import static it.csi.epay.epayfeapi.util.Constants.ERROR_ENTE_NOT_FOUND;
import static it.csi.epay.epayfeapi.util.Constants.ERROR_GET_DATI_SPECIFICI_RISCOSSIONE;
import static it.csi.epay.epayfeapi.util.Constants.ERROR_IUV_GENERATION;
import static it.csi.epay.epayfeapi.util.Constants.ERROR_TIPO_PAGAMENTO_NOT_FOUND;
import static it.csi.epay.epayfeapi.util.Constants.ERROR_TIPO_PAGAMENTO_NOT_UNIQUE;
import static it.csi.epay.epayfeapi.util.Constants.MAX_IMPORTO;
import static it.csi.epay.epayfeapi.util.Constants.PAGAMENTO_SPONTANEO_CODE;
import static it.csi.epay.epayfeapi.util.Constants.REGEX_EMAIL;
import static it.csi.epay.epayfeapi.util.Constants.REGEX_IUV_CF;
import static it.csi.epay.epayfeapi.util.Constants.REGEX_NORMAL_TEXT;
import static it.csi.epay.epayfeapi.util.Constants.REGEX_PAYMENT_TYPE;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_FIELDS_DEBT_POSITION__PAYMENT_DATA_NOTE;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_FIELDS_PAGAMENTO_SPONTANEO__CODICE_FISCALE_PARTIVA_IVA_PAGATORE;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_FIELDS_PAGAMENTO_SPONTANEO__CODICE_FISCALE_PARTIVA_IVA_PAGATORE_MAGGIORE_DI_16;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_FIELDS_PAGAMENTO_SPONTANEO__CODICE_FISCALE_PARTIVA_IVA_PAGATORE_MINORE_DI_11;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_FIELDS_PAGAMENTO_SPONTANEO__ORGANIZATION_FISCAL_CODE;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_FIELDS_PAGAMENTO_SPONTANEO__PAYMENT_DATA;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_FIELDS_PAGAMENTO_SPONTANEO__PAYMENT_DATA_COGNOME;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_FIELDS_PAGAMENTO_SPONTANEO__PAYMENT_DATA_EMAIL;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_FIELDS_PAGAMENTO_SPONTANEO__PAYMENT_DATA_IMPORTO;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_FIELDS_PAGAMENTO_SPONTANEO__PAYMENT_DATA_NOME;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_FIELDS_PAGAMENTO_SPONTANEO__PAYMENT_DATA_NOTE;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_FIELDS_PAGAMENTO_SPONTANEO__PAYMENT_TYPE;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_PAYMENT;
import static it.csi.epay.epayfeapi.util.Constants.SYSTEM_USER_DEFAULT;
import static it.csi.epay.epayfeapi.util.ResponseUtil.generateBusinessErrorResponse;
import static it.csi.epay.epayfeapi.util.ResponseUtil.generateForbiddenResponse;
import static it.csi.epay.epayfeapi.util.ResponseUtil.generateInternalErrorResponse;
import static it.csi.epay.epayfeapi.util.ResponseUtil.generateNotFoundErrorResponse;
import static it.csi.epay.epayfeapi.util.ResponseUtil.generateUnauthorizedEnteResponse;
import static it.csi.epay.epayfeapi.util.ResponseUtil.generateUnauthorizedResponse;
import static it.csi.epay.epayfeapi.util.ResponseUtil.generateValidationErrorResponse;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.openapitools.model.Error;
import org.openapitools.model.PaymentDataPayment;
import org.openapitools.model.PaymentReferences;

import io.quarkus.logging.Log;
import it.csi.epay.epayfeapi.dto.PagamentoComponentiDTO;
import it.csi.epay.epayfeapi.dto.PagamentoDTO;
import it.csi.epay.epayfeapi.entity.EpayTEnti;
import it.csi.epay.epayfeapi.entity.EpayTTipoPagamento;
import it.csi.epay.epayfeapi.enumeration.Scopes;
import it.csi.epay.epayfeapi.exception.MdpException;
import it.csi.epay.epayfeapi.exception.TassonomiaServiceException;
import it.csi.epay.epayfeapi.model.epaypacatalogsrv.DatiSpecificiRiscossione;
import it.csi.epay.epayfeapi.model.epaypacatalogsrv.DatiSpecificiRiscossioneInput;
import it.csi.epay.epayfeapi.model.epaypacatalogsrv.DatiSpecificiRiscossioneOutput;
import it.csi.epay.epayfeapi.rest.client.CodiciEsito;
import it.csi.epay.epayfeapi.rest.client.TassonomiaAdapterClient;
import it.csi.epay.epayfeapi.security.AuthenticationContext;
import it.csi.epay.epayfeapi.service.ChiamanteAutorizzazioneChiamanteService;
import it.csi.epay.epayfeapi.service.ChiamanteEsternoService;
import it.csi.epay.epayfeapi.service.ChiamataEsternaNonValidaService;
import it.csi.epay.epayfeapi.service.ConfigurazioneService;
import it.csi.epay.epayfeapi.service.EnteService;
import it.csi.epay.epayfeapi.service.ModelloUnicoHelperService;
import it.csi.epay.epayfeapi.service.PagamentoService;
import it.csi.epay.epayfeapi.service.RtService;
import it.csi.epay.epayfeapi.service.TipoPagamentoCollegatoService;
import it.csi.epay.epayfeapi.service.TipoPagamentoService;
import it.csi.epay.epayfeapi.service.TracciabilitaChiamanteEsternoService;
import it.csi.epay.epayfeapi.soap.client.mdpmultiiuv.IuvComplex;


/*
 * Servizio 6, pagamento spontaneo
 */
@ApplicationScoped
@Transactional
public class PaymentService {

	@Inject
	AuthenticationContext authenticationContext;

	@Inject
	TracciabilitaChiamanteEsternoService tracciabilitaChiamanteEsternoService;

	@Inject
	ChiamanteEsternoService chiamanteEsternoService;

	@Inject
	EnteService enteService;

	@Inject
	TipoPagamentoService tipoPagamentoService;

	@Inject
	TipoPagamentoCollegatoService tipoPagamentoCollegatoService;

	@Inject
	PagamentoService pagamentoService;

	@Inject
	ChiamanteAutorizzazioneChiamanteService chiamanteAutorizzazioneChiamanteService;

	@Inject
	ChiamataEsternaNonValidaService chiamataEsternaNonValidaService;

	@Inject
	RtService.MultiIuv multiIuv;

	@Inject
	ModelloUnicoHelperService modelloUnicoHelperService;

	public static DatiSpecificiRiscossioneOutput getDatiSpecificiRiscossioneOutput ( String codiceVersamento, String organizationFiscalCode,
					ConfigurazioneService configurazioneService, TassonomiaAdapterClient tassonomiaAdapterClient ) throws Exception {
		var ente = new EpayTEnti ();
		ente.setIdEnte ( 0L ); // fatto per prendere sempre il record di default per tutti gli enti
		var config = configurazioneService.findByCodiceAndCodiceAndEnte ( CONFIG_ENDPOINT_SERVICE_TASSONOMIA, ente );
		if ( null == config ) {
			Log.error ( ERROR_GET_DATI_SPECIFICI_RISCOSSIONE );
			throw new Exception ( ERROR_GET_DATI_SPECIFICI_RISCOSSIONE );
		}
		var url = config.getValore ();
		var request = new DatiSpecificiRiscossioneInput ();
		request.setCodiceFiscaleEnte ( organizationFiscalCode );
		request.setTipoPagamento ( codiceVersamento );
		request.setAnnoEsercizio ( Calendar.getInstance ().get ( Calendar.YEAR ) );
		return tassonomiaAdapterClient.getDatiSpecificiRiscossione ( request, url );
	}

	public static PagamentoComponentiDTO getPagamentoComponentiDTO ( DatiSpecificiRiscossioneOutput dsr, BigDecimal importo, int maxErrorMessageWidth )
					throws TassonomiaServiceException {
		var componente = new PagamentoComponentiDTO ();
		componente.setProgressivo ( 1 );
		componente.setImporto ( importo );
		componente.setUtenteUltimaModifica ( SYSTEM_USER_DEFAULT );
		List<DatiSpecificiRiscossione> listDati = new LinkedList<> ();
		for ( var dato : dsr.getElencoDatiSpecifici () ) {
			if ( null != dato.getAnnoAccertamento () && null != dato.getNumeroAccertamento () ) {
				listDati.add ( dato );
			}
		}
		if ( CollectionUtils.isEmpty ( listDati ) || listDati.size () > 1 ) {
			var ce = CodiciEsito.ERRORE_DATI_SPECIFICI_RISCOSSIONE;
			throw new TassonomiaServiceException ( ce.getCodice (), ce.getMessaggio ( maxErrorMessageWidth ) + ", per la posizione debitoria: " + null
							+ ", non essendo specificate componenti viene richiesto un'unico riferimento contabile, con anno e numero accertamento specificato, valido per l'anno in corso su Catalogo!" );
		} else {
			componente.setAnnoAccertamento ( listDati.get ( 0 ).getAnnoAccertamento () );
			componente.setDatiSpecificiRiscossione ( listDati.get ( 0 ).getCodice () );
			componente.setNumeroAccertamento ( listDati.get ( 0 ).getNumeroAccertamento () );
			componente.setCausale ( listDati.get ( 0 ).getDescrizione () );
			componente.setCodiceTributo ( listDati.get ( 0 ).getCodiceTributo () );
		}
		return componente;
	}

	public Response getIUV ( String organizationFiscalCode, String paymenttype, PaymentDataPayment input, long initialMoment, String serviceName ) {
		var methodName = "[getIUV] ";
		Log.infof ( "%sBEGIN", methodName );
		Log.infof ( "%sparam organizationFiscalCode:%s", methodName, organizationFiscalCode );
		Log.infof ( "%sparam paymenttype:%s", methodName, paymenttype );
		Log.infof ( "%sparam input:%s", methodName, input );

		var user = authenticationContext.getCurrentUser ();
		var cfPIvaPagatore = input.getCodiceFiscalePartitaIVAPagatore ();
		Log.infof ( "%suser:%s", methodName, user );

		/* --- validazione --- */
		// 1. ottiene il chiamante esterno per il tracciamento della chiamata e la validazione dell'autorizzazione
		var chiamanteEsternoEntity = chiamanteEsternoService.findByCodiceChiamante ( user.getName () );
		if ( null == chiamanteEsternoEntity ) {
			var response = generateForbiddenResponse ( SERVICE_PAYMENT, user.getName () );
			chiamataEsternaNonValidaService.track ( null, user, organizationFiscalCode, null, ( (Error) response.getEntity () ).getDetail (), initialMoment, serviceName );
			return response;
		}
		Log.infof ( "%schiamanteEsternoEntity:%s", methodName, chiamanteEsternoEntity );
		//
		// 2. tracciamento della chiamata
		var track = tracciabilitaChiamanteEsternoService.trackExternalCall ( null, cfPIvaPagatore, chiamanteEsternoEntity, null, user, null, initialMoment, serviceName );
		Log.infof ( "%schiamanteEsterno tracciato, idChiamata:%d", methodName, track.getIdChiamata () );
		//
		// 3. validazione autorizzazione
		if ( chiamanteAutorizzazioneChiamanteService.countByCodiceChiamanteAndCodiceAutorizzazioneChiamante ( user.getName (),
						Scopes.PAGAMENTO_SPONTANEO.name () ) < 1 ) {
			var response = generateUnauthorizedResponse ( SERVICE_PAYMENT );
			chiamataEsternaNonValidaService.track ( null, user, organizationFiscalCode, null, ( (Error) response.getEntity () ).getDetail (), initialMoment, serviceName );
			return response;
		}
		Log.infof ( "%sauthorization OK", methodName );

		// validazione input
		var notValids = getNotValidInputs ( organizationFiscalCode, paymenttype, input );
		if ( !notValids.isEmpty () ) {
			var response = generateValidationErrorResponse ( SERVICE_PAYMENT, notValids );
			chiamataEsternaNonValidaService.track ( null, user, organizationFiscalCode, null, ( (Error) response.getEntity () ).getDetail (), initialMoment, serviceName );
			return response;
		}
		Log.infof ( "%svalidation OK", methodName );

		/* --- logica di business --- */

		// verifica ente
		var ente = enteService.findByCodiceFiscale ( organizationFiscalCode );
		if ( null == ente ) {
			var response = generateNotFoundErrorResponse ( SERVICE_PAYMENT, ERROR_ENTE_NOT_FOUND, organizationFiscalCode );
			chiamataEsternaNonValidaService.track ( null, user, organizationFiscalCode, null, ( (Error) response.getEntity () ).getDetail (), initialMoment, serviceName );
			return response;
		}
		if ( ente.getFlagAdesioneCittaFacile()==null || !ente.getFlagAdesioneCittaFacile()) {
			var response = generateUnauthorizedEnteResponse ( SERVICE_PAYMENT);
			chiamataEsternaNonValidaService.track ( null, user, organizationFiscalCode, null, ( (Error) response.getEntity () ).getDetail (), initialMoment, serviceName );
			return response;
		}
		
		Log.infof ( "%sfetched ente[id]:%d", methodName, ente.getIdEnte () );

		// recupera tipo pagamento
		var tipoPagamentoList = tipoPagamentoService.findByEnteAndCodiceVersamento ( ente, paymenttype );
		if ( null == tipoPagamentoList || tipoPagamentoList.isEmpty () ) {
			var response = generateBusinessErrorResponse ( SERVICE_PAYMENT, ERROR_TIPO_PAGAMENTO_NOT_FOUND, paymenttype, organizationFiscalCode );
			chiamataEsternaNonValidaService.track ( null, user, organizationFiscalCode, null, ( (Error) response.getEntity () ).getDetail (), initialMoment, serviceName );
			return response;
		}
		if ( tipoPagamentoList.size () > 1 ) {
			var response = generateBusinessErrorResponse ( SERVICE_PAYMENT, ERROR_TIPO_PAGAMENTO_NOT_UNIQUE, paymenttype, organizationFiscalCode );
			chiamataEsternaNonValidaService.track ( null, user, organizationFiscalCode, null, ( (Error) response.getEntity () ).getDetail (), initialMoment, serviceName );
			return response;
		}
		var tipoPagamento = tipoPagamentoList.get ( 0 );

		// valida campo note, deve essere obbligatorio se il campo compilazione note del tipo pagamento non null
		if ( !StringUtils.isEmpty ( tipoPagamento.getCompilazioneNote () ) && StringUtils.isEmpty ( input.getNote () ) ) {
			chiamataEsternaNonValidaService.track ( null, user, organizationFiscalCode, null, String.format ( ERROR_CAMPO_NOTE_OBBLIGATORIO, paymenttype ), initialMoment, serviceName );
			notValids.add ( SERVICE_FIELDS_DEBT_POSITION__PAYMENT_DATA_NOTE );
			return generateValidationErrorResponse ( SERVICE_PAYMENT, notValids );
		}

		// valida tipo pagamento
		var validateTipoPagResult = validateTipoPagamento ( tipoPagamento );
		if ( null != validateTipoPagResult ) {
			chiamataEsternaNonValidaService.track ( null, user, organizationFiscalCode, null, ( (Error) validateTipoPagResult.getEntity () ).getDetail (), initialMoment, serviceName );
			return validateTipoPagResult;
		}

		// chiamate Soap web service MDP
		IuvComplex iuvComplex;
		try {
			var iuvList = multiIuv.generateNewIuv ( tipoPagamento, 1 );
			iuvComplex = iuvList.get ( 0 );
			Log.infof ( "%sgenerato IUV complex:%s", methodName, iuvComplex );
		} catch ( MdpException e ) {
			var codiceVersamento = tipoPagamento.getCodiceVersamento ();
			var response = generateInternalErrorResponse ( SERVICE_PAYMENT, ERROR_IUV_GENERATION, tipoPagamento.getIdApplicazione (), codiceVersamento );
			chiamataEsternaNonValidaService.track ( null, user, organizationFiscalCode, null, ( (Error) response.getEntity () ).getDetail (), initialMoment, serviceName );
			return response;
		}
		var iuvOttico = iuvComplex.getIuvOttico ();
		Log.infof ( "%siuvOttico:%s", methodName, iuvOttico );

		// altro tracciamento (update record di prima)
		tracciabilitaChiamanteEsternoService.trackExternalCall ( track, cfPIvaPagatore, chiamanteEsternoEntity, iuvOttico, user, null, initialMoment, serviceName );
		Log.infof ( "%scall tracked, id caller:%d", methodName, track.getIdChiamata () );

		// costruzione pagamento
		PagamentoDTO pagamento;
		try {
			pagamento = pagamentoService.buildPayment ( cfPIvaPagatore, input.getNome (), input.getCognome (), input.getRagioneSociale (), input.getImporto (),
							input.getEmail (), input.getNote (), tipoPagamento, iuvComplex, organizationFiscalCode, null, false );

		} catch ( Exception e ) {
			var response = generateInternalErrorResponse ( SERVICE_PAYMENT, e.getMessage () );
			chiamataEsternaNonValidaService.track ( null, user, organizationFiscalCode, null, ( (Error) response.getEntity () ).getDetail (), initialMoment, serviceName );
			return response;
		}

		// checkout call
		var fromCheckout = modelloUnicoHelperService.manageModelloUnico ( organizationFiscalCode, pagamento, iuvOttico, chiamanteEsternoEntity, track,
						SERVICE_PAYMENT, serviceName, initialMoment );
		if ( fromCheckout.isKO () ) {
			return fromCheckout.getResponse ();
		}

		// costruisce la response
		var paymentReferences = new PaymentReferences ();
		paymentReferences.setIuv ( iuvOttico );
		paymentReferences.setPaymentUrl ( fromCheckout.getPaymentUrl () );

		Log.infof ( "%sEND", methodName );
		return Response.status ( Response.Status.CREATED ).entity ( paymentReferences ).build ();
	}

	private List<String> getNotValidInputs ( String organizationFiscalCode, String paymenttype, PaymentDataPayment paymentData ) {
		List<String> notValids = new LinkedList<> ();

		if ( StringUtils.isBlank ( organizationFiscalCode ) ) {
			notValids.add ( SERVICE_FIELDS_PAGAMENTO_SPONTANEO__ORGANIZATION_FISCAL_CODE );
		} else if ( !organizationFiscalCode.matches ( REGEX_IUV_CF ) ) {
			notValids.add ( SERVICE_FIELDS_PAGAMENTO_SPONTANEO__ORGANIZATION_FISCAL_CODE );
		}

		if ( StringUtils.isBlank ( paymenttype ) ) {
			notValids.add ( SERVICE_FIELDS_PAGAMENTO_SPONTANEO__PAYMENT_TYPE );
		} else if ( !paymenttype.matches ( REGEX_PAYMENT_TYPE ) ) {
			notValids.add ( SERVICE_FIELDS_PAGAMENTO_SPONTANEO__PAYMENT_TYPE );
		}

		if ( paymentData == null ) {
			notValids.add ( SERVICE_FIELDS_PAGAMENTO_SPONTANEO__PAYMENT_DATA );
		} else {
			if ( !StringUtils.isBlank ( paymentData.getNote () ) && !paymentData.getNote ().matches ( REGEX_NORMAL_TEXT ) ) {
				notValids.add ( SERVICE_FIELDS_PAGAMENTO_SPONTANEO__PAYMENT_DATA_NOTE );
			}

			if ( paymentData.getImporto () == null
							|| paymentData.getImporto ().doubleValue () <= 0 || paymentData.getImporto ().doubleValue () > MAX_IMPORTO ) {
				notValids.add ( SERVICE_FIELDS_PAGAMENTO_SPONTANEO__PAYMENT_DATA_IMPORTO );
			}

			if ( !StringUtils.isBlank ( paymentData.getEmail () ) && !paymentData.getEmail ().matches ( REGEX_EMAIL ) ) {
				notValids.add ( SERVICE_FIELDS_PAGAMENTO_SPONTANEO__PAYMENT_DATA_EMAIL );
			}

			if ( StringUtils.isBlank ( paymentData.getRagioneSociale () ) ) {
				if ( StringUtils.isBlank ( paymentData.getNome () ) ) {
					notValids.add ( SERVICE_FIELDS_PAGAMENTO_SPONTANEO__PAYMENT_DATA_NOME );
				} else if ( !paymentData.getNome ().matches ( REGEX_NORMAL_TEXT ) ) {
					notValids.add ( SERVICE_FIELDS_PAGAMENTO_SPONTANEO__PAYMENT_DATA_NOME );
				}

				if ( StringUtils.isBlank ( paymentData.getCognome () ) ) {
					notValids.add ( SERVICE_FIELDS_PAGAMENTO_SPONTANEO__PAYMENT_DATA_COGNOME );
				} else if ( !paymentData.getCognome ().matches ( REGEX_NORMAL_TEXT ) ) {
					notValids.add ( SERVICE_FIELDS_PAGAMENTO_SPONTANEO__PAYMENT_DATA_COGNOME );
				}
			}

			if ( paymentData.getCodiceFiscalePartitaIVAPagatore () != null ) {
				String codiceFiscalePartitaIVAPagatore = paymentData.getCodiceFiscalePartitaIVAPagatore ();
				if ( codiceFiscalePartitaIVAPagatore.isBlank () ) {
					notValids.add ( SERVICE_FIELDS_PAGAMENTO_SPONTANEO__CODICE_FISCALE_PARTIVA_IVA_PAGATORE );
				} else {
					if ( codiceFiscalePartitaIVAPagatore.length () < 11 ) {
						if ( !codiceFiscalePartitaIVAPagatore.equals ( "ANONIMO" ) ) {
							notValids.add ( SERVICE_FIELDS_PAGAMENTO_SPONTANEO__CODICE_FISCALE_PARTIVA_IVA_PAGATORE_MINORE_DI_11 );
						}
					}
					if ( codiceFiscalePartitaIVAPagatore.length () > 16 ) {
						notValids.add ( SERVICE_FIELDS_PAGAMENTO_SPONTANEO__CODICE_FISCALE_PARTIVA_IVA_PAGATORE_MAGGIORE_DI_16 );
					}
				}
			} else {
				notValids.add ( SERVICE_FIELDS_PAGAMENTO_SPONTANEO__CODICE_FISCALE_PARTIVA_IVA_PAGATORE );
			}
		}

		return notValids;
	}

	private Response validateTipoPagamento ( EpayTTipoPagamento tipoPagamento ) {
		var today = Date.from ( LocalDate.now ().atStartOfDay ( ZoneId.systemDefault () ).toInstant () );
		var codiceVersamento = tipoPagamento.getCodiceVersamento ();
		if ( tipoPagamento.getInizioValidita () == null || tipoPagamento.getInizioValidita ().after ( today ) ) {
			return generateBusinessErrorResponse ( SERVICE_PAYMENT, ERROR_CODICE_VERSAMENTO_NOT_ENABLED_YET, codiceVersamento );
		}
		if ( tipoPagamento.getFineValidita () != null && tipoPagamento.getFineValidita ().before ( today ) ) {
			return generateBusinessErrorResponse ( SERVICE_PAYMENT, ERROR_CODICE_VERSAMENTO_NOT_VALID, codiceVersamento );
		}
		if ( Boolean.TRUE.equals ( tipoPagamento.getFlagMultibeneficiario () ) ) {
			return generateBusinessErrorResponse ( SERVICE_PAYMENT, ERROR_CODICE_VERSAMENTO_MULTIBENEFICIARIO, codiceVersamento );
		}
		if ( tipoPagamentoCollegatoService.countByIdPagamentoSecondario ( tipoPagamento.getIdTipoPagamento () ) > 0 ) {
			return generateBusinessErrorResponse ( SERVICE_PAYMENT, ERROR_CODICE_VERSAMENTO_MULTIBENEFICIARIO, codiceVersamento );
		}
		if ( !( Boolean.TRUE.equals ( tipoPagamento.getPagamentoSpontaneo () ) ) ) {
			return generateBusinessErrorResponse ( SERVICE_PAYMENT, ERROR_CODICE_VERSAMENTO_NOT_SPONTANEO, codiceVersamento );
		}
		if ( Boolean.FALSE.equals ( tipoPagamento.getPagamentoSpontaneo () ) && !tipoPagamento.getTipologiaPagamento ().getCodice ()
						.equalsIgnoreCase ( PAGAMENTO_SPONTANEO_CODE ) ) {
			return generateBusinessErrorResponse ( SERVICE_PAYMENT, ERROR_CODICE_VERSAMENTO_NOT_SPONTANEO, codiceVersamento );
		}
		return null;
	}
	
	
}
