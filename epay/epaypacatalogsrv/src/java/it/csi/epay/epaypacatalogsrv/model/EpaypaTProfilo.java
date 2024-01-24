/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the epaypa_t_profilo database table.
 * 
 */
@Entity
@Table(schema="epaypa", name="epaypa_t_profilo")
@NamedQuery(name="EpaypaTProfilo.findAll", query="SELECT e FROM EpaypaTProfilo e")
public class EpaypaTProfilo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_profilo")
	private Integer idProfilo;

	private String descrizione;

	@Column(name="id_ente")
	private Integer idEnte;

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

	public Integer getIdEnte() {
		return this.idEnte;
	}

	public void setIdEnte(Integer idEnte) {
		this.idEnte = idEnte;
	}

}
