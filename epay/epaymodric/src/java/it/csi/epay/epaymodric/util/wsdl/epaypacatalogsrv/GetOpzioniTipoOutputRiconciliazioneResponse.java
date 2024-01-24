/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.util.wsdl.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per getOpzioniTipoOutputRiconciliazioneResponse complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="getOpzioniTipoOutputRiconciliazioneResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="result" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}getOpzioniTipoOutputRiconciliazioneOutput" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getOpzioniTipoOutputRiconciliazioneResponse", propOrder = {
    "result"
})
public class GetOpzioniTipoOutputRiconciliazioneResponse {

    protected GetOpzioniTipoOutputRiconciliazioneOutput result;

    /**
     * Recupera il valore della proprieta result.
     * 
     * @return
     *     possible object is
     *     {@link GetOpzioniTipoOutputRiconciliazioneOutput }
     *     
     */
    public GetOpzioniTipoOutputRiconciliazioneOutput getResult() {
        return result;
    }

    /**
     * Imposta il valore della proprieta result.
     * 
     * @param value
     *     allowed object is
     *     {@link GetOpzioniTipoOutputRiconciliazioneOutput }
     *     
     */
    public void setResult(GetOpzioniTipoOutputRiconciliazioneOutput value) {
        this.result = value;
    }

}
