/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.integration.stubs.epaymodricws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per dtoInputWsSpacchettamentoFlussoRendicontazione complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="dtoInputWsSpacchettamentoFlussoRendicontazione"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="codiceFiscaleEnte" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="idFlussoOrigine" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtoInputWsSpacchettamentoFlussoRendicontazione", propOrder = {
    "codiceFiscaleEnte",
    "idFlussoOrigine"
})
public class DtoInputWsSpacchettamentoFlussoRendicontazione {

    protected String codiceFiscaleEnte;
    protected String idFlussoOrigine;

    /**
     * Recupera il valore della proprieta codiceFiscaleEnte.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceFiscaleEnte() {
        return codiceFiscaleEnte;
    }

    /**
     * Imposta il valore della proprieta codiceFiscaleEnte.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceFiscaleEnte(String value) {
        this.codiceFiscaleEnte = value;
    }

    /**
     * Recupera il valore della proprieta idFlussoOrigine.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdFlussoOrigine() {
        return idFlussoOrigine;
    }

    /**
     * Imposta il valore della proprieta idFlussoOrigine.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdFlussoOrigine(String value) {
        this.idFlussoOrigine = value;
    }

}
