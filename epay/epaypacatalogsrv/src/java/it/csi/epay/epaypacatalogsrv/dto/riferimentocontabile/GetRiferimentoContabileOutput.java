/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto.riferimentocontabile;

import it.csi.epay.epaypacatalogsrv.dto.ParentOutput;


public class GetRiferimentoContabileOutput extends ParentOutput {

    private static final long serialVersionUID = 1L;

    private GetRiferimentoContabileOutputDto riferimentoContabile;

    public static GetRiferimentoContabileOutput ok ( GetRiferimentoContabileOutputDto risultato ) {
        GetRiferimentoContabileOutput output = ok ( GetRiferimentoContabileOutput.class );
        output.setRiferimentoContabile ( risultato );
        return output;
    }

    @Override
    public String toString () {
        return "GetRiferimentoContabileOutput [riferimentoContabile=" + riferimentoContabile + "]";
    }

    public GetRiferimentoContabileOutputDto getRiferimentoContabile () {
        return riferimentoContabile;
    }

    public void setRiferimentoContabile ( GetRiferimentoContabileOutputDto riferimentoContabile ) {
        this.riferimentoContabile = riferimentoContabile;
    }

}
