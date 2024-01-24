/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.integration.stubs.epaymodricws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per ricercaFlussiSintesi complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ricercaFlussiSintesi"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ricercaFlussoSintesi" type="{http://epaymodric.interfacews.epaymodric.epay.csi.it/}dtoInputWsRicercaFlussoSintesi" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ricercaFlussiSintesi", propOrder = {
    "ricercaFlussoSintesi"
})
public class RicercaFlussiSintesi {

    protected DtoInputWsRicercaFlussoSintesi ricercaFlussoSintesi;

    /**
     * Recupera il valore della proprieta ricercaFlussoSintesi.
     * 
     * @return
     *     possible object is
     *     {@link DtoInputWsRicercaFlussoSintesi }
     *     
     */
    public DtoInputWsRicercaFlussoSintesi getRicercaFlussoSintesi() {
        return ricercaFlussoSintesi;
    }

    /**
     * Imposta il valore della proprieta ricercaFlussoSintesi.
     * 
     * @param value
     *     allowed object is
     *     {@link DtoInputWsRicercaFlussoSintesi }
     *     
     */
    public void setRicercaFlussoSintesi(DtoInputWsRicercaFlussoSintesi value) {
        this.ricercaFlussoSintesi = value;
    }

}
