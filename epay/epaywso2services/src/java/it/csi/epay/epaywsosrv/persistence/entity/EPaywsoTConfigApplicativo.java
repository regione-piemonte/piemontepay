/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.persistence.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

/**
 * The persistent class for the epaywso_t_configurazione_applicativo virtual database table.
 * 
 */
//@formatter:off
@Entity
@NamedQuery(
	name = "EPaywsoTConfigApplicativo.findAllByParams",
	query = "SELECT DISTINCT NEW EPaywsoTConfigApplicativo("
			+ "     a.idApplicativo, a.descrizione AS descrizioneApplicativo, a.utente, a.msInbound, a.msOutbound, "
			+ "     e.idEnte, e.codFiscaleEnte, e.denominazione AS denominazioneEnte, "
			+ "     c.idCodiceVersamento, c.codVersamento, c.descrizione AS descrizioneCodVersamento, "
			+ "     p.idEndpoint, p.protocollo, p.host, p.port, p.context, p.nomeUtente, p.password, p.autenticato, p.apiManager) "
			+ "FROM EPaywsoTEnte e, "
			+ "     EPaywsoTApplicativo a, "
			+ "     EPaywsoTCodiceVersamento c, "
			+ "     EPaywsoRAppTiporicEp r, "
			+ "     EPaywsoTEndpoint p "
			+ "WHERE e.codFiscaleEnte = :codFiscaleEnte "
			+ "  AND ("
			+ "		c.codVersamento IN (:codsVers) "
			+ "     OR (c.codVersamento = '****' AND r.ePaywsoDTipoRichiesta.multiEndPoint = true) "
			+ "  )"
			+ "  AND a.ePaywsoTEnte.idEnte = e.idEnte "
			+ "  AND a.idApplicativo = c.epaywsoTApplicativo.idApplicativo "
			+ "  AND r.ePaywsoTApplicativo.idApplicativo = a.idApplicativo "
			+ "  AND p.idEndpoint = r.ePaywsoTEndpoint.idEndpoint "
			+ "  AND r.ePaywsoDTipoRichiesta.idTipoRichiesta = :idTipoRichiesta "
			+ "  AND e.dtInizioValidita <= :dt AND (e.dtFineValidita IS NULL OR :dt <= e.dtFineValidita) "
			+ "  AND c.dtInizioValidita <= :dt AND (c.dtFineValidita IS NULL OR :dt <= c.dtFineValidita) "
			+ "  AND a.dtInizioValidita <= :dt AND (a.dtFineValidita IS NULL OR :dt <= a.dtFineValidita) "
			+ "  AND p.dtInizioValidita <= :dt AND (p.dtFineValidita IS NULL OR :dt <= p.dtFineValidita) "
)
public class EPaywsoTConfigApplicativo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer idApplicativo;
	@Id
	private Integer idEnte;
	@Id
	private Integer idCodiceVersamento;
	@Id
	private Integer idEndpoint;

	private String descrizioneApplicativo;
	private String utente;
	private String msInbound;
	private String msOutbound;
	private String codFiscaleEnte;
	private String denominazioneEnte;
	private String codVersamento;
	private String descrizioneCodVersamento;
	private String protocollo;
	private String host;
	private Integer port;
	private String context;
	private String nomeUtente;
	private String password;
	private Boolean autenticato;
	private Boolean apiManager;

	public EPaywsoTConfigApplicativo(
		Integer idApplicativo, String descrizioneApplicativo, String utente, String msInbound, String msOutbound,
		Integer idEnte, String codFiscaleEnte, String denominazioneEnte,
		Integer idCodiceVersamento, String codVersamento, String descrizioneCodVersamento,
		Integer idEndpoint, String protocollo, String host, Integer port, String context, String nomeUtente, String password, Boolean autenticato, Boolean apiManager)
	{
		this.idApplicativo = idApplicativo;
		this.descrizioneApplicativo = descrizioneApplicativo;
		this.utente = utente;
		this.msInbound = msInbound;
		this.msOutbound = msOutbound;

		this.idEnte = idEnte;
		this.codFiscaleEnte = codFiscaleEnte;
		this.denominazioneEnte = denominazioneEnte;

		this.idCodiceVersamento = idCodiceVersamento;
		this.codVersamento = codVersamento;
		this.descrizioneCodVersamento = descrizioneCodVersamento;
		
		this.idEndpoint = idEndpoint;
		this.protocollo = protocollo;
		this.host = host;
		this.port = port;
		this.context = context;
		this.nomeUtente = nomeUtente;
		this.password = password;
		this.autenticato = autenticato;
		this.apiManager = apiManager;
	}
//@formatter:on

	public Integer getIdApplicativo() {
		return idApplicativo;
	}

	public void setIdApplicativo(Integer idApplicativo) {
		this.idApplicativo = idApplicativo;
	}

	public Integer getIdEnte() {
		return idEnte;
	}

	public void setIdEnte(Integer idEnte) {
		this.idEnte = idEnte;
	}

	public Integer getIdCodiceVersamento() {
		return idCodiceVersamento;
	}

	public void setIdCodiceVersamento(Integer idCodiceVersamento) {
		this.idCodiceVersamento = idCodiceVersamento;
	}

	public Integer getIdEndpoint() {
		return idEndpoint;
	}

	public void setIdEndpoint(Integer idEndpoint) {
		this.idEndpoint = idEndpoint;
	}

	public String getDescrizioneApplicativo() {
		return descrizioneApplicativo;
	}

	public void setDescrizioneApplicativo(String descrizioneApplicativo) {
		this.descrizioneApplicativo = descrizioneApplicativo;
	}

	public String getUtente() {
		return utente;
	}

	public void setUtente(String utente) {
		this.utente = utente;
	}

	public String getMsInbound() {
		return msInbound;
	}

	public void setMsInbound(String msInbound) {
		this.msInbound = msInbound;
	}

	public String getMsOutbound() {
		return msOutbound;
	}

	public void setMsOutbound(String msOutbound) {
		this.msOutbound = msOutbound;
	}

	public String getCodFiscaleEnte() {
		return codFiscaleEnte;
	}

	public void setCodFiscaleEnte(String codFiscaleEnte) {
		this.codFiscaleEnte = codFiscaleEnte;
	}

	public String getDenominazioneEnte() {
		return denominazioneEnte;
	}

	public void setDenominazioneEnte(String denominazioneEnte) {
		this.denominazioneEnte = denominazioneEnte;
	}

	public String getCodVersamento() {
		return codVersamento;
	}

	public void setCodVersamento(String codVersamento) {
		this.codVersamento = codVersamento;
	}

	public String getDescrizioneCodVersamento() {
		return descrizioneCodVersamento;
	}

	public void setDescrizioneCodVersamento(String descrizioneCodVersamento) {
		this.descrizioneCodVersamento = descrizioneCodVersamento;
	}

	public String getProtocollo() {
		return protocollo;
	}

	public void setProtocollo(String protocollo) {
		this.protocollo = protocollo;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getNomeUtente() {
		return nomeUtente;
	}

	public void setNomeUtente(String nomeUtente) {
		this.nomeUtente = nomeUtente;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getAutenticato() {
		return autenticato;
	}

	public void setAutenticato(Boolean autenticato) {
		this.autenticato = autenticato;
	}

	public Boolean getApiManager() {
		return apiManager;
	}

	public void setApiManager(Boolean apiManager) {
		this.apiManager = apiManager;
	}

}
