/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.service.external;

import static it.csi.epay.epayfeapi.util.Constants.ERROR_ENTE_NOT_FOUND;
import static it.csi.epay.epayfeapi.util.Constants.ERROR_PAGAMENTO_NON_ASSOCIATO_A_CODICE_FISCALE;
import static it.csi.epay.epayfeapi.util.Constants.ERROR_PAGAMENTO_NON_ASSOCIATO_A_ENTE;
import static it.csi.epay.epayfeapi.util.Constants.ERROR_PAGAMENTO_NOT_FOUND;
import static it.csi.epay.epayfeapi.util.Constants.ERROR_RECEIPT_NOT_FOUND;
import static it.csi.epay.epayfeapi.util.Constants.ERROR_REGISTRO_NOT_FOUND;
import static it.csi.epay.epayfeapi.util.Constants.REGEX_IUV_CF;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_FIELDS_PAYMENT_RECEIPT__CITIZEN_FISCAL_CODE;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_FIELDS_PAYMENT_RECEIPT__IUV;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_FIELDS_PAYMENT_RECEIPT__ORGANIZATION_FISCAL_CODE;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_PAYMENT_RECEIPT;
import static it.csi.epay.epayfeapi.util.ResponseUtil.generateForbiddenResponse;
import static it.csi.epay.epayfeapi.util.ResponseUtil.generateNotFoundErrorResponse;
import static it.csi.epay.epayfeapi.util.ResponseUtil.generateUnauthorizedEnteResponse;
import static it.csi.epay.epayfeapi.util.ResponseUtil.generateUnauthorizedResponse;
import static it.csi.epay.epayfeapi.util.ResponseUtil.generateValidationErrorResponse;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;
import org.openapitools.model.Error;
import org.openapitools.model.PaymentReceipt;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BadPdfFormatException;
import com.lowagie.text.pdf.PdfCopy;
import com.lowagie.text.pdf.PdfReader;

import io.quarkus.logging.Log;
import it.csi.epay.epayfeapi.entity.EpayTPagamento;
import it.csi.epay.epayfeapi.entity.EpayTRtPdf;
import it.csi.epay.epayfeapi.enumeration.Scopes;
import it.csi.epay.epayfeapi.enumeration.StatoPagamentiEnum;
import it.csi.epay.epayfeapi.enumeration.StatoPagamento;
import it.csi.epay.epayfeapi.model.Ricevuta;
import it.csi.epay.epayfeapi.security.AuthenticationContext;
import it.csi.epay.epayfeapi.service.ChiamanteAutorizzazioneChiamanteService;
import it.csi.epay.epayfeapi.service.ChiamanteEsternoService;
import it.csi.epay.epayfeapi.service.ChiamataEsternaNonValidaService;
import it.csi.epay.epayfeapi.service.EnteService;
import it.csi.epay.epayfeapi.service.EsitiRicevutiService;
import it.csi.epay.epayfeapi.service.PagamentoService;
import it.csi.epay.epayfeapi.service.RegistroVersamentiService;
import it.csi.epay.epayfeapi.service.RtService;
import it.csi.epay.epayfeapi.service.TracciabilitaChiamanteEsternoService;


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
	public Response getPaymentReceipt ( String organizationFiscalCode, String citizenFiscalCode, String iuv, long initialMoment, String serviceName ) {
		var methodName = "[CF-V0-getPaymentReceipt-V0] ";
		Log.infof ( "%sBEGIN" );
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
			var response = generateForbiddenResponse ( SERVICE_PAYMENT_RECEIPT, user.getName () );
			chiamataEsternaNonValidaService.track ( null, user, citizenFiscalCode, iuv, ( (Error) response.getEntity () ).getDetail (), initialMoment, serviceName );
			return response;
		}
		Log.infof ( "%schiamanteEsternoEntity:%s", methodName, chiamanteEsternoEntity );
		//
		// 2. tracciamento della chiamata
		tracciabilitaChiamanteEsternoService.trackExternalCall ( null, organizationFiscalCode, chiamanteEsternoEntity, iuv, user, null, initialMoment, serviceName );
		Log.infof ( "%schiamanteEsterno tracciato", methodName );
		//
		// 3. validazione autorizzazione
		if ( chiamanteAutorizzazioneChiamanteService.countByCodiceChiamanteAndCodiceAutorizzazioneChiamante ( user.getName (),
						Scopes.RICEVUTA_PAGAMENTO.name () ) < 1 ) {
			var response = generateUnauthorizedResponse ( SERVICE_PAYMENT_RECEIPT );
			chiamataEsternaNonValidaService.track ( null, user, citizenFiscalCode, iuv, ( (Error) response.getEntity () ).getDetail (), initialMoment, serviceName );
			return response;
		}
		Log.infof ( "%sauthorization OK", methodName );

		// validazione input
		var notValids = getNotValidInputs ( organizationFiscalCode, citizenFiscalCode, iuv );
		if ( !notValids.isEmpty () ) {
			var response = generateValidationErrorResponse ( SERVICE_PAYMENT_RECEIPT, notValids );
			chiamataEsternaNonValidaService.track ( null, user, citizenFiscalCode, iuv, ( (Error) response.getEntity () ).getDetail (), initialMoment, serviceName );
			return response;
		}
		Log.infof ( "%svalidation OK", methodName );

		/* --- logica di business --- */

		// ottiene l'ente corrispondente al codice fiscale
		var enteEntity = enteService.findByCodiceFiscale ( organizationFiscalCode );
		if ( enteEntity == null ) {
			var response = generateNotFoundErrorResponse ( SERVICE_PAYMENT_RECEIPT, ERROR_ENTE_NOT_FOUND, organizationFiscalCode );
			chiamataEsternaNonValidaService.track ( null, user, organizationFiscalCode, null, ( (Error) response.getEntity () ).getDetail (), initialMoment, serviceName );
			return response;
		}
		if ( enteEntity.getFlagAdesioneCittaFacile()==null || !enteEntity.getFlagAdesioneCittaFacile()) {
			var response = generateUnauthorizedEnteResponse ( SERVICE_PAYMENT_RECEIPT);
			chiamataEsternaNonValidaService.track ( null, user, organizationFiscalCode, null, ( (Error) response.getEntity () ).getDetail (), initialMoment, serviceName );
			return response;
		}
		Log.infof ( "%sente:%s", methodName, enteEntity );

		// controllo dello IUV
		var pagamentoEntity = pagamentoService.getPagamentoByiuvNumeroAvviso ( iuv );
		if ( pagamentoEntity == null ) {
			var response = generateNotFoundErrorResponse ( SERVICE_PAYMENT_RECEIPT, ERROR_PAGAMENTO_NOT_FOUND );
			chiamataEsternaNonValidaService.track ( null, user, citizenFiscalCode, iuv, ( (Error) response.getEntity () ).getDetail (), initialMoment, serviceName );
			return response;
		}
		Log.infof ( "%scontrollo IUV OK", methodName );

		// controllo del corrispondenza pagatore
		if ( !citizenFiscalCode.equals ( pagamentoEntity.getEpayTAnagrafica ().getCodiceFiscale () ) ) {
			var response = generateNotFoundErrorResponse ( SERVICE_PAYMENT_RECEIPT, ERROR_PAGAMENTO_NON_ASSOCIATO_A_CODICE_FISCALE );
			chiamataEsternaNonValidaService.track ( null, user, citizenFiscalCode, iuv, ( (Error) response.getEntity () ).getDetail (), initialMoment, serviceName );
			return response;
		}
		Log.infof ( "%scontrollo corrispondenza pagatore OK", methodName );

		// controllo del corrispondenza ente
		if ( !organizationFiscalCode.equals ( pagamentoEntity.getEpayTTipoPagamento ().getEpayTEnti ().getCodiceFiscale () ) ) {
			var response = generateNotFoundErrorResponse ( SERVICE_PAYMENT_RECEIPT, ERROR_PAGAMENTO_NON_ASSOCIATO_A_ENTE );
			chiamataEsternaNonValidaService.track ( null, user, citizenFiscalCode, iuv, ( (Error) response.getEntity () ).getDetail (), initialMoment, serviceName );
			return response;
		}
		Log.infof ( "%scontrollo corrispondenza ente OK", methodName );

		// ottiene l'ultimo idRegistro di epay_t_registro_versamenti pagato
		var versamento = registroVersamentiService.findMaxIdByIdPagamentoAndStatoPagamento ( pagamentoEntity.getIdPagamento (),
						StatoPagamentiEnum.INOLTRATO.getId () );
		if ( versamento == null ) {
			var response = generateNotFoundErrorResponse ( SERVICE_PAYMENT_RECEIPT, ERROR_REGISTRO_NOT_FOUND, pagamentoEntity.getIdPagamento () );
			chiamataEsternaNonValidaService.track ( null, user, citizenFiscalCode, iuv, ( (Error) response.getEntity () ).getDetail (), initialMoment, serviceName );
			return response;
		}
		Log.infof ( "%sottenuto l'ultimo idRegistro:%d", methodName, versamento.getIdRegistro () );

		var result = new PaymentReceipt ();

		// ottiene la ricevuta PDF: prova a ottenerla dalla RT
		var rt = rtService.findPdfByIdRegistroAndCodEsitoPagamento ( versamento.getIdRegistro (), Integer.valueOf ( "0" ) ); // Valutare se fare un'enum
		if ( rt != null ) {
			result.setReceipt ( getPdfFromRT ( rt, pagamentoEntity, versamento.getIdRegistro () ) );
		} else {
			// ... se non la ottiene dalla RT prova a ottenerla dalla quietanza
			var esito = esitiRicevutiService.findByIdRegistroAndIdQuietanzaNotNull ( versamento.getIdRegistro () );
			if ( esito == null || esito.getEpayTQuietanzaEsito () != null && esito.getEpayTQuietanzaEsito ().getRicevutaPdf () != null ) {
				if ( esito != null ) {
					result.setReceipt ( esito.getEpayTQuietanzaEsito ().getRicevutaPdf () );
				} else {
					var response = generateNotFoundErrorResponse ( SERVICE_PAYMENT_RECEIPT, ERROR_RECEIPT_NOT_FOUND, pagamentoEntity.getIdPagamento () );
					chiamataEsternaNonValidaService.track ( null, user, citizenFiscalCode, iuv, ( (Error) response.getEntity () ).getDetail (), initialMoment, serviceName );
					return response;
				}
			} else {
				var response = generateNotFoundErrorResponse ( SERVICE_PAYMENT_RECEIPT, ERROR_RECEIPT_NOT_FOUND, pagamentoEntity.getIdPagamento () );
				chiamataEsternaNonValidaService.track ( null, user, citizenFiscalCode, iuv, ( (Error) response.getEntity () ).getDetail (), initialMoment, serviceName );
				return response;
			}
		}
		Log.infof ( "%sottenuta la ricevuta PDF", methodName );

		Log.infof ( "%sEND", methodName );
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

	private byte[] getPdfFromRT ( EpayTRtPdf rt, EpayTPagamento tPagamento, Long idRegistro ) {
		if ( !Boolean.TRUE.equals ( tPagamento.getEpayTTipoPagamento ().getFlagMultibeneficiario () ) ) {
			return rt.getRicevutaPdf (); // caso semplice
		} // else, caso multi
		var ricevutaPdf = new Ricevuta ( rt.getRicevutaPdf () );
		var rtPrimaria = rtService.findByIdRegistroAndCodEsitoPagamento ( idRegistro, Integer.valueOf ( "0" ) );
		var rtSec = rtService.ricercaRt ( tPagamento.getIdPagamento (), StatoPagamento.IN_ATTESA_SECONDA_RT, rtPrimaria.getIdTransazione () );
		try {
			if ( rtSec != null ) {
				var ricevutaPdfSec = new Ricevuta ( rtSec.getRicevutaPdf () );
				var outputStream = new ByteArrayOutputStream ();
				var document = new Document ();
				var copy = new PdfCopy ( document, outputStream );
				document.open ();
				readerHelper ( ricevutaPdf, copy );
				// Aggiunta del secondo PDF
				readerHelper ( ricevutaPdfSec, copy );
				document.close ();
				return outputStream.toByteArray ();
			} else {// caso come prima, singolo pdf
				Log.error ( "Caso singolo pdf ma in errore" );
				return rt.getRicevutaPdf ();
			}
		} catch ( IOException | DocumentException e ) {
			Log.error ( "Errore in fase di merge pdf", e );
			throw new RuntimeException ( e );
		}
	}

	private void readerHelper ( Ricevuta ricevutaPdf, PdfCopy copy ) throws IOException, BadPdfFormatException {
		var reader = new PdfReader ( ricevutaPdf.getData () );
		int n1 = reader.getNumberOfPages ();
		for ( int i = 1; i <= n1; i++ ) {
			var page = copy.getImportedPage ( reader, i );
			copy.addPage ( page );
		}
		reader.close ();
	}
}
