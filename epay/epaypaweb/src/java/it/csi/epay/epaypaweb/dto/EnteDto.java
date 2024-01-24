/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto;

import java.io.Serializable;

import static it.csi.epay.epaypaweb.util.Util.quote;

public class EnteDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private final Integer id;
	private final String codFiscale;
	private String denominazione;
	private String email;

	public EnteDto(Integer id, String codFiscale) {
		this.id = id;
		this.codFiscale = codFiscale;
	}

    public EnteDto ( Integer id, String codFiscale, String denominazione, String email ) {
        super ();
        this.id = id;
        this.codFiscale = codFiscale;
        this.denominazione = denominazione;
        this.email = email;
    }

    public Integer getId () {
		return id;
	}

	public String getCodFiscale() {
		return codFiscale;
	}

	public String getDenominazione() {
		return denominazione;
	}

	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		final String COMMA = ", ";
		return "{ " +
						"id:" + id + COMMA +
						"codFiscale:" + quote ( codFiscale ) + COMMA +
						"denominazione:" + quote ( denominazione ) + COMMA +
						"email:" + quote ( email ) +
						" }";
	}

}
