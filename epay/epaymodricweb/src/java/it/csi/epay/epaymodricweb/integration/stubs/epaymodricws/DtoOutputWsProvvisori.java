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
 * <p>Java class for dtoOutputWsProvvisori complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dtoOutputWsProvvisori">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="esito" type="{http://epaymodric.interfacews.epaymodric.epay.csi.it/}dtoOutputWsEsito" minOccurs="0"/>
 *         &lt;element name="provvisori" type="{http://epaymodric.interfacews.epaymodric.epay.csi.it/}dtoProvvisorio" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtoOutputWsProvvisori", propOrder = {
    "esito",
    "provvisori"
})
public class DtoOutputWsProvvisori {

    protected DtoOutputWsEsito esito;
    @XmlElement(nillable = true)
    protected List<DtoProvvisorio> provvisori;

    /**
     * Gets the value of the esito property.
     * 
     * @return
     *     possible object is
     *     {@link DtoOutputWsEsito }
     *     
     */
    public DtoOutputWsEsito getEsito() {
        return esito;
    }

    /**
     * Sets the value of the esito property.
     * 
     * @param value
     *     allowed object is
     *     {@link DtoOutputWsEsito }
     *     
     */
    public void setEsito(DtoOutputWsEsito value) {
        this.esito = value;
    }

    /**
     * Gets the value of the provvisori property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the provvisori property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProvvisori().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DtoProvvisorio }
     * 
     * 
     */
    public List<DtoProvvisorio> getProvvisori() {
        if (provvisori == null) {
            provvisori = new ArrayList<DtoProvvisorio>();
        }
        return this.provvisori;
    }

}
