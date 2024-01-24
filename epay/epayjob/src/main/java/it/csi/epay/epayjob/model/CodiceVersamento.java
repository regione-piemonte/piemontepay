/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayjob.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
//import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class CodiceVersamento extends Object implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("id")
	private String id;
	
	@JsonProperty("codiceVersamento")
	private String codiceVersamento;
	
	@JsonProperty("desCodiceVeramento")
	private String desCodiceVeramento;
	
	
	@JsonDeserialize(as=ArrayList.class, contentAs=RiferimentoContabile.class)
    private List<RiferimentoContabile> riferimentiContabili;  
	
	

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

	public List<RiferimentoContabile> getRiferimentiContabili() {
		if (null== riferimentiContabili)
			riferimentiContabili = new LinkedList<RiferimentoContabile>();
		return riferimentiContabili;
		
	}

	public void setRiferimentiContabili(List<RiferimentoContabile> riferimentiContabili) {
		this.riferimentiContabili = riferimentiContabili;
	}




}
