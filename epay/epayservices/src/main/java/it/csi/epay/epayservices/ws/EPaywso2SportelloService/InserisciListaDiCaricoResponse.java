/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayservices.ws.EPaywso2SportelloService;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per anonymous complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.csi.it/epay/epaywso/types}ResponseType">
 *       &lt;sequence>
 *         &lt;element name="EsitoInserimento" type="{http://www.csi.it/epay/epaywso/types}EsitoInserimentoType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "esitoInserimento"
})
@XmlRootElement(name = "InserisciListaDiCaricoResponse")
public class InserisciListaDiCaricoResponse
    extends ResponseType
{

    @XmlElement(name = "EsitoInserimento")
    protected EsitoInserimentoType esitoInserimento;

    /**
     * Recupera il valore della proprieta' esitoInserimento.
     * 
     * @return
     *     possible object is
     *     {@link EsitoInserimentoType }
     *     
     */
    public EsitoInserimentoType getEsitoInserimento() {
        return esitoInserimento;
    }

    /**
     * Imposta il valore della proprieta' esitoInserimento.
     * 
     * @param value
     *     allowed object is
     *     {@link EsitoInserimentoType }
     *     
     */
    public void setEsitoInserimento(EsitoInserimentoType value) {
        this.esitoInserimento = value;
    }

}
