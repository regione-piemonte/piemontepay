/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto;

import static it.csi.epay.epaypaweb.util.Util.quote;

import java.io.Serializable;

public class TestataEsitoPosizioniDebitorieDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private String codEsitoGenerale;
	private String detEsitoGenerale;
	private String idMessaggioFlusso;
	private String codFiscaleEnte;
	private String codVersamento;

	public String getCodEsitoGenerale() {
		return codEsitoGenerale;
	}

	public void setCodEsitoGenerale(String codEsitoGenerale) {
		this.codEsitoGenerale = codEsitoGenerale;
	}

	public String getDetEsitoGenerale() {
		return detEsitoGenerale;
	}

	public void setDetEsitoGenerale(String detEsitoGenerale) {
		this.detEsitoGenerale = detEsitoGenerale;
	}

	public String getIdMessaggioFlusso() {
		return idMessaggioFlusso;
	}

	public void setIdMessaggioFlusso(String idMessaggioFlusso) {
		this.idMessaggioFlusso = idMessaggioFlusso;
	}

	public String getCodFiscaleEnte() {
		return codFiscaleEnte;
	}

	public void setCodFiscaleEnte(String codFiscaleEnte) {
		this.codFiscaleEnte = codFiscaleEnte;
	}

	public String getCodVersamento() {
		return codVersamento;
	}

	public void setCodVersamento(String codVersamento) {
		this.codVersamento = codVersamento;
	}

	@Override
	public String toString() {
		final String COMMA = ", ";
		StringBuilder s = new StringBuilder();
		s.append("{ ");
		s.append("codEsitoGenerale:").append(quote(codEsitoGenerale)).append(COMMA);
		s.append("detEsitoGenerale:").append(quote(detEsitoGenerale)).append(COMMA);
		s.append("idMessaggioFlusso:").append(quote(idMessaggioFlusso)).append(COMMA);
		s.append("codFiscaleEnte:").append(quote(codFiscaleEnte)).append(COMMA);
		s.append("codVersamento:").append(quote(codVersamento));
		s.append(" }");
		return s.toString();
	}

}
