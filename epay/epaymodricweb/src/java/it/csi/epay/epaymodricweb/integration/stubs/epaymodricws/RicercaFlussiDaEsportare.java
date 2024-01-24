/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodricweb.integration.stubs.epaymodricws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ricercaFlussiDaEsportare complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ricercaFlussiDaEsportare">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ricercaFlussiDaEsportare" type="{http://epaymodric.interfacews.epaymodric.epay.csi.it/}dtoInputWsRicercaFlussiDaEsportare" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ricercaFlussiDaEsportare", propOrder = {
    "ricercaFlussiDaEsportare"
})
public class RicercaFlussiDaEsportare {

    protected DtoInputWsRicercaFlussiDaEsportare ricercaFlussiDaEsportare;

    /**
     * Gets the value of the ricercaFlussiDaEsportare property.
     * 
     * @return
     *     possible object is
     *     {@link DtoInputWsRicercaFlussiDaEsportare }
     *     
     */
    public DtoInputWsRicercaFlussiDaEsportare getRicercaFlussiDaEsportare() {
        return ricercaFlussiDaEsportare;
    }

    /**
     * Sets the value of the ricercaFlussiDaEsportare property.
     * 
     * @param value
     *     allowed object is
     *     {@link DtoInputWsRicercaFlussiDaEsportare }
     *     
     */
    public void setRicercaFlussiDaEsportare(DtoInputWsRicercaFlussiDaEsportare value) {
        this.ricercaFlussiDaEsportare = value;
    }

}
