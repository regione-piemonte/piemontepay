/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per ricercaCodiceVersamento complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ricercaCodiceVersamento"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ricercaCodiceVersamentoInput" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}ricercaCodiceVersamentoInput" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ricercaCodiceVersamento", propOrder = {
    "ricercaCodiceVersamentoInput"
})
public class RicercaCodiceVersamento {

    protected RicercaCodiceVersamentoInput ricercaCodiceVersamentoInput;

    /**
     * Recupera il valore della propriet ricercaCodiceVersamentoInput.
     * 
     * @return
     *     possible object is
     *     {@link RicercaCodiceVersamentoInput }
     *     
     */
    public RicercaCodiceVersamentoInput getRicercaCodiceVersamentoInput() {
        return ricercaCodiceVersamentoInput;
    }

    /**
     * Imposta il valore della propriet ricercaCodiceVersamentoInput.
     * 
     * @param value
     *     allowed object is
     *     {@link RicercaCodiceVersamentoInput }
     *     
     */
    public void setRicercaCodiceVersamentoInput(RicercaCodiceVersamentoInput value) {
        this.ricercaCodiceVersamentoInput = value;
    }

}
