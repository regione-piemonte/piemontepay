/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysimweb.integration.stubs.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per ricercaVoceEntrata complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ricercaVoceEntrata"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ricercaVoceEntrataInput" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}ricercaVoceEntrataInput" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ricercaVoceEntrata", propOrder = {
    "ricercaVoceEntrataInput"
})
public class RicercaVoceEntrata {

    protected RicercaVoceEntrataInput ricercaVoceEntrataInput;

    /**
     * Recupera il valore della propriet ricercaVoceEntrataInput.
     * 
     * @return
     *     possible object is
     *     {@link RicercaVoceEntrataInput }
     *     
     */
    public RicercaVoceEntrataInput getRicercaVoceEntrataInput() {
        return ricercaVoceEntrataInput;
    }

    /**
     * Imposta il valore della propriet ricercaVoceEntrataInput.
     * 
     * @param value
     *     allowed object is
     *     {@link RicercaVoceEntrataInput }
     *     
     */
    public void setRicercaVoceEntrataInput(RicercaVoceEntrataInput value) {
        this.ricercaVoceEntrataInput = value;
    }

}
