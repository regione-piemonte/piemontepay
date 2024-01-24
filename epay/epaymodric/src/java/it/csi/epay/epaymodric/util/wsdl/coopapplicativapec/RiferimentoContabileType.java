/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.util.wsdl.coopapplicativapec;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>
 * Classe Java per RiferimentoContabileType complex type.
 *
 * <p>
 * Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 *
 * <pre>
 * &lt;complexType name="RiferimentoContabileType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="CodiceFiscaleEnte" type="{http://www.csi.it/epay/epaywso/types}String16Type"/&gt;
 *         &lt;element name="CodiceVersamento" type="{http://www.csi.it/epay/epaywso/types}CodiceVersamentoType"/&gt;
 *         &lt;element name="DataInizioValidita" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="DataFineValidita" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="TipologiaDatoSpecificoRiscossione" type="{http://www.csi.it/epay/epaywso/coopapplicativapec}TipologiaDatoSpecificoRiscossioneType" minOccurs="0"/&gt;
 *         &lt;element name="DatoSpecificoRiscossione" type="{http://www.csi.it/epay/epaywso/types}String140Type" minOccurs="0"/&gt;
 *         &lt;element name="DescrizioneDatoSpecificoRiscossione" type="{http://www.csi.it/epay/epaywso/types}String80Type" minOccurs="0"/&gt;
 *         &lt;element name="AnnoEsercizio" type="{http://www.csi.it/epay/epaywso/types}AnnoType"/&gt;
 *         &lt;element name="NumeroEsercizio" type="{http://www.csi.it/epay/epaywso/types}Numero6CifreType" minOccurs="0"/&gt;
 *         &lt;element name="AnnoAccertamento" type="{http://www.csi.it/epay/epaywso/types}AnnoType" minOccurs="0"/&gt;
 *         &lt;element name="NumeroAccertamento" type="{http://www.csi.it/epay/epaywso/types}Numero6CifreType" minOccurs="0"/&gt;
 *         &lt;element name="NumeroCapitolo" type="{http://www.csi.it/epay/epaywso/types}String50Type" minOccurs="0"/&gt;
 *         &lt;element name="NumeroArticolo" type="{http://www.csi.it/epay/epaywso/types}Numero6CifreType" minOccurs="0"/&gt;
 *         &lt;element name="LivelloPDC" type="{http://www.csi.it/epay/epaywso/types}String50Type" minOccurs="0"/&gt;
 *         &lt;element name="Titolo" type="{http://www.csi.it/epay/epaywso/types}String200Type" minOccurs="0"/&gt;
 *         &lt;element name="CodiceTipologia" type="{http://www.csi.it/epay/epaywso/coopapplicativapec}String200Type" minOccurs="0"/&gt;
 *         &lt;element name="CodiceCategoria" type="{http://www.csi.it/epay/epaywso/coopapplicativapec}String200Type" minOccurs="0"/&gt;
 *         &lt;element name="ChiaveIntersistema" type="{http://www.csi.it/epay/epaywso/coopapplicativapec}String50Type" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
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

    @XmlElement ( name = "CodiceFiscaleEnte", required = true )
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
    @XmlSchemaType ( name = "string" )
    protected TipologiaDatoSpecificoRiscossioneType tipologiaDatoSpecificoRiscossione;
    @XmlElement(name = "DatoSpecificoRiscossione")
    protected String datoSpecificoRiscossione;
    @XmlElement(name = "DescrizioneDatoSpecificoRiscossione")
    protected String descrizioneDatoSpecificoRiscossione;
    @XmlElement(name = "AnnoEsercizio")
    @XmlSchemaType ( name = "integer" )
    protected int annoEsercizio;
    @XmlElement(name = "NumeroEsercizio")
    @XmlSchemaType ( name = "integer" )
    protected Integer numeroEsercizio;
    @XmlElement(name = "AnnoAccertamento")
    @XmlSchemaType ( name = "integer" )
    protected Integer annoAccertamento;
    @XmlElement(name = "NumeroAccertamento")
    @XmlSchemaType ( name = "integer" )
    protected Integer numeroAccertamento;
    @XmlElement(name = "NumeroCapitolo")
    @XmlSchemaType ( name = "string" )
    protected String numeroCapitolo;
    @XmlElement(name = "NumeroArticolo")
    @XmlSchemaType ( name = "integer" )
    protected Integer numeroArticolo;
    @XmlElement(name = "LivelloPDC")
    protected String livelloPDC;
    @XmlElement(name = "Titolo")
    protected String titolo;
    @XmlElement(name = "CodiceTipologia")
    @XmlSchemaType ( name = "string" )
    protected String codiceTipologia;
    @XmlElement(name = "CodiceCategoria")
    @XmlSchemaType ( name = "string" )
    protected String codiceCategoria;

    @XmlElement ( name = "ChiaveIntersistema" )
    @XmlSchemaType ( name = "string" )
    protected String chiaveIntersistema;

	@XmlElement ( name = "CodiceTributo" )
	@XmlSchemaType ( name = "string" )
	protected String codiceTributo;
    /**
     * Recupera il valore della proprietacodiceFiscaleEnte.
     *
     * @return possible object is {@link String }
     *
     */
    public String getCodiceFiscaleEnte () {
        return codiceFiscaleEnte;
    }

    /**
     * Imposta il valore della proprietacodiceFiscaleEnte.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setCodiceFiscaleEnte ( String value ) {
        this.codiceFiscaleEnte = value;
    }

    /**
     * Recupera il valore della proprietacodiceVersamento.
     *
     * @return possible object is {@link String }
     *
     */
    public String getCodiceVersamento() {
        return codiceVersamento;
    }

    /**
     * Imposta il valore della proprietacodiceVersamento.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setCodiceVersamento(String value) {
        this.codiceVersamento = value;
    }

    /**
     * Recupera il valore della proprietadataInizioValidita.
     *
     * @return possible object is {@link XMLGregorianCalendar }
     *
     */
    public XMLGregorianCalendar getDataInizioValidita() {
        return dataInizioValidita;
    }

    /**
     * Imposta il valore della proprietadataInizioValidita.
     *
     * @param value allowed object is {@link XMLGregorianCalendar }
     *
     */
    public void setDataInizioValidita(XMLGregorianCalendar value) {
        this.dataInizioValidita = value;
    }

    /**
     * Recupera il valore della proprietadataFineValidita.
     *
     * @return possible object is {@link XMLGregorianCalendar }
     *
     */
    public XMLGregorianCalendar getDataFineValidita() {
        return dataFineValidita;
    }

    /**
     * Imposta il valore della proprietadataFineValidita.
     *
     * @param value allowed object is {@link XMLGregorianCalendar }
     *
     */
    public void setDataFineValidita(XMLGregorianCalendar value) {
        this.dataFineValidita = value;
    }

    /**
     * Recupera il valore della proprietatipologiaDatoSpecificoRiscossione.
     *
     * @return possible object is {@link TipologiaDatoSpecificoRiscossioneType }
     *
     */
    public TipologiaDatoSpecificoRiscossioneType getTipologiaDatoSpecificoRiscossione() {
        return tipologiaDatoSpecificoRiscossione;
    }

    /**
     * Imposta il valore della proprietatipologiaDatoSpecificoRiscossione.
     *
     * @param value allowed object is {@link TipologiaDatoSpecificoRiscossioneType }
     *
     */
    public void setTipologiaDatoSpecificoRiscossione(TipologiaDatoSpecificoRiscossioneType value) {
        this.tipologiaDatoSpecificoRiscossione = value;
    }

    /**
     * Recupera il valore della proprietadatoSpecificoRiscossione.
     *
     * @return possible object is {@link String }
     *
     */
    public String getDatoSpecificoRiscossione() {
        return datoSpecificoRiscossione;
    }

    /**
     * Imposta il valore della proprietadatoSpecificoRiscossione.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setDatoSpecificoRiscossione(String value) {
        this.datoSpecificoRiscossione = value;
    }

    /**
     * Recupera il valore della proprietadescrizioneDatoSpecificoRiscossione.
     *
     * @return possible object is {@link String }
     *
     */
    public String getDescrizioneDatoSpecificoRiscossione() {
        return descrizioneDatoSpecificoRiscossione;
    }

    /**
     * Imposta il valore della proprietadescrizioneDatoSpecificoRiscossione.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setDescrizioneDatoSpecificoRiscossione(String value) {
        this.descrizioneDatoSpecificoRiscossione = value;
    }

    /**
     * Recupera il valore della proprietaannoEsercizio.
     *
     */
    public int getAnnoEsercizio() {
        return annoEsercizio;
    }

    /**
     * Imposta il valore della proprietaannoEsercizio.
     *
     */
    public void setAnnoEsercizio(int value) {
        this.annoEsercizio = value;
    }

    /**
     * Recupera il valore della proprietanumeroEsercizio.
     *
     * @return possible object is {@link Integer }
     *
     */
    public Integer getNumeroEsercizio() {
        return numeroEsercizio;
    }

    /**
     * Imposta il valore della proprietanumeroEsercizio.
     *
     * @param value allowed object is {@link Integer }
     *
     */
    public void setNumeroEsercizio(Integer value) {
        this.numeroEsercizio = value;
    }

    /**
     * Recupera il valore della proprietaannoAccertamento.
     *
     * @return possible object is {@link Integer }
     *
     */
    public Integer getAnnoAccertamento() {
        return annoAccertamento;
    }

    /**
     * Imposta il valore della proprietaannoAccertamento.
     *
     * @param value allowed object is {@link Integer }
     *
     */
    public void setAnnoAccertamento(Integer value) {
        this.annoAccertamento = value;
    }

    /**
     * Recupera il valore della proprietanumeroAccertamento.
     *
     * @return possible object is {@link Integer }
     *
     */
    public Integer getNumeroAccertamento() {
        return numeroAccertamento;
    }

    /**
     * Imposta il valore della proprietanumeroAccertamento.
     *
     * @param value allowed object is {@link Integer }
     *
     */
    public void setNumeroAccertamento(Integer value) {
        this.numeroAccertamento = value;
    }

    /**
     * Recupera il valore della proprietanumeroCapitolo.
     *
     * @return possible object is {@link Integer }
     *
     */
    public String getNumeroCapitolo() {
        return numeroCapitolo;
    }

    /**
     * Imposta il valore della proprietanumeroCapitolo.
     *
     * @param value allowed object is {@link Integer }
     *
     */
    public void setNumeroCapitolo(String value) {
        this.numeroCapitolo = value;
    }

    /**
     * Recupera il valore della proprietanumeroArticolo.
     *
     * @return possible object is {@link Integer }
     *
     */
    public Integer getNumeroArticolo() {
        return numeroArticolo;
    }

    /**
     * Imposta il valore della proprietanumeroArticolo.
     *
     * @param value allowed object is {@link Integer }
     *
     */
    public void setNumeroArticolo(Integer value) {
        this.numeroArticolo = value;
    }

    /**
     * Recupera il valore della proprietalivelloPDC.
     *
     * @return possible object is {@link String }
     *
     */
    public String getLivelloPDC() {
        return livelloPDC;
    }

    /**
     * Imposta il valore della proprietalivelloPDC.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setLivelloPDC(String value) {
        this.livelloPDC = value;
    }

    /**
     * Recupera il valore della proprietatitolo.
     *
     * @return possible object is {@link String }
     *
     */
    public String getTitolo() {
        return titolo;
    }

    /**
     * Imposta il valore della proprietatitolo.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setTitolo(String value) {
        this.titolo = value;
    }

    /**
     * Recupera il valore della proprietacodiceTipologia.
     *
     * @return possible object is {@link String }
     *
     */
    public String getCodiceTipologia () {
        return codiceTipologia;
    }

    /**
     * Imposta il valore della proprietacodiceTipologia.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setCodiceTipologia ( String value ) {
        this.codiceTipologia = value;
    }

    /**
     * Recupera il valore della proprietacodiceCategoria.
     *
     * @return possible object is {@link String }
     *
     */
    public String getCodiceCategoria () {
        return codiceCategoria;
    }

    /**
     * Imposta il valore della proprietacodiceCategoria.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setCodiceCategoria ( String value ) {
        this.codiceCategoria = value;
    }

    /**
     * Recupera il valore della proprietachiaveIntersistema.
     *
     * @return possible object is {@link String }
     *
     */
    public String getChiaveIntersistema () {
        return chiaveIntersistema;
    }

    /**
     * Imposta il valore della proprietachiaveIntersistema.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setChiaveIntersistema ( String chiaveIntersistema ) {
        this.chiaveIntersistema = chiaveIntersistema;
    }

	public String getCodiceTributo () {
		return codiceTributo;
	}

	public void setCodiceTributo ( String codiceTributo ) {
		this.codiceTributo = codiceTributo;
	}
}
