/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

//
// Questo file  stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.3.0
// Vedere <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a>
// Qualsiasi modifica a questo file andr persa durante la ricompilazione dello schema di origine.
// Generato il: 2018.10.25 alle 11:56:43 AM CEST
//


package it.csi.epay.epaymodric.util.xsd.epayriconciliazioneversamentipsp;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per ElencoFlussiInErroreType complex type.
 *
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 *
 * <pre>
 * &lt;complexType name="ElencoFlussiInErroreType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="TestataFlussi" type="{http://www.csi.it/epay/epaywso/rendicontazione}TestataFlussiErroreType"/&gt;
 *         &lt;element name="RigheFlussi" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="FlussoErrato" type="{http://www.csi.it/epay/epaywso/rendicontazione}FlussiInErroreType" maxOccurs="unbounded"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ElencoFlussiInErroreType", namespace = "http://www.csi.it/epay/epaywso/rendicontazione", propOrder = {
    "testataFlussi",
    "righeFlussi"
})
public class ElencoFlussiInErroreType {

    @XmlElement(name = "TestataFlussi", required = true)
    protected TestataFlussiErroreType testataFlussi;
    @XmlElement(name = "RigheFlussi")
    protected ElencoFlussiInErroreType.RigheFlussi righeFlussi;

    /**
     * Recupera il valore della propriet testataFlussi.
     *
     * @return
     *     possible object is
     *     {@link TestataFlussiErroreType }
     *
     */
    public TestataFlussiErroreType getTestataFlussi() {
        return testataFlussi;
    }

    /**
     * Imposta il valore della propriet testataFlussi.
     *
     * @param value
     *     allowed object is
     *     {@link TestataFlussiErroreType }
     *
     */
    public void setTestataFlussi(TestataFlussiErroreType value) {
        this.testataFlussi = value;
    }

    /**
     * Recupera il valore della propriet righeFlussi.
     *
     * @return
     *     possible object is
     *     {@link ElencoFlussiInErroreType.RigheFlussi }
     *
     */
    public ElencoFlussiInErroreType.RigheFlussi getRigheFlussi() {
        return righeFlussi;
    }

    /**
     * Imposta il valore della propriet righeFlussi.
     *
     * @param value
     *     allowed object is
     *     {@link ElencoFlussiInErroreType.RigheFlussi }
     *
     */
    public void setRigheFlussi(ElencoFlussiInErroreType.RigheFlussi value) {
        this.righeFlussi = value;
    }


    /**
     * <p>Classe Java per anonymous complex type.
     *
     * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
     *
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
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
    @XmlType(name = "", propOrder = {
        "flussoErrato"
    })
    public static class RigheFlussi {

        @XmlElement(name = "FlussoErrato", namespace = "http://www.csi.it/epay/epaywso/rendicontazione", required = true)
        protected List<FlussiInErroreType> flussoErrato;

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

}
