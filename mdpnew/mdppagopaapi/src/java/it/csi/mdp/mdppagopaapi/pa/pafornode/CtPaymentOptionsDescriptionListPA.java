/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdppagopaapi.pa.pafornode;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Structure containing the details of possible payments relating to the debt position to be paid.
 * 
 * Currently set at 5 eligible payments per single position.
 * 
 * Where each `paymentOptionDescription` items contains :
 * 
 * - `amount` : payment amount
 * - `options` : indicates the payment criteria accepted by the institution with respect to the amount, or if it accepts for this payment option other than `amount`.
 * - `dueDate` : indicates the expiration payment date according to the ISO 8601 format `[YYYY]-[MM]-[DD]`.
 * - `detailDescription` : Free text available to describe the payment reasons
 * - `allCCP` : indicates that all transfers are associable to all postal IBAN
 * 
 * <p>Java class for ctPaymentOptionsDescriptionListPA complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ctPaymentOptionsDescriptionListPA">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="paymentOptionDescription" type="{http://pagopa-api.pagopa.gov.it/pa/paForNode.xsd}ctPaymentOptionDescriptionPA"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ctPaymentOptionsDescriptionListPA", propOrder = {
    "paymentOptionDescription"
})
public class CtPaymentOptionsDescriptionListPA {

    @XmlElement(required = true)
    protected CtPaymentOptionDescriptionPA paymentOptionDescription;

    /**
     * Gets the value of the paymentOptionDescription property.
     * 
     * @return
     *     possible object is
     *     {@link CtPaymentOptionDescriptionPA }
     *     
     */
    public CtPaymentOptionDescriptionPA getPaymentOptionDescription() {
        return paymentOptionDescription;
    }

    /**
     * Sets the value of the paymentOptionDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link CtPaymentOptionDescriptionPA }
     *     
     */
    public void setPaymentOptionDescription(CtPaymentOptionDescriptionPA value) {
        this.paymentOptionDescription = value;
    }

}
