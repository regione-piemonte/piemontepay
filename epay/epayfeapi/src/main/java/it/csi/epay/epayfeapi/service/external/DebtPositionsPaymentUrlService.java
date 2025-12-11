/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.service.external;

import io.quarkus.logging.Log;
import it.csi.epay.epayfeapi.enumeration.Scopes;
import it.csi.epay.epayfeapi.enumeration.StatoPagamento;
import it.csi.epay.epayfeapi.mapper.PagamentoMapper;
import it.csi.epay.epayfeapi.security.AuthenticationContext;
import it.csi.epay.epayfeapi.service.ChiamanteAutorizzazioneChiamanteService;
import it.csi.epay.epayfeapi.service.ChiamanteEsternoService;
import it.csi.epay.epayfeapi.service.ChiamataEsternaNonValidaService;
import it.csi.epay.epayfeapi.service.EnteService;
import it.csi.epay.epayfeapi.service.ModelloUnicoHelperService;
import it.csi.epay.epayfeapi.service.PagamentoService;
import it.csi.epay.epayfeapi.service.TracciabilitaChiamanteEsternoService;
import org.apache.commons.lang3.StringUtils;
import org.openapitools.model.Error;
import org.openapitools.model.PaymentIuv;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import static it.csi.epay.epayfeapi.enumeration.StatoPagamento.ANNULLATO;
import static it.csi.epay.epayfeapi.enumeration.StatoPagamento.TRANSAZIONE_ERRORE;
import static it.csi.epay.epayfeapi.util.Constants.ERROR_ENTE_NOT_FOUND;
import static it.csi.epay.epayfeapi.util.Constants.ERROR_IUV_IS_OF_AN_INVALID_PAYMENT;
import static it.csi.epay.epayfeapi.util.Constants.ERROR_IUV_NOT_FOUND_OR_INVALID_PAYMENT;
import static it.csi.epay.epayfeapi.util.Constants.ERROR_REQUIRES_COST_UPDATE_TRUE;
import static it.csi.epay.epayfeapi.util.Constants.REGEX_IUV_CF;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_FIELDS_GET_PAYMENT_URL__IUV;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_FIELDS_GET_PAYMENT_URL__ORGANIZATION_FISCAL_CODE;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_GET_PAYMENT_URL;
import static it.csi.epay.epayfeapi.util.Constants.ERROR_IUV_IS_OF_AN_ZERO_PAYMENT;
import static it.csi.epay.epayfeapi.util.ResponseUtil.generateBusinessErrorResponse;
import static it.csi.epay.epayfeapi.util.ResponseUtil.generateForbiddenResponse;
import static it.csi.epay.epayfeapi.util.ResponseUtil.generateInternalErrorResponse;
import static it.csi.epay.epayfeapi.util.ResponseUtil.generateNotFoundErrorResponse;
import static it.csi.epay.epayfeapi.util.ResponseUtil.generateUnauthorizedResponse;
import static it.csi.epay.epayfeapi.util.ResponseUtil.generateValidationErrorResponse;
import static it.csi.epay.epayfeapi.util.ResponseUtil.generateUnauthorizedEnteResponse;


/*
 * Servizio 7, pagamento posizione debitoria
 * (doGetPagamentoIUVChiamanteEsterno)
 */
@ApplicationScoped
@Transactional
public class DebtPositionsPaymentUrlService {

	@Inject
	AuthenticationContext authenticationContext;

	@Inject
	ChiamanteEsternoService chiamanteEsternoService;

	@Inject
	PagamentoService pagamentoService;

	@Inject
	EnteService enteService;

	@Inject
	TracciabilitaChiamanteEsternoService tracciabilitaChiamanteEsternoService;

	@Inject
	ChiamanteAutorizzazioneChiamanteService chiamanteAutorizzazioneChiamanteService;

	@Inject
	ChiamataEsternaNonValidaService chiamataEsternaNonValidaService;

	@Inject
	PagamentoMapper pagamentoMapper;

	@Inject
	ModelloUnicoHelperService modelloUnicoHelperService;

	public Response getPaymentUrl ( String organizationFiscalCode, String iuv, long initialMoment, String serviceName ) {
		var methodName = "[getPaymentUrl] ";
		Log.infof ( "%sBEGIN", methodName );
		Log.infof ( "%sparam iuv:%s", methodName, iuv );
		var user = authenticationContext.getCurrentUser ();
		Log.info ( methodName + "user:" + user );

		// 1. ottiene il chiamante esterno per il tracciamento della chiamata e la validazione dell'autorizzazione
		var chiamanteEsternoEntity = chiamanteEsternoService.findByCodiceChiamante ( user.getName () );
		if ( null == chiamanteEsternoEntity ) {
			var response = generateForbiddenResponse ( SERVICE_GET_PAYMENT_URL, user.getName () );
			chiamataEsternaNonValidaService.track ( null, user, organizationFiscalCode, iuv, ( (Error) response.getEntity () ).getDetail (), initialMoment, serviceName );
			return response;
		}
		Log.infof ( "%schiamanteEsternoEntity:%s", methodName, chiamanteEsternoEntity );

		// 2. tracciamento della chiamata
		var track = tracciabilitaChiamanteEsternoService.trackExternalCall ( null, organizationFiscalCode, chiamanteEsternoEntity, iuv, user, null, initialMoment, serviceName );
		Log.infof ( "%schiamanteEsterno tracciato, idChiamata:%s", methodName, track.getIdChiamata () );
		//

		// 3. validazione autorizzazione
		if ( chiamanteAutorizzazioneChiamanteService.countByCodiceChiamanteAndCodiceAutorizzazioneChiamante ( user.getName (),
						Scopes.PAGAMENTO_URL.name () ) < 1 ) {
			var response = generateUnauthorizedResponse ( SERVICE_GET_PAYMENT_URL );
			chiamataEsternaNonValidaService.track ( null, user, organizationFiscalCode, iuv, ( (Error) response.getEntity () ).getDetail (), initialMoment, serviceName );
			return response;
		}
		Log.infof ( "%sauthorization OK", methodName );

		// validazione input
		var notValids = getNotValidInputs ( organizationFiscalCode, iuv );
		if ( !notValids.isEmpty () ) {
			var response = generateValidationErrorResponse ( SERVICE_GET_PAYMENT_URL, notValids );
			chiamataEsternaNonValidaService.track ( null, user, organizationFiscalCode, iuv, ( (Error) response.getEntity () ).getDetail (), initialMoment, serviceName );
			return response;
		}
		Log.infof ( "%svalidation OK", methodName );

		// verifica ente
		var ente = enteService.findByCodiceFiscale ( organizationFiscalCode );
		if ( null == ente ) {
			var response = generateNotFoundErrorResponse ( SERVICE_GET_PAYMENT_URL, ERROR_ENTE_NOT_FOUND, organizationFiscalCode );
			chiamataEsternaNonValidaService.track ( null, user, organizationFiscalCode, iuv, ( (Error) response.getEntity () ).getDetail (), initialMoment, serviceName );
			return response;
		}
		if ( ente.getFlagAdesioneCittaFacile()==null || !ente.getFlagAdesioneCittaFacile()) {
			var response = generateUnauthorizedEnteResponse ( SERVICE_GET_PAYMENT_URL);
			chiamataEsternaNonValidaService.track ( null, user, organizationFiscalCode, null, ( (Error) response.getEntity () ).getDetail (), initialMoment, serviceName );
			return response;
		}
		Log.infof ( "%sfetched ente[id]:%d", methodName, ente.getIdEnte () );

		// verifica dello IUV e quindi stato pagamento
		var pagamento = pagamentoMapper.toDto ( pagamentoService.getPagamentoActiveAndPayableByIUV ( iuv ) );
		if ( pagamento == null ) {
			var response = generateBusinessErrorResponse ( SERVICE_GET_PAYMENT_URL, ERROR_IUV_NOT_FOUND_OR_INVALID_PAYMENT );
			chiamataEsternaNonValidaService.track ( null, user, organizationFiscalCode, iuv, ( (Error) response.getEntity () ).getDetail (), initialMoment, serviceName );
			return response;
		}
		if ( pagamento.getRequiresCostUpdate () != null && pagamento.getRequiresCostUpdate () ) {
			var response = generateInternalErrorResponse ( SERVICE_GET_PAYMENT_URL, ERROR_REQUIRES_COST_UPDATE_TRUE );
			chiamataEsternaNonValidaService.track ( null, user, organizationFiscalCode, iuv, ( (Error) response.getEntity () ).getDetail (), initialMoment, serviceName );
			return response;
		}

		// verifica se l'ente specificato nel tipo pagamento del pagamento corrisponde all'input
		if ( !pagamento.getTipoPagamento ().getEpayTEnti ().getCodiceFiscale ().equalsIgnoreCase ( organizationFiscalCode ) ) {
			var response = generateUnauthorizedResponse ( SERVICE_GET_PAYMENT_URL );
			chiamataEsternaNonValidaService.track ( null, user, organizationFiscalCode, iuv, ( (Error) response.getEntity () ).getDetail (), initialMoment, serviceName );
			return response;
		}

		// controlla se il pagamento e' stato gia' tentato, se , non glielo fai pagare
		var statoPagamento = StatoPagamento.findById ( pagamento.getIdStatoCorrente () );
		if ( statoPagamento == ANNULLATO || statoPagamento == TRANSAZIONE_ERRORE ) {
			var response = generateBusinessErrorResponse ( SERVICE_GET_PAYMENT_URL, ERROR_IUV_IS_OF_AN_INVALID_PAYMENT );
			chiamataEsternaNonValidaService.track ( null, user, organizationFiscalCode, iuv, ( (Error) response.getEntity () ).getDetail (), initialMoment, serviceName );
			return response;
		}
		
		// controlla se l'importo del pagamento e' maggiore di zero
		if (pagamento.getImporto().compareTo(new BigDecimal(0))<1)
		{
			var response = generateBusinessErrorResponse ( SERVICE_GET_PAYMENT_URL, ERROR_IUV_IS_OF_AN_ZERO_PAYMENT );
			chiamataEsternaNonValidaService.track ( null, user, organizationFiscalCode, iuv, ( (Error) response.getEntity () ).getDetail (), initialMoment, serviceName );
			return response;
		}
		

		// checkout call
		var fromCheckout = modelloUnicoHelperService.manageModelloUnico ( organizationFiscalCode, pagamento, iuv, chiamanteEsternoEntity, track,
						SERVICE_GET_PAYMENT_URL, serviceName,initialMoment );
		if ( fromCheckout.isKO () ) {
			return fromCheckout.getResponse ();
		}

		// costruisce la response
		var paymentIuv = new PaymentIuv ();
		paymentIuv.setPaymentUrl ( fromCheckout.getPaymentUrl () );

		Log.infof ( "%sEND", methodName );
		return Response.status ( Response.Status.OK ).entity ( paymentIuv ).build ();
	}

	private List<String> getNotValidInputs ( String organizationFiscalCode, String iuv ) {
		List<String> notValids = new LinkedList<> ();

		if ( StringUtils.isBlank ( organizationFiscalCode ) ) {
			notValids.add ( SERVICE_FIELDS_GET_PAYMENT_URL__ORGANIZATION_FISCAL_CODE );
		} else if ( !organizationFiscalCode.matches ( REGEX_IUV_CF ) ) {
			notValids.add ( SERVICE_FIELDS_GET_PAYMENT_URL__ORGANIZATION_FISCAL_CODE );
		}

		if ( StringUtils.isBlank ( iuv ) ) {
			notValids.add ( SERVICE_FIELDS_GET_PAYMENT_URL__IUV );
		} else if ( !iuv.matches ( REGEX_IUV_CF ) ) {
			notValids.add ( SERVICE_FIELDS_GET_PAYMENT_URL__IUV );
		}

		return notValids;
	}
}
