/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per aggiornaTematicheUtente complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="aggiornaTematicheUtente"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="aggiornaTematicheUtenteInput" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}aggiornaTematicheUtenteInput" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "aggiornaTematicheUtente", propOrder = {
    "aggiornaTematicheUtenteInput"
})
public class AggiornaTematicheUtente {

    protected AggiornaTematicheUtenteInput aggiornaTematicheUtenteInput;

    /**
     * Recupera il valore della propriet aggiornaTematicheUtenteInput.
     * 
     * @return
     *     possible object is
     *     {@link AggiornaTematicheUtenteInput }
     *     
     */
    public AggiornaTematicheUtenteInput getAggiornaTematicheUtenteInput() {
        return aggiornaTematicheUtenteInput;
    }

    /**
     * Imposta il valore della propriet aggiornaTematicheUtenteInput.
     * 
     * @param value
     *     allowed object is
     *     {@link AggiornaTematicheUtenteInput }
     *     
     */
    public void setAggiornaTematicheUtenteInput(AggiornaTematicheUtenteInput value) {
        this.aggiornaTematicheUtenteInput = value;
    }

}
