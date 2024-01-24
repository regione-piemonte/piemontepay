/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.service.external;

import io.quarkiverse.cxf.annotation.CXFClient;
import io.quarkus.logging.Log;
import it.csi.epay.epayfeapi.dto.PagamentoComponentiDTO;
import it.csi.epay.epayfeapi.dto.PagamentoDTO;
import it.csi.epay.epayfeapi.dto.TransazioneMdpDTO;
import it.csi.epay.epayfeapi.entity.EpayDChiamanteEsterno;
import it.csi.epay.epayfeapi.entity.EpayTConfigurazione;
import it.csi.epay.epayfeapi.entity.EpayTEnti;
import it.csi.epay.epayfeapi.entity.EpayTTipoPagamento;
import it.csi.epay.epayfeapi.entity.EpayTTracciabilitaChiamanteEsterno;
import it.csi.epay.epayfeapi.exception.MdpException;
import it.csi.epay.epayfeapi.exception.TassonomiaServiceException;
import it.csi.epay.epayfeapi.mapper.TransazioneMdpMapper;
import it.csi.epay.epayfeapi.model.DatiSpecificiRiscossione;
import it.csi.epay.epayfeapi.model.DatiSpecificiRiscossioneInput;
import it.csi.epay.epayfeapi.model.DatiSpecificiRiscossioneOutput;
import it.csi.epay.epayfeapi.rest.client.CodiciEsito;
import it.csi.epay.epayfeapi.rest.client.TassonomiaAdapterClient;
import it.csi.epay.epayfeapi.security.AuthenticationContext;
import it.csi.epay.epayfeapi.security.Scopes;
import it.csi.epay.epayfeapi.security.User;
import it.csi.epay.epayfeapi.service.ChiamanteAutorizzazioneChiamanteService;
import it.csi.epay.epayfeapi.service.ChiamanteEsternoService;
import it.csi.epay.epayfeapi.service.ChiamataEsternaNonValidaService;
import it.csi.epay.epayfeapi.service.ConfigurazioneService;
import it.csi.epay.epayfeapi.service.EnteService;
import it.csi.epay.epayfeapi.service.MdpCoreCxfServiceWrapper;
import it.csi.epay.epayfeapi.service.PagamentoService;
import it.csi.epay.epayfeapi.service.RPTService;
import it.csi.epay.epayfeapi.service.RegistroVersamentiService;
import it.csi.epay.epayfeapi.service.TipoPagamentoCollegatoService;
import it.csi.epay.epayfeapi.service.TipoPagamentoService;
import it.csi.epay.epayfeapi.service.TracciabilitaChiamanteEsternoService;
import it.csi.epay.epayfeapi.service.TransazioneMdpService;
import it.csi.epay.epayfeapi.soap.client.mdp.core.interfacecxf.ElencoRPT;
import it.csi.epay.epayfeapi.soap.client.mdp.core.interfacecxf.IMdpCoreCxf;
import it.csi.epay.epayfeapi.soap.client.mdp.core.interfacecxf.Transazione;
import it.csi.epay.epayfeapi.soap.client.mdpmultiiuv.IuvComplex;
import it.csi.epay.epayfeapi.util.MultiIuv;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.openapitools.model.Error;
import org.openapitools.model.PaymentDataPayment;
import org.openapitools.model.PaymentReferences;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static it.csi.epay.epayfeapi.dto.OrigineChiamata.CITTA_FACILE;
import static it.csi.epay.epayfeapi.dto.StatoPagamento.TRANSAZIONE_AVVIATA;
import static it.csi.epay.epayfeapi.dto.StatoPagamento.TRANSAZIONE_ERRORE;
import static it.csi.epay.epayfeapi.dto.StatoPagamento.TRANSAZIONE_INIZIALIZZATA;
import static it.csi.epay.epayfeapi.util.Constants.CONFIG_ENDPOINT_SERVICE_TASSONOMIA;
import static it.csi.epay.epayfeapi.util.Constants.ERROR_CAMPO_NOTE_OBBLIGATORIO;
import static it.csi.epay.epayfeapi.util.Constants.ERROR_CODICE_VERSAMENTO_MULTIBENEFICIARIO;
import static it.csi.epay.epayfeapi.util.Constants.ERROR_CODICE_VERSAMENTO_NOT_ENABLED_YET;
import static it.csi.epay.epayfeapi.util.Constants.ERROR_CODICE_VERSAMENTO_NOT_SPONTANEO;
import static it.csi.epay.epayfeapi.util.Constants.ERROR_CODICE_VERSAMENTO_NOT_VALID;
import static it.csi.epay.epayfeapi.util.Constants.ERROR_ENTE_NOT_FOUND;
import static it.csi.epay.epayfeapi.util.Constants.ERROR_GET_DATI_SPECIFICI_RISCOSSIONE;
import static it.csi.epay.epayfeapi.util.Constants.ERROR_IUV_GENERATION;
import static it.csi.epay.epayfeapi.util.Constants.ERROR_MDP_DATI_COMPONENTI_NON_CONGRUENTI;
import static it.csi.epay.epayfeapi.util.Constants.ERROR_MDP_ERRORE_GENERICO;
import static it.csi.epay.epayfeapi.util.Constants.ERROR_TIPO_PAGAMENTO_NOT_FOUND;
import static it.csi.epay.epayfeapi.util.Constants.ERROR_TIPO_PAGAMENTO_NOT_UNIQUE;
import static it.csi.epay.epayfeapi.util.Constants.MAX_IMPORTO;
import static it.csi.epay.epayfeapi.util.Constants.PAGAMENTO_SPONTANEO_CODE;
import static it.csi.epay.epayfeapi.util.Constants.REGEX_EMAIL;
import static it.csi.epay.epayfeapi.util.Constants.REGEX_IUV_CF;
import static it.csi.epay.epayfeapi.util.Constants.REGEX_NORMAL_TEXT;
import static it.csi.epay.epayfeapi.util.Constants.REGEX_PAYMENT_TYPE;
import static it.csi.epay.epayfeapi.util.Constants.RPT_DUPLICATA;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_FIELDS_DEBT_POSITION__PAYMENT_DATA_NOTE;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_FIELDS_PAGAMENTO_SPONTANEO__CODICE_FISCALE_PARTIVA_IVA_PAGATORE;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_FIELDS_PAGAMENTO_SPONTANEO__CODICE_FISCALE_PARTIVA_IVA_PAGATORE_MAGGIORE_DI_16;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_FIELDS_PAGAMENTO_SPONTANEO__CODICE_FISCALE_PARTIVA_IVA_PAGATORE_MINORE_DI_11;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_FIELDS_PAGAMENTO_SPONTANEO__ORGANIZATION_FISCAL_CODE;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_FIELDS_PAGAMENTO_SPONTANEO__PAYMENT_DATA;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_FIELDS_PAGAMENTO_SPONTANEO__PAYMENT_DATA_COGNOME;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_FIELDS_PAGAMENTO_SPONTANEO__PAYMENT_DATA_EMAIL;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_FIELDS_PAGAMENTO_SPONTANEO__PAYMENT_DATA_IMPORTO;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_FIELDS_PAGAMENTO_SPONTANEO__PAYMENT_DATA_NOME;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_FIELDS_PAGAMENTO_SPONTANEO__PAYMENT_DATA_NOTE;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_FIELDS_PAGAMENTO_SPONTANEO__PAYMENT_TYPE;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_PAYMENT;
import static it.csi.epay.epayfeapi.util.Constants.SYSTEM_USER_DEFAULT;
import static it.csi.epay.epayfeapi.util.ResponseUtil.generateBusinessErrorResponse;
import static it.csi.epay.epayfeapi.util.ResponseUtil.generateForbiddenResponse;
import static it.csi.epay.epayfeapi.util.ResponseUtil.generateInternalErrorResponse;
import static it.csi.epay.epayfeapi.util.ResponseUtil.generateNotFoundErrorResponse;
import static it.csi.epay.epayfeapi.util.ResponseUtil.generateUnauthorizedResponse;
import static it.csi.epay.epayfeapi.util.ResponseUtil.generateValidationErrorResponse;


/*
 * Servizio 6, pagamento spontaneo
 */
@ApplicationScoped
@Transactional
public class PaymentService {

	@Inject
	AuthenticationContext authenticationContext;

	@Inject
	TracciabilitaChiamanteEsternoService tracciabilitaChiamanteEsternoService;

	@Inject
	ChiamanteEsternoService chiamanteEsternoService;

	@Inject
	EnteService enteService;

	@Inject
	TipoPagamentoService tipoPagamentoService;

	@Inject
	TipoPagamentoCollegatoService tipoPagamentoCollegatoService;

	@Inject
	PagamentoService pagamentoService;

	@Inject
	RegistroVersamentiService registroVersamentiService;

	@Inject
	ChiamanteAutorizzazioneChiamanteService chiamanteAutorizzazioneChiamanteService;

	@Inject
	ChiamataEsternaNonValidaService chiamataEsternaNonValidaService;

	@Inject
	TransazioneMdpService transazioneMdpService;

	@Inject
	MdpCoreCxfServiceWrapper mdpCoreCxfServiceWrapper;

	@Inject
	RPTService rptService;

	@Inject
	TransazioneMdpMapper transazioneMdpMapper;

	@Inject
	@CXFClient ( "IMdpCoreCxf" )
	@SuppressWarnings ( "all" )
	IMdpCoreCxf mdpCoreCxf;

	@Inject
	MultiIuv multiIuv;

	public Response getIUV ( String organizationFiscalCode, String paymenttype, PaymentDataPayment input ) {
		String methodName = "[getIUV] ";

		Log.info ( methodName + "BEGIN" );
		Log.info ( methodName + "param organizationFiscalCode:" + organizationFiscalCode );
		Log.info ( methodName + "param paymenttype:" + paymenttype );
		Log.info ( methodName + "param input:" + input );

		User user = authenticationContext.getCurrentUser ();
		Log.info ( methodName + "user:" + user );

		/* --- validazione --- */
		// 1. ottiene il chiamante esterno per il tracciamento della chiamata e la validazione dell'autorizzazione
		EpayDChiamanteEsterno chiamanteEsternoEntity = chiamanteEsternoService.findByCodiceChiamante ( user.getName () );
		if ( null == chiamanteEsternoEntity ) {
			Response response = generateForbiddenResponse ( SERVICE_PAYMENT, user.getName () );
			chiamataEsternaNonValidaService.track ( null, user, organizationFiscalCode, null, ( (Error) response.getEntity () ).getDetail () );
			return response;
		}
		Log.info ( methodName + "chiamanteEsternoEntity:" + chiamanteEsternoEntity );
		//
		// 2. tracciamento della chiamata
		EpayTTracciabilitaChiamanteEsterno track
			= tracciabilitaChiamanteEsternoService.trackExternalCall ( null, input.getCodiceFiscalePartitaIVAPagatore (), chiamanteEsternoEntity, null, user,
				null );
		Log.info ( methodName + "chiamanteEsterno tracciato, idChiamata:" + track.getIdChiamata () );
		//
		// 3. validazione autorizzazione
		if ( chiamanteAutorizzazioneChiamanteService.countByCodiceChiamanteAndCodiceAutorizzazioneChiamante ( user.getName (),
			Scopes.PAGAMENTO_SPONTANEO.name () ) < 1 ) {
			Response response = generateUnauthorizedResponse ( SERVICE_PAYMENT );
			chiamataEsternaNonValidaService.track ( null, user, organizationFiscalCode, null, ( (Error) response.getEntity () ).getDetail () );
			return response;
		}
		Log.info ( methodName + "authorization OK" );

		// validazione input
		List<String> notValids = getNotValidInputs ( organizationFiscalCode, paymenttype, input );
		if ( !notValids.isEmpty () ) {
			Response response = generateValidationErrorResponse ( SERVICE_PAYMENT, notValids );
			chiamataEsternaNonValidaService.track ( null, user, organizationFiscalCode, null, ( (Error) response.getEntity () ).getDetail () );
			return response;
		}
		Log.info ( methodName + "validation OK" );

		/* --- logica di business --- */

		// verifica ente
		EpayTEnti ente = enteService.findByCodiceFiscale ( organizationFiscalCode );
		if ( null == ente ) {
			Response response = generateNotFoundErrorResponse ( SERVICE_PAYMENT, ERROR_ENTE_NOT_FOUND, organizationFiscalCode );
			chiamataEsternaNonValidaService.track ( null, user, organizationFiscalCode, null, ( (Error) response.getEntity () ).getDetail () );
			return response;
		}
		Log.info ( methodName + "fetched ente[id]:" + ente.getIdEnte () );

		// recupera tipo pagamento
		List<EpayTTipoPagamento> tipoPagamentoList = tipoPagamentoService.findByEnteAndCodiceVersamento ( ente, paymenttype );
		if ( null == tipoPagamentoList || tipoPagamentoList.isEmpty () ) {
			Response response = generateBusinessErrorResponse ( SERVICE_PAYMENT, ERROR_TIPO_PAGAMENTO_NOT_FOUND, paymenttype, organizationFiscalCode );
			chiamataEsternaNonValidaService.track ( null, user, organizationFiscalCode, null, ( (Error) response.getEntity () ).getDetail () );
			return response;
		}
		if ( tipoPagamentoList.size () > 1 ) {
			Response response = generateBusinessErrorResponse ( SERVICE_PAYMENT, ERROR_TIPO_PAGAMENTO_NOT_UNIQUE, paymenttype, organizationFiscalCode );
			chiamataEsternaNonValidaService.track ( null, user, organizationFiscalCode, null, ( (Error) response.getEntity () ).getDetail () );
			return response;
		}
		EpayTTipoPagamento tipoPagamento = tipoPagamentoList.get ( 0 );

		// valida campo note, deve essere obbligatorio se il campo compilazione note del tipo pagamento non null
		if ( !StringUtils.isEmpty ( tipoPagamento.getCompilazioneNote () ) && StringUtils.isEmpty ( input.getNote () ) ) {
			chiamataEsternaNonValidaService.track ( null, user, organizationFiscalCode, null, String.format ( ERROR_CAMPO_NOTE_OBBLIGATORIO, paymenttype ) );
			notValids.add ( SERVICE_FIELDS_DEBT_POSITION__PAYMENT_DATA_NOTE );
			return generateValidationErrorResponse ( SERVICE_PAYMENT, notValids );
		}

		// valida tipo pagamento
		Response validateTipoPagResult = validateTipoPagamento ( tipoPagamento );
		if ( null != validateTipoPagResult ) {
			chiamataEsternaNonValidaService.track ( null, user, organizationFiscalCode, null, ( (Error) validateTipoPagResult.getEntity () ).getDetail () );
			return validateTipoPagResult;
		}

		// chiamate Soap web service MDP
		IuvComplex iuvComplex;
		try {
			List<IuvComplex> iuvList = multiIuv.generateNewIuv ( tipoPagamento, 1 );
			iuvComplex = iuvList.get ( 0 );
			Log.info ( methodName + "generato IUV:" + iuvComplex + " - iuv.iuv:" + ( iuvComplex != null ? iuvComplex.getIuv () : "null" ) );

		} catch ( MdpException e ) {
			Response response = generateInternalErrorResponse ( SERVICE_PAYMENT, ERROR_IUV_GENERATION, tipoPagamento.getIdApplicazione (),
				tipoPagamento.getCodiceVersamento () );
			chiamataEsternaNonValidaService.track ( null, user, organizationFiscalCode, null, ( (Error) response.getEntity () ).getDetail () );
			return response;
		}

		// altro tracciamento (update record di prima)
		assert iuvComplex != null;
		tracciabilitaChiamanteEsternoService.trackExternalCall ( track, input.getCodiceFiscalePartitaIVAPagatore (), chiamanteEsternoEntity,
			iuvComplex.getIuvOttico (), user, null );
		Log.info ( methodName + "call tracked, id caller:" + track.getIdChiamata () );

		// costruzione pagamento
		PagamentoDTO pagamento;
		try {
			pagamento = pagamentoService.buildPayment ( input.getCodiceFiscalePartitaIVAPagatore (), input.getNome (), input.getCognome (),
				input.getRagioneSociale (), input.getImporto (), input.getEmail (), input.getNote (), tipoPagamento, iuvComplex, organizationFiscalCode, null );
		} catch ( Exception e ) {
			Response response = generateInternalErrorResponse ( SERVICE_PAYMENT, e.getMessage () );
			chiamataEsternaNonValidaService.track ( null, user, organizationFiscalCode, null, ( (Error) response.getEntity () ).getDetail () );
			return response;
		}

		// chiamate Soap web service MDP
		String appId = pagamento.getTipoPagamento ().getIdApplicazione ();
		Transazione transazione;
		Log.info ( "Call iMdpCoreCxf.initTransazione" );
		Log.info ( "     Param IdApplicazione        : " + appId );
		transazione = mdpCoreCxf.initTransazione ( appId, null );
		Log.info ( "Response iMdpCoreCxf.initTransazione (transactionId):" );
		Log.info ( transazione.getTransactionId () );
		transazione.setAmount ( pagamento.getImporto ().doubleValue () );
		Log.info ( methodName + "inizializzata transazione:" + transazione.getTransactionId () );

		TransazioneMdpDTO transazioneMdp = new TransazioneMdpDTO ();
		transazioneMdp.setIdTransazione ( transazione.getTransactionId () );
		transazioneMdp.setIuv ( pagamento.getIuvRegistroVersamenti () );
		transazioneMdpService.save ( transazioneMdpMapper.toEntity ( transazioneMdp ) );
		Log.info ( methodName + "inserita traccia transazione:" + transazioneMdp );

		tracciabilitaChiamanteEsternoService.trackExternalCall ( track, input.getCodiceFiscalePartitaIVAPagatore (), chiamanteEsternoEntity,
			iuvComplex.getIuvOttico (), user, transazioneMdp );
		registroVersamentiService.tracciaRegistroPagamento ( pagamento, TRANSAZIONE_INIZIALIZZATA, transazioneMdp, CITTA_FACILE );

		ElencoRPT datiRPT = rptService.costruisciRPT ( pagamento, transazione, false );
		Log.info ( methodName + "costruito payload RPT:" + datiRPT );

		String urlPagamento;
		try {
			urlPagamento = mdpCoreCxfServiceWrapper.getPaymentURL ( transazione, datiRPT, Boolean.TRUE.equals ( tipoPagamento.getFlagMultibeneficiario () ) );
			registroVersamentiService.tracciaRegistroPagamento ( pagamento, TRANSAZIONE_AVVIATA, transazioneMdp, CITTA_FACILE );

		} catch ( MdpException e ) {
			registroVersamentiService.tracciaRegistroPagamento ( pagamento, TRANSAZIONE_ERRORE, transazioneMdp, CITTA_FACILE );
			Response response;
			if ( e.getCause () != null && e.getCause ().getMessage () != null && e.getCause ().getMessage ().contains ( RPT_DUPLICATA ) ) {
				response = generateInternalErrorResponse ( SERVICE_PAYMENT, ERROR_MDP_DATI_COMPONENTI_NON_CONGRUENTI );
			} else {
				response = generateInternalErrorResponse ( SERVICE_PAYMENT, ERROR_MDP_ERRORE_GENERICO );
			}
			chiamataEsternaNonValidaService.track ( null, user, organizationFiscalCode, null, ( (Error) response.getEntity () ).getDetail () );
			return response;
		}
		Log.info ( methodName + "URL mdp pagamento:" + urlPagamento );

		PaymentReferences paymentReferences = new PaymentReferences ();
		paymentReferences.setIuv ( iuvComplex.getIuvOttico () );
		paymentReferences.setPaymentUrl ( urlPagamento );

		Log.info ( methodName + "END" );
		return Response.status ( Response.Status.CREATED ).entity ( paymentReferences ).build ();
	}

	private List<String> getNotValidInputs ( String organizationFiscalCode, String paymenttype, PaymentDataPayment paymentData ) {
		List<String> notValids = new LinkedList<> ();

		if ( StringUtils.isBlank ( organizationFiscalCode ) ) {
			notValids.add ( SERVICE_FIELDS_PAGAMENTO_SPONTANEO__ORGANIZATION_FISCAL_CODE );
		} else if ( !organizationFiscalCode.matches ( REGEX_IUV_CF ) ) {
			notValids.add ( SERVICE_FIELDS_PAGAMENTO_SPONTANEO__ORGANIZATION_FISCAL_CODE );
		}

		if ( StringUtils.isBlank ( paymenttype ) ) {
			notValids.add ( SERVICE_FIELDS_PAGAMENTO_SPONTANEO__PAYMENT_TYPE );
		} else if ( !paymenttype.matches ( REGEX_PAYMENT_TYPE ) ) {
			notValids.add ( SERVICE_FIELDS_PAGAMENTO_SPONTANEO__PAYMENT_TYPE );
		}

		if ( paymentData == null ) {
			notValids.add ( SERVICE_FIELDS_PAGAMENTO_SPONTANEO__PAYMENT_DATA );
		} else {
			if ( !StringUtils.isBlank ( paymentData.getNote () ) && !paymentData.getNote ().matches ( REGEX_NORMAL_TEXT ) ) {
				notValids.add ( SERVICE_FIELDS_PAGAMENTO_SPONTANEO__PAYMENT_DATA_NOTE );
			}

			if ( paymentData.getImporto () == null
				|| paymentData.getImporto ().doubleValue () <= 0 || paymentData.getImporto ().doubleValue () > MAX_IMPORTO ) {
				notValids.add ( SERVICE_FIELDS_PAGAMENTO_SPONTANEO__PAYMENT_DATA_IMPORTO );
			}

			if ( !StringUtils.isBlank ( paymentData.getEmail () ) && !paymentData.getEmail ().matches ( REGEX_EMAIL ) ) {
				notValids.add ( SERVICE_FIELDS_PAGAMENTO_SPONTANEO__PAYMENT_DATA_EMAIL );
			}

			if ( StringUtils.isBlank ( paymentData.getRagioneSociale () ) ) {
				if ( StringUtils.isBlank ( paymentData.getNome () ) ) {
					notValids.add ( SERVICE_FIELDS_PAGAMENTO_SPONTANEO__PAYMENT_DATA_NOME );
				} else if ( !paymentData.getNome ().matches ( REGEX_NORMAL_TEXT ) ) {
					notValids.add ( SERVICE_FIELDS_PAGAMENTO_SPONTANEO__PAYMENT_DATA_NOME );
				}

				if ( StringUtils.isBlank ( paymentData.getCognome () ) ) {
					notValids.add ( SERVICE_FIELDS_PAGAMENTO_SPONTANEO__PAYMENT_DATA_COGNOME );
				} else if ( !paymentData.getCognome ().matches ( REGEX_NORMAL_TEXT ) ) {
					notValids.add ( SERVICE_FIELDS_PAGAMENTO_SPONTANEO__PAYMENT_DATA_COGNOME );
				}
			}

			if ( paymentData.getCodiceFiscalePartitaIVAPagatore () != null ) {
				String codiceFiscalePartitaIVAPagatore = paymentData.getCodiceFiscalePartitaIVAPagatore ();
				if ( codiceFiscalePartitaIVAPagatore.isBlank () ) {
					notValids.add ( SERVICE_FIELDS_PAGAMENTO_SPONTANEO__CODICE_FISCALE_PARTIVA_IVA_PAGATORE );
				} else {
					if ( codiceFiscalePartitaIVAPagatore.length () < 11 ) {
						if ( !codiceFiscalePartitaIVAPagatore.equals ( "ANONIMO" ) ) {
							notValids.add ( SERVICE_FIELDS_PAGAMENTO_SPONTANEO__CODICE_FISCALE_PARTIVA_IVA_PAGATORE_MINORE_DI_11 );
						}
					}
					if (codiceFiscalePartitaIVAPagatore.length () > 16 ) {
						notValids.add ( SERVICE_FIELDS_PAGAMENTO_SPONTANEO__CODICE_FISCALE_PARTIVA_IVA_PAGATORE_MAGGIORE_DI_16 );
					}
				}
			} else { 
				notValids.add ( SERVICE_FIELDS_PAGAMENTO_SPONTANEO__CODICE_FISCALE_PARTIVA_IVA_PAGATORE );
			}
		}

		return notValids;
	}

	private Response validateTipoPagamento ( EpayTTipoPagamento tipoPagamento ) {
		Date today = Date.from ( LocalDate.now ().atStartOfDay ( ZoneId.systemDefault () ).toInstant () );
		if ( tipoPagamento.getInizioValidita () == null || tipoPagamento.getInizioValidita ().after ( today ) ) {
			return generateBusinessErrorResponse ( SERVICE_PAYMENT, ERROR_CODICE_VERSAMENTO_NOT_ENABLED_YET, tipoPagamento.getCodiceVersamento () );
		}
		if ( tipoPagamento.getFineValidita () != null && tipoPagamento.getFineValidita ().before ( today ) ) {
			return generateBusinessErrorResponse ( SERVICE_PAYMENT, ERROR_CODICE_VERSAMENTO_NOT_VALID, tipoPagamento.getCodiceVersamento () );
		}
		if ( Boolean.TRUE.equals ( tipoPagamento.getFlagMultibeneficiario () ) ) {
			return generateBusinessErrorResponse ( SERVICE_PAYMENT, ERROR_CODICE_VERSAMENTO_MULTIBENEFICIARIO, tipoPagamento.getCodiceVersamento () );
		}
		if ( tipoPagamentoCollegatoService.countByIdPagamentoSecondario ( tipoPagamento.getIdTipoPagamento () ) > 0 ) {
			return generateBusinessErrorResponse ( SERVICE_PAYMENT, ERROR_CODICE_VERSAMENTO_MULTIBENEFICIARIO, tipoPagamento.getCodiceVersamento () );
		}
		if ( ! ( Boolean.TRUE.equals ( tipoPagamento.getPagamentoSpontaneo () ) ) ) {
			return generateBusinessErrorResponse ( SERVICE_PAYMENT, ERROR_CODICE_VERSAMENTO_NOT_SPONTANEO, tipoPagamento.getCodiceVersamento () );
		}
		if ( Boolean.FALSE.equals ( tipoPagamento.getPagamentoSpontaneo () ) && !tipoPagamento.getTipologiaPagamento ().getCodice ()
			.equalsIgnoreCase ( PAGAMENTO_SPONTANEO_CODE ) ) {
			return generateBusinessErrorResponse ( SERVICE_PAYMENT, ERROR_CODICE_VERSAMENTO_NOT_SPONTANEO, tipoPagamento.getCodiceVersamento () );
		}
		return null;
	}

	public static DatiSpecificiRiscossioneOutput getDatiSpecificiRiscossioneOutput ( EpayTTipoPagamento tipoPagamento, String organizationFiscalCode,
		ConfigurazioneService configurazioneService, TassonomiaAdapterClient tassonomiaAdapterClient ) throws Exception {
		EpayTEnti ente = new EpayTEnti ();
		ente.setIdEnte ( 0L ); // fatto per prendere sempre il record di default per tutti gli enti
		EpayTConfigurazione config = configurazioneService.findByCodiceAndCodiceAndEnte ( CONFIG_ENDPOINT_SERVICE_TASSONOMIA, ente );
		if ( null == config ) {
			Log.error ( ERROR_GET_DATI_SPECIFICI_RISCOSSIONE );
			throw new Exception ( ERROR_GET_DATI_SPECIFICI_RISCOSSIONE );
		}
		String url = config.getValore ();
		DatiSpecificiRiscossioneInput request = new DatiSpecificiRiscossioneInput ();
		request.setCodiceFiscaleEnte ( organizationFiscalCode );
		request.setTipoPagamento ( tipoPagamento.getCodiceVersamento () );
		request.setAnnoEsercizio ( Calendar.getInstance ().get ( Calendar.YEAR ) );
		return tassonomiaAdapterClient.getDatiSpecificiRiscossione ( request, url );
	}

	public static PagamentoComponentiDTO getPagamentoComponentiDTO ( DatiSpecificiRiscossioneOutput dsr, BigDecimal importo, int maxErrorMessageWidth )
					throws TassonomiaServiceException {
		PagamentoComponentiDTO componente = new PagamentoComponentiDTO ();
		componente.setProgressivo ( 1 );
		componente.setImporto ( importo );
		componente.setUtenteUltimaModifica ( SYSTEM_USER_DEFAULT );
		List<DatiSpecificiRiscossione> listDati = new LinkedList<> ();
		for ( DatiSpecificiRiscossione dato: dsr.getElencoDatiSpecifici () ) {
			if ( null != dato.getAnnoAccertamento () && null != dato.getNumeroAccertamento () ) {
				listDati.add ( dato );
			}
		}
		if ( CollectionUtils.isEmpty ( listDati ) || listDati.size () > 1 ) {
			CodiciEsito ce = CodiciEsito.ERRORE_DATI_SPECIFICI_RISCOSSIONE;
			throw new TassonomiaServiceException ( ce.getCodice (), ce.getMessaggio (
				maxErrorMessageWidth ) + " , per la posizione debitoria: " + null
				+ ", non essendo specificate componenti viene richiesto un'unico riferimento contabile, con anno e numero accertamento specificato, valido per l'anno in corso su Catalogo!" );
		} else {
			componente.setAnnoAccertamento ( listDati.get ( 0 ).getAnnoAccertamento () );
			componente.setDatiSpecificiRiscossione ( listDati.get ( 0 ).getCodice () );
			componente.setNumeroAccertamento ( listDati.get ( 0 ).getNumeroAccertamento () );
			StringUtils.isNotBlank ( null );
			componente.setCausale ( listDati.get ( 0 ).getDescrizione () );
			componente.setCodiceTributo ( listDati.get ( 0 ).getCodiceTributo () );
		}
		return componente;
	}
}
