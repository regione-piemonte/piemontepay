/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysimweb.integration.stubs.epaypacatalogsrv;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per getProfilazioneUtenteCorrenteTematicaOutputDto complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="getProfilazioneUtenteCorrenteTematicaOutputDto"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="codice" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="codiciVersamento" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}getProfilazioneUtenteCorrenteCodiceVersamentoOutputDto" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="descrizione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="flagVisibilitaTotale" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getProfilazioneUtenteCorrenteTematicaOutputDto", propOrder = {
    "codice",
    "codiciVersamento",
    "descrizione",
    "flagVisibilitaTotale",
    "id"
})
public class GetProfilazioneUtenteCorrenteTematicaOutputDto {

    protected String codice;
    @XmlElement(nillable = true)
    protected List<GetProfilazioneUtenteCorrenteCodiceVersamentoOutputDto> codiciVersamento;
    protected String descrizione;
    protected Boolean flagVisibilitaTotale;
    protected Long id;

    /**
     * Recupera il valore della propriet codice.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodice() {
        return codice;
    }

    /**
     * Imposta il valore della propriet codice.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodice(String value) {
        this.codice = value;
    }

    /**
     * Gets the value of the codiciVersamento property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the codiciVersamento property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCodiciVersamento().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GetProfilazioneUtenteCorrenteCodiceVersamentoOutputDto }
     * 
     * 
     */
    public List<GetProfilazioneUtenteCorrenteCodiceVersamentoOutputDto> getCodiciVersamento() {
        if (codiciVersamento == null) {
            codiciVersamento = new ArrayList<GetProfilazioneUtenteCorrenteCodiceVersamentoOutputDto>();
        }
        return this.codiciVersamento;
    }

    /**
     * Recupera il valore della propriet descrizione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizione() {
        return descrizione;
    }

    /**
     * Imposta il valore della propriet descrizione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizione(String value) {
        this.descrizione = value;
    }

    /**
     * Recupera il valore della propriet flagVisibilitaTotale.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isFlagVisibilitaTotale() {
        return flagVisibilitaTotale;
    }

    /**
     * Imposta il valore della propriet flagVisibilitaTotale.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setFlagVisibilitaTotale(Boolean value) {
        this.flagVisibilitaTotale = value;
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

}
