/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the epaypa_t_profilo database table.
 * 
 */
@Entity
@Table(name="epaypa_t_profilo")
@NamedQueries({
	@NamedQuery(
			name = "EpaypaTProfilo.findAllByIdUtenteAndIdEnte",
			query = "SELECT p "
					+ "FROM EpaypaTUtente u, EpaypaTProfilo p "
					+ "JOIN u.epaypaTProfilos rel "
					+ "WHERE u.idUtente = :idUtente "
					+ "AND p.idProfilo = rel.idProfilo "
					+ "AND p.epaypaTEnte.idEnte = :idEnte"),
	@NamedQuery(
			name = "EpaypaTProfilo.findAll",
			query = "SELECT e FROM EpaypaTProfilo e")
})
public class EpaypaTProfilo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_profilo")
	private Integer idProfilo;

	private String descrizione;

	//uni-directional many-to-many association to EpaypaTCodiceVersamento
	@ManyToMany
	@JoinTable(
		name="epaypa_r_profilo_cod_vers"
		, joinColumns={
			@JoinColumn(name="id_profilo")
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_codice_versamento")
			}
		)
	private List<EpaypaTCodiceVersamento> epaypaTCodiceVersamentos;

	//uni-directional many-to-one association to EpaypaTEnte
	@ManyToOne
	@JoinColumn(name="id_ente")
	private EpaypaTEnte epaypaTEnte;

	public EpaypaTProfilo() {
	}

	public Integer getIdProfilo() {
		return this.idProfilo;
	}

	public void setIdProfilo(Integer idProfilo) {
		this.idProfilo = idProfilo;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public List<EpaypaTCodiceVersamento> getEpaypaTCodiceVersamentos() {
		return this.epaypaTCodiceVersamentos;
	}

	public void setEpaypaTCodiceVersamentos(List<EpaypaTCodiceVersamento> epaypaTCodiceVersamentos) {
		this.epaypaTCodiceVersamentos = epaypaTCodiceVersamentos;
	}

	public EpaypaTEnte getEpaypaTEnte() {
		return this.epaypaTEnte;
	}

	public void setEpaypaTEnte(EpaypaTEnte epaypaTEnte) {
		this.epaypaTEnte = epaypaTEnte;
	}

}
