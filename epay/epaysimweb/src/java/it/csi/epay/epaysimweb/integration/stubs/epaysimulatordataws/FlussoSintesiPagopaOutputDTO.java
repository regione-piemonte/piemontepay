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
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per flussoSintesiPagopaOutputDTO complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="flussoSintesiPagopaOutputDTO"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://epaysim.interfacews.epaysim.epay.csi.it/}parentOutput"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="accertamentoAnno" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="accertamentoNumero" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="articolo" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="capitolo" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="codiceVersamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="datiSpecificiDiRiscossione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="descrizioneCodiceVersamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="descrizioneDatiSpecifici" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="importoQuotaAggregazione" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="macrotipo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="numeroPagamentiAggregazione" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="pdc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="progressivoFlussoSintetico" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="tematica" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="listFlussoDettaglioPagopaOutputDTO" type="{http://epaysim.interfacews.epaysim.epay.csi.it/}flussoDettaglioPagopaOutputDTO" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="flussoOriginePagopaOutputDTO" type="{http://epaysim.interfacews.epaysim.epay.csi.it/}flussoOriginePagopaOutputDTO" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "flussoSintesiPagopaOutputDTO", propOrder = {
    "id",
    "accertamentoAnno",
    "accertamentoNumero",
    "articolo",
    "capitolo",
    "codiceVersamento",
    "datiSpecificiDiRiscossione",
    "descrizioneCodiceVersamento",
    "descrizioneDatiSpecifici",
    "importoQuotaAggregazione",
    "macrotipo",
    "numeroPagamentiAggregazione",
    "pdc",
    "progressivoFlussoSintetico",
    "tematica",
    "listFlussoDettaglioPagopaOutputDTO",
    "flussoOriginePagopaOutputDTO"
})
public class FlussoSintesiPagopaOutputDTO
    extends ParentOutput
{

    protected Long id;
    protected Integer accertamentoAnno;
    protected Integer accertamentoNumero;
    protected BigDecimal articolo;
    protected BigDecimal capitolo;
    protected String codiceVersamento;
    protected String datiSpecificiDiRiscossione;
    protected String descrizioneCodiceVersamento;
    protected String descrizioneDatiSpecifici;
    protected BigDecimal importoQuotaAggregazione;
    protected String macrotipo;
    protected BigDecimal numeroPagamentiAggregazione;
    protected String pdc;
    protected Integer progressivoFlussoSintetico;
    protected String tematica;
    @XmlElement(nillable = true)
    protected List<FlussoDettaglioPagopaOutputDTO> listFlussoDettaglioPagopaOutputDTO;
    protected FlussoOriginePagopaOutputDTO flussoOriginePagopaOutputDTO;

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
     * Recupera il valore della propriet accertamentoAnno.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAccertamentoAnno() {
        return accertamentoAnno;
    }

    /**
     * Imposta il valore della propriet accertamentoAnno.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAccertamentoAnno(Integer value) {
        this.accertamentoAnno = value;
    }

    /**
     * Recupera il valore della propriet accertamentoNumero.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAccertamentoNumero() {
        return accertamentoNumero;
    }

    /**
     * Imposta il valore della propriet accertamentoNumero.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAccertamentoNumero(Integer value) {
        this.accertamentoNumero = value;
    }

    /**
     * Recupera il valore della propriet articolo.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getArticolo() {
        return articolo;
    }

    /**
     * Imposta il valore della propriet articolo.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setArticolo(BigDecimal value) {
        this.articolo = value;
    }

    /**
     * Recupera il valore della propriet capitolo.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCapitolo() {
        return capitolo;
    }

    /**
     * Imposta il valore della propriet capitolo.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCapitolo(BigDecimal value) {
        this.capitolo = value;
    }

    /**
     * Recupera il valore della propriet codiceVersamento.
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
     * Imposta il valore della propriet codiceVersamento.
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
     * Recupera il valore della propriet datiSpecificiDiRiscossione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDatiSpecificiDiRiscossione() {
        return datiSpecificiDiRiscossione;
    }

    /**
     * Imposta il valore della propriet datiSpecificiDiRiscossione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDatiSpecificiDiRiscossione(String value) {
        this.datiSpecificiDiRiscossione = value;
    }

    /**
     * Recupera il valore della propriet descrizioneCodiceVersamento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizioneCodiceVersamento() {
        return descrizioneCodiceVersamento;
    }

    /**
     * Imposta il valore della propriet descrizioneCodiceVersamento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizioneCodiceVersamento(String value) {
        this.descrizioneCodiceVersamento = value;
    }

    /**
     * Recupera il valore della propriet descrizioneDatiSpecifici.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizioneDatiSpecifici() {
        return descrizioneDatiSpecifici;
    }

    /**
     * Imposta il valore della propriet descrizioneDatiSpecifici.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizioneDatiSpecifici(String value) {
        this.descrizioneDatiSpecifici = value;
    }

    /**
     * Recupera il valore della propriet importoQuotaAggregazione.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getImportoQuotaAggregazione() {
        return importoQuotaAggregazione;
    }

    /**
     * Imposta il valore della propriet importoQuotaAggregazione.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setImportoQuotaAggregazione(BigDecimal value) {
        this.importoQuotaAggregazione = value;
    }

    /**
     * Recupera il valore della propriet macrotipo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMacrotipo() {
        return macrotipo;
    }

    /**
     * Imposta il valore della propriet macrotipo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMacrotipo(String value) {
        this.macrotipo = value;
    }

    /**
     * Recupera il valore della propriet numeroPagamentiAggregazione.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getNumeroPagamentiAggregazione() {
        return numeroPagamentiAggregazione;
    }

    /**
     * Imposta il valore della propriet numeroPagamentiAggregazione.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setNumeroPagamentiAggregazione(BigDecimal value) {
        this.numeroPagamentiAggregazione = value;
    }

    /**
     * Recupera il valore della propriet pdc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPdc() {
        return pdc;
    }

    /**
     * Imposta il valore della propriet pdc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPdc(String value) {
        this.pdc = value;
    }

    /**
     * Recupera il valore della propriet progressivoFlussoSintetico.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getProgressivoFlussoSintetico() {
        return progressivoFlussoSintetico;
    }

    /**
     * Imposta il valore della propriet progressivoFlussoSintetico.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setProgressivoFlussoSintetico(Integer value) {
        this.progressivoFlussoSintetico = value;
    }

    /**
     * Recupera il valore della propriet tematica.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTematica() {
        return tematica;
    }

    /**
     * Imposta il valore della propriet tematica.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTematica(String value) {
        this.tematica = value;
    }

    /**
     * Gets the value of the listFlussoDettaglioPagopaOutputDTO property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the listFlussoDettaglioPagopaOutputDTO property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getListFlussoDettaglioPagopaOutputDTO().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FlussoDettaglioPagopaOutputDTO }
     * 
     * 
     */
    public List<FlussoDettaglioPagopaOutputDTO> getListFlussoDettaglioPagopaOutputDTO() {
        if (listFlussoDettaglioPagopaOutputDTO == null) {
            listFlussoDettaglioPagopaOutputDTO = new ArrayList<FlussoDettaglioPagopaOutputDTO>();
        }
        return this.listFlussoDettaglioPagopaOutputDTO;
    }

    /**
     * Recupera il valore della propriet flussoOriginePagopaOutputDTO.
     * 
     * @return
     *     possible object is
     *     {@link FlussoOriginePagopaOutputDTO }
     *     
     */
    public FlussoOriginePagopaOutputDTO getFlussoOriginePagopaOutputDTO() {
        return flussoOriginePagopaOutputDTO;
    }

    /**
     * Imposta il valore della propriet flussoOriginePagopaOutputDTO.
     * 
     * @param value
     *     allowed object is
     *     {@link FlussoOriginePagopaOutputDTO }
     *     
     */
    public void setFlussoOriginePagopaOutputDTO(FlussoOriginePagopaOutputDTO value) {
        this.flussoOriginePagopaOutputDTO = value;
    }

}
