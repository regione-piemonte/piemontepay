/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysim.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 *
 */
@XmlAccessorType ( XmlAccessType.FIELD )
public class FlussoDettaglioPagopaOutputDTO extends ParentOutput implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Long id;

    private String anagraficaPagatore;

    private String causale;

    @XmlSchemaType ( name = "dateTime" )
    private XMLGregorianCalendar dataPagamento;

    private String descrizioneCausaleVersamento;

    private String esitoPagamento;

    private String identificativoUnicoRiscossione;

    private String identificativoUnicoVersamento;

    private BigDecimal importoSingoloVersamento;

    private Integer indiceSingoloVersamento;

    private String transactionid;

    private FlussoSintesiPagopaOutputDTO flussoSintesiPagopaOutputDTO;

    public FlussoDettaglioPagopaOutputDTO () {
    }

    /**
     * @return the id
     */
    public Long getId () {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId ( Long id ) {
        this.id = id;
    }

    /**
     * @return the anagraficaPagatore
     */
    public String getAnagraficaPagatore () {
        return anagraficaPagatore;
    }

    /**
     * @param anagraficaPagatore the anagraficaPagatore to set
     */
    public void setAnagraficaPagatore ( String anagraficaPagatore ) {
        this.anagraficaPagatore = anagraficaPagatore;
    }

    /**
     * @return the causale
     */
    public String getCausale () {
        return causale;
    }

    /**
     * @param causale the causale to set
     */
    public void setCausale ( String causale ) {
        this.causale = causale;
    }

    /**
     * @return the dataPagamento
     */
    public XMLGregorianCalendar getDataPagamento () {
        return dataPagamento;
    }

    /**
     * @param dataPagamento the dataPagamento to set
     */
    public void setDataPagamento ( XMLGregorianCalendar dataPagamento ) {
        this.dataPagamento = dataPagamento;
    }

    /**
     * @return the descrizioneCausaleVersamento
     */
    public String getDescrizioneCausaleVersamento () {
        return descrizioneCausaleVersamento;
    }

    /**
     * @param descrizioneCausaleVersamento the descrizioneCausaleVersamento to set
     */
    public void setDescrizioneCausaleVersamento ( String descrizioneCausaleVersamento ) {
        this.descrizioneCausaleVersamento = descrizioneCausaleVersamento;
    }

    /**
     * @return the esitoPagamento
     */
    public String getEsitoPagamento () {
        return esitoPagamento;
    }

    /**
     * @param esitoPagamento the esitoPagamento to set
     */
    public void setEsitoPagamento ( String esitoPagamento ) {
        this.esitoPagamento = esitoPagamento;
    }

    /**
     * @return the identificativoUnicoRiscossione
     */
    public String getIdentificativoUnicoRiscossione () {
        return identificativoUnicoRiscossione;
    }

    /**
     * @param identificativoUnicoRiscossione the identificativoUnicoRiscossione to set
     */
    public void setIdentificativoUnicoRiscossione ( String identificativoUnicoRiscossione ) {
        this.identificativoUnicoRiscossione = identificativoUnicoRiscossione;
    }

    /**
     * @return the identificativoUnicoVersamento
     */
    public String getIdentificativoUnicoVersamento () {
        return identificativoUnicoVersamento;
    }

    /**
     * @param identificativoUnicoVersamento the identificativoUnicoVersamento to set
     */
    public void setIdentificativoUnicoVersamento ( String identificativoUnicoVersamento ) {
        this.identificativoUnicoVersamento = identificativoUnicoVersamento;
    }

    /**
     * @return the importoSingoloVersamento
     */
    public BigDecimal getImportoSingoloVersamento () {
        return importoSingoloVersamento;
    }

    /**
     * @param importoSingoloVersamento the importoSingoloVersamento to set
     */
    public void setImportoSingoloVersamento ( BigDecimal importoSingoloVersamento ) {
        this.importoSingoloVersamento = importoSingoloVersamento;
    }

    /**
     * @return the indiceSingoloVersamento
     */
    public Integer getIndiceSingoloVersamento () {
        return indiceSingoloVersamento;
    }

    /**
     * @param indiceSingoloVersamento the indiceSingoloVersamento to set
     */
    public void setIndiceSingoloVersamento ( Integer indiceSingoloVersamento ) {
        this.indiceSingoloVersamento = indiceSingoloVersamento;
    }

    /**
     * @return the transactionid
     */
    public String getTransactionid () {
        return transactionid;
    }

    /**
     * @param transactionid the transactionid to set
     */
    public void setTransactionid ( String transactionid ) {
        this.transactionid = transactionid;
    }

    /**
     * @return the simTFlussoSintesiPagopa
     */
    public FlussoSintesiPagopaOutputDTO getFlussoSintesiPagopaOutputDTO () {
        return flussoSintesiPagopaOutputDTO;
    }

    /**
     * @param simTFlussoSintesiPagopa the simTFlussoSintesiPagopa to set
     */
    public void setFlussoSintesiPagopaOutputDTO ( FlussoSintesiPagopaOutputDTO flussoSintesiPagopaOutputDTO ) {
        this.flussoSintesiPagopaOutputDTO = flussoSintesiPagopaOutputDTO;
    }

    public FlussoDettaglioPagopaOutputDTO ( Long id, String anagraficaPagatore, String causale, XMLGregorianCalendar dataPagamento,
        String descrizioneCausaleVersamento,
        String esitoPagamento, String identificativoUnicoRiscossione, String identificativoUnicoVersamento, BigDecimal importoSingoloVersamento,
        Integer indiceSingoloVersamento, String transactionid, FlussoSintesiPagopaOutputDTO flussoSintesiPagopaOutputDTO ) {
        super ();
        this.id = id;
        this.anagraficaPagatore = anagraficaPagatore;
        this.causale = causale;
        this.dataPagamento = dataPagamento;
        this.descrizioneCausaleVersamento = descrizioneCausaleVersamento;
        this.esitoPagamento = esitoPagamento;
        this.identificativoUnicoRiscossione = identificativoUnicoRiscossione;
        this.identificativoUnicoVersamento = identificativoUnicoVersamento;
        this.importoSingoloVersamento = importoSingoloVersamento;
        this.indiceSingoloVersamento = indiceSingoloVersamento;
        this.transactionid = transactionid;
        this.flussoSintesiPagopaOutputDTO = flussoSintesiPagopaOutputDTO;
    }

}
