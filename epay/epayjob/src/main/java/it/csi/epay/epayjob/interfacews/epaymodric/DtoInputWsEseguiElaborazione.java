/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayjob.interfacews.epaymodric;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dtoInputWsEseguiElaborazione complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dtoInputWsEseguiElaborazione">
 *   &lt;complexContent>
 *     &lt;extension base="{http://batch.epaymodric.interfacews.epaymodric.epay.csi.it/}dtoInputBase">
 *       &lt;sequence>
 *         &lt;element name="idElaborazione" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtoInputWsEseguiElaborazione", propOrder = {
    "idElaborazione"
})
public class DtoInputWsEseguiElaborazione
    extends DtoInputBase
{

    protected Long idElaborazione;

    /**
     * Gets the value of the idElaborazione property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdElaborazione() {
        return idElaborazione;
    }

    /**
     * Sets the value of the idElaborazione property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdElaborazione(Long value) {
        this.idElaborazione = value;
    }

}
