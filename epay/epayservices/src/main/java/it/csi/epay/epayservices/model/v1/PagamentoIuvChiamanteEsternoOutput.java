/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayservices.model.v1;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;


public class PagamentoIuvChiamanteEsternoOutput extends AccessoChiamanteEsternoSincronoSplitOutput implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty ( "paymentUrl" )
	private String paymentUrl;
	
	private String iuv;

	
    public String getPaymentUrl () {
        return paymentUrl;
    }

    
    public void setPaymentUrl ( String paymentUrl ) {
        this.paymentUrl = paymentUrl;
    }

    
    public String getIuv () {
        return iuv;
    }

    
    public void setIuv ( String iuv ) {
        this.iuv = iuv;
    }

    @Override
	public String toString () {
		return "PagamentoIuvChiamanteEsternoOutput ["
						+ ( paymentUrl != null ? "paymentUrl=" + paymentUrl + ", " : "" )
						
						+ ( iuv != null ? "iuv=" + iuv + ", " : "" )
						+ "]";
	}
    

}
