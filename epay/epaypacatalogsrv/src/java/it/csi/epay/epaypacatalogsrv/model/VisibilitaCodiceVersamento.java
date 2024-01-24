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
 * The persistent class for the epaycat_v_visibilita_utente_codici_versamento
 * database view.
 *
 */
@Entity
@Table(name = "epaycat_v_visibilita_utente_codici_versamento")
public class VisibilitaCodiceVersamento implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private VisibilitaCodiceVersamentoPK pk;

	@Column(name = "cv_parent_id")
	private Long idCodiceVersamentoPadre;

	@Column(name = "t_cod_tematica")
	private String codiceTematica;

	@Column(name = "t_flag_visibilita_totale")
	private Boolean visibilitaTotale;

	@Column(name = "ve_codice")
	private String codiceVoceEntrata;

	@Column(name = "ve_descrizione")
	private String descrizioneVoceEntrata;

	@Column(name = "cv_codice")
	private String codice;

	@Column(name = "cv_descrizione")
	private String descrizione;

	@Column(name = "cv_annullato")
	private Boolean annullato;

	public VisibilitaCodiceVersamentoPK getPk() {
		return pk;
	}

	public void setPk(VisibilitaCodiceVersamentoPK pk) {
		this.pk = pk;
	}

	public Long getIdCodiceVersamentoPadre() {
		return idCodiceVersamentoPadre;
	}

	public void setIdCodiceVersamentoPadre(Long idCodiceVersamentoPadre) {
		this.idCodiceVersamentoPadre = idCodiceVersamentoPadre;
	}

	public String getCodiceTematica() {
		return codiceTematica;
	}

	public void setCodiceTematica(String codiceTematica) {
		this.codiceTematica = codiceTematica;
	}

	public Boolean getVisibilitaTotale() {
		return visibilitaTotale;
	}

	public void setVisibilitaTotale(Boolean visibilitaTotale) {
		this.visibilitaTotale = visibilitaTotale;
	}

	public String getCodiceVoceEntrata() {
		return codiceVoceEntrata;
	}

	public void setCodiceVoceEntrata(String codiceVoceEntrata) {
		this.codiceVoceEntrata = codiceVoceEntrata;
	}

	public String getDescrizioneVoceEntrata() {
		return descrizioneVoceEntrata;
	}

	public void setDescrizioneVoceEntrata(String descrizioneVoceEntrata) {
		this.descrizioneVoceEntrata = descrizioneVoceEntrata;
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

	public Boolean getAnnullato() {
		return annullato;
	}

	public void setAnnullato(Boolean annullato) {
		this.annullato = annullato;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pk == null) ? 0 : pk.hashCode());
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
		VisibilitaCodiceVersamento other = (VisibilitaCodiceVersamento) obj;
		if (pk == null) {
			if (other.pk != null)
				return false;
		} else if (!pk.equals(other.pk))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "VisibilitaCodiceVersamento [pk=" + pk + ", idCodiceVersamentoPadre=" + idCodiceVersamentoPadre
				+ ", codiceTematica=" + codiceTematica + ", visibilitaTotale=" + visibilitaTotale
				+ ", codiceVoceEntrata=" + codiceVoceEntrata + ", descrizioneVoceEntrata=" + descrizioneVoceEntrata
				+ ", codice=" + codice + ", descrizione=" + descrizione + ", annullato=" + annullato + "]";
	}

}
