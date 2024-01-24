/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.batch;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.csi.epay.epaymodric.business.epaymodric.EpaymodricBusiness;
import it.csi.epay.epaymodric.business.epaymodric.security.RequestContextUtils;
import it.csi.epay.epaymodric.business.epaymodric.security.RequestParserHelper;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputVuoto;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsAcquisizioneFlussoRendicontazione;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsAggiornaStatoFlusso;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsAttivaElaborazioneConStatiDaEscludere;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsEseguiElaborazione;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsSpacchettamentoFlussoRendicontazione;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsTrasmettiFlussiInErrorePagoPA;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsTrasmettiFlussiPagoPA;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsCambioStatoFlusso;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsElaborazione;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsMotoreDiRiconciliazione;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsTrasmettiFlussiInErrorePagoPA;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsTrasmettiFlussiPagoPA;
import it.csi.epay.epaymodric.interfacews.epaymodric.batch.EpaymodricJob;
import it.csi.epay.epaymodric.interfacews.epaymodric.exception.EpaymodricException;
import it.csi.epay.epaymodric.interfacews.epaymodric.exception.UnrecoverableException;


@WebService ( serviceName = "BatchService", portName = "BatchServicePort",
                endpointInterface = "it.csi.epay.epaymodric.interfacews.epaymodric.batch.EpaymodricJob" )
public class EpaymodricJobImpl implements EpaymodricJob {

    @Resource
    WebServiceContext wsContext;

    @Autowired
    private EpaymodricBusiness epaymodricBusiness;

    public EpaymodricBusiness getEpaymodricBusiness () {
        return epaymodricBusiness;
    }

    public void setEpaymodricBusiness ( EpaymodricBusiness epaymodricBusiness ) {
        this.epaymodricBusiness = epaymodricBusiness;
    }

    @PostConstruct
    public void postConstruct () {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext ( this );
    }

    //    @Override
    public String testResources ( java.lang.String dummy )
                    throws it.csi.epay.epaymodric.interfacews.epaymodric.exception.EpaymodricException, Exception, UnrecoverableException {

        RequestContextUtils.getRequestContext ()
            .setInputData ( dummy )
            .setCaller ( RequestParserHelper.getCaller ( null ) )
            .setOutputDataType ( String.class );

        return getEpaymodricBusiness ().testResources ( wsContext, dummy );
    }

    //    @Override
    public DTOOutputWsElaborazione spacchettaFlussiDaSpacchettareByEnte ( DTOInputWsAcquisizioneFlussoRendicontazione input )
                    throws EpaymodricException, Exception, UnrecoverableException {

        RequestContextUtils.getRequestContext ()
            .setInputData ( input )
            .setCaller ( RequestParserHelper.getCaller ( input ) )
            .setOutputDataType ( DTOOutputWsElaborazione.class );

        return getEpaymodricBusiness ().spacchettaFlussiRendicontazioneDaSpacchettareByEnte ( input );
    }

    //    @Override
    public DTOOutputWsElaborazione spacchettaFlussoDaSpacchettareByEnteByIdentificativoFlusso ( DTOInputWsSpacchettamentoFlussoRendicontazione input )
                    throws EpaymodricException, Exception, UnrecoverableException {

        RequestContextUtils.getRequestContext ()
            .setInputData ( input )
            .setCaller ( RequestParserHelper.getCaller ( input ) )
            .setOutputDataType ( DTOOutputWsElaborazione.class );

        return getEpaymodricBusiness ().spacchettaFlussoRendicontazioneDaSpacchettareByEnteByIdentificativoFlusso ( input );
    }

    @Override
    public DTOOutputWsMotoreDiRiconciliazione eseguiElaborazione ( DTOInputWsEseguiElaborazione input )
                    throws EpaymodricException, Exception, UnrecoverableException {

        RequestContextUtils.getRequestContext ()
            .setInputData ( input )
            .setCaller ( RequestParserHelper.getCaller ( input ) )
            .setOutputDataType ( DTOOutputWsMotoreDiRiconciliazione.class );

        return getEpaymodricBusiness ().elaborazioneEsegui ( input.getIdElaborazione () );
    }

    @Override
    public DTOOutputWsMotoreDiRiconciliazione attivaElaborazione ( DTOInputVuoto input )
                    throws EpaymodricException, Exception, UnrecoverableException {

        RequestContextUtils.getRequestContext ()
            .setInputData ( input )
            .setCaller ( RequestParserHelper.getCaller ( input ) )
            .setOutputDataType ( DTOOutputWsMotoreDiRiconciliazione.class );

        return getEpaymodricBusiness ().attivaElaborazione ( null );
    }

    @Override
    public DTOOutputWsMotoreDiRiconciliazione attivaElaborazioneConStatiDaEscludere ( DTOInputWsAttivaElaborazioneConStatiDaEscludere input )
                    throws EpaymodricException, Exception, UnrecoverableException {

        RequestContextUtils.getRequestContext ()
            .setInputData ( input )
            .setCaller ( RequestParserHelper.getCaller ( input ) )
            .setOutputDataType ( DTOOutputWsMotoreDiRiconciliazione.class );

        return getEpaymodricBusiness ().attivaElaborazione ( input.getStatiDaEscludere () );
    }

    //Nuru 2.2.10
    //    @Override
    public DTOOutputWsCambioStatoFlusso aggiornaStatoFlusso ( DTOInputWsAggiornaStatoFlusso input )
                    throws EpaymodricException, Exception, UnrecoverableException {

        RequestContextUtils.getRequestContext ()
            .setInputData ( input )
            .setCaller ( RequestParserHelper.getCaller ( input ) )
            .setOutputDataType ( DTOOutputWsCambioStatoFlusso.class );

        return getEpaymodricBusiness ().aggiornaStatoFlusso ( input.getIdentificativoFlusso (), input.getIdEnte (), input.getNuovoStatoFlusso () );
    }

    public DTOOutputWsTrasmettiFlussiPagoPA testTrasmettiFlussiPagoPA ( DTOInputWsTrasmettiFlussiPagoPA input )
                    throws EpaymodricException, Exception, UnrecoverableException {

        RequestContextUtils.getRequestContext ()
            .setInputData ( input )
            .setCaller ( RequestParserHelper.getCaller ( input ) )
            .setOutputDataType ( DTOOutputWsTrasmettiFlussiPagoPA.class );

        return getEpaymodricBusiness ().trasmettiFlussiPagoPA ( input );
    }

    public DTOOutputWsTrasmettiFlussiInErrorePagoPA testTrasmettiFlussiInErrorePagoPA (
        DTOInputWsTrasmettiFlussiInErrorePagoPA input )
                    throws EpaymodricException, Exception, UnrecoverableException {

        RequestContextUtils.getRequestContext ()
            .setInputData ( input )
            .setCaller ( RequestParserHelper.getCaller ( input ) )
            .setOutputDataType ( DTOOutputWsTrasmettiFlussiInErrorePagoPA.class );

        return getEpaymodricBusiness ().trasmettiFlussiInErrorePagoPA ( input );
    }

}
