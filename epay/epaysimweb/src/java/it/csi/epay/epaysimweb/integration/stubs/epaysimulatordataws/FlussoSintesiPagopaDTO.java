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
 * <p>Classe Java per flussoSintesiPagopaDTO complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="flussoSintesiPagopaDTO"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://epaysim.interfacews.epaysim.epay.csi.it/}parentInput"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="accertamentoAnno" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="accertamentoNumero" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="articolo" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="capitolo" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="codiceVersamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="datiSpecificiDiRiscossione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="idIstitutoRicevente" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="importoQuotaAggregazione" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="numeroVersQuotaAggregazione" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="pianoDeiConti" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="provvisorioAnno" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="provvisorioNro" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="simTFlussoDettaglioPagopas" type="{http://epaysim.interfacews.epaysim.epay.csi.it/}flussoDettaglioPagopaDTO" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="simTFlussoOriginePagopa" type="{http://epaysim.interfacews.epaysim.epay.csi.it/}flussoOriginePagopaDTO" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "flussoSintesiPagopaDTO", propOrder = {
    "accertamentoAnno",
    "accertamentoNumero",
    "articolo",
    "capitolo",
    "codiceVersamento",
    "datiSpecificiDiRiscossione",
    "id",
    "idIstitutoRicevente",
    "importoQuotaAggregazione",
    "numeroVersQuotaAggregazione",
    "pianoDeiConti",
    "provvisorioAnno",
    "provvisorioNro",
    "simTFlussoDettaglioPagopas",
    "simTFlussoOriginePagopa"
})
public class FlussoSintesiPagopaDTO
    extends ParentInput
{

    protected Integer accertamentoAnno;
    protected Integer accertamentoNumero;
    protected BigDecimal articolo;
    protected BigDecimal capitolo;
    protected String codiceVersamento;
    protected String datiSpecificiDiRiscossione;
    protected Long id;
    protected Long idIstitutoRicevente;
    protected BigDecimal importoQuotaAggregazione;
    protected BigDecimal numeroVersQuotaAggregazione;
    protected String pianoDeiConti;
    protected Integer provvisorioAnno;
    protected Integer provvisorioNro;
    @XmlElement(nillable = true)
    protected List<FlussoDettaglioPagopaDTO> simTFlussoDettaglioPagopas;
    protected FlussoOriginePagopaDTO simTFlussoOriginePagopa;

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
     * Recupera il valore della propriet idIstitutoRicevente.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdIstitutoRicevente() {
        return idIstitutoRicevente;
    }

    /**
     * Imposta il valore della propriet idIstitutoRicevente.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdIstitutoRicevente(Long value) {
        this.idIstitutoRicevente = value;
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
     * Recupera il valore della propriet numeroVersQuotaAggregazione.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getNumeroVersQuotaAggregazione() {
        return numeroVersQuotaAggregazione;
    }

    /**
     * Imposta il valore della propriet numeroVersQuotaAggregazione.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setNumeroVersQuotaAggregazione(BigDecimal value) {
        this.numeroVersQuotaAggregazione = value;
    }

    /**
     * Recupera il valore della propriet pianoDeiConti.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPianoDeiConti() {
        return pianoDeiConti;
    }

    /**
     * Imposta il valore della propriet pianoDeiConti.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPianoDeiConti(String value) {
        this.pianoDeiConti = value;
    }

    /**
     * Recupera il valore della propriet provvisorioAnno.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getProvvisorioAnno() {
        return provvisorioAnno;
    }

    /**
     * Imposta il valore della propriet provvisorioAnno.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setProvvisorioAnno(Integer value) {
        this.provvisorioAnno = value;
    }

    /**
     * Recupera il valore della propriet provvisorioNro.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getProvvisorioNro() {
        return provvisorioNro;
    }

    /**
     * Imposta il valore della propriet provvisorioNro.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setProvvisorioNro(Integer value) {
        this.provvisorioNro = value;
    }

    /**
     * Gets the value of the simTFlussoDettaglioPagopas property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the simTFlussoDettaglioPagopas property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSimTFlussoDettaglioPagopas().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FlussoDettaglioPagopaDTO }
     * 
     * 
     */
    public List<FlussoDettaglioPagopaDTO> getSimTFlussoDettaglioPagopas() {
        if (simTFlussoDettaglioPagopas == null) {
            simTFlussoDettaglioPagopas = new ArrayList<FlussoDettaglioPagopaDTO>();
        }
        return this.simTFlussoDettaglioPagopas;
    }

    /**
     * Recupera il valore della propriet simTFlussoOriginePagopa.
     * 
     * @return
     *     possible object is
     *     {@link FlussoOriginePagopaDTO }
     *     
     */
    public FlussoOriginePagopaDTO getSimTFlussoOriginePagopa() {
        return simTFlussoOriginePagopa;
    }

    /**
     * Imposta il valore della propriet simTFlussoOriginePagopa.
     * 
     * @param value
     *     allowed object is
     *     {@link FlussoOriginePagopaDTO }
     *     
     */
    public void setSimTFlussoOriginePagopa(FlussoOriginePagopaDTO value) {
        this.simTFlussoOriginePagopa = value;
    }

}
