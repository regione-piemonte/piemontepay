/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per ricercaFlussoDettaglioPagoPaResponse complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ricercaFlussoDettaglioPagoPaResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="result" type="{http://epaysim.interfacews.epaysim.epay.csi.it/}flussoDettaglioPagopaOutputDTO" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ricercaFlussoDettaglioPagoPaResponse", propOrder = {
    "result"
})
public class RicercaFlussoDettaglioPagoPaResponse {

    protected FlussoDettaglioPagopaOutputDTO result;

    /**
     * Recupera il valore della propriet result.
     * 
     * @return
     *     possible object is
     *     {@link FlussoDettaglioPagopaOutputDTO }
     *     
     */
    public FlussoDettaglioPagopaOutputDTO getResult() {
        return result;
    }

    /**
     * Imposta il valore della propriet result.
     * 
     * @param value
     *     allowed object is
     *     {@link FlussoDettaglioPagopaOutputDTO }
     *     
     */
    public void setResult(FlussoDettaglioPagopaOutputDTO value) {
        this.result = value;
    }

}
