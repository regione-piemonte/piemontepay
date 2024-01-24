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


// classe che contiene i dati di input da mandare al webservice

@XmlAccessorType ( XmlAccessType.PROPERTY )
@XmlType ( name = "dtoInputWsElaborazione" )
public class DTOInputWsElaborazione extends DTOInputBase {

    private static final long serialVersionUID = 1L;

    private String codiceFiscaleEnte;

    private List<String> flussiDaElaborare = new ArrayList<> ();

    private String statoDaImpostare;

    private String utenteElaborazione;

    private String idElaborazionePrecedente;

    private boolean isRiesecuzione;

    public DTOInputWsElaborazione () {
        super ();
    }

    public List<String> getFlussiDaElaborare () {
        return flussiDaElaborare;
    }

    public void setFlussiDaElaborare ( List<String> flussiDaElaborare ) {
        this.flussiDaElaborare = flussiDaElaborare;
    }

    public String getStatoDaImpostare () {
        return statoDaImpostare;
    }

    public void setStatoDaImpostare ( String statoDaImpostare ) {
        this.statoDaImpostare = statoDaImpostare;
    }

    public String getCodiceFiscaleEnte () {
        return codiceFiscaleEnte;
    }

    public void setCodiceFiscaleEnte ( String codiceFiscaleEnte ) {
        this.codiceFiscaleEnte = codiceFiscaleEnte;
    }

    public String getUtenteElaborazione () {
        return utenteElaborazione;
    }

    public void setUtenteElaborazione ( String utenteElaborazione ) {
        this.utenteElaborazione = utenteElaborazione;
    }

    public String getIdElaborazionePrecedente () {
        return idElaborazionePrecedente;
    }

    public void setIdElaborazionePrecedente ( String idElaborazionePrecedente ) {
        this.idElaborazionePrecedente = idElaborazionePrecedente;
    }

    public boolean isRiesecuzione () {
        return isRiesecuzione;
    }

    public void setRiesecuzione ( boolean isRiesecuzione ) {
        this.isRiesecuzione = isRiesecuzione;
    }

}
