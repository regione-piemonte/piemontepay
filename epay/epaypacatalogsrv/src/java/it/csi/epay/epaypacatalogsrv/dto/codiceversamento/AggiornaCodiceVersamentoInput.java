/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto.codiceversamento;

import java.util.Date;

import it.csi.epay.epaypacatalogsrv.dto.ParentInput;


public class AggiornaCodiceVersamentoInput extends ParentInput {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String descrizione;

    private String codiceVoceEntrata;

    private String codiceTipoPagamento;

    private String iban;

    private String bic;

    private Boolean flagInvioFlussi;

    private String email;

    private String codiceModalitaIntegrazione;

    private String ibanAppoggio;

    private String bicAppoggio;
    
    private Boolean flagPresenzaBollettinoPostale;

	private Boolean ibanPostale;

	private Boolean ibanAppoggioPostale;

    private Boolean fattura;
    
    private Boolean flagMbSecondario;
    
    private Boolean flagMbPrimario;
    
    private Long idCovSecondarioAssocciato;
    
    private Boolean flagPersonalizzazioneCov;
    
    private String descrizioneTextCov;
    
    private String strPassphrase;
    
    private String strCredenzialiPnd;
    
    private String urlAttualizzazionePnd;
    
    private Boolean flagVisualizzaDaSportello;

    private Date dtInizioValidita;

    private Date dtFineValidita;
    
    
//    private 
    
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

	public String getCodiceModalitaIntegrazione () {
        return codiceModalitaIntegrazione;
    }

    public void setCodiceModalitaIntegrazione ( String codiceModalitaIntegrazione ) {
        this.codiceModalitaIntegrazione = codiceModalitaIntegrazione;
    }


    @Override
    public String toString () {
        return "AggiornaCodiceVersamentoInput [id=" + id + ", descrizione=" + descrizione + ", codiceVoceEntrata=" + codiceVoceEntrata
            + ", codiceTipoPagamento=" + codiceTipoPagamento + ", iban=" + iban + ", bic=" + bic + ", flagInvioFlussi=" + flagInvioFlussi + ", email=" + email
            + ", codiceModalitaIntegrazione=" + codiceModalitaIntegrazione + "]";
    }

    public Long getId () {
        return id;
    }

    public void setId ( Long id ) {
        this.id = id;
    }

    public String getDescrizione () {
        return descrizione;
    }

    public void setDescrizione ( String descrizione ) {
        this.descrizione = descrizione;
    }

    public String getCodiceVoceEntrata () {
        return codiceVoceEntrata;
    }

    public void setCodiceVoceEntrata ( String codiceVoceEntrata ) {
        this.codiceVoceEntrata = codiceVoceEntrata;
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

    public Boolean getFlagPresenzaBollettinoPostale () {
        return flagPresenzaBollettinoPostale;
    }

    public void setFlagPresenzaBollettinoPostale ( Boolean flagPresenzaBollettinoPostale ) {
        this.flagPresenzaBollettinoPostale = flagPresenzaBollettinoPostale;
    }

    public Boolean getFattura () {
        return fattura;
    }

    public void setFattura ( Boolean fattura ) {
        this.fattura = fattura;
    }

	/**
	 * @return the flagMbSecondario
	 */
	public Boolean getFlagMbSecondario() {
		return flagMbSecondario;
	}

	/**
	 * @param flagMbSecondario the flagMbSecondario to set
	 */
	public void setFlagMbSecondario(Boolean flagMbSecondario) {
		this.flagMbSecondario = flagMbSecondario;
	}

	/**
	 * @return the flagMbPrimario
	 */
	public Boolean getFlagMbPrimario() {
		return flagMbPrimario;
	}

	/**
	 * @param flagMbPrimario the flagMbPrimario to set
	 */
	public void setFlagMbPrimario(Boolean flagMbPrimario) {
		this.flagMbPrimario = flagMbPrimario;
	}

	public Long getIdCovSecondarioAssocciato() {
		return idCovSecondarioAssocciato;
	}

	public void setIdCovSecondarioAssocciato(Long idCovSecondarioAssocciato) {
		this.idCovSecondarioAssocciato = idCovSecondarioAssocciato;
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
