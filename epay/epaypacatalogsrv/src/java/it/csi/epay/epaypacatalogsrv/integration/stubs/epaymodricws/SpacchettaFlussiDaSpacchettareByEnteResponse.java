/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.integration.stubs.epaymodricws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per spacchettaFlussiDaSpacchettareByEnteResponse complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="spacchettaFlussiDaSpacchettareByEnteResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="result" type="{http://epaymodric.interfacews.epaymodric.epay.csi.it/}dtpOutputWsElaborazione" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "spacchettaFlussiDaSpacchettareByEnteResponse", propOrder = {
    "result"
})
public class SpacchettaFlussiDaSpacchettareByEnteResponse {

    protected DtpOutputWsElaborazione result;

    /**
     * Recupera il valore della proprieta result.
     * 
     * @return
     *     possible object is
     *     {@link DtpOutputWsElaborazione }
     *     
     */
    public DtpOutputWsElaborazione getResult() {
        return result;
    }

    /**
     * Imposta il valore della proprieta result.
     * 
     * @param value
     *     allowed object is
     *     {@link DtpOutputWsElaborazione }
     *     
     */
    public void setResult(DtpOutputWsElaborazione value) {
        this.result = value;
    }

}
