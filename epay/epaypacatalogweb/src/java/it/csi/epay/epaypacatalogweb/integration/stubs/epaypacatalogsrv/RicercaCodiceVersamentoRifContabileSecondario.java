/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ricercaCodiceVersamentoRifContabileSecondario complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ricercaCodiceVersamentoRifContabileSecondario">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ricercaCodiceVersamentoRifContabileSecondarioInput" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}ricercaCodiceVersamentoRifContabileSecondarioInput" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ricercaCodiceVersamentoRifContabileSecondario", propOrder = {
    "ricercaCodiceVersamentoRifContabileSecondarioInput"
})
public class RicercaCodiceVersamentoRifContabileSecondario {

    protected RicercaCodiceVersamentoRifContabileSecondarioInput ricercaCodiceVersamentoRifContabileSecondarioInput;

    /**
     * Gets the value of the ricercaCodiceVersamentoRifContabileSecondarioInput property.
     * 
     * @return
     *     possible object is
     *     {@link RicercaCodiceVersamentoRifContabileSecondarioInput }
     *     
     */
    public RicercaCodiceVersamentoRifContabileSecondarioInput getRicercaCodiceVersamentoRifContabileSecondarioInput() {
        return ricercaCodiceVersamentoRifContabileSecondarioInput;
    }

    /**
     * Sets the value of the ricercaCodiceVersamentoRifContabileSecondarioInput property.
     * 
     * @param value
     *     allowed object is
     *     {@link RicercaCodiceVersamentoRifContabileSecondarioInput }
     *     
     */
    public void setRicercaCodiceVersamentoRifContabileSecondarioInput(RicercaCodiceVersamentoRifContabileSecondarioInput value) {
        this.ricercaCodiceVersamentoRifContabileSecondarioInput = value;
    }

}
