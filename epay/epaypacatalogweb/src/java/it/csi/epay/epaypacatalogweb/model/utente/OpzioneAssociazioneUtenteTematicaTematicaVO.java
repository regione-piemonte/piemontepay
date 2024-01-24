/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.model.utente;

import java.io.Serializable;
import java.util.List;


public class OpzioneAssociazioneUtenteTematicaTematicaVO implements Serializable, Comparable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String codice;

    private String descrizione;

    private List<OpzioneAssociazioneUtenteTematicaCodiceVersamentoVO> codiciVersamento;

    public List<OpzioneAssociazioneUtenteTematicaCodiceVersamentoVO> getCodiciVersamento () {
        return codiciVersamento;
    }

    public void setCodiciVersamento ( List<OpzioneAssociazioneUtenteTematicaCodiceVersamentoVO> codiciVersamento ) {
        this.codiciVersamento = codiciVersamento;
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
	public int compareTo(Object o) {
		if ( o instanceof OpzioneAssociazioneUtenteTematicaTematicaVO ) {
			return this.codice.compareTo ( ( ( OpzioneAssociazioneUtenteTematicaTematicaVO ) o ).getCodice () );
		}
		return 0;
	}
}
