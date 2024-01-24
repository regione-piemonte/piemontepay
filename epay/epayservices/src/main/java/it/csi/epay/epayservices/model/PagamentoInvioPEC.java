/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;


public class PagamentoInvioPEC implements Serializable {

    private static final long serialVersionUID = -520928713011664413L;

    private Long idPagamento;

    private String iuvNumeroAvviso;

    private Integer idStato;

    private String descStato;

    private Timestamp dataPagamento;

    private BigDecimal importoPagato;

    private Timestamp dataPagamentoRt;

    private BigDecimal importoPagatoRt;

    private Timestamp dataPagamentoEr;

    private BigDecimal importoPagatoEr;

    private Boolean pagamentoSpontaneo;

    private String codVersamento;

    private String descrCodVersamento;

    private String codFiscaleEnte;
    
    private String notePagamento;
    
    private String nome;
    
    private String cognome; 
    
    private String ragioneSociale;
    
    private String codiceFiscalePagatore;
    
    private Boolean flagPersonaFisica;
    
    private Date dataScadenza;
    
    private String causale;
    
    private Boolean requiresCostUpdate;
    
    public PagamentoInvioPEC () {
        // NOP
    }

    /**
     * creazione oggetto con i campi valorizzati
     * 
     * @param idPagamento
     * @param iuvNumeroAvviso
     * @param idStato
     * @param descStato
     * @param dataPagamento
     * @param importoPagato
     * @param dataPagamentoRt
     * @param importoPagatoRt
     * @param dataPagamentoEr
     * @param importoPagatoEr
     * @param pagamentoSpontaneo
     * @param codVersamento
     * @param descrCodVersamento
     * @param codFiscaleEnte
     * @param notePagamento
     * @param nome
     * @param cognome
     * @param ragioneSociale
     * @param codiceFiscalePagatore
     * @param flagPersonaFisica
     * @param dataScadenza
     * @param requiresCostUpdate
     */
    public PagamentoInvioPEC ( Long idPagamento, String iuvNumeroAvviso, Integer idStato, String descStato, Timestamp dataPagamento, BigDecimal importoPagato,
        Timestamp dataPagamentoRt, BigDecimal importoPagatoRt, Timestamp dataPagamentoEr, BigDecimal importoPagatoEr, Boolean pagamentoSpontaneo, String codVersamento, String descrCodVersamento, String codFiscaleEnte,
        String notePagamento, String nome, String cognome, String ragioneSociale, String codiceFiscalePagatore, Boolean flagPersonaFisica, Date dataScadenza, String causale,
        Boolean requiresCostUpdate) {
        super ();
        this.idPagamento = idPagamento;
        this.iuvNumeroAvviso = iuvNumeroAvviso;
        this.idStato = idStato;
        this.descStato = descStato;
        this.dataPagamento = dataPagamento;
        this.importoPagato = importoPagato;
        this.dataPagamentoRt = dataPagamentoRt;
        this.importoPagatoRt = importoPagatoRt;
        this.dataPagamentoEr = dataPagamentoEr;
        this.importoPagatoEr = importoPagatoEr;
        this.pagamentoSpontaneo = pagamentoSpontaneo;
        this.codVersamento = codVersamento;
        this.descrCodVersamento = descrCodVersamento;
        this.codFiscaleEnte = codFiscaleEnte;
        this.notePagamento = notePagamento;
        this.nome = nome;
        this.cognome = cognome;
        this.ragioneSociale = ragioneSociale;
        this.codiceFiscalePagatore = codiceFiscalePagatore;
        this.flagPersonaFisica = flagPersonaFisica;
        this.dataScadenza = dataScadenza;
        this.causale = causale;
        this.requiresCostUpdate = requiresCostUpdate;
    }

    public Timestamp getDataPagamentoRt () {
        return dataPagamentoRt;
    }

    public void setDataPagamentoRt ( Timestamp dataPagamentoRt ) {
        this.dataPagamentoRt = dataPagamentoRt;
    }

    public BigDecimal getImportoPagatoRt () {
        return importoPagatoRt;
    }

    public void setImportoPagatoRt ( BigDecimal importoPagatoRt ) {
        this.importoPagatoRt = importoPagatoRt;
    }

    public Timestamp getDataPagamentoEr () {
        return dataPagamentoEr;
    }

    public void setDataPagamentoEr ( Timestamp dataPagamentoEr ) {
        this.dataPagamentoEr = dataPagamentoEr;
    }

    public BigDecimal getImportoPagatoEr () {
        return importoPagatoEr;
    }

    public void setImportoPagatoEr ( BigDecimal importoPagatoEr ) {
        this.importoPagatoEr = importoPagatoEr;
    }

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

    public Timestamp getDataPagamento () {
        return dataPagamento;
    }

    public void setDataPagamento ( Timestamp dataPagamento ) {
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
     * @return the notePagamento
     */
    public String getNotePagamento () {
        return notePagamento;
    }

    /**
     * @param notePagamento the notePagamento to set
     */
    public void setNotePagamento ( String notePagamento ) {
        this.notePagamento = notePagamento;
    }
    
    /**
     * @return the nome
     */
    public String getNome () {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome ( String nome ) {
        this.nome = nome;
    }
    
    /**
     * @return the cognome
     */
    public String getCognome () {
        return cognome;
    }

    /**
     * @param cognome the cognome to set
     */
    public void setCognome ( String cognome ) {
        this.cognome = cognome;
    }
    
    /**
     * @return the ragioneSociale
     */
    public String getRagioneSociale () {
        return ragioneSociale;
    }

    /**
     * @param ragioneSociale the ragioneSociale to set
     */
    public void setRagioneSociale ( String ragioneSociale ) {
        this.ragioneSociale = ragioneSociale;
    }
    
    /**
     * @return the codiceFiscalePagatore
     */
    public String getCodiceFiscalePagatore () {
        return codiceFiscalePagatore;
    }

    /**
     * @param codiceFiscalePagatore the codiceFiscalePagatore to set
     */
    public void setCodiceFiscalePagatore ( String codiceFiscalePagatore ) {
        this.codiceFiscalePagatore = codiceFiscalePagatore;
    }
    
    /**
     * @return the flagPersonaFisica
     */
    public boolean isFlagPersonaFisica () {
        return flagPersonaFisica;
    }

    /**
     * @param flagPersonaFisica the flagPersonaFisica to set
     */
    public void setFlagPersonaFisica ( Boolean flagPersonaFisica ) {
        this.flagPersonaFisica = flagPersonaFisica;
    }

    /**
     * @return the dataScadenza
     */
    public Date getDataScadenza () {
        return dataScadenza;
    }

    
    /**
     * @param dataScadenza the dataScadenza to set
     */
    public void setDataScadenza ( Date dataScadenza ) {
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
