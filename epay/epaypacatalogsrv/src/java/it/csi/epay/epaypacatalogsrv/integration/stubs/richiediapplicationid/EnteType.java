/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.integration.stubs.richiediapplicationid;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EnteType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
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
 *         &lt;element name="IbanTesoreria" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BicTesoreria" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IbanAppoggio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BicAppoggio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EnteType", propOrder = {
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
	"flagAdesioneCittaFacile",
	"codiceIstat",
	"templateEmailId",
    "urlDominio",
    "codiceIpa"
	
})
public class EnteType {

    @XmlElement(name = "CodiceFiscale", required = true)
    protected String codiceFiscale;
    @XmlElement(name = "Denominazione", required = true)
    protected String denominazione;
    @XmlElement(name = "Indirizzo", required = true)
    protected String indirizzo;
    @XmlElement(name = "Civico", required = true)
    protected String civico;
    @XmlElement(name = "Localita", required = true)
    protected String localita;
    @XmlElement(name = "Cap", required = true)
    protected String cap;
    @XmlElement(name = "SiglaProvincia", required = true)
    protected String siglaProvincia;
    @XmlElement(name = "Email")
    protected String email;
    @XmlElement(name = "Gs1Gln")
    protected String gs1Gln;
    @XmlElement(name = "Cbill")
    protected String cbill;
    @XmlElement(name = "Iban")
    protected String iban;
    @XmlElement(name = "Bic")
    protected String bic;
    @XmlElementRef(name = "IbanTesoreria", namespace = "http://www.csi.it/epay/epaywso/coopapplicativapec", type = JAXBElement.class, required = false)
    protected JAXBElement<String> ibanTesoreria;
    @XmlElementRef(name = "BicTesoreria", namespace = "http://www.csi.it/epay/epaywso/coopapplicativapec", type = JAXBElement.class, required = false)
    protected JAXBElement<String> bicTesoreria;
    @XmlElementRef(name = "IbanAppoggio", namespace = "http://www.csi.it/epay/epaywso/coopapplicativapec", type = JAXBElement.class, required = false)
    protected JAXBElement<String> ibanAppoggio;
    @XmlElementRef(name = "BicAppoggio", namespace = "http://www.csi.it/epay/epaywso/coopapplicativapec", type = JAXBElement.class, required = false)
    protected JAXBElement<String> bicAppoggio;
    @XmlElement(name = "EntePlurintermediato")
    protected boolean entePlurintermediato;
    @XmlElement(name = "RiconciliazioneVersamenti")
    protected boolean riconciliazioneVersamenti;
    @XmlElement(name = "Accertamento")
    protected boolean accertamento;
    @XmlElement(name = "RicezioneErrori")
    protected boolean ricezioneErrori;
    @XmlElement(name = "TipologiaAccertamento", required = true)
    protected TipologiaAccertamentoType tipologiaAccertamento;
    @XmlElement(name = "ModalitaAcquisizioneProvvisori", required = true)
    protected ModalitaAcquisizioneProvvisoriType modalitaAcquisizioneProvvisori;
    @XmlElement(name = "RicezioneFlussoBaseRendicontazione")
    protected boolean ricezioneFlussoBaseRendicontazione;
    @XmlElement(name = "PeriodicitaSchedulazioneRiconciliazione", required = true)
    protected PeriodicitaSchedulazioneType periodicitaSchedulazioneRiconciliazione;
    @XmlElement(name = "GiornoSchedulazione")
    protected Integer giornoSchedulazione;
    @XmlElement(name = "QualsiasiCodiceVersamento")
    protected boolean qualsiasiCodiceVersamento;
    @XmlElement(name = "Logo")
    protected byte[] logo;
    @XmlElement(name = "FlagPresenzaBollettinoPostale", required = true, type = Boolean.class, nillable = true)
    protected Boolean flagPresenzaBollettinoPostale;
    @XmlElement(name = "FlagCodiceCorrentePostaleAppoggio")
    protected boolean flagCodiceCorrentePostaleAppoggio;
    @XmlElement(name = "FlagCodiceCorrentePostaleTesoreria")
    protected boolean flagCodiceCorrentePostaleTesoreria;
    @XmlElement(name = "Fattura")
    protected boolean fattura;
	@XmlElement ( name = "codiceIstat" )
	protected String codiceIstat;
	@XmlElement ( name = "TemplateEmailId" )
	protected String templateEmailId;
	@XmlElement ( name = "UrlDominio" )
	protected String urlDominio;
    @XmlElement ( name = "codiceIpa" )
    protected String codiceIpa;
    

	@XmlElement ( name = "flagAdesioneCittaFacile" )
	protected boolean flagAdesioneCittaFacile;

    /**
     * Gets the value of the codiceFiscale property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    /**
     * Sets the value of the codiceFiscale property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceFiscale(String value) {
        this.codiceFiscale = value;
    }

    /**
     * Gets the value of the denominazione property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDenominazione() {
        return denominazione;
    }

    /**
     * Sets the value of the denominazione property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDenominazione(String value) {
        this.denominazione = value;
    }

    /**
     * Gets the value of the indirizzo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndirizzo() {
        return indirizzo;
    }

    /**
     * Sets the value of the indirizzo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndirizzo(String value) {
        this.indirizzo = value;
    }

    /**
     * Gets the value of the civico property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCivico() {
        return civico;
    }

    /**
     * Sets the value of the civico property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCivico(String value) {
        this.civico = value;
    }

    /**
     * Gets the value of the localita property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocalita() {
        return localita;
    }

    /**
     * Sets the value of the localita property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocalita(String value) {
        this.localita = value;
    }

    /**
     * Gets the value of the cap property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCap() {
        return cap;
    }

    /**
     * Sets the value of the cap property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCap(String value) {
        this.cap = value;
    }

    /**
     * Gets the value of the siglaProvincia property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSiglaProvincia() {
        return siglaProvincia;
    }

    /**
     * Sets the value of the siglaProvincia property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSiglaProvincia(String value) {
        this.siglaProvincia = value;
    }

    /**
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Gets the value of the gs1Gln property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGs1Gln() {
        return gs1Gln;
    }

    /**
     * Sets the value of the gs1Gln property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGs1Gln(String value) {
        this.gs1Gln = value;
    }

    /**
     * Gets the value of the cbill property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCbill() {
        return cbill;
    }

    /**
     * Sets the value of the cbill property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCbill(String value) {
        this.cbill = value;
    }

    /**
     * Gets the value of the iban property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIban() {
        return iban;
    }

    /**
     * Sets the value of the iban property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIban(String value) {
        this.iban = value;
    }

    /**
     * Gets the value of the bic property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBic() {
        return bic;
    }

    /**
     * Sets the value of the bic property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBic(String value) {
        this.bic = value;
    }

    /**
     * Gets the value of the ibanTesoreria property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIbanTesoreria() {
        return ibanTesoreria;
    }

    /**
     * Sets the value of the ibanTesoreria property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIbanTesoreria(JAXBElement<String> value) {
        this.ibanTesoreria = value;
    }

    /**
     * Gets the value of the bicTesoreria property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getBicTesoreria() {
        return bicTesoreria;
    }

    /**
     * Sets the value of the bicTesoreria property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setBicTesoreria(JAXBElement<String> value) {
        this.bicTesoreria = value;
    }

    /**
     * Gets the value of the ibanAppoggio property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIbanAppoggio() {
        return ibanAppoggio;
    }

    /**
     * Sets the value of the ibanAppoggio property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIbanAppoggio(JAXBElement<String> value) {
        this.ibanAppoggio = value;
    }

    /**
     * Gets the value of the bicAppoggio property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getBicAppoggio() {
        return bicAppoggio;
    }

    /**
     * Sets the value of the bicAppoggio property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setBicAppoggio(JAXBElement<String> value) {
        this.bicAppoggio = value;
    }

    /**
     * Gets the value of the entePlurintermediato property.
     * 
     */
    public boolean isEntePlurintermediato() {
        return entePlurintermediato;
    }

    /**
     * Sets the value of the entePlurintermediato property.
     * 
     */
    public void setEntePlurintermediato(boolean value) {
        this.entePlurintermediato = value;
    }

    /**
     * Gets the value of the riconciliazioneVersamenti property.
     * 
     */
    public boolean isRiconciliazioneVersamenti() {
        return riconciliazioneVersamenti;
    }

    /**
     * Sets the value of the riconciliazioneVersamenti property.
     * 
     */
    public void setRiconciliazioneVersamenti(boolean value) {
        this.riconciliazioneVersamenti = value;
    }

    /**
     * Gets the value of the accertamento property.
     * 
     */
    public boolean isAccertamento() {
        return accertamento;
    }

    /**
     * Sets the value of the accertamento property.
     * 
     */
    public void setAccertamento(boolean value) {
        this.accertamento = value;
    }

    /**
     * Gets the value of the ricezioneErrori property.
     * 
     */
    public boolean isRicezioneErrori() {
        return ricezioneErrori;
    }

    /**
     * Sets the value of the ricezioneErrori property.
     * 
     */
    public void setRicezioneErrori(boolean value) {
        this.ricezioneErrori = value;
    }

    /**
     * Gets the value of the tipologiaAccertamento property.
     * 
     * @return
     *     possible object is
     *     {@link TipologiaAccertamentoType }
     *     
     */
    public TipologiaAccertamentoType getTipologiaAccertamento() {
        return tipologiaAccertamento;
    }

    /**
     * Sets the value of the tipologiaAccertamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link TipologiaAccertamentoType }
     *     
     */
    public void setTipologiaAccertamento(TipologiaAccertamentoType value) {
        this.tipologiaAccertamento = value;
    }

    /**
     * Gets the value of the modalitaAcquisizioneProvvisori property.
     * 
     * @return
     *     possible object is
     *     {@link ModalitaAcquisizioneProvvisoriType }
     *     
     */
    public ModalitaAcquisizioneProvvisoriType getModalitaAcquisizioneProvvisori() {
        return modalitaAcquisizioneProvvisori;
    }

    /**
     * Sets the value of the modalitaAcquisizioneProvvisori property.
     * 
     * @param value
     *     allowed object is
     *     {@link ModalitaAcquisizioneProvvisoriType }
     *     
     */
    public void setModalitaAcquisizioneProvvisori(ModalitaAcquisizioneProvvisoriType value) {
        this.modalitaAcquisizioneProvvisori = value;
    }

    /**
     * Gets the value of the ricezioneFlussoBaseRendicontazione property.
     * 
     */
    public boolean isRicezioneFlussoBaseRendicontazione() {
        return ricezioneFlussoBaseRendicontazione;
    }

    /**
     * Sets the value of the ricezioneFlussoBaseRendicontazione property.
     * 
     */
    public void setRicezioneFlussoBaseRendicontazione(boolean value) {
        this.ricezioneFlussoBaseRendicontazione = value;
    }

    /**
     * Gets the value of the periodicitaSchedulazioneRiconciliazione property.
     * 
     * @return
     *     possible object is
     *     {@link PeriodicitaSchedulazioneType }
     *     
     */
    public PeriodicitaSchedulazioneType getPeriodicitaSchedulazioneRiconciliazione() {
        return periodicitaSchedulazioneRiconciliazione;
    }

    /**
     * Sets the value of the periodicitaSchedulazioneRiconciliazione property.
     * 
     * @param value
     *     allowed object is
     *     {@link PeriodicitaSchedulazioneType }
     *     
     */
    public void setPeriodicitaSchedulazioneRiconciliazione(PeriodicitaSchedulazioneType value) {
        this.periodicitaSchedulazioneRiconciliazione = value;
    }

    /**
     * Gets the value of the giornoSchedulazione property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getGiornoSchedulazione() {
        return giornoSchedulazione;
    }

    /**
     * Sets the value of the giornoSchedulazione property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setGiornoSchedulazione(Integer value) {
        this.giornoSchedulazione = value;
    }

    /**
     * Gets the value of the qualsiasiCodiceVersamento property.
     * 
     */
    public boolean isQualsiasiCodiceVersamento() {
        return qualsiasiCodiceVersamento;
    }

    /**
     * Sets the value of the qualsiasiCodiceVersamento property.
     * 
     */
    public void setQualsiasiCodiceVersamento(boolean value) {
        this.qualsiasiCodiceVersamento = value;
    }

    /**
     * Gets the value of the logo property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getLogo() {
        return logo;
    }

    /**
     * Sets the value of the logo property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setLogo(byte[] value) {
        this.logo = value;
    }

    /**
     * Gets the value of the flagPresenzaBollettinoPostale property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isFlagPresenzaBollettinoPostale() {
        return flagPresenzaBollettinoPostale;
    }

    /**
     * Sets the value of the flagPresenzaBollettinoPostale property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setFlagPresenzaBollettinoPostale(Boolean value) {
        this.flagPresenzaBollettinoPostale = value;
    }

    /**
     * Gets the value of the flagCodiceCorrentePostaleAppoggio property.
     * 
     */
    public boolean isFlagCodiceCorrentePostaleAppoggio() {
        return flagCodiceCorrentePostaleAppoggio;
    }

    /**
     * Sets the value of the flagCodiceCorrentePostaleAppoggio property.
     * 
     */
    public void setFlagCodiceCorrentePostaleAppoggio(boolean value) {
        this.flagCodiceCorrentePostaleAppoggio = value;
    }

    /**
     * Gets the value of the flagCodiceCorrentePostaleTesoreria property.
     * 
     */
    public boolean isFlagCodiceCorrentePostaleTesoreria() {
        return flagCodiceCorrentePostaleTesoreria;
    }

    /**
     * Sets the value of the flagCodiceCorrentePostaleTesoreria property.
     * 
     */
    public void setFlagCodiceCorrentePostaleTesoreria(boolean value) {
        this.flagCodiceCorrentePostaleTesoreria = value;
    }

    /**
     * Gets the value of the fattura property.
     * 
     */
    public boolean isFattura() {
        return fattura;
    }

    /**
     * Sets the value of the fattura property.
     * 
     */
    public void setFattura(boolean value) {
        this.fattura = value;
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
