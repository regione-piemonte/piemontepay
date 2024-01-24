/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.integration.stubs.epaymodricws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per trasmettiFlussiInErrorePagoPAResponse complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="trasmettiFlussiInErrorePagoPAResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="result" type="{http://epaymodric.interfacews.epaymodric.epay.csi.it/}dtoOutputWsTrasmettiFlussiInErrorePagoPA" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "trasmettiFlussiInErrorePagoPAResponse", propOrder = {
    "result"
})
public class TrasmettiFlussiInErrorePagoPAResponse {

    protected DtoOutputWsTrasmettiFlussiInErrorePagoPA result;

    /**
     * Recupera il valore della proprieta result.
     * 
     * @return
     *     possible object is
     *     {@link DtoOutputWsTrasmettiFlussiInErrorePagoPA }
     *     
     */
    public DtoOutputWsTrasmettiFlussiInErrorePagoPA getResult() {
        return result;
    }

    /**
     * Imposta il valore della proprieta result.
     * 
     * @param value
     *     allowed object is
     *     {@link DtoOutputWsTrasmettiFlussiInErrorePagoPA }
     *     
     */
    public void setResult(DtoOutputWsTrasmettiFlussiInErrorePagoPA value) {
        this.result = value;
    }

}
