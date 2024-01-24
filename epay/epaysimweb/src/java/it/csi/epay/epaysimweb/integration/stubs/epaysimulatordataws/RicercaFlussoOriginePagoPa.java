/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per ricercaFlussoOriginePagoPa complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ricercaFlussoOriginePagoPa"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="arg0" type="{http://epaysim.interfacews.epaysim.epay.csi.it/}flussoOriginePagopaDTO" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ricercaFlussoOriginePagoPa", propOrder = {
    "arg0"
})
public class RicercaFlussoOriginePagoPa {

    protected FlussoOriginePagopaDTO arg0;

    /**
     * Recupera il valore della propriet arg0.
     * 
     * @return
     *     possible object is
     *     {@link FlussoOriginePagopaDTO }
     *     
     */
    public FlussoOriginePagopaDTO getArg0() {
        return arg0;
    }

    /**
     * Imposta il valore della propriet arg0.
     * 
     * @param value
     *     allowed object is
     *     {@link FlussoOriginePagopaDTO }
     *     
     */
    public void setArg0(FlussoOriginePagopaDTO value) {
        this.arg0 = value;
    }

}
