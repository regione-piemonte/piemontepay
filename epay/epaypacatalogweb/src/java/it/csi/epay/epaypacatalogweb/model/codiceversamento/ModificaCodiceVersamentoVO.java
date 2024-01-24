/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.model.codiceversamento;

import java.util.Date;

import it.csi.epay.epaypacatalogweb.model.GenericVO;


public class ModificaCodiceVersamentoVO extends GenericVO {

    private static final long serialVersionUID = 1L;

    private String codice;

    private String descrizione;

    private String codiceVoceEntrata;

    private String codiceTipoPagamento;

    private String descrizioneTipoPagamento;

    private String descrizioneVoceEntrata;

    private String descrizioneStatoAggiornamento;

    private String iban;

    private String bic;

    private Boolean flagCodiceCorrentePostaleAppoggio;

    private Boolean flagCodiceCorrentePostaleTesoreria;
    
  	private String ibanAppoggio;
  	
  	private String bicAppoggio;
      
  	private Boolean flagInvioFlussi;
  
  	private Boolean flagPresenzaBollettinoPostale;
  	
    private String email;

    private String codiceModalitaIntegrazione;
    
    private Boolean fattura;
    
    private Boolean flagElementiMultibeneficiario;
    
    private String modalitaAssociazioneMultibeneficiario;
    

    private Long enteSecondarioAssociazioneMultibeneficiario;
    
    private Long covAssociatoAssociazioneMultibeneficiario;
    
    private String modalitaAssociazioneMultibeneficiarioOld;
    
    private Long covAssociatoAssociazioneMultibeneficiarioOld;
    
    private Boolean flagPersonalizzazioneCov;
    
    private String descrizioneTextCov;
     
    private String passphrase;
    
    private String strCredenzialiPnd;
    
    private String urlAttualizzazionePnd;

    
    private Boolean flagVisualizzaDaSportello;

    private Date dataFineValidita;

    private Date dataInizioValidita;

    public ModificaCodiceVersamentoVO () {
        // TODO Auto-generated constructor stub
    }

    @Override
    public String toString () {
        return "ModificaCodiceVersamentoVO [codice=" + codice + ", descrizione=" + descrizione + ", codiceVoceEntrata=" + codiceVoceEntrata
                        + ", codiceTipoPagamento=" + codiceTipoPagamento + ", descrizioneVoceEntrata=" + descrizioneVoceEntrata + ", descrizioneStatoAggiornamento="
                        + descrizioneStatoAggiornamento + ", iban=" + iban + ", bic=" + bic + ", flagInvioFlussi=" + flagInvioFlussi + ", email=" + email
                        + ", codiceModalitaIntegrazione=" + codiceModalitaIntegrazione  
                        + ", descrizioneTextCov=" + descrizioneTextCov 
                        + ", passphrase=" + passphrase 
                        +"]";
    }

    @Override
    public String getCodice () {
        return codice;
    }

    @Override
    public void setCodice ( String codice ) {
        this.codice = codice;
    }

    public String getDescrizioneStatoAggiornamento () {
        return descrizioneStatoAggiornamento;
    }

    public void setDescrizioneStatoAggiornamento ( String descrizioneStatoAggiornamento ) {
        this.descrizioneStatoAggiornamento = descrizioneStatoAggiornamento;
    }

    public String getDescrizioneVoceEntrata () {
        return descrizioneVoceEntrata;
    }

    public void setDescrizioneVoceEntrata ( String descrizioneVoceEntrata ) {
        this.descrizioneVoceEntrata = descrizioneVoceEntrata;
    }

    @Override
    public String getDescrizione () {
        return descrizione;
    }

    @Override
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

    public String getCodiceModalitaIntegrazione () {
        return codiceModalitaIntegrazione;
    }

    public void setCodiceModalitaIntegrazione ( String codiceModalitaIntegrazione ) {
        this.codiceModalitaIntegrazione = codiceModalitaIntegrazione;
    }

    /**
     * @return the descrizioneTipoPagamento
     */
    public String getDescrizioneTipoPagamento () {
        return descrizioneTipoPagamento;
    }

    /**
     * @param descrizioneTipoPagamento the descrizioneTipoPagamento to set
     */
    public void setDescrizioneTipoPagamento ( String descrizioneTipoPagamento ) {
        this.descrizioneTipoPagamento = descrizioneTipoPagamento;
    }

    
    /**
	 * @return the flagCodiceCorrentePostaleTesoreria
	 */
	public Boolean getFlagCodiceCorrentePostaleTesoreria() {
		return flagCodiceCorrentePostaleTesoreria;
	}

	/**
	 * @param flagCodiceCorrentePostaleTesoreria the flagCodiceCorrentePostaleTesoreria to set
	 */
	public void setFlagCodiceCorrentePostaleTesoreria(Boolean flagCodiceCorrentePostaleTesoreria) {
		this.flagCodiceCorrentePostaleTesoreria = flagCodiceCorrentePostaleTesoreria;
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
	 * @return the flagCodiceCorrentePostaleAppoggio
	 */
	public Boolean getFlagCodiceCorrentePostaleAppoggio() {
		return flagCodiceCorrentePostaleAppoggio;
	}

	/**
	 * @param flagCodiceCorrentePostaleAppoggio the flagCodiceCorrentePostaleAppoggio to set
	 */
	public void setFlagCodiceCorrentePostaleAppoggio(Boolean flagCodiceCorrentePostaleAppoggio) {
		this.flagCodiceCorrentePostaleAppoggio = flagCodiceCorrentePostaleAppoggio;
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

    
    public String getModalitaAssociazioneMultibeneficiarioOld () {
        return modalitaAssociazioneMultibeneficiarioOld;
    }

    
    public void setModalitaAssociazioneMultibeneficiarioOld ( String modalitaAssociazioneMultibeneficiarioOld ) {
        this.modalitaAssociazioneMultibeneficiarioOld = modalitaAssociazioneMultibeneficiarioOld;
    }

    
    public Long getCovAssociatoAssociazioneMultibeneficiarioOld () {
        return covAssociatoAssociazioneMultibeneficiarioOld;
    }

    
    public void setCovAssociatoAssociazioneMultibeneficiarioOld ( Long covAssociatoAssociazioneMultibeneficiarioOld ) {
        this.covAssociatoAssociazioneMultibeneficiarioOld = covAssociatoAssociazioneMultibeneficiarioOld;
    }

    
    public Boolean getFlagPersonalizzazioneCov () {
        return flagPersonalizzazioneCov;
    }

    
    public void setFlagPersonalizzazioneCov ( Boolean flagPersonalizzazioneCov ) {
        this.flagPersonalizzazioneCov = flagPersonalizzazioneCov;
    }

    
    public String getDescrizioneTextCov () {
        return descrizioneTextCov;
    }

    
    public void setDescrizioneTextCov ( String descrizioneTextCov ) {
        this.descrizioneTextCov = descrizioneTextCov;
    }

    
    public String getPassphrase () {
        return passphrase;
    }

    
    public void setPassphrase ( String passphrase ) {
        this.passphrase = passphrase;
    }

    
    public Boolean getFlagVisualizzaDaSportello () {
        return flagVisualizzaDaSportello;
    }

    
    public void setFlagVisualizzaDaSportello ( Boolean flagVisualizzaDaSportello ) {
        this.flagVisualizzaDaSportello = flagVisualizzaDaSportello;
    }

    
    public Date getDataFineValidita () {
        return dataFineValidita;
    }

    
    public void setDataFineValidita ( Date dataFineValidita ) {
        this.dataFineValidita = dataFineValidita;
    }

    
    public Date getDataInizioValidita () {
        return dataInizioValidita;
    }

    
    public void setDataInizioValidita ( Date dataInizioValidita ) {
        this.dataInizioValidita = dataInizioValidita;
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
	
    
    

}


