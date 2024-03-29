/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayjob.interfacews.epaypacatalogsrv;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 2.4.6
 * 2018-11-23T11:10:47.404+01:00
 * Generated source version: 2.4.6
 */

@WebFault(name = "Exception", targetNamespace = "http://interfacews.epaypacatalogsrv.epay.csi.it/")
public class Exception_Exception extends java.lang.Exception {
    
    private it.csi.epay.epayjob.interfacews.epaypacatalogsrv.Exception exception;

    public Exception_Exception() {
        super();
    }
    
    public Exception_Exception(String message) {
        super(message);
    }
    
    public Exception_Exception(String message, Throwable cause) {
        super(message, cause);
    }

    public Exception_Exception(String message, it.csi.epay.epayjob.interfacews.epaypacatalogsrv.Exception exception) {
        super(message);
        this.exception = exception;
    }

    public Exception_Exception(String message, it.csi.epay.epayjob.interfacews.epaypacatalogsrv.Exception exception, Throwable cause) {
        super(message, cause);
        this.exception = exception;
    }

    public it.csi.epay.epayjob.interfacews.epaypacatalogsrv.Exception getFaultInfo() {
        return this.exception;
    }
}
