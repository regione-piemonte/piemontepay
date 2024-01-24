/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto.tassonomia;

import java.util.List;

import it.csi.epay.epaypacatalogsrv.dto.ParentOutput;


/**
 * @author manue
 *
 */
public class RicercaTassonomiaOutput extends ParentOutput {

    private static final long serialVersionUID = 1L;

    private Integer numeroRisultatiTotali;

    private List<TassonomiaOutputDto> risultati;

    public static RicercaTassonomiaOutput ok ( List<TassonomiaOutputDto> risultati ) {
        RicercaTassonomiaOutput output = ok ( RicercaTassonomiaOutput.class );
        output.setNumeroRisultatiTotali ( risultati.size () );
        output.setRisultati ( risultati );
        output.setDescrizioneEsito ( risultati.size () + " risultati" );
        return output;
    }

    @Override
    public String toString () {
        return "RicercaRiferimentoContabileOutput [numeroRisultatiTotali=" + numeroRisultatiTotali + ", risultati=" + risultati + "]";
    }

    public Integer getNumeroRisultatiTotali () {
        return numeroRisultatiTotali;
    }

    public void setNumeroRisultatiTotali ( Integer numeroRisultatiTotali ) {
        this.numeroRisultatiTotali = numeroRisultatiTotali;
    }

	/**
	 * @return the risultati
	 */
	public List<TassonomiaOutputDto> getRisultati() {
		return risultati;
	}

	/**
	 * @param risultati the risultati to set
	 */
	public void setRisultati(List<TassonomiaOutputDto> risultati) {
		this.risultati = risultati;
	}

   

}
