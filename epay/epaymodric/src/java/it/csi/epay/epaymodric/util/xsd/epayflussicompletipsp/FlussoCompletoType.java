/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.util.xsd.epayflussicompletipsp;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FlussoCompletoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FlussoCompletoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TestataFlusso" type="{http://www.csi.it/epay/epaywso/FlussoCompleto}TestataFlussoCompletoType"/>
 *         &lt;element name="RigheSintesi">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="SingolaRigaSintesi" type="{http://www.csi.it/epay/epaywso/FlussoCompleto}FlussoSintesiType" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FlussoCompletoType", propOrder = {
    "testataFlusso",
    "righeSintesi"
})
public class FlussoCompletoType {

    @XmlElement(name = "TestataFlusso", required = true)
    protected TestataFlussoCompletoType testataFlusso;
    @XmlElement(name = "RigheSintesi", required = true)
    protected FlussoCompletoType.RigheSintesi righeSintesi;

    /**
     * Gets the value of the testataFlusso property.
     * 
     * @return
     *     possible object is
     *     {@link TestataFlussoCompletoType }
     *     
     */
    public TestataFlussoCompletoType getTestataFlusso() {
        return testataFlusso;
    }

    /**
     * Sets the value of the testataFlusso property.
     * 
     * @param value
     *     allowed object is
     *     {@link TestataFlussoCompletoType }
     *     
     */
    public void setTestataFlusso(TestataFlussoCompletoType value) {
        this.testataFlusso = value;
    }

    /**
     * Gets the value of the righeSintesi property.
     * 
     * @return
     *     possible object is
     *     {@link FlussoCompletoType.RigheSintesi }
     *     
     */
    public FlussoCompletoType.RigheSintesi getRigheSintesi() {
        return righeSintesi;
    }

    /**
     * Sets the value of the righeSintesi property.
     * 
     * @param value
     *     allowed object is
     *     {@link FlussoCompletoType.RigheSintesi }
     *     
     */
    public void setRigheSintesi(FlussoCompletoType.RigheSintesi value) {
        this.righeSintesi = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="SingolaRigaSintesi" type="{http://www.csi.it/epay/epaywso/FlussoCompleto}FlussoSintesiType" maxOccurs="unbounded"/>
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
        "singolaRigaSintesi"
    })
    public static class RigheSintesi {

        @XmlElement(name = "SingolaRigaSintesi", required = true)
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
