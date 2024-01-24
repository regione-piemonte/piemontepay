/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.dto.epaymodric.ws;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import it.csi.epay.epaymodric.dto.epaymodric.base.DTOInputBase;


/**
 *
 */
@XmlAccessorType ( XmlAccessType.PROPERTY )
@XmlType ( name = "dtoInputWsInserisciFlussiDaRielaborare" )
public class DTOInputWsInserisciFlussiDaRielaborare extends DTOInputBase {

    /**
     *
     */
    private static final long serialVersionUID = -4167276349176171288L;

    public List<String> identificativiFlussoDaRielaborare;

    public DTOInputWsInserisciFlussiDaRielaborare () {
    }

    public List<String> getIdentificativiFlussoDaRielaborare () {
        return identificativiFlussoDaRielaborare;
    }

    public void setIdentificativiFlussoDaRielaborare ( List<String> identificativiFlussoDaRielaborare ) {
        this.identificativiFlussoDaRielaborare = identificativiFlussoDaRielaborare;
    }

}
