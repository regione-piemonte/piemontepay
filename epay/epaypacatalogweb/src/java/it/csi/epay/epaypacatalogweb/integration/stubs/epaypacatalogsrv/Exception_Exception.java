/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 3.2.5
 * 2019-06-10T14:12:29.610+02:00
 * Generated source version: 3.2.5
 */

@WebFault(name = "Exception", targetNamespace = "http://interfacews.epaypacatalogsrv.epay.csi.it/")
public class Exception_Exception extends java.lang.Exception {

    private it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.Exception exception;

    public Exception_Exception() {
        super();
    }

    public Exception_Exception(String message) {
        super(message);
    }

    public Exception_Exception(String message, java.lang.Throwable cause) {
        super(message, cause);
    }

    public Exception_Exception(String message, it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.Exception exception) {
        super(message);
        this.exception = exception;
    }

    public Exception_Exception(String message, it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.Exception exception, java.lang.Throwable cause) {
        super(message, cause);
        this.exception = exception;
    }

    public it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.Exception getFaultInfo() {
        return this.exception;
    }
}
