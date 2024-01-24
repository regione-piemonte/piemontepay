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
public class Psp implements Serializable, Comparable<Psp> {

    private static final long serialVersionUID = 9158114923692867188L;

    private Long id;

    private String identificativo;

    private String denominazione;

    private Boolean flagRiconciliabile;

    public Long getId () {
        return id;
    }

    public void setId ( Long id ) {
        this.id = id;
    }

    public String getIdentificativo () {
        return identificativo;
    }

    public void setIdentificativo ( String identificativo ) {
        this.identificativo = identificativo;
    }

    public String getDenominazione () {
        return denominazione;
    }

    public void setDenominazione ( String denominazione ) {
        this.denominazione = denominazione;
    }

    public Boolean isFlagRiconciliabile () {
        return flagRiconciliabile;
    }

    public void setFlagRiconciliabile ( Boolean flagRiconciliabile ) {
        this.flagRiconciliabile = flagRiconciliabile;
    }

    @Override
    public int compareTo ( Psp toCompare ) {
        int compare =
                        (this.id.intValue () == toCompare.getId ().intValue () ? 0 : -1 )
                        + this.identificativo.compareTo ( toCompare.getIdentificativo () )
                        + this.denominazione.compareTo ( toCompare.getDenominazione () )
                        ;
        if (flagRiconciliabile!=null) {
            compare = compare
                            + this.flagRiconciliabile.compareTo ( toCompare.isFlagRiconciliabile () );
        }
        return compare;
    }

    @Override
    public String toString () {
        StringBuffer stringBuffer = new StringBuffer ();
        stringBuffer.append ( "id: [" + id + "]" );
        stringBuffer.append ( "identificativo: [" + identificativo + "]" );
        stringBuffer.append ( "denominazione: [" + denominazione + "]" );
        if (flagRiconciliabile!=null) {
            stringBuffer.append ( "flagRiconciliabile: [" + flagRiconciliabile.booleanValue () + "]" );
        }
        return stringBuffer.toString ();
    }

    @Override
    public int hashCode () {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( denominazione == null ) ? 0 : denominazione.hashCode () );
        result = prime * result + ( ( flagRiconciliabile == null ) ? 0 : flagRiconciliabile.hashCode () );
        result = prime * result + ( ( id == null ) ? 0 : id.hashCode () );
        result = prime * result + ( ( identificativo == null ) ? 0 : identificativo.hashCode () );
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
        Psp other = (Psp) obj;
        if ( denominazione == null ) {
            if ( other.denominazione != null )
                return false;
        } else if ( !denominazione.equals ( other.denominazione ) )
            return false;
        if ( flagRiconciliabile == null ) {
            if ( other.flagRiconciliabile != null )
                return false;
        } else if ( !flagRiconciliabile.equals ( other.flagRiconciliabile ) )
            return false;
        if ( id == null ) {
            if ( other.id != null )
                return false;
        } else if ( !id.equals ( other.id ) )
            return false;
        if ( identificativo == null ) {
            if ( other.identificativo != null )
                return false;
        } else if ( !identificativo.equals ( other.identificativo ) )
            return false;
        return true;
    }

}
