/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.util.wsdl.epaywso;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for InserisciNuovaRichiestaRequestType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="InserisciNuovaRichiestaRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MessageUUID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CodiceFiscaleEnte" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PagamentoSpontaneo" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="CodiceVersamento" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TipoRichiesta" type="{http://www.csi.it/epay/epaywso/types}TipoRichiestaType"/>
 *         &lt;element name="StatoRichiesta" type="{http://www.csi.it/epay/epaywso/types}StatoRichiestaType"/>
 *         &lt;element name="IdMessaggio" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ContenutoMessaggio" type="{http://www.csi.it/epay/epaywso/businesstypes}EmbeddedXMLType"/>
 *         &lt;element name="NumeroTotaleElementi" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="ImportoTotale" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="IdPSP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IdFlussoRendicontazione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InserisciNuovaRichiestaRequestType", propOrder = {
    "messageUUID",
    "codiceFiscaleEnte",
    "pagamentoSpontaneo",
    "codiceVersamento",
    "tipoRichiesta",
    "statoRichiesta",
    "idMessaggio",
    "contenutoMessaggio",
    "numeroTotaleElementi",
    "importoTotale",
    "idPSP",
    "idFlussoRendicontazione"
})
public class InserisciNuovaRichiestaRequestType {

    @XmlElement(name = "MessageUUID", required = true)
    protected String messageUUID;
    @XmlElement(name = "CodiceFiscaleEnte", required = true)
    protected String codiceFiscaleEnte;
    @XmlElement(name = "PagamentoSpontaneo")
    protected Boolean pagamentoSpontaneo;
    @XmlElement(name = "CodiceVersamento", required = true)
    protected String codiceVersamento;
    @XmlElement(name = "TipoRichiesta", required = true)
    protected TipoRichiestaType tipoRichiesta;
    @XmlElement(name = "StatoRichiesta", required = true)
    protected StatoRichiestaType statoRichiesta;
    @XmlElement(name = "IdMessaggio", required = true)
    protected String idMessaggio;
    @XmlElement(name = "ContenutoMessaggio", required = true)
    protected EmbeddedXMLType contenutoMessaggio;
    @XmlElement(name = "NumeroTotaleElementi")
    protected Integer numeroTotaleElementi;
    @XmlElement(name = "ImportoTotale")
    protected BigDecimal importoTotale;
    @XmlElement(name = "IdPSP")
    protected String idPSP;
    @XmlElement(name = "IdFlussoRendicontazione")
    protected String idFlussoRendicontazione;

    /**
     * Gets the value of the messageUUID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessageUUID() {
        return messageUUID;
    }

    /**
     * Sets the value of the messageUUID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessageUUID(String value) {
        this.messageUUID = value;
    }

    /**
     * Gets the value of the codiceFiscaleEnte property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceFiscaleEnte() {
        return codiceFiscaleEnte;
    }

    /**
     * Sets the value of the codiceFiscaleEnte property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceFiscaleEnte(String value) {
        this.codiceFiscaleEnte = value;
    }

    /**
     * Gets the value of the pagamentoSpontaneo property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPagamentoSpontaneo() {
        return pagamentoSpontaneo;
    }

    /**
     * Sets the value of the pagamentoSpontaneo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPagamentoSpontaneo(Boolean value) {
        this.pagamentoSpontaneo = value;
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
     * Gets the value of the tipoRichiesta property.
     * 
     * @return
     *     possible object is
     *     {@link TipoRichiestaType }
     *     
     */
    public TipoRichiestaType getTipoRichiesta() {
        return tipoRichiesta;
    }

    /**
     * Sets the value of the tipoRichiesta property.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoRichiestaType }
     *     
     */
    public void setTipoRichiesta(TipoRichiestaType value) {
        this.tipoRichiesta = value;
    }

    /**
     * Gets the value of the statoRichiesta property.
     * 
     * @return
     *     possible object is
     *     {@link StatoRichiestaType }
     *     
     */
    public StatoRichiestaType getStatoRichiesta() {
        return statoRichiesta;
    }

    /**
     * Sets the value of the statoRichiesta property.
     * 
     * @param value
     *     allowed object is
     *     {@link StatoRichiestaType }
     *     
     */
    public void setStatoRichiesta(StatoRichiestaType value) {
        this.statoRichiesta = value;
    }

    /**
     * Gets the value of the idMessaggio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdMessaggio() {
        return idMessaggio;
    }

    /**
     * Sets the value of the idMessaggio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdMessaggio(String value) {
        this.idMessaggio = value;
    }

    /**
     * Gets the value of the contenutoMessaggio property.
     * 
     * @return
     *     possible object is
     *     {@link EmbeddedXMLType }
     *     
     */
    public EmbeddedXMLType getContenutoMessaggio() {
        return contenutoMessaggio;
    }

    /**
     * Sets the value of the contenutoMessaggio property.
     * 
     * @param value
     *     allowed object is
     *     {@link EmbeddedXMLType }
     *     
     */
    public void setContenutoMessaggio(EmbeddedXMLType value) {
        this.contenutoMessaggio = value;
    }

    /**
     * Gets the value of the numeroTotaleElementi property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumeroTotaleElementi() {
        return numeroTotaleElementi;
    }

    /**
     * Sets the value of the numeroTotaleElementi property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumeroTotaleElementi(Integer value) {
        this.numeroTotaleElementi = value;
    }

    /**
     * Gets the value of the importoTotale property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getImportoTotale() {
        return importoTotale;
    }

    /**
     * Sets the value of the importoTotale property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setImportoTotale(BigDecimal value) {
        this.importoTotale = value;
    }

    /**
     * Gets the value of the idPSP property.
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
     * Sets the value of the idPSP property.
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
     * Gets the value of the idFlussoRendicontazione property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdFlussoRendicontazione() {
        return idFlussoRendicontazione;
    }

    /**
     * Sets the value of the idFlussoRendicontazione property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdFlussoRendicontazione(String value) {
        this.idFlussoRendicontazione = value;
    }

}
