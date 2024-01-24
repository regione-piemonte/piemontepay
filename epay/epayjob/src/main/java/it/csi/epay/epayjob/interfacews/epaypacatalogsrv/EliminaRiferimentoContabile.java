/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayjob.interfacews.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for eliminaRiferimentoContabile complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="eliminaRiferimentoContabile">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="eliminaRiferimentoContabileInput" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}eliminaRiferimentoContabileInput" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "eliminaRiferimentoContabile", propOrder = {
    "eliminaRiferimentoContabileInput"
})
public class EliminaRiferimentoContabile {

    protected EliminaRiferimentoContabileInput eliminaRiferimentoContabileInput;

    /**
     * Gets the value of the eliminaRiferimentoContabileInput property.
     * 
     * @return
     *     possible object is
     *     {@link EliminaRiferimentoContabileInput }
     *     
     */
    public EliminaRiferimentoContabileInput getEliminaRiferimentoContabileInput() {
        return eliminaRiferimentoContabileInput;
    }

    /**
     * Sets the value of the eliminaRiferimentoContabileInput property.
     * 
     * @param value
     *     allowed object is
     *     {@link EliminaRiferimentoContabileInput }
     *     
     */
    public void setEliminaRiferimentoContabileInput(EliminaRiferimentoContabileInput value) {
        this.eliminaRiferimentoContabileInput = value;
    }

}
