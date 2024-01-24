/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.model.utente;

import java.io.Serializable;


public class AssociazioneUtenteCodiceVersamentoVO implements Serializable, Comparable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String codice;

    private String descrizione;

    private Boolean selezionato;

    public AssociazioneUtenteCodiceVersamentoVO () {
        super ();
    }

    public Boolean getSelezionato () {
        return selezionato;
    }

    public void setSelezionato ( Boolean selezionato ) {
        this.selezionato = selezionato;
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
        return "epaycat_r_utente_codice_versamento [id=" + id + ", codice=" + codice + ", descrizione=" + descrizione + ", selezionato=" + selezionato + "]";
    }


	@Override
	public int compareTo ( Object o ) {
			if ( o instanceof AssociazioneUtenteCodiceVersamentoVO ) {
				return this.codice.compareTo ( ( ( AssociazioneUtenteCodiceVersamentoVO ) o ).getCodice () );
			}
		return 0;
	}
}
