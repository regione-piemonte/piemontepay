/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayjob.interfacews.epaymodric;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dtoInputWsAttivaElaborazioneConStatiDaEscludere complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dtoInputWsAttivaElaborazioneConStatiDaEscludere">
 *   &lt;complexContent>
 *     &lt;extension base="{http://batch.epaymodric.interfacews.epaymodric.epay.csi.it/}dtoInputBase">
 *       &lt;sequence>
 *         &lt;element name="statiDaEscludere" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtoInputWsAttivaElaborazioneConStatiDaEscludere", propOrder = {
    "statiDaEscludere"
})
public class DtoInputWsAttivaElaborazioneConStatiDaEscludere
    extends DtoInputBase
{

    @XmlElement(nillable = true)
    protected List<String> statiDaEscludere;

    /**
     * Gets the value of the statiDaEscludere property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the statiDaEscludere property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStatiDaEscludere().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getStatiDaEscludere() {
        if (statiDaEscludere == null) {
            statiDaEscludere = new ArrayList<String>();
        }
        return this.statiDaEscludere;
    }

}
