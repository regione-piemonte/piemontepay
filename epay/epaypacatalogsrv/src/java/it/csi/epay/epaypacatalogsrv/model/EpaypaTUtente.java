/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the epaypa_t_utente database table.
 * 
 */
@Entity
@Table(schema="epaypa", name="epaypa_t_utente")
@NamedQuery(name="EpaypaTUtente.findAll", query="SELECT e FROM EpaypaTUtente e")
public class EpaypaTUtente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_utente")
	private Long idUtente;

	@Column(name="cod_utente")
	private String codUtente;

	private String nome;

	public EpaypaTUtente() {
	}

	public Long getIdUtente() {
		return this.idUtente;
	}

	public void setIdUtente(Long idUtente) {
		this.idUtente = idUtente;
	}

	public String getCodUtente() {
		return this.codUtente;
	}

	public void setCodUtente(String codUtente) {
		this.codUtente = codUtente;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "EpaypaTUtente [idUtente=" + idUtente + ", codUtente=" + codUtente + ", nome=" + nome + "]";
	}

}
