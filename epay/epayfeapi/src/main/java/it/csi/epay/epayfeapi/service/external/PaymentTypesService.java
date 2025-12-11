/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.service.external;

import io.quarkus.logging.Log;
import it.csi.epay.epayfeapi.entity.EpayTTipoPagamento;
import it.csi.epay.epayfeapi.enumeration.Scopes;
import it.csi.epay.epayfeapi.model.filtrable.XPaymentType;
import it.csi.epay.epayfeapi.security.AuthenticationContext;
import it.csi.epay.epayfeapi.service.ChiamanteAutorizzazioneChiamanteService;
import it.csi.epay.epayfeapi.service.ChiamanteEsternoService;
import it.csi.epay.epayfeapi.service.ChiamataEsternaNonValidaService;
import it.csi.epay.epayfeapi.service.ConfigurazioneService;
import it.csi.epay.epayfeapi.service.EnteService;
import it.csi.epay.epayfeapi.service.TipoPagamentoService;
import it.csi.epay.epayfeapi.service.TipologiaPagamentoService;
import it.csi.epay.epayfeapi.service.TracciabilitaChiamanteEsternoService;
import it.csi.epay.epayfeapi.util.FieldsUtil;
import org.apache.commons.lang3.StringUtils;
import org.openapitools.model.Error;
import org.openapitools.model.PaymentType;
import org.openapitools.model.PaymentTypes;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static it.csi.epay.epayfeapi.util.Constants.ERROR_ENTE_NOT_FOUND;
import static it.csi.epay.epayfeapi.util.Constants.ERROR_TIPOLOGIA_PAGAMENTO_NOT_DEFINED;
import static it.csi.epay.epayfeapi.util.Constants.REGEX_IUV_CF;
import static it.csi.epay.epayfeapi.util.Constants.REGEX_NORMAL_TEXT;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_FIELDS_PAYMENT_TYPES__CODICE_VERSAMENTO;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_FIELDS_PAYMENT_TYPES__DESCRIZIONE_VERSAMENTO;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_FIELDS_PAYMENT_TYPES__IMPORTO_FISSO;
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
import static it.csi.epay.epayfeapi.util.ResponseUtil.generateUnauthorizedEnteResponse;


/*
 * Servizio 5 - Elenco tipologie di pagamento spontaneo associate ad uno specifico ente
 */
@ApplicationScoped
@Transactional
public class PaymentTypesService {

	private static final String[] SORTABLE_FIELDS = new String[] {
					SERVICE_FIELDS_PAYMENT_TYPES__CODICE_VERSAMENTO,
					SERVICE_FIELDS_PAYMENT_TYPES__DESCRIZIONE_VERSAMENTO,
	};

	private static final String[] VALID_FIELDS = new String[] {
					SERVICE_FIELDS_PAYMENT_TYPES__CODICE_VERSAMENTO,
					SERVICE_FIELDS_PAYMENT_TYPES__DESCRIZIONE_VERSAMENTO,
					SERVICE_FIELDS_PAYMENT_TYPES__NOTE_PER_IL_PAGATORE,
					SERVICE_FIELDS_PAYMENT_TYPES__NOTE_OBBLIGATORIE,
					SERVICE_FIELDS_PAYMENT_TYPES__IMPORTO_FISSO,
	};

	private static final String CODICE_TIPOLOGIA_PAGAMENTO_SPONTANEO = "PS";

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

	public Response getPaymentTypes (
					String organizationFiscalCode,
					String paymentTypesDescription,
					Integer currentPage,
					Integer elements,
					String sort,
					String fields,
					long initialMoment,
					String serviceName ) {
		var methodName = "[CF-V0-getPaymentTypes-V0] ";
		Log.infof ( "%sBEGIN", methodName );
		Log.infof ( "%sparam organizationFiscalCode:%s", methodName, organizationFiscalCode );
		Log.infof ( "%sparam paymentTypesDescription:%s", methodName, paymentTypesDescription );
		Log.infof ( "%sparam currentPage:%d", methodName, currentPage );
		Log.infof ( "%sparam elements:%d", methodName, elements );
		Log.infof ( "%sparam sort:%s", methodName, sort );
		Log.infof ( "%sparam fields:%s", methodName, fields );

		var user = authenticationContext.getCurrentUser ();
		Log.infof ( "%suser:%s", methodName, user );

		/* --- validazione --- */

		// autorizzazione
		// 1. ottiene il chiamante esterno per il tracciamento della chiamata e la validazione dell'autorizzazione
		var chiamanteEsternoEntity = chiamanteEsternoService.findByCodiceChiamante ( user.getName () );
		if ( chiamanteEsternoEntity == null ) {
			var response = generateForbiddenResponse ( SERVICE_PAYMENT_TYPES, user.getName () );
			chiamataEsternaNonValidaService.track ( null, user, organizationFiscalCode, null, ( (Error) response.getEntity () ).getDetail (), initialMoment, serviceName );
			return response;
		}
		Log.infof ( "%schiamanteEsternoEntity:%s", methodName, chiamanteEsternoEntity );
		//
		// 2. tracciamento della chiamata
		tracciabilitaChiamanteEsternoService.trackExternalCall ( null, organizationFiscalCode, chiamanteEsternoEntity, null, user, null, initialMoment, serviceName );
		Log.infof ( "%schiamanteEsterno tracciato", methodName );
		//
		// 3. validazione autorizzazione
		if ( chiamanteAutorizzazioneChiamanteService.countByCodiceChiamanteAndCodiceAutorizzazioneChiamante (
						user.getName (), Scopes.TIPOLOGIE_VERS_SPONTANEO.name () ) < 1 ) {
			var response = generateUnauthorizedResponse ( SERVICE_PAYMENT_TYPES );
			chiamataEsternaNonValidaService.track ( null, user, organizationFiscalCode, null, ( (Error) response.getEntity () ).getDetail (), initialMoment, serviceName );
			return response;
		}
		Log.infof ( "%sauthorization OK", methodName );

		// validazione input
		var notValids = getNotValidInputs ( organizationFiscalCode, paymentTypesDescription, currentPage, elements, sort, fields );
		if ( !notValids.isEmpty () ) {
			var response = generateValidationErrorResponse ( SERVICE_PAYMENT_TYPES, notValids );
			chiamataEsternaNonValidaService.track ( null, user, organizationFiscalCode, null, ( (Error) response.getEntity () ).getDetail (), initialMoment, serviceName );
			return response;
		}
		Log.infof ( "%svalidation OK", methodName );

		/* --- logica di business --- */

		// ottiene l'ente corrispondente al codice fiscale
		var enteEntity = enteService.findByCodiceFiscale ( organizationFiscalCode );
		if ( enteEntity == null ) {
			var response = generateNotFoundErrorResponse ( SERVICE_PAYMENT_TYPES, ERROR_ENTE_NOT_FOUND, organizationFiscalCode );
			chiamataEsternaNonValidaService.track ( null, user, organizationFiscalCode, null, ( (Error) response.getEntity () ).getDetail (), initialMoment, serviceName );
			return response;
		}
		
		if ( enteEntity.getFlagAdesioneCittaFacile()==null || !enteEntity.getFlagAdesioneCittaFacile()) {
			var response = generateUnauthorizedEnteResponse ( SERVICE_PAYMENT_TYPES);
			chiamataEsternaNonValidaService.track ( null, user, organizationFiscalCode, null, ( (Error) response.getEntity () ).getDetail (), initialMoment, serviceName );
			return response;
		}
		Log.infof ( "%sente:%s", methodName, enteEntity );

		// ottiene la tipologia di pagamento spontaneo
		var tipologiaPagamentoSpontaneoEntity = tipologiaPagamentoService.findByCodice ( CODICE_TIPOLOGIA_PAGAMENTO_SPONTANEO );
		if ( tipologiaPagamentoSpontaneoEntity == null ) {
			var response = generateBusinessErrorResponse ( SERVICE_PAYMENT_TYPES, ERROR_TIPOLOGIA_PAGAMENTO_NOT_DEFINED, CODICE_TIPOLOGIA_PAGAMENTO_SPONTANEO );
			chiamataEsternaNonValidaService.track ( null, user, organizationFiscalCode, null, ( (Error) response.getEntity () ).getDetail (), initialMoment, serviceName );
			return response;
		}
		Log.infof ( "%stipologia pagamento spontaneo OK", methodName );

		// ottiene il numero massimo di elementi per pagina di default se non specificato il parametro di input elements
		if ( null == elements ) {
			var confServiceResponse = configurazioneService.getMaxElementPerPage ( SERVICE_PAYMENT_TYPES, user, organizationFiscalCode, initialMoment );
			if ( confServiceResponse.isOk () ) {
				elements = confServiceResponse.getElements ();
			} else {
				var response = confServiceResponse.getResponse ();
				chiamataEsternaNonValidaService.track ( null, user, organizationFiscalCode, null, ( (Error) response.getEntity () ).getDetail (), initialMoment, serviceName );
				return response;
			}
		}

		// ottiene i tipi di pagamento ordinata
		var pagedListResult = tipoPagamentoService.findByEnteAndTipologiaPagamentoAndLikeDescrizione (
						enteEntity,
						tipologiaPagamentoSpontaneoEntity,
						paymentTypesDescription,
						SORTABLE_FIELDS,
						sort,
						currentPage != null ? currentPage - 1 : 0, // N.B zero-based
						elements );
		Log.infof ( "%sricerca eseguita con successo", methodName );

		// costruisce la response
		var paymentTypes = new PaymentTypes ();
		paymentTypes.setPaymentTypes ( buildPaymentTypeList ( pagedListResult.getList (), fields ) );
		paymentTypes.setCurrentPage ( pagedListResult.getCurrentPage () );
		paymentTypes.setPageSize ( pagedListResult.getPageSize () );
		paymentTypes.setTotalElements ( (int) pagedListResult.getTotalElements () ); // forzatura, dato che l'editor swagger non accetta tipi long
		paymentTypes.setTotPages ( pagedListResult.getTotalPages () );

		Log.infof ( "%sEND", methodName );
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
		var methodName = "[buildPaymentTypeList] ";
		Log.infof ( "%sBEGIN", methodName );
		Log.infof ( "%sfields:%s", methodName, fields );

		var fieldSet = FieldsUtil.getInputFieldSet ( VALID_FIELDS, fields );
		Log.infof ( "%sfieldSet:%s", methodName, fieldSet );

		List<PaymentType> paymentTypeList = new ArrayList<> ();
		for ( var entity : entityList ) {
			paymentTypeList.add ( getxPaymentType ( entity, fieldSet ) );
		}

		Log.infof ( "%sEND", methodName );
		return paymentTypeList;
	}

	private static XPaymentType getxPaymentType ( EpayTTipoPagamento entity, Set<String> fieldSet ) {
		var xpaymentType = new XPaymentType ();

		// campi obbligatori
		xpaymentType.setCodiceVersamento ( entity.getCodiceVersamento () );
		xpaymentType.setDescrizioneVersamento ( entity.getDescrizionePortale () );

		// campi opzionali
		if ( fieldSet.contains ( SERVICE_FIELDS_PAYMENT_TYPES__NOTE_PER_IL_PAGATORE ) ) {
			xpaymentType.setNotePerIlPagatore ( entity.getCompilazioneNote () );
		}
		if ( fieldSet.contains ( SERVICE_FIELDS_PAYMENT_TYPES__IMPORTO_FISSO ) ) {
			xpaymentType.setImportoFisso ( entity.getImportoPagamentoSpontaneo () );
		}
		if ( fieldSet.contains ( SERVICE_FIELDS_PAYMENT_TYPES__NOTE_OBBLIGATORIE ) ) {
			xpaymentType.setNoteObbligatorie ( entity.getCompilazioneNote () != null && !entity.getCompilazioneNote ().isEmpty () );
		}
		return xpaymentType;
	}
}
