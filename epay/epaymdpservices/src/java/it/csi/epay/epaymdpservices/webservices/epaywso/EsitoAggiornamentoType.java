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
 * <p>Classe Java per EsitoAggiornamentoType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="EsitoAggiornamentoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ElencoPosizioniDebitorieAggiornate">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="PosizioneDebitoriaAggiornata" type="{http://www.csi.it/epay/epaywso/types}PosizioneDebitoriaType" maxOccurs="1000"/>
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
@XmlType(name = "EsitoAggiornamentoType", namespace = "http://www.csi.it/epay/epaywso/types", propOrder = {
    "elencoPosizioniDebitorieAggiornate"
})
public class EsitoAggiornamentoType {

    @XmlElement(name = "ElencoPosizioniDebitorieAggiornate", required = true)
    protected EsitoAggiornamentoType.ElencoPosizioniDebitorieAggiornate elencoPosizioniDebitorieAggiornate;

    /**
     * Recupera il valore della proprietÓ elencoPosizioniDebitorieAggiornate.
     * 
     * @return
     *     possible object is
     *     {@link EsitoAggiornamentoType.ElencoPosizioniDebitorieAggiornate }
     *     
     */
    public EsitoAggiornamentoType.ElencoPosizioniDebitorieAggiornate getElencoPosizioniDebitorieAggiornate() {
        return elencoPosizioniDebitorieAggiornate;
    }

    /**
     * Imposta il valore della proprietÓ elencoPosizioniDebitorieAggiornate.
     * 
     * @param value
     *     allowed object is
     *     {@link EsitoAggiornamentoType.ElencoPosizioniDebitorieAggiornate }
     *     
     */
    public void setElencoPosizioniDebitorieAggiornate(EsitoAggiornamentoType.ElencoPosizioniDebitorieAggiornate value) {
        this.elencoPosizioniDebitorieAggiornate = value;
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
     *         &lt;element name="PosizioneDebitoriaAggiornata" type="{http://www.csi.it/epay/epaywso/types}PosizioneDebitoriaType" maxOccurs="1000"/>
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
        "posizioneDebitoriaAggiornata"
    })
    public static class ElencoPosizioniDebitorieAggiornate {

        @XmlElement(name = "PosizioneDebitoriaAggiornata", namespace = "http://www.csi.it/epay/epaywso/types", required = true)
        protected List<PosizioneDebitoriaType> posizioneDebitoriaAggiornata;

        /**
         * Gets the value of the posizioneDebitoriaAggiornata property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the posizioneDebitoriaAggiornata property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getPosizioneDebitoriaAggiornata().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link PosizioneDebitoriaType }
         * 
         * 
         */
        public List<PosizioneDebitoriaType> getPosizioneDebitoriaAggiornata() {
            if (posizioneDebitoriaAggiornata == null) {
                posizioneDebitoriaAggiornata = new ArrayList<PosizioneDebitoriaType>();
            }
            return this.posizioneDebitoriaAggiornata;
        }

    }

}
