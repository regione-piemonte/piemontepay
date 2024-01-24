/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java per provvisorioDTO complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="provvisorioDTO"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="annoEsercizio" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="annoProvvisorio" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="causale" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="codiceFiscaleEnte" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="utenteInsVar" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="dataInsVar" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="dataMovimento" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="identificativoFlusso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="importoProvvisorio" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="numeroProvvisorio" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="stato" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="idGiornaleDiCassa" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="utenteModifica" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="dataModifica" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "provvisorioDTO", propOrder = {
    "annoEsercizio",
    "annoProvvisorio",
    "causale",
    "codiceFiscaleEnte",
    "utenteInsVar",
    "dataInsVar",
    "dataMovimento",
    "id",
    "identificativoFlusso",
    "importoProvvisorio",
    "numeroProvvisorio",
    "stato",
    "idGiornaleDiCassa",
    "utenteModifica",
    "dataModifica"
})
public class ProvvisorioDTO {

    protected Integer annoEsercizio;
    protected Integer annoProvvisorio;
    protected String causale;
    protected String codiceFiscaleEnte;
    protected String utenteInsVar;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataInsVar;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataMovimento;
    protected Long id;
    protected String identificativoFlusso;
    protected BigDecimal importoProvvisorio;
    protected Integer numeroProvvisorio;
    protected String stato;
    protected Integer idGiornaleDiCassa;
    protected String utenteModifica;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataModifica;

    /**
     * Recupera il valore della propriet annoEsercizio.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAnnoEsercizio() {
        return annoEsercizio;
    }

    /**
     * Imposta il valore della propriet annoEsercizio.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAnnoEsercizio(Integer value) {
        this.annoEsercizio = value;
    }

    /**
     * Recupera il valore della propriet annoProvvisorio.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAnnoProvvisorio() {
        return annoProvvisorio;
    }

    /**
     * Imposta il valore della propriet annoProvvisorio.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAnnoProvvisorio(Integer value) {
        this.annoProvvisorio = value;
    }

    /**
     * Recupera il valore della propriet causale.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCausale() {
        return causale;
    }

    /**
     * Imposta il valore della propriet causale.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCausale(String value) {
        this.causale = value;
    }

    /**
     * Recupera il valore della propriet codiceFiscaleEnte.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceFiscaleEnte() {
        return codiceFiscaleEnte;
    }

    /**
     * Imposta il valore della propriet codiceFiscaleEnte.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceFiscaleEnte(String value) {
        this.codiceFiscaleEnte = value;
    }

    /**
     * Recupera il valore della propriet utenteInsVar.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUtenteInsVar() {
        return utenteInsVar;
    }

    /**
     * Imposta il valore della propriet utenteInsVar.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUtenteInsVar(String value) {
        this.utenteInsVar = value;
    }

    /**
     * Recupera il valore della propriet dataInsVar.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataInsVar() {
        return dataInsVar;
    }

    /**
     * Imposta il valore della propriet dataInsVar.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataInsVar(XMLGregorianCalendar value) {
        this.dataInsVar = value;
    }

    /**
     * Recupera il valore della propriet dataMovimento.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataMovimento() {
        return dataMovimento;
    }

    /**
     * Imposta il valore della propriet dataMovimento.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataMovimento(XMLGregorianCalendar value) {
        this.dataMovimento = value;
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
     * Recupera il valore della propriet identificativoFlusso.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentificativoFlusso() {
        return identificativoFlusso;
    }

    /**
     * Imposta il valore della propriet identificativoFlusso.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentificativoFlusso(String value) {
        this.identificativoFlusso = value;
    }

    /**
     * Recupera il valore della propriet importoProvvisorio.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getImportoProvvisorio() {
        return importoProvvisorio;
    }

    /**
     * Imposta il valore della propriet importoProvvisorio.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setImportoProvvisorio(BigDecimal value) {
        this.importoProvvisorio = value;
    }

    /**
     * Recupera il valore della propriet numeroProvvisorio.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumeroProvvisorio() {
        return numeroProvvisorio;
    }

    /**
     * Imposta il valore della propriet numeroProvvisorio.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumeroProvvisorio(Integer value) {
        this.numeroProvvisorio = value;
    }

    /**
     * Recupera il valore della propriet stato.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStato() {
        return stato;
    }

    /**
     * Imposta il valore della propriet stato.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStato(String value) {
        this.stato = value;
    }

    /**
     * Recupera il valore della propriet idGiornaleDiCassa.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIdGiornaleDiCassa() {
        return idGiornaleDiCassa;
    }

    /**
     * Imposta il valore della propriet idGiornaleDiCassa.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIdGiornaleDiCassa(Integer value) {
        this.idGiornaleDiCassa = value;
    }

    /**
     * Recupera il valore della propriet utenteModifica.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUtenteModifica() {
        return utenteModifica;
    }

    /**
     * Imposta il valore della propriet utenteModifica.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUtenteModifica(String value) {
        this.utenteModifica = value;
    }

    /**
     * Recupera il valore della propriet dataModifica.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataModifica() {
        return dataModifica;
    }

    /**
     * Imposta il valore della propriet dataModifica.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataModifica(XMLGregorianCalendar value) {
        this.dataModifica = value;
    }

}
