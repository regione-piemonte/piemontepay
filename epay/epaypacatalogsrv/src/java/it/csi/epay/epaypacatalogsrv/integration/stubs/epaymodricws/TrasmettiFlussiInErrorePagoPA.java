/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.integration.stubs.epaymodricws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per trasmettiFlussiInErrorePagoPA complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="trasmettiFlussiInErrorePagoPA"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="input" type="{http://epaymodric.interfacews.epaymodric.epay.csi.it/}dtoInputWsTrasmettiFlussiInErrorePagoPA" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "trasmettiFlussiInErrorePagoPA", propOrder = {
    "input"
})
public class TrasmettiFlussiInErrorePagoPA {

    protected DtoInputWsTrasmettiFlussiInErrorePagoPA input;

    /**
     * Recupera il valore della proprieta input.
     * 
     * @return
     *     possible object is
     *     {@link DtoInputWsTrasmettiFlussiInErrorePagoPA }
     *     
     */
    public DtoInputWsTrasmettiFlussiInErrorePagoPA getInput() {
        return input;
    }

    /**
     * Imposta il valore della proprieta input.
     * 
     * @param value
     *     allowed object is
     *     {@link DtoInputWsTrasmettiFlussiInErrorePagoPA }
     *     
     */
    public void setInput(DtoInputWsTrasmettiFlussiInErrorePagoPA value) {
        this.input = value;
    }

}
