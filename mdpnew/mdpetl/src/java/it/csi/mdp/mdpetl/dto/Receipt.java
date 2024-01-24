/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.dto;

import java.io.Serializable;
import java.sql.Timestamp;


public class Receipt implements Serializable {

	private static final long serialVersionUID = -2672575438405866965L;

	private Integer id;

	private String applicationId;

	private String transactionId;

	private Timestamp insertDate;

	private Timestamp lastUpdate;

	private Timestamp dataMsgRicevuta;

	private String idMsgRicevuta;

	private byte[] rtData;

	private String tipoFirma;

	private String iuv;

	private Integer idEsitoPagamento;

	private String descEsitoPagamento;

	private String idMsgRichiesta;

	private String statoInvioFruitore;

	private Timestamp dataInvioFruitore;

	private String sogenteInvioFruitore;

	private double commissioniApplicatePSP;

	private Integer idRr;

	public Integer getId () {
		return id;
	}

	public void setId ( Integer id ) {
		this.id = id;
	}

	public String getApplicationId () {
		return applicationId;
	}

	public void setApplicationId ( String applicationId ) {
		this.applicationId = applicationId;
	}

	public String getTransactionId () {
		return transactionId;
	}

	public void setTransactionId ( String transactionId ) {
		this.transactionId = transactionId;
	}

	public Timestamp getInsertDate () {
		return insertDate;
	}

	public void setInsertDate ( Timestamp insertDate ) {
		this.insertDate = insertDate;
	}

	public Timestamp getLastUpdate () {
		return lastUpdate;
	}

	public void setLastUpdate ( Timestamp lastUpdate ) {
		this.lastUpdate = lastUpdate;
	}

	public Timestamp getDataMsgRicevuta () {
		return dataMsgRicevuta;
	}

	public void setDataMsgRicevuta ( Timestamp dataMsgRicevuta ) {
		this.dataMsgRicevuta = dataMsgRicevuta;
	}

	public String getIdMsgRicevuta () {
		return idMsgRicevuta;
	}

	public void setIdMsgRicevuta ( String idMsgRicevuta ) {
		this.idMsgRicevuta = idMsgRicevuta;
	}

	public byte[] getRtData () {
		return rtData;
	}

	public void setRtData ( byte[] rtData ) {
		this.rtData = rtData;
	}

	public String getTipoFirma () {
		return tipoFirma;
	}

	public void setTipoFirma ( String tipoFirma ) {
		this.tipoFirma = tipoFirma;
	}

	public String getIuv () {
		return iuv;
	}

	public void setIuv ( String iuv ) {
		this.iuv = iuv;
	}

	public Integer getIdEsitoPagamento () {
		return idEsitoPagamento;
	}

	public void setIdEsitoPagamento ( Integer idEsitoPagamento ) {
		this.idEsitoPagamento = idEsitoPagamento;
	}

	public String getDescEsitoPagamento () {
		return descEsitoPagamento;
	}

	public void setDescEsitoPagamento ( String descEsitoPagamento ) {
		this.descEsitoPagamento = descEsitoPagamento;
	}

	public String getIdMsgRichiesta () {
		return idMsgRichiesta;
	}

	public void setIdMsgRichiesta ( String idMsgRichiesta ) {
		this.idMsgRichiesta = idMsgRichiesta;
	}

	public String getStatoInvioFruitore () {
		return statoInvioFruitore;
	}

	public void setStatoInvioFruitore ( String statoInvioFruitore ) {
		this.statoInvioFruitore = statoInvioFruitore;
	}

	public Timestamp getDataInvioFruitore () {
		return dataInvioFruitore;
	}

	public void setDataInvioFruitore ( Timestamp dataInvioFruitore ) {
		this.dataInvioFruitore = dataInvioFruitore;
	}

	public String getSogenteInvioFruitore () {
		return sogenteInvioFruitore;
	}

	public void setSogenteInvioFruitore ( String sogenteInvioFruitore ) {
		this.sogenteInvioFruitore = sogenteInvioFruitore;
	}

	public double getCommissioniApplicatePSP () {
		return commissioniApplicatePSP;
	}

	public void setCommissioniApplicatePSP ( double commissioniApplicatePSP ) {
		this.commissioniApplicatePSP = commissioniApplicatePSP;
	}

	public Integer getIdRr () {
		return idRr;
	}

	public void setIdRr ( Integer idRr ) {
		this.idRr = idRr;
	}
}
