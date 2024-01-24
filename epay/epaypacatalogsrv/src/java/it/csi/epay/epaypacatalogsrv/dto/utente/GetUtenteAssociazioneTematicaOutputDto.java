/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto.utente;

import java.io.Serializable;
import java.util.List;


public class GetUtenteAssociazioneTematicaOutputDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String codice;

    private String descrizione;

    private Boolean flagVisibilitaTotale;

    private List<GetUtenteAssociazioneCodiceVersamentoOutputDto> codiciVersamento;

    public List<GetUtenteAssociazioneCodiceVersamentoOutputDto> getCodiciVersamento () {
        return codiciVersamento;
    }

    public void setCodiciVersamento ( List<GetUtenteAssociazioneCodiceVersamentoOutputDto> codiciVersamento ) {
        this.codiciVersamento = codiciVersamento;
    }

    public Boolean getFlagVisibilitaTotale () {
        return flagVisibilitaTotale;
    }

    public void setFlagVisibilitaTotale ( Boolean flagVisibilitaTotale ) {
        this.flagVisibilitaTotale = flagVisibilitaTotale;
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

    @Override
    public String toString () {
        return "GetUtenteAssociazioneTematicaOutputDto [id=" + id + ", codice=" + codice + ", descrizione=" + descrizione + ", flagVisibilitaTotale="
            + flagVisibilitaTotale + ", codiciVersamento=" + codiciVersamento + "]";
    }

}
