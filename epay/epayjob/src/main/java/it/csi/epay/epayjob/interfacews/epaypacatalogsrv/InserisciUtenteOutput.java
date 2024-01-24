/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayjob.interfacews.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for inserisciUtenteOutput complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="inserisciUtenteOutput">
 *   &lt;complexContent>
 *     &lt;extension base="{http://interfacews.epaypacatalogsrv.epay.csi.it/}parentOutput">
 *       &lt;sequence>
 *         &lt;element name="risultatoInserimento" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}inserisciUtenteOutputDto" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "inserisciUtenteOutput", propOrder = {
    "risultatoInserimento"
})
public class InserisciUtenteOutput
    extends ParentOutput
{

    protected InserisciUtenteOutputDto risultatoInserimento;

    /**
     * Gets the value of the risultatoInserimento property.
     * 
     * @return
     *     possible object is
     *     {@link InserisciUtenteOutputDto }
     *     
     */
    public InserisciUtenteOutputDto getRisultatoInserimento() {
        return risultatoInserimento;
    }

    /**
     * Sets the value of the risultatoInserimento property.
     * 
     * @param value
     *     allowed object is
     *     {@link InserisciUtenteOutputDto }
     *     
     */
    public void setRisultatoInserimento(InserisciUtenteOutputDto value) {
        this.risultatoInserimento = value;
    }

}
