/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.vo;

public class ThemeVO {

    private int id;

    private String themeName;

    private boolean isChecked;

    public ThemeVO () {
        super();
    }

    public ThemeVO ( int id, String themeName ) {
        super();
        this.id = id;
        this.themeName = themeName;
        this.isChecked = false;
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

    public boolean isChecked () {
        return isChecked;
    }

    public void setChecked ( boolean isChecked ) {
        this.isChecked = isChecked;
    }


    @Override
    public String toString () {
        return "ThemeVO [id=" + id + ", themeName=" + themeName + ", isChecked=" + isChecked + "]";
    }

    @Override
    public int hashCode () {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals ( Object obj ) {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass () != obj.getClass () )
            return false;
        ThemeVO other = (ThemeVO) obj;
        if ( id != other.id )
            return false;
        return true;
    }



}
