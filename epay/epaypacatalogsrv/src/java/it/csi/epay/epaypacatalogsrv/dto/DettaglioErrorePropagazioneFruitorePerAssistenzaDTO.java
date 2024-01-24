/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto;

import java.io.Serializable;

import it.csi.epay.epaypacatalogsrv.model.Sottoscrittore;


public class DettaglioErrorePropagazioneFruitorePerAssistenzaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Sottoscrittore sottoscrittore;

    private String messaggio;

    private String messaggioSottoscrittore;

    public DettaglioErrorePropagazioneFruitorePerAssistenzaDTO ( Sottoscrittore sottoscrittore, String messaggio, String messaggioSottoscrittore ) {
        super ();
        this.sottoscrittore = sottoscrittore;
        this.messaggio = messaggio;
        this.messaggioSottoscrittore = messaggioSottoscrittore;
    }

    public Sottoscrittore getSottoscrittore () {
        return sottoscrittore;
    }

    public void setSottoscrittore ( Sottoscrittore sottoscrittore ) {
        this.sottoscrittore = sottoscrittore;
    }

    public String getMessaggio () {
        return messaggio;
    }

    public void setMessaggio ( String messaggio ) {
        this.messaggio = messaggio;
    }

    public String getMessaggioSottoscrittore () {
        return messaggioSottoscrittore;
    }

    public void setMessaggioSottoscrittore ( String messaggioSottoscrittore ) {
        this.messaggioSottoscrittore = messaggioSottoscrittore;
    }

}
