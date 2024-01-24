/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java per flussoOriginePagopaDTO complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="flussoOriginePagopaDTO"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://epaysim.interfacews.epaysim.epay.csi.it/}parentInput"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="dataInserimento" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="dataRegolamento" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="dataoraFlusso" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="ibanRiversamentoFlusso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="idElaborazione" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="idMessaggio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="identificativoFlusso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="identificativoPsp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="identificativoUnivocoRegolamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="importoTotalePagamenti" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="importoTotalePagamentiIntermediati" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="numeroTotalePagamenti" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="numeroTotalePagamentiIntermediati" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="codiceFiscaleEnte" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="statoElaborazioneFlusso" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "flussoOriginePagopaDTO", propOrder = {
    "id",
    "dataInserimento",
    "dataRegolamento",
    "dataoraFlusso",
    "ibanRiversamentoFlusso",
    "idElaborazione",
    "idMessaggio",
    "identificativoFlusso",
    "identificativoPsp",
    "identificativoUnivocoRegolamento",
    "importoTotalePagamenti",
    "importoTotalePagamentiIntermediati",
    "numeroTotalePagamenti",
    "numeroTotalePagamentiIntermediati",
    "codiceFiscaleEnte",
    "statoElaborazioneFlusso"
})
public class FlussoOriginePagopaDTO
    extends ParentInput
{

    protected Long id;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataInserimento;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataRegolamento;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataoraFlusso;
    protected String ibanRiversamentoFlusso;
    protected Long idElaborazione;
    protected String idMessaggio;
    protected String identificativoFlusso;
    protected String identificativoPsp;
    protected String identificativoUnivocoRegolamento;
    protected BigDecimal importoTotalePagamenti;
    protected BigDecimal importoTotalePagamentiIntermediati;
    protected Integer numeroTotalePagamenti;
    protected Integer numeroTotalePagamentiIntermediati;
    protected String codiceFiscaleEnte;
    protected Boolean statoElaborazioneFlusso;

    /**
     * Recupera il valore della propriet id.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getId() {
        return id;
    }

    /**
     * Imposta il valore della propriet id.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setId(Long value) {
        this.id = value;
    }

    /**
     * Recupera il valore della propriet dataInserimento.
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
     * Imposta il valore della propriet dataInserimento.
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
     * Recupera il valore della propriet dataoraFlusso.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataoraFlusso() {
        return dataoraFlusso;
    }

    /**
     * Imposta il valore della propriet dataoraFlusso.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataoraFlusso(XMLGregorianCalendar value) {
        this.dataoraFlusso = value;
    }

    /**
     * Recupera il valore della propriet ibanRiversamentoFlusso.
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
     * Imposta il valore della propriet ibanRiversamentoFlusso.
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
     * Recupera il valore della propriet idElaborazione.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdElaborazione() {
        return idElaborazione;
    }

    /**
     * Imposta il valore della propriet idElaborazione.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdElaborazione(Long value) {
        this.idElaborazione = value;
    }

    /**
     * Recupera il valore della propriet idMessaggio.
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
     * Imposta il valore della propriet idMessaggio.
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
     * Recupera il valore della propriet identificativoPsp.
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
     * Imposta il valore della propriet identificativoPsp.
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
     * Recupera il valore della propriet importoTotalePagamenti.
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
     * Imposta il valore della propriet importoTotalePagamenti.
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
     * Recupera il valore della propriet numeroTotalePagamenti.
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
     * Imposta il valore della propriet numeroTotalePagamenti.
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
     * Recupera il valore della propriet numeroTotalePagamentiIntermediati.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumeroTotalePagamentiIntermediati() {
        return numeroTotalePagamentiIntermediati;
    }

    /**
     * Imposta il valore della propriet numeroTotalePagamentiIntermediati.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumeroTotalePagamentiIntermediati(Integer value) {
        this.numeroTotalePagamentiIntermediati = value;
    }

    /**
     * Recupera il valore della propriet codiceFiscaleEnte.
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
     * Imposta il valore della propriet codiceFiscaleEnte.
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
     * Recupera il valore della propriet statoElaborazioneFlusso.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isStatoElaborazioneFlusso() {
        return statoElaborazioneFlusso;
    }

    /**
     * Imposta il valore della propriet statoElaborazioneFlusso.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setStatoElaborazioneFlusso(Boolean value) {
        this.statoElaborazioneFlusso = value;
    }

}
