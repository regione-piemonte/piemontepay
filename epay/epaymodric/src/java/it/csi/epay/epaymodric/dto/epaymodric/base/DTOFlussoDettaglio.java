/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.dto.epaymodric.base;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * @ author vsgro
 */
@XmlAccessorType ( XmlAccessType.PROPERTY )
@XmlType ( name = "dtoFlussoDettaglio" )
public class DTOFlussoDettaglio implements Serializable {

    private static final long serialVersionUID = -5131645756603097628L;

    private String id;

    private String identificativoUnicoVersamento;

    private String identificativoUnicoRiscossione;

    private String codiceVersamento;

    private String descrizioneVersamento;

    private String datiSpecificiDiRiscossione;

    private String importoSingoloVersamento;

    private String dataPagamento;

    private String esitoPagamento;

    private String causale;

    private String idTransaction;

    private String statoInvioFruitore;

    private String anagraficaPagatore;

    private String codiceFiscalePagatore;

    private String indiceSingoloVersamento;

    private DTOFlussoSintesi flussoSintesi = new DTOFlussoSintesi ();

    public String getId () {
        return id;
    }

    public void setId ( String id ) {
        this.id = id;
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

    public String getImportoSingoloVersamento () {
        return importoSingoloVersamento;
    }

    public void setImportoSingoloVersamento ( String importoSingoloVersamento ) {
        this.importoSingoloVersamento = importoSingoloVersamento;
    }

    public String getDataPagamento () {
        return dataPagamento;
    }

    public void setDataPagamento ( String dataPagamento ) {
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

    public String getIndiceSingoloVersamento () {
        return indiceSingoloVersamento;
    }

    public void setIndiceSingoloVersamento ( String indiceSingoloVersamento ) {
        this.indiceSingoloVersamento = indiceSingoloVersamento;
    }

    public DTOFlussoSintesi getFlussoSintesi () {
        return flussoSintesi;
    }

    public void setFlussoSintesi ( DTOFlussoSintesi flussoSintesi ) {
        this.flussoSintesi = flussoSintesi;
    }

    @Override
    public String toString () {
        StringBuffer stringBuffer = new StringBuffer ();
        stringBuffer.append ( "id: [" + id + "]" );
        if ( flussoSintesi != null ) {
            stringBuffer.append ( "\nflussoSintesi: [" + flussoSintesi.toString () + "]\n" );
        }
        stringBuffer.append ( "identificativoUnicoVersamento: [" + identificativoUnicoVersamento + "]" );
        stringBuffer.append ( "identificativoUnicoRiscossione: [" + identificativoUnicoRiscossione + "]" );
        stringBuffer.append ( "codiceVersamento: [" + codiceVersamento + "]" );
        stringBuffer.append ( "descrizioneVersamento: [" + descrizioneVersamento + "]" );
        stringBuffer.append ( "datiSpecificiDiRiscossione: [" + datiSpecificiDiRiscossione + "]" );
        if ( importoSingoloVersamento != null ) {
            stringBuffer.append ( "importoSingoloVersamento: [" + importoSingoloVersamento + "]" );
        }
        stringBuffer.append ( "dataPagamento: [" + ( dataPagamento ) + "]" );
        stringBuffer.append ( "esitoPagamento: [" + esitoPagamento + "]" );
        stringBuffer.append ( "causale: [" + causale + "]" );
        stringBuffer.append ( "idTransaction: [" + idTransaction + "]" );
        stringBuffer.append ( "statoInvioFruitore: [" + statoInvioFruitore + "]" );
        stringBuffer.append ( "anagraficaPagamtore: [" + anagraficaPagatore + "]" );
        stringBuffer.append ( "codiceFiscalePagatore: [" + codiceFiscalePagatore + "]" );
        stringBuffer.append ( "indiceSingoloVersamento: [" + indiceSingoloVersamento + "]" );
        return stringBuffer.toString ();
    }

}
