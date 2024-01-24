/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodricweb.integration.stubs.epaymodricws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for dtoOutputWsRicercaPrenotazioneReport complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dtoOutputWsRicercaPrenotazioneReport">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codiceFiscaleEnte" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codiceFiscaleUtente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataOraPrenotazioneReport" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="fileReport" type="{http://epaymodric.interfacews.epaymodric.epay.csi.it/}dtoFileReport" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="idUtente" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="nominativoExport" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="statoReport" type="{http://epaymodric.interfacews.epaymodric.epay.csi.it/}dtoStatoReport" minOccurs="0"/>
 *         &lt;element name="tipoFileReport" type="{http://epaymodric.interfacews.epaymodric.epay.csi.it/}dtoTipoFileReport" minOccurs="0"/>
 *         &lt;element name="tipoReport" type="{http://epaymodric.interfacews.epaymodric.epay.csi.it/}dtoTipoReport" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtoOutputWsRicercaPrenotazioneReport", propOrder = {
    "codiceFiscaleEnte",
    "codiceFiscaleUtente",
    "dataOraPrenotazioneReport",
    "fileReport",
    "id",
    "idUtente",
    "nominativoExport",
    "statoReport",
    "tipoFileReport",
    "tipoReport"
})
public class DtoOutputWsRicercaPrenotazioneReport {

    protected String codiceFiscaleEnte;
    protected String codiceFiscaleUtente;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataOraPrenotazioneReport;
    protected DtoFileReport fileReport;
    protected Long id;
    protected Long idUtente;
    protected String nominativoExport;
    protected DtoStatoReport statoReport;
    protected DtoTipoFileReport tipoFileReport;
    protected DtoTipoReport tipoReport;

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
     * Gets the value of the dataOraPrenotazioneReport property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataOraPrenotazioneReport() {
        return dataOraPrenotazioneReport;
    }

    /**
     * Sets the value of the dataOraPrenotazioneReport property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataOraPrenotazioneReport(XMLGregorianCalendar value) {
        this.dataOraPrenotazioneReport = value;
    }

    /**
     * Gets the value of the fileReport property.
     * 
     * @return
     *     possible object is
     *     {@link DtoFileReport }
     *     
     */
    public DtoFileReport getFileReport() {
        return fileReport;
    }

    /**
     * Sets the value of the fileReport property.
     * 
     * @param value
     *     allowed object is
     *     {@link DtoFileReport }
     *     
     */
    public void setFileReport(DtoFileReport value) {
        this.fileReport = value;
    }

    /**
     * Gets the value of the id property.
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
     * Sets the value of the id property.
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
     * Gets the value of the idUtente property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdUtente() {
        return idUtente;
    }

    /**
     * Sets the value of the idUtente property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdUtente(Long value) {
        this.idUtente = value;
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
     * Gets the value of the statoReport property.
     * 
     * @return
     *     possible object is
     *     {@link DtoStatoReport }
     *     
     */
    public DtoStatoReport getStatoReport() {
        return statoReport;
    }

    /**
     * Sets the value of the statoReport property.
     * 
     * @param value
     *     allowed object is
     *     {@link DtoStatoReport }
     *     
     */
    public void setStatoReport(DtoStatoReport value) {
        this.statoReport = value;
    }

    /**
     * Gets the value of the tipoFileReport property.
     * 
     * @return
     *     possible object is
     *     {@link DtoTipoFileReport }
     *     
     */
    public DtoTipoFileReport getTipoFileReport() {
        return tipoFileReport;
    }

    /**
     * Sets the value of the tipoFileReport property.
     * 
     * @param value
     *     allowed object is
     *     {@link DtoTipoFileReport }
     *     
     */
    public void setTipoFileReport(DtoTipoFileReport value) {
        this.tipoFileReport = value;
    }

    /**
     * Gets the value of the tipoReport property.
     * 
     * @return
     *     possible object is
     *     {@link DtoTipoReport }
     *     
     */
    public DtoTipoReport getTipoReport() {
        return tipoReport;
    }

    /**
     * Sets the value of the tipoReport property.
     * 
     * @param value
     *     allowed object is
     *     {@link DtoTipoReport }
     *     
     */
    public void setTipoReport(DtoTipoReport value) {
        this.tipoReport = value;
    }

}
