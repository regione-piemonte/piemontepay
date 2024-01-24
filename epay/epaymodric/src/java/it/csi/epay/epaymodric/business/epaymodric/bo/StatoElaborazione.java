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
public class StatoElaborazione implements Serializable, Comparable<StatoElaborazione> {

    private static final long serialVersionUID = 5974720882634403820L;

    private Long id;

    private String codiceStatoElaborazione;

    private String descrizioneStatoElaborazione;

    public Long getId () {
        return id;
    }

    public void setId ( Long id ) {
        this.id = id;
    }

    public String getCodiceStatoElaborazione () {
        return codiceStatoElaborazione;
    }

    public void setCodiceStatoElaborazione ( String codiceStatoElaborazione ) {
        this.codiceStatoElaborazione = codiceStatoElaborazione;
    }

    public String getDescrizioneStatoElaborazione () {
        return descrizioneStatoElaborazione;
    }

    public void setDescrizioneStatoElaborazione ( String descrizioneStatoElaborazione ) {
        this.descrizioneStatoElaborazione = descrizioneStatoElaborazione;
    }

    @Override
    public int compareTo ( StatoElaborazione toCompare ) {
        int compare =
                        (this.id.intValue () == toCompare.getId ().intValue () ? 0 : -1 )
                        + this.codiceStatoElaborazione.compareTo ( toCompare.getCodiceStatoElaborazione () )
                        + this.descrizioneStatoElaborazione.compareTo ( toCompare.getDescrizioneStatoElaborazione () )
                        ;
        return compare;
    }

    @Override
    public String toString () {
        StringBuffer stringBuffer = new StringBuffer ();
        stringBuffer.append ( "id: [" + id + "]" );
        stringBuffer.append ( "codiceStatoElaborazione: [" + codiceStatoElaborazione + "]" );
        stringBuffer.append ( "descrizioneStatoElaborazione: [" + descrizioneStatoElaborazione + "]" );
        return stringBuffer.toString ();
    }

    @Override
    public int hashCode () {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( codiceStatoElaborazione == null ) ? 0 : codiceStatoElaborazione.hashCode () );
        result = prime * result + ( ( descrizioneStatoElaborazione == null ) ? 0 : descrizioneStatoElaborazione.hashCode () );
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
        StatoElaborazione other = (StatoElaborazione) obj;
        if ( codiceStatoElaborazione == null ) {
            if ( other.codiceStatoElaborazione != null )
                return false;
        } else if ( !codiceStatoElaborazione.equals ( other.codiceStatoElaborazione ) )
            return false;
        if ( descrizioneStatoElaborazione == null ) {
            if ( other.descrizioneStatoElaborazione != null )
                return false;
        } else if ( !descrizioneStatoElaborazione.equals ( other.descrizioneStatoElaborazione ) )
            return false;
        if ( id == null ) {
            if ( other.id != null )
                return false;
        } else if ( !id.equals ( other.id ) )
            return false;
        return true;
    }

}
