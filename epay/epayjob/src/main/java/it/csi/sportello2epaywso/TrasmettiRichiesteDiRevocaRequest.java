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
 *         &lt;element name="Testata" type="{http://www.csi.it/epay/epaywso/epaywso2enti/types}TestataRichiesteDiRevocaType"/&gt;
 *         &lt;element name="CorpoRichiesteDiRevoca" type="{http://www.csi.it/epay/epaywso/epaywso2enti/types}CorpoRichiesteDiRevocaType"/&gt;
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
    "corpoRichiesteDiRevoca"
})
@XmlRootElement(name = "TrasmettiRichiesteDiRevocaRequest")
public class TrasmettiRichiesteDiRevocaRequest {

    @XmlElement(name = "Testata", required = true)
    protected TestataRichiesteDiRevocaType testata;
    @XmlElement(name = "CorpoRichiesteDiRevoca", required = true)
    protected CorpoRichiesteDiRevocaType corpoRichiesteDiRevoca;

    /**
     * Recupera il valore della proprieta testata.
     * 
     * @return
     *     possible object is
     *     {@link TestataRichiesteDiRevocaType }
     *     
     */
    public TestataRichiesteDiRevocaType getTestata() {
        return testata;
    }

    /**
     * Imposta il valore della proprieta testata.
     * 
     * @param value
     *     allowed object is
     *     {@link TestataRichiesteDiRevocaType }
     *     
     */
    public void setTestata(TestataRichiesteDiRevocaType value) {
        this.testata = value;
    }

    /**
     * Recupera il valore della proprieta corpoRichiesteDiRevoca.
     * 
     * @return
     *     possible object is
     *     {@link CorpoRichiesteDiRevocaType }
     *     
     */
    public CorpoRichiesteDiRevocaType getCorpoRichiesteDiRevoca() {
        return corpoRichiesteDiRevoca;
    }

    /**
     * Imposta il valore della proprieta corpoRichiesteDiRevoca.
     * 
     * @param value
     *     allowed object is
     *     {@link CorpoRichiesteDiRevocaType }
     *     
     */
    public void setCorpoRichiesteDiRevoca(CorpoRichiesteDiRevocaType value) {
        this.corpoRichiesteDiRevoca = value;
    }

}
