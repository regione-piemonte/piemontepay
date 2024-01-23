/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class Enti implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -6038914740685279736L;
	String enteId;
	String partitaIva;
	String descrizione;
	String attivo;
	/**
	 * @return the enteId
	 */
	public String getEnteId() {
		return enteId;
	}
	/**
	 * @param enteId the enteId to set
	 */
	public void setEnteId(String enteId) {
		this.enteId = enteId;
	}
	/**
	 * @return the partitaIva
	 */
	public String getPartitaIva() {
		return partitaIva;
	}
	/**
	 * @param partitaIva the partitaIva to set
	 */
	public void setPartitaIva(String partitaIva) {
		this.partitaIva = partitaIva;
	}
	/**
	 * @return the descrizione
	 */
	public String getDescrizione() {
		return descrizione;
	}
	/**
	 * @param descrizione the descrizione to set
	 */
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
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
