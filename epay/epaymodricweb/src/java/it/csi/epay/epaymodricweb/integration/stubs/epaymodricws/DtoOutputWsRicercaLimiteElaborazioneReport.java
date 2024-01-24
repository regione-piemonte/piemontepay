/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodricweb.integration.stubs.epaymodricws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dtoOutputWsRicercaLimiteElaborazioneReport complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dtoOutputWsRicercaLimiteElaborazioneReport">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="esito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numMaxRecordsReport" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtoOutputWsRicercaLimiteElaborazioneReport", propOrder = {
    "esito",
    "numMaxRecordsReport"
})
public class DtoOutputWsRicercaLimiteElaborazioneReport {

    protected String esito;
    protected String numMaxRecordsReport;

    /**
     * Gets the value of the esito property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEsito() {
        return esito;
    }

    /**
     * Sets the value of the esito property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEsito(String value) {
        this.esito = value;
    }

    /**
     * Gets the value of the numMaxRecordsReport property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumMaxRecordsReport() {
        return numMaxRecordsReport;
    }

    /**
     * Sets the value of the numMaxRecordsReport property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumMaxRecordsReport(String value) {
        this.numMaxRecordsReport = value;
    }

}
