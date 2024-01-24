/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the epaypa_t_ruolo database table.
 * 
 */
@Entity
@Table(schema="epaypa", name="epaypa_t_ruolo")
@NamedQuery(name="EpaypaTRuolo.findAll", query="SELECT e FROM EpaypaTRuolo e")
public class EpaypaTRuolo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_ruolo")
	private Integer idRuolo;

	@Column(name="cod_ruolo")
	private String codRuolo;

	private String descrizione;

	@Column(name="id_ente")
	private Integer idEnte;

	public EpaypaTRuolo() {
	}

	public Integer getIdRuolo() {
		return this.idRuolo;
	}

	public void setIdRuolo(Integer idRuolo) {
		this.idRuolo = idRuolo;
	}

	public String getCodRuolo() {
		return this.codRuolo;
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

	public Integer getIdEnte() {
		return this.idEnte;
	}

	public void setIdEnte(Integer idEnte) {
		this.idEnte = idEnte;
	}

}
