/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.interfacews.epaymodric.enums;
public enum NomeFiltroReportEnum {
	
//	NOMINATIVO_EXPORT ("NOMINATIVO_EXPORT"),
	IDENTIFICATIVO_FLUSSO ("IDENTIFICATIVO_FLUSSO"),
	CAUSALE_PROVVISORIO ("CAUSALE_PROVVISORIO"),
	IUV ("IUV"),
	ID_CODICE_VERSAMENTO ("ID_CODICE_VERSAMENTO"),
	ID_STATO_FLUSSO ("ID_STATO_FLUSSO"),
	DATA_ELABORAZIONE_DA ("DATA_ELABORAZIONE_DA"),
	DATA_ELABORAZIONE_A ("DATA_ELABORAZIONE_A"),
	DATA_RICEZIONE_DA ("DATA_RICEZIONE_DA"),
	DATA_RICEZIONE_A ("DATA_RICEZIONE_A"),
	
	CODICE_DESCRIZIONE_PSP ("CODICE_DESCRIZIONE_PSP");
	
	private String code;

	private NomeFiltroReportEnum(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
	
	static public NomeFiltroReportEnum fromId(String code) {
		for (NomeFiltroReportEnum en : NomeFiltroReportEnum.values()) {
			if (en.code.equals(code)) {
				return en;
			}
		}
		return null;
	}
}
