/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto.riferimentocontabile;

import it.csi.epay.epaypacatalogsrv.dto.ParentOutput;


public class ChiudiRiferimentoContabileOutput extends ParentOutput {

    private static final long serialVersionUID = 1L;

    public static ChiudiRiferimentoContabileOutput ok () {
        ChiudiRiferimentoContabileOutput output = ChiudiRiferimentoContabileOutput.ok ( ChiudiRiferimentoContabileOutput.class );
        return output;
    }

    @Override
    public String toString () {
        return "ChiudiRiferimentoContabileOutput []";
    }

}
