/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysimweb.integration.stubs.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per ricercaRiferimentoContabile complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ricercaRiferimentoContabile"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ricercaRiferimentoContabileInput" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}ricercaRiferimentoContabileInput" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ricercaRiferimentoContabile", propOrder = {
    "ricercaRiferimentoContabileInput"
})
public class RicercaRiferimentoContabile {

    protected RicercaRiferimentoContabileInput ricercaRiferimentoContabileInput;

    /**
     * Recupera il valore della propriet ricercaRiferimentoContabileInput.
     * 
     * @return
     *     possible object is
     *     {@link RicercaRiferimentoContabileInput }
     *     
     */
    public RicercaRiferimentoContabileInput getRicercaRiferimentoContabileInput() {
        return ricercaRiferimentoContabileInput;
    }

    /**
     * Imposta il valore della propriet ricercaRiferimentoContabileInput.
     * 
     * @param value
     *     allowed object is
     *     {@link RicercaRiferimentoContabileInput }
     *     
     */
    public void setRicercaRiferimentoContabileInput(RicercaRiferimentoContabileInput value) {
        this.ricercaRiferimentoContabileInput = value;
    }

}
