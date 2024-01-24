/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */



package it.csi.epay.epaywsosrv.facade.cooppec;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.jws.WebService;

import org.apache.commons.lang3.StringUtils;

import it.csi.epay.epaywsosrv.business.CoopApplicativaManager;
import it.csi.epay.epaywsosrv.facade.EPaywsoFacadeBase;
import it.csi.epay.epaywsosrv.facade.cooppec.dto.AggiornaCodiceVersamentoRequest;
import it.csi.epay.epaywsosrv.facade.cooppec.dto.AggiornaCodiceVersamentoResponse;
import it.csi.epay.epaywsosrv.facade.cooppec.dto.AggiornaEnteRequest;
import it.csi.epay.epaywsosrv.facade.cooppec.dto.AggiornaEnteResponse;
import it.csi.epay.epaywsosrv.facade.cooppec.dto.AnnullaOperazioneRequest;
import it.csi.epay.epaywsosrv.facade.cooppec.dto.AnnullaOperazioneResponse;
import it.csi.epay.epaywsosrv.facade.cooppec.dto.ConfermaOperazioneRequest;
import it.csi.epay.epaywsosrv.facade.cooppec.dto.ConfermaOperazioneResponse;
import it.csi.epay.epaywsosrv.facade.cooppec.dto.ResponseType;
import it.csi.epay.epaywsosrv.facade.cooppec.dto.ResultType;
import it.csi.epay.epaywsosrv.facade.cooppec.dto.TestResourcesRequest;
import it.csi.epay.epaywsosrv.facade.cooppec.dto.TestResourcesResponse;
import it.csi.epay.epaywsosrv.facade.cooppec.service.CoopApplicativaPEC;
import it.csi.epay.epaywsosrv.util.CostantiErrori;

@Stateless
@WebService(name = "CoopApplicativaPEC", portName = "CoopApplicativaPEC", serviceName="CoopApplicativaPEC", targetNamespace = "http://www.csi.it/epay/epaywso/coopapplicativapec")
@TransactionManagement(TransactionManagementType.CONTAINER)
public class CoopApplicativaPECImpl extends EPaywsoFacadeBase implements CoopApplicativaPEC {

    @Inject
    private CoopApplicativaManager coopApplicativaManager;

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
            if ( StringUtils.isNotBlank ( str ) ) {
                try {
                    String [] arr = str.split ( "=" );
                    if ( null != arr && arr.length > 0 ) {
                        str = arr [arr.length - 1];
                        if ( StringUtils.isNotBlank ( str ) ) {
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
            resultType.setMessaggio ( StringUtils.isNotBlank ( message ) ? message : "Errore in fase di aggiornamento di conferma dell'operazione." );
            responseType.setResult ( resultType );
        }

        response.setEsito ( responseType );

        return response;
    }

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
