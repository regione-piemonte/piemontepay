/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdppagopaapi.pa.pafornode;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.math.BigDecimal;


/**
 * <p>Java class for ctTransferPA complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ctTransferPA">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idTransfer" type="{http://pagopa-api.pagopa.gov.it/pa/paForNode.xsd}stIdTransfer"/>
 *         &lt;element name="transferAmount" type="{http://pagopa-api.pagopa.gov.it/pa/paForNode.xsd}stAmountNotZero"/>
 *         &lt;element name="fiscalCodePA" type="{http://pagopa-api.pagopa.gov.it/pa/paForNode.xsd}stFiscalCodePA"/>
 *         &lt;element name="IBAN" type="{http://pagopa-api.pagopa.gov.it/pa/paForNode.xsd}stIBAN"/>
 *         &lt;element name="remittanceInformation" type="{http://pagopa-api.pagopa.gov.it/pa/paForNode.xsd}stText140"/>
 *         &lt;element name="transferCategory" type="{http://pagopa-api.pagopa.gov.it/pa/paForNode.xsd}stText140"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ctTransferPA", propOrder = {
    "idTransfer",
    "transferAmount",
    "fiscalCodePA",
    "iban",
    "remittanceInformation",
    "transferCategory"
})
public class CtTransferPA {

    protected int idTransfer;
    @XmlElement(required = true)
    protected BigDecimal transferAmount;
    @XmlElement(required = true)
    protected String fiscalCodePA;
    @XmlElement(name = "IBAN", required = true)
    protected String iban;
    @XmlElement(required = true)
    protected String remittanceInformation;
    @XmlElement(required = true)
    protected String transferCategory;

    /**
     * Gets the value of the idTransfer property.
     * 
     */
    public int getIdTransfer() {
        return idTransfer;
    }

    /**
     * Sets the value of the idTransfer property.
     * 
     */
    public void setIdTransfer(int value) {
        this.idTransfer = value;
    }

    /**
     * Gets the value of the transferAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTransferAmount() {
        return transferAmount;
    }

    /**
     * Sets the value of the transferAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTransferAmount(BigDecimal value) {
        this.transferAmount = value;
    }

    /**
     * Gets the value of the fiscalCodePA property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFiscalCodePA() {
        return fiscalCodePA;
    }

    /**
     * Sets the value of the fiscalCodePA property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFiscalCodePA(String value) {
        this.fiscalCodePA = value;
    }

    /**
     * Gets the value of the iban property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIBAN() {
        return iban;
    }

    /**
     * Sets the value of the iban property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIBAN(String value) {
        this.iban = value;
    }

    /**
     * Gets the value of the remittanceInformation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRemittanceInformation() {
        return remittanceInformation;
    }

    /**
     * Sets the value of the remittanceInformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRemittanceInformation(String value) {
        this.remittanceInformation = value;
    }

    /**
     * Gets the value of the transferCategory property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransferCategory() {
        return transferCategory;
    }

    /**
     * Sets the value of the transferCategory property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransferCategory(String value) {
        this.transferCategory = value;
    }

	public void addAmount ( CtTransferPA other ) {
		this.transferAmount = this.transferAmount.add ( other.getTransferAmount () );
	}
}
