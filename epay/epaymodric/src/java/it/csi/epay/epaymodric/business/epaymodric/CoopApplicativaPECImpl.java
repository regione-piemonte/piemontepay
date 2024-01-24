/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */



package it.csi.epay.epaymodric.business.epaymodric;

import javax.annotation.PostConstruct;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.csi.epay.epaymodric.business.epaymodric.manager.CoopApplicativaManager;
import it.csi.epay.epaymodric.interfacews.epaymodric.CoopApplicativaPEC;
import it.csi.epay.epaymodric.interfacews.epaymodric.consts.CostantiErrori;
import it.csi.epay.epaymodric.util.wsdl.coopapplicativapec.AggiornaCodiceVersamentoRequest;
import it.csi.epay.epaymodric.util.wsdl.coopapplicativapec.AggiornaCodiceVersamentoResponse;
import it.csi.epay.epaymodric.util.wsdl.coopapplicativapec.AggiornaEnteRequest;
import it.csi.epay.epaymodric.util.wsdl.coopapplicativapec.AggiornaEnteResponse;
import it.csi.epay.epaymodric.util.wsdl.coopapplicativapec.AggiornaRiferimentoContabileRequest;
import it.csi.epay.epaymodric.util.wsdl.coopapplicativapec.AggiornaRiferimentoContabileResponse;
import it.csi.epay.epaymodric.util.wsdl.coopapplicativapec.AnnullaOperazioneRequest;
import it.csi.epay.epaymodric.util.wsdl.coopapplicativapec.AnnullaOperazioneResponse;
import it.csi.epay.epaymodric.util.wsdl.coopapplicativapec.ConfermaOperazioneRequest;
import it.csi.epay.epaymodric.util.wsdl.coopapplicativapec.ConfermaOperazioneResponse;
import it.csi.epay.epaymodric.util.wsdl.coopapplicativapec.ResponseType;
import it.csi.epay.epaymodric.util.wsdl.coopapplicativapec.ResultType;
import it.csi.epay.epaymodric.util.wsdl.coopapplicativapec.TestResourcesRequest;
import it.csi.epay.epaymodric.util.wsdl.coopapplicativapec.TestResourcesResponse;


@WebService (
    serviceName = "CoopApplicativaPEC",
    portName = "CoopApplicativaPEC",
    targetNamespace = "http://www.csi.it/epay/epaywso/coopapplicativapec",
    endpointInterface = "it.csi.epay.epaymodric.interfacews.epaymodric.CoopApplicativaPEC" )

public class CoopApplicativaPECImpl implements CoopApplicativaPEC {

    @Autowired
    private CoopApplicativaManager coopApplicativaManager;

    /*
     * (non-Javadoc)
     * @see CoopApplicativaPEC#aggiornaCodiceVersamento(AggiornaCodiceVersamentoRequest parameters )*
     */
    @Override
    public AggiornaCodiceVersamentoResponse aggiornaCodiceVersamento ( AggiornaCodiceVersamentoRequest parameters ) {

        AggiornaCodiceVersamentoResponse response = new AggiornaCodiceVersamentoResponse ();

        ResponseType responseType = new ResponseType ();
        try {
            responseType = coopApplicativaManager.aggiornaCodiceVersamento ( parameters );
        } catch ( Exception e ) {
            ResultType resultType = new ResultType ();
            resultType.setCodice ( CostantiErrori.WS_ESITO_KO_DEFAULT );
            resultType.setMessaggio ( "Errore in fase di aggiornamento dei codici veramento." );
            responseType.setResult ( resultType );
        }

        response.setEsito ( responseType );

        return response;
    }

    /* (non-Javadoc)
     * @see it.csi.epay.epaymodric.util.wsdl.coopapplicativapec.CoopApplicativaPEC#aggiornaEnte(it.csi.epay.epaymodric.util.wsdl.coopapplicativapec.AggiornaEnteRequest  parameters )*
     */
    @Override
    public AggiornaEnteResponse aggiornaEnte ( AggiornaEnteRequest parameters ) {

        AggiornaEnteResponse response = new AggiornaEnteResponse ();

        ResponseType responseType = new ResponseType ();
        try {
            responseType = coopApplicativaManager.aggiornaEnte ( parameters );
        } catch ( Exception e ) {
            ResultType resultType = new ResultType ();
            resultType.setCodice ( CostantiErrori.WS_ESITO_KO_DEFAULT );
            String str = e.getMessage ();
            if ( StringUtils.hasText ( str ) ) {
                try {
                    String [] arr = str.split ( "=" );
                    if ( null != arr && arr.length > 0 ) {
                        str = arr [arr.length - 1];
                        if ( StringUtils.hasText ( str ) ) {
                            int indexFine = str.indexOf ( ")" );
                            int indexInizio = str.indexOf ( ", " );
                            if ( indexFine >= 0 && indexInizio >= 0 && indexFine >= indexInizio ) {
                                str = "Codice Versamento " + str.substring ( indexInizio + 1, indexFine ) + " non censito.";
                            }
                        }
                    }
                } catch ( Exception e1 ) {
                    str = "Errore in fase di aggiornamento dell'ente.";
                }
            } else {
                str = "Errore in fase di aggiornamento dell'ente.";
            }
            resultType.setMessaggio ( str );
            responseType.setResult ( resultType );
        }

        response.setEsito ( responseType );
        return response;

    }

    /*
     * (non-Javadoc)
     * @see CoopApplicativaPEC#aggiornaRiferimentoContabile(AggiornaRiferimentoContabileRequest parameters )*
     */
    @Override
    public AggiornaRiferimentoContabileResponse aggiornaRiferimentoContabile ( AggiornaRiferimentoContabileRequest parameters ) {

        AggiornaRiferimentoContabileResponse response = new AggiornaRiferimentoContabileResponse ();

        ResponseType responseType = new ResponseType ();
        try {
            responseType = coopApplicativaManager.aggiornaCatalogo ( parameters );
        } catch ( Exception e ) {
            ResultType resultType = new ResultType ();
            resultType.setCodice ( CostantiErrori.WS_ESITO_KO_DEFAULT );
            resultType.setMessaggio ( "Errore in fase di aggiornamento del riferimento contabile." );
            responseType.setResult ( resultType );
        }
        response.setEsito ( responseType );
        return response;
    }

    /*
     * (non-Javadoc)
     * @see CoopApplicativaPEC#annullaOperazione(AnnullaOperazioneRequest parameters )*
     */
    @Override
    public AnnullaOperazioneResponse annullaOperazione ( AnnullaOperazioneRequest parameters ) {

        AnnullaOperazioneResponse response = new AnnullaOperazioneResponse ();

        ResponseType responseType = new ResponseType ();
        try {
            responseType = coopApplicativaManager.annullaOperazione ( parameters );
        } catch ( Exception e ) {
            ResultType resultType = new ResultType ();
            resultType.setCodice ( CostantiErrori.WS_ESITO_KO_DEFAULT );
            resultType.setMessaggio ( "Errore in fase di aggiornamento di annullamento dell'operazione." );
            responseType.setResult ( resultType );
        }

        response.setEsito ( responseType );

        return response;

    }

    /*
     * (non-Javadoc)
     * @see CoopApplicativaPEC#confermaOperazione(ConfermaOperazioneRequest parameters )*
     */
    @Override
    public ConfermaOperazioneResponse confermaOperazione ( ConfermaOperazioneRequest parameters ) {

        ConfermaOperazioneResponse response = new ConfermaOperazioneResponse ();

        ResponseType responseType = new ResponseType ();
        try {
            responseType = coopApplicativaManager.confermaOperazione ( parameters );
        } catch ( Exception e ) {
            ResultType resultType = new ResultType ();
            resultType.setCodice ( CostantiErrori.WS_ESITO_KO_DEFAULT );
            String message = e.getMessage ();
            resultType.setMessaggio ( StringUtils.hasText ( message ) ? message : "Errore in fase di aggiornamento di conferma dell'operazione." );
            responseType.setResult ( resultType );
        }

        response.setEsito ( responseType );

        return response;
    }

    @PostConstruct
    public void postConstruct () {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext ( this );
    }

    /*
     * (non-Javadoc)
     * @see CoopApplicativaPEC#testResources(TestResourcesRequest parameters )*
     */
    @Override
    public TestResourcesResponse testResources ( TestResourcesRequest parameters ) {
        try {
            TestResourcesResponse response = new TestResourcesResponse ();
            ResponseType responseType = new ResponseType();
            ResultType result = new ResultType();
            result.setCodice ( CostantiErrori.WS_ESITO_OK_DEFAULT );
            result.setMessaggio ( "Operazione completata correttamente" );
            responseType.setResult ( result );
            response.setEsito ( responseType );
            return response;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

}
