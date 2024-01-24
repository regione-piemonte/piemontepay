/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymdpservices.webservices.interfaces;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for chiediDatiPagamento complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="chiediDatiPagamento">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="parametri" type="{http://serviziofruitore.interfacews.mdp.nodospc.csi.it/}parametriChiediDatiPagamento" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "chiediDatiPagamento", propOrder = {
    "parametri"
})
public class ChiediDatiPagamento {

    protected ParametriChiediDatiPagamento parametri;

    /**
     * Gets the value of the parametri property.
     * 
     * @return
     *     possible object is
     *     {@link ParametriChiediDatiPagamento }
     *     
     */
    public ParametriChiediDatiPagamento getParametri() {
        return parametri;
    }

    /**
     * Sets the value of the parametri property.
     * 
     * @param value
     *     allowed object is
     *     {@link ParametriChiediDatiPagamento }
     *     
     */
    public void setParametri(ParametriChiediDatiPagamento value) {
        this.parametri = value;
    }

}
