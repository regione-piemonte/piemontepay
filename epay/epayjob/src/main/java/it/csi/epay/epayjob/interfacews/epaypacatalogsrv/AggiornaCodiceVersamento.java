/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayjob.interfacews.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for aggiornaCodiceVersamento complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="aggiornaCodiceVersamento">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="aggiornaCodiceVersamentoInput" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}aggiornaCodiceVersamentoInput" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "aggiornaCodiceVersamento", propOrder = {
    "aggiornaCodiceVersamentoInput"
})
public class AggiornaCodiceVersamento {

    protected AggiornaCodiceVersamentoInput aggiornaCodiceVersamentoInput;

    /**
     * Gets the value of the aggiornaCodiceVersamentoInput property.
     * 
     * @return
     *     possible object is
     *     {@link AggiornaCodiceVersamentoInput }
     *     
     */
    public AggiornaCodiceVersamentoInput getAggiornaCodiceVersamentoInput() {
        return aggiornaCodiceVersamentoInput;
    }

    /**
     * Sets the value of the aggiornaCodiceVersamentoInput property.
     * 
     * @param value
     *     allowed object is
     *     {@link AggiornaCodiceVersamentoInput }
     *     
     */
    public void setAggiornaCodiceVersamentoInput(AggiornaCodiceVersamentoInput value) {
        this.aggiornaCodiceVersamentoInput = value;
    }

}
