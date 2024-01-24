/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogweb.integration.rs;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;

public class ParentInput implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7180376674995036323L;
	@JsonProperty("caller")
    protected CallerInputDto caller;

    /**
     * Recupera il valore della propriet caller.
     * 
     * @return
     *     possible object is
     *     {@link CallerInputDto }
     *     
     */
    public CallerInputDto getCaller() {
        return caller;
    }

    /**
     * Imposta il valore della propriet caller.
     * 
     * @param value
     *     allowed object is
     *     {@link CallerInputDto }
     *     
     */
    public void setCaller(CallerInputDto value) {
        this.caller = value;
    }

}
