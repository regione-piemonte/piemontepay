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

import it.csi.epay.epaymodric.dto.epaymodric.base.DTOEnte;


/**
 * @author vsgro
 */
@XmlAccessorType ( XmlAccessType.PROPERTY )
@XmlType ( name = "dtoOutputWsEnti" )
public class DTOOutputWsEnti {

    private List<DTOEnte> enti = new ArrayList<DTOEnte> ();

    public List<DTOEnte> getEnti () {
        return enti;
    }

    public void setEnti ( List<DTOEnte> enti ) {
        this.enti = enti;
    }

    public DTOOutputWsEnti () {
        super ();
    }
}
