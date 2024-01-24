/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.integration.stubs.epaymodricws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per dtoInputWsLock complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="dtoInputWsLock"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="codiceFiscaleEnte" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="dataFine" type="{http://epaymodric.interfacews.epaymodric.epay.csi.it/}timestamp" minOccurs="0"/&gt;
 *         &lt;element name="dataInizio" type="{http://epaymodric.interfacews.epaymodric.epay.csi.it/}timestamp" minOccurs="0"/&gt;
 *         &lt;element name="idUtente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtoInputWsLock", propOrder = {
    "codiceFiscaleEnte",
    "dataFine",
    "dataInizio",
    "idUtente"
})
public class DtoInputWsLock {

    protected String codiceFiscaleEnte;
    protected Timestamp dataFine;
    protected Timestamp dataInizio;
    protected String idUtente;

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
     * Recupera il valore della proprieta dataFine.
     * 
     * @return
     *     possible object is
     *     {@link Timestamp }
     *     
     */
    public Timestamp getDataFine() {
        return dataFine;
    }

    /**
     * Imposta il valore della proprieta dataFine.
     * 
     * @param value
     *     allowed object is
     *     {@link Timestamp }
     *     
     */
    public void setDataFine(Timestamp value) {
        this.dataFine = value;
    }

    /**
     * Recupera il valore della proprieta dataInizio.
     * 
     * @return
     *     possible object is
     *     {@link Timestamp }
     *     
     */
    public Timestamp getDataInizio() {
        return dataInizio;
    }

    /**
     * Imposta il valore della proprieta dataInizio.
     * 
     * @param value
     *     allowed object is
     *     {@link Timestamp }
     *     
     */
    public void setDataInizio(Timestamp value) {
        this.dataInizio = value;
    }

    /**
     * Recupera il valore della proprieta idUtente.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdUtente() {
        return idUtente;
    }

    /**
     * Imposta il valore della proprieta idUtente.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdUtente(String value) {
        this.idUtente = value;
    }

}
