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
 * <p>Classe Java per startTransazioneCarrello complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="startTransazioneCarrello">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="t" type="{http://interfacecxf.core.mdp.csi.it/}Transazione" minOccurs="0"/>
 *         &lt;element name="tea" type="{http://interfacecxf.core.mdp.csi.it/}transazioneExtraAttribute" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="elencoRPT" type="{http://interfacecxf.core.mdp.csi.it/}ElencoRPT" minOccurs="0"/>
 *         &lt;element name="multiBeneficiario" type="{http://interfacecxf.core.mdp.csi.it/}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "startTransazioneCarrello", propOrder = {
    "t",
    "tea",
    "elencoRPT",
    "multiBeneficiario"
})
public class StartTransazioneCarrello {

    protected Transazione t;
    protected List<TransazioneExtraAttribute> tea;
    protected ElencoRPT elencoRPT;
    protected boolean multiBeneficiario;

    /**
     * Recupera il valore della proprieta' t.
     * 
     * @return
     *     possible object is
     *     {@link Transazione }
     *     
     */
    public Transazione getT() {
        return t;
    }

    /**
     * Imposta il valore della proprieta' t.
     * 
     * @param value
     *     allowed object is
     *     {@link Transazione }
     *     
     */
    public void setT(Transazione value) {
        this.t = value;
    }

    /**
     * Gets the value of the tea property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tea property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTea().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TransazioneExtraAttribute }
     * 
     * 
     */
    public List<TransazioneExtraAttribute> getTea() {
        if (tea == null) {
            tea = new ArrayList<TransazioneExtraAttribute>();
        }
        return this.tea;
    }

    /**
     * Recupera il valore della proprieta' elencoRPT.
     * 
     * @return
     *     possible object is
     *     {@link ElencoRPT }
     *     
     */
    public ElencoRPT getElencoRPT() {
        return elencoRPT;
    }

    /**
     * Imposta il valore della proprieta' elencoRPT.
     * 
     * @param value
     *     allowed object is
     *     {@link ElencoRPT }
     *     
     */
    public void setElencoRPT(ElencoRPT value) {
        this.elencoRPT = value;
    }

    /**
     * @return the multiBeneficiario
     */
    public boolean isMultiBeneficiario () {
        return multiBeneficiario;
    }

    /**
     * @param multiBeneficiario the multiBeneficiario to set
     */
    public void setMultiBeneficiario ( boolean multiBeneficiario ) {
        this.multiBeneficiario = multiBeneficiario;
    }

}
