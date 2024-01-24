/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the errore database table.
 * 
 */
@Entity
@NamedQuery(name="Errore.findAll", query="SELECT e FROM Errore e")
public class Errore implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ERRORE_ID_GENERATOR", sequenceName="ERRORE_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ERRORE_ID_GENERATOR")
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

	public Errore() {
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

}
