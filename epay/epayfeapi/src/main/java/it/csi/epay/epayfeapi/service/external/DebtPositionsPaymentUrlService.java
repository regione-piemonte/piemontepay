/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.service.external;

import io.quarkiverse.cxf.annotation.CXFClient;
import io.quarkus.logging.Log;
import it.csi.epay.epayfeapi.dto.PagamentoDTO;
import it.csi.epay.epayfeapi.dto.StatoPagamento;
import it.csi.epay.epayfeapi.dto.TransazioneMdpDTO;
import it.csi.epay.epayfeapi.entity.EpayDEsitoChiamataEsterna;
import it.csi.epay.epayfeapi.entity.EpayTChiamataEsternaNonValida;
import it.csi.epay.epayfeapi.exception.MdpException;
import it.csi.epay.epayfeapi.mapper.PagamentoMapper;
import it.csi.epay.epayfeapi.mapper.TransazioneMdpMapper;
import it.csi.epay.epayfeapi.security.AuthenticationContext;
import it.csi.epay.epayfeapi.security.Scopes;
import it.csi.epay.epayfeapi.service.ChiamanteAutorizzazioneChiamanteService;
import it.csi.epay.epayfeapi.service.ChiamanteEsternoService;
import it.csi.epay.epayfeapi.service.ChiamataEsternaNonValidaService;
import it.csi.epay.epayfeapi.service.EnteService;
import it.csi.epay.epayfeapi.service.EsitoChiamataEsternaService;
import it.csi.epay.epayfeapi.service.MdpCoreCxfServiceWrapper;
import it.csi.epay.epayfeapi.service.PagamentoService;
import it.csi.epay.epayfeapi.service.RPTService;
import it.csi.epay.epayfeapi.service.RegistroVersamentiService;
import it.csi.epay.epayfeapi.service.TracciabilitaChiamanteEsternoService;
import it.csi.epay.epayfeapi.service.TransazioneMdpService;
import it.csi.epay.epayfeapi.soap.client.mdp.core.interfacecxf.IMdpCoreCxf;
import it.csi.epay.epayfeapi.soap.client.mdp.core.interfacecxf.Transazione;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openapitools.model.Error;
import org.openapitools.model.PaymentIuv;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import static it.csi.epay.epayfeapi.dto.OrigineChiamata.CITTA_FACILE;
import static it.csi.epay.epayfeapi.dto.StatoPagamento.ANNULLATO;
import static it.csi.epay.epayfeapi.dto.StatoPagamento.TRANSAZIONE_AVVIATA;
import static it.csi.epay.epayfeapi.dto.StatoPagamento.TRANSAZIONE_ERRORE;
import static it.csi.epay.epayfeapi.dto.StatoPagamento.TRANSAZIONE_INIZIALIZZATA;
import static it.csi.epay.epayfeapi.util.Constants.ERROR_ENTE_NOT_FOUND;
import static it.csi.epay.epayfeapi.util.Constants.ERROR_IUV_IS_OF_AN_INVALID_PAYMENT;
import static it.csi.epay.epayfeapi.util.Constants.ERROR_IUV_NOT_FOUND_OR_INVALID_PAYMENT;
import static it.csi.epay.epayfeapi.util.Constants.ERROR_MDP_DATI_COMPONENTI_NON_CONGRUENTI;
import static it.csi.epay.epayfeapi.util.Constants.ERROR_MDP_ERRORE_GENERICO;
import static it.csi.epay.epayfeapi.util.Constants.ERROR_REQUIRES_COST_UPDATE_TRUE;
import static it.csi.epay.epayfeapi.util.Constants.ESITO_CHIAMATA_ESTERNA_ERRORE_DATI_COMPONENTI_NON_CONGRUENTI;
import static it.csi.epay.epayfeapi.util.Constants.ESITO_CHIAMATA_ESTERNA_ERRORE_GENERICO;
import static it.csi.epay.epayfeapi.util.Constants.ESITO_CHIAMATA_ESTERNA_OPERAZIONE_COMPLETATA_CON_SUCCESSO;
import static it.csi.epay.epayfeapi.util.Constants.REGEX_IUV_CF;
import static it.csi.epay.epayfeapi.util.Constants.RPT_DUPLICATA;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_FIELDS_GET_PAYMENT_URL__IUV;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_FIELDS_GET_PAYMENT_URL__ORGANIZATION_FISCAL_CODE;
import static it.csi.epay.epayfeapi.util.Constants.SERVICE_GET_PAYMENT_URL;
import static it.csi.epay.epayfeapi.util.ResponseUtil.generateBusinessErrorResponse;
import static it.csi.epay.epayfeapi.util.ResponseUtil.generateForbiddenResponse;
import static it.csi.epay.epayfeapi.util.ResponseUtil.generateInternalErrorResponse;
import static it.csi.epay.epayfeapi.util.ResponseUtil.generateNotFoundErrorResponse;
import static it.csi.epay.epayfeapi.util.ResponseUtil.generateUnauthorizedResponse;
import static it.csi.epay.epayfeapi.util.ResponseUtil.generateValidationErrorResponse;


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
	TransazioneMdpService transazioneMdpService;

	@Inject
	TracciabilitaChiamanteEsternoService tracciabilitaChiamanteEsternoService;

	@Inject
	RegistroVersamentiService registroVersamentiService;

	@Inject
	EsitoChiamataEsternaService esitoChiamataEsternaService;

	@Inject
	ChiamanteAutorizzazioneChiamanteService chiamanteAutorizzazioneChiamanteService;

	@Inject
	MdpCoreCxfServiceWrapper mdpCoreCxfServiceWrapper;

	@Inject
	RPTService rptService;

	@Inject
	ChiamataEsternaNonValidaService chiamataEsternaNonValidaService;

	@Inject
	PagamentoMapper pagamentoMapper;

	@Inject
	TransazioneMdpMapper transazioneMdpMapper;

	@Inject
	@CXFClient ( "IMdpCoreCxf" )
	@SuppressWarnings ( "all" )
	IMdpCoreCxf mdpCoreCxf;

	private static final int MDP_NUMBER_RETRY = 3;

	public Response getPaymentUrl ( String organizationFiscalCode, String iuv ) {
		var methodName = "[getPaymentUrl] ";
		Log.info ( methodName + "BEGIN" );
		Log.info ( methodName + "param iuv:" + iuv );

		var user = authenticationContext.getCurrentUser ();
		Log.info ( methodName + "user:" + user );

		// 1. ottiene il chiamante esterno per il tracciamento della chiamata e la validazione dell'autorizzazione
		var chiamanteEsternoEntity = chiamanteEsternoService.findByCodiceChiamante ( user.getName () );
		if ( null == chiamanteEsternoEntity ) {
			var response = generateForbiddenResponse ( SERVICE_GET_PAYMENT_URL, user.getName () );
			chiamataEsternaNonValidaService.track ( null, user, organizationFiscalCode, iuv, ( (Error) response.getEntity () ).getDetail () );
			return response;
		}
		Log.info ( methodName + "chiamanteEsternoEntity:" + chiamanteEsternoEntity );

		// 2. tracciamento della chiamata
		var track
			= tracciabilitaChiamanteEsternoService.trackExternalCall ( null, organizationFiscalCode, chiamanteEsternoEntity, iuv, user, null );
		Log.info ( methodName + "chiamanteEsterno tracciato, idChiamata:" + track.getIdChiamata () );

		// 3. validazione autorizzazione
		if ( chiamanteAutorizzazioneChiamanteService.countByCodiceChiamanteAndCodiceAutorizzazioneChiamante ( user.getName (),
			Scopes.PAGAMENTO_URL.name () ) < 1 ) {
			var response = generateUnauthorizedResponse ( SERVICE_GET_PAYMENT_URL );
			chiamataEsternaNonValidaService.track ( null, user, organizationFiscalCode, iuv, ( (Error) response.getEntity () ).getDetail () );
			return response;
		}
		Log.info ( methodName + "authorization OK" );

		// validazione input
		var notValids = getNotValidInputs ( organizationFiscalCode, iuv );
		if ( !notValids.isEmpty () ) {
			var response = generateValidationErrorResponse ( SERVICE_GET_PAYMENT_URL, notValids );
			chiamataEsternaNonValidaService.track ( null, user, organizationFiscalCode, iuv, ( (Error) response.getEntity () ).getDetail () );
			return response;
		}
		Log.info ( methodName + "validation OK" );

		// verifica ente
		var ente = enteService.findByCodiceFiscale ( organizationFiscalCode );
		if ( null == ente ) {
			var response = generateNotFoundErrorResponse ( SERVICE_GET_PAYMENT_URL, ERROR_ENTE_NOT_FOUND, organizationFiscalCode );
			chiamataEsternaNonValidaService.track ( null, user, organizationFiscalCode, iuv, ( (Error) response.getEntity () ).getDetail () );
			return response;
		}
		Log.info ( methodName + "fetched ente[id]:" + ente.getIdEnte () );

		// verifica dello IUV e quindi stato pagamento
		var pagamento = pagamentoMapper.toDto ( pagamentoService.getPagamentoActiveAndPayableByIUV ( iuv ) );
		if ( pagamento == null ) {
			var response = generateBusinessErrorResponse ( SERVICE_GET_PAYMENT_URL, ERROR_IUV_NOT_FOUND_OR_INVALID_PAYMENT );
			chiamataEsternaNonValidaService.track ( null, user, organizationFiscalCode, iuv, ( (Error) response.getEntity () ).getDetail () );
			return response;
		}
		if ( pagamento.getRequiresCostUpdate () != null && pagamento.getRequiresCostUpdate () ) {
			var response = generateInternalErrorResponse ( SERVICE_GET_PAYMENT_URL, ERROR_REQUIRES_COST_UPDATE_TRUE );
			chiamataEsternaNonValidaService.track ( null, user, organizationFiscalCode, iuv, ( (Error) response.getEntity () ).getDetail () );
			return response;
		}

		// verifica se l'ente specificato nel tipo pagamento del pagamento corrisponde all'input
		if ( !pagamento.getTipoPagamento ().getEpayTEnti ().getCodiceFiscale ().equalsIgnoreCase ( organizationFiscalCode ) ) {
			var response = generateUnauthorizedResponse ( SERVICE_GET_PAYMENT_URL );
			chiamataEsternaNonValidaService.track ( null, user, organizationFiscalCode, iuv, ( (Error) response.getEntity () ).getDetail () );
			return response;
		}

		// controlla se il pagamento e' stato gia' tentato, se è, non glielo fai pagare
		var statoPagamento = StatoPagamento.findById ( pagamento.getIdStatoCorrente () );
		if ( statoPagamento == ANNULLATO || statoPagamento == TRANSAZIONE_ERRORE ) {
			var response = generateBusinessErrorResponse ( SERVICE_GET_PAYMENT_URL, ERROR_IUV_IS_OF_AN_INVALID_PAYMENT );
			chiamataEsternaNonValidaService.track ( null, user, organizationFiscalCode, iuv, ( (Error) response.getEntity () ).getDetail () );
			return response;
		}

		// inizializza LA transazione con chiamata Soap a MDP
		var transazione = initializzaTransazione ( pagamento, MDP_NUMBER_RETRY );
		transazione.setAmount ( pagamento.getImporto ().doubleValue () );
		Log.info ( methodName + "inizializzata transazione:" + transazione.getTransactionId () );
		//
		var transazioneMdp = new TransazioneMdpDTO ();
		transazioneMdp.setIdTransazione ( transazione.getTransactionId () );
		transazioneMdp.setIuv ( pagamento.getIuv () );
		transazioneMdpService.save ( transazioneMdpMapper.toEntity ( transazioneMdp ) );
		Log.info ( methodName + "inserita traccia transazione:" + transazioneMdp );
		//
		tracciabilitaChiamanteEsternoService.trackExternalCall ( track, organizationFiscalCode, chiamanteEsternoEntity, iuv, user, transazioneMdp );
		Log.info ( methodName + "aggiornamento tracciatura chiamanteEsterno (idChiamata:" + track.getIdChiamata () + ") con la transazioneMdp" );
		//
		registroVersamentiService.tracciaRegistroPagamento ( pagamento, TRANSAZIONE_INIZIALIZZATA, transazioneMdp, CITTA_FACILE );

		var datiRPT = rptService.costruisciRPT ( pagamento, transazione, pagamento.getTipoPagamento ().getFlagMultibeneficiario () );
		Log.info ( methodName + "costruito payload RPT:" + datiRPT );

		String urlPagamento;
		try {
			var isMultibeneficiario = Boolean.TRUE.equals ( pagamento.getTipoPagamento ().getFlagMultibeneficiario () );
			urlPagamento = mdpCoreCxfServiceWrapper.getPaymentURL ( transazione, datiRPT, isMultibeneficiario );
			registroVersamentiService.tracciaRegistroPagamento ( pagamento, TRANSAZIONE_AVVIATA, transazioneMdp, CITTA_FACILE );
			Log.info ( methodName + "URL mdp pagamento:" + urlPagamento );
			cercaEsitoChiamataEsterna ();

		} catch ( MdpException e ) {
			registroVersamentiService.tracciaRegistroPagamento ( pagamento, TRANSAZIONE_ERRORE, transazioneMdp, CITTA_FACILE );
			String message;
			if ( e.getCause () != null && e.getCause ().getMessage () != null && e.getCause ().getMessage ().contains ( RPT_DUPLICATA ) ) {
				message = ERROR_MDP_DATI_COMPONENTI_NON_CONGRUENTI;
				tracciaFallimentoMdp ( organizationFiscalCode, ESITO_CHIAMATA_ESTERNA_ERRORE_DATI_COMPONENTI_NON_CONGRUENTI, iuv, message, e );
			} else {
				message = ERROR_MDP_ERRORE_GENERICO;
				tracciaFallimentoMdp ( organizationFiscalCode, ESITO_CHIAMATA_ESTERNA_ERRORE_GENERICO, iuv, message, e );
			}
			var response = generateInternalErrorResponse ( SERVICE_GET_PAYMENT_URL, ERROR_MDP_ERRORE_GENERICO );
			chiamataEsternaNonValidaService.track ( null, user, organizationFiscalCode, null, ( (Error) response.getEntity () ).getDetail () );
			return response;
		}

		Log.info ( methodName + "END" );

		var paymentIuv = new PaymentIuv ();
		paymentIuv.setPaymentUrl ( urlPagamento );
		return Response.status ( Response.Status.OK ).entity ( paymentIuv ).build ();
	}

	private void tracciaFallimentoMdp ( String organizationFiscalCode, String codiceEsito, String iuv, String dettagli, MdpException mdpException ) {
		if ( dettagli != null ) {
			Log.error ( "Errore transazione: " + dettagli );
		}

		if ( mdpException != null ) {
			Log.error ( ExceptionUtils.getStackTrace ( mdpException ) );
		}

		var esito = esitoChiamataEsternaService.findByCodice ( codiceEsito );

		try {
			tracciaFallimentoSuDB ( organizationFiscalCode, esito, iuv, dettagli );
			Log.warn ( "Tracciata informazione dell'errore sul database" );
		} catch ( Exception e ) {
			Log.error ( "Errore nella chiamata al servizio" );
			Log.error ( ExceptionUtils.getStackTrace ( e ) );
		}
	}

	private void tracciaFallimentoSuDB ( String codiceFiscale, EpayDEsitoChiamataEsterna esito, String iuv, String dettagli ) {

		var entity = new EpayTChiamataEsternaNonValida ();

		var user = authenticationContext.getCurrentUser ();

		entity.setCodiceChiamante ( user.getName () );
		entity.setCodiceFiscale ( codiceFiscale );
		entity.setDescrizioneErrore (
			StringUtils.abbreviate ( StringUtils.isBlank ( dettagli ) ? esito.getDescrizione () : dettagli, 500 ) );
		entity.setIuv ( iuv );
		entity.setRemoteHost ( user.getRemoteAddress () );
		entity.setTimestampChiamata ( new Timestamp ( user.getTimestamp ().getTime () ) );

		try {
			var id = chiamataEsternaNonValidaService.save ( entity );
			entity.setIdChiamata ( id );
			Log.debug ( "inserita voce di tracciatura fallimento chiamata esterna : \n" + entity );
		} catch ( Exception e ) {
			throw new RuntimeException ( "Errore inserimento della tracciabilita' di errore", e );
		}
	}

	private void cercaEsitoChiamataEsterna () {
		var esito = esitoChiamataEsternaService.findByCodice ( ESITO_CHIAMATA_ESTERNA_OPERAZIONE_COMPLETATA_CON_SUCCESSO );
		Log.debug ( "Trovato l'esito di default della chiamata esterna : " + esito.getCodice () );
		if ( null == esito.getCodice () ) {
			throw new RuntimeException ( "Esito chiamata esterna non trovato: " + esito.getCodice () );
		}
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

	private Transazione initializzaTransazione ( final PagamentoDTO pagamento, int numberTry ) throws MdpException {
		try {
			return initializzaTransazione ( pagamento );
		} catch ( Exception e ) {
			if ( --numberTry == 0 ) {
				throw e;
			}
			return initializzaTransazione ( pagamento, numberTry );
		}
	}

	public Transazione initializzaTransazione ( final PagamentoDTO pagamento ) throws MdpException {
		var methodName = "[initializzaTransazione] ";
		Log.info ( methodName + "BEGIN" );
		try {
			var appId = pagamento.getTipoPagamento ().getIdApplicazione ();

			Log.info ( methodName + "--------------------------------------------------------------------------------" );
			Log.info ( methodName + "Call iMdpCoreCxf.initTransazione" );
			Log.info ( methodName + "     Param IdApplicazione        : " + appId );
			var transazione = mdpCoreCxf.initTransazione ( appId, null );
			Log.info ( methodName + "Response iMdpCoreCxf.initTransazione : " );
			Log.info ( methodName + transazione );

			return transazione;

		} catch ( Exception e ) {
			Log.error ( methodName + "response error: " + e.getMessage () );
			throw new MdpException ( "Errore invocazione initTransazione", e );
		} finally {
			Log.info ( "--------------------------------------------------------------------------------" );
			Log.info ( methodName + "END" );
		}
	}
}
