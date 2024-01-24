/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.service.external;

import static it.csi.epay.epayfeapi.util.Constants.ERROR_ENTE_NOT_FOUND;
import static it.csi.epay.epayfeapi.util.Constants.ERROR_TIPOLOGIA_PAGAMENTO_NOT_DEFINED;
import static it.csi.epay.epayfeapi.util.Constants.REGEX_IUV_CF;
import static it.csi.epay.epayfeapi.util.Constants.REGEX_NORMAL_TEXT;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_FIELDS_PAYMENT_TYPES__CODICE_VERSAMENTO;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_FIELDS_PAYMENT_TYPES__DESCRIZIONE_VERSAMENTO;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_FIELDS_PAYMENT_TYPES__NOTE_OBBLIGATORIE;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_FIELDS_PAYMENT_TYPES__NOTE_PER_IL_PAGATORE;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_FIELDS_PAYMENT_TYPES__ORGANIZATION_FISCAL_CODE;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_FIELDS_PAYMENT_TYPES__PAYMENT_TYPES_DESCRIPTION;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_PAYMENT_TYPES;
import static it.csi.epay.epayfeapi.util.FieldsUtil.validateCommonFields;
import static it.csi.epay.epayfeapi.util.ResponseUtil.generateBusinessErrorResponse;
import static it.csi.epay.epayfeapi.util.ResponseUtil.generateForbiddenResponse;
import static it.csi.epay.epayfeapi.util.ResponseUtil.generateNotFoundErrorResponse;
import static it.csi.epay.epayfeapi.util.ResponseUtil.generateUnauthorizedResponse;
import static it.csi.epay.epayfeapi.util.ResponseUtil.generateValidationErrorResponse;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;
import org.openapitools.model.Error;
import org.openapitools.model.PaymentType;
import org.openapitools.model.PaymentTypes;

import io.quarkus.logging.Log;
import it.csi.epay.epayfeapi.dto.ConfigurazioneServiceElementsDTO;
import it.csi.epay.epayfeapi.dto.PagedListResultDTO;
import it.csi.epay.epayfeapi.entity.EpayDChiamanteEsterno;
import it.csi.epay.epayfeapi.entity.EpayDTipologiaPagamento;
import it.csi.epay.epayfeapi.entity.EpayTEnti;
import it.csi.epay.epayfeapi.entity.EpayTTipoPagamento;
import it.csi.epay.epayfeapi.model.filtrable.XPaymentType;
import it.csi.epay.epayfeapi.security.AuthenticationContext;
import it.csi.epay.epayfeapi.security.Scopes;
import it.csi.epay.epayfeapi.security.User;
import it.csi.epay.epayfeapi.service.ChiamanteAutorizzazioneChiamanteService;
import it.csi.epay.epayfeapi.service.ChiamanteEsternoService;
import it.csi.epay.epayfeapi.service.ChiamataEsternaNonValidaService;
import it.csi.epay.epayfeapi.service.ConfigurazioneService;
import it.csi.epay.epayfeapi.service.EnteService;
import it.csi.epay.epayfeapi.service.TipoPagamentoService;
import it.csi.epay.epayfeapi.service.TipologiaPagamentoService;
import it.csi.epay.epayfeapi.service.TracciabilitaChiamanteEsternoService;
import it.csi.epay.epayfeapi.util.FieldsUtil;


/*
 * Servizio 5 - Elenco tipologie di pagamento spontaneo associate ad uno specifico ente
 */
@ApplicationScoped
@Transactional
public class PaymentTypesService {

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
	TipologiaPagamentoService tipologiaPagamentoService;

	@Inject
	ConfigurazioneService configurazioneService;

	@Inject
	TipoPagamentoService tipoPagamentoService;

	private static final String [] SORTABLE_FIELDS = new String [] {
		SERVICE_FIELDS_PAYMENT_TYPES__CODICE_VERSAMENTO,
		SERVICE_FIELDS_PAYMENT_TYPES__DESCRIZIONE_VERSAMENTO
	};

	private static final String [] VALID_FIELDS = new String [] {
		SERVICE_FIELDS_PAYMENT_TYPES__CODICE_VERSAMENTO,
		SERVICE_FIELDS_PAYMENT_TYPES__DESCRIZIONE_VERSAMENTO,
		SERVICE_FIELDS_PAYMENT_TYPES__NOTE_PER_IL_PAGATORE,
		SERVICE_FIELDS_PAYMENT_TYPES__NOTE_OBBLIGATORIE
	};

	private static final String CODICE_TIPOLOGIA_PAGAMENTO_SPONTANEO = "PS";

	public Response getPaymentTypes (
		String organizationFiscalCode,
		String paymentTypesDescription,
		Integer currentPage,
		Integer elements,
		String sort,
		String fields ) {

		String methodName = "[getPaymentTypes] ";
		Log.info ( methodName + "BEGIN" );
		Log.info ( methodName + "param organizationFiscalCode:" + organizationFiscalCode );
		Log.info ( methodName + "param paymentTypesDescription:" + paymentTypesDescription );
		Log.info ( methodName + "param currentPage:" + currentPage );
		Log.info ( methodName + "param elements:" + elements );
		Log.info ( methodName + "param sort:" + sort );
		Log.info ( methodName + "param fields:" + fields );

		User user = authenticationContext.getCurrentUser ();
		Log.info ( methodName + "user:" + user );

		/* --- validazione --- */

		// autorizzazione
		// 1. ottiene il chiamante esterno per il tracciamento della chiamata e la validazione dell'autorizzazione
		EpayDChiamanteEsterno chiamanteEsternoEntity = chiamanteEsternoService.findByCodiceChiamante ( user.getName () );
		if ( chiamanteEsternoEntity == null ) {
			Response response = generateForbiddenResponse ( SERVICE_PAYMENT_TYPES, user.getName () );
			chiamataEsternaNonValidaService.track ( null, user, organizationFiscalCode, null, ( (Error) response.getEntity () ).getDetail () );
			return response;
		}
		Log.info ( methodName + "chiamanteEsternoEntity:" + chiamanteEsternoEntity );
		//
		// 2. tracciamento della chiamata
		tracciabilitaChiamanteEsternoService.trackExternalCall ( null, organizationFiscalCode, chiamanteEsternoEntity, null, user, null );
		Log.info ( methodName + "chiamanteEsterno tracciato" );
		//
		// 3. validazione autorizzazione
		if ( chiamanteAutorizzazioneChiamanteService.countByCodiceChiamanteAndCodiceAutorizzazioneChiamante (
			user.getName (), Scopes.TIPOLOGIE_VERS_SPONTANEO.name () ) < 1 ) {
			Response response = generateUnauthorizedResponse ( SERVICE_PAYMENT_TYPES );
			chiamataEsternaNonValidaService.track ( null, user, organizationFiscalCode, null, ( (Error) response.getEntity () ).getDetail () );
			return response;
		}
		Log.info ( methodName + "authorization OK" );

		// validazione input
		List<String> notValids = getNotValidInputs ( organizationFiscalCode, paymentTypesDescription, currentPage, elements, sort, fields );
		if ( !notValids.isEmpty () ) {
			Response response = generateValidationErrorResponse ( SERVICE_PAYMENT_TYPES, notValids );
			chiamataEsternaNonValidaService.track ( null, user, organizationFiscalCode, null, ( (Error) response.getEntity () ).getDetail () );
			return response;
		}
		Log.info ( methodName + "validation OK" );

		/* --- logica di business --- */

		// ottiene l'ente corrispondente al codice fiscale
		EpayTEnti enteEntity = enteService.findByCodiceFiscale ( organizationFiscalCode );
		if ( enteEntity == null ) {
			Response response = generateNotFoundErrorResponse ( SERVICE_PAYMENT_TYPES, ERROR_ENTE_NOT_FOUND, organizationFiscalCode );
			chiamataEsternaNonValidaService.track ( null, user, organizationFiscalCode, null, ( (Error) response.getEntity () ).getDetail () );
			return response;
		}
		Log.info ( methodName + "ente:" + enteEntity );

		// ottiene la tipologia di pagamento spontaneo
		EpayDTipologiaPagamento tipologiaPagamentoSpontaneoEntity = tipologiaPagamentoService.findByCodice ( CODICE_TIPOLOGIA_PAGAMENTO_SPONTANEO );
		if ( tipologiaPagamentoSpontaneoEntity == null ) {
			Response response
				= generateBusinessErrorResponse ( SERVICE_PAYMENT_TYPES, ERROR_TIPOLOGIA_PAGAMENTO_NOT_DEFINED,
					CODICE_TIPOLOGIA_PAGAMENTO_SPONTANEO );
			chiamataEsternaNonValidaService.track ( null, user, organizationFiscalCode, null, ( (Error) response.getEntity () ).getDetail () );
			return response;
		}
		Log.info ( methodName + "tipologia pagamento spontaneo OK" );

		// ottiene il numero massimo di elementi per pagina di default se non specificato il parametro di input elements
		if ( elements == null ) {
			ConfigurazioneServiceElementsDTO confServiceResponse
				= configurazioneService.getMaxElementPerPage ( SERVICE_PAYMENT_TYPES, user, organizationFiscalCode );
			if ( confServiceResponse.isOk () ) {
				elements = confServiceResponse.getElements ();
			} else {
				Response response = confServiceResponse.getResponse ();
				chiamataEsternaNonValidaService.track ( null, user, organizationFiscalCode, null, ( (Error) response.getEntity () ).getDetail () );
				return response;
			}
		}

		// ottiene i tipi di pagamento ordinata
		PagedListResultDTO<EpayTTipoPagamento> pagedListResult = tipoPagamentoService.findByEnteAndTipologiaPagamentoAndLikeDescrizione (
			enteEntity,
			tipologiaPagamentoSpontaneoEntity,
			paymentTypesDescription,
			SORTABLE_FIELDS,
			sort,
			currentPage != null ? currentPage - 1 : 0, // N.B zero-based
			elements );
		Log.info ( methodName + "ricerca eseguita con successo" );

		// costruisce la response
		PaymentTypes paymentTypes = new PaymentTypes ();
		paymentTypes.setPaymentTypes ( buildPaymentTypeList ( pagedListResult.getList (), fields ) );
		paymentTypes.setCurrentPage ( pagedListResult.getCurrentPage () );
		paymentTypes.setPageSize ( pagedListResult.getPageSize () );
		paymentTypes.setTotalElements ( (int) pagedListResult.getTotalElements () ); // forzatura, dato che l'editor swagger non accetta tipi long
		paymentTypes.setTotPages ( pagedListResult.getTotalPages () );

		Log.info ( methodName + "END" );
		return Response.status ( Response.Status.OK ).entity ( paymentTypes ).build ();
	}

	private List<String> getNotValidInputs (
		String organizationFiscalCode,
		String paymentTypesDescription,
		Integer currentPage,
		Integer elements,
		String sort,
		String fields ) {

		List<String> notValids = new LinkedList<> ();
		if ( !organizationFiscalCode.matches ( REGEX_IUV_CF ) ) {
			notValids.add ( SERVICE_FIELDS_PAYMENT_TYPES__ORGANIZATION_FISCAL_CODE );
		}

		if ( paymentTypesDescription != null && paymentTypesDescription.trim ().length () < 3 ) {
			notValids.add ( SERVICE_FIELDS_PAYMENT_TYPES__PAYMENT_TYPES_DESCRIPTION );
		}
		if ( !StringUtils.isBlank ( paymentTypesDescription ) && !paymentTypesDescription.matches ( REGEX_NORMAL_TEXT ) ) {
			notValids.add ( SERVICE_FIELDS_PAYMENT_TYPES__PAYMENT_TYPES_DESCRIPTION );
		}
		return validateCommonFields ( notValids, currentPage, elements, sort, fields, SORTABLE_FIELDS, VALID_FIELDS );
	}

	private List<PaymentType> buildPaymentTypeList ( List<EpayTTipoPagamento> entityList, String fields ) {
		String methodName = "[buildPaymentTypeList] ";
		Log.info ( methodName + "BEGIN" );
		Log.info ( methodName + "fields:" + fields );

		Set<String> fieldSet = FieldsUtil.getInputFieldSet ( VALID_FIELDS, fields );
		Log.info ( methodName + "fieldSet:" + fieldSet );

		List<PaymentType> paymentTypeList = new ArrayList<> ();
		for ( EpayTTipoPagamento entity: entityList ) {
			XPaymentType xpaymentType = new XPaymentType ();

			// campi obbligatori
			xpaymentType.setCodiceVersamento ( entity.getCodiceVersamento () );
			xpaymentType.setDescrizioneVersamento ( entity.getDescrizionePortale () );

			// campi opzionali
			if ( fieldSet.contains ( SERVICE_FIELDS_PAYMENT_TYPES__NOTE_PER_IL_PAGATORE ) ) {
				xpaymentType.setNotePerIlPagatore ( entity.getCompilazioneNote () );
			}
			if ( fieldSet.contains ( SERVICE_FIELDS_PAYMENT_TYPES__NOTE_OBBLIGATORIE ) ) {
				xpaymentType.setNoteObbligatorie ( entity.getCompilazioneNote () != null && !entity.getCompilazioneNote ().isEmpty () );
			}

			paymentTypeList.add ( xpaymentType );
		}

		Log.info ( methodName + "END" );
		return paymentTypeList;
	}
}
