/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.interfacews.epaymodric.exception;

import java.util.ArrayList;
import java.util.List;

public class RiconciliazioneException extends Exception {

    private static final long serialVersionUID = 1907688044798522617L;

    private String codiceErrore;

    private List<String> infoAggiuntive;

    public RiconciliazioneException() {
    }

    public RiconciliazioneException ( String message, String codiceErrore ) {
        super ( message );
        this.codiceErrore = codiceErrore;
    }
    
    public RiconciliazioneException(String message, String codiceErrore, List<String> infoAggiuntive) {
    	this(message, codiceErrore);
    	this.infoAggiuntive = infoAggiuntive;
    }
    
    public RiconciliazioneException(String message, String codiceErrore, String infoAggiuntiva) {
    	this(message, codiceErrore);
    	List<String> infoAggiuntive = new ArrayList<String>();
    	infoAggiuntive.add(infoAggiuntiva);
    	this.infoAggiuntive = infoAggiuntive;
    }

    public RiconciliazioneException ( String message ) {
        super ( message );
    }

    public String getCodiceErrore() {
        return codiceErrore;
    }

    public void setCodiceErrore(String codiceErrore) {
        this.codiceErrore = codiceErrore;
    }

    public List<String> getInfoAggiuntive() {
        return infoAggiuntive;
    }

    public void setInfoAggiuntive(List<String> infoAggiuntive) {
        this.infoAggiuntive = infoAggiuntive;
    }

}
