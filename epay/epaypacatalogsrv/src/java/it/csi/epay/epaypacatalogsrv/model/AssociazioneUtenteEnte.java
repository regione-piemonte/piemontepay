/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the epaycat_r_utente_ente database table.
 *
 */
@Entity
@Table(name = "epaycat_r_utente_ente")
@NamedQuery(name = "AssociazioneUtenteEnte.findAll", query = "SELECT a FROM AssociazioneUtenteEnte a")
public class AssociazioneUtenteEnte extends AbstractCSILogAuditedParentEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public String getPrimaryKeyRepresentation() {
		if (id == null) {
			return String.valueOf(id);
		} else {
			return String.valueOf("{\"id_utente\"=" + id.getIdUtente() + ", \"id_ente\"=" + id.getIdEnte() + "\"}");
		}
	}

	@EmbeddedId
	private AssociazioneUtenteEntePK id;

	// EPAY-80
	@Temporal(TemporalType.DATE)
	@Column(name = "data_inizio_validita")
	private Date dataInizioValidita;

	// EPAY-80
	@Temporal(TemporalType.DATE)
	@Column(name = "data_fine_validita")
	private Date dataFineValidita;

	// EPAY-80
	@Column(name = "email")
	private String email;

	// EPAY-80
	@Column(name = "flag_admin")
	private Boolean flagAdmin;

	// EPAY-80
	@Column(name = "utente_inserimento")
	private String utenteInserimento;

	// EPAY-80
	@Column(name = "data_inserimento")
	private Timestamp dataInserimento;

	// EPAY-80
	@Column(name = "utente_modifica")
	private String utenteModifica;

	// EPAY-80
	@Column(name = "data_modifica")
	private Timestamp dataModifica;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_ente", referencedColumnName = "id", insertable = false, updatable = false)
	private Ente ente;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_utente", referencedColumnName = "id", insertable = false, updatable = false)
	private Utente utente;

	public AssociazioneUtenteEnte() {
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public AssociazioneUtenteEntePK getId() {
		return id;
	}

	public void setId(AssociazioneUtenteEntePK id) {
		this.id = id;
	}

	/**
	 * @return the dataInizioValidita
	 */
	public Date getDataInizioValidita() {
		return dataInizioValidita;
	}

	/**
	 * @param dataInizioValidita the dataInizioValidita to set
	 */
	public void setDataInizioValidita(Date dataInizioValidita) {
		this.dataInizioValidita = dataInizioValidita;
	}

	/**
	 * @return the dataFineValidita
	 */
	public Date getDataFineValidita() {
		return dataFineValidita;
	}

	/**
	 * @param dataFineValidita the dataFineValidita to set
	 */
	public void setDataFineValidita(Date dataFineValidita) {
		this.dataFineValidita = dataFineValidita;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the flagAdmin
	 */
	public Boolean getFlagAdmin() {
		return flagAdmin;
	}

	/**
	 * @param flagAdmin the flagAdmin to set
	 */
	public void setFlagAdmin(Boolean flagAdmin) {
		this.flagAdmin = flagAdmin;
	}

	/**
	 * @return the utenteInserimento
	 */
	public String getUtenteInserimento() {
		return utenteInserimento;
	}

	/**
	 * @param utenteInserimento the utenteInserimento to set
	 */
	public void setUtenteInserimento(String utenteInserimento) {
		this.utenteInserimento = utenteInserimento;
	}

	/**
	 * @return the dataInserimento
	 */
	public Timestamp getDataInserimento() {
		return dataInserimento;
	}

	/**
	 * @param dataInserimento the dataInserimento to set
	 */
	public void setDataInserimento(Timestamp dataInserimento) {
		this.dataInserimento = dataInserimento;
	}

	/**
	 * @return the utenteModifica
	 */
	public String getUtenteModifica() {
		return utenteModifica;
	}

	/**
	 * @param utenteModifica the utenteModifica to set
	 */
	public void setUtenteModifica(String utenteModifica) {
		this.utenteModifica = utenteModifica;
	}

	/**
	 * @return the dataModifica
	 */
	public Timestamp getDataModifica() {
		return dataModifica;
	}

	/**
	 * @param dataModifica the dataModifica to set
	 */
	public void setDataModifica(Timestamp dataModifica) {
		this.dataModifica = dataModifica;
	}

	public Ente getEnte() {
		return ente;
	}

	public void setEnte(Ente ente) {
		this.ente = ente;
	}

	@Override
	public String toString() {
		return "AssociazioneUtenteEnte [id=" + id + "]";
	}

}
