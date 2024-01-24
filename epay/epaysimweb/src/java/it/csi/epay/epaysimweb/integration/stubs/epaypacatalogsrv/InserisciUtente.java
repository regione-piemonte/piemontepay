/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysimweb.integration.stubs.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per inserisciUtente complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="inserisciUtente"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="inserisciUtenteInput" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}inserisciUtenteInput" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "inserisciUtente", propOrder = {
    "inserisciUtenteInput"
})
public class InserisciUtente {

    protected InserisciUtenteInput inserisciUtenteInput;

    /**
     * Recupera il valore della propriet inserisciUtenteInput.
     * 
     * @return
     *     possible object is
     *     {@link InserisciUtenteInput }
     *     
     */
    public InserisciUtenteInput getInserisciUtenteInput() {
        return inserisciUtenteInput;
    }

    /**
     * Imposta il valore della propriet inserisciUtenteInput.
     * 
     * @param value
     *     allowed object is
     *     {@link InserisciUtenteInput }
     *     
     */
    public void setInserisciUtenteInput(InserisciUtenteInput value) {
        this.inserisciUtenteInput = value;
    }

}
