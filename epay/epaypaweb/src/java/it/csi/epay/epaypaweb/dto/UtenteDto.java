/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto;

import static it.csi.epay.epaypaweb.util.Util.quote;

import java.io.Serializable;

public class UtenteDto implements Serializable {
	private static final long serialVersionUID = 1L;

    protected Long id;

    protected String cod;

    protected String nome;

	public UtenteDto(Long id, String cod) {
		this.id = id;
		this.cod = cod;
	}

	public Long getId() {
		return id;
	}

	public String getCod() {
		return cod;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

    // L'implementazione di getNomeCompleto per UtenteCatalogDto, che estende,
    // riferisce ad un campo differente. tengo l'implementazione nel padre in modo
    // da avere un fallback in caso debba mostrare il nome utente anche prima
    // di aver completato il processo di login e profilazione
    public String getNomeCompleto () {
        return nome;
    }

    @Override
	public String toString() {
		final String COMMA = ", ";
		StringBuilder s = new StringBuilder();
		s.append("{ ");
		s.append("id:").append(id).append(COMMA);
		s.append("cod:").append(quote(cod)).append(COMMA);
		s.append("nome:").append(quote(nome)).append(COMMA);
		s.append(" }");
		return s.toString();
	}

}
