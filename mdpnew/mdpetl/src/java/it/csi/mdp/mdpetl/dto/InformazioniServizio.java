/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.dto;

import java.io.Serializable;

public class InformazioniServizio extends BaseDTO implements Serializable {

	private static final long serialVersionUID = -560227165395359832L;
	
	private Integer idinformativapsp;
	private String codiceLingua;
	private String descrizioneServizio;
	private String disponibilitaServizio;
	private String limitazioniServzio;
	private String urlInformazioniServizio;
	
	
	public Integer getIdinformativapsp() {
		return idinformativapsp;
	}
	public void setIdinformativapsp(Integer idinformativapsp) {
		this.idinformativapsp = idinformativapsp;
	}
	public String getCodiceLingua() {
		return codiceLingua;
	}
	public void setCodiceLingua(String codiceLingua) {
		this.codiceLingua = codiceLingua;
	}
	public String getDescrizioneServizio() {
		return descrizioneServizio;
	}
	public void setDescrizioneServizio(String descrizioneServizio) {
		this.descrizioneServizio = descrizioneServizio;
	}
	public String getLimitazioniServzio() {
		return limitazioniServzio;
	}
	public void setLimitazioniServzio(String limitazioniServzio) {
		this.limitazioniServzio = limitazioniServzio;
	}
	public String getUrlInformazioniServizio() {
		return urlInformazioniServizio;
	}
	public void setUrlInformazioniServizio(String urlInformazioniServizio) {
		this.urlInformazioniServizio = urlInformazioniServizio;
	}
	public String getDisponibilitaServizio() {
		return disponibilitaServizio;
	}
	public void setDisponibilitaServizio(String disponibilitaServizio) {
		this.disponibilitaServizio = disponibilitaServizio;
	}
	
	
	
}
