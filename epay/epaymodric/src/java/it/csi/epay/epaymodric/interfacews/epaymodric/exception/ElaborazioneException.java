/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.interfacews.epaymodric.exception;

import java.util.List;

import it.csi.epay.epaymodric.business.epaymodric.bo.Elaborazione;
import it.csi.epay.epaymodric.interfacews.epaymodric.enums.StatoElaborazioneEnum;

public class ElaborazioneException extends RiconciliazioneException {

    /**
     *
     */
    private static final long serialVersionUID = -908014083852348169L;
    private Elaborazione elaborazione;

    private StatoElaborazioneEnum statoElaborazione;

    public ElaborazioneException ( String message, String codiceErrore ) {
        super ( message, codiceErrore );
    }

    public ElaborazioneException(String message, String codiceErrore, List<String> infoAggiuntive) {
        super(message, codiceErrore, infoAggiuntive);
    }

    public ElaborazioneException(String message, String codiceErrore, String infoAggiuntiva) {
        super(message, codiceErrore, infoAggiuntiva);
    }

    public ElaborazioneException ( String message, String codiceErrore, StatoElaborazioneEnum statoElaborazione ) {
        super ( message, codiceErrore );
        this.statoElaborazione = statoElaborazione;
    }

    public Elaborazione getElaborazione() {
        return elaborazione;
    }

    public void setElaborazione(Elaborazione elaborazione) {
        this.elaborazione = elaborazione;
    }

    public StatoElaborazioneEnum getStatoElaborazione () {
        return statoElaborazione;
    }

    public void setStatoElaborazione ( StatoElaborazioneEnum statoElaborazione ) {
        this.statoElaborazione = statoElaborazione;
    }

}
