/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypaweb.facade.sincronizzapagamentipec.dto;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "idPagamento",
    "iuvNumeroAvviso",
    "idStato",
    "descStato",
    "dataPagamento",
    "importoPagato",
    "pagamentoSpontaneo",
    "codVersamento",
    "descrCodVersamento",
    "codFiscaleEnte",
    "notePagamento",
    "nome",
    "cognome",
    "ragioneSociale",
    "codiceFiscalePagatore",
    "flagPersonaFisica",
    "dataScadenza",
    "causale", "requiresCostUpdate" //RDI-23
})
@XmlRootElement ( name = "SincronizzaPagamento" )
public class SincronizzaPagamento {

    @XmlElement ( name = "IdPagamento" )
    private Long idPagamento;

    @XmlElement ( name = "IuvNumeroAvviso" )
    private String iuvNumeroAvviso;

    @XmlElement ( name = "IdStato" )
    private Integer idStato;

    @XmlElement ( name = "DescStato" )
    private String descStato;

    @XmlSchemaType ( name = "dateTime" )
    @XmlElement ( name = "DataPagamento" )
    private XMLGregorianCalendar dataPagamento;

    @XmlElement ( name = "ImportoPagato" )
    private BigDecimal importoPagato;

    @XmlElement ( name = "PagamentoSpontaneo" )
    private Boolean pagamentoSpontaneo;

    @XmlElement ( name = "CodVersamento" )
    private String codVersamento;

    @XmlElement ( name = "DescrCodVersamento" )
    private String descrCodVersamento;

    @XmlElement ( name = "CodFiscaleEnte" )
    private String codFiscaleEnte;

    @XmlElement(name = "NotePagamento")
    protected String notePagamento;

    @XmlElement(name = "Nome")
    protected String nome;

    @XmlElement(name = "Cognome")
    protected String cognome;

    @XmlElement(name = "RagioneSociale")
    protected String ragioneSociale;

    @XmlElement(name = "CodiceFiscalePagatore")
    protected String codiceFiscalePagatore;

    @XmlElement(name = "FlagPersonaFisica")
    protected Boolean flagPersonaFisica;

    @XmlSchemaType ( name = "date" )
    @XmlElement ( name = "DataScadenza" )
    private XMLGregorianCalendar dataScadenza;

    //RDI-23
    @XmlElement ( name = "Causale" )
    protected String causale;
    
    @XmlElement ( name = "RequiresCostUpdate" )
    private Boolean requiresCostUpdate;

    public Long getIdPagamento () {
        return idPagamento;
    }

    public void setIdPagamento ( Long idPagamento ) {
        this.idPagamento = idPagamento;
    }

    public String getIuvNumeroAvviso () {
        return iuvNumeroAvviso;
    }

    public void setIuvNumeroAvviso ( String iuvNumeroAvviso ) {
        this.iuvNumeroAvviso = iuvNumeroAvviso;
    }

    public Integer getIdStato () {
        return idStato;
    }

    public void setIdStato ( Integer idStato ) {
        this.idStato = idStato;
    }

    public String getDescStato () {
        return descStato;
    }

    public void setDescStato ( String descStato ) {
        this.descStato = descStato;
    }

    public XMLGregorianCalendar getDataPagamento () {
        return dataPagamento;
    }

    public void setDataPagamento ( XMLGregorianCalendar dataPagamento ) {
        this.dataPagamento = dataPagamento;
    }

    public BigDecimal getImportoPagato () {
        return importoPagato;
    }

    public void setImportoPagato ( BigDecimal importoPagato ) {
        this.importoPagato = importoPagato;
    }

    public Boolean getPagamentoSpontaneo () {
        return pagamentoSpontaneo;
    }

    public void setPagamentoSpontaneo ( Boolean pagamentoSpontaneo ) {
        this.pagamentoSpontaneo = pagamentoSpontaneo;
    }

    /**
     * @return the codVersamento
     */
    public String getCodVersamento () {
        return codVersamento;
    }

    /**
     * @param codVersamento the codVersamento to set
     */
    public void setCodVersamento ( String codVersamento ) {
        this.codVersamento = codVersamento;
    }

    /**
     * @return the descrCodVersamento
     */
    public String getDescrCodVersamento () {
        return descrCodVersamento;
    }

    /**
     * @param descrCodVersamento the descrCodVersamento to set
     */
    public void setDescrCodVersamento ( String descrCodVersamento ) {
        this.descrCodVersamento = descrCodVersamento;
    }

    /**
     * @return the codFiscaleEnte
     */
    public String getCodFiscaleEnte () {
        return codFiscaleEnte;
    }

    /**
     * @param codFiscaleEnte the codFiscaleEnte to set
     */
    public void setCodFiscaleEnte ( String codFiscaleEnte ) {
        this.codFiscaleEnte = codFiscaleEnte;
    }

    /**
     * Gets the value of the notePagamento property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getNotePagamento() {
        return notePagamento;
    }

    /**
     * Sets the value of the notePagamento property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setNotePagamento(String notePagamento) {
        this.notePagamento = notePagamento;
    }

    /**
     * Gets the value of the nome property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getNome() {
        return nome;
    }

    /**
     * Sets the value of the nome property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Gets the value of the cognome property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * Sets the value of the cognome property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    /**
     * Gets the value of the ragioneSociale property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getRagioneSociale() {
        return ragioneSociale;
    }

    /**
     * Sets the value of the ragioneSociale property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setRagioneSociale(String ragioneSociale) {
        this.ragioneSociale = ragioneSociale;
    }

    /**
     * Gets the value of the codiceFiscalePagatore property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getCodiceFiscalePagatore() {
        return codiceFiscalePagatore;
    }

    /**
     * Sets the value of the codiceFiscalePagatore property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCodiceFiscalePagatore(String codiceFiscalePagatore) {
        this.codiceFiscalePagatore = codiceFiscalePagatore;
    }

    /**
     * Gets the value of the flagPersonaFisica property.
     *
     * @return
     *     possible object is
     *     {@link boolean }
     *
     */
    public Boolean getFlagPersonaFisica() {
        return flagPersonaFisica;
    }

    /**
     * Sets the value of the flagPersonaFisica property.
     *
     * @param value
     *     allowed object is
     *     {@link boolean }
     *
     */
    public void setFlagPersonaFisica(Boolean flagPersonaFisica) {
        this.flagPersonaFisica = flagPersonaFisica;
    }


    /**
     * @return the dataScadenza
     */
    public XMLGregorianCalendar getDataScadenza () {
        return dataScadenza;
    }


    /**
     * @param dataScadenza the dataScadenza to set
     */
    public void setDataScadenza ( XMLGregorianCalendar dataScadenza ) {
        this.dataScadenza = dataScadenza;
    }

    public String getCausale () {
        return causale;
    }

    public void setCausale ( String causale ) {
        this.causale = causale;
    }

	public Boolean getRequiresCostUpdate() {
		return requiresCostUpdate;
	}

	public void setRequiresCostUpdate(Boolean requiresCostUpdate) {
		this.requiresCostUpdate = requiresCostUpdate;
	}
    
    

}
