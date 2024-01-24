/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayservices.model.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import it.csi.epay.epayservices.model.AccessoChiamanteEsternoSincronoSplitOutput;

import java.io.Serializable;
import java.util.List;


@SuppressWarnings ( "unused" )
public class PagamentoMarcaBolloChiamanteEsternoOutput extends AccessoChiamanteEsternoSincronoSplitOutput implements Serializable {

	private static final long serialVersionUID = 8377937832375101313L;

	@JsonProperty ( "paymentUrl" )
	private String paymentUrl;

	private String iuvDocumento;

	private List<String> elencoIuvMarcaBollo;

	
    public String getPaymentUrl () {
        return paymentUrl;
    }

    
    public void setPaymentUrl ( String paymentUrl ) {
        this.paymentUrl = paymentUrl;
    }

    public String getIuvDocumento () {
		return iuvDocumento;
	}

	public void setIuvDocumento ( String iuvDocumento ) {
		this.iuvDocumento = iuvDocumento;
	}

	public List<String> getElencoIuvMarcaBollo () {
		return elencoIuvMarcaBollo;
	}

	public void setElencoIuvMarcaBollo ( List<String> elencoIuvMarcaBollo ) {
		this.elencoIuvMarcaBollo = elencoIuvMarcaBollo;
	}

	@Override
	public String toString () {
		return "PagamentoIuvChiamanteEsternoOutput ["
						+ ( paymentUrl != null ? "paymentUrl=" + paymentUrl + ", " : "" )
						+ "]";
	}

}
