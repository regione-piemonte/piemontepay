/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class ComponentiImportoOutput implements Serializable {
	
	private static final long serialVersionUID = 1L;

	List<SingoloComponenteImportoOutput> componentiImportoList = new LinkedList<SingoloComponenteImportoOutput>();
	
	private String codiceEsito;
	
	private String descrizioneEsito;

	public List<SingoloComponenteImportoOutput> getComponentiImportoList() {
		return componentiImportoList;
	}

	public void setComponentiImportoList(List<SingoloComponenteImportoOutput> componentiImportoList) {
		this.componentiImportoList = componentiImportoList;
	}

	public String getCodiceEsito() {
		return codiceEsito;
	}

	public void setCodiceEsito(String codiceEsito) {
		this.codiceEsito = codiceEsito;
	}

	public String getDescrizioneEsito() {
		return descrizioneEsito;
	}

	public void setDescrizioneEsito(String descrizioneEsito) {
		this.descrizioneEsito = descrizioneEsito;
	}

}
