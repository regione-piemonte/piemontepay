/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayjob.model;

import java.util.List;

import it.csi.epay.epayjob.interfacews.epaypacatalogsrv.ParentOutput;

public class RicercaTassonomiaOutput extends ParentOutput {

    private static final long serialVersionUID = 1L;

    private Integer numeroRisultatiTotali;

    private List<TassonomiaOutputDto> risultati;

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
    public List<TassonomiaOutputDto> getRisultati () {
        return risultati;
    }

    /**
     * @param risultati the risultati to set
     */
    public void setRisultati ( List<TassonomiaOutputDto> risultati ) {
        this.risultati = risultati;
    }

}
