/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto.codiceversamento;

import it.csi.epay.epaypacatalogsrv.dto.ParentOutput;


public class EliminaCodiceVersamentoOutput extends ParentOutput {

    private static final long serialVersionUID = 1L;

    public static EliminaCodiceVersamentoOutput ok () {
        EliminaCodiceVersamentoOutput output = EliminaCodiceVersamentoOutput.ok ( EliminaCodiceVersamentoOutput.class );
        return output;
    }
    @Override
    public String toString () {
        return "EliminaCodiceVersamentoOutput []";
    }

}
