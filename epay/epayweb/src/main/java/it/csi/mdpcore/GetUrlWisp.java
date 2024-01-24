/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdpcore;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per getUrlWisp complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="getUrlWisp">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="applicationId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="transazione" type="{http://interfacecxf.core.mdp.csi.it/}Transazione" minOccurs="0"/>
 *         &lt;element name="parametriUrlWisp" type="{http://interfacecxf.core.mdp.csi.it/}parametriUrlWisp" minOccurs="0"/>
 *         &lt;element name="parametriAggiuntivi" type="{http://interfacecxf.core.mdp.csi.it/}ChiaveValore" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getUrlWisp", propOrder = {
    "applicationId",
    "transazione",
    "parametriUrlWisp",
    "parametriAggiuntivi"
})
public class GetUrlWisp {

    protected String applicationId;
    protected Transazione transazione;
    protected ParametriUrlWisp parametriUrlWisp;
    protected List<ChiaveValore> parametriAggiuntivi;

    /**
     * Recupera il valore della proprieta' applicationId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApplicationId() {
        return applicationId;
    }

    /**
     * Imposta il valore della proprieta' applicationId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApplicationId(String value) {
        this.applicationId = value;
    }

    /**
     * Recupera il valore della proprieta' transazione.
     * 
     * @return
     *     possible object is
     *     {@link Transazione }
     *     
     */
    public Transazione getTransazione() {
        return transazione;
    }

    /**
     * Imposta il valore della proprieta' transazione.
     * 
     * @param value
     *     allowed object is
     *     {@link Transazione }
     *     
     */
    public void setTransazione(Transazione value) {
        this.transazione = value;
    }

    /**
     * Recupera il valore della proprieta' parametriUrlWisp.
     * 
     * @return
     *     possible object is
     *     {@link ParametriUrlWisp }
     *     
     */
    public ParametriUrlWisp getParametriUrlWisp() {
        return parametriUrlWisp;
    }

    /**
     * Imposta il valore della proprieta' parametriUrlWisp.
     * 
     * @param value
     *     allowed object is
     *     {@link ParametriUrlWisp }
     *     
     */
    public void setParametriUrlWisp(ParametriUrlWisp value) {
        this.parametriUrlWisp = value;
    }

    /**
     * Gets the value of the parametriAggiuntivi property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the parametriAggiuntivi property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getParametriAggiuntivi().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ChiaveValore }
     * 
     * 
     */
    public List<ChiaveValore> getParametriAggiuntivi() {
        if (parametriAggiuntivi == null) {
            parametriAggiuntivi = new ArrayList<ChiaveValore>();
        }
        return this.parametriAggiuntivi;
    }

}
