/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayjob.interfacews.epaymodric;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for attivaElaborazioneConStatiDaEscludere complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="attivaElaborazioneConStatiDaEscludere">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="attivaElaborazioneInputConStatiDaEscludere" type="{http://batch.epaymodric.interfacews.epaymodric.epay.csi.it/}dtoInputWsAttivaElaborazioneConStatiDaEscludere" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "attivaElaborazioneConStatiDaEscludere", propOrder = {
    "attivaElaborazioneInputConStatiDaEscludere"
})
public class AttivaElaborazioneConStatiDaEscludere {

    protected DtoInputWsAttivaElaborazioneConStatiDaEscludere attivaElaborazioneInputConStatiDaEscludere;

    /**
     * Gets the value of the attivaElaborazioneInputConStatiDaEscludere property.
     * 
     * @return
     *     possible object is
     *     {@link DtoInputWsAttivaElaborazioneConStatiDaEscludere }
     *     
     */
    public DtoInputWsAttivaElaborazioneConStatiDaEscludere getAttivaElaborazioneInputConStatiDaEscludere() {
        return attivaElaborazioneInputConStatiDaEscludere;
    }

    /**
     * Sets the value of the attivaElaborazioneInputConStatiDaEscludere property.
     * 
     * @param value
     *     allowed object is
     *     {@link DtoInputWsAttivaElaborazioneConStatiDaEscludere }
     *     
     */
    public void setAttivaElaborazioneInputConStatiDaEscludere(DtoInputWsAttivaElaborazioneConStatiDaEscludere value) {
        this.attivaElaborazioneInputConStatiDaEscludere = value;
    }

}
