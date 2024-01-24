/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;


public class TipoPagamento implements Serializable {

    private static final long serialVersionUID = -8549346768048968214L;

    private Long idTipoPagamento;

    private String descrizionePortale;

    private String compilazioneNote;

    private String idApplicazione;

    private String codiceVersamento;

    private Date fineValidita;

    private Date inizioValidita;

    private String datiSpecificiRiscossione;

    private Boolean flagInvioPagamenti;

    private Ente epayTEnti;

    private String numeroAccertamento;

    private Long annoAccertamento;

    private String chiaveIntersistema;

    private Long contatoreCompilazioni;

    private Long contatorePagamenti;

    private Long contatoreSelezioni;

    private Boolean pagamentoSpontaneo;

    private TipologiaPagamento tipologiaPagamento;

    private Boolean flagPresenzaBollettinoPostale;

    private Boolean flagInvioRT;
    
    private Boolean flagMultibeneficiario;

    private Boolean flagPersonalizzazioneCov;
    
    private Boolean flagInvioNotificatore;
    
    private byte[] passphrase;
    
    private String descrizioneTextCov;
    
    private Boolean flagVisualizzaDaSportello ;
    private String urlAttualizzazionePnd;
    
    private byte[] credenzialiPnd;
    
    

    public TipologiaPagamento getTipologiaPagamento () {
        return tipologiaPagamento;
    }

    public void setTipologiaPagamento ( TipologiaPagamento tipologiaPagamento ) {
        this.tipologiaPagamento = tipologiaPagamento;
    }

    public Boolean getPagamentoSpontaneo () {
        return pagamentoSpontaneo;
    }

    public void setPagamentoSpontaneo ( Boolean pagamentoSpontaneo ) {
        this.pagamentoSpontaneo = pagamentoSpontaneo;
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

    public String getChiaveIntersistema () {
        return chiaveIntersistema;
    }

    public void setChiaveIntersistema ( String chiaveIntersistema ) {
        this.chiaveIntersistema = chiaveIntersistema;
    }

    public Ente getEpayTEnti () {
        return epayTEnti;
    }

    public void setEpayTEnti ( Ente epayTEnti ) {
        this.epayTEnti = epayTEnti;
    }

    /**
     * @return the idTipoPagamento
     */
    public Long getIdTipoPagamento () {
        return idTipoPagamento;
    }

    /**
     * @param idTipoPagamento the idTipoPagamento to set
     */
    public void setIdTipoPagamento ( Long idTipoPagamento ) {
        this.idTipoPagamento = idTipoPagamento;
    }

    /**
     * @return the descrizionePortale
     */
    public String getDescrizionePortale () {
        return descrizionePortale;
    }

    /**
     * @param codice the codice to set
     */
    public void setDescrizionePortale ( String descrizionePortale ) {
        this.descrizionePortale = descrizionePortale;
    }

    /**
     * @return the compilazioneNote
     */
    public String getCompilazioneNote () {
        return compilazioneNote;
    }

    /**
     * @param compilazioneNote the compilazioneNote to set
     */
    public void setCompilazioneNote ( String compilazioneNote ) {
        this.compilazioneNote = compilazioneNote;
    }

    /**
     * @return the idApplicazione
     */
    public String getIdApplicazione () {
        return idApplicazione;
    }

    /**
     * @param idApplicazione the idApplicazione to set
     */
    public void setIdApplicazione ( String idApplicazione ) {
        this.idApplicazione = idApplicazione;
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
     * @return the fineValidita
     */
    public Date getFineValidita () {
        return fineValidita;
    }

    /**
     * @param fineValidita the fineValidita to set
     */
    public void setFineValidita ( Date fineValidita ) {
        this.fineValidita = fineValidita;
    }

    /**
     * @return the inizioValidita
     */
    public Date getInizioValidita () {
        return inizioValidita;
    }

    /**
     * @param inizioValidita the inizioValidita to set
     */
    public void setInizioValidita ( Date inizioValidita ) {
        this.inizioValidita = inizioValidita;
    }

    public String getDatiSpecificiRiscossione () {
        return datiSpecificiRiscossione;
    }

    public void setDatiSpecificiRiscossione ( String datiSpecificiRiscossione ) {
        this.datiSpecificiRiscossione = datiSpecificiRiscossione;
    }

    /**
     * @return the flagInvioPagamenti
     */
    public Boolean getFlagInvioPagamenti () {
        return flagInvioPagamenti;
    }

    /**
     * @param flagInvioPagamenti the flagInvioPagamenti to set
     */
    public void setFlagInvioPagamenti ( Boolean flagInvioPagamenti ) {
        this.flagInvioPagamenti = flagInvioPagamenti;
    }

    /**
     * @return the flagPresenzaBollettinoPostale
     */
    public Boolean getFlagPresenzaBollettinoPostale() {
        return flagPresenzaBollettinoPostale;
    }

    /**
     * @param flagPresenzaBollettinoPostale the flagPresenzaBollettinoPostale to set
     */
    public void setFlagPresenzaBollettinoPostale(Boolean flagPresenzaBollettinoPostale) {
        this.flagPresenzaBollettinoPostale = flagPresenzaBollettinoPostale;
    }

    public Boolean getFlagInvioRT () {
        return flagInvioRT;
    }

    public void setFlagInvioRT ( Boolean flagInvioRT ) {
        this.flagInvioRT = flagInvioRT;
    }
    
    
	public Boolean getFlagMultibeneficiario() {
		return flagMultibeneficiario;
	}

	public void setFlagMultibeneficiario(Boolean flagMultibeneficiario) {
		this.flagMultibeneficiario = flagMultibeneficiario;
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
     * @return the flagVisualizzaDaSportello
     */
    public Boolean getFlagVisualizzaDaSportello () {
        return flagVisualizzaDaSportello;
    }

    
    /**
     * @param flagVisualizzaDaSportello the flagVisualizzaDaSportello to set
     */
    public void setFlagVisualizzaDaSportello ( Boolean flagVisualizzaDaSportello ) {
        this.flagVisualizzaDaSportello = flagVisualizzaDaSportello;
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
    

    
    /**
     * @return the credenzialiPnd
     */
    public byte [] getCredenzialiPnd () {
        return credenzialiPnd;
    }

    
    /**
     * @param credenzialiPnd the credenzialiPnd to set
     */
    public void setCredenzialiPnd ( byte [] credenzialiPnd ) {
        this.credenzialiPnd = credenzialiPnd;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString () {
        return "TipoPagamento [idTipoPagamento=" + idTipoPagamento + ", descrizionePortale=" + descrizionePortale + ", compilazioneNote=" + compilazioneNote
            + ", idApplicazione=" + idApplicazione + ", codiceVersamento=" + codiceVersamento + ", fineValidita=" + fineValidita + ", inizioValidita="
            + inizioValidita + ", datiSpecificiRiscossione=" + datiSpecificiRiscossione + ", flagInvioPagamenti=" + flagInvioPagamenti + ", epayTEnti="
            + epayTEnti + ", numeroAccertamento=" + numeroAccertamento + ", annoAccertamento=" + annoAccertamento + ", chiaveIntersistema=" + chiaveIntersistema
            + ", contatoreCompilazioni=" + contatoreCompilazioni + ", contatorePagamenti=" + contatorePagamenti + ", contatoreSelezioni=" + contatoreSelezioni
            + ", pagamentoSpontaneo=" + pagamentoSpontaneo + ", tipologiaPagamento=" + tipologiaPagamento + ", flagPresenzaBollettinoPostale="
            + flagPresenzaBollettinoPostale + ", flagInvioRT=" + flagInvioRT + ", flagMultibeneficiario=" + flagMultibeneficiario + ", flagInvioNotificatore="
            + flagInvioNotificatore + ", passphrase=" + Arrays.toString ( passphrase ) + ", descrizioneTextCov=" + descrizioneTextCov + "]";
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    
    
}
