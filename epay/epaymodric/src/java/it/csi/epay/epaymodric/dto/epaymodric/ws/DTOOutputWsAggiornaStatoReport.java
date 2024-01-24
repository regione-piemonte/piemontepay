/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.dto.epaymodric.ws;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import it.csi.epay.epaymodric.dto.epaymodric.base.DTOFileReport;

@XmlAccessorType ( XmlAccessType.PROPERTY )
@XmlType ( name = "dtoOutputWsAggiornaStatoReport" )
public class DTOOutputWsAggiornaStatoReport implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codiceErrore;
	private String descrizione;
	private String esito;
	private DTOFileReport file;
	
	public DTOOutputWsAggiornaStatoReport() {
		
	}
	
	public String getCodiceErrore() {
		return codiceErrore;
	}
	public void setCodiceErrore(String codiceErrore) {
		this.codiceErrore = codiceErrore;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getEsito() {
		return esito;
	}
	public void setEsito(String esito) {
		this.esito = esito;
	}

	public DTOFileReport getFile() {
		return file;
	}

	public void setFile(DTOFileReport file) {
		this.file = file;
	}
	
}
