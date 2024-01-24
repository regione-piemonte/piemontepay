/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysimweb.integration.stubs.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per aggiornaCodiceVersamento complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="aggiornaCodiceVersamento"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="aggiornaCodiceVersamentoInput" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}aggiornaCodiceVersamentoInput" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "aggiornaCodiceVersamento", propOrder = {
    "aggiornaCodiceVersamentoInput"
})
public class AggiornaCodiceVersamento {

    protected AggiornaCodiceVersamentoInput aggiornaCodiceVersamentoInput;

    /**
     * Recupera il valore della propriet aggiornaCodiceVersamentoInput.
     * 
     * @return
     *     possible object is
     *     {@link AggiornaCodiceVersamentoInput }
     *     
     */
    public AggiornaCodiceVersamentoInput getAggiornaCodiceVersamentoInput() {
        return aggiornaCodiceVersamentoInput;
    }

    /**
     * Imposta il valore della propriet aggiornaCodiceVersamentoInput.
     * 
     * @param value
     *     allowed object is
     *     {@link AggiornaCodiceVersamentoInput }
     *     
     */
    public void setAggiornaCodiceVersamentoInput(AggiornaCodiceVersamentoInput value) {
        this.aggiornaCodiceVersamentoInput = value;
    }

}
