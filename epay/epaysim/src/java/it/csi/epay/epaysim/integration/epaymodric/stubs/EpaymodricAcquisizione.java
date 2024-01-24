/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaysim.integration.epaymodric.stubs;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 2.4.6
 * 2018-07-30T12:17:55.690+02:00
 * Generated source version: 2.4.6
 * 
 */
@WebService(targetNamespace = "http://epaymodric.interfacews.epaymodric.epay.csi.it/", name = "EpaymodricAcquisizione")
@XmlSeeAlso({ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface EpaymodricAcquisizione {

    @WebMethod(operationName = "TrasmettiFlussoRendicontazioneExt", action = "http://www.csi.it/epay/epaywso/rendicontazione-ext/TrasmettiFlussoRendicontazioneExt")
    @WebResult(name = "EPaywsoServiceResponse", targetNamespace = "http://www.csi.it/epay/epaywso/types", partName = "parameters")
    public ResponseType trasmettiFlussoRendicontazioneExt(
        @WebParam(partName = "parameters", name = "TrasmettiFlussoRendicontazioneExtRequest", targetNamespace = "http://www.csi.it/epay/epaywso/rendicontazione-ext")
        TrasmettiFlussoRendicontazioneExtRequestType parameters
    ) throws UnrecoverableException_Exception, Exception_Exception, EpaymodricException_Exception;
}