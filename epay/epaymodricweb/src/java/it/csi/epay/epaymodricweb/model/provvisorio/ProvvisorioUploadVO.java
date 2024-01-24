/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodricweb.model.provvisorio;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 *
 */

public class ProvvisorioUploadVO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String identificativoFlusso;//

    private String descrizione;//

    private String stato;//sempre validato

    private Integer annoProvvisorio;//

    private Date dataMovimento;//

    private Integer annoEsercizio;//

    //private Date dataFine;

    //private String idEnte;//prendi da caller

    private BigDecimal importoProvvisorio;//

    private Integer numeroProvvisorio;//

    /**
     * @return the identificativoFlusso
     */
    public String getIdentificativoFlusso () {
        return identificativoFlusso;
    }

    /**
     * @param identificativoFlusso the identificativoFlusso to set
     */
    public void setIdentificativoFlusso ( String identificativoFlusso ) {
        this.identificativoFlusso = identificativoFlusso;
    }

    /**
     * @return the descrizione
     */
    public String getDescrizione () {
        return descrizione;
    }

    /**
     * @param descrizione the descrizione to set
     */
    public void setDescrizione ( String descrizione ) {
        this.descrizione = descrizione;
    }

    /**
     * @return the stato
     */
    public String getStato () {
        return stato;
    }

    /**
     * @param stato the stato to set
     */
    public void setStato ( String stato ) {
        this.stato = stato;
    }

    /**
     * @return the annoProvvisorio
     */
    public Integer getAnnoProvvisorio () {
        return annoProvvisorio;
    }

    /**
     * @param annoProvvisorio the annoProvvisorio to set
     */
    public void setAnnoProvvisorio ( Integer annoProvvisorio ) {
        this.annoProvvisorio = annoProvvisorio;
    }

    /**
     * @return the dataMovimento
     */
    public Date getDataMovimento () {
        return dataMovimento;
    }

    /**
     * @param dataMovimento the dataMovimento to set
     */
    public void setDataMovimento ( Date dataMovimento ) {
        this.dataMovimento = dataMovimento;
    }

    /**
     * @return the annoEsercizio
     */
    public Integer getAnnoEsercizio () {
        return annoEsercizio;
    }

    /**
     * @param annoEsercizio the annoEsercizio to set
     */
    public void setAnnoEsercizio ( Integer annoEsercizio ) {
        this.annoEsercizio = annoEsercizio;
    }

    /**
     * @return the importoProvvisorio
     */
    public BigDecimal getImportoProvvisorio () {
        return importoProvvisorio;
    }

    /**
     * @param importoProvvisorio the importoProvvisorio to set
     */
    public void setImportoProvvisorio ( BigDecimal importoProvvisorio ) {
        this.importoProvvisorio = importoProvvisorio;
    }

    /**
     * @return the numeroProvvisorio
     */
    public Integer getNumeroProvvisorio () {
        return numeroProvvisorio;
    }

    /**
     * @param numeroProvvisorio the numeroProvvisorio to set
     */
    public void setNumeroProvvisorio ( Integer numeroProvvisorio ) {
        this.numeroProvvisorio = numeroProvvisorio;
    }

    //private String tipoMovimento; //sospeso di entrata


}
