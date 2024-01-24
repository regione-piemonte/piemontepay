/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysimweb.integration.stubs.epaypacatalogsrv;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per getEnteOutputDto complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="getEnteOutputDto"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="bic" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="cap" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="cbill" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="civico" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="codiceFiscale" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="codiceModalitaAcquisizioneProvvisori" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="codicePeriodicitaSchedulazioneRiconciliazione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="codiceStatoAggiornamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="codiceTipologiaAccertamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="codiciVersamentoAssociati" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}getEnteOutputCodiceVersamentoAssociatoDto" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="denominazione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="descrizioneErroreAggiornamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="descrizioneStatoAggiornamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="descrizioneUtenteAmministratore" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="flagAccertamento" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="flagEntePlurintermediato" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="flagQualsiasiCodiceVersamento" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="flagRicezioneErrori" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="flagRicezioneFlussoBaseRendicontazione" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="flagRicezioneFlussoRendicontazione" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="flagRiconciliazioneVersamenti" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="giornoSchedulazione" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="gs1Gln" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="iban" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="idUtenteAmministratore" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="indirizzo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="localita" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="logoContent" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/&gt;
 *         &lt;element name="logoContentType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="logoFileName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="logoFileSize" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="siglaProvincia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
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
     * Recupera il valore della propriet bic.
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
     * Imposta il valore della propriet bic.
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
     * Recupera il valore della propriet cap.
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
     * Imposta il valore della propriet cap.
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
     * Recupera il valore della propriet cbill.
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
     * Imposta il valore della propriet cbill.
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
     * Recupera il valore della propriet civico.
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
     * Imposta il valore della propriet civico.
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
     * Recupera il valore della propriet codiceFiscale.
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
     * Imposta il valore della propriet codiceFiscale.
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
     * Recupera il valore della propriet codiceModalitaAcquisizioneProvvisori.
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
     * Imposta il valore della propriet codiceModalitaAcquisizioneProvvisori.
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
     * Recupera il valore della propriet codicePeriodicitaSchedulazioneRiconciliazione.
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
     * Imposta il valore della propriet codicePeriodicitaSchedulazioneRiconciliazione.
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
     * Recupera il valore della propriet codiceStatoAggiornamento.
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
     * Imposta il valore della propriet codiceStatoAggiornamento.
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
     * Recupera il valore della propriet codiceTipologiaAccertamento.
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
     * Imposta il valore della propriet codiceTipologiaAccertamento.
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
     * Recupera il valore della propriet denominazione.
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
     * Imposta il valore della propriet denominazione.
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
     * Recupera il valore della propriet descrizioneErroreAggiornamento.
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
     * Imposta il valore della propriet descrizioneErroreAggiornamento.
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
     * Recupera il valore della propriet descrizioneStatoAggiornamento.
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
     * Imposta il valore della propriet descrizioneStatoAggiornamento.
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
     * Recupera il valore della propriet descrizioneUtenteAmministratore.
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
     * Imposta il valore della propriet descrizioneUtenteAmministratore.
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
     * Recupera il valore della propriet email.
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
     * Imposta il valore della propriet email.
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
     * Recupera il valore della propriet flagAccertamento.
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
     * Imposta il valore della propriet flagAccertamento.
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
     * Recupera il valore della propriet flagEntePlurintermediato.
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
     * Imposta il valore della propriet flagEntePlurintermediato.
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
     * Recupera il valore della propriet flagQualsiasiCodiceVersamento.
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
     * Imposta il valore della propriet flagQualsiasiCodiceVersamento.
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
     * Recupera il valore della propriet flagRicezioneErrori.
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
     * Imposta il valore della propriet flagRicezioneErrori.
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
     * Recupera il valore della propriet flagRicezioneFlussoBaseRendicontazione.
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
     * Imposta il valore della propriet flagRicezioneFlussoBaseRendicontazione.
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
     * Recupera il valore della propriet flagRicezioneFlussoRendicontazione.
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
     * Imposta il valore della propriet flagRicezioneFlussoRendicontazione.
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
     * Recupera il valore della propriet flagRiconciliazioneVersamenti.
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
     * Imposta il valore della propriet flagRiconciliazioneVersamenti.
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
     * Recupera il valore della propriet giornoSchedulazione.
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
     * Imposta il valore della propriet giornoSchedulazione.
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
     * Recupera il valore della propriet gs1Gln.
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
     * Imposta il valore della propriet gs1Gln.
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
     * Recupera il valore della propriet iban.
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
     * Imposta il valore della propriet iban.
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
     * Recupera il valore della propriet id.
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
     * Imposta il valore della propriet id.
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
     * Recupera il valore della propriet idUtenteAmministratore.
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
     * Imposta il valore della propriet idUtenteAmministratore.
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
     * Recupera il valore della propriet indirizzo.
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
     * Imposta il valore della propriet indirizzo.
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
     * Recupera il valore della propriet localita.
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
     * Imposta il valore della propriet localita.
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
     * Recupera il valore della propriet logoContent.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getLogoContent() {
        return logoContent;
    }

    /**
     * Imposta il valore della propriet logoContent.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setLogoContent(byte[] value) {
        this.logoContent = value;
    }

    /**
     * Recupera il valore della propriet logoContentType.
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
     * Imposta il valore della propriet logoContentType.
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
     * Recupera il valore della propriet logoFileName.
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
     * Imposta il valore della propriet logoFileName.
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
     * Recupera il valore della propriet logoFileSize.
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
     * Imposta il valore della propriet logoFileSize.
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
     * Recupera il valore della propriet siglaProvincia.
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
     * Imposta il valore della propriet siglaProvincia.
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
