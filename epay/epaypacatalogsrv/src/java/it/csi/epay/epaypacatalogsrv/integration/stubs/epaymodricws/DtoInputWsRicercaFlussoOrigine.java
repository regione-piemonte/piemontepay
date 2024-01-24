/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.integration.stubs.epaymodricws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java per dtoInputWsRicercaFlussoOrigine complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="dtoInputWsRicercaFlussoOrigine"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="dataElaborazioneA" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="dataElaborazioneDa" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="datiUtente" type="{http://epaymodric.interfacews.epaymodric.epay.csi.it/}dtoUtente" minOccurs="0"/&gt;
 *         &lt;element name="idStatoFlusso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="identificativoFlusso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="identificativoIstitutoRicevente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtoInputWsRicercaFlussoOrigine", propOrder = {
    "dataElaborazioneA",
    "dataElaborazioneDa",
    "datiUtente",
    "idStatoFlusso",
    "identificativoFlusso",
    "identificativoIstitutoRicevente"
})
public class DtoInputWsRicercaFlussoOrigine {

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataElaborazioneA;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataElaborazioneDa;
    protected DtoUtente datiUtente;
    protected String idStatoFlusso;
    protected String identificativoFlusso;
    protected String identificativoIstitutoRicevente;

    /**
     * Recupera il valore della proprieta dataElaborazioneA.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataElaborazioneA() {
        return dataElaborazioneA;
    }

    /**
     * Imposta il valore della proprieta dataElaborazioneA.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataElaborazioneA(XMLGregorianCalendar value) {
        this.dataElaborazioneA = value;
    }

    /**
     * Recupera il valore della proprieta dataElaborazioneDa.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataElaborazioneDa() {
        return dataElaborazioneDa;
    }

    /**
     * Imposta il valore della proprieta dataElaborazioneDa.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataElaborazioneDa(XMLGregorianCalendar value) {
        this.dataElaborazioneDa = value;
    }

    /**
     * Recupera il valore della proprieta datiUtente.
     * 
     * @return
     *     possible object is
     *     {@link DtoUtente }
     *     
     */
    public DtoUtente getDatiUtente() {
        return datiUtente;
    }

    /**
     * Imposta il valore della proprieta datiUtente.
     * 
     * @param value
     *     allowed object is
     *     {@link DtoUtente }
     *     
     */
    public void setDatiUtente(DtoUtente value) {
        this.datiUtente = value;
    }

    /**
     * Recupera il valore della proprieta idStatoFlusso.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdStatoFlusso() {
        return idStatoFlusso;
    }

    /**
     * Imposta il valore della proprieta idStatoFlusso.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdStatoFlusso(String value) {
        this.idStatoFlusso = value;
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

}
