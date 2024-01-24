/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the dati_singolo_versamento database table.
 *
 */
@Entity
@Table(name="dati_singolo_versamento")
@NamedQuery(name="DatiSingoloVersamento.findAll", query="SELECT d FROM DatiSingoloVersamento d")
public class DatiSingoloVersamento implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator ( name = "DATI_SINGOLO_VERSAMENTO_ID_GENERATOR", sequenceName = "multiversamento_singolopag_keyid_seq", allocationSize = 1 )
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DATI_SINGOLO_VERSAMENTO_ID_GENERATOR")
    private Integer id;

    private Integer annoaccertamento;

    private String causaleversamento;

    private BigDecimal commissionecaricopa;

    private String credenzialipagatore;

    private String datispecificiriscossione;

    private String hashdocumento;

    private BigDecimal importosingoloversamento;

    @Column(name="multi_id")
    private Integer multiId;

    private Integer numeroaccertamento;

    private Integer posizione;

    private String provinciaresidenza;

    private String tipobollo;

    @Column ( name = "applicationid" )
    private String applicationid;

    @Generated ( "SparkTools" )
    private DatiSingoloVersamento ( Builder builder ) {
        this.id = builder.id;
        this.annoaccertamento = builder.annoaccertamento;
        this.causaleversamento = builder.causaleversamento;
        this.commissionecaricopa = builder.commissionecaricopa;
        this.credenzialipagatore = builder.credenzialipagatore;
        this.datispecificiriscossione = builder.datispecificiriscossione;
        this.hashdocumento = builder.hashdocumento;
        this.importosingoloversamento = builder.importosingoloversamento;
        this.multiId = builder.multiId;
        this.numeroaccertamento = builder.numeroaccertamento;
        this.posizione = builder.posizione;
        this.provinciaresidenza = builder.provinciaresidenza;
        this.tipobollo = builder.tipobollo;
        this.applicationid = builder.applicationid;
    }

    public DatiSingoloVersamento() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAnnoaccertamento() {
        return this.annoaccertamento;
    }

    public void setAnnoaccertamento(Integer annoaccertamento) {
        this.annoaccertamento = annoaccertamento;
    }

    public String getCausaleversamento() {
        return this.causaleversamento;
    }

    public void setCausaleversamento(String causaleversamento) {
        this.causaleversamento = causaleversamento;
    }

    public BigDecimal getCommissionecaricopa() {
        return this.commissionecaricopa;
    }

    public void setCommissionecaricopa(BigDecimal commissionecaricopa) {
        this.commissionecaricopa = commissionecaricopa;
    }

    public String getCredenzialipagatore() {
        return this.credenzialipagatore;
    }

    public void setCredenzialipagatore(String credenzialipagatore) {
        this.credenzialipagatore = credenzialipagatore;
    }

    public String getDatispecificiriscossione() {
        return this.datispecificiriscossione;
    }

    public void setDatispecificiriscossione(String datispecificiriscossione) {
        this.datispecificiriscossione = datispecificiriscossione;
    }

    public String getHashdocumento() {
        return this.hashdocumento;
    }

    public void setHashdocumento(String hashdocumento) {
        this.hashdocumento = hashdocumento;
    }

    public BigDecimal getImportosingoloversamento() {
        return this.importosingoloversamento;
    }

    public void setImportosingoloversamento(BigDecimal importosingoloversamento) {
        this.importosingoloversamento = importosingoloversamento;
    }

    public Integer getMultiId() {
        return this.multiId;
    }

    public void setMultiId(Integer multiId) {
        this.multiId = multiId;
    }

    public Integer getNumeroaccertamento() {
        return this.numeroaccertamento;
    }

    public void setNumeroaccertamento(Integer numeroaccertamento) {
        this.numeroaccertamento = numeroaccertamento;
    }

    public Integer getPosizione() {
        return this.posizione;
    }

    public void setPosizione(Integer posizione) {
        this.posizione = posizione;
    }

    public String getProvinciaresidenza() {
        return this.provinciaresidenza;
    }

    public void setProvinciaresidenza(String provinciaresidenza) {
        this.provinciaresidenza = provinciaresidenza;
    }

    public String getTipobollo() {
        return this.tipobollo;
    }

    public void setTipobollo(String tipobollo) {
        this.tipobollo = tipobollo;
    }

    public String getApplicationid () {
        return applicationid;
    }

    public void setApplicationid ( String applicationid ) {
        this.applicationid = applicationid;
    }

    @Generated ( "SparkTools" )
    public static Builder builder () {
        return new Builder ();
    }


    @Generated ( "SparkTools" )
    public static final class Builder {

        private Integer id;

        private Integer annoaccertamento;

        private String causaleversamento;

        private BigDecimal commissionecaricopa;

        private String credenzialipagatore;

        private String datispecificiriscossione;

        private String hashdocumento;

        private BigDecimal importosingoloversamento;

        private Integer multiId;

        private Integer numeroaccertamento;

        private Integer posizione;

        private String provinciaresidenza;

        private String tipobollo;

        private String applicationid;

        private Builder () {
        }

        public Builder withId ( Integer id ) {
            this.id = id;
            return this;
        }

        public Builder withAnnoaccertamento ( Integer annoaccertamento ) {
            this.annoaccertamento = annoaccertamento;
            return this;
        }

        public Builder withCausaleversamento ( String causaleversamento ) {
            this.causaleversamento = causaleversamento;
            return this;
        }

        public Builder withCommissionecaricopa ( BigDecimal commissionecaricopa ) {
            this.commissionecaricopa = commissionecaricopa;
            return this;
        }

        public Builder withCredenzialipagatore ( String credenzialipagatore ) {
            this.credenzialipagatore = credenzialipagatore;
            return this;
        }

        public Builder withDatispecificiriscossione ( String datispecificiriscossione ) {
            this.datispecificiriscossione = datispecificiriscossione;
            return this;
        }

        public Builder withHashdocumento ( String hashdocumento ) {
            this.hashdocumento = hashdocumento;
            return this;
        }

        public Builder withImportosingoloversamento ( BigDecimal importosingoloversamento ) {
            this.importosingoloversamento = importosingoloversamento;
            return this;
        }

        public Builder withMultiId ( Integer multiId ) {
            this.multiId = multiId;
            return this;
        }

        public Builder withNumeroaccertamento ( Integer numeroaccertamento ) {
            this.numeroaccertamento = numeroaccertamento;
            return this;
        }

        public Builder withPosizione ( Integer posizione ) {
            this.posizione = posizione;
            return this;
        }

        public Builder withProvinciaresidenza ( String provinciaresidenza ) {
            this.provinciaresidenza = provinciaresidenza;
            return this;
        }

        public Builder withTipobollo ( String tipobollo ) {
            this.tipobollo = tipobollo;
            return this;
        }

        public Builder withApplicationid ( String applicationid ) {
            this.applicationid = applicationid;
            return this;
        }

        public DatiSingoloVersamento build () {
            return new DatiSingoloVersamento ( this );
        }
    }


}
