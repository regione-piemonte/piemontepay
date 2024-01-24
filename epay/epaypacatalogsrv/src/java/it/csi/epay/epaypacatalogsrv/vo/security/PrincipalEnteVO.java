/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.vo.security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class PrincipalEnteVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String codiceFiscale;

    private String denominazione;

    private String indirizzo;

    private String localita;

    private List<Long> admins = new ArrayList<Long>();

    private String descrizioneUtenteAmministratore;

    private String email;

    public String getEmail () {
        return email;
    }

    @Override
    public String toString () {
        return "PrincipalEnteVO [id=" + id + ", codiceFiscale=" + codiceFiscale + ", denominazione=" + denominazione + ", indirizzo=" + indirizzo
            + ", localita=" + localita + ", admins=" + admins + ", descrizioneUtenteAmministratore=" + descrizioneUtenteAmministratore + ", email=" + email
            + "]";
    }

    public void setEmail ( String email ) {
        this.email = email;
    }

    public Long getId () {
        return id;
    }

    public void setId ( Long id ) {
        this.id = id;
    }

    public String getCodiceFiscale () {
        return codiceFiscale;
    }

    public void setCodiceFiscale ( String codiceFiscale ) {
        this.codiceFiscale = codiceFiscale;
    }

    public String getDenominazione () {
        return denominazione;
    }

    public void setDenominazione ( String denominazione ) {
        this.denominazione = denominazione;
    }

    public String getIndirizzo () {
        return indirizzo;
    }

    public void setIndirizzo ( String indirizzo ) {
        this.indirizzo = indirizzo;
    }

    public String getLocalita () {
        return localita;
    }

    public void setLocalita ( String localita ) {
        this.localita = localita;
    }

    public String getDescrizioneUtenteAmministratore () {
        return descrizioneUtenteAmministratore;
    }

    public void setDescrizioneUtenteAmministratore ( String descrizioneUtenteAmministratore ) {
        this.descrizioneUtenteAmministratore = descrizioneUtenteAmministratore;
    }

    
    public List<Long> getAdmins () {
        return admins;
    }

    
    public void setAdmins ( List<Long> admins ) {
        this.admins = admins;
    }

}
