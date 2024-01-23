/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dto;

import java.io.Serializable;

public class InformativePSPScelto implements Serializable  {	

	private static final long serialVersionUID = -631459055276417638L;

	private Integer idinformativapsp;
	private String identificativoFlusso;
	private String identificativoPSP;
	private String ragioneSociale;
	private String urlInformazioniPSP;
	private Integer stornoPagamento;
	private String identificativoIntermediario;
	private String identificativoCanale;
	private String tipoVersamento;
	private Integer modelloPagamento;
	private Integer priorita;
	private String disponibilitaServizio;
	private String descrizioneServizio;
	private String condizioniEconomicheMassime;
	private String urlInformazioniCanale;
	private String statoinserimento = "NEW";
	private Integer ordinamento;
	private String origine;
	private String gatewayId;
	private String paymentModeId;
	
	public String getIdentificativoFlusso() {
		return identificativoFlusso;
	}
	public void setIdentificativoFlusso(String identificativoFlusso) {
		this.identificativoFlusso = identificativoFlusso;
	}
	public String getIdentificativoPSP() {
		return identificativoPSP;
	}
	public void setIdentificativoPSP(String identificativoPSP) {
		this.identificativoPSP = identificativoPSP;
	}
	public String getRagioneSociale() {
		return ragioneSociale;
	}
	public void setRagioneSociale(String ragioneSociale) {
		this.ragioneSociale = ragioneSociale;
	}
	public String getUrlInformazioniPSP() {
		return urlInformazioniPSP;
	}
	public void setUrlInformazioniPSP(String urlInformazioniPSP) {
		this.urlInformazioniPSP = urlInformazioniPSP;
	}
	public Integer getStornoPagamento() {
		return stornoPagamento;
	}
	public void setStornoPagamento(Integer stornoPagamento) {
		this.stornoPagamento = stornoPagamento;
	}
	public String getIdentificativoIntermediario() {
		return identificativoIntermediario;
	}
	public void setIdentificativoIntermediario(String identificativoIntermediario) {
		this.identificativoIntermediario = identificativoIntermediario;
	}
	public String getIdentificativoCanale() {
		return identificativoCanale;
	}
	public void setIdentificativoCanale(String identificativoCanale) {
		this.identificativoCanale = identificativoCanale;
	}
	public String getTipoVersamento() {
		return tipoVersamento;
	}
	public void setTipoVersamento(String tipoVersamento) {
		this.tipoVersamento = tipoVersamento;
	}
	public Integer getModelloPagamento() {
		return modelloPagamento;
	}
	public void setModelloPagamento(Integer modelloPagamento) {
		this.modelloPagamento = modelloPagamento;
	}
	public Integer getPriorita() {
		return priorita;
	}
	public void setPriorita(Integer priorita) {
		this.priorita = priorita;
	}
	public String getDisponibilitaServizio() {
		return disponibilitaServizio;
	}
	public void setDisponibilitaServizio(String disponibilitaServizio) {
		this.disponibilitaServizio = disponibilitaServizio;
	}
	public String getDescrizioneServizio() {
		return descrizioneServizio;
	}
	public void setDescrizioneServizio(String descrizioneServizio) {
		this.descrizioneServizio = descrizioneServizio;
	}
	public String getCondizioniEconomicheMassime() {
		return condizioniEconomicheMassime;
	}
	public void setCondizioniEconomicheMassime(String condizioniEconomicheMassime) {
		this.condizioniEconomicheMassime = condizioniEconomicheMassime;
	}
	public String getUrlInformazioniCanale() {
		return urlInformazioniCanale;
	}
	public void setUrlInformazioniCanale(String urlInformazioniCanale) {
		this.urlInformazioniCanale = urlInformazioniCanale;
	}
	public String getStatoinserimento() {
		return statoinserimento;
	}
	public void setStatoinserimento(String statoinserimento) {
		this.statoinserimento = statoinserimento;
	}
	public Integer getOrdinamento() {
		return ordinamento;
	}
	public void setOrdinamento(Integer ordinamento) {
		this.ordinamento = ordinamento;
	}
	public String getOrigine() {
		return origine;
	}
	public void setOrigine(String origine) {
		this.origine = origine;
	}
	public String getGatewayId() {
		return gatewayId;
	}
	public void setGatewayId(String gatewayId) {
		this.gatewayId = gatewayId;
	}
	public String getPaymentModeId() {
		return paymentModeId;
	}
	public void setPaymentModeId(String paymentModeId) {
		this.paymentModeId = paymentModeId;
	}
	public Integer getIdinformativapsp() {
		return idinformativapsp;
	}
	public void setIdinformativapsp(Integer idinformativapsp) {
		this.idinformativapsp = idinformativapsp;
	}
	
	
}
