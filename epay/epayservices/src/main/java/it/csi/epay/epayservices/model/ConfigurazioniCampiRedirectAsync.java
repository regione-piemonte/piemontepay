/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayservices.model;

import java.io.Serializable;

/**
 *
 */

public class ConfigurazioniCampiRedirectAsync  implements Serializable{

    private static final long serialVersionUID = 5666905238693921699L;

    private String id;
    private String campoInterfaccia;
    private String descrizione;
    private Boolean flgAbilitato;

    public String getId () {
        return id;
    }

    public void setId ( String id ) {
        this.id = id;
    }

    public String getCampoInterfaccia () {
        return campoInterfaccia;
    }


    public void setCampoInterfaccia ( String campoInterfaccia ) {
        this.campoInterfaccia = campoInterfaccia;
    }

    public String getDescrizione () {
        return descrizione;
    }

    public void setDescrizione ( String descrizione ) {
        this.descrizione = descrizione;
    }

    public Boolean getFlgAbilitato () {
        return flgAbilitato;
    }

    public void setFlgAbilitato ( Boolean flgAbilitato ) {
        this.flgAbilitato = flgAbilitato;
    }

}
