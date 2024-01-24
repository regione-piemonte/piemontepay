/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.integration.stubs.epaymodricws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per dtoInputWsLogAudit complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="dtoInputWsLogAudit"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="codappmodify" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="dataOra" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="descrizione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="dtoAuditAction" type="{http://epaymodric.interfacews.epaymodric.epay.csi.it/}dtoAuditAction" minOccurs="0"/&gt;
 *         &lt;element name="idaction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="uniqueid" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="utente" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtoInputWsLogAudit", propOrder = {
    "codappmodify",
    "dataOra",
    "descrizione",
    "dtoAuditAction",
    "idaction",
    "uniqueid",
    "utente"
})
public class DtoInputWsLogAudit {

    protected String codappmodify;
    protected String dataOra;
    protected String descrizione;
    protected DtoAuditAction dtoAuditAction;
    protected String idaction;
    protected Integer uniqueid;
    protected Integer utente;

    /**
     * Recupera il valore della proprieta codappmodify.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodappmodify() {
        return codappmodify;
    }

    /**
     * Imposta il valore della proprieta codappmodify.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodappmodify(String value) {
        this.codappmodify = value;
    }

    /**
     * Recupera il valore della proprieta dataOra.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataOra() {
        return dataOra;
    }

    /**
     * Imposta il valore della proprieta dataOra.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataOra(String value) {
        this.dataOra = value;
    }

    /**
     * Recupera il valore della proprieta descrizione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizione() {
        return descrizione;
    }

    /**
     * Imposta il valore della proprieta descrizione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizione(String value) {
        this.descrizione = value;
    }

    /**
     * Recupera il valore della proprieta dtoAuditAction.
     * 
     * @return
     *     possible object is
     *     {@link DtoAuditAction }
     *     
     */
    public DtoAuditAction getDtoAuditAction() {
        return dtoAuditAction;
    }

    /**
     * Imposta il valore della proprieta dtoAuditAction.
     * 
     * @param value
     *     allowed object is
     *     {@link DtoAuditAction }
     *     
     */
    public void setDtoAuditAction(DtoAuditAction value) {
        this.dtoAuditAction = value;
    }

    /**
     * Recupera il valore della proprieta idaction.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdaction() {
        return idaction;
    }

    /**
     * Imposta il valore della proprieta idaction.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdaction(String value) {
        this.idaction = value;
    }

    /**
     * Recupera il valore della proprieta uniqueid.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getUniqueid() {
        return uniqueid;
    }

    /**
     * Imposta il valore della proprieta uniqueid.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setUniqueid(Integer value) {
        this.uniqueid = value;
    }

    /**
     * Recupera il valore della proprieta utente.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getUtente() {
        return utente;
    }

    /**
     * Imposta il valore della proprieta utente.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setUtente(Integer value) {
        this.utente = value;
    }

}
