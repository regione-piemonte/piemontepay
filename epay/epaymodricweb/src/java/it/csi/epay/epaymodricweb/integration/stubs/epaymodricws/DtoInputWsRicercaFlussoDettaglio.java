/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodricweb.integration.stubs.epaymodricws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dtoInputWsRicercaFlussoDettaglio complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dtoInputWsRicercaFlussoDettaglio">
 *   &lt;complexContent>
 *     &lt;extension base="{http://epaymodric.interfacews.epaymodric.epay.csi.it/}dtoInputBase">
 *       &lt;sequence>
 *         &lt;element name="idFlussoSintesi" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtoInputWsRicercaFlussoDettaglio", propOrder = {
    "idFlussoSintesi"
})
public class DtoInputWsRicercaFlussoDettaglio
    extends DtoInputBase
{

    protected String idFlussoSintesi;

    /**
     * Gets the value of the idFlussoSintesi property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdFlussoSintesi() {
        return idFlussoSintesi;
    }

    /**
     * Sets the value of the idFlussoSintesi property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdFlussoSintesi(String value) {
        this.idFlussoSintesi = value;
    }

}
