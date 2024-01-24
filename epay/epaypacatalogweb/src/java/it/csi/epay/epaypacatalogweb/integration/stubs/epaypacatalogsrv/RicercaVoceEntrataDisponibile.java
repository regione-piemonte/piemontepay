/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per ricercaVoceEntrataDisponibile complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ricercaVoceEntrataDisponibile"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ricercaVoceEntrataDisponibileInput" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}ricercaVoceEntrataDisponibileInput" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ricercaVoceEntrataDisponibile", propOrder = {
    "ricercaVoceEntrataDisponibileInput"
})
public class RicercaVoceEntrataDisponibile {

    protected RicercaVoceEntrataDisponibileInput ricercaVoceEntrataDisponibileInput;

    /**
     * Recupera il valore della propriet ricercaVoceEntrataDisponibileInput.
     * 
     * @return
     *     possible object is
     *     {@link RicercaVoceEntrataDisponibileInput }
     *     
     */
    public RicercaVoceEntrataDisponibileInput getRicercaVoceEntrataDisponibileInput() {
        return ricercaVoceEntrataDisponibileInput;
    }

    /**
     * Imposta il valore della propriet ricercaVoceEntrataDisponibileInput.
     * 
     * @param value
     *     allowed object is
     *     {@link RicercaVoceEntrataDisponibileInput }
     *     
     */
    public void setRicercaVoceEntrataDisponibileInput(RicercaVoceEntrataDisponibileInput value) {
        this.ricercaVoceEntrataDisponibileInput = value;
    }

}
