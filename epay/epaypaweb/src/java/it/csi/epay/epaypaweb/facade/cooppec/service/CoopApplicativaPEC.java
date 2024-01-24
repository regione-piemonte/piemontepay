/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypaweb.facade.cooppec.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

import it.csi.epay.epaypaweb.facade.cooppec.dto.AggiornaCodiceVersamentoRequest;
import it.csi.epay.epaypaweb.facade.cooppec.dto.AggiornaCodiceVersamentoResponse;
import it.csi.epay.epaypaweb.facade.cooppec.dto.AggiornaEnteRequest;
import it.csi.epay.epaypaweb.facade.cooppec.dto.AggiornaEnteResponse;
import it.csi.epay.epaypaweb.facade.cooppec.dto.AnnullaOperazioneRequest;
import it.csi.epay.epaypaweb.facade.cooppec.dto.AnnullaOperazioneResponse;
import it.csi.epay.epaypaweb.facade.cooppec.dto.ConfermaOperazioneRequest;
import it.csi.epay.epaypaweb.facade.cooppec.dto.ConfermaOperazioneResponse;
import it.csi.epay.epaypaweb.facade.cooppec.dto.TestResourcesRequest;
import it.csi.epay.epaypaweb.facade.cooppec.dto.TestResourcesResponse;

@WebService(targetNamespace = "http://www.csi.it/epay/epaywso/coopapplicativapec", name = "CoopApplicativaPEC")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    it.csi.epay.epaypaweb.facade.cooppec.dto.ObjectFactory.class,
})
public interface CoopApplicativaPEC {

    @WebMethod(operationName = "AggiornaEnte", action = "http://www.csi.it/epay/epaywso/coopapplicativapec/AggiornaEnte")
    @WebResult(name = "AggiornaEnteResponse", targetNamespace = "http://www.csi.it/epay/epaywso/coopapplicativapec", partName = "parameters")
    public AggiornaEnteResponse aggiornaEnte(
        @WebParam(partName = "parameters", name = "AggiornaEnteRequest", targetNamespace = "http://www.csi.it/epay/epaywso/coopapplicativapec")
        AggiornaEnteRequest parameters
                    );
    
    @WebMethod(operationName = "AnnullaOperazione", action = "http://www.csi.it/epay/epaywso/coopapplicativapec/AnnullaOperazione")
    @WebResult(name = "AnnullaOperazioneResponse", targetNamespace = "http://www.csi.it/epay/epaywso/coopapplicativapec", partName = "parameters")
    public AnnullaOperazioneResponse annullaOperazione(
        @WebParam(partName = "parameters", name = "AnnullaOperazioneRequest", targetNamespace = "http://www.csi.it/epay/epaywso/coopapplicativapec")
        AnnullaOperazioneRequest parameters
                    );
    
    @WebMethod(operationName = "AggiornaCodiceVersamento", action = "http://www.csi.it/epay/epaywso/coopapplicativapec/AggiornaCodiceVersamento")
    @WebResult(name = "AggiornaCodiceVersamentoResponse", targetNamespace = "http://www.csi.it/epay/epaywso/coopapplicativapec", partName = "parameters")
    public AggiornaCodiceVersamentoResponse aggiornaCodiceVersamento(
        @WebParam(partName = "parameters", name = "AggiornaCodiceVersamentoRequest", targetNamespace = "http://www.csi.it/epay/epaywso/coopapplicativapec")
        AggiornaCodiceVersamentoRequest parameters
                    );
    
    @WebMethod(operationName = "ConfermaOperazione", action = "http://www.csi.it/epay/epaywso/coopapplicativapec/ConfermaOperazione")
    @WebResult(name = "ConfermaOperazioneResponse", targetNamespace = "http://www.csi.it/epay/epaywso/coopapplicativapec", partName = "parameters")
    public ConfermaOperazioneResponse confermaOperazione(
        @WebParam(partName = "parameters", name = "ConfermaOperazioneRequest", targetNamespace = "http://www.csi.it/epay/epaywso/coopapplicativapec")
        ConfermaOperazioneRequest parameters
                    );
    
    @WebMethod(operationName = "TestResources", action = "http://www.csi.it/epay/epaywso/coopapplicativapec/TestResources")
    @WebResult(name = "TestResourcesResponse", targetNamespace = "http://www.csi.it/epay/epaywso/coopapplicativapec", partName = "parameters")
    public TestResourcesResponse testResources(
        @WebParam(partName = "parameters", name = "TestResourcesRequest", targetNamespace = "http://www.csi.it/epay/epaywso/coopapplicativapec")
        TestResourcesRequest parameters
                    );    
    
}
