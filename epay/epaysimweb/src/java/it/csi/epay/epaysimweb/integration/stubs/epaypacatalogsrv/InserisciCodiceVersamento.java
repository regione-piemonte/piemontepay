/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysimweb.integration.stubs.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per inserisciCodiceVersamento complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="inserisciCodiceVersamento"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="inserisciCodiceVersamentoInput" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}inserisciCodiceVersamentoInput" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "inserisciCodiceVersamento", propOrder = {
    "inserisciCodiceVersamentoInput"
})
public class InserisciCodiceVersamento {

    protected InserisciCodiceVersamentoInput inserisciCodiceVersamentoInput;

    /**
     * Recupera il valore della propriet inserisciCodiceVersamentoInput.
     * 
     * @return
     *     possible object is
     *     {@link InserisciCodiceVersamentoInput }
     *     
     */
    public InserisciCodiceVersamentoInput getInserisciCodiceVersamentoInput() {
        return inserisciCodiceVersamentoInput;
    }

    /**
     * Imposta il valore della propriet inserisciCodiceVersamentoInput.
     * 
     * @param value
     *     allowed object is
     *     {@link InserisciCodiceVersamentoInput }
     *     
     */
    public void setInserisciCodiceVersamentoInput(InserisciCodiceVersamentoInput value) {
        this.inserisciCodiceVersamentoInput = value;
    }

}
