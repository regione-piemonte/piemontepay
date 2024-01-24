/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the rt database table.
 * 
 */
@Entity
@NamedQuery(name="Rt.findAll", query="SELECT r FROM Rt r")
public class Rt implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="RT_ID_GENERATOR", sequenceName="RT_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="RT_ID_GENERATOR")
	private Integer id;

	@Column(name="application_id")
	private String applicationId;

	private BigDecimal commissioniapplicatepsp;

	@Temporal(TemporalType.DATE)
	@Column(name="data_invio_fallito")
	private Date dataInvioFallito;

	@Column(name="data_invio_fruitore")
	private Timestamp dataInvioFruitore;

	@Column(name="data_msg_ricevuta")
	private Timestamp dataMsgRicevuta;

	@Column(name="id_esito_pagamento")
	private Integer idEsitoPagamento;

	@Column(name="id_msg_ricevuta")
	private String idMsgRicevuta;

	@Column(name="id_msg_richiesta")
	private String idMsgRichiesta;

	@Column(name="id_rr")
	private Integer idRr;

	@Column(name="insert_date")
	private Timestamp insertDate;

	private String iuv;

	@Column(name="last_update")
	private Timestamp lastUpdate;

	@Column(name="rt_data")
	private byte[] rtData;

	@Column(name="sorgente_invio_fruitore")
	private String sorgenteInvioFruitore;

	@Column(name="stato_invio")
	private BigDecimal statoInvio;

	@Column(name="stato_invio_fruitore")
	private String statoInvioFruitore;

	@Column(name="tipo_firma")
	private String tipoFirma;

	@Column(name="transaction_id")
	private String transactionId;

	public Rt() {
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

	public BigDecimal getCommissioniapplicatepsp() {
		return this.commissioniapplicatepsp;
	}

	public void setCommissioniapplicatepsp(BigDecimal commissioniapplicatepsp) {
		this.commissioniapplicatepsp = commissioniapplicatepsp;
	}

	public Date getDataInvioFallito() {
		return this.dataInvioFallito;
	}

	public void setDataInvioFallito(Date dataInvioFallito) {
		this.dataInvioFallito = dataInvioFallito;
	}

	public Timestamp getDataInvioFruitore() {
		return this.dataInvioFruitore;
	}

	public void setDataInvioFruitore(Timestamp dataInvioFruitore) {
		this.dataInvioFruitore = dataInvioFruitore;
	}

	public Timestamp getDataMsgRicevuta() {
		return this.dataMsgRicevuta;
	}

	public void setDataMsgRicevuta(Timestamp dataMsgRicevuta) {
		this.dataMsgRicevuta = dataMsgRicevuta;
	}

	public Integer getIdEsitoPagamento() {
		return this.idEsitoPagamento;
	}

	public void setIdEsitoPagamento(Integer idEsitoPagamento) {
		this.idEsitoPagamento = idEsitoPagamento;
	}

	public String getIdMsgRicevuta() {
		return this.idMsgRicevuta;
	}

	public void setIdMsgRicevuta(String idMsgRicevuta) {
		this.idMsgRicevuta = idMsgRicevuta;
	}

	public String getIdMsgRichiesta() {
		return this.idMsgRichiesta;
	}

	public void setIdMsgRichiesta(String idMsgRichiesta) {
		this.idMsgRichiesta = idMsgRichiesta;
	}

	public Integer getIdRr() {
		return this.idRr;
	}

	public void setIdRr(Integer idRr) {
		this.idRr = idRr;
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

	public byte[] getRtData() {
		return this.rtData;
	}

	public void setRtData(byte[] rtData) {
		this.rtData = rtData;
	}

	public String getSorgenteInvioFruitore() {
		return this.sorgenteInvioFruitore;
	}

	public void setSorgenteInvioFruitore(String sorgenteInvioFruitore) {
		this.sorgenteInvioFruitore = sorgenteInvioFruitore;
	}

	public BigDecimal getStatoInvio() {
		return this.statoInvio;
	}

	public void setStatoInvio(BigDecimal statoInvio) {
		this.statoInvio = statoInvio;
	}

	public String getStatoInvioFruitore() {
		return this.statoInvioFruitore;
	}

	public void setStatoInvioFruitore(String statoInvioFruitore) {
		this.statoInvioFruitore = statoInvioFruitore;
	}

	public String getTipoFirma() {
		return this.tipoFirma;
	}

	public void setTipoFirma(String tipoFirma) {
		this.tipoFirma = tipoFirma;
	}

	public String getTransactionId() {
		return this.transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

}
