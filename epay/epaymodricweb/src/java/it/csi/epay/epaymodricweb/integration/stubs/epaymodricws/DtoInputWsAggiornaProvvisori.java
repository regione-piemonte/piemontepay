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
 * <p>Java class for dtoInputWsAggiornaProvvisori complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dtoInputWsAggiornaProvvisori">
 *   &lt;complexContent>
 *     &lt;extension base="{http://epaymodric.interfacews.epaymodric.epay.csi.it/}dtoInputBase">
 *       &lt;sequence>
 *         &lt;element name="dtoProvvisorioList" type="{http://epaymodric.interfacews.epaymodric.epay.csi.it/}dtoProvvisorio" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="identificativoEnte" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nonBatch" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtoInputWsAggiornaProvvisori", propOrder = {
    "dtoProvvisorioList",
    "identificativoEnte",
    "nonBatch"
})
public class DtoInputWsAggiornaProvvisori
    extends DtoInputBase
{

    @XmlElement(nillable = true)
    protected List<DtoProvvisorio> dtoProvvisorioList;
    protected String identificativoEnte;
    protected Boolean nonBatch;

    /**
     * Gets the value of the dtoProvvisorioList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dtoProvvisorioList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDtoProvvisorioList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DtoProvvisorio }
     * 
     * 
     */
    public List<DtoProvvisorio> getDtoProvvisorioList() {
        if (dtoProvvisorioList == null) {
            dtoProvvisorioList = new ArrayList<DtoProvvisorio>();
        }
        return this.dtoProvvisorioList;
    }

    /**
     * Gets the value of the identificativoEnte property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentificativoEnte() {
        return identificativoEnte;
    }

    /**
     * Sets the value of the identificativoEnte property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentificativoEnte(String value) {
        this.identificativoEnte = value;
    }

    /**
     * Gets the value of the nonBatch property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isNonBatch() {
        return nonBatch;
    }

    /**
     * Sets the value of the nonBatch property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setNonBatch(Boolean value) {
        this.nonBatch = value;
    }

}
