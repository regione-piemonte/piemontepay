/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodricweb.integration.stubs.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for messageDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="messageDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codiceMessaggio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "messageDto", propOrder = {
    "codiceMessaggio",
    "messaggio"
})
public class MessageDto {

    protected String codiceMessaggio;
    protected String messaggio;

    /**
     * Gets the value of the codiceMessaggio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceMessaggio() {
        return codiceMessaggio;
    }

    /**
     * Sets the value of the codiceMessaggio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceMessaggio(String value) {
        this.codiceMessaggio = value;
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
