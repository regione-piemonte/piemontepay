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
 *         &lt;element name="Testata" type="{http://www.csi.it/epay/epaywso/enti2epaywso/types}TestataListaCarico"/>
 *         &lt;element name="ListaDiCarico" type="{http://www.csi.it/epay/epaywso/enti2epaywso/types}ListaDiCarico"/>
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
    "listaDiCarico"
})
@XmlRootElement(name = "InserisciListaDiCaricoRequest")
public class InserisciListaDiCaricoRequest {

    @XmlElement(name = "Testata", required = true)
    protected TestataListaCarico testata;
    @XmlElement(name = "ListaDiCarico", required = true)
    protected ListaDiCarico listaDiCarico;

    /**
     * Recupera il valore della proprieta' testata.
     * 
     * @return
     *     possible object is
     *     {@link TestataListaCarico }
     *     
     */
    public TestataListaCarico getTestata() {
        return testata;
    }

    /**
     * Imposta il valore della proprieta' testata.
     * 
     * @param value
     *     allowed object is
     *     {@link TestataListaCarico }
     *     
     */
    public void setTestata(TestataListaCarico value) {
        this.testata = value;
    }

    /**
     * Recupera il valore della proprieta' listaDiCarico.
     * 
     * @return
     *     possible object is
     *     {@link ListaDiCarico }
     *     
     */
    public ListaDiCarico getListaDiCarico() {
        return listaDiCarico;
    }

    /**
     * Imposta il valore della proprieta' listaDiCarico.
     * 
     * @param value
     *     allowed object is
     *     {@link ListaDiCarico }
     *     
     */
    public void setListaDiCarico(ListaDiCarico value) {
        this.listaDiCarico = value;
    }

}
