/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.model.riferimentocontabile;

import it.csi.epay.epaypacatalogweb.model.GenericVO;

import java.util.Date;

@SuppressWarnings ( "unused" )
public class ModificaRiferimentoContabileVO extends GenericVO {

	private static final long serialVersionUID = 1L;

	private Long id;

	private Long idCodiceVersamento;

	private Integer annoAccertamento;

	private Integer annoEsercizio;

	private Date dataInizioValidita;

	private Date dataFineValidita;

	private String datoSpecificoRiscossione;

	private String descrizioneDatoSpecificoRiscossione;

	private String codiceDatoSpecificoRiscossione;

	private Long idTassonomia;


	private Date dataFineValiditaCodiceTassonomico;

	private Date dataInizioValiditaCodiceTassonomico;

	private String livelloPdc;

	private Integer numeroAccertamento;

	private Integer numeroArticolo;

	private String numeroCapitolo;

	private String titolo;

	private String categoria;

	private String codiceTipologiaDatoSpecificoRiscossione;

	private String tipologia;

	private String codiceCodiceVersamento;

	private String descrizioneCodiceVersamento;

	private String codiceVoceEntrata;

	private String descrizioneVoceEntrata;

	private String codiceStatoAggiornamento;

	private String descrizioneStatoAggiornamento;

	private String descrizioneTipologiaDatoSpecificoRiscossione;

	private String tipoPagamento;

	private Boolean flagPrincipale;

	private Boolean flagValoreInizialeIdCodiceVersamento;

	private Boolean flagElementiMultibeneficiario;

	private Boolean flagAssociaRifContSecondario;

	private Boolean flagAssociaRifContPrimario;

	private String enteSecondarioAssociato;

	private String covSecondarioAssociato;

	private Long idRifContabileSecondarioAssociato;

	private Boolean flagAssociaRifContSecondarioValue;

	private Boolean flagAssociaRifContPrimarioValue;

	private String tipologiaModifica;
	
	private String rifContabileSecondarioAssociato;

	private String codiceTributo;

	public Boolean getFlagPrincipale () {
		return flagPrincipale;
	}

	public void setFlagPrincipale ( Boolean flagPrincipale ) {
		this.flagPrincipale = flagPrincipale;
	}

	public Date getDataFineValidita () {
		return dataFineValidita;
	}

	public void setDataFineValidita ( Date dataFineValidita ) {
		this.dataFineValidita = dataFineValidita;
	}

	public String getDescrizioneTipologiaDatoSpecificoRiscossione () {
		return descrizioneTipologiaDatoSpecificoRiscossione;
	}

	public void setDescrizioneTipologiaDatoSpecificoRiscossione ( String descrizioneTipologiaDatoSpecificoRiscossione ) {
		this.descrizioneTipologiaDatoSpecificoRiscossione = descrizioneTipologiaDatoSpecificoRiscossione;
	}

	public String getDescrizioneCodiceVersamento () {
		return descrizioneCodiceVersamento;
	}

	public void setDescrizioneCodiceVersamento ( String descrizioneCodiceVersamento ) {
		this.descrizioneCodiceVersamento = descrizioneCodiceVersamento;
	}

	public String getCodiceCodiceVersamento () {
		return codiceCodiceVersamento;
	}

	public void setCodiceCodiceVersamento ( String codiceCodiceVersamento ) {
		this.codiceCodiceVersamento = codiceCodiceVersamento;
	}

	public String getCodiceVoceEntrata () {
		return codiceVoceEntrata;
	}

	public void setCodiceVoceEntrata ( String codiceVoceEntrata ) {
		this.codiceVoceEntrata = codiceVoceEntrata;
	}

	public String getDescrizioneVoceEntrata () {
		return descrizioneVoceEntrata;
	}

	public void setDescrizioneVoceEntrata ( String descrizioneVoceEntrata ) {
		this.descrizioneVoceEntrata = descrizioneVoceEntrata;
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

	public Long getIdCodiceVersamento () {
		return idCodiceVersamento;
	}

	public void setIdCodiceVersamento ( Long idCodiceVersamento ) {
		this.idCodiceVersamento = idCodiceVersamento;
	}

	@Override
	public Long getId () {
		return id;
	}

	@Override
	public void setId ( Long id ) {
		this.id = id;
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

	public Date getDataInizioValidita () {
		return dataInizioValidita;
	}

	public void setDataInizioValidita ( Date dataInizioValidita ) {
		this.dataInizioValidita = dataInizioValidita;
	}

	public String getDatoSpecificoRiscossione () {
		return datoSpecificoRiscossione;
	}

	public void setDatoSpecificoRiscossione ( String datoSpecificoRiscossione ) {
		this.datoSpecificoRiscossione = datoSpecificoRiscossione;
	}

	public String getDescrizioneDatoSpecificoRiscossione () {
		return descrizioneDatoSpecificoRiscossione;
	}

	public void setDescrizioneDatoSpecificoRiscossione ( String descrizioneDatoSpecificoRiscossione ) {
		this.descrizioneDatoSpecificoRiscossione = descrizioneDatoSpecificoRiscossione;
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

	public String getTitolo () {
		return titolo;
	}

	public void setTitolo ( String titolo ) {
		this.titolo = titolo;
	}

	public String getCodiceTipologiaDatoSpecificoRiscossione () {
		return codiceTipologiaDatoSpecificoRiscossione;
	}

	public void setCodiceTipologiaDatoSpecificoRiscossione ( String codiceTipologiaDatoSpecificoRiscossione ) {
		this.codiceTipologiaDatoSpecificoRiscossione = codiceTipologiaDatoSpecificoRiscossione;
	}

	public String getCategoria () {
		return categoria;
	}

	public void setCategoria ( String categoria ) {
		this.categoria = categoria;
	}

	public String getTipologia () {
		return tipologia;
	}

	public void setTipologia ( String tipologia ) {
		this.tipologia = tipologia;
	}




	/**
	 * @return the dataFineValiditaCodiceTassonomico
	 */
	public Date getDataFineValiditaCodiceTassonomico() {
		return dataFineValiditaCodiceTassonomico;
	}

	/**
	 * @param dataFineValiditaCodiceTassonomico the dataFineValiditaCodiceTassonomico to set
	 */
	public void setDataFineValiditaCodiceTassonomico(Date dataFineValiditaCodiceTassonomico) {
		this.dataFineValiditaCodiceTassonomico = dataFineValiditaCodiceTassonomico;
	}

	/**
	 * @return the dataInizioValiditaCodiceTassonomico
	 */
	public Date getDataInizioValiditaCodiceTassonomico() {
		return dataInizioValiditaCodiceTassonomico;
	}

	/**
	 * @param dataInizioValiditaCodiceTassonomico the dataInizioValiditaCodiceTassonomico to set
	 */
	public void setDataInizioValiditaCodiceTassonomico(Date dataInizioValiditaCodiceTassonomico) {
		this.dataInizioValiditaCodiceTassonomico = dataInizioValiditaCodiceTassonomico;
	}



	/**
	 * @return the codiceDatoSpecificoRiscossione
	 */
	public String getCodiceDatoSpecificoRiscossione() {
		return codiceDatoSpecificoRiscossione;
	}

	/**
	 * @param codiceDatoSpecificoRiscossione the codiceDatoSpecificoRiscossione to set
	 */
	public void setCodiceDatoSpecificoRiscossione(String codiceDatoSpecificoRiscossione) {
		this.codiceDatoSpecificoRiscossione = codiceDatoSpecificoRiscossione;
	}


	/**
	 * @return the idTassonomia
	 */
	public Long getIdTassonomia() {
		return idTassonomia;
	}

	/**
	 * @param idTassonomia the idTassonomia to set
	 */
	public void setIdTassonomia(Long idTassonomia) {
		this.idTassonomia = idTassonomia;
	}

	/**
	 * @return the tipoPagamento
	 */
	public String getTipoPagamento() {
		return tipoPagamento;
	}

	/**
	 * @param tipoPagamento the tipoPagamento to set
	 */
	public void setTipoPagamento(String tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}

	public Boolean getFlagValoreInizialeIdCodiceVersamento() {
		return flagValoreInizialeIdCodiceVersamento;
	}

	public void setFlagValoreInizialeIdCodiceVersamento(Boolean flagValoreInizialeIdCodiceVersamento) {
		this.flagValoreInizialeIdCodiceVersamento = flagValoreInizialeIdCodiceVersamento;
	}

	public Boolean getFlagElementiMultibeneficiario() {
		return flagElementiMultibeneficiario;
	}

	public void setFlagElementiMultibeneficiario(Boolean flagElementiMultibeneficiario) {
		this.flagElementiMultibeneficiario = flagElementiMultibeneficiario;
	}

	public Boolean getFlagAssociaRifContSecondario() {
		return flagAssociaRifContSecondario;
	}

	public void setFlagAssociaRifContSecondario(Boolean flagAssociaRifContSecondario) {
		this.flagAssociaRifContSecondario = flagAssociaRifContSecondario;
	}



	public Boolean getFlagAssociaRifContPrimario() {
		return flagAssociaRifContPrimario;
	}

	public void setFlagAssociaRifContPrimario(Boolean flagAssociaRifContPrimario) {
		this.flagAssociaRifContPrimario = flagAssociaRifContPrimario;
	}



	public String getEnteSecondarioAssociato() {
		return enteSecondarioAssociato;
	}

	public void setEnteSecondarioAssociato(String enteSecondarioAssociato) {
		this.enteSecondarioAssociato = enteSecondarioAssociato;
	}

	public String getCovSecondarioAssociato() {
		return covSecondarioAssociato;
	}

	public void setCovSecondarioAssociato(String covSecondarioAssociato) {
		this.covSecondarioAssociato = covSecondarioAssociato;
	}

	public Long getIdRifContabileSecondarioAssociato() {
		return idRifContabileSecondarioAssociato;
	}

	public void setIdRifContabileSecondarioAssociato(Long idRifContabileSecondarioAssociato) {
		this.idRifContabileSecondarioAssociato = idRifContabileSecondarioAssociato;
	}


	public Boolean getFlagAssociaRifContSecondarioValue() {
		return flagAssociaRifContSecondarioValue;
	}

	public void setFlagAssociaRifContSecondarioValue(Boolean flagAssociaRifContSecondarioValue) {
		this.flagAssociaRifContSecondarioValue = flagAssociaRifContSecondarioValue;
	}

	public Boolean getFlagAssociaRifContPrimarioValue() {
		return flagAssociaRifContPrimarioValue;
	}

	public void setFlagAssociaRifContPrimarioValue(Boolean flagAssociaRifContPrimarioValue) {
		this.flagAssociaRifContPrimarioValue = flagAssociaRifContPrimarioValue;
	}

		public String getTipologiaModifica () {
			return tipologiaModifica;
		}

		public void setTipologiaModifica ( String tipologiaModifica ) {
			this.tipologiaModifica = tipologiaModifica;
		}
		
		

		
        /**
         * @return the rifContabileSecondarioAssociato
         */
        public String getRifContabileSecondarioAssociato () {
            return rifContabileSecondarioAssociato;
        }

        
        /**
         * @param rifContabileSecondarioAssociato the rifContabileSecondarioAssociato to set
         */
        public void setRifContabileSecondarioAssociato ( String rifContabileSecondarioAssociato ) {
            this.rifContabileSecondarioAssociato = rifContabileSecondarioAssociato;
        }

	public String getCodiceTributo () {
		return codiceTributo;
	}

	public void setCodiceTributo ( String codiceTributo ) {
		this.codiceTributo = codiceTributo;
	}

	@Override public String toString () {
		return "ModificaRiferimentoContabileVO{" +
						"id=" + id +
						", idCodiceVersamento=" + idCodiceVersamento +
						", annoAccertamento=" + annoAccertamento +
						", annoEsercizio=" + annoEsercizio +
						", dataInizioValidita=" + dataInizioValidita +
						", dataFineValidita=" + dataFineValidita +
						", datoSpecificoRiscossione='" + datoSpecificoRiscossione + '\'' +
						", descrizioneDatoSpecificoRiscossione='" + descrizioneDatoSpecificoRiscossione + '\'' +
						", codiceDatoSpecificoRiscossione='" + codiceDatoSpecificoRiscossione + '\'' +
						", idTassonomia=" + idTassonomia +
						", dataFineValiditaCodiceTassonomico=" + dataFineValiditaCodiceTassonomico +
						", dataInizioValiditaCodiceTassonomico=" + dataInizioValiditaCodiceTassonomico +
						", livelloPdc='" + livelloPdc + '\'' +
						", numeroAccertamento=" + numeroAccertamento +
						", numeroArticolo=" + numeroArticolo +
						", numeroCapitolo='" + numeroCapitolo + '\'' +
						", titolo='" + titolo + '\'' +
						", categoria='" + categoria + '\'' +
						", codiceTipologiaDatoSpecificoRiscossione='" + codiceTipologiaDatoSpecificoRiscossione + '\'' +
						", tipologia='" + tipologia + '\'' +
						", codiceCodiceVersamento='" + codiceCodiceVersamento + '\'' +
						", descrizioneCodiceVersamento='" + descrizioneCodiceVersamento + '\'' +
						", codiceVoceEntrata='" + codiceVoceEntrata + '\'' +
						", descrizioneVoceEntrata='" + descrizioneVoceEntrata + '\'' +
						", codiceStatoAggiornamento='" + codiceStatoAggiornamento + '\'' +
						", descrizioneStatoAggiornamento='" + descrizioneStatoAggiornamento + '\'' +
						", descrizioneTipologiaDatoSpecificoRiscossione='" + descrizioneTipologiaDatoSpecificoRiscossione + '\'' +
						", tipoPagamento='" + tipoPagamento + '\'' +
						", flagPrincipale=" + flagPrincipale +
						", flagValoreInizialeIdCodiceVersamento=" + flagValoreInizialeIdCodiceVersamento +
						", flagElementiMultibeneficiario=" + flagElementiMultibeneficiario +
						", flagAssociaRifContSecondario=" + flagAssociaRifContSecondario +
						", flagAssociaRifContPrimario=" + flagAssociaRifContPrimario +
						", enteSecondarioAssociato='" + enteSecondarioAssociato + '\'' +
						", covSecondarioAssociato='" + covSecondarioAssociato + '\'' +
						", idRifContabileSecondarioAssociato=" + idRifContabileSecondarioAssociato +
						", flagAssociaRifContSecondarioValue=" + flagAssociaRifContSecondarioValue +
						", flagAssociaRifContPrimarioValue=" + flagAssociaRifContPrimarioValue +
						", tipologiaModifica='" + tipologiaModifica + '\'' +
						", rifContabileSecondarioAssociato='" + rifContabileSecondarioAssociato + '\'' +
						", codiceTributo='" + codiceTributo + '\'' +
						'}';
	}
}
