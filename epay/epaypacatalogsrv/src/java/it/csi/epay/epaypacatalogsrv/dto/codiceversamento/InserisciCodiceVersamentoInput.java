/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto.codiceversamento;

import javax.persistence.Column;
import java.util.Date;

import it.csi.epay.epaypacatalogsrv.dto.ParentInput;


public class InserisciCodiceVersamentoInput extends ParentInput {

    private static final long serialVersionUID = 1L;

    private Long idCodiceVersamentoPadre;

    private String descrizione;

    private String codiceVoceEntrata;

    private String codiceTipoPagamento;

    private String iban;

    private String bic;

    private Boolean flagInvioFlussi;
    
    private String ibanAppoggio;

    private String bicAppoggio;

    private Boolean flagPresenzaBollettinoPostale;

	private Boolean ibanPostale;

    private Boolean ibanAppoggioPostale;
    
    private String email;

    private String codiceModalitaIntegrazione;
    
    private Boolean fattura;
    
    private Boolean flagMbSecondario;
    
    private Boolean flagMbPrimario;
    
    private Boolean flagElementiMultibeneficiario;
    
    private String modalitaAssociazioneMultibeneficiario;
    
    private Long enteSecondarioAssociazioneMultibeneficiario;
    
    private Long covAssociatoAssociazioneMultibeneficiario;
    
    private Boolean flagPersonalizzazioneCov;
    
    private String descrizioneTextCov;
    
    private String strPassphrase;
    
    private String strCredenzialiPnd;
    
    private String urlAttualizzazionePnd;
    
    
    private Boolean flagVisualizzaDaSportello;

    private Date dtInizioValidita;

    private Date dtFineValidita;
    
    


    public String getCodiceModalitaIntegrazione () {
        return codiceModalitaIntegrazione;
    }

    public void setCodiceModalitaIntegrazione ( String codiceModalitaIntegrazione ) {
        this.codiceModalitaIntegrazione = codiceModalitaIntegrazione;
    }

    

    @Override
    public String toString () {
        return "InserisciCodiceVersamentoInput [idCodiceVersamentoPadre=" + idCodiceVersamentoPadre + ", descrizione=" + descrizione + ", codiceVoceEntrata="
            + codiceVoceEntrata + ", codiceTipoPagamento=" + codiceTipoPagamento + ", iban=" + iban + ", bic=" + bic + ", flagInvioFlussi=" + flagInvioFlussi
            + ", ibanAppoggio=" + ibanAppoggio + ", bicAppoggio=" + bicAppoggio + ", flagPresenzaBollettinoPostale=" + flagPresenzaBollettinoPostale
            + ", ibanPostale=" + ibanPostale + ", ibanAppoggioPostale=" + ibanAppoggioPostale + ", email=" + email + ", codiceModalitaIntegrazione="
            + codiceModalitaIntegrazione + ", fattura=" + fattura + ", flagMbSecondario=" + flagMbSecondario + ", flagMbPrimario=" + flagMbPrimario
            + ", flagElementiMultibeneficiario=" + flagElementiMultibeneficiario + ", modalitaAssociazioneMultibeneficiario="
            + modalitaAssociazioneMultibeneficiario + ", enteSecondarioAssociazioneMultibeneficiario=" + enteSecondarioAssociazioneMultibeneficiario
            + ", covAssociatoAssociazioneMultibeneficiario=" + covAssociatoAssociazioneMultibeneficiario + ", flagPersonalizzazioneCov="
            + flagPersonalizzazioneCov + ", descrizioneTextCov=" + descrizioneTextCov + ", strPassphrase=" + strPassphrase + "]";
    }

    public Long getIdCodiceVersamentoPadre () {
        return idCodiceVersamentoPadre;
    }

    public void setIdCodiceVersamentoPadre ( Long idCodiceVersamentoPadre ) {
        this.idCodiceVersamentoPadre = idCodiceVersamentoPadre;
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

	public Boolean getFlagElementiMultibeneficiario() {
		return flagElementiMultibeneficiario;
	}

	public void setFlagElementiMultibeneficiario(Boolean flagElementiMultibeneficiario) {
		this.flagElementiMultibeneficiario = flagElementiMultibeneficiario;
	}

	public String getModalitaAssociazioneMultibeneficiario() {
		return modalitaAssociazioneMultibeneficiario;
	}

	public void setModalitaAssociazioneMultibeneficiario(String modalitaAssociazioneMultibeneficiario) {
		this.modalitaAssociazioneMultibeneficiario = modalitaAssociazioneMultibeneficiario;
	}

	public Long getEnteSecondarioAssociazioneMultibeneficiario() {
		return enteSecondarioAssociazioneMultibeneficiario;
	}

	public void setEnteSecondarioAssociazioneMultibeneficiario(Long enteSecondarioAssociazioneMultibeneficiario) {
		this.enteSecondarioAssociazioneMultibeneficiario = enteSecondarioAssociazioneMultibeneficiario;
	}

	public Long getCovAssociatoAssociazioneMultibeneficiario() {
		return covAssociatoAssociazioneMultibeneficiario;
	}

	public void setCovAssociatoAssociazioneMultibeneficiario(Long covAssociatoAssociazioneMultibeneficiario) {
		this.covAssociatoAssociazioneMultibeneficiario = covAssociatoAssociazioneMultibeneficiario;
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
