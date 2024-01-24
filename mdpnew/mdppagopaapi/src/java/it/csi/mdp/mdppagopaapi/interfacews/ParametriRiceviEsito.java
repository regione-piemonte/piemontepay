/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.interfacews;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

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
    "idMsgRichiesta", "rtData", "timestamp", "mac", "identificativoUnivocoRiscossione", "importoPagato", "elencoParametriAggiuntivi", "rtPresente", "identificativoPSP", "denominazionePSP" })
public class ParametriRiceviEsito {
    @XmlElement(required = true)
    protected String applicationId;

    protected String transactionId;

    @XmlSchemaType(name = "date")
    protected Date dataOraMsgRicevuta;
    @XmlSchemaType(name = "date")
    @XmlElement(required = true)
    protected Date dataEsitoSingoloPagamento;


    @XmlElement(required = true)
    protected String iuv;
    @XmlElement(required = true)
    protected String codEsitoPagamento;

    protected String descEsitoPagamento;



    @XmlElement(required = true)
    protected String timestamp;
    @XmlElement(required = true)
    protected String mac;
    @XmlElement(required = true)
    private String identificativoUnivocoRiscossione;
    @XmlElement(required = true)
    private BigDecimal importoPagato;
    @XmlElement(required = false, name = "elencoParametriAggiuntivi")
    private Map<String, String> elencoParametriAggiuntivi;

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

    public Map<String, String> getElencoParametriAggiuntivi () {
        return elencoParametriAggiuntivi;
    }

    public void setElencoParametriAggiuntivi ( Map<String, String> elencoParametriAggiuntivi ) {
        this.elencoParametriAggiuntivi = elencoParametriAggiuntivi;
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

    public Date getDataOraMsgRicevuta () {
        return dataOraMsgRicevuta;
    }

    public void setDataOraMsgRicevuta ( Date date ) {
        this.dataOraMsgRicevuta = date;
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

    public Date getDataEsitoSingoloPagamento () {
        return dataEsitoSingoloPagamento;
    }

    public void setDataEsitoSingoloPagamento ( Date dataEsitoSingoloPagamento ) {
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
