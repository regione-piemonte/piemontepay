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
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dtoFlussoSintesi complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dtoFlussoSintesi">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="annoAccertamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codiceVersamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="datiSpecificiDiRiscossione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descrizioneVersamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="flussoOrigine" type="{http://epaymodric.interfacews.epaymodric.epay.csi.it/}dtoFlussoOrigine" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="identificativoFlusso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="importoQuotaAggregazione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="listaVersamenti" type="{http://epaymodric.interfacews.epaymodric.epay.csi.it/}dtoFlussoDettaglio" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="numeroAccertamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numeroVersamentoQuotaAggregazione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="provvisorioAnno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="provvisorioNumero" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtoFlussoSintesi", propOrder = {
    "annoAccertamento",
    "codiceVersamento",
    "datiSpecificiDiRiscossione",
    "descrizioneVersamento",
    "flussoOrigine",
    "id",
    "identificativoFlusso",
    "importoQuotaAggregazione",
    "listaVersamenti",
    "numeroAccertamento",
    "numeroVersamentoQuotaAggregazione",
    "provvisorioAnno",
    "provvisorioNumero"
})
public class DtoFlussoSintesi {

    protected String annoAccertamento;
    protected String codiceVersamento;
    protected String datiSpecificiDiRiscossione;
    protected String descrizioneVersamento;
    protected DtoFlussoOrigine flussoOrigine;
    protected String id;
    protected String identificativoFlusso;
    protected String importoQuotaAggregazione;
    @XmlElement(nillable = true)
    protected List<DtoFlussoDettaglio> listaVersamenti;
    protected String numeroAccertamento;
    protected String numeroVersamentoQuotaAggregazione;
    protected String provvisorioAnno;
    protected String provvisorioNumero;

    /**
     * Gets the value of the annoAccertamento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAnnoAccertamento() {
        return annoAccertamento;
    }

    /**
     * Sets the value of the annoAccertamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAnnoAccertamento(String value) {
        this.annoAccertamento = value;
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
     * Gets the value of the datiSpecificiDiRiscossione property.
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
     * Sets the value of the datiSpecificiDiRiscossione property.
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
     * Gets the value of the descrizioneVersamento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizioneVersamento() {
        return descrizioneVersamento;
    }

    /**
     * Sets the value of the descrizioneVersamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizioneVersamento(String value) {
        this.descrizioneVersamento = value;
    }

    /**
     * Gets the value of the flussoOrigine property.
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
     * Sets the value of the flussoOrigine property.
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
     * Gets the value of the id property.
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
     * Sets the value of the id property.
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
     * Gets the value of the importoQuotaAggregazione property.
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
     * Sets the value of the importoQuotaAggregazione property.
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
     * Gets the value of the numeroAccertamento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroAccertamento() {
        return numeroAccertamento;
    }

    /**
     * Sets the value of the numeroAccertamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroAccertamento(String value) {
        this.numeroAccertamento = value;
    }

    /**
     * Gets the value of the numeroVersamentoQuotaAggregazione property.
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
     * Sets the value of the numeroVersamentoQuotaAggregazione property.
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
     * Gets the value of the provvisorioAnno property.
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
     * Sets the value of the provvisorioAnno property.
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
     * Gets the value of the provvisorioNumero property.
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
     * Sets the value of the provvisorioNumero property.
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
