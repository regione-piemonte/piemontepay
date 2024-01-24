/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per aggiornaUtente complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="aggiornaUtente"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="aggiornaUtenteInput" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}aggiornaUtenteInput" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "aggiornaUtente", propOrder = {
    "aggiornaUtenteInput"
})
public class AggiornaUtente {

    protected AggiornaUtenteInput aggiornaUtenteInput;

    /**
     * Recupera il valore della propriet aggiornaUtenteInput.
     * 
     * @return
     *     possible object is
     *     {@link AggiornaUtenteInput }
     *     
     */
    public AggiornaUtenteInput getAggiornaUtenteInput() {
        return aggiornaUtenteInput;
    }

    /**
     * Imposta il valore della propriet aggiornaUtenteInput.
     * 
     * @param value
     *     allowed object is
     *     {@link AggiornaUtenteInput }
     *     
     */
    public void setAggiornaUtenteInput(AggiornaUtenteInput value) {
        this.aggiornaUtenteInput = value;
    }

}
