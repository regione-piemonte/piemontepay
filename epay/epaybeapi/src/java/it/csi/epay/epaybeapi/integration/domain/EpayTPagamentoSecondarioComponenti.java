/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the epay_t_pagamento_secondario_componenti database table.
 *
 */
@Entity
@Table ( name = "epay_t_pagamento_secondario_componenti" )
@NamedQuery ( name = "EpayTPagamentoSecondarioComponenti.findAll", query = "SELECT e FROM EpayTPagamentoSecondarioComponenti e" )
public class EpayTPagamentoSecondarioComponenti implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator ( name = "EPAY_T_PAGAMENTO_SECONDARIO_COMPONENTI_ID_GENERATOR", sequenceName = "EPAY_T_PAGAMENTO_SECONDARIO_COMPONENTI_ID_SEQ" )
    @GeneratedValue ( strategy = GenerationType.SEQUENCE, generator = "EPAY_T_PAGAMENTO_SECONDARIO_COMPONENTI_ID_GENERATOR" )
    private Long id;

    @Column(name="anno_accertamento")
    private Integer annoAccertamento;

    private String causale;

    @Column(name="dati_specifici_riscossione")
    private String datiSpecificiRiscossione;

    private BigDecimal importo;

    @Column(name="numero_accertamento")
    private String numeroAccertamento;

    private Integer progressivo;

    @Column(name="utente_ultima_modifica")
    private String utenteUltimaModifica;
    
    @Column( name = "codice_tributo")
    private String codiceTributo;


    //bi-directional many-to-one association to EpayTPagamentoSecondario
    @ManyToOne
    @JoinColumn(name="id_pagamento_secondario")
    private EpayTPagamentoSecondario epayTPagamentoSecondario;

    public EpayTPagamentoSecondarioComponenti() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAnnoAccertamento() {
        return this.annoAccertamento;
    }

    public void setAnnoAccertamento(Integer annoAccertamento) {
        this.annoAccertamento = annoAccertamento;
    }

    public String getCausale() {
        return this.causale;
    }

    public void setCausale(String causale) {
        this.causale = causale;
    }

    public String getDatiSpecificiRiscossione() {
        return this.datiSpecificiRiscossione;
    }

    public void setDatiSpecificiRiscossione(String datiSpecificiRiscossione) {
        this.datiSpecificiRiscossione = datiSpecificiRiscossione;
    }

    public BigDecimal getImporto() {
        return this.importo;
    }

    public void setImporto(BigDecimal importo) {
        this.importo = importo;
    }

    public String getNumeroAccertamento() {
        return this.numeroAccertamento;
    }

    public void setNumeroAccertamento(String numeroAccertamento) {
        this.numeroAccertamento = numeroAccertamento;
    }

    public Integer getProgressivo() {
        return this.progressivo;
    }

    public void setProgressivo(Integer progressivo) {
        this.progressivo = progressivo;
    }

    public String getUtenteUltimaModifica() {
        return this.utenteUltimaModifica;
    }

    public void setUtenteUltimaModifica(String utenteUltimaModifica) {
        this.utenteUltimaModifica = utenteUltimaModifica;
    }

    public EpayTPagamentoSecondario getEpayTPagamentoSecondario() {
        return this.epayTPagamentoSecondario;
    }

    public void setEpayTPagamentoSecondario(EpayTPagamentoSecondario epayTPagamentoSecondario) {
        this.epayTPagamentoSecondario = epayTPagamentoSecondario;
    }

    
    public String getCodiceTributo () {
        return codiceTributo;
    }

    
    public void setCodiceTributo ( String codiceTributo ) {
        this.codiceTributo = codiceTributo;
    }
    

}
