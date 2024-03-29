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

@WebFault(name = "EpaymodricException", targetNamespace = "http://batch.epaymodric.interfacews.epaymodric.epay.csi.it/")
public class EpaymodricException_Exception extends java.lang.Exception {
    public static final long serialVersionUID = 20230302150321L;
    
    private it.csi.epay.epayjob.interfacews.epaymodric.EpaymodricException epaymodricException;

    public EpaymodricException_Exception() {
        super();
    }
    
    public EpaymodricException_Exception(String message) {
        super(message);
    }
    
    public EpaymodricException_Exception(String message, Throwable cause) {
        super(message, cause);
    }

    public EpaymodricException_Exception(String message, it.csi.epay.epayjob.interfacews.epaymodric.EpaymodricException epaymodricException) {
        super(message);
        this.epaymodricException = epaymodricException;
    }

    public EpaymodricException_Exception(String message, it.csi.epay.epayjob.interfacews.epaymodric.EpaymodricException epaymodricException, Throwable cause) {
        super(message, cause);
        this.epaymodricException = epaymodricException;
    }

    public it.csi.epay.epayjob.interfacews.epaymodric.EpaymodricException getFaultInfo() {
        return this.epaymodricException;
    }
}
