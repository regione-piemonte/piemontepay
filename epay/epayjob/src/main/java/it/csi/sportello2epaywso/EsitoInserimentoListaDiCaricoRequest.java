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
 *     &lt;extension base="{http://www.csi.it/epay/epaywso/types}ResponseType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="TestataEsito" type="{http://www.csi.it/epay/epaywso/epaywso2enti/types}TestataEsitoType"/&gt;
 *         &lt;element name="EsitoInserimento" type="{http://www.csi.it/epay/epaywso/types}EsitoInserimentoType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "testataEsito",
    "esitoInserimento"
})
@XmlRootElement(name = "EsitoInserimentoListaDiCaricoRequest")
public class EsitoInserimentoListaDiCaricoRequest
    extends ResponseType
{

    @XmlElement(name = "TestataEsito", required = true)
    protected TestataEsitoType testataEsito;
    @XmlElement(name = "EsitoInserimento")
    protected EsitoInserimentoType esitoInserimento;

    /**
     * Recupera il valore della proprieta testataEsito.
     * 
     * @return
     *     possible object is
     *     {@link TestataEsitoType }
     *     
     */
    public TestataEsitoType getTestataEsito() {
        return testataEsito;
    }

    /**
     * Imposta il valore della proprieta testataEsito.
     * 
     * @param value
     *     allowed object is
     *     {@link TestataEsitoType }
     *     
     */
    public void setTestataEsito(TestataEsitoType value) {
        this.testataEsito = value;
    }

    /**
     * Recupera il valore della proprieta esitoInserimento.
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
     * Imposta il valore della proprieta esitoInserimento.
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
