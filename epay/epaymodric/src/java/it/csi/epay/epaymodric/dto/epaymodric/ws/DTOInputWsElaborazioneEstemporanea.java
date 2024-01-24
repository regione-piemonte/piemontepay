/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.dto.epaymodric.ws;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import it.csi.epay.epaymodric.dto.epaymodric.base.DTOBaseElaborazione;

// classe che contiene i dati di input da mandare al webservice

@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(name = "dtoInputWsElaborazioneEstemporanea")
public class DTOInputWsElaborazioneEstemporanea extends DTOBaseElaborazione implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public DTOInputWsElaborazioneEstemporanea() {
		super();
	}

}
