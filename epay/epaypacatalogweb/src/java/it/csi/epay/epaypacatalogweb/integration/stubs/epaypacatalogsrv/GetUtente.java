/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per getUtente complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="getUtente"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="getUtenteInput" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}getUtenteInput" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getUtente", propOrder = {
    "getUtenteInput"
})
public class GetUtente {

    protected GetUtenteInput getUtenteInput;

    /**
     * Recupera il valore della propriet getUtenteInput.
     * 
     * @return
     *     possible object is
     *     {@link GetUtenteInput }
     *     
     */
    public GetUtenteInput getGetUtenteInput() {
        return getUtenteInput;
    }

    /**
     * Imposta il valore della propriet getUtenteInput.
     * 
     * @param value
     *     allowed object is
     *     {@link GetUtenteInput }
     *     
     */
    public void setGetUtenteInput(GetUtenteInput value) {
        this.getUtenteInput = value;
    }

}
