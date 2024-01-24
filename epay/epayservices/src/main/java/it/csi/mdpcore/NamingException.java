/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdpcore;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per NamingException complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
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
     * Recupera il valore della proprieta' resolvedObj.
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
     * Imposta il valore della proprieta' resolvedObj.
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
