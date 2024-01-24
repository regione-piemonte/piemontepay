/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.dto.epaymodric.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import it.csi.epay.epaymodric.dto.epaymodric.base.DTOInputBase;


@XmlAccessorType ( XmlAccessType.PROPERTY )
@XmlType ( name = "dtoInputWsSpacchettamentoFlussoRendicontazione" )
public class DTOInputWsSpacchettamentoFlussoRendicontazione extends DTOInputBase {

    private static final long serialVersionUID = 1L;

    private String idFlussoOrigine;

    private String codiceFiscaleEnte;

    public DTOInputWsSpacchettamentoFlussoRendicontazione () {
        super ();
    }

    public String getIdFlussoOrigine () {
        return idFlussoOrigine;
    }

    public void setIdFlussoOrigine ( String idFlussoOrigine ) {
        this.idFlussoOrigine = idFlussoOrigine;
    }

    public String getCodiceFiscaleEnte () {
        return codiceFiscaleEnte;
    }

    public void setCodiceFiscaleEnte ( String codiceFiscaleEnte ) {
        this.codiceFiscaleEnte = codiceFiscaleEnte;
    }

}
