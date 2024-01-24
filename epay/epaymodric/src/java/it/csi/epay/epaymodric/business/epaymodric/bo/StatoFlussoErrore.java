/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric.bo;

import java.io.Serializable;
import java.sql.Timestamp;


/**
 *
 * @ author vsgro
 */
public class StatoFlussoErrore implements Serializable, Comparable<StatoFlussoErrore> {

    private static final long serialVersionUID = -6026717812386165166L;

    private Long id;

    private Timestamp dataAggiornamentoStato;

    private String descrizioneAggiuntivaErrore;

    private Errore errore;

    private FlussoOrigine flussoOrigine;

    public Long getId () {
        return id;
    }

    public void setId ( Long id ) {
        this.id = id;
    }

    public Timestamp getDataAggiornamentoStato () {
        return dataAggiornamentoStato;
    }

    public void setDataAggiornamentoStato ( Timestamp dataAggiornamentoStato ) {
        this.dataAggiornamentoStato = dataAggiornamentoStato;
    }

    public String getDescrizioneAggiuntivaErrore () {
        return descrizioneAggiuntivaErrore;
    }

    public void setDescrizioneAggiuntivaErrore ( String descrizioneAggiuntivaErrore ) {
        this.descrizioneAggiuntivaErrore = descrizioneAggiuntivaErrore;
    }

    public Errore getErrore () {
        return errore;
    }

    public void setErrore ( Errore errore ) {
        this.errore = errore;
    }

    public FlussoOrigine getFlussoOrigine () {
        return flussoOrigine;
    }

    public void setFlussoOrigine ( FlussoOrigine flussoOrigine ) {
        this.flussoOrigine = flussoOrigine;
    }

    @Override
    public int compareTo ( StatoFlussoErrore toCompare ) {
        int compare =
                        (this.id.intValue () == toCompare.getId ().intValue () ? 0 : -1 )
                        + this.descrizioneAggiuntivaErrore.compareTo ( toCompare.getDescrizioneAggiuntivaErrore () )
                        ;
        if (dataAggiornamentoStato!=null) {
            compare = compare
                            + (this.dataAggiornamentoStato.getTime () == toCompare.getDataAggiornamentoStato ().getTime ()  ? 0 : -1 );
        }
        if (errore!=null && toCompare.getErrore ()!=null) {
            compare = compare
                            + errore.compareTo (toCompare.getErrore ());
        } else {
            compare = compare -1;
        }
        if (flussoOrigine!=null) {
            compare = compare
                            + flussoOrigine.compareTo (toCompare.getFlussoOrigine ());
        } else {
            compare = compare -1;
        }
        return compare;
    }

    @Override
    public String toString () {
        StringBuffer stringBuffer = new StringBuffer ();
        stringBuffer.append ( "id: ["+id+"]" );
        if (flussoOrigine != null) {
            stringBuffer.append ( "identificativoIstitutoRicevente: ["+ flussoOrigine.getIdentificativoIstitutoRicevente ()  +"]" );
            stringBuffer.append ( "identificativoFlusso: ["+ flussoOrigine.getIdentificativoFlusso () +"]" );
        }
        if (dataAggiornamentoStato!=null) {
            stringBuffer.append ( "dataAggiornamentoStato: ["+dataAggiornamentoStato.getTime ()+"]" );
        }
        stringBuffer.append ( "descrizioneAggiuntivaErrore: ["+descrizioneAggiuntivaErrore+"]" );
        if (errore!=null) {
            stringBuffer.append ( "codice errore: ["+errore.getCodiceErrore ()+"]" );
            stringBuffer.append ( "descrizione errore: ["+errore.getDescrizioneErrore ()+"]" );
        }
        return stringBuffer.toString ();
    }

    @Override
    public int hashCode () {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( dataAggiornamentoStato == null ) ? 0 : dataAggiornamentoStato.hashCode () );
        result = prime * result + ( ( descrizioneAggiuntivaErrore == null ) ? 0 : descrizioneAggiuntivaErrore.hashCode () );
        result = prime * result + ( ( errore == null ) ? 0 : errore.hashCode () );
        result = prime * result + ( ( flussoOrigine == null ) ? 0 : flussoOrigine.hashCode () );
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
        StatoFlussoErrore other = (StatoFlussoErrore) obj;
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
        if ( errore == null ) {
            if ( other.errore != null )
                return false;
        } else if ( !errore.equals ( other.errore ) )
            return false;
        if ( flussoOrigine == null ) {
            if ( other.flussoOrigine != null )
                return false;
        } else if ( !flussoOrigine.equals ( other.flussoOrigine ) )
            return false;
        if ( id == null ) {
            if ( other.id != null )
                return false;
        } else if ( !id.equals ( other.id ) )
            return false;
        return true;
    }

}
