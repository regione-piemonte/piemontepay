/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the logging_flusso database table.
 * 
 */
@Entity
@Table(name="logging_flusso")
@NamedQuery(name="LoggingFlusso.findAll", query="SELECT l FROM LoggingFlusso l")
public class LoggingFlusso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="data_ora_invio")
	private Timestamp dataOraInvio;

	private String errori;

	private String esito;

	@Id
	@Column(name="id_flusso")
	private String idFlusso;

	@Column(name="istituto_mittente")
	private String istitutoMittente;

	@Column(name="tipo_flusso")
	private String tipoFlusso;

	private String warning;

	public LoggingFlusso() {
	}

	public Timestamp getDataOraInvio() {
		return this.dataOraInvio;
	}

	public void setDataOraInvio(Timestamp dataOraInvio) {
		this.dataOraInvio = dataOraInvio;
	}

	public String getErrori() {
		return this.errori;
	}

	public void setErrori(String errori) {
		this.errori = errori;
	}

	public String getEsito() {
		return this.esito;
	}

	public void setEsito(String esito) {
		this.esito = esito;
	}

	public String getIdFlusso() {
		return this.idFlusso;
	}

	public void setIdFlusso(String idFlusso) {
		this.idFlusso = idFlusso;
	}

	public String getIstitutoMittente() {
		return this.istitutoMittente;
	}

	public void setIstitutoMittente(String istitutoMittente) {
		this.istitutoMittente = istitutoMittente;
	}

	public String getTipoFlusso() {
		return this.tipoFlusso;
	}

	public void setTipoFlusso(String tipoFlusso) {
		this.tipoFlusso = tipoFlusso;
	}

	public String getWarning() {
		return this.warning;
	}

	public void setWarning(String warning) {
		this.warning = warning;
	}

}
