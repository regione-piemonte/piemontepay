/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.vo;

public class FunzioneVO {

    private Long id;

    private String codice;

    private String descrizione;

    private String codiceCategoria;

    private String descrizioneCategoria;

    public FunzioneVO () {
        super ();
    }

    public Long getId () {
        return id;
    }

    public void setId ( Long id ) {
        this.id = id;
    }

    public String getCodice () {
        return codice;
    }

    public void setCodice ( String codice ) {
        this.codice = codice;
    }

    public String getDescrizione () {
        return descrizione;
    }

    public void setDescrizione ( String descrizione ) {
        this.descrizione = descrizione;
    }

    public String getCodiceCategoria () {
        return codiceCategoria;
    }

    public void setCodiceCategoria ( String codiceCategoria ) {
        this.codiceCategoria = codiceCategoria;
    }

    public String getDescrizioneCategoria () {
        return descrizioneCategoria;
    }

    public void setDescrizioneCategoria ( String descrizioneCategoria ) {
        this.descrizioneCategoria = descrizioneCategoria;
    }

    @Override
    public String toString () {
        return "FunzioneVO [id=" + id + ", codice=" + codice + ", descrizione=" + descrizione + ", codiceCategoria=" + codiceCategoria
            + ", descrizioneCategoria=" + descrizioneCategoria + "]";
    }

}
