/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.integration.stubs.epaymodricws;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 3.2.5
 * 2018-08-03T09:43:22.119+02:00
 * Generated source version: 3.2.5
 */

@WebFault(name = "UnrecoverableException", targetNamespace = "http://epaymodric.interfacews.epaymodric.epay.csi.it/")
public class UnrecoverableException_Exception extends java.lang.Exception {

    private it.csi.epay.epaypacatalogsrv.integration.stubs.epaymodricws.UnrecoverableException unrecoverableException;

    public UnrecoverableException_Exception() {
        super();
    }

    public UnrecoverableException_Exception(String message) {
        super(message);
    }

    public UnrecoverableException_Exception(String message, java.lang.Throwable cause) {
        super(message, cause);
    }

    public UnrecoverableException_Exception(String message, it.csi.epay.epaypacatalogsrv.integration.stubs.epaymodricws.UnrecoverableException unrecoverableException) {
        super(message);
        this.unrecoverableException = unrecoverableException;
    }

    public UnrecoverableException_Exception(String message, it.csi.epay.epaypacatalogsrv.integration.stubs.epaymodricws.UnrecoverableException unrecoverableException, java.lang.Throwable cause) {
        super(message, cause);
        this.unrecoverableException = unrecoverableException;
    }

    public it.csi.epay.epaypacatalogsrv.integration.stubs.epaymodricws.UnrecoverableException getFaultInfo() {
        return this.unrecoverableException;
    }
}
