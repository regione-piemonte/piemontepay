/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysim.dto.ws.flussi.riconciliazioneversamentipsptypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="TestataTrasmissioneFlussi" type="{http://www.csi.it/epay/epaywso/riconciliazione-versamenti-psp/types}TestataTrasmissioneFlussiType"/>
 *         &lt;element name="FlussoRiconciliato" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
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
    "testataTrasmissioneFlussi",
    "flussoRiconciliato"
})
@XmlRootElement(name = "TrasmettiFlussiPagoPARequest", namespace = "http://www.csi.it/epay/epaywso/riconciliazione-versamenti-psp/types")
public class TrasmettiFlussiPagoPARequest {

    @XmlElement(name = "TestataTrasmissioneFlussi", namespace = "http://www.csi.it/epay/epaywso/riconciliazione-versamenti-psp/types", required = true)
    protected TestataTrasmissioneFlussiType testataTrasmissioneFlussi;
    @XmlElement(name = "FlussoRiconciliato", namespace = "http://www.csi.it/epay/epaywso/riconciliazione-versamenti-psp/types", required = true)
    protected byte[] flussoRiconciliato;

    /**
     * Gets the value of the testataTrasmissioneFlussi property.
     * 
     * @return
     *     possible object is
     *     {@link TestataTrasmissioneFlussiType }
     *     
     */
    public TestataTrasmissioneFlussiType getTestataTrasmissioneFlussi() {
        return testataTrasmissioneFlussi;
    }

    /**
     * Sets the value of the testataTrasmissioneFlussi property.
     * 
     * @param value
     *     allowed object is
     *     {@link TestataTrasmissioneFlussiType }
     *     
     */
    public void setTestataTrasmissioneFlussi(TestataTrasmissioneFlussiType value) {
        this.testataTrasmissioneFlussi = value;
    }

    /**
     * Gets the value of the flussoRiconciliato property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getFlussoRiconciliato() {
        return flussoRiconciliato;
    }

    /**
     * Sets the value of the flussoRiconciliato property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setFlussoRiconciliato(byte[] value) {
        this.flussoRiconciliato = value;
    }

}
