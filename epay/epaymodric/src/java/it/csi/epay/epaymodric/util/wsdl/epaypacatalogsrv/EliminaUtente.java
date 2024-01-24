/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.util.wsdl.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for eliminaUtente complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="eliminaUtente">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="eliminaUtenteInput" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}eliminaUtenteInput" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "eliminaUtente", propOrder = {
    "eliminaUtenteInput"
})
public class EliminaUtente {

    protected EliminaUtenteInput eliminaUtenteInput;

    /**
     * Gets the value of the eliminaUtenteInput property.
     * 
     * @return
     *     possible object is
     *     {@link EliminaUtenteInput }
     *     
     */
    public EliminaUtenteInput getEliminaUtenteInput() {
        return eliminaUtenteInput;
    }

    /**
     * Sets the value of the eliminaUtenteInput property.
     * 
     * @param value
     *     allowed object is
     *     {@link EliminaUtenteInput }
     *     
     */
    public void setEliminaUtenteInput(EliminaUtenteInput value) {
        this.eliminaUtenteInput = value;
    }

}
