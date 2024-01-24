/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.interfacews.epaymodric.exception;

/**
 * @author vsgro
 */

public class EnteException extends Exception {

    public EnteException ( String descrizione ) {
        super(descrizione);
    }

    private static final long serialVersionUID = 8667715486901013201L;

}
