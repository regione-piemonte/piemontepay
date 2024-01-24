/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodricweb.integration.stubs.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getMessaggiOutputDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getMessaggiOutputDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codice" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codiceApplicativo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codiceBreve" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codiceLingua" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="messaggio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getMessaggiOutputDto", propOrder = {
    "codice",
    "codiceApplicativo",
    "codiceBreve",
    "codiceLingua",
    "id",
    "messaggio"
})
public class GetMessaggiOutputDto {

    protected String codice;
    protected String codiceApplicativo;
    protected String codiceBreve;
    protected String codiceLingua;
    protected Long id;
    protected String messaggio;

    /**
     * Gets the value of the codice property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodice() {
        return codice;
    }

    /**
     * Sets the value of the codice property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodice(String value) {
        this.codice = value;
    }

    /**
     * Gets the value of the codiceApplicativo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceApplicativo() {
        return codiceApplicativo;
    }

    /**
     * Sets the value of the codiceApplicativo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceApplicativo(String value) {
        this.codiceApplicativo = value;
    }

    /**
     * Gets the value of the codiceBreve property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceBreve() {
        return codiceBreve;
    }

    /**
     * Sets the value of the codiceBreve property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceBreve(String value) {
        this.codiceBreve = value;
    }

    /**
     * Gets the value of the codiceLingua property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceLingua() {
        return codiceLingua;
    }

    /**
     * Sets the value of the codiceLingua property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceLingua(String value) {
        this.codiceLingua = value;
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
     * Gets the value of the messaggio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessaggio() {
        return messaggio;
    }

    /**
     * Sets the value of the messaggio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessaggio(String value) {
        this.messaggio = value;
    }

}
