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
 * <p>Classe Java per ListaDiCarico complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ListaDiCarico">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PosizioniDaInserire">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="PosizioneDaInserire" type="{http://www.csi.it/epay/epaywso/enti2epaywso/types}PosizioneDaInserireType" maxOccurs="1000"/>
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
@XmlType(name = "ListaDiCarico", propOrder = {
    "posizioniDaInserire"
})
public class ListaDiCarico {

    @XmlElement(name = "PosizioniDaInserire", required = true)
    protected ListaDiCarico.PosizioniDaInserire posizioniDaInserire;

    /**
     * Recupera il valore della proprieta' posizioniDaInserire.
     * 
     * @return
     *     possible object is
     *     {@link ListaDiCarico.PosizioniDaInserire }
     *     
     */
    public ListaDiCarico.PosizioniDaInserire getPosizioniDaInserire() {
        return posizioniDaInserire;
    }

    /**
     * Imposta il valore della proprieta' posizioniDaInserire.
     * 
     * @param value
     *     allowed object is
     *     {@link ListaDiCarico.PosizioniDaInserire }
     *     
     */
    public void setPosizioniDaInserire(ListaDiCarico.PosizioniDaInserire value) {
        this.posizioniDaInserire = value;
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
     *         &lt;element name="PosizioneDaInserire" type="{http://www.csi.it/epay/epaywso/enti2epaywso/types}PosizioneDaInserireType" maxOccurs="1000"/>
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
        "posizioneDaInserire"
    })
    public static class PosizioniDaInserire {

        @XmlElement(name = "PosizioneDaInserire", required = true)
        protected List<PosizioneDaInserireType> posizioneDaInserire;

        /**
         * Gets the value of the posizioneDaInserire property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the posizioneDaInserire property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getPosizioneDaInserire().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link PosizioneDaInserireType }
         * 
         * 
         */
        public List<PosizioneDaInserireType> getPosizioneDaInserire() {
            if (posizioneDaInserire == null) {
                posizioneDaInserire = new ArrayList<PosizioneDaInserireType>();
            }
            return this.posizioneDaInserire;
        }

    }

}
