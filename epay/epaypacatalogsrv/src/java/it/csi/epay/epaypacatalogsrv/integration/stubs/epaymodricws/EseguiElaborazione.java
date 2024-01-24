/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.integration.stubs.epaymodricws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per eseguiElaborazione complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="eseguiElaborazione"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="codiceEnte" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="identificativiFlusso" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="isRiesecuzione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="idElaborazione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "eseguiElaborazione", propOrder = {
    "codiceEnte",
    "identificativiFlusso",
    "isRiesecuzione",
    "idElaborazione"
})
public class EseguiElaborazione {

    protected String codiceEnte;
    protected List<String> identificativiFlusso;
    protected String isRiesecuzione;
    protected String idElaborazione;

    /**
     * Recupera il valore della proprieta codiceEnte.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceEnte() {
        return codiceEnte;
    }

    /**
     * Imposta il valore della proprieta codiceEnte.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceEnte(String value) {
        this.codiceEnte = value;
    }

    /**
     * Gets the value of the identificativiFlusso property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the identificativiFlusso property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIdentificativiFlusso().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getIdentificativiFlusso() {
        if (identificativiFlusso == null) {
            identificativiFlusso = new ArrayList<String>();
        }
        return this.identificativiFlusso;
    }

    /**
     * Recupera il valore della proprieta isRiesecuzione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsRiesecuzione() {
        return isRiesecuzione;
    }

    /**
     * Imposta il valore della proprieta isRiesecuzione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsRiesecuzione(String value) {
        this.isRiesecuzione = value;
    }

    /**
     * Recupera il valore della proprieta idElaborazione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdElaborazione() {
        return idElaborazione;
    }

    /**
     * Imposta il valore della proprieta idElaborazione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdElaborazione(String value) {
        this.idElaborazione = value;
    }

}
