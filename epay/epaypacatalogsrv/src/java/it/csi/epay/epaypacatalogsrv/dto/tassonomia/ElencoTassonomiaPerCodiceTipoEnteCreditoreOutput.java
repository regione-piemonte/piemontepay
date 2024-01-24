/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto.tassonomia;

import java.io.Serializable;
import java.util.List;

import it.csi.epay.epaypacatalogsrv.dto.ParentOutput;



public class ElencoTassonomiaPerCodiceTipoEnteCreditoreOutput extends ParentOutput implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;


    private List<TassonomiaOutputDto> elencoTassonomia;

    /**
  

	/**
	 * @return the elencoTassonomia
	 */
	public List<TassonomiaOutputDto> getElencoTassonomia() {
		return elencoTassonomia;
	}

	/**
	 * @param elencoTassonomia the elencoTassonomia to set
	 */
	public void setElencoTassonomia(List<TassonomiaOutputDto> elencoTassonomia) {
		this.elencoTassonomia = elencoTassonomia;
	}

    
    

}
