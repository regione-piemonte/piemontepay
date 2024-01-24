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
 * <p>Classe Java per EsitoInserimentoType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="EsitoInserimentoType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ElencoPosizioniDebitorieInserite"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="PosizioneDebitoriaInserita" type="{http://www.csi.it/epay/epaywso/types}PosizioneDebitoriaType" maxOccurs="1000"/&gt;
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
@XmlType(name = "EsitoInserimentoType", propOrder = {
    "elencoPosizioniDebitorieInserite"
})
public class EsitoInserimentoType {

    @XmlElement(name = "ElencoPosizioniDebitorieInserite", required = true)
    protected EsitoInserimentoType.ElencoPosizioniDebitorieInserite elencoPosizioniDebitorieInserite;

    /**
     * Recupera il valore della propriet elencoPosizioniDebitorieInserite.
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
     * Imposta il valore della propriet elencoPosizioniDebitorieInserite.
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
     * <p>Classe Java per anonymous complex type.
     * 
     * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="PosizioneDebitoriaInserita" type="{http://www.csi.it/epay/epaywso/types}PosizioneDebitoriaType" maxOccurs="1000"/&gt;
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
