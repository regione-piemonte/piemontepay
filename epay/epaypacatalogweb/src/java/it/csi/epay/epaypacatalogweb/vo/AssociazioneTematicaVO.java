/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.vo;

import java.util.ArrayList;
import java.util.List;


public class AssociazioneTematicaVO {

    private int id;

    private String depositCode;

    private boolean isChecked;

    List<ThemeVO> themeList, selectedThemes;


    public AssociazioneTematicaVO () {
        super();
    }

    public AssociazioneTematicaVO ( int id, String depositCode, ArrayList<ThemeVO> themes ) {
        super();
        this.id = id;
        this.depositCode = depositCode;
        this.isChecked = false;

        this.themeList = themes;
        this.selectedThemes = new ArrayList<> ();

    }


    public int getId () {
        return id;
    }


    public void setId ( int id ) {
        this.id = id;
    }


    public String getDepositCode () {
        return depositCode;
    }


    public void setDepositCode ( String depositCode ) {
        this.depositCode = depositCode;
    }


    public List<ThemeVO> getThemeList () {
        return themeList;
    }

    public void setThemeList ( List<ThemeVO> themeList ) {
        this.themeList = themeList;
    }

    public List<ThemeVO> getSelectedThemes () {
        return selectedThemes;
    }

    public boolean isChecked () {
        return isChecked;
    }

    public void setChecked ( boolean isChecked ) {
        this.isChecked = isChecked;
    }


    @Override
    public String toString () {
        return "DepositCodesVO [id=" + id + ", depositCode=" + depositCode + ", isChecked=" + isChecked + ", themeList=" + themeList + ", selectedThemes="
                        + selectedThemes + "]";
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
        AssociazioneTematicaVO other = (AssociazioneTematicaVO) obj;
        if ( id != other.id )
            return false;
        return true;
    }







}
