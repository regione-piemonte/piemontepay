/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodricweb.integration.stubs.epaymodricws;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 2.4.6
 * 2019-06-18T09:05:34.440+02:00
 * Generated source version: 2.4.6
 */

@WebFault(name = "Exception", targetNamespace = "http://epaymodric.interfacews.epaymodric.epay.csi.it/")
public class Exception_Exception extends java.lang.Exception {
    
    private it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.Exception exception;

    public Exception_Exception() {
        super();
    }
    
    public Exception_Exception(String message) {
        super(message);
    }
    
    public Exception_Exception(String message, Throwable cause) {
        super(message, cause);
    }

    public Exception_Exception(String message, it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.Exception exception) {
        super(message);
        this.exception = exception;
    }

    public Exception_Exception(String message, it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.Exception exception, Throwable cause) {
        super(message, cause);
        this.exception = exception;
    }

    public it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.Exception getFaultInfo() {
        return this.exception;
    }
}
