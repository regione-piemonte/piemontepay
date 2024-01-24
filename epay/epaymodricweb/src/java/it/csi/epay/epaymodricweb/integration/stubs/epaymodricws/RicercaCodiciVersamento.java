/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodricweb.integration.stubs.epaymodricws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ricercaCodiciVersamento complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ricercaCodiciVersamento">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ricercaCodiciVersamento" type="{http://epaymodric.interfacews.epaymodric.epay.csi.it/}dtoInputWsRicercaCodiciVersamento" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ricercaCodiciVersamento", propOrder = {
    "ricercaCodiciVersamento"
})
public class RicercaCodiciVersamento {

    protected DtoInputWsRicercaCodiciVersamento ricercaCodiciVersamento;

    /**
     * Gets the value of the ricercaCodiciVersamento property.
     * 
     * @return
     *     possible object is
     *     {@link DtoInputWsRicercaCodiciVersamento }
     *     
     */
    public DtoInputWsRicercaCodiciVersamento getRicercaCodiciVersamento() {
        return ricercaCodiciVersamento;
    }

    /**
     * Sets the value of the ricercaCodiciVersamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link DtoInputWsRicercaCodiciVersamento }
     *     
     */
    public void setRicercaCodiciVersamento(DtoInputWsRicercaCodiciVersamento value) {
        this.ricercaCodiciVersamento = value;
    }

}
