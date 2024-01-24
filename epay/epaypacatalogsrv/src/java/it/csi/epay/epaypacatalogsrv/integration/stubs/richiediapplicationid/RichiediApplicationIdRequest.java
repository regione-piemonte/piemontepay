/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.integration.stubs.richiediapplicationid;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CodiceFiscaleEnte" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IbanEnte" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IbanCodiceVersamento" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IbanAppoggio" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IdOperazione" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "codiceFiscaleEnte",
    "ibanEnte",
    "ibanCodiceVersamento",
    "ibanAppoggio",
    "idOperazione"
})
@XmlRootElement(name = "RichiediApplicationIdRequest", namespace = "http://www.csi.it/epay/epaywso/richiediapplicationid")
public class RichiediApplicationIdRequest {

    @XmlElement(name = "CodiceFiscaleEnte", namespace = "http://www.csi.it/epay/epaywso/richiediapplicationid", required = true)
    protected String codiceFiscaleEnte;
    @XmlElement(name = "IbanEnte", namespace = "http://www.csi.it/epay/epaywso/richiediapplicationid", required = true)
    protected String ibanEnte;
    @XmlElement(name = "IbanCodiceVersamento", namespace = "http://www.csi.it/epay/epaywso/richiediapplicationid", required = true)
    protected String ibanCodiceVersamento;
    @XmlElement(name = "IbanAppoggio", namespace = "http://www.csi.it/epay/epaywso/richiediapplicationid", required = true)
    protected String ibanAppoggio;
    @XmlElement(name = "IdOperazione", namespace = "http://www.csi.it/epay/epaywso/richiediapplicationid", required = true)
    protected String idOperazione;

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
     * Gets the value of the ibanEnte property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIbanEnte() {
        return ibanEnte;
    }

    /**
     * Sets the value of the ibanEnte property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIbanEnte(String value) {
        this.ibanEnte = value;
    }

    /**
     * Gets the value of the ibanCodiceVersamento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIbanCodiceVersamento() {
        return ibanCodiceVersamento;
    }

    /**
     * Sets the value of the ibanCodiceVersamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIbanCodiceVersamento(String value) {
        this.ibanCodiceVersamento = value;
    }

    /**
     * Gets the value of the ibanAppoggio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIbanAppoggio() {
        return ibanAppoggio;
    }

    /**
     * Sets the value of the ibanAppoggio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIbanAppoggio(String value) {
        this.ibanAppoggio = value;
    }

    /**
     * Gets the value of the idOperazione property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdOperazione() {
        return idOperazione;
    }

    /**
     * Sets the value of the idOperazione property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdOperazione(String value) {
        this.idOperazione = value;
    }

}
