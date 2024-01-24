/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaysim.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


public class FlussoDettaglioPagopaDTO extends ParentInput implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Integer accertamentoAnno;

    private Integer accertamentoNumero;

    private String anagraficaPagatore;

    private String causale;

    private String codiceVersamento;

    private String codicefiscalePagatore;

    //private Timestamp dataPagamento;
    private Date dataPagamento;

    private String datiSpecificiDiRiscossione;

    private String descrizioneCausaleVersamento;

    private String esitoPagamento;

    private String identificativoUnicoRiscossione;

    private String identificativoUnicoVersamento;

    private BigDecimal importoSingoloVersamento;

    private Integer indiceSingoloVersamento;

    private String statoInvioFruitore;

    private String tipoAnagraficaPagatore;

    private String transactionid;

    private FlussoSintesiPagopaDTO simTFlussoSintesiPagopa;

    public FlussoDettaglioPagopaDTO () {
    }

    public FlussoDettaglioPagopaDTO ( Long id, Integer accertamentoAnno, Integer accertamentoNumero, String anagraficaPagatore, String causale,
        String codiceVersamento, String codicefiscalePagatore, Date dataPagamento, String datiSpecificiDiRiscossione, String descrizioneCausaleVersamento,
        String esitoPagamento, String identificativoUnicoRiscossione, String identificativoUnicoVersamento, BigDecimal importoSingoloVersamento,
        Integer indiceSingoloVersamento, String statoInvioFruitore, String tipoAnagraficaPagatore, String transactionid,
        FlussoSintesiPagopaDTO simTFlussoSintesiPagopa ) {
        super ();
        this.id = id;
        this.accertamentoAnno = accertamentoAnno;
        this.accertamentoNumero = accertamentoNumero;
        this.anagraficaPagatore = anagraficaPagatore;
        this.causale = causale;
        this.codiceVersamento = codiceVersamento;
        this.codicefiscalePagatore = codicefiscalePagatore;
        this.dataPagamento = dataPagamento;
        this.datiSpecificiDiRiscossione = datiSpecificiDiRiscossione;
        this.descrizioneCausaleVersamento = descrizioneCausaleVersamento;
        this.esitoPagamento = esitoPagamento;
        this.identificativoUnicoRiscossione = identificativoUnicoRiscossione;
        this.identificativoUnicoVersamento = identificativoUnicoVersamento;
        this.importoSingoloVersamento = importoSingoloVersamento;
        this.indiceSingoloVersamento = indiceSingoloVersamento;
        this.statoInvioFruitore = statoInvioFruitore;
        this.tipoAnagraficaPagatore = tipoAnagraficaPagatore;
        this.transactionid = transactionid;
        this.simTFlussoSintesiPagopa = simTFlussoSintesiPagopa;
    }

    public Long getId () {
        return this.id;
    }

    public void setId ( Long id ) {
        this.id = id;
    }

    public Integer getAccertamentoAnno () {
        return this.accertamentoAnno;
    }

    public void setAccertamentoAnno ( Integer accertamentoAnno ) {
        this.accertamentoAnno = accertamentoAnno;
    }

    public Integer getAccertamentoNumero () {
        return this.accertamentoNumero;
    }

    public void setAccertamentoNumero ( Integer accertamentoNumero ) {
        this.accertamentoNumero = accertamentoNumero;
    }

    public String getAnagraficaPagatore () {
        return this.anagraficaPagatore;
    }

    public void setAnagraficaPagatore ( String anagraficaPagatore ) {
        this.anagraficaPagatore = anagraficaPagatore;
    }

    public String getCausale () {
        return this.causale;
    }

    public void setCausale ( String causale ) {
        this.causale = causale;
    }

    public String getCodiceVersamento () {
        return this.codiceVersamento;
    }

    public void setCodiceVersamento ( String codiceVersamento ) {
        this.codiceVersamento = codiceVersamento;
    }

    public String getCodicefiscalePagatore () {
        return this.codicefiscalePagatore;
    }

    public void setCodicefiscalePagatore ( String codicefiscalePagatore ) {
        this.codicefiscalePagatore = codicefiscalePagatore;
    }

    public Date getDataPagamento () {
        return this.dataPagamento;
    }

    public void setDataPagamento ( Date dataPagamento ) {
        this.dataPagamento = dataPagamento;
    }

    public String getDatiSpecificiDiRiscossione () {
        return this.datiSpecificiDiRiscossione;
    }

    public void setDatiSpecificiDiRiscossione ( String datiSpecificiDiRiscossione ) {
        this.datiSpecificiDiRiscossione = datiSpecificiDiRiscossione;
    }

    public String getDescrizioneCausaleVersamento () {
        return this.descrizioneCausaleVersamento;
    }

    public void setDescrizioneCausaleVersamento ( String descrizioneCausaleVersamento ) {
        this.descrizioneCausaleVersamento = descrizioneCausaleVersamento;
    }

    public String getEsitoPagamento () {
        return this.esitoPagamento;
    }

    public void setEsitoPagamento ( String esitoPagamento ) {
        this.esitoPagamento = esitoPagamento;
    }

    public String getIdentificativoUnicoRiscossione () {
        return this.identificativoUnicoRiscossione;
    }

    public void setIdentificativoUnicoRiscossione ( String identificativoUnicoRiscossione ) {
        this.identificativoUnicoRiscossione = identificativoUnicoRiscossione;
    }

    public String getIdentificativoUnicoVersamento () {
        return this.identificativoUnicoVersamento;
    }

    public void setIdentificativoUnicoVersamento ( String identificativoUnicoVersamento ) {
        this.identificativoUnicoVersamento = identificativoUnicoVersamento;
    }

    public BigDecimal getImportoSingoloVersamento () {
        return this.importoSingoloVersamento;
    }

    public void setImportoSingoloVersamento ( BigDecimal importoSingoloVersamento ) {
        this.importoSingoloVersamento = importoSingoloVersamento;
    }

    public Integer getIndiceSingoloVersamento () {
        return this.indiceSingoloVersamento;
    }

    public void setIndiceSingoloVersamento ( Integer indiceSingoloVersamento ) {
        this.indiceSingoloVersamento = indiceSingoloVersamento;
    }

    public String getStatoInvioFruitore () {
        return this.statoInvioFruitore;
    }

    public void setStatoInvioFruitore ( String statoInvioFruitore ) {
        this.statoInvioFruitore = statoInvioFruitore;
    }

    public String getTipoAnagraficaPagatore () {
        return this.tipoAnagraficaPagatore;
    }

    public void setTipoAnagraficaPagatore ( String tipoAnagraficaPagatore ) {
        this.tipoAnagraficaPagatore = tipoAnagraficaPagatore;
    }

    public String getTransactionid () {
        return this.transactionid;
    }

    public void setTransactionid ( String transactionid ) {
        this.transactionid = transactionid;
    }

    public FlussoSintesiPagopaDTO getSimTFlussoSintesiPagopa () {
        return this.simTFlussoSintesiPagopa;
    }

    public void setSimTFlussoSintesiPagopa ( FlussoSintesiPagopaDTO simTFlussoSintesiPagopa ) {
        this.simTFlussoSintesiPagopa = simTFlussoSintesiPagopa;
    }

}
