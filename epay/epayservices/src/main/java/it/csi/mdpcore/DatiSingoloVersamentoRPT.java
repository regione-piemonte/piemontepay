/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdpcore;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per DatiSingoloVersamentoRPT complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="DatiSingoloVersamentoRPT">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="importoSingoloVersamento" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="commissioneCaricoPA" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="credenzialiPagatore" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="causaleVersamento" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="datiSpecificiRiscossione" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="datiMarcaBolloDigitale" type="{http://interfacecxf.core.mdp.csi.it/}DatiMarcaBolloDigitale" minOccurs="0"/>
 *         &lt;element name="datiAccertamento" type="{http://interfacecxf.core.mdp.csi.it/}DatiAccertamentoRPT" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DatiSingoloVersamentoRPT", propOrder = {
    "importoSingoloVersamento",
    "commissioneCaricoPA",
    "credenzialiPagatore",
    "causaleVersamento",
    "datiSpecificiRiscossione",
    "datiMarcaBolloDigitale",
    "datiAccertamento"
})
public class DatiSingoloVersamentoRPT {

    @XmlElement(required = true)
    protected BigDecimal importoSingoloVersamento;
    protected BigDecimal commissioneCaricoPA;
    protected String credenzialiPagatore;
    @XmlElement(required = true)
    protected String causaleVersamento;
    @XmlElement(required = true)
    protected String datiSpecificiRiscossione;
    protected DatiMarcaBolloDigitale datiMarcaBolloDigitale;
    protected DatiAccertamentoRPT datiAccertamento;

    /**
     * Recupera il valore della proprieta' importoSingoloVersamento.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getImportoSingoloVersamento() {
        return importoSingoloVersamento;
    }

    /**
     * Imposta il valore della proprieta' importoSingoloVersamento.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setImportoSingoloVersamento(BigDecimal value) {
        this.importoSingoloVersamento = value;
    }

    /**
     * Recupera il valore della proprieta' commissioneCaricoPA.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCommissioneCaricoPA() {
        return commissioneCaricoPA;
    }

    /**
     * Imposta il valore della proprieta' commissioneCaricoPA.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCommissioneCaricoPA(BigDecimal value) {
        this.commissioneCaricoPA = value;
    }

    /**
     * Recupera il valore della proprieta' credenzialiPagatore.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCredenzialiPagatore() {
        return credenzialiPagatore;
    }

    /**
     * Imposta il valore della proprieta' credenzialiPagatore.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCredenzialiPagatore(String value) {
        this.credenzialiPagatore = value;
    }

    /**
     * Recupera il valore della proprieta' causaleVersamento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCausaleVersamento() {
        return causaleVersamento;
    }

    /**
     * Imposta il valore della proprieta' causaleVersamento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCausaleVersamento(String value) {
        this.causaleVersamento = value;
    }

    /**
     * Recupera il valore della proprieta' datiSpecificiRiscossione.
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
     * Imposta il valore della proprieta' datiSpecificiRiscossione.
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
     * Recupera il valore della proprieta' datiMarcaBolloDigitale.
     * 
     * @return
     *     possible object is
     *     {@link DatiMarcaBolloDigitale }
     *     
     */
    public DatiMarcaBolloDigitale getDatiMarcaBolloDigitale() {
        return datiMarcaBolloDigitale;
    }

    /**
     * Imposta il valore della proprieta' datiMarcaBolloDigitale.
     * 
     * @param value
     *     allowed object is
     *     {@link DatiMarcaBolloDigitale }
     *     
     */
    public void setDatiMarcaBolloDigitale(DatiMarcaBolloDigitale value) {
        this.datiMarcaBolloDigitale = value;
    }

    /**
     * Recupera il valore della proprieta' datiAccertamento.
     * 
     * @return
     *     possible object is
     *     {@link DatiAccertamentoRPT }
     *     
     */
    public DatiAccertamentoRPT getDatiAccertamento() {
        return datiAccertamento;
    }

    /**
     * Imposta il valore della proprieta' datiAccertamento.
     * 
     * @param value
     *     allowed object is
     *     {@link DatiAccertamentoRPT }
     *     
     */
    public void setDatiAccertamento(DatiAccertamentoRPT value) {
        this.datiAccertamento = value;
    }

}
