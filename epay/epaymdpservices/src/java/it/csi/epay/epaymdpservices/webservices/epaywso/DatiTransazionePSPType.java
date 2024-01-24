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
 * <p>Classe Java per DatiTransazionePSPType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="DatiTransazionePSPType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IdPSP" type="{http://www.csi.it/epay/epaywso/types}String35Type"/>
 *         &lt;element name="RagioneSocialePSP" type="{http://www.csi.it/epay/epaywso/types}String250Type" minOccurs="0"/>
 *         &lt;element name="TipoVersamento" type="{http://www.csi.it/epay/epaywso/epaywso2enti/types}TipoVersamentoType" minOccurs="0"/>
 *         &lt;element name="IdFlussoRendicontazionePSP" type="{http://www.csi.it/epay/epaywso/types}String35Type" minOccurs="0"/>
 *         &lt;element name="DataOraAvvioTransazione" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="IUR" type="{http://www.csi.it/epay/epaywso/types}String35Type"/>
 *         &lt;element name="DataOraAutorizzazione" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="TipoSicurezza" type="{http://www.csi.it/epay/epaywso/types}String50Type" minOccurs="0"/>
 *         &lt;element name="ImportoTransato" type="{http://www.csi.it/epay/epaywso/types}ImportoType" minOccurs="0"/>
 *         &lt;element name="ImportoCommissioni" type="{http://www.csi.it/epay/epaywso/types}ImportoType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DatiTransazionePSPType", propOrder = {
    "idPSP",
    "ragioneSocialePSP",
    "tipoVersamento",
    "idFlussoRendicontazionePSP",
    "dataOraAvvioTransazione",
    "iur",
    "dataOraAutorizzazione",
    "tipoSicurezza",
    "importoTransato",
    "importoCommissioni"
})
public class DatiTransazionePSPType {

    @XmlElement(name = "IdPSP", required = true)
    protected String idPSP;
    @XmlElement(name = "RagioneSocialePSP")
    protected String ragioneSocialePSP;
    @XmlElement(name = "TipoVersamento")
    protected String tipoVersamento;
    @XmlElement(name = "IdFlussoRendicontazionePSP")
    protected String idFlussoRendicontazionePSP;
    @XmlElement(name = "DataOraAvvioTransazione", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataOraAvvioTransazione;
    @XmlElement(name = "IUR", required = true)
    protected String iur;
    @XmlElement(name = "DataOraAutorizzazione")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataOraAutorizzazione;
    @XmlElement(name = "TipoSicurezza")
    protected String tipoSicurezza;
    @XmlElement(name = "ImportoTransato")
    protected BigDecimal importoTransato;
    @XmlElement(name = "ImportoCommissioni")
    protected BigDecimal importoCommissioni;

    /**
     * Recupera il valore della propriet� idPSP.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdPSP() {
        return idPSP;
    }

    /**
     * Imposta il valore della propriet� idPSP.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdPSP(String value) {
        this.idPSP = value;
    }

    /**
     * Recupera il valore della propriet� ragioneSocialePSP.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRagioneSocialePSP() {
        return ragioneSocialePSP;
    }

    /**
     * Imposta il valore della propriet� ragioneSocialePSP.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRagioneSocialePSP(String value) {
        this.ragioneSocialePSP = value;
    }

    /**
     * Recupera il valore della propriet� tipoVersamento.
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
     * Imposta il valore della propriet� tipoVersamento.
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
     * Recupera il valore della propriet� idFlussoRendicontazionePSP.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdFlussoRendicontazionePSP() {
        return idFlussoRendicontazionePSP;
    }

    /**
     * Imposta il valore della propriet� idFlussoRendicontazionePSP.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdFlussoRendicontazionePSP(String value) {
        this.idFlussoRendicontazionePSP = value;
    }

    /**
     * Recupera il valore della propriet� dataOraAvvioTransazione.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataOraAvvioTransazione() {
        return dataOraAvvioTransazione;
    }

    /**
     * Imposta il valore della propriet� dataOraAvvioTransazione.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataOraAvvioTransazione(XMLGregorianCalendar value) {
        this.dataOraAvvioTransazione = value;
    }

    /**
     * Recupera il valore della propriet� iur.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIUR() {
        return iur;
    }

    /**
     * Imposta il valore della propriet� iur.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIUR(String value) {
        this.iur = value;
    }

    /**
     * Recupera il valore della propriet� dataOraAutorizzazione.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataOraAutorizzazione() {
        return dataOraAutorizzazione;
    }

    /**
     * Imposta il valore della propriet� dataOraAutorizzazione.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataOraAutorizzazione(XMLGregorianCalendar value) {
        this.dataOraAutorizzazione = value;
    }

    /**
     * Recupera il valore della propriet� tipoSicurezza.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoSicurezza() {
        return tipoSicurezza;
    }

    /**
     * Imposta il valore della propriet� tipoSicurezza.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoSicurezza(String value) {
        this.tipoSicurezza = value;
    }

    /**
     * Recupera il valore della propriet� importoTransato.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getImportoTransato() {
        return importoTransato;
    }

    /**
     * Imposta il valore della propriet� importoTransato.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setImportoTransato(BigDecimal value) {
        this.importoTransato = value;
    }

    /**
     * Recupera il valore della propriet� importoCommissioni.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getImportoCommissioni() {
        return importoCommissioni;
    }

    /**
     * Imposta il valore della propriet� importoCommissioni.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setImportoCommissioni(BigDecimal value) {
        this.importoCommissioni = value;
    }

}
