/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto;

import static it.csi.epay.epaypaweb.util.Util.quote;

import java.io.Serializable;

import it.csi.epay.epaypaweb.enumeration.StatoFlussoEnum;

public class StatoFlussoDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private StatoFlussoEnum statoFlussoEnum;
	private String descrizione;
	private String direzione;

	public StatoFlussoDto(StatoFlussoEnum statoFlussoEnum, String direzione) {
		this.statoFlussoEnum = statoFlussoEnum;
		this.direzione = direzione;
	}

	public StatoFlussoEnum getStatoFlussoEnum() {
		return statoFlussoEnum;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getDirezione() {
		return direzione;
	}

	@Override
	public String toString() {
		final String COMMA = ", ";
		StringBuilder s = new StringBuilder();
		s.append("{ ");
		s.append("statoFlussoEnum:").append(statoFlussoEnum).append(COMMA);
		s.append("descrizione:").append(quote(descrizione)).append(COMMA);
		s.append("direzione:").append(quote(direzione));
		s.append(" }");
		return s.toString();
	}

}
