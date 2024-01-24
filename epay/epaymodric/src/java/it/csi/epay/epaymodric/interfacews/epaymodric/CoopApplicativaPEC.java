/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.interfacews.epaymodric;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

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
import it.csi.epay.epaymodric.util.wsdl.coopapplicativapec.ObjectFactory;
import it.csi.epay.epaymodric.util.wsdl.coopapplicativapec.TestResourcesRequest;
import it.csi.epay.epaymodric.util.wsdl.coopapplicativapec.TestResourcesResponse;


@WebService(targetNamespace = "http://www.csi.it/epay/epaywso/coopapplicativapec", name = "CoopApplicativaPEC")
@XmlSeeAlso({ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
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

    @WebMethod(operationName = "AggiornaRiferimentoContabile", action = "http://www.csi.it/epay/epaywso/coopapplicativapec/AggiornaRiferimentoContabile")
    @WebResult(name = "AggiornaRiferimentoContabileResponse", targetNamespace = "http://www.csi.it/epay/epaywso/coopapplicativapec", partName = "parameters")
    public AggiornaRiferimentoContabileResponse aggiornaRiferimentoContabile(
        @WebParam(partName = "parameters", name = "AggiornaRiferimentoContabileRequest", targetNamespace = "http://www.csi.it/epay/epaywso/coopapplicativapec")
        AggiornaRiferimentoContabileRequest parameters
                    );

    @WebMethod(operationName = "TestResources", action = "http://www.csi.it/epay/epaywso/coopapplicativapec/TestResources")
    @WebResult(name = "TestResourcesResponse", targetNamespace = "http://www.csi.it/epay/epaywso/coopapplicativapec", partName = "parameters")
    public TestResourcesResponse testResources(
        @WebParam(partName = "parameters", name = "TestResourcesRequest", targetNamespace = "http://www.csi.it/epay/epaywso/coopapplicativapec")
        TestResourcesRequest parameters
                    );
}
