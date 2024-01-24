/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymdpservices.dto.epaymdpservices;

import java.math.BigDecimal;
import java.util.List;

import javax.activation.DataHandler;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlMimeType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for parametriRiceviRT complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="parametriRiceviRT">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="applicationId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="transactionId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="dataOraMsgRicevuta" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="idMsgRicevuta" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tipoFirma" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="iuv" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codEsitoPagamento" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="descEsitoPagamento" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="idMsgRichiesta" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="rtData" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *         &lt;element name="timestamp" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="mac" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "parametriRiceviEsito", propOrder = { "applicationId", "transactionId", "dataOraMsgRicevuta","dataEsitoSingoloPagamento", "idMsgRicevuta", "tipoFirma", "iuv", "codEsitoPagamento", "descEsitoPagamento",
    "idMsgRichiesta", "rtData", "timestamp", "mac", "identificativoUnivocoRiscossione", "importoPagato", "datiAggiuntivi", "rtPresente",
    "identificativoPSP", "denominazionePSP", "codiceContestoPagamento", "identificativoDominio" } )
public class ParametriRiceviEsito {
    @XmlElement(required = true)
    protected String applicationId;

    protected String transactionId;

    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataOraMsgRicevuta;
    @XmlSchemaType(name = "date")
    @XmlElement(required = true)
    protected XMLGregorianCalendar dataEsitoSingoloPagamento;
    @XmlElement(required = true)
    protected String idMsgRicevuta;

    protected String tipoFirma;
    @XmlElement(required = true)
    protected String iuv;
    @XmlElement(required = true)
    protected String codEsitoPagamento;

    protected String descEsitoPagamento;

    protected String idMsgRichiesta;

    @XmlMimeType("application/octet-stream")
    protected DataHandler rtData;
    @XmlElement(required = true)
    protected String timestamp;
    @XmlElement(required = true)
    protected String mac;
    @XmlElement(required = true)
    private String identificativoUnivocoRiscossione;
    @XmlElement(required = true)
    private BigDecimal importoPagato;
    @XmlElement(required = false, name = "elencoParametriAggiuntivi")
    private List<ChiaveValore> datiAggiuntivi;
    @XmlElement(required = true)
    private Boolean rtPresente;
    @XmlElement(required = true)
    private String identificativoPSP;
    @XmlElement(required = true)
    private String denominazionePSP;

    @XmlElement ( required = true )
    private String codiceContestoPagamento;

    @XmlElement ( required = true )
    private String identificativoDominio;
    //aggiungere il campo per discriminare modalita' di ricezione

    public String getIdentificativoUnivocoRiscossione() {
        return identificativoUnivocoRiscossione;
    }

    public void setIdentificativoUnivocoRiscossione(String identificativoUnivocoRiscossione) {
        this.identificativoUnivocoRiscossione = identificativoUnivocoRiscossione;
    }

    public BigDecimal getImportoPagato() {
        return importoPagato;
    }

    public void setImportoPagato(BigDecimal importoPagato) {
        this.importoPagato = importoPagato;
    }

    public List<ChiaveValore> getDatiAggiuntivi () {
        return datiAggiuntivi;
    }

    public void setDatiAggiuntivi ( List<ChiaveValore> datiAggiuntivi ) {
        this.datiAggiuntivi = datiAggiuntivi;
    }

    public Boolean getRtPresente() {
        return rtPresente;
    }

    public void setRtPresente(Boolean rtPresente) {
        this.rtPresente = rtPresente;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public XMLGregorianCalendar getDataOraMsgRicevuta() {
        return dataOraMsgRicevuta;
    }

    public void setDataOraMsgRicevuta(XMLGregorianCalendar dataOraMsgRicevuta) {
        this.dataOraMsgRicevuta = dataOraMsgRicevuta;
    }

    public String getIdMsgRicevuta() {
        return idMsgRicevuta;
    }

    public void setIdMsgRicevuta(String idMsgRicevuta) {
        this.idMsgRicevuta = idMsgRicevuta;
    }

    public String getTipoFirma() {
        return tipoFirma;
    }

    public void setTipoFirma(String tipoFirma) {
        this.tipoFirma = tipoFirma;
    }

    public String getIuv() {
        return iuv;
    }

    public void setIuv(String iuv) {
        this.iuv = iuv;
    }

    public String getCodEsitoPagamento() {
        return codEsitoPagamento;
    }

    public void setCodEsitoPagamento(String codEsitoPagamento) {
        this.codEsitoPagamento = codEsitoPagamento;
    }

    public String getDescEsitoPagamento() {
        return descEsitoPagamento;
    }

    public void setDescEsitoPagamento(String descEsitoPagamento) {
        this.descEsitoPagamento = descEsitoPagamento;
    }

    public String getIdMsgRichiesta() {
        return idMsgRichiesta;
    }

    public void setIdMsgRichiesta(String idMsgRichiesta) {
        this.idMsgRichiesta = idMsgRichiesta;
    }

    public DataHandler getRtData() {
        return rtData;
    }

    public void setRtData(DataHandler rtData) {
        this.rtData = rtData;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getIdentificativoPSP() {
        return identificativoPSP;
    }

    public void setIdentificativoPSP(String identificativoPSP) {
        this.identificativoPSP = identificativoPSP;
    }

    public String getDenominazionePSP() {
        return denominazionePSP;
    }

    public void setDenominazionePSP(String denominazionePSP) {
        this.denominazionePSP = denominazionePSP;
    }

    public XMLGregorianCalendar getDataEsitoSingoloPagamento() {
        return dataEsitoSingoloPagamento;
    }

    public void setDataEsitoSingoloPagamento(XMLGregorianCalendar dataEsitoSingoloPagamento) {
        this.dataEsitoSingoloPagamento = dataEsitoSingoloPagamento;
    }

    public String getCodiceContestoPagamento () {
        return codiceContestoPagamento;
    }

    public void setCodiceContestoPagamento ( String codiceContestoPagamento ) {
        this.codiceContestoPagamento = codiceContestoPagamento;
    }

    public String getIdentificativoDominio () {
        return identificativoDominio;
    }

    public void setIdentificativoDominio ( String identificativoDominio ) {
        this.identificativoDominio = identificativoDominio;
    }

}
