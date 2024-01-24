/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.dto.epaymodric.ws;

import java.io.Serializable;

/**
 *
 *
 * @author Gueye
 *
 */
public class DTOOutputWsAggiornaStatoElaborazione implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private String  risultato;

	private String es;

	private DTOOutputWsEsito esito = null;


    public DTOOutputWsAggiornaStatoElaborazione ( String risultato, DTOOutputWsEsito esito ) {
		super();
		this.risultato = risultato;
		this.esito = esito;
	}


	public DTOOutputWsAggiornaStatoElaborazione(String risultato, String es) {
		super();
		this.risultato = risultato;
		this.es = es;
	}





	public DTOOutputWsAggiornaStatoElaborazione() {

	}

	public String getRisultato() {
		return risultato;
	}

	public void setRisultato(String risultato) {
		this.risultato = risultato;
	}

	public DTOOutputWsEsito getEsito() {
		return esito;
	}

	public void setEsito(DTOOutputWsEsito esito) {
		this.esito = esito;
	}



	public String getEs() {
		return es;
	}


	public void setEs(String es) {
		this.es = es;
	}


	@Override
	public String toString() {
        return "DTOOutputAggiornaStatoElaborazione [risultato=" + risultato + ", esito="
				+ esito + "]";
	}





}
