/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysimweb.integration.stubs.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per getEnte complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="getEnte"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="getEnteInput" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}getEnteInput" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getEnte", propOrder = {
    "getEnteInput"
})
public class GetEnte {

    protected GetEnteInput getEnteInput;

    /**
     * Recupera il valore della propriet getEnteInput.
     * 
     * @return
     *     possible object is
     *     {@link GetEnteInput }
     *     
     */
    public GetEnteInput getGetEnteInput() {
        return getEnteInput;
    }

    /**
     * Imposta il valore della propriet getEnteInput.
     * 
     * @param value
     *     allowed object is
     *     {@link GetEnteInput }
     *     
     */
    public void setGetEnteInput(GetEnteInput value) {
        this.getEnteInput = value;
    }

}
