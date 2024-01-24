/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysimweb.vo;

import java.util.HashMap;

/**
 *
 */

public class SelectedDepositAndThemesVO {

    private HashMap<Integer, Boolean> depositCodes;

    private HashMap<String, Boolean> themes;

    public SelectedDepositAndThemesVO () {

        depositCodes = new HashMap<> ();
        themes = new HashMap<> ();

    }

    public HashMap<Integer, Boolean> getDepositCodes () {
        return depositCodes;
    }

    public void setDepositCodes ( HashMap<Integer, Boolean> depositCodes ) {
        this.depositCodes = depositCodes;
    }

    //    public void setDepositCodes ( DepositCodesVO depositCodes ) {
    //        this.depositCodes.put ( depositCodes.getId (), true );
    //    }

    public HashMap<String, Boolean> getThemes () {
        return themes;
    }

    public void setThemes ( HashMap<String, Boolean> themes ) {
        this.themes = themes;
    }

    @Override
    public String toString () {
        return "SelectedDepositAndThemesVO [depositCodes=" + depositCodes + ", themes=" + themes + "]";
    }

}
