/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per eseguiMigrazione complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="eseguiMigrazione"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="eseguiMigrazioneInput" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}eseguiMigrazioneInput" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "eseguiMigrazione", propOrder = {
    "eseguiMigrazioneInput"
})
public class EseguiMigrazione {

    protected EseguiMigrazioneInput eseguiMigrazioneInput;

    /**
     * Recupera il valore della propriet eseguiMigrazioneInput.
     * 
     * @return
     *     possible object is
     *     {@link EseguiMigrazioneInput }
     *     
     */
    public EseguiMigrazioneInput getEseguiMigrazioneInput() {
        return eseguiMigrazioneInput;
    }

    /**
     * Imposta il valore della propriet eseguiMigrazioneInput.
     * 
     * @param value
     *     allowed object is
     *     {@link EseguiMigrazioneInput }
     *     
     */
    public void setEseguiMigrazioneInput(EseguiMigrazioneInput value) {
        this.eseguiMigrazioneInput = value;
    }

}
