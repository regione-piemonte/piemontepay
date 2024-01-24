/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.facade.cooppec.dto;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>
 * Classe Java per CodiceVersamentoType complex type.
 *
 * <p>
 * Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 *
 * <pre>
 * &lt;complexType name="CodiceVersamentoType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="CFEnte" type="{http://www.csi.it/epay/epaywso/types}String16Type"/&gt;
 *         &lt;element name="Codice" type="{http://www.csi.it/epay/epaywso/types}CodiceVersamentoType"/&gt;
 *         &lt;element name="Descrizione" type="{http://www.csi.it/epay/epaywso/types}String16Type" minOccurs="0"/&gt;
 *         &lt;element name="VoceEntrata" type="{http://www.csi.it/epay/epaywso/types}String16Type"/&gt;
 *         &lt;element name="TipoPagamento" type="{http://www.csi.it/epay/epaywso/coopapplicativapec}TipoPagamentoType"/&gt;
 *         &lt;element name="Iban" type="{http://www.csi.it/epay/epaywso/types}IBANType" minOccurs="0"/&gt;
 *         &lt;element name="Bic" type="{http://www.csi.it/epay/epaywso/types}String35Type" minOccurs="0"/&gt;
  *         &lt;element name="IbanAppoggio" type="{http://www.csi.it/epay/epaywso/types}IBANType" minOccurs="0"/&gt;
 *         &lt;element name="BicAppoggio" type="{http://www.csi.it/epay/epaywso/types}String35Type" minOccurs="0"/&gt;
 *         &lt;element name="InvioFlussi" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="Email" type="{http://www.csi.it/epay/epaywso/types}EMailAddress" minOccurs="0"/&gt;
 *         &lt;element name="Annullato" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="ModalitaDiIntegrazione" type="{http://www.csi.it/epay/epaywso/coopapplicativapec}ModalitaDiIntegrazioneType"/&gt;
 *         &lt;element name="ApplicationId" type="{http://www.csi.it/epay/epaywso/coopapplicativapec}String35Type"/&gt;
 *         &lt;element name="FlagPresenzaBollettinoPostale" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="FlagCodiceCorrentePostaleAppoggio" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="FlagCodiceCorrentePostaleTesoreria" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="Fattura" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="TipoMultibeneficiario" type="{http://www.csi.it/epay/epaywso/coopapplicativapec}TipoMultibeneficiarioType"/>
 *         &lt;element name="CFEnteSecondario"type="{http://www.csi.it/epay/epaywso/types}String16Type" minOccurs="0"/>
 *         &lt;element name="CodiceVersamentoSecondario" type="{http://www.csi.it/epay/epaywso/types}CodiceVersamentoType" minOccurs="0"/>
 *         &lt;element name="FlagInvioNotificatore" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="FlagPersonalizzazioneCov" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="DescrizioneTextCov" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Passphrase" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="CredenzialiPnd" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="UrlAttualizzazionePnd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FlagPersonalizzazioneCov" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="DataInizioValidita" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="DataFineValidita" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CodiceVersamentoType", propOrder = {
    "cfEnte",
    "codice",
    "descrizione",
    "voceEntrata",
    "tipoPagamento",
    "iban",
    "bic",
    "ibanAppoggio",
    "bicAppoggio",
    "invioFlussi",
    "email",
    "annullato",
    "modalitaDiIntegrazione",
    "applicationId",
    "flagPresenzaBollettinoPostale",
    "flagCodiceCorrentePostaleAppoggio",
    "flagCodiceCorrentePostaleTesoreria",
    "fattura",
    "tipoMultibeneficiario",
    "cfEnteSecondario",
    "codiceVersamentoSecondario",
    "flagInvioNotificatore",
    "flagPersonalizzazioneCov",
    "descrizioneTextCov",
    "passphrase",
    "credenzialiPnd",
    "urlAttualizzazionePnd",
    "flagVisualizzaDaSportello",
    "dataInizioValidita",
    "dataFineValidita"
})
public class CodiceVersamentoType {



	@XmlElement(name = "CFEnte", required = true)
    protected String cfEnte;
    @XmlElement(name = "Codice", required = true)
    protected String codice;
    @XmlElement(name = "Descrizione")
    protected String descrizione;
    @XmlElement(name = "VoceEntrata", required = true)
    protected String voceEntrata;
    @XmlElement(name = "TipoPagamento", required = true)
    @XmlSchemaType ( name = "string" )
    protected TipoPagamentoType tipoPagamento;
    @XmlElement(name = "Iban")
    protected String iban;
    @XmlElement(name = "Bic")
    protected String bic;
    @XmlElement(name = "IbanAppoggio")
    protected String ibanAppoggio;
    @XmlElement(name = "BicAppoggio")
    protected String bicAppoggio;
    @XmlElement(name = "InvioFlussi")
    protected boolean invioFlussi;
    @XmlElement(name = "Email")
    protected String email;
    @XmlElement(name = "Annullato")
    protected boolean annullato;
    @XmlElement(name = "ModalitaDiIntegrazione", required = true)
    @XmlSchemaType ( name = "string" )
    protected ModalitaDiIntegrazioneType modalitaDiIntegrazione;
    @XmlElement ( name = "ApplicationId", required = true )
    @XmlSchemaType ( name = "string" )
    protected String applicationId;
	@XmlElement ( name = "FlagPresenzaBollettinoPostale" )
    protected boolean flagPresenzaBollettinoPostale;
    @XmlElement ( name = "FlagCodiceCorrentePostaleAppoggio" )
    protected boolean flagCodiceCorrentePostaleAppoggio;
    @XmlElement ( name = "FlagCodiceCorrentePostaleTesoreria" )
    protected boolean flagCodiceCorrentePostaleTesoreria;
    @XmlElement(name = "Fattura")
    protected boolean fattura;
    @XmlElement(name = "TipoMultibeneficiario", required = true)
    protected TipoMultibeneficiarioType tipoMultibeneficiario;
    @XmlElement(name = "CFEnteSecondario")
    protected String cfEnteSecondario;
    @XmlElement(name = "CodiceVersamentoSecondario")
    protected String codiceVersamentoSecondario;

    @XmlElement(name = "FlagInvioNotificatore")
    protected Boolean flagInvioNotificatore;
    @XmlElement(name = "FlagPersonalizzazioneCov")
    protected Boolean flagPersonalizzazioneCov;
    @XmlElement(name = "DescrizioneTextCov")
    protected String descrizioneTextCov;
    @XmlElement(name = "Passphrase")
    protected byte[] passphrase;
    
    @XmlElement(name = "CredenzialiPnd")
    protected byte[] credenzialiPnd;
    @XmlElement(name = "UrlAttualizzazionePnd")
    protected String urlAttualizzazionePnd;
    

    
    
    @XmlElement(name = "FlagVisualizzaDaSportello")
    protected boolean flagVisualizzaDaSportello;
    @XmlElement(name = "DataInizioValidita")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataInizioValidita;
    @XmlElement(name = "DataFineValidita")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataFineValidita;
    
    
    /**
	 * @return the flagPresenzaBollettinoPostale
	 */
	public boolean isFlagPresenzaBollettinoPostale() {
		return flagPresenzaBollettinoPostale;
	}

	/**
	 * @param flagPresenzaBollettinoPostale the flagPresenzaBollettinoPostale to set
	 */
	public void setFlagPresenzaBollettinoPostale(boolean flagPresenzaBollettinoPostale) {
		this.flagPresenzaBollettinoPostale = flagPresenzaBollettinoPostale;
	}

	/**
	 * @return the flagCodiceCorrentePostaleAppoggio
	 */
	public boolean isFlagCodiceCorrentePostaleAppoggio() {
		return flagCodiceCorrentePostaleAppoggio;
	}

	/**
	 * @param flagCodiceCorrentePostaleAppoggio the flagCodiceCorrentePostaleAppoggio to set
	 */
	public void setFlagCodiceCorrentePostaleAppoggio(boolean flagCodiceCorrentePostaleAppoggio) {
		this.flagCodiceCorrentePostaleAppoggio = flagCodiceCorrentePostaleAppoggio;
	}

	/**
	 * @return the flagCodiceCorrentePostaleTesoreria
	 */
	public boolean isFlagCodiceCorrentePostaleTesoreria() {
		return flagCodiceCorrentePostaleTesoreria;
	}

	/**
	 * @param flagCodiceCorrentePostaleTesoreria the flagCodiceCorrentePostaleTesoreria to set
	 */
	public void setFlagCodiceCorrentePostaleTesoreria(boolean flagCodiceCorrentePostaleTesoreria) {
		this.flagCodiceCorrentePostaleTesoreria = flagCodiceCorrentePostaleTesoreria;
	}

	/**
     * Recupera il valore della proprietacfEnte.
     *
     * @return possible object is {@link String }
     *
     */
    public String getCFEnte() {
        return cfEnte;
    }

    /**
     * Imposta il valore della proprietacfEnte.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setCFEnte(String value) {
        this.cfEnte = value;
    }

    /**
     * Recupera il valore della proprietacodice.
     *
     * @return possible object is {@link String }
     *
     */
    public String getCodice() {
        return codice;
    }

    /**
     * Imposta il valore della proprietacodice.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setCodice(String value) {
        this.codice = value;
    }

    /**
     * Recupera il valore della proprietadescrizione.
     *
     * @return possible object is {@link String }
     *
     */
    public String getDescrizione() {
        return descrizione;
    }

    /**
     * Imposta il valore della proprietadescrizione.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setDescrizione(String value) {
        this.descrizione = value;
    }

    /**
     * Recupera il valore della proprietavoceEntrata.
     *
     * @return possible object is {@link String }
     *
     */
    public String getVoceEntrata() {
        return voceEntrata;
    }

    /**
     * Imposta il valore della proprietavoceEntrata.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setVoceEntrata(String value) {
        this.voceEntrata = value;
    }

    /**
     * Recupera il valore della proprietatipoPagamento.
     *
     * @return possible object is {@link TipoPagamentoType }
     *
     */
    public TipoPagamentoType getTipoPagamento() {
        return tipoPagamento;
    }

    /**
     * Imposta il valore della proprietatipoPagamento.
     *
     * @param value allowed object is {@link TipoPagamentoType }
     *
     */
    public void setTipoPagamento(TipoPagamentoType value) {
        this.tipoPagamento = value;
    }

    /**
     * Recupera il valore della proprietaiban.
     *
     * @return possible object is {@link String }
     *
     */
    public String getIban() {
        return iban;
    }

    /**
     * Imposta il valore della proprietaiban.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setIban(String value) {
        this.iban = value;
    }

    /**
     * Recupera il valore della proprietabic.
     *
     * @return possible object is {@link String }
     *
     */
    public String getBic() {
        return bic;
    }

    /**
     * Imposta il valore della proprietabic.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setBic(String value) {
        this.bic = value;
    }

    /**
     * Recupera il valore della proprietainvioFlussi.
     *
     */
    public boolean isInvioFlussi() {
        return invioFlussi;
    }

    /**
     * Imposta il valore della proprietainvioFlussi.
     *
     */
    public void setInvioFlussi(boolean value) {
        this.invioFlussi = value;
    }

    /**
     * Recupera il valore della proprietaemail.
     *
     * @return possible object is {@link String }
     *
     */
    public String getEmail() {
        return email;
    }
    
    /**
	 * @return the ibanAppoggio
	 */
	public String getIbanAppoggio() {
		return ibanAppoggio;
	}

	/**
	 * @param ibanAppoggio the ibanAppoggio to set
	 */
	public void setIbanAppoggio(String ibanAppoggio) {
		this.ibanAppoggio = ibanAppoggio;
	}

	/**
	 * @return the bicAppoggio
	 */
	public String getBicAppoggio() {
		return bicAppoggio;
	}

	/**
	 * @param bicAppoggio the bicAppoggio to set
	 */
	public void setBicAppoggio(String bicAppoggio) {
		this.bicAppoggio = bicAppoggio;
	}

    /**
     * Imposta il valore della proprietaemail.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Recupera il valore della proprietaannullato.
     *
     */
    public boolean isAnnullato() {
        return annullato;
    }

    /**
     * Imposta il valore della proprietaannullato.
     *
     */
    public void setAnnullato(boolean value) {
        this.annullato = value;
    }

    /**
     * Recupera il valore della proprietamodalitaDiIntegrazione.
     *
     * @return possible object is {@link ModalitaDiIntegrazioneType }
     *
     */
    public ModalitaDiIntegrazioneType getModalitaDiIntegrazione() {
        return modalitaDiIntegrazione;
    }

    /**
     * Imposta il valore della proprietamodalitaDiIntegrazione.
     *
     * @param value allowed object is {@link ModalitaDiIntegrazioneType }
     *
     */
    public void setModalitaDiIntegrazione(ModalitaDiIntegrazioneType value) {
        this.modalitaDiIntegrazione = value;
    }

    public String getCfEnte () {
        return cfEnte;
    }

    public void setCfEnte ( String cfEnte ) {
        this.cfEnte = cfEnte;
    }

    public String getApplicationId () {
        return applicationId;
    }

    public void setApplicationId ( String applicationId ) {
        this.applicationId = applicationId;
    }

    
    public boolean isFattura () {
        return fattura;
    }

    
    public void setFattura ( boolean fattura ) {
        this.fattura = fattura;
    }
    
    /**
     * Gets the value of the tipoMultibeneficiario property.
     * 
     * @return
     *     possible object is
     *     {@link TipoMultibeneficiarioType }
     *     
     */
    public TipoMultibeneficiarioType getTipoMultibeneficiario() {
        return tipoMultibeneficiario;
    }

    /**
     * Sets the value of the tipoMultibeneficiario property.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoMultibeneficiarioType }
     *     
     */
    public void setTipoMultibeneficiario(TipoMultibeneficiarioType value) {
        this.tipoMultibeneficiario = value;
    }

    

    
    public String getCfEnteSecondario () {
        return cfEnteSecondario;
    }

    
    public void setCfEnteSecondario ( String cfEnteSecondario ) {
        this.cfEnteSecondario = cfEnteSecondario;
    }

    /**
     * Gets the value of the codiceVersamentoSecondario property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceVersamentoSecondario() {
        return codiceVersamentoSecondario;
    }

    /**
     * Sets the value of the codiceVersamentoSecondario property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceVersamentoSecondario(String value) {
        this.codiceVersamentoSecondario = value;
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
     * @return the passphrase
     */
    public byte [] getPassphrase () {
        return passphrase;
    }

    
    /**
     * @param passphrase the passphrase to set
     */
    public void setPassphrase ( byte [] passphrase ) {
        this.passphrase = passphrase;
    }

    
    /**
     * @return the flagInvioNotificatore
     */
    public Boolean getFlagInvioNotificatore () {
        return flagInvioNotificatore;
    }

    
    /**
     * @param flagInvioNotificatore the flagInvioNotificatore to set
     */
    public void setFlagInvioNotificatore ( Boolean flagInvioNotificatore ) {
        this.flagInvioNotificatore = flagInvioNotificatore;
    }

    
    /**
     * @return the credenzialiPnd
     */
    public byte [] getCredenzialiPnd () {
        return credenzialiPnd;
    }

    
    /**
     * @param credenzialiPnd the credenzialiPnd to set
     */
    public void setCredenzialiPnd ( byte [] credenzialiPnd ) {
        this.credenzialiPnd = credenzialiPnd;
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
    
        
    /**
     * @return the flagVisualizzaDaSportello
     */
    public boolean isFlagVisualizzaDaSportello () {
        return flagVisualizzaDaSportello;
    }

    
    /**
     * @param flagVisualizzaDaSportello the flagVisualizzaDaSportello to set
     */
    public void setFlagVisualizzaDaSportello ( boolean flagVisualizzaDaSportello ) {
        this.flagVisualizzaDaSportello = flagVisualizzaDaSportello;
    }

    
    /**
     * @return the dataInizioValidita
     */
    public XMLGregorianCalendar getDataInizioValidita () {
        return dataInizioValidita;
    }

    
    /**
     * @param dataInizioValidita the dataInizioValidita to set
     */
    public void setDataInizioValidita ( XMLGregorianCalendar dataInizioValidita ) {
        this.dataInizioValidita = dataInizioValidita;
    }
    
    /**
     * @return the dataFineValidita
     */
    public XMLGregorianCalendar getDataFineValidita () {
        return dataFineValidita;
    }

    
    /**
     * @param dataFineValidita the dataFineValidita to set
     */
    public void setDataFineValidita ( XMLGregorianCalendar dataFineValidita ) {
        this.dataFineValidita = dataFineValidita;
    }
    
    

}
