/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the epaywso_t_endpoint database table.
 * 
 */
@Entity
@Table(name="epaywso_t_endpoint")
@NamedQueries({
	@NamedQuery(
			name = "EPaywsoTEndpoint.findOneById",
			query = "SELECT e "
					+ "FROM EPaywsoTEndpoint e "
					+ "WHERE e.idEndpoint = :idEndpoint "
					+ "  AND e.dtInizioValidita <= :dt AND (e.dtFineValidita IS NULL OR :dt <= e.dtFineValidita)"),
	@NamedQuery(
			name="EPaywsoTEndpoint.findAll",
			query="SELECT e FROM EPaywsoTEndpoint e")
})
public class EPaywsoTEndpoint implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_endpoint")
	private Integer idEndpoint;

	@Column(name="api_manager")
	private Boolean apiManager;

	private Boolean autenticato;

	private String context;

	@Column(name="dt_fine_validita")
	private Timestamp dtFineValidita;

	@Column(name="dt_inizio_validita")
	private Timestamp dtInizioValidita;

	private String host;

	@Column(name="id_applicativo")
	private Integer idApplicativo;

	@Column(name="nome_utente")
	private String nomeUtente;

	private String password;

	private Integer port;

	private String protocollo;

	public EPaywsoTEndpoint() {
	}

	public Integer getIdEndpoint() {
		return this.idEndpoint;
	}

	public void setIdEndpoint(Integer idEndpoint) {
		this.idEndpoint = idEndpoint;
	}

	public Boolean getApiManager() {
		return this.apiManager;
	}

	public void setApiManager(Boolean apiManager) {
		this.apiManager = apiManager;
	}

	public Boolean getAutenticato() {
		return this.autenticato;
	}

	public void setAutenticato(Boolean autenticato) {
		this.autenticato = autenticato;
	}

	public String getContext() {
		return this.context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public Timestamp getDtFineValidita() {
		return this.dtFineValidita;
	}

	public void setDtFineValidita(Timestamp dtFineValidita) {
		this.dtFineValidita = dtFineValidita;
	}

	public Timestamp getDtInizioValidita() {
		return this.dtInizioValidita;
	}

	public void setDtInizioValidita(Timestamp dtInizioValidita) {
		this.dtInizioValidita = dtInizioValidita;
	}

	public String getHost() {
		return this.host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Integer getIdApplicativo() {
		return this.idApplicativo;
	}

	public void setIdApplicativo(Integer idApplicativo) {
		this.idApplicativo = idApplicativo;
	}

	public String getNomeUtente() {
		return this.nomeUtente;
	}

	public void setNomeUtente(String nomeUtente) {
		this.nomeUtente = nomeUtente;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getPort() {
		return this.port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public String getProtocollo() {
		return this.protocollo;
	}

	public void setProtocollo(String protocollo) {
		this.protocollo = protocollo;
	}

}
