/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per getProfilazioneUtenteCorrente complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="getProfilazioneUtenteCorrente"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="getProfilazioneUtenteCorrenteInput" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}getProfilazioneUtenteCorrenteInput" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getProfilazioneUtenteCorrente", propOrder = {
    "getProfilazioneUtenteCorrenteInput"
})
public class GetProfilazioneUtenteCorrente {

    protected GetProfilazioneUtenteCorrenteInput getProfilazioneUtenteCorrenteInput;

    /**
     * Recupera il valore della propriet getProfilazioneUtenteCorrenteInput.
     * 
     * @return
     *     possible object is
     *     {@link GetProfilazioneUtenteCorrenteInput }
     *     
     */
    public GetProfilazioneUtenteCorrenteInput getGetProfilazioneUtenteCorrenteInput() {
        return getProfilazioneUtenteCorrenteInput;
    }

    /**
     * Imposta il valore della propriet getProfilazioneUtenteCorrenteInput.
     * 
     * @param value
     *     allowed object is
     *     {@link GetProfilazioneUtenteCorrenteInput }
     *     
     */
    public void setGetProfilazioneUtenteCorrenteInput(GetProfilazioneUtenteCorrenteInput value) {
        this.getProfilazioneUtenteCorrenteInput = value;
    }

}
