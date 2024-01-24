/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysim.business.epaysim;

import javax.xml.ws.WebServiceContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.xml.transform.StringSource;

import it.csi.epay.epaysim.business.epaysim.manager.FlussiManager;
import it.csi.epay.epaysim.business.epaysim.manager.ProvvisorioManager;
import it.csi.epay.epaysim.business.epaysim.manager.TestResourcesManager;
import it.csi.epay.epaysim.dto.flussi.riconciliazioneversamentipsptypes.ElencoFlussiInErroreType;
import it.csi.epay.epaysim.dto.flussi.riconciliazioneversamentipsptypes.FlussiInErroreType;
import it.csi.epay.epaysim.dto.flussi.riconciliazioneversamentipsptypes.ResponseType;
import it.csi.epay.epaysim.dto.flussi.riconciliazioneversamentipsptypes.ResultType;
import it.csi.epay.epaysim.dto.ws.flussi.riconciliazioneversamentipsptypes.EsitoFlussiPagoPAResponse;
import it.csi.epay.epaysim.dto.ws.flussi.riconciliazioneversamentipsptypes.RicercaProvvisoriPagoPARequest;
import it.csi.epay.epaysim.dto.ws.flussi.riconciliazioneversamentipsptypes.RicercaProvvisoriPagoPAResponse;
import it.csi.epay.epaysim.dto.ws.flussi.riconciliazioneversamentipsptypes.TestataTrasmissioneFlussiType;
import it.csi.epay.epaysim.dto.ws.flussi.riconciliazioneversamentipsptypes.TrasmettiFlussiInErrorePagoPARequest;
import it.csi.epay.epaysim.dto.ws.flussi.riconciliazioneversamentipsptypes.TrasmettiFlussiPagoPARequest;
import it.csi.epay.epaysim.exception.epaysim.EpaysimulatorException;
import it.csi.epay.epaysim.exception.epaysim.UnrecoverableException;
import it.csi.epay.epaysim.interfacews.epaysim.CostantiErrori;
import it.csi.epay.epaysim.util.LogConfig;


@Service
public class EpaysimulatorwsBusinessImpl implements EpaysimulatorwsBusiness {

    private static final Logger LOGGER = Logger.getLogger ( LogConfig.HANDLER_SERVICES );

    public static final String LOGGER_PREFIX = "epaysimulator";

    @Autowired
    private TestResourcesManager testResourcesManager;

    @Autowired
    private ProvvisorioManager epaymodricProvvisorioManager;

    @Autowired
    private FlussiManager flussiManager;

    @Autowired
    private Jaxb2Marshaller jaxb2Marshaller;

    @Override
    public String testResources ( WebServiceContext wsContext, java.lang.String dummy )
                    throws it.csi.epay.epaysim.exception.epaysim.EpaysimulatorException, Exception,
                    UnrecoverableException {
        LOGGER.debug ( "[EpaysimulatorwsBusinessImpl::testResources] - START" );
        String ret = "INPUT='" + dummy + "' - OUTPUT='" + this.getTestResourcesManager ().testResources () + "'";
        LOGGER.debug ( "[EpaysimulatorwsBusinessImpl::testResources] - END" );
        return ret;
    }

    @Override
    public RicercaProvvisoriPagoPAResponse ricercaProvvisoriPagoPA (
        RicercaProvvisoriPagoPARequest ricercaProvvisoriPagoPARequest )
                        throws EpaysimulatorException, Exception, UnrecoverableException {
        LOGGER.debug ( "[EpaysimulatorwsBusinessImpl::ricercaProvvisoriPagoPA] - START" );
        RicercaProvvisoriPagoPAResponse ret = getEpaymodricProvvisorioManager ().ricercaProvvisoriPagoPA ( ricercaProvvisoriPagoPARequest );
        return ret;
    }

    @Override
    public EsitoFlussiPagoPAResponse esitoFlussiPagoPA (
        TestataTrasmissioneFlussiType testataTrasmissioneFlussiType )
                        throws EpaysimulatorException, Exception, UnrecoverableException {
        LOGGER.debug ( "[EpaysimulatorwsBusinessImpl::esitoFlussiPagoPA] - START" );
        EsitoFlussiPagoPAResponse ret = flussiManager.esitoFlussiPagoPA ( testataTrasmissioneFlussiType );
        return ret;
    }

    public TestResourcesManager getTestResourcesManager () {
        return testResourcesManager;
    }

    public void setTestResourcesManager ( TestResourcesManager testResourcesManager ) {
        this.testResourcesManager = testResourcesManager;
    }

    public ProvvisorioManager getEpaymodricProvvisorioManager () {
        return epaymodricProvvisorioManager;
    }

    public void setEpaymodricProvvisorioManager ( ProvvisorioManager epaymodricProvvisorioManager ) {
        this.epaymodricProvvisorioManager = epaymodricProvvisorioManager;
    }

    @Override
    public ResponseType flussoInErrore ( TrasmettiFlussiInErrorePagoPARequest trasmettiFlussiInErrorePagoPARequest )
                    throws EpaysimulatorException, UnrecoverableException, Exception {
        ResponseType responseType = new ResponseType ();
        responseType.setResult ( new ResultType () );
        Assert.notNull ( trasmettiFlussiInErrorePagoPARequest.getFlussiInErrore (), "Oggetto elencoFlussiInErroreType non valorizzato!" );
        Assert.notNull ( trasmettiFlussiInErrorePagoPARequest.getTestataFlussiInErrore ().getCFEnteCreditore (), "CF Ente Creditore non valorizzato!" );

        ElencoFlussiInErroreType elencoFlussiInErroreType = unmarshal ( trasmettiFlussiInErrorePagoPARequest );
        Assert.notNull ( elencoFlussiInErroreType, "Oggetto elencoFlussiInErroreType non valorizzato!" );
        Assert.notNull ( elencoFlussiInErroreType.getRigheFlussi (), "Oggetto getRigheFlussi non valorizzato!" );
        Assert.notNull ( elencoFlussiInErroreType.getRigheFlussi ().getFlussoErrato (), "Oggetto getRigheFlussi non valorizzato!" );
        Assert.notEmpty ( elencoFlussiInErroreType.getRigheFlussi ().getFlussoErrato (), "Oggetto getRigheFlussi vuoto!" );

        int errore = 0;
        for ( FlussiInErroreType flussiInErroreType: elencoFlussiInErroreType.getRigheFlussi ().getFlussoErrato () ) {
            try {
                flussiManager.salvaFlussoInErrore ( trasmettiFlussiInErrorePagoPARequest, flussiInErroreType );
            } catch ( Exception e ) {
                errore++;
                LOGGER.error ( String.format ( "Errore in fase di salvataggio del flusso con idMessaggio: %s e identificativoFlusso: %s",
                    flussiInErroreType.getIdentificativoFlusso () ), e );
            }
        }

        if ( errore > 0 ) {
            responseType.getResult ().setCodice ( CostantiErrori.ELABORAZIONE_TERMINATA_CON_ERRORI );
            responseType.getResult ().setMessaggio ( "Elaborazione terminata con errore/i N: " + errore );
        } else {
            responseType.getResult ().setCodice ( CostantiErrori.WS_ESITO_OK_DEFAULT );
            responseType.getResult ().setMessaggio ( "Elaborazione terminata con successo" );
        }
        return responseType;
    }

    @Override
    public ResponseType flussoRiconciliazione ( TrasmettiFlussiPagoPARequest trasmettiFlussiPagoPARequest )
                    throws EpaysimulatorException, UnrecoverableException, Exception {
        Assert.notNull ( trasmettiFlussiPagoPARequest, "Oggetto trasmettiFlussiPagoPARequest non valorizzato!" );
        Assert.notNull ( trasmettiFlussiPagoPARequest, "Oggetto trasmettiFlussiPagoPARequest non valorizzato!" );
        Assert.notNull ( trasmettiFlussiPagoPARequest.getTestataTrasmissioneFlussi (), "Oggetto TestataTrasmissioneFlussi non valorizzato!" );

        Assert.notNull ( trasmettiFlussiPagoPARequest.getTestataTrasmissioneFlussi ().getCFEnteCreditore (), "CF Ente Creditore non valorizzato!" );
        ResponseType responseType = new ResponseType ();
        responseType.setResult ( new ResultType () );
        responseType.getResult ().setCodice ( CostantiErrori.ERRORE_DI_SISTEMA );
        responseType.getResult ().setMessaggio ( "Errore di sistema" );
        int errore = flussiManager.salvaFlussoRiconciliazione ( trasmettiFlussiPagoPARequest );
        //gestione ritorno errori
        if ( errore > 0 ) {
            responseType.getResult ().setCodice ( CostantiErrori.ELABORAZIONE_TERMINATA_CON_ERRORI );
            responseType.getResult ().setMessaggio ( "Elaborazione terminata con errore/i N: " + errore );
        } else {
            responseType.getResult ().setCodice ( CostantiErrori.WS_ESITO_OK_DEFAULT );
            responseType.getResult ().setMessaggio ( "Elaborazione terminata con successo" );
        }
        return responseType;
    }

    private ElencoFlussiInErroreType unmarshal ( TrasmettiFlussiInErrorePagoPARequest trasmettiFlussiInErrorePagoPARequest ) throws EpaysimulatorException {

        byte [] base64Bytes = trasmettiFlussiInErrorePagoPARequest.getFlussiInErrore ();
        ElencoFlussiInErroreType elencoFlussiInErroreType = new ElencoFlussiInErroreType ();
        StringSource stringSource = new StringSource ( new String ( base64Bytes ) );

        elencoFlussiInErroreType = (ElencoFlussiInErroreType) jaxb2Marshaller.unmarshal ( stringSource );

        return elencoFlussiInErroreType;
    }

}
