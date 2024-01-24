/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.util.wsdl.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getUtenteOutput complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getUtenteOutput">
 *   &lt;complexContent>
 *     &lt;extension base="{http://interfacews.epaypacatalogsrv.epay.csi.it/}parentOutput">
 *       &lt;sequence>
 *         &lt;element name="utente" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}getUtenteOutputDto" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getUtenteOutput", propOrder = {
    "utente"
})
public class GetUtenteOutput
    extends ParentOutput
{

    protected GetUtenteOutputDto utente;

    /**
     * Gets the value of the utente property.
     * 
     * @return
     *     possible object is
     *     {@link GetUtenteOutputDto }
     *     
     */
    public GetUtenteOutputDto getUtente() {
        return utente;
    }

    /**
     * Sets the value of the utente property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetUtenteOutputDto }
     *     
     */
    public void setUtente(GetUtenteOutputDto value) {
        this.utente = value;
    }

}
