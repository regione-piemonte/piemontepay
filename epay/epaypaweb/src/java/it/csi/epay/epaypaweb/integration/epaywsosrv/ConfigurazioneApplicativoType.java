/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypaweb.integration.epaywsosrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ConfigurazioneApplicativoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ConfigurazioneApplicativoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CodiceFiscaleEnte" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DenominazioneEnte" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DescrizioneCodiceVersamento" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IdApplicativo" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="DescrizioneApplicativo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Endpoint" type="{http://www.csi.it/epay/epaywso/types}EndpointType"/>
 *         &lt;element name="UtenteApplicativo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="MSInbound" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="MSOutbound" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConfigurazioneApplicativoType", propOrder = {
    "codiceFiscaleEnte",
    "denominazioneEnte",
    "descrizioneCodiceVersamento",
    "idApplicativo",
    "descrizioneApplicativo",
    "endpoint",
    "utenteApplicativo",
    "msInbound",
    "msOutbound"
})
public class ConfigurazioneApplicativoType {

    @XmlElement(name = "CodiceFiscaleEnte", required = true)
    protected String codiceFiscaleEnte;
    @XmlElement(name = "DenominazioneEnte", required = true)
    protected String denominazioneEnte;
    @XmlElement(name = "DescrizioneCodiceVersamento", required = true)
    protected String descrizioneCodiceVersamento;
    @XmlElement(name = "IdApplicativo", required = true, type = Integer.class, nillable = true)
    protected Integer idApplicativo;
    @XmlElement(name = "DescrizioneApplicativo", required = true)
    protected String descrizioneApplicativo;
    @XmlElement(name = "Endpoint", required = true)
    protected EndpointType endpoint;
    @XmlElement(name = "UtenteApplicativo", required = true)
    protected String utenteApplicativo;
    @XmlElement(name = "MSInbound", required = true)
    protected String msInbound;
    @XmlElement(name = "MSOutbound", required = true)
    protected String msOutbound;

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
     * Gets the value of the denominazioneEnte property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDenominazioneEnte() {
        return denominazioneEnte;
    }

    /**
     * Sets the value of the denominazioneEnte property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDenominazioneEnte(String value) {
        this.denominazioneEnte = value;
    }

    /**
     * Gets the value of the descrizioneCodiceVersamento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizioneCodiceVersamento() {
        return descrizioneCodiceVersamento;
    }

    /**
     * Sets the value of the descrizioneCodiceVersamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizioneCodiceVersamento(String value) {
        this.descrizioneCodiceVersamento = value;
    }

    /**
     * Gets the value of the idApplicativo property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIdApplicativo() {
        return idApplicativo;
    }

    /**
     * Sets the value of the idApplicativo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIdApplicativo(Integer value) {
        this.idApplicativo = value;
    }

    /**
     * Gets the value of the descrizioneApplicativo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizioneApplicativo() {
        return descrizioneApplicativo;
    }

    /**
     * Sets the value of the descrizioneApplicativo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizioneApplicativo(String value) {
        this.descrizioneApplicativo = value;
    }

    /**
     * Gets the value of the endpoint property.
     * 
     * @return
     *     possible object is
     *     {@link EndpointType }
     *     
     */
    public EndpointType getEndpoint() {
        return endpoint;
    }

    /**
     * Sets the value of the endpoint property.
     * 
     * @param value
     *     allowed object is
     *     {@link EndpointType }
     *     
     */
    public void setEndpoint(EndpointType value) {
        this.endpoint = value;
    }

    /**
     * Gets the value of the utenteApplicativo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUtenteApplicativo() {
        return utenteApplicativo;
    }

    /**
     * Sets the value of the utenteApplicativo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUtenteApplicativo(String value) {
        this.utenteApplicativo = value;
    }

    /**
     * Gets the value of the msInbound property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMSInbound() {
        return msInbound;
    }

    /**
     * Sets the value of the msInbound property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMSInbound(String value) {
        this.msInbound = value;
    }

    /**
     * Gets the value of the msOutbound property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMSOutbound() {
        return msOutbound;
    }

    /**
     * Sets the value of the msOutbound property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMSOutbound(String value) {
        this.msOutbound = value;
    }

}
