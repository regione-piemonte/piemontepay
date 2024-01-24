/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.integration.stubs.epaymodricws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per ricercaStatoFlusso complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ricercaStatoFlusso"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ricercaStatoFlusso" type="{http://epaymodric.interfacews.epaymodric.epay.csi.it/}dtoInputWsRicercaStatoFlusso" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ricercaStatoFlusso", propOrder = {
    "ricercaStatoFlusso"
})
public class RicercaStatoFlusso {

    protected DtoInputWsRicercaStatoFlusso ricercaStatoFlusso;

    /**
     * Recupera il valore della proprieta ricercaStatoFlusso.
     * 
     * @return
     *     possible object is
     *     {@link DtoInputWsRicercaStatoFlusso }
     *     
     */
    public DtoInputWsRicercaStatoFlusso getRicercaStatoFlusso() {
        return ricercaStatoFlusso;
    }

    /**
     * Imposta il valore della proprieta ricercaStatoFlusso.
     * 
     * @param value
     *     allowed object is
     *     {@link DtoInputWsRicercaStatoFlusso }
     *     
     */
    public void setRicercaStatoFlusso(DtoInputWsRicercaStatoFlusso value) {
        this.ricercaStatoFlusso = value;
    }

}
