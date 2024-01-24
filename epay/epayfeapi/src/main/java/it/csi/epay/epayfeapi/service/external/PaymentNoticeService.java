/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.service.external;

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

import java.util.LinkedList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;
import org.openapitools.model.Error;
import org.openapitools.model.PaymentNotice;

import io.quarkus.logging.Log;
import it.csi.epay.epayfeapi.dto.PagamentoDTO;
import it.csi.epay.epayfeapi.entity.EpayDChiamanteEsterno;
import it.csi.epay.epayfeapi.entity.EpayTEnti;
import it.csi.epay.epayfeapi.entity.EpayTPagamento;
import it.csi.epay.epayfeapi.security.AuthenticationContext;
import it.csi.epay.epayfeapi.security.Scopes;
import it.csi.epay.epayfeapi.security.User;
import it.csi.epay.epayfeapi.service.ChiamanteAutorizzazioneChiamanteService;
import it.csi.epay.epayfeapi.service.ChiamanteEsternoService;
import it.csi.epay.epayfeapi.service.ChiamataEsternaNonValidaService;
import it.csi.epay.epayfeapi.service.CreaAvvisoPagamentoSpontaneoService;
import it.csi.epay.epayfeapi.service.EnteService;
import it.csi.epay.epayfeapi.service.PagamentoService;
import it.csi.epay.epayfeapi.service.TracciabilitaChiamanteEsternoService;


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
	public Response getPaymentNotice ( String organizationFiscalCode, String citizenFiscalCode, String iuv ) {
		String methodName = "[getPaymentNotice] ";
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
			Response response = generateForbiddenResponse ( SERVICE_PAYMENT_NOTICE, user.getName () );
			chiamataEsternaNonValidaService.track ( null, user, organizationFiscalCode, null, ( (Error) response.getEntity () ).getDetail () );
			return response;
		}
		Log.info ( methodName + "chiamanteEsternoEntity:" + chiamanteEsternoEntity );
		//
		// 2. tracciamento della chiamata
		tracciabilitaChiamanteEsternoService.trackExternalCall ( null, organizationFiscalCode, chiamanteEsternoEntity, iuv, user, null );
		Log.info ( methodName + "chiamanteEsterno tracciato" );
		//
		// 3. validazione autorizzazione
		if ( chiamanteAutorizzazioneChiamanteService.countByCodiceChiamanteAndCodiceAutorizzazioneChiamante (
			user.getName (), Scopes.AVVISO_PAGAMENTO.name () ) < 1 ) {
			Response response = generateUnauthorizedResponse ( SERVICE_PAYMENT_NOTICE );
			chiamataEsternaNonValidaService.track ( null, user, organizationFiscalCode, null, ( (Error) response.getEntity () ).getDetail () );
			return response;
		}
		Log.info ( methodName + "authorization OK" );

		// validazione input
		List<String> notValids = getNotValidInputs ( organizationFiscalCode, citizenFiscalCode, iuv );
		if ( !notValids.isEmpty () ) {
			Response response = generateValidationErrorResponse ( SERVICE_PAYMENT_NOTICE, notValids );
			chiamataEsternaNonValidaService.track ( null, user, organizationFiscalCode, null, ( (Error) response.getEntity () ).getDetail () );
			return response;
		}
		Log.info ( methodName + "validation OK" );

		/* --- logica di business --- */

		// ottiene l'ente corrispondente al codice fiscale
		EpayTEnti enteEntity = enteService.findByCodiceFiscale ( organizationFiscalCode );
		if ( enteEntity == null ) {
			Response response = generateNotFoundErrorResponse ( SERVICE_PAYMENT_NOTICE, ERROR_ENTE_NOT_FOUND, organizationFiscalCode );
			chiamataEsternaNonValidaService.track ( null, user, organizationFiscalCode, null, ( (Error) response.getEntity () ).getDetail () );
			return response;
		}
		Log.info ( methodName + "ente:" + enteEntity );

		// controllo dello IUV
		EpayTPagamento pagamentoEntity = pagamentoService.getPagamentoActiveAndPayableByIUV ( iuv );
		if ( pagamentoEntity == null ) {
			Response response = generateNotFoundErrorResponse ( SERVICE_PAYMENT_NOTICE, ERROR_PAGAMENTO_NOT_FOUND_OR_NON_VALIDO_OR_NON_PAGABILE );
			chiamataEsternaNonValidaService.track ( null, user, citizenFiscalCode, iuv, ( (Error) response.getEntity () ).getDetail () );
			return response;
		}
		Log.info ( methodName + "controllo IUV OK" );

		// controllo del corrispondenza pagatore
		if ( !citizenFiscalCode.equals ( pagamentoEntity.getEpayTAnagrafica ().getCodiceFiscale () ) ) {
			Response response = generateNotFoundErrorResponse ( SERVICE_PAYMENT_NOTICE, ERROR_PAGAMENTO_NON_ASSOCIATO_A_CODICE_FISCALE );
			chiamataEsternaNonValidaService.track ( null, user, citizenFiscalCode, iuv, ( (Error) response.getEntity () ).getDetail () );
			return response;
		}
		Log.info ( methodName + "controllo corrispondenza pagatore OK" );

		// controllo del corrispondenza ente
		if ( !organizationFiscalCode.equals ( pagamentoEntity.getEpayTTipoPagamento ().getEpayTEnti ().getCodiceFiscale () ) ) {
			Response response = generateBusinessErrorResponse ( SERVICE_PAYMENT_NOTICE, ERROR_PAGAMENTO_NON_ASSOCIATO_A_ENTE );
			chiamataEsternaNonValidaService.track ( null, user, citizenFiscalCode, iuv, ( (Error) response.getEntity () ).getDetail () );
			return response;
		}
		Log.info ( methodName + "controllo corrispondenza ente OK" );

		// controllo della tipologia di pagamento (marca da bollo)
		PagamentoDTO pagamento = pagamentoService.mapPagamentoEsteso ( pagamentoEntity );
		if ( pagamento.getTipoPagamento ().getTipologiaPagamento ().getCodice ().equals ( "MABL" ) ) {
			Response response = generateBusinessErrorResponse ( SERVICE_PAYMENT_NOTICE, ERROR_TIPOLOGIA_PAGAMENTO_MARCA_DA_BOLLO );
			chiamataEsternaNonValidaService.track ( null, user, citizenFiscalCode, iuv, ( (Error) response.getEntity () ).getDetail () );
			return response;
		}
		Log.info ( methodName + "controllo tipologia di pagamento OK" );

		PaymentNotice result = new PaymentNotice ();
		try {
			result.setNotice ( creaAvvisoPagamentoSpontaneoService.buildAvvisoPagamentoSpontaneo ( pagamento ) );

		} catch ( Exception e ) {
			Response response = generateNotFoundErrorResponse ( SERVICE_PAYMENT_NOTICE, ERROR_PDF_GENERATION, pagamentoEntity.getIdPagamento () );
			chiamataEsternaNonValidaService.track ( null, user, citizenFiscalCode, iuv, ( (Error) response.getEntity () ).getDetail () );
			return response;
		}

		Log.info ( methodName + "END" );
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
