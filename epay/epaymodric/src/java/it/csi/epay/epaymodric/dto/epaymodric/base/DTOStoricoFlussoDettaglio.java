/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.dto.epaymodric.base;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * @author vsgro
 */
@XmlAccessorType ( XmlAccessType.PROPERTY )
@XmlType ( name = "dtoStoricoFlussoDettaglio" )
public class DTOStoricoFlussoDettaglio extends BaseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String anagraficaPagatore;

    private String codiceVersamento;

    private String codicefiscalePagatore;

    private Timestamp dataInserimento;

    private Timestamp dataModifica;

    private Timestamp dataPagamento;

    private String datiSpecificiDiRiscossione;

    private String descrizioneCausaleVersamento;

    private String esitoPagamento;

    private Long idFlussoSintesi;

    private String identificativoUnicoRiscossione;

    private String identificativoUnicoVersamento;

    private BigDecimal importoSingoloVersamento;

    private Integer indiceSingoloVersamento;

    private String statoInvioFruitore;

    private String transactionid;

    private String utenteInserimento;

    private String utenteModifica;

    public Long getId () {
        return id;
    }

    public void setId ( Long id ) {
        this.id = id;
    }


    public String getAnagraficaPagatore () {
        return anagraficaPagatore;
    }


    public void setAnagraficaPagatore ( String anagraficaPagatore ) {
        this.anagraficaPagatore = anagraficaPagatore;
    }


    public String getCodiceVersamento () {
        return codiceVersamento;
    }


    public void setCodiceVersamento ( String codiceVersamento ) {
        this.codiceVersamento = codiceVersamento;
    }


    public String getCodicefiscalePagatore () {
        return codicefiscalePagatore;
    }


    public void setCodicefiscalePagatore ( String codicefiscalePagatore ) {
        this.codicefiscalePagatore = codicefiscalePagatore;
    }

    @Override
    public Timestamp getDataInserimento () {
        return dataInserimento;
    }

    @Override
    public void setDataInserimento ( Timestamp dataInserimento ) {
        this.dataInserimento = dataInserimento;
    }

    @Override
    public Timestamp getDataModifica () {
        return dataModifica;
    }

    @Override
    public void setDataModifica ( Timestamp dataModifica ) {
        this.dataModifica = dataModifica;
    }

    public Timestamp getDataPagamento () {
        return dataPagamento;
    }

    public void setDataPagamento ( Timestamp dataPagamento ) {
        this.dataPagamento = dataPagamento;
    }

    public String getDatiSpecificiDiRiscossione () {
        return datiSpecificiDiRiscossione;
    }

    public void setDatiSpecificiDiRiscossione ( String datiSpecificiDiRiscossione ) {
        this.datiSpecificiDiRiscossione = datiSpecificiDiRiscossione;
    }

    public String getDescrizioneCausaleVersamento () {
        return descrizioneCausaleVersamento;
    }

    public void setDescrizioneCausaleVersamento ( String descrizioneCausaleVersamento ) {
        this.descrizioneCausaleVersamento = descrizioneCausaleVersamento;
    }

    public String getEsitoPagamento () {
        return esitoPagamento;
    }

    public void setEsitoPagamento ( String esitoPagamento ) {
        this.esitoPagamento = esitoPagamento;
    }

    public Long getIdFlussoSintesi () {
        return idFlussoSintesi;
    }

    public void setIdFlussoSintesi ( Long idFlussoSintesi ) {
        this.idFlussoSintesi = idFlussoSintesi;
    }

    public String getIdentificativoUnicoRiscossione () {
        return identificativoUnicoRiscossione;
    }

    public void setIdentificativoUnicoRiscossione ( String identificativoUnicoRiscossione ) {
        this.identificativoUnicoRiscossione = identificativoUnicoRiscossione;
    }

    public String getIdentificativoUnicoVersamento () {
        return identificativoUnicoVersamento;
    }

    public void setIdentificativoUnicoVersamento ( String identificativoUnicoVersamento ) {
        this.identificativoUnicoVersamento = identificativoUnicoVersamento;
    }

    public BigDecimal getImportoSingoloVersamento () {
        return importoSingoloVersamento;
    }

    public void setImportoSingoloVersamento ( BigDecimal importoSingoloVersamento ) {
        this.importoSingoloVersamento = importoSingoloVersamento;
    }

    public Integer getIndiceSingoloVersamento () {
        return indiceSingoloVersamento;
    }

    public void setIndiceSingoloVersamento ( Integer indiceSingoloVersamento ) {
        this.indiceSingoloVersamento = indiceSingoloVersamento;
    }

    public String getStatoInvioFruitore () {
        return statoInvioFruitore;
    }

    public void setStatoInvioFruitore ( String statoInvioFruitore ) {
        this.statoInvioFruitore = statoInvioFruitore;
    }

    public String getTransactionid () {
        return transactionid;
    }

    public void setTransactionid ( String transactionid ) {
        this.transactionid = transactionid;
    }

    @Override
    public String getUtenteInserimento () {
        return utenteInserimento;
    }

    @Override
    public void setUtenteInserimento ( String utenteInserimento ) {
        this.utenteInserimento = utenteInserimento;
    }


    @Override
    public String getUtenteModifica () {
        return utenteModifica;
    }


    @Override
    public void setUtenteModifica ( String utenteModifica ) {
        this.utenteModifica = utenteModifica;
    }

}
