/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.service.external;

import io.quarkus.logging.Log;
import it.csi.epay.epayfeapi.enumeration.Scopes;
import it.csi.epay.epayfeapi.security.AuthenticationContext;
import it.csi.epay.epayfeapi.service.ChiamanteAutorizzazioneChiamanteService;
import it.csi.epay.epayfeapi.service.ChiamanteEsternoService;
import it.csi.epay.epayfeapi.service.ChiamataEsternaNonValidaService;
import it.csi.epay.epayfeapi.service.CreaAvvisoPagamentoSpontaneoService;
import it.csi.epay.epayfeapi.service.EnteService;
import it.csi.epay.epayfeapi.service.PagamentoService;
import it.csi.epay.epayfeapi.service.TracciabilitaChiamanteEsternoService;
import org.apache.commons.lang3.StringUtils;
import org.openapitools.model.Error;
import org.openapitools.model.PaymentNotice;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.util.LinkedList;
import java.util.List;

import static it.csi.epay.epayfeapi.util.Constants.ERROR_ENTE_NOT_FOUND;
import static it.csi.epay.epayfeapi.util.Constants.ERROR_PAGAMENTO_NON_ASSOCIATO_A_CODICE_FISCALE;
import static it.csi.epay.epayfeapi.util.Constants.ERROR_PAGAMENTO_NON_ASSOCIATO_A_ENTE;
import static it.csi.epay.epayfeapi.util.Constants.ERROR_PAGAMENTO_NOT_FOUND_OR_NON_VALIDO_OR_NON_PAGABILE;
import static it.csi.epay.epayfeapi.util.Constants.ERROR_PDF_GENERATION;
import static it.csi.epay.epayfeapi.util.Constants.ERROR_TIPOLOGIA_PAGAMENTO_MARCA_DA_BOLLO;
import static it.csi.epay.epayfeapi.util.Constants.REGEX_IUV_CF;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_FIELDS_PAYMENT_NOTICE__IUV;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_FIELDS_PAYMENT_NOTICE__ORGANIZATION_FISCAL_CODE;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_PAYMENT_NOTICE;
import static it.csi.epay.epayfeapi.util.ResponseUtil.generateBusinessErrorResponse;
import static it.csi.epay.epayfeapi.util.ResponseUtil.generateForbiddenResponse;
import static it.csi.epay.epayfeapi.util.ResponseUtil.generateNotFoundErrorResponse;
import static it.csi.epay.epayfeapi.util.ResponseUtil.generateUnauthorizedResponse;
import static it.csi.epay.epayfeapi.util.ResponseUtil.generateValidationErrorResponse;
import static it.csi.epay.epayfeapi.util.ResponseUtil.generateUnauthorizedEnteResponse;


@ApplicationScoped
@Transactional
public class PaymentNoticeService {

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
	TracciabilitaChiamanteEsternoService tracciabilitaChiamanteEsternoService;

	@Inject
	EnteService enteService;

	@Inject
	CreaAvvisoPagamentoSpontaneoService creaAvvisoPagamentoSpontaneoService;

	/*
	 * - Servizio 3 - PDF relativo ad un avviso di pagamento
	 */
	public Response getPaymentNotice ( String organizationFiscalCode, String citizenFiscalCode, String iuv, long initialMoment, String serviceName ) {
		var methodName = "[CF-V0-getPaymentNotice-V0] ";
		Log.infof ( "%sBEGIN", methodName );
		Log.infof ( "%sparam organizationFiscalCode:%s", methodName, organizationFiscalCode );
		Log.infof ( "%sparam citizenFiscalCode:%s", methodName, citizenFiscalCode );
		Log.infof ( "%sparam iuv:%s", methodName, iuv );

		var user = authenticationContext.getCurrentUser ();
		Log.infof ( "%suser:%s", methodName, user );

		/* --- validazione --- */
		// autorizzazione
		// 1. ottiene il chiamante esterno per il tracciamento della chiamata e la validazione dell'autorizzazione
		var chiamanteEsternoEntity = chiamanteEsternoService.findByCodiceChiamante ( user.getName () );
		if ( chiamanteEsternoEntity == null ) {
			var response = generateForbiddenResponse ( SERVICE_PAYMENT_NOTICE, user.getName () );
			chiamataEsternaNonValidaService.track ( null, user, organizationFiscalCode, null, ( (Error) response.getEntity () ).getDetail (), initialMoment, serviceName );
			return response;
		}
		Log.infof ( "%schiamanteEsternoEntity:%s", methodName, chiamanteEsternoEntity );
		//
		// 2. tracciamento della chiamata
		tracciabilitaChiamanteEsternoService.trackExternalCall ( null, organizationFiscalCode, chiamanteEsternoEntity, iuv, user, null, initialMoment, serviceName );
		Log.infof ( "%schiamanteEsterno tracciato", methodName );
		//
		// 3. validazione autorizzazione
		if ( chiamanteAutorizzazioneChiamanteService.countByCodiceChiamanteAndCodiceAutorizzazioneChiamante (
						user.getName (), Scopes.AVVISO_PAGAMENTO.name () ) < 1 ) {
			var response = generateUnauthorizedResponse ( SERVICE_PAYMENT_NOTICE );
			chiamataEsternaNonValidaService.track ( null, user, organizationFiscalCode, null, ( (Error) response.getEntity () ).getDetail (), initialMoment, serviceName );
			return response;
		}
		Log.infof ( "%sauthorization OK", methodName );

		// validazione input
		var notValids = getNotValidInputs ( organizationFiscalCode, citizenFiscalCode, iuv );
		if ( !notValids.isEmpty () ) {
			var response = generateValidationErrorResponse ( SERVICE_PAYMENT_NOTICE, notValids );
			chiamataEsternaNonValidaService.track ( null, user, organizationFiscalCode, null, ( (Error) response.getEntity () ).getDetail (), initialMoment, serviceName );
			return response;
		}
		Log.infof ( "%svalidation OK", methodName );

		/* --- logica di business --- */

		// ottiene l'ente corrispondente al codice fiscale
		var enteEntity = enteService.findByCodiceFiscale ( organizationFiscalCode );
		if ( enteEntity == null ) {
			var response = generateNotFoundErrorResponse ( SERVICE_PAYMENT_NOTICE, ERROR_ENTE_NOT_FOUND, organizationFiscalCode );
			chiamataEsternaNonValidaService.track ( null, user, organizationFiscalCode, null, ( (Error) response.getEntity () ).getDetail (), initialMoment, serviceName );
			return response;
		}
		if ( enteEntity.getFlagAdesioneCittaFacile()==null || !enteEntity.getFlagAdesioneCittaFacile()) {
			var response = generateUnauthorizedEnteResponse ( SERVICE_PAYMENT_NOTICE);
			chiamataEsternaNonValidaService.track ( null, user, organizationFiscalCode, null, ( (Error) response.getEntity () ).getDetail (), initialMoment, serviceName );
			return response;
		}
		Log.infof ( "%sente:%s", methodName, enteEntity );

		// controllo dello IUV
		var pagamentoEntity = pagamentoService.getPagamentoActiveAndPayableByIUV ( iuv );
		if ( pagamentoEntity == null ) {
			var response = generateNotFoundErrorResponse ( SERVICE_PAYMENT_NOTICE, ERROR_PAGAMENTO_NOT_FOUND_OR_NON_VALIDO_OR_NON_PAGABILE );
			chiamataEsternaNonValidaService.track ( null, user, citizenFiscalCode, iuv, ( (Error) response.getEntity () ).getDetail (), initialMoment, serviceName );
			return response;
		}
		Log.infof ( "%scontrollo IUV OK", methodName );

		// controllo del corrispondenza pagatore
		if ( !citizenFiscalCode.equals ( pagamentoEntity.getEpayTAnagrafica ().getCodiceFiscale () ) ) {
			var response = generateNotFoundErrorResponse ( SERVICE_PAYMENT_NOTICE, ERROR_PAGAMENTO_NON_ASSOCIATO_A_CODICE_FISCALE );
			chiamataEsternaNonValidaService.track ( null, user, citizenFiscalCode, iuv, ( (Error) response.getEntity () ).getDetail (), initialMoment, serviceName );
			return response;
		}
		Log.infof ( "%scontrollo corrispondenza pagatore OK", methodName );

		// controllo del corrispondenza ente
		if ( !organizationFiscalCode.equals ( pagamentoEntity.getEpayTTipoPagamento ().getEpayTEnti ().getCodiceFiscale () ) ) {
			var response = generateBusinessErrorResponse ( SERVICE_PAYMENT_NOTICE, ERROR_PAGAMENTO_NON_ASSOCIATO_A_ENTE );
			chiamataEsternaNonValidaService.track ( null, user, citizenFiscalCode, iuv, ( (Error) response.getEntity () ).getDetail (), initialMoment, serviceName );
			return response;
		}
		Log.infof ( "%scontrollo corrispondenza ente OK", methodName );

		// controllo della tipologia di pagamento (marca da bollo)
		var pagamento = pagamentoService.mapPagamentoEsteso ( pagamentoEntity );
		if ( pagamento.getTipoPagamento ().getTipologiaPagamento ().getCodice ().equals ( "MABL" ) ) {
			var response = generateBusinessErrorResponse ( SERVICE_PAYMENT_NOTICE, ERROR_TIPOLOGIA_PAGAMENTO_MARCA_DA_BOLLO );
			chiamataEsternaNonValidaService.track ( null, user, citizenFiscalCode, iuv, ( (Error) response.getEntity () ).getDetail (), initialMoment, serviceName );
			return response;
		}
		Log.infof ( "%scontrollo tipologia di pagamento OK", methodName );

		var result = new PaymentNotice ();
		try {
			result.setNotice ( creaAvvisoPagamentoSpontaneoService.buildAvvisoPagamentoSpontaneo ( pagamento ) );
		} catch ( Exception e ) {
			var response = generateNotFoundErrorResponse ( SERVICE_PAYMENT_NOTICE, ERROR_PDF_GENERATION, pagamentoEntity.getIdPagamento () );
			chiamataEsternaNonValidaService.track ( null, user, citizenFiscalCode, iuv, ( (Error) response.getEntity () ).getDetail (), initialMoment, serviceName );
			return response;
		}

		Log.infof ( "%sEND", methodName );
		return Response.status ( Response.Status.OK ).entity ( result ).build ();
	}

	private List<String> getNotValidInputs ( String organizationFiscalCode, String citizenFiscalCode, String iuv ) {
		List<String> notValids = new LinkedList<> ();

		if ( StringUtils.isBlank ( organizationFiscalCode ) ) {
			notValids.add ( SERVICE_FIELDS_PAYMENT_NOTICE__ORGANIZATION_FISCAL_CODE );
		} else if ( !organizationFiscalCode.matches ( REGEX_IUV_CF ) ) {
			notValids.add ( SERVICE_FIELDS_PAYMENT_NOTICE__ORGANIZATION_FISCAL_CODE );
		}

		if ( StringUtils.isBlank ( citizenFiscalCode ) ) {
			notValids.add ( SERVICE_FIELDS_PAYMENT_NOTICE__ORGANIZATION_FISCAL_CODE );
		} else if ( !citizenFiscalCode.matches ( REGEX_IUV_CF ) ) {
			notValids.add ( SERVICE_FIELDS_PAYMENT_NOTICE__ORGANIZATION_FISCAL_CODE );
		}

		if ( StringUtils.isBlank ( iuv ) ) {
			notValids.add ( SERVICE_FIELDS_PAYMENT_NOTICE__IUV );
		} else if ( !iuv.matches ( REGEX_IUV_CF ) ) {
			notValids.add ( SERVICE_FIELDS_PAYMENT_NOTICE__IUV );
		}

		return notValids;
	}
}
