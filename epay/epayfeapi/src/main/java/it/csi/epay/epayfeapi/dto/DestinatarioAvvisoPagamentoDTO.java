/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.dto;

import java.io.Serializable;


public class DestinatarioAvvisoPagamentoDTO implements Serializable {

	private static final long serialVersionUID = -1005992378963214542L;

	private String idUnivocoDestinatario;

	private String anagraficaDestinatario;

	private String indirizzoDestinatario;

	private String datiDestinatario;

	public String getIdUnivocoDestinatario () {
		return idUnivocoDestinatario;
	}

	public void setIdUnivocoDestinatario ( String idUnivocoDestinatario ) {
		this.idUnivocoDestinatario = idUnivocoDestinatario;
	}

	public String getAnagraficaDestinatario () {
		return anagraficaDestinatario;
	}

	public void setAnagraficaDestinatario ( String anagraficaDestinatario ) {
		this.anagraficaDestinatario = anagraficaDestinatario;
	}

	public String getIndirizzoDestinatario () {
		return indirizzoDestinatario;
	}

	public void setIndirizzoDestinatario ( String indirizzoDestinatario ) {
		this.indirizzoDestinatario = indirizzoDestinatario;
	}

	public String getDatiDestinatario () {
		return datiDestinatario;
	}

	public void setDatiDestinatario ( String datiDestinatario ) {
		this.datiDestinatario = datiDestinatario;
	}

}
