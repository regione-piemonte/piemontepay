/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

public class QuietanzaEsito implements Serializable {

    private static final long serialVersionUID = -4798523297268806406L;

    private Long idQuietanzaEsito;
    private byte[] ricevutaPdf;

    private Date dataOraCreazione;

    private String origineInserimento;

    public Long getIdQuietanzaEsito () {
        return idQuietanzaEsito;
    }

    public void setIdQuietanzaEsito ( Long idQuietanzaEsito ) {
        this.idQuietanzaEsito = idQuietanzaEsito;
    }

    public byte [] getRicevutaPdf () {
        return ricevutaPdf;
    }

    public void setRicevutaPdf ( byte [] ricevutaPdf ) {
        this.ricevutaPdf = ricevutaPdf;
    }


    public String getOrigineInserimento () {
        return origineInserimento;
    }

    public void setOrigineInserimento ( String origineInserimento ) {
        this.origineInserimento = origineInserimento;
    }

    @Override
    public String toString () {
        return "QuietanzaEsito [idQuietanzaEsito=" + idQuietanzaEsito + ", ricevutaPdf=" + Arrays.toString ( ricevutaPdf )
            + ", dataOraCreazione=" + dataOraCreazione + ", origineInserimento=" + origineInserimento + "]";
    }

    public Date getDataOraCreazione () {
        return dataOraCreazione;
    }

    public void setDataOraCreazione ( Date dataOraCreazione ) {
        this.dataOraCreazione = dataOraCreazione;
    }

}
