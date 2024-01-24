/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ricercaRiferimentiContabiliSecondariPerCov complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ricercaRiferimentiContabiliSecondariPerCov">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ricercaRiferimentiContabiliSecondariPerCovInput" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}ricercaRiferimentiContabiliSecondariPerCovInput" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ricercaRiferimentiContabiliSecondariPerCov", propOrder = {
    "ricercaRiferimentiContabiliSecondariPerCovInput"
})
public class RicercaRiferimentiContabiliSecondariPerCov {

    protected RicercaRiferimentiContabiliSecondariPerCovInput ricercaRiferimentiContabiliSecondariPerCovInput;

    /**
     * Gets the value of the ricercaRiferimentiContabiliSecondariPerCovInput property.
     * 
     * @return
     *     possible object is
     *     {@link RicercaRiferimentiContabiliSecondariPerCovInput }
     *     
     */
    public RicercaRiferimentiContabiliSecondariPerCovInput getRicercaRiferimentiContabiliSecondariPerCovInput() {
        return ricercaRiferimentiContabiliSecondariPerCovInput;
    }

    /**
     * Sets the value of the ricercaRiferimentiContabiliSecondariPerCovInput property.
     * 
     * @param value
     *     allowed object is
     *     {@link RicercaRiferimentiContabiliSecondariPerCovInput }
     *     
     */
    public void setRicercaRiferimentiContabiliSecondariPerCovInput(RicercaRiferimentiContabiliSecondariPerCovInput value) {
        this.ricercaRiferimentiContabiliSecondariPerCovInput = value;
    }

}
