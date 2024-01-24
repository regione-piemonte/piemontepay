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
 * <p>Classe Java per getCodiceVersamentoOutputDto complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="getCodiceVersamentoOutputDto"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="bic" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="codice" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="codiceMacrotipo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="codiceModalitaIntegrazione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="codiceStatoAggiornamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="codiceTematica" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="codiceTipoPagamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="codiceVersamentoPadre" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}getCodiceVersamentoOutputDto" minOccurs="0"/&gt;
 *         &lt;element name="codiceVoceEntrata" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="codiciVersamentoCollegati" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}getCodiceVersamentoOutputDto" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="descrizione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="descrizioneErroreAggiornamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="descrizioneMacrotipo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="descrizioneStatoAggiornamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="descrizioneTematica" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="descrizioneTipoPagamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="descrizioneVoceEntrata" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="flagInvioFlussi" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="iban" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="idVoceEntrata" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getCodiceVersamentoOutputDto", propOrder = {
    "bic",
    "codice",
    "codiceMacrotipo",
    "codiceModalitaIntegrazione",
    "codiceStatoAggiornamento",
    "codiceTematica",
    "codiceTipoPagamento",
    "codiceVersamentoPadre",
    "codiceVoceEntrata",
    "codiciVersamentoCollegati",
    "descrizione",
    "descrizioneErroreAggiornamento",
    "descrizioneMacrotipo",
    "descrizioneStatoAggiornamento",
    "descrizioneTematica",
    "descrizioneTipoPagamento",
    "descrizioneVoceEntrata",
    "email",
    "flagInvioFlussi",
    "iban",
    "id",
    "idVoceEntrata"
})
public class GetCodiceVersamentoOutputDto {

    protected String bic;
    protected String codice;
    protected String codiceMacrotipo;
    protected String codiceModalitaIntegrazione;
    protected String codiceStatoAggiornamento;
    protected String codiceTematica;
    protected String codiceTipoPagamento;
    protected GetCodiceVersamentoOutputDto codiceVersamentoPadre;
    protected String codiceVoceEntrata;
    @XmlElement(nillable = true)
    protected List<GetCodiceVersamentoOutputDto> codiciVersamentoCollegati;
    protected String descrizione;
    protected String descrizioneErroreAggiornamento;
    protected String descrizioneMacrotipo;
    protected String descrizioneStatoAggiornamento;
    protected String descrizioneTematica;
    protected String descrizioneTipoPagamento;
    protected String descrizioneVoceEntrata;
    protected String email;
    protected Boolean flagInvioFlussi;
    protected String iban;
    protected Long id;
    protected Long idVoceEntrata;

    /**
     * Recupera il valore della propriet bic.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBic() {
        return bic;
    }

    /**
     * Imposta il valore della propriet bic.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBic(String value) {
        this.bic = value;
    }

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
     * Recupera il valore della propriet codiceMacrotipo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceMacrotipo() {
        return codiceMacrotipo;
    }

    /**
     * Imposta il valore della propriet codiceMacrotipo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceMacrotipo(String value) {
        this.codiceMacrotipo = value;
    }

    /**
     * Recupera il valore della propriet codiceModalitaIntegrazione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceModalitaIntegrazione() {
        return codiceModalitaIntegrazione;
    }

    /**
     * Imposta il valore della propriet codiceModalitaIntegrazione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceModalitaIntegrazione(String value) {
        this.codiceModalitaIntegrazione = value;
    }

    /**
     * Recupera il valore della propriet codiceStatoAggiornamento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceStatoAggiornamento() {
        return codiceStatoAggiornamento;
    }

    /**
     * Imposta il valore della propriet codiceStatoAggiornamento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceStatoAggiornamento(String value) {
        this.codiceStatoAggiornamento = value;
    }

    /**
     * Recupera il valore della propriet codiceTematica.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceTematica() {
        return codiceTematica;
    }

    /**
     * Imposta il valore della propriet codiceTematica.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceTematica(String value) {
        this.codiceTematica = value;
    }

    /**
     * Recupera il valore della propriet codiceTipoPagamento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceTipoPagamento() {
        return codiceTipoPagamento;
    }

    /**
     * Imposta il valore della propriet codiceTipoPagamento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceTipoPagamento(String value) {
        this.codiceTipoPagamento = value;
    }

    /**
     * Recupera il valore della propriet codiceVersamentoPadre.
     * 
     * @return
     *     possible object is
     *     {@link GetCodiceVersamentoOutputDto }
     *     
     */
    public GetCodiceVersamentoOutputDto getCodiceVersamentoPadre() {
        return codiceVersamentoPadre;
    }

    /**
     * Imposta il valore della propriet codiceVersamentoPadre.
     * 
     * @param value
     *     allowed object is
     *     {@link GetCodiceVersamentoOutputDto }
     *     
     */
    public void setCodiceVersamentoPadre(GetCodiceVersamentoOutputDto value) {
        this.codiceVersamentoPadre = value;
    }

    /**
     * Recupera il valore della propriet codiceVoceEntrata.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceVoceEntrata() {
        return codiceVoceEntrata;
    }

    /**
     * Imposta il valore della propriet codiceVoceEntrata.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceVoceEntrata(String value) {
        this.codiceVoceEntrata = value;
    }

    /**
     * Gets the value of the codiciVersamentoCollegati property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the codiciVersamentoCollegati property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCodiciVersamentoCollegati().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GetCodiceVersamentoOutputDto }
     * 
     * 
     */
    public List<GetCodiceVersamentoOutputDto> getCodiciVersamentoCollegati() {
        if (codiciVersamentoCollegati == null) {
            codiciVersamentoCollegati = new ArrayList<GetCodiceVersamentoOutputDto>();
        }
        return this.codiciVersamentoCollegati;
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
     * Recupera il valore della propriet descrizioneErroreAggiornamento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizioneErroreAggiornamento() {
        return descrizioneErroreAggiornamento;
    }

    /**
     * Imposta il valore della propriet descrizioneErroreAggiornamento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizioneErroreAggiornamento(String value) {
        this.descrizioneErroreAggiornamento = value;
    }

    /**
     * Recupera il valore della propriet descrizioneMacrotipo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizioneMacrotipo() {
        return descrizioneMacrotipo;
    }

    /**
     * Imposta il valore della propriet descrizioneMacrotipo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizioneMacrotipo(String value) {
        this.descrizioneMacrotipo = value;
    }

    /**
     * Recupera il valore della propriet descrizioneStatoAggiornamento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizioneStatoAggiornamento() {
        return descrizioneStatoAggiornamento;
    }

    /**
     * Imposta il valore della propriet descrizioneStatoAggiornamento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizioneStatoAggiornamento(String value) {
        this.descrizioneStatoAggiornamento = value;
    }

    /**
     * Recupera il valore della propriet descrizioneTematica.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizioneTematica() {
        return descrizioneTematica;
    }

    /**
     * Imposta il valore della propriet descrizioneTematica.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizioneTematica(String value) {
        this.descrizioneTematica = value;
    }

    /**
     * Recupera il valore della propriet descrizioneTipoPagamento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizioneTipoPagamento() {
        return descrizioneTipoPagamento;
    }

    /**
     * Imposta il valore della propriet descrizioneTipoPagamento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizioneTipoPagamento(String value) {
        this.descrizioneTipoPagamento = value;
    }

    /**
     * Recupera il valore della propriet descrizioneVoceEntrata.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizioneVoceEntrata() {
        return descrizioneVoceEntrata;
    }

    /**
     * Imposta il valore della propriet descrizioneVoceEntrata.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizioneVoceEntrata(String value) {
        this.descrizioneVoceEntrata = value;
    }

    /**
     * Recupera il valore della propriet email.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Imposta il valore della propriet email.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Recupera il valore della propriet flagInvioFlussi.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isFlagInvioFlussi() {
        return flagInvioFlussi;
    }

    /**
     * Imposta il valore della propriet flagInvioFlussi.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setFlagInvioFlussi(Boolean value) {
        this.flagInvioFlussi = value;
    }

    /**
     * Recupera il valore della propriet iban.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIban() {
        return iban;
    }

    /**
     * Imposta il valore della propriet iban.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIban(String value) {
        this.iban = value;
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
     * Recupera il valore della propriet idVoceEntrata.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdVoceEntrata() {
        return idVoceEntrata;
    }

    /**
     * Imposta il valore della propriet idVoceEntrata.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdVoceEntrata(Long value) {
        this.idVoceEntrata = value;
    }

}
