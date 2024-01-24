/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.util.wsdl.epaywso;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlMimeType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.transform.Source;


/**
 * <p>Java class for EmbeddedXMLType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EmbeddedXMLType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Contenuto" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EmbeddedXMLType", propOrder = {
    "contenuto"
})
public class EmbeddedXMLType {

    @XmlElement(name = "Contenuto", required = true)
    @XmlMimeType("text/xml")
    protected Source contenuto;

    /**
     * Gets the value of the contenuto property.
     * 
     * @return
     *     possible object is
     *     {@link Source }
     *     
     */
    public Source getContenuto() {
        return contenuto;
    }

    /**
     * Sets the value of the contenuto property.
     * 
     * @param value
     *     allowed object is
     *     {@link Source }
     *     
     */
    public void setContenuto(Source value) {
        this.contenuto = value;
    }

}
