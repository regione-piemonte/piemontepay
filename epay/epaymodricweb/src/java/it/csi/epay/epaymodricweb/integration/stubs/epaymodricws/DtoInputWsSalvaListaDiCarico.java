/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodricweb.integration.stubs.epaymodricws;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for dtoInputWsSalvaListaDiCarico complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dtoInputWsSalvaListaDiCarico">
 *   &lt;complexContent>
 *     &lt;extension base="{http://epaymodric.interfacews.epaymodric.epay.csi.it/}dtoInputBase">
 *       &lt;sequence>
 *         &lt;element name="accertamentoAnno" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="accertamentoNro" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="annoEsercizio" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="articolo" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="capitolo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codiceVersamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataFineValidita" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataInizioValidita" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="datiSpecificiRiscossione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descrizioneCausale" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="importoVersamento" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="pianoDeiConti" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rifPosizioneDebitoria" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtoInputWsSalvaListaDiCarico", propOrder = {
    "accertamentoAnno",
    "accertamentoNro",
    "annoEsercizio",
    "articolo",
    "capitolo",
    "codiceVersamento",
    "dataFineValidita",
    "dataInizioValidita",
    "datiSpecificiRiscossione",
    "descrizioneCausale",
    "importoVersamento",
    "pianoDeiConti",
    "rifPosizioneDebitoria"
})
public class DtoInputWsSalvaListaDiCarico
    extends DtoInputBase
{

    protected Integer accertamentoAnno;
    protected Integer accertamentoNro;
    protected Integer annoEsercizio;
    protected Integer articolo;
    protected String capitolo;
    protected String codiceVersamento;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataFineValidita;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataInizioValidita;
    protected String datiSpecificiRiscossione;
    protected String descrizioneCausale;
    protected BigDecimal importoVersamento;
    protected String pianoDeiConti;
    protected Integer rifPosizioneDebitoria;

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
     * Gets the value of the accertamentoNro property.
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
     * Sets the value of the accertamentoNro property.
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
     * Gets the value of the annoEsercizio property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAnnoEsercizio() {
        return annoEsercizio;
    }

    /**
     * Sets the value of the annoEsercizio property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAnnoEsercizio(Integer value) {
        this.annoEsercizio = value;
    }

    /**
     * Gets the value of the articolo property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getArticolo() {
        return articolo;
    }

    /**
     * Sets the value of the articolo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setArticolo(Integer value) {
        this.articolo = value;
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
     * Gets the value of the dataFineValidita property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataFineValidita() {
        return dataFineValidita;
    }

    /**
     * Sets the value of the dataFineValidita property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataFineValidita(XMLGregorianCalendar value) {
        this.dataFineValidita = value;
    }

    /**
     * Gets the value of the dataInizioValidita property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataInizioValidita() {
        return dataInizioValidita;
    }

    /**
     * Sets the value of the dataInizioValidita property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataInizioValidita(XMLGregorianCalendar value) {
        this.dataInizioValidita = value;
    }

    /**
     * Gets the value of the datiSpecificiRiscossione property.
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
     * Sets the value of the datiSpecificiRiscossione property.
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
     * Gets the value of the descrizioneCausale property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizioneCausale() {
        return descrizioneCausale;
    }

    /**
     * Sets the value of the descrizioneCausale property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizioneCausale(String value) {
        this.descrizioneCausale = value;
    }

    /**
     * Gets the value of the importoVersamento property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getImportoVersamento() {
        return importoVersamento;
    }

    /**
     * Sets the value of the importoVersamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setImportoVersamento(BigDecimal value) {
        this.importoVersamento = value;
    }

    /**
     * Gets the value of the pianoDeiConti property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPianoDeiConti() {
        return pianoDeiConti;
    }

    /**
     * Sets the value of the pianoDeiConti property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPianoDeiConti(String value) {
        this.pianoDeiConti = value;
    }

    /**
     * Gets the value of the rifPosizioneDebitoria property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getRifPosizioneDebitoria() {
        return rifPosizioneDebitoria;
    }

    /**
     * Sets the value of the rifPosizioneDebitoria property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setRifPosizioneDebitoria(Integer value) {
        this.rifPosizioneDebitoria = value;
    }

}
