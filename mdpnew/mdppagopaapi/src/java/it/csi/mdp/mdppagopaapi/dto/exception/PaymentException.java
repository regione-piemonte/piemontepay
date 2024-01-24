/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdppagopaapi.dto.exception;

import org.springframework.util.StringUtils;

import it.csi.mdp.mdppagopaapi.pa.pafornode.CtFaultBean;

/**
 *
 */

public class PaymentException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 3215645938406902214L;

    private final CtFaultBean ctFault;


    /**
     */
    public PaymentException ( CtFaultBean ctFault ) {
        super ();
        this.ctFault = ctFault;
    }



    /**
     * @return the ctfault
     */
    public CtFaultBean getCtFault () {
        return ctFault;
    }

    public String getCtCodiceDescrizione () {
        StringBuilder ret = new StringBuilder ();
        if ( null != ctFault && StringUtils.hasLength ( ctFault.getFaultCode () ) ) {
            ret.append ( ctFault.getFaultCode () );
            if ( StringUtils.hasLength ( ctFault.getDescription () ) ) {
                ret.append ( "," ).append ( ctFault.getDescription () );
            }
        }
        return ret.toString ();
    }
}
