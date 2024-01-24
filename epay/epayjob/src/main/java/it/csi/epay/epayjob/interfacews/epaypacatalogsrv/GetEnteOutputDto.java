/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayjob.interfacews.epaypacatalogsrv;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getEnteOutputDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getEnteOutputDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="bic" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cap" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cbill" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="civico" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codiceFiscale" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codiceModalitaAcquisizioneProvvisori" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codiceModalitaIntegrazione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codicePeriodicitaSchedulazioneRiconciliazione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codiceStatoAggiornamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codiceTipologiaAccertamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codiciVersamentoAssociati" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}getEnteOutputCodiceVersamentoAssociatoDto" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="denominazione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descrizioneErroreAggiornamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descrizioneStatoAggiornamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descrizioneUtenteAmministratore" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="flagAccertamento" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="flagEntePlurintermediato" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="flagQualsiasiCodiceVersamento" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="flagRicezioneErrori" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="flagRicezioneFlussoBaseRendicontazione" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="flagRicezioneFlussoRendicontazione" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="flagRiconciliazioneVersamenti" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="giornoSchedulazione" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="gs1Gln" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iban" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="idUtenteAmministratore" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="indirizzo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="localita" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="logoContent" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="logoContentType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="logoFileName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="logoFileSize" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="siglaProvincia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getEnteOutputDto", propOrder = {
    "bic",
    "cap",
    "cbill",
    "civico",
    "codiceFiscale",
    "codiceModalitaAcquisizioneProvvisori",
    "codiceModalitaIntegrazione",
    "codicePeriodicitaSchedulazioneRiconciliazione",
    "codiceStatoAggiornamento",
    "codiceTipologiaAccertamento",
    "codiciVersamentoAssociati",
    "denominazione",
    "descrizioneErroreAggiornamento",
    "descrizioneStatoAggiornamento",
    "descrizioneUtenteAmministratore",
    "email",
    "flagAccertamento",
    "flagEntePlurintermediato",
    "flagQualsiasiCodiceVersamento",
    "flagRicezioneErrori",
    "flagRicezioneFlussoBaseRendicontazione",
    "flagRicezioneFlussoRendicontazione",
    "flagRiconciliazioneVersamenti",
    "giornoSchedulazione",
    "gs1Gln",
    "iban",
    "id",
    "idUtenteAmministratore",
    "indirizzo",
    "localita",
    "logoContent",
    "logoContentType",
    "logoFileName",
    "logoFileSize",
    "siglaProvincia"
})
public class GetEnteOutputDto {

    protected String bic;
    protected String cap;
    protected String cbill;
    protected String civico;
    protected String codiceFiscale;
    protected String codiceModalitaAcquisizioneProvvisori;
    protected String codiceModalitaIntegrazione;
    protected String codicePeriodicitaSchedulazioneRiconciliazione;
    protected String codiceStatoAggiornamento;
    protected String codiceTipologiaAccertamento;
    @XmlElement(nillable = true)
    protected List<GetEnteOutputCodiceVersamentoAssociatoDto> codiciVersamentoAssociati;
    protected String denominazione;
    protected String descrizioneErroreAggiornamento;
    protected String descrizioneStatoAggiornamento;
    protected String descrizioneUtenteAmministratore;
    protected String email;
    protected Boolean flagAccertamento;
    protected Boolean flagEntePlurintermediato;
    protected Boolean flagQualsiasiCodiceVersamento;
    protected Boolean flagRicezioneErrori;
    protected Boolean flagRicezioneFlussoBaseRendicontazione;
    protected Boolean flagRicezioneFlussoRendicontazione;
    protected Boolean flagRiconciliazioneVersamenti;
    protected Integer giornoSchedulazione;
    protected String gs1Gln;
    protected String iban;
    protected Long id;
    protected Long idUtenteAmministratore;
    protected String indirizzo;
    protected String localita;
    protected byte[] logoContent;
    protected String logoContentType;
    protected String logoFileName;
    protected Integer logoFileSize;
    protected String siglaProvincia;

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
     * Gets the value of the codiceModalitaAcquisizioneProvvisori property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceModalitaAcquisizioneProvvisori() {
        return codiceModalitaAcquisizioneProvvisori;
    }

    /**
     * Sets the value of the codiceModalitaAcquisizioneProvvisori property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceModalitaAcquisizioneProvvisori(String value) {
        this.codiceModalitaAcquisizioneProvvisori = value;
    }

    /**
     * Gets the value of the codiceModalitaIntegrazione property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceModalitaIntegrazione() {
        return codiceModalitaIntegrazione;
    }

    /**
     * Sets the value of the codiceModalitaIntegrazione property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceModalitaIntegrazione(String value) {
        this.codiceModalitaIntegrazione = value;
    }

    /**
     * Gets the value of the codicePeriodicitaSchedulazioneRiconciliazione property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodicePeriodicitaSchedulazioneRiconciliazione() {
        return codicePeriodicitaSchedulazioneRiconciliazione;
    }

    /**
     * Sets the value of the codicePeriodicitaSchedulazioneRiconciliazione property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodicePeriodicitaSchedulazioneRiconciliazione(String value) {
        this.codicePeriodicitaSchedulazioneRiconciliazione = value;
    }

    /**
     * Gets the value of the codiceStatoAggiornamento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceStatoAggiornamento() {
        return codiceStatoAggiornamento;
    }

    /**
     * Sets the value of the codiceStatoAggiornamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceStatoAggiornamento(String value) {
        this.codiceStatoAggiornamento = value;
    }

    /**
     * Gets the value of the codiceTipologiaAccertamento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceTipologiaAccertamento() {
        return codiceTipologiaAccertamento;
    }

    /**
     * Sets the value of the codiceTipologiaAccertamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceTipologiaAccertamento(String value) {
        this.codiceTipologiaAccertamento = value;
    }

    /**
     * Gets the value of the codiciVersamentoAssociati property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the codiciVersamentoAssociati property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCodiciVersamentoAssociati().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GetEnteOutputCodiceVersamentoAssociatoDto }
     * 
     * 
     */
    public List<GetEnteOutputCodiceVersamentoAssociatoDto> getCodiciVersamentoAssociati() {
        if (codiciVersamentoAssociati == null) {
            codiciVersamentoAssociati = new ArrayList<GetEnteOutputCodiceVersamentoAssociatoDto>();
        }
        return this.codiciVersamentoAssociati;
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
     * Gets the value of the descrizioneErroreAggiornamento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizioneErroreAggiornamento() {
        return descrizioneErroreAggiornamento;
    }

    /**
     * Sets the value of the descrizioneErroreAggiornamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizioneErroreAggiornamento(String value) {
        this.descrizioneErroreAggiornamento = value;
    }

    /**
     * Gets the value of the descrizioneStatoAggiornamento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizioneStatoAggiornamento() {
        return descrizioneStatoAggiornamento;
    }

    /**
     * Sets the value of the descrizioneStatoAggiornamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizioneStatoAggiornamento(String value) {
        this.descrizioneStatoAggiornamento = value;
    }

    /**
     * Gets the value of the descrizioneUtenteAmministratore property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizioneUtenteAmministratore() {
        return descrizioneUtenteAmministratore;
    }

    /**
     * Sets the value of the descrizioneUtenteAmministratore property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizioneUtenteAmministratore(String value) {
        this.descrizioneUtenteAmministratore = value;
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
     * Gets the value of the flagAccertamento property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isFlagAccertamento() {
        return flagAccertamento;
    }

    /**
     * Sets the value of the flagAccertamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setFlagAccertamento(Boolean value) {
        this.flagAccertamento = value;
    }

    /**
     * Gets the value of the flagEntePlurintermediato property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isFlagEntePlurintermediato() {
        return flagEntePlurintermediato;
    }

    /**
     * Sets the value of the flagEntePlurintermediato property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setFlagEntePlurintermediato(Boolean value) {
        this.flagEntePlurintermediato = value;
    }

    /**
     * Gets the value of the flagQualsiasiCodiceVersamento property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isFlagQualsiasiCodiceVersamento() {
        return flagQualsiasiCodiceVersamento;
    }

    /**
     * Sets the value of the flagQualsiasiCodiceVersamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setFlagQualsiasiCodiceVersamento(Boolean value) {
        this.flagQualsiasiCodiceVersamento = value;
    }

    /**
     * Gets the value of the flagRicezioneErrori property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isFlagRicezioneErrori() {
        return flagRicezioneErrori;
    }

    /**
     * Sets the value of the flagRicezioneErrori property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setFlagRicezioneErrori(Boolean value) {
        this.flagRicezioneErrori = value;
    }

    /**
     * Gets the value of the flagRicezioneFlussoBaseRendicontazione property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isFlagRicezioneFlussoBaseRendicontazione() {
        return flagRicezioneFlussoBaseRendicontazione;
    }

    /**
     * Sets the value of the flagRicezioneFlussoBaseRendicontazione property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setFlagRicezioneFlussoBaseRendicontazione(Boolean value) {
        this.flagRicezioneFlussoBaseRendicontazione = value;
    }

    /**
     * Gets the value of the flagRicezioneFlussoRendicontazione property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isFlagRicezioneFlussoRendicontazione() {
        return flagRicezioneFlussoRendicontazione;
    }

    /**
     * Sets the value of the flagRicezioneFlussoRendicontazione property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setFlagRicezioneFlussoRendicontazione(Boolean value) {
        this.flagRicezioneFlussoRendicontazione = value;
    }

    /**
     * Gets the value of the flagRiconciliazioneVersamenti property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isFlagRiconciliazioneVersamenti() {
        return flagRiconciliazioneVersamenti;
    }

    /**
     * Sets the value of the flagRiconciliazioneVersamenti property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setFlagRiconciliazioneVersamenti(Boolean value) {
        this.flagRiconciliazioneVersamenti = value;
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
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setId(Long value) {
        this.id = value;
    }

    /**
     * Gets the value of the idUtenteAmministratore property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdUtenteAmministratore() {
        return idUtenteAmministratore;
    }

    /**
     * Sets the value of the idUtenteAmministratore property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdUtenteAmministratore(Long value) {
        this.idUtenteAmministratore = value;
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
     * Gets the value of the logoContent property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getLogoContent() {
        return logoContent;
    }

    /**
     * Sets the value of the logoContent property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setLogoContent(byte[] value) {
        this.logoContent = value;
    }

    /**
     * Gets the value of the logoContentType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLogoContentType() {
        return logoContentType;
    }

    /**
     * Sets the value of the logoContentType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLogoContentType(String value) {
        this.logoContentType = value;
    }

    /**
     * Gets the value of the logoFileName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLogoFileName() {
        return logoFileName;
    }

    /**
     * Sets the value of the logoFileName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLogoFileName(String value) {
        this.logoFileName = value;
    }

    /**
     * Gets the value of the logoFileSize property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getLogoFileSize() {
        return logoFileSize;
    }

    /**
     * Sets the value of the logoFileSize property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setLogoFileSize(Integer value) {
        this.logoFileSize = value;
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

}
