/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysimweb.integration.stubs.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per getEntiAssociati complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="getEntiAssociati"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="getEntiAssociatiInput" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}getEntiAssociatiInput" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getEntiAssociati", propOrder = {
    "getEntiAssociatiInput"
})
public class GetEntiAssociati {

    protected GetEntiAssociatiInput getEntiAssociatiInput;

    /**
     * Recupera il valore della propriet getEntiAssociatiInput.
     * 
     * @return
     *     possible object is
     *     {@link GetEntiAssociatiInput }
     *     
     */
    public GetEntiAssociatiInput getGetEntiAssociatiInput() {
        return getEntiAssociatiInput;
    }

    /**
     * Imposta il valore della propriet getEntiAssociatiInput.
     * 
     * @param value
     *     allowed object is
     *     {@link GetEntiAssociatiInput }
     *     
     */
    public void setGetEntiAssociatiInput(GetEntiAssociatiInput value) {
        this.getEntiAssociatiInput = value;
    }

}
