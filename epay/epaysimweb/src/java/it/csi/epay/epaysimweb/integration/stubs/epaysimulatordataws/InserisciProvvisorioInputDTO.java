/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>
 * Java class for inserisciProvvisorioInputDTO complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="inserisciProvvisorioInputDTO">
 *   &lt;complexContent>
 *     &lt;extension base="{http://epaysim.interfacews.epaysim.epay.csi.it/}parentInput">
 *       &lt;sequence>
 *         &lt;element name="provvisorioDTOList" type="{http://epaysim.interfacews.epaysim.epay.csi.it/}provvisorioDTO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="xmlFlusso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="identificativoFlussoBT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "inserisciProvvisorioInputDTO", propOrder = {
    "provvisorioDTOList",
    "xmlFlusso",
    "identificativoFlussoBT"
})
public class InserisciProvvisorioInputDTO
    extends ParentInput
{

    @XmlElement(nillable = true)
    protected List<ProvvisorioDTO> provvisorioDTOList;
    protected String xmlFlusso;

    protected String identificativoFlussoBT;

    /**
     * Gets the value of the provvisorioDTOList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the provvisorioDTOList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProvvisorioDTOList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ProvvisorioDTO }
     * 
     * 
     */
    public List<ProvvisorioDTO> getProvvisorioDTOList() {
        if (provvisorioDTOList == null) {
            provvisorioDTOList = new ArrayList<ProvvisorioDTO>();
        }
        return this.provvisorioDTOList;
    }

    /**
     * Gets the value of the xmlFlusso property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getXmlFlusso() {
        return xmlFlusso;
    }

    /**
     * Sets the value of the xmlFlusso property.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setXmlFlusso(String value) {
        this.xmlFlusso = value;
    }

    /**
     * Gets the value of the identificativoFlussoBT property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getIdentificativoFlussoBT () {
        return identificativoFlussoBT;
    }

    /**
     * Sets the value of the identificativoFlussoBT property.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setIdentificativoFlussoBT ( String value ) {
        this.identificativoFlussoBT = value;
    }

}
