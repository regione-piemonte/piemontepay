/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto.riferimentocontabile;

import it.csi.epay.epaypacatalogsrv.dto.ParentOutput;


public class EliminaRiferimentoContabileOutput extends ParentOutput {

    private static final long serialVersionUID = 1L;

    public static EliminaRiferimentoContabileOutput ok () {
        EliminaRiferimentoContabileOutput output = EliminaRiferimentoContabileOutput.ok ( EliminaRiferimentoContabileOutput.class );
        return output;
    }

    @Override
    public String toString () {
        return "EliminaRiferimentoContabileOutput []";
    }

}
