/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto.utente;

import it.csi.epay.epaypacatalogsrv.dto.ParentOutput;


public class GetUtenteOutput extends ParentOutput {

    private static final long serialVersionUID = 1L;

    private GetUtenteOutputDto utente;

    public static GetUtenteOutput ok ( GetUtenteOutputDto utente ) {
        GetUtenteOutput output = ok ( GetUtenteOutput.class );
        output.setUtente ( utente );
        return output;
    }

    public GetUtenteOutputDto getUtente () {
        return utente;
    }

    public void setUtente ( GetUtenteOutputDto utente ) {
        this.utente = utente;
    }

    @Override
    public String toString () {
        return "GetUtenteOutput [utente=" + utente + "]";
    }

}
