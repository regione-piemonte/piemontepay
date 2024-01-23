/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dto;

import java.sql.Timestamp;

public class FlussoSingoloPagamento extends FlussoRiversamento  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 733869665822040984L;	

	Integer   id;
	Integer   idFlusso;
	String    iuv;
	String    identificativounivocoriscossione;
	Double   singoloimportopagato;
	String    codiceesitosingolopagamento;
	Timestamp dataesitosingolopagamento;
	Timestamp datainserimento;
	Timestamp datamodifica;
	String    applicationId;
	String    applicationname;
	/**
	 * @return the idFlusso
	 */
	public Integer getIdFlusso() {
		return idFlusso;
	}
	/**
	 * @param idFlusso the idFlusso to set
	 */
	public void setIdFlusso(Integer idFlusso) {
		this.idFlusso = idFlusso;
	}
	/**
	 * @return the iuv
	 */
	public String getIuv() {
		return iuv;
	}
	/**
	 * @param iuv the iuv to set
	 */
	public void setIuv(String iuv) {
		this.iuv = iuv;
	}
	/**
	 * @return the identificativounivocoriscossione
	 */
	public String getIdentificativounivocoriscossione() {
		return identificativounivocoriscossione;
	}
	/**
	 * @param identificativounivocoriscossione the identificativounivocoriscossione to set
	 */
	public void setIdentificativounivocoriscossione(String identificativounivocoriscossione) {
		this.identificativounivocoriscossione = identificativounivocoriscossione;
	}
	/**
	 * @return the singoloimportopagato
	 */
	public Double getSingoloimportopagato() {
		return singoloimportopagato;
	}
	/**
	 * @param singoloimportopagato the singoloimportopagato to set
	 */
	public void setSingoloimportopagato(Double singoloimportopagato) {
		this.singoloimportopagato = singoloimportopagato;
	}
	/**
	 * @return the codiceesitosingolopagamento
	 */
	public String getCodiceesitosingolopagamento() {
		return codiceesitosingolopagamento;
	}
	/**
	 * @param codiceesitosingolopagamento the codiceesitosingolopagamento to set
	 */
	public void setCodiceesitosingolopagamento(String codiceesitosingolopagamento) {
		this.codiceesitosingolopagamento = codiceesitosingolopagamento;
	}
	/**
	 * @return the dataesitosingolopagamento
	 */
	public Timestamp getDataesitosingolopagamento() {
		return dataesitosingolopagamento;
	}
	/**
	 * @param dataesitosingolopagamento the dataesitosingolopagamento to set
	 */
	public void setDataesitosingolopagamento(Timestamp dataesitosingolopagamento) {
		this.dataesitosingolopagamento = dataesitosingolopagamento;
	}
	/**
	 * @return the datainserimento
	 */
	public Timestamp getDatainserimento() {
		return datainserimento;
	}
	/**
	 * @param datainserimento the datainserimento to set
	 */
	public void setDatainserimento(Timestamp datainserimento) {
		this.datainserimento = datainserimento;
	}
	/**
	 * @return the datamodifica
	 */
	public Timestamp getDatamodifica() {
		return datamodifica;
	}
	/**
	 * @param datamodifica the datamodifica to set
	 */
	public void setDatamodifica(Timestamp datamodifica) {
		this.datamodifica = datamodifica;
	}
	/**
	 * @return the applicationId
	 */
	public String getApplicationId() {
		return applicationId;
	}
	/**
	 * @param applicationId the applicationId to set
	 */
	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}
	/**
	 * @return the applicationname
	 */
	public String getApplicationname() {
		return applicationname;
	}
	/**
	 * @param applicationname the applicationname to set
	 */
	public void setApplicationname(String applicationname) {
		this.applicationname = applicationname;
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
	
	
}
