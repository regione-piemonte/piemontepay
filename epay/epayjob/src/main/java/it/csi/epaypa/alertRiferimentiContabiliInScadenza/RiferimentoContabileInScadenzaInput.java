/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epaypa.alertRiferimentiContabiliInScadenza;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;


public class RiferimentoContabileInScadenzaInput implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("codiceFiscaleEnte")
	private String codiceFiscaleEnte;
	
	@JsonProperty("idEnte")
	private String idEnte;
	

	public String getCodiceFiscaleEnte() {
		return codiceFiscaleEnte;
	}


	public void setCodiceFiscaleEnte(String codiceFiscaleEnte) {
		this.codiceFiscaleEnte = codiceFiscaleEnte;
	}


	public String getIdEnte() {
		return idEnte;
	}


	public void setIdEnte(String idEnte) {
		this.idEnte = idEnte;
	}
	
	
	
	
	
	
	


		
	


}
