/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogweb.integration.rs;

import java.util.ArrayList;
import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;

import org.codehaus.jackson.annotate.JsonProperty;


public class GetProfilazioneUtenteCorrenteOutputDto {

    
    @JsonProperty("cdu")
    protected List<GetProfilazioneUtenteCorrenteCduOutputDto> cdu;//not null
    
    @JsonProperty("codiceFiscale")
    protected String codiceFiscale;
    
    @JsonProperty("cognome")
    protected String cognome;
    
    @JsonProperty("dataFineValidita")
    protected XMLGregorianCalendar dataFineValidita;
    
    @JsonProperty("dataFineValiditaCurrent")
    protected XMLGregorianCalendar dataFineValiditaCurrent;
    
    @JsonProperty("dataInizioValidita")
    protected XMLGregorianCalendar dataInizioValidita;
    
    @JsonProperty("dataInizioValiditaCurrent")
    protected XMLGregorianCalendar dataInizioValiditaCurrent;
    
    @JsonProperty("email")
    protected String email;
    
    @JsonProperty("id")
    protected Long id;
    
    @JsonProperty("nome")
    protected String nome;

    /**
     * Gets the value of the cdu property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cdu property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCdu().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GetProfilazioneUtenteCorrenteCduOutputDto }
     * 
     * 
     */
    public List<GetProfilazioneUtenteCorrenteCduOutputDto> getCdu() {
        if (cdu == null) {
            cdu = new ArrayList<GetProfilazioneUtenteCorrenteCduOutputDto>();
        }
        return this.cdu;
    }

    /**
     * Recupera il valore della propriet codiceFiscale.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    /**
     * Imposta il valore della propriet codiceFiscale.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceFiscale(String value) {
        this.codiceFiscale = value;
    }

    /**
     * Recupera il valore della propriet cognome.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * Imposta il valore della propriet cognome.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCognome(String value) {
        this.cognome = value;
    }

    /**
     * Recupera il valore della propriet dataFineValidita.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataFineValidita() {
        return dataFineValidita;
    }

    /**
     * Imposta il valore della propriet dataFineValidita.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataFineValidita(XMLGregorianCalendar value) {
        this.dataFineValidita = value;
    }

    /**
     * Recupera il valore della propriet dataFineValiditaCurrent.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataFineValiditaCurrent() {
        return dataFineValiditaCurrent;
    }

    /**
     * Imposta il valore della propriet dataFineValiditaCurrent.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataFineValiditaCurrent(XMLGregorianCalendar value) {
        this.dataFineValiditaCurrent = value;
    }

    /**
     * Recupera il valore della propriet dataInizioValidita.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataInizioValidita() {
        return dataInizioValidita;
    }

    /**
     * Imposta il valore della propriet dataInizioValidita.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataInizioValidita(XMLGregorianCalendar value) {
        this.dataInizioValidita = value;
    }

    /**
     * Recupera il valore della propriet dataInizioValiditaCurrent.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataInizioValiditaCurrent() {
        return dataInizioValiditaCurrent;
    }

    /**
     * Imposta il valore della propriet dataInizioValiditaCurrent.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataInizioValiditaCurrent(XMLGregorianCalendar value) {
        this.dataInizioValiditaCurrent = value;
    }

    /**
     * Recupera il valore della propriet email.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Imposta il valore della propriet email.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
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
     * Recupera il valore della propriet nome.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNome() {
        return nome;
    }

    /**
     * Imposta il valore della propriet nome.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNome(String value) {
        this.nome = value;
    }

}
