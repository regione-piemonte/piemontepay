/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto.codiceversamento;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class GetCodiceVersamentoOutputDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String codice;

    private String descrizione;

    private String codiceTematica;

    private String descrizioneTematica;

    private String codiceMacrotipo;

    private String descrizioneMacrotipo;

    private String descrizioneTipoPagamento;

    private Long idVoceEntrata;

    private String codiceVoceEntrata;

    private String descrizioneVoceEntrata;

    private String codiceTipoPagamento;

    private String iban;

    private String bic;

	private String codiceStatoAggiornamento;

    private String descrizioneStatoAggiornamento;

    private String descrizioneErroreAggiornamento;

    private List<GetCodiceVersamentoOutputDto> codiciVersamentoCollegati;

    private GetCodiceVersamentoOutputDto codiceVersamentoPadre;

    private String codiceModalitaIntegrazione;

	private Boolean flagPresenzaBollettinoPostale;

	private Boolean ibanPostale;

	private Boolean ibanAppoggioPostale;
	
    private String ibanAppoggio;

    private String bicAppoggio;

    private Boolean flagInvioFlussi;

    private String email;

    private Boolean fattura;
    
   private Boolean flagMbSecondario;
    
    private Boolean flagMbPrimario;
    
    private Long idCodiceVersamentoSecondarioCollegato;
    
    private Long idEnteSecondarioCollegato;
    
    private String mbModalita;
	private String mbEnteSecondario;
	private String mbCodiceVersamentoAssociato;
	
	private Boolean flagPersonalizzazioneCov;
	private String descrizioneTextCov;
	private String strPassphrase;

    private String strCredenzialiPnd;
    private String urlAttualizzazionePnd;
    
	
	 private Boolean flagVisualizzaDaSportello;
	 private Date dtInizioValidita;
	 private Date dtFineValidita;
    

    /**
	 * @return the ibanAppoggio
	 */
	public String getIbanAppoggio() {
		return ibanAppoggio;
	}

	/**
	 * @param ibanAppoggio the ibanAppoggio to set
	 */
	public void setIbanAppoggio(String ibanAppoggio) {
		this.ibanAppoggio = ibanAppoggio;
	}

	/**
	 * @return the bicAppoggio
	 */
	public String getBicAppoggio() {
		return bicAppoggio;
	}

	/**
	 * @param bicAppoggio the bicAppoggio to set
	 */
	public void setBicAppoggio(String bicAppoggio) {
		this.bicAppoggio = bicAppoggio;
	}


    public String getDescrizioneErroreAggiornamento () {
        return descrizioneErroreAggiornamento;
    }

    public void setDescrizioneErroreAggiornamento ( String descrizioneErroreAggiornamento ) {
        this.descrizioneErroreAggiornamento = descrizioneErroreAggiornamento;
    }

    public String getCodiceStatoAggiornamento () {
        return codiceStatoAggiornamento;
    }

    public void setCodiceStatoAggiornamento ( String codiceStatoAggiornamento ) {
        this.codiceStatoAggiornamento = codiceStatoAggiornamento;
    }

    public String getDescrizioneStatoAggiornamento () {
        return descrizioneStatoAggiornamento;
    }

    public void setDescrizioneStatoAggiornamento ( String descrizioneStatoAggiornamento ) {
        this.descrizioneStatoAggiornamento = descrizioneStatoAggiornamento;
    }

    public GetCodiceVersamentoOutputDto getCodiceVersamentoPadre () {
        return codiceVersamentoPadre;
    }

    public void setCodiceVersamentoPadre ( GetCodiceVersamentoOutputDto codiceVersamentoPadre ) {
        this.codiceVersamentoPadre = codiceVersamentoPadre;
    }

    public List<GetCodiceVersamentoOutputDto> getCodiciVersamentoCollegati () {
        return codiciVersamentoCollegati;
    }

    public void setCodiciVersamentoCollegati ( List<GetCodiceVersamentoOutputDto> codiciVersamentoCollegati ) {
        this.codiciVersamentoCollegati = codiciVersamentoCollegati;
    }

    public String getCodiceTematica () {
        return codiceTematica;
    }

    public void setCodiceTematica ( String codiceTematica ) {
        this.codiceTematica = codiceTematica;
    }

    public String getDescrizioneTematica () {
        return descrizioneTematica;
    }

    public void setDescrizioneTematica ( String descrizioneTematica ) {
        this.descrizioneTematica = descrizioneTematica;
    }

    public String getCodiceMacrotipo () {
        return codiceMacrotipo;
    }

    public void setCodiceMacrotipo ( String codiceMacrotipo ) {
        this.codiceMacrotipo = codiceMacrotipo;
    }

    public String getDescrizioneMacrotipo () {
        return descrizioneMacrotipo;
    }

    public void setDescrizioneMacrotipo ( String descrizioneMacrotipo ) {
        this.descrizioneMacrotipo = descrizioneMacrotipo;
    }

    public String getDescrizioneTipoPagamento () {
        return descrizioneTipoPagamento;
    }

    public void setDescrizioneTipoPagamento ( String descrizioneTipoPagamento ) {
        this.descrizioneTipoPagamento = descrizioneTipoPagamento;
    }

    public Long getId () {
        return id;
    }

    public void setId ( Long id ) {
        this.id = id;
    }

    public String getCodice () {
        return codice;
    }

    public void setCodice ( String codice ) {
        this.codice = codice;
    }

    public String getDescrizione () {
        return descrizione;
    }

    public void setDescrizione ( String descrizione ) {
        this.descrizione = descrizione;
    }

    public Long getIdVoceEntrata () {
        return idVoceEntrata;
    }

    public void setIdVoceEntrata ( Long idVoceEntrata ) {
        this.idVoceEntrata = idVoceEntrata;
    }

    public String getCodiceVoceEntrata () {
        return codiceVoceEntrata;
    }

    public void setCodiceVoceEntrata ( String codiceVoceEntrata ) {
        this.codiceVoceEntrata = codiceVoceEntrata;
    }

    public String getDescrizioneVoceEntrata () {
        return descrizioneVoceEntrata;
    }

    public void setDescrizioneVoceEntrata ( String descrizioneVoceEntrata ) {
        this.descrizioneVoceEntrata = descrizioneVoceEntrata;
    }

    public String getCodiceTipoPagamento () {
        return codiceTipoPagamento;
    }

    public void setCodiceTipoPagamento ( String codiceTipoPagamento ) {
        this.codiceTipoPagamento = codiceTipoPagamento;
    }

    public String getIban () {
        return iban;
    }

    public void setIban ( String iban ) {
        this.iban = iban;
    }

    public String getBic () {
        return bic;
    }

    public void setBic ( String bic ) {
        this.bic = bic;
    }

    public Boolean getFlagInvioFlussi () {
        return flagInvioFlussi;
    }

    public void setFlagInvioFlussi ( Boolean flagInvioFlussi ) {
        this.flagInvioFlussi = flagInvioFlussi;
    }

    public String getEmail () {
        return email;
    }

    public void setEmail ( String email ) {
        this.email = email;
    }

    public String getCodiceModalitaIntegrazione () {
        return codiceModalitaIntegrazione;
    }

    public void setCodiceModalitaIntegrazione ( String codiceModalitaIntegrazione ) {
        this.codiceModalitaIntegrazione = codiceModalitaIntegrazione;
    }

    public Boolean getFattura () {
        return fattura;
    }

    public void setFattura ( Boolean fattura ) {
        this.fattura = fattura;
    }

    /**
     * @return the ibanPostale
     */
	public Boolean getIbanPostale() {
		return ibanPostale;
	}

	/**
	 * @param ibanPostale the ibanPostale to set
	 */
	public void setIbanPostale(Boolean ibanPostale) {
		this.ibanPostale = ibanPostale;
	}

	/**
	 * @return the ibanAppoggioPostale
	 */
	public Boolean getIbanAppoggioPostale() {
		return ibanAppoggioPostale;
    }

	/**
	 * @param ibanAppoggioPostale the ibanAppoggioPostale to set
	 */
	public void setIbanAppoggioPostale(Boolean ibanAppoggioPostale) {
		this.ibanAppoggioPostale = ibanAppoggioPostale;
	}

	public Boolean getFlagPresenzaBollettinoPostale() {
		return flagPresenzaBollettinoPostale;
	}

	public void setFlagPresenzaBollettinoPostale(Boolean flagPresenzaBollettinoPostale) {
		this.flagPresenzaBollettinoPostale = flagPresenzaBollettinoPostale;
	}

	public Boolean getFlagMbSecondario() {
		return flagMbSecondario;
	}

	public void setFlagMbSecondario(Boolean flagMbSecondario) {
		this.flagMbSecondario = flagMbSecondario;
	}

	public Boolean getFlagMbPrimario() {
		return flagMbPrimario;
	}

	public void setFlagMbPrimario(Boolean flagMbPrimario) {
		this.flagMbPrimario = flagMbPrimario;
	}

	public Long getIdCodiceVersamentoSecondarioCollegato() {
		return idCodiceVersamentoSecondarioCollegato;
	}

	public void setIdCodiceVersamentoSecondarioCollegato(Long idCodiceVersamentoSecondarioCollegato) {
		this.idCodiceVersamentoSecondarioCollegato = idCodiceVersamentoSecondarioCollegato;
	}

	public Long getIdEnteSecondarioCollegato() {
		return idEnteSecondarioCollegato;
	}

	public void setIdEnteSecondarioCollegato(Long idEnteSecondarioCollegato) {
		this.idEnteSecondarioCollegato = idEnteSecondarioCollegato;
	}

	public String getMbModalita() {
		return mbModalita;
	}

	public void setMbModalita(String mbModalita) {
		this.mbModalita = mbModalita;
	}

	public String getMbEnteSecondario() {
		return mbEnteSecondario;
	}

	public void setMbEnteSecondario(String mbEnteSecondario) {
		this.mbEnteSecondario = mbEnteSecondario;
	}

	public String getMbCodiceVersamentoAssociato() {
		return mbCodiceVersamentoAssociato;
	}

	public void setMbCodiceVersamentoAssociato(String mbCodiceVersamentoAssociato) {
		this.mbCodiceVersamentoAssociato = mbCodiceVersamentoAssociato;
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
     * @return the strPassphrase
     */
    public String getStrPassphrase () {
        return strPassphrase;
    }

    
    /**
     * @param strPassphrase the strPassphrase to set
     */
    public void setStrPassphrase ( String strPassphrase ) {
        this.strPassphrase = strPassphrase;
    }

    
    /**
     * @return the strCredenzialiPnd
     */
    public String getStrCredenzialiPnd () {
        return strCredenzialiPnd;
    }

    
    /**
     * @param strCredenzialiPnd the strCredenzialiPnd to set
     */
    public void setStrCredenzialiPnd ( String strCredenzialiPnd ) {
        this.strCredenzialiPnd = strCredenzialiPnd;
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
    public Boolean getFlagVisualizzaDaSportello () {
        return flagVisualizzaDaSportello;
    }

    
    public void setFlagVisualizzaDaSportello ( Boolean flagVisualizzaDaSportello ) {
        this.flagVisualizzaDaSportello = flagVisualizzaDaSportello;
    }

    
    public Date getDtInizioValidita () {
        return dtInizioValidita;
    }

    
    public void setDtInizioValidita ( Date dtInizioValidita ) {
        this.dtInizioValidita = dtInizioValidita;
    }

    
    public Date getDtFineValidita () {
        return dtFineValidita;
    }

    
    public void setDtFineValidita ( Date dtFineValidita ) {
        this.dtFineValidita = dtFineValidita;
    }
    
    
    
    
	
	
}
