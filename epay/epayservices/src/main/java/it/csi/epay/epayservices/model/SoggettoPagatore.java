/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.model;

import java.io.Serializable;


public class SoggettoPagatore implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    PersonaFisica personaFisica;

    PersonaGiuridica personaGiuridica;

    String identificativoUnivocoFiscale;

    String indirizzo;

    String civico;

    String cap;

    String localita;

    String provincia;

    String nazione;

    String email;

    public PersonaGiuridica getPersonaGiuridica () {
        return personaGiuridica;
    }

    public void setPersonaGiuridica ( PersonaGiuridica personaGiuridica ) {
        this.personaGiuridica = personaGiuridica;
    }

    public String getIdentificativoUnivocoFiscale () {
        return identificativoUnivocoFiscale;
    }

    public void setIdentificativoUnivocoFiscale ( String identificativoUnivocoFiscale ) {
        this.identificativoUnivocoFiscale = identificativoUnivocoFiscale;
    }

    public String getIndirizzo () {
        return indirizzo;
    }

    public void setIndirizzo ( String indirizzo ) {
        this.indirizzo = indirizzo;
    }

    public String getCivico () {
        return civico;
    }

    public void setCivico ( String civico ) {
        this.civico = civico;
    }

    public String getCap () {
        return cap;
    }

    public void setCap ( String cap ) {
        this.cap = cap;
    }

    public String getLocalita () {
        return localita;
    }

    public void setLocalita ( String localita ) {
        this.localita = localita;
    }

    public String getProvincia () {
        return provincia;
    }

    public void setProvincia ( String provincia ) {
        this.provincia = provincia;
    }

    public String getNazione () {
        return nazione;
    }

    public void setNazione ( String nazione ) {
        this.nazione = nazione;
    }

    public String getEmail () {
        return email;
    }

    public void setEmail ( String email ) {
        this.email = email;
    }


    public PersonaFisica getPersonaFisica () {
        return personaFisica;
    }

    public void setPersonaFisica ( PersonaFisica personaFisica ) {
        this.personaFisica = personaFisica;
    }

}
