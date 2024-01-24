/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.model;

import java.io.Serializable;

public class AnagraficaTemp implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long idAnagrafica;
	private String codiceFiscale;

	
	/**
	 * @return the idAnagrafica
	 */
	public Long getIdAnagrafica() {
		return idAnagrafica;
	}
	/**
	 * @param idAnagrafica the idAnagrafica to set
	 */
	public void setIdAnagrafica(Long idAnagrafica) {
		this.idAnagrafica = idAnagrafica;
	}
	/**
	 * @return the codiceFiscale
	 */
	public String getCodiceFiscale() {
		return codiceFiscale;
	}
	/**
	 * @param codiceFiscale the codiceFiscale to set
	 */
	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}
}

