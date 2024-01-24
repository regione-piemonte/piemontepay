/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.util.xsd.trasmissioneflussi;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>
 * Java class for FlussoSintesiType complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="FlussoSintesiType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CodiceVersamento" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DescrizioneCodiceVersamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DatiSpecificiDiRiscossione" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DescrizioneDatiSpecifici" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ImportoQuotaAggregazione" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="NroPagamentiAggregazione" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="Capitolo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Articolo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PdC" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ProgressivoFlussoSintetico" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="AccertamentoAnno" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="AccertamentoNro" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="Macrotipo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Tematica" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RigheDettaglio" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="SingolaRigaDettaglio" type="{http://www.csi.it/epay/epaywso/riconciliazione-versamenti-psp/types}FlussoDettaglioType" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType ( name = "FlussoSintesiType", propOrder = {
    "codiceVersamento",
    "descrizioneCodiceVersamento",
    "datiSpecificiDiRiscossione",
    "descrizioneDatiSpecifici",
    "importoQuotaAggregazione",
    "nroPagamentiAggregazione",
    "capitolo",
    "articolo",
    "pdC",
    "progressivoFlussoSintetico",
    "accertamentoAnno",
    "accertamentoNro",
    "macrotipo",
    "tematica",
    "righeDettaglio"
})
public class FlussoSintesiType {

    @XmlElement(name = "CodiceVersamento", required = true)
    protected String codiceVersamento;
    @XmlElement(name = "DescrizioneCodiceVersamento")
    protected String descrizioneCodiceVersamento;
    @XmlElement(name = "DatiSpecificiDiRiscossione", required = true)
    protected String datiSpecificiDiRiscossione;

    @XmlElement ( name = "DescrizioneDatiSpecifici" )
    protected String descrizioneDatiSpecifici;
    @XmlElement(name = "ImportoQuotaAggregazione", required = true)
    protected BigDecimal importoQuotaAggregazione;

    @XmlElement ( name = "NroPagamentiAggregazione", required = true )
    protected BigInteger nroPagamentiAggregazione;
    @XmlElement(name = "Capitolo")
    protected String capitolo;
    @XmlElement(name = "Articolo")
    protected String articolo;
    @XmlElement(name = "PdC")
    protected String pdC;

    @XmlElement ( name = "ProgressivoFlussoSintetico", required = true, nillable = true )
    protected BigInteger progressivoFlussoSintetico;

    @XmlElement ( name = "AccertamentoAnno", required = true, nillable = true )
    protected BigInteger accertamentoAnno;

    @XmlElement ( name = "AccertamentoNro", required = true, nillable = true )
    protected BigInteger accertamentoNro;

    @XmlElement ( name = "Macrotipo" )
    protected String macrotipo;

    @XmlElement ( name = "Tematica" )
    protected String tematica;

    @XmlElement ( name = "RigheDettaglio" )
    protected FlussoSintesiType.RigheDettaglio righeDettaglio;

    /**
     * Gets the value of the codiceVersamento property.
     *
     * @return possible object is {@link String }
     * 
     */
    public String getCodiceVersamento () {
        return codiceVersamento;
    }

    /**
     * Sets the value of the codiceVersamento property.
     *
     * @param value allowed object is {@link String }
     * 
     */
    public void setCodiceVersamento ( String value ) {
        this.codiceVersamento = value;
    }

    /**
     * Gets the value of the descrizioneCodiceVersamento property.
     *
     * @return possible object is {@link String }
     * 
     */
    public String getDescrizioneCodiceVersamento () {
        return descrizioneCodiceVersamento;
    }

    /**
     * Sets the value of the descrizioneCodiceVersamento property.
     *
     * @param value allowed object is {@link String }
     * 
     */
    public void setDescrizioneCodiceVersamento ( String value ) {
        this.descrizioneCodiceVersamento = value;
    }

    /**
     * Gets the value of the datiSpecificiDiRiscossione property.
     *
     * @return possible object is {@link String }
     * 
     */
    public String getDatiSpecificiDiRiscossione () {
        return datiSpecificiDiRiscossione;
    }

    /**
     * Sets the value of the datiSpecificiDiRiscossione property.
     *
     * @param value allowed object is {@link String }
     * 
     */
    public void setDatiSpecificiDiRiscossione ( String value ) {
        this.datiSpecificiDiRiscossione = value;
    }

    /**
     * Gets the value of the descrizioneDatiSpecifici property.
     *
     * @return possible object is {@link String }
     * 
     */
    public String getDescrizioneDatiSpecifici () {
        return descrizioneDatiSpecifici;
    }

    /**
     * Sets the value of the descrizioneDatiSpecifici property.
     *
     * @param value allowed object is {@link String }
     * 
     */
    public void setDescrizioneDatiSpecifici ( String value ) {
        this.descrizioneDatiSpecifici = value;
    }

    /**
     * Gets the value of the importoQuotaAggregazione property.
     *
     * @return possible object is {@link BigDecimal }
     * 
     */
    public BigDecimal getImportoQuotaAggregazione() {
        return importoQuotaAggregazione;
    }

    /**
     * Sets the value of the importoQuotaAggregazione property.
     *
     * @param value allowed object is {@link BigDecimal }
     * 
     */
    public void setImportoQuotaAggregazione(BigDecimal value) {
        this.importoQuotaAggregazione = value;
    }

    /**
     * Gets the value of the nroPagamentiAggregazione property.
     *
     * @return possible object is {@link BigInteger }
     * 
     */
    public BigInteger getNroPagamentiAggregazione () {
        return nroPagamentiAggregazione;
    }

    /**
     * Sets the value of the nroPagamentiAggregazione property.
     *
     * @param value allowed object is {@link BigInteger }
     * 
     */
    public void setNroPagamentiAggregazione ( BigInteger value ) {
        this.nroPagamentiAggregazione = value;
    }

    /**
     * Gets the value of the capitolo property.
     *
     * @return possible object is {@link String }
     * 
     */
    public String getCapitolo() {
        return capitolo;
    }

    /**
     * Sets the value of the capitolo property.
     *
     * @param value allowed object is {@link String }
     * 
     */
    public void setCapitolo(String value) {
        this.capitolo = value;
    }

    /**
     * Gets the value of the articolo property.
     *
     * @return possible object is {@link String }
     * 
     */
    public String getArticolo() {
        return articolo;
    }

    /**
     * Sets the value of the articolo property.
     *
     * @param value allowed object is {@link String }
     * 
     */
    public void setArticolo(String value) {
        this.articolo = value;
    }

    /**
     * Gets the value of the pdC property.
     *
     * @return possible object is {@link String }
     * 
     */
    public String getPdC() {
        return pdC;
    }

    /**
     * Sets the value of the pdC property.
     *
     * @param value allowed object is {@link String }
     * 
     */
    public void setPdC(String value) {
        this.pdC = value;
    }

    /**
     * Gets the value of the progressivoFlussoSintetico property.
     *
     * @return possible object is {@link BigInteger }
     * 
     */
    public BigInteger getProgressivoFlussoSintetico () {
        return progressivoFlussoSintetico;
    }

    /**
     * Sets the value of the progressivoFlussoSintetico property.
     *
     * @param value allowed object is {@link BigInteger }
     * 
     */
    public void setProgressivoFlussoSintetico ( BigInteger value ) {
        this.progressivoFlussoSintetico = value;
    }

    /**
     * Gets the value of the accertamentoAnno property.
     *
     * @return possible object is {@link BigInteger }
     * 
     */
    public BigInteger getAccertamentoAnno () {
        return accertamentoAnno;
    }

    /**
     * Sets the value of the accertamentoAnno property.
     *
     * @param value allowed object is {@link BigInteger }
     * 
     */
    public void setAccertamentoAnno ( BigInteger value ) {
        this.accertamentoAnno = value;
    }

    /**
     * Gets the value of the accertamentoNro property.
     *
     * @return possible object is {@link BigInteger }
     * 
     */
    public BigInteger getAccertamentoNro () {
        return accertamentoNro;
    }

    /**
     * Sets the value of the accertamentoNro property.
     *
     * @param value allowed object is {@link BigInteger }
     * 
     */
    public void setAccertamentoNro ( BigInteger value ) {
        this.accertamentoNro = value;
    }

    /**
     * Gets the value of the macrotipo property.
     *
     * @return possible object is {@link String }
     * 
     */
    public String getMacrotipo () {
        return macrotipo;
    }

    /**
     * Sets the value of the macrotipo property.
     *
     * @param value allowed object is {@link String }
     * 
     */
    public void setMacrotipo ( String value ) {
        this.macrotipo = value;
    }

    /**
     * Gets the value of the tematica property.
     *
     * @return possible object is {@link String }
     * 
     */
    public String getTematica () {
        return tematica;
    }

    /**
     * Sets the value of the tematica property.
     *
     * @param value allowed object is {@link String }
     * 
     */
    public void setTematica ( String value ) {
        this.tematica = value;
    }

    /**
     * Gets the value of the righeDettaglio property.
     *
     * @return possible object is {@link FlussoSintesiType.RigheDettaglio }
     * 
     */
    public FlussoSintesiType.RigheDettaglio getRigheDettaglio () {
        return righeDettaglio;
    }

    /**
     * Sets the value of the righeDettaglio property.
     *
     * @param value allowed object is {@link FlussoSintesiType.RigheDettaglio }
     * 
     */
    public void setRigheDettaglio ( FlussoSintesiType.RigheDettaglio value ) {
        this.righeDettaglio = value;
    }

    /**
     * <p>
     * Java class for anonymous complex type.
     *
     * <p>
     * The following schema fragment specifies the expected content contained within this class.
     *
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="SingolaRigaDettaglio" type="{http://www.csi.it/epay/epaywso/riconciliazione-versamenti-psp/types}FlussoDettaglioType" maxOccurs="unbounded"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     *
     *
     */
    @XmlAccessorType ( XmlAccessType.FIELD )
    @XmlType ( name = "", propOrder = {
        "singolaRigaDettaglio"
    } )
    public static class RigheDettaglio {

        @XmlElement ( name = "SingolaRigaDettaglio", required = true )
        protected List<FlussoDettaglioType> singolaRigaDettaglio;

        /**
         * Gets the value of the singolaRigaDettaglio property.
         *
         * <p>
         * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present
         * inside the JAXB object. This is why there is not a <CODE>set</CODE> method for the singolaRigaDettaglio property.
         *
         * <p>
         * For example, to add a new item, do as follows:
         * 
         * <pre>
         * getSingolaRigaDettaglio ().add ( newItem );
         * </pre>
         *
         *
         * <p>
         * Objects of the following type(s) are allowed in the list {@link FlussoDettaglioType }
         *
         *
         */
        public List<FlussoDettaglioType> getSingolaRigaDettaglio () {
            if ( singolaRigaDettaglio == null ) {
                singolaRigaDettaglio = new ArrayList<FlussoDettaglioType> ();
            }
            return this.singolaRigaDettaglio;
        }

    }

}
