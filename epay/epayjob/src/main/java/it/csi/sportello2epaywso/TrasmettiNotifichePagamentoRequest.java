/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.sportello2epaywso;

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
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Testata" type="{http://www.csi.it/epay/epaywso/epaywso2enti/types}TestataNotifichePagamentoType"/&gt;
 *         &lt;element name="CorpoNotifichePagamento" type="{http://www.csi.it/epay/epaywso/epaywso2enti/types}CorpoNotifichePagamentoType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "testata",
    "corpoNotifichePagamento"
})
@XmlRootElement(name = "TrasmettiNotifichePagamentoRequest")
public class TrasmettiNotifichePagamentoRequest {

    @XmlElement(name = "Testata", required = true)
    protected TestataNotifichePagamentoType testata;
    @XmlElement(name = "CorpoNotifichePagamento", required = true)
    protected CorpoNotifichePagamentoType corpoNotifichePagamento;

    /**
     * Recupera il valore della proprieta testata.
     * 
     * @return
     *     possible object is
     *     {@link TestataNotifichePagamentoType }
     *     
     */
    public TestataNotifichePagamentoType getTestata() {
        return testata;
    }

    /**
     * Imposta il valore della proprieta testata.
     * 
     * @param value
     *     allowed object is
     *     {@link TestataNotifichePagamentoType }
     *     
     */
    public void setTestata(TestataNotifichePagamentoType value) {
        this.testata = value;
    }

    /**
     * Recupera il valore della proprieta corpoNotifichePagamento.
     * 
     * @return
     *     possible object is
     *     {@link CorpoNotifichePagamentoType }
     *     
     */
    public CorpoNotifichePagamentoType getCorpoNotifichePagamento() {
        return corpoNotifichePagamento;
    }

    /**
     * Imposta il valore della proprieta corpoNotifichePagamento.
     * 
     * @param value
     *     allowed object is
     *     {@link CorpoNotifichePagamentoType }
     *     
     */
    public void setCorpoNotifichePagamento(CorpoNotifichePagamentoType value) {
        this.corpoNotifichePagamento = value;
    }

}
