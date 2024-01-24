/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto;

import static it.csi.epay.epaypaweb.util.Util.quote;

import java.io.Serializable;

import it.csi.epay.epaypaweb.enumeration.CampoFlussoEnum;
import it.csi.epay.epaypaweb.enumeration.TipoFormatoFileEnum;

public class ColonnaTemplateDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private String intestazione;
	private int posizioneOrdinale;
	private CampoFlussoEnum campoFlusso;
	private String formatoCSV;
	private String formatoXLSX;

	public String getIntestazione() {
		return intestazione;
	}

	public void setIntestazione(String intestazione) {
		this.intestazione = intestazione;
	}

	public int getPosizioneOrdinale() {
		return posizioneOrdinale;
	}

	public void setPosizioneOrdinale(int posizioneOrdinale) {
		this.posizioneOrdinale = posizioneOrdinale;
	}

	public CampoFlussoEnum getCampoFlusso() {
		return campoFlusso;
	}

	public void setCampoFlusso(CampoFlussoEnum campoFlusso) {
		this.campoFlusso = campoFlusso;
	}

	public String getFormatoCSV() {
		return formatoCSV;
	}

	public void setFormatoCSV(String formatoCSV) {
		this.formatoCSV = formatoCSV;
	}

	public String getFormatoXLSX() {
		return formatoXLSX;
	}

	public void setFormatoXLSX(String formatoXLSX) {
		this.formatoXLSX = formatoXLSX;
	}

	public String getFormato(TipoFormatoFileEnum tipoFormato) {
		String formato = null;
		switch (tipoFormato) {
		case CSV:
			formato = formatoCSV;
			break;
		case XLSX:
			formato = formatoXLSX;
			break;
		}
		return formato;
	}

	@Override
	public String toString() {
		final String COMMA = ", ";
		StringBuilder s = new StringBuilder();
		s.append("{ ");
		s.append("intestazione:").append(quote(intestazione)).append(COMMA);
		s.append("posizioneOrdinale:").append(posizioneOrdinale).append(COMMA);
		s.append("campoFlusso:").append(campoFlusso).append(COMMA);
		s.append("formatoCSV:").append(quote(formatoCSV)).append(COMMA);
		s.append("formatoXLSX:").append(quote(formatoXLSX));
		s.append(" }");
		return s.toString();
	}

}
