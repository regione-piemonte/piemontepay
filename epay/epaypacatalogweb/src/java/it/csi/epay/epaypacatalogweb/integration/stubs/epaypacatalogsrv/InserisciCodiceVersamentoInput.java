/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for inserisciCodiceVersamentoInput complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="inserisciCodiceVersamentoInput">
 *   &lt;complexContent>
 *     &lt;extension base="{http://interfacews.epaypacatalogsrv.epay.csi.it/}parentInput">
 *       &lt;sequence>
 *         &lt;element name="bic" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bicAppoggio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codiceModalitaIntegrazione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codiceTipoPagamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codiceVoceEntrata" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="covAssociatoAssociazioneMultibeneficiario" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="descrizione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="enteSecondarioAssociazioneMultibeneficiario" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="fattura" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="flagElementiMultibeneficiario" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="flagInvioFlussi" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="flagMbPrimario" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="flagMbSecondario" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="flagPresenzaBollettinoPostale" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="iban" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ibanAppoggio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ibanAppoggioPostale" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="ibanPostale" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="idCodiceVersamentoPadre" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="modalitaAssociazioneMultibeneficiario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="flagPersonalizzazioneCov" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="descrizioneTextCov" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="strPassphrase" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="strCredenzialiPnd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="urlAttualizzazionePnd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="flagVisualizzaDaSportello" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="dtFineValidita" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="dtInizioValidita" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "inserisciCodiceVersamentoInput", propOrder = {
    "bic",
    "bicAppoggio",
    "codiceModalitaIntegrazione",
    "codiceTipoPagamento",
    "codiceVoceEntrata",
    "covAssociatoAssociazioneMultibeneficiario",
    "descrizione",
    "email",
    "enteSecondarioAssociazioneMultibeneficiario",
    "fattura",
    "flagElementiMultibeneficiario",
    "flagInvioFlussi",
    "flagMbPrimario",
    "flagMbSecondario",
    "flagPresenzaBollettinoPostale",
    "iban",
    "ibanAppoggio",
    "ibanAppoggioPostale",
    "ibanPostale",
    "idCodiceVersamentoPadre",
    "modalitaAssociazioneMultibeneficiario",
    "flagPersonalizzazioneCov",
    "descrizioneTextCov",
    "strPassphrase",
    "strCredenzialiPnd",
    "urlAttualizzazionePnd",
    "flagVisualizzaDaSportello",
    "dtInizioValidita",
    "dtFineValidita"
    
})
public class InserisciCodiceVersamentoInput
    extends ParentInput
{

    protected String bic;
    protected String bicAppoggio;
    protected String codiceModalitaIntegrazione;
    protected String codiceTipoPagamento;
    protected String codiceVoceEntrata;
    protected Long covAssociatoAssociazioneMultibeneficiario;
    protected String descrizione;
    protected String email;
    protected Long enteSecondarioAssociazioneMultibeneficiario;
    protected Boolean fattura;
    protected Boolean flagElementiMultibeneficiario;
    protected Boolean flagInvioFlussi;
    protected Boolean flagMbPrimario;
    protected Boolean flagMbSecondario;
    protected Boolean flagPresenzaBollettinoPostale;
    protected String iban;
    protected String ibanAppoggio;
    protected Boolean ibanAppoggioPostale;
    protected Boolean ibanPostale;
    protected Long idCodiceVersamentoPadre;
    protected String modalitaAssociazioneMultibeneficiario;
    protected Boolean flagPersonalizzazioneCov;
    protected String descrizioneTextCov;
    protected String strPassphrase;
    protected String strCredenzialiPnd;
    protected String urlAttualizzazionePnd;
    protected Boolean flagVisualizzaDaSportello;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dtFineValidita;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dtInizioValidita;

    /**
     * Gets the value of the bic property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBic() {
        return bic;
    }

    /**
     * Sets the value of the bic property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBic(String value) {
        this.bic = value;
    }

    /**
     * Gets the value of the bicAppoggio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBicAppoggio() {
        return bicAppoggio;
    }

    /**
     * Sets the value of the bicAppoggio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBicAppoggio(String value) {
        this.bicAppoggio = value;
    }

    /**
     * Gets the value of the codiceModalitaIntegrazione property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceModalitaIntegrazione() {
        return codiceModalitaIntegrazione;
    }

    /**
     * Sets the value of the codiceModalitaIntegrazione property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceModalitaIntegrazione(String value) {
        this.codiceModalitaIntegrazione = value;
    }

    /**
     * Gets the value of the codiceTipoPagamento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceTipoPagamento() {
        return codiceTipoPagamento;
    }

    /**
     * Sets the value of the codiceTipoPagamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceTipoPagamento(String value) {
        this.codiceTipoPagamento = value;
    }

    /**
     * Gets the value of the codiceVoceEntrata property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceVoceEntrata() {
        return codiceVoceEntrata;
    }

    /**
     * Sets the value of the codiceVoceEntrata property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceVoceEntrata(String value) {
        this.codiceVoceEntrata = value;
    }

    /**
     * Gets the value of the covAssociatoAssociazioneMultibeneficiario property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCovAssociatoAssociazioneMultibeneficiario() {
        return covAssociatoAssociazioneMultibeneficiario;
    }

    /**
     * Sets the value of the covAssociatoAssociazioneMultibeneficiario property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCovAssociatoAssociazioneMultibeneficiario(Long value) {
        this.covAssociatoAssociazioneMultibeneficiario = value;
    }

    /**
     * Gets the value of the descrizione property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizione() {
        return descrizione;
    }

    /**
     * Sets the value of the descrizione property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizione(String value) {
        this.descrizione = value;
    }

    /**
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Gets the value of the enteSecondarioAssociazioneMultibeneficiario property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getEnteSecondarioAssociazioneMultibeneficiario() {
        return enteSecondarioAssociazioneMultibeneficiario;
    }

    /**
     * Sets the value of the enteSecondarioAssociazioneMultibeneficiario property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setEnteSecondarioAssociazioneMultibeneficiario(Long value) {
        this.enteSecondarioAssociazioneMultibeneficiario = value;
    }

    /**
     * Gets the value of the fattura property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean getFattura() {
        return fattura;
    }

    /**
     * Sets the value of the fattura property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setFattura(Boolean value) {
        this.fattura = value;
    }

    /**
     * Gets the value of the flagElementiMultibeneficiario property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean getFlagElementiMultibeneficiario() {
        return flagElementiMultibeneficiario;
    }

    /**
     * Sets the value of the flagElementiMultibeneficiario property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setFlagElementiMultibeneficiario(Boolean value) {
        this.flagElementiMultibeneficiario = value;
    }

    /**
     * Gets the value of the flagInvioFlussi property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean getFlagInvioFlussi() {
        return flagInvioFlussi;
    }

    /**
     * Sets the value of the flagInvioFlussi property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setFlagInvioFlussi(Boolean value) {
        this.flagInvioFlussi = value;
    }

    /**
     * Gets the value of the flagMbPrimario property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean getFlagMbPrimario() {
        return flagMbPrimario;
    }

    /**
     * Sets the value of the flagMbPrimario property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setFlagMbPrimario(Boolean value) {
        this.flagMbPrimario = value;
    }

    /**
     * Gets the value of the flagMbSecondario property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean getFlagMbSecondario() {
        return flagMbSecondario;
    }

    /**
     * Sets the value of the flagMbSecondario property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setFlagMbSecondario(Boolean value) {
        this.flagMbSecondario = value;
    }

    /**
     * Gets the value of the flagPresenzaBollettinoPostale property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean getFlagPresenzaBollettinoPostale() {
        return flagPresenzaBollettinoPostale;
    }

    /**
     * Sets the value of the flagPresenzaBollettinoPostale property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setFlagPresenzaBollettinoPostale(Boolean value) {
        this.flagPresenzaBollettinoPostale = value;
    }

    /**
     * Gets the value of the iban property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIban() {
        return iban;
    }

    /**
     * Sets the value of the iban property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIban(String value) {
        this.iban = value;
    }

    /**
     * Gets the value of the ibanAppoggio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIbanAppoggio() {
        return ibanAppoggio;
    }

    /**
     * Sets the value of the ibanAppoggio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIbanAppoggio(String value) {
        this.ibanAppoggio = value;
    }

    /**
     * Gets the value of the ibanAppoggioPostale property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean getIbanAppoggioPostale() {
        return ibanAppoggioPostale;
    }

    /**
     * Sets the value of the ibanAppoggioPostale property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIbanAppoggioPostale(Boolean value) {
        this.ibanAppoggioPostale = value;
    }

    /**
     * Gets the value of the ibanPostale property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean getIbanPostale() {
        return ibanPostale;
    }

    /**
     * Sets the value of the ibanPostale property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIbanPostale(Boolean value) {
        this.ibanPostale = value;
    }

    /**
     * Gets the value of the idCodiceVersamentoPadre property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdCodiceVersamentoPadre() {
        return idCodiceVersamentoPadre;
    }

    /**
     * Sets the value of the idCodiceVersamentoPadre property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdCodiceVersamentoPadre(Long value) {
        this.idCodiceVersamentoPadre = value;
    }

    /**
     * Gets the value of the modalitaAssociazioneMultibeneficiario property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModalitaAssociazioneMultibeneficiario() {
        return modalitaAssociazioneMultibeneficiario;
    }

    /**
     * Sets the value of the modalitaAssociazioneMultibeneficiario property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModalitaAssociazioneMultibeneficiario(String value) {
        this.modalitaAssociazioneMultibeneficiario = value;
    }

    
    /**
     * @return the descrizioneTextCov
     */
    public String getDescrizioneTextCov () {
        return descrizioneTextCov;
    }

    
    /**
     * @param descrizioneTextCov the descrizioneTextCov to set
     */
    public void setDescrizioneTextCov ( String descrizioneTextCov ) {
        this.descrizioneTextCov = descrizioneTextCov;
    }

    
    
    
    /**
     * @return the flagPersonalizzazioneCov
     */
    public Boolean getFlagPersonalizzazioneCov () {
        return flagPersonalizzazioneCov;
    }

    
    /**
     * @param flagPersonalizzazioneCov the flagPersonalizzazioneCov to set
     */
    public void setFlagPersonalizzazioneCov ( Boolean flagPersonalizzazioneCov ) {
        this.flagPersonalizzazioneCov = flagPersonalizzazioneCov;
    }

    
    /**
     * @return the strPassphrase
     */
    public String getStrPassphrase () {
        return strPassphrase;
    }

    
    /**
     * @param strPassphrase the strPassphrase to set
     */
    public void setStrPassphrase ( String strPassphrase ) {
        this.strPassphrase = strPassphrase;
    }

    
    /**
     * @return the strCredenzialiPnd
     */
    public String getStrCredenzialiPnd () {
        return strCredenzialiPnd;
    }

    
    /**
     * @param strCredenzialiPnd the strCredenzialiPnd to set
     */
    public void setStrCredenzialiPnd ( String strCredenzialiPnd ) {
        this.strCredenzialiPnd = strCredenzialiPnd;
    }

    
    /**
     * @return the urlAttualizzazionePnd
     */
    public String getUrlAttualizzazionePnd () {
        return urlAttualizzazionePnd;
    }

    
    /**
     * @param urlAttualizzazionePnd the urlAttualizzazionePnd to set
     */
    public void setUrlAttualizzazionePnd ( String urlAttualizzazionePnd ) {
        this.urlAttualizzazionePnd = urlAttualizzazionePnd;
    }

    
    public Boolean getFlagVisualizzaDaSportello () {
        return flagVisualizzaDaSportello;
    }

    
    public void setFlagVisualizzaDaSportello ( Boolean flagVisualizzaDaSportello ) {
        this.flagVisualizzaDaSportello = flagVisualizzaDaSportello;
    }

    
    public XMLGregorianCalendar getDtFineValidita () {
        return dtFineValidita;
    }

    
    public void setDtFineValidita ( XMLGregorianCalendar dtFineValidita ) {
        this.dtFineValidita = dtFineValidita;
    }

    
    public XMLGregorianCalendar getDtInizioValidita () {
        return dtInizioValidita;
    }

    
    public void setDtInizioValidita ( XMLGregorianCalendar dtInizioValidita ) {
        this.dtInizioValidita = dtInizioValidita;
    }

    

    

}
