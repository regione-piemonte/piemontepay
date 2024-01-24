/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto.ente;

import java.util.Arrays;
import java.util.List;

import it.csi.epay.epaypacatalogsrv.dto.ParentInput;


public class AggiornaEnteInput extends ParentInput {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String cap;

    private String denominazione;

    private String siglaProvincia;

    private Boolean flagAccertamento;

    private Boolean flagEntePlurintermediato;

    private Boolean flagRicezioneErrori;

    private Boolean flagRicezioneFlussoRendicontazione;

    private Boolean flagRiconciliazioneVersamenti;

    private Boolean flagRicezioneFlussoBaseRendicontazione;

    private Boolean flagQualsiasiCodiceVersamento;

	private Boolean flagAdesioneCittaFacile;

    private String indirizzo;

    private String localita;

    private byte [] logoContent;

    private String logoContentType;

    private String logoFileName;

    private Integer logoFileSize;

    private Integer giornoSchedulazione;

    private String codiceModalitaAcquisizioneProvvisori;

    private String codicePeriodicitaSchedulazioneRiconciliazione;

    private String codiceTipologiaAccertamento;

    private List<Long> idCodiciVersamentoAssociati;

    private Boolean cancellaLogo;

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
     * @return the id
     */
    public Long getId () {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId ( Long id ) {
        this.id = id;
    }

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

    /**
     * @return the flagRicezioneFlussoRendicontazione
     */
    public Boolean getFlagRicezioneFlussoRendicontazione () {
        return flagRicezioneFlussoRendicontazione;
    }

    /**
     * @param flagRicezioneFlussoRendicontazione the flagRicezioneFlussoRendicontazione to set
     */
    public void setFlagRicezioneFlussoRendicontazione ( Boolean flagRicezioneFlussoRendicontazione ) {
        this.flagRicezioneFlussoRendicontazione = flagRicezioneFlussoRendicontazione;
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

	public Boolean getFlagAdesioneCittaFacile () {
		return flagAdesioneCittaFacile;
	}

	public void setFlagAdesioneCittaFacile ( Boolean value ) {
		this.flagAdesioneCittaFacile = value;
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
     * @return the giornoSchedulazione
     */
    public Integer getGiornoSchedulazione () {
        return giornoSchedulazione;
    }

    /**
     * @param giornoSchedulazione the giornoSchedulazione to set
     */
    public void setGiornoSchedulazione ( Integer giornoSchedulazione ) {
        this.giornoSchedulazione = giornoSchedulazione;
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
     * @return the idCodiciVersamentoAssociati
     */
    public List<Long> getIdCodiciVersamentoAssociati () {
        return idCodiciVersamentoAssociati;
    }

    /**
     * @param idCodiciVersamentoAssociati the idCodiciVersamentoAssociati to set
     */
    public void setIdCodiciVersamentoAssociati ( List<Long> idCodiciVersamentoAssociati ) {
        this.idCodiciVersamentoAssociati = idCodiciVersamentoAssociati;
    }

    /**
     * @return the cancellaLogo
     */
    public Boolean getCancellaLogo () {
        return cancellaLogo;
    }

    /**
     * @param cancellaLogo the cancellaLogo to set
     */
    public void setCancellaLogo ( Boolean cancellaLogo ) {
        this.cancellaLogo = cancellaLogo;
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

	public String getCodiceIstat () {
		return codiceIstat;
	}

	public void setCodiceIstat ( String codiceIstat ) {
		this.codiceIstat = codiceIstat;
	}

    
    public String getTemplateEmailId () {
        return templateEmailId;
    }

    
    public void setTemplateEmailId ( String templateEmailId ) {
        this.templateEmailId = templateEmailId;
    }

    
    public String getUrlDominio () {
        return urlDominio;
    }

    
    public void setUrlDominio ( String urlDominio ) {
        this.urlDominio = urlDominio;
    }
    
    public String getCodiceIpa () {
        return codiceIpa;
    }

    
    public void setCodiceIpa ( String codiceIpa ) {
        this.codiceIpa = codiceIpa;
    }

    @Override
    public String toString () {
        return "AggiornaEnteInput [id=" + id + ", cap=" + cap + ", denominazione=" + denominazione + ", siglaProvincia=" + siglaProvincia
            + ", flagAccertamento=" + flagAccertamento + ", flagEntePlurintermediato=" + flagEntePlurintermediato + ", flagRicezioneErrori="
            + flagRicezioneErrori + ", flagRicezioneFlussoRendicontazione=" + flagRicezioneFlussoRendicontazione + ", flagRiconciliazioneVersamenti="
            + flagRiconciliazioneVersamenti + ", flagRicezioneFlussoBaseRendicontazione=" + flagRicezioneFlussoBaseRendicontazione
            + ", flagQualsiasiCodiceVersamento=" + flagQualsiasiCodiceVersamento + ", flagAdesioneCittaFacile=" + flagAdesioneCittaFacile + ", indirizzo="
            + indirizzo + ", localita=" + localita + ", logoFileName=" + logoFileName + ", logoFileSize=" + logoFileSize + ", giornoSchedulazione="
            + giornoSchedulazione + ", codiceModalitaAcquisizioneProvvisori=" + codiceModalitaAcquisizioneProvvisori
            + ", codicePeriodicitaSchedulazioneRiconciliazione=" + codicePeriodicitaSchedulazioneRiconciliazione + ", codiceTipologiaAccertamento="
            + codiceTipologiaAccertamento + ", idCodiciVersamentoAssociati=" + idCodiciVersamentoAssociati + ", cancellaLogo=" + cancellaLogo + ", civico="
            + civico + ", email=" + email + ", gs1Gln=" + gs1Gln + ", cbill=" + cbill + ", iban=" + iban + ", bic=" + bic + ", codiceIstat=" + codiceIstat
            + ", templateEmailId=" + templateEmailId + ", urlDominio=" + urlDominio 
            + ", codiceIpa=" + codiceIpa + "]";
    }
    
    
	
	
	
}
