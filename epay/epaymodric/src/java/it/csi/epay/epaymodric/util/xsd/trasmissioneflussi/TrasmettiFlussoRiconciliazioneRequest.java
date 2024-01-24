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
 * <p>Classe Java per TrasmettiFlussoRiconciliazioneRequest complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="TrasmettiFlussoRiconciliazioneRequest"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="TestataFlusso" type="{http://www.csi.it/epay/epaywso/rendicontazione}TestataFlussoRiconciliazioneType"/&gt;
 *         &lt;element name="SingolaRigaSintesi" type="{http://www.csi.it/epay/epaywso/rendicontazione}FlussoSintesiType" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TrasmettiFlussoRiconciliazioneRequest", namespace = "http://www.csi.it/epay/epaywso/rendicontazione", propOrder = {
    "testataFlusso",
    "singolaRigaSintesi"
})
public class TrasmettiFlussoRiconciliazioneRequest {

    @XmlElement(name = "TestataFlusso", required = true)
    protected TestataFlussoRiconciliazioneType testataFlusso;
    @XmlElement(name = "SingolaRigaSintesi", required = true)
    protected List<FlussoSintesiType> singolaRigaSintesi;

    /**
     * Recupera il valore della proprieta' testataFlusso.
     * 
     * @return
     *     possible object is
     *     {@link TestataFlussoRiconciliazioneType }
     *     
     */
    public TestataFlussoRiconciliazioneType getTestataFlusso() {
        return testataFlusso;
    }

    /**
     * Imposta il valore della proprieta' testataFlusso.
     * 
     * @param value
     *     allowed object is
     *     {@link TestataFlussoRiconciliazioneType }
     *     
     */
    public void setTestataFlusso(TestataFlussoRiconciliazioneType value) {
        this.testataFlusso = value;
    }

    /**
     * Gets the value of the singolaRigaSintesi property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the singolaRigaSintesi property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSingolaRigaSintesi().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FlussoSintesiType }
     * 
     * 
     */
    public List<FlussoSintesiType> getSingolaRigaSintesi() {
        if (singolaRigaSintesi == null) {
            singolaRigaSintesi = new ArrayList<FlussoSintesiType>();
        }
        return this.singolaRigaSintesi;
    }

}
