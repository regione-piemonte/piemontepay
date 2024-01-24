/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto;

import static it.csi.epay.epaypaweb.util.Util.quote;

import java.io.Serializable;
import java.util.List;

import it.csi.epay.epaypaweb.enumeration.TipoFormatoFileEnum;

public class TemplateDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private String nome;
	private String descrizione;
	private List<ColonnaTemplateDto> colonneTemplate;
	private TipoFormatoFileEnum tipoFormato;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public List<ColonnaTemplateDto> getColonneTemplate() {
		return colonneTemplate;
	}

	public void setColonneTemplate(List<ColonnaTemplateDto> colonneTemplate) {
		this.colonneTemplate = colonneTemplate;
	}

	public TipoFormatoFileEnum getTipoFormato() {
		return tipoFormato;
	}

	public void setTipoFormato(TipoFormatoFileEnum tipoFormato) {
		this.tipoFormato = tipoFormato;
	}

	@Override
	public String toString() {
		final String COMMA = ", ";
		StringBuilder s = new StringBuilder();
		s.append("{ ");
		s.append("nome:").append(quote(nome)).append(COMMA);
		s.append("descrizione:").append(quote(descrizione)).append(COMMA);
		s.append("colonneTemplate:").append(colonneTemplate).append(COMMA);
		s.append("tipoFormato:").append(tipoFormato);
		s.append(" }");
		return s.toString();
	}

}
