/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysimweb.integration.stubs.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per getMessaggiOutputDto complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="getMessaggiOutputDto"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="codice" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="codiceApplicativo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="codiceBreve" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="codiceLingua" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="messaggio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getMessaggiOutputDto", propOrder = {
    "codice",
    "codiceApplicativo",
    "codiceBreve",
    "codiceLingua",
    "id",
    "messaggio"
})
public class GetMessaggiOutputDto {

    protected String codice;
    protected String codiceApplicativo;
    protected String codiceBreve;
    protected String codiceLingua;
    protected Long id;
    protected String messaggio;

    /**
     * Recupera il valore della propriet codice.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodice() {
        return codice;
    }

    /**
     * Imposta il valore della propriet codice.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodice(String value) {
        this.codice = value;
    }

    /**
     * Recupera il valore della propriet codiceApplicativo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceApplicativo() {
        return codiceApplicativo;
    }

    /**
     * Imposta il valore della propriet codiceApplicativo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceApplicativo(String value) {
        this.codiceApplicativo = value;
    }

    /**
     * Recupera il valore della propriet codiceBreve.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceBreve() {
        return codiceBreve;
    }

    /**
     * Imposta il valore della propriet codiceBreve.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceBreve(String value) {
        this.codiceBreve = value;
    }

    /**
     * Recupera il valore della propriet codiceLingua.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceLingua() {
        return codiceLingua;
    }

    /**
     * Imposta il valore della propriet codiceLingua.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceLingua(String value) {
        this.codiceLingua = value;
    }

    /**
     * Recupera il valore della propriet id.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getId() {
        return id;
    }

    /**
     * Imposta il valore della propriet id.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setId(Long value) {
        this.id = value;
    }

    /**
     * Recupera il valore della propriet messaggio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessaggio() {
        return messaggio;
    }

    /**
     * Imposta il valore della propriet messaggio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessaggio(String value) {
        this.messaggio = value;
    }

}
