/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysim.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 */

public class FlussoOrigineErrorePagopaOutputDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Long id;

    private String identificativoFlusso;

    private String identificativoPsp;

    private Integer numeroTotalePagamenti;

    private BigDecimal importoTotalePagamenti;

    private String ibanRiversamentoFlusso;

    private Date dataoraFlusso;

    private Date dataInserimento;

    private Integer contatoreTentativi;

    private Long idElaborazione;

    private Long idStatoFlussolusso;

    private BigDecimal importoTotalePagamentiIntermediati;

    private Integer numeroTotalePagamentiIntermediati;

    private Date dataRegolamento;

    private String codiceVersamento;

    private String codiceErrore;

    private String descrizioneErrore;

    private String idMessaggio;

    private String identificativoUnivocoRegolamento;

    private String utenteInserimento;

    private String utenteModifica;

    private Date dataModifica;

    private String cfEnteRicevente;

    private EsitoDTO esito;


    public FlussoOrigineErrorePagopaOutputDTO () {

    }

    public String getCfEnteRicevente () {
        return cfEnteRicevente;
    }

    public String getCodiceErrore () {
        return codiceErrore;
    }

    public String getCodiceVersamento () {
        return codiceVersamento;
    }

    public Integer getContatoreTentativi () {
        return contatoreTentativi;
    }

    public Date getDataInserimento () {
        return dataInserimento;
    }

    public Date getDataModifica () {
        return dataModifica;
    }

    public Date getDataoraFlusso () {
        return dataoraFlusso;
    }

    public Date getDataRegolamento () {
        return dataRegolamento;
    }

    public String getDescrizioneErrore () {
        return descrizioneErrore;
    }

    public EsitoDTO getEsito () {
        return esito;
    }

    public String getIbanRiversamentoFlusso () {
        return ibanRiversamentoFlusso;
    }

    public Long getId () {
        return id;
    }

    public Long getIdElaborazione () {
        return idElaborazione;
    }

    public String getIdentificativoFlusso () {
        return identificativoFlusso;
    }

    public String getIdentificativoPsp () {
        return identificativoPsp;
    }

    public String getIdentificativoUnivocoRegolamento () {
        return identificativoUnivocoRegolamento;
    }

    public String getIdMessaggio () {
        return idMessaggio;
    }

    public Long getIdStatoFlussolusso () {
        return idStatoFlussolusso;
    }

    public BigDecimal getImportoTotalePagamenti () {
        return importoTotalePagamenti;
    }

    public BigDecimal getImportoTotalePagamentiIntermediati () {
        return importoTotalePagamentiIntermediati;
    }

    public Integer getNumeroTotalePagamenti () {
        return numeroTotalePagamenti;
    }

    public Integer getNumeroTotalePagamentiIntermediati () {
        return numeroTotalePagamentiIntermediati;
    }

    public String getUtenteInserimento () {
        return utenteInserimento;
    }

    public String getUtenteModifica () {
        return utenteModifica;
    }

    public void setCfEnteRicevente ( String cfEnteRicevente ) {
        this.cfEnteRicevente = cfEnteRicevente;
    }

    public void setCodiceErrore ( String codiceErrore ) {
        this.codiceErrore = codiceErrore;
    }

    public void setCodiceVersamento ( String codiceVersamento ) {
        this.codiceVersamento = codiceVersamento;
    }

    public void setContatoreTentativi ( Integer contatoreTentativi ) {
        this.contatoreTentativi = contatoreTentativi;
    }

    public void setDataInserimento ( Date dataInserimento ) {
        this.dataInserimento = dataInserimento;
    }

    public void setDataModifica ( Date dataModifica ) {
        this.dataModifica = dataModifica;
    }

    public void setDataoraFlusso ( Date dataoraFlusso ) {
        this.dataoraFlusso = dataoraFlusso;
    }

    public void setDataRegolamento ( Date dataRegolamento ) {
        this.dataRegolamento = dataRegolamento;
    }

    public void setDescrizioneErrore ( String descrizioneErrore ) {
        this.descrizioneErrore = descrizioneErrore;
    }

    public void setEsito ( EsitoDTO esito ) {
        this.esito = esito;
    }

    public void setIbanRiversamentoFlusso ( String ibanRiversamentoFlusso ) {
        this.ibanRiversamentoFlusso = ibanRiversamentoFlusso;
    }

    public void setId ( Long id ) {
        this.id = id;
    }

    public void setIdElaborazione ( Long idElaborazione ) {
        this.idElaborazione = idElaborazione;
    }

    public void setIdentificativoFlusso ( String identificativoFlusso ) {
        this.identificativoFlusso = identificativoFlusso;
    }

    public void setIdentificativoPsp ( String identificativoPsp ) {
        this.identificativoPsp = identificativoPsp;
    }

    public void setIdentificativoUnivocoRegolamento ( String identificativoUnivocoRegolamento ) {
        this.identificativoUnivocoRegolamento = identificativoUnivocoRegolamento;
    }

    public void setIdMessaggio ( String idMessaggio ) {
        this.idMessaggio = idMessaggio;
    }

    public void setIdStatoFlussolusso ( Long idStatoFlussolusso ) {
        this.idStatoFlussolusso = idStatoFlussolusso;
    }

    public void setImportoTotalePagamenti ( BigDecimal importoTotalePagamenti ) {
        this.importoTotalePagamenti = importoTotalePagamenti;
    }

    public void setImportoTotalePagamentiIntermediati ( BigDecimal importoTotalePagamentiIntermediati ) {
        this.importoTotalePagamentiIntermediati = importoTotalePagamentiIntermediati;
    }

    public void setNumeroTotalePagamenti ( Integer numeroTotalePagamenti ) {
        this.numeroTotalePagamenti = numeroTotalePagamenti;
    }

    public void setNumeroTotalePagamentiIntermediati ( Integer numeroTotalePagamentiIntermediati ) {
        this.numeroTotalePagamentiIntermediati = numeroTotalePagamentiIntermediati;
    }

    public void setUtenteInserimento ( String utenteInserimento ) {
        this.utenteInserimento = utenteInserimento;
    }

    public void setUtenteModifica ( String utenteModifica ) {
        this.utenteModifica = utenteModifica;
    }

}
