/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * The persistent class for the epaycat_v_visibilita_utente_tematiche
 * database view.
 *
 */
@Entity
@Table(name = "epaycat_v_visibilita_utente_tematiche")
public class VisibilitaTematica implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private VisibilitaTematicaPK pk;

	@Column(name = "flag_visibilita_totale")
	private Boolean visibilitaTotale;

	@Column(name = "descrizione")
	private String descrizione;

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public VisibilitaTematicaPK getPk() {
		return pk;
	}

	public void setPk(VisibilitaTematicaPK pk) {
		this.pk = pk;
	}

	public Boolean getVisibilitaTotale() {
		return visibilitaTotale;
	}

	public void setVisibilitaTotale(Boolean visibilitaTotale) {
		this.visibilitaTotale = visibilitaTotale;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pk == null) ? 0 : pk.hashCode());
		result = prime * result + ((visibilitaTotale == null) ? 0 : visibilitaTotale.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VisibilitaTematica other = (VisibilitaTematica) obj;
		if (pk == null) {
			if (other.pk != null)
				return false;
		} else if (!pk.equals(other.pk))
			return false;
		if (visibilitaTotale == null) {
			if (other.visibilitaTotale != null)
				return false;
		} else if (!visibilitaTotale.equals(other.visibilitaTotale))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "VisibilitaTematica [pk=" + pk + ", visibilitaTotale=" + visibilitaTotale + "]";
	}

}
