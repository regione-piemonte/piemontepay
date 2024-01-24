/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.integration.db.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the epay_t_esiti_ricevuti database table.
 *
 */
@Entity
@Table(name="epay_t_esiti_ricevuti")
public class EpayTEsitiRicevuti implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="EPAY_T_ESITI_RICEVUTI_IDESITO_GENERATOR", allocationSize=1, sequenceName="EPAY_T_ESITI_RICEVUTI_ID_ESITO_SEQ")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EPAY_T_ESITI_RICEVUTI_IDESITO_GENERATOR")
    @Column(name="id_esito", unique=true, nullable=false)
    private Long idEsito;

    @Column(name="cod_esito_pagamento")
    private Integer codEsitoPagamento;

    @Column(name="data_pagamento")
    private Timestamp dataPagamento;

    @Column(name="desc_esito_pagamento", length=255)
    private String descEsitoPagamento;

    @Column(name="id_applicazione", length=30)
    private String idApplicazione;

    @Column(name="id_modalita_ricezione", nullable=false)
    private Integer idModalitaRicezione;

    @Column(name="id_transazione", length=50)
    private String idTransazione;

    @Column(name="identificativo_psp", length=250)
    private String identificativoPsp;

    @Column(precision=10, scale=2)
    private BigDecimal importo;

    @Column(length=35)
    private String iur;

    @Column(name="ragione_sociale_psp", length=250)
    private String ragioneSocialePsp;

    @Column ( name = "iuv" )
    private String iuv;

    @Column ( name = "data_ora_operazione" )
    private Date dataOraOperazione;

    public Date getDataOraOperazione () {
        return dataOraOperazione;
    }

    public void setDataOraOperazione ( Date dataOraOperazione ) {
        this.dataOraOperazione = dataOraOperazione;
    }

    public String getIuv () {
        return iuv;
    }

    public void setIuv ( String iuv ) {
        this.iuv = iuv;
    }

    //bi-directional many-to-one association to EpayDModalitaPagamento
    @ManyToOne
    @JoinColumn(name="id_modalita_pagamento")
    private EpayDModalitaPagamento epayDModalitaPagamento;

    //bi-directional many-to-one association to EpayTRegistroVersamenti
    @ManyToOne
    @JoinColumn(name="id_registro", nullable=false)
    private EpayTRegistroVersamenti epayTRegistroVersamenti;

    //bi-directional many-to-one association to EpayTQuietanzaEsito
    @ManyToOne
    @JoinColumn ( name = "id_quietanza_esito" )
    private EpayTQuietanzaEsito epayTQuietanzaEsito;

    public EpayTQuietanzaEsito getEpayTQuietanzaEsito () {
        return epayTQuietanzaEsito;
    }

    public void setEpayTQuietanzaEsito ( EpayTQuietanzaEsito epayTQuietanzaEsito ) {
        this.epayTQuietanzaEsito = epayTQuietanzaEsito;
    }

    public EpayTEsitiRicevuti() {
    }

    public Long getIdEsito() {
        return this.idEsito;
    }

    public void setIdEsito(Long idEsito) {
        this.idEsito = idEsito;
    }

    public Integer getCodEsitoPagamento() {
        return this.codEsitoPagamento;
    }

    public void setCodEsitoPagamento(Integer codEsitoPagamento) {
        this.codEsitoPagamento = codEsitoPagamento;
    }

    public Timestamp getDataPagamento() {
        return this.dataPagamento;
    }

    public void setDataPagamento(Timestamp dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public String getDescEsitoPagamento() {
        return this.descEsitoPagamento;
    }

    public void setDescEsitoPagamento(String descEsitoPagamento) {
        this.descEsitoPagamento = descEsitoPagamento;
    }

    public String getIdApplicazione() {
        return this.idApplicazione;
    }

    public void setIdApplicazione(String idApplicazione) {
        this.idApplicazione = idApplicazione;
    }

    public Integer getIdModalitaRicezione() {
        return this.idModalitaRicezione;
    }

    public void setIdModalitaRicezione(Integer idModalitaRicezione) {
        this.idModalitaRicezione = idModalitaRicezione;
    }

    public String getIdTransazione() {
        return this.idTransazione;
    }

    public void setIdTransazione(String idTransazione) {
        this.idTransazione = idTransazione;
    }

    public String getIdentificativoPsp() {
        return this.identificativoPsp;
    }

    public void setIdentificativoPsp(String identificativoPsp) {
        this.identificativoPsp = identificativoPsp;
    }

    public BigDecimal getImporto() {
        return this.importo;
    }

    public void setImporto(BigDecimal importo) {
        this.importo = importo;
    }

    public String getIur() {
        return this.iur;
    }

    public void setIur(String iur) {
        this.iur = iur;
    }

    public String getRagioneSocialePsp() {
        return this.ragioneSocialePsp;
    }

    public void setRagioneSocialePsp(String ragioneSocialePsp) {
        this.ragioneSocialePsp = ragioneSocialePsp;
    }

    public EpayDModalitaPagamento getEpayDModalitaPagamento() {
        return this.epayDModalitaPagamento;
    }

    public void setEpayDModalitaPagamento(EpayDModalitaPagamento epayDModalitaPagamento) {
        this.epayDModalitaPagamento = epayDModalitaPagamento;
    }

    public EpayTRegistroVersamenti getEpayTRegistroVersamenti() {
        return this.epayTRegistroVersamenti;
    }

    public void setEpayTRegistroVersamenti(EpayTRegistroVersamenti epayTRegistroVersamenti) {
        this.epayTRegistroVersamenti = epayTRegistroVersamenti;
    }

}
