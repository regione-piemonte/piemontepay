/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class IbanEnteApplication implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8847987678632810010L;

	  Integer id;
	  String applicationId ;
	  String idEnte;
	  String tipoversamento;
	  String identificativopsp;
	  String iban;
	  Timestamp dataInizioValidita;
	  Timestamp dataFineValidita;
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
	public Timestamp getDataInizioValidita() {
		return dataInizioValidita;
	}
	/**
	 * @param dataInizioValidita the dataInizioValidita to set
	 */
	public void setDataInizioValidita(Timestamp dataInizioValidita) {
		this.dataInizioValidita = dataInizioValidita;
	}
	/**
	 * @return the dataFineValidita
	 */
	public Timestamp getDataFineValidita() {
		return dataFineValidita;
	}
	/**
	 * @param dataFineValidita the dataFineValidita to set
	 */
	public void setDataFineValidita(Timestamp dataFineValidita) {
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
