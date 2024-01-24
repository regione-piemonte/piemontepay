/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.ws.business;

import java.util.concurrent.Callable;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

import it.csi.epay.epayservices.interfaces.rs.CodiciEsito;
import it.csi.epay.epayservices.utilities.LogUtil;
import it.csi.epay.epayservices.utilities.XmlUtil;
import it.csi.epay.epayservices.ws.business.coopapplicativapec.AggiornaCodiceVersamentoOperation;
import it.csi.epay.epayservices.ws.business.coopapplicativapec.AggiornaEnteOperation;
import it.csi.epay.epayservices.ws.business.coopapplicativapec.AggiornaRiferimentoContabileOperation;
import it.csi.epay.epayservices.ws.business.coopapplicativapec.AnnullaOperazioneOperation;
import it.csi.epay.epayservices.ws.business.coopapplicativapec.ConfermaOperazioneOperation;
import it.csi.epay.epayservices.ws.business.coopapplicativapec.TestResourcesOperation;
import it.csi.epay.epayservices.ws.coopapplicativapec.AggiornaCodiceVersamentoRequest;
import it.csi.epay.epayservices.ws.coopapplicativapec.AggiornaCodiceVersamentoResponse;
import it.csi.epay.epayservices.ws.coopapplicativapec.AggiornaEnteRequest;
import it.csi.epay.epayservices.ws.coopapplicativapec.AggiornaEnteResponse;
import it.csi.epay.epayservices.ws.coopapplicativapec.AggiornaRiferimentoContabileRequest;
import it.csi.epay.epayservices.ws.coopapplicativapec.AggiornaRiferimentoContabileResponse;
import it.csi.epay.epayservices.ws.coopapplicativapec.AnnullaOperazioneRequest;
import it.csi.epay.epayservices.ws.coopapplicativapec.AnnullaOperazioneResponse;
import it.csi.epay.epayservices.ws.coopapplicativapec.ConfermaOperazioneRequest;
import it.csi.epay.epayservices.ws.coopapplicativapec.ConfermaOperazioneResponse;
import it.csi.epay.epayservices.ws.coopapplicativapec.ResponseType;
import it.csi.epay.epayservices.ws.coopapplicativapec.ResultType;
import it.csi.epay.epayservices.ws.coopapplicativapec.TestResourcesRequest;
import it.csi.epay.epayservices.ws.coopapplicativapec.TestResourcesResponse;
import it.csi.epay.epayservices.ws.interfaces.CoopApplicativaPECService;


@Stateless
@WebService (
                targetNamespace = "http://www.csi.it/epay/epaywso/coopapplicativapec",
                portName = "CoopApplicativaPEC",
                serviceName = "CoopApplicativaPEC",
                endpointInterface = "it.csi.epay.epayservices.ws.interfaces.CoopApplicativaPECService" )
public class CoopApplicativaPEC implements CoopApplicativaPECService {

    protected LogUtil log = new LogUtil ( this.getClass () );

    static final private int maxErrorMessageWidth = 200;

    @EJB
    TestResourcesOperation testResourcesOperation;

    @EJB
    AggiornaEnteOperation aggiornaEnteOperation;

    @EJB
    AggiornaCodiceVersamentoOperation aggiornaCodiceVersamentoOperation;

    @EJB
    AggiornaRiferimentoContabileOperation aggiornaRiferimentoContabileOperation;

    @EJB
    ConfermaOperazioneOperation confermaOperazioneOperation;

    @EJB
    AnnullaOperazioneOperation annullaOperazioneOperation;

    @Override
    public AggiornaEnteResponse aggiornaEnte ( AggiornaEnteRequest parameters ) {
        ResultType result = execute ( "aggiornaEnte", parameters, new Callable<CodiciEsito> () {

            @Override
            public CodiciEsito call () throws Exception {
                return aggiornaEnteOperation.execute ( parameters );
            }

        } );

        AggiornaEnteResponse response = new AggiornaEnteResponse ();
        ResponseType esito = new ResponseType ();
        esito.setResult ( result );
        response.setEsito ( esito );
        return response;
    }

    @Override
    public AnnullaOperazioneResponse annullaOperazione ( AnnullaOperazioneRequest parameters ) {
        ResultType result = execute ( "annullaOperazione", parameters, new Callable<CodiciEsito> () {

            @Override
            public CodiciEsito call () throws Exception {
                return annullaOperazioneOperation.execute ( parameters );
            }

        } );

        AnnullaOperazioneResponse response = new AnnullaOperazioneResponse ();
        ResponseType esito = new ResponseType ();
        esito.setResult ( result );
        response.setEsito ( esito );
        return response;
    }

    @Override
    public AggiornaCodiceVersamentoResponse aggiornaCodiceVersamento ( AggiornaCodiceVersamentoRequest parameters ) {
        ResultType result = execute ( "aggiornaCodiceVersamento", parameters, new Callable<CodiciEsito> () {

            @Override
            public CodiciEsito call () throws Exception {
                return aggiornaCodiceVersamentoOperation.execute ( parameters );
            }

        } );

        AggiornaCodiceVersamentoResponse response = new AggiornaCodiceVersamentoResponse ();
        ResponseType esito = new ResponseType ();
        esito.setResult ( result );
        response.setEsito ( esito );
        return response;
    }

    @Override
    public ConfermaOperazioneResponse confermaOperazione ( ConfermaOperazioneRequest parameters ) {
        ResultType result = execute ( "confermaOperazione", parameters, new Callable<CodiciEsito> () {

            @Override
            public CodiciEsito call () throws Exception {
                return confermaOperazioneOperation.execute ( parameters );
            }

        } );

        ConfermaOperazioneResponse response = new ConfermaOperazioneResponse ();
        ResponseType esito = new ResponseType ();
        esito.setResult ( result );
        response.setEsito ( esito );
        return response;
    }

    @Override
    public AggiornaRiferimentoContabileResponse aggiornaRiferimentoContabile ( AggiornaRiferimentoContabileRequest parameters ) {
        ResultType result = execute ( "aggiornaRiferimentoContabile", parameters, new Callable<CodiciEsito> () {

            @Override
            public CodiciEsito call () throws Exception {
                return aggiornaRiferimentoContabileOperation.execute ( parameters );
            }

        } );

        AggiornaRiferimentoContabileResponse response = new AggiornaRiferimentoContabileResponse ();
        ResponseType esito = new ResponseType ();
        esito.setResult ( result );
        response.setEsito ( esito );
        return response;
    }

    @Override
    public TestResourcesResponse testResources ( TestResourcesRequest parameters ) {
        ResultType result = execute ( "testResources", parameters, new Callable<CodiciEsito> () {

            @Override
            public CodiciEsito call () throws Exception {
                return testResourcesOperation.execute ( parameters );
            }

        } );

        TestResourcesResponse response = new TestResourcesResponse ();
        ResponseType esito = new ResponseType ();
        esito.setResult ( result );
        response.setEsito ( esito );
        return response;
    }

    private <T> ResultType execute ( String methodName, T input, Callable<CodiciEsito> callable ) {
        log.debugStart ( methodName );
        log.debug ( methodName, "Request\n : " + XmlUtil.obj2Xml ( input ) );

        ResultType result = new ResultType ();
        CodiciEsito ce;

        try {
            ce = callable.call ();
            log.debug ( methodName, "Response\n : " + XmlUtil.obj2Xml ( ce ) );

            result.setCodice ( ce.getCodice () );
            result.setMessaggio ( ce.getMessaggio ( maxErrorMessageWidth ) );

        } catch ( Exception e ) {
            log.error ( methodName, "Errore servizio:" );
            log.error ( methodName, e.getMessage () );

            ce = CodiciEsito.COOP_ERRORE_GENERICO;
            result.setCodice ( ce.getCodice () );
            result.setMessaggio ( ce.getMessaggio ( maxErrorMessageWidth,
                getExceptionMessage ( getRealException ( e ) ) ) );
        }

        log.debugEnd ( methodName );

        return result;
    }

    private Throwable getRealException ( Throwable e ) {
        while ( e.getCause () != null ) {
            e = e.getCause ();
        }

        return e;
    }

    private String getExceptionMessage ( Throwable e ) {
        if ( e.getMessage () != null ) {
            return e.getMessage ();
        } else {
            return e.getClass ().getSimpleName ();
        }
    }
}
