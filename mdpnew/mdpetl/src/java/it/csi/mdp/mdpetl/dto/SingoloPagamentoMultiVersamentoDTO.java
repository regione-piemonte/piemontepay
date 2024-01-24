/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.dto;

import java.math.BigDecimal;

public class SingoloPagamentoMultiVersamentoDTO {
	
	private Integer multiId;
	private String applicationId;
	private String transactionId;
	private Integer posizione;
	private String iuv;
	private String modelloPagamento;
	private Integer singoloPagamentiId;
	private BigDecimal importo;
	private BigDecimal commissione;
	private String credenzialiPagatore;
	private String causale;
	private String datiSpecificiRiscossione;
	private Integer annoAccertamento;
	private Integer numeroAccertamento;
	
	
	public Integer getMultiId() {
		return multiId;
	}
	public void setMultiId(Integer multiId) {
		this.multiId = multiId;
	}
	public String getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public Integer getPosizione() {
		return posizione;
	}
	public void setPosizione(Integer posizione) {
		this.posizione = posizione;
	}
	public String getIuv() {
		return iuv;
	}
	public void setIuv(String iuv) {
		this.iuv = iuv;
	}
	public String getModelloPagamento() {
		return modelloPagamento;
	}
	public void setModelloPagamento(String modelloPagamento) {
		this.modelloPagamento = modelloPagamento;
	}
	public Integer getSingoloPagamentiId() {
		return singoloPagamentiId;
	}
	public void setSingoloPagamentiId(Integer singoloPagamentiId) {
		this.singoloPagamentiId = singoloPagamentiId;
	}
	public BigDecimal getImporto() {
		return importo;
	}
	public void setImporto(BigDecimal importo) {
		this.importo = importo;
	}
	public BigDecimal getCommissione() {
		return commissione;
	}
	public void setCommissione(BigDecimal commissione) {
		this.commissione = commissione;
	}
	public String getCredenzialiPagatore() {
		return credenzialiPagatore;
	}
	public void setCredenzialiPagatore(String credenzialiPagatore) {
		this.credenzialiPagatore = credenzialiPagatore;
	}
	public String getCausale() {
		return causale;
	}
	public void setCausale(String causale) {
		this.causale = causale;
	}
	public String getDatiSpecificiRiscossione() {
		return datiSpecificiRiscossione;
	}
	public void setDatiSpecificiRiscossione(String datiSpecificiRiscossione) {
		this.datiSpecificiRiscossione = datiSpecificiRiscossione;
	}
	public Integer getAnnoAccertamento() {
		return annoAccertamento;
	}
	public void setAnnoAccertamento(Integer annoAccertamento) {
		this.annoAccertamento = annoAccertamento;
	}
	public Integer getNumeroAccertamento() {
		return numeroAccertamento;
	}
	public void setNumeroAccertamento(Integer numeroAccertamento) {
		this.numeroAccertamento = numeroAccertamento;
	}
}
