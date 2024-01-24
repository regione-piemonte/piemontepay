/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.integration.stubs.epaymodricws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per ricercaFlussoOrigine complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ricercaFlussoOrigine"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ricercaFlussoOrigine" type="{http://epaymodric.interfacews.epaymodric.epay.csi.it/}dtoInputWsRicercaFlussoOrigine" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ricercaFlussoOrigine", propOrder = {
    "ricercaFlussoOrigine"
})
public class RicercaFlussoOrigine {

    protected DtoInputWsRicercaFlussoOrigine ricercaFlussoOrigine;

    /**
     * Recupera il valore della proprieta ricercaFlussoOrigine.
     * 
     * @return
     *     possible object is
     *     {@link DtoInputWsRicercaFlussoOrigine }
     *     
     */
    public DtoInputWsRicercaFlussoOrigine getRicercaFlussoOrigine() {
        return ricercaFlussoOrigine;
    }

    /**
     * Imposta il valore della proprieta ricercaFlussoOrigine.
     * 
     * @param value
     *     allowed object is
     *     {@link DtoInputWsRicercaFlussoOrigine }
     *     
     */
    public void setRicercaFlussoOrigine(DtoInputWsRicercaFlussoOrigine value) {
        this.ricercaFlussoOrigine = value;
    }

}
