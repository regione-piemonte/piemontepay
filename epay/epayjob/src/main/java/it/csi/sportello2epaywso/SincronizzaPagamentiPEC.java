/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.sportello2epaywso;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 3.2.5
 * 2020-01-22T17:44:32.059+01:00
 * Generated source version: 3.2.5
 *
 */
@WebService(targetNamespace = "http://www.csi.it/epay/epaypa/sincronizzapagamentipec", name = "SincronizzaPagamentiPEC")
@XmlSeeAlso({ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface SincronizzaPagamentiPEC {

    @WebMethod(operationName = "TestResources", action = "http://www.csi.it/epay/epaypa/sincronizzapagamentipec/TestResources")
    @WebResult(name = "TestResourcesResponse", targetNamespace = "http://www.csi.it/epay/epaypa/sincronizzapagamentipec", partName = "parameters")
    public TestResourcesResponse testResources(
        @WebParam(partName = "parameters", name = "TestResourcesRequest", targetNamespace = "http://www.csi.it/epay/epaypa/sincronizzapagamentipec")
        TestResourcesRequest parameters
    );

    @WebMethod(operationName = "SincronizzaPagamenti", action = "http://www.csi.it/epay/epaypa/sincronizzapagamentipec/SincronizzaPagamenti")
    @WebResult(name = "SincronizzaPagamentiResponse", targetNamespace = "http://www.csi.it/epay/epaypa/sincronizzapagamentipec", partName = "parameters")
    public SincronizzaPagamentiResponse sincronizzaPagamenti(
        @WebParam(partName = "parameters", name = "SincronizzaPagamentiRequest", targetNamespace = "http://www.csi.it/epay/epaypa/sincronizzapagamentipec")
        SincronizzaPagamentiRequest parameters
    );
}