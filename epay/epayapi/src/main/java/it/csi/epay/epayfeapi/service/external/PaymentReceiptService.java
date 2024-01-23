/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.service.external;

import io.quarkus.logging.Log;
import it.csi.epay.epayfeapi.entity.EpayDChiamanteEsterno;
import it.csi.epay.epayfeapi.entity.EpayTEnti;
import it.csi.epay.epayfeapi.entity.EpayTEsitiRicevuti;
import it.csi.epay.epayfeapi.entity.EpayTPagamento;
import it.csi.epay.epayfeapi.entity.EpayTRegistroVersamentiReflection;
import it.csi.epay.epayfeapi.entity.EpayTRtPdf;
import it.csi.epay.epayfeapi.enumeration.StatoPagamentiEnum;
import it.csi.epay.epayfeapi.security.AuthenticationContext;
import it.csi.epay.epayfeapi.security.Scopes;
import it.csi.epay.epayfeapi.security.User;
import it.csi.epay.epayfeapi.service.ChiamanteAutorizzazioneChiamanteService;
import it.csi.epay.epayfeapi.service.ChiamanteEsternoService;
import it.csi.epay.epayfeapi.service.ChiamataEsternaNonValidaService;
import it.csi.epay.epayfeapi.service.EnteService;
import it.csi.epay.epayfeapi.service.EsitiRicevutiService;
import it.csi.epay.epayfeapi.service.PagamentoService;
import it.csi.epay.epayfeapi.service.RegistroVersamentiService;
import it.csi.epay.epayfeapi.service.RtService;
import it.csi.epay.epayfeapi.service.TracciabilitaChiamanteEsternoService;
import org.apache.commons.lang3.StringUtils;
import org.openapitools.model.Error;
import org.openapitools.model.PaymentReceipt;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.util.LinkedList;
import java.util.List;

import static it.csi.epay.epayfeapi.util.Constants.ERROR_ENTE_NOT_FOUND;
import static it.csi.epay.epayfeapi.util.Constants.ERROR_PAGAMENTO_NON_ASSOCIATO_A_CODICE_FISCALE;
import static it.csi.epay.epayfeapi.util.Constants.ERROR_PAGAMENTO_NON_ASSOCIATO_A_ENTE;
import static it.csi.epay.epayfeapi.util.Constants.ERROR_PAGAMENTO_NOT_FOUND;
import static it.csi.epay.epayfeapi.util.Constants.ERROR_RECEIPT_NOT_FOUND;
import static it.csi.epay.epayfeapi.util.Constants.ERROR_REGISTRO_NOT_FOUND;
import static it.csi.epay.epayfeapi.util.Constants.REGEX_IUV_CF;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_FIELDS_PAYMENT_NOTICE__IUV;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_FIELDS_PAYMENT_NOTICE__ORGANIZATION_FISCAL_CODE;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_FIELDS_PAYMENT_RECEIPT__CITIZEN_FISCAL_CODE;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_FIELDS_PAYMENT_RECEIPT__IUV;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_FIELDS_PAYMENT_RECEIPT__ORGANIZATION_FISCAL_CODE;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_PAYMENT_RECEIPT;
import static it.csi.epay.epayfeapi.util.ResponseUtil.generateForbiddenResponse;
import static it.csi.epay.epayfeapi.util.ResponseUtil.generateNotFoundErrorResponse;
import static it.csi.epay.epayfeapi.util.ResponseUtil.generateUnauthorizedResponse;
import static it.csi.epay.epayfeapi.util.ResponseUtil.generateValidationErrorResponse;


@ApplicationScoped
@Transactional
public class PaymentReceiptService {

	@Inject
	AuthenticationContext authenticationContext;

	@Inject
	ChiamanteEsternoService chiamanteEsternoService;

	@Inject
	ChiamataEsternaNonValidaService chiamataEsternaNonValidaService;

	@Inject
	ChiamanteAutorizzazioneChiamanteService chiamanteAutorizzazioneChiamanteService;

	@Inject
	PagamentoService pagamentoService;

	@Inject
	RegistroVersamentiService registroVersamentiService;

	@Inject
	RtService rtService;

	@Inject
	TracciabilitaChiamanteEsternoService tracciabilitaChiamanteEsternoService;

	@Inject
	EnteService enteService;

	@Inject
	EsitiRicevutiService esitiRicevutiService;

	/*
	 * - Servizio 4 - PDF relativo ad una ricevuta di pagamento
	 */
	public Response getPaymentReceipt ( String organizationFiscalCode, String citizenFiscalCode, String iuv ) {
		String methodName = "[getPaymentReceipt] ";
		Log.info ( methodName + "BEGIN" );
		Log.info ( methodName + "param organizationFiscalCode:" + organizationFiscalCode );
		Log.info ( methodName + "param citizenFiscalCode:" + citizenFiscalCode );
		Log.info ( methodName + "param iuv:" + iuv );

		User user = authenticationContext.getCurrentUser ();
		Log.info ( methodName + "user:" + user );

		/* --- validazione --- */
		// autorizzazione
		// 1. ottiene il chiamante esterno per il tracciamento della chiamata e la validazione dell'autorizzazione
		EpayDChiamanteEsterno chiamanteEsternoEntity = chiamanteEsternoService.findByCodiceChiamante ( user.getName () );
		if ( chiamanteEsternoEntity == null ) {
			Response response = generateForbiddenResponse ( SERVICE_PAYMENT_RECEIPT, user.getName () );
			chiamataEsternaNonValidaService.track ( null, user, citizenFiscalCode, iuv, ( (Error) response.getEntity () ).getDetail () );
			return response;
		}
		Log.info ( methodName + "chiamanteEsternoEntity:" + chiamanteEsternoEntity );
		//
		// 2. tracciamento della chiamata
		tracciabilitaChiamanteEsternoService.trackExternalCall ( null, organizationFiscalCode, chiamanteEsternoEntity, iuv, user, null );
		Log.info ( methodName + "chiamanteEsterno tracciato" );
		//
		// 3. validazione autorizzazione
		if ( chiamanteAutorizzazioneChiamanteService.countByCodiceChiamanteAndCodiceAutorizzazioneChiamante ( user.getName (),
			Scopes.RICEVUTA_PAGAMENTO.name () ) < 1 ) {
			Response response = generateUnauthorizedResponse ( SERVICE_PAYMENT_RECEIPT );
			chiamataEsternaNonValidaService.track ( null, user, citizenFiscalCode, iuv, ( (Error) response.getEntity () ).getDetail () );
			return response;
		}
		Log.info ( methodName + "authorization OK" );

		// validazione input
		List<String> notValids = getNotValidInputs ( organizationFiscalCode, citizenFiscalCode, iuv );
		if ( !notValids.isEmpty () ) {
			Response response = generateValidationErrorResponse ( SERVICE_PAYMENT_RECEIPT, notValids );
			chiamataEsternaNonValidaService.track ( null, user, citizenFiscalCode, iuv, ( (Error) response.getEntity () ).getDetail () );
			return response;
		}
		Log.info ( methodName + "validation OK" );

		/* --- logica di business --- */

		// ottiene l'ente corrispondente al codice fiscale
		EpayTEnti enteEntity = enteService.findByCodiceFiscale ( organizationFiscalCode );
		if ( enteEntity == null ) {
			Response response = generateNotFoundErrorResponse ( SERVICE_PAYMENT_RECEIPT, ERROR_ENTE_NOT_FOUND, organizationFiscalCode );
			chiamataEsternaNonValidaService.track ( null, user, organizationFiscalCode, null, ( (Error) response.getEntity () ).getDetail () );
			return response;
		}
		Log.info ( methodName + "ente:" + enteEntity );

		// controllo dello IUV
		EpayTPagamento pagamentoEntity = pagamentoService.getPagamentoByiuvNumeroAvviso ( iuv );
		if ( pagamentoEntity == null ) {
			Response response = generateNotFoundErrorResponse ( SERVICE_PAYMENT_RECEIPT, ERROR_PAGAMENTO_NOT_FOUND );
			chiamataEsternaNonValidaService.track ( null, user, citizenFiscalCode, iuv, ( (Error) response.getEntity () ).getDetail () );
			return response;
		}
		Log.info ( methodName + "controllo IUV OK" );

		// controllo del corrispondenza pagatore
		if ( !citizenFiscalCode.equals ( pagamentoEntity.getEpayTAnagrafica ().getCodiceFiscale () ) ) {
			Response response = generateNotFoundErrorResponse ( SERVICE_PAYMENT_RECEIPT, ERROR_PAGAMENTO_NON_ASSOCIATO_A_CODICE_FISCALE );
			chiamataEsternaNonValidaService.track ( null, user, citizenFiscalCode, iuv, ( (Error) response.getEntity () ).getDetail () );
			return response;
		}
		Log.info ( methodName + "controllo corrispondenza pagatore OK" );

		// controllo del corrispondenza ente
		if ( !organizationFiscalCode.equals ( pagamentoEntity.getEpayTTipoPagamento ().getEpayTEnti ().getCodiceFiscale () ) ) {
			Response response = generateNotFoundErrorResponse ( SERVICE_PAYMENT_RECEIPT, ERROR_PAGAMENTO_NON_ASSOCIATO_A_ENTE );
			chiamataEsternaNonValidaService.track ( null, user, citizenFiscalCode, iuv, ( (Error) response.getEntity () ).getDetail () );
			return response;
		}
		Log.info ( methodName + "controllo corrispondenza ente OK" );

		// ottiene l'ultimo idRegistro di epay_t_registro_versamenti pagato
		EpayTRegistroVersamentiReflection versamento
			= registroVersamentiService.findMaxIdByIdPagamentoAndStatoPagamento ( pagamentoEntity.getIdPagamento (), StatoPagamentiEnum.INOLTRATO.getId () );
		if ( versamento == null ) {
			Response response
				= generateNotFoundErrorResponse ( SERVICE_PAYMENT_RECEIPT, ERROR_REGISTRO_NOT_FOUND, pagamentoEntity.getIdPagamento () );
			chiamataEsternaNonValidaService.track ( null, user, citizenFiscalCode, iuv, ( (Error) response.getEntity () ).getDetail () );
			return response;
		}
		Log.info ( methodName + "ottenuto l'ultimo idRegistro:" + versamento.getIdRegistro () );

		PaymentReceipt result = new PaymentReceipt ();

		// ottiene la ricevuta PDF: prova a ottenerla dalla RT
		EpayTRtPdf rt = rtService.findPdfByIdRegistroAndCodEsitoPagamento ( versamento.getIdRegistro (), Integer.valueOf ( "0" ) ); // Valutare se fare un'enum
		if ( rt != null ) {
			result.setReceipt ( rt.getRicevutaPdf () );
		} else {
			// ... se non la ottiene dalla RT prova a ottenerla dalla quietanza
			EpayTEsitiRicevuti esito = esitiRicevutiService.findByIdRegistroAndIdQuietanzaNotNull ( versamento.getIdRegistro () );
			if ( esito == null || esito.getEpayTQuietanzaEsito () != null && esito.getEpayTQuietanzaEsito ().getRicevutaPdf () != null ) {
				if ( esito != null ) {
					result.setReceipt ( esito.getEpayTQuietanzaEsito ().getRicevutaPdf () );
				} else {
					Response response
									= generateNotFoundErrorResponse ( SERVICE_PAYMENT_RECEIPT, ERROR_RECEIPT_NOT_FOUND, pagamentoEntity.getIdPagamento () );
					chiamataEsternaNonValidaService.track ( null, user, citizenFiscalCode, iuv, ( (Error) response.getEntity () ).getDetail () );
					return response;
				}
			} else {
				Response response
					= generateNotFoundErrorResponse ( SERVICE_PAYMENT_RECEIPT, ERROR_RECEIPT_NOT_FOUND, pagamentoEntity.getIdPagamento () );
				chiamataEsternaNonValidaService.track ( null, user, citizenFiscalCode, iuv, ( (Error) response.getEntity () ).getDetail () );
				return response;
			}
		}
		Log.info ( methodName + "ottenuta la ricevuta PDF" );

		Log.info ( methodName + "END" );
		return Response.status ( Response.Status.OK ).entity ( result ).build ();
	}

	private List<String> getNotValidInputs ( String organizationFiscalCode, String citizenFiscalCode, String iuv ) {
		List<String> notValids = new LinkedList<> ();

		if ( StringUtils.isBlank ( organizationFiscalCode ) ) {
			notValids.add ( SERVICE_FIELDS_PAYMENT_RECEIPT__ORGANIZATION_FISCAL_CODE );
		} else if ( !organizationFiscalCode.matches ( REGEX_IUV_CF ) ) {
			notValids.add ( SERVICE_FIELDS_PAYMENT_RECEIPT__ORGANIZATION_FISCAL_CODE );
		}

		if ( StringUtils.isBlank ( citizenFiscalCode ) ) {
			notValids.add ( SERVICE_FIELDS_PAYMENT_RECEIPT__CITIZEN_FISCAL_CODE );
		} else if ( !citizenFiscalCode.matches ( REGEX_IUV_CF ) ) {
			notValids.add ( SERVICE_FIELDS_PAYMENT_RECEIPT__CITIZEN_FISCAL_CODE );
		}

		if ( StringUtils.isBlank ( iuv ) ) {
			notValids.add ( SERVICE_FIELDS_PAYMENT_RECEIPT__IUV );
		} else if ( !iuv.matches ( REGEX_IUV_CF ) ) {
			notValids.add ( SERVICE_FIELDS_PAYMENT_RECEIPT__IUV );
		}

		return notValids;
	}
}
