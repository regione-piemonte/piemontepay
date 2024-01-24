/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java per flussoOriginePagopaOutputDTO complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="flussoOriginePagopaOutputDTO"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://epaysim.interfacews.epaysim.epay.csi.it/}parentOutput"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="cfEnteCreditore" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="dataInserimento" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="dataModifica" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="dataOraMessaggio" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="dataRegolamento" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="denominazioneEnte" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="denominazionePsp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="idMessaggio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="identificativoFlusso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="identificativoPsp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="identificativoUnivocoRegolamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="importoTotalePagamenti" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="importoTotalePagamentiIntermediati" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="numeroTotalePagamenti" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="numeroTotalePagamentiIntermediati" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="provvisorioAnno" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="provvisorioNumero" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="statoElaborazioneFlusso" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="utenteInserimento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="utenteModifica" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="listFlussoSintesiPagopaOutputDTO" type="{http://epaysim.interfacews.epaysim.epay.csi.it/}flussoSintesiPagopaOutputDTO" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "flussoOriginePagopaOutputDTO", propOrder = {
    "id",
    "cfEnteCreditore",
    "dataInserimento",
    "dataModifica",
    "dataOraMessaggio",
    "dataRegolamento",
    "denominazioneEnte",
    "denominazionePsp",
    "idMessaggio",
    "identificativoFlusso",
    "identificativoPsp",
    "identificativoUnivocoRegolamento",
    "importoTotalePagamenti",
    "importoTotalePagamentiIntermediati",
    "numeroTotalePagamenti",
    "numeroTotalePagamentiIntermediati",
    "provvisorioAnno",
    "provvisorioNumero",
    "statoElaborazioneFlusso",
    "utenteInserimento",
    "utenteModifica",
    "listFlussoSintesiPagopaOutputDTO"
})
public class FlussoOriginePagopaOutputDTO
    extends ParentOutput
{

    protected Long id;
    protected String cfEnteCreditore;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataInserimento;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataModifica;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataOraMessaggio;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataRegolamento;
    protected String denominazioneEnte;
    protected String denominazionePsp;
    protected String idMessaggio;
    protected String identificativoFlusso;
    protected String identificativoPsp;
    protected String identificativoUnivocoRegolamento;
    protected BigDecimal importoTotalePagamenti;
    protected BigDecimal importoTotalePagamentiIntermediati;
    protected Integer numeroTotalePagamenti;
    protected Integer numeroTotalePagamentiIntermediati;
    protected Integer provvisorioAnno;
    protected Integer provvisorioNumero;
    protected Boolean statoElaborazioneFlusso;
    protected String utenteInserimento;
    protected String utenteModifica;
    @XmlElement(nillable = true)
    protected List<FlussoSintesiPagopaOutputDTO> listFlussoSintesiPagopaOutputDTO;

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
     * Recupera il valore della propriet cfEnteCreditore.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCfEnteCreditore() {
        return cfEnteCreditore;
    }

    /**
     * Imposta il valore della propriet cfEnteCreditore.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCfEnteCreditore(String value) {
        this.cfEnteCreditore = value;
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
     * Recupera il valore della propriet dataOraMessaggio.
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
     * Imposta il valore della propriet dataOraMessaggio.
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
     * Recupera il valore della propriet denominazioneEnte.
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
     * Imposta il valore della propriet denominazioneEnte.
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
     * Recupera il valore della propriet denominazionePsp.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDenominazionePsp() {
        return denominazionePsp;
    }

    /**
     * Imposta il valore della propriet denominazionePsp.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDenominazionePsp(String value) {
        this.denominazionePsp = value;
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
     * Recupera il valore della propriet provvisorioAnno.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getProvvisorioAnno() {
        return provvisorioAnno;
    }

    /**
     * Imposta il valore della propriet provvisorioAnno.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setProvvisorioAnno(Integer value) {
        this.provvisorioAnno = value;
    }

    /**
     * Recupera il valore della propriet provvisorioNumero.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getProvvisorioNumero() {
        return provvisorioNumero;
    }

    /**
     * Imposta il valore della propriet provvisorioNumero.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setProvvisorioNumero(Integer value) {
        this.provvisorioNumero = value;
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
     * Gets the value of the listFlussoSintesiPagopaOutputDTO property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the listFlussoSintesiPagopaOutputDTO property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getListFlussoSintesiPagopaOutputDTO().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FlussoSintesiPagopaOutputDTO }
     * 
     * 
     */
    public List<FlussoSintesiPagopaOutputDTO> getListFlussoSintesiPagopaOutputDTO() {
        if (listFlussoSintesiPagopaOutputDTO == null) {
            listFlussoSintesiPagopaOutputDTO = new ArrayList<FlussoSintesiPagopaOutputDTO>();
        }
        return this.listFlussoSintesiPagopaOutputDTO;
    }

}
