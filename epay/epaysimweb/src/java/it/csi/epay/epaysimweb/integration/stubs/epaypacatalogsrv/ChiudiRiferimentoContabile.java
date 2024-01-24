/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysimweb.integration.stubs.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per chiudiRiferimentoContabile complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="chiudiRiferimentoContabile"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="chiudiRiferimentoContabileInput" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}chiudiRiferimentoContabileInput" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "chiudiRiferimentoContabile", propOrder = {
    "chiudiRiferimentoContabileInput"
})
public class ChiudiRiferimentoContabile {

    protected ChiudiRiferimentoContabileInput chiudiRiferimentoContabileInput;

    /**
     * Recupera il valore della propriet chiudiRiferimentoContabileInput.
     * 
     * @return
     *     possible object is
     *     {@link ChiudiRiferimentoContabileInput }
     *     
     */
    public ChiudiRiferimentoContabileInput getChiudiRiferimentoContabileInput() {
        return chiudiRiferimentoContabileInput;
    }

    /**
     * Imposta il valore della propriet chiudiRiferimentoContabileInput.
     * 
     * @param value
     *     allowed object is
     *     {@link ChiudiRiferimentoContabileInput }
     *     
     */
    public void setChiudiRiferimentoContabileInput(ChiudiRiferimentoContabileInput value) {
        this.chiudiRiferimentoContabileInput = value;
    }

}
