/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypaweb.integration.epaywsosrv;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for RichiestaType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RichiestaType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IdRichiesta" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="CodiceFiscaleEnte" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TipoRichiesta" type="{http://www.csi.it/epay/epaywso/types}TipoRichiestaType"/>
 *         &lt;element name="StatoRichiesta" type="{http://www.csi.it/epay/epaywso/types}StatoRichiestaType"/>
 *         &lt;element name="IdMessaggio" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CfEnteOrigine" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PagamentoSpontaneo" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="CodiceVersamento" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="MessageUUID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DataInserimentoRichiesta" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="DataUltimaVariazione" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="CodiceEsito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DettaglioEsito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EsitiInvio" type="{http://www.csi.it/epay/epaywso/businesstypes}EsitoInvioTypeList" minOccurs="0"/>
 *         &lt;element name="DataCallback" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="CodiceEsitoCallback" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DettaglioEsitoCallback" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NumeroTotaleElementi" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="ImportoTotale" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="DimensioneMessaggioMB" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="ContenutoMessaggio" type="{http://www.csi.it/epay/epaywso/businesstypes}EmbeddedXMLType"/>
 *         &lt;element name="ContenutoCallback" type="{http://www.csi.it/epay/epaywso/businesstypes}EmbeddedXMLType" minOccurs="0"/>
 *         &lt;element name="IdPSP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IdFlussoRendicontazione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RichiestaType", propOrder = {
    "idRichiesta",
    "codiceFiscaleEnte",
    "tipoRichiesta",
    "statoRichiesta",
    "idMessaggio",
    "cfEnteOrigine",
    "pagamentoSpontaneo",
    "codiceVersamento",
    "messageUUID",
    "dataInserimentoRichiesta",
    "dataUltimaVariazione",
    "codiceEsito",
    "dettaglioEsito",
    "esitiInvio",
    "dataCallback",
    "codiceEsitoCallback",
    "dettaglioEsitoCallback",
    "numeroTotaleElementi",
    "importoTotale",
    "dimensioneMessaggioMB",
    "contenutoMessaggio",
    "contenutoCallback",
    "idPSP",
    "idFlussoRendicontazione"
})
public class RichiestaType {

    @XmlElement(name = "IdRichiesta", required = true)
    protected BigInteger idRichiesta;
    @XmlElement(name = "CodiceFiscaleEnte", required = true)
    protected String codiceFiscaleEnte;
    @XmlElement(name = "TipoRichiesta", required = true)
    protected TipoRichiestaType tipoRichiesta;
    @XmlElement(name = "StatoRichiesta", required = true)
    protected StatoRichiestaType statoRichiesta;
    @XmlElement(name = "IdMessaggio", required = true)
    protected String idMessaggio;
    @XmlElement(name = "CfEnteOrigine", required = true)
    protected String cfEnteOrigine;
    @XmlElement(name = "PagamentoSpontaneo", required = true, type = Boolean.class, nillable = true)
    protected Boolean pagamentoSpontaneo;
    @XmlElement(name = "CodiceVersamento", required = true)
    protected String codiceVersamento;
    @XmlElement(name = "MessageUUID", required = true)
    protected String messageUUID;
    @XmlElement(name = "DataInserimentoRichiesta", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataInserimentoRichiesta;
    @XmlElement(name = "DataUltimaVariazione")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataUltimaVariazione;
    @XmlElement(name = "CodiceEsito")
    protected String codiceEsito;
    @XmlElement(name = "DettaglioEsito")
    protected String dettaglioEsito;
    @XmlElement(name = "EsitiInvio")
    protected EsitoInvioTypeList esitiInvio;
    @XmlElement(name = "DataCallback")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataCallback;
    @XmlElement(name = "CodiceEsitoCallback")
    protected String codiceEsitoCallback;
    @XmlElement(name = "DettaglioEsitoCallback")
    protected String dettaglioEsitoCallback;
    @XmlElement(name = "NumeroTotaleElementi")
    protected Integer numeroTotaleElementi;
    @XmlElement(name = "ImportoTotale")
    protected BigDecimal importoTotale;
    @XmlElement(name = "DimensioneMessaggioMB")
    protected BigDecimal dimensioneMessaggioMB;
    @XmlElement(name = "ContenutoMessaggio", required = true)
    protected EmbeddedXMLType contenutoMessaggio;
    @XmlElement(name = "ContenutoCallback")
    protected EmbeddedXMLType contenutoCallback;
    @XmlElement(name = "IdPSP")
    protected String idPSP;
    @XmlElement(name = "IdFlussoRendicontazione")
    protected String idFlussoRendicontazione;

    /**
     * Gets the value of the idRichiesta property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getIdRichiesta() {
        return idRichiesta;
    }

    /**
     * Sets the value of the idRichiesta property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setIdRichiesta(BigInteger value) {
        this.idRichiesta = value;
    }

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
     * Gets the value of the statoRichiesta property.
     * 
     * @return
     *     possible object is
     *     {@link StatoRichiestaType }
     *     
     */
    public StatoRichiestaType getStatoRichiesta() {
        return statoRichiesta;
    }

    /**
     * Sets the value of the statoRichiesta property.
     * 
     * @param value
     *     allowed object is
     *     {@link StatoRichiestaType }
     *     
     */
    public void setStatoRichiesta(StatoRichiestaType value) {
        this.statoRichiesta = value;
    }

    /**
     * Gets the value of the idMessaggio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdMessaggio() {
        return idMessaggio;
    }

    /**
     * Sets the value of the idMessaggio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdMessaggio(String value) {
        this.idMessaggio = value;
    }

    /**
     * Gets the value of the cfEnteOrigine property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCfEnteOrigine() {
        return cfEnteOrigine;
    }

    /**
     * Sets the value of the cfEnteOrigine property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCfEnteOrigine(String value) {
        this.cfEnteOrigine = value;
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
     * Gets the value of the codiceVersamento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceVersamento() {
        return codiceVersamento;
    }

    /**
     * Sets the value of the codiceVersamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceVersamento(String value) {
        this.codiceVersamento = value;
    }

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
     * Gets the value of the dataInserimentoRichiesta property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataInserimentoRichiesta() {
        return dataInserimentoRichiesta;
    }

    /**
     * Sets the value of the dataInserimentoRichiesta property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataInserimentoRichiesta(XMLGregorianCalendar value) {
        this.dataInserimentoRichiesta = value;
    }

    /**
     * Gets the value of the dataUltimaVariazione property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataUltimaVariazione() {
        return dataUltimaVariazione;
    }

    /**
     * Sets the value of the dataUltimaVariazione property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataUltimaVariazione(XMLGregorianCalendar value) {
        this.dataUltimaVariazione = value;
    }

    /**
     * Gets the value of the codiceEsito property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceEsito() {
        return codiceEsito;
    }

    /**
     * Sets the value of the codiceEsito property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceEsito(String value) {
        this.codiceEsito = value;
    }

    /**
     * Gets the value of the dettaglioEsito property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDettaglioEsito() {
        return dettaglioEsito;
    }

    /**
     * Sets the value of the dettaglioEsito property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDettaglioEsito(String value) {
        this.dettaglioEsito = value;
    }

    /**
     * Gets the value of the esitiInvio property.
     * 
     * @return
     *     possible object is
     *     {@link EsitoInvioTypeList }
     *     
     */
    public EsitoInvioTypeList getEsitiInvio() {
        return esitiInvio;
    }

    /**
     * Sets the value of the esitiInvio property.
     * 
     * @param value
     *     allowed object is
     *     {@link EsitoInvioTypeList }
     *     
     */
    public void setEsitiInvio(EsitoInvioTypeList value) {
        this.esitiInvio = value;
    }

    /**
     * Gets the value of the dataCallback property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataCallback() {
        return dataCallback;
    }

    /**
     * Sets the value of the dataCallback property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataCallback(XMLGregorianCalendar value) {
        this.dataCallback = value;
    }

    /**
     * Gets the value of the codiceEsitoCallback property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceEsitoCallback() {
        return codiceEsitoCallback;
    }

    /**
     * Sets the value of the codiceEsitoCallback property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceEsitoCallback(String value) {
        this.codiceEsitoCallback = value;
    }

    /**
     * Gets the value of the dettaglioEsitoCallback property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDettaglioEsitoCallback() {
        return dettaglioEsitoCallback;
    }

    /**
     * Sets the value of the dettaglioEsitoCallback property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDettaglioEsitoCallback(String value) {
        this.dettaglioEsitoCallback = value;
    }

    /**
     * Gets the value of the numeroTotaleElementi property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumeroTotaleElementi() {
        return numeroTotaleElementi;
    }

    /**
     * Sets the value of the numeroTotaleElementi property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumeroTotaleElementi(Integer value) {
        this.numeroTotaleElementi = value;
    }

    /**
     * Gets the value of the importoTotale property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getImportoTotale() {
        return importoTotale;
    }

    /**
     * Sets the value of the importoTotale property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setImportoTotale(BigDecimal value) {
        this.importoTotale = value;
    }

    /**
     * Gets the value of the dimensioneMessaggioMB property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDimensioneMessaggioMB() {
        return dimensioneMessaggioMB;
    }

    /**
     * Sets the value of the dimensioneMessaggioMB property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDimensioneMessaggioMB(BigDecimal value) {
        this.dimensioneMessaggioMB = value;
    }

    /**
     * Gets the value of the contenutoMessaggio property.
     * 
     * @return
     *     possible object is
     *     {@link EmbeddedXMLType }
     *     
     */
    public EmbeddedXMLType getContenutoMessaggio() {
        return contenutoMessaggio;
    }

    /**
     * Sets the value of the contenutoMessaggio property.
     * 
     * @param value
     *     allowed object is
     *     {@link EmbeddedXMLType }
     *     
     */
    public void setContenutoMessaggio(EmbeddedXMLType value) {
        this.contenutoMessaggio = value;
    }

    /**
     * Gets the value of the contenutoCallback property.
     * 
     * @return
     *     possible object is
     *     {@link EmbeddedXMLType }
     *     
     */
    public EmbeddedXMLType getContenutoCallback() {
        return contenutoCallback;
    }

    /**
     * Sets the value of the contenutoCallback property.
     * 
     * @param value
     *     allowed object is
     *     {@link EmbeddedXMLType }
     *     
     */
    public void setContenutoCallback(EmbeddedXMLType value) {
        this.contenutoCallback = value;
    }

    /**
     * Gets the value of the idPSP property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdPSP() {
        return idPSP;
    }

    /**
     * Sets the value of the idPSP property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdPSP(String value) {
        this.idPSP = value;
    }

    /**
     * Gets the value of the idFlussoRendicontazione property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdFlussoRendicontazione() {
        return idFlussoRendicontazione;
    }

    /**
     * Sets the value of the idFlussoRendicontazione property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdFlussoRendicontazione(String value) {
        this.idFlussoRendicontazione = value;
    }

}
