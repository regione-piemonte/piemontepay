/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto.codiceversamento;

import it.csi.epay.epaypacatalogsrv.dto.ParentOutput;


public class GetCodiceVersamentoOutput extends ParentOutput {

    private static final long serialVersionUID = 1L;

    private GetCodiceVersamentoOutputDto codiceVersamento;

    public static GetCodiceVersamentoOutput ok ( GetCodiceVersamentoOutputDto risultato ) {
        GetCodiceVersamentoOutput output = ok ( GetCodiceVersamentoOutput.class );
        output.setCodiceVersamento ( risultato );
        return output;
    }

    @Override
    public String toString () {
        return "GetCodiceVersamentoOutput [codiceVersamento=" + codiceVersamento + "]";
    }

    public GetCodiceVersamentoOutputDto getCodiceVersamento () {
        return codiceVersamento;
    }

    public void setCodiceVersamento ( GetCodiceVersamentoOutputDto codiceVersamento ) {
        this.codiceVersamento = codiceVersamento;
    }

}
