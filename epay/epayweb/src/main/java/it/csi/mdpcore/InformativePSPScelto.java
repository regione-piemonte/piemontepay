/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdpcore;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per informativePSPScelto complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="informativePSPScelto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="condizioniEconomicheMassime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descrizioneServizio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="disponibilitaServizio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="gatewayId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="identificativoCanale" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="identificativoFlusso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="identificativoIntermediario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="identificativoPSP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idinformativapsp" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="modelloPagamento" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="ordinamento" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="origine" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="paymentModeId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="priorita" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="ragioneSociale" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="statoinserimento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stornoPagamento" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="tipoVersamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="urlInformazioniCanale" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="urlInformazioniPSP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "informativePSPScelto", propOrder = {
    "condizioniEconomicheMassime",
    "descrizioneServizio",
    "disponibilitaServizio",
    "gatewayId",
    "identificativoCanale",
    "identificativoFlusso",
    "identificativoIntermediario",
    "identificativoPSP",
    "idinformativapsp",
    "modelloPagamento",
    "ordinamento",
    "origine",
    "paymentModeId",
    "priorita",
    "ragioneSociale",
    "statoinserimento",
    "stornoPagamento",
    "tipoVersamento",
    "urlInformazioniCanale",
    "urlInformazioniPSP"
})
public class InformativePSPScelto {

    protected String condizioniEconomicheMassime;
    protected String descrizioneServizio;
    protected String disponibilitaServizio;
    protected String gatewayId;
    protected String identificativoCanale;
    protected String identificativoFlusso;
    protected String identificativoIntermediario;
    protected String identificativoPSP;
    protected Integer idinformativapsp;
    protected Integer modelloPagamento;
    protected Integer ordinamento;
    protected String origine;
    protected String paymentModeId;
    protected Integer priorita;
    protected String ragioneSociale;
    protected String statoinserimento;
    protected Integer stornoPagamento;
    protected String tipoVersamento;
    protected String urlInformazioniCanale;
    protected String urlInformazioniPSP;

    /**
     * Recupera il valore della proprieta' condizioniEconomicheMassime.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCondizioniEconomicheMassime() {
        return condizioniEconomicheMassime;
    }

    /**
     * Imposta il valore della proprieta' condizioniEconomicheMassime.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCondizioniEconomicheMassime(String value) {
        this.condizioniEconomicheMassime = value;
    }

    /**
     * Recupera il valore della proprieta' descrizioneServizio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizioneServizio() {
        return descrizioneServizio;
    }

    /**
     * Imposta il valore della proprieta' descrizioneServizio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizioneServizio(String value) {
        this.descrizioneServizio = value;
    }

    /**
     * Recupera il valore della proprieta' disponibilitaServizio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDisponibilitaServizio() {
        return disponibilitaServizio;
    }

    /**
     * Imposta il valore della proprieta' disponibilitaServizio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDisponibilitaServizio(String value) {
        this.disponibilitaServizio = value;
    }

    /**
     * Recupera il valore della proprieta' gatewayId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGatewayId() {
        return gatewayId;
    }

    /**
     * Imposta il valore della proprieta' gatewayId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGatewayId(String value) {
        this.gatewayId = value;
    }

    /**
     * Recupera il valore della proprieta' identificativoCanale.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentificativoCanale() {
        return identificativoCanale;
    }

    /**
     * Imposta il valore della proprieta' identificativoCanale.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentificativoCanale(String value) {
        this.identificativoCanale = value;
    }

    /**
     * Recupera il valore della proprieta' identificativoFlusso.
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
     * Imposta il valore della proprieta' identificativoFlusso.
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
     * Recupera il valore della proprieta' identificativoIntermediario.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentificativoIntermediario() {
        return identificativoIntermediario;
    }

    /**
     * Imposta il valore della proprieta' identificativoIntermediario.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentificativoIntermediario(String value) {
        this.identificativoIntermediario = value;
    }

    /**
     * Recupera il valore della proprieta' identificativoPSP.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentificativoPSP() {
        return identificativoPSP;
    }

    /**
     * Imposta il valore della proprieta' identificativoPSP.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentificativoPSP(String value) {
        this.identificativoPSP = value;
    }

    /**
     * Recupera il valore della proprieta' idinformativapsp.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIdinformativapsp() {
        return idinformativapsp;
    }

    /**
     * Imposta il valore della proprieta' idinformativapsp.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIdinformativapsp(Integer value) {
        this.idinformativapsp = value;
    }

    /**
     * Recupera il valore della proprieta' modelloPagamento.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getModelloPagamento() {
        return modelloPagamento;
    }

    /**
     * Imposta il valore della proprieta' modelloPagamento.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setModelloPagamento(Integer value) {
        this.modelloPagamento = value;
    }

    /**
     * Recupera il valore della proprieta' ordinamento.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getOrdinamento() {
        return ordinamento;
    }

    /**
     * Imposta il valore della proprieta' ordinamento.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setOrdinamento(Integer value) {
        this.ordinamento = value;
    }

    /**
     * Recupera il valore della proprieta' origine.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrigine() {
        return origine;
    }

    /**
     * Imposta il valore della proprieta' origine.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrigine(String value) {
        this.origine = value;
    }

    /**
     * Recupera il valore della proprieta' paymentModeId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentModeId() {
        return paymentModeId;
    }

    /**
     * Imposta il valore della proprieta' paymentModeId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentModeId(String value) {
        this.paymentModeId = value;
    }

    /**
     * Recupera il valore della proprieta' priorita.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPriorita() {
        return priorita;
    }

    /**
     * Imposta il valore della proprieta' priorita.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPriorita(Integer value) {
        this.priorita = value;
    }

    /**
     * Recupera il valore della proprieta' ragioneSociale.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRagioneSociale() {
        return ragioneSociale;
    }

    /**
     * Imposta il valore della proprieta' ragioneSociale.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRagioneSociale(String value) {
        this.ragioneSociale = value;
    }

    /**
     * Recupera il valore della proprieta' statoinserimento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatoinserimento() {
        return statoinserimento;
    }

    /**
     * Imposta il valore della proprieta' statoinserimento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatoinserimento(String value) {
        this.statoinserimento = value;
    }

    /**
     * Recupera il valore della proprieta' stornoPagamento.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getStornoPagamento() {
        return stornoPagamento;
    }

    /**
     * Imposta il valore della proprieta' stornoPagamento.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setStornoPagamento(Integer value) {
        this.stornoPagamento = value;
    }

    /**
     * Recupera il valore della proprieta' tipoVersamento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoVersamento() {
        return tipoVersamento;
    }

    /**
     * Imposta il valore della proprieta' tipoVersamento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoVersamento(String value) {
        this.tipoVersamento = value;
    }

    /**
     * Recupera il valore della proprieta' urlInformazioniCanale.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrlInformazioniCanale() {
        return urlInformazioniCanale;
    }

    /**
     * Imposta il valore della proprieta' urlInformazioniCanale.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrlInformazioniCanale(String value) {
        this.urlInformazioniCanale = value;
    }

    /**
     * Recupera il valore della proprieta' urlInformazioniPSP.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrlInformazioniPSP() {
        return urlInformazioniPSP;
    }

    /**
     * Imposta il valore della proprieta' urlInformazioniPSP.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrlInformazioniPSP(String value) {
        this.urlInformazioniPSP = value;
    }

}
