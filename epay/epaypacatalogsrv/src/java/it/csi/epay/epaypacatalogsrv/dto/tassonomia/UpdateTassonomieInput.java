/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto.tassonomia;

import java.util.List;

import it.csi.epay.epaypacatalogsrv.dto.ParentInput;


public class UpdateTassonomieInput extends ParentInput {

    private static final long serialVersionUID = 174900871036100253L;

    private List<UpdateTassonomieItemInput> tassonomie;

    public List<UpdateTassonomieItemInput> getTassonomie () {
        return tassonomie;
    }

    public void setTassonomie ( List<UpdateTassonomieItemInput> tassonomie ) {
        this.tassonomie = tassonomie;
    }

}
