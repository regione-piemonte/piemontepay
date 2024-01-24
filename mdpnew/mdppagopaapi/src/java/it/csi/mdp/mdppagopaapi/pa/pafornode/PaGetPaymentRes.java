/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdppagopaapi.pa.pafornode;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * Its a response to `paGetPaymentReq` and contains :
 * 
 * - `outcome` and _optional_ `fault` (_see below to details_)
 * - all `data` related to payment (_see below to details_)
 * 
 * <p>Java class for paGetPaymentRes complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="paGetPaymentRes">
 *   &lt;complexContent>
 *     &lt;extension base="{http://pagopa-api.pagopa.gov.it/pa/paForNode.xsd}ctResponse">
 *       &lt;sequence>
 *         &lt;element name="data" type="{http://pagopa-api.pagopa.gov.it/pa/paForNode.xsd}ctPaymentPA" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "paGetPaymentRes", propOrder = {
    "data"
})
public class PaGetPaymentRes
    extends CtResponse
{

    protected CtPaymentPA data;

    /**
     * Gets the value of the data property.
     * 
     * @return
     *     possible object is
     *     {@link CtPaymentPA }
     *     
     */
    public CtPaymentPA getData() {
        return data;
    }

    /**
     * Sets the value of the data property.
     * 
     * @param value
     *     allowed object is
     *     {@link CtPaymentPA }
     *     
     */
    public void setData(CtPaymentPA value) {
        this.data = value;
    }

}
