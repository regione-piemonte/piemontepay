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
@XmlType ( name = "ResultType", namespace = "http://www.csi.it/epay/epaypa/types", propOrder = {
    "codice",
    "messaggio"
})
public class ResultType {

    @XmlElement(name = "Codice", required = true)
    protected String codice;
    @XmlElement(name = "Messaggio")
    protected String messaggio;

    /**
     * Gets the value of the codice property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getCodice() {
        return codice;
    }

    /**
     * Sets the value of the codice property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCodice(String value) {
        codice = value;
    }

    /**
     * Gets the value of the messaggio property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getMessaggio() {
        return messaggio;
    }

    /**
     * Sets the value of the messaggio property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setMessaggio(String value) {
        messaggio = value;
    }

}
