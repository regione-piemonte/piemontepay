/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdpboweb.business.ws.coreCxf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for NamingException complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="NamingException">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="resolvedObj" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NamingException", propOrder = {
    "resolvedObj"
})
public class NamingException {

    @XmlElement(required = true, nillable = true)
    protected Object resolvedObj;

    /**
     * Gets the value of the resolvedObj property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getResolvedObj() {
        return resolvedObj;
    }

    /**
     * Sets the value of the resolvedObj property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setResolvedObj(Object value) {
        this.resolvedObj = value;
    }

}
