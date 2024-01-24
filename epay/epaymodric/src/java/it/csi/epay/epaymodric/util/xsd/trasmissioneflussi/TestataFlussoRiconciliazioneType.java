/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.util.xsd.trasmissioneflussi;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java per TestataFlussoRiconciliazioneType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="TestataFlussoRiconciliazioneType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="IdMessaggio" type="{http://www.csi.it/epay/epaywso/types}String35Type"/&gt;
 *         &lt;element name="DataOraMessaggio" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="CFEnteCreditore" type="{http://www.csi.it/epay/epaywso/types}String35Type"/&gt;
 *         &lt;element name="DenominazioneEnte" type="{http://www.csi.it/epay/epaywso/types}String70Type"/&gt;
 *         &lt;element name="IdPSP" type="{http://www.csi.it/epay/epaywso/types}String35Type"/&gt;
 *         &lt;element name="DenominazionePSP" type="{http://www.csi.it/epay/epaywso/types}String70Type"/&gt;
 *         &lt;element name="IdentificativoFlusso" type="{http://www.csi.it/epay/epaywso/types}String35Type"/&gt;
 *         &lt;element name="IdentificativoUnivocoRegolamento" type="{http://www.csi.it/epay/epaywso/types}String35Type"/&gt;
 *         &lt;element name="DataRegolamento" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="NumeroTotalePagamentiFlusso" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="NumeroTotalePagamentiIntermediati" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="ImportoTotalePagamentiFlusso" type="{http://www.csi.it/epay/epaywso/types}ImportoType"/&gt;
 *         &lt;element name="ImportoTotalePagamentiIntermediati" type="{http://www.csi.it/epay/epaywso/types}ImportoType"/&gt;
 *         &lt;element name="ProvvisorioAnno" type="{http://www.csi.it/epay/epaywso/types}AnnoType"/&gt;
 *         &lt;element name="ProvvisorioNumero" type="{http://www.csi.it/epay/epaywso/types}Numero6CifreType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TestataFlussoRiconciliazioneType", namespace = "http://www.csi.it/epay/epaywso/rendicontazione", propOrder = {
    "idMessaggio",
    "dataOraMessaggio",
    "cfEnteCreditore",
    "denominazioneEnte",
    "idPSP",
    "denominazionePSP",
    "identificativoFlusso",
    "identificativoUnivocoRegolamento",
    "dataRegolamento",
    "numeroTotalePagamentiFlusso",
    "numeroTotalePagamentiIntermediati",
    "importoTotalePagamentiFlusso",
    "importoTotalePagamentiIntermediati",
    "provvisorioAnno",
    "provvisorioNumero"
})
public class TestataFlussoRiconciliazioneType {

    @XmlElement(name = "IdMessaggio", required = true)
    protected String idMessaggio;
    @XmlElement(name = "DataOraMessaggio", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataOraMessaggio;
    @XmlElement(name = "CFEnteCreditore", required = true)
    protected String cfEnteCreditore;
    @XmlElement(name = "DenominazioneEnte", required = true)
    protected String denominazioneEnte;
    @XmlElement(name = "IdPSP", required = true)
    protected String idPSP;
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
    @XmlElement(name = "ProvvisorioAnno")
    @XmlSchemaType(name = "integer")
    protected int provvisorioAnno;
    @XmlElement(name = "ProvvisorioNumero")
    @XmlSchemaType(name = "integer")
    protected int provvisorioNumero;

    /**
     * Recupera il valore della proprieta' idMessaggio.
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
     * Imposta il valore della proprieta' idMessaggio.
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
     * Recupera il valore della proprieta' dataOraMessaggio.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataOraMessaggio() {
        return dataOraMessaggio;
    }

    /**
     * Imposta il valore della proprieta' dataOraMessaggio.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataOraMessaggio(XMLGregorianCalendar value) {
        this.dataOraMessaggio = value;
    }

    /**
     * Recupera il valore della proprieta' cfEnteCreditore.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCFEnteCreditore() {
        return cfEnteCreditore;
    }

    /**
     * Imposta il valore della proprieta' cfEnteCreditore.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCFEnteCreditore(String value) {
        this.cfEnteCreditore = value;
    }

    /**
     * Recupera il valore della proprieta' denominazioneEnte.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDenominazioneEnte() {
        return denominazioneEnte;
    }

    /**
     * Imposta il valore della proprieta' denominazioneEnte.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDenominazioneEnte(String value) {
        this.denominazioneEnte = value;
    }

    /**
     * Recupera il valore della proprieta' idPSP.
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
     * Imposta il valore della proprieta' idPSP.
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
     * Recupera il valore della proprieta' denominazionePSP.
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
     * Imposta il valore della proprieta' denominazionePSP.
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
     * Recupera il valore della proprieta' identificativoFlusso.
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
     * Imposta il valore della proprieta' identificativoFlusso.
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
     * Recupera il valore della proprieta' identificativoUnivocoRegolamento.
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
     * Imposta il valore della proprieta' identificativoUnivocoRegolamento.
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
     * Recupera il valore della proprieta' dataRegolamento.
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
     * Imposta il valore della proprieta' dataRegolamento.
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
     * Recupera il valore della proprieta' numeroTotalePagamentiFlusso.
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
     * Imposta il valore della proprieta' numeroTotalePagamentiFlusso.
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
     * Recupera il valore della proprieta' numeroTotalePagamentiIntermediati.
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
     * Imposta il valore della proprieta' numeroTotalePagamentiIntermediati.
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
     * Recupera il valore della proprieta' importoTotalePagamentiFlusso.
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
     * Imposta il valore della proprieta' importoTotalePagamentiFlusso.
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
     * Recupera il valore della proprieta' importoTotalePagamentiIntermediati.
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
     * Imposta il valore della proprieta' importoTotalePagamentiIntermediati.
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
     * Recupera il valore della proprieta' provvisorioAnno.
     * 
     */
    public int getProvvisorioAnno() {
        return provvisorioAnno;
    }

    /**
     * Imposta il valore della proprieta' provvisorioAnno.
     * 
     */
    public void setProvvisorioAnno(int value) {
        this.provvisorioAnno = value;
    }

    /**
     * Recupera il valore della proprieta' provvisorioNumero.
     * 
     */
    public int getProvvisorioNumero() {
        return provvisorioNumero;
    }

    /**
     * Imposta il valore della proprieta' provvisorioNumero.
     * 
     */
    public void setProvvisorioNumero(int value) {
        this.provvisorioNumero = value;
    }

}
