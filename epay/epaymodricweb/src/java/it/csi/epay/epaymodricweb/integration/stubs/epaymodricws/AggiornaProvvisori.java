/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodricweb.integration.stubs.epaymodricws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for aggiornaProvvisori complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="aggiornaProvvisori">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="listaProvvisoriDaAggiornare" type="{http://epaymodric.interfacews.epaymodric.epay.csi.it/}dtoInputWsAggiornaProvvisori" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "aggiornaProvvisori", propOrder = {
    "listaProvvisoriDaAggiornare"
})
public class AggiornaProvvisori {

    protected DtoInputWsAggiornaProvvisori listaProvvisoriDaAggiornare;

    /**
     * Gets the value of the listaProvvisoriDaAggiornare property.
     * 
     * @return
     *     possible object is
     *     {@link DtoInputWsAggiornaProvvisori }
     *     
     */
    public DtoInputWsAggiornaProvvisori getListaProvvisoriDaAggiornare() {
        return listaProvvisoriDaAggiornare;
    }

    /**
     * Sets the value of the listaProvvisoriDaAggiornare property.
     * 
     * @param value
     *     allowed object is
     *     {@link DtoInputWsAggiornaProvvisori }
     *     
     */
    public void setListaProvvisoriDaAggiornare(DtoInputWsAggiornaProvvisori value) {
        this.listaProvvisoriDaAggiornare = value;
    }

}
