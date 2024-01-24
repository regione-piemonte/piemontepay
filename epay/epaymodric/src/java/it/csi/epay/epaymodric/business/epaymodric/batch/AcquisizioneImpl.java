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
import it.csi.epay.epaymodric.interfacews.epaymodric.batch.Acquisizione;
import it.csi.epay.epaymodric.interfacews.epaymodric.enums.TipoAcquisizione;
import it.csi.epay.epaymodric.interfacews.epaymodric.exception.EpaymodricException;
import it.csi.epay.epaymodric.interfacews.epaymodric.exception.UnrecoverableException;
import it.csi.epay.epaymodric.util.wsdl.flussorendicontazione.ResponseType;
import it.csi.epay.epaymodric.util.wsdl.flussorendicontazione.TrasmettiFlussoRendicontazioneExtRequestType;
import it.csi.epay.epaymodric.util.wsdl.flussorendicontazione.TrasmettiRTExtRequestType;


@WebService ( serviceName = "AcquisizioneService", portName = "EpaymodricPort",
endpointInterface = "it.csi.epay.epaymodric.interfacews.epaymodric.batch.Acquisizione" )
public class AcquisizioneImpl implements Acquisizione {

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

    @Override
    public ResponseType trasmettiFlussoRendicontazioneExt ( TrasmettiFlussoRendicontazioneExtRequestType input ) {

        ResponseType response = new ResponseType ();
        // TODO questo servizio accetta input non autenticato ??
        //effettuare modifica anche qua.

        RequestContextUtils.getRequestContext ()
        .setInputData ( input )
        .setCaller ( RequestParserHelper.getCaller ( null ) )
        .setOutputDataType ( ResponseType.class );
        try {
            response = getEpaymodricBusiness ().acquisciFlussoRendicontazione ( input, TipoAcquisizione.FLUSSO );
        } catch ( EpaymodricException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace ();
        } catch ( UnrecoverableException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace ();
        } catch ( Exception e ) {
            // TODO Auto-generated catch block
            e.printStackTrace ();
        }

        return response;
    }

    @Override
    public ResponseType trasmettiRT ( TrasmettiRTExtRequestType inputRT ) {

        ResponseType response = new ResponseType ();

        //creare una colonna in piu' sulla tabella del flusso origine.
        //che dica se sto spacchettando un flusso o un RT
        TrasmettiFlussoRendicontazioneExtRequestType input = new TrasmettiFlussoRendicontazioneExtRequestType ();
        input.setFlussoRiversamento ( inputRT.getFlussoRT () );
        input.setPagamentiIntermediati ( new TrasmettiFlussoRendicontazioneExtRequestType.PagamentiIntermediati () );
        input.getPagamentiIntermediati ().getPagamentoIntermediato ().addAll ( inputRT.getPagamentiIntermediati ().getPagamentoIntermediato () );
        input.setTestata ( inputRT.getTestata () );

        RequestContextUtils.getRequestContext ()
        .setInputData ( input )
        .setCaller ( RequestParserHelper.getCaller ( null ) )
        .setOutputDataType ( ResponseType.class );

        try {
            response = getEpaymodricBusiness ().acquisciFlussoRendicontazione ( input, TipoAcquisizione.RT );
        } catch ( EpaymodricException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace ();
        } catch ( UnrecoverableException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace ();
        } catch ( Exception e ) {
            // TODO Auto-generated catch block
            e.printStackTrace ();
        }

        return response;
    }
}
