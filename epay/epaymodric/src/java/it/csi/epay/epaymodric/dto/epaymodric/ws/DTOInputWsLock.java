/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.dto.epaymodric.ws;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import it.csi.epay.epaymodric.dto.epaymodric.base.DTOInputBase;


@XmlAccessorType ( XmlAccessType.PROPERTY )
@XmlType ( name = "dtoInputWsLock" )
public class DTOInputWsLock extends DTOInputBase {

    private static final long serialVersionUID = 1L;

    private String idUtente;

    private Date dataInizio;

    private Date dataFine;

    private String codiceFiscaleEnte;

    public String getCodiceFiscaleEnte () {
        return codiceFiscaleEnte;
    }

    public void setCodiceFiscaleEnte ( String codiceFiscaleEnte ) {
        this.codiceFiscaleEnte = codiceFiscaleEnte;
    }

    public Date getDataFine () {
        return dataFine;
    }

    public void setDataFine ( Date dataFine ) {
        this.dataFine = dataFine;
    }

    public Date getDataInizio () {
        return dataInizio;
    }

    public void setDataInizio ( Date dataInizio ) {
        this.dataInizio = dataInizio;
    }

    public String getIdUtente () {
        return idUtente;
    }

    public void setIdUtente ( String idUtente ) {
        this.idUtente = idUtente;
    }

}
