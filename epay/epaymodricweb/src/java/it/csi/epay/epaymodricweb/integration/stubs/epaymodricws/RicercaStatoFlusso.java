/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodricweb.integration.stubs.epaymodricws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ricercaStatoFlusso complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ricercaStatoFlusso">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ricercaStatoFlusso" type="{http://epaymodric.interfacews.epaymodric.epay.csi.it/}dtoInputWsRicercaStatoFlusso" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ricercaStatoFlusso", propOrder = {
    "ricercaStatoFlusso"
})
public class RicercaStatoFlusso {

    protected DtoInputWsRicercaStatoFlusso ricercaStatoFlusso;

    /**
     * Gets the value of the ricercaStatoFlusso property.
     * 
     * @return
     *     possible object is
     *     {@link DtoInputWsRicercaStatoFlusso }
     *     
     */
    public DtoInputWsRicercaStatoFlusso getRicercaStatoFlusso() {
        return ricercaStatoFlusso;
    }

    /**
     * Sets the value of the ricercaStatoFlusso property.
     * 
     * @param value
     *     allowed object is
     *     {@link DtoInputWsRicercaStatoFlusso }
     *     
     */
    public void setRicercaStatoFlusso(DtoInputWsRicercaStatoFlusso value) {
        this.ricercaStatoFlusso = value;
    }

}
