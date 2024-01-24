/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.util.wsdl.flussorendicontazione;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for TestataFlussoRendicontazioneExtType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TestataFlussoRendicontazioneExtType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IdMessaggio" type="{http://www.csi.it/epay/epaywso/types}String35Type"/>
 *         &lt;element name="DataOraMessaggio" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="CFEnteCreditore" type="{http://www.csi.it/epay/epaywso/types}String35Type"/>
 *         &lt;element name="DenominazioneEnte" type="{http://www.csi.it/epay/epaywso/types}String70Type"/>
 *         &lt;element name="IdPSP" type="{http://www.csi.it/epay/epaywso/types}String35Type"/>
 *         &lt;element name="DenominazionePSP" type="{http://www.csi.it/epay/epaywso/types}String70Type"/>
 *         &lt;element name="IdentificativoFlusso" type="{http://www.csi.it/epay/epaywso/types}String35Type"/>
 *         &lt;element name="IdentificativoUnivocoRegolamento" type="{http://www.csi.it/epay/epaywso/types}String35Type"/>
 *         &lt;element name="DataRegolamento" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="NumeroTotalePagamentiFlusso" type="{http://www.w3.org/2001/XMLSchema}integer" form="qualified"/>
 *         &lt;element name="NumeroTotalePagamentiIntermediati" type="{http://www.w3.org/2001/XMLSchema}integer" form="qualified"/>
 *         &lt;element name="NumeroTotalePagamentiNonIntermediati" type="{http://www.w3.org/2001/XMLSchema}integer" form="qualified"/>
 *         &lt;element name="NumeroTotalePagamentiSconosciuti" type="{http://www.w3.org/2001/XMLSchema}integer" form="qualified"/>
 *         &lt;element name="ImportoTotalePagamentiFlusso" type="{http://www.w3.org/2001/XMLSchema}decimal" form="qualified"/>
 *         &lt;element name="ImportoTotalePagamentiIntermediati" type="{http://www.w3.org/2001/XMLSchema}decimal" form="qualified"/>
 *         &lt;element name="ImportoTotalePagamentiNonIntermediati" type="{http://www.w3.org/2001/XMLSchema}decimal" form="qualified"/>
 *         &lt;element name="ImportoTotalePagamentiSconosciuti" type="{http://www.w3.org/2001/XMLSchema}decimal" form="qualified"/>
 *         &lt;element name="ElencoCodiciVersamento">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="CodiceVersamento" type="{http://www.csi.it/epay/epaywso/types}CodiceVersamentoType" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TestataFlussoRendicontazioneExtType", namespace = "http://www.csi.it/epay/epaywso/rendicontazione-ext", propOrder = {
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
    "numeroTotalePagamentiSconosciuti",
    "numeroTotalePagamentiNonIntermediati",
    "importoTotalePagamentiFlusso",
    "importoTotalePagamentiIntermediati",
    "importoTotalePagamentiSconosciuti",
    "importoTotalePagamentiNonIntermediati",
    "elencoCodiciVersamento"
})
public class TestataFlussoRendicontazioneExtType {

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
    @XmlElement(name = "ImportoTotalePagamentiSconosciuti", required = true)
    protected BigDecimal importoTotalePagamentiSconosciuti;
    @XmlElement(name = "ImportoTotalePagamentiNonIntermediati", required = true)
    protected BigDecimal importoTotalePagamentiNonIntermediati;
    @XmlElement(name = "NumeroTotalePagamentiSconosciuti", required = true)
    protected BigInteger numeroTotalePagamentiSconosciuti;
    @XmlElement(name = "NumeroTotalePagamentiNonIntermediati", required = true)
    protected BigInteger numeroTotalePagamentiNonIntermediati;
    @XmlElement(name = "ElencoCodiciVersamento", required = true)
    protected TestataFlussoRendicontazioneExtType.ElencoCodiciVersamento elencoCodiciVersamento;

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
     * Gets the value of the dataOraMessaggio property.
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
     * Sets the value of the dataOraMessaggio property.
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
     * Gets the value of the cfEnteCreditore property.
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
     * Sets the value of the cfEnteCreditore property.
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
     * Gets the value of the denominazioneEnte property.
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
     * Sets the value of the denominazioneEnte property.
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
     * Gets the value of the denominazionePSP property.
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
     * Sets the value of the denominazionePSP property.
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
     * Gets the value of the identificativoUnivocoRegolamento property.
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
     * Sets the value of the identificativoUnivocoRegolamento property.
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
     * Gets the value of the dataRegolamento property.
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
     * Sets the value of the dataRegolamento property.
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
     * Gets the value of the numeroTotalePagamentiFlusso property.
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
     * Sets the value of the numeroTotalePagamentiFlusso property.
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
     * Gets the value of the numeroTotalePagamentiIntermediati property.
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
     * Sets the value of the numeroTotalePagamentiIntermediati property.
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
     * Gets the value of the importoTotalePagamentiFlusso property.
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
     * Sets the value of the importoTotalePagamentiFlusso property.
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
     * Gets the value of the importoTotalePagamentiIntermediati property.
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
     * Sets the value of the importoTotalePagamentiIntermediati property.
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
     * Gets the value of the elencoCodiciVersamento property.
     * 
     * @return
     *     possible object is
     *     {@link TestataFlussoRendicontazioneExtType.ElencoCodiciVersamento }
     *     
     */
    public TestataFlussoRendicontazioneExtType.ElencoCodiciVersamento getElencoCodiciVersamento() {
        return elencoCodiciVersamento;
    }

    /**
     * Sets the value of the elencoCodiciVersamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link TestataFlussoRendicontazioneExtType.ElencoCodiciVersamento }
     *     
     */
    public void setElencoCodiciVersamento(TestataFlussoRendicontazioneExtType.ElencoCodiciVersamento value) {
        this.elencoCodiciVersamento = value;
    }


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
     *         &lt;element name="CodiceVersamento" type="{http://www.csi.it/epay/epaywso/types}CodiceVersamentoType" maxOccurs="unbounded"/>
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
        "codiceVersamento"
    })
    public static class ElencoCodiciVersamento {

        @XmlElement(name = "CodiceVersamento", namespace = "http://www.csi.it/epay/epaywso/rendicontazione-ext", required = true)
        protected List<String> codiceVersamento;

        /**
         * Gets the value of the codiceVersamento property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the codiceVersamento property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getCodiceVersamento().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getCodiceVersamento() {
            if (codiceVersamento == null) {
                codiceVersamento = new ArrayList<String>();
            }
            return this.codiceVersamento;
        }

    }
    
    /**
     * @return the importoTotalePagamentiSconosciuti
     */
    public BigDecimal getImportoTotalePagamentiSconosciuti () {
        return importoTotalePagamentiSconosciuti;
    }

    
    /**
     * @param importoTotalePagamentiSconosciuti the importoTotalePagamentiSconosciuti to set
     */
    public void setImportoTotalePagamentiSconosciuti ( BigDecimal importoTotalePagamentiSconosciuti ) {
        this.importoTotalePagamentiSconosciuti = importoTotalePagamentiSconosciuti;
    }

    
    /**
     * @return the importoTotalePagamentiNonIntermediati
     */
    public BigDecimal getImportoTotalePagamentiNonIntermediati () {
        return importoTotalePagamentiNonIntermediati;
    }

    
    /**
     * @param importoTotalePagamentiNonIntermediati the importoTotalePagamentiNonIntermediati to set
     */
    public void setImportoTotalePagamentiNonIntermediati ( BigDecimal importoTotalePagamentiNonIntermediati ) {
        this.importoTotalePagamentiNonIntermediati = importoTotalePagamentiNonIntermediati;
    }

    
    /**
     * @return the numeroTotalePagamentiSconosciuti
     */
    public BigInteger getNumeroTotalePagamentiSconosciuti () {
        return numeroTotalePagamentiSconosciuti;
    }

    
    /**
     * @param numeroTotalePagamentiSconosciuti the numeroTotalePagamentiSconosciuti to set
     */
    public void setNumeroTotalePagamentiSconosciuti ( BigInteger numeroTotalePagamentiSconosciuti ) {
        this.numeroTotalePagamentiSconosciuti = numeroTotalePagamentiSconosciuti;
    }

    
    /**
     * @return the numeroTotalePagamentiNonIntermediati
     */
    public BigInteger getNumeroTotalePagamentiNonIntermediati () {
        return numeroTotalePagamentiNonIntermediati;
    }

    
    /**
     * @param numeroTotalePagamentiNonIntermediati the numeroTotalePagamentiNonIntermediati to set
     */
    public void setNumeroTotalePagamentiNonIntermediati ( BigInteger numeroTotalePagamentiNonIntermediati ) {
        this.numeroTotalePagamentiNonIntermediati = numeroTotalePagamentiNonIntermediati;
    }

}
