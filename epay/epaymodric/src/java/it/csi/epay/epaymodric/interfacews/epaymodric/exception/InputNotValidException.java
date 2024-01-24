/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.interfacews.epaymodric.exception;


/**
 *
 */

public class InputNotValidException extends RiconciliazioneException {

    /**
     *
     */
    private static final long serialVersionUID = 8895051088237565791L;

    public InputNotValidException ( String message ) {
        super ( message );
    }

}
