/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

//
// Questo file  stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.3.0 
// Vedere <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Qualsiasi modifica a questo file andr persa durante la ricompilazione dello schema di origine. 
// Generato il: 2018.10.25 alle 10:35:00 AM CEST 
//


package it.csi.epay.epaymodric.util.xsd.epayriconciliazioneversamentipsp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per FlussoSintesiType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="FlussoSintesiType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="CodiceVersamento" type="{http://www.csi.it/epay/epaywso/types}CodiceVersamentoType"/&gt;
 *         &lt;element name="DescrizioneCodiceVersamento" type="{http://www.csi.it/epay/epaywso/types}String500Type" minOccurs="0"/&gt;
 *         &lt;element name="DatiSpecificiDiRiscossione" type="{http://www.csi.it/epay/epaywso/types}String70Type"/&gt;
 *         &lt;element name="DescrizioneDatiSpecifici" type="{http://www.csi.it/epay/epaywso/types}String500Type" minOccurs="0"/&gt;
 *         &lt;element name="Macrotipo" type="{http://www.csi.it/epay/epaywso/types}String200Type" minOccurs="0"/&gt;
 *         &lt;element name="Tematica" type="{http://www.csi.it/epay/epaywso/types}String200Type" minOccurs="0"/&gt;
 *         &lt;element name="ImportoQuotaAggregazione" type="{http://www.csi.it/epay/epaywso/types}ImportoSignedType"/&gt;
 *         &lt;element name="NroPagamentiAggregazione" type="{http://www.csi.it/epay/epaywso/types}Numero6CifreType"/&gt;
 *         &lt;element name="Capitolo" type="{http://www.csi.it/epay/epaywso/types}String50Type" minOccurs="0"/&gt;
 *         &lt;element name="Articolo" type="{http://www.csi.it/epay/epaywso/types}String200Type" minOccurs="0"/&gt;
 *         &lt;element name="PdC" type="{http://www.csi.it/epay/epaywso/types}String200Type" minOccurs="0"/&gt;
 *         &lt;element name="ProgressivoFlussoSintetico" type="{http://www.csi.it/epay/epaywso/types}Numero6CifreType"/&gt;
 *         &lt;element name="AccertamentoAnno" type="{http://www.csi.it/epay/epaywso/types}AnnoType"/&gt;
 *         &lt;element name="AccertamentoNro" type="{http://www.csi.it/epay/epaywso/types}Numero6CifreType"/&gt;
 *         &lt;element name="RigheDettaglio" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="SingolaRigaDettaglio" type="{http://www.csi.it/epay/epaywso/rendicontazione}FlussoDettaglioType" maxOccurs="unbounded"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FlussoSintesiType", namespace = "http://www.csi.it/epay/epaywso/rendicontazione", propOrder = {
    "codiceVersamento",
    "descrizioneCodiceVersamento",
    "datiSpecificiDiRiscossione",
    "descrizioneDatiSpecifici",
    "macrotipo",
    "tematica",
    "importoQuotaAggregazione",
    "nroPagamentiAggregazione",
    "capitolo",
    "articolo",
    "pdC",
    "progressivoFlussoSintetico",
    "accertamentoAnno",
    "accertamentoNro",
    "righeDettaglio"
})
public class FlussoSintesiType {

    @XmlElement(name = "CodiceVersamento", required = true)
    protected String codiceVersamento;
    @XmlElement(name = "DescrizioneCodiceVersamento")
    protected String descrizioneCodiceVersamento;
    @XmlElement(name = "DatiSpecificiDiRiscossione", required = true)
    protected String datiSpecificiDiRiscossione;
    @XmlElement(name = "DescrizioneDatiSpecifici")
    protected String descrizioneDatiSpecifici;
    @XmlElement(name = "Macrotipo")
    protected String macrotipo;
    @XmlElement(name = "Tematica")
    protected String tematica;
    @XmlElement(name = "ImportoQuotaAggregazione", required = true)
    protected BigDecimal importoQuotaAggregazione;
    @XmlElement(name = "NroPagamentiAggregazione")
    @XmlSchemaType(name = "integer")
    protected int nroPagamentiAggregazione;
    @XmlElement(name = "Capitolo")
    protected String capitolo;
    @XmlElement(name = "Articolo")
    protected String articolo;
    @XmlElement(name = "PdC")
    protected String pdC;
    @XmlElement(name = "ProgressivoFlussoSintetico", required = true, type = Integer.class, nillable = true)
    @XmlSchemaType(name = "integer")
    protected Integer progressivoFlussoSintetico;
    @XmlElement(name = "AccertamentoAnno", required = true, type = Integer.class, nillable = true)
    @XmlSchemaType(name = "integer")
    protected Integer accertamentoAnno;
    @XmlElement(name = "AccertamentoNro", required = true, type = Integer.class, nillable = true)
    @XmlSchemaType(name = "integer")
    protected Integer accertamentoNro;
    @XmlElement(name = "RigheDettaglio")
    protected FlussoSintesiType.RigheDettaglio righeDettaglio;

    /**
     * Recupera il valore della propriet codiceVersamento.
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
     * Imposta il valore della propriet codiceVersamento.
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
     * Recupera il valore della propriet descrizioneCodiceVersamento.
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
     * Imposta il valore della propriet descrizioneCodiceVersamento.
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
     * Recupera il valore della propriet datiSpecificiDiRiscossione.
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
     * Imposta il valore della propriet datiSpecificiDiRiscossione.
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
     * Recupera il valore della propriet descrizioneDatiSpecifici.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizioneDatiSpecifici() {
        return descrizioneDatiSpecifici;
    }

    /**
     * Imposta il valore della propriet descrizioneDatiSpecifici.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizioneDatiSpecifici(String value) {
        this.descrizioneDatiSpecifici = value;
    }

    /**
     * Recupera il valore della propriet macrotipo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMacrotipo() {
        return macrotipo;
    }

    /**
     * Imposta il valore della propriet macrotipo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMacrotipo(String value) {
        this.macrotipo = value;
    }

    /**
     * Recupera il valore della propriet tematica.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTematica() {
        return tematica;
    }

    /**
     * Imposta il valore della propriet tematica.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTematica(String value) {
        this.tematica = value;
    }

    /**
     * Recupera il valore della propriet importoQuotaAggregazione.
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
     * Imposta il valore della propriet importoQuotaAggregazione.
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
     * Recupera il valore della propriet nroPagamentiAggregazione.
     * 
     */
    public int getNroPagamentiAggregazione() {
        return nroPagamentiAggregazione;
    }

    /**
     * Imposta il valore della propriet nroPagamentiAggregazione.
     * 
     */
    public void setNroPagamentiAggregazione(int value) {
        this.nroPagamentiAggregazione = value;
    }

    /**
     * Recupera il valore della propriet capitolo.
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
     * Imposta il valore della propriet capitolo.
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
     * Recupera il valore della propriet articolo.
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
     * Imposta il valore della propriet articolo.
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
     * Recupera il valore della propriet pdC.
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
     * Imposta il valore della propriet pdC.
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
     * Recupera il valore della propriet progressivoFlussoSintetico.
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
     * Imposta il valore della propriet progressivoFlussoSintetico.
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
     * Recupera il valore della propriet accertamentoAnno.
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
     * Imposta il valore della propriet accertamentoAnno.
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
     * Recupera il valore della propriet accertamentoNro.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAccertamentoNro() {
        return accertamentoNro;
    }

    /**
     * Imposta il valore della propriet accertamentoNro.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAccertamentoNro(Integer value) {
        this.accertamentoNro = value;
    }

    /**
     * Recupera il valore della propriet righeDettaglio.
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
     * Imposta il valore della propriet righeDettaglio.
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
     * <p>Classe Java per anonymous complex type.
     * 
     * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="SingolaRigaDettaglio" type="{http://www.csi.it/epay/epaywso/rendicontazione}FlussoDettaglioType" maxOccurs="unbounded"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "singolaRigaDettaglio"
    })
    public static class RigheDettaglio {

        @XmlElement(name = "SingolaRigaDettaglio", namespace = "http://www.csi.it/epay/epaywso/rendicontazione", required = true)
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
