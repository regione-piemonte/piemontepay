/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per getOpzioniCodiceVersamento complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="getOpzioniCodiceVersamento"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="getOpzioniCodiceVersamentoInput" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}getOpzioniCodiceVersamentoInput" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getOpzioniCodiceVersamento", propOrder = {
    "getOpzioniCodiceVersamentoInput"
})
public class GetOpzioniCodiceVersamento {

    protected GetOpzioniCodiceVersamentoInput getOpzioniCodiceVersamentoInput;

    /**
     * Recupera il valore della propriet getOpzioniCodiceVersamentoInput.
     * 
     * @return
     *     possible object is
     *     {@link GetOpzioniCodiceVersamentoInput }
     *     
     */
    public GetOpzioniCodiceVersamentoInput getGetOpzioniCodiceVersamentoInput() {
        return getOpzioniCodiceVersamentoInput;
    }

    /**
     * Imposta il valore della propriet getOpzioniCodiceVersamentoInput.
     * 
     * @param value
     *     allowed object is
     *     {@link GetOpzioniCodiceVersamentoInput }
     *     
     */
    public void setGetOpzioniCodiceVersamentoInput(GetOpzioniCodiceVersamentoInput value) {
        this.getOpzioniCodiceVersamentoInput = value;
    }

}
