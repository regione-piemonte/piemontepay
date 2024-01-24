/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ricercaRiferimentiContabiliSecondariPerCovResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ricercaRiferimentiContabiliSecondariPerCovResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="result" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}ricercaRiferimentiContabiliSecondariPerCovOutput" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ricercaRiferimentiContabiliSecondariPerCovResponse", propOrder = {
    "result"
})
public class RicercaRiferimentiContabiliSecondariPerCovResponse {

    protected RicercaRiferimentiContabiliSecondariPerCovOutput result;

    /**
     * Gets the value of the result property.
     * 
     * @return
     *     possible object is
     *     {@link RicercaRiferimentiContabiliSecondariPerCovOutput }
     *     
     */
    public RicercaRiferimentiContabiliSecondariPerCovOutput getResult() {
        return result;
    }

    /**
     * Sets the value of the result property.
     * 
     * @param value
     *     allowed object is
     *     {@link RicercaRiferimentiContabiliSecondariPerCovOutput }
     *     
     */
    public void setResult(RicercaRiferimentiContabiliSecondariPerCovOutput value) {
        this.result = value;
    }

}
