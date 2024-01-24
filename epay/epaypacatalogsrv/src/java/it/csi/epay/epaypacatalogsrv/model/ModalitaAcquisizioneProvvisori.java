/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the epaypacat_d_modalita_acquisizione_provvisori database table.
 *
 */
@Entity
@Table(name="epaycat_d_modalita_acquisizione_provvisori")
@NamedQuery ( name = "ModalitaAcquisizioneProvvisori.findAll", query = "SELECT e FROM ModalitaAcquisizioneProvvisori e" )
public class ModalitaAcquisizioneProvvisori extends AbstractCSILogAuditedParentEntity implements Serializable {
	private static final long serialVersionUID = 1L;

    @Override
    public String getPrimaryKeyRepresentation () {
        return String.valueOf ( id );
    }

	@Id
	private Integer id;

	private String codice;

	private String descrizione;

	public ModalitaAcquisizioneProvvisori() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	@Override
	public String toString() {
		return "ModalitaAcquisizioneProvvisori [id=" + id + ", codice=" + codice + ", descrizione=" + descrizione + "]";
	}

}
