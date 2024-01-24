/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

/*
 * Copyright 2001-2018 CSI Piemonte. All Rights Reserved.
 *
 * This software is proprietary information of CSI Piemonte.
 * Use is subject to license terms.
 *
 */

package it.csi.epay.epaymodric.dto.epaymodric.ws;

import java.io.Serializable;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import it.csi.epay.epaymodric.dto.epaymodric.base.DTOStatoFlusso;


/**
 * 
 * @ author vsgro
 * 
 */
@XmlAccessorType ( XmlAccessType.PROPERTY )
@XmlType ( name = "dtoOutputWsStatoFlusso" )
public class DTOOutputWsStatoFlusso implements Serializable {

    private static final long serialVersionUID = 6024907684664456910L;

    private DTOOutputWsEsito esito = null;

    private ArrayList<DTOStatoFlusso> statiFlusso = new ArrayList <> ();

    public DTOOutputWsEsito getEsito () {
        return esito;
    }

    public void setEsito ( DTOOutputWsEsito esito ) {
        this.esito = esito;
    }
    
    public ArrayList<DTOStatoFlusso> getStatiFlusso () {
        return statiFlusso;
    }

    
    public void setStatiFlusso ( ArrayList<DTOStatoFlusso> statiFlusso ) {
        this.statiFlusso = statiFlusso;
    }

    @Override
    public String toString () {
        StringBuffer stringBuffer = new StringBuffer ( super.toString () );
        stringBuffer.append ( "\n " );
        stringBuffer.append ( "esito: [" + esito.toString () + "]" );
        if (statiFlusso!=null && statiFlusso.size ()>0) {
            for (int i=0;i<statiFlusso.size ();i++) {
                DTOStatoFlusso dtoStatoFlusso = statiFlusso.get ( i );
                if (dtoStatoFlusso!=null) {
                    stringBuffer.append ( "esito: [" + dtoStatoFlusso.toString () + "]" );
                }
            }
        }
        return stringBuffer.toString ();
    }

}
