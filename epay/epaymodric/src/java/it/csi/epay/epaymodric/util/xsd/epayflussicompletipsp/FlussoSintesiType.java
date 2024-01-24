/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.util.xsd.epayflussicompletipsp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FlussoSintesiType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FlussoSintesiType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IdFlusso" type="{http://www.csi.it/epay/epaywso/types}String35Type"/>
 *         &lt;element name="CodiceVersamento" type="{http://www.csi.it/epay/epaywso/types}CodiceVersamentoType"/>
 *         &lt;element name="DescrizioneCodiceVersamento" type="{http://www.csi.it/epay/epaywso/types}String500Type" minOccurs="0"/>
 *         &lt;element name="DatiSpecificiDiRiscossione" type="{http://www.csi.it/epay/epaywso/types}String70Type"/>
 *         &lt;element name="ImportoQuotaAggregazione" type="{http://www.csi.it/epay/epaywso/types}ImportoSignedType"/>
 *         &lt;element name="NroPagamentiAggregazione" type="{http://www.csi.it/epay/epaywso/types}Numero6CifreType"/>
 *         &lt;element name="Capitolo" type="{http://www.csi.it/epay/epaywso/types}String200Type" minOccurs="0"/>
 *         &lt;element name="Articolo" type="{http://www.csi.it/epay/epaywso/types}String200Type" minOccurs="0"/>
 *         &lt;element name="PdC" type="{http://www.csi.it/epay/epaywso/types}String200Type" minOccurs="0"/>
 *         &lt;element name="ProgressivoFlussoSintetico" type="{http://www.csi.it/epay/epaywso/types}Numero6CifreType" minOccurs="0"/>
 *         &lt;element name="AccertamentoAnno" type="{http://www.csi.it/epay/epaywso/types}AnnoType" minOccurs="0"/>
 *         &lt;element name="AccertamentoNumero" type="{http://www.csi.it/epay/epaywso/types}Numero6CifreType" minOccurs="0"/>
 *         &lt;element name="RigheDettaglio" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="SingolaRigaDettaglio" type="{http://www.csi.it/epay/epaywso/FlussoCompleto}FlussoDettaglioType" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "FlussoSintesiType", propOrder = {
    "idFlusso",
    "codiceVersamento",
    "descrizioneCodiceVersamento",
    "datiSpecificiDiRiscossione",
    "importoQuotaAggregazione",
    "nroPagamentiAggregazione",
    "capitolo",
    "articolo",
    "pdC",
    "progressivoFlussoSintetico",
    "accertamentoAnno",
    "accertamentoNumero",
    "righeDettaglio"
})
public class FlussoSintesiType {

    @XmlElement(name = "IdFlusso", required = true)
    protected String idFlusso;
    @XmlElement(name = "CodiceVersamento", required = true)
    protected String codiceVersamento;
    @XmlElement(name = "DescrizioneCodiceVersamento")
    protected String descrizioneCodiceVersamento;
    @XmlElement(name = "DatiSpecificiDiRiscossione", required = true)
    protected String datiSpecificiDiRiscossione;
    @XmlElement(name = "ImportoQuotaAggregazione", required = true)
    protected BigDecimal importoQuotaAggregazione;
    @XmlElement(name = "NroPagamentiAggregazione")
    protected int nroPagamentiAggregazione;
    @XmlElement(name = "Capitolo")
    protected String capitolo;
    @XmlElement(name = "Articolo")
    protected String articolo;
    @XmlElement(name = "PdC")
    protected String pdC;
    @XmlElement(name = "ProgressivoFlussoSintetico")
    protected Integer progressivoFlussoSintetico;
    @XmlElement(name = "AccertamentoAnno")
    protected Integer accertamentoAnno;
    @XmlElement(name = "AccertamentoNumero")
    protected Integer accertamentoNumero;
    @XmlElement(name = "RigheDettaglio")
    protected FlussoSintesiType.RigheDettaglio righeDettaglio;

    /**
     * Gets the value of the idFlusso property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdFlusso() {
        return idFlusso;
    }

    /**
     * Sets the value of the idFlusso property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdFlusso(String value) {
        this.idFlusso = value;
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
     * Gets the value of the descrizioneCodiceVersamento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizioneCodiceVersamento() {
        return descrizioneCodiceVersamento;
    }

    /**
     * Sets the value of the descrizioneCodiceVersamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizioneCodiceVersamento(String value) {
        this.descrizioneCodiceVersamento = value;
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
     * Gets the value of the importoQuotaAggregazione property.
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
     * Sets the value of the importoQuotaAggregazione property.
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
     * Gets the value of the nroPagamentiAggregazione property.
     * 
     */
    public int getNroPagamentiAggregazione() {
        return nroPagamentiAggregazione;
    }

    /**
     * Sets the value of the nroPagamentiAggregazione property.
     * 
     */
    public void setNroPagamentiAggregazione(int value) {
        this.nroPagamentiAggregazione = value;
    }

    /**
     * Gets the value of the capitolo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCapitolo() {
        return capitolo;
    }

    /**
     * Sets the value of the capitolo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCapitolo(String value) {
        this.capitolo = value;
    }

    /**
     * Gets the value of the articolo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArticolo() {
        return articolo;
    }

    /**
     * Sets the value of the articolo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArticolo(String value) {
        this.articolo = value;
    }

    /**
     * Gets the value of the pdC property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPdC() {
        return pdC;
    }

    /**
     * Sets the value of the pdC property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPdC(String value) {
        this.pdC = value;
    }

    /**
     * Gets the value of the progressivoFlussoSintetico property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getProgressivoFlussoSintetico() {
        return progressivoFlussoSintetico;
    }

    /**
     * Sets the value of the progressivoFlussoSintetico property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setProgressivoFlussoSintetico(Integer value) {
        this.progressivoFlussoSintetico = value;
    }

    /**
     * Gets the value of the accertamentoAnno property.
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
     * Sets the value of the accertamentoAnno property.
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
     * Gets the value of the accertamentoNumero property.
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
     * Sets the value of the accertamentoNumero property.
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
     * Gets the value of the righeDettaglio property.
     * 
     * @return
     *     possible object is
     *     {@link FlussoSintesiType.RigheDettaglio }
     *     
     */
    public FlussoSintesiType.RigheDettaglio getRigheDettaglio() {
        return righeDettaglio;
    }

    /**
     * Sets the value of the righeDettaglio property.
     * 
     * @param value
     *     allowed object is
     *     {@link FlussoSintesiType.RigheDettaglio }
     *     
     */
    public void setRigheDettaglio(FlussoSintesiType.RigheDettaglio value) {
        this.righeDettaglio = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="SingolaRigaDettaglio" type="{http://www.csi.it/epay/epaywso/FlussoCompleto}FlussoDettaglioType" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "singolaRigaDettaglio"
    })
    public static class RigheDettaglio {

        @XmlElement(name = "SingolaRigaDettaglio")
        protected List<FlussoDettaglioType> singolaRigaDettaglio;

        /**
         * Gets the value of the singolaRigaDettaglio property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the singolaRigaDettaglio property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getSingolaRigaDettaglio().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link FlussoDettaglioType }
         * 
         * 
         */
        public List<FlussoDettaglioType> getSingolaRigaDettaglio() {
            if (singolaRigaDettaglio == null) {
                singolaRigaDettaglio = new ArrayList<FlussoDettaglioType>();
            }
            return this.singolaRigaDettaglio;
        }

    }

}
