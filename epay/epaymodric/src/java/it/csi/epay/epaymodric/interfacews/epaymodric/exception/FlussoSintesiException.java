/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.interfacews.epaymodric.exception;

import java.util.List;

public class FlussoSintesiException  extends RiconciliazioneException {

    private static final long serialVersionUID = 8255007347932075376L;

    public FlussoSintesiException ( String message, String codiceErrore ) {
        super ( message, codiceErrore );
    }
    
    public FlussoSintesiException(String message, String codiceErrore, List<String> infoAggiuntive) {
    	super(message, codiceErrore, infoAggiuntive);
    }
    
    public FlussoSintesiException(String message, String codiceErrore, String infoAggiuntiva) {
    	super(message, codiceErrore, infoAggiuntiva);
    }

}
