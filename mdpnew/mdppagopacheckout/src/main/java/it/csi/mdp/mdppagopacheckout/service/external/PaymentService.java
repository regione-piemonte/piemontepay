/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopacheckout.service.external;

import io.quarkus.logging.Log;
import it.csi.mdp.mdppagopacheckout.model.CheckoutPagoPAInput;
import it.csi.mdp.mdppagopacheckout.model.CheckoutPagoPAOKOutput;
import it.csi.mdp.mdppagopacheckout.model.CheckoutPagoPAOutput;
import it.csi.mdp.mdppagopacheckout.model.CheckoutPagoPAPaymentNoticeInput;
import it.csi.mdp.mdppagopacheckout.model.CheckoutPagoPAReturnUrlInput;
import it.csi.mdp.mdppagopacheckout.model.IuvOtticoAndErrorMessage;
import it.csi.mdp.mdppagopacheckout.rest.CheckoutPagoPAAdapterClient;
import it.csi.mdp.mdppagopacheckout.service.ApplicationCustomFieldService;
import it.csi.mdp.mdppagopacheckout.service.ConfigService;
import it.csi.mdp.mdppagopacheckout.service.GdeService;
import it.csi.mdp.mdppagopacheckout.service.IuvOtticiService;
import it.csi.mdp.mdppagopacheckout.service.TransazioneIuvService;
import it.csi.mdp.mdppagopacheckout.service.TransazioneService;
import it.csi.mdp.mdppagopacheckout.util.MacUtil;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openapitools.model.Cart;
import org.openapitools.model.PaymentNotice;
import org.openapitools.model.Transaction;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import static it.csi.mdp.mdppagopacheckout.util.Constants.APPLICATION_ID_UNMATCH;
import static it.csi.mdp.mdppagopacheckout.util.Constants.CALL_TO_PAGOPA_ERROR;
import static it.csi.mdp.mdppagopacheckout.util.Constants.CODICE_INDENTIFICATIVO_UNIVOCO_BENEFICIARIO_FIELDNAME;
import static it.csi.mdp.mdppagopacheckout.util.Constants.CONTO_POSTE_FIELDNAME;
import static it.csi.mdp.mdppagopacheckout.util.Constants.DENOMINAZIONE_BENEFICIARIO;
import static it.csi.mdp.mdppagopacheckout.util.Constants.EMAIL_NOT_VALID;
import static it.csi.mdp.mdppagopacheckout.util.Constants.IUV_NOT_FOUND;
import static it.csi.mdp.mdppagopacheckout.util.Constants.MAIL_REGEX;
import static it.csi.mdp.mdppagopacheckout.util.Constants.MDP_RETURN_URL_CANCEL;
import static it.csi.mdp.mdppagopacheckout.util.Constants.MDP_RETURN_URL_ERROR;
import static it.csi.mdp.mdppagopacheckout.util.Constants.MDP_RETURN_URL_OK;
import static it.csi.mdp.mdppagopacheckout.util.Constants.M_PARAM;
import static it.csi.mdp.mdppagopacheckout.util.Constants.OCP_APIM_SUBSCRIPTION;
import static it.csi.mdp.mdppagopacheckout.util.Constants.PASSPHRASE;
import static it.csi.mdp.mdppagopacheckout.util.Constants.Q_PARAM;
import static it.csi.mdp.mdppagopacheckout.util.ResponseUtil.generateInternalErrorResponse;
import static it.csi.mdp.mdppagopacheckout.util.ResponseUtil.generateNotFoundUrl;
import static it.csi.mdp.mdppagopacheckout.util.ResponseUtil.generateValidationErrorResponse;


@SuppressWarnings ( "unused" )
@ApplicationScoped
@Transactional
public class PaymentService {

	@Inject
	IuvOtticiService iuvOtticiService;

	@Inject
	ApplicationCustomFieldService applicationCustomFieldService;

	@Inject
	TransazioneService transazioneService;

	@Inject
	GdeService gdeService;

	@Inject
	CheckoutPagoPAAdapterClient checkoutPagoPAAdapterClient;

	@Inject
	ConfigService configService;

	@Inject
	TransazioneIuvService transazioneIuvService;

	@SuppressWarnings ( "unused" )
	public Response manageCart ( Cart cart ) {
		Log.info ( "Called method manageCart" );

		// Check iuv and application id
		for ( PaymentNotice paymentNotice : cart.getPaymentNotices () ) {
			var iuvOtticoAndErrorMessage = checkIUVandApplicationID ( paymentNotice );
			if ( iuvOtticoAndErrorMessage.getError () != null ) {
				return generateValidationErrorResponse ( iuvOtticoAndErrorMessage.getError () );
			}
		}

		// Email validation
		if ( cart.getEmailNotice () == null || cart.getEmailNotice ().isEmpty () ) {
			Log.info ( "Email field is empty" );
		} else {
			if ( !cart.getEmailNotice ().matches ( MAIL_REGEX ) ) {
				return generateValidationErrorResponse ( EMAIL_NOT_VALID );
			}
		}

		// init transazione
		var transactionId = transazioneService.initTransazione ( cart.getPaymentNotices ().get ( 0 ).getApplicationId () );
		// one line too in transazione_iuv
		for ( PaymentNotice paymentNotice : cart.getPaymentNotices () ) {
			transazioneIuvService.saveTransazioneIuv ( transactionId, iuvOtticiService.getIuvFromNoticeNumber ( paymentNotice.getNoticeNumber () ) );
		}

		// retry urls from config table
		var okUrl = configService.getConfigByKey ( MDP_RETURN_URL_OK );
		if ( null == okUrl ) {
			return generateNotFoundUrl ( MDP_RETURN_URL_OK );
		}
		var cancelUrl = configService.getConfigByKey ( MDP_RETURN_URL_CANCEL );
		if ( null == cancelUrl ) {
			return generateNotFoundUrl ( MDP_RETURN_URL_OK );
		}
		var errorUrl = configService.getConfigByKey ( MDP_RETURN_URL_ERROR );
		if ( null == errorUrl ) {
			return generateNotFoundUrl ( MDP_RETURN_URL_ERROR );
		}

		// Check custom fields
		List<String> contosPoste = new ArrayList<> ();
		List<String> identificativosDominio = new ArrayList<> ();
		for ( var paymentNotice : cart.getPaymentNotices () ) {
			var applicationId = paymentNotice.getApplicationId ();
			var contoPoste = applicationCustomFieldService.getByApplicationIdAndFieldName ( applicationId, CONTO_POSTE_FIELDNAME );
			contosPoste.add ( applicationCustomFieldService.decrypt ( contoPoste ) );
			var codiceIdentificativoUnivocoBeneficiario = applicationCustomFieldService.getByApplicationIdAndFieldName ( applicationId,
							CODICE_INDENTIFICATIVO_UNIVOCO_BENEFICIARIO_FIELDNAME );
			identificativosDominio.add ( applicationCustomFieldService.decrypt ( codiceIdentificativoUnivocoBeneficiario ) );
		}

		// call to pagoPA
		CheckoutPagoPAOutput responseFromPagoPA;
		try {
			// NUOVA CONNETTIVITA : prendere il primo idApplication, la chiave e' uguale per tutte le idApplication dell'elenco
			var passphrase =
							applicationCustomFieldService.getByApplicationIdAndFieldName ( cart.getPaymentNotices ().get ( 0 ).getApplicationId (),
											PASSPHRASE );
			// init payload for pagoPA call
			var checkoutPagoPAInput = initPayload ( cart, transactionId, okUrl.getValue (), cancelUrl.getValue (), errorUrl.getValue (), contosPoste,
							identificativosDominio, passphrase.getFieldvalue () );
			//
			var opt = applicationCustomFieldService.getByApplicationIdAndFieldName ( cart.getPaymentNotices ().get ( 0 ).getApplicationId (),
							OCP_APIM_SUBSCRIPTION );
			var key = applicationCustomFieldService.decrypt ( opt );

			responseFromPagoPA = checkoutPagoPAAdapterClient.postCheckoutPagoPA ( checkoutPagoPAInput, key );
		} catch ( IOException e ) {
			Log.error ( "Error call to pagoPA" );
			Log.error ( "stack-trace of " + ExceptionUtils.getStackTrace ( e ) );
			return generateInternalErrorResponse ( e.getMessage () );
		} catch ( NoSuchAlgorithmException e ) {
			Log.error ( "Error mac" );
			Log.error ( "stack-trace of " + ExceptionUtils.getStackTrace ( e ) );
			return generateInternalErrorResponse ( e.getMessage () );
		}
		if ( !responseFromPagoPA.isOk () ) {
			return generateInternalErrorResponse ( CALL_TO_PAGOPA_ERROR );
		}

		// build output
		var responseOK = (CheckoutPagoPAOKOutput) responseFromPagoPA;
		var result = new Transaction ();
		result.setPaymentUrl ( responseOK.getLocation () );
		result.setIdTransaction ( transactionId );

		Log.info ( "End method manageCart" );
		return Response.status ( Response.Status.CREATED ).entity ( result ).build ();
	}

	/*
	 * Return an error String if check fails, null if ok, inside the IuvOtticoAndErrorMessage object
	 */
	private IuvOtticoAndErrorMessage checkIUVandApplicationID ( PaymentNotice paymentNotice ) {
		var iuvOtticoAndErrorMessage = new IuvOtticoAndErrorMessage ();
		var iuv = iuvOtticiService.getIuvFromNoticeNumber ( paymentNotice.getNoticeNumber () );
		var iuvOttici = iuvOtticiService.getByIUV ( iuv );
		if ( iuvOttici.isEmpty () ) {
			gdeService.trackKO ( iuv, paymentNotice.getApplicationId () );
			iuvOtticoAndErrorMessage.setError ( IUV_NOT_FOUND );
		} else {
			var iuvOttico = iuvOttici.get ( 0 ); // prendo il primo
			if ( paymentNotice.getApplicationId ().equalsIgnoreCase ( iuvOttico.getApplication ().getId () ) ) {
				iuvOtticoAndErrorMessage.setIuvOttico ( iuvOttico );
			} else {
				iuvOtticoAndErrorMessage.setError ( APPLICATION_ID_UNMATCH );
			}
		}
		return iuvOtticoAndErrorMessage;
	}

	private CheckoutPagoPAInput initPayload ( Cart cart, String transactionId, String okUrl, String cancelUrl,
					String errorUrl, List<String> contosPoste, List<String> identificativosDominio, String passphrase ) throws NoSuchAlgorithmException {

		List<CheckoutPagoPAPaymentNoticeInput> paymentNotices = new ArrayList<> ();
		for ( var index = 0; index < cart.getPaymentNotices ().size (); index++ ) {
			var paymentNotice = cart.getPaymentNotices ().get ( index );
			var applicationId = paymentNotice.getApplicationId ();
			var denominazioneBeneficiario = applicationCustomFieldService.getByApplicationIdAndFieldName ( applicationId, DENOMINAZIONE_BENEFICIARIO );
			var allCCP = contosPoste.get ( index ) != null;
			var checkoutPagoPAPaymentNoticeInput = new CheckoutPagoPAPaymentNoticeInput.Builder ()
							.noticeNumber ( paymentNotice.getNoticeNumber () )
							.fiscalCode ( identificativosDominio.get ( index ) )
							.amount ( ( new BigDecimal ( 100 ).multiply ( paymentNotice.getAmount () ) ).intValue () )
							.companyName ( applicationCustomFieldService.decrypt ( denominazioneBeneficiario ) )
							.description ( paymentNotice.getDescription () )
							.allCCP ( allCCP )
							.build ();
			paymentNotices.add ( checkoutPagoPAPaymentNoticeInput );
		}

		List<CheckoutPagoPAReturnUrlInput> returnUrls = new ArrayList<> ();

		var checkoutPagoPAReturnUrlInput = new CheckoutPagoPAReturnUrlInput.Builder ()
						.returnOkUrl ( String.format ( "%s%s%s%s%s", okUrl, Q_PARAM, transactionId, M_PARAM,
										MacUtil.generateMacPagopa ( passphrase, transactionId, okUrl, passphrase ) ) )

						.returnCancelUrl ( String.format ( "%s%s%s%s%s", cancelUrl, Q_PARAM, transactionId, M_PARAM,
										MacUtil.generateMacPagopa ( passphrase, transactionId, cancelUrl, passphrase ) ) )

						.returnErroUrl ( String.format ( "%s%s%s%s%s", errorUrl, Q_PARAM, transactionId, M_PARAM,
										MacUtil.generateMacPagopa ( passphrase, transactionId, errorUrl, passphrase ) ) )
						.build ();
		returnUrls.add ( checkoutPagoPAReturnUrlInput );

		return new CheckoutPagoPAInput.Builder ()
						.emailNotice ( cart.getEmailNotice () )
						.idCart ( transactionId )
						.paymentNotices ( paymentNotices )
						.returnUrls ( returnUrls.get ( 0 ) )
						.build ();
	}
}
