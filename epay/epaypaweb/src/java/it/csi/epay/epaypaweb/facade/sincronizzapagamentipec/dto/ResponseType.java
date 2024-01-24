/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypaweb.facade.sincronizzapagamentipec.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType ( name = "ResponseType", namespace = "http://www.csi.it/epay/epaypa/types", propOrder = {
    "result"
})
public class ResponseType {

    @XmlElement(name = "Result", required = true)
    protected ResultType result;

    /**
     * Gets the value of the result property.
     *
     * @return
     *     possible object is
     *     {@link ResultType }
     *
     */
    public ResultType getResult() {
        return result;
    }

    /**
     * Sets the value of the result property.
     *
     * @param value
     *     allowed object is
     *     {@link ResultType }
     *
     */
    public void setResult(ResultType value) {
        result = value;
    }

}
