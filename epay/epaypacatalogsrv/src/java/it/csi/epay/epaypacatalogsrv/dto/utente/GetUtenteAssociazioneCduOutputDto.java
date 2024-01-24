/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto.utente;

import java.io.Serializable;


public class GetUtenteAssociazioneCduOutputDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String codice;

    private String codiceCategoria;

    private String descrizione;

    private String descrizioneCategoria;

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

    public String getCodiceCategoria () {
        return codiceCategoria;
    }

    public void setCodiceCategoria ( String codiceCategoria ) {
        this.codiceCategoria = codiceCategoria;
    }

    public String getDescrizione () {
        return descrizione;
    }

    public void setDescrizione ( String descrizione ) {
        this.descrizione = descrizione;
    }

    public String getDescrizioneCategoria () {
        return descrizioneCategoria;
    }

    public void setDescrizioneCategoria ( String descrizioneCategoria ) {
        this.descrizioneCategoria = descrizioneCategoria;
    }

    @Override
    public String toString () {
        return "GetUtenteAssociazioneCduOutputDto [id=" + id + ", codice=" + codice + ", codiceCategoria=" + codiceCategoria + ", descrizione=" + descrizione
            + ", descrizioneCategoria=" + descrizioneCategoria + "]";
    }

}
