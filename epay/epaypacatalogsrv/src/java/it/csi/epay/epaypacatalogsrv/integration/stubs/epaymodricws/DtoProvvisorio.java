/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.integration.stubs.epaymodricws;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="annoEsercizio" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="annoProvvisorio" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="causale" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="dataFine" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" minOccurs="0"/&gt;
 *         &lt;element name="dataMovimento" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" minOccurs="0"/&gt;
 *         &lt;element name="descrizione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="idEnte" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="identificativoFlusso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="importoDisponibilita" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="importoProvvisorio" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="numeroProvvisorio" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="stato" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="tipoMovimento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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

    protected Integer annoEsercizio;
    protected Integer annoProvvisorio;
    protected String causale;
    @XmlSchemaType(name = "anySimpleType")
    protected Object dataFine;
    @XmlSchemaType(name = "anySimpleType")
    protected Object dataMovimento;
    protected String descrizione;
    protected String idEnte;
    protected String identificativoFlusso;
    protected BigDecimal importoDisponibilita;
    protected BigDecimal importoProvvisorio;
    protected Integer numeroProvvisorio;
    protected String stato;
    protected String tipoMovimento;

    /**
     * Recupera il valore della proprieta annoEsercizio.
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
     * Imposta il valore della proprieta annoEsercizio.
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
     * Recupera il valore della proprieta annoProvvisorio.
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
     * Imposta il valore della proprieta annoProvvisorio.
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
     * Recupera il valore della proprieta causale.
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
     * Imposta il valore della proprieta causale.
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
     * Recupera il valore della proprieta dataFine.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getDataFine() {
        return dataFine;
    }

    /**
     * Imposta il valore della proprieta dataFine.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setDataFine(Object value) {
        this.dataFine = value;
    }

    /**
     * Recupera il valore della proprieta dataMovimento.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getDataMovimento() {
        return dataMovimento;
    }

    /**
     * Imposta il valore della proprieta dataMovimento.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setDataMovimento(Object value) {
        this.dataMovimento = value;
    }

    /**
     * Recupera il valore della proprieta descrizione.
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
     * Imposta il valore della proprieta descrizione.
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
     * Recupera il valore della proprieta idEnte.
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
     * Imposta il valore della proprieta idEnte.
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
     * Recupera il valore della proprieta identificativoFlusso.
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
     * Imposta il valore della proprieta identificativoFlusso.
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
     * Recupera il valore della proprieta importoDisponibilita.
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
     * Imposta il valore della proprieta importoDisponibilita.
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
     * Recupera il valore della proprieta importoProvvisorio.
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
     * Imposta il valore della proprieta importoProvvisorio.
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
     * Recupera il valore della proprieta numeroProvvisorio.
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
     * Imposta il valore della proprieta numeroProvvisorio.
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
     * Recupera il valore della proprieta stato.
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
     * Imposta il valore della proprieta stato.
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
     * Recupera il valore della proprieta tipoMovimento.
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
     * Imposta il valore della proprieta tipoMovimento.
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
