/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto;

import java.io.Serializable;

import it.csi.epay.epaypacatalogsrv.dto.enums.EsitoInvioFruitore;


public class EsitoPropagazioneDatiDTO implements Serializable {

    private static final long serialVersionUID = 6632443777179392268L;

    private EsitoInvioFruitore esito;

    private String messaggio;

    public EsitoPropagazioneDatiDTO () {
        super ();
    }

    public EsitoPropagazioneDatiDTO ( EsitoInvioFruitore esito, String messaggio ) {
        super ();
        this.esito = esito;
        this.messaggio = messaggio;
    }

    public EsitoInvioFruitore getEsito () {
        return esito;
    }

    public void setEsito ( EsitoInvioFruitore esito ) {
        this.esito = esito;
    }

    public String getMessaggio () {
        return messaggio;
    }

    public void setMessaggio ( String messaggio ) {
        this.messaggio = messaggio;
    }

}
