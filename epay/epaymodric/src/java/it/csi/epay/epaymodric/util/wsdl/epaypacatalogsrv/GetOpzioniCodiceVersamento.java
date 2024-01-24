/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.util.wsdl.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getOpzioniCodiceVersamento complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getOpzioniCodiceVersamento">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="getOpzioniCodiceVersamentoInput" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}getOpzioniCodiceVersamentoInput" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getOpzioniCodiceVersamento", propOrder = {
    "getOpzioniCodiceVersamentoInput"
})
public class GetOpzioniCodiceVersamento {

    protected GetOpzioniCodiceVersamentoInput getOpzioniCodiceVersamentoInput;

    /**
     * Gets the value of the getOpzioniCodiceVersamentoInput property.
     * 
     * @return
     *     possible object is
     *     {@link GetOpzioniCodiceVersamentoInput }
     *     
     */
    public GetOpzioniCodiceVersamentoInput getGetOpzioniCodiceVersamentoInput() {
        return getOpzioniCodiceVersamentoInput;
    }

    /**
     * Sets the value of the getOpzioniCodiceVersamentoInput property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetOpzioniCodiceVersamentoInput }
     *     
     */
    public void setGetOpzioniCodiceVersamentoInput(GetOpzioniCodiceVersamentoInput value) {
        this.getOpzioniCodiceVersamentoInput = value;
    }

}
