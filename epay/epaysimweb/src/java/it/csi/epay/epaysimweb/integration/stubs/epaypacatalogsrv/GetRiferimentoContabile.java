/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysimweb.integration.stubs.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per getRiferimentoContabile complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="getRiferimentoContabile"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="getRiferimentoContabileInput" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}getRiferimentoContabileInput" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getRiferimentoContabile", propOrder = {
    "getRiferimentoContabileInput"
})
public class GetRiferimentoContabile {

    protected GetRiferimentoContabileInput getRiferimentoContabileInput;

    /**
     * Recupera il valore della propriet getRiferimentoContabileInput.
     * 
     * @return
     *     possible object is
     *     {@link GetRiferimentoContabileInput }
     *     
     */
    public GetRiferimentoContabileInput getGetRiferimentoContabileInput() {
        return getRiferimentoContabileInput;
    }

    /**
     * Imposta il valore della propriet getRiferimentoContabileInput.
     * 
     * @param value
     *     allowed object is
     *     {@link GetRiferimentoContabileInput }
     *     
     */
    public void setGetRiferimentoContabileInput(GetRiferimentoContabileInput value) {
        this.getRiferimentoContabileInput = value;
    }

}
