/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodricweb.model.flusso;

import java.io.Serializable;
import java.util.Date;


/**
 *
 */

public class EsportazioneFlussoVO implements Serializable {

    private static final long serialVersionUID = 1467112700415849371L;

    protected Integer contatoreTentativi;

    protected Date dataInserimento;

    protected Date dataOraFlusso;

    protected String ibanRiversamentoFlusso;

    protected String identificativoFlusso;

    protected String identificativoIstitutoRicevente;
    protected String identificativoIstitutoRiceventeDescrizione;

    protected String identificativoPsp;
    protected String identificativoPspDescrizione;

    protected Double importoTotalePagamenti;

    protected Integer numeroTotalePagamenti;

    protected Long idStato;

    protected String codiceStato;

    protected String descrizioneStato;

    protected Boolean permettiRielaborazione;

    protected String codiceVersamento;

    protected String datiSpecificiDiRiscossione;

    protected Double importoQuotaAggregazione;

    protected Integer numeroVersamentoQuotaAggregazione;

    protected Integer provvisorioAnno;

    protected Integer provvisorioNumero;


    protected String anagraficaPagatore;

    protected String causale;

    protected String codiceFiscalePagatore;

    protected Date dataPagamento;

    protected String descrizioneVersamento;

    protected String esitoPagamento;

    protected String idTransazione;

    protected String identificativoUnicoRiscossione;

    protected String identificativoUnicoVersamento;

    protected Double importoSingoloVersamento;

    protected Integer indiceSingoloVersamento;

    protected String statoInvioFruitore;

    protected Integer annoAccertamento;

    protected Integer numeroAccertamento;


    public Integer getAnnoAccertamento() {
		return annoAccertamento;
	}

	public void setAnnoAccertamento(Integer annoAccertamento) {
		this.annoAccertamento = annoAccertamento;
	}

	public Integer getNumeroAccertamento() {
		return numeroAccertamento;
	}

	public void setNumeroAccertamento(Integer numeroAccertamento) {
		this.numeroAccertamento = numeroAccertamento;
	}

	public EsportazioneFlussoVO () {
    }

    public EsportazioneFlussoVO ( FlussoOrigineVO flussoOrigineVO, FlussoSintesiVO flussoSintesiVO, FlussoDettaglioVO flussoDettaglioVO ) {

        if (null != flussoOrigineVO) {
            this.contatoreTentativi = flussoOrigineVO.getContatoreTentativi ();
            this.dataInserimento = flussoOrigineVO.getDataInserimento ();
            this.dataOraFlusso = flussoOrigineVO.getDataOraFlusso ();
            this.ibanRiversamentoFlusso = flussoOrigineVO.getIbanRiversamentoFlusso ();
            this.identificativoFlusso = flussoOrigineVO.getIdentificativoFlusso ();
            this.identificativoIstitutoRicevente = flussoOrigineVO.getIdentificativoIstitutoRicevente ();
            this.identificativoIstitutoRiceventeDescrizione = flussoOrigineVO.getIdentificativoIstitutoRiceventeDescrizione ();
            this.identificativoPsp = flussoOrigineVO.getIdentificativoPsp ();
            this.identificativoPspDescrizione = flussoOrigineVO.getIdentificativoPspDescrizione ();
            this.importoTotalePagamenti = flussoOrigineVO.getImportoTotalePagamenti ();
            this.numeroTotalePagamenti = flussoOrigineVO.getNumeroTotalePagamenti ();
            this.idStato = flussoOrigineVO.getIdStato ();
            this.codiceStato = flussoOrigineVO.getCodiceStato ();
            this.descrizioneStato = flussoOrigineVO.getDescrizioneStato ();
            this.permettiRielaborazione = flussoOrigineVO.getPermettiRielaborazione ();
        }

        if ( null != flussoSintesiVO ) {
            this.codiceVersamento = flussoSintesiVO.getCodiceVersamento ();
            this.datiSpecificiDiRiscossione = flussoSintesiVO.getDatiSpecificiDiRiscossione ();
            this.importoQuotaAggregazione = flussoSintesiVO.getImportoQuotaAggregazione ();
            this.numeroVersamentoQuotaAggregazione = flussoSintesiVO.getNumeroVersamentoQuotaAggregazione ();
            this.provvisorioAnno = flussoSintesiVO.getProvvisorioAnno ();
            this.provvisorioNumero = flussoSintesiVO.getProvvisorioNumero ();
            this.descrizioneVersamento = flussoSintesiVO.getDescrizioneVersamento ();
            this.annoAccertamento = flussoSintesiVO.getAnnoAccertamento();
            this.numeroAccertamento = flussoSintesiVO.getNumeroAccertamento();
        }

        if ( null != flussoDettaglioVO ) {
            this.anagraficaPagatore = flussoDettaglioVO.getAnagraficaPagatore ();
            this.causale = flussoDettaglioVO.getCausale ();
            this.codiceFiscalePagatore = flussoDettaglioVO.getCodiceFiscalePagatore ();
            this.dataPagamento = flussoDettaglioVO.getDataPagamento ();
            this.esitoPagamento = flussoDettaglioVO.getEsitoPagamento ();
            this.idTransazione = flussoDettaglioVO.getIdTransazione ();
            this.identificativoUnicoRiscossione = flussoDettaglioVO.getIdentificativoUnicoRiscossione ();
            this.identificativoUnicoVersamento = flussoDettaglioVO.getIdentificativoUnicoVersamento ();
            this.importoSingoloVersamento = flussoDettaglioVO.getImportoSingoloVersamento ();
            this.indiceSingoloVersamento = flussoDettaglioVO.getIndiceSingoloVersamento ();
            this.statoInvioFruitore = flussoDettaglioVO.getStatoInvioFruitore ();
        }

    }

    public Integer getContatoreTentativi () {
        return contatoreTentativi;
    }

    public void setContatoreTentativi ( Integer contatoreTentativi ) {
        this.contatoreTentativi = contatoreTentativi;
    }

    public Date getDataInserimento () {
        return dataInserimento;
    }

    public void setDataInserimento ( Date dataInserimento ) {
        this.dataInserimento = dataInserimento;
    }

    public Date getDataOraFlusso () {
        return dataOraFlusso;
    }

    public void setDataOraFlusso ( Date dataOraFlusso ) {
        this.dataOraFlusso = dataOraFlusso;
    }

    public String getIbanRiversamentoFlusso () {
        return ibanRiversamentoFlusso;
    }

    public void setIbanRiversamentoFlusso ( String ibanRiversamentoFlusso ) {
        this.ibanRiversamentoFlusso = ibanRiversamentoFlusso;
    }

    public String getIdentificativoFlusso () {
        return identificativoFlusso;
    }

    public void setIdentificativoFlusso ( String identificativoFlusso ) {
        this.identificativoFlusso = identificativoFlusso;
    }

    public String getIdentificativoIstitutoRicevente () {
        return identificativoIstitutoRicevente;
    }

    public void setIdentificativoIstitutoRicevente ( String identificativoIstitutoRicevente ) {
        this.identificativoIstitutoRicevente = identificativoIstitutoRicevente;
    }

    public String getIdentificativoPsp () {
        return identificativoPsp;
    }

    public void setIdentificativoPsp ( String identificativoPsp ) {
        this.identificativoPsp = identificativoPsp;
    }

    public Double getImportoTotalePagamenti () {
        return importoTotalePagamenti;
    }

    public void setImportoTotalePagamenti ( Double importoTotalePagamenti ) {
        this.importoTotalePagamenti = importoTotalePagamenti;
    }

    public Integer getNumeroTotalePagamenti () {
        return numeroTotalePagamenti;
    }

    public void setNumeroTotalePagamenti ( Integer numeroTotalePagamenti ) {
        this.numeroTotalePagamenti = numeroTotalePagamenti;
    }

    public Long getIdStato () {
        return idStato;
    }

    public void setIdStato ( Long idStato ) {
        this.idStato = idStato;
    }

    public String getCodiceStato () {
        return codiceStato;
    }

    public void setCodiceStato ( String codiceStato ) {
        this.codiceStato = codiceStato;
    }

    public String getDescrizioneStato () {
        return descrizioneStato;
    }

    public void setDescrizioneStato ( String descrizioneStato ) {
        this.descrizioneStato = descrizioneStato;
    }

    public String getCodiceVersamento () {
        return codiceVersamento;
    }

    public void setCodiceVersamento ( String codiceVersamento ) {
        this.codiceVersamento = codiceVersamento;
    }

    public String getDatiSpecificiDiRiscossione () {
        return datiSpecificiDiRiscossione;
    }

    public void setDatiSpecificiDiRiscossione ( String datiSpecificiDiRiscossione ) {
        this.datiSpecificiDiRiscossione = datiSpecificiDiRiscossione;
    }

    public Double getImportoQuotaAggregazione () {
        return importoQuotaAggregazione;
    }

    public void setImportoQuotaAggregazione ( Double importoQuotaAggregazione ) {
        this.importoQuotaAggregazione = importoQuotaAggregazione;
    }

    public Integer getNumeroVersamentoQuotaAggregazione () {
        return numeroVersamentoQuotaAggregazione;
    }

    public void setNumeroVersamentoQuotaAggregazione ( Integer numeroVersamentoQuotaAggregazione ) {
        this.numeroVersamentoQuotaAggregazione = numeroVersamentoQuotaAggregazione;
    }

    public Integer getProvvisorioAnno () {
        return provvisorioAnno;
    }

    public void setProvvisorioAnno ( Integer provvisorioAnno ) {
        this.provvisorioAnno = provvisorioAnno;
    }

    public Integer getProvvisorioNumero () {
        return provvisorioNumero;
    }

    public void setProvvisorioNumero ( Integer provvisorioNumero ) {
        this.provvisorioNumero = provvisorioNumero;
    }

    public String getAnagraficaPagatore () {
        return anagraficaPagatore;
    }

    public void setAnagraficaPagatore ( String anagraficaPagatore ) {
        this.anagraficaPagatore = anagraficaPagatore;
    }

    public String getCausale () {
        return causale;
    }

    public void setCausale ( String causale ) {
        this.causale = causale;
    }

    public String getCodiceFiscalePagatore () {
        return codiceFiscalePagatore;
    }

    public void setCodiceFiscalePagatore ( String codiceFiscalePagatore ) {
        this.codiceFiscalePagatore = codiceFiscalePagatore;
    }

    public Date getDataPagamento () {
        return dataPagamento;
    }

    public void setDataPagamento ( Date dataPagamento ) {
        this.dataPagamento = dataPagamento;
    }

    public String getDescrizioneVersamento () {
        return descrizioneVersamento;
    }

    public void setDescrizioneVersamento ( String descrizioneVersamento ) {
        this.descrizioneVersamento = descrizioneVersamento;
    }

    public String getEsitoPagamento () {
        return esitoPagamento;
    }

    public void setEsitoPagamento ( String esitoPagamento ) {
        this.esitoPagamento = esitoPagamento;
    }

    public String getIdTransazione () {
        return idTransazione;
    }

    public void setIdTransazione ( String idTransazione ) {
        this.idTransazione = idTransazione;
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

    public Double getImportoSingoloVersamento () {
        return importoSingoloVersamento;
    }

    public void setImportoSingoloVersamento ( Double importoSingoloVersamento ) {
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


    public String getIdentificativoIstitutoRiceventeDescrizione () {
        return identificativoIstitutoRiceventeDescrizione;
    }


    public void setIdentificativoIstitutoRiceventeDescrizione ( String identificativoIstitutoRiceventeDescrizione ) {
        this.identificativoIstitutoRiceventeDescrizione = identificativoIstitutoRiceventeDescrizione;
    }


    public String getIdentificativoPspDescrizione () {
        return identificativoPspDescrizione;
    }


    public void setIdentificativoPspDescrizione ( String identificativoPspDescrizione ) {
        this.identificativoPspDescrizione = identificativoPspDescrizione;
    }

    public Boolean getPermettiRielaborazione () {
        return permettiRielaborazione;
    }

    public void setPermettiRielaborazione ( Boolean permettiRielaborazione ) {
        this.permettiRielaborazione = permettiRielaborazione;
    }

}
