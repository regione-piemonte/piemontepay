/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.enumeration;

public enum TipoFlussoEnum {
	//@formatter:off
	NOTIFICHE_PAGAMENTO(1),
	LISTE_DI_CARICO(2),
	AVVISI_SCADUTI(3),
	AGGIORNAMENTO_POSIZIONI_DEBITORIE(4),
	TRASMETTI_FLUSSO_RENDICONTAZIONE(5),
    RICHIESTE_DI_REVOCA(6),
    REPORT_PAGAMENTI_ENTE_XLS ( 7 ),
    REPORT_PAGAMENTI_ENTE_CSV ( 8 ),
    REPORT_FLUSSO_BASE_XLS ( 9 ),
    REPORT_FLUSSO_BASE_CSV ( 10 );
	//@formatter:on

	private Integer id;

	private TipoFlussoEnum(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	static public TipoFlussoEnum fromId(Integer id) {
		for (TipoFlussoEnum en : TipoFlussoEnum.values()) {
			if (en.id.equals(id)) {
				return en;
			}
		}
		return null;
	}

}
