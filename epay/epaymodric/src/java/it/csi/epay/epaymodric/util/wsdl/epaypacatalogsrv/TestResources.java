/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.util.wsdl.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for testResources complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="testResources">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="testResourcesInput" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}testResourcesInput" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "testResources", propOrder = {
    "testResourcesInput"
})
public class TestResources {

    protected TestResourcesInput testResourcesInput;

    /**
     * Gets the value of the testResourcesInput property.
     * 
     * @return
     *     possible object is
     *     {@link TestResourcesInput }
     *     
     */
    public TestResourcesInput getTestResourcesInput() {
        return testResourcesInput;
    }

    /**
     * Sets the value of the testResourcesInput property.
     * 
     * @param value
     *     allowed object is
     *     {@link TestResourcesInput }
     *     
     */
    public void setTestResourcesInput(TestResourcesInput value) {
        this.testResourcesInput = value;
    }

}
