/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per ricercaFlussoSintesiPagoPaResponse complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ricercaFlussoSintesiPagoPaResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="return" type="{http://epaysim.interfacews.epaysim.epay.csi.it/}flussoSintesiPagopaOutputDTO" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ricercaFlussoSintesiPagoPaResponse", propOrder = {
    "_return"
})
public class RicercaFlussoSintesiPagoPaResponse {

    @XmlElement(name = "return")
    protected FlussoSintesiPagopaOutputDTO _return;

    /**
     * Recupera il valore della propriet return.
     * 
     * @return
     *     possible object is
     *     {@link FlussoSintesiPagopaOutputDTO }
     *     
     */
    public FlussoSintesiPagopaOutputDTO getReturn() {
        return _return;
    }

    /**
     * Imposta il valore della propriet return.
     * 
     * @param value
     *     allowed object is
     *     {@link FlussoSintesiPagopaOutputDTO }
     *     
     */
    public void setReturn(FlussoSintesiPagopaOutputDTO value) {
        this._return = value;
    }

}
