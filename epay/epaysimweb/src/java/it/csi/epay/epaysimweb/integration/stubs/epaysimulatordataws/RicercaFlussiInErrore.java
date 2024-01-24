/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per ricercaFlussiInErrore complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ricercaFlussiInErrore"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="param" type="{http://epaysim.interfacews.epaysim.epay.csi.it/}ricercaFlussoErroreInputDTO" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ricercaFlussiInErrore", propOrder = {
    "param"
})
public class RicercaFlussiInErrore {

    protected RicercaFlussoErroreInputDTO param;

    /**
     * Recupera il valore della propriet param.
     * 
     * @return
     *     possible object is
     *     {@link RicercaFlussoErroreInputDTO }
     *     
     */
    public RicercaFlussoErroreInputDTO getParam() {
        return param;
    }

    /**
     * Imposta il valore della propriet param.
     * 
     * @param value
     *     allowed object is
     *     {@link RicercaFlussoErroreInputDTO }
     *     
     */
    public void setParam(RicercaFlussoErroreInputDTO value) {
        this.param = value;
    }

}
