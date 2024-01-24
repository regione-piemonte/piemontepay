/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.enumeration;

public enum StatoRichiestaEnum {
	//@formatter:off
	IN_CORSO_DI_ACQUISIZIONE(1),
	ERRORE_IN_FASE_DI_ACQUISIZIONE(2),
	DA_ELABORARE(3),
	ERRORE_IN_FASE_DI_ELABORAZIONE(4),
	ELABORATA(5),
	IN_CORSO_DI_ELABORAZIONE(6);
	//@formatter:on

	private Integer id;

	private StatoRichiestaEnum(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	static public StatoRichiestaEnum fromId(Integer id) {
		for (StatoRichiestaEnum en : StatoRichiestaEnum.values()) {
			if (en.id.equals(id)) {
				return en;
			}
		}
		return null;
	}

}
