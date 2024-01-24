/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per eliminaUtente complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="eliminaUtente"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="eliminaUtenteInput" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}eliminaUtenteInput" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "eliminaUtente", propOrder = {
    "eliminaUtenteInput"
})
public class EliminaUtente {

    protected EliminaUtenteInput eliminaUtenteInput;

    /**
     * Recupera il valore della propriet eliminaUtenteInput.
     * 
     * @return
     *     possible object is
     *     {@link EliminaUtenteInput }
     *     
     */
    public EliminaUtenteInput getEliminaUtenteInput() {
        return eliminaUtenteInput;
    }

    /**
     * Imposta il valore della propriet eliminaUtenteInput.
     * 
     * @param value
     *     allowed object is
     *     {@link EliminaUtenteInput }
     *     
     */
    public void setEliminaUtenteInput(EliminaUtenteInput value) {
        this.eliminaUtenteInput = value;
    }

}
