/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the stati_esteri_min database table.
 * 
 */
@Entity
@Table(name="stati_esteri_min")
@NamedQuery(name="StatiEsteriMin.findAll", query="SELECT s FROM StatiEsteriMin s")
public class StatiEsteriMin implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String codice;

	@Column(name="codice_next")
	private String codiceNext;

	@Column(name="codice_prev")
	private String codicePrev;

	private String codrif;

	private String continente;

	@Column(name="d_start")
	private Timestamp dStart;

	@Column(name="d_stop")
	private Timestamp dStop;

	@Column(name="id_stato_ministero")
	private BigDecimal idStatoMinistero;

	@Column(name="r_status")
	private String rStatus;

	private String stato;

	private String territorio;

	public StatiEsteriMin() {
	}

	public String getCodice() {
		return this.codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getCodiceNext() {
		return this.codiceNext;
	}

	public void setCodiceNext(String codiceNext) {
		this.codiceNext = codiceNext;
	}

	public String getCodicePrev() {
		return this.codicePrev;
	}

	public void setCodicePrev(String codicePrev) {
		this.codicePrev = codicePrev;
	}

	public String getCodrif() {
		return this.codrif;
	}

	public void setCodrif(String codrif) {
		this.codrif = codrif;
	}

	public String getContinente() {
		return this.continente;
	}

	public void setContinente(String continente) {
		this.continente = continente;
	}

	public Timestamp getDStart() {
		return this.dStart;
	}

	public void setDStart(Timestamp dStart) {
		this.dStart = dStart;
	}

	public Timestamp getDStop() {
		return this.dStop;
	}

	public void setDStop(Timestamp dStop) {
		this.dStop = dStop;
	}

	public BigDecimal getIdStatoMinistero() {
		return this.idStatoMinistero;
	}

	public void setIdStatoMinistero(BigDecimal idStatoMinistero) {
		this.idStatoMinistero = idStatoMinistero;
	}

	public String getRStatus() {
		return this.rStatus;
	}

	public void setRStatus(String rStatus) {
		this.rStatus = rStatus;
	}

	public String getStato() {
		return this.stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public String getTerritorio() {
		return this.territorio;
	}

	public void setTerritorio(String territorio) {
		this.territorio = territorio;
	}

}
