/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.dto.epaymodric.ws;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType ( XmlAccessType.PROPERTY )
@XmlType ( name = "dtoOutputWsRicercaLimiteEsportazione" )
public class DTOOutputWsRicecaLimiteEsportazione implements Serializable{
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String esito;
	private String numMaxRecordsReport;
	public String getEsito() {
		return esito;
	}
	public void setEsito(String esito) {
		this.esito = esito;
	}
	public String getNumMaxRecordsReport() {
		return numMaxRecordsReport;
	}
	public void setNumMaxRecordsReport(String numMaxRecordsReport) {
		this.numMaxRecordsReport = numMaxRecordsReport;
	}
	
		
}
