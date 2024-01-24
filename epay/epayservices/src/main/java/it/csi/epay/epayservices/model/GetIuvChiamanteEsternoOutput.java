/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayservices.model;

import java.io.Serializable;

/**
 *
 */

public class GetIuvChiamanteEsternoOutput extends AccessoChiamanteEsternoSincronoSplitOutput implements Serializable{

    private static final long serialVersionUID = 1L;
    
    private String iuv;

    private String codiceAvviso;
    
    public String getIuv () {
        return iuv;
    }

    
    public void setIuv ( String iuv ) {
        this.iuv = iuv;
    }

    public String getCodiceAvviso() {
		return codiceAvviso;
	}


	public void setCodiceAvviso(String codiceAvviso) {
		this.codiceAvviso = codiceAvviso;
	}


	@Override
	public String toString() {
		return "GetIuvChiamanteEsternoOutput [iuv=" + iuv + ", codiceAvviso=" + codiceAvviso + "]";
	}
 
}
