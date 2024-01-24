/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto.codiceversamento;

import java.util.List;

import it.csi.epay.epaypacatalogsrv.dto.ParentOutput;
import it.csi.epay.epaypacatalogsrv.dto.decodifica.DecodificaCodiciVersamentoOutputDto;


public class RicercaCodiceVersamentoRifContabileSecondarioOutput extends ParentOutput {

    private static final long serialVersionUID = 1L;


    private List<DecodificaCodiciVersamentoOutputDto> risultati;

    public static RicercaCodiceVersamentoRifContabileSecondarioOutput ok ( List<DecodificaCodiciVersamentoOutputDto> risultati ) {
        RicercaCodiceVersamentoRifContabileSecondarioOutput output = ok ( RicercaCodiceVersamentoRifContabileSecondarioOutput.class );
        output.setRisultati ( risultati );
        output.setDescrizioneEsito ( risultati.size () + " risultati" );
        return output;
    }

	public List<DecodificaCodiciVersamentoOutputDto> getRisultati() {
		return risultati;
	}

	public void setRisultati(List<DecodificaCodiciVersamentoOutputDto> risultati) {
		this.risultati = risultati;
	}
    
}
