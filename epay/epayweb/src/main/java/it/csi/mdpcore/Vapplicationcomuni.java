/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdpcore;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per vapplicationcomuni complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="vapplicationcomuni">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="applicationId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codiceimm" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descComune" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="gatewayId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="merchantid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "vapplicationcomuni", propOrder = {
    "applicationId",
    "codiceimm",
    "descComune",
    "gatewayId",
    "merchantid"
})
public class Vapplicationcomuni {

    protected String applicationId;
    protected String codiceimm;
    protected String descComune;
    protected String gatewayId;
    protected String merchantid;

    /**
     * Recupera il valore della proprieta' applicationId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApplicationId() {
        return applicationId;
    }

    /**
     * Imposta il valore della proprieta' applicationId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApplicationId(String value) {
        this.applicationId = value;
    }

    /**
     * Recupera il valore della proprieta' codiceimm.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceimm() {
        return codiceimm;
    }

    /**
     * Imposta il valore della proprieta' codiceimm.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceimm(String value) {
        this.codiceimm = value;
    }

    /**
     * Recupera il valore della proprieta' descComune.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescComune() {
        return descComune;
    }

    /**
     * Imposta il valore della proprieta' descComune.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescComune(String value) {
        this.descComune = value;
    }

    /**
     * Recupera il valore della proprieta' gatewayId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGatewayId() {
        return gatewayId;
    }

    /**
     * Imposta il valore della proprieta' gatewayId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGatewayId(String value) {
        this.gatewayId = value;
    }

    /**
     * Recupera il valore della proprieta' merchantid.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMerchantid() {
        return merchantid;
    }

    /**
     * Imposta il valore della proprieta' merchantid.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMerchantid(String value) {
        this.merchantid = value;
    }

}
