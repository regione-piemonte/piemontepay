/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysimweb.integration.stubs.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per testResources complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="testResources"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="testResourcesInput" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}testResourcesInput" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
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
     * Recupera il valore della propriet testResourcesInput.
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
     * Imposta il valore della propriet testResourcesInput.
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
