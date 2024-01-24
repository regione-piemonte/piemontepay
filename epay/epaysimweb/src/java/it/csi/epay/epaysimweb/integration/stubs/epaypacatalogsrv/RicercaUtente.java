/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysimweb.integration.stubs.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per ricercaUtente complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ricercaUtente"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ricercaUtenteInput" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}ricercaUtenteInput" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ricercaUtente", propOrder = {
    "ricercaUtenteInput"
})
public class RicercaUtente {

    protected RicercaUtenteInput ricercaUtenteInput;

    /**
     * Recupera il valore della propriet ricercaUtenteInput.
     * 
     * @return
     *     possible object is
     *     {@link RicercaUtenteInput }
     *     
     */
    public RicercaUtenteInput getRicercaUtenteInput() {
        return ricercaUtenteInput;
    }

    /**
     * Imposta il valore della propriet ricercaUtenteInput.
     * 
     * @param value
     *     allowed object is
     *     {@link RicercaUtenteInput }
     *     
     */
    public void setRicercaUtenteInput(RicercaUtenteInput value) {
        this.ricercaUtenteInput = value;
    }

}
