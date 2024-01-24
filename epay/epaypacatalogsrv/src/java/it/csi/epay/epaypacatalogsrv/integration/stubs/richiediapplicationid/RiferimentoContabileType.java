/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.integration.stubs.richiediapplicationid;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigInteger;


/**
 * <p>Java class for RiferimentoContabileType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RiferimentoContabileType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CodiceFiscaleEnte" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CodiceVersamento" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DataInizioValidita" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="DataFineValidita" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="TipologiaDatoSpecificoRiscossione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DatoSpecificoRiscossione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DescrizioneDatoSpecificoRiscossione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AnnoEsercizio" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="NumeroEsercizio" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="AnnoAccertamento" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="NumeroAccertamento" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="NumeroCapitolo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NumeroArticolo" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="LivelloPDC" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Titolo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CodiceTipologia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CodiceCategoria" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ChiaveIntersistema" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RiferimentoContabileType", propOrder = {
    "codiceFiscaleEnte",
    "codiceVersamento",
    "dataInizioValidita",
    "dataFineValidita",
    "tipologiaDatoSpecificoRiscossione",
    "datoSpecificoRiscossione",
    "descrizioneDatoSpecificoRiscossione",
    "annoEsercizio",
    "numeroEsercizio",
    "annoAccertamento",
    "numeroAccertamento",
    "numeroCapitolo",
    "numeroArticolo",
    "livelloPDC",
    "titolo",
    "codiceTipologia",
    "codiceCategoria",
    "chiaveIntersistema",
	"codiceTributo",
})
public class RiferimentoContabileType {

    @XmlElement(name = "CodiceFiscaleEnte", required = true)
    protected String codiceFiscaleEnte;
    @XmlElement(name = "CodiceVersamento", required = true)
    protected String codiceVersamento;
    @XmlElement(name = "DataInizioValidita", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataInizioValidita;
    @XmlElement(name = "DataFineValidita")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataFineValidita;
    @XmlElement(name = "TipologiaDatoSpecificoRiscossione")
    protected String tipologiaDatoSpecificoRiscossione;
    @XmlElement(name = "DatoSpecificoRiscossione")
    protected String datoSpecificoRiscossione;
    @XmlElement(name = "DescrizioneDatoSpecificoRiscossione")
    protected String descrizioneDatoSpecificoRiscossione;
    @XmlElement(name = "AnnoEsercizio", required = true)
    protected BigInteger annoEsercizio;
    @XmlElement(name = "NumeroEsercizio")
    protected BigInteger numeroEsercizio;
    @XmlElement(name = "AnnoAccertamento")
    protected BigInteger annoAccertamento;
    @XmlElement(name = "NumeroAccertamento")
    protected BigInteger numeroAccertamento;
    @XmlElement(name = "NumeroCapitolo")
    protected String numeroCapitolo;
    @XmlElement(name = "NumeroArticolo")
    protected BigInteger numeroArticolo;
    @XmlElement(name = "LivelloPDC")
    protected String livelloPDC;
    @XmlElement(name = "Titolo")
    protected String titolo;
    @XmlElement(name = "CodiceTipologia")
    protected String codiceTipologia;
    @XmlElement(name = "CodiceCategoria")
    protected String codiceCategoria;
    @XmlElement(name = "ChiaveIntersistema")
    protected String chiaveIntersistema;

	@XmlElement ( name = "CodiceTributo" )
	protected String codiceTributo;
    /**
     * Gets the value of the codiceFiscaleEnte property.
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
     * Sets the value of the codiceFiscaleEnte property.
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
     * Gets the value of the codiceVersamento property.
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
     * Sets the value of the codiceVersamento property.
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
     * Gets the value of the dataInizioValidita property.
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
     * Sets the value of the dataInizioValidita property.
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
     * Gets the value of the dataFineValidita property.
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
     * Sets the value of the dataFineValidita property.
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
     * Gets the value of the tipologiaDatoSpecificoRiscossione property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipologiaDatoSpecificoRiscossione() {
        return tipologiaDatoSpecificoRiscossione;
    }

    /**
     * Sets the value of the tipologiaDatoSpecificoRiscossione property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipologiaDatoSpecificoRiscossione(String value) {
        this.tipologiaDatoSpecificoRiscossione = value;
    }

    /**
     * Gets the value of the datoSpecificoRiscossione property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDatoSpecificoRiscossione() {
        return datoSpecificoRiscossione;
    }

    /**
     * Sets the value of the datoSpecificoRiscossione property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDatoSpecificoRiscossione(String value) {
        this.datoSpecificoRiscossione = value;
    }

    /**
     * Gets the value of the descrizioneDatoSpecificoRiscossione property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizioneDatoSpecificoRiscossione() {
        return descrizioneDatoSpecificoRiscossione;
    }

    /**
     * Sets the value of the descrizioneDatoSpecificoRiscossione property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizioneDatoSpecificoRiscossione(String value) {
        this.descrizioneDatoSpecificoRiscossione = value;
    }

    /**
     * Gets the value of the annoEsercizio property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getAnnoEsercizio() {
        return annoEsercizio;
    }

    /**
     * Sets the value of the annoEsercizio property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setAnnoEsercizio(BigInteger value) {
        this.annoEsercizio = value;
    }

    /**
     * Gets the value of the numeroEsercizio property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNumeroEsercizio() {
        return numeroEsercizio;
    }

    /**
     * Sets the value of the numeroEsercizio property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNumeroEsercizio(BigInteger value) {
        this.numeroEsercizio = value;
    }

    /**
     * Gets the value of the annoAccertamento property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getAnnoAccertamento() {
        return annoAccertamento;
    }

    /**
     * Sets the value of the annoAccertamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setAnnoAccertamento(BigInteger value) {
        this.annoAccertamento = value;
    }

    /**
     * Gets the value of the numeroAccertamento property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNumeroAccertamento() {
        return numeroAccertamento;
    }

    /**
     * Sets the value of the numeroAccertamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNumeroAccertamento(BigInteger value) {
        this.numeroAccertamento = value;
    }

    /**
     * Gets the value of the numeroCapitolo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroCapitolo() {
        return numeroCapitolo;
    }

    /**
     * Sets the value of the numeroCapitolo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroCapitolo(String value) {
        this.numeroCapitolo = value;
    }

    /**
     * Gets the value of the numeroArticolo property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNumeroArticolo() {
        return numeroArticolo;
    }

    /**
     * Sets the value of the numeroArticolo property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNumeroArticolo(BigInteger value) {
        this.numeroArticolo = value;
    }

    /**
     * Gets the value of the livelloPDC property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLivelloPDC() {
        return livelloPDC;
    }

    /**
     * Sets the value of the livelloPDC property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLivelloPDC(String value) {
        this.livelloPDC = value;
    }

    /**
     * Gets the value of the titolo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitolo() {
        return titolo;
    }

    /**
     * Sets the value of the titolo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitolo(String value) {
        this.titolo = value;
    }

    /**
     * Gets the value of the codiceTipologia property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceTipologia() {
        return codiceTipologia;
    }

    /**
     * Sets the value of the codiceTipologia property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceTipologia(String value) {
        this.codiceTipologia = value;
    }

    /**
     * Gets the value of the codiceCategoria property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceCategoria() {
        return codiceCategoria;
    }

    /**
     * Sets the value of the codiceCategoria property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceCategoria(String value) {
        this.codiceCategoria = value;
    }

    /**
     * Gets the value of the chiaveIntersistema property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChiaveIntersistema() {
        return chiaveIntersistema;
    }

    /**
     * Sets the value of the chiaveIntersistema property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChiaveIntersistema(String value) {
        this.chiaveIntersistema = value;
    }

	public String getCodiceTributo () {
		return codiceTributo;
	}

	public void setCodiceTributo ( String codiceTributo ) {
		this.codiceTributo = codiceTributo;
	}
}
