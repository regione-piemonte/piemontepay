/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodricweb.integration.stubs.epaymodricws;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for dtoProvvisorio complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dtoProvvisorio">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="annoEsercizio" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="annoProvvisorio" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="causale" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataFine" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataMovimento" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="descrizione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idEnte" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="identificativoFlusso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="importoDisponibilita" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="importoProvvisorio" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="numeroProvvisorio" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="stato" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoMovimento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtoProvvisorio", propOrder = {
    "id",
    "annoEsercizio",
    "annoProvvisorio",
    "causale",
    "dataFine",
    "dataMovimento",
    "descrizione",
    "idEnte",
    "identificativoFlusso",
    "importoDisponibilita",
    "importoProvvisorio",
    "numeroProvvisorio",
    "stato",
    "tipoMovimento"
})
public class DtoProvvisorio {

    protected Long id;
    protected Integer annoEsercizio;
    protected Integer annoProvvisorio;
    protected String causale;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataFine;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataMovimento;
    protected String descrizione;
    protected String idEnte;
    protected String identificativoFlusso;
    protected BigDecimal importoDisponibilita;
    protected BigDecimal importoProvvisorio;
    protected Integer numeroProvvisorio;
    protected String stato;
    protected String tipoMovimento;

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
     * Gets the value of the annoEsercizio property.
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
     * Sets the value of the annoEsercizio property.
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
     * Gets the value of the annoProvvisorio property.
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
     * Sets the value of the annoProvvisorio property.
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
     * Gets the value of the causale property.
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
     * Sets the value of the causale property.
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
     * Gets the value of the dataFine property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataFine() {
        return dataFine;
    }

    /**
     * Sets the value of the dataFine property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataFine(XMLGregorianCalendar value) {
        this.dataFine = value;
    }

    /**
     * Gets the value of the dataMovimento property.
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
     * Sets the value of the dataMovimento property.
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
     * Gets the value of the descrizione property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizione() {
        return descrizione;
    }

    /**
     * Sets the value of the descrizione property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizione(String value) {
        this.descrizione = value;
    }

    /**
     * Gets the value of the idEnte property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdEnte() {
        return idEnte;
    }

    /**
     * Sets the value of the idEnte property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdEnte(String value) {
        this.idEnte = value;
    }

    /**
     * Gets the value of the identificativoFlusso property.
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
     * Sets the value of the identificativoFlusso property.
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
     * Gets the value of the importoDisponibilita property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getImportoDisponibilita() {
        return importoDisponibilita;
    }

    /**
     * Sets the value of the importoDisponibilita property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setImportoDisponibilita(BigDecimal value) {
        this.importoDisponibilita = value;
    }

    /**
     * Gets the value of the importoProvvisorio property.
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
     * Sets the value of the importoProvvisorio property.
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
     * Gets the value of the numeroProvvisorio property.
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
     * Sets the value of the numeroProvvisorio property.
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
     * Gets the value of the stato property.
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
     * Sets the value of the stato property.
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
     * Gets the value of the tipoMovimento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoMovimento() {
        return tipoMovimento;
    }

    /**
     * Sets the value of the tipoMovimento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoMovimento(String value) {
        this.tipoMovimento = value;
    }

}
