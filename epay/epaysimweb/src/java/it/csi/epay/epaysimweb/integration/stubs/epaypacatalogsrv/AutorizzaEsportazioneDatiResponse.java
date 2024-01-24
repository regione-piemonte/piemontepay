/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysimweb.integration.stubs.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per autorizzaEsportazioneDatiResponse complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="autorizzaEsportazioneDatiResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="result" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}autorizzaEsportazioneDatiOutput" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "autorizzaEsportazioneDatiResponse", propOrder = {
    "result"
})
public class AutorizzaEsportazioneDatiResponse {

    protected AutorizzaEsportazioneDatiOutput result;

    /**
     * Recupera il valore della propriet result.
     * 
     * @return
     *     possible object is
     *     {@link AutorizzaEsportazioneDatiOutput }
     *     
     */
    public AutorizzaEsportazioneDatiOutput getResult() {
        return result;
    }

    /**
     * Imposta il valore della propriet result.
     * 
     * @param value
     *     allowed object is
     *     {@link AutorizzaEsportazioneDatiOutput }
     *     
     */
    public void setResult(AutorizzaEsportazioneDatiOutput value) {
        this.result = value;
    }

}
