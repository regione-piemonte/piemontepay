/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto.utente;

import java.util.List;

import it.csi.epay.epaypacatalogsrv.dto.ParentOutput;


public class RicercaUtenteOutput extends ParentOutput {

    private static final long serialVersionUID = 1L;

    private Integer numeroRisultatiTotali;

    private List<RicercaUtenteOutputDto> risultati;

    public static RicercaUtenteOutput ok ( List<RicercaUtenteOutputDto> risultati ) {
        RicercaUtenteOutput output = ok ( RicercaUtenteOutput.class );
        output.setNumeroRisultatiTotali ( risultati.size () );
        output.setRisultati ( risultati );
        output.setDescrizioneEsito ( risultati.size () + " risultati" );
        return output;
    }

    public Integer getNumeroRisultatiTotali () {
        return numeroRisultatiTotali;
    }

    public void setNumeroRisultatiTotali ( Integer numeroRisultatiTotali ) {
        this.numeroRisultatiTotali = numeroRisultatiTotali;
    }

    public List<RicercaUtenteOutputDto> getRisultati () {
        return risultati;
    }

    public void setRisultati ( List<RicercaUtenteOutputDto> risultati ) {
        this.risultati = risultati;
    }

    @Override
    public String toString () {
        return "RicercaUtenteOutput [numeroRisultatiTotali=" + numeroRisultatiTotali + ", risultati=" + risultati + "]";
    }

}
