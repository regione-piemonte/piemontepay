/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.integration.stubs.epaymodricws;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java per dtoFlussoOrigine complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="dtoFlussoOrigine"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="contatoreTentativi" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="dataInserimento" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="dataOraFlusso" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="ibanRiversamentoFlusso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="identificativoFlusso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="identificativoIstitutoRicevente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="identificativoPsp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="importoTotalePagamenti" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="numeroTotalePagamenti" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="statoFlusso" type="{http://epaymodric.interfacews.epaymodric.epay.csi.it/}dtoStatoFlusso" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtoFlussoOrigine", propOrder = {
    "contatoreTentativi",
    "dataInserimento",
    "dataOraFlusso",
    "ibanRiversamentoFlusso",
    "id",
    "identificativoFlusso",
    "identificativoIstitutoRicevente",
    "identificativoPsp",
    "importoTotalePagamenti",
    "numeroTotalePagamenti",
    "statoFlusso"
})
public class DtoFlussoOrigine {

    protected Integer contatoreTentativi;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataInserimento;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataOraFlusso;
    protected String ibanRiversamentoFlusso;
    protected String id;
    protected String identificativoFlusso;
    protected String identificativoIstitutoRicevente;
    protected String identificativoPsp;
    protected BigDecimal importoTotalePagamenti;
    protected Integer numeroTotalePagamenti;
    protected DtoStatoFlusso statoFlusso;

    /**
     * Recupera il valore della proprieta contatoreTentativi.
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
     * Imposta il valore della proprieta contatoreTentativi.
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
     * Recupera il valore della proprieta dataInserimento.
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
     * Imposta il valore della proprieta dataInserimento.
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
     * Recupera il valore della proprieta dataOraFlusso.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataOraFlusso() {
        return dataOraFlusso;
    }

    /**
     * Imposta il valore della proprieta dataOraFlusso.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataOraFlusso(XMLGregorianCalendar value) {
        this.dataOraFlusso = value;
    }

    /**
     * Recupera il valore della proprieta ibanRiversamentoFlusso.
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
     * Imposta il valore della proprieta ibanRiversamentoFlusso.
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
     * Recupera il valore della proprieta id.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Imposta il valore della proprieta id.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Recupera il valore della proprieta identificativoFlusso.
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
     * Imposta il valore della proprieta identificativoFlusso.
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
     * Recupera il valore della proprieta identificativoIstitutoRicevente.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentificativoIstitutoRicevente() {
        return identificativoIstitutoRicevente;
    }

    /**
     * Imposta il valore della proprieta identificativoIstitutoRicevente.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentificativoIstitutoRicevente(String value) {
        this.identificativoIstitutoRicevente = value;
    }

    /**
     * Recupera il valore della proprieta identificativoPsp.
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
     * Imposta il valore della proprieta identificativoPsp.
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
     * Recupera il valore della proprieta importoTotalePagamenti.
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
     * Imposta il valore della proprieta importoTotalePagamenti.
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
     * Recupera il valore della proprieta numeroTotalePagamenti.
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
     * Imposta il valore della proprieta numeroTotalePagamenti.
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
     * Recupera il valore della proprieta statoFlusso.
     * 
     * @return
     *     possible object is
     *     {@link DtoStatoFlusso }
     *     
     */
    public DtoStatoFlusso getStatoFlusso() {
        return statoFlusso;
    }

    /**
     * Imposta il valore della proprieta statoFlusso.
     * 
     * @param value
     *     allowed object is
     *     {@link DtoStatoFlusso }
     *     
     */
    public void setStatoFlusso(DtoStatoFlusso value) {
        this.statoFlusso = value;
    }

}
