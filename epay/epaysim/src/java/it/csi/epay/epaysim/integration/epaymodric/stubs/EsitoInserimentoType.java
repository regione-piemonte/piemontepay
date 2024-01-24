/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysim.integration.epaymodric.stubs;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EsitoInserimentoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EsitoInserimentoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ElencoPosizioniDebitorieInserite">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="PosizioneDebitoriaInserita" type="{http://www.csi.it/epay/epaywso/types}PosizioneDebitoriaType" maxOccurs="unbounded"/>
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
@XmlType(name = "EsitoInserimentoType", propOrder = {
    "elencoPosizioniDebitorieInserite"
})
public class EsitoInserimentoType {

    @XmlElement(name = "ElencoPosizioniDebitorieInserite", required = true)
    protected EsitoInserimentoType.ElencoPosizioniDebitorieInserite elencoPosizioniDebitorieInserite;

    /**
     * Gets the value of the elencoPosizioniDebitorieInserite property.
     * 
     * @return
     *     possible object is
     *     {@link EsitoInserimentoType.ElencoPosizioniDebitorieInserite }
     *     
     */
    public EsitoInserimentoType.ElencoPosizioniDebitorieInserite getElencoPosizioniDebitorieInserite() {
        return elencoPosizioniDebitorieInserite;
    }

    /**
     * Sets the value of the elencoPosizioniDebitorieInserite property.
     * 
     * @param value
     *     allowed object is
     *     {@link EsitoInserimentoType.ElencoPosizioniDebitorieInserite }
     *     
     */
    public void setElencoPosizioniDebitorieInserite(EsitoInserimentoType.ElencoPosizioniDebitorieInserite value) {
        this.elencoPosizioniDebitorieInserite = value;
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
     *         &lt;element name="PosizioneDebitoriaInserita" type="{http://www.csi.it/epay/epaywso/types}PosizioneDebitoriaType" maxOccurs="unbounded"/>
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
        "posizioneDebitoriaInserita"
    })
    public static class ElencoPosizioniDebitorieInserite {

        @XmlElement(name = "PosizioneDebitoriaInserita", required = true)
        protected List<PosizioneDebitoriaType> posizioneDebitoriaInserita;

        /**
         * Gets the value of the posizioneDebitoriaInserita property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the posizioneDebitoriaInserita property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getPosizioneDebitoriaInserita().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link PosizioneDebitoriaType }
         * 
         * 
         */
        public List<PosizioneDebitoriaType> getPosizioneDebitoriaInserita() {
            if (posizioneDebitoriaInserita == null) {
                posizioneDebitoriaInserita = new ArrayList<PosizioneDebitoriaType>();
            }
            return this.posizioneDebitoriaInserita;
        }

    }

}
