/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.controller;

import io.quarkus.logging.Log;
import it.csi.epay.epayfeapi.exception.CustomException;
import it.csi.epay.epayfeapi.security.AuthenticationContext;
import it.csi.epay.epayfeapi.service.ChiamataEsternaNonValidaService;
import it.csi.epay.epayfeapi.service.external.CreateDebtPositionService;
import it.csi.epay.epayfeapi.service.external.DebtPositionService;
import it.csi.epay.epayfeapi.service.external.DebtPositionsPaymentUrlService;
import it.csi.epay.epayfeapi.service.external.PaymentNoticeService;
import it.csi.epay.epayfeapi.service.external.PaymentReceiptService;
import it.csi.epay.epayfeapi.service.external.PaymentService;
import it.csi.epay.epayfeapi.service.external.PaymentTypesService;
import it.csi.epay.epayfeapi.service.external.ReceiptStorageService;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openapitools.api.ApiApi;
import org.openapitools.model.PaymentData;
import org.openapitools.model.PaymentDataPayment;

import javax.inject.Inject;
import javax.ws.rs.core.Response;


public class EndPointController implements ApiApi {

	@Inject
	AuthenticationContext authenticationContext;

	@Inject
	ChiamataEsternaNonValidaService chiamataEsternaNonValidaService;

	@Inject
	PaymentTypesService paymentTypesService;

	@Inject
	PaymentService paymentService;

	@Inject
	PaymentReceiptService paymentReceiptService;

	@Inject
	PaymentNoticeService paymentNoticeService;

	@Inject
	ReceiptStorageService receiptStorageService;

	@Inject
	CreateDebtPositionService createDebtPositionService;

	@Inject
	DebtPositionsPaymentUrlService debtPositionsPaymentUrlService;

	@Inject
	DebtPositionService debtPositionService;

	/*
	 * CDU#1 Debt Position - Elenco dei pagamenti da effettuare/effettuati
	 */
	@Override
	public Response findDebtPositions ( String organizationFiscalCode, String citizenFiscalCode, String status, String iuv, Integer currentPage,
					Integer elements, String sort, String fields ) {
		var initialMoment = System.currentTimeMillis ();
		var serviceName = "CF-V0-findDebtPositions-V0";
		Log.info ( "Called method findDebtPositions" );
		Response response;
		try {
			response = debtPositionService.findDebtPositions ( organizationFiscalCode, citizenFiscalCode, status, iuv, currentPage, elements, sort, fields, initialMoment, serviceName );
		} catch ( Exception e ) {
			throw traceExceptionAndCreateCustomException ( organizationFiscalCode, e, initialMoment, serviceName );
		} finally {
			Log.info ( "Method findDebtPositions ended" );
		}
		return response;
	}

	/*
	 * CDU#2 Create Debt Position - PDF relativo ad avviso pagamento spontaneo
	 */
	@Override
	public Response createDebtPosition ( String organizationFiscalCode, String citizenFiscalCode, String paymenttype, PaymentData paymentData ) {
		var initialMoment = System.currentTimeMillis ();
		var serviceName = "CF-V0-createDebtPosition-V0";
		Log.info ( "Called method createDebtPosition" );
		Response response;
		try {
			response = createDebtPositionService.createDebtPosition ( organizationFiscalCode, citizenFiscalCode, paymenttype, paymentData, initialMoment, serviceName );
		} catch ( Exception e ) {
			throw traceExceptionAndCreateCustomException ( organizationFiscalCode, e, initialMoment, serviceName );
		} finally {
			Log.info ( "Method createDebtPosition ended" );
		}
		return response;
	}

	/*
	 * CDU#3 Payment Notice - PDF relativo ad una posizione debitoria
	 */
	@Override
	public Response getPaymentNotice ( String organizationFiscalCode, String citizenFiscalCode, String iuv ) {
		var initialMoment = System.currentTimeMillis ();
		var serviceName = "CF-V0-getPaymentNotice-V0";
		Log.info ( "Called method getPaymentNotice" );
		Response response;
		try {
			response = paymentNoticeService.getPaymentNotice ( organizationFiscalCode, citizenFiscalCode, iuv, initialMoment, serviceName );
		} catch ( Exception e ) {
			throw traceExceptionAndCreateCustomException ( organizationFiscalCode, e, initialMoment, serviceName );
		} finally {
			Log.info ( "Method getPaymentNotice ended" );
		}
		return response;
	}

	/*
	 * CDU#4 Payment Receipt - PDF relativo ad una ricevuta di pagamento
	 */
	@Override
	public Response getPaymentReceipt ( String organizationFiscalCode, String citizenFiscalCode, String iuv ) {
		var initialMoment = System.currentTimeMillis ();
		var serviceName = "CF-V0-getPaymentReceipt-V0";
		Log.info ( "Called method getPaymentReceipt" );
		Response response;
		try {
			response = paymentReceiptService.getPaymentReceipt ( organizationFiscalCode, citizenFiscalCode, iuv, initialMoment, serviceName );
		} catch ( Exception e ) {
			throw traceExceptionAndCreateCustomException ( organizationFiscalCode, e, initialMoment, serviceName );
		} finally {
			Log.info ( "Method getPaymentReceipt ended" );
		}
		return response;
	}

	/*
	 * CDU#5 Payment Types - Elenco tipologie di pagamento spontaneo associate ad uno specifico ente
	 */
	@Override
	public Response getPaymentTypes ( String organizationFiscalCode, String paymentTypesDescription, Integer currentPage, Integer elements, String sort,
					String fields ) {
		var initialMoment = System.currentTimeMillis ();
		var serviceName = "CF-V0-getPaymentTypes-V0";
		Log.info ( "Called method getPaymentTypes" );
		Response response;
		try {
			response = paymentTypesService.getPaymentTypes ( organizationFiscalCode, paymentTypesDescription, currentPage, elements, sort, fields, initialMoment, serviceName );
		} catch ( Exception e ) {
			throw traceExceptionAndCreateCustomException ( organizationFiscalCode, e, initialMoment, serviceName );
		} finally {
			Log.info ( "Method getPaymentTypes ended" );
		}
		return response;
	}

	/*
	 * CDU#6 Payment - Pagamento spontaneo
	 */
	@Override
	public Response getPaymentUrl ( String organizationFiscalCode, String paymenttype, PaymentDataPayment paymentData ) {
		var initialMoment = System.currentTimeMillis ();
		var serviceName = "CF-V0-getPaymentUrl-V0";
		Log.info ( "Called method getPaymentUrl" );
		Response response;
		try {
			response = paymentService.getIUV ( organizationFiscalCode, paymenttype, paymentData, initialMoment, serviceName );
		} catch ( Exception e ) {
			throw traceExceptionAndCreateCustomException ( organizationFiscalCode, e, initialMoment, serviceName );
		} finally {
			Log.info ( "Method getPaymentUrl ended" );
		}
		return response;
	}

	/*
	 * CDU#7 Payment - Pagamento di una posizione debitoria
	 */
	@Override
	public Response getDebtPositionsPaymentUrl ( String organizationFiscalCode, String iuv ) {
		var initialMoment = System.currentTimeMillis ();
		var serviceName = "CF-V0-iuvPayment-V0";
		Log.info ( "Called method getDebtPositionsPaymentUrl" );
		Response response;
		try {
			response = debtPositionsPaymentUrlService.getPaymentUrl ( organizationFiscalCode, iuv, initialMoment, serviceName );
		} catch ( Exception e ) {
			throw traceExceptionAndCreateCustomException ( organizationFiscalCode, e, initialMoment, serviceName );
		} finally {
			Log.info ( "Method getDebtPositionsPaymentUrl ended" );
		}
		return response;
	}

	/*
	 * CDU#8 Update archive flag - Aggiornare la preferenza all'archiviazione delle ricevute di pagamento
	 */
	@Override
	public Response enableReceiptStorage ( String organizationFiscalCode, String citizenFiscalCode, Boolean enableReceiptStorage ) {
		var initialMoment = System.currentTimeMillis ();
		var serviceName = "CF-V0-updatearchiveflag-V0";
		Log.info ( "Called method enableReceiptStorage" );
		Response response;
		try {
			response = receiptStorageService.enableReceiptStorage ( organizationFiscalCode, citizenFiscalCode, enableReceiptStorage, initialMoment, serviceName );
		} catch ( Exception e ) {
			throw traceExceptionAndCreateCustomException ( organizationFiscalCode, e, initialMoment, serviceName );
		} finally {
			Log.info ( "Method enableReceiptStorage ended" );
		}
		return response;
	}

	/*
	 * CDU#9 Get archive flag - Restituisce la preferenza all'archiviazione delle ricevute di pagamento per il cittadino e per l'ente specificato
	 */
	@Override
	public Response getReceiptStorage ( String organizationFiscalCode, String citizenFiscalCode ) {
		var initialMoment = System.currentTimeMillis ();
		var serviceName = "CF-V0-getarchiveflag-V0";
		Log.info ( "Called method getReceiptStorage" );
		Response response;
		try {
			response = receiptStorageService.getReceiptStorage ( organizationFiscalCode, citizenFiscalCode, initialMoment, serviceName );
		} catch ( Exception e ) {
			throw traceExceptionAndCreateCustomException ( organizationFiscalCode, e, initialMoment, serviceName );
		} finally {
			Log.info ( "Method getReceiptStorage ended" );
		}
		return response;
	}

	private CustomException traceExceptionAndCreateCustomException ( String organizationFiscalCode, Throwable exception, long initialMoment, String serviceName ) {
		Log.errorf ( "stack-trace of %s", ExceptionUtils.getStackTrace ( exception ) );
		var errorDetail = exception.getMessage ();
		chiamataEsternaNonValidaService.track ( null, authenticationContext.getCurrentUser (), organizationFiscalCode, null, errorDetail, initialMoment, serviceName );
		return new CustomException ( errorDetail );
	}
}
