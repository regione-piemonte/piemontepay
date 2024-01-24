/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypaweb.integration.epaywsosrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for FiltroSelezioneRichiesteType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FiltroSelezioneRichiesteType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CodiceFiscaleEnte" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TipoRichiesta" type="{http://www.csi.it/epay/epaywso/types}TipoRichiestaType" minOccurs="0"/>
 *         &lt;element name="PagamentoSpontaneo" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="StatiRichiesta" type="{http://www.csi.it/epay/epaywso/businesstypes}StatoRichiestaTypeList" minOccurs="0"/>
 *         &lt;element name="CodiciVersamento" type="{http://www.csi.it/epay/epaywso/businesstypes}CodiceVersamentoTypeList" minOccurs="0"/>
 *         &lt;element name="DataInserimentoRichiestaDa" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="DataInserimentoRichiestaA" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="DataInvioAlDestinatarioDa" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="DataInvioAlDestinatarioA" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FiltroSelezioneRichiesteType", propOrder = {
    "codiceFiscaleEnte",
    "tipoRichiesta",
    "pagamentoSpontaneo",
    "statiRichiesta",
    "codiciVersamento",
    "dataInserimentoRichiestaDa",
    "dataInserimentoRichiestaA",
    "dataInvioAlDestinatarioDa",
    "dataInvioAlDestinatarioA"
})
public class FiltroSelezioneRichiesteType {

    @XmlElement(name = "CodiceFiscaleEnte")
    protected String codiceFiscaleEnte;
    @XmlElement(name = "TipoRichiesta")
    protected TipoRichiestaType tipoRichiesta;
    @XmlElement(name = "PagamentoSpontaneo")
    protected Boolean pagamentoSpontaneo;
    @XmlElement(name = "StatiRichiesta")
    protected StatoRichiestaTypeList statiRichiesta;
    @XmlElement(name = "CodiciVersamento")
    protected CodiceVersamentoTypeList codiciVersamento;
    @XmlElement(name = "DataInserimentoRichiestaDa")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataInserimentoRichiestaDa;
    @XmlElement(name = "DataInserimentoRichiestaA")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataInserimentoRichiestaA;
    @XmlElement(name = "DataInvioAlDestinatarioDa")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataInvioAlDestinatarioDa;
    @XmlElement(name = "DataInvioAlDestinatarioA")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataInvioAlDestinatarioA;

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
     * Gets the value of the tipoRichiesta property.
     * 
     * @return
     *     possible object is
     *     {@link TipoRichiestaType }
     *     
     */
    public TipoRichiestaType getTipoRichiesta() {
        return tipoRichiesta;
    }

    /**
     * Sets the value of the tipoRichiesta property.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoRichiestaType }
     *     
     */
    public void setTipoRichiesta(TipoRichiestaType value) {
        this.tipoRichiesta = value;
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
     * Gets the value of the statiRichiesta property.
     * 
     * @return
     *     possible object is
     *     {@link StatoRichiestaTypeList }
     *     
     */
    public StatoRichiestaTypeList getStatiRichiesta() {
        return statiRichiesta;
    }

    /**
     * Sets the value of the statiRichiesta property.
     * 
     * @param value
     *     allowed object is
     *     {@link StatoRichiestaTypeList }
     *     
     */
    public void setStatiRichiesta(StatoRichiestaTypeList value) {
        this.statiRichiesta = value;
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
     * Gets the value of the dataInserimentoRichiestaDa property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataInserimentoRichiestaDa() {
        return dataInserimentoRichiestaDa;
    }

    /**
     * Sets the value of the dataInserimentoRichiestaDa property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataInserimentoRichiestaDa(XMLGregorianCalendar value) {
        this.dataInserimentoRichiestaDa = value;
    }

    /**
     * Gets the value of the dataInserimentoRichiestaA property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataInserimentoRichiestaA() {
        return dataInserimentoRichiestaA;
    }

    /**
     * Sets the value of the dataInserimentoRichiestaA property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataInserimentoRichiestaA(XMLGregorianCalendar value) {
        this.dataInserimentoRichiestaA = value;
    }

    /**
     * Gets the value of the dataInvioAlDestinatarioDa property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataInvioAlDestinatarioDa() {
        return dataInvioAlDestinatarioDa;
    }

    /**
     * Sets the value of the dataInvioAlDestinatarioDa property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataInvioAlDestinatarioDa(XMLGregorianCalendar value) {
        this.dataInvioAlDestinatarioDa = value;
    }

    /**
     * Gets the value of the dataInvioAlDestinatarioA property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataInvioAlDestinatarioA() {
        return dataInvioAlDestinatarioA;
    }

    /**
     * Sets the value of the dataInvioAlDestinatarioA property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataInvioAlDestinatarioA(XMLGregorianCalendar value) {
        this.dataInvioAlDestinatarioA = value;
    }

}
