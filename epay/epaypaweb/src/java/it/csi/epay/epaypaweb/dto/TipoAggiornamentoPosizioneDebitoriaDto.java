/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto;

import static it.csi.epay.epaypaweb.util.Util.quote;

import java.io.Serializable;

import it.csi.epay.epaypaweb.enumeration.TipoAggiornamentoPosizioneDebitoriaEnum;

public class TipoAggiornamentoPosizioneDebitoriaDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private TipoAggiornamentoPosizioneDebitoriaEnum tipoAggiornamentoEnum;
	private String descrizione;

	public TipoAggiornamentoPosizioneDebitoriaDto(TipoAggiornamentoPosizioneDebitoriaEnum tipoAggiornamentoEnum) {
		this.tipoAggiornamentoEnum = tipoAggiornamentoEnum;
	}

	public TipoAggiornamentoPosizioneDebitoriaEnum getTipoAggiornamentoEnum() {
		return tipoAggiornamentoEnum;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	@Override
	public String toString() {
		final String COMMA = ", ";
		StringBuilder s = new StringBuilder();
		s.append("{ ");
		s.append("tipoAggiornamentoEnum:").append(tipoAggiornamentoEnum).append(COMMA);
		s.append("descrizione:").append(quote(descrizione));
		s.append(" }");
		return s.toString();
	}

}
