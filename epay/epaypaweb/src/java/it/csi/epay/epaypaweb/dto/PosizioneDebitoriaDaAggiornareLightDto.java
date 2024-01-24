/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto;

import static it.csi.epay.epaypaweb.util.Util.quote;

import java.io.Serializable;

/** dto facade <-> business <-> persistence */
public class PosizioneDebitoriaDaAggiornareLightDto extends ElementoFlussoDto implements Serializable {
	private static final long serialVersionUID = 1L;

	// dati della posizione debitoria da aggiornare light
	private TipoAggiornamentoPosizioneDebitoriaDto tipoAggiornamento;
	private String idPosizioneDebitoriaEsterna;
	private String motivazione;
	private String codAvviso;
	private String codEsito;
	private String detEsito;

	public PosizioneDebitoriaDaAggiornareLightDto() {
		super();
	}

	public PosizioneDebitoriaDaAggiornareLightDto(Long id) {
		super(id);
	}

	public TipoAggiornamentoPosizioneDebitoriaDto getTipoAggiornamento() {
		return tipoAggiornamento;
	}

	public void setTipoAggiornamento(TipoAggiornamentoPosizioneDebitoriaDto tipoAggiornamento) {
		this.tipoAggiornamento = tipoAggiornamento;
	}

	public String getIdPosizioneDebitoriaEsterna() {
		return idPosizioneDebitoriaEsterna;
	}

	public void setIdPosizioneDebitoriaEsterna(String idPosizioneDebitoriaEsterna) {
		this.idPosizioneDebitoriaEsterna = idPosizioneDebitoriaEsterna;
	}

	public String getMotivazione() {
		return motivazione;
	}

	public void setMotivazione(String motivazione) {
		this.motivazione = motivazione;
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

	public String getEsito() {
		StringBuilder s = new StringBuilder();
		if (codEsito != null)
			s.append(codEsito);
		s.append(" ");
		if (detEsito != null)
			s.append(detEsito);
		return s.toString().trim();
	}

	public String toSuperString() {
		final String COMMA = ", ";
		StringBuilder s = new StringBuilder();
		s.append(super.toSuperString()).append(COMMA);
		s.append("tipoAggiornamento:").append(tipoAggiornamento).append(COMMA);
		s.append("idPosizioneDebitoriaEsterna:").append(quote(idPosizioneDebitoriaEsterna)).append(COMMA);
		s.append("motivazione:").append(quote(motivazione)).append(COMMA);
		s.append("codAvviso:").append(quote(codAvviso)).append(COMMA);
		s.append("codEsito:").append(quote(codEsito)).append(COMMA);
		s.append("detEsito:").append(quote(detEsito));
		return s.toString();
	}

}
