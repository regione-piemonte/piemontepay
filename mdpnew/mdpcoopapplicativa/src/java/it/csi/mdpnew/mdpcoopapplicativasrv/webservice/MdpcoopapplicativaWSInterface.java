/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdpnew.mdpcoopapplicativasrv.webservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

import it.csi.mdpnew.mdpcoopapplicativasrv.dto.mdpcoopapplicativa.coop.ws.AggiornaCodiceVersamentoRequest;
import it.csi.mdpnew.mdpcoopapplicativasrv.dto.mdpcoopapplicativa.coop.ws.AggiornaCodiceVersamentoResponse;
import it.csi.mdpnew.mdpcoopapplicativasrv.dto.mdpcoopapplicativa.coop.ws.AggiornaEnteRequest;
import it.csi.mdpnew.mdpcoopapplicativasrv.dto.mdpcoopapplicativa.coop.ws.AggiornaEnteResponse;
import it.csi.mdpnew.mdpcoopapplicativasrv.dto.mdpcoopapplicativa.coop.ws.AnnullaOperazioneRequest;
import it.csi.mdpnew.mdpcoopapplicativasrv.dto.mdpcoopapplicativa.coop.ws.AnnullaOperazioneResponse;
import it.csi.mdpnew.mdpcoopapplicativasrv.dto.mdpcoopapplicativa.coop.ws.ConfermaOperazioneRequest;
import it.csi.mdpnew.mdpcoopapplicativasrv.dto.mdpcoopapplicativa.coop.ws.ConfermaOperazioneResponse;
import it.csi.mdpnew.mdpcoopapplicativasrv.dto.mdpcoopapplicativa.coop.ws.TestResourcesRequest;
import it.csi.mdpnew.mdpcoopapplicativasrv.dto.mdpcoopapplicativa.coop.ws.TestResourcesResponse;


@WebService ( targetNamespace = "http://www.csi.it/epay/epaywso/coopapplicativapec", name = "CoopApplicativaPEC" )
@SOAPBinding ( parameterStyle = SOAPBinding.ParameterStyle.BARE )
@XmlSeeAlso ( {
    it.csi.mdpnew.mdpcoopapplicativasrv.dto.mdpcoopapplicativa.coop.ws.ObjectFactory.class
} )
public interface MdpcoopapplicativaWSInterface {

    @WebMethod ( operationName = "AggiornaEnte", action = "http://www.csi.it/epay/epaywso/coopapplicativapec/AggiornaEnte" )
    @WebResult ( name = "AggiornaEnteResponse", targetNamespace = "http://www.csi.it/epay/epaywso/coopapplicativapec", partName = "parameters" )
    public AggiornaEnteResponse aggiornaEnte (
        @WebParam ( partName = "parameters", name = "AggiornaEnteRequest",
                        targetNamespace = "http://www.csi.it/epay/epaywso/coopapplicativapec" ) AggiornaEnteRequest parameters );

    @WebMethod ( operationName = "AnnullaOperazione", action = "http://www.csi.it/epay/epaywso/coopapplicativapec/AnnullaOperazione" )
    @WebResult ( name = "AnnullaOperazioneResponse", targetNamespace = "http://www.csi.it/epay/epaywso/coopapplicativapec", partName = "parameters" )
    public AnnullaOperazioneResponse annullaOperazione (
        @WebParam ( partName = "parameters", name = "AnnullaOperazioneRequest",
                        targetNamespace = "http://www.csi.it/epay/epaywso/coopapplicativapec" ) AnnullaOperazioneRequest parameters );

    @WebMethod ( operationName = "AggiornaCodiceVersamento", action = "http://www.csi.it/epay/epaywso/coopapplicativapec/AggiornaCodiceVersamento" )
    @WebResult ( name = "AggiornaCodiceVersamentoResponse", targetNamespace = "http://www.csi.it/epay/epaywso/coopapplicativapec", partName = "parameters" )
    public AggiornaCodiceVersamentoResponse aggiornaCodiceVersamento (
        @WebParam ( partName = "parameters", name = "AggiornaCodiceVersamentoRequest",
                        targetNamespace = "http://www.csi.it/epay/epaywso/coopapplicativapec" ) AggiornaCodiceVersamentoRequest parameters );

    @WebMethod ( operationName = "ConfermaOperazione", action = "http://www.csi.it/epay/epaywso/coopapplicativapec/ConfermaOperazione" )
    @WebResult ( name = "ConfermaOperazioneResponse", targetNamespace = "http://www.csi.it/epay/epaywso/coopapplicativapec", partName = "parameters" )
    public ConfermaOperazioneResponse confermaOperazione (
        @WebParam ( partName = "parameters", name = "ConfermaOperazioneRequest",
                        targetNamespace = "http://www.csi.it/epay/epaywso/coopapplicativapec" ) ConfermaOperazioneRequest parameters );

    @WebMethod ( operationName = "TestResources", action = "http://www.csi.it/epay/epaywso/coopapplicativapec/TestResources" )
    @WebResult ( name = "TestResourcesResponse", targetNamespace = "http://www.csi.it/epay/epaywso/coopapplicativapec", partName = "parameters" )
    public TestResourcesResponse testResources (
        @WebParam ( partName = "parameters", name = "TestResourcesRequest",
                        targetNamespace = "http://www.csi.it/epay/epaywso/coopapplicativapec" ) TestResourcesRequest parameters );
}
