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
 * <p>Java class for DeterminaProssimoEndpointRequestType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DeterminaProssimoEndpointRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MessageUUID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CodiciVersamento" type="{http://www.csi.it/epay/epaywso/businesstypes}CodiceVersamentoTypeList"/>
 *         &lt;element name="TolleranzaSecondi" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DeterminaProssimoEndpointRequestType", propOrder = {
    "messageUUID",
    "codiciVersamento",
    "tolleranzaSecondi"
})
public class DeterminaProssimoEndpointRequestType {

    @XmlElement(name = "MessageUUID", required = true)
    protected String messageUUID;
    @XmlElement(name = "CodiciVersamento", required = true)
    protected CodiceVersamentoTypeList codiciVersamento;
    @XmlElement(name = "TolleranzaSecondi")
    protected int tolleranzaSecondi;

    /**
     * Gets the value of the messageUUID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessageUUID() {
        return messageUUID;
    }

    /**
     * Sets the value of the messageUUID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessageUUID(String value) {
        this.messageUUID = value;
    }

    /**
     * Gets the value of the codiciVersamento property.
     * 
     * @return
     *     possible object is
     *     {@link CodiceVersamentoTypeList }
     *     
     */
    public CodiceVersamentoTypeList getCodiciVersamento() {
        return codiciVersamento;
    }

    /**
     * Sets the value of the codiciVersamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link CodiceVersamentoTypeList }
     *     
     */
    public void setCodiciVersamento(CodiceVersamentoTypeList value) {
        this.codiciVersamento = value;
    }

    /**
     * Gets the value of the tolleranzaSecondi property.
     * 
     */
    public int getTolleranzaSecondi() {
        return tolleranzaSecondi;
    }

    /**
     * Sets the value of the tolleranzaSecondi property.
     * 
     */
    public void setTolleranzaSecondi(int value) {
        this.tolleranzaSecondi = value;
    }

}
