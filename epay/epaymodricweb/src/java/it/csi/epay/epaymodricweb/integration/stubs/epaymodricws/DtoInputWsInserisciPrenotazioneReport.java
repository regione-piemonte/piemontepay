/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodricweb.integration.stubs.epaymodricws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dtoInputWsInserisciPrenotazioneReport complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dtoInputWsInserisciPrenotazioneReport">
 *   &lt;complexContent>
 *     &lt;extension base="{http://epaymodric.interfacews.epaymodric.epay.csi.it/}dtoInputDate">
 *       &lt;sequence>
 *         &lt;element name="causaleProvvisorio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codiceFiscaleEnte" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codiceFiscaleUtente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idCodiceVersamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idEnte" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idFile" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idStato" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idStatoFlusso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idTipoFile" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idTipoReport" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idUtente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="identificativoFlusso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iuv" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nominativoExport" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="psp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtoInputWsInserisciPrenotazioneReport", propOrder = {
    "causaleProvvisorio",
    "codiceFiscaleEnte",
    "codiceFiscaleUtente",
    "idCodiceVersamento",
    "idEnte",
    "idFile",
    "idStato",
    "idStatoFlusso",
    "idTipoFile",
    "idTipoReport",
    "idUtente",
    "identificativoFlusso",
    "iuv",
    "nominativoExport",
    "psp"
})
public class DtoInputWsInserisciPrenotazioneReport
    extends DtoInputDate
{

    protected String causaleProvvisorio;
    protected String codiceFiscaleEnte;
    protected String codiceFiscaleUtente;
    protected String idCodiceVersamento;
    protected String idEnte;
    protected String idFile;
    protected String idStato;
    protected String idStatoFlusso;
    protected String idTipoFile;
    protected String idTipoReport;
    protected String idUtente;
    protected String identificativoFlusso;
    protected String iuv;
    protected String nominativoExport;
    protected String psp;

    /**
     * Gets the value of the causaleProvvisorio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCausaleProvvisorio() {
        return causaleProvvisorio;
    }

    /**
     * Sets the value of the causaleProvvisorio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCausaleProvvisorio(String value) {
        this.causaleProvvisorio = value;
    }

    /**
     * Gets the value of the codiceFiscaleEnte property.
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
     * Sets the value of the codiceFiscaleEnte property.
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
     * Gets the value of the codiceFiscaleUtente property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceFiscaleUtente() {
        return codiceFiscaleUtente;
    }

    /**
     * Sets the value of the codiceFiscaleUtente property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceFiscaleUtente(String value) {
        this.codiceFiscaleUtente = value;
    }

    /**
     * Gets the value of the idCodiceVersamento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdCodiceVersamento() {
        return idCodiceVersamento;
    }

    /**
     * Sets the value of the idCodiceVersamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdCodiceVersamento(String value) {
        this.idCodiceVersamento = value;
    }

    /**
     * Gets the value of the idEnte property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdEnte() {
        return idEnte;
    }

    /**
     * Sets the value of the idEnte property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdEnte(String value) {
        this.idEnte = value;
    }

    /**
     * Gets the value of the idFile property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdFile() {
        return idFile;
    }

    /**
     * Sets the value of the idFile property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdFile(String value) {
        this.idFile = value;
    }

    /**
     * Gets the value of the idStato property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdStato() {
        return idStato;
    }

    /**
     * Sets the value of the idStato property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdStato(String value) {
        this.idStato = value;
    }

    /**
     * Gets the value of the idStatoFlusso property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdStatoFlusso() {
        return idStatoFlusso;
    }

    /**
     * Sets the value of the idStatoFlusso property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdStatoFlusso(String value) {
        this.idStatoFlusso = value;
    }

    /**
     * Gets the value of the idTipoFile property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdTipoFile() {
        return idTipoFile;
    }

    /**
     * Sets the value of the idTipoFile property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdTipoFile(String value) {
        this.idTipoFile = value;
    }

    /**
     * Gets the value of the idTipoReport property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdTipoReport() {
        return idTipoReport;
    }

    /**
     * Sets the value of the idTipoReport property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdTipoReport(String value) {
        this.idTipoReport = value;
    }

    /**
     * Gets the value of the idUtente property.
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
     * Sets the value of the idUtente property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdUtente(String value) {
        this.idUtente = value;
    }

    /**
     * Gets the value of the identificativoFlusso property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentificativoFlusso() {
        return identificativoFlusso;
    }

    /**
     * Sets the value of the identificativoFlusso property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentificativoFlusso(String value) {
        this.identificativoFlusso = value;
    }

    /**
     * Gets the value of the iuv property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIuv() {
        return iuv;
    }

    /**
     * Sets the value of the iuv property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIuv(String value) {
        this.iuv = value;
    }

    /**
     * Gets the value of the nominativoExport property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNominativoExport() {
        return nominativoExport;
    }

    /**
     * Sets the value of the nominativoExport property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNominativoExport(String value) {
        this.nominativoExport = value;
    }

    /**
     * Gets the value of the psp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPsp() {
        return psp;
    }

    /**
     * Sets the value of the psp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPsp(String value) {
        this.psp = value;
    }

}
