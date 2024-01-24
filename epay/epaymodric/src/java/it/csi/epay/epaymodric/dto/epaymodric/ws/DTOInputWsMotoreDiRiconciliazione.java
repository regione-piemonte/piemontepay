/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.dto.epaymodric.ws;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import it.csi.epay.epaymodric.dto.epaymodric.base.DTOInputBase;


/**
 *  @author vsgro
 */
@XmlAccessorType ( XmlAccessType.PROPERTY )
@XmlType ( name = "dtoInputWsMotoreDiRiconciliazione" )
public class DTOInputWsMotoreDiRiconciliazione extends DTOInputBase {

    private static final long serialVersionUID = 1L;

    private String idElaborazione;

    private String codiceEnte;

    private List<String> flussiDaElaborare = new ArrayList<> ();

    private String isRiesecuzione;

    public DTOInputWsMotoreDiRiconciliazione () {
        super ();
    }

    public String getIdElaborazione () {
        return idElaborazione;
    }

    public void setIdElaborazione ( String idElaborazione ) {
        this.idElaborazione = idElaborazione;
    }

    public String getCodiceEnte () {
        return codiceEnte;
    }

    public void setCodiceEnte ( String codiceEnte ) {
        this.codiceEnte = codiceEnte;
    }

    public List<String> getFlussiDaElaborare () {
        return flussiDaElaborare;
    }

    public void setFlussiDaElaborare ( List<String> flussiDaElaborare ) {
        this.flussiDaElaborare = flussiDaElaborare;
    }

    public String getIsRiesecuzione () {
        return isRiesecuzione;
    }

    public void setIsRiesecuzione ( String isRiesecuzione ) {
        this.isRiesecuzione = isRiesecuzione;
    }

}
