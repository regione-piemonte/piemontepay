/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the epaypa_t_utente database table.
 * 
 */
@Entity
@Table(name="epaypa_t_utente")
@NamedQueries({
	@NamedQuery(
			name = "EpaypaTUtente.findCodUtenteByIdUtente",
			query = "SELECT e.codUtente "
					+ "FROM EpaypaTUtente e "
					+ "WHERE e.idUtente = :idUtente"),
	@NamedQuery(
			name = "EpaypaTUtente.findIdUtenteByCodUtente",
			query = "SELECT e.idUtente "
					+ "FROM EpaypaTUtente e "
					+ "WHERE e.codUtente = :codUtente"),
	@NamedQuery(
			name = "EpaypaTUtente.findOneByCodUtente",
			query = "SELECT e "
					+ "FROM EpaypaTUtente e "
					+ "WHERE e.codUtente = :codUtente"),
	@NamedQuery(
			name = "EpaypaTUtente.findAll",
			query = "SELECT e FROM EpaypaTUtente e")
})
public class EpaypaTUtente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_utente")
	private Long idUtente;

	@Column(name="cod_utente")
	private String codUtente;

	private String nome;

	//uni-directional many-to-many association to EpaypaTProfilo
	@ManyToMany
	@JoinTable(
		name="epaypa_r_utente_profilo"
		, joinColumns={
			@JoinColumn(name="id_utente")
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_profilo")
			}
		)
	private Set<EpaypaTProfilo> epaypaTProfilos;

	//uni-directional many-to-many association to EpaypaTRuolo
	@ManyToMany
	@JoinTable(
		name="epaypa_r_utente_ruolo"
		, joinColumns={
			@JoinColumn(name="id_utente")
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_ruolo")
			}
		)
	private Set<EpaypaTRuolo> epaypaTRuolos;

	//uni-directional many-to-many association to EpaypaTEnte
	@ManyToMany
	@JoinTable(
		name="epaypa_r_utente_ente"
		, joinColumns={
			@JoinColumn(name="id_utente")
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_ente")
			}
		)
	private Set<EpaypaTEnte> epaypaTEntes;

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

	public Set<EpaypaTProfilo> getEpaypaTProfilos() {
		return this.epaypaTProfilos;
	}

	public void setEpaypaTProfilos(Set<EpaypaTProfilo> epaypaTProfilos) {
		this.epaypaTProfilos = epaypaTProfilos;
	}

	public Set<EpaypaTRuolo> getEpaypaTRuolos() {
		return this.epaypaTRuolos;
	}

	public void setEpaypaTRuolos(Set<EpaypaTRuolo> epaypaTRuolos) {
		this.epaypaTRuolos = epaypaTRuolos;
	}

	public Set<EpaypaTEnte> getEpaypaTEntes() {
		return this.epaypaTEntes;
	}

	public void setEpaypaTEntes(Set<EpaypaTEnte> epaypaTEntes) {
		this.epaypaTEntes = epaypaTEntes;
	}

}
