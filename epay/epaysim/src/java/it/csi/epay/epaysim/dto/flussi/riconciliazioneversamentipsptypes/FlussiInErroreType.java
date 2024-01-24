/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

//
// Questo file  stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.3.0 
// Vedere <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Qualsiasi modifica a questo file andr persa durante la ricompilazione dello schema di origine. 
// Generato il: 2018.10.25 alle 05:45:36 PM CEST 
//


package it.csi.epay.epaysim.dto.flussi.riconciliazioneversamentipsptypes;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java per FlussiInErroreType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="FlussiInErroreType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="IdPsp" type="{http://www.csi.it/epay/epaywso/types}String70Type"/&gt;
 *         &lt;element name="DenominazionePSP" type="{http://www.csi.it/epay/epaywso/types}String70Type"/&gt;
 *         &lt;element name="IdentificativoFlusso" type="{http://www.csi.it/epay/epaywso/types}String35Type"/&gt;
 *         &lt;element name="IdentificativoUnivocoRegolamento" type="{http://www.csi.it/epay/epaywso/types}String35Type"/&gt;
 *         &lt;element name="DataRegolamento" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="NumeroTotalePagamentiFlusso" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="NumeroTotalePagamentiIntermediati" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="ImportoTotalePagamentiFlusso" type="{http://www.csi.it/epay/epaywso/types}ImportoType"/&gt;
 *         &lt;element name="ImportoTotalePagamentiIntermediati" type="{http://www.csi.it/epay/epaywso/types}ImportoType"/&gt;
 *         &lt;element name="CodiceVersamento" type="{http://www.csi.it/epay/epaywso/types}CodiceVersamentoType" minOccurs="0"/&gt;
 *         &lt;element name="CodiceErrore" type="{http://www.csi.it/epay/epaywso/types}String30Type"/&gt;
 *         &lt;element name="DescrizioneErrore" type="{http://www.csi.it/epay/epaywso/types}String500Type"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FlussiInErroreType", namespace = "http://www.csi.it/epay/epaywso/rendicontazione", propOrder = {
    "idPsp",
    "denominazionePSP",
    "identificativoFlusso",
    "identificativoUnivocoRegolamento",
    "dataRegolamento",
    "numeroTotalePagamentiFlusso",
    "numeroTotalePagamentiIntermediati",
    "importoTotalePagamentiFlusso",
    "importoTotalePagamentiIntermediati",
    "codiceVersamento",
    "codiceErrore",
    "descrizioneErrore"
})
public class FlussiInErroreType {

    @XmlElement(name = "IdPsp", required = true)
    protected String idPsp;
    @XmlElement(name = "DenominazionePSP", required = true)
    protected String denominazionePSP;
    @XmlElement(name = "IdentificativoFlusso", required = true)
    protected String identificativoFlusso;
    @XmlElement(name = "IdentificativoUnivocoRegolamento", required = true)
    protected String identificativoUnivocoRegolamento;
    @XmlElement(name = "DataRegolamento", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataRegolamento;
    @XmlElement(name = "NumeroTotalePagamentiFlusso", required = true)
    protected BigInteger numeroTotalePagamentiFlusso;
    @XmlElement(name = "NumeroTotalePagamentiIntermediati", required = true)
    protected BigInteger numeroTotalePagamentiIntermediati;
    @XmlElement(name = "ImportoTotalePagamentiFlusso", required = true)
    protected BigDecimal importoTotalePagamentiFlusso;
    @XmlElement(name = "ImportoTotalePagamentiIntermediati", required = true)
    protected BigDecimal importoTotalePagamentiIntermediati;
    @XmlElement(name = "CodiceVersamento")
    protected String codiceVersamento;
    @XmlElement(name = "CodiceErrore", required = true)
    protected String codiceErrore;
    @XmlElement(name = "DescrizioneErrore", required = true)
    protected String descrizioneErrore;

    /**
     * Recupera il valore della propriet idPsp.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdPsp() {
        return idPsp;
    }

    /**
     * Imposta il valore della propriet idPsp.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdPsp(String value) {
        this.idPsp = value;
    }

    /**
     * Recupera il valore della propriet denominazionePSP.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDenominazionePSP() {
        return denominazionePSP;
    }

    /**
     * Imposta il valore della propriet denominazionePSP.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDenominazionePSP(String value) {
        this.denominazionePSP = value;
    }

    /**
     * Recupera il valore della propriet identificativoFlusso.
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
     * Imposta il valore della propriet identificativoFlusso.
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
     * Recupera il valore della propriet identificativoUnivocoRegolamento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentificativoUnivocoRegolamento() {
        return identificativoUnivocoRegolamento;
    }

    /**
     * Imposta il valore della propriet identificativoUnivocoRegolamento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentificativoUnivocoRegolamento(String value) {
        this.identificativoUnivocoRegolamento = value;
    }

    /**
     * Recupera il valore della propriet dataRegolamento.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataRegolamento() {
        return dataRegolamento;
    }

    /**
     * Imposta il valore della propriet dataRegolamento.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataRegolamento(XMLGregorianCalendar value) {
        this.dataRegolamento = value;
    }

    /**
     * Recupera il valore della propriet numeroTotalePagamentiFlusso.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNumeroTotalePagamentiFlusso() {
        return numeroTotalePagamentiFlusso;
    }

    /**
     * Imposta il valore della propriet numeroTotalePagamentiFlusso.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNumeroTotalePagamentiFlusso(BigInteger value) {
        this.numeroTotalePagamentiFlusso = value;
    }

    /**
     * Recupera il valore della propriet numeroTotalePagamentiIntermediati.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNumeroTotalePagamentiIntermediati() {
        return numeroTotalePagamentiIntermediati;
    }

    /**
     * Imposta il valore della propriet numeroTotalePagamentiIntermediati.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNumeroTotalePagamentiIntermediati(BigInteger value) {
        this.numeroTotalePagamentiIntermediati = value;
    }

    /**
     * Recupera il valore della propriet importoTotalePagamentiFlusso.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getImportoTotalePagamentiFlusso() {
        return importoTotalePagamentiFlusso;
    }

    /**
     * Imposta il valore della propriet importoTotalePagamentiFlusso.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setImportoTotalePagamentiFlusso(BigDecimal value) {
        this.importoTotalePagamentiFlusso = value;
    }

    /**
     * Recupera il valore della propriet importoTotalePagamentiIntermediati.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getImportoTotalePagamentiIntermediati() {
        return importoTotalePagamentiIntermediati;
    }

    /**
     * Imposta il valore della propriet importoTotalePagamentiIntermediati.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setImportoTotalePagamentiIntermediati(BigDecimal value) {
        this.importoTotalePagamentiIntermediati = value;
    }

    /**
     * Recupera il valore della propriet codiceVersamento.
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
     * Imposta il valore della propriet codiceVersamento.
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
     * Recupera il valore della propriet codiceErrore.
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
     * Imposta il valore della propriet codiceErrore.
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
     * Recupera il valore della propriet descrizioneErrore.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizioneErrore() {
        return descrizioneErrore;
    }

    /**
     * Imposta il valore della propriet descrizioneErrore.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizioneErrore(String value) {
        this.descrizioneErrore = value;
    }

}
