/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.dto.epaymodric.base;

import java.io.Serializable;


/**
 * 
 * @ author vsgro
 */
public class DTOEnte implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String codiceFiscale;

    private String denominazione;

    private String emailEnte;

    private Boolean entePlurintermediato;

    private Boolean flagAccertamento;

    private Boolean flagRicezioneErrori;

    private Boolean flagRiconciliazione;

    private int giornoSchedulazione;

    private String ibanTesoreria;

    private String idEnte;

    private int periodicitaSchedulazione;

    public Long getId () {
        return id;
    }

    public void setId ( Long id ) {
        this.id = id;
    }

    public String getIdEnte () {
        return idEnte;
    }

    public void setIdEnte ( String idEnte ) {
        this.idEnte = idEnte;
    }

    public String getCodiceFiscale () {
        return codiceFiscale;
    }

    public void setCodiceFiscale ( String codiceFiscale ) {
        this.codiceFiscale = codiceFiscale;
    }

    public String getDenominazione () {
        return denominazione;
    }

    public void setDenominazione ( String denominazione ) {
        this.denominazione = denominazione;
    }

    public Boolean isFlagAccertamento () {
        return flagAccertamento;
    }

    public void setFlagAccertamento ( Boolean flagAccertamento ) {
        this.flagAccertamento = flagAccertamento;
    }

    public Boolean isFlagRiconciliazione () {
        return flagRiconciliazione;
    }

    public void setFlagRiconciliazione ( Boolean flagRiconciliazione ) {
        this.flagRiconciliazione = flagRiconciliazione;
    }

    public String getIbanTesoreria () {
        return ibanTesoreria;
    }

    public void setIbanTesoreria ( String ibanTesoreria ) {
        this.ibanTesoreria = ibanTesoreria;
    }

    public Boolean isEntePlurintermediato () {
        return entePlurintermediato;
    }

    public void setEntePlurintermediato ( Boolean entePlurintermediato ) {
        this.entePlurintermediato = entePlurintermediato;
    }

    public int getPeriodicitaSchedulazione () {
        return periodicitaSchedulazione;
    }

    public void setPeriodicitaSchedulazione ( int periodicitaSchedulazione ) {
        this.periodicitaSchedulazione = periodicitaSchedulazione;
    }

    public int getGiornoSchedulazione () {
        return giornoSchedulazione;
    }

    public void setGiornoSchedulazione ( int giornoSchedulazione ) {
        this.giornoSchedulazione = giornoSchedulazione;
    }

    public Boolean isFlagRicezioneErrori () {
        return flagRicezioneErrori;
    }

    public void setFlagRicezioneErrori ( Boolean flagRicezioneErrori ) {
        this.flagRicezioneErrori = flagRicezioneErrori;
    }

    public String getEmailEnte () {
        return emailEnte;
    }

    public void setEmailEnte ( String emailEnte ) {
        this.emailEnte = emailEnte;
    }

}
