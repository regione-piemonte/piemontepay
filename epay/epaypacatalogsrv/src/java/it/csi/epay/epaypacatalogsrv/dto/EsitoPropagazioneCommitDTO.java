/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto;

import java.io.Serializable;

import it.csi.epay.epaypacatalogsrv.dto.enums.EsitoCommitFruitore;


public class EsitoPropagazioneCommitDTO implements Serializable {

    private static final long serialVersionUID = 6632443777179392268L;

    private EsitoCommitFruitore esito;

    private String messaggio;

    public EsitoPropagazioneCommitDTO () {
        super ();
    }

    public EsitoPropagazioneCommitDTO ( EsitoCommitFruitore esito, String messaggio ) {
        super ();
        this.esito = esito;
        this.messaggio = messaggio;
    }

    public EsitoCommitFruitore getEsito () {
        return esito;
    }

    public void setEsito ( EsitoCommitFruitore esito ) {
        this.esito = esito;
    }

    public String getMessaggio () {
        return messaggio;
    }

    public void setMessaggio ( String messaggio ) {
        this.messaggio = messaggio;
    }

}
