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
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Testata" type="{http://www.csi.it/epay/epaywso/enti2epaywso/types}TestataAggiornaPosizioniDebitorie"/>
 *         &lt;element name="ElencoPosizioniDaAggiornare" type="{http://www.csi.it/epay/epaywso/enti2epaywso/types}ElencoPosizioniDaAggiornareType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "testata",
    "elencoPosizioniDaAggiornare"
})
@XmlRootElement(name = "AggiornaPosizioniDebitorieRequest")
public class AggiornaPosizioniDebitorieRequest {

    @XmlElement(name = "Testata", required = true)
    protected TestataAggiornaPosizioniDebitorie testata;
    @XmlElement(name = "ElencoPosizioniDaAggiornare", required = true)
    protected ElencoPosizioniDaAggiornareType elencoPosizioniDaAggiornare;

    /**
     * Recupera il valore della proprieta' testata.
     * 
     * @return
     *     possible object is
     *     {@link TestataAggiornaPosizioniDebitorie }
     *     
     */
    public TestataAggiornaPosizioniDebitorie getTestata() {
        return testata;
    }

    /**
     * Imposta il valore della proprieta' testata.
     * 
     * @param value
     *     allowed object is
     *     {@link TestataAggiornaPosizioniDebitorie }
     *     
     */
    public void setTestata(TestataAggiornaPosizioniDebitorie value) {
        this.testata = value;
    }

    /**
     * Recupera il valore della proprieta' elencoPosizioniDaAggiornare.
     * 
     * @return
     *     possible object is
     *     {@link ElencoPosizioniDaAggiornareType }
     *     
     */
    public ElencoPosizioniDaAggiornareType getElencoPosizioniDaAggiornare() {
        return elencoPosizioniDaAggiornare;
    }

    /**
     * Imposta il valore della proprieta' elencoPosizioniDaAggiornare.
     * 
     * @param value
     *     allowed object is
     *     {@link ElencoPosizioniDaAggiornareType }
     *     
     */
    public void setElencoPosizioniDaAggiornare(ElencoPosizioniDaAggiornareType value) {
        this.elencoPosizioniDaAggiornare = value;
    }

}
