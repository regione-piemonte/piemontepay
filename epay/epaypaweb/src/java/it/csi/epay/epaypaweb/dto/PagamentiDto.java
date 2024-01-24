/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/** dto facade <-> business <-> persistence */
public class PagamentiDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String iuv;

    private String causale;

    private String descrizioneCausaleVersamento;

    private BigDecimal importoDovuto;

    private BigDecimal importoPagato;

    private String codiceCodiceVersamento;

    private String descrizioneCodiceVersamento;

    private Date dataScadenza;

    private Date dataPagamento;

    private Integer idStatoPagamento;

    private String descrizioneStatoPagamento;

    private String identificativoUnivocoDebitore;

    public String getIdentificativoUnivocoDebitore () {
        return identificativoUnivocoDebitore;
    }

    public void setIdentificativoUnivocoDebitore ( String identificativoUnivocoDebitore ) {
        this.identificativoUnivocoDebitore = identificativoUnivocoDebitore;
    }

    public String getIuv () {
        return iuv;
    }

    public void setIuv ( String iuv ) {
        this.iuv = iuv;
    }

    public String getDescrizioneCausaleVersamento () {
        return descrizioneCausaleVersamento;
    }

    public void setDescrizioneCausaleVersamento ( String descrizioneCausaleVersamento ) {
        this.descrizioneCausaleVersamento = descrizioneCausaleVersamento;
    }

    public BigDecimal getImportoDovuto () {
        return importoDovuto;
    }

    public void setImportoDovuto ( BigDecimal importoDovuto ) {
        this.importoDovuto = importoDovuto;
    }

    public BigDecimal getImportoPagato () {
        return importoPagato;
    }

    public void setImportoPagato ( BigDecimal importoPagato ) {
        this.importoPagato = importoPagato;
    }

    public String getCodiceCodiceVersamento () {
        return codiceCodiceVersamento;
    }

    public void setCodiceCodiceVersamento ( String codiceCodiceVersamento ) {
        this.codiceCodiceVersamento = codiceCodiceVersamento;
    }

    public String getDescrizioneCodiceVersamento () {
        return descrizioneCodiceVersamento;
    }

    public void setDescrizioneCodiceVersamento ( String descrizioneCodiceVersamento ) {
        this.descrizioneCodiceVersamento = descrizioneCodiceVersamento;
    }

    public Date getDataScadenza () {
        return dataScadenza;
    }

    public void setDataScadenza ( Date dataScadenza ) {
        this.dataScadenza = dataScadenza;
    }

    public Date getDataPagamento () {
        return dataPagamento;
    }

    public void setDataPagamento ( Date dataPagamento ) {
        this.dataPagamento = dataPagamento;
    }

    public Integer getIdStatoPagamento () {
        return idStatoPagamento;
    }

    public void setIdStatoPagamento ( Integer idStatoPagamento ) {
        this.idStatoPagamento = idStatoPagamento;
    }

    public String getDescrizioneStatoPagamento () {
        return descrizioneStatoPagamento;
    }

    public void setDescrizioneStatoPagamento ( String descrizioneStatoPagamento ) {
        this.descrizioneStatoPagamento = descrizioneStatoPagamento;
    }

    public String getCausale () {
        return causale;
    }

    public void setCausale ( String causale ) {
        this.causale = causale;
    }

    @Override
    public String toString () {
        return "PagamentiDto [" + ( iuv != null ? "iuv=" + iuv + ", " : "" )
                        + ( causale != null ? "causale=" + causale + ", " : "" )
                        + ( descrizioneCausaleVersamento != null ? "descrizioneCausaleVersamento=" + descrizioneCausaleVersamento + ", " : "" )
                        + ( importoDovuto != null ? "importoDovuto=" + importoDovuto + ", " : "" )
                        + ( importoPagato != null ? "importoPagato=" + importoPagato + ", " : "" )
                        + ( codiceCodiceVersamento != null ? "codiceCodiceVersamento=" + codiceCodiceVersamento + ", " : "" )
                        + ( descrizioneCodiceVersamento != null ? "descrizioneCodiceVersamento=" + descrizioneCodiceVersamento + ", " : "" )
                        + ( dataScadenza != null ? "dataScadenza=" + dataScadenza + ", " : "" ) + ( dataPagamento != null ? "dataPagamento=" + dataPagamento + ", " : "" )
                        + ( idStatoPagamento != null ? "idStatoPagamento=" + idStatoPagamento + ", " : "" )
                        + ( descrizioneStatoPagamento != null ? "descrizioneStatoPagamento=" + descrizioneStatoPagamento + ", " : "" )
                        + ( identificativoUnivocoDebitore != null ? "identificativoUnivocoDebitore=" + identificativoUnivocoDebitore : "" ) + "]";
    }

}
