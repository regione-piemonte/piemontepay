/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto.riferimentocontabile;

import it.csi.epay.epaypacatalogsrv.dto.ParentOutput;


public class InserisciRiferimentoContabileOutput extends ParentOutput {

    private static final long serialVersionUID = 1L;

    private InserisciRiferimentoContabileOutputDto risultatoInserimento;

    public static InserisciRiferimentoContabileOutput ok ( Long id ) {
        InserisciRiferimentoContabileOutput output = InserisciRiferimentoContabileOutput.ok ( InserisciRiferimentoContabileOutput.class );
        output.setRisultatoInserimento ( new InserisciRiferimentoContabileOutputDto () );
        output.getRisultatoInserimento ().setId ( id );
        return output;
    }

    @Override
    public String toString () {
        return "InserisciRiferimentoContabileOutput [risultatoInserimento=" + risultatoInserimento + "]";
    }

    public InserisciRiferimentoContabileOutputDto getRisultatoInserimento () {
        return risultatoInserimento;
    }

    public void setRisultatoInserimento ( InserisciRiferimentoContabileOutputDto risultatoInserimento ) {
        this.risultatoInserimento = risultatoInserimento;
    }

}
