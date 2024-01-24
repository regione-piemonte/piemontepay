/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.util.wsdl.epayriconciliazioneversamentipsp;

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
 *         &lt;element name="TestataFlussiInErrore" type="{http://www.csi.it/epay/epaywso/riconciliazione-versamenti-psp/types}TestataFlussiInErrore"/>
 *         &lt;element name="FlussiInErrore" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
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
    "testataFlussiInErrore",
    "flussiInErrore"
})
@XmlRootElement(name = "TrasmettiFlussiInErrorePagoPARequest", namespace = "http://www.csi.it/epay/epaywso/riconciliazione-versamenti-psp/types")
public class TrasmettiFlussiInErrorePagoPARequest {

    @XmlElement(name = "TestataFlussiInErrore", namespace = "http://www.csi.it/epay/epaywso/riconciliazione-versamenti-psp/types", required = true)
    protected TestataFlussiInErrore testataFlussiInErrore;
    @XmlElement(name = "FlussiInErrore", namespace = "http://www.csi.it/epay/epaywso/riconciliazione-versamenti-psp/types", required = true)
    protected byte[] flussiInErrore;

    /**
     * Gets the value of the testataFlussiInErrore property.
     * 
     * @return
     *     possible object is
     *     {@link TestataFlussiInErrore }
     *     
     */
    public TestataFlussiInErrore getTestataFlussiInErrore() {
        return testataFlussiInErrore;
    }

    /**
     * Sets the value of the testataFlussiInErrore property.
     * 
     * @param value
     *     allowed object is
     *     {@link TestataFlussiInErrore }
     *     
     */
    public void setTestataFlussiInErrore(TestataFlussiInErrore value) {
        this.testataFlussiInErrore = value;
    }

    /**
     * Gets the value of the flussiInErrore property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getFlussiInErrore() {
        return flussiInErrore;
    }

    /**
     * Sets the value of the flussiInErrore property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setFlussiInErrore(byte[] value) {
        this.flussiInErrore = value;
    }

}
