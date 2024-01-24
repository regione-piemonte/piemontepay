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
@WebFault(name = "MdpMultiIuvSrvException", targetNamespace = "http://mdpnew.csi.it/mdpmultiiuv/")
public class MdpMultiIuvSrvException_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private MdpMultiIuvSrvException faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public MdpMultiIuvSrvException_Exception(String message, MdpMultiIuvSrvException faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param cause
     * @param message
     */
    public MdpMultiIuvSrvException_Exception(String message, MdpMultiIuvSrvException faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: it.csi.mdpmultiiuv.MdpMultiIuvSrvException
     */
    public MdpMultiIuvSrvException getFaultInfo() {
        return faultInfo;
    }

}
