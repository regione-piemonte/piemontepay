/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.dto;

import static it.csi.epay.epaywsosrv.util.Util.date2strdatetime;
import static it.csi.epay.epaywsosrv.util.Util.quote;

import java.io.Serializable;
import java.util.Date;

public class EndpointDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String protocol;
	private String host;
	private Integer port;
	private String context;
	private String nomeUtente;
	private String password;
	private Boolean autenticato;
	private Boolean apiManager;
	private Date dataInizioValidita;
	private Date dataFineValidita;

	public EndpointDto(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
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

	public Date getDataInizioValidita() {
		return dataInizioValidita;
	}

	public void setDataInizioValidita(Date dataInizioValidita) {
		this.dataInizioValidita = dataInizioValidita;
	}

	public Date getDataFineValidita() {
		return dataFineValidita;
	}

	public void setDataFineValidita(Date dataFineValidita) {
		this.dataFineValidita = dataFineValidita;
	}

	@Override
	public String toString() {
		final String COMMA = ", ";
		StringBuilder s = new StringBuilder();
		s.append("{ ");
		s.append("id:").append(id).append(COMMA);
		s.append("protocol:").append(quote(protocol)).append(COMMA);
		s.append("host:").append(quote(host)).append(COMMA);
		s.append("port:").append(port).append(COMMA);
		s.append("context").append(quote(context)).append(COMMA);
		s.append("nomeUtente").append(quote(nomeUtente)).append(COMMA);
		s.append("password").append(quote(password)).append(COMMA);
		s.append("autenticato").append(autenticato).append(COMMA);
		s.append("apiManager:").append(apiManager).append(COMMA);
		s.append("dataInizioValidita:").append(date2strdatetime(dataInizioValidita)).append(COMMA);
		s.append("dataFineValidita:").append(date2strdatetime(dataFineValidita));
		s.append(" }");
		return s.toString();
	}

}
