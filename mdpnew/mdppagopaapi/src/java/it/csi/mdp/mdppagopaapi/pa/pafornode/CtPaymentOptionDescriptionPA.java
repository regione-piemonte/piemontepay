/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdppagopaapi.pa.pafornode;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for ctPaymentOptionDescriptionPA complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ctPaymentOptionDescriptionPA">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="amount" type="{http://pagopa-api.pagopa.gov.it/pa/paForNode.xsd}stAmount"/>
 *         &lt;element name="options" type="{http://pagopa-api.pagopa.gov.it/pa/paForNode.xsd}stAmountOption"/>
 *         &lt;element name="dueDate" type="{http://pagopa-api.pagopa.gov.it/pa/paForNode.xsd}stISODate" minOccurs="0"/>
 *         &lt;element name="detailDescription" type="{http://pagopa-api.pagopa.gov.it/pa/paForNode.xsd}stText140" minOccurs="0"/>
 *         &lt;element name="allCCP" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ctPaymentOptionDescriptionPA", propOrder = {
    "amount",
    "options",
    "dueDate",
    "detailDescription",
    "allCCP"
})
public class CtPaymentOptionDescriptionPA {

    @XmlElement(required = true)
    protected BigDecimal amount;
    @XmlElement(required = true)
    protected StAmountOption options;
    protected XMLGregorianCalendar dueDate;
    protected String detailDescription;
    protected boolean allCCP;

    /**
     * Gets the value of the amount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Sets the value of the amount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAmount(BigDecimal value) {
        this.amount = value;
    }

    /**
     * Gets the value of the options property.
     * 
     * @return
     *     possible object is
     *     {@link StAmountOption }
     *     
     */
    public StAmountOption getOptions() {
        return options;
    }

    /**
     * Sets the value of the options property.
     * 
     * @param value
     *     allowed object is
     *     {@link StAmountOption }
     *     
     */
    public void setOptions(StAmountOption value) {
        this.options = value;
    }

    /**
     * Gets the value of the dueDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDueDate() {
        return dueDate;
    }

    /**
     * Sets the value of the dueDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDueDate(XMLGregorianCalendar value) {
        this.dueDate = value;
    }

    /**
     * Gets the value of the detailDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDetailDescription() {
        return detailDescription;
    }

    /**
     * Sets the value of the detailDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDetailDescription(String value) {
        this.detailDescription = value;
    }

    /**
     * Gets the value of the allCCP property.
     * 
     */
    public boolean isAllCCP() {
        return allCCP;
    }

    /**
     * Sets the value of the allCCP property.
     * 
     */
    public void setAllCCP(boolean value) {
        this.allCCP = value;
    }

}
