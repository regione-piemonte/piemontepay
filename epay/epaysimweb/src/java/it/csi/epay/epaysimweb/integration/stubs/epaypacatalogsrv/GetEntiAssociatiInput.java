/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysimweb.integration.stubs.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per getEntiAssociatiInput complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="getEntiAssociatiInput"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://interfacews.epaypacatalogsrv.epay.csi.it/}parentInput"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="codiceFiscaleUtente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getEntiAssociatiInput", propOrder = {
    "codiceFiscaleUtente"
})
public class GetEntiAssociatiInput
    extends ParentInput
{

    protected String codiceFiscaleUtente;

    /**
     * Recupera il valore della propriet codiceFiscaleUtente.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceFiscaleUtente() {
        return codiceFiscaleUtente;
    }

    /**
     * Imposta il valore della propriet codiceFiscaleUtente.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceFiscaleUtente(String value) {
        this.codiceFiscaleUtente = value;
    }

}
