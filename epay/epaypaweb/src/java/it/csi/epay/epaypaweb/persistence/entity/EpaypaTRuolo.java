/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the epaypa_t_ruolo database table.
 * 
 */
@Entity
@Table(name="epaypa_t_ruolo")
@NamedQueries({
	@NamedQuery(
			name = "EpaypaTRuolo.findAllByIdUtenteAndIdEnte",
			query = "SELECT r "
					+ "FROM EpaypaTUtente u, EpaypaTRuolo r "
					+ "JOIN u.epaypaTRuolos rel "
					+ "WHERE u.idUtente = :idUtente "
					+ "AND r.idRuolo = rel.idRuolo "
					+ "AND r.epaypaTEnte.idEnte = :idEnte"),
	@NamedQuery(
			name = "EpaypaTRuolo.findAll",
			query = "SELECT e FROM EpaypaTRuolo e")
})
public class EpaypaTRuolo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_ruolo")
	private Integer idRuolo;

	@Column(name="cod_ruolo")
	private String codRuolo;

	private String descrizione;

	//uni-directional many-to-many association to EpaypaTCdu
	@ManyToMany
	@JoinTable(
		name="epaypa_r_ruolo_cdu"
		, joinColumns={
			@JoinColumn(name="cod_ruolo", referencedColumnName="cod_ruolo")
			}
		, inverseJoinColumns={
			@JoinColumn(name="cod_cdu", referencedColumnName="cod_cdu")
			}
		)
	private List<EpaypaTCdu> epaypaTCdus;

	//uni-directional many-to-one association to EpaypaTEnte
	@ManyToOne
	@JoinColumn(name="id_ente")
	private EpaypaTEnte epaypaTEnte;

	public EpaypaTRuolo() {
	}

	public Integer getIdRuolo() {
		return this.idRuolo;
	}

	public void setIdRuolo(Integer idRuolo) {
		this.idRuolo = idRuolo;
	}

	public String getCodRuolo() {
		return codRuolo;
	}

	public void setCodRuolo(String codRuolo) {
		this.codRuolo = codRuolo;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public List<EpaypaTCdu> getEpaypaTCdus() {
		return this.epaypaTCdus;
	}

	public void setEpaypaTCdus(List<EpaypaTCdu> epaypaTCdus) {
		this.epaypaTCdus = epaypaTCdus;
	}

	public EpaypaTEnte getEpaypaTEnte() {
		return this.epaypaTEnte;
	}

	public void setEpaypaTEnte(EpaypaTEnte epaypaTEnte) {
		this.epaypaTEnte = epaypaTEnte;
	}

}
