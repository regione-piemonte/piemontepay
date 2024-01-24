/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.dto.epaymodric.ws;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import it.csi.epay.epaymodric.dto.epaymodric.base.DTOInputBase;


// classe che contiene i dati di input da mandare al webservice

@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(name = "dtoInputWsAttivaElaborazioneConStatiDaEscludere")
public class DTOInputWsAttivaElaborazioneConStatiDaEscludere extends DTOInputBase {

	private static final long serialVersionUID = 1L;

    
    private List<String> statiDaEscludere;

	public DTOInputWsAttivaElaborazioneConStatiDaEscludere() {
		super();
	}


	public List<String> getStatiDaEscludere() {
		return statiDaEscludere;
	}

	public void setStatiDaEscludere(List<String> statiDaEscludere) {
		this.statiDaEscludere = statiDaEscludere;
	}
    
    


}
