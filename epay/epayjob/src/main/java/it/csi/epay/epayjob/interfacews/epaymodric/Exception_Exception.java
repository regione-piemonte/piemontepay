/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayjob.interfacews.epaymodric;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 2.2.5
 * Thu Mar 02 15:03:21 CET 2023
 * Generated source version: 2.2.5
 * 
 */

@WebFault(name = "Exception", targetNamespace = "http://batch.epaymodric.interfacews.epaymodric.epay.csi.it/")
public class Exception_Exception extends java.lang.Exception {
    public static final long serialVersionUID = 20230302150321L;
    
    private it.csi.epay.epayjob.interfacews.epaymodric.Exception exception;

    public Exception_Exception() {
        super();
    }
    
    public Exception_Exception(String message) {
        super(message);
    }
    
    public Exception_Exception(String message, Throwable cause) {
        super(message, cause);
    }

    public Exception_Exception(String message, it.csi.epay.epayjob.interfacews.epaymodric.Exception exception) {
        super(message);
        this.exception = exception;
    }

    public Exception_Exception(String message, it.csi.epay.epayjob.interfacews.epaymodric.Exception exception, Throwable cause) {
        super(message, cause);
        this.exception = exception;
    }

    public it.csi.epay.epayjob.interfacews.epaymodric.Exception getFaultInfo() {
        return this.exception;
    }
}
