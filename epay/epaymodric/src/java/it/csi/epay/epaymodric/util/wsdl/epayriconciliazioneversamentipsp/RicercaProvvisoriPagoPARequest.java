/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.util.wsdl.epayriconciliazioneversamentipsp;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CFEnteCreditore" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ElencoCausaliVersamenti" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="CausaleVersamento" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="AnnoEsercizio" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="DataProvvisorioDal" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="DataProvvisorioAl" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="NumeroProvvisorioDal" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="NumeroProvvisorioAl" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="Stato" type="{http://www.csi.it/epay/epaywso/riconciliazione-versamenti-psp/types}StatoProvvisorioType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "cfEnteCreditore",
    "elencoCausaliVersamenti",
    "annoEsercizio",
    "dataProvvisorioDal",
    "dataProvvisorioAl",
    "numeroProvvisorioDal",
    "numeroProvvisorioAl",
    "stato"
})
@XmlRootElement(name = "RicercaProvvisoriPagoPARequest", namespace = "http://www.csi.it/epay/epaywso/riconciliazione-versamenti-psp/types")
public class RicercaProvvisoriPagoPARequest {

    @XmlElement(name = "CFEnteCreditore", namespace = "http://www.csi.it/epay/epaywso/riconciliazione-versamenti-psp/types", required = true)
    protected String cfEnteCreditore;
    @XmlElement(name = "ElencoCausaliVersamenti", namespace = "http://www.csi.it/epay/epaywso/riconciliazione-versamenti-psp/types")
    protected RicercaProvvisoriPagoPARequest.ElencoCausaliVersamenti elencoCausaliVersamenti;
    @XmlElement(name = "AnnoEsercizio", namespace = "http://www.csi.it/epay/epaywso/riconciliazione-versamenti-psp/types")
    protected int annoEsercizio;
    @XmlElement(name = "DataProvvisorioDal", namespace = "http://www.csi.it/epay/epaywso/riconciliazione-versamenti-psp/types")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataProvvisorioDal;
    @XmlElement(name = "DataProvvisorioAl", namespace = "http://www.csi.it/epay/epaywso/riconciliazione-versamenti-psp/types")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataProvvisorioAl;
    @XmlElement(name = "NumeroProvvisorioDal", namespace = "http://www.csi.it/epay/epaywso/riconciliazione-versamenti-psp/types")
    protected BigInteger numeroProvvisorioDal;
    @XmlElement(name = "NumeroProvvisorioAl", namespace = "http://www.csi.it/epay/epaywso/riconciliazione-versamenti-psp/types")
    protected BigInteger numeroProvvisorioAl;
    @XmlElement(name = "Stato", namespace = "http://www.csi.it/epay/epaywso/riconciliazione-versamenti-psp/types")
    protected StatoProvvisorioType stato;

    /**
     * Gets the value of the cfEnteCreditore property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCFEnteCreditore() {
        return cfEnteCreditore;
    }

    /**
     * Sets the value of the cfEnteCreditore property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCFEnteCreditore(String value) {
        this.cfEnteCreditore = value;
    }

    /**
     * Gets the value of the elencoCausaliVersamenti property.
     * 
     * @return
     *     possible object is
     *     {@link RicercaProvvisoriPagoPARequest.ElencoCausaliVersamenti }
     *     
     */
    public RicercaProvvisoriPagoPARequest.ElencoCausaliVersamenti getElencoCausaliVersamenti() {
        return elencoCausaliVersamenti;
    }

    /**
     * Sets the value of the elencoCausaliVersamenti property.
     * 
     * @param value
     *     allowed object is
     *     {@link RicercaProvvisoriPagoPARequest.ElencoCausaliVersamenti }
     *     
     */
    public void setElencoCausaliVersamenti(RicercaProvvisoriPagoPARequest.ElencoCausaliVersamenti value) {
        this.elencoCausaliVersamenti = value;
    }

    /**
     * Gets the value of the annoEsercizio property.
     * 
     */
    public int getAnnoEsercizio() {
        return annoEsercizio;
    }

    /**
     * Sets the value of the annoEsercizio property.
     * 
     */
    public void setAnnoEsercizio(int value) {
        this.annoEsercizio = value;
    }

    /**
     * Gets the value of the dataProvvisorioDal property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataProvvisorioDal() {
        return dataProvvisorioDal;
    }

    /**
     * Sets the value of the dataProvvisorioDal property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataProvvisorioDal(XMLGregorianCalendar value) {
        this.dataProvvisorioDal = value;
    }

    /**
     * Gets the value of the dataProvvisorioAl property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataProvvisorioAl() {
        return dataProvvisorioAl;
    }

    /**
     * Sets the value of the dataProvvisorioAl property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataProvvisorioAl(XMLGregorianCalendar value) {
        this.dataProvvisorioAl = value;
    }

    /**
     * Gets the value of the numeroProvvisorioDal property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNumeroProvvisorioDal() {
        return numeroProvvisorioDal;
    }

    /**
     * Sets the value of the numeroProvvisorioDal property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNumeroProvvisorioDal(BigInteger value) {
        this.numeroProvvisorioDal = value;
    }

    /**
     * Gets the value of the numeroProvvisorioAl property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNumeroProvvisorioAl() {
        return numeroProvvisorioAl;
    }

    /**
     * Sets the value of the numeroProvvisorioAl property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNumeroProvvisorioAl(BigInteger value) {
        this.numeroProvvisorioAl = value;
    }

    /**
     * Gets the value of the stato property.
     * 
     * @return
     *     possible object is
     *     {@link StatoProvvisorioType }
     *     
     */
    public StatoProvvisorioType getStato() {
        return stato;
    }

    /**
     * Sets the value of the stato property.
     * 
     * @param value
     *     allowed object is
     *     {@link StatoProvvisorioType }
     *     
     */
    public void setStato(StatoProvvisorioType value) {
        this.stato = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="CausaleVersamento" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "causaleVersamento"
    })
    public static class ElencoCausaliVersamenti {

        @XmlElement(name = "CausaleVersamento", namespace = "http://www.csi.it/epay/epaywso/riconciliazione-versamenti-psp/types", required = true)
        protected List<String> causaleVersamento;

        /**
         * Gets the value of the causaleVersamento property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the causaleVersamento property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getCausaleVersamento().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getCausaleVersamento() {
            if (causaleVersamento == null) {
                causaleVersamento = new ArrayList<String>();
            }
            return this.causaleVersamento;
        }

    }

}
