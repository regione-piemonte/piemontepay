/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the logging_rt database table.
 * 
 */
@Entity
@Table(name="logging_rt")
@NamedQuery(name="LoggingRt.findAll", query="SELECT l FROM LoggingRt l")
public class LoggingRt implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="data_ora_invio")
	private Timestamp dataOraInvio;

	@Id
	private String errori;

	private String esito;

	@Column(name="istituto_mittente")
	private String istitutoMittente;

	private String iuv;

	private String warning;

	public LoggingRt() {
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

	public String getIstitutoMittente() {
		return this.istitutoMittente;
	}

	public void setIstitutoMittente(String istitutoMittente) {
		this.istitutoMittente = istitutoMittente;
	}

	public String getIuv() {
		return this.iuv;
	}

	public void setIuv(String iuv) {
		this.iuv = iuv;
	}

	public String getWarning() {
		return this.warning;
	}

	public void setWarning(String warning) {
		this.warning = warning;
	}

}
