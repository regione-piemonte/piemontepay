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
 * <p>Java class for dtoOutputWsFlussoSintesi complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dtoOutputWsFlussoSintesi">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="esito" type="{http://epaymodric.interfacews.epaymodric.epay.csi.it/}dtoOutputWsEsito" minOccurs="0"/>
 *         &lt;element name="flussiSintesi" type="{http://epaymodric.interfacews.epaymodric.epay.csi.it/}dtoFlussoSintesi" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="flussoOrigine" type="{http://epaymodric.interfacews.epaymodric.epay.csi.it/}dtoFlussoOrigine" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtoOutputWsFlussoSintesi", propOrder = {
    "esito",
    "flussiSintesi",
    "flussoOrigine"
})
public class DtoOutputWsFlussoSintesi {

    protected DtoOutputWsEsito esito;
    @XmlElement(nillable = true)
    protected List<DtoFlussoSintesi> flussiSintesi;
    protected DtoFlussoOrigine flussoOrigine;

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
     * Gets the value of the flussiSintesi property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the flussiSintesi property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFlussiSintesi().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DtoFlussoSintesi }
     * 
     * 
     */
    public List<DtoFlussoSintesi> getFlussiSintesi() {
        if (flussiSintesi == null) {
            flussiSintesi = new ArrayList<DtoFlussoSintesi>();
        }
        return this.flussiSintesi;
    }

    /**
     * Gets the value of the flussoOrigine property.
     * 
     * @return
     *     possible object is
     *     {@link DtoFlussoOrigine }
     *     
     */
    public DtoFlussoOrigine getFlussoOrigine() {
        return flussoOrigine;
    }

    /**
     * Sets the value of the flussoOrigine property.
     * 
     * @param value
     *     allowed object is
     *     {@link DtoFlussoOrigine }
     *     
     */
    public void setFlussoOrigine(DtoFlussoOrigine value) {
        this.flussoOrigine = value;
    }

}
