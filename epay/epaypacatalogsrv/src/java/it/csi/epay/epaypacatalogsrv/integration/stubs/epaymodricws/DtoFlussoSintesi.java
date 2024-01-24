/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.integration.stubs.epaymodricws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per dtoFlussoSintesi complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="dtoFlussoSintesi"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="codiceVersamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="datiSpecificiDiRiscossione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="flussoOrigine" type="{http://epaymodric.interfacews.epaymodric.epay.csi.it/}dtoFlussoOrigine" minOccurs="0"/&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="identificativoFlusso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="importoQuotaAggregazione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="listaVersamenti" type="{http://epaymodric.interfacews.epaymodric.epay.csi.it/}dtoFlussoDettaglio" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="numeroVersamentoQuotaAggregazione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="provvisorioAnno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="provvisorioNumero" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtoFlussoSintesi", propOrder = {
    "codiceVersamento",
    "datiSpecificiDiRiscossione",
    "flussoOrigine",
    "id",
    "identificativoFlusso",
    "importoQuotaAggregazione",
    "listaVersamenti",
    "numeroVersamentoQuotaAggregazione",
    "provvisorioAnno",
    "provvisorioNumero"
})
public class DtoFlussoSintesi {

    protected String codiceVersamento;
    protected String datiSpecificiDiRiscossione;
    protected DtoFlussoOrigine flussoOrigine;
    protected String id;
    protected String identificativoFlusso;
    protected String importoQuotaAggregazione;
    @XmlElement(nillable = true)
    protected List<DtoFlussoDettaglio> listaVersamenti;
    protected String numeroVersamentoQuotaAggregazione;
    protected String provvisorioAnno;
    protected String provvisorioNumero;

    /**
     * Recupera il valore della proprieta codiceVersamento.
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
     * Imposta il valore della proprieta codiceVersamento.
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
     * Recupera il valore della proprieta datiSpecificiDiRiscossione.
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
     * Imposta il valore della proprieta datiSpecificiDiRiscossione.
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
     * Recupera il valore della proprieta flussoOrigine.
     * 
     * @return
     *     possible object is
     *     {@link DtoFlussoOrigine }
     *     
     */
    public DtoFlussoOrigine getFlussoOrigine() {
        return flussoOrigine;
    }

    /**
     * Imposta il valore della proprieta flussoOrigine.
     * 
     * @param value
     *     allowed object is
     *     {@link DtoFlussoOrigine }
     *     
     */
    public void setFlussoOrigine(DtoFlussoOrigine value) {
        this.flussoOrigine = value;
    }

    /**
     * Recupera il valore della proprieta id.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Imposta il valore della proprieta id.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
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
     * Recupera il valore della proprieta importoQuotaAggregazione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImportoQuotaAggregazione() {
        return importoQuotaAggregazione;
    }

    /**
     * Imposta il valore della proprieta importoQuotaAggregazione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImportoQuotaAggregazione(String value) {
        this.importoQuotaAggregazione = value;
    }

    /**
     * Gets the value of the listaVersamenti property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the listaVersamenti property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getListaVersamenti().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DtoFlussoDettaglio }
     * 
     * 
     */
    public List<DtoFlussoDettaglio> getListaVersamenti() {
        if (listaVersamenti == null) {
            listaVersamenti = new ArrayList<DtoFlussoDettaglio>();
        }
        return this.listaVersamenti;
    }

    /**
     * Recupera il valore della proprieta numeroVersamentoQuotaAggregazione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroVersamentoQuotaAggregazione() {
        return numeroVersamentoQuotaAggregazione;
    }

    /**
     * Imposta il valore della proprieta numeroVersamentoQuotaAggregazione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroVersamentoQuotaAggregazione(String value) {
        this.numeroVersamentoQuotaAggregazione = value;
    }

    /**
     * Recupera il valore della proprieta provvisorioAnno.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProvvisorioAnno() {
        return provvisorioAnno;
    }

    /**
     * Imposta il valore della proprieta provvisorioAnno.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProvvisorioAnno(String value) {
        this.provvisorioAnno = value;
    }

    /**
     * Recupera il valore della proprieta provvisorioNumero.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProvvisorioNumero() {
        return provvisorioNumero;
    }

    /**
     * Imposta il valore della proprieta provvisorioNumero.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProvvisorioNumero(String value) {
        this.provvisorioNumero = value;
    }

}
