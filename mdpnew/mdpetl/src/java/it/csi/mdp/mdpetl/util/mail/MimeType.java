/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.util.mail;

public enum MimeType {
	TESTO("text/plain"), PDF("application/pdf"), XML("text/xml"), CSV("text/csv");
	private String type;
	MimeType(String type) {
		this.type = type;
	}
	public String getType() {
		return type;
	}
}
