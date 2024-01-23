/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.paymentmanager.local;

import java.io.Serializable;
import java.util.Date;

public class AppGatewayInformativa extends AppGateway implements Serializable {

	private static final long serialVersionUID = 1L;
	
	Integer idInformativaPSP;
	String identificativoFlusso;
	String identificativoPSP;
	String ragioneSociale;
	String dataPubblicazione;
	String dataInizioValidita;
	String urlInformazioniPSP;
	Integer stornoPagamento;
	String identificativoIntermediario;
	String identificativoCanale;
	String tipoVersamento;
	Integer modelloPagamento;
	Integer priorita;
	String disponibilitaServizio;
	String descrizioneServizio;
	String condizioniEconomicheMassime;
	Date datainserimento = new Date();
	String statoinserimento = "NEW";
	Integer ordinamento;
	String origine;
	
	
	
	public Integer getIdInformativaPSP() {
		return idInformativaPSP;
	}
	public void setIdInformativaPSP (Integer id) {
		this.idInformativaPSP = id;
	}
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
	public String getDataPubblicazione() {
		return dataPubblicazione;
	}
	public void setDataPubblicazione(String dataPubblicazione) {
		this.dataPubblicazione = dataPubblicazione;
	}
	public String getDataInizioValidita() {
		return dataInizioValidita;
	}
	public void setDataInizioValidita(String dataInizioValidita) {
		this.dataInizioValidita = dataInizioValidita;
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
	public Date getDatainserimento() {
		return datainserimento;
	}
	public void setDatainserimento(Date datainserimento) {
		this.datainserimento = datainserimento;
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
}
