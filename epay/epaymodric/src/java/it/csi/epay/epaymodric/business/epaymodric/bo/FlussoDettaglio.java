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
 * @ author
 */
public class FlussoDettaglio extends BaseBO implements Serializable, Comparable<FlussoDettaglio> {

    private static final long serialVersionUID = 6024907684664456910L;

    private Long id;

    private Long idFlussoSintesi;

    private FlussoSintesi flussoSintesi;

    private String identificativoUnicoVersamento;

    private String identificativoUnicoRiscossione;

    private String codiceVersamento;

    private String descrizioneVersamento;

    private String datiSpecificiDiRiscossione;

    private BigDecimal importoSingoloVersamento;

    private Timestamp dataPagamento;

    private String esitoPagamento;

    private String causale;

    private String idTransaction;

    private String statoInvioFruitore;

    private String anagraficaPagatore;

    private String codiceFiscalePagatore;

    private Integer indiceSingoloVersamento;

    public Long getId () {
        return id;
    }

    public void setId ( Long id ) {
        this.id = id;
    }

    public Long getIdFlussoSintesi () {
        return idFlussoSintesi;
    }

    public void setIdFlussoSintesi ( Long idFlussoSintesi ) {
        this.idFlussoSintesi = idFlussoSintesi;
    }

    public FlussoSintesi getFlussoSintesi () {
        return flussoSintesi;
    }

    public void setFlussoSintesi ( FlussoSintesi flussoSintesi ) {
        this.flussoSintesi = flussoSintesi;
    }

    public Integer getIndiceSingoloVersamento () {
        return indiceSingoloVersamento;
    }

    public String getIdentificativoUnicoVersamento () {
        return identificativoUnicoVersamento;
    }

    public void setIdentificativoUnicoVersamento ( String identificativoUnicoVersamento ) {
        this.identificativoUnicoVersamento = identificativoUnicoVersamento;
    }

    public String getIdentificativoUnicoRiscossione () {
        return identificativoUnicoRiscossione;
    }

    public void setIdentificativoUnicoRiscossione ( String identificativoUnicoRiscossione ) {
        this.identificativoUnicoRiscossione = identificativoUnicoRiscossione;
    }

    public String getCodiceVersamento () {
        return codiceVersamento;
    }

    public void setCodiceVersamento ( String codiceVersamento ) {
        this.codiceVersamento = codiceVersamento;
    }

    public String getDescrizioneVersamento () {
        return descrizioneVersamento;
    }

    public void setDescrizioneVersamento ( String descrizioneVersamento ) {
        this.descrizioneVersamento = descrizioneVersamento;
    }

    public String getDatiSpecificiDiRiscossione () {
        return datiSpecificiDiRiscossione;
    }

    public void setDatiSpecificiDiRiscossione ( String datiSpecificiDiRiscossione ) {
        this.datiSpecificiDiRiscossione = datiSpecificiDiRiscossione;
    }

    public BigDecimal getImportoSingoloVersamento () {
        return importoSingoloVersamento;
    }

    public void setImportoSingoloVersamento ( BigDecimal importoSingoloVersamento ) {
        this.importoSingoloVersamento = importoSingoloVersamento;
    }

    public Timestamp getDataPagamento () {
        return dataPagamento;
    }

    public void setDataPagamento ( Timestamp dataPagamento ) {
        this.dataPagamento = dataPagamento;
    }

    public String getEsitoPagamento () {
        return esitoPagamento;
    }

    public void setEsitoPagamento ( String esitoPagamento ) {
        this.esitoPagamento = esitoPagamento;
    }

    public String getCausale () {
        return causale;
    }

    public void setCausale ( String causale ) {
        this.causale = causale;
    }

    public String getIdTransaction () {
        return idTransaction;
    }

    public void setIdTransaction ( String idTransaction ) {
        this.idTransaction = idTransaction;
    }

    public String getStatoInvioFruitore () {
        return statoInvioFruitore;
    }

    public void setStatoInvioFruitore ( String statoInvioFruitore ) {
        this.statoInvioFruitore = statoInvioFruitore;
    }

    public String getAnagraficaPagatore () {
        return anagraficaPagatore;
    }

    public void setAnagraficaPagatore ( String anagraficaPagatore ) {
        this.anagraficaPagatore = anagraficaPagatore;
    }

    public String getCodiceFiscalePagatore () {
        return codiceFiscalePagatore;
    }

    public void setCodiceFiscalePagatore ( String codiceFiscalePagatore ) {
        this.codiceFiscalePagatore = codiceFiscalePagatore;
    }

    public Integer getIndiceSingoloPagamento () {
        return indiceSingoloVersamento;
    }

    public void setIndiceSingoloVersamento ( Integer indiceSingoloVersamento ) {
        this.indiceSingoloVersamento = indiceSingoloVersamento;
    }

    @Override
    public int compareTo ( FlussoDettaglio toCompare ) {
        int compare =
                        (this.id.intValue () == toCompare.getId ().intValue () ? 0 : -1 )
                        + (this.idFlussoSintesi.intValue () == toCompare.getIdFlussoSintesi ().intValue () ? 0 : -1 )
                        + this.identificativoUnicoVersamento.compareTo ( toCompare.getIdentificativoUnicoVersamento () )
                        + this.identificativoUnicoRiscossione.compareTo ( toCompare.getIdentificativoUnicoRiscossione () )
                        + this.codiceVersamento.compareTo ( toCompare.getCodiceVersamento () )
                        + this.descrizioneVersamento.compareTo ( toCompare.getDescrizioneVersamento () )
                        + this.datiSpecificiDiRiscossione.compareTo ( toCompare.getDatiSpecificiDiRiscossione () )
                        + this.importoSingoloVersamento.compareTo ( toCompare.getImportoSingoloVersamento () )
                        + this.dataPagamento.compareTo ( toCompare.getDataPagamento () )
                        + this.esitoPagamento.compareTo ( toCompare.getEsitoPagamento () )
                        + this.causale.compareTo ( toCompare.getCausale () )
                        + this.idTransaction.compareTo ( toCompare.getIdTransaction () )
                        + this.statoInvioFruitore.compareTo ( toCompare.getStatoInvioFruitore () )
                        + this.anagraficaPagatore.compareTo ( toCompare.getAnagraficaPagatore () )
                        + this.codiceFiscalePagatore.compareTo ( toCompare.getCodiceFiscalePagatore () )
                        + (this.indiceSingoloVersamento.intValue () == toCompare.getIndiceSingoloPagamento ().intValue () ? 0 : -1 )
                        ;
        return compare;
    }

    @Override
    public String toString () {
        StringBuffer stringBuffer = new StringBuffer ();
        stringBuffer.append ( "id: [" + id + "]" );
        stringBuffer.append ( "idFlussoSintesi: [" + idFlussoSintesi + "]" );
        stringBuffer.append ( "identificativoUnicoVersamento: [" + identificativoUnicoVersamento + "]" );
        stringBuffer.append ( "identificativoUnicoRiscossione: [" + identificativoUnicoRiscossione + "]" );
        stringBuffer.append ( "codiceVersamento: [" + codiceVersamento + "]" );
        stringBuffer.append ( "descrizioneVersamento: [" + descrizioneVersamento + "]" );
        stringBuffer.append ( "datiSpecificiDiRiscossione: [" + datiSpecificiDiRiscossione + "]" );
        if (importoSingoloVersamento!=null) {
            stringBuffer.append ( "importoSingoloVersamento: [" +importoSingoloVersamento.floatValue ()+ "]" );
        }
        if (dataPagamento!=null) {
            stringBuffer.append ( "dataPagamento: [" + (dataPagamento.getTime () ) + "]" );
        }
        stringBuffer.append ( "esitoPagamento: [" + esitoPagamento + "]" );
        stringBuffer.append ( "causale: [" + causale + "]" );
        stringBuffer.append ( "idTransaction: [" + idTransaction + "]" );
        stringBuffer.append ( "statoInvioFruitore: [" + statoInvioFruitore + "]" );
        stringBuffer.append ( "anagraficaPagatore: [" + anagraficaPagatore + "]" );
        stringBuffer.append ( "codiceFiscalePagatore: [" + codiceFiscalePagatore + "]" );
        stringBuffer.append ( "indiceSingoloVersamento: [" + indiceSingoloVersamento + "]" );
        return stringBuffer.toString ();
    }

    @Override
    public int hashCode () {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( anagraficaPagatore == null ) ? 0 : anagraficaPagatore.hashCode () );
        result = prime * result + ( ( causale == null ) ? 0 : causale.hashCode () );
        result = prime * result + ( ( codiceFiscalePagatore == null ) ? 0 : codiceFiscalePagatore.hashCode () );
        result = prime * result + ( ( codiceVersamento == null ) ? 0 : codiceVersamento.hashCode () );
        result = prime * result + ( ( dataPagamento == null ) ? 0 : dataPagamento.hashCode () );
        result = prime * result + ( ( datiSpecificiDiRiscossione == null ) ? 0 : datiSpecificiDiRiscossione.hashCode () );
        result = prime * result + ( ( descrizioneVersamento == null ) ? 0 : descrizioneVersamento.hashCode () );
        result = prime * result + ( ( esitoPagamento == null ) ? 0 : esitoPagamento.hashCode () );
        result = prime * result + ( ( flussoSintesi == null ) ? 0 : flussoSintesi.hashCode () );
        result = prime * result + ( ( id == null ) ? 0 : id.hashCode () );
        result = prime * result + ( ( idFlussoSintesi == null ) ? 0 : idFlussoSintesi.hashCode () );
        result = prime * result + ( ( idTransaction == null ) ? 0 : idTransaction.hashCode () );
        result = prime * result + ( ( identificativoUnicoRiscossione == null ) ? 0 : identificativoUnicoRiscossione.hashCode () );
        result = prime * result + ( ( identificativoUnicoVersamento == null ) ? 0 : identificativoUnicoVersamento.hashCode () );
        result = prime * result + ( ( importoSingoloVersamento == null ) ? 0 : importoSingoloVersamento.hashCode () );
        result = prime * result + ( ( indiceSingoloVersamento == null ) ? 0 : indiceSingoloVersamento.hashCode () );
        result = prime * result + ( ( statoInvioFruitore == null ) ? 0 : statoInvioFruitore.hashCode () );
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
        FlussoDettaglio other = (FlussoDettaglio) obj;
        if ( anagraficaPagatore == null ) {
            if ( other.anagraficaPagatore != null )
                return false;
        } else if ( !anagraficaPagatore.equals ( other.anagraficaPagatore ) )
            return false;
        if ( causale == null ) {
            if ( other.causale != null )
                return false;
        } else if ( !causale.equals ( other.causale ) )
            return false;
        if ( codiceFiscalePagatore == null ) {
            if ( other.codiceFiscalePagatore != null )
                return false;
        } else if ( !codiceFiscalePagatore.equals ( other.codiceFiscalePagatore ) )
            return false;
        if ( codiceVersamento == null ) {
            if ( other.codiceVersamento != null )
                return false;
        } else if ( !codiceVersamento.equals ( other.codiceVersamento ) )
            return false;
        if ( dataPagamento == null ) {
            if ( other.dataPagamento != null )
                return false;
        } else if ( !dataPagamento.equals ( other.dataPagamento ) )
            return false;
        if ( datiSpecificiDiRiscossione == null ) {
            if ( other.datiSpecificiDiRiscossione != null )
                return false;
        } else if ( !datiSpecificiDiRiscossione.equals ( other.datiSpecificiDiRiscossione ) )
            return false;
        if ( descrizioneVersamento == null ) {
            if ( other.descrizioneVersamento != null )
                return false;
        } else if ( !descrizioneVersamento.equals ( other.descrizioneVersamento ) )
            return false;
        if ( esitoPagamento == null ) {
            if ( other.esitoPagamento != null )
                return false;
        } else if ( !esitoPagamento.equals ( other.esitoPagamento ) )
            return false;
        if ( flussoSintesi == null ) {
            if ( other.flussoSintesi != null )
                return false;
        } else if ( !flussoSintesi.equals ( other.flussoSintesi ) )
            return false;
        if ( id == null ) {
            if ( other.id != null )
                return false;
        } else if ( !id.equals ( other.id ) )
            return false;
        if ( idFlussoSintesi == null ) {
            if ( other.idFlussoSintesi != null )
                return false;
        } else if ( !idFlussoSintesi.equals ( other.idFlussoSintesi ) )
            return false;
        if ( idTransaction == null ) {
            if ( other.idTransaction != null )
                return false;
        } else if ( !idTransaction.equals ( other.idTransaction ) )
            return false;
        if ( identificativoUnicoRiscossione == null ) {
            if ( other.identificativoUnicoRiscossione != null )
                return false;
        } else if ( !identificativoUnicoRiscossione.equals ( other.identificativoUnicoRiscossione ) )
            return false;
        if ( identificativoUnicoVersamento == null ) {
            if ( other.identificativoUnicoVersamento != null )
                return false;
        } else if ( !identificativoUnicoVersamento.equals ( other.identificativoUnicoVersamento ) )
            return false;
        if ( importoSingoloVersamento == null ) {
            if ( other.importoSingoloVersamento != null )
                return false;
        } else if ( !importoSingoloVersamento.equals ( other.importoSingoloVersamento ) )
            return false;
        if ( indiceSingoloVersamento == null ) {
            if ( other.indiceSingoloVersamento != null )
                return false;
        } else if ( !indiceSingoloVersamento.equals ( other.indiceSingoloVersamento ) )
            return false;
        if ( statoInvioFruitore == null ) {
            if ( other.statoInvioFruitore != null )
                return false;
        } else if ( !statoInvioFruitore.equals ( other.statoInvioFruitore ) )
            return false;
        return true;
    }

}
