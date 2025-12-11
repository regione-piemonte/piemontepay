/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.business.impl.v1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.csi.epay.epayapi.business.v1.EpayServicesClientService;
import it.csi.epay.epayservices.interfaces.ejb.v1.ChiamataEsternaSincronaFacade;
import it.csi.epay.epayservices.interfaces.ejb.v1.ChiamataEsternaSincronaSplitFacade;
import it.csi.epay.epayservices.interfaces.ejb.v1.PagamentoMarcaDaBolloFacade;
import it.csi.epay.epayservices.interfaces.exception.NoDataException;
import it.csi.epay.epayservices.model.DatiAvvisoPagamento;
import it.csi.epay.epayservices.model.GetIuvChiamanteEsternoInput;
import it.csi.epay.epayservices.model.GetIuvChiamanteEsternoOutput;
import it.csi.epay.epayservices.model.v1.GetIuvMultibeneficiarioChiamanteEsternoInput;
import it.csi.epay.epayservices.model.GetIuvMultibeneficiarioChiamanteEsternoOutput;
import it.csi.epay.epayservices.model.GetRTChiamanteEsternoInput;
import it.csi.epay.epayservices.model.GetRTChiamanteEsternoOutput;
import it.csi.epay.epayservices.model.Rt;
import it.csi.epay.epayservices.model.v1.AccessoChiamanteEsternoSincronoInput;
import it.csi.epay.epayservices.model.v1.AccessoChiamanteEsternoSincronoOutput;
import it.csi.epay.epayservices.model.v1.AggiornaPosizioneDebitoriaChiamanteEsternoInput;
import it.csi.epay.epayservices.model.v1.AggiornaPosizioneDebitoriaChiamanteEsternoMultibeneficiarioInput;
import it.csi.epay.epayservices.model.v1.AggiornaPosizioneDebitoriaChiamanteEsternoMultibeneficiarioOutput;
import it.csi.epay.epayservices.model.v1.AggiornaPosizioneDebitoriaChiamanteEsternoOutput;
import it.csi.epay.epayservices.model.v1.EliminaPosizioneDebitoriaChiamanteEsternoInput;
import it.csi.epay.epayservices.model.v1.EliminaPosizioneDebitoriaChiamanteEsternoOutput;
import it.csi.epay.epayservices.model.v1.GetDatiPagamentoChiamanteEsternoInput;
import it.csi.epay.epayservices.model.v1.GetDatiPagamentoChiamanteEsternoOutput;
import it.csi.epay.epayservices.model.v1.GetStatoPosizioneDebitoriaInput;
import it.csi.epay.epayservices.model.v1.GetStatoPosizioneDebitoriaOutput;
import it.csi.epay.epayservices.model.v1.PagamentoIuvChiamanteEsternoInput;
import it.csi.epay.epayservices.model.v1.PagamentoIuvChiamanteEsternoOutput;
import it.csi.epay.epayservices.model.v1.PagamentoMarcaBolloChiamanteEsternoOutput;
import it.csi.epay.epayservices.model.v1.PagamentoMarcaDaBolloChiamanteEsternoInput;
import it.csi.epay.epayservices.model.v1.PagamentoPerStampaAvvisoInput;
import it.csi.epay.epayservices.model.v1.PagamentoPerStampaAvvisoOutput;
import it.csi.epay.epayservices.model.v1.StampTaxAttachmentChiamanteEsternoInput;
import it.csi.epay.epayservices.model.v1.StampTaxAttachmentChiamanteEsternoOutput;


/**
 * Implementazione del servizio client per epayservices
 */
@Service ("EpayServicesClientServiceV1")
@Transactional
public class EpayServicesClientServiceImpl implements EpayServicesClientService {

	private static final Logger logger = LogManager.getLogger ( EpayServicesClientServiceImpl.class );

    @Autowired
    private ChiamataEsternaSincronaFacade chiamataEsternaSincrona;

    @Autowired
    private ChiamataEsternaSincronaSplitFacade chiamataEsternaSincronaSplitV1;

    @Autowired
    private it.csi.epay.epayservices.interfaces.ejb.ChiamataEsternaSincronaSplitFacade chiamataEsternaSincronaSplit;

    @Autowired
    private PagamentoMarcaDaBolloFacade pagamentoMarcaDaBollo;

    @Override
    public AccessoChiamanteEsternoSincronoOutput accessoChiamanteEsternoSincrono ( String organization, String paymentType, AccessoChiamanteEsternoSincronoInput input ) {
        logger.debug ( "accessoChiamanteEsternoSincrono" );
		input.setCodiceFiscaleEnte ( organization );
		input.setTipoPagamento ( paymentType );
        return chiamataEsternaSincrona.accessoChiamanteEsternoSincrono  ( input );
    }

    @Override
    public GetIuvChiamanteEsternoOutput getIUVChiamanteEsterno ( String organization, String paymentType, GetIuvChiamanteEsternoInput input ) {
        logger.debug ( "getIUVChiamanteEsterno" );
		input.setCodiceFiscaleEnte ( organization );
		input.setTipoPagamento ( paymentType );
        return chiamataEsternaSincronaSplit.getIUVChiamanteEsterno ( input );
    }

    @Override
    public GetIuvMultibeneficiarioChiamanteEsternoOutput getIUVMultibeneficiarioChiamanteEsterno ( String organization, String paymentType, GetIuvMultibeneficiarioChiamanteEsternoInput input ) {
        logger.debug ( "getIUVMultibeneficiarioChiamanteEsterno" );
		input.setCodiceFiscaleEnte ( organization );
		input.setTipoPagamento ( paymentType );
        return chiamataEsternaSincronaSplit.getIUVMultibeneficiarioChiamanteEsternov1  ( input );
    }

    @Override
    public PagamentoIuvChiamanteEsternoOutput getPagamentoIUVChiamanteEsterno ( PagamentoIuvChiamanteEsternoInput input ) {
        logger.debug ( "pagamentoIUVChiamanteEsterno" );
        return chiamataEsternaSincronaSplitV1.getPagamentoIUVChiamanteEsterno ( input );
    }

    @Override
    public GetRTChiamanteEsternoOutput getRTChiamanteEsterno ( GetRTChiamanteEsternoInput input ) {
        logger.debug ( "getRtChiamanteEsterno" );
        return chiamataEsternaSincronaSplit.getRTChiamanteEsterno ( input );

    }

    @Override
    public void test () {
        //per unit test
    }

	@Override public PagamentoPerStampaAvvisoOutput ricercaPagamentoByIUV ( PagamentoPerStampaAvvisoInput input ) {
		logger.debug ( "ricercaPagamentoByIUV" );
		return chiamataEsternaSincronaSplitV1.ricercaPagamentoByIUV ( input );
	}

	@Override public Rt ricercaRtByIuv ( String iuv ) throws NoDataException {
		logger.debug ( "ricercaRtByIuv" );
		return chiamataEsternaSincronaSplitV1.ricercaRtByIuv ( iuv );
	}

	@Override public DatiAvvisoPagamento ricercaDatiAvvisoPagamentoByIdTipoPagamento ( Long idTipoPagamento ) throws NoDataException {
		logger.debug ( "ricercaDatiAvvisoPagamentoByIdTipoPagamento" );
		return chiamataEsternaSincronaSplitV1.ricercaDatiAvvisoPagamentoByIdTipoPagamento ( idTipoPagamento );
	}

	/**
     * Effettua il pagamento di una marca da bollo con o senza istanza collegata
     *
     * @param input i dati di identificazione del pagamento e marca da bollo
     * @return l'url presso il quale effettuare il pagamento
     */
    @Override
    public PagamentoMarcaBolloChiamanteEsternoOutput getPagamentoMarcaBolloChiamanteEsterno ( PagamentoMarcaDaBolloChiamanteEsternoInput input ) {
        logger.debug ( "pagamentoMarcaBolloChiamanteEsterno" );
        return pagamentoMarcaDaBollo.pagamentoMarcaDaBollo ( input );

    }

    @Override
    public AggiornaPosizioneDebitoriaChiamanteEsternoOutput
        aggiornaPosizioneChiamanteEsterno ( AggiornaPosizioneDebitoriaChiamanteEsternoInput input ) {
        logger.debug ( "aggiornaPosizioneChiamanteEsterno" );
        return chiamataEsternaSincronaSplitV1.aggiornaPosizioneDebitoriaChiamanteEsterno ( input );
    }

    @Override
    public AggiornaPosizioneDebitoriaChiamanteEsternoMultibeneficiarioOutput
        aggiornaPosizioneMultibeneficiarioChiamanteEsterno ( AggiornaPosizioneDebitoriaChiamanteEsternoMultibeneficiarioInput input ) {
        logger.debug ( "aggiornaPosizioneChiamanteEsternoMultibeneficiario" );
        return chiamataEsternaSincronaSplitV1.aggiornaPosizioneDebitoriaMultibeneficiarioChiamanteEsterno ( input );
    }

    @Override
    public EliminaPosizioneDebitoriaChiamanteEsternoOutput eliminaPosizioneChiamanteEsterno ( EliminaPosizioneDebitoriaChiamanteEsternoInput input ) {
        logger.debug ( "eliminaPosizioneChiamanteEsterno" );
        return chiamataEsternaSincronaSplitV1.eliminaPosizioneDebitoriaChiamanteEsterno ( input );
    }

	@Override public GetStatoPosizioneDebitoriaOutput getStatoPosizioneDebitoriaChiamanteEsterno ( GetStatoPosizioneDebitoriaInput input ) {
		logger.debug ( "getStatoPosizioneDebitoriaChiamanteEsterno" );
		return chiamataEsternaSincronaSplitV1.getStatoPosizioneDebitoria ( input );
	}

	@Override public GetDatiPagamentoChiamanteEsternoOutput getDatiPagamentoChiamanteEsterno ( GetDatiPagamentoChiamanteEsternoInput input ) {
		logger.debug ( "getDatiPagamentoChiamanteEsterno" );
		return chiamataEsternaSincronaSplitV1.getDatiPagamentoChiamanteEsterno ( input );
	}
	
	@Override public StampTaxAttachmentChiamanteEsternoOutput getStampTaxAttachment ( StampTaxAttachmentChiamanteEsternoInput input ) 
	{
	    logger.debug ( "getStampTaxAttachment" );
	    return pagamentoMarcaDaBollo.getStampTaxAttachment( input );
	}

}
