/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysim.integration.epaymodric.stubs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dtpOutputElaborazione complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dtpOutputElaborazione">
 *   &lt;complexContent>
 *     &lt;extension base="{http://epaymodric.interfacews.epaymodric.epay.csi.it/}dtoBaseContabilizzatore">
 *       &lt;sequence>
 *         &lt;element name="idElaborazione" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="idEnte" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="risultatoOutputEstemporanea" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtpOutputElaborazione", namespace = "http://epaymodric.interfacews.epaymodric.epay.csi.it/", propOrder = {
    "idElaborazione",
    "idEnte",
    "risultatoOutputEstemporanea"
})
public class DtpOutputElaborazione
    extends DtoBaseContabilizzatore
{

    @XmlElement(namespace = "")
    protected Long idElaborazione;
    @XmlElement(namespace = "")
    protected String idEnte;
    @XmlElement(namespace = "")
    protected String risultatoOutputEstemporanea;

    /**
     * Gets the value of the idElaborazione property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdElaborazione() {
        return idElaborazione;
    }

    /**
     * Sets the value of the idElaborazione property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdElaborazione(Long value) {
        this.idElaborazione = value;
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
     * Gets the value of the risultatoOutputEstemporanea property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRisultatoOutputEstemporanea() {
        return risultatoOutputEstemporanea;
    }

    /**
     * Sets the value of the risultatoOutputEstemporanea property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRisultatoOutputEstemporanea(String value) {
        this.risultatoOutputEstemporanea = value;
    }

}
