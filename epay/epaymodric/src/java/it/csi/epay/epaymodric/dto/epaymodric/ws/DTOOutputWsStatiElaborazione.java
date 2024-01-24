/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.dto.epaymodric.ws;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import it.csi.epay.epaymodric.dto.epaymodric.base.DTOStatoElaborazione;


/**
 * @author vsgro
 */
@XmlAccessorType ( XmlAccessType.PROPERTY )
@XmlType ( name = "dtoOutputWsStatiElaborazione" )
public class DTOOutputWsStatiElaborazione {

    private List<DTOStatoElaborazione> statiElaborazione = new ArrayList<DTOStatoElaborazione> ();

    public DTOOutputWsStatiElaborazione () {
        super ();
    }

    public List<DTOStatoElaborazione> getStatiElaborazione () {
        return statiElaborazione;
    }

    public void setStatiElaborazione ( List<DTOStatoElaborazione> statiElaborazione ) {
        this.statiElaborazione = statiElaborazione;
    }
}
