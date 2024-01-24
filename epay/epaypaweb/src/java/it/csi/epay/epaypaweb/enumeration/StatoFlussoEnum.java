/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.enumeration;

public enum StatoFlussoEnum {
	//@formatter:off
	IN_CORSO_DI_ACQUISIZIONE(1),
	ERRORE_IN_FASE_DI_ACQUISIZIONE(2),
	ACQUISITO(3),
	INOLTRATO(4),
	IN_CORSO_DI_REDAZIONE(5),
	ERRORE_IN_FASE_DI_INVIO(6),
	INVIATO(7),
	ESITO_RICEVUTO(8),
	ESITO_INOLTRATO(9),
	ERRORE_IN_FASE_DI_INOLTRO(10),
	BOZZA(11);
	//@formatter:on

	private Integer id;

	private StatoFlussoEnum(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	static public StatoFlussoEnum fromId(Integer id) {
		for (StatoFlussoEnum en : StatoFlussoEnum.values()) {
			if (en.id.equals(id)) {
				return en;
			}
		}
		return null;
	}

}
