/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaysimweb.model.flussi;

import java.util.Date;


public class FlussoDettaglioVO implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    protected Long id;

    protected String anagraficaPagatore;

    protected String causale;

    protected Date dataPagamento;

    protected String descrizioneCausaleVersamento;

    protected String esitoPagamento;

    protected String identificativoUnicoRiscossione;

    protected String identificativoUnicoVersamento;

    protected Double importoSingoloVersamento;

    protected Integer indiceSingoloVersamento;

    protected String transactionid;

    public FlussoDettaglioVO () {
    }

    public FlussoDettaglioVO ( Long id, String anagraficaPagatore, String causale, Date dataPagamento, String descrizioneCausaleVersamento,
        String esitoPagamento, String identificativoUnicoRiscossione, String identificativoUnicoVersamento, Double importoSingoloVersamento,
        Integer indiceSingoloVersamento, String transactionid ) {
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
    public Date getDataPagamento () {
        return dataPagamento;
    }

    /**
     * @param dataPagamento the dataPagamento to set
     */
    public void setDataPagamento ( Date dataPagamento ) {
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
    public Double getImportoSingoloVersamento () {
        return importoSingoloVersamento;
    }

    /**
     * @param importoSingoloVersamento the importoSingoloVersamento to set
     */
    public void setImportoSingoloVersamento ( Double importoSingoloVersamento ) {
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

}
