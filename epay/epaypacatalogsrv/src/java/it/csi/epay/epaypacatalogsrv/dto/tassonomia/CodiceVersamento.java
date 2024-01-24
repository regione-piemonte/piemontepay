/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto.tassonomia;
import java.io.Serializable;


public class CodiceVersamento implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private String codiceVersamento;
	
	private String desCodiceVeramento;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCodiceVersamento() {
		return codiceVersamento;
	}

	public void setCodiceVersamento(String codiceVersamento) {
		this.codiceVersamento = codiceVersamento;
	}

	public String getDesCodiceVeramento() {
		return desCodiceVeramento;
	}

	public void setDesCodiceVeramento(String desCodiceVeramento) {
		this.desCodiceVeramento = desCodiceVeramento;
	}
	
	
	
	
    

    

}
