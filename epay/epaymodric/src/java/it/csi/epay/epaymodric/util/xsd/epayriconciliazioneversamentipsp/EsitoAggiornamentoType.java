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
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per EsitoAggiornamentoType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="EsitoAggiornamentoType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ElencoPosizioniDebitorieAggiornate"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="PosizioneDebitoriaAggiornata" type="{http://www.csi.it/epay/epaywso/types}PosizioneDebitoriaType" maxOccurs="1000"/&gt;
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
@XmlType(name = "EsitoAggiornamentoType", propOrder = {
    "elencoPosizioniDebitorieAggiornate"
})
public class EsitoAggiornamentoType {

    @XmlElement(name = "ElencoPosizioniDebitorieAggiornate", required = true)
    protected EsitoAggiornamentoType.ElencoPosizioniDebitorieAggiornate elencoPosizioniDebitorieAggiornate;

    /**
     * Recupera il valore della propriet elencoPosizioniDebitorieAggiornate.
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
     * Imposta il valore della propriet elencoPosizioniDebitorieAggiornate.
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
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="PosizioneDebitoriaAggiornata" type="{http://www.csi.it/epay/epaywso/types}PosizioneDebitoriaType" maxOccurs="1000"/&gt;
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
        "posizioneDebitoriaAggiornata"
    })
    public static class ElencoPosizioniDebitorieAggiornate {

        @XmlElement(name = "PosizioneDebitoriaAggiornata", required = true)
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
