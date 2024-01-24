/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the rpt database table.
 * 
 */
@Entity
@NamedQuery(name="Rpt.findAll", query="SELECT r FROM Rpt r")
public class Rpt implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="RPT_ID_GENERATOR", sequenceName="RPT_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="RPT_ID_GENERATOR")
	private Integer id;

	@Column(name="application_id")
	private String applicationId;

	@Column(name="auth_soggetto")
	private String authSoggetto;

	@Column(name="codice_contesto_pagamento")
	private String codiceContestoPagamento;

	@Column(name="da_inviare")
	private Boolean daInviare;

	@Column(name="data_invio")
	private Timestamp dataInvio;

	@Column(name="data_msg_richiesta")
	private Timestamp dataMsgRichiesta;

	@Column(name="id_canale")
	private String idCanale;

	@Column(name="id_interm_psp")
	private String idIntermPsp;

	@Column(name="id_msg_richiesta")
	private String idMsgRichiesta;

	@Column(name="id_psp")
	private String idPsp;

	@Column(name="id_stati_rpt")
	private Integer idStatiRpt;

	@Column(name="identificativo_dominio")
	private String identificativoDominio;

	@Column(name="identificativo_intermediario_pa")
	private String identificativoIntermediarioPa;

	@Column(name="identificativo_stazione_intermediario_pa")
	private String identificativoStazioneIntermediarioPa;

	@Column(name="insert_date")
	private Timestamp insertDate;

	private String iuv;

	@Column(name="last_update")
	private Timestamp lastUpdate;

	@Column(name="rpt_xml")
	private String rptXml;

	@Column(name="transaction_id")
	private String transactionId;

	public Rpt() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getApplicationId() {
		return this.applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public String getAuthSoggetto() {
		return this.authSoggetto;
	}

	public void setAuthSoggetto(String authSoggetto) {
		this.authSoggetto = authSoggetto;
	}

	public String getCodiceContestoPagamento() {
		return this.codiceContestoPagamento;
	}

	public void setCodiceContestoPagamento(String codiceContestoPagamento) {
		this.codiceContestoPagamento = codiceContestoPagamento;
	}

	public Boolean getDaInviare() {
		return this.daInviare;
	}

	public void setDaInviare(Boolean daInviare) {
		this.daInviare = daInviare;
	}

	public Timestamp getDataInvio() {
		return this.dataInvio;
	}

	public void setDataInvio(Timestamp dataInvio) {
		this.dataInvio = dataInvio;
	}

	public Timestamp getDataMsgRichiesta() {
		return this.dataMsgRichiesta;
	}

	public void setDataMsgRichiesta(Timestamp dataMsgRichiesta) {
		this.dataMsgRichiesta = dataMsgRichiesta;
	}

	public String getIdCanale() {
		return this.idCanale;
	}

	public void setIdCanale(String idCanale) {
		this.idCanale = idCanale;
	}

	public String getIdIntermPsp() {
		return this.idIntermPsp;
	}

	public void setIdIntermPsp(String idIntermPsp) {
		this.idIntermPsp = idIntermPsp;
	}

	public String getIdMsgRichiesta() {
		return this.idMsgRichiesta;
	}

	public void setIdMsgRichiesta(String idMsgRichiesta) {
		this.idMsgRichiesta = idMsgRichiesta;
	}

	public String getIdPsp() {
		return this.idPsp;
	}

	public void setIdPsp(String idPsp) {
		this.idPsp = idPsp;
	}

	public Integer getIdStatiRpt() {
		return this.idStatiRpt;
	}

	public void setIdStatiRpt(Integer idStatiRpt) {
		this.idStatiRpt = idStatiRpt;
	}

	public String getIdentificativoDominio() {
		return this.identificativoDominio;
	}

	public void setIdentificativoDominio(String identificativoDominio) {
		this.identificativoDominio = identificativoDominio;
	}

	public String getIdentificativoIntermediarioPa() {
		return this.identificativoIntermediarioPa;
	}

	public void setIdentificativoIntermediarioPa(String identificativoIntermediarioPa) {
		this.identificativoIntermediarioPa = identificativoIntermediarioPa;
	}

	public String getIdentificativoStazioneIntermediarioPa() {
		return this.identificativoStazioneIntermediarioPa;
	}

	public void setIdentificativoStazioneIntermediarioPa(String identificativoStazioneIntermediarioPa) {
		this.identificativoStazioneIntermediarioPa = identificativoStazioneIntermediarioPa;
	}

	public Timestamp getInsertDate() {
		return this.insertDate;
	}

	public void setInsertDate(Timestamp insertDate) {
		this.insertDate = insertDate;
	}

	public String getIuv() {
		return this.iuv;
	}

	public void setIuv(String iuv) {
		this.iuv = iuv;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getRptXml() {
		return this.rptXml;
	}

	public void setRptXml(String rptXml) {
		this.rptXml = rptXml;
	}

	public String getTransactionId() {
		return this.transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

}
