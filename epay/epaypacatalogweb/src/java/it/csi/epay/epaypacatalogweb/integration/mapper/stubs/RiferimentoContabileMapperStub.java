/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.integration.mapper.stubs;

import java.util.Date;

public class RiferimentoContabileMapperStub {

    protected Integer annoAccertamento;

    protected Integer annoEsercizio;

    protected Integer numeroEsercizio;

    protected Integer annoProvvisorio;

    protected String codiceMacrotipo;

    protected String codiceTematica;

    protected String tipologia;

    protected String codiceTipologiaDatoSpecificoRiscossione;

    protected String codiceVoceEntrata;

    protected String datoSpecificoRiscossione;

    protected String categoria;

    protected String descrizioneCodiceVersamento;

    protected String descrizioneDatoSpecificoRiscossione;
    
    protected String descrizioneDatoSpecificoRiscossioneRifCont;
    

    protected String descrizioneErroreAggiornamento;

    protected String descrizioneMacrotipo;

    protected String descrizioneTematica;

    protected String descrizioneTipologiaDatoSpecificoRiscossione;

    protected String descrizioneVoceEntrata;

    protected Long id;

    protected Long idCodiceVersamento;

    protected String codiceCodiceVersamento;

    protected String livelloPdc;

    protected Integer numeroAccertamento;

    protected Integer numeroArticolo;

    protected String numeroCapitolo;

    protected Integer numeroProvvisorio;

    protected String titolo;

    protected String codiceStatoAggiornamento;

    protected String descrizioneStatoAggiornamento;
    
    
    
    protected String nomeEnte;
    
    protected String tipoEnte;


    protected Date dataFineValidita;

    protected Date dataInizioValidita;

    
    protected String datoSpecificoRiscossioneTassonomia;

    
    protected String ibanAppoggioCodiceVersamento;
    
    protected String ibanCodiceVersamento;
    
    protected boolean fatturaCodiceVersamento;
    
    protected String mailCodiceVersamento;
    
    protected String modalitaIntegrazioneCodiceVersamento;
    
    protected String noteCodiceVersamento;
    
    protected boolean statoCodiceVersamento;
    
    protected String tipologiaCodiceVersamento;


    protected Boolean inVita;

    protected Boolean flagPrincipale;
  
    protected String tipoServizio;
    
    protected Date dataFineValiditaCodiceTassonomico;

    protected Date dataInizioValiditaCodiceTassonomico;
    
    protected String tipoServizioTassonomia;
    
    

    @Override
    public String toString () {
        return "RiferimentoContabileMapperStub [annoAccertamento=" + annoAccertamento + ", annoEsercizio=" + annoEsercizio 
        	+ ", numeroEsercizio="+ numeroEsercizio + ", annoProvvisorio=" + annoProvvisorio  
        	+ ", nomeEnte=" + nomeEnte +", tipoEnte=" + tipoEnte 
        	+ ", codiceMacrotipo=" + codiceMacrotipo + ", codiceTematica=" + codiceTematica
            + ", tipologia=" + tipologia + ", codiceTipologiaDatoSpecificoRiscossione=" + codiceTipologiaDatoSpecificoRiscossione 
            + ", codiceVoceEntrata="+ codiceVoceEntrata + ", datoSpecificoRiscossione=" + datoSpecificoRiscossione 
            + ", datoSpecificoRiscossioneTassonomia=" + datoSpecificoRiscossioneTassonomia
            + ", categoria=" + categoria + ", descrizioneCodiceVersamento=" + descrizioneCodiceVersamento 
            + ", descrizioneDatoSpecificoRiscossione=" + descrizioneDatoSpecificoRiscossione + ", descrizioneErroreAggiornamento="
            + descrizioneErroreAggiornamento + ", descrizioneMacrotipo=" + descrizioneMacrotipo + ", descrizioneTematica=" + descrizioneTematica
            + ", descrizioneTipologiaDatoSpecificoRiscossione=" + descrizioneTipologiaDatoSpecificoRiscossione + ", descrizioneVoceEntrata="
            + descrizioneVoceEntrata + ", id=" + id + ", idCodiceVersamento=" + idCodiceVersamento + ", codiceCodiceVersamento=" + codiceCodiceVersamento
            + ", tipologiaCodiceVersamento=" + tipologiaCodiceVersamento  + ", statoCodiceVersamento=" + statoCodiceVersamento
            + ", mailCodiceVersamento=" + mailCodiceVersamento + ", noteCodiceVersamento=" + noteCodiceVersamento 
            + ", fatturaCodiceVersamento=" + fatturaCodiceVersamento + ", ibanCodiceVersamento=" + ibanCodiceVersamento 
            + ", ibanAppoggioCodiceVersamento=" + ibanAppoggioCodiceVersamento + ", modalitaIntegrazioneCodiceVersamento=" + modalitaIntegrazioneCodiceVersamento 
            + ", livelloPdc=" + livelloPdc + ", numeroAccertamento=" + numeroAccertamento + ", numeroArticolo=" + numeroArticolo + ", numeroCapitolo="
            + numeroCapitolo + ", numeroProvvisorio=" + numeroProvvisorio + ", titolo=" + titolo + ", codiceStatoAggiornamento=" + codiceStatoAggiornamento
            + ", descrizioneStatoAggiornamento=" + descrizioneStatoAggiornamento 
            + ", tipoServizio=" + tipoServizio 
            + "]";
         
    }

    public String getTipologia () {
        return tipologia;
    }

    public void setTipologia ( String tipologia ) {
        this.tipologia = tipologia;
    }

    public String getCategoria () {
        return categoria;
    }

    public void setCategoria ( String categoria ) {
        this.categoria = categoria;
    }

    public String getCodiceStatoAggiornamento () {
        return codiceStatoAggiornamento;
    }

    public void setCodiceStatoAggiornamento ( String codiceStatoAggiornamento ) {
        this.codiceStatoAggiornamento = codiceStatoAggiornamento;
    }

    public String getDescrizioneStatoAggiornamento () {
        return descrizioneStatoAggiornamento;
    }

    public void setDescrizioneStatoAggiornamento ( String descrizioneStatoAggiornamento ) {
        this.descrizioneStatoAggiornamento = descrizioneStatoAggiornamento;
    }

    public RiferimentoContabileMapperStub () {
        // TODO Auto-generated constructor stub
    }

    public String getCodiceCodiceVersamento () {
        return codiceCodiceVersamento;
    }

    public void setCodiceCodiceVersamento ( String codiceCodiceVersamento ) {
        this.codiceCodiceVersamento = codiceCodiceVersamento;
    }

    public Integer getNumeroEsercizio () {
        return numeroEsercizio;
    }

    public void setNumeroEsercizio ( Integer numeroEsercizio ) {
        this.numeroEsercizio = numeroEsercizio;
    }

    public Integer getAnnoAccertamento () {
        return annoAccertamento;
    }

    public void setAnnoAccertamento ( Integer annoAccertamento ) {
        this.annoAccertamento = annoAccertamento;
    }

    public Integer getAnnoEsercizio () {
        return annoEsercizio;
    }

    public void setAnnoEsercizio ( Integer annoEsercizio ) {
        this.annoEsercizio = annoEsercizio;
    }

    public Integer getAnnoProvvisorio () {
        return annoProvvisorio;
    }

    public void setAnnoProvvisorio ( Integer annoProvvisorio ) {
        this.annoProvvisorio = annoProvvisorio;
    }

    public String getCodiceMacrotipo () {
        return codiceMacrotipo;
    }

    public void setCodiceMacrotipo ( String codiceMacrotipo ) {
        this.codiceMacrotipo = codiceMacrotipo;
    }

    public String getCodiceTematica () {
        return codiceTematica;
    }

    public void setCodiceTematica ( String codiceTematica ) {
        this.codiceTematica = codiceTematica;
    }

    public String getCodiceTipologiaDatoSpecificoRiscossione () {
        return codiceTipologiaDatoSpecificoRiscossione;
    }

    public void setCodiceTipologiaDatoSpecificoRiscossione ( String codiceTipologiaDatoSpecificoRiscossione ) {
        this.codiceTipologiaDatoSpecificoRiscossione = codiceTipologiaDatoSpecificoRiscossione;
    }

    public String getCodiceVoceEntrata () {
        return codiceVoceEntrata;
    }

    public void setCodiceVoceEntrata ( String codiceVoceEntrata ) {
        this.codiceVoceEntrata = codiceVoceEntrata;
    }

    public String getDatoSpecificoRiscossione () {
        return datoSpecificoRiscossione;
    }

    public void setDatoSpecificoRiscossione ( String datoSpecificoRiscossione ) {
        this.datoSpecificoRiscossione = datoSpecificoRiscossione;
    }

    public String getDescrizioneCodiceVersamento () {
        return descrizioneCodiceVersamento;
    }

    public void setDescrizioneCodiceVersamento ( String descrizioneCodiceVersamento ) {
        this.descrizioneCodiceVersamento = descrizioneCodiceVersamento;
    }

    public String getDescrizioneDatoSpecificoRiscossione () {
        return descrizioneDatoSpecificoRiscossione;
    }

    public void setDescrizioneDatoSpecificoRiscossione ( String descrizioneDatoSpecificoRiscossione ) {
        this.descrizioneDatoSpecificoRiscossione = descrizioneDatoSpecificoRiscossione;
    }

    public String getDescrizioneErroreAggiornamento () {
        return descrizioneErroreAggiornamento;
    }

    public void setDescrizioneErroreAggiornamento ( String descrizioneErroreAggiornamento ) {
        this.descrizioneErroreAggiornamento = descrizioneErroreAggiornamento;
    }

    public String getDescrizioneMacrotipo () {
        return descrizioneMacrotipo;
    }

    public void setDescrizioneMacrotipo ( String descrizioneMacrotipo ) {
        this.descrizioneMacrotipo = descrizioneMacrotipo;
    }

    public String getDescrizioneTematica () {
        return descrizioneTematica;
    }

    public void setDescrizioneTematica ( String descrizioneTematica ) {
        this.descrizioneTematica = descrizioneTematica;
    }

    public String getDescrizioneTipologiaDatoSpecificoRiscossione () {
        return descrizioneTipologiaDatoSpecificoRiscossione;
    }

    public void setDescrizioneTipologiaDatoSpecificoRiscossione ( String descrizioneTipologiaDatoSpecificoRiscossione ) {
        this.descrizioneTipologiaDatoSpecificoRiscossione = descrizioneTipologiaDatoSpecificoRiscossione;
    }

    public String getDescrizioneVoceEntrata () {
        return descrizioneVoceEntrata;
    }

    public void setDescrizioneVoceEntrata ( String descrizioneVoceEntrata ) {
        this.descrizioneVoceEntrata = descrizioneVoceEntrata;
    }

    public Long getId () {
        return id;
    }

    public void setId ( Long id ) {
        this.id = id;
    }

    public Long getIdCodiceVersamento () {
        return idCodiceVersamento;
    }

    public void setIdCodiceVersamento ( Long idCodiceVersamento ) {
        this.idCodiceVersamento = idCodiceVersamento;
    }

    public String getLivelloPdc () {
        return livelloPdc;
    }

    public void setLivelloPdc ( String livelloPdc ) {
        this.livelloPdc = livelloPdc;
    }

    public Integer getNumeroAccertamento () {
        return numeroAccertamento;
    }

    public void setNumeroAccertamento ( Integer numeroAccertamento ) {
        this.numeroAccertamento = numeroAccertamento;
    }

    public Integer getNumeroArticolo () {
        return numeroArticolo;
    }

    public void setNumeroArticolo ( Integer numeroArticolo ) {
        this.numeroArticolo = numeroArticolo;
    }

    public String getNumeroCapitolo () {
        return numeroCapitolo;
    }

    public void setNumeroCapitolo ( String numeroCapitolo ) {
        this.numeroCapitolo = numeroCapitolo;
    }

    public Integer getNumeroProvvisorio () {
        return numeroProvvisorio;
    }

    public void setNumeroProvvisorio ( Integer numeroProvvisorio ) {
        this.numeroProvvisorio = numeroProvvisorio;
    }

    public String getTitolo () {
        return titolo;
    }

    public void setTitolo ( String titolo ) {
        this.titolo = titolo;
    }

	/**
	 * @return the nomeEnte
	 */
	public String getNomeEnte() {
		return nomeEnte;
	}

	/**
	 * @param nomeEnte the nomeEnte to set
	 */
	public void setNomeEnte(String nomeEnte) {
		this.nomeEnte = nomeEnte;
	}

	/**
	 * @return the tipoEnte
	 */
	public String getTipoEnte() {
		return tipoEnte;
	}

	/**
	 * @param tipoEnte the tipoEnte to set
	 */
	public void setTipoEnte(String tipoEnte) {
		this.tipoEnte = tipoEnte;
	}

	

	/**
	 * @return the datoSpecificoRiscossioneTassonomia
	 */
	public String getDatoSpecificoRiscossioneTassonomia() {
		return datoSpecificoRiscossioneTassonomia;
	}

	/**
	 * @param datoSpecificoRiscossioneTassonomia the datoSpecificoRiscossioneTassonomia to set
	 */
	public void setDatoSpecificoRiscossioneTassonomia(String datoSpecificoRiscossioneTassonomia) {
		this.datoSpecificoRiscossioneTassonomia = datoSpecificoRiscossioneTassonomia;
	}

	/**
	 * @return the ibanAppoggioCodiceVersamento
	 */
	public String getIbanAppoggioCodiceVersamento() {
		return ibanAppoggioCodiceVersamento;
	}

	/**
	 * @param ibanAppoggioCodiceVersamento the ibanAppoggioCodiceVersamento to set
	 */
	public void setIbanAppoggioCodiceVersamento(String ibanAppoggioCodiceVersamento) {
		this.ibanAppoggioCodiceVersamento = ibanAppoggioCodiceVersamento;
	}

	/**
	 * @return the ibanCodiceVersamento
	 */
	public String getIbanCodiceVersamento() {
		return ibanCodiceVersamento;
	}

	/**
	 * @param ibanCodiceVersamento the ibanCodiceVersamento to set
	 */
	public void setIbanCodiceVersamento(String ibanCodiceVersamento) {
		this.ibanCodiceVersamento = ibanCodiceVersamento;
	}

	/**
	 * @return the fatturaCodiceVersamento
	 */
	public boolean isFatturaCodiceVersamento() {
		return fatturaCodiceVersamento;
	}

	/**
	 * @param fatturaCodiceVersamento the fatturaCodiceVersamento to set
	 */
	public void setFatturaCodiceVersamento(boolean fatturaCodiceVersamento) {
		this.fatturaCodiceVersamento = fatturaCodiceVersamento;
	}

	/**
	 * @return the mailCodiceVersamento
	 */
	public String getMailCodiceVersamento() {
		return mailCodiceVersamento;
	}

	/**
	 * @param mailCodiceVersamento the mailCodiceVersamento to set
	 */
	public void setMailCodiceVersamento(String mailCodiceVersamento) {
		this.mailCodiceVersamento = mailCodiceVersamento;
	}

	/**
	 * @return the modalitaIntegrazioneCodiceVersamento
	 */
	public String getModalitaIntegrazioneCodiceVersamento() {
		return modalitaIntegrazioneCodiceVersamento;
	}

	/**
	 * @param modalitaIntegrazioneCodiceVersamento the modalitaIntegrazioneCodiceVersamento to set
	 */
	public void setModalitaIntegrazioneCodiceVersamento(String modalitaIntegrazioneCodiceVersamento) {
		this.modalitaIntegrazioneCodiceVersamento = modalitaIntegrazioneCodiceVersamento;
	}

	/**
	 * @return the noteCodiceVersamento
	 */
	public String getNoteCodiceVersamento() {
		return noteCodiceVersamento;
	}

	/**
	 * @param noteCodiceVersamento the noteCodiceVersamento to set
	 */
	public void setNoteCodiceVersamento(String noteCodiceVersamento) {
		this.noteCodiceVersamento = noteCodiceVersamento;
	}

	/**
	 * @return the statoCodiceVersamento
	 */
	public boolean isStatoCodiceVersamento() {
		return statoCodiceVersamento;
	}

	/**
	 * @param statoCodiceVersamento the statoCodiceVersamento to set
	 */
	public void setStatoCodiceVersamento(boolean statoCodiceVersamento) {
		this.statoCodiceVersamento = statoCodiceVersamento;
	}

	/**
	 * @return the tipologiaCodiceVersamento
	 */
	public String getTipologiaCodiceVersamento() {
		return tipologiaCodiceVersamento;
	}

	/**
	 * @param tipologiaCodiceVersamento the tipologiaCodiceVersamento to set
	 */
	public void setTipologiaCodiceVersamento(String tipologiaCodiceVersamento) {
		this.tipologiaCodiceVersamento = tipologiaCodiceVersamento;
	}

	/**
	 * @return the inVita
	 */
	public Boolean getInVita() {
		return inVita;
	}

	/**
	 * @param inVita the inVita to set
	 */
	public void setInVita(Boolean inVita) {
		this.inVita = inVita;
	}

	/**
	 * @return the flagPrincipale
	 */
	public Boolean getFlagPrincipale() {
		return flagPrincipale;
	}

	/**
	 * @param flagPrincipale the flagPrincipale to set
	 */
	public void setFlagPrincipale(Boolean flagPrincipale) {
		this.flagPrincipale = flagPrincipale;
	}

	/**
	 * @return the tipoServizio
	 */
	public String getTipoServizio() {
		return tipoServizio;
	}

	/**
	 * @param tipoServizio the tipoServizio to set
	 */
	public void setTipoServizio(String tipoServizio) {
		this.tipoServizio = tipoServizio;
	}

    
    /**
     * @return the tipoServizioTassonomia
     */
    public String getTipoServizioTassonomia () {
        return tipoServizioTassonomia;
    }
    
    /**
     * @param tipoServizioTassonomia the tipoServizioTassonomia to set
     */
    public void setTipoServizioTassonomia ( String tipoServizioTassonomia ) {
        this.tipoServizioTassonomia = tipoServizioTassonomia;
    }

    
    /**
     * @return the descrizioneDatoSpecificoRiscossioneRifCont
     */
    public String getDescrizioneDatoSpecificoRiscossioneRifCont () {
        return descrizioneDatoSpecificoRiscossioneRifCont;
    }

    
    /**
     * @param descrizioneDatoSpecificoRiscossioneRifCont the descrizioneDatoSpecificoRiscossioneRifCont to set
     */
    public void setDescrizioneDatoSpecificoRiscossioneRifCont ( String descrizioneDatoSpecificoRiscossioneRifCont ) {
        this.descrizioneDatoSpecificoRiscossioneRifCont = descrizioneDatoSpecificoRiscossioneRifCont;
    }


}
