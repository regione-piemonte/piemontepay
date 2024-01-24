/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.dto.epaymodric.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import it.csi.epay.epaymodric.dto.epaymodric.base.DTOInputBase;


// classe che contiene i dati di input da mandare al webservice

@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(name = "dtoInputWsAttivaElaborazione")
public class DTOInputWsAttivaElaborazione extends DTOInputBase {

	private static final long serialVersionUID = 1L;

    private String codiceFiscaleEnte;

    private Long idElaborazionePrecedente;

    private Boolean daFrontend;

	public DTOInputWsAttivaElaborazione() {
		super();
	}

    public String getCodiceFiscaleEnte () {
        return codiceFiscaleEnte;
    }

    public void setCodiceFiscaleEnte ( String codiceFiscaleEnte ) {
        this.codiceFiscaleEnte = codiceFiscaleEnte;
    }

    public Boolean getDaFrontend () {
        return daFrontend;
    }

    public void setDaFrontend ( Boolean daFrontend ) {
        this.daFrontend = daFrontend;
    }

    public Long getIdElaborazionePrecedente () {
        return idElaborazionePrecedente;
    }

    public void setIdElaborazionePrecedente ( Long idElaborazionePrecedente ) {
        this.idElaborazionePrecedente = idElaborazionePrecedente;
    }


}
