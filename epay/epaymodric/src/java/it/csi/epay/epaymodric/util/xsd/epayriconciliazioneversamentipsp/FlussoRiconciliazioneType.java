/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

//
// Questo file  stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.3.0
// Vedere <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a>
// Qualsiasi modifica a questo file andr persa durante la ricompilazione dello schema di origine.
// Generato il: 2018.10.25 alle 10:35:00 AM CEST
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
 * <p>Classe Java per FlussoRiconciliazioneType complex type.
 *
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 *
 * <pre>
 * &lt;complexType name="FlussoRiconciliazioneType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="TestataFlusso" type="{http://www.csi.it/epay/epaywso/rendicontazione}TestataFlussoRiconciliazioneType"/&gt;
 *         &lt;element name="RigheSintesi"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="SingolaRigaSintesi" type="{http://www.csi.it/epay/epaywso/rendicontazione}FlussoSintesiType" maxOccurs="unbounded"/&gt;
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
@XmlType ( name = "FlussoRiconciliazione", namespace = "http://www.csi.it/epay/epaywso/rendicontazione", propOrder = {
    "testataFlusso",
    "righeSintesi"
})
public class FlussoRiconciliazioneType {

    @XmlElement(name = "TestataFlusso", required = true)
    protected TestataFlussoRiconciliazioneType testataFlusso;
    @XmlElement(name = "RigheSintesi", required = true)
    protected FlussoRiconciliazioneType.RigheSintesi righeSintesi;

    /**
     * Recupera il valore della propriet testataFlusso.
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
     * Imposta il valore della propriet testataFlusso.
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
     * Recupera il valore della propriet righeSintesi.
     *
     * @return
     *     possible object is
     *     {@link FlussoRiconciliazioneType.RigheSintesi }
     *
     */
    public FlussoRiconciliazioneType.RigheSintesi getRigheSintesi() {
        return righeSintesi;
    }

    /**
     * Imposta il valore della propriet righeSintesi.
     *
     * @param value
     *     allowed object is
     *     {@link FlussoRiconciliazioneType.RigheSintesi }
     *
     */
    public void setRigheSintesi(FlussoRiconciliazioneType.RigheSintesi value) {
        this.righeSintesi = value;
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
    @XmlType(name = "", propOrder = {
        "singolaRigaSintesi"
    })
    public static class RigheSintesi {

        @XmlElement(name = "SingolaRigaSintesi", namespace = "http://www.csi.it/epay/epaywso/rendicontazione", required = true)
        protected List<FlussoSintesiType> singolaRigaSintesi;

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

}
