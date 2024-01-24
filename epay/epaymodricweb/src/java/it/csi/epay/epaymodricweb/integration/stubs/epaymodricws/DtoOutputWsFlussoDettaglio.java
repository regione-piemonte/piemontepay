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
 * <p>Java class for dtoOutputWsFlussoDettaglio complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dtoOutputWsFlussoDettaglio">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="esito" type="{http://epaymodric.interfacews.epaymodric.epay.csi.it/}dtoOutputWsEsito" minOccurs="0"/>
 *         &lt;element name="flussiDettaglio" type="{http://epaymodric.interfacews.epaymodric.epay.csi.it/}dtoFlussoDettaglio" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="flussoSintesi" type="{http://epaymodric.interfacews.epaymodric.epay.csi.it/}dtoFlussoSintesi" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtoOutputWsFlussoDettaglio", propOrder = {
    "esito",
    "flussiDettaglio",
    "flussoSintesi"
})
public class DtoOutputWsFlussoDettaglio {

    protected DtoOutputWsEsito esito;
    @XmlElement(nillable = true)
    protected List<DtoFlussoDettaglio> flussiDettaglio;
    protected DtoFlussoSintesi flussoSintesi;

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
     * Gets the value of the flussiDettaglio property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the flussiDettaglio property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFlussiDettaglio().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DtoFlussoDettaglio }
     * 
     * 
     */
    public List<DtoFlussoDettaglio> getFlussiDettaglio() {
        if (flussiDettaglio == null) {
            flussiDettaglio = new ArrayList<DtoFlussoDettaglio>();
        }
        return this.flussiDettaglio;
    }

    /**
     * Gets the value of the flussoSintesi property.
     * 
     * @return
     *     possible object is
     *     {@link DtoFlussoSintesi }
     *     
     */
    public DtoFlussoSintesi getFlussoSintesi() {
        return flussoSintesi;
    }

    /**
     * Sets the value of the flussoSintesi property.
     * 
     * @param value
     *     allowed object is
     *     {@link DtoFlussoSintesi }
     *     
     */
    public void setFlussoSintesi(DtoFlussoSintesi value) {
        this.flussoSintesi = value;
    }

}
