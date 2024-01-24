/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.model;

import java.io.Serializable;
import javax.persistence.*;

@Embeddable
public class VisibilitaCodiceVersamentoPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name = "id_utente")
	private Long idUtente;

	@Column(name = "id_ente")
	private Long idEnte;

	@Column(name = "cv_id")
	private Long id;

	@Column(name = "livello")
	private Integer livello;

	@Column(name = "causa_visibilita")
	private String causaVisibilita;

	public Long getIdUtente() {
		return idUtente;
	}

	public void setIdUtente(Long idUtente) {
		this.idUtente = idUtente;
	}

	public Long getIdEnte() {
		return idEnte;
	}

	public void setIdEnte(Long idEnte) {
		this.idEnte = idEnte;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getLivello() {
		return livello;
	}

	public void setLivello(Integer livello) {
		this.livello = livello;
	}

	public String getCausaVisibilita() {
		return causaVisibilita;
	}

	public void setCausaVisibilita(String causaVisibilita) {
		this.causaVisibilita = causaVisibilita;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((causaVisibilita == null) ? 0 : causaVisibilita.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((idEnte == null) ? 0 : idEnte.hashCode());
		result = prime * result + ((idUtente == null) ? 0 : idUtente.hashCode());
		result = prime * result + ((livello == null) ? 0 : livello.hashCode());
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
		VisibilitaCodiceVersamentoPK other = (VisibilitaCodiceVersamentoPK) obj;
		if (causaVisibilita == null) {
			if (other.causaVisibilita != null)
				return false;
		} else if (!causaVisibilita.equals(other.causaVisibilita))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (idEnte == null) {
			if (other.idEnte != null)
				return false;
		} else if (!idEnte.equals(other.idEnte))
			return false;
		if (idUtente == null) {
			if (other.idUtente != null)
				return false;
		} else if (!idUtente.equals(other.idUtente))
			return false;
		if (livello == null) {
			if (other.livello != null)
				return false;
		} else if (!livello.equals(other.livello))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "VisibilitaCodiceVersamentoPK [idUtente=" + idUtente + ", idEnte=" + idEnte + ", id=" + id + ", livello="
				+ livello + ", causaVisibilita=" + causaVisibilita + "]";
	}

}
