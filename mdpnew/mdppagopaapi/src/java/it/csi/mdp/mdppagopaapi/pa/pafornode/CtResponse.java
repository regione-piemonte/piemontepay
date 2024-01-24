/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdppagopaapi.pa.pafornode;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ctResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ctResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="outcome" type="{http://pagopa-api.pagopa.gov.it/pa/paForNode.xsd}stOutcome"/>
 *         &lt;element name="fault" type="{http://pagopa-api.pagopa.gov.it/pa/paForNode.xsd}ctFaultBean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ctResponse", propOrder = {
    "outcome",
    "fault"
})
@XmlSeeAlso({
    PaSendRTRes.class,
    PaGetPaymentRes.class,
    PaVerifyPaymentNoticeRes.class
})
public class CtResponse {

    @XmlElement(required = true)
    protected StOutcome outcome;
    protected CtFaultBean fault;

    /**
     * Gets the value of the outcome property.
     * 
     * @return
     *     possible object is
     *     {@link StOutcome }
     *     
     */
    public StOutcome getOutcome() {
        return outcome;
    }

    /**
     * Sets the value of the outcome property.
     * 
     * @param value
     *     allowed object is
     *     {@link StOutcome }
     *     
     */
    public void setOutcome(StOutcome value) {
        this.outcome = value;
    }

    /**
     * Gets the value of the fault property.
     * 
     * @return
     *     possible object is
     *     {@link CtFaultBean }
     *     
     */
    public CtFaultBean getFault() {
        return fault;
    }

    /**
     * Sets the value of the fault property.
     * 
     * @param value
     *     allowed object is
     *     {@link CtFaultBean }
     *     
     */
    public void setFault(CtFaultBean value) {
        this.fault = value;
    }

}
