/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysim.business.epaysim;

import javax.annotation.PostConstruct;
import javax.jws.WebService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.csi.epay.epaysim.dto.flussi.riconciliazioneversamentipsptypes.ResponseType;
import it.csi.epay.epaysim.dto.flussi.riconciliazioneversamentipsptypes.ResultType;
import it.csi.epay.epaysim.dto.ws.flussi.riconciliazioneversamentipsptypes.EsitoFlussiPagoPAResponse;
import it.csi.epay.epaysim.dto.ws.flussi.riconciliazioneversamentipsptypes.RicercaProvvisoriPagoPARequest;
import it.csi.epay.epaysim.dto.ws.flussi.riconciliazioneversamentipsptypes.RicercaProvvisoriPagoPAResponse;
import it.csi.epay.epaysim.dto.ws.flussi.riconciliazioneversamentipsptypes.RicercaProvvisoriPagoPAResponse.ElencoProvvisori;
import it.csi.epay.epaysim.dto.ws.flussi.riconciliazioneversamentipsptypes.TestataTrasmissioneFlussiType;
import it.csi.epay.epaysim.dto.ws.flussi.riconciliazioneversamentipsptypes.TrasmettiFlussiInErrorePagoPARequest;
import it.csi.epay.epaysim.dto.ws.flussi.riconciliazioneversamentipsptypes.TrasmettiFlussiPagoPARequest;
import it.csi.epay.epaysim.exception.epaysim.UserException;
import it.csi.epay.epaysim.interfacews.epaysim.CostantiErrori;
import it.csi.epay.epaysim.interfacews.epaysim.EPayRiconciliazioneVersamentiPSP;
import it.csi.epay.epaysim.util.LogConfig;


/**
 *
 *
 */
@WebService ( serviceName = "EPayRiconciliazioneVersamentiPSP", portName = "EPayRiconciliazioneVersamentiPSPSOAP",
targetNamespace = "http://www.csi.it/epay/epaywso/riconciliazione-versamenti-psp",
endpointInterface = "it.csi.epay.epaysim.interfacews.epaysim.EPayRiconciliazioneVersamentiPSP" )
public class EPayRiconciliazioneVersamentiPSPImpl implements EPayRiconciliazioneVersamentiPSP {

    private static final Logger LOGGER = Logger.getLogger ( LogConfig.HANDLER_SERVICES );

    @Autowired
    private EpaysimulatorwsBusiness epaysimulatorBusiness;

    @PostConstruct
    public void postConstruct () {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext ( this );
    }

    @Override
    public ResponseType trasmettiFlussiPagoPA ( TrasmettiFlussiPagoPARequest trasmettiFlussiPagoPARequest ) {
        try {
            return epaysimulatorBusiness.flussoRiconciliazione ( trasmettiFlussiPagoPARequest );
        } catch ( Exception e ) {
            LOGGER.error ( "Errore in fase di elaborazione del flusso di riconciliazione.", e );
            return creaOggettoDiRitornoDaEccezione ( e );
        }
    }

    @Override
    public RicercaProvvisoriPagoPAResponse ricercaProvvisoriPagoPA ( RicercaProvvisoriPagoPARequest ricercaProvvisoriPagoPARequest ) {
        try {
            return epaysimulatorBusiness.ricercaProvvisoriPagoPA ( ricercaProvvisoriPagoPARequest );
        } catch ( Exception e ) {
            LOGGER.error ( "Errore in fase di ricercaProvvisoriPagoPA." );
            ResponseType ret = creaOggettoDiRitornoDaEccezione ( e );
            RicercaProvvisoriPagoPAResponse response = new RicercaProvvisoriPagoPAResponse ();
            response.setResult ( ret.getResult () );
            response.setElencoProvvisori ( new ElencoProvvisori () );
            return response;
        }
    }

    @Override
    public EsitoFlussiPagoPAResponse esitoFlussiPagoPA ( TestataTrasmissioneFlussiType testataTrasmissioneFlussiType ) {
        try {
            return epaysimulatorBusiness.esitoFlussiPagoPA ( testataTrasmissioneFlussiType );
        } catch ( Exception e ) {
            LOGGER.error ( "Errore in fase di esitoFlussiPagoPA." );
            ResponseType ret = creaOggettoDiRitornoDaEccezione ( e );
            EsitoFlussiPagoPAResponse response = new EsitoFlussiPagoPAResponse ();
            response.setResult ( ret.getResult () );
            return response;
        }
    }

    @Override
    public ResponseType trasmettiFlussiInErrorePagoPA ( TrasmettiFlussiInErrorePagoPARequest trasmettiFlussiInErrorePagoPARequest ) {
        try {
            return epaysimulatorBusiness.flussoInErrore ( trasmettiFlussiInErrorePagoPARequest );
        } catch ( Exception e ) {
            LOGGER.error ( "Errore in fase di elaborazione del flusso in errore." );
            return creaOggettoDiRitornoDaEccezione ( e );
        }
    }

    private ResponseType creaOggettoDiRitornoDaEccezione ( Exception e ) {
        ResponseType responseType = new ResponseType ();
        ResultType resultType = new ResultType ();
        responseType.setResult ( resultType );
        if ( e instanceof UserException ) {
            resultType.setCodice ( ( (UserException) e ).getCodiceErrore () );
            resultType.setMessaggio ( e.getMessage () );
        } else {
            resultType.setCodice ( CostantiErrori.ERRORE_DI_SISTEMA );
            resultType.setMessaggio ( "Si e' verificato un errore di sistema" );
        }
        return responseType;
    }
}
