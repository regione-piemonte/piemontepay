/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto.utente;

import it.csi.epay.epaypacatalogsrv.dto.ParentOutput;


public class InserisciUtenteOutput extends ParentOutput {

    private static final long serialVersionUID = 1L;

    private InserisciUtenteOutputDto risultatoInserimento;

    public static InserisciUtenteOutput ok ( Long id ) {
        InserisciUtenteOutput output = InserisciUtenteOutput.ok ( InserisciUtenteOutput.class );
        output.setRisultatoInserimento ( new InserisciUtenteOutputDto () );
        output.getRisultatoInserimento ().setId ( id );
        return output;
    }

    public InserisciUtenteOutputDto getRisultatoInserimento () {
        return risultatoInserimento;
    }

    public void setRisultatoInserimento ( InserisciUtenteOutputDto risultatoInserimento ) {
        this.risultatoInserimento = risultatoInserimento;
    }

    @Override
    public String toString () {
        return "InserisciUtenteOutput [risultatoInserimento=" + risultatoInserimento + "]";
    }

}
