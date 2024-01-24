/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric.bo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 *
 * @ author vsgro
 */
public class Provvisori extends BaseBO implements Serializable, Comparable<Provvisori> {

    private static final long serialVersionUID = -3315181448991242657L;

    private Long id;

    private Integer annoEsercizio;

    private Integer annoProvvisorio;

    private String causale;

    private Timestamp dataFine;

    private Timestamp dataMovimento;

    private String descrizione;

    private String idEnte;

    private String identificativoFlusso;

    private BigDecimal importoDisponibilita;

    private BigDecimal importoProvvisorio;

    private Integer numeroProvvisorio;

    private String stato;

    private String tipoMovimento;

    public Long getId () {
        return id;
    }

    public void setId ( Long id ) {
        this.id = id;
    }

    public Integer getAnnoEsercizio () {
        return annoEsercizio;
    }

    public void setAnnoEsercizio ( Integer annoEsercizio ) {
        this.annoEsercizio = annoEsercizio;
    }

    public Integer getAnnoProvvisorio () {
        return annoProvvisorio;
    }

    public void setAnnoProvvisorio ( Integer annoProvvisorio ) {
        this.annoProvvisorio = annoProvvisorio;
    }

    public String getCausale () {
        return causale;
    }

    public void setCausale ( String causale ) {
        this.causale = causale;
    }

    public Timestamp getDataFine () {
        return dataFine;
    }

    public void setDataFine ( Timestamp dataFine ) {
        this.dataFine = dataFine;
    }

    public Timestamp getDataMovimento () {
        return dataMovimento;
    }

    public void setDataMovimento ( Timestamp dataMovimento ) {
        this.dataMovimento = dataMovimento;
    }

    public String getDescrizione () {
        return descrizione;
    }

    public void setDescrizione ( String descrizione ) {
        this.descrizione = descrizione;
    }

    public String getIdEnte () {
        return idEnte;
    }

    public void setIdEnte ( String idEnte ) {
        this.idEnte = idEnte;
    }

    public String getIdentificativoFlusso () {
        return identificativoFlusso;
    }

    public void setIdentificativoFlusso ( String identificativoFlusso ) {
        this.identificativoFlusso = identificativoFlusso;
    }

    public BigDecimal getImportoDisponibilita () {
        return importoDisponibilita;
    }

    public void setImportoDisponibilita ( BigDecimal importoDisponibilita ) {
        this.importoDisponibilita = importoDisponibilita;
    }

    public BigDecimal getImportoProvvisorio () {
        return importoProvvisorio;
    }

    public void setImportoProvvisorio ( BigDecimal importoProvvisorio ) {
        this.importoProvvisorio = importoProvvisorio;
    }

    public Integer getNumeroProvvisorio () {
        return numeroProvvisorio;
    }

    public void setNumeroProvvisorio ( Integer numeroProvvisorio ) {
        this.numeroProvvisorio = numeroProvvisorio;
    }

    public String getStato () {
        return stato;
    }

    public void setStato ( String stato ) {
        this.stato = stato;
    }

    public String getTipoMovimento () {
        return tipoMovimento;
    }

    public void setTipoMovimento ( String tipoMovimento ) {
        this.tipoMovimento = tipoMovimento;
    }

    @Override
    public int compareTo ( Provvisori toCompare ) {
        int compare =
                        (this.id.intValue () == toCompare.getId ().intValue () ? 0 : -1 )
                        + this.causale.compareTo ( toCompare.causale )
                        + this.descrizione.compareTo ( toCompare.getDescrizione () )
                        + this.idEnte.compareTo ( toCompare.getIdEnte () )
                        + this.identificativoFlusso.compareTo ( toCompare.getIdentificativoFlusso () )
                        + this.stato.compareTo ( toCompare.getStato () )
                        + this.tipoMovimento.compareTo ( toCompare.getTipoMovimento () )
                        ;
        if (annoEsercizio!=null) {
            compare = compare
                            + (this.annoEsercizio.intValue () == toCompare.getAnnoEsercizio ().intValue () ? 0 : -1 );
        }
        if (annoProvvisorio!=null) {
            compare = compare
                            + (this.annoProvvisorio.intValue () == toCompare.getAnnoProvvisorio ().intValue () ? 0 : -1 );
        }
        if (dataFine!=null) {
            compare = compare
                            + (this.dataFine.getTime () == toCompare.getDataFine ().getTime () ? 0 : -1 );
        }
        if (dataMovimento!=null) {
            compare = compare
                            + (this.dataMovimento.getTime () == toCompare.getDataMovimento ().getTime () ? 0 : -1 );
        }
        if (importoDisponibilita!=null) {
            compare = compare
                            + this.importoDisponibilita.compareTo ( toCompare.getImportoDisponibilita ());
        }
        if (importoProvvisorio!=null) {
            compare = compare
                            + this.importoProvvisorio.compareTo ( toCompare.getImportoProvvisorio ());
        }
        if (numeroProvvisorio!=null) {
            compare = compare
                            + (this.numeroProvvisorio.intValue () == toCompare.getNumeroProvvisorio ().intValue () ? 0 : -1 );
        }
        return compare;
    }

    @Override
    public String toString () {
        StringBuffer stringBuffer = new StringBuffer ();
        stringBuffer.append ( "id: [" + id + "]" );
        if (annoEsercizio!=null) {
            stringBuffer.append ( "annoEsercizio: [" + annoEsercizio.intValue () + "]" );
        }
        if (annoProvvisorio!=null) {
            stringBuffer.append ( "annoProvvisorio: [" + annoProvvisorio.intValue () + "]" );
        }
        stringBuffer.append ( "causale: [" + causale + "]" );
        if(dataFine!=null) {
            stringBuffer.append ( "dataFine: [" + dataFine.getTime () + "]" );
        }
        if(dataMovimento!=null) {
            stringBuffer.append ( "dataMovimento: [" + dataMovimento.getTime () + "]" );
        }
        stringBuffer.append ( "descrizione: [" + descrizione + "]" );
        stringBuffer.append ( "idEnte: [" + idEnte + "]" );
        stringBuffer.append ( "identificativoFlusso: [" + identificativoFlusso + "]" );
        if (importoDisponibilita!=null) {
            stringBuffer.append ( "importoDisponibilita: [" + importoDisponibilita.floatValue () + "]" );
        }
        if (importoProvvisorio!=null) {
            stringBuffer.append ( "importoProvvisorio: [" + importoProvvisorio.floatValue () + "]" );
        }
        if (numeroProvvisorio!=null) {
            stringBuffer.append ( "numeroProvvisorio: [" + numeroProvvisorio.intValue () + "]" );
        }
        stringBuffer.append ( "stato: [" + stato + "]" );
        stringBuffer.append ( "tipoMovimento: [" + tipoMovimento + "]" );
        return stringBuffer.toString ();
    }

    @Override
    public int hashCode () {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( annoEsercizio == null ) ? 0 : annoEsercizio.hashCode () );
        result = prime * result + ( ( annoProvvisorio == null ) ? 0 : annoProvvisorio.hashCode () );
        result = prime * result + ( ( causale == null ) ? 0 : causale.hashCode () );
        result = prime * result + ( ( dataFine == null ) ? 0 : dataFine.hashCode () );
        result = prime * result + ( ( dataMovimento == null ) ? 0 : dataMovimento.hashCode () );
        result = prime * result + ( ( descrizione == null ) ? 0 : descrizione.hashCode () );
        result = prime * result + ( ( id == null ) ? 0 : id.hashCode () );
        result = prime * result + ( ( idEnte == null ) ? 0 : idEnte.hashCode () );
        result = prime * result + ( ( identificativoFlusso == null ) ? 0 : identificativoFlusso.hashCode () );
        result = prime * result + ( ( importoDisponibilita == null ) ? 0 : importoDisponibilita.hashCode () );
        result = prime * result + ( ( importoProvvisorio == null ) ? 0 : importoProvvisorio.hashCode () );
        result = prime * result + ( ( numeroProvvisorio == null ) ? 0 : numeroProvvisorio.hashCode () );
        result = prime * result + ( ( stato == null ) ? 0 : stato.hashCode () );
        result = prime * result + ( ( tipoMovimento == null ) ? 0 : tipoMovimento.hashCode () );
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
        Provvisori other = (Provvisori) obj;
        if ( annoEsercizio == null ) {
            if ( other.annoEsercizio != null )
                return false;
        } else if ( !annoEsercizio.equals ( other.annoEsercizio ) )
            return false;
        if ( annoProvvisorio == null ) {
            if ( other.annoProvvisorio != null )
                return false;
        } else if ( !annoProvvisorio.equals ( other.annoProvvisorio ) )
            return false;
        if ( causale == null ) {
            if ( other.causale != null )
                return false;
        } else if ( !causale.equals ( other.causale ) )
            return false;
        if ( dataFine == null ) {
            if ( other.dataFine != null )
                return false;
        } else if ( !dataFine.equals ( other.dataFine ) )
            return false;
        if ( dataMovimento == null ) {
            if ( other.dataMovimento != null )
                return false;
        } else if ( !dataMovimento.equals ( other.dataMovimento ) )
            return false;
        if ( descrizione == null ) {
            if ( other.descrizione != null )
                return false;
        } else if ( !descrizione.equals ( other.descrizione ) )
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
        if ( identificativoFlusso == null ) {
            if ( other.identificativoFlusso != null )
                return false;
        } else if ( !identificativoFlusso.equals ( other.identificativoFlusso ) )
            return false;
        if ( importoDisponibilita == null ) {
            if ( other.importoDisponibilita != null )
                return false;
        } else if ( !importoDisponibilita.equals ( other.importoDisponibilita ) )
            return false;
        if ( importoProvvisorio == null ) {
            if ( other.importoProvvisorio != null )
                return false;
        } else if ( !importoProvvisorio.equals ( other.importoProvvisorio ) )
            return false;
        if ( numeroProvvisorio == null ) {
            if ( other.numeroProvvisorio != null )
                return false;
        } else if ( !numeroProvvisorio.equals ( other.numeroProvvisorio ) )
            return false;
        if ( stato == null ) {
            if ( other.stato != null )
                return false;
        } else if ( !stato.equals ( other.stato ) )
            return false;
        if ( tipoMovimento == null ) {
            if ( other.tipoMovimento != null )
                return false;
        } else if ( !tipoMovimento.equals ( other.tipoMovimento ) )
            return false;
        return true;
    }

}
