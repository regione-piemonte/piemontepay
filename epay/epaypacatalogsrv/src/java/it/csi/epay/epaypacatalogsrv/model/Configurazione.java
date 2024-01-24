/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the epaycat_d_configurazione database table.
 * 
 */
@Entity
@Table(name="epaycat_d_configurazione")
@NamedQuery(name="Configurazione.findAll", query="SELECT c FROM Configurazione c")
public class Configurazione implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private String codice;

	private String descrizione;

	@Column(name="id_ente")
	private Long idEnte;

	private String valore;

	public Configurazione() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodice() {
		return this.codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Long getIdEnte() {
		return this.idEnte;
	}

	public void setIdEnte(Long idEnte) {
		this.idEnte = idEnte;
	}

	public String getValore() {
		return this.valore;
	}

	public void setValore(String valore) {
		this.valore = valore;
	}

	@Override
	public String toString() {
		return "Configurazione [id=" + id + ", codice=" + codice + ", descrizione=" + descrizione + ", idEnte=" + idEnte
				+ "]";
	}

}
