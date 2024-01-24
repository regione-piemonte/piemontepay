/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.util.xsd.trasmissioneflussi;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per TrasmettiFlussiInErroreRequest complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="TrasmettiFlussiInErroreRequest"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="TestataFlusso" type="{http://www.csi.it/epay/epaywso/rendicontazione}TestataFlussiErroreType"/&gt;
 *         &lt;element name="FlussoErrato" type="{http://www.csi.it/epay/epaywso/rendicontazione}FlussiInErroreType" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TrasmettiFlussiInErroreRequest", namespace = "http://www.csi.it/epay/epaywso/rendicontazione", propOrder = {
    "testataFlusso",
    "flussoErrato"
})
public class TrasmettiFlussiInErroreRequest {

    @XmlElement(name = "TestataFlusso", required = true)
    protected TestataFlussiErroreType testataFlusso;
    @XmlElement(name = "FlussoErrato", required = true)
    protected List<FlussiInErroreType> flussoErrato;

    /**
     * Recupera il valore della proprieta' testataFlusso.
     * 
     * @return
     *     possible object is
     *     {@link TestataFlussiErroreType }
     *     
     */
    public TestataFlussiErroreType getTestataFlusso() {
        return testataFlusso;
    }

    /**
     * Imposta il valore della proprieta' testataFlusso.
     * 
     * @param value
     *     allowed object is
     *     {@link TestataFlussiErroreType }
     *     
     */
    public void setTestataFlusso(TestataFlussiErroreType value) {
        this.testataFlusso = value;
    }

    /**
     * Gets the value of the flussoErrato property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the flussoErrato property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFlussoErrato().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FlussiInErroreType }
     * 
     * 
     */
    public List<FlussiInErroreType> getFlussoErrato() {
        if (flussoErrato == null) {
            flussoErrato = new ArrayList<FlussiInErroreType>();
        }
        return this.flussoErrato;
    }

}
