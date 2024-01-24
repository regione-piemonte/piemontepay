/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.boservices.dto;

import java.util.Date;

public class StatiRptDTO extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8313233669365952586L;
	private Integer idStatiRpt;
	
	/**
	 * @return the idStatiRpt
	 */
	public Integer getIdStatiRpt() {
		return idStatiRpt;
	}

	/**
	 * @param idStatiRpt the idStatiRpt to set
	 */
	public void setIdStatiRpt(Integer idStatiRpt) {
		this.idStatiRpt = idStatiRpt;
	}

	private String descrizione;

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
	
	
}
