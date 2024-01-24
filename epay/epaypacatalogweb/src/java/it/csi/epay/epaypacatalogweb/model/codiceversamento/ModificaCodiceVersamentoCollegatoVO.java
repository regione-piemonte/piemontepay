/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.model.codiceversamento;

import java.io.Serializable;
import java.util.Date;


public class ModificaCodiceVersamentoCollegatoVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String codice;

    private String descrizione;
    
    private String codiceTipoPagamento;

    private String iban;

    private String bic;

    private Boolean flagInvioFlussi;
    
    private Boolean flagCodiceCorrentePostaleTesoreria;
    
    private Boolean flagPresenzaBollettinoPostale;
    
    private Boolean flagCodiceCorrentePostaleAppoggio;

    private String email;

    private String descrizioneStatoAggiornamento;

    private String descrizionePadre;
    
    private String ibanAppoggio;

    private String bicAppoggio;
    
    private Boolean fattura;
    
    private Boolean flagElementiMultibeneficiario;
    
    private String modalitaAssociazioneMultibeneficiario;
    
    private String modalitaAssociazioneMultibeneficiarioOld;
    

    private Long enteSecondarioAssociazioneMultibeneficiario;
    
    private Long covAssociatoAssociazioneMultibeneficiario;
    

    
    private Long covAssociatoAssociazioneMultibeneficiarioOld;
    
    private Boolean flagPersonalizzazioneCov;
    
    private String descrizioneTextCov;
     
    private String passphrase;

    private String strCredenzialiPnd;
    
    private String urlAttualizzazionePnd;
    
    
    
    private Boolean flagVisualizzaDaSportello;

    private Date dataFineValidita;

    private Date dataInizioValidita;
    
    
    

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

	@Override
    public String toString () {
        return "ModificaCodiceVersamentoCollegatoVO [id=" + id + ", codice=" + codice + ", descrizione=" + descrizione + ", iban=" + iban + ", bic=" + bic
            + ", flagInvioFlussi=" + flagInvioFlussi + ", email=" + email + ", descrizioneStatoAggiornamento=" + descrizioneStatoAggiornamento + "]";
    }

    public String getDescrizionePadre () {
        return descrizionePadre;
    }

    public void setDescrizionePadre ( String descrizionePadre ) {
        this.descrizionePadre = descrizionePadre;
    }

    public String getDescrizioneStatoAggiornamento () {
        return descrizioneStatoAggiornamento;
    }

    public void setDescrizioneStatoAggiornamento ( String descrizioneStatoAggiornamento ) {
        this.descrizioneStatoAggiornamento = descrizioneStatoAggiornamento;
    }

    public String getCodice () {
        return codice;
    }

    public void setCodice ( String codice ) {
        this.codice = codice;
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


    public Boolean getFlagPresenzaBollettinoPostale () {
        return flagPresenzaBollettinoPostale;
    }

    public void setFlagPresenzaBollettinoPostale ( Boolean flagPresenzaBollettinoPostale ) {
        this.flagPresenzaBollettinoPostale = flagPresenzaBollettinoPostale;
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

    
    public String getCodiceTipoPagamento () {
        return codiceTipoPagamento;
    }

    
    public void setCodiceTipoPagamento ( String codiceTipoPagamento ) {
        this.codiceTipoPagamento = codiceTipoPagamento;
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
    
    

}
