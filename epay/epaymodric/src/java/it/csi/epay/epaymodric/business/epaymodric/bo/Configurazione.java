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
public class Configurazione implements Serializable, Comparable<Configurazione> {

    private static final long serialVersionUID = -8632080607351435058L;

    private Long id;

    private String idEnte;

    private String nomeAttributo;

    private String valore;

    public Long getId () {
        return id;
    }

    public void setId ( Long id ) {
        this.id = id;
    }

    public String getIdEnte () {
        return idEnte;
    }

    public void setIdEnte ( String idEnte ) {
        this.idEnte = idEnte;
    }

    public String getNomeAttributo () {
        return nomeAttributo;
    }

    public void setNomeAttributo ( String nomeAttributo ) {
        this.nomeAttributo = nomeAttributo;
    }

    public String getValore () {
        return valore;
    }

    public void setValore ( String valore ) {
        this.valore = valore;
    }

    @Override
    public int compareTo ( Configurazione toCompare ) {
        int compare =
                        (this.id.intValue () == toCompare.getId ().intValue () ? 0 : -1 )
                        + this.idEnte.compareTo ( toCompare.getIdEnte () )
                        + this.nomeAttributo.compareTo ( toCompare.getNomeAttributo () )
                        + this.valore.compareTo ( toCompare.getValore () );
        return compare;
    }

    @Override
    public String toString () {
        StringBuffer stringBuffer = new StringBuffer ();
        stringBuffer.append ( "id: [" + id + "]" );
        stringBuffer.append ( "idEnte: [" + idEnte + "]" );
        stringBuffer.append ( "nomeAttributo: [" + nomeAttributo + "]" );
        stringBuffer.append ( "valore: [" + valore + "]" );
        return stringBuffer.toString ();
    }

    @Override
    public int hashCode () {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( id == null ) ? 0 : id.hashCode () );
        result = prime * result + ( ( idEnte == null ) ? 0 : idEnte.hashCode () );
        result = prime * result + ( ( nomeAttributo == null ) ? 0 : nomeAttributo.hashCode () );
        result = prime * result + ( ( valore == null ) ? 0 : valore.hashCode () );
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
        Configurazione other = (Configurazione) obj;
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
        if ( nomeAttributo == null ) {
            if ( other.nomeAttributo != null )
                return false;
        } else if ( !nomeAttributo.equals ( other.nomeAttributo ) )
            return false;
        if ( valore == null ) {
            if ( other.valore != null )
                return false;
        } else if ( !valore.equals ( other.valore ) )
            return false;
        return true;
    }

}
