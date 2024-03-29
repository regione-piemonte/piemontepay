/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.util.wsdl.epayflussicompletipsp;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

import it.csi.epay.epaymodric.util.xsd.epayriconciliazioneversamentipsp.ResponseType;

/**
 * This class was generated by Apache CXF 2.4.6
 * 2020-02-05T16:28:49.234+01:00
 * Generated source version: 2.4.6
 * 
 */
@WebService(targetNamespace = "http://www.csi.it/epay/epaywso/flussi-completi-psp", name = "EPayFlussiCompletiPSP")
@XmlSeeAlso({ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface EPayFlussiCompletiPSP {

    @WebMethod(operationName = "TrasmettiFlussiPagoPA", action = "http://www.csi.it/epay/epaywso/FlussiCompletiPSP/TrasmettiFlussiPagoPA")
    @WebResult(name = "TrasmettiFlussiPagoPAResponse", targetNamespace = "http://www.csi.it/epay/epaywso/flussi-completi-psp/types", partName = "parameters")
    public ResponseType trasmettiFlussiPagoPA(
        @WebParam(partName = "parameters", name = "TrasmettiFlussiPagoPARequest", targetNamespace = "http://www.csi.it/epay/epaywso/flussi-completi-psp/types")
        TrasmettiFlussiPagoPARequest parameters
    );
}
