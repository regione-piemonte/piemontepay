/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.model.codiceversamento;

import java.util.Date;
import java.util.List;

import it.csi.epay.epaypacatalogweb.model.GenericVO;


/**
 * @author manue
 *
 */
public class CodiceVersamentoVO extends GenericVO {

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
    
    private String ibanAppoggio;

    private String bicAppoggio;

    private Boolean flagInvioFlussi;
    
    private Boolean flagCodiceCorrentePostaleTesoreria;

    private String email;

    private String codiceStatoAggiornamento;

    private String descrizioneStatoAggiornamento;

    private String descrizioneErroreAggiornamento;

    private List<CodiceVersamentoVO> codiciVersamentoCollegati;

    private CodiceVersamentoVO codiceVersamentoPadre;
    
    private String codiceModalitaIntegrazione;
    
    private Boolean flagPresenzaBollettinoPostale;
    
    private Boolean flagCodiceCorrentePostaleAppoggio;

    private Boolean fattura;
    
    private Boolean flagElementiMultibeneficiario;
    
    private String modalitaAssociazioneMultibeneficiario;
    
    private Long enteSecondarioAssociazioneMultibeneficiario;
    
    private Long covAssociatoAssociazioneMultibeneficiario;

	private String mbModalita;
	private String mbEnteSecondario;
	private String mbCodiceVersamentoAssociato;
	
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
	
	
//	 private Boolean flagElementiMultibeneficiario;
	 

	

	/**
	 * @return the bicAppoggio
	 */
	public String getBicAppoggio() {
		return bicAppoggio;
	}


    @Override
    public String toString () {
        return "CodiceVersamentoVO [id=" + id + ", codice=" + codice + ", descrizione=" + descrizione + ", codiceTematica=" + codiceTematica
            + ", descrizioneTematica=" + descrizioneTematica + ", codiceMacrotipo=" + codiceMacrotipo + ", descrizioneMacrotipo=" + descrizioneMacrotipo
            + ", descrizioneTipoPagamento=" + descrizioneTipoPagamento + ", idVoceEntrata=" + idVoceEntrata + ", codiceVoceEntrata=" + codiceVoceEntrata
            + ", descrizioneVoceEntrata=" + descrizioneVoceEntrata + ", codiceTipoPagamento=" + codiceTipoPagamento + ", iban=" + iban + ", bic=" + bic
            + ", ibanAppoggio=" + ibanAppoggio + ", bicAppoggio=" + bicAppoggio + ", flagInvioFlussi=" + flagInvioFlussi
            + ", flagCodiceCorrentePostaleTesoreria=" + flagCodiceCorrentePostaleTesoreria + ", email=" + email + ", codiceStatoAggiornamento="
            + codiceStatoAggiornamento + ", descrizioneStatoAggiornamento=" + descrizioneStatoAggiornamento + ", descrizioneErroreAggiornamento="
            + descrizioneErroreAggiornamento + ", codiciVersamentoCollegati=" + codiciVersamentoCollegati + ", codiceVersamentoPadre=" + codiceVersamentoPadre
            + ", codiceModalitaIntegrazione=" + codiceModalitaIntegrazione + ", flagPresenzaBollettinoPostale=" + flagPresenzaBollettinoPostale
            + ", flagCodiceCorrentePostaleAppoggio=" + flagCodiceCorrentePostaleAppoggio + ", fattura=" + fattura + ", flagElementiMultibeneficiario="
            + flagElementiMultibeneficiario + ", modalitaAssociazioneMultibeneficiario=" + modalitaAssociazioneMultibeneficiario
            + ", enteSecondarioAssociazioneMultibeneficiario=" + enteSecondarioAssociazioneMultibeneficiario + ", covAssociatoAssociazioneMultibeneficiario="
            + covAssociatoAssociazioneMultibeneficiario + ", mbModalita=" + mbModalita + ", mbEnteSecondario=" + mbEnteSecondario
            + ", mbCodiceVersamentoAssociato=" + mbCodiceVersamentoAssociato + ", modalitaAssociazioneMultibeneficiarioOld="
            + modalitaAssociazioneMultibeneficiarioOld + ", covAssociatoAssociazioneMultibeneficiarioOld=" + covAssociatoAssociazioneMultibeneficiarioOld
            + ", flagPersonalizzazioneCov=" + flagPersonalizzazioneCov + ", descrizioneTextCov=" + descrizioneTextCov + ", passphrase=" + passphrase + "]";
    }


    /**
	 * @param bicAppoggio the bicAppoggio to set
	 */
	public void setBicAppoggio(String bicAppoggio) {
		this.bicAppoggio = bicAppoggio;
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

    /**
	 * @return the ibanDiAppoggio
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

	public String getDescrizioneErroreAggiornamento () {
        return descrizioneErroreAggiornamento;
    }

    public void setDescrizioneErroreAggiornamento ( String descrizioneErroreAggiornamento ) {
        this.descrizioneErroreAggiornamento = descrizioneErroreAggiornamento;
    }

    public List<CodiceVersamentoVO> getCodiciVersamentoCollegati () {
        return codiciVersamentoCollegati;
    }

    public void setCodiciVersamentoCollegati ( List<CodiceVersamentoVO> codiciVersamentoCollegati ) {
        this.codiciVersamentoCollegati = codiciVersamentoCollegati;
    }

    public CodiceVersamentoVO getCodiceVersamentoPadre () {
        return codiceVersamentoPadre;
    }

    public void setCodiceVersamentoPadre ( CodiceVersamentoVO codiceVersamentoPadre ) {
        this.codiceVersamentoPadre = codiceVersamentoPadre;
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

    @Override
    public Long getId () {
        return id;
    }

    @Override
    public void setId ( Long id ) {
        this.id = id;
    }

    @Override
    public String getCodice () {
        return codice;
    }

    @Override
    public void setCodice ( String codice ) {
        this.codice = codice;
    }

    @Override
    public String getDescrizione () {
        return descrizione;
    }

    @Override
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

    
    public String getCodiceModalitaIntegrazione () {
        return codiceModalitaIntegrazione;
    }

    
    public void setCodiceModalitaIntegrazione ( String codiceModalitaIntegrazione ) {
        this.codiceModalitaIntegrazione = codiceModalitaIntegrazione;
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
