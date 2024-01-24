/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayjob.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;


public class TassonomiaUpdateInputDto implements Serializable {

    private static final long serialVersionUID = 3844295025386842060L;

    @JsonProperty ( "tassonomie" )
    private List<TassonomiaOutputDto> tassonomie;

    public List<TassonomiaOutputDto> getTassonomie () {
        return tassonomie;
    }

    public void setTassonomie ( List<TassonomiaOutputDto> tassonomie ) {
        this.tassonomie = tassonomie;
    }

}
