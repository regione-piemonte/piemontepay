/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.dto.epaymodric.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import it.csi.epay.epaymodric.dto.epaymodric.base.DTOInputBase;


// classe che contiene i dati di input da mandare al webservice

@XmlAccessorType ( XmlAccessType.PROPERTY )
@XmlType ( name = "dtoInputWsRicercaFlussoDettaglio" )
public class DTOInputWsRicercaFlussoDettaglio extends DTOInputBase {

    private static final long serialVersionUID = 1L;

    private String idFlussoSintesi;

    public String getIdFlussoSintesi () {
        return idFlussoSintesi;
    }

    public void setIdFlussoSintesi ( String idFlussoSintesi ) {
        this.idFlussoSintesi = idFlussoSintesi;
    }

    @Override
    public String toString () {
        StringBuffer stringBuffer = new StringBuffer ( super.toString () );
        stringBuffer.append ( "\n " );
        stringBuffer.append ( "idFlussoSintesi: [" + idFlussoSintesi + "]" );
        return stringBuffer.toString ();
    }

}
