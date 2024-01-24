/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysimweb.integration.stubs.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java per aggiornaRiferimentoContabileInput complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="aggiornaRiferimentoContabileInput"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://interfacews.epaypacatalogsrv.epay.csi.it/}parentInput"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="annoAccertamento" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="categoria" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="dataFineValidita" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="livelloPdc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="numeroAccertamento" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="numeroArticolo" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="numeroCapitolo" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="tipologia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="titolo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "aggiornaRiferimentoContabileInput", propOrder = {
    "annoAccertamento",
    "categoria",
    "dataFineValidita",
    "id",
    "livelloPdc",
    "numeroAccertamento",
    "numeroArticolo",
    "numeroCapitolo",
    "tipologia",
    "titolo"
})
public class AggiornaRiferimentoContabileInput
    extends ParentInput
{

    protected Integer annoAccertamento;
    protected String categoria;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataFineValidita;
    protected Long id;
    protected String livelloPdc;
    protected Integer numeroAccertamento;
    protected Integer numeroArticolo;
    protected Integer numeroCapitolo;
    protected String tipologia;
    protected String titolo;

    /**
     * Recupera il valore della propriet annoAccertamento.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAnnoAccertamento() {
        return annoAccertamento;
    }

    /**
     * Imposta il valore della propriet annoAccertamento.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAnnoAccertamento(Integer value) {
        this.annoAccertamento = value;
    }

    /**
     * Recupera il valore della propriet categoria.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * Imposta il valore della propriet categoria.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategoria(String value) {
        this.categoria = value;
    }

    /**
     * Recupera il valore della propriet dataFineValidita.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataFineValidita() {
        return dataFineValidita;
    }

    /**
     * Imposta il valore della propriet dataFineValidita.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataFineValidita(XMLGregorianCalendar value) {
        this.dataFineValidita = value;
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
     * Recupera il valore della propriet livelloPdc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLivelloPdc() {
        return livelloPdc;
    }

    /**
     * Imposta il valore della propriet livelloPdc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLivelloPdc(String value) {
        this.livelloPdc = value;
    }

    /**
     * Recupera il valore della propriet numeroAccertamento.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumeroAccertamento() {
        return numeroAccertamento;
    }

    /**
     * Imposta il valore della propriet numeroAccertamento.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumeroAccertamento(Integer value) {
        this.numeroAccertamento = value;
    }

    /**
     * Recupera il valore della propriet numeroArticolo.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumeroArticolo() {
        return numeroArticolo;
    }

    /**
     * Imposta il valore della propriet numeroArticolo.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumeroArticolo(Integer value) {
        this.numeroArticolo = value;
    }

    /**
     * Recupera il valore della propriet numeroCapitolo.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumeroCapitolo() {
        return numeroCapitolo;
    }

    /**
     * Imposta il valore della propriet numeroCapitolo.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumeroCapitolo(Integer value) {
        this.numeroCapitolo = value;
    }

    /**
     * Recupera il valore della propriet tipologia.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipologia() {
        return tipologia;
    }

    /**
     * Imposta il valore della propriet tipologia.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipologia(String value) {
        this.tipologia = value;
    }

    /**
     * Recupera il valore della propriet titolo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitolo() {
        return titolo;
    }

    /**
     * Imposta il valore della propriet titolo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitolo(String value) {
        this.titolo = value;
    }

}
