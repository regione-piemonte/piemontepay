/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdppagopaapi.util.clientws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ChiaveValore complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ChiaveValore">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="chiave" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="valore" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ChiaveValore", propOrder = {
    "chiave",
    "valore"
})
public class ChiaveValore {

    @XmlElement(required = true)
    protected String chiave;
    protected String valore;

    /**
     * Gets the value of the chiave property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChiave() {
        return chiave;
    }

    /**
     * Sets the value of the chiave property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChiave(String value) {
        this.chiave = value;
    }

    /**
     * Gets the value of the valore property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValore() {
        return valore;
    }

    /**
     * Sets the value of the valore property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValore(String value) {
        this.valore = value;
    }

}
