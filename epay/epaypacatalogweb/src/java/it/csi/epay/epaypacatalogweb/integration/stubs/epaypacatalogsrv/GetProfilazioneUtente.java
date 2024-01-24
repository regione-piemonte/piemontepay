/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per getProfilazioneUtente complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="getProfilazioneUtente"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="getProfilazioneUtenteInput" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}getProfilazioneUtenteInput" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getProfilazioneUtente", propOrder = {
    "getProfilazioneUtenteInput"
})
public class GetProfilazioneUtente {

    protected GetProfilazioneUtenteInput getProfilazioneUtenteInput;

    /**
     * Recupera il valore della propriet getProfilazioneUtenteInput.
     * 
     * @return
     *     possible object is
     *     {@link GetProfilazioneUtenteInput }
     *     
     */
    public GetProfilazioneUtenteInput getGetProfilazioneUtenteInput() {
        return getProfilazioneUtenteInput;
    }

    /**
     * Imposta il valore della propriet getProfilazioneUtenteInput.
     * 
     * @param value
     *     allowed object is
     *     {@link GetProfilazioneUtenteInput }
     *     
     */
    public void setGetProfilazioneUtenteInput(GetProfilazioneUtenteInput value) {
        this.getProfilazioneUtenteInput = value;
    }

}
