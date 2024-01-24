/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.integration.db.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the epay_t_pagamento_secondario database table.
 *
 */
@Entity
@Table(name="epay_t_pagamento_secondario")
@NamedQuery(name="EpayTPagamentoSecondario.findAll", query="SELECT e FROM EpayTPagamentoSecondario e")
public class EpayTPagamentoSecondario implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="EPAY_T_PAGAMENTO_SECONDARIO_ID_GENERATOR", allocationSize = 1, sequenceName="EPAY_T_PAGAMENTO_SECONDARIO_ID_SEQ")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EPAY_T_PAGAMENTO_SECONDARIO_ID_GENERATOR")
    @Column ( name = "id_pagamento_secondario", unique = true, nullable = false )
    private Long idPagamentoSecondario;

    private String causale;

    @Column(name="importo_complessivo")
    private BigDecimal importoComplessivo;

    //bi-directional many-to-one association to EpayTPagamentoSecondarioComponenti
    @OneToMany ( mappedBy = "epayTPagamentoSecondario" )
    private List<EpayTPagamentoSecondarioComponenti> epayTPagamentoSecondarioComponentis;

    //bi-directional many-to-one association to EpayTPagamento
    @ManyToOne
    @JoinColumn(name="id_pagamento")
    private EpayTPagamento epayTPagamento;

    //bi-directional many-to-one association to EpayTTipoPagamento
    @ManyToOne
    @JoinColumn(name="id_tipo_pagamento")
    private EpayTTipoPagamento epayTTipoPagamento;

    @Column ( name = "identificativo_dominio" )
    private String identificativoDominio;

    public EpayTPagamentoSecondario() {
    }

    public Long getIdPagamentoSecondario () {
        return this.idPagamentoSecondario;
    }

    public void setIdPagamentoSecondario ( Long idPagamentoSecondario ) {
        this.idPagamentoSecondario = idPagamentoSecondario;
    }

    public String getCausale() {
        return this.causale;
    }

    public void setCausale(String causale) {
        this.causale = causale;
    }

    public BigDecimal getImportoComplessivo() {
        return this.importoComplessivo;
    }

    public void setImportoComplessivo(BigDecimal importoComplessivo) {
        this.importoComplessivo = importoComplessivo;
    }

    public List<EpayTPagamentoSecondarioComponenti> getEpayTPagamentoSecondarioComponentis () {
        return this.epayTPagamentoSecondarioComponentis;
    }

    public void setEpayTPagamentoSecondarioComponentis ( List<EpayTPagamentoSecondarioComponenti> epayTPagamentoSecondarioComponentis ) {
        this.epayTPagamentoSecondarioComponentis = epayTPagamentoSecondarioComponentis;
    }

    public EpayTPagamentoSecondarioComponenti addEpayTPagamentoSecondarioComponenti ( EpayTPagamentoSecondarioComponenti epayTPagamentoSecondarioComponenti ) {
        getEpayTPagamentoSecondarioComponentis ().add ( epayTPagamentoSecondarioComponenti );
        epayTPagamentoSecondarioComponenti.setEpayTPagamentoSecondario ( this );

        return epayTPagamentoSecondarioComponenti;
    }

    public EpayTPagamentoSecondarioComponenti
    removeEpayTPagamentoSecondarioComponenti ( EpayTPagamentoSecondarioComponenti epayTPagamentoSecondarioComponenti ) {
        getEpayTPagamentoSecondarioComponentis ().remove ( epayTPagamentoSecondarioComponenti );
        epayTPagamentoSecondarioComponenti.setEpayTPagamentoSecondario ( null );

        return epayTPagamentoSecondarioComponenti;
    }

    public EpayTPagamento getEpayTPagamento() {
        return this.epayTPagamento;
    }

    public void setEpayTPagamento(EpayTPagamento epayTPagamento) {
        this.epayTPagamento = epayTPagamento;
    }

    public EpayTTipoPagamento getEpayTTipoPagamento() {
        return this.epayTTipoPagamento;
    }

    public void setEpayTTipoPagamento(EpayTTipoPagamento epayTTipoPagamento) {
        this.epayTTipoPagamento = epayTTipoPagamento;
    }
    public String getIdentificativoDominio () {
        return identificativoDominio;
    }

    public void setIdentificativoDominio ( String identificativoDominio ) {
        this.identificativoDominio = identificativoDominio;
    }


}
