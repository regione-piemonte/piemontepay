/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.integration.stubs.epaymodricws;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 3.2.5
 * 2018-08-03T09:43:22.150+02:00
 * Generated source version: 3.2.5
 */

@WebFault(name = "EpaymodricException", targetNamespace = "http://epaymodric.interfacews.epaymodric.epay.csi.it/")
public class EpaymodricException_Exception extends java.lang.Exception {

    private it.csi.epay.epaypacatalogsrv.integration.stubs.epaymodricws.EpaymodricException epaymodricException;

    public EpaymodricException_Exception() {
        super();
    }

    public EpaymodricException_Exception(String message) {
        super(message);
    }

    public EpaymodricException_Exception(String message, java.lang.Throwable cause) {
        super(message, cause);
    }

    public EpaymodricException_Exception(String message, it.csi.epay.epaypacatalogsrv.integration.stubs.epaymodricws.EpaymodricException epaymodricException) {
        super(message);
        this.epaymodricException = epaymodricException;
    }

    public EpaymodricException_Exception(String message, it.csi.epay.epaypacatalogsrv.integration.stubs.epaymodricws.EpaymodricException epaymodricException, java.lang.Throwable cause) {
        super(message, cause);
        this.epaymodricException = epaymodricException;
    }

    public it.csi.epay.epaypacatalogsrv.integration.stubs.epaymodricws.EpaymodricException getFaultInfo() {
        return this.epaymodricException;
    }
}
