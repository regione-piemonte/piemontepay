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
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(name = "dtoErroreFlusso")
public class DTOErroreFlusso implements Serializable, Comparable<DTOErroreFlusso> {

    private static final long serialVersionUID = -6026717812386165166L;

    private String identificativoFlusso;

    private String dataAggiornamentoStato;

    private String codiceErrore;

    private String descrizioneErrore;

    private String descrizioneAggiuntivaErrore;

    private String tipologia;


    public String getIdentificativoFlusso () {
        return identificativoFlusso;
    }


    public void setIdentificativoFlusso ( String identificativoFlusso ) {
        this.identificativoFlusso = identificativoFlusso;
    }


    public String getDataAggiornamentoStato () {
        return dataAggiornamentoStato;
    }


    public void setDataAggiornamentoStato ( String dataAggiornamentoStato ) {
        this.dataAggiornamentoStato = dataAggiornamentoStato;
    }


    public String getCodiceErrore () {
        return codiceErrore;
    }


    public void setCodiceErrore ( String codiceErrore ) {
        this.codiceErrore = codiceErrore;
    }


    public String getDescrizioneErrore () {
        return descrizioneErrore;
    }


    public void setDescrizioneErrore ( String descrizioneErrore ) {
        this.descrizioneErrore = descrizioneErrore;
    }


    public String getDescrizioneAggiuntivaErrore () {
        return descrizioneAggiuntivaErrore;
    }


    public void setDescrizioneAggiuntivaErrore ( String descrizioneAggiuntivaErrore ) {
        this.descrizioneAggiuntivaErrore = descrizioneAggiuntivaErrore;
    }


    public String getTipologia () {
        return tipologia;
    }


    public void setTipologia ( String tipologia ) {
        this.tipologia = tipologia;
    }

    @Override
    public int compareTo ( DTOErroreFlusso toCompare ) {
        int compare =
                        this.identificativoFlusso.compareTo ( toCompare.getIdentificativoFlusso ())
                        +  this.codiceErrore.compareTo ( toCompare.getCodiceErrore () )
                        +  this.descrizioneErrore.compareTo ( toCompare.getDescrizioneErrore () )
                        +  this.tipologia.compareTo ( toCompare.getTipologia () )
                        +  this.dataAggiornamentoStato.compareTo ( toCompare.getDataAggiornamentoStato () )
                        ;
        return compare;
    }


    @Override
    public String toString () {
        StringBuffer stringBuffer = new StringBuffer ();
        stringBuffer.append ( "identificativoFlusso: ["+identificativoFlusso+"]" );
        stringBuffer.append ( "dataAggiornamentoStato: ["+dataAggiornamentoStato+"]" );
        stringBuffer.append ( "codiceErrore: ["+codiceErrore+"]" );
        stringBuffer.append ( "descrizioneAggiuntivaErrore: ["+descrizioneAggiuntivaErrore+"]" );
        stringBuffer.append ( "descrizioneErrore: ["+descrizioneErrore+"]" );
        stringBuffer.append ( "tipologia: ["+tipologia+"]" );
        return stringBuffer.toString ();
    }

    @Override
    public int hashCode () {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( codiceErrore == null ) ? 0 : codiceErrore.hashCode () );
        result = prime * result + ( ( dataAggiornamentoStato == null ) ? 0 : dataAggiornamentoStato.hashCode () );
        result = prime * result + ( ( descrizioneAggiuntivaErrore == null ) ? 0 : descrizioneAggiuntivaErrore.hashCode () );
        result = prime * result + ( ( descrizioneErrore == null ) ? 0 : descrizioneErrore.hashCode () );
        result = prime * result + ( ( identificativoFlusso == null ) ? 0 : identificativoFlusso.hashCode () );
        result = prime * result + ( ( tipologia == null ) ? 0 : tipologia.hashCode () );
        return result;
    }

    @Override
    public boolean equals ( Object obj ) {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass () != obj.getClass () )
            return false;
        DTOErroreFlusso other = (DTOErroreFlusso) obj;
        if ( codiceErrore == null ) {
            if ( other.codiceErrore != null )
                return false;
        } else if ( !codiceErrore.equals ( other.codiceErrore ) )
            return false;
        if ( dataAggiornamentoStato == null ) {
            if ( other.dataAggiornamentoStato != null )
                return false;
        } else if ( !dataAggiornamentoStato.equals ( other.dataAggiornamentoStato ) )
            return false;
        if ( descrizioneAggiuntivaErrore == null ) {
            if ( other.descrizioneAggiuntivaErrore != null )
                return false;
        } else if ( !descrizioneAggiuntivaErrore.equals ( other.descrizioneAggiuntivaErrore ) )
            return false;
        if ( descrizioneErrore == null ) {
            if ( other.descrizioneErrore != null )
                return false;
        } else if ( !descrizioneErrore.equals ( other.descrizioneErrore ) )
            return false;
        if ( identificativoFlusso == null ) {
            if ( other.identificativoFlusso != null )
                return false;
        } else if ( !identificativoFlusso.equals ( other.identificativoFlusso ) )
            return false;
        if ( tipologia == null ) {
            if ( other.tipologia != null )
                return false;
        } else if ( !tipologia.equals ( other.tipologia ) )
            return false;
        return true;
    }
}
