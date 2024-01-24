/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodricweb.integration.stubs.epaymodricws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dtoInputWsRicercaFlussoSintesi complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dtoInputWsRicercaFlussoSintesi">
 *   &lt;complexContent>
 *     &lt;extension base="{http://epaymodric.interfacews.epaymodric.epay.csi.it/}dtoInputBase">
 *       &lt;sequence>
 *         &lt;element name="idFlussoOrigine" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtoInputWsRicercaFlussoSintesi", propOrder = {
    "idFlussoOrigine"
})
public class DtoInputWsRicercaFlussoSintesi
    extends DtoInputBase
{

    protected String idFlussoOrigine;

    /**
     * Gets the value of the idFlussoOrigine property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdFlussoOrigine() {
        return idFlussoOrigine;
    }

    /**
     * Sets the value of the idFlussoOrigine property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdFlussoOrigine(String value) {
        this.idFlussoOrigine = value;
    }

}
