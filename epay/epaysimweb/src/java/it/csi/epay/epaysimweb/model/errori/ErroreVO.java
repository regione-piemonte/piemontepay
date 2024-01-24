/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysimweb.model.errori;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.annotation.Generated;


public class ErroreVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String cfEnteRicevente;

    private String codiceErrore;

    private String codiceVersamento;

    private Date dataInserimento;

    private Date dataModifica;

    private Date dataRegolamento;

    private Date dataoraFlusso;

    private String descrizioneErrore;

    private String ibanRiversamentoFlusso;

    private Long idElaborazione;

    private String idMessaggio;

    private Long idStatoFlussolusso;

    private String identificativoFlusso;

    private String identificativoPsp;

    private BigDecimal importoTotalePagamenti;

    private BigDecimal importoTotalePagamentiIntermediati;

    private Integer numeroTotalePagamenti;

    private Integer numeroTotalePagamentiIntermediati;

    private String identificativoUnivocoRegolamento;
    
    public ErroreVO () {
    }

    public Long getId () {
        return id;
    }

    public void setId ( Long id ) {
        this.id = id;
    }

    public String getCfEnteRicevente () {
        return cfEnteRicevente;
    }

    public void setCfEnteRicevente ( String cfEnteRicevente ) {
        this.cfEnteRicevente = cfEnteRicevente;
    }

    public String getCodiceErrore () {
        return codiceErrore;
    }

    public void setCodiceErrore ( String codiceErrore ) {
        this.codiceErrore = codiceErrore;
    }

    public String getCodiceVersamento () {
        return codiceVersamento;
    }

    public void setCodiceVersamento ( String codiceVersamento ) {
        this.codiceVersamento = codiceVersamento;
    }

    public Date getDataInserimento () {
        return dataInserimento;
    }

    public void setDataInserimento ( Date dataInserimento ) {
        this.dataInserimento = dataInserimento;
    }

    public Date getDataModifica () {
        return dataModifica;
    }

    public void setDataModifica ( Date dataModifica ) {
        this.dataModifica = dataModifica;
    }

    public Date getDataRegolamento () {
        return dataRegolamento;
    }

    public void setDataRegolamento ( Date dataRegolamento ) {
        this.dataRegolamento = dataRegolamento;
    }

    public Date getDataoraFlusso () {
        return dataoraFlusso;
    }

    public void setDataoraFlusso ( Date dataoraFlusso ) {
        this.dataoraFlusso = dataoraFlusso;
    }

    public String getDescrizioneErrore () {
        return descrizioneErrore;
    }

    public void setDescrizioneErrore ( String descrizioneErrore ) {
        this.descrizioneErrore = descrizioneErrore;
    }

    public String getIbanRiversamentoFlusso () {
        return ibanRiversamentoFlusso;
    }

    public void setIbanRiversamentoFlusso ( String ibanRiversamentoFlusso ) {
        this.ibanRiversamentoFlusso = ibanRiversamentoFlusso;
    }

    public Long getIdElaborazione () {
        return idElaborazione;
    }

    public void setIdElaborazione ( Long idElaborazione ) {
        this.idElaborazione = idElaborazione;
    }

    public String getIdMessaggio () {
        return idMessaggio;
    }

    public void setIdMessaggio ( String idMessaggio ) {
        this.idMessaggio = idMessaggio;
    }

    public Long getIdStatoFlussolusso () {
        return idStatoFlussolusso;
    }

    public void setIdStatoFlussolusso ( Long idStatoFlussolusso ) {
        this.idStatoFlussolusso = idStatoFlussolusso;
    }

    public String getIdentificativoFlusso () {
        return identificativoFlusso;
    }

    public void setIdentificativoFlusso ( String identificativoFlusso ) {
        this.identificativoFlusso = identificativoFlusso;
    }

    public String getIdentificativoPsp () {
        return identificativoPsp;
    }

    public void setIdentificativoPsp ( String identificativoPsp ) {
        this.identificativoPsp = identificativoPsp;
    }

    public BigDecimal getImportoTotalePagamenti () {
        return importoTotalePagamenti;
    }

    public void setImportoTotalePagamenti ( BigDecimal importoTotalePagamenti ) {
        this.importoTotalePagamenti = importoTotalePagamenti;
    }

    public BigDecimal getImportoTotalePagamentiIntermediati () {
        return importoTotalePagamentiIntermediati;
    }

    public void setImportoTotalePagamentiIntermediati ( BigDecimal importoTotalePagamentiIntermediati ) {
        this.importoTotalePagamentiIntermediati = importoTotalePagamentiIntermediati;
    }

    public Integer getNumeroTotalePagamenti () {
        return numeroTotalePagamenti;
    }

    public void setNumeroTotalePagamenti ( Integer numeroTotalePagamenti ) {
        this.numeroTotalePagamenti = numeroTotalePagamenti;
    }

    public Integer getNumeroTotalePagamentiIntermediati () {
        return numeroTotalePagamentiIntermediati;
    }

    public void setNumeroTotalePagamentiIntermediati ( Integer numeroTotalePagamentiIntermediati ) {
        this.numeroTotalePagamentiIntermediati = numeroTotalePagamentiIntermediati;
    }

    
    public String getIdentificativoUnivocoRegolamento () {
        return identificativoUnivocoRegolamento;
    }

    
    public void setIdentificativoUnivocoRegolamento ( String identificativoUnivocoRegolamento ) {
        this.identificativoUnivocoRegolamento = identificativoUnivocoRegolamento;
    }

}
