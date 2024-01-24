/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.enumeration;

public enum EsitoRiversamentoEnum {
	CODICE_0("0", "Pagamento eseguito"), CODICE_3("3", "Pagamento revocato"), CODICE_9("9", "Pagamento eseguito in assenza di RPT");

	private String id;
	private String des;

	private EsitoRiversamentoEnum(String id, String des) {
		this.id = id;
		this.des = des;
	}

	public String getId() {
		return id;
	}

	public String getDes() {
		return des;
	}

	static public EsitoRiversamentoEnum fromId(String id) {
		for (EsitoRiversamentoEnum en : EsitoRiversamentoEnum.values()) {
			if (en.id.equals(id)) {
				return en;
			}
		}
		return null;
	}
}
