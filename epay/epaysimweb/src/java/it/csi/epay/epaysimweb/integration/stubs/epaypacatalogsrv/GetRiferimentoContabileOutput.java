/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysimweb.integration.stubs.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per getRiferimentoContabileOutput complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="getRiferimentoContabileOutput"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://interfacews.epaypacatalogsrv.epay.csi.it/}parentOutput"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="riferimentoContabile" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}getRiferimentoContabileOutputDto" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getRiferimentoContabileOutput", propOrder = {
    "riferimentoContabile"
})
public class GetRiferimentoContabileOutput
    extends ParentOutput
{

    protected GetRiferimentoContabileOutputDto riferimentoContabile;

    /**
     * Recupera il valore della propriet riferimentoContabile.
     * 
     * @return
     *     possible object is
     *     {@link GetRiferimentoContabileOutputDto }
     *     
     */
    public GetRiferimentoContabileOutputDto getRiferimentoContabile() {
        return riferimentoContabile;
    }

    /**
     * Imposta il valore della propriet riferimentoContabile.
     * 
     * @param value
     *     allowed object is
     *     {@link GetRiferimentoContabileOutputDto }
     *     
     */
    public void setRiferimentoContabile(GetRiferimentoContabileOutputDto value) {
        this.riferimentoContabile = value;
    }

}
