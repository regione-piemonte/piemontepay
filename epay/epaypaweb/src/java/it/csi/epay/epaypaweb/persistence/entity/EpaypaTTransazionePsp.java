/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigDecimal;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;


/**
 * The persistent class for the epaypa_t_transazione_psp database table.
 * 
 */
@Entity
@Table(name="epaypa_t_transazione_psp")
@NamedQuery(name="EpaypaTTransazionePsp.findAll", query="SELECT e FROM EpaypaTTransazionePsp e")
public class EpaypaTTransazionePsp implements Serializable {
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator="foreigngen")
    @GenericGenerator(strategy="foreign", name="foreigngen", parameters=@Parameter(name="property", value="epaypaTNotificaPagamento"))
    @Column(name = "id_notifica_pagamento")
	private Long idNotificaPagamento;

    @OneToOne(mappedBy = "epaypaTTransazionePsp")
    private EpaypaTNotificaPagamento epaypaTNotificaPagamento;

	@Column(name="dt_autorizzazione")
	private Timestamp dtAutorizzazione;

	@Column(name="dt_avvio_transazione")
	private Timestamp dtAvvioTransazione;

	@Column(name="id_flusso_rendicontazione")
	private String idFlussoRendicontazione;

	@Column(name="id_psp")
	private String idPsp;

	@Column(name="importo_commissioni")
	private BigDecimal importoCommissioni;

	@Column(name="importo_transato")
	private BigDecimal importoTransato;

	private String iur;

	@Column(name="ragione_sociale_psp")
	private String ragioneSocialePsp;

	@Column(name="tipo_sicurezza")
	private String tipoSicurezza;

	//uni-directional many-to-one association to EpaypaDTipoVersamento
	@ManyToOne
	@JoinColumn(name="id_tipo_versamento")
	private EpaypaDTipoVersamento epaypaDTipoVersamento;

	public EpaypaTTransazionePsp() {
	}

	public Long getIdNotificaPagamento() {
		return this.idNotificaPagamento;
	}

	public void setIdNotificaPagamento(Long idNotificaPagamento) {
		this.idNotificaPagamento = idNotificaPagamento;
	}

	public EpaypaTNotificaPagamento getEpaypaTNotificaPagamento() {
		return epaypaTNotificaPagamento;
	}

	public void setEpaypaTNotificaPagamento(EpaypaTNotificaPagamento epaypaTNotificaPagamento) {
		this.epaypaTNotificaPagamento = epaypaTNotificaPagamento;
	}

	public Timestamp getDtAutorizzazione() {
		return this.dtAutorizzazione;
	}

	public void setDtAutorizzazione(Timestamp dtAutorizzazione) {
		this.dtAutorizzazione = dtAutorizzazione;
	}

	public Timestamp getDtAvvioTransazione() {
		return this.dtAvvioTransazione;
	}

	public void setDtAvvioTransazione(Timestamp dtAvvioTransazione) {
		this.dtAvvioTransazione = dtAvvioTransazione;
	}

	public String getIdFlussoRendicontazione() {
		return this.idFlussoRendicontazione;
	}

	public void setIdFlussoRendicontazione(String idFlussoRendicontazione) {
		this.idFlussoRendicontazione = idFlussoRendicontazione;
	}

	public String getIdPsp() {
		return this.idPsp;
	}

	public void setIdPsp(String idPsp) {
		this.idPsp = idPsp;
	}

	public BigDecimal getImportoCommissioni() {
		return this.importoCommissioni;
	}

	public void setImportoCommissioni(BigDecimal importoCommissioni) {
		this.importoCommissioni = importoCommissioni;
	}

	public BigDecimal getImportoTransato() {
		return this.importoTransato;
	}

	public void setImportoTransato(BigDecimal importoTransato) {
		this.importoTransato = importoTransato;
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

	public String getTipoSicurezza() {
		return this.tipoSicurezza;
	}

	public void setTipoSicurezza(String tipoSicurezza) {
		this.tipoSicurezza = tipoSicurezza;
	}

	public EpaypaDTipoVersamento getEpaypaDTipoVersamento() {
		return this.epaypaDTipoVersamento;
	}

	public void setEpaypaDTipoVersamento(EpaypaDTipoVersamento epaypaDTipoVersamento) {
		this.epaypaDTipoVersamento = epaypaDTipoVersamento;
	}

}
