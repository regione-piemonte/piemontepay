/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodricweb.integration.stubs.epaymodricws;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 2.4.6
 * 2019-06-18T09:05:34.448+02:00
 * Generated source version: 2.4.6
 */

@WebFault(name = "EpaymodricException", targetNamespace = "http://epaymodric.interfacews.epaymodric.epay.csi.it/")
public class EpaymodricException_Exception extends java.lang.Exception {
    
    private it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.EpaymodricException epaymodricException;

    public EpaymodricException_Exception() {
        super();
    }
    
    public EpaymodricException_Exception(String message) {
        super(message);
    }
    
    public EpaymodricException_Exception(String message, Throwable cause) {
        super(message, cause);
    }

    public EpaymodricException_Exception(String message, it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.EpaymodricException epaymodricException) {
        super(message);
        this.epaymodricException = epaymodricException;
    }

    public EpaymodricException_Exception(String message, it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.EpaymodricException epaymodricException, Throwable cause) {
        super(message, cause);
        this.epaymodricException = epaymodricException;
    }

    public it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.EpaymodricException getFaultInfo() {
        return this.epaymodricException;
    }
}
