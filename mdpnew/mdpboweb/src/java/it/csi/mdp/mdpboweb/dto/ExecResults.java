/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.dto;

import java.util.*;

public class ExecResults {
	private String _resultCode;
	private Map<String, String> fldErrors = new HashMap<String, String>();
	private Collection<String> globalErrors = new ArrayList<String>();
	private Collection<String> globalMessages = new ArrayList<String>();
	private Object _model;

	public void setModel(Object model) {
		_model = model;
	}

	public Object getModel() {
		return _model;
	}

	public void setResultCode(String code) {
		_resultCode = code;
	}

	public String getResultCode() {
		return _resultCode;
	}

	public Map<String, String> getFldErrors() {
		return fldErrors;
	}

	public void setFldErrors(Map<String, String> fldErrors) {
		this.fldErrors = fldErrors;
	}

	public Collection<String> getGlobalErrors() {
		return globalErrors;
	}

	public void setGlobalErrors(Collection<String> globalErrors) {
		this.globalErrors = globalErrors;
	}

	public Collection<String> getGlobalMessages() {
		return globalMessages;
	}

	public void setGlobalMessages(Collection<String> globalMessages) {
		this.globalMessages = globalMessages;
	}
}
