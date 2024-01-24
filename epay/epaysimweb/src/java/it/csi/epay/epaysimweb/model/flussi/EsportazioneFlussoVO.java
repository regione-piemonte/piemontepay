/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysimweb.model.flussi;

import java.io.Serializable;
import java.util.Date;


/**
 *
 */

public class EsportazioneFlussoVO implements Serializable {

    private static final long serialVersionUID = 1467112700415849371L;

    //FLUSSO ORIGINE
    protected String cfEnteCreditore;

    protected Date dataInserimento;

    protected Date dataModifica;

    protected Date dataOraMessaggio;

    protected Date dataRegolamento;

    protected String denominazioneEnte;

    protected String denominazionePsp;

    protected String idMessaggio;

    protected String identificativoFlusso;

    protected String identificativoPsp;

    protected String identificativoUnivocoRegolamento;

    protected Double importoTotalePagamenti;

    protected Double importoTotalePagamentiIntermediati;

    protected Integer numeroTotalePagamenti;

    protected Integer numeroTotalePagamentiIntermediati;

    protected Integer provvisorioAnno;

    protected Integer provvisorioNumero;

    protected String statoElaborazioneFlusso;

    protected String utenteInserimento;

    protected String utenteModifica;

    //FLUSSO SINTESI
    protected Integer accertamentoAnno;

    protected Integer accertamentoNumero;

    protected Integer articolo;

    protected Integer capitolo;

    protected String codiceVersamento;

    protected String datiSpecificiDiRiscossione;

    protected String descrizioneCodiceVersamento;

    protected String descrizioneDatiSpecifici;

    protected Double importoQuotaAggregazione;

    protected String macrotipo;

    protected Integer numeroPagamentiAggregazione;

    protected String pdc;

    protected Integer progressivoFlussoSintetico;

    protected String tematica;

    //FLUSSO DETTAGLIO
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

    public EsportazioneFlussoVO () {
    }

    public EsportazioneFlussoVO ( FlussoOrigineVO flussoOrigineVO, FlussoSintesiVO flussoSintesiVO, FlussoDettaglioVO flussoDettaglioVO ) {

        if ( null != flussoOrigineVO ) {
            this.cfEnteCreditore = flussoOrigineVO.getCfEnteCreditore ();
            this.dataInserimento = flussoOrigineVO.getDataInserimento ();
            this.dataModifica = flussoOrigineVO.getDataModifica ();
            this.dataOraMessaggio = flussoOrigineVO.getDataOraMessaggio ();
            this.dataRegolamento = flussoOrigineVO.getDataRegolamento ();
            this.denominazioneEnte = flussoOrigineVO.getDenominazioneEnte ();
            this.denominazionePsp = flussoOrigineVO.getDenominazionePsp ();
            this.idMessaggio = flussoOrigineVO.getIdMessaggio ();
            this.identificativoFlusso = flussoOrigineVO.getIdentificativoFlusso ();
            this.identificativoPsp = flussoOrigineVO.getIdentificativoPsp ();
            this.identificativoUnivocoRegolamento = flussoOrigineVO.getIdentificativoUnivocoRegolamento ();
            this.importoTotalePagamenti = flussoOrigineVO.getImportoTotalePagamenti ();
            this.importoTotalePagamentiIntermediati = flussoOrigineVO.getImportoTotalePagamentiIntermediati ();
            this.numeroTotalePagamenti = flussoOrigineVO.getNumeroTotalePagamenti ();
            this.numeroTotalePagamentiIntermediati = flussoOrigineVO.getNumeroTotalePagamentiIntermediati ();
            this.provvisorioAnno = flussoOrigineVO.getProvvisorioAnno ();
            this.provvisorioNumero = flussoOrigineVO.getProvvisorioNumero ();
            this.statoElaborazioneFlusso = flussoOrigineVO.getStatoElaborazioneFlusso ();
            this.utenteInserimento = flussoOrigineVO.getUtenteInserimento ();
            this.utenteModifica = flussoOrigineVO.getUtenteModifica ();

        }

        if ( null != flussoSintesiVO ) {
            this.accertamentoAnno = flussoSintesiVO.getAccertamentoAnno ();
            this.accertamentoNumero = flussoSintesiVO.getAccertamentoNumero ();
            this.articolo = flussoSintesiVO.getArticolo ();
            this.capitolo = flussoSintesiVO.getCapitolo ();
            this.codiceVersamento = flussoSintesiVO.getCodiceVersamento ();
            this.datiSpecificiDiRiscossione = flussoSintesiVO.getDatiSpecificiDiRiscossione ();
            this.descrizioneCodiceVersamento = flussoSintesiVO.getDescrizioneCodiceVersamento ();
            this.descrizioneDatiSpecifici = flussoSintesiVO.getDescrizioneDatiSpecifici ();
            this.importoQuotaAggregazione = flussoSintesiVO.getImportoQuotaAggregazione ();
            this.macrotipo = flussoSintesiVO.getMacrotipo ();
            this.numeroPagamentiAggregazione = flussoSintesiVO.getNumeroPagamentiAggregazione ();
            this.pdc = flussoSintesiVO.getPdc ();
            this.progressivoFlussoSintetico = flussoSintesiVO.getProgressivoFlussoSintetico ();
            this.tematica = flussoSintesiVO.getTematica ();

        }

        if ( null != flussoDettaglioVO ) {
            this.anagraficaPagatore = flussoDettaglioVO.getAnagraficaPagatore ();
            this.causale = flussoDettaglioVO.getCausale ();
            this.dataPagamento = flussoDettaglioVO.getDataPagamento ();
            this.descrizioneCausaleVersamento = flussoDettaglioVO.getDescrizioneCausaleVersamento ();
            this.esitoPagamento = flussoDettaglioVO.getEsitoPagamento ();
            this.identificativoUnicoRiscossione = flussoDettaglioVO.getIdentificativoUnicoRiscossione ();
            this.identificativoUnicoVersamento = flussoDettaglioVO.getIdentificativoUnicoVersamento ();
            this.importoSingoloVersamento = flussoDettaglioVO.getImportoSingoloVersamento ();
            this.indiceSingoloVersamento = flussoDettaglioVO.getIndiceSingoloVersamento ();
            this.transactionid = flussoDettaglioVO.getTransactionid ();

        }

    }

    /**
     * @return the cfEnteCreditore
     */
    public String getCfEnteCreditore () {
        return cfEnteCreditore;
    }

    /**
     * @param cfEnteCreditore the cfEnteCreditore to set
     */
    public void setCfEnteCreditore ( String cfEnteCreditore ) {
        this.cfEnteCreditore = cfEnteCreditore;
    }

    /**
     * @return the dataInserimento
     */
    public Date getDataInserimento () {
        return dataInserimento;
    }

    /**
     * @param dataInserimento the dataInserimento to set
     */
    public void setDataInserimento ( Date dataInserimento ) {
        this.dataInserimento = dataInserimento;
    }

    /**
     * @return the dataModifica
     */
    public Date getDataModifica () {
        return dataModifica;
    }

    /**
     * @param dataModifica the dataModifica to set
     */
    public void setDataModifica ( Date dataModifica ) {
        this.dataModifica = dataModifica;
    }

    /**
     * @return the dataOraMessaggio
     */
    public Date getDataOraMessaggio () {
        return dataOraMessaggio;
    }

    /**
     * @param dataOraMessaggio the dataOraMessaggio to set
     */
    public void setDataOraMessaggio ( Date dataOraMessaggio ) {
        this.dataOraMessaggio = dataOraMessaggio;
    }

    /**
     * @return the dataRegolamento
     */
    public Date getDataRegolamento () {
        return dataRegolamento;
    }

    /**
     * @param dataRegolamento the dataRegolamento to set
     */
    public void setDataRegolamento ( Date dataRegolamento ) {
        this.dataRegolamento = dataRegolamento;
    }

    /**
     * @return the denominazioneEnte
     */
    public String getDenominazioneEnte () {
        return denominazioneEnte;
    }

    /**
     * @param denominazioneEnte the denominazioneEnte to set
     */
    public void setDenominazioneEnte ( String denominazioneEnte ) {
        this.denominazioneEnte = denominazioneEnte;
    }

    /**
     * @return the denominazionePsp
     */
    public String getDenominazionePsp () {
        return denominazionePsp;
    }

    /**
     * @param denominazionePsp the denominazionePsp to set
     */
    public void setDenominazionePsp ( String denominazionePsp ) {
        this.denominazionePsp = denominazionePsp;
    }

    /**
     * @return the idMessaggio
     */
    public String getIdMessaggio () {
        return idMessaggio;
    }

    /**
     * @param idMessaggio the idMessaggio to set
     */
    public void setIdMessaggio ( String idMessaggio ) {
        this.idMessaggio = idMessaggio;
    }

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
     * @return the identificativoPsp
     */
    public String getIdentificativoPsp () {
        return identificativoPsp;
    }

    /**
     * @param identificativoPsp the identificativoPsp to set
     */
    public void setIdentificativoPsp ( String identificativoPsp ) {
        this.identificativoPsp = identificativoPsp;
    }

    /**
     * @return the identificativoUnivocoRegolamento
     */
    public String getIdentificativoUnivocoRegolamento () {
        return identificativoUnivocoRegolamento;
    }

    /**
     * @param identificativoUnivocoRegolamento the identificativoUnivocoRegolamento to set
     */
    public void setIdentificativoUnivocoRegolamento ( String identificativoUnivocoRegolamento ) {
        this.identificativoUnivocoRegolamento = identificativoUnivocoRegolamento;
    }

    /**
     * @return the importoTotalePagamenti
     */
    public Double getImportoTotalePagamenti () {
        return importoTotalePagamenti;
    }

    /**
     * @param importoTotalePagamenti the importoTotalePagamenti to set
     */
    public void setImportoTotalePagamenti ( Double importoTotalePagamenti ) {
        this.importoTotalePagamenti = importoTotalePagamenti;
    }

    /**
     * @return the importoTotalePagamentiIntermediati
     */
    public Double getImportoTotalePagamentiIntermediati () {
        return importoTotalePagamentiIntermediati;
    }

    /**
     * @param importoTotalePagamentiIntermediati the importoTotalePagamentiIntermediati to set
     */
    public void setImportoTotalePagamentiIntermediati ( Double importoTotalePagamentiIntermediati ) {
        this.importoTotalePagamentiIntermediati = importoTotalePagamentiIntermediati;
    }

    /**
     * @return the numeroTotalePagamenti
     */
    public Integer getNumeroTotalePagamenti () {
        return numeroTotalePagamenti;
    }

    /**
     * @param numeroTotalePagamenti the numeroTotalePagamenti to set
     */
    public void setNumeroTotalePagamenti ( Integer numeroTotalePagamenti ) {
        this.numeroTotalePagamenti = numeroTotalePagamenti;
    }

    /**
     * @return the numeroTotalePagamentiIntermediati
     */
    public Integer getNumeroTotalePagamentiIntermediati () {
        return numeroTotalePagamentiIntermediati;
    }

    /**
     * @param numeroTotalePagamentiIntermediati the numeroTotalePagamentiIntermediati to set
     */
    public void setNumeroTotalePagamentiIntermediati ( Integer numeroTotalePagamentiIntermediati ) {
        this.numeroTotalePagamentiIntermediati = numeroTotalePagamentiIntermediati;
    }

    /**
     * @return the provvisorioAnno
     */
    public Integer getProvvisorioAnno () {
        return provvisorioAnno;
    }

    /**
     * @param provvisorioAnno the provvisorioAnno to set
     */
    public void setProvvisorioAnno ( Integer provvisorioAnno ) {
        this.provvisorioAnno = provvisorioAnno;
    }

    /**
     * @return the provvisorioNumero
     */
    public Integer getProvvisorioNumero () {
        return provvisorioNumero;
    }

    /**
     * @param provvisorioNumero the provvisorioNumero to set
     */
    public void setProvvisorioNumero ( Integer provvisorioNumero ) {
        this.provvisorioNumero = provvisorioNumero;
    }

    /**
     * @return the statoElaborazioneFlusso
     */
    public String getStatoElaborazioneFlusso () {
        return statoElaborazioneFlusso;
    }

    /**
     * @param statoElaborazioneFlusso the statoElaborazioneFlusso to set
     */
    public void setStatoElaborazioneFlusso ( String statoElaborazioneFlusso ) {
        this.statoElaborazioneFlusso = statoElaborazioneFlusso;
    }

    /**
     * @return the utenteInserimento
     */
    public String getUtenteInserimento () {
        return utenteInserimento;
    }

    /**
     * @param utenteInserimento the utenteInserimento to set
     */
    public void setUtenteInserimento ( String utenteInserimento ) {
        this.utenteInserimento = utenteInserimento;
    }

    /**
     * @return the utenteModifica
     */
    public String getUtenteModifica () {
        return utenteModifica;
    }

    /**
     * @param utenteModifica the utenteModifica to set
     */
    public void setUtenteModifica ( String utenteModifica ) {
        this.utenteModifica = utenteModifica;
    }

    /**
     * @return the accertamentoAnno
     */
    public Integer getAccertamentoAnno () {
        return accertamentoAnno;
    }

    /**
     * @param accertamentoAnno the accertamentoAnno to set
     */
    public void setAccertamentoAnno ( Integer accertamentoAnno ) {
        this.accertamentoAnno = accertamentoAnno;
    }

    /**
     * @return the accertamentoNumero
     */
    public Integer getAccertamentoNumero () {
        return accertamentoNumero;
    }

    /**
     * @param accertamentoNumero the accertamentoNumero to set
     */
    public void setAccertamentoNumero ( Integer accertamentoNumero ) {
        this.accertamentoNumero = accertamentoNumero;
    }

    /**
     * @return the articolo
     */
    public Integer getArticolo () {
        return articolo;
    }

    /**
     * @param articolo the articolo to set
     */
    public void setArticolo ( Integer articolo ) {
        this.articolo = articolo;
    }

    /**
     * @return the capitolo
     */
    public Integer getCapitolo () {
        return capitolo;
    }

    /**
     * @param capitolo the capitolo to set
     */
    public void setCapitolo ( Integer capitolo ) {
        this.capitolo = capitolo;
    }

    /**
     * @return the codiceVersamento
     */
    public String getCodiceVersamento () {
        return codiceVersamento;
    }

    /**
     * @param codiceVersamento the codiceVersamento to set
     */
    public void setCodiceVersamento ( String codiceVersamento ) {
        this.codiceVersamento = codiceVersamento;
    }

    /**
     * @return the datiSpecificiDiRiscossione
     */
    public String getDatiSpecificiDiRiscossione () {
        return datiSpecificiDiRiscossione;
    }

    /**
     * @param datiSpecificiDiRiscossione the datiSpecificiDiRiscossione to set
     */
    public void setDatiSpecificiDiRiscossione ( String datiSpecificiDiRiscossione ) {
        this.datiSpecificiDiRiscossione = datiSpecificiDiRiscossione;
    }

    /**
     * @return the descrizioneCodiceVersamento
     */
    public String getDescrizioneCodiceVersamento () {
        return descrizioneCodiceVersamento;
    }

    /**
     * @param descrizioneCodiceVersamento the descrizioneCodiceVersamento to set
     */
    public void setDescrizioneCodiceVersamento ( String descrizioneCodiceVersamento ) {
        this.descrizioneCodiceVersamento = descrizioneCodiceVersamento;
    }

    /**
     * @return the descrizioneDatiSpecifici
     */
    public String getDescrizioneDatiSpecifici () {
        return descrizioneDatiSpecifici;
    }

    /**
     * @param descrizioneDatiSpecifici the descrizioneDatiSpecifici to set
     */
    public void setDescrizioneDatiSpecifici ( String descrizioneDatiSpecifici ) {
        this.descrizioneDatiSpecifici = descrizioneDatiSpecifici;
    }

    /**
     * @return the importoQuotaAggregazione
     */
    public Double getImportoQuotaAggregazione () {
        return importoQuotaAggregazione;
    }

    /**
     * @param importoQuotaAggregazione the importoQuotaAggregazione to set
     */
    public void setImportoQuotaAggregazione ( Double importoQuotaAggregazione ) {
        this.importoQuotaAggregazione = importoQuotaAggregazione;
    }

    /**
     * @return the macrotipo
     */
    public String getMacrotipo () {
        return macrotipo;
    }

    /**
     * @param macrotipo the macrotipo to set
     */
    public void setMacrotipo ( String macrotipo ) {
        this.macrotipo = macrotipo;
    }

    /**
     * @return the numeroPagamentiAggregazione
     */
    public Integer getNumeroPagamentiAggregazione () {
        return numeroPagamentiAggregazione;
    }

    /**
     * @param numeroPagamentiAggregazione the numeroPagamentiAggregazione to set
     */
    public void setNumeroPagamentiAggregazione ( Integer numeroPagamentiAggregazione ) {
        this.numeroPagamentiAggregazione = numeroPagamentiAggregazione;
    }

    /**
     * @return the pdc
     */
    public String getPdc () {
        return pdc;
    }

    /**
     * @param pdc the pdc to set
     */
    public void setPdc ( String pdc ) {
        this.pdc = pdc;
    }

    /**
     * @return the progressivoFlussoSintetico
     */
    public Integer getProgressivoFlussoSintetico () {
        return progressivoFlussoSintetico;
    }

    /**
     * @param progressivoFlussoSintetico the progressivoFlussoSintetico to set
     */
    public void setProgressivoFlussoSintetico ( Integer progressivoFlussoSintetico ) {
        this.progressivoFlussoSintetico = progressivoFlussoSintetico;
    }

    /**
     * @return the tematica
     */
    public String getTematica () {
        return tematica;
    }

    /**
     * @param tematica the tematica to set
     */
    public void setTematica ( String tematica ) {
        this.tematica = tematica;
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
