/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysimweb.integration.stubs.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per getOpzioniModalitaAcquisizioneProvvisoriResponse complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="getOpzioniModalitaAcquisizioneProvvisoriResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="result" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}getOpzioniModalitaAcquisizioneProvvisoriOutput" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getOpzioniModalitaAcquisizioneProvvisoriResponse", propOrder = {
    "result"
})
public class GetOpzioniModalitaAcquisizioneProvvisoriResponse {

    protected GetOpzioniModalitaAcquisizioneProvvisoriOutput result;

    /**
     * Recupera il valore della propriet result.
     * 
     * @return
     *     possible object is
     *     {@link GetOpzioniModalitaAcquisizioneProvvisoriOutput }
     *     
     */
    public GetOpzioniModalitaAcquisizioneProvvisoriOutput getResult() {
        return result;
    }

    /**
     * Imposta il valore della propriet result.
     * 
     * @param value
     *     allowed object is
     *     {@link GetOpzioniModalitaAcquisizioneProvvisoriOutput }
     *     
     */
    public void setResult(GetOpzioniModalitaAcquisizioneProvvisoriOutput value) {
        this.result = value;
    }

}
