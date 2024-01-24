/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto.codiceversamento;

import java.util.List;

import it.csi.epay.epaypacatalogsrv.dto.ParentOutput;


public class RicercaCodiceVersamentoOutput extends ParentOutput {

    private static final long serialVersionUID = 1L;

    private Integer numeroRisultatiTotali;

    private List<RicercaCodiceVersamentoOutputDto> risultati;

    public static RicercaCodiceVersamentoOutput ok ( List<RicercaCodiceVersamentoOutputDto> risultati ) {
        RicercaCodiceVersamentoOutput output = ok ( RicercaCodiceVersamentoOutput.class );
        output.setNumeroRisultatiTotali ( risultati.size () );
        output.setRisultati ( risultati );
        output.setDescrizioneEsito ( risultati.size () + " risultati" );
        return output;
    }

    @Override
    public String toString () {
        return "RicercaCodiciVersamentoOutput [numeroRisultatiTotali=" + numeroRisultatiTotali + ", risultati=" + risultati + "]";
    }

    public Integer getNumeroRisultatiTotali () {
        return numeroRisultatiTotali;
    }

    public void setNumeroRisultatiTotali ( Integer numeroRisultatiTotali ) {
        this.numeroRisultatiTotali = numeroRisultatiTotali;
    }

    public List<RicercaCodiceVersamentoOutputDto> getRisultati () {
        return risultati;
    }

    public void setRisultati ( List<RicercaCodiceVersamentoOutputDto> risultati ) {
        this.risultati = risultati;
    }

}
