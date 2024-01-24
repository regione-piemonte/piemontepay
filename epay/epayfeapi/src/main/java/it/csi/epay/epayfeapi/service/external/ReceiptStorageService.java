/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.service.external;

import static it.csi.epay.epayfeapi.util.Constants.ERROR_ENTE_NOT_FOUND;
import static it.csi.epay.epayfeapi.util.Constants.ERROR_PREFERENZA_ARCHIVIAZIONE_NOT_FOUND;
import static it.csi.epay.epayfeapi.util.Constants.REGEX_IUV_CF;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_ENABLE_RECEIPT_STORAGE;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_FIELDS_PAYMENT_RECEIPT__CITIZEN_FISCAL_CODE;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_FIELDS_PAYMENT_RECEIPT__ORGANIZATION_FISCAL_CODE;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_FIELDS_RECEIPT_STORAGE__CITIZEN_FISCAL_CODE;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_FIELDS_RECEIPT_STORAGE__ENABLE_RECEIPT_STORAGE;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_FIELDS_RECEIPT_STORAGE__ORGANIZATION_FISCAL_CODE;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_GET_RECEIPT_STORAGE;
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
import org.openapitools.model.ReceiptStorage;

import io.quarkus.logging.Log;
import it.csi.epay.epayfeapi.dto.FlagAbilitaArchiviazioneDTO;
import it.csi.epay.epayfeapi.entity.EpayDChiamanteEsterno;
import it.csi.epay.epayfeapi.entity.EpayTEnti;
import it.csi.epay.epayfeapi.security.AuthenticationContext;
import it.csi.epay.epayfeapi.security.Scopes;
import it.csi.epay.epayfeapi.security.User;
import it.csi.epay.epayfeapi.service.ChiamanteAutorizzazioneChiamanteService;
import it.csi.epay.epayfeapi.service.ChiamanteEsternoService;
import it.csi.epay.epayfeapi.service.ChiamataEsternaNonValidaService;
import it.csi.epay.epayfeapi.service.EnteService;
import it.csi.epay.epayfeapi.service.StatoArchiviazioneService;
import it.csi.epay.epayfeapi.service.TracciabilitaChiamanteEsternoService;


/*
 * Servizio 8 - Aggiornare la preferenza all'archiviazione delle ricevute di pagament
 */
@ApplicationScoped
@Transactional
public class ReceiptStorageService {

	@Inject
	AuthenticationContext authenticationContext;

	@Inject
	ChiamanteEsternoService chiamanteEsternoService;

	@Inject
	ChiamataEsternaNonValidaService chiamataEsternaNonValidaService;

	@Inject
	ChiamanteAutorizzazioneChiamanteService chiamanteAutorizzazioneChiamanteService;

	@Inject
	TracciabilitaChiamanteEsternoService tracciabilitaChiamanteEsternoService;

	@Inject
	EnteService enteService;

	@Inject
	StatoArchiviazioneService statoArchiviazioneService;

	public Response enableReceiptStorage ( String organizationFiscalCode, String citizenFiscalCode, Boolean enableReceiptStorage ) {
		String methodName = "[enableReceiptStorage] ";
		Log.info ( methodName + "BEGIN" );
		Log.info ( methodName + "param organizationFiscalCode:" + organizationFiscalCode );
		Log.info ( methodName + "param citizenFiscalCode:" + citizenFiscalCode );
		Log.info ( methodName + "param enableReceiptStorage:" + enableReceiptStorage );

		User user = authenticationContext.getCurrentUser ();
		Log.info ( methodName + "user:" + user );

		/* --- validazione --- */

		// autorizzazione
		// 1. ottiene il chiamante esterno per il tracciamento della chiamata e la validazione dell'autorizzazione
		EpayDChiamanteEsterno chiamanteEsternoEntity = chiamanteEsternoService.findByCodiceChiamante ( user.getName () );
		if ( chiamanteEsternoEntity == null ) {
			Response response = generateForbiddenResponse ( SERVICE_ENABLE_RECEIPT_STORAGE, user.getName () );
			chiamataEsternaNonValidaService.track ( null, user, citizenFiscalCode, null, ( (Error) response.getEntity () ).getDetail () );
			return response;
		}
		Log.info ( methodName + "chiamanteEsternoEntity:" + chiamanteEsternoEntity );
		//
		// 2. tracciamento della chiamata
		tracciabilitaChiamanteEsternoService.trackExternalCall ( null, organizationFiscalCode, chiamanteEsternoEntity, null, user, null );
		Log.info ( methodName + "chiamanteEsterno tracciato" );
		//
		// 3. validazione autorizzazione
		if ( chiamanteAutorizzazioneChiamanteService.countByCodiceChiamanteAndCodiceAutorizzazioneChiamante ( user.getName (),
			Scopes.FLAG_ARCHIVIAZIONE.name () ) < 1 ) {
			Response response = generateUnauthorizedResponse ( SERVICE_ENABLE_RECEIPT_STORAGE );
			chiamataEsternaNonValidaService.track ( null, user, citizenFiscalCode, null, ( (Error) response.getEntity () ).getDetail () );
			return response;
		}
		Log.info ( methodName + "authorization OK" );

		// validazione input
		List<String> notValids = getNotValidInputs ( organizationFiscalCode, citizenFiscalCode, enableReceiptStorage );
		if ( !notValids.isEmpty () ) {
			Response response = generateValidationErrorResponse ( SERVICE_ENABLE_RECEIPT_STORAGE, notValids );
			chiamataEsternaNonValidaService.track ( null, user, citizenFiscalCode, null, ( (Error) response.getEntity () ).getDetail () );
			return response;
		}
		Log.info ( methodName + "validation OK" );

		/* --- logica di business --- */

		// ottiene l'ente corrispondente al codice fiscale
		EpayTEnti enteEntity = enteService.findByCodiceFiscale ( organizationFiscalCode );
		if ( enteEntity == null ) {
			Response response = generateNotFoundErrorResponse ( SERVICE_ENABLE_RECEIPT_STORAGE, ERROR_ENTE_NOT_FOUND, organizationFiscalCode );
			chiamataEsternaNonValidaService.track ( null, user, organizationFiscalCode, null, ( (Error) response.getEntity () ).getDetail () );
			return response;
		}
		Log.info ( methodName + "ente:" + enteEntity );

		// impostazione dello stato di archiviazione
		statoArchiviazioneService.setFlagAbilitaArchiviazione ( citizenFiscalCode, enteEntity, enableReceiptStorage, chiamanteEsternoEntity );

		Log.info ( methodName + "END" );
		return Response.status ( Response.Status.OK ).build ();
	}

	/*
	 * Servizio 9 - restituisce la preferenza all'archiviazione delle ricevute di pagamento per il cittadino e per l'ente specificato.
	 */
	public Response getReceiptStorage ( String organizationFiscalCode, String citizenFiscalCode ) {
		String methodName = "[getReceiptStorage] ";
		Log.info ( methodName + "BEGIN" );
		Log.info ( methodName + "param organizationFiscalCode:" + organizationFiscalCode );
		Log.info ( methodName + "param citizenFiscalCode:" + citizenFiscalCode );

		User user = authenticationContext.getCurrentUser ();
		Log.info ( methodName + "user:" + user );

		/* --- validazione --- */

		// 1. ottiene il chiamante esterno per il tracciamento della chiamata e la validazione dell'autorizzazione
		EpayDChiamanteEsterno chiamanteEsterno = chiamanteEsternoService.findByCodiceChiamante ( user.getName () );
		if ( chiamanteEsterno == null ) {
			Response response = generateForbiddenResponse ( SERVICE_GET_RECEIPT_STORAGE, user.getName () );
			chiamataEsternaNonValidaService.track ( null, user, citizenFiscalCode, null, ( (Error) response.getEntity () ).getDetail () );
			return response;
		}
		Log.info ( methodName + "chiamanteEsterno:" + chiamanteEsterno );
		//
		// 2. tracciamento della chiamata
		tracciabilitaChiamanteEsternoService.trackExternalCall ( null, organizationFiscalCode, chiamanteEsterno, null, user, null );
		Log.info ( methodName + "chiamanteEsterno tracciato" );
		//
		// 3. validazione autorizzazione
		if ( chiamanteAutorizzazioneChiamanteService.countByCodiceChiamanteAndCodiceAutorizzazioneChiamante (
			user.getName (), Scopes.GET_ARCHIVIAZIONE.name () ) < 1 ) {
			Response response = generateUnauthorizedResponse ( SERVICE_GET_RECEIPT_STORAGE );
			chiamataEsternaNonValidaService.track ( null, user, citizenFiscalCode, null, ( (Error) response.getEntity () ).getDetail () );
			return response;
		}
		Log.info ( methodName + "authorization OK" );

		// validazione input
		List<String> notValids = getNotValidInputs ( organizationFiscalCode, citizenFiscalCode );
		if ( !notValids.isEmpty () ) {
			Response response = generateValidationErrorResponse ( SERVICE_GET_RECEIPT_STORAGE, notValids );
			chiamataEsternaNonValidaService.track ( null, user, citizenFiscalCode, null, ( (Error) response.getEntity () ).getDetail () );
			return response;
		}
		Log.info ( methodName + "validation OK" );

		/* --- logica di business --- */

		// ottiene l'ente corrispondente al codice fiscale
		EpayTEnti enteEntity = enteService.findByCodiceFiscale ( organizationFiscalCode );
		if ( enteEntity == null ) {
			Response response = generateNotFoundErrorResponse ( SERVICE_GET_RECEIPT_STORAGE, ERROR_ENTE_NOT_FOUND, organizationFiscalCode );
			chiamataEsternaNonValidaService.track ( null, user, organizationFiscalCode, null, ( (Error) response.getEntity () ).getDetail () );
			return response;
		}
		Log.info ( methodName + "ente:" + enteEntity );

		// lettura del flag di archiviazione
		FlagAbilitaArchiviazioneDTO flag = statoArchiviazioneService.getFlagAbilitaArchiviazione ( citizenFiscalCode, enteEntity );

		// costruisce la response
		Log.info ( methodName + "END" );
		if ( flag != null ) {
			ReceiptStorage receiptStorage = new ReceiptStorage ();
			receiptStorage.setFlag ( flag.isAbilitato () );
			receiptStorage.setDataUltimaVariazione ( flag.getDataOraUltimaModifica () );
			return Response.status ( Response.Status.OK ).entity ( receiptStorage ).build ();
		} else {
			Response response = generateNotFoundErrorResponse ( SERVICE_GET_RECEIPT_STORAGE, ERROR_PREFERENZA_ARCHIVIAZIONE_NOT_FOUND );
			chiamataEsternaNonValidaService.track ( null, user, organizationFiscalCode, null, ( (Error) response.getEntity () ).getDetail () );
			return response;
		}
	}

	private List<String> getNotValidInputs ( String organizationFiscalCode, String citizenFiscalCode, Boolean enableReceiptStorage ) {
		List<String> notValids = new LinkedList<> ();
		return getNotValidInputs ( notValids, organizationFiscalCode, citizenFiscalCode, enableReceiptStorage );
	}

	private List<String> getNotValidInputs ( String organizationFiscalCode, String citizenFiscalCode ) {
		List<String> notValids = new LinkedList<> ();
		return getNotValidInputs ( notValids, organizationFiscalCode, citizenFiscalCode );
	}

	private List<String> getNotValidInputs ( List<String> notValids, String organizationFiscalCode, String citizenFiscalCode, Boolean enableReceiptStorage ) {
		getNotValidInputs ( notValids, organizationFiscalCode, citizenFiscalCode );

		if ( enableReceiptStorage == null ) {
			notValids.add ( SERVICE_FIELDS_RECEIPT_STORAGE__ENABLE_RECEIPT_STORAGE );
		}

		return notValids;
	}

	private List<String> getNotValidInputs ( List<String> notValids, String organizationFiscalCode, String citizenFiscalCode ) {
		if ( StringUtils.isBlank ( organizationFiscalCode ) ) {
			notValids.add ( SERVICE_FIELDS_RECEIPT_STORAGE__ORGANIZATION_FISCAL_CODE );
		} else if ( !organizationFiscalCode.matches ( REGEX_IUV_CF ) ) {
			notValids.add ( SERVICE_FIELDS_RECEIPT_STORAGE__ORGANIZATION_FISCAL_CODE );
		}

		if ( StringUtils.isBlank ( citizenFiscalCode ) ) {
			notValids.add ( SERVICE_FIELDS_RECEIPT_STORAGE__CITIZEN_FISCAL_CODE );
		} else if ( !citizenFiscalCode.matches ( REGEX_IUV_CF ) ) {
			notValids.add ( SERVICE_FIELDS_RECEIPT_STORAGE__CITIZEN_FISCAL_CODE );
		}

		return notValids;
	}
}
