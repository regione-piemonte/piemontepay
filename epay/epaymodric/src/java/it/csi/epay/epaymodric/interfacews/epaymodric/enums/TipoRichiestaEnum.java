/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.interfacews.epaymodric.enums;

public enum TipoRichiestaEnum {
	//@formatter:off
	INSERISCI_LISTA_DI_CARICO(1),
	AGGIORNA_POSIZIONI_DEBITORIE(2),
	TRASMETTI_NOTIFICHE_PAGAMENTO(3),
	TRASMETTI_AVVISI_SCADUTI(4),
	TRASMETTI_FLUSSO_RENDICONTAZIONE(5),
	TRASMETTI_FLUSSO_RENDICONTAZIONE_ESTESO(6),
    	TRASMETTI_RICHIESTA_DI_REVOCA(7),
	TRASMETTI_RT(8),
	TRASMETTI_FLUSSO_RENDICONTAZIONE_COMPLETO(9),
	TRASMETTI_RT_ESTESA(10),
;
	//@formatter:on

	private Integer id;

	private TipoRichiestaEnum(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	static public TipoRichiestaEnum fromId(Integer id) {
		for (TipoRichiestaEnum en : TipoRichiestaEnum.values()) {
			if (en.id.equals(id)) {
				return en;
			}
		}
		return null;
	}

}
