/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto.riferimentocontabile;

import java.util.List;

import it.csi.epay.epaypacatalogsrv.dto.ParentOutput;


public class RicercaRiferimentiContabiliSecondariPerCovOutput extends ParentOutput {

    private static final long serialVersionUID = 1L;


    private List<RicercaRiferimentoContabileOutputDto> risultati;

    public static RicercaRiferimentiContabiliSecondariPerCovOutput ok ( List<RicercaRiferimentoContabileOutputDto> risultati ) {
        RicercaRiferimentiContabiliSecondariPerCovOutput output = ok ( RicercaRiferimentiContabiliSecondariPerCovOutput.class );
        output.setRisultati ( risultati );
        return output;
    }


	public List<RicercaRiferimentoContabileOutputDto> getRisultati() {
		return risultati;
	}

	public void setRisultati(List<RicercaRiferimentoContabileOutputDto> risultati) {
		this.risultati = risultati;
	}

    

}
