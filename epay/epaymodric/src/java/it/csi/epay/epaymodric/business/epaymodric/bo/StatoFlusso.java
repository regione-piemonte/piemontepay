/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric.bo;

import java.io.Serializable;


/**
 *
 * @ author vsgro
 */
public class StatoFlusso implements Serializable, Comparable<StatoFlusso> {

    private static final long serialVersionUID = 1041867229097347624L;

    private Long id;

    private String codiceStatoFlusso;

    private String descrizioneStatoFlusso;

    private Boolean permettiRielaborazione;

    public Long getId () {
        return id;
    }

    public void setId ( Long id ) {
        this.id = id;
    }

    public String getCodiceStatoFlusso () {
        return codiceStatoFlusso;
    }

    public void setCodiceStatoFlusso ( String codiceStatoFlusso ) {
        this.codiceStatoFlusso = codiceStatoFlusso;
    }

    public String getDescrizioneStatoFlusso () {
        return descrizioneStatoFlusso;
    }

    public void setDescrizioneStatoFlusso ( String descrizioneStatoFlusso ) {
        this.descrizioneStatoFlusso = descrizioneStatoFlusso;
    }

    public Boolean getPermettiRielaborazione () {
        return permettiRielaborazione;
    }

    public void setPermettiRielaborazione ( Boolean permettiRielaborazione ) {
        this.permettiRielaborazione = permettiRielaborazione;
    }

    @Override
    public int compareTo ( StatoFlusso toCompare ) {
        int compare =
                        (this.id.intValue () == toCompare.getId ().intValue () ? 0 : -1 )
                        + this.codiceStatoFlusso.compareTo ( toCompare.getCodiceStatoFlusso () )
                        + this.descrizioneStatoFlusso.compareTo ( toCompare.getDescrizioneStatoFlusso () )
                        ;
        return compare;
    }

    @Override
    public String toString () {
        StringBuffer stringBuffer = new StringBuffer ();
        stringBuffer.append ( "id: [" + id + "]" );
        stringBuffer.append ( "codiceStatoFlusso: [" + codiceStatoFlusso + "]" );
        stringBuffer.append ( "descrizioneStatoFlusso: [" + descrizioneStatoFlusso + "]" );
        return stringBuffer.toString ();
    }

    @Override
    public int hashCode () {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( codiceStatoFlusso == null ) ? 0 : codiceStatoFlusso.hashCode () );
        result = prime * result + ( ( descrizioneStatoFlusso == null ) ? 0 : descrizioneStatoFlusso.hashCode () );
        result = prime * result + ( ( id == null ) ? 0 : id.hashCode () );
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
        StatoFlusso other = (StatoFlusso) obj;
        if ( codiceStatoFlusso == null ) {
            if ( other.codiceStatoFlusso != null )
                return false;
        } else if ( !codiceStatoFlusso.equals ( other.codiceStatoFlusso ) )
            return false;
        if ( descrizioneStatoFlusso == null ) {
            if ( other.descrizioneStatoFlusso != null )
                return false;
        } else if ( !descrizioneStatoFlusso.equals ( other.descrizioneStatoFlusso ) )
            return false;
        if ( id == null ) {
            if ( other.id != null )
                return false;
        } else if ( !id.equals ( other.id ) )
            return false;
        return true;
    }

}
