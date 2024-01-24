/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayjob.interfacews.epaymodric;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dtoInputBase complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dtoInputBase">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="caller" type="{http://batch.epaymodric.interfacews.epaymodric.epay.csi.it/}dtoCaller" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtoInputBase", propOrder = {
    "caller"
})
@XmlSeeAlso({
    DtoInputWsAttivaElaborazioneConStatiDaEscludere.class,
    DtoInputWsEseguiElaborazione.class,
    DtoInputVuoto.class
})
public class DtoInputBase {

    protected DtoCaller caller;

    /**
     * Gets the value of the caller property.
     * 
     * @return
     *     possible object is
     *     {@link DtoCaller }
     *     
     */
    public DtoCaller getCaller() {
        return caller;
    }

    /**
     * Sets the value of the caller property.
     * 
     * @param value
     *     allowed object is
     *     {@link DtoCaller }
     *     
     */
    public void setCaller(DtoCaller value) {
        this.caller = value;
    }

}
