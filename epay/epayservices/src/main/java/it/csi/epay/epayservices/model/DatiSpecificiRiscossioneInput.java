/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DatiSpecificiRiscossioneInput implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@JsonProperty("codiceFiscaleEnte")
	private String codiceFiscaleEnte;
	
	@JsonProperty("tipoPagamento")
	private String tipoPagamento;
	
	@JsonProperty("annoEsercizio")
    private Integer annoEsercizio;
	
	
	
	public String getCodiceFiscaleEnte() {
		return codiceFiscaleEnte;
	}

	public void setCodiceFiscaleEnte(String codiceFiscaleEnte) {
		this.codiceFiscaleEnte = codiceFiscaleEnte;
	}

	public String getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(String tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}
    
    public Integer getAnnoEsercizio () {
        return annoEsercizio;
    }
    
    public void setAnnoEsercizio ( Integer annoEsercizio ) {
        this.annoEsercizio = annoEsercizio;
    }

	


}
