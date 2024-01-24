/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodricweb.integration.stubs.epaymodricws;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for dtoFlussoOrigine complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dtoFlussoOrigine">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="contatoreTentativi" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="dataInserimento" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataOraFlusso" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="flagFlussoIntermediato" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="ibanRiversamentoFlusso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="identificativoFlusso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="identificativoIstitutoRicevente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="identificativoIstitutoRiceventeDescrizione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="identificativoPsp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="identificativoPspDescrizione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="importoTotalePagamenti" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="numeroTotalePagamenti" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="statoFlusso" type="{http://epaymodric.interfacews.epaymodric.epay.csi.it/}dtoStatoFlusso" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtoFlussoOrigine", propOrder = {
    "contatoreTentativi",
    "dataInserimento",
    "dataOraFlusso",
    "flagFlussoIntermediato",
    "ibanRiversamentoFlusso",
    "id",
    "identificativoFlusso",
    "identificativoIstitutoRicevente",
    "identificativoIstitutoRiceventeDescrizione",
    "identificativoPsp",
    "identificativoPspDescrizione",
    "importoTotalePagamenti",
    "numeroTotalePagamenti",
    "statoFlusso"
})
public class DtoFlussoOrigine {

    protected Integer contatoreTentativi;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataInserimento;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataOraFlusso;
    protected Boolean flagFlussoIntermediato;
    protected String ibanRiversamentoFlusso;
    protected String id;
    protected String identificativoFlusso;
    protected String identificativoIstitutoRicevente;
    protected String identificativoIstitutoRiceventeDescrizione;
    protected String identificativoPsp;
    protected String identificativoPspDescrizione;
    protected BigDecimal importoTotalePagamenti;
    protected Integer numeroTotalePagamenti;
    protected DtoStatoFlusso statoFlusso;

    /**
     * Gets the value of the contatoreTentativi property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getContatoreTentativi() {
        return contatoreTentativi;
    }

    /**
     * Sets the value of the contatoreTentativi property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setContatoreTentativi(Integer value) {
        this.contatoreTentativi = value;
    }

    /**
     * Gets the value of the dataInserimento property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataInserimento() {
        return dataInserimento;
    }

    /**
     * Sets the value of the dataInserimento property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataInserimento(XMLGregorianCalendar value) {
        this.dataInserimento = value;
    }

    /**
     * Gets the value of the dataOraFlusso property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataOraFlusso() {
        return dataOraFlusso;
    }

    /**
     * Sets the value of the dataOraFlusso property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataOraFlusso(XMLGregorianCalendar value) {
        this.dataOraFlusso = value;
    }

    /**
     * Gets the value of the flagFlussoIntermediato property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean getFlagFlussoIntermediato() {
        return flagFlussoIntermediato;
    }

    /**
     * Sets the value of the flagFlussoIntermediato property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setFlagFlussoIntermediato(Boolean value) {
        this.flagFlussoIntermediato = value;
    }

    /**
     * Gets the value of the ibanRiversamentoFlusso property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIbanRiversamentoFlusso() {
        return ibanRiversamentoFlusso;
    }

    /**
     * Sets the value of the ibanRiversamentoFlusso property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIbanRiversamentoFlusso(String value) {
        this.ibanRiversamentoFlusso = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the identificativoFlusso property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentificativoFlusso() {
        return identificativoFlusso;
    }

    /**
     * Sets the value of the identificativoFlusso property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentificativoFlusso(String value) {
        this.identificativoFlusso = value;
    }

    /**
     * Gets the value of the identificativoIstitutoRicevente property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentificativoIstitutoRicevente() {
        return identificativoIstitutoRicevente;
    }

    /**
     * Sets the value of the identificativoIstitutoRicevente property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentificativoIstitutoRicevente(String value) {
        this.identificativoIstitutoRicevente = value;
    }

    /**
     * Gets the value of the identificativoIstitutoRiceventeDescrizione property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentificativoIstitutoRiceventeDescrizione() {
        return identificativoIstitutoRiceventeDescrizione;
    }

    /**
     * Sets the value of the identificativoIstitutoRiceventeDescrizione property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentificativoIstitutoRiceventeDescrizione(String value) {
        this.identificativoIstitutoRiceventeDescrizione = value;
    }

    /**
     * Gets the value of the identificativoPsp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentificativoPsp() {
        return identificativoPsp;
    }

    /**
     * Sets the value of the identificativoPsp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentificativoPsp(String value) {
        this.identificativoPsp = value;
    }

    /**
     * Gets the value of the identificativoPspDescrizione property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentificativoPspDescrizione() {
        return identificativoPspDescrizione;
    }

    /**
     * Sets the value of the identificativoPspDescrizione property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentificativoPspDescrizione(String value) {
        this.identificativoPspDescrizione = value;
    }

    /**
     * Gets the value of the importoTotalePagamenti property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getImportoTotalePagamenti() {
        return importoTotalePagamenti;
    }

    /**
     * Sets the value of the importoTotalePagamenti property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setImportoTotalePagamenti(BigDecimal value) {
        this.importoTotalePagamenti = value;
    }

    


    /**
     * Gets the value of the numeroTotalePagamenti property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumeroTotalePagamenti() {
        return numeroTotalePagamenti;
    }

    /**
     * Sets the value of the numeroTotalePagamenti property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumeroTotalePagamenti(Integer value) {
        this.numeroTotalePagamenti = value;
    }

    /**
     * Gets the value of the statoFlusso property.
     * 
     * @return
     *     possible object is
     *     {@link DtoStatoFlusso }
     *     
     */
    public DtoStatoFlusso getStatoFlusso() {
        return statoFlusso;
    }

    /**
     * Sets the value of the statoFlusso property.
     * 
     * @param value
     *     allowed object is
     *     {@link DtoStatoFlusso }
     *     
     */
    public void setStatoFlusso(DtoStatoFlusso value) {
        this.statoFlusso = value;
    }

}
