/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the cbl_d_errore database table.
 * 
 */
@Entity
@Table(name="cbl_d_errore",schema="epaymodric")
@NamedQuery(name="CblDErrore.findAll", query="SELECT c FROM CblDErrore c")
public class CblDErrore implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CBL_D_ERRORE_ID_GENERATOR", sequenceName="epaymodric.CBL_D_ERRORE_ID_SEQ" ,schema="epaymodric", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CBL_D_ERRORE_ID_GENERATOR")
	private Long id;


	@Column(name="codice_errore")
	private String codiceErrore;

	@Column(name="descrizione_errore")
	private String descrizioneErrore;

	@Column(name="flag_mail")
	private Boolean flagMail;

	@Column(name="flag_rielaborazione")
	private Boolean flagRielaborazione;

	private String tipologia;

	//bi-directional many-to-one association to CblTElaborazione
	@OneToMany(mappedBy="cblDErrore")
	private List<CblTElaborazione> cblTElaboraziones;

	public CblDErrore() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodiceErrore() {
		return this.codiceErrore;
	}

	public void setCodiceErrore(String codiceErrore) {
		this.codiceErrore = codiceErrore;
	}

	public String getDescrizioneErrore() {
		return this.descrizioneErrore;
	}

	public void setDescrizioneErrore(String descrizioneErrore) {
		this.descrizioneErrore = descrizioneErrore;
	}

	public Boolean getFlagMail() {
		return this.flagMail;
	}

	public void setFlagMail(Boolean flagMail) {
		this.flagMail = flagMail;
	}

	public Boolean getFlagRielaborazione() {
		return this.flagRielaborazione;
	}

	public void setFlagRielaborazione(Boolean flagRielaborazione) {
		this.flagRielaborazione = flagRielaborazione;
	}

	public String getTipologia() {
		return this.tipologia;
	}

	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}

	public List<CblTElaborazione> getCblTElaboraziones() {
		return this.cblTElaboraziones;
	}

	public void setCblTElaboraziones(List<CblTElaborazione> cblTElaboraziones) {
		this.cblTElaboraziones = cblTElaboraziones;
	}

	public CblTElaborazione addCblTElaborazione(CblTElaborazione cblTElaborazione) {
		getCblTElaboraziones().add(cblTElaborazione);
		cblTElaborazione.setCblDErrore(this);

		return cblTElaborazione;
	}

	public CblTElaborazione removeCblTElaborazione(CblTElaborazione cblTElaborazione) {
		getCblTElaboraziones().remove(cblTElaborazione);
		cblTElaborazione.setCblDErrore(null);

		return cblTElaborazione;
	}

}
