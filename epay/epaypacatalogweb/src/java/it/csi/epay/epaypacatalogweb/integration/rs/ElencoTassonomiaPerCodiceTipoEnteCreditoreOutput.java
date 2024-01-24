/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.integration.rs;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;



public class ElencoTassonomiaPerCodiceTipoEnteCreditoreOutput extends ParentOutput implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

   
    @JsonProperty("elencoTassonomia")
    private List<TassonomiaDto> elencoTassonomia;

    	/**
	 * @return the elencoTassonomia
	 */
	public List<TassonomiaDto> getElencoTassonomia() {
		return elencoTassonomia;
	}

	/**
	 * @param elencoTassonomia the elencoTassonomia to set
	 */
	public void setElencoTassonomia(List<TassonomiaDto> elencoTassonomia) {
		this.elencoTassonomia = elencoTassonomia;
	}

    
    

}
