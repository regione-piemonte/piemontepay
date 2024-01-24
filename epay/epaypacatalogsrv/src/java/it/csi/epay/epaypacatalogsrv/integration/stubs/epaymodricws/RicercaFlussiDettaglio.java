/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.integration.stubs.epaymodricws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per ricercaFlussiDettaglio complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ricercaFlussiDettaglio"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ricercaFlussoDettaglio" type="{http://epaymodric.interfacews.epaymodric.epay.csi.it/}dtoInputWsRicercaFlussoDettaglio" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ricercaFlussiDettaglio", propOrder = {
    "ricercaFlussoDettaglio"
})
public class RicercaFlussiDettaglio {

    protected DtoInputWsRicercaFlussoDettaglio ricercaFlussoDettaglio;

    /**
     * Recupera il valore della proprieta ricercaFlussoDettaglio.
     * 
     * @return
     *     possible object is
     *     {@link DtoInputWsRicercaFlussoDettaglio }
     *     
     */
    public DtoInputWsRicercaFlussoDettaglio getRicercaFlussoDettaglio() {
        return ricercaFlussoDettaglio;
    }

    /**
     * Imposta il valore della proprieta ricercaFlussoDettaglio.
     * 
     * @param value
     *     allowed object is
     *     {@link DtoInputWsRicercaFlussoDettaglio }
     *     
     */
    public void setRicercaFlussoDettaglio(DtoInputWsRicercaFlussoDettaglio value) {
        this.ricercaFlussoDettaglio = value;
    }

}
