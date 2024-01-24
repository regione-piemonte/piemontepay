/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto.codiceversamento;

import java.util.ArrayList;
import java.util.List;

import it.csi.epay.epaypacatalogsrv.dto.MessageDto;
import it.csi.epay.epaypacatalogsrv.dto.ParentOutput;


public class AggiornaCodiceVersamentoOutput extends ParentOutput {

    private static final long serialVersionUID = 1L;

    private List<MessageDto> messaggi = new ArrayList<> ();

    public static AggiornaCodiceVersamentoOutput ok () {
        AggiornaCodiceVersamentoOutput output = AggiornaCodiceVersamentoOutput.ok ( AggiornaCodiceVersamentoOutput.class );
        return output;
    }

    @Override
    public String toString () {
        return "AggiornaCodiceVersamentoOutput []";
    }

    public List<MessageDto> getMessaggi () {
        return messaggi;
    }

    public AggiornaCodiceVersamentoOutput conMessaggi ( List<MessageDto> messaggi ) {
        this.messaggi.addAll ( messaggi );
        return this;
    }

    public void setMessaggi ( List<MessageDto> messaggi ) {
        this.messaggi = messaggi;
    }

}
