/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.integration.db.entities;

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
    @SequenceGenerator ( name = "EPAY_T_PAGAMENTO_SECONDARIO_COMPONENTI_ID_GENERATOR", allocationSize = 1, sequenceName = "EPAY_T_PAGAMENTO_SECONDARIO_COMPONENTI_ID_SEQ" )
    @GeneratedValue ( strategy = GenerationType.SEQUENCE, generator = "EPAY_T_PAGAMENTO_SECONDARIO_COMPONENTI_ID_GENERATOR" )
    @Column ( name = "id_componente_secondaria", unique = true, nullable = false )
    private Long idComponente; //Chiamato cosi' per fare un unico mapping con la classe PagamentoComponenti

    @Column(name="anno_accertamento")
    private Integer annoAccertamento;

    @Column ( nullable = false, length = 140 )
    private String causale;

    @Column ( name = "dati_specifici_riscossione", nullable = false, length = 140 )
    private String datiSpecificiRiscossione;

    @Column ( nullable = false, precision = 10, scale = 2 )
    private BigDecimal importo;

    @Column ( name = "numero_accertamento", nullable = false, length = 35 )
    private String numeroAccertamento;

    @Column ( nullable = false )
    private Integer progressivo;

    @Column ( name = "utente_ultima_modifica", nullable = false, length = 100 )
    private String utenteUltimaModifica;
    
    @Column( name = "codice_tributo")
    private String codiceTributo;

    //bi-directional many-to-one association to EpayTPagamentoSecondario
    @ManyToOne
    @JoinColumn(name="id_pagamento_secondario")
    private EpayTPagamentoSecondario epayTPagamentoSecondario;

    public EpayTPagamentoSecondarioComponenti() {
    }

    public Long getIdComponente () {
        return this.idComponente;
    }

    public void setIdComponente ( Long idComponente ) {
        this.idComponente = idComponente;
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
