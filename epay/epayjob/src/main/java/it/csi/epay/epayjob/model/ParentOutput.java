/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayjob.model;

import java.io.Serializable;


public class ParentOutput implements Serializable {

    private static final long serialVersionUID = 7433033888177508083L;

    private Integer codiceStato;

    private String codiceEsito;

    private String codiceMessaggio;

    private String descrizioneEsito;

    public String getCodiceMessaggio () {
        return codiceMessaggio;
    }

    public void setCodiceMessaggio ( String codiceMessaggio ) {
        this.codiceMessaggio = codiceMessaggio;
    }

    public Integer getCodiceStato () {
        return codiceStato;
    }

    public void setCodiceStato ( Integer codiceStato ) {
        this.codiceStato = codiceStato;
    }

    public String getCodiceEsito () {
        return codiceEsito;
    }

    public void setCodiceEsito ( String codiceEsito ) {
        this.codiceEsito = codiceEsito;
    }

    public String getDescrizioneEsito () {
        return descrizioneEsito;
    }

    public void setDescrizioneEsito ( String descrizioneEsito ) {
        this.descrizioneEsito = descrizioneEsito;
    }

    @Override
    public String toString () {
        return "ParentOutput [codiceStato=" + codiceStato + ", codiceEsito=" + codiceEsito + ", codiceMessaggio=" + codiceMessaggio + ", descrizioneEsito="
                        + descrizioneEsito + "]";
    }
}
