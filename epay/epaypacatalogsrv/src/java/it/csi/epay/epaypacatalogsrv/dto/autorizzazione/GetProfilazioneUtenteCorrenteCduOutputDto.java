/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto.autorizzazione;

import java.io.Serializable;


public class GetProfilazioneUtenteCorrenteCduOutputDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String codiceCategoria;

    private String descrizioneCategoria;

    private String codice;

    private String descrizione;

    @Override
    public String toString () {
        return "GetInfoUtenteCduOutputDto [id=" + id + ", codiceCategoria=" + codiceCategoria + ", descrizioneCategoria=" + descrizioneCategoria + ", codice="
            + codice + ", descrizione=" + descrizione + "]";
    }

    public String getDescrizioneCategoria () {
        return descrizioneCategoria;
    }

    public void setDescrizioneCategoria ( String descrizioneCategoria ) {
        this.descrizioneCategoria = descrizioneCategoria;
    }

    public Long getId () {
        return id;
    }

    public void setId ( Long id ) {
        this.id = id;
    }

    public String getCodiceCategoria () {
        return codiceCategoria;
    }

    public void setCodiceCategoria ( String codiceCategoria ) {
        this.codiceCategoria = codiceCategoria;
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

}
