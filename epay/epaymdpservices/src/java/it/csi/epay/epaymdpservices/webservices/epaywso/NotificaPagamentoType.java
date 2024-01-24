/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymdpservices.webservices.epaywso;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java per NotificaPagamentoType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="NotificaPagamentoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IdPosizioneDebitoria" type="{http://www.csi.it/epay/epaywso/types}String50Type" minOccurs="0"/>
 *         &lt;element name="AnnoDiRiferimento" type="{http://www.csi.it/epay/epaywso/types}AnnoType" minOccurs="0"/>
 *         &lt;element name="IUV" type="{http://www.csi.it/epay/epaywso/types}String35Type"/>
 *         &lt;element name="ImportoPagato" type="{http://www.csi.it/epay/epaywso/types}ImportoType"/>
 *         &lt;element name="DataScadenza" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="DescrizioneCausaleVersamento" type="{http://www.csi.it/epay/epaywso/types}String140Type" minOccurs="0"/>
 *         &lt;element name="DataEsitoPagamento" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="SoggettoDebitore" type="{http://www.csi.it/epay/epaywso/types}SoggettoType"/>
 *         &lt;element name="SoggettoVersante" type="{http://www.csi.it/epay/epaywso/types}SoggettoType" minOccurs="0"/>
 *         &lt;element name="DatiTransazionePSP" type="{http://www.csi.it/epay/epaywso/epaywso2enti/types}DatiTransazionePSPType"/>
 *         &lt;element name="DatiSpecificiRiscossione" type="{http://www.csi.it/epay/epaywso/types}String140Type" minOccurs="0"/>
 *         &lt;element name="Note" type="{http://www.csi.it/epay/epaywso/types}String2000Type" minOccurs="0"/>
 *         &lt;element name="CodiceAvviso" type="{http://www.csi.it/epay/epaywso/types}String35Type" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NotificaPagamentoType", propOrder = {
    "idPosizioneDebitoria",
    "annoDiRiferimento",
    "iuv",
    "importoPagato",
    "dataScadenza",
    "descrizioneCausaleVersamento",
    "dataEsitoPagamento",
    "soggettoDebitore",
    "soggettoVersante",
    "datiTransazionePSP",
    "datiSpecificiRiscossione",
    "note",
    "codiceAvviso"
})
public class NotificaPagamentoType {

    @XmlElement(name = "IdPosizioneDebitoria")
    protected String idPosizioneDebitoria;
    @XmlElement(name = "AnnoDiRiferimento")
    @XmlSchemaType(name = "integer")
    protected Integer annoDiRiferimento;
    @XmlElement(name = "IUV", required = true)
    protected String iuv;
    @XmlElement(name = "ImportoPagato", required = true)
    protected BigDecimal importoPagato;
    @XmlElement(name = "DataScadenza")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataScadenza;
    @XmlElement(name = "DescrizioneCausaleVersamento")
    protected String descrizioneCausaleVersamento;
    @XmlElement(name = "DataEsitoPagamento", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataEsitoPagamento;
    @XmlElement(name = "SoggettoDebitore", required = true)
    protected SoggettoType soggettoDebitore;
    @XmlElement(name = "SoggettoVersante")
    protected SoggettoType soggettoVersante;
    @XmlElement(name = "DatiTransazionePSP", required = true)
    protected DatiTransazionePSPType datiTransazionePSP;
    @XmlElement(name = "DatiSpecificiRiscossione")
    protected String datiSpecificiRiscossione;
    @XmlElement(name = "Note")
    protected String note;
    @XmlElement(name = "CodiceAvviso")
    protected String codiceAvviso;

    /**
     * Recupera il valore della propriet� idPosizioneDebitoria.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdPosizioneDebitoria() {
        return idPosizioneDebitoria;
    }

    /**
     * Imposta il valore della propriet� idPosizioneDebitoria.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdPosizioneDebitoria(String value) {
        this.idPosizioneDebitoria = value;
    }

    /**
     * Recupera il valore della propriet� annoDiRiferimento.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAnnoDiRiferimento() {
        return annoDiRiferimento;
    }

    /**
     * Imposta il valore della propriet� annoDiRiferimento.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAnnoDiRiferimento(Integer value) {
        this.annoDiRiferimento = value;
    }

    /**
     * Recupera il valore della propriet� iuv.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIUV() {
        return iuv;
    }

    /**
     * Imposta il valore della propriet� iuv.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIUV(String value) {
        this.iuv = value;
    }

    /**
     * Recupera il valore della propriet� importoPagato.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getImportoPagato() {
        return importoPagato;
    }

    /**
     * Imposta il valore della propriet� importoPagato.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setImportoPagato(BigDecimal value) {
        this.importoPagato = value;
    }

    /**
     * Recupera il valore della propriet� dataScadenza.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataScadenza() {
        return dataScadenza;
    }

    /**
     * Imposta il valore della propriet� dataScadenza.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataScadenza(XMLGregorianCalendar value) {
        this.dataScadenza = value;
    }

    /**
     * Recupera il valore della propriet� descrizioneCausaleVersamento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizioneCausaleVersamento() {
        return descrizioneCausaleVersamento;
    }

    /**
     * Imposta il valore della propriet� descrizioneCausaleVersamento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizioneCausaleVersamento(String value) {
        this.descrizioneCausaleVersamento = value;
    }

    /**
     * Recupera il valore della propriet� dataEsitoPagamento.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataEsitoPagamento() {
        return dataEsitoPagamento;
    }

    /**
     * Imposta il valore della propriet� dataEsitoPagamento.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataEsitoPagamento(XMLGregorianCalendar value) {
        this.dataEsitoPagamento = value;
    }

    /**
     * Recupera il valore della propriet� soggettoDebitore.
     * 
     * @return
     *     possible object is
     *     {@link SoggettoType }
     *     
     */
    public SoggettoType getSoggettoDebitore() {
        return soggettoDebitore;
    }

    /**
     * Imposta il valore della propriet� soggettoDebitore.
     * 
     * @param value
     *     allowed object is
     *     {@link SoggettoType }
     *     
     */
    public void setSoggettoDebitore(SoggettoType value) {
        this.soggettoDebitore = value;
    }

    /**
     * Recupera il valore della propriet� soggettoVersante.
     * 
     * @return
     *     possible object is
     *     {@link SoggettoType }
     *     
     */
    public SoggettoType getSoggettoVersante() {
        return soggettoVersante;
    }

    /**
     * Imposta il valore della propriet� soggettoVersante.
     * 
     * @param value
     *     allowed object is
     *     {@link SoggettoType }
     *     
     */
    public void setSoggettoVersante(SoggettoType value) {
        this.soggettoVersante = value;
    }

    /**
     * Recupera il valore della propriet� datiTransazionePSP.
     * 
     * @return
     *     possible object is
     *     {@link DatiTransazionePSPType }
     *     
     */
    public DatiTransazionePSPType getDatiTransazionePSP() {
        return datiTransazionePSP;
    }

    /**
     * Imposta il valore della propriet� datiTransazionePSP.
     * 
     * @param value
     *     allowed object is
     *     {@link DatiTransazionePSPType }
     *     
     */
    public void setDatiTransazionePSP(DatiTransazionePSPType value) {
        this.datiTransazionePSP = value;
    }

    /**
     * Recupera il valore della propriet� datiSpecificiRiscossione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDatiSpecificiRiscossione() {
        return datiSpecificiRiscossione;
    }

    /**
     * Imposta il valore della propriet� datiSpecificiRiscossione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDatiSpecificiRiscossione(String value) {
        this.datiSpecificiRiscossione = value;
    }

    /**
     * Recupera il valore della propriet� note.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNote() {
        return note;
    }

    /**
     * Imposta il valore della propriet� note.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNote(String value) {
        this.note = value;
    }

    /**
     * Recupera il valore della propriet� codiceAvviso.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceAvviso() {
        return codiceAvviso;
    }

    /**
     * Imposta il valore della propriet� codiceAvviso.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceAvviso(String value) {
        this.codiceAvviso = value;
    }

}
