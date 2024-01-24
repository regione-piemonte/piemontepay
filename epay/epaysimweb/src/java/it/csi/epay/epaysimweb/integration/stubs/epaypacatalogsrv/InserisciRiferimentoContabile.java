/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysimweb.integration.stubs.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per inserisciRiferimentoContabile complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="inserisciRiferimentoContabile"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="inserisciRiferimentoContabileInput" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}inserisciRiferimentoContabileInput" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "inserisciRiferimentoContabile", propOrder = {
    "inserisciRiferimentoContabileInput"
})
public class InserisciRiferimentoContabile {

    protected InserisciRiferimentoContabileInput inserisciRiferimentoContabileInput;

    /**
     * Recupera il valore della propriet inserisciRiferimentoContabileInput.
     * 
     * @return
     *     possible object is
     *     {@link InserisciRiferimentoContabileInput }
     *     
     */
    public InserisciRiferimentoContabileInput getInserisciRiferimentoContabileInput() {
        return inserisciRiferimentoContabileInput;
    }

    /**
     * Imposta il valore della propriet inserisciRiferimentoContabileInput.
     * 
     * @param value
     *     allowed object is
     *     {@link InserisciRiferimentoContabileInput }
     *     
     */
    public void setInserisciRiferimentoContabileInput(InserisciRiferimentoContabileInput value) {
        this.inserisciRiferimentoContabileInput = value;
    }

}
