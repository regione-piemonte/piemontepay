/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.boservices.dto;

import java.util.Date;

public class InformativePSPDTO extends BaseDto {


	/**
	 * 
	 */
	private static final long serialVersionUID = 4132879767711943113L;
	Integer idinformativapsp;
	String identificativoFlusso;
	String identificativoPSP;
	String ragioneSociale;
	Date dataPubblicazione;
	Date dataInizioValidita;
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
	String urlInformazioniCanale;
	Date datainserimento;
	String statoinserimento;
	Integer ordinamento;
	String origine;

	/**
	 * @return the identificativoFlusso
	 */
	public String getIdentificativoFlusso() {
		return identificativoFlusso;
	}
	/**
	 * @param identificativoFlusso the identificativoFlusso to set
	 */
	public void setIdentificativoFlusso(String identificativoFlusso) {
		this.identificativoFlusso = identificativoFlusso;
	}
	/**
	 * @return the identificativoPSP
	 */
	public String getIdentificativoPSP() {
		return identificativoPSP;
	}
	/**
	 * @param identificativoPSP the identificativoPSP to set
	 */
	public void setIdentificativoPSP(String identificativoPSP) {
		this.identificativoPSP = identificativoPSP;
	}
	/**
	 * @return the ragioneSociale
	 */
	public String getRagioneSociale() {
		return ragioneSociale;
	}
	/**
	 * @param ragioneSociale the ragioneSociale to set
	 */
	public void setRagioneSociale(String ragioneSociale) {
		this.ragioneSociale = ragioneSociale;
	}
	/**
	 * @return the dataPubblicazione
	 */
	public Date getDataPubblicazione() {
		return dataPubblicazione;
	}
	/**
	 * @param dataPubblicazione the dataPubblicazione to set
	 */
	public void setDataPubblicazione(Date dataPubblicazione) {
		this.dataPubblicazione = dataPubblicazione;
	}
	/**
	 * @return the dataInizioValidita
	 */
	public Date getDataInizioValidita() {
		return dataInizioValidita;
	}
	/**
	 * @param dataInizioValidita the dataInizioValidita to set
	 */
	public void setDataInizioValidita(Date dataInizioValidita) {
		this.dataInizioValidita = dataInizioValidita;
	}
	/**
	 * @return the urlInformazioniPSP
	 */
	public String getUrlInformazioniPSP() {
		return urlInformazioniPSP;
	}
	/**
	 * @param urlInformazioniPSP the urlInformazioniPSP to set
	 */
	public void setUrlInformazioniPSP(String urlInformazioniPSP) {
		this.urlInformazioniPSP = urlInformazioniPSP;
	}
	/**
	 * @return the stornoPagamento
	 */
	public Integer getStornoPagamento() {
		return stornoPagamento;
	}
	/**
	 * @param stornoPagamento the stornoPagamento to set
	 */
	public void setStornoPagamento(Integer stornoPagamento) {
		this.stornoPagamento = stornoPagamento;
	}
	/**
	 * @return the identificativoIntermediario
	 */
	public String getIdentificativoIntermediario() {
		return identificativoIntermediario;
	}
	/**
	 * @param identificativoIntermediario the identificativoIntermediario to set
	 */
	public void setIdentificativoIntermediario(String identificativoIntermediario) {
		this.identificativoIntermediario = identificativoIntermediario;
	}
	/**
	 * @return the identificativoCanale
	 */
	public String getIdentificativoCanale() {
		return identificativoCanale;
	}
	/**
	 * @param identificativoCanale the identificativoCanale to set
	 */
	public void setIdentificativoCanale(String identificativoCanale) {
		this.identificativoCanale = identificativoCanale;
	}
	/**
	 * @return the tipoVersamento
	 */
	public String getTipoVersamento() {
		return tipoVersamento;
	}
	/**
	 * @param tipoVersamento the tipoVersamento to set
	 */
	public void setTipoVersamento(String tipoVersamento) {
		this.tipoVersamento = tipoVersamento;
	}
	/**
	 * @return the modelloPagamento
	 */
	public Integer getModelloPagamento() {
		return modelloPagamento;
	}
	/**
	 * @param modelloPagamento the modelloPagamento to set
	 */
	public void setModelloPagamento(Integer modelloPagamento) {
		this.modelloPagamento = modelloPagamento;
	}
	/**
	 * @return the priorita
	 */
	public Integer getPriorita() {
		return priorita;
	}
	/**
	 * @param priorita the priorita to set
	 */
	public void setPriorita(Integer priorita) {
		this.priorita = priorita;
	}
	/**
	 * @return the disponibilitaServizio
	 */
	public String getDisponibilitaServizio() {
		return disponibilitaServizio;
	}
	/**
	 * @param disponibilitaServizio the disponibilitaServizio to set
	 */
	public void setDisponibilitaServizio(String disponibilitaServizio) {
		this.disponibilitaServizio = disponibilitaServizio;
	}
	/**
	 * @return the descrizioneServizio
	 */
	public String getDescrizioneServizio() {
		return descrizioneServizio;
	}
	/**
	 * @param descrizioneServizio the descrizioneServizio to set
	 */
	public void setDescrizioneServizio(String descrizioneServizio) {
		this.descrizioneServizio = descrizioneServizio;
	}
	/**
	 * @return the condizioniEconomicheMassime
	 */
	public String getCondizioniEconomicheMassime() {
		return condizioniEconomicheMassime;
	}
	/**
	 * @param condizioniEconomicheMassime the condizioniEconomicheMassime to set
	 */
	public void setCondizioniEconomicheMassime(String condizioniEconomicheMassime) {
		this.condizioniEconomicheMassime = condizioniEconomicheMassime;
	}
	/**
	 * @return the urlInformazioniCanale
	 */
	public String getUrlInformazioniCanale() {  
		return urlInformazioniCanale;
	}
	/**
	 * @param urlInformazioniCanale the urlInformazioniCanale to set
	 */
	public void setUrlInformazioniCanale(String urlInformazioniCanale) {
		this.urlInformazioniCanale = urlInformazioniCanale;
	}
	/**
	 * @return the dataInserimento
	 */
	public Date getDatainserimento() {
		return datainserimento;
	}
	/**
	 * @param dataInserimento the dataInserimento to set
	 */
	public void setDatainserimento(Date datainserimento) {
		this.datainserimento = datainserimento;
	}
	/**
	 * @return the idinformativapsp
	 */
	public Integer getIdinformativapsp() {
		return idinformativapsp;
	}
	/**
	 * @param idinformativapsp the idinformativapsp to set
	 */
	public void setIdinformativapsp(Integer idinformativapsp) {
		this.idinformativapsp = idinformativapsp;
	}
	/**
	 * @return the statoinserimento
	 */
	public String getStatoinserimento() {
		return statoinserimento;
	}
	/**
	 * @param statoinserimento the statoinserimento to set
	 */
	public void setStatoinserimento(String statoinserimento) {
		this.statoinserimento = statoinserimento;
	}
	/**
	 * @return the ordinamento
	 */
	public Integer getOrdinamento() {
		return ordinamento;
	}
	/**
	 * @param ordinamento the ordinamento to set
	 */
	public void setOrdinamento(Integer ordinamento) {
		this.ordinamento = ordinamento;
	}
	/**
	 * @return the origine
	 */
	public String getOrigine() {
		return origine;
	}
	/**
	 * @param origine the origine to set
	 */
	public void setOrigine(String origine) {
		this.origine = origine;
	}
	
	
}
