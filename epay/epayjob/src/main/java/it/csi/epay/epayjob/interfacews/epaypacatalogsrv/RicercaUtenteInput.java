/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayjob.interfacews.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ricercaUtenteInput complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ricercaUtenteInput">
 *   &lt;complexContent>
 *     &lt;extension base="{http://interfacews.epaypacatalogsrv.epay.csi.it/}parentInput">
 *       &lt;sequence>
 *         &lt;element name="codiceCategoriaCdu" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codiceCdu" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codiceFiscale" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codiceTematica" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cognome" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idCodiceVersamento" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="nome" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="soloUtentiInVita" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ricercaUtenteInput", propOrder = {
    "codiceCategoriaCdu",
    "codiceCdu",
    "codiceFiscale",
    "codiceTematica",
    "cognome",
    "email",
    "idCodiceVersamento",
    "nome",
    "soloUtentiInVita"
})
public class RicercaUtenteInput
    extends ParentInput
{

    protected String codiceCategoriaCdu;
    protected String codiceCdu;
    protected String codiceFiscale;
    protected String codiceTematica;
    protected String cognome;
    protected String email;
    protected Long idCodiceVersamento;
    protected String nome;
    protected Boolean soloUtentiInVita;

    /**
     * Gets the value of the codiceCategoriaCdu property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceCategoriaCdu() {
        return codiceCategoriaCdu;
    }

    /**
     * Sets the value of the codiceCategoriaCdu property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceCategoriaCdu(String value) {
        this.codiceCategoriaCdu = value;
    }

    /**
     * Gets the value of the codiceCdu property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceCdu() {
        return codiceCdu;
    }

    /**
     * Sets the value of the codiceCdu property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceCdu(String value) {
        this.codiceCdu = value;
    }

    /**
     * Gets the value of the codiceFiscale property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    /**
     * Sets the value of the codiceFiscale property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceFiscale(String value) {
        this.codiceFiscale = value;
    }

    /**
     * Gets the value of the codiceTematica property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceTematica() {
        return codiceTematica;
    }

    /**
     * Sets the value of the codiceTematica property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceTematica(String value) {
        this.codiceTematica = value;
    }

    /**
     * Gets the value of the cognome property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * Sets the value of the cognome property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCognome(String value) {
        this.cognome = value;
    }

    /**
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Gets the value of the idCodiceVersamento property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdCodiceVersamento() {
        return idCodiceVersamento;
    }

    /**
     * Sets the value of the idCodiceVersamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdCodiceVersamento(Long value) {
        this.idCodiceVersamento = value;
    }

    /**
     * Gets the value of the nome property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNome() {
        return nome;
    }

    /**
     * Sets the value of the nome property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNome(String value) {
        this.nome = value;
    }

    /**
     * Gets the value of the soloUtentiInVita property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSoloUtentiInVita() {
        return soloUtentiInVita;
    }

    /**
     * Sets the value of the soloUtentiInVita property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSoloUtentiInVita(Boolean value) {
        this.soloUtentiInVita = value;
    }

}
