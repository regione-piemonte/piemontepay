/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.sportello2epaywso;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java per RichiestaDiRevocaResponseType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="RichiestaDiRevocaResponseType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="IdentificativoDominio" type="{http://www.csi.it/epay/epaywso/types}String35Type"/&gt;
 *         &lt;element name="ApplicationId" type="{http://www.csi.it/epay/epaywso/types}String50Type"/&gt;
 *         &lt;element name="IdentificativoMessaggioEsito" type="{http://www.csi.it/epay/epaywso/types}String50Type"/&gt;
 *         &lt;element name="DataOraMessaggioEsito" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="RiferimentoMessaggioRevoca" type="{http://www.csi.it/epay/epaywso/types}String50Type"/&gt;
 *         &lt;element name="RiferimentoDataOraRevoca" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="IstitutoAttestante" type="{http://www.csi.it/epay/epaywso/types}SoggettoType"/&gt;
 *         &lt;element name="ImportoPagato" type="{http://www.csi.it/epay/epaywso/types}ImportoType"/&gt;
 *         &lt;element name="IUV" type="{http://www.csi.it/epay/epaywso/types}String35Type"/&gt;
 *         &lt;element name="CodiceContestoPagamento" type="{http://www.csi.it/epay/epaywso/types}String35Type"/&gt;
 *         &lt;element name="InvioOkRispostaRevoca" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;sequence&gt;
 *           &lt;element name="XML" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
 *         &lt;/sequence&gt;
 *         &lt;sequence&gt;
 *           &lt;element name="DatiSingoloEsitoRevoca" type="{http://www.csi.it/epay/epaywso/epaywso2enti/types}DatiEsitoSingolaRevocaType"/&gt;
 *         &lt;/sequence&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RichiestaDiRevocaResponseType", propOrder = {
    "identificativoDominio",
    "applicationId",
    "identificativoMessaggioEsito",
    "dataOraMessaggioEsito",
    "riferimentoMessaggioRevoca",
    "riferimentoDataOraRevoca",
    "istitutoAttestante",
    "importoPagato",
    "iuv",
    "codiceContestoPagamento",
    "invioOkRispostaRevoca",
    "xml",
    "datiSingoloEsitoRevoca"
})
public class RichiestaDiRevocaResponseType {

    @XmlElement(name = "IdentificativoDominio", required = true)
    protected String identificativoDominio;
    @XmlElement(name = "ApplicationId", required = true)
    protected String applicationId;
    @XmlElement(name = "IdentificativoMessaggioEsito", required = true)
    protected String identificativoMessaggioEsito;
    @XmlElement(name = "DataOraMessaggioEsito", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataOraMessaggioEsito;
    @XmlElement(name = "RiferimentoMessaggioRevoca", required = true)
    protected String riferimentoMessaggioRevoca;
    @XmlElement(name = "RiferimentoDataOraRevoca", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar riferimentoDataOraRevoca;
    @XmlElement(name = "IstitutoAttestante", required = true)
    protected SoggettoType istitutoAttestante;
    @XmlElement(name = "ImportoPagato", required = true)
    protected BigDecimal importoPagato;
    @XmlElement(name = "IUV", required = true)
    protected String iuv;
    @XmlElement(name = "CodiceContestoPagamento", required = true)
    protected String codiceContestoPagamento;
    @XmlElement(name = "InvioOkRispostaRevoca")
    protected boolean invioOkRispostaRevoca;
    @XmlElement(name = "XML", required = true)
    protected byte[] xml;
    @XmlElement(name = "DatiSingoloEsitoRevoca", required = true)
    protected DatiEsitoSingolaRevocaType datiSingoloEsitoRevoca;

    /**
     * Recupera il valore della proprieta identificativoDominio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentificativoDominio() {
        return identificativoDominio;
    }

    /**
     * Imposta il valore della proprieta identificativoDominio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentificativoDominio(String value) {
        this.identificativoDominio = value;
    }

    /**
     * Recupera il valore della proprieta applicationId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApplicationId() {
        return applicationId;
    }

    /**
     * Imposta il valore della proprieta applicationId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApplicationId(String value) {
        this.applicationId = value;
    }

    /**
     * Recupera il valore della proprieta identificativoMessaggioEsito.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentificativoMessaggioEsito() {
        return identificativoMessaggioEsito;
    }

    /**
     * Imposta il valore della proprieta identificativoMessaggioEsito.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentificativoMessaggioEsito(String value) {
        this.identificativoMessaggioEsito = value;
    }

    /**
     * Recupera il valore della proprieta dataOraMessaggioEsito.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataOraMessaggioEsito() {
        return dataOraMessaggioEsito;
    }

    /**
     * Imposta il valore della proprieta dataOraMessaggioEsito.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataOraMessaggioEsito(XMLGregorianCalendar value) {
        this.dataOraMessaggioEsito = value;
    }

    /**
     * Recupera il valore della proprieta riferimentoMessaggioRevoca.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRiferimentoMessaggioRevoca() {
        return riferimentoMessaggioRevoca;
    }

    /**
     * Imposta il valore della proprieta riferimentoMessaggioRevoca.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRiferimentoMessaggioRevoca(String value) {
        this.riferimentoMessaggioRevoca = value;
    }

    /**
     * Recupera il valore della proprieta riferimentoDataOraRevoca.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getRiferimentoDataOraRevoca() {
        return riferimentoDataOraRevoca;
    }

    /**
     * Imposta il valore della proprieta riferimentoDataOraRevoca.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setRiferimentoDataOraRevoca(XMLGregorianCalendar value) {
        this.riferimentoDataOraRevoca = value;
    }

    /**
     * Recupera il valore della proprieta istitutoAttestante.
     * 
     * @return
     *     possible object is
     *     {@link SoggettoType }
     *     
     */
    public SoggettoType getIstitutoAttestante() {
        return istitutoAttestante;
    }

    /**
     * Imposta il valore della proprieta istitutoAttestante.
     * 
     * @param value
     *     allowed object is
     *     {@link SoggettoType }
     *     
     */
    public void setIstitutoAttestante(SoggettoType value) {
        this.istitutoAttestante = value;
    }

    /**
     * Recupera il valore della proprieta importoPagato.
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
     * Imposta il valore della proprieta importoPagato.
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
     * Recupera il valore della proprieta iuv.
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
     * Imposta il valore della proprieta iuv.
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
     * Recupera il valore della proprieta codiceContestoPagamento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceContestoPagamento() {
        return codiceContestoPagamento;
    }

    /**
     * Imposta il valore della proprieta codiceContestoPagamento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceContestoPagamento(String value) {
        this.codiceContestoPagamento = value;
    }

    /**
     * Recupera il valore della proprieta invioOkRispostaRevoca.
     * 
     */
    public boolean isInvioOkRispostaRevoca() {
        return invioOkRispostaRevoca;
    }

    /**
     * Imposta il valore della proprieta invioOkRispostaRevoca.
     * 
     */
    public void setInvioOkRispostaRevoca(boolean value) {
        this.invioOkRispostaRevoca = value;
    }

    /**
     * Recupera il valore della proprieta xml.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getXML() {
        return xml;
    }

    /**
     * Imposta il valore della proprieta xml.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setXML(byte[] value) {
        this.xml = value;
    }

    /**
     * Recupera il valore della proprieta datiSingoloEsitoRevoca.
     * 
     * @return
     *     possible object is
     *     {@link DatiEsitoSingolaRevocaType }
     *     
     */
    public DatiEsitoSingolaRevocaType getDatiSingoloEsitoRevoca() {
        return datiSingoloEsitoRevoca;
    }

    /**
     * Imposta il valore della proprieta datiSingoloEsitoRevoca.
     * 
     * @param value
     *     allowed object is
     *     {@link DatiEsitoSingolaRevocaType }
     *     
     */
    public void setDatiSingoloEsitoRevoca(DatiEsitoSingolaRevocaType value) {
        this.datiSingoloEsitoRevoca = value;
    }

}
