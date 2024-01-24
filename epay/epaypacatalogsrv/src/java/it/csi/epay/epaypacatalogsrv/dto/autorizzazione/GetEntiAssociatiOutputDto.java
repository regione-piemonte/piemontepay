/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto.autorizzazione;

import java.io.Serializable;


public class GetEntiAssociatiOutputDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String codiceFiscale;

    private String denominazione;

    private String email;

    public Long getId () {
        return id;
    }

    public void setId ( Long id ) {
        this.id = id;
    }

    public String getEmail () {
        return email;
    }

    public void setEmail ( String email ) {
        this.email = email;
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

    @Override
    public String toString () {
        return "GetEntiAssociatiOutputDto [codiceFiscale=" + codiceFiscale + ", denominazione=" + denominazione + "]";
    }

}
