/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaywsosrv.facade.cooppec.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>
 * Java class for EnteType complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="EnteType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CodiceFiscale" type="{http://www.csi.it/epay/epaywso/types}String16Type"/>
 *         &lt;element name="Denominazione" type="{http://www.csi.it/epay/epaywso/types}String250Type"/>
 *         &lt;element name="Indirizzo" type="{http://www.csi.it/epay/epaywso/types}String70Type"/>
 *         &lt;element name="Civico" type="{http://www.csi.it/epay/epaywso/types}String16Type"/>
 *         &lt;element name="Localita" type="{http://www.csi.it/epay/epaywso/types}String100Type"/>
 *         &lt;element name="Cap" type="{http://www.csi.it/epay/epaywso/types}String5Type"/>
 *         &lt;element name="SiglaProvincia" type="{http://www.csi.it/epay/epaywso/types}String2Type"/>
 *         &lt;element name="Email" type="{http://www.csi.it/epay/epaywso/types}EMailAddress" minOccurs="0"/>
 *         &lt;element name="Gs1Gln" type="{http://www.csi.it/epay/epaywso/types}String35Type" minOccurs="0"/>
 *         &lt;element name="Cbill" type="{http://www.csi.it/epay/epaywso/types}String35Type" minOccurs="0"/>
 *         &lt;element name="Iban" type="{http://www.csi.it/epay/epaywso/types}IBANType" minOccurs="0"/>
 *         &lt;element name="Bic" type="{http://www.csi.it/epay/epaywso/types}String35Type" minOccurs="0"/>
 *         &lt;element name="EntePlurintermediato" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="RiconciliazioneVersamenti" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="Accertamento" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="RicezioneErrori" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="TipologiaAccertamento" type="{http://www.csi.it/epay/epaywso/coopapplicativapec}TipologiaAccertamentoType"/>
 *         &lt;element name="ModalitaAcquisizioneProvvisori" type="{http://www.csi.it/epay/epaywso/coopapplicativapec}ModalitaAcquisizioneProvvisoriType"/>
 *         &lt;element name="RicezioneFlussoBaseRendicontazione" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="PeriodicitaSchedulazioneRiconciliazione" type="{http://www.csi.it/epay/epaywso/coopapplicativapec}PeriodicitaSchedulazioneType"/>
 *         &lt;element name="GiornoSchedulazione" type="{http://www.csi.it/epay/epaywso/coopapplicativapec}GiornoDelMeseType" minOccurs="0"/>
 *         &lt;element name="QualsiasiCodiceVersamento" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="logo" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *         &lt;element name="FlagPresenzaBollettinoPostale" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="FlagCodiceCorrentePostaleAppoggio" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="FlagCodiceCorrentePostaleTesoreria" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="Fattura" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
    "flagPresenzaBollettinoPostale",
    "flagCodiceCorrentePostaleAppoggio",
    "flagCodiceCorrentePostaleTesoreria",
    "fattura",
    "flagGestionePpay",
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
    protected TipologiaAccertamentoType tipologiaAccertamento;

    @XmlElement ( name = "ModalitaAcquisizioneProvvisori", required = true )
    protected ModalitaAcquisizioneProvvisoriType modalitaAcquisizioneProvvisori;

    @XmlElement ( name = "RicezioneFlussoBaseRendicontazione" )
    protected boolean ricezioneFlussoBaseRendicontazione;

    @XmlElement ( name = "PeriodicitaSchedulazioneRiconciliazione", required = true )
    protected PeriodicitaSchedulazioneType periodicitaSchedulazioneRiconciliazione;

    @XmlElement ( name = "GiornoSchedulazione" )
    protected Integer giornoSchedulazione;

    @XmlElement ( name = "QualsiasiCodiceVersamento" )
    protected boolean qualsiasiCodiceVersamento;

    @XmlElement ( name = "Logo" )
    protected byte [] logo;

    @XmlElement ( name = "FlagPresenzaBollettinoPostale", nillable = true )
    protected boolean flagPresenzaBollettinoPostale;

    @XmlElement ( name = "FlagCodiceCorrentePostaleAppoggio" )
    protected boolean flagCodiceCorrentePostaleAppoggio;

    @XmlElement ( name = "FlagCodiceCorrentePostaleTesoreria" )
    protected boolean flagCodiceCorrentePostaleTesoreria;

    @XmlElement ( name = "Fattura" )
    protected boolean fattura;

    @XmlElement ( name = "flagGestionePpay" )
    protected boolean flagGestionePpay;

	@XmlElement ( name = "codiceIstat" )
	protected String codiceIstat;
	
	@XmlElement ( name = "flagAdesioneCittaFacile" )
	protected boolean flagAdesioneCittaFacile;
    
    @XmlElement ( name = "TemplateEmailId" )
    protected String templateEmailId;
    
    @XmlElement ( name = "UrlDominio" )
    protected String urlDominio;
    
    @XmlElement ( name = "codiceIpa" )
    protected String codiceIpa;
    
	
	

    /**
     * @return the flagPresenzaBollettinoPostale
     */
    public boolean isFlagPresenzaBollettinoPostale () {
        return flagPresenzaBollettinoPostale;
    }

    /**
     * @param flagPresenzaBollettinoPostale the flagPresenzaBollettinoPostale to set
     */
    public void setFlagPresenzaBollettinoPostale ( boolean flagPresenzaBollettinoPostale ) {
        this.flagPresenzaBollettinoPostale = flagPresenzaBollettinoPostale;
    }

    /**
     * @return the flagCodiceCorrentePostaleAppoggio
     */
    public boolean isFlagCodiceCorrentePostaleAppoggio () {
        return flagCodiceCorrentePostaleAppoggio;
    }

    /**
     * @param flagCodiceCorrentePostaleAppoggio the flagCodiceCorrentePostaleAppoggio to set
     */
    public void setFlagCodiceCorrentePostaleAppoggio ( boolean flagCodiceCorrentePostaleAppoggio ) {
        this.flagCodiceCorrentePostaleAppoggio = flagCodiceCorrentePostaleAppoggio;
    }

    /**
     * @return the flagCodiceCorrentePostaleTesoreria
     */
    public boolean isFlagCodiceCorrentePostaleTesoreria () {
        return flagCodiceCorrentePostaleTesoreria;
    }

    /**
     * @param flagCodiceCorrentePostaleTesoreria the flagCodiceCorrentePostaleTesoreria to set
     */
    public void setFlagCodiceCorrentePostaleTesoreria ( boolean flagCodiceCorrentePostaleTesoreria ) {
        this.flagCodiceCorrentePostaleTesoreria = flagCodiceCorrentePostaleTesoreria;
    }

    /**
     * Gets the value of the codiceFiscale property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getCodiceFiscale () {
        return codiceFiscale;
    }

    /**
     * Sets the value of the codiceFiscale property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setCodiceFiscale ( String value ) {
        codiceFiscale = value;
    }

    /**
     * Gets the value of the denominazione property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getDenominazione () {
        return denominazione;
    }

    /**
     * Sets the value of the denominazione property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setDenominazione ( String value ) {
        denominazione = value;
    }

    /**
     * Gets the value of the indirizzo property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getIndirizzo () {
        return indirizzo;
    }

    /**
     * Sets the value of the indirizzo property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setIndirizzo ( String value ) {
        indirizzo = value;
    }

    /**
     * Gets the value of the civico property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getCivico () {
        return civico;
    }

    /**
     * Sets the value of the civico property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setCivico ( String value ) {
        civico = value;
    }

    /**
     * Gets the value of the localita property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getLocalita () {
        return localita;
    }

    /**
     * Sets the value of the localita property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setLocalita ( String value ) {
        localita = value;
    }

    /**
     * Gets the value of the cap property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getCap () {
        return cap;
    }

    /**
     * Sets the value of the cap property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setCap ( String value ) {
        cap = value;
    }

    /**
     * Gets the value of the siglaProvincia property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getSiglaProvincia () {
        return siglaProvincia;
    }

    /**
     * Sets the value of the siglaProvincia property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setSiglaProvincia ( String value ) {
        siglaProvincia = value;
    }

    /**
     * Gets the value of the email property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getEmail () {
        return email;
    }

    /**
     * Sets the value of the email property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setEmail ( String value ) {
        email = value;
    }

    /**
     * Gets the value of the gs1Gln property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getGs1Gln () {
        return gs1Gln;
    }

    /**
     * Sets the value of the gs1Gln property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setGs1Gln ( String value ) {
        gs1Gln = value;
    }

    /**
     * Gets the value of the cbill property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getCbill () {
        return cbill;
    }

    /**
     * Sets the value of the cbill property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setCbill ( String value ) {
        cbill = value;
    }

    /**
     * Gets the value of the iban property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getIban () {
        return iban;
    }

    /**
     * Sets the value of the iban property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setIban ( String value ) {
        iban = value;
    }

    /**
     * Gets the value of the bic property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getBic () {
        return bic;
    }

    /**
     * Sets the value of the bic property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setBic ( String value ) {
        bic = value;
    }

    /**
     * Gets the value of the entePlurintermediato property.
     *
     */
    public boolean isEntePlurintermediato () {
        return entePlurintermediato;
    }

    /**
     * Sets the value of the entePlurintermediato property.
     *
     */
    public void setEntePlurintermediato ( boolean value ) {
        entePlurintermediato = value;
    }

    /**
     * Gets the value of the riconciliazioneVersamenti property.
     *
     */
    public boolean isRiconciliazioneVersamenti () {
        return riconciliazioneVersamenti;
    }

    /**
     * Sets the value of the riconciliazioneVersamenti property.
     *
     */
    public void setRiconciliazioneVersamenti ( boolean value ) {
        riconciliazioneVersamenti = value;
    }

    /**
     * Gets the value of the accertamento property.
     *
     */
    public boolean isAccertamento () {
        return accertamento;
    }

    /**
     * Sets the value of the accertamento property.
     *
     */
    public void setAccertamento ( boolean value ) {
        accertamento = value;
    }

    /**
     * Gets the value of the ricezioneErrori property.
     *
     */
    public boolean isRicezioneErrori () {
        return ricezioneErrori;
    }

    /**
     * Sets the value of the ricezioneErrori property.
     *
     */
    public void setRicezioneErrori ( boolean value ) {
        ricezioneErrori = value;
    }

    /**
     * Gets the value of the tipologiaAccertamento property.
     *
     * @return possible object is {@link TipologiaAccertamentoType }
     *
     */
    public TipologiaAccertamentoType getTipologiaAccertamento () {
        return tipologiaAccertamento;
    }

    /**
     * Sets the value of the tipologiaAccertamento property.
     *
     * @param value allowed object is {@link TipologiaAccertamentoType }
     *
     */
    public void setTipologiaAccertamento ( TipologiaAccertamentoType value ) {
        tipologiaAccertamento = value;
    }

    /**
     * Gets the value of the modalitaAcquisizioneProvvisori property.
     *
     * @return possible object is {@link ModalitaAcquisizioneProvvisoriType }
     *
     */
    public ModalitaAcquisizioneProvvisoriType getModalitaAcquisizioneProvvisori () {
        return modalitaAcquisizioneProvvisori;
    }

    /**
     * Sets the value of the modalitaAcquisizioneProvvisori property.
     *
     * @param value allowed object is {@link ModalitaAcquisizioneProvvisoriType }
     *
     */
    public void setModalitaAcquisizioneProvvisori ( ModalitaAcquisizioneProvvisoriType value ) {
        modalitaAcquisizioneProvvisori = value;
    }

    /**
     * Gets the value of the ricezioneFlussoBaseRendicontazione property.
     *
     */
    public boolean isRicezioneFlussoBaseRendicontazione () {
        return ricezioneFlussoBaseRendicontazione;
    }

    /**
     * Sets the value of the ricezioneFlussoBaseRendicontazione property.
     *
     */
    public void setRicezioneFlussoBaseRendicontazione ( boolean value ) {
        ricezioneFlussoBaseRendicontazione = value;
    }

    /**
     * Gets the value of the periodicitaSchedulazioneRiconciliazione property.
     *
     * @return possible object is {@link PeriodicitaSchedulazioneType }
     *
     */
    public PeriodicitaSchedulazioneType getPeriodicitaSchedulazioneRiconciliazione () {
        return periodicitaSchedulazioneRiconciliazione;
    }

    /**
     * Sets the value of the periodicitaSchedulazioneRiconciliazione property.
     *
     * @param value allowed object is {@link PeriodicitaSchedulazioneType }
     *
     */
    public void setPeriodicitaSchedulazioneRiconciliazione ( PeriodicitaSchedulazioneType value ) {
        periodicitaSchedulazioneRiconciliazione = value;
    }

    /**
     * Gets the value of the giornoSchedulazione property.
     *
     * @return possible object is {@link Integer }
     *
     */
    public Integer getGiornoSchedulazione () {
        return giornoSchedulazione;
    }

    /**
     * Sets the value of the giornoSchedulazione property.
     *
     * @param value allowed object is {@link Integer }
     *
     */
    public void setGiornoSchedulazione ( Integer value ) {
        giornoSchedulazione = value;
    }

    /**
     * Gets the value of the qualsiasiCodiceVersamento property.
     *
     */
    public boolean isQualsiasiCodiceVersamento () {
        return qualsiasiCodiceVersamento;
    }

    /**
     * Sets the value of the qualsiasiCodiceVersamento property.
     *
     */
    public void setQualsiasiCodiceVersamento ( boolean value ) {
        qualsiasiCodiceVersamento = value;
    }

    /**
     * Gets the value of the logo property.
     *
     * @return possible object is byte[]
     */
    public byte [] getLogo () {
        return logo;
    }

    /**
     * Sets the value of the logo property.
     *
     * @param value allowed object is byte[]
     */
    public void setLogo ( byte [] value ) {
        logo = value;
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

    public boolean isFattura () {
        return fattura;
    }

    public void setFattura ( boolean fattura ) {
        this.fattura = fattura;
    }

    public boolean isFlagGestionePpay () {
        return flagGestionePpay;
    }

    public void setFlagGestionePpay ( boolean flagGestionePpay ) {
        this.flagGestionePpay = flagGestionePpay;
    }

	public boolean isFlagAdesioneCittaFacile () {
		return flagAdesioneCittaFacile;
	}
	public String getCodiceIstat () {
		return codiceIstat;
	}

	public void setFlagAdesioneCittaFacile ( boolean flagAdesioneCittaFacile ) {
		this.flagAdesioneCittaFacile = flagAdesioneCittaFacile;
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
