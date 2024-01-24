/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodricweb.integration.stubs.epaymodricws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for elencaStatiElaborazioneResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="elencaStatiElaborazioneResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://epaymodric.interfacews.epaymodric.epay.csi.it/}dtoOutputWsStatiElaborazione" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "elencaStatiElaborazioneResponse", propOrder = {
    "_return"
})
public class ElencaStatiElaborazioneResponse {

    @XmlElement(name = "return")
    protected DtoOutputWsStatiElaborazione _return;

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link DtoOutputWsStatiElaborazione }
     *     
     */
    public DtoOutputWsStatiElaborazione getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link DtoOutputWsStatiElaborazione }
     *     
     */
    public void setReturn(DtoOutputWsStatiElaborazione value) {
        this._return = value;
    }

}
