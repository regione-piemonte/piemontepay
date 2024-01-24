/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto.migrazione;

import java.util.List;

import it.csi.epay.epaypacatalogsrv.dto.ParentOutput;


public class EseguiMigrazioneOutput extends ParentOutput {

    private static final long serialVersionUID = 1L;

    private List<EseguiMigrazioneLogOutputDto> log;

    public static EseguiMigrazioneOutput ok () {
        EseguiMigrazioneOutput output = ok ( EseguiMigrazioneOutput.class );
        return output;
    }

    public List<EseguiMigrazioneLogOutputDto> getLog () {
        return log;
    }

    public void setLog ( List<EseguiMigrazioneLogOutputDto> log ) {
        this.log = log;
    }

}
