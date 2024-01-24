/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysimweb.integration.stubs.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per aggiornaRiferimentoContabile complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="aggiornaRiferimentoContabile"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="aggiornaRiferimentoContabileInput" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}aggiornaRiferimentoContabileInput" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "aggiornaRiferimentoContabile", propOrder = {
    "aggiornaRiferimentoContabileInput"
})
public class AggiornaRiferimentoContabile {

    protected AggiornaRiferimentoContabileInput aggiornaRiferimentoContabileInput;

    /**
     * Recupera il valore della propriet aggiornaRiferimentoContabileInput.
     * 
     * @return
     *     possible object is
     *     {@link AggiornaRiferimentoContabileInput }
     *     
     */
    public AggiornaRiferimentoContabileInput getAggiornaRiferimentoContabileInput() {
        return aggiornaRiferimentoContabileInput;
    }

    /**
     * Imposta il valore della propriet aggiornaRiferimentoContabileInput.
     * 
     * @param value
     *     allowed object is
     *     {@link AggiornaRiferimentoContabileInput }
     *     
     */
    public void setAggiornaRiferimentoContabileInput(AggiornaRiferimentoContabileInput value) {
        this.aggiornaRiferimentoContabileInput = value;
    }

}
