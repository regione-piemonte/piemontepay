/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaysimweb.vo;

import java.util.ArrayList;
import java.util.List;


public class DepositCodesVO {

    private int id;

    private String depositCode;

    List<ThemeVO> themeList;

    public DepositCodesVO () {
        super();
    }

    public DepositCodesVO ( int id, String depositCode, ArrayList<ThemeVO> themes ) {
        super();
        this.id = id;
        this.depositCode = depositCode;
        this.themeList = themes;
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


    //    public void setThemeList ( ThemeVO theme ) {
    //        this.emptyThemeList.add ( theme );
    //    }

    public void setThemeList ( List<ThemeVO> themeList ) {
        this.themeList = themeList;
    }

    @Override
    public String toString () {
        return "DepositCodesVO [id=" + id + ", depositCode=" + depositCode + ", themeList=" + themeList + "]";
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
        DepositCodesVO other = (DepositCodesVO) obj;
        if ( id != other.id )
            return false;
        return true;
    }







}
