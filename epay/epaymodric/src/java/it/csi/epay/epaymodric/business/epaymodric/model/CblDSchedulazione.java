/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the cbl_d_schedulazione database table.
 * 
 */
@Entity
@Table(name="cbl_d_schedulazione",schema="epaymodric")
@NamedQuery(name="CblDSchedulazione.findAll", query="SELECT c FROM CblDSchedulazione c")
public class CblDSchedulazione implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CBL_D_SCHEDULAZIONE_ID_GENERATOR", sequenceName="epaymodric.CBL_D_SCHEDULAZIONE_ID_SEQ" ,schema="epaymodric", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CBL_D_SCHEDULAZIONE_ID_GENERATOR")
	private Long id;

	@Column(name="descrizione_periodicita")
	private String descrizionePeriodicita;

	@Column(name="periodicita_schedulazione")
	private BigDecimal periodicitaSchedulazione;

	public CblDSchedulazione() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescrizionePeriodicita() {
		return this.descrizionePeriodicita;
	}

	public void setDescrizionePeriodicita(String descrizionePeriodicita) {
		this.descrizionePeriodicita = descrizionePeriodicita;
	}

	public BigDecimal getPeriodicitaSchedulazione() {
		return this.periodicitaSchedulazione;
	}

	public void setPeriodicitaSchedulazione(BigDecimal periodicitaSchedulazione) {
		this.periodicitaSchedulazione = periodicitaSchedulazione;
	}

}
