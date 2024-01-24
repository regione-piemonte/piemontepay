/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypaweb.facade.sincronizzapagamentipec.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

import it.csi.epay.epaypaweb.facade.sincronizzapagamentipec.dto.SincronizzaPagamentiRequest;
import it.csi.epay.epaypaweb.facade.sincronizzapagamentipec.dto.SincronizzaPagamentiResponse;
import it.csi.epay.epaypaweb.facade.sincronizzapagamentipec.dto.TestResourcesRequest;
import it.csi.epay.epaypaweb.facade.sincronizzapagamentipec.dto.TestResourcesResponse;


@WebService ( targetNamespace = "http://www.csi.it/epay/epaypa/sincronizzapagamentipec", name = "SincronizzaPagamentiPEC" )
@SOAPBinding ( parameterStyle = SOAPBinding.ParameterStyle.BARE )
@XmlSeeAlso ( {
    it.csi.epay.epaypaweb.facade.sincronizzapagamentipec.dto.ObjectFactory.class,
} )
public interface SincronizzaPagamentiPEC {

    @WebMethod ( operationName = "SincronizzaPagamenti", action = "http://www.csi.it/epay/epaypa/sincronizzapagamentipec/SincronizzaPagamenti" )
    @WebResult ( name = "SincronizzaPagamentiResponse", targetNamespace = "http://www.csi.it/epay/epaypa/sincronizzapagamentipec", partName = "parameters" )
    public SincronizzaPagamentiResponse sincronizzaPagamenti (
        @WebParam ( partName = "parameters", name = "SincronizzaPagamentiRequest",
                        targetNamespace = "http://www.csi.it/epay/epaypa/sincronizzapagamentipec" ) SincronizzaPagamentiRequest parameters );

    @WebMethod ( operationName = "TestResources", action = "http://www.csi.it/epay/epaypa/sincronizzapagamentipec/TestResources" )
    @WebResult ( name = "TestResourcesResponse", targetNamespace = "http://www.csi.it/epay/epaypa/sincronizzapagamentipec", partName = "parameters" )
    public TestResourcesResponse testResources (
        @WebParam ( partName = "parameters", name = "TestResourcesRequest",
                        targetNamespace = "http://www.csi.it/epay/epaypa/sincronizzapagamentipec" ) TestResourcesRequest parameters );

}
