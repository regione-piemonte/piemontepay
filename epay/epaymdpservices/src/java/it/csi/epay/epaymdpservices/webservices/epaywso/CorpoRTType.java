/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymdpservices.webservices.epaywso;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per CorpoRTType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="CorpoRTType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ElencoRT">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="RT" type="{http://www.csi.it/epay/epaywso/epaywso2enti/types}RTType" maxOccurs="1000"/>
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
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CorpoRTType", propOrder = {
    "elencoRT"
})
public class CorpoRTType {

    @XmlElement(name = "ElencoRT", required = true)
    protected CorpoRTType.ElencoRT elencoRT;

    /**
     * Recupera il valore della proprietà elencoRT.
     * 
     * @return
     *     possible object is
     *     {@link CorpoRTType.ElencoRT }
     *     
     */
    public CorpoRTType.ElencoRT getElencoRT() {
        return elencoRT;
    }

    /**
     * Imposta il valore della proprietà elencoRT.
     * 
     * @param value
     *     allowed object is
     *     {@link CorpoRTType.ElencoRT }
     *     
     */
    public void setElencoRT(CorpoRTType.ElencoRT value) {
        this.elencoRT = value;
    }


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
     *         &lt;element name="RT" type="{http://www.csi.it/epay/epaywso/epaywso2enti/types}RTType" maxOccurs="1000"/>
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
        "rt"
    })
    public static class ElencoRT {

        @XmlElement(name = "RT", required = true)
        protected List<RTType> rt;

        /**
         * Gets the value of the rt property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the rt property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getRT().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link RTType }
         * 
         * 
         */
        public List<RTType> getRT() {
            if (rt == null) {
                rt = new ArrayList<RTType>();
            }
            return this.rt;
        }

    }

}
