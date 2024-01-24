/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysimweb.integration.stubs.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per getOpzioniMacrotipo complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="getOpzioniMacrotipo"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="getOpzioniInput" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}getOpzioniInput" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getOpzioniMacrotipo", propOrder = {
    "getOpzioniInput"
})
public class GetOpzioniMacrotipo {

    protected GetOpzioniInput getOpzioniInput;

    /**
     * Recupera il valore della propriet getOpzioniInput.
     * 
     * @return
     *     possible object is
     *     {@link GetOpzioniInput }
     *     
     */
    public GetOpzioniInput getGetOpzioniInput() {
        return getOpzioniInput;
    }

    /**
     * Imposta il valore della propriet getOpzioniInput.
     * 
     * @param value
     *     allowed object is
     *     {@link GetOpzioniInput }
     *     
     */
    public void setGetOpzioniInput(GetOpzioniInput value) {
        this.getOpzioniInput = value;
    }

}
