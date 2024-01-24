/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayservices.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 */

public class AccessoChiamanteEsternoSincronoSplitInput implements Serializable{

    private static final long serialVersionUID = 1L;
    
    private Date timestampChiamata;

    private String ipChiamante;

    private String codiceChiamante;

    private String identificativoPagamento;

    
    public Date getTimestampChiamata () {
        return timestampChiamata;
    }

    
    public void setTimestampChiamata ( Date timestampChiamata ) {
        this.timestampChiamata = timestampChiamata;
    }

    
    public String getIpChiamante () {
        return ipChiamante;
    }

    
    public void setIpChiamante ( String ipChiamante ) {
        this.ipChiamante = ipChiamante;
    }

    
    public String getCodiceChiamante () {
        return codiceChiamante;
    }

    
    public void setCodiceChiamante ( String codiceChiamante ) {
        this.codiceChiamante = codiceChiamante;
    }

    public String getIdentificativoPagamento () {
        return identificativoPagamento;
    }

    
    public void setIdentificativoPagamento ( String identificativoPagamento ) {
        this.identificativoPagamento = identificativoPagamento;
    }

    @Override
    public String toString () {
        return "AccessoChiamanteEsternoSincronoSplitInput [" 
        		+ ( timestampChiamata != null ? "timestampChiamata=" + timestampChiamata + ", " : "" )
        		+ ( ipChiamante != null ? "ipChiamante=" + ipChiamante + ", " : "" )
        		+ ( codiceChiamante != null ? "codiceChiamante=" + codiceChiamante + ", " : "" ) 
        		+ ( identificativoPagamento != null ? "identificativoPagamento=" + identificativoPagamento + ", " : "" )
        		+ "]";
    }

    
    
    
 
}
