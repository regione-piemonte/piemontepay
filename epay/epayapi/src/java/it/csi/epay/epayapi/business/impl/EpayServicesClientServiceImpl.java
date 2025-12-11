/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.business.impl;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.csi.epay.epayapi.business.EpayServicesClientService;
import it.csi.epay.epayservices.interfaces.ejb.ChiamataEsternaSincronaFacade;
import it.csi.epay.epayservices.interfaces.ejb.ChiamataEsternaSincronaSplitFacade;
import it.csi.epay.epayservices.interfaces.ejb.PagamentoMarcaDaBolloFacade;
import it.csi.epay.epayservices.model.AccessoChiamanteEsternoSincronoInput;
import it.csi.epay.epayservices.model.AccessoChiamanteEsternoSincronoOutput;
import it.csi.epay.epayservices.model.AggiornaPosizioneDebitoriaChiamanteEsternoInput;
import it.csi.epay.epayservices.model.AggiornaPosizioneDebitoriaChiamanteEsternoOutput;
import it.csi.epay.epayservices.model.AggiornaPosizioneDebitoriaMultibeneficiarioChiamanteEsternoInput;
import it.csi.epay.epayservices.model.AggiornaPosizioneDebitoriaMultibeneficiarioChiamanteEsternoOutput;
import it.csi.epay.epayservices.model.GetIuvChiamanteEsternoInput;
import it.csi.epay.epayservices.model.GetIuvChiamanteEsternoInputContainer;
import it.csi.epay.epayservices.model.GetIuvChiamanteEsternoOutput;
import it.csi.epay.epayservices.model.GetIuvChiamanteEsternoOutputContainer;
import it.csi.epay.epayservices.model.GetIuvMultibeneficiarioChiamanteEsternoInput;
import it.csi.epay.epayservices.model.GetIuvMultibeneficiarioChiamanteEsternoInputContainer;
import it.csi.epay.epayservices.model.GetIuvMultibeneficiarioChiamanteEsternoOutput;
import it.csi.epay.epayservices.model.GetIuvMultibeneficiarioChiamanteEsternoOutputContainer;
import it.csi.epay.epayservices.model.GetRTChiamanteEsternoInput;
import it.csi.epay.epayservices.model.GetRTChiamanteEsternoOutput;
import it.csi.epay.epayservices.model.PagamentoIuvChiamanteEsternoInput;
import it.csi.epay.epayservices.model.PagamentoIuvChiamanteEsternoOutput;
import it.csi.epay.epayservices.model.PagamentoMarcaBolloChiamanteEsternoOutput;
import it.csi.epay.epayservices.model.PagamentoMarcaDaBolloChiamanteEsternoInput;


/**
 * Implementazione del servizio client per epayservices
 */
@Service
@Transactional
public class EpayServicesClientServiceImpl implements EpayServicesClientService {

	private static final Logger logger = LogManager.getLogger ( EpayServicesClientService.class );

    @Autowired
    private ChiamataEsternaSincronaFacade chiamataEsternaSincrona;

    @Autowired
    private ChiamataEsternaSincronaSplitFacade chiamataEsternaSincronaSplit;

    @Autowired
    private PagamentoMarcaDaBolloFacade pagamentoMarcaDaBollo;

    @Override
    public AccessoChiamanteEsternoSincronoOutput accessoChiamanteEsternoSincrono ( AccessoChiamanteEsternoSincronoInput input ) {
        logger.debug ( "accessoChiamanteEsternoSincrono" );
        return chiamataEsternaSincrona.accessoChiamanteEsternoSincrono ( input );
    }

    @Override
    public GetIuvChiamanteEsternoOutput getIUVChiamanteEsterno ( GetIuvChiamanteEsternoInput input ) {
        logger.debug ( "getIUVChiamanteEsterno" );
        return chiamataEsternaSincronaSplit.getIUVChiamanteEsterno ( input );
    }

    @Override
    public GetIuvMultibeneficiarioChiamanteEsternoOutput getIUVMultibeneficiarioChiamanteEsterno ( GetIuvMultibeneficiarioChiamanteEsternoInput input ) {
        logger.debug ( "getIUVMultibeneficiarioChiamanteEsterno" );
        return chiamataEsternaSincronaSplit.getIUVMultibeneficiarioChiamanteEsterno ( input );
    }

    @Override
    public PagamentoIuvChiamanteEsternoOutput getPagamentoIUVChiamanteEsterno ( PagamentoIuvChiamanteEsternoInput input ) {
        logger.debug ( "pagamentoIUVChiamanteEsterno" );
        return chiamataEsternaSincronaSplit.getPagamentoIUVChiamanteEsterno ( input );
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
	public GetIuvChiamanteEsternoOutputContainer getListIUVChiamanteEsterno ( GetIuvChiamanteEsternoInputContainer input ) {
        logger.debug ( "getListIUVChiamanteEsterno" );
        return chiamataEsternaSincronaSplit.getListIUVChiamanteEsterno ( input );
    }

    @Override
	public GetIuvMultibeneficiarioChiamanteEsternoOutputContainer
        getListIUVMultibeneficiarioChiamanteEsterno ( GetIuvMultibeneficiarioChiamanteEsternoInputContainer input ) {
        logger.debug ( "getListIUVMultibeneficiarioChiamanteEsterno" );
        return chiamataEsternaSincronaSplit.getListIUVMultibeneficiarioChiamanteEsterno ( input );
    }

    @Override
    public AggiornaPosizioneDebitoriaChiamanteEsternoOutput
        aggiornaPosizioneChiamanteEsterno ( AggiornaPosizioneDebitoriaChiamanteEsternoInput input ) {
        logger.debug ( "aggiornaPosizioneChiamanteEsterno" );
        return chiamataEsternaSincronaSplit.aggiornaPosizioneDebitoriaChiamanteEsterno ( input );
    }

    @Override
    public AggiornaPosizioneDebitoriaMultibeneficiarioChiamanteEsternoOutput
        aggiornaPosizioneChiamanteEsternoMultibeneficiario ( AggiornaPosizioneDebitoriaMultibeneficiarioChiamanteEsternoInput input ) {
        logger.debug ( "aggiornaPosizioneChiamanteEsternoMultibeneficiario" );
        return chiamataEsternaSincronaSplit.aggiornaPosizioneDebitoriaChiamanteEsternoMultibeneficiario ( input );
    }


}
