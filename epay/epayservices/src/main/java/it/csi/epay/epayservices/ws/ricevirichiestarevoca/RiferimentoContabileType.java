/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayservices.ws.ricevirichiestarevoca;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigInteger;


/**
 * <p>Classe Java per RiferimentoContabileType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
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
 *         &lt;element name="NumeroCapitolo" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
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
    protected BigInteger numeroCapitolo;
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
     * Recupera il valore della proprieta' codiceFiscaleEnte.
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
     * Imposta il valore della proprieta' codiceFiscaleEnte.
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
     * Recupera il valore della proprieta' codiceVersamento.
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
     * Imposta il valore della proprieta' codiceVersamento.
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
     * Recupera il valore della proprieta' dataInizioValidita.
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
     * Imposta il valore della proprieta' dataInizioValidita.
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
     * Recupera il valore della proprieta' dataFineValidita.
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
     * Imposta il valore della proprieta' dataFineValidita.
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
     * Recupera il valore della proprieta' tipologiaDatoSpecificoRiscossione.
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
     * Imposta il valore della proprieta' tipologiaDatoSpecificoRiscossione.
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
     * Recupera il valore della proprieta' datoSpecificoRiscossione.
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
     * Imposta il valore della proprieta' datoSpecificoRiscossione.
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
     * Recupera il valore della proprieta' descrizioneDatoSpecificoRiscossione.
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
     * Imposta il valore della proprieta' descrizioneDatoSpecificoRiscossione.
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
     * Recupera il valore della proprieta' annoEsercizio.
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
     * Imposta il valore della proprieta' annoEsercizio.
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
     * Recupera il valore della proprieta' numeroEsercizio.
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
     * Imposta il valore della proprieta' numeroEsercizio.
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
     * Recupera il valore della proprieta' annoAccertamento.
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
     * Imposta il valore della proprieta' annoAccertamento.
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
     * Recupera il valore della proprieta' numeroAccertamento.
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
     * Imposta il valore della proprieta' numeroAccertamento.
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
     * Recupera il valore della proprieta' numeroCapitolo.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNumeroCapitolo() {
        return numeroCapitolo;
    }

    /**
     * Imposta il valore della proprieta' numeroCapitolo.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNumeroCapitolo(BigInteger value) {
        this.numeroCapitolo = value;
    }

    /**
     * Recupera il valore della proprieta' numeroArticolo.
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
     * Imposta il valore della proprieta' numeroArticolo.
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
     * Recupera il valore della proprieta' livelloPDC.
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
     * Imposta il valore della proprieta' livelloPDC.
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
     * Recupera il valore della proprieta' titolo.
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
     * Imposta il valore della proprieta' titolo.
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
     * Recupera il valore della proprieta' codiceTipologia.
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
     * Imposta il valore della proprieta' codiceTipologia.
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
     * Recupera il valore della proprieta' codiceCategoria.
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
     * Imposta il valore della proprieta' codiceCategoria.
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
     * Recupera il valore della proprieta' chiaveIntersistema.
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
     * Imposta il valore della proprieta' chiaveIntersistema.
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
