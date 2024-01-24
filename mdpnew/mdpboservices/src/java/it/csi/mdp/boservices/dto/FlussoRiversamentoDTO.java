/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.boservices.dto;

import java.io.Serializable;
import java.util.Date;

public class FlussoRiversamentoDTO extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5839189397693709682L;	

	Integer   id;
	String    identificativopsp;
	String    identificativoflusso;
	String    versioneoggetto;
	String    identificativounivocoregolamento;
	String    identificativoistitutomittente;
	String    identificativoistitutoricevente;
	Integer   numerototalepagamenti;
	Integer   importototalepagamenti;
	Date 	  dataoraflusso;
	Date 	  dataregolamento;
	Date 	  datainserimento;
	Date 	  datamodifica;
	String    xmlflusso;
	String    denominazionemittente;
	String    denominazionericevente;
	/**
	 * @return the denominazionemittente
	 */
	public String getDenominazionemittente() {
		return denominazionemittente;
	}
	/**
	 * @param denominazionemittente the denominazionemittente to set
	 */
	public void setDenominazionemittente(String denominazionemittente) {
		this.denominazionemittente = denominazionemittente;
	}
	/**
	 * @return the denominazionericevente
	 */
	public String getDenominazionericevente() {
		return denominazionericevente;
	}
	/**
	 * @param denominazionericevente the denominazionericevente to set
	 */
	public void setDenominazionericevente(String denominazionericevente) {
		this.denominazionericevente = denominazionericevente;
	}
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the identificativopsp
	 */
	public String getIdentificativopsp() {
		return identificativopsp;
	}
	/**
	 * @param identificativopsp the identificativopsp to set
	 */
	public void setIdentificativopsp(String identificativopsp) {
		this.identificativopsp = identificativopsp;
	}
	/**
	 * @return the identificativoflusso
	 */
	public String getIdentificativoflusso() {
		return identificativoflusso;
	}
	/**
	 * @param identificativoflusso the identificativoflusso to set
	 */
	public void setIdentificativoflusso(String identificativoflusso) {
		this.identificativoflusso = identificativoflusso;
	}
	/**
	 * @return the versioneoggetto
	 */
	public String getVersioneoggetto() {
		return versioneoggetto;
	}
	/**
	 * @param versioneoggetto the versioneoggetto to set
	 */
	public void setVersioneoggetto(String versioneoggetto) {
		this.versioneoggetto = versioneoggetto;
	}
	/**
	 * @return the identificativounivocoregolamento
	 */
	public String getIdentificativounivocoregolamento() {
		return identificativounivocoregolamento;
	}
	/**
	 * @param identificativounivocoregolamento the identificativounivocoregolamento to set
	 */
	public void setIdentificativounivocoregolamento(String identificativounivocoregolamento) {
		this.identificativounivocoregolamento = identificativounivocoregolamento;
	}
	/**
	 * @return the identificativoistitutomittente
	 */
	public String getIdentificativoistitutomittente() {
		return identificativoistitutomittente;
	}
	/**
	 * @param identificativoistitutomittente the identificativoistitutomittente to set
	 */
	public void setIdentificativoistitutomittente(String identificativoistitutomittente) {
		this.identificativoistitutomittente = identificativoistitutomittente;
	}
	/**
	 * @return the identificativoistitutoricevente
	 */
	public String getIdentificativoistitutoricevente() {
		return identificativoistitutoricevente;
	}
	/**
	 * @param identificativoistitutoricevente the identificativoistitutoricevente to set
	 */
	public void setIdentificativoistitutoricevente(String identificativoistitutoricevente) {
		this.identificativoistitutoricevente = identificativoistitutoricevente;
	}
	/**
	 * @return the numerototalepagamenti
	 */
	public Integer getNumerototalepagamenti() {
		return numerototalepagamenti;
	}
	/**
	 * @param numerototalepagamenti the numerototalepagamenti to set
	 */
	public void setNumerototalepagamenti(Integer numerototalepagamenti) {
		this.numerototalepagamenti = numerototalepagamenti;
	}
	/**
	 * @return the importototalepagamenti
	 */
	public Integer getImportototalepagamenti() {
		return importototalepagamenti;
	}
	/**
	 * @param importototalepagamenti the importototalepagamenti to set
	 */
	public void setImportototalepagamenti(Integer importototalepagamenti) {
		this.importototalepagamenti = importototalepagamenti;
	}
	/**
	 * @return the dataoraflusso
	 */
	public Date getDataoraflusso() {
		return dataoraflusso;
	}
	/**
	 * @param dataoraflusso the dataoraflusso to set
	 */
	public void setDataoraflusso(Date dataoraflusso) {
		this.dataoraflusso = dataoraflusso;
	}
	/**
	 * @return the dataregolamento
	 */
	public Date getDataregolamento() {
		return dataregolamento;
	}
	/**
	 * @param dataregolamento the dataregolamento to set
	 */
	public void setDataregolamento(Date dataregolamento) {
		this.dataregolamento = dataregolamento;
	}
	/**
	 * @return the datainserimento
	 */
	public Date getDatainserimento() {
		return datainserimento;
	}
	/**
	 * @param datainserimento the datainserimento to set
	 */
	public void setDatainserimento(Date datainserimento) {
		this.datainserimento = datainserimento;
	}
	/**
	 * @return the datamodifica
	 */
	public Date getDatamodifica() {
		return datamodifica;
	}
	/**
	 * @param datamodifica the datamodifica to set
	 */
	public void setDatamodifica(Date datamodifica) {
		this.datamodifica = datamodifica;
	}
	/**
	 * @return the xmlflusso
	 */
	public String getXmlflusso() {
		return xmlflusso;
	}
	/**
	 * @param xmlflusso the xmlflusso to set
	 */
	public void setXmlflusso(String xmlflusso) {
		this.xmlflusso = xmlflusso;
	}
	
	
	
}
