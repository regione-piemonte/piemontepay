/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.util.wsdl.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for inserisciRiferimentoContabileOutput complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="inserisciRiferimentoContabileOutput">
 *   &lt;complexContent>
 *     &lt;extension base="{http://interfacews.epaypacatalogsrv.epay.csi.it/}parentOutput">
 *       &lt;sequence>
 *         &lt;element name="risultatoInserimento" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}inserisciRiferimentoContabileOutputDto" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "inserisciRiferimentoContabileOutput", propOrder = {
    "risultatoInserimento"
})
public class InserisciRiferimentoContabileOutput
    extends ParentOutput
{

    protected InserisciRiferimentoContabileOutputDto risultatoInserimento;

    /**
     * Gets the value of the risultatoInserimento property.
     * 
     * @return
     *     possible object is
     *     {@link InserisciRiferimentoContabileOutputDto }
     *     
     */
    public InserisciRiferimentoContabileOutputDto getRisultatoInserimento() {
        return risultatoInserimento;
    }

    /**
     * Sets the value of the risultatoInserimento property.
     * 
     * @param value
     *     allowed object is
     *     {@link InserisciRiferimentoContabileOutputDto }
     *     
     */
    public void setRisultatoInserimento(InserisciRiferimentoContabileOutputDto value) {
        this.risultatoInserimento = value;
    }

}
