/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.dto.epaymodric.ws;

import java.io.Serializable;

public class DTOOutputWsCambioStatoFlusso implements Serializable {

	 /**
	 * @Gueye
	 */
	private static final long serialVersionUID = 1L;

	private DTOOutputWsEsito esito = null;


    public DTOOutputWsCambioStatoFlusso ( DTOOutputWsEsito esito ) {
		super();
		this.esito = esito;
	}

	public DTOOutputWsCambioStatoFlusso() {
		esito = new DTOOutputWsEsito();
	}

	public DTOOutputWsEsito getEsito() {
		return esito;
	}

	public void setEsito(DTOOutputWsEsito esito) {
		this.esito = esito;
	}




}
