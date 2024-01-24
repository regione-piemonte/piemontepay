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
public class Errore implements Serializable, Comparable<Errore> {

    private static final long serialVersionUID = -800312933525573788L;

    private Long id;

    private String codiceErrore;

    private String descrizioneErrore;

    private Boolean flagMail;

    private Boolean flagRielaborazione;

    private String tipologia;

    public Long getId () {
        return id;
    }

    public void setId ( Long id ) {
        this.id = id;
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

    public Boolean getFlagMail () {
        return flagMail;
    }

    public void setFlagMail ( Boolean flagMail ) {
        this.flagMail = flagMail;
    }

    public Boolean getFlagRielaborazione () {
        return flagRielaborazione;
    }

    public void setFlagRielaborazione ( Boolean flagRielaborazione ) {
        this.flagRielaborazione = flagRielaborazione;
    }

    public String getTipologia () {
        return tipologia;
    }

    public void setTipologia ( String tipologia ) {
        this.tipologia = tipologia;
    }

    @Override
    public int compareTo ( Errore toCompare ) {

        int compare =
                        (this.id.intValue () == toCompare.getId ().intValue () ? 0 : -1 )
                        + this.codiceErrore.compareTo ( toCompare.getCodiceErrore () )
                        + this.descrizioneErrore.compareTo ( toCompare.getDescrizioneErrore () )
                        + (this.flagMail.booleanValue () == toCompare.getFlagMail ().booleanValue () ? 0 : -1  )
                        + (this.flagRielaborazione.booleanValue () ==  toCompare.getFlagRielaborazione ().booleanValue () ? 0 : -1  )
                        + this.tipologia.compareTo ( toCompare.getTipologia ()  )
                        ;

        return compare;
    }

    @Override
    public String toString () {
        StringBuffer stringBuffer = new StringBuffer ();

        stringBuffer.append ( "id: [" + id + "]" );
        stringBuffer.append ( "codiceErrore: [" + codiceErrore + "]" );
        stringBuffer.append ( "descrizioneErrore: [" + descrizioneErrore + "]" );
        stringBuffer.append ( "tipologia: [" + tipologia + "]" );
        stringBuffer.append ( "flagMail: [" + flagMail + "]" );
        stringBuffer.append ( "flagRielaborazione: [" + flagRielaborazione + "]" );

        return stringBuffer.toString ();
    }

    @Override
    public int hashCode () {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( codiceErrore == null ) ? 0 : codiceErrore.hashCode () );
        result = prime * result + ( ( descrizioneErrore == null ) ? 0 : descrizioneErrore.hashCode () );
        result = prime * result + ( ( flagMail == null ) ? 0 : flagMail.hashCode () );
        result = prime * result + ( ( flagRielaborazione == null ) ? 0 : flagRielaborazione.hashCode () );
        result = prime * result + ( ( id == null ) ? 0 : id.hashCode () );
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
        Errore other = (Errore) obj;
        if ( codiceErrore == null ) {
            if ( other.codiceErrore != null )
                return false;
        } else if ( !codiceErrore.equals ( other.codiceErrore ) )
            return false;
        if ( descrizioneErrore == null ) {
            if ( other.descrizioneErrore != null )
                return false;
        } else if ( !descrizioneErrore.equals ( other.descrizioneErrore ) )
            return false;
        if ( flagMail == null ) {
            if ( other.flagMail != null )
                return false;
        } else if ( !flagMail.equals ( other.flagMail ) )
            return false;
        if ( flagRielaborazione == null ) {
            if ( other.flagRielaborazione != null )
                return false;
        } else if ( !flagRielaborazione.equals ( other.flagRielaborazione ) )
            return false;
        if ( id == null ) {
            if ( other.id != null )
                return false;
        } else if ( !id.equals ( other.id ) )
            return false;
        if ( tipologia == null ) {
            if ( other.tipologia != null )
                return false;
        } else if ( !tipologia.equals ( other.tipologia ) )
            return false;
        return true;
    }

}
