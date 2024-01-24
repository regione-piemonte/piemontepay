/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayservices.model;

import java.io.Serializable;


/**
 *
 */

public class PagamentoIuvChiamanteEsternoInput extends AccessoChiamanteEsternoSincronoSplitInput implements Serializable {

	private static final long serialVersionUID = 5297807441397131380L;

	private String iuv;

	private String codiceFiscale;
	

	public PagamentoIuvChiamanteEsternoInput () {
        super ();
    }

    public PagamentoIuvChiamanteEsternoInput ( String codiceFiscale, String iuv ) {
		this.iuv = iuv;
		this.codiceFiscale = codiceFiscale;
	}

	public String getIuv () {
		return iuv;
	}

	public void setIuv ( String iuv ) {
		this.iuv = iuv;
	}

	public String getCodiceFiscale () {
		return codiceFiscale;
	}

	public void setCodiceFiscale ( String codiceFiscale ) {
		this.codiceFiscale = codiceFiscale;
	}

	@Override
	public String toString () {
		return "PagamentoIuvChiamanteEsternoInput ["
						+ ( codiceFiscale != null ? "codiceFiscale=" + codiceFiscale + ", " : "" )
						+ ( iuv != null ? "iuv=" + iuv + ", " : "" ) + "]";
	}

}
