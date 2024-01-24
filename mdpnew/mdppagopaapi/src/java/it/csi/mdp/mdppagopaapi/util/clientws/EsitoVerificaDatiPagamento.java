/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdppagopaapi.util.clientws;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for esitoVerificaDatiPagamento complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="esitoVerificaDatiPagamento">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="esito" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codiceErrore" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="messaggioErrore" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="importoDovuto" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="ibanAccredito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bicAccredito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="credenzialiPagatore" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="causaleVersamento" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="timestamp" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="mac" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="dataFineValiditaPagamento" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="descrizionePagamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "esitoVerificaDatiPagamento", propOrder = {
    "esito",
    "codiceErrore",
    "messaggioErrore",
    "importoDovuto",
    "ibanAccredito",
    "bicAccredito",
    "credenzialiPagatore",
    "causaleVersamento",
    "timestamp",
    "mac",
    "dataFineValiditaPagamento0020",
    "descrizionePagamento"
})
public class EsitoVerificaDatiPagamento {

    @XmlElement(required = true)
    protected String esito;
    protected String codiceErrore;
    protected String messaggioErrore;
    @XmlElement(required = true)
    protected BigDecimal importoDovuto;
    protected String ibanAccredito;
    protected String bicAccredito;
    protected String credenzialiPagatore;
    @XmlElement(required = true)
    protected String causaleVersamento;
    @XmlElement(required = true)
    protected String timestamp;
    @XmlElement(required = true)
    protected String mac;
    @XmlElement(name = "dataFineValiditaPagamento")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataFineValiditaPagamento0020;
    protected String descrizionePagamento;

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
     * Gets the value of the codiceErrore property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceErrore() {
        return codiceErrore;
    }

    /**
     * Sets the value of the codiceErrore property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceErrore(String value) {
        this.codiceErrore = value;
    }

    /**
     * Gets the value of the messaggioErrore property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessaggioErrore() {
        return messaggioErrore;
    }

    /**
     * Sets the value of the messaggioErrore property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessaggioErrore(String value) {
        this.messaggioErrore = value;
    }

    /**
     * Gets the value of the importoDovuto property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getImportoDovuto() {
        return importoDovuto;
    }

    /**
     * Sets the value of the importoDovuto property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setImportoDovuto(BigDecimal value) {
        this.importoDovuto = value;
    }

    /**
     * Gets the value of the ibanAccredito property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIbanAccredito() {
        return ibanAccredito;
    }

    /**
     * Sets the value of the ibanAccredito property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIbanAccredito(String value) {
        this.ibanAccredito = value;
    }

    /**
     * Gets the value of the bicAccredito property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBicAccredito() {
        return bicAccredito;
    }

    /**
     * Sets the value of the bicAccredito property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBicAccredito(String value) {
        this.bicAccredito = value;
    }

    /**
     * Gets the value of the credenzialiPagatore property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCredenzialiPagatore() {
        return credenzialiPagatore;
    }

    /**
     * Sets the value of the credenzialiPagatore property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCredenzialiPagatore(String value) {
        this.credenzialiPagatore = value;
    }

    /**
     * Gets the value of the causaleVersamento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCausaleVersamento() {
        return causaleVersamento;
    }

    /**
     * Sets the value of the causaleVersamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCausaleVersamento(String value) {
        this.causaleVersamento = value;
    }

    /**
     * Gets the value of the timestamp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the value of the timestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimestamp(String value) {
        this.timestamp = value;
    }

    /**
     * Gets the value of the mac property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMac() {
        return mac;
    }

    /**
     * Sets the value of the mac property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMac(String value) {
        this.mac = value;
    }

    /**
     * Gets the value of the dataFineValiditaPagamento0020 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataFineValiditaPagamento_0020() {
        return dataFineValiditaPagamento0020;
    }

    /**
     * Sets the value of the dataFineValiditaPagamento0020 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataFineValiditaPagamento_0020(XMLGregorianCalendar value) {
        this.dataFineValiditaPagamento0020 = value;
    }

    /**
     * Gets the value of the descrizionePagamento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizionePagamento() {
        return descrizionePagamento;
    }

    /**
     * Sets the value of the descrizionePagamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizionePagamento(String value) {
        this.descrizionePagamento = value;
    }

}
