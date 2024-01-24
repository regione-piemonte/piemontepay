/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.dto;

public class LoggingFlussoEsteso extends LoggingFlusso {

	private static final long serialVersionUID = 7170066161082607446L;

	@Override
	public String getTipoFlusso() {
		return "ESTESO";
	}

}
