/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto.voceentrata;

import it.csi.epay.epaypacatalogsrv.dto.ParentInput;

public class RicercaVoceEntrataInput extends ParentInput {

	private static final long serialVersionUID = 1L;

	private String codice;
	private String descrizione;
	private String codiceTematica;
	private String codiceMacrotipo;

    @Override
    public String toString () {
        return "RicercaVoceEntrataInput [codice=" + codice + ", descrizione=" + descrizione + ", codiceTematica=" + codiceTematica + ", codiceMacrotipo="
            + codiceMacrotipo + "]";
    }

    public String getCodice () {
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

	public String getCodiceTematica() {
		return codiceTematica;
	}

	public void setCodiceTematica(String codiceTematica) {
		this.codiceTematica = codiceTematica;
	}

	public String getCodiceMacrotipo() {
		return codiceMacrotipo;
	}

	public void setCodiceMacrotipo(String codiceMacrotipo) {
		this.codiceMacrotipo = codiceMacrotipo;
	}

}
