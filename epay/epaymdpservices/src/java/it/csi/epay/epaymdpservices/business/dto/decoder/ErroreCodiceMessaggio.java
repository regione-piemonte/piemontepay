/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymdpservices.business.dto.decoder;

public class ErroreCodiceMessaggio {

	private String codice;
	private String messaggio;

    public ErroreCodiceMessaggio ( String codice, String messaggio ) {
        super ();
        this.codice = codice;
        this.messaggio = messaggio;
    }

    public ErroreCodiceMessaggio () {
        super ();
    }

    public String getCodice () {
		return codice;
	}
	public void setCodice(String codice) {
		this.codice = codice;
	}
	public String getMessaggioErrore() {
		return messaggio;
	}
	public void setMessaggioErrore(String messaggio) {
		this.messaggio = messaggio;
	}



}
