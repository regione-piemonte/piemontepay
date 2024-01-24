/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto.utente;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class AggiornaTematicheUtenteTematicaInputDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String codice;

    private List<Long> idCodiciVersamento = new ArrayList<> ();

    private Boolean flagVisibilitaTotale;

    public static AggiornaTematicheUtenteTematicaInputDto conVisibilitaCompleta ( String codice ) {
        AggiornaTematicheUtenteTematicaInputDto t1 = new AggiornaTematicheUtenteTematicaInputDto ();
        t1.setCodice ( codice );
        t1.setFlagVisibilitaTotale ( true );
        t1.setIdCodiciVersamento ( new ArrayList<> () );
        return t1;
    }

    public static AggiornaTematicheUtenteTematicaInputDto conVisibilitaParziale ( String codice ) {
        AggiornaTematicheUtenteTematicaInputDto t1 = new AggiornaTematicheUtenteTematicaInputDto ();
        t1.setCodice ( codice );
        t1.setFlagVisibilitaTotale ( false );
        t1.setIdCodiciVersamento ( new ArrayList<> () );
        return t1;
    }

    public Boolean getFlagVisibilitaTotale () {
        return flagVisibilitaTotale;
    }

    public void setFlagVisibilitaTotale ( Boolean flagVisibilitaTotale ) {
        this.flagVisibilitaTotale = flagVisibilitaTotale;
    }

    public String getCodice () {
        return codice;
    }

    public void setCodice ( String codice ) {
        this.codice = codice;
    }

    public List<Long> getIdCodiciVersamento () {
        return idCodiciVersamento;
    }

    public void setIdCodiciVersamento ( List<Long> idCodiciVersamento ) {
        this.idCodiciVersamento = idCodiciVersamento;
    }

    @Override
    public String toString () {
        return "AggiornaTematicheUtenteTematicaInputDto [codice=" + codice + ", idCodiciVersamento=" + idCodiciVersamento + ", flagVisibilitaTotale="
            + flagVisibilitaTotale + "]";
    }

}
