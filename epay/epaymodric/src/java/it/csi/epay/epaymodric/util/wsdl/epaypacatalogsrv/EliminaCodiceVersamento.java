/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.util.wsdl.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for eliminaCodiceVersamento complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="eliminaCodiceVersamento">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="eliminaCodiceVersamentoInput" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}eliminaCodiceVersamentoInput" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "eliminaCodiceVersamento", propOrder = {
    "eliminaCodiceVersamentoInput"
})
public class EliminaCodiceVersamento {

    protected EliminaCodiceVersamentoInput eliminaCodiceVersamentoInput;

    /**
     * Gets the value of the eliminaCodiceVersamentoInput property.
     * 
     * @return
     *     possible object is
     *     {@link EliminaCodiceVersamentoInput }
     *     
     */
    public EliminaCodiceVersamentoInput getEliminaCodiceVersamentoInput() {
        return eliminaCodiceVersamentoInput;
    }

    /**
     * Sets the value of the eliminaCodiceVersamentoInput property.
     * 
     * @param value
     *     allowed object is
     *     {@link EliminaCodiceVersamentoInput }
     *     
     */
    public void setEliminaCodiceVersamentoInput(EliminaCodiceVersamentoInput value) {
        this.eliminaCodiceVersamentoInput = value;
    }

}
