/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.dto.epaymodric.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import it.csi.epay.epaymodric.dto.epaymodric.base.DTOInputBase;

@XmlAccessorType ( XmlAccessType.PROPERTY )
@XmlType ( name = "dtoInputWsAggiornaStatoReport" )
public class DTOInputWsAggiornaStatoReport extends DTOInputBase{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long idExport;
	
	public DTOInputWsAggiornaStatoReport() {}
	
	public Long getIdExport() {
		return idExport;
	}
	public void setIdExport(Long idExport) {
		this.idExport = idExport;
	}
	
}
