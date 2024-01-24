/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto.voceentrata;

import java.util.List;

import it.csi.epay.epaypacatalogsrv.dto.ParentOutput;


public class RicercaVoceEntrataDisponibileOutput extends ParentOutput {

    private static final long serialVersionUID = 1L;

    private Integer numeroRisultatiTotali;

    private List<RicercaVoceEntrataDisponibileOutputDto> risultati;

    public static RicercaVoceEntrataDisponibileOutput ok ( List<RicercaVoceEntrataDisponibileOutputDto> risultati ) {
        RicercaVoceEntrataDisponibileOutput output = ok ( RicercaVoceEntrataDisponibileOutput.class );
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

    public List<RicercaVoceEntrataDisponibileOutputDto> getRisultati () {
        return risultati;
    }

    public void setRisultati ( List<RicercaVoceEntrataDisponibileOutputDto> risultati ) {
        this.risultati = risultati;
    }

    @Override
    public String toString () {
        return "RicercaVoceEntrataDisponibileOutput [numeroRisultatiTotali=" + numeroRisultatiTotali + ", risultati=" + risultati + "]";
    }

}
