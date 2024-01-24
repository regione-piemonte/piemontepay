/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the cbl_t_ente database table.
 *
 */
@Entity
@Table ( name = "cbl_t_ente_temp", schema = "epaymodric" )
@NamedQuery(name="CblTEnteTemp.findAll", query="SELECT c FROM CblTEnteTemp c")
public class CblTEnteTemp extends DatiTecniciParentEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /* necessaria per i metodi di audit */
    @Override
    public String getPrimaryKeyRepresentation () {
        if ( id == null ) {
            return "null";
        } else {
            return id.toString ();
        }
    }

    @Id
    @SequenceGenerator ( name = "CBL_T_ENTE_TEMP_ID_GENERATOR", sequenceName = "epaymodric.CBL_T_ENTE_TEMP_ID_SEQ", schema = "epaymodric", allocationSize = 1 )
    @GeneratedValue ( strategy = GenerationType.SEQUENCE, generator = "CBL_T_ENTE_TEMP_ID_GENERATOR" )
    private Long id;

    @Column(name="codice_fiscale")
    private String codiceFiscale;

    private String denominazione;

    @Column(name="email_ente")
    private String emailEnte;

    @Column(name="ente_plurintermediato")
    private Boolean entePlurintermediato;

    @Column(name="flag_accertamento")
    private Boolean flagAccertamento;

    @Column(name="flag_ricezione_errori")
    private Boolean flagRicezioneErrori;

    @Column(name="flag_riconciliazione")
    private Boolean flagRiconciliazione;

    @Column(name="giorno_schedulazione")
    private Integer giornoSchedulazione;

    @Column(name="iban_tesoreria")
    private String ibanTesoreria;

    @Column(name="id_ente")
    private String idEnte;

    @Column(name="modalita_acquisizione_provvisori")
    private Integer modalitaAcquisizioneProvvisori;

    @Column(name="periodicita_schedulazione")
    private Integer periodicitaSchedulazione;

    @Column ( name = "id_operazione" )
    private String idOperazione;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    public String getDenominazione() {
        return denominazione;
    }

    public void setDenominazione(String denominazione) {
        this.denominazione = denominazione;
    }

    public String getEmailEnte() {
        return emailEnte;
    }

    public void setEmailEnte(String emailEnte) {
        this.emailEnte = emailEnte;
    }

    public Boolean getEntePlurintermediato() {
        return entePlurintermediato;
    }

    public void setEntePlurintermediato(Boolean entePlurintermediato) {
        this.entePlurintermediato = entePlurintermediato;
    }

    public Boolean getFlagAccertamento() {
        return flagAccertamento;
    }

    public void setFlagAccertamento(Boolean flagAccertamento) {
        this.flagAccertamento = flagAccertamento;
    }

    public Boolean getFlagRicezioneErrori() {
        return flagRicezioneErrori;
    }

    public void setFlagRicezioneErrori(Boolean flagRicezioneErrori) {
        this.flagRicezioneErrori = flagRicezioneErrori;
    }

    public Boolean getFlagRiconciliazione() {
        return flagRiconciliazione;
    }

    public void setFlagRiconciliazione(Boolean flagRiconciliazione) {
        this.flagRiconciliazione = flagRiconciliazione;
    }

    public Integer getGiornoSchedulazione() {
        return giornoSchedulazione;
    }

    public void setGiornoSchedulazione(Integer giornoSchedulazione) {
        this.giornoSchedulazione = giornoSchedulazione;
    }

    public String getIbanTesoreria() {
        return ibanTesoreria;
    }

    public void setIbanTesoreria(String ibanTesoreria) {
        this.ibanTesoreria = ibanTesoreria;
    }

    public String getIdEnte() {
        return idEnte;
    }

    public void setIdEnte(String idEnte) {
        this.idEnte = idEnte;
    }

    public Integer getModalitaAcquisizioneProvvisori() {
        return modalitaAcquisizioneProvvisori;
    }

    public void setModalitaAcquisizioneProvvisori(Integer modalitaAcquisizioneProvvisori) {
        this.modalitaAcquisizioneProvvisori = modalitaAcquisizioneProvvisori;
    }

    public Integer getPeriodicitaSchedulazione() {
        return periodicitaSchedulazione;
    }

    public void setPeriodicitaSchedulazione(Integer periodicitaSchedulazione) {
        this.periodicitaSchedulazione = periodicitaSchedulazione;
    }

    /**
     * @return the idOperazione
     */
    public String getIdOperazione () {
        return idOperazione;
    }

    /**
     * @param idOperazione the idOperazione to set
     */
    public void setIdOperazione ( String idOperazione ) {
        this.idOperazione = idOperazione;
    }

}
