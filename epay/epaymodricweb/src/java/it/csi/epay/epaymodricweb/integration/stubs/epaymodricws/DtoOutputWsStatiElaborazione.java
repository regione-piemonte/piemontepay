/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodricweb.integration.stubs.epaymodricws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dtoOutputWsStatiElaborazione complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dtoOutputWsStatiElaborazione">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="statiElaborazione" type="{http://epaymodric.interfacews.epaymodric.epay.csi.it/}dtoStatoElaborazione" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtoOutputWsStatiElaborazione", propOrder = {
    "statiElaborazione"
})
public class DtoOutputWsStatiElaborazione {

    @XmlElement(nillable = true)
    protected List<DtoStatoElaborazione> statiElaborazione;

    /**
     * Gets the value of the statiElaborazione property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the statiElaborazione property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStatiElaborazione().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DtoStatoElaborazione }
     * 
     * 
     */
    public List<DtoStatoElaborazione> getStatiElaborazione() {
        if (statiElaborazione == null) {
            statiElaborazione = new ArrayList<DtoStatoElaborazione>();
        }
        return this.statiElaborazione;
    }

}
