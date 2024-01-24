/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.model.gestioneente;

import it.csi.epay.epaypacatalogweb.model.GenericVO;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;


public class EnteVO extends GenericVO {

    private static final long serialVersionUID = 1L;

    private String cap;

    private String codiceFiscale;

    private String codiceFiscaleUtenteVariazione;

    private String codiceModalitaAcquisizioneProvvisori;

    private String codicePeriodicitaSchedulazioneRiconciliazione;

    private String codiceStatoAggiornamento;

    private String codiceTipologiaAccertamento;

    private LocalDateTime dataVariazione;

    private String denominazione;

    private String descrizioneStatoAggiornamento;

    private String descrizioneUtenteAmministratore;

    private Boolean flagAccertamento;

    private Boolean flagEntePlurintermediato;

    private Boolean flagQualsiasiCodiceVersamento;

    private Boolean flagRicezioneErrori;
    private Boolean flagAdesioneCittaFacile;

    private Boolean flagRicezioneFlussoBaseRendicontazione;

    private Boolean flagRiconciliazioneVersamenti;

    private String giornoSchedulazione;

    private Long id;

    private List<Long> admins;

    private String indirizzo;

    private String localita;

    private byte [] logoContent;

    private String logoContentType;

    private String logoFileName;

    private Integer logoFileSize;

    private String siglaProvincia;

    private String descrizioneErroreAggiornamento;

    private List<GenericVO> codiciVersamentoAssociati;

    private String civico;

    private String email;

    private String gs1Gln;

    private String cbill;

    private String iban;

    private String bic;

	private String codiceIstat;
    
    private String templateEmailId;
    
    private String urlDominio;
    
    private String codiceIpa;

	/**
     * @return the cap
     */
    public String getCap () {
        return cap;
    }

    /**
     * @param cap the cap to set
     */
    public void setCap ( String cap ) {
        this.cap = cap;
    }

    /**
     * @return the codiceFiscale
     */
    public String getCodiceFiscale () {
        return codiceFiscale;
    }

    /**
     * @param codiceFiscale the codiceFiscale to set
     */
    public void setCodiceFiscale ( String codiceFiscale ) {
        this.codiceFiscale = codiceFiscale;
    }

    /**
     * @return the codiceFiscaleUtenteVariazione
     */
    public String getCodiceFiscaleUtenteVariazione () {
        return codiceFiscaleUtenteVariazione;
    }

    /**
     * @param codiceFiscaleUtenteVariazione the codiceFiscaleUtenteVariazione to set
     */
    public void setCodiceFiscaleUtenteVariazione ( String codiceFiscaleUtenteVariazione ) {
        this.codiceFiscaleUtenteVariazione = codiceFiscaleUtenteVariazione;
    }

    /**
     * @return the codiceModalitaAcquisizioneProvvisori
     */
    public String getCodiceModalitaAcquisizioneProvvisori () {
        return codiceModalitaAcquisizioneProvvisori;
    }

    /**
     * @param codiceModalitaAcquisizioneProvvisori the codiceModalitaAcquisizioneProvvisori to set
     */
    public void setCodiceModalitaAcquisizioneProvvisori ( String codiceModalitaAcquisizioneProvvisori ) {
        this.codiceModalitaAcquisizioneProvvisori = codiceModalitaAcquisizioneProvvisori;
    }

    /**
     * @return the codicePeriodicitaSchedulazioneRiconciliazione
     */
    public String getCodicePeriodicitaSchedulazioneRiconciliazione () {
        return codicePeriodicitaSchedulazioneRiconciliazione;
    }

    /**
     * @param codicePeriodicitaSchedulazioneRiconciliazione the codicePeriodicitaSchedulazioneRiconciliazione to set
     */
    public void setCodicePeriodicitaSchedulazioneRiconciliazione ( String codicePeriodicitaSchedulazioneRiconciliazione ) {
        this.codicePeriodicitaSchedulazioneRiconciliazione = codicePeriodicitaSchedulazioneRiconciliazione;
    }

    /**
     * @return the codiceStatoAggiornamento
     */
    public String getCodiceStatoAggiornamento () {
        return codiceStatoAggiornamento;
    }

    /**
     * @param codiceStatoAggiornamento the codiceStatoAggiornamento to set
     */
    public void setCodiceStatoAggiornamento ( String codiceStatoAggiornamento ) {
        this.codiceStatoAggiornamento = codiceStatoAggiornamento;
    }

    /**
     * @return the codiceTipologiaAccertamento
     */
    public String getCodiceTipologiaAccertamento () {
        return codiceTipologiaAccertamento;
    }

    /**
     * @param codiceTipologiaAccertamento the codiceTipologiaAccertamento to set
     */
    public void setCodiceTipologiaAccertamento ( String codiceTipologiaAccertamento ) {
        this.codiceTipologiaAccertamento = codiceTipologiaAccertamento;
    }

    /**
     * @return the dataVariazione
     */
    public LocalDateTime getDataVariazione () {
        return dataVariazione;
    }

    /**
     * @param dataVariazione the dataVariazione to set
     */
    public void setDataVariazione ( LocalDateTime dataVariazione ) {
        this.dataVariazione = dataVariazione;
    }

    /**
     * @return the denominazione
     */
    public String getDenominazione () {
        return denominazione;
    }

    /**
     * @param denominazione the denominazione to set
     */
    public void setDenominazione ( String denominazione ) {
        this.denominazione = denominazione;
    }

    /**
     * @return the descrizioneStatoAggiornamento
     */
    public String getDescrizioneStatoAggiornamento () {
        return descrizioneStatoAggiornamento;
    }

    /**
     * @param descrizioneStatoAggiornamento the descrizioneStatoAggiornamento to set
     */
    public void setDescrizioneStatoAggiornamento ( String descrizioneStatoAggiornamento ) {
        this.descrizioneStatoAggiornamento = descrizioneStatoAggiornamento;
    }

    /**
     * @return the descrizioneUtenteAmministratore
     */
    public String getDescrizioneUtenteAmministratore () {
        return descrizioneUtenteAmministratore;
    }

    /**
     * @param descrizioneUtenteAmministratore the descrizioneUtenteAmministratore to set
     */
    public void setDescrizioneUtenteAmministratore ( String descrizioneUtenteAmministratore ) {
        this.descrizioneUtenteAmministratore = descrizioneUtenteAmministratore;
    }

    /**
     * @return the flagAccertamento
     */
    public Boolean getFlagAccertamento () {
        return flagAccertamento;
    }

    /**
     * @param flagAccertamento the flagAccertamento to set
     */
    public void setFlagAccertamento ( Boolean flagAccertamento ) {
        this.flagAccertamento = flagAccertamento;
    }

    /**
     * @return the flagEntePlurintermediato
     */
    public Boolean getFlagEntePlurintermediato () {
        return flagEntePlurintermediato;
    }

    /**
     * @param flagEntePlurintermediato the flagEntePlurintermediato to set
     */
    public void setFlagEntePlurintermediato ( Boolean flagEntePlurintermediato ) {
        this.flagEntePlurintermediato = flagEntePlurintermediato;
    }

    /**
     * @return the flagQualsiasiCodiceVersamento
     */
    public Boolean getFlagQualsiasiCodiceVersamento () {
        return flagQualsiasiCodiceVersamento;
    }

    /**
     * @param flagQualsiasiCodiceVersamento the flagQualsiasiCodiceVersamento to set
     */
    public void setFlagQualsiasiCodiceVersamento ( Boolean flagQualsiasiCodiceVersamento ) {
        this.flagQualsiasiCodiceVersamento = flagQualsiasiCodiceVersamento;
    }

    /**
     * @return the flagRicezioneErrori
     */
    public Boolean getFlagRicezioneErrori () {
        return flagRicezioneErrori;
    }

    /**
     * @param flagRicezioneErrori the flagRicezioneErrori to set
     */
    public void setFlagRicezioneErrori ( Boolean flagRicezioneErrori ) {
        this.flagRicezioneErrori = flagRicezioneErrori;
    }

	public Boolean getFlagAdesioneCittaFacile () {
		return flagAdesioneCittaFacile;
	}

	public void setFlagAdesioneCittaFacile ( Boolean value ) {
		this.flagAdesioneCittaFacile = value;
	}

    /**
     * @return the flagRicezioneFlussoBaseRendicontazione
     */
    public Boolean getFlagRicezioneFlussoBaseRendicontazione () {
        return flagRicezioneFlussoBaseRendicontazione;
    }

    /**
     * @param flagRicezioneFlussoBaseRendicontazione the flagRicezioneFlussoBaseRendicontazione to set
     */
    public void setFlagRicezioneFlussoBaseRendicontazione ( Boolean flagRicezioneFlussoBaseRendicontazione ) {
        this.flagRicezioneFlussoBaseRendicontazione = flagRicezioneFlussoBaseRendicontazione;
    }

    /**
     * @return the flagRiconciliazioneVersamenti
     */
    public Boolean getFlagRiconciliazioneVersamenti () {
        return flagRiconciliazioneVersamenti;
    }

    /**
     * @param flagRiconciliazioneVersamenti the flagRiconciliazioneVersamenti to set
     */
    public void setFlagRiconciliazioneVersamenti ( Boolean flagRiconciliazioneVersamenti ) {
        this.flagRiconciliazioneVersamenti = flagRiconciliazioneVersamenti;
    }

    /**
     * @return the giornoSchedulazione
     */
    public String getGiornoSchedulazione () {
        return giornoSchedulazione;
    }

    /**
     * @param giornoSchedulazione the giornoSchedulazione to set
     */
    public void setGiornoSchedulazione ( String giornoSchedulazione ) {
        this.giornoSchedulazione = giornoSchedulazione;
    }

    /**
     * @return the id
     */
    @Override
    public Long getId () {
        return id;
    }

    /**
     * @param id the id to set
     */
    @Override
    public void setId ( Long id ) {
        this.id = id;
    }

    /**
     * @return the indirizzo
     */
    public String getIndirizzo () {
        return indirizzo;
    }

    /**
     * @param indirizzo the indirizzo to set
     */
    public void setIndirizzo ( String indirizzo ) {
        this.indirizzo = indirizzo;
    }

    /**
     * @return the localita
     */
    public String getLocalita () {
        return localita;
    }

    /**
     * @param localita the localita to set
     */
    public void setLocalita ( String localita ) {
        this.localita = localita;
    }

    /**
     * @return the logoContent
     */
    public byte [] getLogoContent () {
        return logoContent;
    }

    /**
     * @param logoContent the logoContent to set
     */
    public void setLogoContent ( byte [] logoContent ) {
        this.logoContent = logoContent;
    }

    /**
     * @return the logoContentType
     */
    public String getLogoContentType () {
        return logoContentType;
    }

    /**
     * @param logoContentType the logoContentType to set
     */
    public void setLogoContentType ( String logoContentType ) {
        this.logoContentType = logoContentType;
    }

    /**
     * @return the logoFileName
     */
    public String getLogoFileName () {
        return logoFileName;
    }

    /**
     * @param logoFileName the logoFileName to set
     */
    public void setLogoFileName ( String logoFileName ) {
        this.logoFileName = logoFileName;
    }

    /**
     * @return the logoFileSize
     */
    public Integer getLogoFileSize () {
        return logoFileSize;
    }

    /**
     * @param logoFileSize the logoFileSize to set
     */
    public void setLogoFileSize ( Integer logoFileSize ) {
        this.logoFileSize = logoFileSize;
    }

    /**
     * @return the siglaProvincia
     */
    public String getSiglaProvincia () {
        return siglaProvincia;
    }

    /**
     * @param siglaProvincia the siglaProvincia to set
     */
    public void setSiglaProvincia ( String siglaProvincia ) {
        this.siglaProvincia = siglaProvincia;
    }

    /**
     * @return the descrizioneErroreAggiornamento
     */
    public String getDescrizioneErroreAggiornamento () {
        return descrizioneErroreAggiornamento;
    }

    /**
     * @param descrizioneErroreAggiornamento the descrizioneErroreAggiornamento to set
     */
    public void setDescrizioneErroreAggiornamento ( String descrizioneErroreAggiornamento ) {
        this.descrizioneErroreAggiornamento = descrizioneErroreAggiornamento;
    }

    /**
     * @return the codiciVersamentoAssociati
     */
    public List<GenericVO> getCodiciVersamentoAssociati () {
        return codiciVersamentoAssociati;
    }

    /**
     * @param codiciVersamentoAssociati the codiciVersamentoAssociati to set
     */
    public void setCodiciVersamentoAssociati ( List<GenericVO> codiciVersamentoAssociati ) {
        this.codiciVersamentoAssociati = codiciVersamentoAssociati;
    }

    /**
     * @return the civico
     */
    public String getCivico () {
        return civico;
    }

    /**
     * @param civico the civico to set
     */
    public void setCivico ( String civico ) {
        this.civico = civico;
    }

    /**
     * @return the email
     */
    public String getEmail () {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail ( String email ) {
        this.email = email;
    }

    /**
     * @return the gs1Gln
     */
    public String getGs1Gln () {
        return gs1Gln;
    }

    /**
     * @param gs1Gln the gs1Gln to set
     */
    public void setGs1Gln ( String gs1Gln ) {
        this.gs1Gln = gs1Gln;
    }

    /**
     * @return the cbill
     */
    public String getCbill () {
        return cbill;
    }

    /**
     * @param cbill the cbill to set
     */
    public void setCbill ( String cbill ) {
        this.cbill = cbill;
    }

    /**
     * @return the iban
     */
    public String getIban () {
        return iban;
    }

    /**
     * @param iban the iban to set
     */
    public void setIban ( String iban ) {
        this.iban = iban;
    }

    /**
     * @return the bic
     */
    public String getBic () {
        return bic;
    }

    /**
     * @param bic the bic to set
     */
    public void setBic ( String bic ) {
        this.bic = bic;
    }

    public List<Long> getAdmins () {
        return admins;
    }

    public void setAdmins ( List<Long> admins ) {
        this.admins = admins;
    }


	public String getCodiceIstat () {
		return codiceIstat;
	}

	public void setCodiceIstat ( String codiceIstat ) {
		this.codiceIstat = codiceIstat;
	}
	
	

	
    /**
     * @return the templateEmailId
     */
    public String getTemplateEmailId () {
        return templateEmailId;
    }

    
    /**
     * @param templateEmailId the templateEmailId to set
     */
    public void setTemplateEmailId ( String templateEmailId ) {
        this.templateEmailId = templateEmailId;
    }

    
    /**
     * @return the urlDominio
     */
    public String getUrlDominio () {
        return urlDominio;
    }

    
    /**
     * @param urlDominio the urlDominio to set
     */
    public void setUrlDominio ( String urlDominio ) {
        this.urlDominio = urlDominio;
    }
    

    
    /**
     * @return the codiceIpa
     */
    public String getCodiceIpa () {
        return codiceIpa;
    }

    
    /**
     * @param codiceIpa the codiceIpa to set
     */
    public void setCodiceIpa ( String codiceIpa ) {
        this.codiceIpa = codiceIpa;
    }

    @Override public String toString () {
		return "EnteVO{" +
						"cap='" + cap + '\'' +
						", codiceFiscale='" + codiceFiscale + '\'' +
						", codiceFiscaleUtenteVariazione='" + codiceFiscaleUtenteVariazione + '\'' +
						", codiceModalitaAcquisizioneProvvisori='" + codiceModalitaAcquisizioneProvvisori + '\'' +
						", codicePeriodicitaSchedulazioneRiconciliazione='" + codicePeriodicitaSchedulazioneRiconciliazione + '\'' +
						", codiceStatoAggiornamento='" + codiceStatoAggiornamento + '\'' +
						", codiceTipologiaAccertamento='" + codiceTipologiaAccertamento + '\'' +
						", dataVariazione=" + dataVariazione +
						", denominazione='" + denominazione + '\'' +
						", descrizioneStatoAggiornamento='" + descrizioneStatoAggiornamento + '\'' +
						", descrizioneUtenteAmministratore='" + descrizioneUtenteAmministratore + '\'' +
						", flagAccertamento=" + flagAccertamento +
						", flagEntePlurintermediato=" + flagEntePlurintermediato +
						", flagQualsiasiCodiceVersamento=" + flagQualsiasiCodiceVersamento +
						", flagRicezioneErrori=" + flagRicezioneErrori +
						", flagAdesioneCittaFacile=" + flagAdesioneCittaFacile +
						", flagRicezioneFlussoBaseRendicontazione=" + flagRicezioneFlussoBaseRendicontazione +
						", flagRiconciliazioneVersamenti=" + flagRiconciliazioneVersamenti +
						", giornoSchedulazione='" + giornoSchedulazione + '\'' +
						", id=" + id +
						", admins=" + admins +
						", indirizzo='" + indirizzo + '\'' +
						", localita='" + localita + '\'' +
						", logoContent=" + Arrays.toString ( logoContent ) +
						", logoContentType='" + logoContentType + '\'' +
						", logoFileName='" + logoFileName + '\'' +
						", logoFileSize=" + logoFileSize +
						", siglaProvincia='" + siglaProvincia + '\'' +
						", descrizioneErroreAggiornamento='" + descrizioneErroreAggiornamento + '\'' +
						", codiciVersamentoAssociati=" + codiciVersamentoAssociati +
						", civico='" + civico + '\'' +
						", email='" + email + '\'' +
						", gs1Gln='" + gs1Gln + '\'' +
						", cbill='" + cbill + '\'' +
						", iban='" + iban + '\'' +
						", bic='" + bic + '\'' +
						", codiceIstat='" + codiceIstat + '\'' +
						", templateEmailId='" + templateEmailId + '\'' +
						", urlDominio='" + urlDominio + '\'' +
						", codiceIpa='" + codiceIpa + '\'' +
						'}';
	}
}
