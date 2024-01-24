/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdpnew.mdpcoopapplicativasrv.webservice;

import javax.jws.WebService;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import it.csi.mdpnew.mdpcoopapplicativasrv.business.mdpcoopapplicativa.interfaces.MdpcoopapplicativaBusinessInterface;
import it.csi.mdpnew.mdpcoopapplicativasrv.dto.mdpcoopapplicativa.coop.ws.AggiornaCodiceVersamentoRequest;
import it.csi.mdpnew.mdpcoopapplicativasrv.dto.mdpcoopapplicativa.coop.ws.AggiornaCodiceVersamentoResponse;
import it.csi.mdpnew.mdpcoopapplicativasrv.dto.mdpcoopapplicativa.coop.ws.AggiornaEnteRequest;
import it.csi.mdpnew.mdpcoopapplicativasrv.dto.mdpcoopapplicativa.coop.ws.AggiornaEnteResponse;
import it.csi.mdpnew.mdpcoopapplicativasrv.dto.mdpcoopapplicativa.coop.ws.AnnullaOperazioneRequest;
import it.csi.mdpnew.mdpcoopapplicativasrv.dto.mdpcoopapplicativa.coop.ws.AnnullaOperazioneResponse;
import it.csi.mdpnew.mdpcoopapplicativasrv.dto.mdpcoopapplicativa.coop.ws.ConfermaOperazioneRequest;
import it.csi.mdpnew.mdpcoopapplicativasrv.dto.mdpcoopapplicativa.coop.ws.ConfermaOperazioneResponse;
import it.csi.mdpnew.mdpcoopapplicativasrv.dto.mdpcoopapplicativa.coop.ws.ResponseType;
import it.csi.mdpnew.mdpcoopapplicativasrv.dto.mdpcoopapplicativa.coop.ws.ResultType;
import it.csi.mdpnew.mdpcoopapplicativasrv.dto.mdpcoopapplicativa.coop.ws.TestResourcesRequest;
import it.csi.mdpnew.mdpcoopapplicativasrv.dto.mdpcoopapplicativa.coop.ws.TestResourcesResponse;
import it.csi.mdpnew.mdpcoopapplicativasrv.util.MdpcoopapplicativaConstants;
import it.csi.mdpnew.mdpcoopapplicativasrv.util.coop.CostantiErrori;


@WebService ( name = "CoopApplicativaPEC", portName = "CoopApplicativaPEC", serviceName = "CoopApplicativaPEC",
                targetNamespace = "http://www.csi.it/epay/epaywso/coopapplicativapec" )
public class MdpcoopapplicativaWSImpl implements MdpcoopapplicativaWSInterface {

    protected static Logger log = Logger.getLogger ( MdpcoopapplicativaConstants.MDPCOOPAPPLICATIVA_ROOT_LOG_CATEGORY + ".ws" );

    MdpcoopapplicativaBusinessInterface mdpcoopapplicativaBusinessInterface;

    public MdpcoopapplicativaBusinessInterface getMdpcoopapplicativaBusinessInterface () {
        return mdpcoopapplicativaBusinessInterface;
    }

    public void setMdpcoopapplicativaBusinessInterface (
        MdpcoopapplicativaBusinessInterface mdpcoopapplicativaBusinessInterface ) {
        this.mdpcoopapplicativaBusinessInterface = mdpcoopapplicativaBusinessInterface;
    }

    
    public AggiornaCodiceVersamentoResponse aggiornaCodiceVersamento ( AggiornaCodiceVersamentoRequest parameters ) {

        AggiornaCodiceVersamentoResponse response = new AggiornaCodiceVersamentoResponse ();
        
        ResponseType responseType = new ResponseType ();
        try {
            responseType = mdpcoopapplicativaBusinessInterface.aggiornaCodiceVersamento ( parameters );
        } catch ( Exception e ) {
            ResultType resultType = new ResultType ();
            resultType.setCodice ( CostantiErrori.WS_ESITO_KO_DEFAULT );
            resultType.setMessaggio ( "Errore in fase di aggiornamento dei codici veramento." );
            responseType.setResult ( resultType );
        }
        
        response.setEsito ( responseType );
        
        return response;
        
    }

    
    public AggiornaEnteResponse aggiornaEnte ( AggiornaEnteRequest parameters ) {

        AggiornaEnteResponse response = new AggiornaEnteResponse ();

        ResponseType responseType = new ResponseType ();
        try {
            responseType = mdpcoopapplicativaBusinessInterface.aggiornaEnte ( parameters );
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

    
    public AnnullaOperazioneResponse annullaOperazione ( AnnullaOperazioneRequest parameters ) {
        AnnullaOperazioneResponse response = new AnnullaOperazioneResponse ();

        ResponseType responseType = new ResponseType ();
        try {
            responseType = mdpcoopapplicativaBusinessInterface.annullaOperazione ( parameters );
        } catch ( Exception e ) {
            ResultType resultType = new ResultType ();
            resultType.setCodice ( CostantiErrori.WS_ESITO_KO_DEFAULT );
            resultType.setMessaggio ( "Errore in fase di aggiornamento di annullamento dell'operazione." );
            responseType.setResult ( resultType );
        }

        response.setEsito ( responseType );

        return response;

    }

    
    public ConfermaOperazioneResponse confermaOperazione ( ConfermaOperazioneRequest parameters ) {

        ConfermaOperazioneResponse response = new ConfermaOperazioneResponse ();

        ResponseType responseType = new ResponseType ();
        try {
            responseType = mdpcoopapplicativaBusinessInterface.confermaOperazione ( parameters );
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

