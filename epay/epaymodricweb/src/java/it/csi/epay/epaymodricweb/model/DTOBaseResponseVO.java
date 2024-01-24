/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodricweb.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DTOBaseResponseVO {
	
	private String esito = "OK";

    private String codice = "";

    private String descrizione = "";

    private String timestamp = new SimpleDateFormat ().format ( new Date () );

    private String stacktrace = "NONE";

	public DTOBaseResponseVO() {
	
	}

	public String getEsito() {
		return esito;
	}

	public void setEsito(String esito) {
		this.esito = esito;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getStacktrace() {
		return stacktrace;
	}

	public void setStacktrace(String stacktrace) {
		this.stacktrace = stacktrace;
	}
    
    

}
