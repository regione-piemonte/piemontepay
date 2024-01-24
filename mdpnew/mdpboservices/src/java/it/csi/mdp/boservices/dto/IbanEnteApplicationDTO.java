/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.boservices.dto;

import java.sql.Timestamp;
import java.util.Date;

public class IbanEnteApplicationDTO  extends BaseDto {
	  Integer id;
	  String applicationId ;
	  String idEnte;
	  String tipoversamento;
	  String identificativopsp;
	  String iban;
	  Date dataInizioValidita;
	  Date dataFineValidita;
	  String attivo;
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
	 * @return the idEnte
	 */
	public String getIdEnte() {
		return idEnte;
	}
	/**
	 * @param idEnte the idEnte to set
	 */
	public void setIdEnte(String idEnte) {
		this.idEnte = idEnte;
	}
	/**
	 * @return the tipoversamento
	 */
	public String getTipoversamento() {
		return tipoversamento;
	}
	/**
	 * @param tipoversamento the tipoversamento to set
	 */
	public void setTipoversamento(String tipoversamento) {
		this.tipoversamento = tipoversamento;
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
	 * @return the iban
	 */
	public String getIban() {
		return iban;
	}
	/**
	 * @param iban the iban to set
	 */
	public void setIban(String iban) {
		this.iban = iban;
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
	 * @return the dataFineValidita
	 */
	public Date getDataFineValidita() {
		return dataFineValidita;
	}
	/**
	 * @param dataFineValidita the dataFineValidita to set
	 */
	public void setDataFineValidita(Date dataFineValidita) {
		this.dataFineValidita = dataFineValidita;
	}
	/**
	 * @return the attivo
	 */
	public String getAttivo() {
		return attivo;
	}
	/**
	 * @param attivo the attivo to set
	 */
	public void setAttivo(String attivo) {
		this.attivo = attivo;
	}
	  
	  
	  
}
