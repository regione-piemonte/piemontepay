/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per getMessaggi complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="getMessaggi"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="getMessaggiInput" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}getMessaggiInput" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getMessaggi", propOrder = {
    "getMessaggiInput"
})
public class GetMessaggi {

    protected GetMessaggiInput getMessaggiInput;

    /**
     * Recupera il valore della propriet getMessaggiInput.
     * 
     * @return
     *     possible object is
     *     {@link GetMessaggiInput }
     *     
     */
    public GetMessaggiInput getGetMessaggiInput() {
        return getMessaggiInput;
    }

    /**
     * Imposta il valore della propriet getMessaggiInput.
     * 
     * @param value
     *     allowed object is
     *     {@link GetMessaggiInput }
     *     
     */
    public void setGetMessaggiInput(GetMessaggiInput value) {
        this.getMessaggiInput = value;
    }

}
