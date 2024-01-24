/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodricweb.integration.stubs.epaymodricws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ricercaCodiciVersamentoPerCodice complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ricercaCodiciVersamentoPerCodice">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="param" type="{http://epaymodric.interfacews.epaymodric.epay.csi.it/}dtoInputWsRicercaCodiciVersamento" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ricercaCodiciVersamentoPerCodice", propOrder = {
    "param"
})
public class RicercaCodiciVersamentoPerCodice {

    protected DtoInputWsRicercaCodiciVersamento param;

    /**
     * Gets the value of the param property.
     * 
     * @return
     *     possible object is
     *     {@link DtoInputWsRicercaCodiciVersamento }
     *     
     */
    public DtoInputWsRicercaCodiciVersamento getParam() {
        return param;
    }

    /**
     * Sets the value of the param property.
     * 
     * @param value
     *     allowed object is
     *     {@link DtoInputWsRicercaCodiciVersamento }
     *     
     */
    public void setParam(DtoInputWsRicercaCodiciVersamento value) {
        this.param = value;
    }

}
