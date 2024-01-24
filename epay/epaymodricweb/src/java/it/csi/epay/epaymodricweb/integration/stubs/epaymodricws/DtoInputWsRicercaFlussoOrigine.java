/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodricweb.integration.stubs.epaymodricws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dtoInputWsRicercaFlussoOrigine complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dtoInputWsRicercaFlussoOrigine">
 *   &lt;complexContent>
 *     &lt;extension base="{http://epaymodric.interfacews.epaymodric.epay.csi.it/}dtoInputDate">
 *       &lt;sequence>
 *         &lt;element name="draw" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="idCodVersamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idStatoFlusso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="identificativiFlusso" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="identificativoFlusso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="identificativoIstitutoRicevente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iuv" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="length" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="psp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sortingCol" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sortingDir" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="start" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtoInputWsRicercaFlussoOrigine", propOrder = {
    "draw",
    "idCodVersamento",
    "idStatoFlusso",
    "identificativiFlusso",
    "identificativoFlusso",
    "identificativoIstitutoRicevente",
    "iuv",
    "length",
    "psp",
    "sortingCol",
    "sortingDir",
    "start"
})
@XmlSeeAlso({
    DtoInputWsRicercaFlussiDaEsportare.class
})
public class DtoInputWsRicercaFlussoOrigine
    extends DtoInputDate
{

    protected Integer draw;
    protected String idCodVersamento;
    protected String idStatoFlusso;
    @XmlElement(nillable = true)
    protected List<String> identificativiFlusso;
    protected String identificativoFlusso;
    protected String identificativoIstitutoRicevente;
    protected String iuv;
    protected Integer length;
    protected String psp;
    protected String sortingCol;
    protected String sortingDir;
    protected Integer start;

    /**
     * Gets the value of the draw property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDraw() {
        return draw;
    }

    /**
     * Sets the value of the draw property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDraw(Integer value) {
        this.draw = value;
    }

    /**
     * Gets the value of the idCodVersamento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdCodVersamento() {
        return idCodVersamento;
    }

    /**
     * Sets the value of the idCodVersamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdCodVersamento(String value) {
        this.idCodVersamento = value;
    }

    /**
     * Gets the value of the idStatoFlusso property.
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
     * Sets the value of the idStatoFlusso property.
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
     * Gets the value of the identificativiFlusso property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the identificativiFlusso property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIdentificativiFlusso().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getIdentificativiFlusso() {
        if (identificativiFlusso == null) {
            identificativiFlusso = new ArrayList<String>();
        }
        return this.identificativiFlusso;
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
     * Gets the value of the identificativoIstitutoRicevente property.
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
     * Sets the value of the identificativoIstitutoRicevente property.
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
     * Gets the value of the iuv property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIuv() {
        return iuv;
    }

    /**
     * Sets the value of the iuv property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIuv(String value) {
        this.iuv = value;
    }

    /**
     * Gets the value of the length property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getLength() {
        return length;
    }

    /**
     * Sets the value of the length property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setLength(Integer value) {
        this.length = value;
    }

    /**
     * Gets the value of the psp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPsp() {
        return psp;
    }

    /**
     * Sets the value of the psp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPsp(String value) {
        this.psp = value;
    }

    /**
     * Gets the value of the sortingCol property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSortingCol() {
        return sortingCol;
    }

    /**
     * Sets the value of the sortingCol property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSortingCol(String value) {
        this.sortingCol = value;
    }

    /**
     * Gets the value of the sortingDir property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSortingDir() {
        return sortingDir;
    }

    /**
     * Sets the value of the sortingDir property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSortingDir(String value) {
        this.sortingDir = value;
    }

    /**
     * Gets the value of the start property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getStart() {
        return start;
    }

    /**
     * Sets the value of the start property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setStart(Integer value) {
        this.start = value;
    }

}
