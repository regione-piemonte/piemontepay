/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.presentation.uiutils;

/**
 * Contiene lo stato della tabella dal punto di vista di:
 * - porzione di dati da visualizzare nella viewport
 * - ordinamento
 * @generated
 */
public class TableStatus {

	private String callback;
	private String start;
	private String limit;
	private String sort;
	private String dir;
	//***parametro che contiene il nome dell'id del record
	private String valueSelector;
	//***parametro che contiene lo stato della tabella
	//***se impostato a true allora rimuove il relativo cookie dello stato.
	private boolean clearStatus;

	public String getCallback() {
		return callback;
	}
	public void setCallback(String callback) {
		this.callback = callback;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getLimit() {
		return limit;
	}
	public void setLimit(String limit) {
		this.limit = limit;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getDir() {
		return dir;
	}
	public void setDir(String dir) {
		this.dir = dir;
	}
	public String getValueSelector() {
		return valueSelector;
	}
	public void setValueSelector(String valueSelector) {
		this.valueSelector = valueSelector;
	}
	public boolean isClearStatus() {
		return clearStatus;
	}
	public void setClearStatus(boolean clearStatus) {
		this.clearStatus = clearStatus;
	}
}
