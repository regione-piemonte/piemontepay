/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto.codiceversamento;

import java.util.ArrayList;
import java.util.List;

import it.csi.epay.epaypacatalogsrv.dto.MessageDto;
import it.csi.epay.epaypacatalogsrv.dto.ParentOutput;


public class InserisciCodiceVersamentoOutput extends ParentOutput {

    private static final long serialVersionUID = 1L;

    private InserisciCodiceVersamentoOutputDto risultatoInserimento;

    private List<MessageDto> messaggi = new ArrayList<> ();

    public static InserisciCodiceVersamentoOutput ok ( Long id ) {
        InserisciCodiceVersamentoOutput output = InserisciCodiceVersamentoOutput.ok ( InserisciCodiceVersamentoOutput.class );
        output.setRisultatoInserimento ( new InserisciCodiceVersamentoOutputDto () );
        output.getRisultatoInserimento ().setId ( id );
        return output;
    }

    @Override
    public String toString () {
        return "InserisciCodiceVersamentoOutput [risultatoInserimento=" + risultatoInserimento + "]";
    }

    public InserisciCodiceVersamentoOutputDto getRisultatoInserimento () {
        return risultatoInserimento;
    }

    public void setRisultatoInserimento ( InserisciCodiceVersamentoOutputDto risultatoInserimento ) {
        this.risultatoInserimento = risultatoInserimento;
    }

    public List<MessageDto> getMessaggi () {
        return messaggi;
    }

    public InserisciCodiceVersamentoOutput conMessaggi ( List<MessageDto> messaggi ) {
        this.messaggi.addAll ( messaggi );
        return this;
    }

    public void setMessaggi ( List<MessageDto> messaggi ) {
        this.messaggi = messaggi;
    }

}
