/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto;

import java.io.Serializable;

import it.csi.epay.epaypacatalogsrv.model.Sottoscrittore;


public class EsitoVerificaStatoServizioDTO implements Serializable {

    private static final long serialVersionUID = 6632443777179392268L;

    private Boolean ok;

    private String messaggio;

    private Sottoscrittore sottoscrittore;

    public EsitoVerificaStatoServizioDTO () {
        super ();
    }

    public EsitoVerificaStatoServizioDTO ( Sottoscrittore sottoscrittore, Boolean ok, String messaggio ) {
        super ();
        this.ok = ok;
        this.messaggio = messaggio;
        this.sottoscrittore = sottoscrittore;
    }

    public Sottoscrittore getSottoscrittore () {
        return sottoscrittore;
    }

    public void setSottoscrittore ( Sottoscrittore sottoscrittore ) {
        this.sottoscrittore = sottoscrittore;
    }

    public Boolean getOk () {
        return ok;
    }

    public void setOk ( Boolean ok ) {
        this.ok = ok;
    }

    public String getMessaggio () {
        return messaggio;
    }

    public void setMessaggio ( String messaggio ) {
        this.messaggio = messaggio;
    }

}
