/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaybeapi.dto.common;

import java.io.Serializable;


public class LoggerDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 5561609730082620076L;

    private String name;

    private String level;

    public String getName () {
        return name;
    }

    public void setName ( String name ) {
        this.name = name;
    }

    public String getLevel () {
        return level;
    }

    public void setLevel ( String level ) {
        this.level = level;
    }

    public LoggerDTO ( String name, String level ) {
        super ();
        this.name = name;
        this.level = level;
    }

    public LoggerDTO () {
        super ();
    }

}
