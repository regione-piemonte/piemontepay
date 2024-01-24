/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.dto.epaymodric.ws;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType ( XmlAccessType.PROPERTY )
@XmlType ( name = "dtoOutputWsLock" )
public class DTOOutputWsLock {

    private Long id;

    public Long getId () {
        return id;
    }

    public void setId ( Long id ) {
        this.id = id;
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

    public String getIdEnte () {
        return idEnte;
    }

    public void setIdEnte ( String idEnte ) {
        this.idEnte = idEnte;
    }

    private String descrizioneEnte;

    public String getDescrizioneEnte () {
        return descrizioneEnte;
    }

    public void setDescrizioneEnte ( String descrizioneEnte ) {
        this.descrizioneEnte = descrizioneEnte;
    }

    private Date dataFine;

    private Date dataInizio;

    private String idUtente;

    private String idEnte;

}
