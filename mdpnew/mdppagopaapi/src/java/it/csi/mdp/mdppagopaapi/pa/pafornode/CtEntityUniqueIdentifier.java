/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdppagopaapi.pa.pafornode;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ctEntityUniqueIdentifier complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ctEntityUniqueIdentifier">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="entityUniqueIdentifierType" type="{http://pagopa-api.pagopa.gov.it/pa/paForNode.xsd}stEntityUniqueIdentifierType"/>
 *         &lt;element name="entityUniqueIdentifierValue" type="{http://pagopa-api.pagopa.gov.it/pa/paForNode.xsd}stEntityUniqueIdentifierValue"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ctEntityUniqueIdentifier", propOrder = {
    "entityUniqueIdentifierType",
    "entityUniqueIdentifierValue"
})
public class CtEntityUniqueIdentifier {

    @XmlElement(required = true)
    protected StEntityUniqueIdentifierType entityUniqueIdentifierType;
    @XmlElement(required = true)
    protected String entityUniqueIdentifierValue;

    /**
     * Gets the value of the entityUniqueIdentifierType property.
     * 
     * @return
     *     possible object is
     *     {@link StEntityUniqueIdentifierType }
     *     
     */
    public StEntityUniqueIdentifierType getEntityUniqueIdentifierType() {
        return entityUniqueIdentifierType;
    }

    /**
     * Sets the value of the entityUniqueIdentifierType property.
     * 
     * @param value
     *     allowed object is
     *     {@link StEntityUniqueIdentifierType }
     *     
     */
    public void setEntityUniqueIdentifierType(StEntityUniqueIdentifierType value) {
        this.entityUniqueIdentifierType = value;
    }

    /**
     * Gets the value of the entityUniqueIdentifierValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEntityUniqueIdentifierValue() {
        return entityUniqueIdentifierValue;
    }

    /**
     * Sets the value of the entityUniqueIdentifierValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEntityUniqueIdentifierValue(String value) {
        this.entityUniqueIdentifierValue = value;
    }

}
