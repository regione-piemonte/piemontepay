/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysim.integration.epaymodric.stubs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dtoBaseContabilizzatore complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dtoBaseContabilizzatore">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="risposta" type="{http://epaymodric.interfacews.epaymodric.epay.csi.it/}dtoBaseResponse" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtoBaseContabilizzatore", namespace = "http://epaymodric.interfacews.epaymodric.epay.csi.it/", propOrder = {
    "risposta"
})
@XmlSeeAlso({
    DtpOutputElaborazione.class
})
public class DtoBaseContabilizzatore {

    @XmlElement(namespace = "")
    protected DtoBaseResponse risposta;

    /**
     * Gets the value of the risposta property.
     * 
     * @return
     *     possible object is
     *     {@link DtoBaseResponse }
     *     
     */
    public DtoBaseResponse getRisposta() {
        return risposta;
    }

    /**
     * Sets the value of the risposta property.
     * 
     * @param value
     *     allowed object is
     *     {@link DtoBaseResponse }
     *     
     */
    public void setRisposta(DtoBaseResponse value) {
        this.risposta = value;
    }

}
