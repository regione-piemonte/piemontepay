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

@WebFault(name = "ChiediDatiPagamentoException", targetNamespace = "http://serviziofruitore.interfacews.mdp.nodospc.csi.it/")
public class ChiediDatiPagamentoException extends Exception {
    public static final long serialVersionUID = 20151214161915L;
    
    private ChiediDatiPagamentoInfo chiediDatiPagamentoException;

    public ChiediDatiPagamentoException() {
        super();
    }
    
    public ChiediDatiPagamentoException(String message) {
        super(message);
    }
    
    public ChiediDatiPagamentoException(String message, Throwable cause) {
        super(message, cause);
    }

    public ChiediDatiPagamentoException(String message, ChiediDatiPagamentoInfo chiediDatiPagamentoException) {
        super(message);
        this.chiediDatiPagamentoException = chiediDatiPagamentoException;
    }

    public ChiediDatiPagamentoException(String message, ChiediDatiPagamentoInfo chiediDatiPagamentoException, Throwable cause) {
        super(message, cause);
        this.chiediDatiPagamentoException = chiediDatiPagamentoException;
    }

    public ChiediDatiPagamentoInfo getFaultInfo() {
        return this.chiediDatiPagamentoException;
    }
}
