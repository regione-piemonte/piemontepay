/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayservices.ws.EPaywso2SportelloService;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per ElencoPosizioniDaAggiornareType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ElencoPosizioniDaAggiornareType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PosizioniDaAggiornare">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="PosizioneDaAggiornare" type="{http://www.csi.it/epay/epaywso/enti2epaywso/types}PosizioneDaAggiornareType" maxOccurs="1000"/>
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
@XmlType(name = "ElencoPosizioniDaAggiornareType", propOrder = {
    "posizioniDaAggiornare"
})
public class ElencoPosizioniDaAggiornareType {

    @XmlElement(name = "PosizioniDaAggiornare", required = true)
    protected ElencoPosizioniDaAggiornareType.PosizioniDaAggiornare posizioniDaAggiornare;

    /**
     * Recupera il valore della proprieta' posizioniDaAggiornare.
     * 
     * @return
     *     possible object is
     *     {@link ElencoPosizioniDaAggiornareType.PosizioniDaAggiornare }
     *     
     */
    public ElencoPosizioniDaAggiornareType.PosizioniDaAggiornare getPosizioniDaAggiornare() {
        return posizioniDaAggiornare;
    }

    /**
     * Imposta il valore della proprieta' posizioniDaAggiornare.
     * 
     * @param value
     *     allowed object is
     *     {@link ElencoPosizioniDaAggiornareType.PosizioniDaAggiornare }
     *     
     */
    public void setPosizioniDaAggiornare(ElencoPosizioniDaAggiornareType.PosizioniDaAggiornare value) {
        this.posizioniDaAggiornare = value;
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
     *         &lt;element name="PosizioneDaAggiornare" type="{http://www.csi.it/epay/epaywso/enti2epaywso/types}PosizioneDaAggiornareType" maxOccurs="1000"/>
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
        "posizioneDaAggiornare"
    })
    public static class PosizioniDaAggiornare {

        @XmlElement(name = "PosizioneDaAggiornare", required = true)
        protected List<PosizioneDaAggiornareType> posizioneDaAggiornare;

        /**
         * Gets the value of the posizioneDaAggiornare property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the posizioneDaAggiornare property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getPosizioneDaAggiornare().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link PosizioneDaAggiornareType }
         * 
         * 
         */
        public List<PosizioneDaAggiornareType> getPosizioneDaAggiornare() {
            if (posizioneDaAggiornare == null) {
                posizioneDaAggiornare = new ArrayList<PosizioneDaAggiornareType>();
            }
            return this.posizioneDaAggiornare;
        }

    }

}
