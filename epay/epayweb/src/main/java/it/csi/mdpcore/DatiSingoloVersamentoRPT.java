/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdpcore;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>
 * Java class for DatiSingoloVersamentoRPT complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
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
public class DatiSingoloVersamentoRPT implements Serializable{

    private static final long serialVersionUID = -1949736849832735320L;
    
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
     * Gets the value of the importoSingoloVersamento property.
     * 
     * @return possible object is {@link BigDecimal }
     * 
     */
    public BigDecimal getImportoSingoloVersamento() {
        return importoSingoloVersamento;
    }

    /**
     * Sets the value of the importoSingoloVersamento property.
     * 
     * @param value allowed object is {@link BigDecimal }
     * 
     */
    public void setImportoSingoloVersamento(BigDecimal value) {
        this.importoSingoloVersamento = value;
    }

    /**
     * Gets the value of the commissioneCaricoPA property.
     * 
     * @return possible object is {@link BigDecimal }
     * 
     */
    public BigDecimal getCommissioneCaricoPA() {
        return commissioneCaricoPA;
    }

    /**
     * Sets the value of the commissioneCaricoPA property.
     * 
     * @param value allowed object is {@link BigDecimal }
     * 
     */
    public void setCommissioneCaricoPA(BigDecimal value) {
        this.commissioneCaricoPA = value;
    }

    /**
     * Gets the value of the credenzialiPagatore property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getCredenzialiPagatore() {
        return credenzialiPagatore;
    }

    /**
     * Sets the value of the credenzialiPagatore property.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setCredenzialiPagatore(String value) {
        this.credenzialiPagatore = value;
    }

    /**
     * Gets the value of the causaleVersamento property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getCausaleVersamento() {
        return causaleVersamento;
    }

    /**
     * Sets the value of the causaleVersamento property.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setCausaleVersamento(String value) {
        this.causaleVersamento = value;
    }

    /**
     * Gets the value of the datiSpecificiRiscossione property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getDatiSpecificiRiscossione() {
        return datiSpecificiRiscossione;
    }

    /**
     * Sets the value of the datiSpecificiRiscossione property.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setDatiSpecificiRiscossione(String value) {
        this.datiSpecificiRiscossione = value;
    }

    /**
     * Gets the value of the datiMarcaBolloDigitale property.
     * 
     * @return possible object is {@link DatiMarcaBolloDigitale }
     * 
     */
    public DatiMarcaBolloDigitale getDatiMarcaBolloDigitale() {
        return datiMarcaBolloDigitale;
    }

    /**
     * Sets the value of the datiMarcaBolloDigitale property.
     * 
     * @param value allowed object is {@link DatiMarcaBolloDigitale }
     * 
     */
    public void setDatiMarcaBolloDigitale(DatiMarcaBolloDigitale value) {
        this.datiMarcaBolloDigitale = value;
    }

    /**
     * Gets the value of the datiAccertamento property.
     * 
     * @return possible object is {@link DatiAccertamentoRPT }
     * 
     */
    public DatiAccertamentoRPT getDatiAccertamento () {
        return datiAccertamento;
    }

    /**
     * Sets the value of the datiAccertamento property.
     * 
     * @param value allowed object is {@link DatiAccertamentoRPT }
     * 
     */
    public void setDatiAccertamento ( DatiAccertamentoRPT value ) {
        this.datiAccertamento = value;
    }

}
