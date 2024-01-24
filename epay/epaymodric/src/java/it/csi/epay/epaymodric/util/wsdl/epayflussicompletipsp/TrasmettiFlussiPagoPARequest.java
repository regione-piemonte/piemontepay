/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.util.wsdl.epayflussicompletipsp;

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
 *         &lt;element name="Testata" type="{http://www.csi.it/epay/epaywso/flussi-completi-psp/types}TestataTrasmissioneFlussiType"/>
 *         &lt;element name="FlussoCompleto" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *         &lt;element name="DatiAggiuntivi" type="{http://www.csi.it/epay/epaywso/flussi-completi-psp/types}DatiAggiuntiviType"/>
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
    "testata",
    "flussoCompleto",
    "datiAggiuntivi"
})
@XmlRootElement(name = "TrasmettiFlussiPagoPARequest")
public class TrasmettiFlussiPagoPARequest {

    @XmlElement(name = "Testata", required = true)
    protected TestataTrasmissioneFlussiType testata;
    @XmlElement(name = "FlussoCompleto", required = true)
    protected byte[] flussoCompleto;
    @XmlElement(name = "DatiAggiuntivi", required = true)
    protected DatiAggiuntiviType datiAggiuntivi;

    /**
     * Gets the value of the testata property.
     * 
     * @return
     *     possible object is
     *     {@link TestataTrasmissioneFlussiType }
     *     
     */
    public TestataTrasmissioneFlussiType getTestata() {
        return testata;
    }

    /**
     * Sets the value of the testata property.
     * 
     * @param value
     *     allowed object is
     *     {@link TestataTrasmissioneFlussiType }
     *     
     */
    public void setTestata(TestataTrasmissioneFlussiType value) {
        this.testata = value;
    }

    /**
     * Gets the value of the flussoCompleto property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getFlussoCompleto() {
        return flussoCompleto;
    }

    /**
     * Sets the value of the flussoCompleto property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setFlussoCompleto(byte[] value) {
        this.flussoCompleto = value;
    }

    /**
     * Gets the value of the datiAggiuntivi property.
     * 
     * @return
     *     possible object is
     *     {@link DatiAggiuntiviType }
     *     
     */
    public DatiAggiuntiviType getDatiAggiuntivi() {
        return datiAggiuntivi;
    }

    /**
     * Sets the value of the datiAggiuntivi property.
     * 
     * @param value
     *     allowed object is
     *     {@link DatiAggiuntiviType }
     *     
     */
    public void setDatiAggiuntivi(DatiAggiuntiviType value) {
        this.datiAggiuntivi = value;
    }

}
