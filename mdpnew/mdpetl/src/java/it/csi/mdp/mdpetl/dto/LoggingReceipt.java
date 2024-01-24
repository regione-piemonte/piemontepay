/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.dto;

import java.sql.Timestamp;


public class LoggingReceipt extends BaseDTO {

	private String iuv;

	private String istitutoMittente;

	private Timestamp dataOraInvio;

	private String errori;

	private String warning;

	private String esito;

	public String getIuv () {
		return iuv;
	}

	public void setIuv ( String iuv ) {
		this.iuv = iuv;
	}

	public String getIstitutoMittente () {
		return istitutoMittente;
	}

	public void setIstitutoMittente ( String istitutoMittente ) {
		this.istitutoMittente = istitutoMittente;
	}

	public Timestamp getDataOraInvio () {
		return dataOraInvio;
	}

	public void setDataOraInvio ( Timestamp dataOraInvio ) {
		this.dataOraInvio = dataOraInvio;
	}

	public String getErrori () {
		return errori;
	}

	public void setErrori ( String errori ) {
		this.errori = errori;
	}

	public String getWarning () {
		return warning;
	}

	public void setWarning ( String warning ) {
		this.warning = warning;
	}

	public String getEsito () {
		return esito;
	}

	public void setEsito ( String esito ) {
		this.esito = esito;
	}
}
