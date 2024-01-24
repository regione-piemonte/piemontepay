/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto;


public class NotificheRevoca {
    private String iuv;
    private Long idNotificaPagamento;
    private Long idRR;
    private String descrizione;
    
    public String getIuv () {
        return iuv;
    }
    
    public void setIuv ( String iuv ) {
        this.iuv = iuv;
    }
    
    public Long getIdNotificaPagamento () {
        return idNotificaPagamento;
    }
    
    public void setIdNotificaPagamento ( Long idNotificaPagamento ) {
        this.idNotificaPagamento = idNotificaPagamento;
    }
    
    public Long getIdRR () {
        return idRR;
    }
    
    public void setIdRR ( Long idRR ) {
        this.idRR = idRR;
    }
    
    public String getDescrizione () {
        return descrizione;
    }
    
    public void setDescrizione ( String descrizione ) {
        this.descrizione = descrizione;
    }
}
