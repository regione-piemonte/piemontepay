/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.util.wsdl.epaywso;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 2.2.5
 * Fri Nov 27 11:46:40 CET 2020
 * Generated source version: 2.2.5
 * 
 */
 
@WebService(targetNamespace = "http://www.csi.it/epay/epaywso/business", name = "EPaywsoBusinessService")
@XmlSeeAlso({ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface EPaywsoBusinessService {

    @WebMethod(operationName = "InserisciNuovaRichiesta", action = "http://www.csi.it/epay/epaywso/business/InserisciNuovaRichiesta")
    @WebResult(name = "InserisciNuovaRichiestaResponse", targetNamespace = "http://www.csi.it/epay/epaywso/businesstypes", partName = "parameters")
    public InserisciNuovaRichiestaResponseType inserisciNuovaRichiesta(
        @WebParam(partName = "parameters", name = "InserisciNuovaRichiestaRequest", targetNamespace = "http://www.csi.it/epay/epaywso/businesstypes")
        InserisciNuovaRichiestaRequestType parameters
    );

    @WebMethod(operationName = "RicercaEnte", action = "http://www.csi.it/epay/epaywso/business/RicercaEnte")
    @WebResult(name = "RicercaEnteResponse", targetNamespace = "http://www.csi.it/epay/epaywso/businesstypes", partName = "parameters")
    public RicercaEnteResponseType ricercaEnte(
        @WebParam(partName = "parameters", name = "RicercaEnteRequest", targetNamespace = "http://www.csi.it/epay/epaywso/businesstypes")
        RicercaEnteRequestType parameters
    );

    @WebMethod(operationName = "RicercaListaConfigurazioniApplicativi", action = "http://www.csi.it/epay/epaywso/business/RicercaListaConfigurazioniApplicativi")
    @WebResult(name = "RicercaListaConfigurazioniApplicativiResponse", targetNamespace = "http://www.csi.it/epay/epaywso/businesstypes", partName = "parameters")
    public RicercaListaConfigurazioniApplicativiResponseType ricercaListaConfigurazioniApplicativi(
        @WebParam(partName = "parameters", name = "RicercaListaConfigurazioniApplicativiRequest", targetNamespace = "http://www.csi.it/epay/epaywso/businesstypes")
        RicercaListaConfigurazioniApplicativiRequestType parameters
    );

    @WebMethod(operationName = "AggiornaRichiestaESingoloEsitoInvio", action = "http://www.csi.it/epay/epaywso/business/AggiornaRichiestaESingoloEsitoInvio")
    @WebResult(name = "AggiornaRichiestaESingoloEsitoInvioResponse", targetNamespace = "http://www.csi.it/epay/epaywso/businesstypes", partName = "parameters")
    public ResponseType aggiornaRichiestaESingoloEsitoInvio(
        @WebParam(partName = "parameters", name = "AggiornaRichiestaESingoloEsitoInvioRequest", targetNamespace = "http://www.csi.it/epay/epaywso/businesstypes")
        AggiornaRichiestaESingoloEsitoInvioRequestType parameters
    );

    @WebMethod(operationName = "RicercaConfigurazioneApplicativo", action = "http://www.csi.it/epay/epaywso/business/RicercaConfigurazioneApplicativo")
    @WebResult(name = "RicercaConfigurazioneApplicativoResponse", targetNamespace = "http://www.csi.it/epay/epaywso/businesstypes", partName = "parameters")
    public RicercaConfigurazioneApplicativoResponseType ricercaConfigurazioneApplicativo(
        @WebParam(partName = "parameters", name = "RicercaConfigurazioneApplicativoRequest", targetNamespace = "http://www.csi.it/epay/epaywso/businesstypes")
        RicercaConfigurazioneApplicativoRequestType parameters
    );

    @WebMethod(operationName = "RicercaStatiAggregatiWso", action = "http://www.csi.it/epay/epaywso/business/RicercaStatoAggregatoWso")
    @WebResult(name = "RicercaStatiAggregatiWsoResponse", targetNamespace = "http://www.csi.it/epay/epaywso/businesstypes", partName = "parameters")
    public RicercaStatiAggregatiWsoResponseType ricercaStatiAggregatiWso(
        @WebParam(partName = "parameters", name = "RicercaStatiAggregatiWsoRequest", targetNamespace = "http://www.csi.it/epay/epaywso/businesstypes")
        RicercaStatiAggregatiWsoRequestType parameters
    );

    @WebMethod(operationName = "ContaNumeroRichiesteSelezionate", action = "http://www.csi.it/epay/epaywso/business/ContaNumeroRichiesteSelezionate")
    @WebResult(name = "ContaNumeroRichiesteSelezionateResponse", targetNamespace = "http://www.csi.it/epay/epaywso/businesstypes", partName = "parameters")
    public ContaNumeroRichiesteSelezionateResponseType contaNumeroRichiesteSelezionate(
        @WebParam(partName = "parameters", name = "ContaNumeroRichiesteSelezionateRequest", targetNamespace = "http://www.csi.it/epay/epaywso/businesstypes")
        ContaNumeroRichiesteSelezionateRequestType parameters
    );

    @WebMethod(operationName = "DeterminaProssimoEndpoint", action = "http://www.csi.it/epay/epaywso/business/DeterminaProssimoEndpoint")
    @WebResult(name = "DeterminaProssimoEndpointResponse", targetNamespace = "http://www.csi.it/epay/epaywso/businesstypes", partName = "parameters")
    public DeterminaProssimoEndpointResponseType determinaProssimoEndpoint(
        @WebParam(partName = "parameters", name = "DeterminaProssimoEndpointRequest", targetNamespace = "http://www.csi.it/epay/epaywso/businesstypes")
        DeterminaProssimoEndpointRequestType parameters
    );

    @WebMethod(operationName = "AggiornaRichiesta", action = "http://www.csi.it/epay/epaywso/business/AggiornaRichiesta")
    @WebResult(name = "AggiornaRichiestaResponse", targetNamespace = "http://www.csi.it/epay/epaywso/businesstypes", partName = "parameters")
    public ResponseType aggiornaRichiesta(
        @WebParam(partName = "parameters", name = "AggiornaRichiestaRequest", targetNamespace = "http://www.csi.it/epay/epaywso/businesstypes")
        AggiornaRichiestaRequestType parameters
    );

    @WebMethod(operationName = "LeggiRichiesta", action = "http://www.csi.it/epay/epaywso/business/LeggiRichiesta")
    @WebResult(name = "LeggiRichiestaResponse", targetNamespace = "http://www.csi.it/epay/epaywso/businesstypes", partName = "parameters")
    public LeggiRichiestaResponseType leggiRichiesta(
        @WebParam(partName = "parameters", name = "LeggiRichiestaRequest", targetNamespace = "http://www.csi.it/epay/epaywso/businesstypes")
        LeggiRichiestaRequestType parameters
    );

    @WebMethod(operationName = "AggiornaEsitoComplessivo", action = "http://www.csi.it/epay/epaywso/business/AggiornaEsitoComplessivo")
    @WebResult(name = "AggiornaEsitoComplessivoResponse", targetNamespace = "http://www.csi.it/epay/epaywso/businesstypes", partName = "parameters")
    public ResponseType aggiornaEsitoComplessivo(
        @WebParam(partName = "parameters", name = "AggiornaEsitoComplessivoRequest", targetNamespace = "http://www.csi.it/epay/epaywso/businesstypes")
        AggiornaEsitoComplessivoRequestType parameters
    );

    @WebMethod(operationName = "RicercaRichieste", action = "http://www.csi.it/epay/epaywso/business/RicercaRichieste")
    @WebResult(name = "RicercaRichiesteResponse", targetNamespace = "http://www.csi.it/epay/epaywso/businesstypes", partName = "parameters")
    public RicercaRichiesteResponseType ricercaRichieste(
        @WebParam(partName = "parameters", name = "RicercaRichiesteRequest", targetNamespace = "http://www.csi.it/epay/epaywso/businesstypes")
        RicercaRichiesteRequestType parameters
    );

    @WebMethod(operationName = "AggiornaEsitoInvio", action = "http://www.csi.it/epay/epaywso/business/AggiornaEsitoInvio")
    @WebResult(name = "AggiornaEsitoInvioResponse", targetNamespace = "http://www.csi.it/epay/epaywso/businesstypes", partName = "parameters")
    public ResponseType aggiornaEsitoInvio(
        @WebParam(partName = "parameters", name = "AggiornaEsitoInvioRequest", targetNamespace = "http://www.csi.it/epay/epaywso/businesstypes")
        AggiornaEsitoInvioRequestType parameters
    );
}
