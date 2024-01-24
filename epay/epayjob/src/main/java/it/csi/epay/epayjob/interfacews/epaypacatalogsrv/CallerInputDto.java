/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayjob.interfacews.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for callerInputDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="callerInputDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codiceApplicativo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ip" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="principal" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}principalInputDto" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "callerInputDto", propOrder = {
    "codiceApplicativo",
    "ip",
    "principal"
})
public class CallerInputDto {

    protected String codiceApplicativo;
    protected String ip;
    protected PrincipalInputDto principal;

    /**
     * Gets the value of the codiceApplicativo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceApplicativo() {
        return codiceApplicativo;
    }

    /**
     * Sets the value of the codiceApplicativo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceApplicativo(String value) {
        this.codiceApplicativo = value;
    }

    /**
     * Gets the value of the ip property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIp() {
        return ip;
    }

    /**
     * Sets the value of the ip property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIp(String value) {
        this.ip = value;
    }

    /**
     * Gets the value of the principal property.
     * 
     * @return
     *     possible object is
     *     {@link PrincipalInputDto }
     *     
     */
    public PrincipalInputDto getPrincipal() {
        return principal;
    }

    /**
     * Sets the value of the principal property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrincipalInputDto }
     *     
     */
    public void setPrincipal(PrincipalInputDto value) {
        this.principal = value;
    }

}
