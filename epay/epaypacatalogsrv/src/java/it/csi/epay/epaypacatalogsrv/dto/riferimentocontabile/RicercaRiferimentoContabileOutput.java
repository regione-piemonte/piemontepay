/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto.riferimentocontabile;

import java.util.List;

import it.csi.epay.epaypacatalogsrv.dto.ParentOutput;


public class RicercaRiferimentoContabileOutput extends ParentOutput {

    private static final long serialVersionUID = 1L;

    private Integer numeroRisultatiTotali;

    private List<RicercaRiferimentoContabileGruppoCodiceVersamentoOutputDto> risultati;

    public static RicercaRiferimentoContabileOutput ok ( List<RicercaRiferimentoContabileGruppoCodiceVersamentoOutputDto> risultati ) {
        RicercaRiferimentoContabileOutput output = ok ( RicercaRiferimentoContabileOutput.class );
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

    public List<RicercaRiferimentoContabileGruppoCodiceVersamentoOutputDto> getRisultati () {
        return risultati;
    }

    public void setRisultati ( List<RicercaRiferimentoContabileGruppoCodiceVersamentoOutputDto> risultati ) {
        this.risultati = risultati;
    }

}
