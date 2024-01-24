/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdpcore;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per getStatoTransazioneResponse complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="getStatoTransazioneResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://interfacecxf.core.mdp.csi.it/}Transazione" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getStatoTransazioneResponse", propOrder = {
    "_return"
})
public class GetStatoTransazioneResponse {

    @XmlElement(name = "return")
    protected Transazione _return;

    /**
     * Recupera il valore della proprieta' return.
     * 
     * @return
     *     possible object is
     *     {@link Transazione }
     *     
     */
    public Transazione getReturn() {
        return _return;
    }

    /**
     * Imposta il valore della proprieta' return.
     * 
     * @param value
     *     allowed object is
     *     {@link Transazione }
     *     
     */
    public void setReturn(Transazione value) {
        this._return = value;
    }

}
