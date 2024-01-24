/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class RevocaPerWso2 implements Serializable {

    private static final long serialVersionUID = -3707504275951941360L;

    private Rr rr;

    private Er er;

    private Rt rt;

    private List<DatiSingolaRevoca> dati = new ArrayList<DatiSingolaRevoca> ();

    public Rr getRr () {
        return rr;
    }

    public void setRr ( Rr rr ) {
        this.rr = rr;
    }

    public Er getEr () {
        return er;
    }

    public void setEr ( Er er ) {
        this.er = er;
    }

    public Rt getRt () {
        return rt;
    }

    public void setRt ( Rt rt ) {
        this.rt = rt;
    }

    public List<DatiSingolaRevoca> getDati () {
        return dati;
    }

    public void setDati ( List<DatiSingolaRevoca> dati ) {
        this.dati = dati;
    }

}
