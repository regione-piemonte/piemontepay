/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdppagopaapi.pa.pafornode;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Contains all data for the subject of payment :
 * 
 * - `uniqueIdentifier` : (_see below to details_)
 * - `fullName` : name of the subject
 * - `streetName` : street name
 * - `civicNumber` : building number
 * - `postalCode` : postal code
 * - `city` : town name
 * - `stateProvinceRegion` : country subdivision
 * - `country` : country name
 * - `e-mail` : remittance location electronic address
 * 
 * <p>Java class for ctSubject complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ctSubject">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="uniqueIdentifier" type="{http://pagopa-api.pagopa.gov.it/pa/paForNode.xsd}ctEntityUniqueIdentifier"/>
 *         &lt;element name="fullName" type="{http://pagopa-api.pagopa.gov.it/pa/paForNode.xsd}stText70"/>
 *         &lt;element name="streetName" type="{http://pagopa-api.pagopa.gov.it/pa/paForNode.xsd}stText70" minOccurs="0"/>
 *         &lt;element name="civicNumber" type="{http://pagopa-api.pagopa.gov.it/pa/paForNode.xsd}stText16" minOccurs="0"/>
 *         &lt;element name="postalCode" type="{http://pagopa-api.pagopa.gov.it/pa/paForNode.xsd}stText16" minOccurs="0"/>
 *         &lt;element name="city" type="{http://pagopa-api.pagopa.gov.it/pa/paForNode.xsd}stText35" minOccurs="0"/>
 *         &lt;element name="stateProvinceRegion" type="{http://pagopa-api.pagopa.gov.it/pa/paForNode.xsd}stText35" minOccurs="0"/>
 *         &lt;element name="country" type="{http://pagopa-api.pagopa.gov.it/pa/paForNode.xsd}stNazioneProvincia" minOccurs="0"/>
 *         &lt;element name="e-mail" type="{http://pagopa-api.pagopa.gov.it/pa/paForNode.xsd}stEMail" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ctSubject", propOrder = {
    "uniqueIdentifier",
    "fullName",
    "streetName",
    "civicNumber",
    "postalCode",
    "city",
    "stateProvinceRegion",
    "country",
    "eMail"
})
public class CtSubject {

    @XmlElement(required = true)
    protected CtEntityUniqueIdentifier uniqueIdentifier;
    @XmlElement(required = true)
    protected String fullName;
    protected String streetName;
    protected String civicNumber;
    protected String postalCode;
    protected String city;
    protected String stateProvinceRegion;
    protected String country;
    @XmlElement(name = "e-mail")
    protected String eMail;

    /**
     * Gets the value of the uniqueIdentifier property.
     * 
     * @return
     *     possible object is
     *     {@link CtEntityUniqueIdentifier }
     *     
     */
    public CtEntityUniqueIdentifier getUniqueIdentifier() {
        return uniqueIdentifier;
    }

    /**
     * Sets the value of the uniqueIdentifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link CtEntityUniqueIdentifier }
     *     
     */
    public void setUniqueIdentifier(CtEntityUniqueIdentifier value) {
        this.uniqueIdentifier = value;
    }

    /**
     * Gets the value of the fullName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Sets the value of the fullName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFullName(String value) {
        this.fullName = value;
    }

    /**
     * Gets the value of the streetName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStreetName() {
        return streetName;
    }

    /**
     * Sets the value of the streetName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStreetName(String value) {
        this.streetName = value;
    }

    /**
     * Gets the value of the civicNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCivicNumber() {
        return civicNumber;
    }

    /**
     * Sets the value of the civicNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCivicNumber(String value) {
        this.civicNumber = value;
    }

    /**
     * Gets the value of the postalCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Sets the value of the postalCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPostalCode(String value) {
        this.postalCode = value;
    }

    /**
     * Gets the value of the city property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the value of the city property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCity(String value) {
        this.city = value;
    }

    /**
     * Gets the value of the stateProvinceRegion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStateProvinceRegion() {
        return stateProvinceRegion;
    }

    /**
     * Sets the value of the stateProvinceRegion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStateProvinceRegion(String value) {
        this.stateProvinceRegion = value;
    }

    /**
     * Gets the value of the country property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the value of the country property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountry(String value) {
        this.country = value;
    }

    /**
     * Gets the value of the eMail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEMail() {
        return eMail;
    }

    /**
     * Sets the value of the eMail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEMail(String value) {
        this.eMail = value;
    }

}
