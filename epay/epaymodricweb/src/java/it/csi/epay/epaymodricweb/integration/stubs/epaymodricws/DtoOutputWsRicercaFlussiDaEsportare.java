/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodricweb.integration.stubs.epaymodricws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dtoOutputWsRicercaFlussiDaEsportare complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dtoOutputWsRicercaFlussiDaEsportare">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="contenuto" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="esito" type="{http://epaymodric.interfacews.epaymodric.epay.csi.it/}dtoOutputWsEsito" minOccurs="0"/>
 *         &lt;element name="nomeFile" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtoOutputWsRicercaFlussiDaEsportare", propOrder = {
    "contenuto",
    "esito",
    "nomeFile"
})
public class DtoOutputWsRicercaFlussiDaEsportare {

    protected byte[] contenuto;
    protected DtoOutputWsEsito esito;
    protected String nomeFile;

    /**
     * Gets the value of the contenuto property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getContenuto() {
        return contenuto;
    }

    /**
     * Sets the value of the contenuto property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setContenuto(byte[] value) {
        this.contenuto = ((byte[]) value);
    }

    /**
     * Gets the value of the esito property.
     * 
     * @return
     *     possible object is
     *     {@link DtoOutputWsEsito }
     *     
     */
    public DtoOutputWsEsito getEsito() {
        return esito;
    }

    /**
     * Sets the value of the esito property.
     * 
     * @param value
     *     allowed object is
     *     {@link DtoOutputWsEsito }
     *     
     */
    public void setEsito(DtoOutputWsEsito value) {
        this.esito = value;
    }

    /**
     * Gets the value of the nomeFile property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomeFile() {
        return nomeFile;
    }

    /**
     * Sets the value of the nomeFile property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomeFile(String value) {
        this.nomeFile = value;
    }

}
