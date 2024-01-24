/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodricweb.integration.stubs.epaymodricws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dtoInputWsRicercaPrenotazioneReport complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dtoInputWsRicercaPrenotazioneReport">
 *   &lt;complexContent>
 *     &lt;extension base="{http://epaymodric.interfacews.epaymodric.epay.csi.it/}dtoInputDate">
 *       &lt;sequence>
 *         &lt;element name="causaleProvvisorio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idCodiceVersamento" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="idStatoFlusso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="identificativoFlusso" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
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
@XmlType(name = "dtoInputWsRicercaPrenotazioneReport", propOrder = {
    "causaleProvvisorio",
    "idCodiceVersamento",
    "idStatoFlusso",
    "identificativoFlusso",
    "iuv",
    "nominativoExport",
    "psp"
})
public class DtoInputWsRicercaPrenotazioneReport
    extends DtoInputDate
{

    protected String causaleProvvisorio;
    protected Integer idCodiceVersamento;
    protected String idStatoFlusso;
    protected Integer identificativoFlusso;
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
     * Gets the value of the idCodiceVersamento property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIdCodiceVersamento() {
        return idCodiceVersamento;
    }

    /**
     * Sets the value of the idCodiceVersamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIdCodiceVersamento(Integer value) {
        this.idCodiceVersamento = value;
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
     * Gets the value of the identificativoFlusso property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIdentificativoFlusso() {
        return identificativoFlusso;
    }

    /**
     * Sets the value of the identificativoFlusso property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIdentificativoFlusso(Integer value) {
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
