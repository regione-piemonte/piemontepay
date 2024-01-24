/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto.migrazione;

import it.csi.epay.epaypacatalogsrv.dto.ParentOutput;


public class EseguiMigrazioneLogOutputDto extends ParentOutput {

    private static final long serialVersionUID = 1L;

    private String codiceEntita;

    private String codiceMessaggio;

    private String messaggio;

    public String getCodiceEntita () {
        return codiceEntita;
    }

    public void setCodiceEntita ( String codiceEntita ) {
        this.codiceEntita = codiceEntita;
    }

    @Override
    public String getCodiceMessaggio () {
        return codiceMessaggio;
    }

    @Override
    public void setCodiceMessaggio ( String codiceMessaggio ) {
        this.codiceMessaggio = codiceMessaggio;
    }

    public String getMessaggio () {
        return messaggio;
    }

    public void setMessaggio ( String messaggio ) {
        this.messaggio = messaggio;
    }

}
