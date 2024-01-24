/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.interfacews.epaymodric.exception;

import java.util.List;

import it.csi.epay.epaymodric.interfacews.epaymodric.enums.StatoFlussoEnum;

public class FlussoOrigineException extends RiconciliazioneException {

    /**
     *
     */
    private static final long serialVersionUID = -2204624295272173743L;

    private StatoFlussoEnum statoFlusso = StatoFlussoEnum.IN_ERRORE;

    public FlussoOrigineException ( String message, String codiceErrore, StatoFlussoEnum statoFlusso ) {
        super ( message, codiceErrore );
        this.statoFlusso = statoFlusso;
    }
    
    public FlussoOrigineException(String message, String codiceErrore, List<String> infoAggiuntive) {
    	super(message, codiceErrore, infoAggiuntive);
    }
    
    public FlussoOrigineException(String message, String codiceErrore, String infoAggiuntiva) {
    	super(message, codiceErrore, infoAggiuntiva);
    }
    
    public FlussoOrigineException ( String message, String codiceErrore, StatoFlussoEnum statoFlusso, String infoAggiuntiva ) {
        super(message, codiceErrore, infoAggiuntiva);
        this.statoFlusso = statoFlusso;
    }

    public StatoFlussoEnum getStatoFlusso () {
        return statoFlusso;
    }

}
