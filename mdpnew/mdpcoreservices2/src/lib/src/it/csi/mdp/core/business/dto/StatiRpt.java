/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dto;

import java.io.Serializable;

public class StatiRpt implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 843987379743914713L;




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

	private Integer idStatiRpt;
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
