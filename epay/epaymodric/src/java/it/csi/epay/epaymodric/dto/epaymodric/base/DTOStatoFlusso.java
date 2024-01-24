/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.dto.epaymodric.base;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * @ author vsgro
 */
@XmlAccessorType ( XmlAccessType.PROPERTY )
@XmlType ( name = "dtoStatoFlusso" )
public class DTOStatoFlusso implements Serializable {

    private static final long serialVersionUID = -3847919575729495763L;

    private String id;

    private String codiceStato;

    private String descrizioneStato;

    private Boolean permettiRielaborazione;

    public Boolean getPermettiRielaborazione () {
        return permettiRielaborazione;
    }

    public void setPermettiRielaborazione ( Boolean permettiRielaborazione ) {
        this.permettiRielaborazione = permettiRielaborazione;
    }

    public String getId () {
        return id;
    }

    public void setId ( String id ) {
        this.id = id;
    }

    public String getCodiceStato () {
        return codiceStato;
    }

    public void setCodiceStato ( String codiceStato ) {
        this.codiceStato = codiceStato;
    }

    public String getDescrizioneStato () {
        return descrizioneStato;
    }

    public void setDescrizioneStato ( String descrizioneStato ) {
        this.descrizioneStato = descrizioneStato;
    }

    @Override
    public String toString () {
        StringBuffer stringBuffer = new StringBuffer ();
        stringBuffer.append ( "id: [" + id + "]" );
        stringBuffer.append ( "codiceStato: [" + codiceStato + "]" );
        stringBuffer.append ( "descrizioneStato: [" + descrizioneStato + "]" );
        return stringBuffer.toString ();
    }

}
