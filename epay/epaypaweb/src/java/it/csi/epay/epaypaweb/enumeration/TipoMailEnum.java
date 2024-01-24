/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.enumeration;

public enum TipoMailEnum {
	//@formatter:off
	TRASMISSIONE_NOTIFICHE_PAGAMENTO(1),
	TRASMISSIONE_AVVISI_SCADUTI(2),
	TRASMISSIONE_FLUSSO_RENDICONTAZIONE(3),
    TRASMISSIONE_RICHIESTE_DI_REVOCA(4);
	//@formatter:on

	private Integer id;

	private TipoMailEnum(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	static public TipoMailEnum fromId(Integer id) {
		for (TipoMailEnum en : TipoMailEnum.values()) {
			if (en.id.equals(id)) {
				return en;
			}
		}
		return null;
	}
}
