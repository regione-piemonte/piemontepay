/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto;

import java.io.Serializable;

import it.csi.epay.epaypacatalogsrv.dto.enums.EsitoRollbackFruitore;


public class EsitoPropagazioneRollbackDTO implements Serializable {

    private static final long serialVersionUID = 6632443777179392268L;

    private EsitoRollbackFruitore esito;

    private String messaggio;

    public EsitoPropagazioneRollbackDTO () {
        super ();
    }

    public EsitoPropagazioneRollbackDTO ( EsitoRollbackFruitore esito, String messaggio ) {
        super ();
        this.esito = esito;
        this.messaggio = messaggio;
    }

    public EsitoRollbackFruitore getEsito () {
        return esito;
    }

    public void setEsito ( EsitoRollbackFruitore esito ) {
        this.esito = esito;
    }

    public String getMessaggio () {
        return messaggio;
    }

    public void setMessaggio ( String messaggio ) {
        this.messaggio = messaggio;
    }

}
