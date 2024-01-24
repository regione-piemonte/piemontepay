/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epaypa.sincronizzapagamentipec;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


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
 *         &lt;element name="IdPagamento" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="IuvNumeroAvviso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IdStato" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="DescStato" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DataPagamento" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="ImportoPagato" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="PagamentoSpontaneo" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="CodVersamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DescrCodVersamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CodFiscaleEnte" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NotePagamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Nome" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Cognome" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RagioneSociale" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CodiceFiscalePagatore" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FlagPersonaFisica" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="DataScadenza" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
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
    "idPagamento",
    "iuvNumeroAvviso",
    "idStato",
    "descStato",
    "dataPagamento",
    "importoPagato",
    "pagamentoSpontaneo",
    "codVersamento",
    "descrCodVersamento",
    "codFiscaleEnte",
    "notePagamento",
    "nome",
    "cognome",
    "ragioneSociale",
    "codiceFiscalePagatore",
    "flagPersonaFisica",
    "dataScadenza"
})
@XmlRootElement(name = "SincronizzaPagamento")
public class SincronizzaPagamento {

    @XmlElement(name = "IdPagamento")
    protected Long idPagamento;
    @XmlElement(name = "IuvNumeroAvviso")
    protected String iuvNumeroAvviso;
    @XmlElement(name = "IdStato")
    protected Integer idStato;
    @XmlElement(name = "DescStato")
    protected String descStato;
    @XmlElement(name = "DataPagamento")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataPagamento;
    @XmlElement(name = "ImportoPagato")
    protected BigDecimal importoPagato;
    @XmlElement(name = "PagamentoSpontaneo")
    protected Boolean pagamentoSpontaneo;
    @XmlElement(name = "CodVersamento")
    protected String codVersamento;
    @XmlElement(name = "DescrCodVersamento")
    protected String descrCodVersamento;
    @XmlElement(name = "CodFiscaleEnte")
    protected String codFiscaleEnte;
    @XmlElement(name = "NotePagamento")
    protected String notePagamento;
    @XmlElement(name = "Nome")
    protected String nome;
    @XmlElement(name = "Cognome")
    protected String cognome;
    @XmlElement(name = "RagioneSociale")
    protected String ragioneSociale;
    @XmlElement(name = "CodiceFiscalePagatore")
    protected String codiceFiscalePagatore;
    @XmlElement(name = "FlagPersonaFisica")
    protected Boolean flagPersonaFisica;
    @XmlElement(name = "DataScadenza")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataScadenza;

    /**
     * Gets the value of the idPagamento property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdPagamento() {
        return idPagamento;
    }

    /**
     * Sets the value of the idPagamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdPagamento(Long value) {
        this.idPagamento = value;
    }

    /**
     * Gets the value of the iuvNumeroAvviso property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIuvNumeroAvviso() {
        return iuvNumeroAvviso;
    }

    /**
     * Sets the value of the iuvNumeroAvviso property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIuvNumeroAvviso(String value) {
        this.iuvNumeroAvviso = value;
    }

    /**
     * Gets the value of the idStato property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIdStato() {
        return idStato;
    }

    /**
     * Sets the value of the idStato property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIdStato(Integer value) {
        this.idStato = value;
    }

    /**
     * Gets the value of the descStato property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescStato() {
        return descStato;
    }

    /**
     * Sets the value of the descStato property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescStato(String value) {
        this.descStato = value;
    }

    /**
     * Gets the value of the dataPagamento property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataPagamento() {
        return dataPagamento;
    }

    /**
     * Sets the value of the dataPagamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataPagamento(XMLGregorianCalendar value) {
        this.dataPagamento = value;
    }

    /**
     * Gets the value of the importoPagato property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getImportoPagato() {
        return importoPagato;
    }

    /**
     * Sets the value of the importoPagato property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setImportoPagato(BigDecimal value) {
        this.importoPagato = value;
    }

    /**
     * Gets the value of the pagamentoSpontaneo property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean getPagamentoSpontaneo() {
        return pagamentoSpontaneo;
    }

    /**
     * Sets the value of the pagamentoSpontaneo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPagamentoSpontaneo(Boolean value) {
        this.pagamentoSpontaneo = value;
    }

    /**
     * Gets the value of the codVersamento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodVersamento() {
        return codVersamento;
    }

    /**
     * Sets the value of the codVersamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodVersamento(String value) {
        this.codVersamento = value;
    }

    /**
     * Gets the value of the descrCodVersamento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrCodVersamento() {
        return descrCodVersamento;
    }

    /**
     * Sets the value of the descrCodVersamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrCodVersamento(String value) {
        this.descrCodVersamento = value;
    }

    /**
     * Gets the value of the codFiscaleEnte property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodFiscaleEnte() {
        return codFiscaleEnte;
    }

    /**
     * Sets the value of the codFiscaleEnte property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodFiscaleEnte(String value) {
        this.codFiscaleEnte = value;
    }

    /**
     * Gets the value of the notePagamento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNotePagamento() {
        return notePagamento;
    }

    /**
     * Sets the value of the notePagamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNotePagamento(String value) {
        this.notePagamento = value;
    }

    /**
     * Gets the value of the nome property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNome() {
        return nome;
    }

    /**
     * Sets the value of the nome property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNome(String value) {
        this.nome = value;
    }

    /**
     * Gets the value of the cognome property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * Sets the value of the cognome property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCognome(String value) {
        this.cognome = value;
    }

    /**
     * Gets the value of the ragioneSociale property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRagioneSociale() {
        return ragioneSociale;
    }

    /**
     * Sets the value of the ragioneSociale property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRagioneSociale(String value) {
        this.ragioneSociale = value;
    }

    /**
     * Gets the value of the codiceFiscalePagatore property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceFiscalePagatore() {
        return codiceFiscalePagatore;
    }

    /**
     * Sets the value of the codiceFiscalePagatore property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceFiscalePagatore(String value) {
        this.codiceFiscalePagatore = value;
    }

    /**
     * Gets the value of the flagPersonaFisica property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean getFlagPersonaFisica() {
        return flagPersonaFisica;
    }

    /**
     * Sets the value of the flagPersonaFisica property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setFlagPersonaFisica(Boolean value) {
        this.flagPersonaFisica = value;
    }

    /**
     * Gets the value of the dataScadenza property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataScadenza() {
        return dataScadenza;
    }

    /**
     * Sets the value of the dataScadenza property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataScadenza(XMLGregorianCalendar value) {
        this.dataScadenza = value;
    }

}
