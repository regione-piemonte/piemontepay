/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaysimweb.vo;

public class ThemeVO {

    private int id;

    private String themeName;

    public ThemeVO () {
        super();
    }

    public ThemeVO ( int id, String themeName ) {
        super();
        this.id = id;
        this.themeName = themeName;
    }

    public int getId () {
        return id;
    }

    public void setId ( int id ) {
        this.id = id;
    }

    public String getThemeName () {
        return themeName;
    }

    public void setThemeName ( String themeName ) {
        this.themeName = themeName;
    }

    @Override
    public String toString () {
        return "ThemeVO [id=" + id + ", themeName=" + themeName + "]";
    }




}
