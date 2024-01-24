/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymdpservices.webservices.epaywso;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per anonymous complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Testata" type="{http://www.csi.it/epay/epaywso/epaywso2enti/types}TestataRTType"/>
 *         &lt;element name="CorpoRT" type="{http://www.csi.it/epay/epaywso/epaywso2enti/types}CorpoRTType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "testata",
    "corpoRT"
})
@XmlRootElement(name = "TrasmettiRTRequest")
public class TrasmettiRTRequest {

    @XmlElement(name = "Testata", required = true)
    protected TestataRTType testata;
    @XmlElement(name = "CorpoRT", required = true)
    protected CorpoRTType corpoRT;

    /**
     * Recupera il valore della proprietà testata.
     * 
     * @return
     *     possible object is
     *     {@link TestataRTType }
     *     
     */
    public TestataRTType getTestata() {
        return testata;
    }

    /**
     * Imposta il valore della proprietà testata.
     * 
     * @param value
     *     allowed object is
     *     {@link TestataRTType }
     *     
     */
    public void setTestata(TestataRTType value) {
        this.testata = value;
    }

    /**
     * Recupera il valore della proprietà corpoRT.
     * 
     * @return
     *     possible object is
     *     {@link CorpoRTType }
     *     
     */
    public CorpoRTType getCorpoRT() {
        return corpoRT;
    }

    /**
     * Imposta il valore della proprietà corpoRT.
     * 
     * @param value
     *     allowed object is
     *     {@link CorpoRTType }
     *     
     */
    public void setCorpoRT(CorpoRTType value) {
        this.corpoRT = value;
    }

}
