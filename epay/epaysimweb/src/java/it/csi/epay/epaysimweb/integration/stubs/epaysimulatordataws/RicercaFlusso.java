/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per ricercaFlusso complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ricercaFlusso"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="param" type="{http://epaysim.interfacews.epaysim.epay.csi.it/}ricercaFlussoInputDTO" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ricercaFlusso", propOrder = {
    "param"
})
public class RicercaFlusso {

    protected RicercaFlussoInputDTO param;

    /**
     * Recupera il valore della propriet param.
     * 
     * @return
     *     possible object is
     *     {@link RicercaFlussoInputDTO }
     *     
     */
    public RicercaFlussoInputDTO getParam() {
        return param;
    }

    /**
     * Imposta il valore della propriet param.
     * 
     * @param value
     *     allowed object is
     *     {@link RicercaFlussoInputDTO }
     *     
     */
    public void setParam(RicercaFlussoInputDTO value) {
        this.param = value;
    }

}
