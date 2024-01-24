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
 * The persistent class for the cbl_d_modalita_provvisori database table.
 * 
 */
@Entity
@Table(name="cbl_d_modalita_provvisori",schema="epaymodric")
@NamedQuery(name="CblDModalitaProvvisori.findAll", query="SELECT c FROM CblDModalitaProvvisori c")
public class CblDModalitaProvvisori implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CBL_D_MODALITA_PROVVISORI_ID_GENERATOR", sequenceName="epaymodric.CBL_D_MODALITA_PROVVISORI_ID_SEQ" ,schema="epaymodric", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CBL_D_MODALITA_PROVVISORI_ID_GENERATOR")
	private Long id;

	@Column(name="descrizione_modalita")
	private String descrizioneModalita;

	@Column(name="modalita_acquisizione")
	private BigDecimal modalitaAcquisizione;

	public CblDModalitaProvvisori() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescrizioneModalita() {
		return this.descrizioneModalita;
	}

	public void setDescrizioneModalita(String descrizioneModalita) {
		this.descrizioneModalita = descrizioneModalita;
	}

	public BigDecimal getModalitaAcquisizione() {
		return this.modalitaAcquisizione;
	}

	public void setModalitaAcquisizione(BigDecimal modalitaAcquisizione) {
		this.modalitaAcquisizione = modalitaAcquisizione;
	}

}
