/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.entity.cooppec;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="epaypa_d_errore",schema="epaypa")

@NamedQueries({
    @NamedQuery(
        name="EpaypaDErrore.findAll", 
        query="SELECT c FROM EpaypaDErrore c"),

    @NamedQuery(
        name = "EpaypaDErrore.findByCodiceErrore",
        query = "SELECT e "
                + "FROM EpaypaDErrore e "
                + "WHERE e.codiceErrore = :codiceErrore "
                + "ORDER BY e.codiceErrore")
})

public class EpaypaDErrore implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="EPAYPA_D_ERRORE_ID_GENERATOR", sequenceName="epaypa.EPAYPA_D_ERRORE_ID_SEQ" ,schema="epaypa", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EPAYPA_D_ERRORE_ID_GENERATOR")
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

	public EpaypaDErrore() {
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
