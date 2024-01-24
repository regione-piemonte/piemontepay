/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java per ricercaFlussoInputDTO complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ricercaFlussoInputDTO"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://epaysim.interfacews.epaysim.epay.csi.it/}parentInput"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="dateEnd" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="dateStart" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="identificativoFlusso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="statoElaborazioneFlusso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ricercaFlussoInputDTO", propOrder = {
    "dateEnd",
    "dateStart",
    "identificativoFlusso",
    "statoElaborazioneFlusso"
})
public class RicercaFlussoInputDTO
    extends ParentInput
{

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dateEnd;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dateStart;
    protected String identificativoFlusso;
    protected String statoElaborazioneFlusso;

    /**
     * Recupera il valore della propriet dateEnd.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateEnd() {
        return dateEnd;
    }

    /**
     * Imposta il valore della propriet dateEnd.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateEnd(XMLGregorianCalendar value) {
        this.dateEnd = value;
    }

    /**
     * Recupera il valore della propriet dateStart.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateStart() {
        return dateStart;
    }

    /**
     * Imposta il valore della propriet dateStart.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateStart(XMLGregorianCalendar value) {
        this.dateStart = value;
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
     * Recupera il valore della propriet statoElaborazioneFlusso.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatoElaborazioneFlusso() {
        return statoElaborazioneFlusso;
    }

    /**
     * Imposta il valore della propriet statoElaborazioneFlusso.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatoElaborazioneFlusso(String value) {
        this.statoElaborazioneFlusso = value;
    }

}
