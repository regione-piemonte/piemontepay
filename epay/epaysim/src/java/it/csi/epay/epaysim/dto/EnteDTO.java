/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaysim.dto;

import java.io.Serializable;


public class EnteDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String codiceFiscale;

    private String denominazione;

    private String emailEnte;

    private Boolean entePlurintermediato;

    private Boolean flagAccertamento;

    private Boolean flagRicezioneErrori;

    private Boolean flagRiconciliazione;

    private Integer giornoSchedulazione;

    private String ibanTesoreria;

    private String idEnte;

    private Integer modalitaAcquisizioneProvvisori;

    private Integer periodicitaSchedulazione;

    public EnteDTO ( Long id, String codiceFiscale, String denominazione, String emailEnte, Boolean entePlurintermediato, Boolean flagAccertamento,
        Boolean flagRicezioneErrori, Boolean flagRiconciliazione, Integer giornoSchedulazione, String ibanTesoreria, String idEnte,
        Integer modalitaAcquisizioneProvvisori, Integer periodicitaSchedulazione ) {
        super ();
        this.id = id;
        this.codiceFiscale = codiceFiscale;
        this.denominazione = denominazione;
        this.emailEnte = emailEnte;
        this.entePlurintermediato = entePlurintermediato;
        this.flagAccertamento = flagAccertamento;
        this.flagRicezioneErrori = flagRicezioneErrori;
        this.flagRiconciliazione = flagRiconciliazione;
        this.giornoSchedulazione = giornoSchedulazione;
        this.ibanTesoreria = ibanTesoreria;
        this.idEnte = idEnte;
        this.modalitaAcquisizioneProvvisori = modalitaAcquisizioneProvvisori;
        this.periodicitaSchedulazione = periodicitaSchedulazione;
    }

    public Long getId () {
        return this.id;
    }

    public void setId ( Long id ) {
        this.id = id;
    }

    public String getCodiceFiscale () {
        return this.codiceFiscale;
    }

    public void setCodiceFiscale ( String codiceFiscale ) {
        this.codiceFiscale = codiceFiscale;
    }

    public String getDenominazione () {
        return this.denominazione;
    }

    public void setDenominazione ( String denominazione ) {
        this.denominazione = denominazione;
    }

    public String getEmailEnte () {
        return this.emailEnte;
    }

    public void setEmailEnte ( String emailEnte ) {
        this.emailEnte = emailEnte;
    }

    public Boolean getEntePlurintermediato () {
        return this.entePlurintermediato;
    }

    public void setEntePlurintermediato ( Boolean entePlurintermediato ) {
        this.entePlurintermediato = entePlurintermediato;
    }

    public Boolean getFlagAccertamento () {
        return this.flagAccertamento;
    }

    public void setFlagAccertamento ( Boolean flagAccertamento ) {
        this.flagAccertamento = flagAccertamento;
    }

    public Boolean getFlagRicezioneErrori () {
        return this.flagRicezioneErrori;
    }

    public void setFlagRicezioneErrori ( Boolean flagRicezioneErrori ) {
        this.flagRicezioneErrori = flagRicezioneErrori;
    }

    public Boolean getFlagRiconciliazione () {
        return this.flagRiconciliazione;
    }

    public void setFlagRiconciliazione ( Boolean flagRiconciliazione ) {
        this.flagRiconciliazione = flagRiconciliazione;
    }

    public Integer getGiornoSchedulazione () {
        return this.giornoSchedulazione;
    }

    public void setGiornoSchedulazione ( Integer giornoSchedulazione ) {
        this.giornoSchedulazione = giornoSchedulazione;
    }

    public String getIbanTesoreria () {
        return this.ibanTesoreria;
    }

    public void setIbanTesoreria ( String ibanTesoreria ) {
        this.ibanTesoreria = ibanTesoreria;
    }

    public String getIdEnte () {
        return this.idEnte;
    }

    public void setIdEnte ( String idEnte ) {
        this.idEnte = idEnte;
    }

    public Integer getModalitaAcquisizioneProvvisori () {
        return this.modalitaAcquisizioneProvvisori;
    }

    public void setModalitaAcquisizioneProvvisori ( Integer modalitaAcquisizioneProvvisori ) {
        this.modalitaAcquisizioneProvvisori = modalitaAcquisizioneProvvisori;
    }

    public Integer getPeriodicitaSchedulazione () {
        return this.periodicitaSchedulazione;
    }

    public void setPeriodicitaSchedulazione ( Integer periodicitaSchedulazione ) {
        this.periodicitaSchedulazione = periodicitaSchedulazione;
    }

}
