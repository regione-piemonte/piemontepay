/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdppagopaapi.util.clientws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for verificaDatiPagamento complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="verificaDatiPagamento">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="parametri" type="{http://serviziofruitore.interfacews.mdp.nodospc.csi.it/}parametriVerificaDatiPagamento" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "verificaDatiPagamento", propOrder = {
    "parametri"
})
public class VerificaDatiPagamento {

    protected ParametriVerificaDatiPagamento parametri;

    /**
     * Gets the value of the parametri property.
     * 
     * @return
     *     possible object is
     *     {@link ParametriVerificaDatiPagamento }
     *     
     */
    public ParametriVerificaDatiPagamento getParametri() {
        return parametri;
    }

    /**
     * Sets the value of the parametri property.
     * 
     * @param value
     *     allowed object is
     *     {@link ParametriVerificaDatiPagamento }
     *     
     */
    public void setParametri(ParametriVerificaDatiPagamento value) {
        this.parametri = value;
    }

}
