/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.persistence.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the epaywso_t_esito_invio database table.
 * 
 */
@Entity
@Table(name="epaywso_t_esito_invio")
@NamedQueries({
	@NamedQuery(
			name = "EPaywsoTEsitoInvio.findAllByIdRichiesta",
			query = "SELECT e "
					+ "FROM EPaywsoTEsitoInvio e "
					+ "WHERE idRichiesta = :idRichiesta"),
	@NamedQuery(
			name="EPaywsoTEsitoInvio.findAll",
			query="SELECT e FROM EPaywsoTEsitoInvio e")
})
public class EPaywsoTEsitoInvio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_esito_invio")
	private Long idEsitoInvio;

	@Column(name="des_applicativo")
	private String desApplicativo;

	@Column(name="dettaglio_esito")
	private String dettaglioEsito;

	@Column(name="dt_esito_invio")
	private Timestamp dtEsitoInvio;

	@Column(name="id_richiesta")
	private Long idRichiesta;

	@Column(name="message_store")
	private String messageStore;

	//uni-directional many-to-one association to EPaywsoDEsito
	@ManyToOne
	@JoinColumn(name="cod_esito")
	private EPaywsoDEsito ePaywsoDEsito;
	
	public EPaywsoTEsitoInvio() {
	}
	
	public Long getIdEsitoInvio() {
		return this.idEsitoInvio;
	}

	public void setIdEsitoInvio(Long idEsitoInvio) {
		this.idEsitoInvio = idEsitoInvio;
	}

	public String getDesApplicativo() {
		return this.desApplicativo;
	}

	public void setDesApplicativo(String desApplicativo) {
		this.desApplicativo = desApplicativo;
	}

	public String getDettaglioEsito() {
		return this.dettaglioEsito;
	}

	public void setDettaglioEsito(String dettaglioEsito) {
		this.dettaglioEsito = dettaglioEsito;
	}

	public Timestamp getDtEsitoInvio() {
		return this.dtEsitoInvio;
	}

	public void setDtEsitoInvio(Timestamp dtEsitoInvio) {
		this.dtEsitoInvio = dtEsitoInvio;
	}

	public Long getIdRichiesta() {
		return this.idRichiesta;
	}

	public void setIdRichiesta(Long idRichiesta) {
		this.idRichiesta = idRichiesta;
	}

	public String getMessageStore() {
		return this.messageStore;
	}

	public void setMessageStore(String messageStore) {
		this.messageStore = messageStore;
	}

	public EPaywsoDEsito getEPaywsoDEsito() {
		return this.ePaywsoDEsito;
	}

	public void setEPaywsoDEsito(EPaywsoDEsito ePaywsoDEsito) {
		this.ePaywsoDEsito = ePaywsoDEsito;
	}

}
