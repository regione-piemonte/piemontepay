/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypaweb.dto;

import java.io.Serializable;

/**
 *
 */

public class DatiAvvisoPagamentoDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String settore;

    private String indirizzo;

    private String localita;

    private String cap;

    private String siglaProvincia;

    private String email;

    private String infoEnte;

    private String intestatarioCCPostale;

    private String autorizzazioneDiPosteIt;

    private String numeroCCPostale;

    public String getSettore () {
        return settore;
    }

    public void setSettore ( String settore ) {
        this.settore = settore;
    }

    public String getIndirizzo () {
        return indirizzo;
    }

    public void setIndirizzo ( String indirizzo ) {
        this.indirizzo = indirizzo;
    }

    public String getLocalita () {
        return localita;
    }

    public void setLocalita ( String localita ) {
        this.localita = localita;
    }

    public String getCap () {
        return cap;
    }

    public void setCap ( String cap ) {
        this.cap = cap;
    }

    public String getSiglaProvincia () {
        return siglaProvincia;
    }

    public void setSiglaProvincia ( String siglaProvincia ) {
        this.siglaProvincia = siglaProvincia;
    }

    public String getEmail () {
        return email;
    }

    public void setEmail ( String email ) {
        this.email = email;
    }

    public String getInfoEnte () {
        return infoEnte;
    }

    public void setInfoEnte ( String infoEnte ) {
        this.infoEnte = infoEnte;
    }

    public String getIntestatarioCCPostale () {
        return intestatarioCCPostale;
    }

    public void setIntestatarioCCPostale ( String intestatarioCCPostale ) {
        this.intestatarioCCPostale = intestatarioCCPostale;
    }

    public String getAutorizzazioneDiPosteIt () {
        return autorizzazioneDiPosteIt;
    }

    public void setAutorizzazioneDiPosteIt ( String autorizzazioneDiPosteIt ) {
        this.autorizzazioneDiPosteIt = autorizzazioneDiPosteIt;
    }

    public String getNumeroCCPostale () {
        return numeroCCPostale;
    }

    public void setNumeroCCPostale ( String numeroCCPostale ) {
        this.numeroCCPostale = numeroCCPostale;
    }

}
