/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymdpservices.webservices.interfaces;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 2.2.5
 * Mon Dec 14 16:19:15 CET 2015
 * Generated source version: 2.2.5
 * 
 */

@WebFault(name = "RiceviRTException", targetNamespace = "http://serviziofruitore.interfacews.mdp.nodospc.csi.it/")
public class RiceviEsitoException extends Exception {
    public static final long serialVersionUID = 20151214161915L;
    
    private RiceviEsitoExceptionInfo riceviRTException;

    public RiceviEsitoException() {
        super();
    }
    
    public RiceviEsitoException(String message) {
        super(message);
    }
    
    public RiceviEsitoException(String message, Throwable cause) {
        super(message, cause);
    }

    public RiceviEsitoException(String message, RiceviEsitoExceptionInfo riceviRTException) {
        super(message);
        this.riceviRTException = riceviRTException;
    }

    public RiceviEsitoException(String message, RiceviEsitoExceptionInfo riceviRTException, Throwable cause) {
        super(message, cause);
        this.riceviRTException = riceviRTException;
    }

    public RiceviEsitoExceptionInfo getFaultInfo() {
        return this.riceviRTException;
    }
}
