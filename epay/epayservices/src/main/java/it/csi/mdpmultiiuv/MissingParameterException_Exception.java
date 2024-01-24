/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdpmultiiuv;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.10
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "MissingParameterException", targetNamespace = "http://mdpnew.csi.it/mdpmultiiuv/")
public class MissingParameterException_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private MissingParameterException faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public MissingParameterException_Exception(String message, MissingParameterException faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param cause
     * @param message
     */
    public MissingParameterException_Exception(String message, MissingParameterException faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: it.csi.mdpmultiiuv.MissingParameterException
     */
    public MissingParameterException getFaultInfo() {
        return faultInfo;
    }

}
