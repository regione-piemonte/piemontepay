/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the csi_log_audit database table.
 *
 */
@Entity
@Table(name="csi_log_audit")
@NamedQuery(name="CsiLogAudit.findAll", query="SELECT c FROM CsiLogAudit c")
public class CsiLogAudit implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY )
	@Column(name="uniqueid")
	private Integer id;

	@Column(name="codappmodify")
	private String codiceApplicazione;

	@Column(name="codfisc")
	private String codiceFiscaleOperatore;

	@Column(name="data_ora")
	private Timestamp dataOra;

	private String descrizione;

	@Column(name="id_app")
	private String idApplicazione;

	@Column(name="ip_address")
	private String indirizzoIp;

	@Column(name="key_oper")
	private String idOggetto;

	@Column(name="ogg_oper")
	private String entitaOggetto;

	@Column(name="operazione")
	private String descrizioneOperazione;

	@Column(name="utente")
	private Integer idUtente;

	//uni-directional many-to-one association to AuditAction
	@ManyToOne
	@JoinColumn(name="idaction")
	private AuditAction Action;

	public CsiLogAudit() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodiceApplicazione() {
		return codiceApplicazione;
	}

	public void setCodiceApplicazione(String codiceApplicazione) {
		this.codiceApplicazione = codiceApplicazione;
	}

	public String getCodiceFiscaleOperatore() {
		return codiceFiscaleOperatore;
	}

	public void setCodiceFiscaleOperatore(String codiceFiscaleOperatore) {
		this.codiceFiscaleOperatore = codiceFiscaleOperatore;
	}

	public Timestamp getDataOra() {
		return dataOra;
	}

	public void setDataOra(Timestamp dataOra) {
		this.dataOra = dataOra;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getIdApplicazione() {
		return idApplicazione;
	}

	public void setIdApplicazione(String idApplicazione) {
		this.idApplicazione = idApplicazione;
	}

	public String getIndirizzoIp() {
		return indirizzoIp;
	}

	public void setIndirizzoIp(String indirizzoIp) {
		this.indirizzoIp = indirizzoIp;
	}

	public String getIdOggetto() {
		return idOggetto;
	}

	public void setIdOggetto(String idOggetto) {
		this.idOggetto = idOggetto;
	}

	public String getEntitaOggetto() {
		return entitaOggetto;
	}

	public void setEntitaOggetto(String entitaOggetto) {
		this.entitaOggetto = entitaOggetto;
	}

	public String getDescrizioneOperazione() {
		return descrizioneOperazione;
	}

	public void setDescrizioneOperazione(String descrizioneOperazione) {
		this.descrizioneOperazione = descrizioneOperazione;
	}

	public Integer getIdUtente() {
		return idUtente;
	}

	public void setIdUtente(Integer idUtente) {
		this.idUtente = idUtente;
	}

	public AuditAction getAction() {
		return Action;
	}

	public void setAction(AuditAction Action) {
		this.Action = Action;
	}

    @Override
    public String toString () {
        return "CsiLogAudit [id=" + id + ", codiceApplicazione=" + codiceApplicazione + ", codiceFiscaleOperatore=" + codiceFiscaleOperatore + ", dataOra="
            + dataOra + ", descrizione=" + descrizione + ", idApplicazione=" + idApplicazione + ", indirizzoIp=" + indirizzoIp + ", idOggetto=" + idOggetto
            + ", entitaOggetto=" + entitaOggetto + ", descrizioneOperazione=" + descrizioneOperazione + ", idUtente=" + idUtente + ", Action=" + Action + "]";
    }

}
