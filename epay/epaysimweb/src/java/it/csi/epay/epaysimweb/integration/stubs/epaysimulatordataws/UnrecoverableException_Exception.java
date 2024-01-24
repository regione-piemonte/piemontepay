/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 3.2.5
 * 2018-10-24T15:04:56.686+02:00
 * Generated source version: 3.2.5
 */

@WebFault(name = "UnrecoverableException", targetNamespace = "http://epaysim.interfacews.epaysim.epay.csi.it/")
public class UnrecoverableException_Exception extends java.lang.Exception {

    private it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws.UnrecoverableException unrecoverableException;

    public UnrecoverableException_Exception() {
        super();
    }

    public UnrecoverableException_Exception(String message) {
        super(message);
    }

    public UnrecoverableException_Exception(String message, java.lang.Throwable cause) {
        super(message, cause);
    }

    public UnrecoverableException_Exception(String message, it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws.UnrecoverableException unrecoverableException) {
        super(message);
        this.unrecoverableException = unrecoverableException;
    }

    public UnrecoverableException_Exception(String message, it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws.UnrecoverableException unrecoverableException, java.lang.Throwable cause) {
        super(message, cause);
        this.unrecoverableException = unrecoverableException;
    }

    public it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws.UnrecoverableException getFaultInfo() {
        return this.unrecoverableException;
    }
}
