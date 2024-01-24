/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getCodiceVersamento complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getCodiceVersamento">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="getCodiceVersamentoInput" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}getCodiceVersamentoInput" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getCodiceVersamento", propOrder = {
    "getCodiceVersamentoInput"
})
public class GetCodiceVersamento {

    protected GetCodiceVersamentoInput getCodiceVersamentoInput;

    /**
     * Gets the value of the getCodiceVersamentoInput property.
     * 
     * @return
     *     possible object is
     *     {@link GetCodiceVersamentoInput }
     *     
     */
    public GetCodiceVersamentoInput getGetCodiceVersamentoInput() {
        return getCodiceVersamentoInput;
    }

    /**
     * Sets the value of the getCodiceVersamentoInput property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetCodiceVersamentoInput }
     *     
     */
    public void setGetCodiceVersamentoInput(GetCodiceVersamentoInput value) {
        this.getCodiceVersamentoInput = value;
    }

}
