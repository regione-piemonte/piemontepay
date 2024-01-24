/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.dto.epaymodric.base;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(name = "dtoOutputElaborazioneEstemporanea", propOrder = {"risultatoOutputEstemporanea"})
public class DTOOutputElaborazioneEstemporanea extends DTOBaseContabilizzatore implements Serializable {

	private String risultatoOutputEstemporanea;
	
	private static final long serialVersionUID = 1L;
	
	public DTOOutputElaborazioneEstemporanea() {
		super();
		risultatoOutputEstemporanea ="It's ok";
	}


	public String getRisultatoOutputEstemporanea() {
		return risultatoOutputEstemporanea;
	}


	public void setRisultatoOutputEstemporanea(String risultatoOutputEstemporanea) {
		this.risultatoOutputEstemporanea = risultatoOutputEstemporanea;
	}
}
