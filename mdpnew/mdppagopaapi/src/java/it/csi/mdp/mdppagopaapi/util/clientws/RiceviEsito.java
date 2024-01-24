/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdppagopaapi.util.clientws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for riceviEsito complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="riceviEsito">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="parametri" type="{http://serviziofruitore.interfacews.mdp.nodospc.csi.it/}parametriRiceviEsito" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "riceviEsito", propOrder = {
    "parametri"
})
public class RiceviEsito {

    protected ParametriRiceviEsito parametri;

    /**
     * Gets the value of the parametri property.
     * 
     * @return
     *     possible object is
     *     {@link ParametriRiceviEsito }
     *     
     */
    public ParametriRiceviEsito getParametri() {
        return parametri;
    }

    /**
     * Sets the value of the parametri property.
     * 
     * @param value
     *     allowed object is
     *     {@link ParametriRiceviEsito }
     *     
     */
    public void setParametri(ParametriRiceviEsito value) {
        this.parametri = value;
    }

}
