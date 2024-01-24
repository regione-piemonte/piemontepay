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
 * <p>Classe Java per flussoOrigineErrorePagopaOutputDTO complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="flussoOrigineErrorePagopaOutputDTO"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="cfEnteRicevente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="codiceErrore" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="codiceVersamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="contatoreTentativi" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="dataInserimento" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="dataModifica" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="dataRegolamento" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="dataoraFlusso" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="descrizioneErrore" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="esito" type="{http://epaysim.interfacews.epaysim.epay.csi.it/}esitoDTO" minOccurs="0"/&gt;
 *         &lt;element name="ibanRiversamentoFlusso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="idElaborazione" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="idMessaggio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="idStatoFlussolusso" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="identificativoFlusso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="identificativoPsp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="identificativoUnivocoRegolamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="importoTotalePagamenti" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="importoTotalePagamentiIntermediati" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="numeroTotalePagamenti" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="numeroTotalePagamentiIntermediati" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="utenteInserimento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="utenteModifica" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="xmlFlusso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "flussoOrigineErrorePagopaOutputDTO", propOrder = {
    "cfEnteRicevente",
    "codiceErrore",
    "codiceVersamento",
    "contatoreTentativi",
    "dataInserimento",
    "dataModifica",
    "dataRegolamento",
    "dataoraFlusso",
    "descrizioneErrore",
    "esito",
    "ibanRiversamentoFlusso",
    "id",
    "idElaborazione",
    "idMessaggio",
    "idStatoFlussolusso",
    "identificativoFlusso",
    "identificativoPsp",
    "identificativoUnivocoRegolamento",
    "importoTotalePagamenti",
    "importoTotalePagamentiIntermediati",
    "numeroTotalePagamenti",
    "numeroTotalePagamentiIntermediati",
    "utenteInserimento",
    "utenteModifica",
    "xmlFlusso"
})
public class FlussoOrigineErrorePagopaOutputDTO {

    protected String cfEnteRicevente;
    protected String codiceErrore;
    protected String codiceVersamento;
    protected Integer contatoreTentativi;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataInserimento;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataModifica;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataRegolamento;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataoraFlusso;
    protected String descrizioneErrore;
    protected EsitoDTO esito;
    protected String ibanRiversamentoFlusso;
    protected Long id;
    protected Long idElaborazione;
    protected String idMessaggio;
    protected Long idStatoFlussolusso;
    protected String identificativoFlusso;
    protected String identificativoPsp;
    protected String identificativoUnivocoRegolamento;
    protected BigDecimal importoTotalePagamenti;
    protected BigDecimal importoTotalePagamentiIntermediati;
    protected Integer numeroTotalePagamenti;
    protected Integer numeroTotalePagamentiIntermediati;
    protected String utenteInserimento;
    protected String utenteModifica;
    protected String xmlFlusso;

    /**
     * Recupera il valore della propriet cfEnteRicevente.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCfEnteRicevente() {
        return cfEnteRicevente;
    }

    /**
     * Imposta il valore della propriet cfEnteRicevente.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCfEnteRicevente(String value) {
        this.cfEnteRicevente = value;
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
     * Recupera il valore della propriet contatoreTentativi.
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
     * Imposta il valore della propriet contatoreTentativi.
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
     * Recupera il valore della propriet dataModifica.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataModifica() {
        return dataModifica;
    }

    /**
     * Imposta il valore della propriet dataModifica.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataModifica(XMLGregorianCalendar value) {
        this.dataModifica = value;
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

    /**
     * Recupera il valore della propriet esito.
     * 
     * @return
     *     possible object is
     *     {@link EsitoDTO }
     *     
     */
    public EsitoDTO getEsito() {
        return esito;
    }

    /**
     * Imposta il valore della propriet esito.
     * 
     * @param value
     *     allowed object is
     *     {@link EsitoDTO }
     *     
     */
    public void setEsito(EsitoDTO value) {
        this.esito = value;
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
     * Recupera il valore della propriet idStatoFlussolusso.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdStatoFlussolusso() {
        return idStatoFlussolusso;
    }

    /**
     * Imposta il valore della propriet idStatoFlussolusso.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdStatoFlussolusso(Long value) {
        this.idStatoFlussolusso = value;
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
     * Recupera il valore della propriet utenteInserimento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUtenteInserimento() {
        return utenteInserimento;
    }

    /**
     * Imposta il valore della propriet utenteInserimento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUtenteInserimento(String value) {
        this.utenteInserimento = value;
    }

    /**
     * Recupera il valore della propriet utenteModifica.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUtenteModifica() {
        return utenteModifica;
    }

    /**
     * Imposta il valore della propriet utenteModifica.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUtenteModifica(String value) {
        this.utenteModifica = value;
    }

    /**
     * Recupera il valore della propriet xmlFlusso.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXmlFlusso() {
        return xmlFlusso;
    }

    /**
     * Imposta il valore della propriet xmlFlusso.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXmlFlusso(String value) {
        this.xmlFlusso = value;
    }

}
