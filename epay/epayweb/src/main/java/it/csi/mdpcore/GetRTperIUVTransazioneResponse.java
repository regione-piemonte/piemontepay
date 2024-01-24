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
 * <p>Classe Java per getRTperIUVTransazioneResponse complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="getRTperIUVTransazioneResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://interfacecxf.core.mdp.csi.it/}ricevutaTelematicaNodo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getRTperIUVTransazioneResponse", propOrder = {
    "_return"
})
public class GetRTperIUVTransazioneResponse {

    @XmlElement(name = "return")
    protected RicevutaTelematicaNodo _return;

    /**
     * Recupera il valore della proprieta' return.
     * 
     * @return
     *     possible object is
     *     {@link RicevutaTelematicaNodo }
     *     
     */
    public RicevutaTelematicaNodo getReturn() {
        return _return;
    }

    /**
     * Imposta il valore della proprieta' return.
     * 
     * @param value
     *     allowed object is
     *     {@link RicevutaTelematicaNodo }
     *     
     */
    public void setReturn(RicevutaTelematicaNodo value) {
        this._return = value;
    }

}
