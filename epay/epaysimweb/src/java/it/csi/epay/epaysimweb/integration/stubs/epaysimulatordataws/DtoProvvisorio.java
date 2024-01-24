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
 * <p>Classe Java per dtoProvvisorio complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="dtoProvvisorio"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="annoEsercizio" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="annoProvvisorio" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="causale" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="descrizione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="dataMovimento" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="idEnte" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="identificativoFlusso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="importoProvvisorio" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="numeroProvvisorio" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="stato" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
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
    "descrizione",
    "dataMovimento",
    "idEnte",
    "identificativoFlusso",
    "importoProvvisorio",
    "numeroProvvisorio",
    "stato"
})
public class DtoProvvisorio {

    protected Long id;
    protected Integer annoEsercizio;
    protected Integer annoProvvisorio;
    protected String causale;
    protected String descrizione;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataMovimento;
    protected String idEnte;
    protected String identificativoFlusso;
    protected BigDecimal importoProvvisorio;
    protected Integer numeroProvvisorio;
    protected String stato;

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
     * Recupera il valore della propriet descrizione.
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
     * Imposta il valore della propriet descrizione.
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
     * Recupera il valore della propriet idEnte.
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
     * Imposta il valore della propriet idEnte.
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

}
