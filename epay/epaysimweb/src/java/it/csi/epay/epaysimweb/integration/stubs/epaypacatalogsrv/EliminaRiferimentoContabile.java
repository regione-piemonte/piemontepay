/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysimweb.integration.stubs.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per eliminaRiferimentoContabile complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="eliminaRiferimentoContabile"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="eliminaRiferimentoContabileInput" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}eliminaRiferimentoContabileInput" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "eliminaRiferimentoContabile", propOrder = {
    "eliminaRiferimentoContabileInput"
})
public class EliminaRiferimentoContabile {

    protected EliminaRiferimentoContabileInput eliminaRiferimentoContabileInput;

    /**
     * Recupera il valore della propriet eliminaRiferimentoContabileInput.
     * 
     * @return
     *     possible object is
     *     {@link EliminaRiferimentoContabileInput }
     *     
     */
    public EliminaRiferimentoContabileInput getEliminaRiferimentoContabileInput() {
        return eliminaRiferimentoContabileInput;
    }

    /**
     * Imposta il valore della propriet eliminaRiferimentoContabileInput.
     * 
     * @param value
     *     allowed object is
     *     {@link EliminaRiferimentoContabileInput }
     *     
     */
    public void setEliminaRiferimentoContabileInput(EliminaRiferimentoContabileInput value) {
        this.eliminaRiferimentoContabileInput = value;
    }

}
