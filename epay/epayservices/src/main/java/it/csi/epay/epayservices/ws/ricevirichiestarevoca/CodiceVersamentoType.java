/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.ws.ricevirichiestarevoca;



import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="InvioFlussi" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="Email" type="{http://www.csi.it/epay/epaywso/types}EMailAddress" minOccurs="0"/&gt;
 *         &lt;element name="Annullato" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="ModalitaDiIntegrazione" type="{http://www.csi.it/epay/epaywso/coopapplicativapec}ModalitaDiIntegrazioneType"/&gt;
 *         &lt;element name="ApplicationId" type="{http://www.csi.it/epay/epaywso/coopapplicativapec}String35Type"/&gt;
 *         &lt;element name="IbanAppoggio" type="{http://www.csi.it/epay/epaywso/types}IBANType" minOccurs="0"/&gt;
 *         &lt;element name="BicAppoggio" type="{http://www.csi.it/epay/epaywso/types}String35Type" minOccurs="0"/&gt;
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
    "invioFlussi",
    "email",
    "annullato",
    "modalitaDiIntegrazione",
    "applicationId",
    "ibanAppoggio",
    "bicAppoggio"
    
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
    @XmlSchemaType(name = "string")
    protected TipoPagamentoType tipoPagamento;
    @XmlElement(name = "Iban")
    protected String iban;
    @XmlElement(name = "Bic")
    protected String bic;
    @XmlElement(name = "InvioFlussi")
    protected boolean invioFlussi;
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

	@XmlElement(name = "Email")
    protected String email;
    @XmlElement(name = "Annullato")
    protected boolean annullato;
    @XmlElement(name = "ModalitaDiIntegrazione", required = true)
    @XmlSchemaType(name = "string")
    protected ModalitaDiIntegrazioneType modalitaDiIntegrazione;
    @XmlElement ( name = "ApplicationId", required = true )
    @XmlSchemaType ( name = "string" )
    protected String applicationId;
    @XmlElement(name = "IbanAppoggio")
    protected String ibanAppoggio;
    @XmlElement(name = "BicAppoggio")
    protected String bicAppoggio;

    /**
     * Recupera il valore della proprietacfEnte.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getCFEnte() {
        return cfEnte;
    }

    /**
     * Imposta il valore della proprietacfEnte.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCFEnte(String value) {
        this.cfEnte = value;
    }

    /**
     * Recupera il valore della proprietacodice.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getCodice() {
        return codice;
    }

    /**
     * Imposta il valore della proprietacodice.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCodice(String value) {
        this.codice = value;
    }

    /**
     * Recupera il valore della proprietadescrizione.
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
     * Imposta il valore della proprietadescrizione.
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
     * Recupera il valore della proprietavoceEntrata.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getVoceEntrata() {
        return voceEntrata;
    }

    /**
     * Imposta il valore della proprietavoceEntrata.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setVoceEntrata(String value) {
        this.voceEntrata = value;
    }

    /**
     * Recupera il valore della proprietatipoPagamento.
     *
     * @return
     *     possible object is
     *     {@link TipoPagamentoType }
     *
     */
    public TipoPagamentoType getTipoPagamento() {
        return tipoPagamento;
    }

    /**
     * Imposta il valore della proprietatipoPagamento.
     *
     * @param value
     *     allowed object is
     *     {@link TipoPagamentoType }
     *
     */
    public void setTipoPagamento(TipoPagamentoType value) {
        this.tipoPagamento = value;
    }

    /**
     * Recupera il valore della proprietaiban.
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
     * Imposta il valore della proprietaiban.
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
     * Recupera il valore della proprietabic.
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
     * Imposta il valore della proprietabic.
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
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getEmail() {
        return email;
    }

    /**
     * Imposta il valore della proprietaemail.
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
     * @return
     *     possible object is
     *     {@link ModalitaDiIntegrazioneType }
     *
     */
    public ModalitaDiIntegrazioneType getModalitaDiIntegrazione() {
        return modalitaDiIntegrazione;
    }

    /**
     * Imposta il valore della proprietamodalitaDiIntegrazione.
     *
     * @param value
     *     allowed object is
     *     {@link ModalitaDiIntegrazioneType }
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

}
