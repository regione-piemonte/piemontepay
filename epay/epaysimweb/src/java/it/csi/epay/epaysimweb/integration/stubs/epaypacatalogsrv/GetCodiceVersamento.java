/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysimweb.integration.stubs.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per getCodiceVersamento complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="getCodiceVersamento"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="getCodiceVersamentoInput" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}getCodiceVersamentoInput" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getCodiceVersamento", propOrder = {
    "getCodiceVersamentoInput"
})
public class GetCodiceVersamento {

    protected GetCodiceVersamentoInput getCodiceVersamentoInput;

    /**
     * Recupera il valore della propriet getCodiceVersamentoInput.
     * 
     * @return
     *     possible object is
     *     {@link GetCodiceVersamentoInput }
     *     
     */
    public GetCodiceVersamentoInput getGetCodiceVersamentoInput() {
        return getCodiceVersamentoInput;
    }

    /**
     * Imposta il valore della propriet getCodiceVersamentoInput.
     * 
     * @param value
     *     allowed object is
     *     {@link GetCodiceVersamentoInput }
     *     
     */
    public void setGetCodiceVersamentoInput(GetCodiceVersamentoInput value) {
        this.getCodiceVersamentoInput = value;
    }

}
