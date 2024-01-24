/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epaypa.notificheIoApp;

public class InviaNotificaServiceResponse {
	
	private String codEsito;

    private String desEsito;
    
    public InviaNotificaServiceResponse ( String codEsito, String desEsito ) {
        this.codEsito = codEsito;
        this.desEsito = desEsito;
    }

	public String getCodEsito() {
		return codEsito;
	}

	public void setCodEsito(String codEsito) {
		this.codEsito = codEsito;
	}

	public String getDesEsito() {
		return desEsito;
	}

	public void setDesEsito(String desEsito) {
		this.desEsito = desEsito;
	}

	
	

}
