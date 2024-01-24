/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.integration.db.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the epay_d_modalita_ricezione_esito database table.
 * 
 */
@Entity
@Table(name="epay_d_modalita_ricezione_esito")
public class EpayDModalitaRicezioneEsito implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_modalita_ricezione_esito", unique=true, nullable=false)
	private Integer idModalitaRicezioneEsito;

	@Column(nullable=false, length=200)
	private String descrizione;

	public EpayDModalitaRicezioneEsito() {
	}

	public Integer getIdModalitaRicezioneEsito() {
		return this.idModalitaRicezioneEsito;
	}

	public void setIdModalitaRicezioneEsito(Integer idModalitaRicezioneEsito) {
		this.idModalitaRicezioneEsito = idModalitaRicezioneEsito;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

}
