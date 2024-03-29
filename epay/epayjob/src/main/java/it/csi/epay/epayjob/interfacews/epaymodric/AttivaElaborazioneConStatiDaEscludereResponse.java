/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayjob.interfacews.epaymodric;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for attivaElaborazioneConStatiDaEscludereResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="attivaElaborazioneConStatiDaEscludereResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="result" type="{http://batch.epaymodric.interfacews.epaymodric.epay.csi.it/}dtoOutputWsMotoreDiRiconciliazione" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "attivaElaborazioneConStatiDaEscludereResponse", propOrder = {
    "result"
})
public class AttivaElaborazioneConStatiDaEscludereResponse {

    protected DtoOutputWsMotoreDiRiconciliazione result;

    /**
     * Gets the value of the result property.
     * 
     * @return
     *     possible object is
     *     {@link DtoOutputWsMotoreDiRiconciliazione }
     *     
     */
    public DtoOutputWsMotoreDiRiconciliazione getResult() {
        return result;
    }

    /**
     * Sets the value of the result property.
     * 
     * @param value
     *     allowed object is
     *     {@link DtoOutputWsMotoreDiRiconciliazione }
     *     
     */
    public void setResult(DtoOutputWsMotoreDiRiconciliazione value) {
        this.result = value;
    }

}
