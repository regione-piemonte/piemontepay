/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric.bo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;

import it.csi.epay.epaymodric.interfacews.epaymodric.enums.StatoElaborazioneEnum;


/**
 *
 * @ author vsgro
 */
public class Elaborazione extends BaseBO implements Serializable, Comparable<Elaborazione> {

    private static final long serialVersionUID = 5974720882634403820L;

    private Long id;

    private String idEnte;

    private Timestamp dataElaborazione;

    private StatoElaborazioneEnum statoElaborazione;

    private Timestamp dataInizio;

    private Timestamp dataFine;

    private ArrayList<String> listaFlussi;

    private Errore errore;

    private String msgErrore;

    //lfantini: la seguente property serve per evitare di aggiornare piu' volte la tabella nel caso di piu' flussi in errore
    private boolean statoAggiornato;

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

    public Timestamp getDataElaborazione () {
        return dataElaborazione;
    }

    public void setDataElaborazione ( Timestamp dataElaborazione ) {
        this.dataElaborazione = dataElaborazione;
    }

    public StatoElaborazioneEnum getStatoElaborazione () {
        return statoElaborazione;
    }

    public void setStatoElaborazione ( StatoElaborazioneEnum statoElaborazione ) {
        this.statoElaborazione = statoElaborazione;
    }

    public Timestamp getDataInizio () {
        return dataInizio;
    }

    public void setDataInizio ( Timestamp dataInizio ) {
        this.dataInizio = dataInizio;
    }

    public Timestamp getDataFine () {
        return dataFine;
    }

    public void setDataFine ( Timestamp dataFine ) {
        this.dataFine = dataFine;
    }

    public ArrayList<String> getListaFlussi () {
        return listaFlussi;
    }

    public void setListaFlussi ( ArrayList<String> listaFlussi ) {
        this.listaFlussi = listaFlussi;
    }

    public Errore getErrore () {
        return errore;
    }

    public void setErrore ( Errore errore ) {
        this.errore = errore;
    }

    public String getMsgErrore () {
        return msgErrore;
    }

    public void setMsgErrore ( String msgErrore ) {
        this.msgErrore = msgErrore;
    }

    @Override
    public int compareTo ( Elaborazione toCompare ) {
        int compare =
                        (this.id.intValue () == toCompare.getId ().intValue () ? 0 : -1 )
                        + this.idEnte.compareTo ( toCompare.getIdEnte () )
                        + this.dataElaborazione.compareTo ( toCompare.getDataElaborazione () )
                        + this.dataInizio.compareTo ( toCompare.getDataInizio () )
                        + this.dataFine.compareTo ( toCompare.getDataFine () )
                        + this.msgErrore.compareTo ( toCompare.getMsgErrore () )
                        ;
        if (statoElaborazione!=null && toCompare.getStatoElaborazione () !=null) {
            compare =  compare
                            + this.statoElaborazione.getCodice ().compareTo ( toCompare.getStatoElaborazione ().getCodice () );
        } else {
            compare = compare -1;
        }
        if (errore!=null && toCompare.getErrore () !=null) {
            compare =  compare
                            + this.errore.compareTo ( toCompare.getErrore () );
        } else {
            compare = compare -1;
        }
        if (listaFlussi!=null && toCompare.getListaFlussi () !=null) {
            if (listaFlussi.size ()==toCompare.getListaFlussi ().size ()) {
                for (int i=0; i<listaFlussi.size (); i++) {
                    compare =  compare
                                    + listaFlussi.get ( i ).compareTo ( toCompare.getListaFlussi ().get ( i ) );
                }
            }
        }
        return compare;
    }

    @Override
    public String toString () {
        StringBuffer stringBuffer = new StringBuffer ();
        stringBuffer.append ( "id: ["+id+"]" );
        stringBuffer.append ( "idEnte: ["+idEnte+"]" );
        if (dataElaborazione!=null) {
            stringBuffer.append ( "dataElaborazione: ["+dataElaborazione.getTime ()+"]" );
        }
        if (statoElaborazione!=null) {
            stringBuffer.append ( "statoElaborazione: ["+statoElaborazione.getCodice ()+"]" );
        }
        if (dataInizio!=null) {
            stringBuffer.append ( "dataInizio: ["+dataInizio.getTime ()+"]" );
        }
        if (dataFine!=null) {
            stringBuffer.append ( "dataFine: ["+dataFine.getTime ()+"]" );
        }
        if (errore!=null) {
            stringBuffer.append ( "errore: ["+errore.getCodiceErrore ()+"]" );
        }
        if (listaFlussi!=null && listaFlussi.size ()>0) {
            stringBuffer.append ( "listaFlussi: " );
            for (int i=0; i<listaFlussi.size (); i++) {
                stringBuffer.append ( "\n ["+listaFlussi.get ( i )+"]" );
            }
        }
        stringBuffer.append ( "msgErrore: ["+msgErrore+"]" );
        return stringBuffer.toString ();
    }

    public boolean isStatoAggiornato() {
        return statoAggiornato;
    }

    public void setStatoAggiornato(boolean statoAggiornato) {
        this.statoAggiornato = statoAggiornato;
    }

    @Override
    public int hashCode () {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( dataElaborazione == null ) ? 0 : dataElaborazione.hashCode () );
        result = prime * result + ( ( dataFine == null ) ? 0 : dataFine.hashCode () );
        result = prime * result + ( ( dataInizio == null ) ? 0 : dataInizio.hashCode () );
        result = prime * result + ( ( errore == null ) ? 0 : errore.hashCode () );
        result = prime * result + ( ( id == null ) ? 0 : id.hashCode () );
        result = prime * result + ( ( idEnte == null ) ? 0 : idEnte.hashCode () );
        result = prime * result + ( ( listaFlussi == null ) ? 0 : listaFlussi.hashCode () );
        result = prime * result + ( ( msgErrore == null ) ? 0 : msgErrore.hashCode () );
        result = prime * result + ( statoAggiornato ? 1231 : 1237 );
        result = prime * result + ( ( statoElaborazione == null ) ? 0 : statoElaborazione.hashCode () );
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
        Elaborazione other = (Elaborazione) obj;
        if ( dataElaborazione == null ) {
            if ( other.dataElaborazione != null )
                return false;
        } else if ( !dataElaborazione.equals ( other.dataElaborazione ) )
            return false;
        if ( dataFine == null ) {
            if ( other.dataFine != null )
                return false;
        } else if ( !dataFine.equals ( other.dataFine ) )
            return false;
        if ( dataInizio == null ) {
            if ( other.dataInizio != null )
                return false;
        } else if ( !dataInizio.equals ( other.dataInizio ) )
            return false;
        if ( errore == null ) {
            if ( other.errore != null )
                return false;
        } else if ( !errore.equals ( other.errore ) )
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
        if ( listaFlussi == null ) {
            if ( other.listaFlussi != null )
                return false;
        } else if ( !listaFlussi.equals ( other.listaFlussi ) )
            return false;
        if ( msgErrore == null ) {
            if ( other.msgErrore != null )
                return false;
        } else if ( !msgErrore.equals ( other.msgErrore ) )
            return false;
        if ( statoAggiornato != other.statoAggiornato )
            return false;
        if ( statoElaborazione != other.statoElaborazione )
            return false;
        return true;
    }

}
