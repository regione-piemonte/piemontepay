/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdppagopaapi.util.clientws;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 2.2.5
 * Fri Feb 18 11:58:24 CET 2022
 * Generated source version: 2.2.5
 * 
 */

@WebFault(name = "RiceviRTException", targetNamespace = "http://serviziofruitore.interfacews.mdp.nodospc.csi.it/")
public class RiceviEsitoException extends Exception {
    public static final long serialVersionUID = 20220218115824L;
    
    private it.csi.mdp.mdppagopaapi.util.clientws.RiceviRTException riceviRTException;

    public RiceviEsitoException() {
        super();
    }
    
    public RiceviEsitoException(String message) {
        super(message);
    }
    
    public RiceviEsitoException(String message, Throwable cause) {
        super(message, cause);
    }

    public RiceviEsitoException(String message, it.csi.mdp.mdppagopaapi.util.clientws.RiceviRTException riceviRTException) {
        super(message);
        this.riceviRTException = riceviRTException;
    }

    public RiceviEsitoException(String message, it.csi.mdp.mdppagopaapi.util.clientws.RiceviRTException riceviRTException, Throwable cause) {
        super(message, cause);
        this.riceviRTException = riceviRTException;
    }

    public it.csi.mdp.mdppagopaapi.util.clientws.RiceviRTException getFaultInfo() {
        return this.riceviRTException;
    }
}
