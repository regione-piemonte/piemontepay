/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysimweb.integration.stubs.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per aggiornaCduUtente complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="aggiornaCduUtente"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="aggiornaCduUtenteInput" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}aggiornaCduUtenteInput" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "aggiornaCduUtente", propOrder = {
    "aggiornaCduUtenteInput"
})
public class AggiornaCduUtente {

    protected AggiornaCduUtenteInput aggiornaCduUtenteInput;

    /**
     * Recupera il valore della propriet aggiornaCduUtenteInput.
     * 
     * @return
     *     possible object is
     *     {@link AggiornaCduUtenteInput }
     *     
     */
    public AggiornaCduUtenteInput getAggiornaCduUtenteInput() {
        return aggiornaCduUtenteInput;
    }

    /**
     * Imposta il valore della propriet aggiornaCduUtenteInput.
     * 
     * @param value
     *     allowed object is
     *     {@link AggiornaCduUtenteInput }
     *     
     */
    public void setAggiornaCduUtenteInput(AggiornaCduUtenteInput value) {
        this.aggiornaCduUtenteInput = value;
    }

}
