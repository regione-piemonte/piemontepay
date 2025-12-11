package it.csi.epay.epayfeapi.service;

import io.quarkus.logging.Log;
import it.csi.epay.epayfeapi.dto.ModelloUnicoDTO;
import it.csi.epay.epayfeapi.dto.PagamentoDTO;
import it.csi.epay.epayfeapi.dto.TransazioneMdpDTO;
import it.csi.epay.epayfeapi.entity.EpayDChiamanteEsterno;
import it.csi.epay.epayfeapi.entity.EpayTTracciabilitaChiamanteEsterno;
import it.csi.epay.epayfeapi.mapper.TransazioneMdpMapper;
import it.csi.epay.epayfeapi.model.mdppagopacheckout.Cart;
import it.csi.epay.epayfeapi.model.mdppagopacheckout.Transaction;
import it.csi.epay.epayfeapi.rest.client.TransactionAdapterClient;
import it.csi.epay.epayfeapi.security.AuthenticationContext;
import org.openapitools.model.Error;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import static it.csi.epay.epayfeapi.enumeration.OrigineChiamata.CITTA_FACILE;
import static it.csi.epay.epayfeapi.enumeration.StatoPagamento.DA_PAGARE;
import static it.csi.epay.epayfeapi.util.Constants.CF_ENTE_DEFAULT;
import static it.csi.epay.epayfeapi.util.Constants.CONFIG_ENDPOINT_SERVICE_CART;
import static it.csi.epay.epayfeapi.util.Constants.ERROR_VALORE_DI_CONFIGURAZIONE_NOT_DEFINED;
import static it.csi.epay.epayfeapi.util.ResponseUtil.generateBusinessErrorResponse;
import static it.csi.epay.epayfeapi.util.ResponseUtil.generateInternalErrorResponse;


@ApplicationScoped
@Transactional
public class ModelloUnicoHelperService {

	@Inject
	AuthenticationContext authenticationContext;

	@Inject
	EnteService enteService;

	@Inject
	ConfigurazioneService configurazioneService;

	@Inject
	ChiamataEsternaNonValidaService chiamataEsternaNonValidaService;

	@Inject
	PagamentoService pagamentoService;

	@Inject
	TransactionAdapterClient transactionAdapterClient;

	@Inject
	TransazioneMdpService transazioneMdpService;

	@Inject
	TransazioneMdpMapper transazioneMdpMapper;

	@Inject
	TracciabilitaChiamanteEsternoService tracciabilitaChiamanteEsternoService;

	@Inject
	RegistroVersamentiService registroVersamentiService;

	// chiamata al checkout
	public ModelloUnicoDTO manageModelloUnico (
					String cfPIva,
					PagamentoDTO pagamento,
					String iuv,
					EpayDChiamanteEsterno chiamanteEsterno,
					EpayTTracciabilitaChiamanteEsterno track,
					String serviceName,
					String serviceNemeForTrack,
					long initialMoment ) {
		var methodName = "manageModelloUnico";
		var user = authenticationContext.getCurrentUser ();
		var pagoPa = enteService.findByCodiceFiscale ( CF_ENTE_DEFAULT );
		// 1. ottiene la url del servizio da chiamarare
		var config = configurazioneService.findByCodiceAndCodiceAndEnte ( CONFIG_ENDPOINT_SERVICE_CART, pagoPa );
		if ( config == null ) {
			var response = generateBusinessErrorResponse ( serviceName, ERROR_VALORE_DI_CONFIGURAZIONE_NOT_DEFINED, CONFIG_ENDPOINT_SERVICE_CART );
			chiamataEsternaNonValidaService.track ( null, user, cfPIva, null, ( (Error) response.getEntity () ).getDetail (), initialMoment, serviceNemeForTrack );
			return new ModelloUnicoDTO ( response );
		}
		var serviceCartUrl = config.getValore ();
		Log.infof ( "%sserviceCartUrl:%s", methodName, serviceCartUrl );
		//
		// 2. costruisce la richiesta per il servizio
		Cart cart;
		try {
			cart = pagamentoService.buildCart ( pagamento );
		} catch ( IllegalArgumentException e ) {
			var response = generateBusinessErrorResponse ( serviceName, e.getMessage () );
			chiamataEsternaNonValidaService.track ( null, user, cfPIva, iuv, ( (Error) response.getEntity () ).getDetail (), initialMoment, serviceNemeForTrack );
			return new ModelloUnicoDTO ( response );
		}
		//
		// 3. chiama il servizio
		Transaction transaction;
		try {
			transaction = transactionAdapterClient.getTransaction ( cart, serviceCartUrl );
		} catch ( Exception e ) {
			var response = generateInternalErrorResponse ( serviceName, e.getMessage () );
			chiamataEsternaNonValidaService.track ( null, user, cfPIva, null, ( (Error) response.getEntity () ).getDetail (), initialMoment, serviceNemeForTrack );
			return new ModelloUnicoDTO ( response );
		}
		var idTransazione = transaction.getIdTransaction ();
		var paymentUrl = transaction.getPaymentUrl ();
		Log.infof ( "%stransazione avviata:%s", methodName, idTransazione );
		Log.infof ( "%spaymentURL:%s", methodName, paymentUrl );
		//
		// 4. registra la transazione come avviata
		var transazioneMdp = new TransazioneMdpDTO ();
		transazioneMdp.setIdTransazione ( idTransazione );
		transazioneMdp.setIuv ( pagamento.getIuv () );
		transazioneMdpService.save ( transazioneMdpMapper.toEntity ( transazioneMdp ) );
		Log.infof ( "%sInserita traccia transazione:%s", methodName, transazioneMdp );
		//
		tracciabilitaChiamanteEsternoService.trackExternalCall ( track, cfPIva, chiamanteEsterno, iuv, user, idTransazione, initialMoment, serviceNemeForTrack );
		// un record in più!!!
		registroVersamentiService.tracciaRegistroPagamento ( pagamento, DA_PAGARE, transazioneMdp, CITTA_FACILE );
		// tutto ok!
		return new ModelloUnicoDTO ( paymentUrl );
	}
}
