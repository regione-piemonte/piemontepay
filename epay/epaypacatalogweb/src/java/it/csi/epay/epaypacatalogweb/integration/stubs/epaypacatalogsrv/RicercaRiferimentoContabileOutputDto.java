/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>
 * Classe Java per ricercaRiferimentoContabileOutputDto complex type.
 * 
 * <p>
 * Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ricercaRiferimentoContabileOutputDto"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         
 *         &lt;element name="annoAccertamento" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="annoEsercizio" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="categoria" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="codiceCodiceVersamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="codiceMacrotipo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="codiceTematica" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="codiceTipologiaDatoSpecificoRiscossione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="codiceVoceEntrata" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="dataFineValidita" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="dataInizioValidita" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="datoSpecificoRiscossione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="datoSpecificoRiscossioneTassonomia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="descrizioneCodiceVersamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="descrizioneDatoSpecificoRiscossione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="descrizioneDatoSpecificoRiscossioneRifCont" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="descrizioneErroreAggiornamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="descrizioneMacrotipo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="descrizioneTematica" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="descrizioneTipologiaDatoSpecificoRiscossione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="descrizioneVoceEntrata" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="fatturaCodiceVersamento" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="ibanAppoggioCodiceVersamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ibanCodiceVersamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="idCodiceVersamento" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="livelloPdc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="mailCodiceVersamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="modalitaIntegrazioneCodiceVersamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="nomeEnte" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="noteCodiceVersamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="numeroAccertamento" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="numeroArticolo" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="numeroCapitolo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="statoCodiceVersamento" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="storico" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}
 *         &lt;element name=ricercaRiferimentoContabileStoricoOutputDto" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="tipoEnte" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="tipologia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="tipologiaCodiceVersamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="tipoServizio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="titolo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="dataFineValiditaCodiceTassonomico" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="dataInizioValiditaCodiceTassonomico" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType ( XmlAccessType.FIELD )
@XmlType ( name = "ricercaRiferimentoContabileOutputDto", propOrder = {

    "annoAccertamento",
    "annoEsercizio",
    "categoria",
    "codiceCodiceVersamento",
    "codiceMacrotipo",
    "codiceTematica",
    "codiceTipologiaDatoSpecificoRiscossione",
    "codiceVoceEntrata",
    "dataFineValidita",
    "dataInizioValidita",
    "datoSpecificoRiscossione",
    "datoSpecificoRiscossioneTassonomia",
    "descrizioneCodiceVersamento",
    "descrizioneDatoSpecificoRiscossione",
    "descrizioneDatoSpecificoRiscossioneRifCont",
    "descrizioneErroreAggiornamento",
    "descrizioneMacrotipo",
    "descrizioneTematica",
    "descrizioneTipologiaDatoSpecificoRiscossione",
    "descrizioneVoceEntrata",
    "fatturaCodiceVersamento",
    "ibanAppoggioCodiceVersamento",
    "ibanCodiceVersamento",
    "id",
    "idCodiceVersamento",
    "livelloPdc",
    "mailCodiceVersamento",
    "modalitaIntegrazioneCodiceVersamento",
    "nomeEnte",
    "noteCodiceVersamento",
    "numeroAccertamento",
    "numeroArticolo",
    "numeroCapitolo",
    "statoCodiceVersamento",
    "storico",
    "tipoEnte",
    "tipologia",
    "tipologiaCodiceVersamento",
    "tipoServizio",
    "dataFineValiditaCodiceTassonomico",
    "dataInizioValiditaCodiceTassonomico",
    "titolo",
    "flagMbPrimario",
    "flagMbSecondario",
    "mbModalita",
    "mbEnteSecondario",
    "mbCodiceVersamentoAssociato",
	"codiceTributo",
} )
public class RicercaRiferimentoContabileOutputDto {

    protected Integer annoAccertamento;

    protected Integer annoEsercizio;

    protected String categoria;

    protected String codiceCodiceVersamento;

    protected String codiceMacrotipo;

    protected String codiceTematica;

    protected String codiceTipologiaDatoSpecificoRiscossione;

    protected String codiceVoceEntrata;

    @XmlSchemaType ( name = "dateTime" )
    protected XMLGregorianCalendar dataFineValidita;

    @XmlSchemaType ( name = "dateTime" )
    protected XMLGregorianCalendar dataInizioValidita;

    protected String datoSpecificoRiscossione;

    protected String datoSpecificoRiscossioneTassonomia;

    protected String descrizioneCodiceVersamento;

    protected String descrizioneDatoSpecificoRiscossione;
    
    protected String descrizioneDatoSpecificoRiscossioneRifCont;

    protected String descrizioneErroreAggiornamento;

    protected String descrizioneMacrotipo;

    protected String descrizioneTematica;

    protected String descrizioneTipologiaDatoSpecificoRiscossione;

    protected String descrizioneVoceEntrata;

    protected boolean fatturaCodiceVersamento;

    protected String ibanAppoggioCodiceVersamento;

    protected String ibanCodiceVersamento;

    protected Long id;

    protected Long idCodiceVersamento;

    protected String livelloPdc;

    protected String mailCodiceVersamento;

    protected String modalitaIntegrazioneCodiceVersamento;

    protected String nomeEnte;

    protected String noteCodiceVersamento;

    protected Integer numeroAccertamento;

    protected Integer numeroArticolo;

    protected String numeroCapitolo;

    protected boolean statoCodiceVersamento;

    @XmlElement ( nillable = true )
    protected List<RicercaRiferimentoContabileStoricoOutputDto> storico;

    protected String tipoEnte;

    protected String tipologia;

    protected String tipologiaCodiceVersamento;

    protected String tipoServizio;

    protected String titolo;

    @XmlSchemaType ( name = "dateTime" )
    protected XMLGregorianCalendar dataFineValiditaCodiceTassonomico;

    @XmlSchemaType ( name = "dateTime" )
    protected XMLGregorianCalendar dataInizioValiditaCodiceTassonomico;

    protected Boolean flagMbPrimario;

    protected Boolean flagMbSecondario;

    protected String mbModalita;

    protected String mbEnteSecondario;

    protected String mbCodiceVersamentoAssociato;

	protected String codiceTributo;

    /**
     * Recupera il valore della propriet annoAccertamento.
     * 
     * @return possible object is {@link Integer }
     * 
     */
    public Integer getAnnoAccertamento () {
        return annoAccertamento;
    }

    /**
     * Imposta il valore della propriet annoAccertamento.
     * 
     * @param value allowed object is {@link Integer }
     * 
     */
    public void setAnnoAccertamento ( Integer value ) {
        this.annoAccertamento = value;
    }

    /**
     * Recupera il valore della propriet annoEsercizio.
     * 
     * @return possible object is {@link Integer }
     * 
     */
    public Integer getAnnoEsercizio () {
        return annoEsercizio;
    }

    /**
     * Imposta il valore della propriet annoEsercizio.
     * 
     * @param value allowed object is {@link Integer }
     * 
     */
    public void setAnnoEsercizio ( Integer value ) {
        this.annoEsercizio = value;
    }

    /**
     * Recupera il valore della propriet categoria.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getCategoria () {
        return categoria;
    }

    /**
     * Imposta il valore della propriet categoria.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setCategoria ( String value ) {
        this.categoria = value;
    }

    /**
     * Recupera il valore della propriet codiceCodiceVersamento.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getCodiceCodiceVersamento () {
        return codiceCodiceVersamento;
    }

    /**
     * Imposta il valore della propriet codiceCodiceVersamento.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setCodiceCodiceVersamento ( String value ) {
        this.codiceCodiceVersamento = value;
    }

    /**
     * Recupera il valore della propriet codiceMacrotipo.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getCodiceMacrotipo () {
        return codiceMacrotipo;
    }

    /**
     * Imposta il valore della propriet codiceMacrotipo.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setCodiceMacrotipo ( String value ) {
        this.codiceMacrotipo = value;
    }

    /**
     * Recupera il valore della propriet codiceTematica.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getCodiceTematica () {
        return codiceTematica;
    }

    /**
     * Imposta il valore della propriet codiceTematica.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setCodiceTematica ( String value ) {
        this.codiceTematica = value;
    }

    /**
     * Recupera il valore della propriet codiceTipologiaDatoSpecificoRiscossione.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getCodiceTipologiaDatoSpecificoRiscossione () {
        return codiceTipologiaDatoSpecificoRiscossione;
    }

    /**
     * Imposta il valore della propriet codiceTipologiaDatoSpecificoRiscossione.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setCodiceTipologiaDatoSpecificoRiscossione ( String value ) {
        this.codiceTipologiaDatoSpecificoRiscossione = value;
    }

    /**
     * Recupera il valore della propriet codiceVoceEntrata.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getCodiceVoceEntrata () {
        return codiceVoceEntrata;
    }

    /**
     * Imposta il valore della propriet codiceVoceEntrata.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setCodiceVoceEntrata ( String value ) {
        this.codiceVoceEntrata = value;
    }

    /**
     * Recupera il valore della propriet dataFineValidita.
     * 
     * @return possible object is {@link XMLGregorianCalendar }
     * 
     */
    public XMLGregorianCalendar getDataFineValidita () {
        return dataFineValidita;
    }

    /**
     * Imposta il valore della propriet dataFineValidita.
     * 
     * @param value allowed object is {@link XMLGregorianCalendar }
     * 
     */
    public void setDataFineValidita ( XMLGregorianCalendar value ) {
        this.dataFineValidita = value;
    }

    /**
     * Recupera il valore della propriet dataInizioValidita.
     * 
     * @return possible object is {@link XMLGregorianCalendar }
     * 
     */
    public XMLGregorianCalendar getDataInizioValidita () {
        return dataInizioValidita;
    }

    /**
     * Imposta il valore della propriet dataInizioValidita.
     * 
     * @param value allowed object is {@link XMLGregorianCalendar }
     * 
     */
    public void setDataInizioValidita ( XMLGregorianCalendar value ) {
        this.dataInizioValidita = value;
    }

    /**
     * Recupera il valore della propriet datoSpecificoRiscossione.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getDatoSpecificoRiscossione () {
        return datoSpecificoRiscossione;
    }

    /**
     * Imposta il valore della propriet datoSpecificoRiscossione.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setDatoSpecificoRiscossione ( String value ) {
        this.datoSpecificoRiscossione = value;
    }

    /**
     * Recupera il valore della propriet descrizioneCodiceVersamento.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getDescrizioneCodiceVersamento () {
        return descrizioneCodiceVersamento;
    }

    /**
     * Imposta il valore della propriet descrizioneCodiceVersamento.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setDescrizioneCodiceVersamento ( String value ) {
        this.descrizioneCodiceVersamento = value;
    }

    /**
     * Recupera il valore della propriet descrizioneDatoSpecificoRiscossione.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getDescrizioneDatoSpecificoRiscossione () {
        return descrizioneDatoSpecificoRiscossione;
    }

    /**
     * Imposta il valore della propriet descrizioneDatoSpecificoRiscossione.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setDescrizioneDatoSpecificoRiscossione ( String value ) {
        this.descrizioneDatoSpecificoRiscossione = value;
    }

    /**
     * Recupera il valore della propriet descrizioneErroreAggiornamento.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getDescrizioneErroreAggiornamento () {
        return descrizioneErroreAggiornamento;
    }

    /**
     * Imposta il valore della propriet descrizioneErroreAggiornamento.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setDescrizioneErroreAggiornamento ( String value ) {
        this.descrizioneErroreAggiornamento = value;
    }

    /**
     * Recupera il valore della propriet descrizioneMacrotipo.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getDescrizioneMacrotipo () {
        return descrizioneMacrotipo;
    }

    /**
     * Imposta il valore della propriet descrizioneMacrotipo.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setDescrizioneMacrotipo ( String value ) {
        this.descrizioneMacrotipo = value;
    }

    /**
     * Recupera il valore della propriet descrizioneTematica.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getDescrizioneTematica () {
        return descrizioneTematica;
    }

    /**
     * Imposta il valore della propriet descrizioneTematica.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setDescrizioneTematica ( String value ) {
        this.descrizioneTematica = value;
    }

    /**
     * Recupera il valore della propriet descrizioneTipologiaDatoSpecificoRiscossione.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getDescrizioneTipologiaDatoSpecificoRiscossione () {
        return descrizioneTipologiaDatoSpecificoRiscossione;
    }

    /**
     * Imposta il valore della propriet descrizioneTipologiaDatoSpecificoRiscossione.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setDescrizioneTipologiaDatoSpecificoRiscossione ( String value ) {
        this.descrizioneTipologiaDatoSpecificoRiscossione = value;
    }

    /**
     * Recupera il valore della propriet descrizioneVoceEntrata.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getDescrizioneVoceEntrata () {
        return descrizioneVoceEntrata;
    }

    /**
     * Imposta il valore della propriet descrizioneVoceEntrata.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setDescrizioneVoceEntrata ( String value ) {
        this.descrizioneVoceEntrata = value;
    }

    /**
     * Recupera il valore della propriet id.
     * 
     * @return possible object is {@link Long }
     * 
     */
    public Long getId () {
        return id;
    }

    /**
     * Imposta il valore della propriet id.
     * 
     * @param value allowed object is {@link Long }
     * 
     */
    public void setId ( Long value ) {
        this.id = value;
    }

    /**
     * Recupera il valore della propriet idCodiceVersamento.
     * 
     * @return possible object is {@link Long }
     * 
     */
    public Long getIdCodiceVersamento () {
        return idCodiceVersamento;
    }

    /**
     * Imposta il valore della propriet idCodiceVersamento.
     * 
     * @param value allowed object is {@link Long }
     * 
     */
    public void setIdCodiceVersamento ( Long value ) {
        this.idCodiceVersamento = value;
    }

    /**
     * Recupera il valore della propriet livelloPdc.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getLivelloPdc () {
        return livelloPdc;
    }

    /**
     * Imposta il valore della propriet livelloPdc.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setLivelloPdc ( String value ) {
        this.livelloPdc = value;
    }

    /**
     * Recupera il valore della propriet numeroAccertamento.
     * 
     * @return possible object is {@link Integer }
     * 
     */
    public Integer getNumeroAccertamento () {
        return numeroAccertamento;
    }

    /**
     * Imposta il valore della propriet numeroAccertamento.
     * 
     * @param value allowed object is {@link Integer }
     * 
     */
    public void setNumeroAccertamento ( Integer value ) {
        this.numeroAccertamento = value;
    }

    /**
     * Recupera il valore della propriet numeroArticolo.
     * 
     * @return possible object is {@link Integer }
     * 
     */
    public Integer getNumeroArticolo () {
        return numeroArticolo;
    }

    /**
     * Imposta il valore della propriet numeroArticolo.
     * 
     * @param value allowed object is {@link Integer }
     * 
     */
    public void setNumeroArticolo ( Integer value ) {
        this.numeroArticolo = value;
    }

    /**
     * Recupera il valore della propriet numeroCapitolo.
     * 
     * @return possible object is {@link Integer }
     * 
     */
    public String getNumeroCapitolo () {
        return numeroCapitolo;
    }

    /**
     * Imposta il valore della propriet numeroCapitolo.
     * 
     * @param value allowed object is {@link Integer }
     * 
     */
    public void setNumeroCapitolo ( String value ) {
        this.numeroCapitolo = value;
    }

    /**
     * Gets the value of the storico property.
     * 
     * <p>
     * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present
     * inside the JAXB object. This is why there is not a <CODE>set</CODE> method for the storico property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * 
     * <pre>
     * getStorico ().add ( newItem );
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list {@link RicercaRiferimentoContabileStoricoOutputDto }
     * 
     * 
     */
    public List<RicercaRiferimentoContabileStoricoOutputDto> getStorico () {
        if ( storico == null ) {
            storico = new ArrayList<>();
        }
        return this.storico;
    }

    /**
     * Recupera il valore della propriet tipologia.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getTipologia () {
        return tipologia;
    }

    /**
     * Imposta il valore della propriet tipologia.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setTipologia ( String value ) {
        this.tipologia = value;
    }

    /**
     * Recupera il valore della propriet titolo.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getTitolo () {
        return titolo;
    }

    /**
     * Imposta il valore della propriet titolo.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setTitolo ( String value ) {
        this.titolo = value;
    }

    /**
     * @return the dataFineValiditaCodiceTassonomico
     */
    public XMLGregorianCalendar getDataFineValiditaCodiceTassonomico () {
        return dataFineValiditaCodiceTassonomico;
    }

    /**
     * @param dataFineValiditaCodiceTassonomico the dataFineValiditaCodiceTassonomico to set
     */
    public void setDataFineValiditaCodiceTassonomico ( XMLGregorianCalendar dataFineValiditaCodiceTassonomico ) {
        this.dataFineValiditaCodiceTassonomico = dataFineValiditaCodiceTassonomico;
    }

    /**
     * @return the dataInizioValiditaCodiceTassonomico
     */
    public XMLGregorianCalendar getDataInizioValiditaCodiceTassonomico () {
        return dataInizioValiditaCodiceTassonomico;
    }

    /**
     * @param dataInizioValiditaCodiceTassonomico the dataInizioValiditaCodiceTassonomico to set
     */
    public void setDataInizioValiditaCodiceTassonomico ( XMLGregorianCalendar dataInizioValiditaCodiceTassonomico ) {
        this.dataInizioValiditaCodiceTassonomico = dataInizioValiditaCodiceTassonomico;
    }

    /**
     * @return the datoSpecificoRiscossioneTassonomia
     */
    public String getDatoSpecificoRiscossioneTassonomia () {
        return datoSpecificoRiscossioneTassonomia;
    }

    /**
     * @param datoSpecificoRiscossioneTassonomia the datoSpecificoRiscossioneTassonomia to set
     */
    public void setDatoSpecificoRiscossioneTassonomia ( String datoSpecificoRiscossioneTassonomia ) {
        this.datoSpecificoRiscossioneTassonomia = datoSpecificoRiscossioneTassonomia;
    }

    /**
     * @return the fatturaCodiceVersamento
     */
    public boolean isFatturaCodiceVersamento () {
        return fatturaCodiceVersamento;
    }

    /**
     * @param fatturaCodiceVersamento the fatturaCodiceVersamento to set
     */
    public void setFatturaCodiceVersamento ( boolean fatturaCodiceVersamento ) {
        this.fatturaCodiceVersamento = fatturaCodiceVersamento;
    }

    /**
     * @return the ibanAppoggioCodiceVersamento
     */
    public String getIbanAppoggioCodiceVersamento () {
        return ibanAppoggioCodiceVersamento;
    }

    /**
     * @param ibanAppoggioCodiceVersamento the ibanAppoggioCodiceVersamento to set
     */
    public void setIbanAppoggioCodiceVersamento ( String ibanAppoggioCodiceVersamento ) {
        this.ibanAppoggioCodiceVersamento = ibanAppoggioCodiceVersamento;
    }

    /**
     * @return the ibanCodiceVersamento
     */
    public String getIbanCodiceVersamento () {
        return ibanCodiceVersamento;
    }

    /**
     * @param ibanCodiceVersamento the ibanCodiceVersamento to set
     */
    public void setIbanCodiceVersamento ( String ibanCodiceVersamento ) {
        this.ibanCodiceVersamento = ibanCodiceVersamento;
    }

    /**
     * @return the mailCodiceVersamento
     */
    public String getMailCodiceVersamento () {
        return mailCodiceVersamento;
    }

    /**
     * @param mailCodiceVersamento the mailCodiceVersamento to set
     */
    public void setMailCodiceVersamento ( String mailCodiceVersamento ) {
        this.mailCodiceVersamento = mailCodiceVersamento;
    }

    /**
     * @return the modalitaIntegrazioneCodiceVersamento
     */
    public String getModalitaIntegrazioneCodiceVersamento () {
        return modalitaIntegrazioneCodiceVersamento;
    }

    /**
     * @param modalitaIntegrazioneCodiceVersamento the modalitaIntegrazioneCodiceVersamento to set
     */
    public void setModalitaIntegrazioneCodiceVersamento ( String modalitaIntegrazioneCodiceVersamento ) {
        this.modalitaIntegrazioneCodiceVersamento = modalitaIntegrazioneCodiceVersamento;
    }

    /**
     * @return the nomeEnte
     */
    public String getNomeEnte () {
        return nomeEnte;
    }

    /**
     * @param nomeEnte the nomeEnte to set
     */
    public void setNomeEnte ( String nomeEnte ) {
        this.nomeEnte = nomeEnte;
    }

    /**
     * @return the noteCodiceVersamento
     */
    public String getNoteCodiceVersamento () {
        return noteCodiceVersamento;
    }

    /**
     * @param noteCodiceVersamento the noteCodiceVersamento to set
     */
    public void setNoteCodiceVersamento ( String noteCodiceVersamento ) {
        this.noteCodiceVersamento = noteCodiceVersamento;
    }

    /**
     * @return the statoCodiceVersamento
     */
    public boolean isStatoCodiceVersamento () {
        return statoCodiceVersamento;
    }

    /**
     * @param statoCodiceVersamento the statoCodiceVersamento to set
     */
    public void setStatoCodiceVersamento ( boolean statoCodiceVersamento ) {
        this.statoCodiceVersamento = statoCodiceVersamento;
    }

    /**
     * @return the tipoEnte
     */
    public String getTipoEnte () {
        return tipoEnte;
    }

    /**
     * @param tipoEnte the tipoEnte to set
     */
    public void setTipoEnte ( String tipoEnte ) {
        this.tipoEnte = tipoEnte;
    }

    /**
     * @return the tipologiaCodiceVersamento
     */
    public String getTipologiaCodiceVersamento () {
        return tipologiaCodiceVersamento;
    }

    /**
     * @param tipologiaCodiceVersamento the tipologiaCodiceVersamento to set
     */
    public void setTipologiaCodiceVersamento ( String tipologiaCodiceVersamento ) {
        this.tipologiaCodiceVersamento = tipologiaCodiceVersamento;
    }

    /**
     * @param storico the storico to set
     */
    public void setStorico ( List<RicercaRiferimentoContabileStoricoOutputDto> storico ) {
        this.storico = storico;
    }

    public Boolean getFlagMbPrimario () {
        return flagMbPrimario;
    }

    public void setFlagMbPrimario ( Boolean flagMbPrimario ) {
        this.flagMbPrimario = flagMbPrimario;
    }

    public Boolean getFlagMbSecondario () {
        return flagMbSecondario;
    }

    public void setFlagMbSecondario ( Boolean flagMbSecondario ) {
        this.flagMbSecondario = flagMbSecondario;
    }

    public String getMbModalita () {
        return mbModalita;
    }

    public void setMbModalita ( String mbModalita ) {
        this.mbModalita = mbModalita;
    }

    public String getMbEnteSecondario () {
        return mbEnteSecondario;
    }

    public void setMbEnteSecondario ( String mbEnteSecondario ) {
        this.mbEnteSecondario = mbEnteSecondario;
    }

    public String getMbCodiceVersamentoAssociato () {
        return mbCodiceVersamentoAssociato;
    }

    public void setMbCodiceVersamentoAssociato ( String mbCodiceVersamentoAssociato ) {
        this.mbCodiceVersamentoAssociato = mbCodiceVersamentoAssociato;
    }

    
    /**
     * @return the descrizioneDatoSpecificoRiscossioneRifCont
     */
    public String getDescrizioneDatoSpecificoRiscossioneRifCont () {
        return descrizioneDatoSpecificoRiscossioneRifCont;
    }

    
    /**
     * @param descrizioneDatoSpecificoRiscossioneRifCont the descrizioneDatoSpecificoRiscossioneRifCont to set
     */
    public void setDescrizioneDatoSpecificoRiscossioneRifCont ( String descrizioneDatoSpecificoRiscossioneRifCont ) {
        this.descrizioneDatoSpecificoRiscossioneRifCont = descrizioneDatoSpecificoRiscossioneRifCont;
    }

    
    /**
     * @return the tipoServizio
     */
    public String getTipoServizio () {
        return tipoServizio;
    }

    
    /**
     * @param tipoServizio the tipoServizio to set
     */
    public void setTipoServizio ( String tipoServizio ) {
        this.tipoServizio = tipoServizio;
    }

	public String getCodiceTributo () {
		return codiceTributo;
	}

	public void setCodiceTributo ( String codiceTributo ) {
		this.codiceTributo = codiceTributo;
	}
}
