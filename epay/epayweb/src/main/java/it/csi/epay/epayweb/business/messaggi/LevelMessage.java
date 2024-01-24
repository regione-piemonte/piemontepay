/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayweb.business.messaggi;
//
public enum LevelMessage {
	SUCCESS("alert alert-success", "msg-success"), 
	INFO("alert alert-info", "msg-info"), 
	WARNING("alert alert-warning", "msg-warning"), 
	DANGER("alert alert-danger", "msg-danger");
	
	private String cssAlertClass;
	private String cssMsgClass;
	
	private LevelMessage(String cssAlertClass, String cssMsgClass) {
		this.cssAlertClass = cssAlertClass;
		this.cssMsgClass = cssMsgClass;
	}
	
	public int getSeverity() {
		return ordinal();
	}
	
	public String getCssAlertClass() {
		return cssAlertClass;
	}
	
	public String getCssMsgClass() {
		return cssMsgClass;
	}
}
