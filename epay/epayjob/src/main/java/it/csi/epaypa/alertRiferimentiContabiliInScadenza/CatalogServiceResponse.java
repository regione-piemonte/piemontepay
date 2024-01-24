/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epaypa.alertRiferimentiContabiliInScadenza;

public class CatalogServiceResponse {
	
	private String codiceEsito;

    private String desEsito;
    
    private String data;
    
    public CatalogServiceResponse ()
    {
    	
    }
    
    public CatalogServiceResponse ( String codiceEsito, String desEsito ) {
        this.codiceEsito = codiceEsito;
        this.desEsito = desEsito;
    }


	public String getCodiceEsito() {
		return codiceEsito;
	}

	public void setCodiceEsito(String codiceEsito) {
		this.codiceEsito = codiceEsito;
	}

	public String getDesEsito() {
		return desEsito;
	}

	public void setDesEsito(String desEsito) {
		this.desEsito = desEsito;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	
	
	

	
	

}
