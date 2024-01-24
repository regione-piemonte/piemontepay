/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the epaycat_t_log_migrazione database table.
 * 
 */
@Entity
@Table(name="epaycat_t_log_migrazione")
@NamedQuery(name="LogMigrazione.findAll", query="SELECT l FROM LogMigrazione l")
public class LogMigrazione implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="codice_entita")
	private String codiceEntita;

	@Column(name="codice_messaggio")
	private String codiceMessaggio;

	@Column(name="data_ora")
	private Timestamp dataOra;

	@Column(name="id_migrazione")
	private String idMigrazione;

	private String messaggio;

	public LogMigrazione() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodiceEntita() {
		return this.codiceEntita;
	}

	public void setCodiceEntita(String codiceEntita) {
		this.codiceEntita = codiceEntita;
	}

	public String getCodiceMessaggio() {
		return this.codiceMessaggio;
	}

	public void setCodiceMessaggio(String codiceMessaggio) {
		this.codiceMessaggio = codiceMessaggio;
	}

	public Timestamp getDataOra() {
		return this.dataOra;
	}

	public void setDataOra(Timestamp dataOra) {
		this.dataOra = dataOra;
	}

	public String getIdMigrazione() {
		return this.idMigrazione;
	}

	public void setIdMigrazione(String idMigrazione) {
		this.idMigrazione = idMigrazione;
	}

	public String getMessaggio() {
		return this.messaggio;
	}

	public void setMessaggio(String messaggio) {
		this.messaggio = messaggio;
	}

	@Override
	public String toString() {
		return "LogMigrazione [id=" + id + "]";
	}

}
