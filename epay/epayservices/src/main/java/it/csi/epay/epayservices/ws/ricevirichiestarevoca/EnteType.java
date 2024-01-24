/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayservices.ws.ricevirichiestarevoca;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>
 * Classe Java per EnteType complex type.
 * 
 * <p>
 * Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="EnteType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CodiceFiscale" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Denominazione" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Indirizzo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Civico" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Localita" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Cap" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SiglaProvincia" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Gs1Gln" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Cbill" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Iban" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Bic" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EntePlurintermediato" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="RiconciliazioneVersamenti" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="Accertamento" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="RicezioneErrori" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="TipologiaAccertamento" type="{http://www.csi.it/epay/epaywso/coopapplicativapec}TipologiaAccertamentoType"/>
 *         &lt;element name="ModalitaAcquisizioneProvvisori" type="{http://www.csi.it/epay/epaywso/coopapplicativapec}ModalitaAcquisizioneProvvisoriType"/>
 *         &lt;element name="RicezioneFlussoBaseRendicontazione" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="PeriodicitaSchedulazioneRiconciliazione" type="{http://www.csi.it/epay/epaywso/coopapplicativapec}PeriodicitaSchedulazioneType"/>
 *         &lt;element name="GiornoSchedulazione" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="QualsiasiCodiceVersamento" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="Logo" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType ( XmlAccessType.FIELD )
@XmlType ( name = "EnteType", propOrder = {
    "codiceFiscale",
    "denominazione",
    "indirizzo",
    "civico",
    "localita",
    "cap",
    "siglaProvincia",
    "email",
    "gs1Gln",
    "cbill",
    "iban",
    "bic",
    "ibanTesoreria",
    "bicTesoreria",
    "ibanAppoggio",
    "bicAppoggio",
    "entePlurintermediato",
    "riconciliazioneVersamenti",
    "accertamento",
    "ricezioneErrori",
    "tipologiaAccertamento",
    "modalitaAcquisizioneProvvisori",
    "ricezioneFlussoBaseRendicontazione",
    "periodicitaSchedulazioneRiconciliazione",
    "giornoSchedulazione",
    "qualsiasiCodiceVersamento",
    "logo",
	"flagAdesioneCittaFacile",
	"codiceIstat",
    "templateEmailId",
    "urlDominio",
    "codiceIpa"
} )
public class EnteType {

    @XmlElement ( name = "CodiceFiscale", required = true )
    protected String codiceFiscale;

    @XmlElement ( name = "Denominazione", required = true )
    protected String denominazione;

    @XmlElement ( name = "Indirizzo", required = true )
    protected String indirizzo;

    @XmlElement ( name = "Civico", required = true )
    protected String civico;

    @XmlElement ( name = "Localita", required = true )
    protected String localita;

    @XmlElement ( name = "Cap", required = true )
    protected String cap;

    @XmlElement ( name = "SiglaProvincia", required = true )
    protected String siglaProvincia;

    @XmlElement ( name = "Email" )
    protected String email;

    @XmlElement ( name = "Gs1Gln" )
    protected String gs1Gln;

    @XmlElement ( name = "Cbill" )
    protected String cbill;

    @XmlElement ( name = "Iban" )
    protected String iban;

    @XmlElement ( name = "Bic" )
    protected String bic;

    @XmlElement ( name = "IbanTesoreria", nillable = true )
    protected String ibanTesoreria;

    @XmlElement ( name = "BicTesoreria", nillable = true )
    protected String bicTesoreria;

    @XmlElement ( name = "IbanAppoggio", nillable = true )
    protected String ibanAppoggio;

    @XmlElement ( name = "BicAppoggio", nillable = true )
    protected String bicAppoggio;

    @XmlElement ( name = "EntePlurintermediato" )
    protected boolean entePlurintermediato;

    @XmlElement ( name = "RiconciliazioneVersamenti" )
    protected boolean riconciliazioneVersamenti;

    @XmlElement ( name = "Accertamento" )
    protected boolean accertamento;

    @XmlElement ( name = "RicezioneErrori" )
    protected boolean ricezioneErrori;

    @XmlElement ( name = "TipologiaAccertamento", required = true )
    @XmlSchemaType ( name = "string" )
    protected TipologiaAccertamentoType tipologiaAccertamento;

    @XmlElement ( name = "ModalitaAcquisizioneProvvisori", required = true )
    @XmlSchemaType ( name = "string" )
    protected ModalitaAcquisizioneProvvisoriType modalitaAcquisizioneProvvisori;

    @XmlElement ( name = "RicezioneFlussoBaseRendicontazione" )
    protected boolean ricezioneFlussoBaseRendicontazione;

    @XmlElement ( name = "PeriodicitaSchedulazioneRiconciliazione", required = true )
    @XmlSchemaType ( name = "string" )
    protected PeriodicitaSchedulazioneType periodicitaSchedulazioneRiconciliazione;

    @XmlElement ( name = "GiornoSchedulazione" )
    protected Integer giornoSchedulazione;

    @XmlElement ( name = "QualsiasiCodiceVersamento" )
    protected boolean qualsiasiCodiceVersamento;

    @XmlElement ( name = "Logo" )
    protected byte [] logo;

	@XmlElement ( name = "flagAdesioneCittaFacile" )
	protected boolean flagAdesioneCittaFacile;
	
	@XmlElement ( name = "codiceIstat" )
	protected String codiceIstat;
	
    @XmlElement ( name = "TemplateEmailId" )
    protected String templateEmailId;
    
    @XmlElement ( name = "UrlDominio" )
    protected String urlDominio;
    
    @XmlElement ( name = "codiceIpa" )
    protected String codiceIpa;

    /**
     * Recupera il valore della proprieta' codiceFiscale.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getCodiceFiscale () {
        return codiceFiscale;
    }

    /**
     * Imposta il valore della proprieta' codiceFiscale.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setCodiceFiscale ( String value ) {
        this.codiceFiscale = value;
    }

    /**
     * Recupera il valore della proprieta' denominazione.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getDenominazione () {
        return denominazione;
    }

    /**
     * Imposta il valore della proprieta' denominazione.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setDenominazione ( String value ) {
        this.denominazione = value;
    }

    /**
     * Recupera il valore della proprieta' indirizzo.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getIndirizzo () {
        return indirizzo;
    }

    /**
     * Imposta il valore della proprieta' indirizzo.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setIndirizzo ( String value ) {
        this.indirizzo = value;
    }

    /**
     * Recupera il valore della proprieta' civico.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getCivico () {
        return civico;
    }

    /**
     * Imposta il valore della proprieta' civico.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setCivico ( String value ) {
        this.civico = value;
    }

    /**
     * Recupera il valore della proprieta' localita.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getLocalita () {
        return localita;
    }

    /**
     * Imposta il valore della proprieta' localita.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setLocalita ( String value ) {
        this.localita = value;
    }

    /**
     * Recupera il valore della proprieta' cap.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getCap () {
        return cap;
    }

    /**
     * Imposta il valore della proprieta' cap.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setCap ( String value ) {
        this.cap = value;
    }

    /**
     * Recupera il valore della proprieta' siglaProvincia.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getSiglaProvincia () {
        return siglaProvincia;
    }

    /**
     * Imposta il valore della proprieta' siglaProvincia.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setSiglaProvincia ( String value ) {
        this.siglaProvincia = value;
    }

    /**
     * Recupera il valore della proprieta' email.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getEmail () {
        return email;
    }

    /**
     * Imposta il valore della proprieta' email.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setEmail ( String value ) {
        this.email = value;
    }

    /**
     * Recupera il valore della proprieta' gs1Gln.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getGs1Gln () {
        return gs1Gln;
    }

    /**
     * Imposta il valore della proprieta' gs1Gln.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setGs1Gln ( String value ) {
        this.gs1Gln = value;
    }

    /**
     * Recupera il valore della proprieta' cbill.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getCbill () {
        return cbill;
    }

    /**
     * Imposta il valore della proprieta' cbill.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setCbill ( String value ) {
        this.cbill = value;
    }

    /**
     * Recupera il valore della proprieta' iban.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getIban () {
        return iban;
    }

    /**
     * Imposta il valore della proprieta' iban.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setIban ( String value ) {
        this.iban = value;
    }

    /**
     * Recupera il valore della proprieta' bic.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getBic () {
        return bic;
    }

    /**
     * Imposta il valore della proprieta' bic.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setBic ( String value ) {
        this.bic = value;
    }

    /**
     * Recupera il valore della proprieta' entePlurintermediato.
     * 
     */
    public boolean isEntePlurintermediato () {
        return entePlurintermediato;
    }

    /**
     * Imposta il valore della proprieta' entePlurintermediato.
     * 
     */
    public void setEntePlurintermediato ( boolean value ) {
        this.entePlurintermediato = value;
    }

    /**
     * Recupera il valore della proprieta' riconciliazioneVersamenti.
     * 
     */
    public boolean isRiconciliazioneVersamenti () {
        return riconciliazioneVersamenti;
    }

    /**
     * Imposta il valore della proprieta' riconciliazioneVersamenti.
     * 
     */
    public void setRiconciliazioneVersamenti ( boolean value ) {
        this.riconciliazioneVersamenti = value;
    }

    /**
     * Recupera il valore della proprieta' accertamento.
     * 
     */
    public boolean isAccertamento () {
        return accertamento;
    }

    /**
     * Imposta il valore della proprieta' accertamento.
     * 
     */
    public void setAccertamento ( boolean value ) {
        this.accertamento = value;
    }

    /**
     * Recupera il valore della proprieta' ricezioneErrori.
     * 
     */
    public boolean isRicezioneErrori () {
        return ricezioneErrori;
    }

    /**
     * Imposta il valore della proprieta' ricezioneErrori.
     * 
     */
    public void setRicezioneErrori ( boolean value ) {
        this.ricezioneErrori = value;
    }

    /**
     * Recupera il valore della proprieta' tipologiaAccertamento.
     * 
     * @return possible object is {@link TipologiaAccertamentoType }
     * 
     */
    public TipologiaAccertamentoType getTipologiaAccertamento () {
        return tipologiaAccertamento;
    }

    /**
     * Imposta il valore della proprieta' tipologiaAccertamento.
     * 
     * @param value allowed object is {@link TipologiaAccertamentoType }
     * 
     */
    public void setTipologiaAccertamento ( TipologiaAccertamentoType value ) {
        this.tipologiaAccertamento = value;
    }

    /**
     * Recupera il valore della proprieta' modalitaAcquisizioneProvvisori.
     * 
     * @return possible object is {@link ModalitaAcquisizioneProvvisoriType }
     * 
     */
    public ModalitaAcquisizioneProvvisoriType getModalitaAcquisizioneProvvisori () {
        return modalitaAcquisizioneProvvisori;
    }

    /**
     * Imposta il valore della proprieta' modalitaAcquisizioneProvvisori.
     * 
     * @param value allowed object is {@link ModalitaAcquisizioneProvvisoriType }
     * 
     */
    public void setModalitaAcquisizioneProvvisori ( ModalitaAcquisizioneProvvisoriType value ) {
        this.modalitaAcquisizioneProvvisori = value;
    }

    /**
     * Recupera il valore della proprieta' ricezioneFlussoBaseRendicontazione.
     * 
     */
    public boolean isRicezioneFlussoBaseRendicontazione () {
        return ricezioneFlussoBaseRendicontazione;
    }

    /**
     * Imposta il valore della proprieta' ricezioneFlussoBaseRendicontazione.
     * 
     */
    public void setRicezioneFlussoBaseRendicontazione ( boolean value ) {
        this.ricezioneFlussoBaseRendicontazione = value;
    }

    /**
     * Recupera il valore della proprieta' periodicitaSchedulazioneRiconciliazione.
     * 
     * @return possible object is {@link PeriodicitaSchedulazioneType }
     * 
     */
    public PeriodicitaSchedulazioneType getPeriodicitaSchedulazioneRiconciliazione () {
        return periodicitaSchedulazioneRiconciliazione;
    }

    /**
     * Imposta il valore della proprieta' periodicitaSchedulazioneRiconciliazione.
     * 
     * @param value allowed object is {@link PeriodicitaSchedulazioneType }
     * 
     */
    public void setPeriodicitaSchedulazioneRiconciliazione ( PeriodicitaSchedulazioneType value ) {
        this.periodicitaSchedulazioneRiconciliazione = value;
    }

    /**
     * Recupera il valore della proprieta' giornoSchedulazione.
     * 
     * @return possible object is {@link Integer }
     * 
     */
    public Integer getGiornoSchedulazione () {
        return giornoSchedulazione;
    }

    /**
     * Imposta il valore della proprieta' giornoSchedulazione.
     * 
     * @param value allowed object is {@link Integer }
     * 
     */
    public void setGiornoSchedulazione ( Integer value ) {
        this.giornoSchedulazione = value;
    }

    /**
     * Recupera il valore della proprieta' qualsiasiCodiceVersamento.
     * 
     */
    public boolean isQualsiasiCodiceVersamento () {
        return qualsiasiCodiceVersamento;
    }

    /**
     * Imposta il valore della proprieta' qualsiasiCodiceVersamento.
     * 
     */
    public void setQualsiasiCodiceVersamento ( boolean value ) {
        this.qualsiasiCodiceVersamento = value;
    }

    /**
     * Recupera il valore della proprieta' logo.
     * 
     * @return possible object is byte[]
     */
    public byte [] getLogo () {
        return logo;
    }

    /**
     * Imposta il valore della proprieta' logo.
     * 
     * @param value allowed object is byte[]
     */
    public void setLogo ( byte [] value ) {
        this.logo = value;
    }

    public String getIbanTesoreria () {
        return ibanTesoreria;
    }

    public void setIbanTesoreria ( String ibanTesoreria ) {
        this.ibanTesoreria = ibanTesoreria;
    }

    public String getBicTesoreria () {
        return bicTesoreria;
    }

    public void setBicTesoreria ( String bicTesoreria ) {
        this.bicTesoreria = bicTesoreria;
    }

    public String getIbanAppoggio () {
        return ibanAppoggio;
    }

    public void setIbanAppoggio ( String ibanAppoggio ) {
        this.ibanAppoggio = ibanAppoggio;
    }

    public String getBicAppoggio () {
        return bicAppoggio;
    }

    public void setBicAppoggio ( String bicAppoggio ) {
        this.bicAppoggio = bicAppoggio;
    }

	public boolean isFlagAdesioneCittaFacile () {
		return flagAdesioneCittaFacile;
	}

	public void setFlagAdesioneCittaFacile ( boolean flagAdesioneCittaFacile ) {
		this.flagAdesioneCittaFacile = flagAdesioneCittaFacile;
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

}
