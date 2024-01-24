/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.model.utente;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class AssociazioneUtenteTematicaVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String codice;

    private String descrizione;

    private Boolean flagVisibilitaTotale;

    private List<AssociazioneUtenteCodiceVersamentoVO> codiciVersamento;

    public AssociazioneUtenteTematicaVO () {
        super ();
        // TODO Auto-generated constructor stub
        codiciVersamento = new ArrayList<> ();
    }

    public boolean almenoUnCodiceVersamentoSelezionato () {
        if ( codiciVersamento != null ) {
            for ( AssociazioneUtenteCodiceVersamentoVO cv: codiciVersamento ) {
                if ( cv.getSelezionato () != null && cv.getSelezionato () ) {
                    return true;
                }
            }
        }

        return false;
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

    public Boolean getFlagVisibilitaTotale () {
        return flagVisibilitaTotale;
    }

    public void setFlagVisibilitaTotale ( Boolean flagVisibilitaTotale ) {
        this.flagVisibilitaTotale = flagVisibilitaTotale;
    }

    public List<AssociazioneUtenteCodiceVersamentoVO> getCodiciVersamento () {
        return codiciVersamento;
    }

    public void setCodiciVersamento ( List<AssociazioneUtenteCodiceVersamentoVO> codiciVersamento ) {
        this.codiciVersamento = codiciVersamento;
    }

    @Override
    public String toString () {
        return "epaycat_r_utente_tematica [id=" + id + ", codice=" + codice + ", descrizione=" + descrizione + ", flagVisibilitaTotale="
            + flagVisibilitaTotale + ", codiciVersamento=" + codiciVersamento + "]";
    }

}
