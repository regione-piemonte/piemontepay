/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per aggiornaEnte complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="aggiornaEnte"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="modificaEnteInput" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}aggiornaEnteInput" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "aggiornaEnte", propOrder = {
    "modificaEnteInput"
})
public class AggiornaEnte {

    protected AggiornaEnteInput modificaEnteInput;

    /**
     * Recupera il valore della propriet modificaEnteInput.
     * 
     * @return
     *     possible object is
     *     {@link AggiornaEnteInput }
     *     
     */
    public AggiornaEnteInput getModificaEnteInput() {
        return modificaEnteInput;
    }

    /**
     * Imposta il valore della propriet modificaEnteInput.
     * 
     * @param value
     *     allowed object is
     *     {@link AggiornaEnteInput }
     *     
     */
    public void setModificaEnteInput(AggiornaEnteInput value) {
        this.modificaEnteInput = value;
    }

}
