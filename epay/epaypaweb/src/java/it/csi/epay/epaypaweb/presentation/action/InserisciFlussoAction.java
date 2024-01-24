/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.presentation.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import it.csi.epay.epaypaweb.dto.FlussoCompletoDto;

public abstract class InserisciFlussoAction<T> extends EpaypawebBaseAction implements SessionAware {
	static private final long serialVersionUID = 1L;

	protected Map<String, Object> session;

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	@SuppressWarnings("unchecked")
	protected FlussoCompletoDto<T> getFlussoCompletoFromSession() {
		return (FlussoCompletoDto<T>) session.get(getSessionKey());
	}

	protected void putFlussoCompletoInSession(FlussoCompletoDto<T> flussoCompleto) {
		session.put(getSessionKey(), flussoCompleto);
	}

	protected void putFlussoCompletoNewInSessione() {
		putFlussoCompletoInSession(new FlussoCompletoDto<T>());
	}

	abstract protected String getSessionKey();
	
}
