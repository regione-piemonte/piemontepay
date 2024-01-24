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
 * <p>Java class for dtoOutputWsStatoFlusso complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dtoOutputWsStatoFlusso">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="esito" type="{http://epaymodric.interfacews.epaymodric.epay.csi.it/}dtoOutputWsEsito" minOccurs="0"/>
 *         &lt;element name="statiFlusso" type="{http://epaymodric.interfacews.epaymodric.epay.csi.it/}dtoStatoFlusso" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtoOutputWsStatoFlusso", propOrder = {
    "esito",
    "statiFlusso"
})
public class DtoOutputWsStatoFlusso {

    protected DtoOutputWsEsito esito;
    @XmlElement(nillable = true)
    protected List<DtoStatoFlusso> statiFlusso;

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
     * Gets the value of the statiFlusso property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the statiFlusso property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStatiFlusso().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DtoStatoFlusso }
     * 
     * 
     */
    public List<DtoStatoFlusso> getStatiFlusso() {
        if (statiFlusso == null) {
            statiFlusso = new ArrayList<DtoStatoFlusso>();
        }
        return this.statiFlusso;
    }

}
