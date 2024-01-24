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
 *         &lt;element name="EsitoAggiornamento" type="{http://www.csi.it/epay/epaywso/types}EsitoAggiornamentoType" minOccurs="0"/>
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
    "esitoAggiornamento"
})
@XmlRootElement(name = "AggiornaPosizioniDebitorieResponse")
public class AggiornaPosizioniDebitorieResponse
    extends ResponseType
{

    @XmlElement(name = "EsitoAggiornamento")
    protected EsitoAggiornamentoType esitoAggiornamento;

    /**
     * Recupera il valore della proprieta' esitoAggiornamento.
     * 
     * @return
     *     possible object is
     *     {@link EsitoAggiornamentoType }
     *     
     */
    public EsitoAggiornamentoType getEsitoAggiornamento() {
        return esitoAggiornamento;
    }

    /**
     * Imposta il valore della proprieta' esitoAggiornamento.
     * 
     * @param value
     *     allowed object is
     *     {@link EsitoAggiornamentoType }
     *     
     */
    public void setEsitoAggiornamento(EsitoAggiornamentoType value) {
        this.esitoAggiornamento = value;
    }

}
