/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysimweb.integration.stubs.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per autorizzaEsportazioneDati complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="autorizzaEsportazioneDati"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="autorizzaEsportazioneDatiInput" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}autorizzaEsportazioneDatiInput" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "autorizzaEsportazioneDati", propOrder = {
    "autorizzaEsportazioneDatiInput"
})
public class AutorizzaEsportazioneDati {

    protected AutorizzaEsportazioneDatiInput autorizzaEsportazioneDatiInput;

    /**
     * Recupera il valore della propriet autorizzaEsportazioneDatiInput.
     * 
     * @return
     *     possible object is
     *     {@link AutorizzaEsportazioneDatiInput }
     *     
     */
    public AutorizzaEsportazioneDatiInput getAutorizzaEsportazioneDatiInput() {
        return autorizzaEsportazioneDatiInput;
    }

    /**
     * Imposta il valore della propriet autorizzaEsportazioneDatiInput.
     * 
     * @param value
     *     allowed object is
     *     {@link AutorizzaEsportazioneDatiInput }
     *     
     */
    public void setAutorizzaEsportazioneDatiInput(AutorizzaEsportazioneDatiInput value) {
        this.autorizzaEsportazioneDatiInput = value;
    }

}
