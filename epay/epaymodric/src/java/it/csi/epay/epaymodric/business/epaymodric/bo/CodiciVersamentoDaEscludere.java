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
public class CodiciVersamentoDaEscludere implements Serializable, Comparable<CodiciVersamentoDaEscludere> {

    private static final long serialVersionUID = 6582581300366120363L;

    private Long id;

    private String codiceVersamento;

    private String idEnte;

    private String motivazione;

    public Long getId () {
        return id;
    }

    public void setId ( Long id ) {
        this.id = id;
    }

    public String getCodiceVersamento () {
        return codiceVersamento;
    }

    public void setCodiceVersamento ( String codiceVersamento ) {
        this.codiceVersamento = codiceVersamento;
    }

    public String getIdEnte () {
        return idEnte;
    }

    public void setIdEnte ( String idEnte ) {
        this.idEnte = idEnte;
    }

    public String getMotivazione () {
        return motivazione;
    }

    public void setMotivazione ( String motivazione ) {
        this.motivazione = motivazione;
    }

    @Override
    public int compareTo ( CodiciVersamentoDaEscludere toCompare ) {
        int compare =
                        (this.id.intValue () == toCompare.getId ().intValue () ? 0 : -1 )
                        + this.codiceVersamento.compareTo ( toCompare.getCodiceVersamento () )
                        + this.idEnte.compareTo ( toCompare.getIdEnte () )
                        + this.motivazione.compareTo ( toCompare.getMotivazione () );
        return compare;
    }

    @Override
    public String toString () {
        StringBuffer stringBuffer = new StringBuffer ();
        stringBuffer.append ( "id: [" + id + "]" );
        stringBuffer.append ( "codiceVersamento: [" + codiceVersamento + "]" );
        stringBuffer.append ( "idEnte: [" + idEnte + "]" );
        stringBuffer.append ( "motivazione: [" + motivazione + "]" );
        return stringBuffer.toString ();
    }

    @Override
    public int hashCode () {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( codiceVersamento == null ) ? 0 : codiceVersamento.hashCode () );
        result = prime * result + ( ( id == null ) ? 0 : id.hashCode () );
        result = prime * result + ( ( idEnte == null ) ? 0 : idEnte.hashCode () );
        result = prime * result + ( ( motivazione == null ) ? 0 : motivazione.hashCode () );
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
        CodiciVersamentoDaEscludere other = (CodiciVersamentoDaEscludere) obj;
        if ( codiceVersamento == null ) {
            if ( other.codiceVersamento != null )
                return false;
        } else if ( !codiceVersamento.equals ( other.codiceVersamento ) )
            return false;
        if ( id == null ) {
            if ( other.id != null )
                return false;
        } else if ( !id.equals ( other.id ) )
            return false;
        if ( idEnte == null ) {
            if ( other.idEnte != null )
                return false;
        } else if ( !idEnte.equals ( other.idEnte ) )
            return false;
        if ( motivazione == null ) {
            if ( other.motivazione != null )
                return false;
        } else if ( !motivazione.equals ( other.motivazione ) )
            return false;
        return true;
    }

}
