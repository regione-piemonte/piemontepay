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
 * The persistent class for the part_an_comune database table.
 * 
 */
@Entity
@Table(name="part_an_comune")
@NamedQuery(name="PartAnComune.findAll", query="SELECT p FROM PartAnComune p")
public class PartAnComune implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PART_AN_COMUNE_IDCOMUNE_GENERATOR", sequenceName="PART_AN_COMUNE_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PART_AN_COMUNE_IDCOMUNE_GENERATOR")
	@Column(name="id_comune")
	private long idComune;

	private String cap;

	@Column(name="d_start")
	private Timestamp dStart;

	@Column(name="d_stop")
	private Timestamp dStop;

	@Column(name="desc_comune")
	private String descComune;

	@Column(name="desc_provincia")
	private String descProvincia;

	@Column(name="desc_regione")
	private String descRegione;

	@Column(name="id_comune_next")
	private BigDecimal idComuneNext;

	@Column(name="id_comune_prev")
	private BigDecimal idComunePrev;

	@Column(name="istat_comune")
	private String istatComune;

	@Column(name="istat_provincia")
	private String istatProvincia;

	@Column(name="istat_regione")
	private String istatRegione;

	@Column(name="r_status")
	private String rStatus;

	@Column(name="sigla_prov")
	private String siglaProv;

	public PartAnComune() {
	}

	public long getIdComune() {
		return this.idComune;
	}

	public void setIdComune(long idComune) {
		this.idComune = idComune;
	}

	public String getCap() {
		return this.cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
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

	public String getDescComune() {
		return this.descComune;
	}

	public void setDescComune(String descComune) {
		this.descComune = descComune;
	}

	public String getDescProvincia() {
		return this.descProvincia;
	}

	public void setDescProvincia(String descProvincia) {
		this.descProvincia = descProvincia;
	}

	public String getDescRegione() {
		return this.descRegione;
	}

	public void setDescRegione(String descRegione) {
		this.descRegione = descRegione;
	}

	public BigDecimal getIdComuneNext() {
		return this.idComuneNext;
	}

	public void setIdComuneNext(BigDecimal idComuneNext) {
		this.idComuneNext = idComuneNext;
	}

	public BigDecimal getIdComunePrev() {
		return this.idComunePrev;
	}

	public void setIdComunePrev(BigDecimal idComunePrev) {
		this.idComunePrev = idComunePrev;
	}

	public String getIstatComune() {
		return this.istatComune;
	}

	public void setIstatComune(String istatComune) {
		this.istatComune = istatComune;
	}

	public String getIstatProvincia() {
		return this.istatProvincia;
	}

	public void setIstatProvincia(String istatProvincia) {
		this.istatProvincia = istatProvincia;
	}

	public String getIstatRegione() {
		return this.istatRegione;
	}

	public void setIstatRegione(String istatRegione) {
		this.istatRegione = istatRegione;
	}

	public String getRStatus() {
		return this.rStatus;
	}

	public void setRStatus(String rStatus) {
		this.rStatus = rStatus;
	}

	public String getSiglaProv() {
		return this.siglaProv;
	}

	public void setSiglaProv(String siglaProv) {
		this.siglaProv = siglaProv;
	}

}
