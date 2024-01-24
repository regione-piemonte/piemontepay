/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodricweb.integration.stubs.epaymodricws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for cancellaProvvisori complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="cancellaProvvisori">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="listaProvvisoriDaCancellare" type="{http://epaymodric.interfacews.epaymodric.epay.csi.it/}dtoInputWsCancellaProvvisori" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "cancellaProvvisori", propOrder = {
    "listaProvvisoriDaCancellare"
})
public class CancellaProvvisori {

    protected DtoInputWsCancellaProvvisori listaProvvisoriDaCancellare;

    /**
     * Gets the value of the listaProvvisoriDaCancellare property.
     * 
     * @return
     *     possible object is
     *     {@link DtoInputWsCancellaProvvisori }
     *     
     */
    public DtoInputWsCancellaProvvisori getListaProvvisoriDaCancellare() {
        return listaProvvisoriDaCancellare;
    }

    /**
     * Sets the value of the listaProvvisoriDaCancellare property.
     * 
     * @param value
     *     allowed object is
     *     {@link DtoInputWsCancellaProvvisori }
     *     
     */
    public void setListaProvvisoriDaCancellare(DtoInputWsCancellaProvvisori value) {
        this.listaProvvisoriDaCancellare = value;
    }

}
