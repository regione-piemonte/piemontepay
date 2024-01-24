/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto;

import static it.csi.epay.epaypaweb.util.Util.quote;

import java.io.Serializable;

/** dto facade <-> business <-> persistence */
public class EsitoPosizioneDebitoriaDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private String idPosizioneDebitoriaEsterna;
	private String iuv; // N.B. opzionale per gli esiti posizioni debitorie inserite, assente per gli esiti posizioni debitorie aggiornate
	private String codAvviso;
	private String codEsito;
	private String detEsito;

	public String getIdPosizioneDebitoriaEsterna() {
		return idPosizioneDebitoriaEsterna;
	}

	public void setIdPosizioneDebitoriaEsterna(String idPosizioneDebitoriaEsterna) {
		this.idPosizioneDebitoriaEsterna = idPosizioneDebitoriaEsterna;
	}

	public String getIUV() {
		return iuv;
	}

	public void setIUV(String iuv) {
		this.iuv = iuv;
	}

	public String getCodAvviso() {
		return codAvviso;
	}

	public void setCodAvviso(String codAvviso) {
		this.codAvviso = codAvviso;
	}

	public String getCodEsito() {
		return codEsito;
	}

	public void setCodEsito(String codEsito) {
		this.codEsito = codEsito;
	}

	public String getDetEsito() {
		return detEsito;
	}

	public void setDetEsito(String detEsito) {
		this.detEsito = detEsito;
	}

	@Override
	public String toString() {
		final String COMMA = ", ";
		StringBuilder s = new StringBuilder();
		s.append("{ ");
		s.append("idPosizioneDebitoriaEsterna:").append(quote(idPosizioneDebitoriaEsterna)).append(COMMA);
		s.append("iuv:").append(quote(iuv)).append(COMMA);
		s.append("codAvviso:").append(quote(codAvviso)).append(COMMA);
		s.append("codEsito:").append(quote(codEsito)).append(COMMA);
		s.append("detEsito:").append(quote(detEsito));
		s.append(" }");
		return s.toString();
	}

}
