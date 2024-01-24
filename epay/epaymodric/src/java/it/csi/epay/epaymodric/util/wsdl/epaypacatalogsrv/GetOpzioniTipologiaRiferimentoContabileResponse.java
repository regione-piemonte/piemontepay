/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.util.wsdl.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per getOpzioniTipologiaRiferimentoContabileResponse complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="getOpzioniTipologiaRiferimentoContabileResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="result" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}getOpzioniTipologiaRiferimentoContabileOutput" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getOpzioniTipologiaRiferimentoContabileResponse", propOrder = {
    "result"
})
public class GetOpzioniTipologiaRiferimentoContabileResponse {

    protected GetOpzioniTipologiaRiferimentoContabileOutput result;

    /**
     * Recupera il valore della proprieta result.
     * 
     * @return
     *     possible object is
     *     {@link GetOpzioniTipologiaRiferimentoContabileOutput }
     *     
     */
    public GetOpzioniTipologiaRiferimentoContabileOutput getResult() {
        return result;
    }

    /**
     * Imposta il valore della proprieta result.
     * 
     * @param value
     *     allowed object is
     *     {@link GetOpzioniTipologiaRiferimentoContabileOutput }
     *     
     */
    public void setResult(GetOpzioniTipologiaRiferimentoContabileOutput value) {
        this.result = value;
    }

}
