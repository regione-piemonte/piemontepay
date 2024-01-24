/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.sportello2epaywso;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per CorpoRichiesteDiRevocaType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="CorpoRichiesteDiRevocaType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="RichiestaDiRevocaResponse" type="{http://www.csi.it/epay/epaywso/epaywso2enti/types}RichiestaDiRevocaResponseType"/&gt;
 *         &lt;element name="ElencoRichiesteDiRevoca"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="RichiestaDiRevoca" type="{http://www.csi.it/epay/epaywso/epaywso2enti/types}RichiestaDiRevocaType" maxOccurs="1000"/&gt;
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
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CorpoRichiesteDiRevocaType", propOrder = {
    "richiestaDiRevocaResponse",
    "elencoRichiesteDiRevoca"
})
public class CorpoRichiesteDiRevocaType {

    @XmlElement(name = "RichiestaDiRevocaResponse", required = true)
    protected RichiestaDiRevocaResponseType richiestaDiRevocaResponse;
    @XmlElement(name = "ElencoRichiesteDiRevoca", required = true)
    protected CorpoRichiesteDiRevocaType.ElencoRichiesteDiRevoca elencoRichiesteDiRevoca;

    /**
     * Recupera il valore della proprieta richiestaDiRevocaResponse.
     * 
     * @return
     *     possible object is
     *     {@link RichiestaDiRevocaResponseType }
     *     
     */
    public RichiestaDiRevocaResponseType getRichiestaDiRevocaResponse() {
        return richiestaDiRevocaResponse;
    }

    /**
     * Imposta il valore della proprieta richiestaDiRevocaResponse.
     * 
     * @param value
     *     allowed object is
     *     {@link RichiestaDiRevocaResponseType }
     *     
     */
    public void setRichiestaDiRevocaResponse(RichiestaDiRevocaResponseType value) {
        this.richiestaDiRevocaResponse = value;
    }

    /**
     * Recupera il valore della proprieta elencoRichiesteDiRevoca.
     * 
     * @return
     *     possible object is
     *     {@link CorpoRichiesteDiRevocaType.ElencoRichiesteDiRevoca }
     *     
     */
    public CorpoRichiesteDiRevocaType.ElencoRichiesteDiRevoca getElencoRichiesteDiRevoca() {
        return elencoRichiesteDiRevoca;
    }

    /**
     * Imposta il valore della proprieta elencoRichiesteDiRevoca.
     * 
     * @param value
     *     allowed object is
     *     {@link CorpoRichiesteDiRevocaType.ElencoRichiesteDiRevoca }
     *     
     */
    public void setElencoRichiesteDiRevoca(CorpoRichiesteDiRevocaType.ElencoRichiesteDiRevoca value) {
        this.elencoRichiesteDiRevoca = value;
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
     *         &lt;element name="RichiestaDiRevoca" type="{http://www.csi.it/epay/epaywso/epaywso2enti/types}RichiestaDiRevocaType" maxOccurs="1000"/&gt;
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
        "richiestaDiRevoca"
    })
    public static class ElencoRichiesteDiRevoca {

        @XmlElement(name = "RichiestaDiRevoca", required = true)
        protected List<RichiestaDiRevocaType> richiestaDiRevoca;

        /**
         * Gets the value of the richiestaDiRevoca property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the richiestaDiRevoca property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getRichiestaDiRevoca().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link RichiestaDiRevocaType }
         * 
         * 
         */
        public List<RichiestaDiRevocaType> getRichiestaDiRevoca() {
            if (richiestaDiRevoca == null) {
                richiestaDiRevoca = new ArrayList<RichiestaDiRevocaType>();
            }
            return this.richiestaDiRevoca;
        }

    }

}
