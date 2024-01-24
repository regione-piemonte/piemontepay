/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodricweb.integration.stubs.epaymodricws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for aggiornaStatoReportResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="aggiornaStatoReportResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="result" type="{http://epaymodric.interfacews.epaymodric.epay.csi.it/}dtoOutputWsAggiornaStatoReport" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "aggiornaStatoReportResponse", propOrder = {
    "result"
})
public class AggiornaStatoReportResponse {

    protected DtoOutputWsAggiornaStatoReport result;

    /**
     * Gets the value of the result property.
     * 
     * @return
     *     possible object is
     *     {@link DtoOutputWsAggiornaStatoReport }
     *     
     */
    public DtoOutputWsAggiornaStatoReport getResult() {
        return result;
    }

    /**
     * Sets the value of the result property.
     * 
     * @param value
     *     allowed object is
     *     {@link DtoOutputWsAggiornaStatoReport }
     *     
     */
    public void setResult(DtoOutputWsAggiornaStatoReport value) {
        this.result = value;
    }

}
