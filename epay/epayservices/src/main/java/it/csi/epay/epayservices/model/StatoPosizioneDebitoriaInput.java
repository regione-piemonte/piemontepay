/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.model;

import java.io.Serializable;

public class StatoPosizioneDebitoriaInput implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private String codiceFiscaleEnte;
    
    private String paymentType;

    private String iuv;

    

    public StatoPosizioneDebitoriaInput ( String codiceFiscaleEnte, String paymentType, String iuv ) {
        super ();
        this.codiceFiscaleEnte = codiceFiscaleEnte;
        this.paymentType = paymentType;
        this.iuv = iuv;
    }

    public String getIuv () {
        return iuv;
    }

    public void setIuv ( String iuv ) {
        this.iuv = iuv;
    }
    
    
    public String getCodiceFiscaleEnte () {
        return codiceFiscaleEnte;
    }

    
    public void setCodiceFiscaleEnte ( String codiceFiscaleEnte ) {
        this.codiceFiscaleEnte = codiceFiscaleEnte;
    }

    public String getPaymentType () {
        return paymentType;
    }

    
    public void setPaymentType ( String paymentType ) {
        this.paymentType = paymentType;
    }

    @Override
    public String toString () {
        return "StatoPosizioneDebitoriaInput [codiceFiscaleEnte=" + codiceFiscaleEnte + ", paymentType=" + paymentType +
                        ", iuv=" + iuv +"]";
    }

}
