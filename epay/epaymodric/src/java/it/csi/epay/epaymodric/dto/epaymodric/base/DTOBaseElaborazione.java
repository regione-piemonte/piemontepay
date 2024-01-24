/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.dto.epaymodric.base;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import it.csi.epay.epaymodric.interfacews.epaymodric.enums.EnteEnum;
import it.csi.epay.epaymodric.interfacews.epaymodric.enums.StatoElaborazioneEnum;

@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(name = "dtoBaseElaborazione", propOrder = {"idEnte","idStato"})
public class DTOBaseElaborazione extends DTOInputBase {

	private static final long serialVersionUID = 1L;

	private String idEnte = EnteEnum.ENTE_SCONOSCIUTO.getCodice ();

	private String idStato = StatoElaborazioneEnum.NON_ATTIVA.getCodice ();

	public DTOBaseElaborazione() {
		super();
	}

    public String getIdEnte () {
        return idEnte;
    }

    public void setIdEnte ( String idEnte ) {
        this.idEnte = idEnte;
    }

    public String getIdStato () {
        return idStato;
    }

    public void setIdStato ( String idStato ) {
        this.idStato = idStato;
    }



}
