/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.utilities;

public enum Messages {
	IUV_INESISTENTE ( "Codice IUV inesistente" ),
	PAGAMENTO_NON_EFFETTUABILE ( "Codice Iuv riferito a pagamento effettuato, annullato o in attesa di ricevuta oppure non piu' valido perche' scaduto." ),
	;

	private final String defaultValue;

	public String getMessage () {
		return defaultValue;
	}

	Messages ( String defaultValue ) {
		this.defaultValue = defaultValue;
	}
}
