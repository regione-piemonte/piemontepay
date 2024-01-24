/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayservices.model;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;


/**
 * Richiesta di stampa Rt.
 */

public class GetRTChiamanteEsternoInput extends AccessoChiamanteEsternoSincronoSplitInput  implements Serializable {

	private static final long serialVersionUID = -2210094069982573097L;

	private String codiceFiscale;

    private String iuv;

    private String formatoRT;
    
    private String codiceFiscaleEnte;

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getIuv() {
		return iuv;
	}

	public void setIuv(String iuv) {
		this.iuv = iuv;
	}

	public String getFormatoRT() {
		return formatoRT;
	}

	public void setFormatoRT(String formatoRT) {
		this.formatoRT = formatoRT;
	}

    public String getCodiceFiscaleEnte() {
		return codiceFiscaleEnte;
	}

	public void setCodiceFiscaleEnte(String codiceFiscaleEnte) {
		this.codiceFiscaleEnte = codiceFiscaleEnte;
	}

	@Override
    public String toString () {
        return "GetRTChiamanteEsternoInput ["
            + " codiceFiscale=" + StringUtils.trimToEmpty ( getCodiceFiscale () )
            + " iuv=" + StringUtils.trimToEmpty ( getIuv () )
            + " formatoRT= " + StringUtils.trimToEmpty ( getFormatoRT () )
            + " codiceFiscaleEnte= " + StringUtils.trimToEmpty ( getCodiceFiscaleEnte () )
            + "]";
    }

}
