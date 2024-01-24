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
@XmlType ( name = "dtoOutputWsAggiornaProvvisori" )
public class DTOOutputWsAggiornaProvvisori implements Serializable{
	
	private static final long serialVersionUID = 2158554345569096657L;
	
	private DTOOutputWsEsito dtoEsito;
	
	public DTOOutputWsAggiornaProvvisori() { }

	public DTOOutputWsEsito getDtoEsito() {
		return dtoEsito;
	}

	public void setDtoEsito(DTOOutputWsEsito dtoEsito) {
		this.dtoEsito = dtoEsito;
	}

	
}
