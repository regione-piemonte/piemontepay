/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.model;

import java.io.Serializable;
import java.util.Date;


public class TipoPagamentoTemp implements Serializable {

    private static final long serialVersionUID = -8549346768048968214L;

    private String tipoOperazione;

    private String descrizionePortale;

    private String compilazioneNote;

    private String idApplicazione;

    private String codiceVersamento;

    private Date fineValidita;

    private Date inizioValidita;

    private String datiSpecificiRiscossione;

    private Boolean flagInvioPagamenti;

    private Long idTipoPagamentoTemp;

    private String idOperazione;

    private String chiaveIntersistema;

    private TipoPagamento tipoPagamento;

    private Ente ente;

    private Long contatoreCompilazioni;

    private Long contatorePagamenti;

    private Long contatoreSelezioni;

    private Boolean pagamentoSpontaneo;

    private String numeroAccertamento;

    private Long annoAccertamento;

    private TipologiaPagamento tipologiaPagamento;

    private Boolean flagPresenzaBollettinoPostale;

    private Boolean flagInvioRT;
    
    private Boolean multibeneficiario;
    
    private Long idTipoPagamentoSecondario;
    
    private Boolean multibeneficiarioSecondario ;
    
    private Boolean flagPersonalizzazioneCov;
    
    private Boolean flagInvioNotificatore;
    
    private byte[] passphrase;
    
    private String descrizioneTextCov;
    
    private Boolean flagVisualizzaDaSportello ;
    
    private byte[] credenzialiPnd;

    private String urlAttualizzazionePnd;
    
    

    public TipologiaPagamento getTipologiaPagamento () {
        return tipologiaPagamento;
    }

    public void setTipologiaPagamento ( TipologiaPagamento tipologiaPagamento ) {
        this.tipologiaPagamento = tipologiaPagamento;
    }

    public String getTipoOperazione () {
        return tipoOperazione;
    }

    public void setTipoOperazione ( String tipoOperazione ) {
        this.tipoOperazione = tipoOperazione;
    }

    public String getDescrizionePortale () {
        return descrizionePortale;
    }

    public void setDescrizionePortale ( String descrizionePortale ) {
        this.descrizionePortale = descrizionePortale;
    }

    public String getCompilazioneNote () {
        return compilazioneNote;
    }

    public void setCompilazioneNote ( String compilazioneNote ) {
        this.compilazioneNote = compilazioneNote;
    }

    public String getIdApplicazione () {
        return idApplicazione;
    }

    public void setIdApplicazione ( String idApplicazione ) {
        this.idApplicazione = idApplicazione;
    }

    public String getCodiceVersamento () {
        return codiceVersamento;
    }

    public void setCodiceVersamento ( String codiceVersamento ) {
        this.codiceVersamento = codiceVersamento;
    }

    public Date getFineValidita () {
        return fineValidita;
    }

    public void setFineValidita ( Date fineValidita ) {
        this.fineValidita = fineValidita;
    }

    public Date getInizioValidita () {
        return inizioValidita;
    }

    public void setInizioValidita ( Date inizioValidita ) {
        this.inizioValidita = inizioValidita;
    }

    public String getDatiSpecificiRiscossione () {
        return datiSpecificiRiscossione;
    }

    public void setDatiSpecificiRiscossione ( String datiSpecificiRiscossione ) {
        this.datiSpecificiRiscossione = datiSpecificiRiscossione;
    }

    public Boolean getFlagInvioPagamenti () {
        return flagInvioPagamenti;
    }

    public void setFlagInvioPagamenti ( Boolean flagInvioPagamenti ) {
        this.flagInvioPagamenti = flagInvioPagamenti;
    }

    public Long getIdTipoPagamentoTemp () {
        return idTipoPagamentoTemp;
    }

    public void setIdTipoPagamentoTemp ( Long idTipoPagamentoTemp ) {
        this.idTipoPagamentoTemp = idTipoPagamentoTemp;
    }

    public String getIdOperazione () {
        return idOperazione;
    }

    public void setIdOperazione ( String idOperazione ) {
        this.idOperazione = idOperazione;
    }

    public String getChiaveIntersistema () {
        return chiaveIntersistema;
    }

    public void setChiaveIntersistema ( String chiaveIntersistema ) {
        this.chiaveIntersistema = chiaveIntersistema;
    }

    public TipoPagamento getTipoPagamento () {
        return tipoPagamento;
    }

    public void setTipoPagamento ( TipoPagamento tipoPagamento ) {
        this.tipoPagamento = tipoPagamento;
    }

    public Ente getEnte () {
        return ente;
    }

    public void setEnte ( Ente ente ) {
        this.ente = ente;
    }

    public Long getContatoreCompilazioni () {
        return contatoreCompilazioni;
    }

    public void setContatoreCompilazioni ( Long contatoreCompilazioni ) {
        this.contatoreCompilazioni = contatoreCompilazioni;
    }

    public Long getContatorePagamenti () {
        return contatorePagamenti;
    }

    public void setContatorePagamenti ( Long contatorePagamenti ) {
        this.contatorePagamenti = contatorePagamenti;
    }

    public Long getContatoreSelezioni () {
        return contatoreSelezioni;
    }

    public void setContatoreSelezioni ( Long contatoreSelezioni ) {
        this.contatoreSelezioni = contatoreSelezioni;
    }

    public Boolean getPagamentoSpontaneo () {
        return pagamentoSpontaneo;
    }

    public void setPagamentoSpontaneo ( Boolean pagamentoSpontaneo ) {
        this.pagamentoSpontaneo = pagamentoSpontaneo;
    }

    public String getNumeroAccertamento () {
        return numeroAccertamento;
    }

    public void setNumeroAccertamento ( String numeroAccertamento ) {
        this.numeroAccertamento = numeroAccertamento;
    }

    public Long getAnnoAccertamento () {
        return annoAccertamento;
    }

    public void setAnnoAccertamento ( Long annoAccertamento ) {
        this.annoAccertamento = annoAccertamento;
    }

    public Boolean getFlagPresenzaBollettinoPostale () {
        return flagPresenzaBollettinoPostale;
    }

    public void setFlagPresenzaBollettinoPostale ( Boolean flagPresenzaBollettinoPostale ) {
        this.flagPresenzaBollettinoPostale = flagPresenzaBollettinoPostale;
    }

    public Boolean getFlagInvioRT () {
        return flagInvioRT;
    }

    public void setFlagInvioRT ( Boolean flagInvioRT ) {
        this.flagInvioRT = flagInvioRT;
    }

    
    public Boolean getMultibeneficiario () {
        return multibeneficiario;
    }
    
    public void setMultibeneficiario ( Boolean multibeneficiario ) {
        this.multibeneficiario = multibeneficiario;
    }

    
    public Long getIdTipoPagamentoSecondario () {
        return idTipoPagamentoSecondario;
    }

    
    public void setIdTipoPagamentoSecondario ( Long idTipoPagamentoSecondario ) {
        this.idTipoPagamentoSecondario = idTipoPagamentoSecondario;
    }

    
    public Boolean getMultibeneficiarioSecondario () {
        return multibeneficiarioSecondario;
    }

    
    public void setMultibeneficiarioSecondario ( Boolean multibeneficiarioSecondario ) {
        this.multibeneficiarioSecondario = multibeneficiarioSecondario;
    }

    
    /**
     * @return the flagPersonalizzazioneCov
     */
    public Boolean getFlagPersonalizzazioneCov () {
        return flagPersonalizzazioneCov;
    }

    
    /**
     * @param flagPersonalizzazioneCov the flagPersonalizzazioneCov to set
     */
    public void setFlagPersonalizzazioneCov ( Boolean flagPersonalizzazioneCov ) {
        this.flagPersonalizzazioneCov = flagPersonalizzazioneCov;
    }

    
    /**
     * @return the flagInvioNotificatore
     */
    public Boolean getFlagInvioNotificatore () {
        return flagInvioNotificatore;
    }

    
    /**
     * @param flagInvioNotificatore the flagInvioNotificatore to set
     */
    public void setFlagInvioNotificatore ( Boolean flagInvioNotificatore ) {
        this.flagInvioNotificatore = flagInvioNotificatore;
    }

    
    /**
     * @return the passphrase
     */
    public byte [] getPassphrase () {
        return passphrase;
    }

    
    /**
     * @param passphrase the passphrase to set
     */
    public void setPassphrase ( byte [] passphrase ) {
        this.passphrase = passphrase;
    }

    
    /**
     * @return the descrizioneTextCov
     */
    public String getDescrizioneTextCov () {
        return descrizioneTextCov;
    }

    
    /**
     * @param descrizioneTextCov the descrizioneTextCov to set
     */
    public void setDescrizioneTextCov ( String descrizioneTextCov ) {
        this.descrizioneTextCov = descrizioneTextCov;
    }

    
    /**
     * @return the flagVisualizzaDaSportello
     */
    public Boolean getFlagVisualizzaDaSportello () {
        return flagVisualizzaDaSportello;
    }
    
    /**
     * @return the credenzialiPnd
     */
    public byte [] getCredenzialiPnd () {
        return credenzialiPnd;
    }

    
    /**
     * @param flagVisualizzaDaSportello the flagVisualizzaDaSportello to set
     */
    public void setFlagVisualizzaDaSportello ( Boolean flagVisualizzaDaSportello ) {
        this.flagVisualizzaDaSportello = flagVisualizzaDaSportello;
    }
    
    /**
     * @param credenzialiPnd the credenzialiPnd to set
     */
    public void setCredenzialiPnd ( byte [] credenzialiPnd ) {
        this.credenzialiPnd = credenzialiPnd;
    }
    

    
    /**
     * @return the urlAttualizzazionePnd
     */
    public String getUrlAttualizzazionePnd () {
        return urlAttualizzazionePnd;
    }

    
    /**
     * @param urlAttualizzazionePnd the urlAttualizzazionePnd to set
     */
    public void setUrlAttualizzazionePnd ( String urlAttualizzazionePnd ) {
        this.urlAttualizzazionePnd = urlAttualizzazionePnd;
    }
    
    
    
    
}
