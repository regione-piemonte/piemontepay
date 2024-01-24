/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodricweb.security;

import java.io.Serializable;

/**
 * 
 * @author lorenzo.fantini Usata da Iride
 */
public class IdentityDetails implements Serializable {

	private static final long serialVersionUID = -1938371395836781704L;
	private String identity;
	private String codiceUtente;
	private String codiceEnte;
	private String codiceRuolo;

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public String getCodiceUtente() {
		return codiceUtente;
	}

	public void setCodiceUtente(String codiceUtente) {
		this.codiceUtente = codiceUtente;
	}

	public String getCodiceEnte() {
		return codiceEnte;
	}

	public void setCodiceEnte(String codiceEnte) {
		this.codiceEnte = codiceEnte;
	}

	public String getCodiceRuolo() {
		return codiceRuolo;
	}

	public void setCodiceRuolo(String codiceRuolo) {
		this.codiceRuolo = codiceRuolo;
	}

}
