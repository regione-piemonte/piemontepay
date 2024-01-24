/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto.riferimentocontabile;

import it.csi.epay.epaypacatalogsrv.dto.ParentInput;

import java.util.Date;

@SuppressWarnings ( "unused" )
public class InserisciRiferimentoContabileInput extends ParentInput {

	private static final long serialVersionUID = 1L;

	private Long idCodiceVersamento;

	private Integer annoAccertamento;

	private Integer annoEsercizio;

	private Date dataInizioValidita;

	private Date dataFineValidita;

	private Long idTassonomia;

	private String livelloPdc;

	private Integer numeroAccertamento;

	private Integer numeroArticolo;

	private String numeroCapitolo;

	private String titolo;

	private String categoria;

	private String tipologia;

	private Boolean flagElementiMultibeneficiario;

	private Boolean flagAssociaRifContSecondario;

	private Boolean flagAssociaRifContPrimario;

	private Long idRifContabileSecondarioAssociato;

	private String datoSpecificoRiscossione;

	private String descrizioneDatoSpecificoRiscossione;

	private String codiceTributo;

	public Long getIdRifContabileSecondarioAssociato() {
		return idRifContabileSecondarioAssociato;
	}

	public void setIdRifContabileSecondarioAssociato(Long idRifContabileSecondarioAssociato) {
		this.idRifContabileSecondarioAssociato = idRifContabileSecondarioAssociato;
	}

	public Date getDataFineValidita () {
		return dataFineValidita;
	}

	public void setDataFineValidita ( Date dataFineValidita ) {
		this.dataFineValidita = dataFineValidita;
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

	public Long getIdCodiceVersamento () {
		return idCodiceVersamento;
	}

	public void setIdCodiceVersamento ( Long idCodiceVersamento ) {
		this.idCodiceVersamento = idCodiceVersamento;
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

	public String getDatoSpecificoRiscossione() {
		return datoSpecificoRiscossione;
	}

	public void setDatoSpecificoRiscossione(String datoSpecificoRiscossione) {
		this.datoSpecificoRiscossione = datoSpecificoRiscossione;
	}

	public String getDescrizioneDatoSpecificoRiscossione() {
		return descrizioneDatoSpecificoRiscossione;
	}

	public void setDescrizioneDatoSpecificoRiscossione(String descrizioneDatoSpecificoRiscossione) {
		this.descrizioneDatoSpecificoRiscossione = descrizioneDatoSpecificoRiscossione;
	}

	public String getCodiceTributo () {
		return codiceTributo;
	}

	public void setCodiceTributo ( String codiceTributo ) {
		this.codiceTributo = codiceTributo;
	}

	@Override public String toString () {
		return "InserisciRiferimentoContabileInput{" +
						"idCodiceVersamento=" + idCodiceVersamento +
						", annoAccertamento=" + annoAccertamento +
						", annoEsercizio=" + annoEsercizio +
						", dataInizioValidita=" + dataInizioValidita +
						", dataFineValidita=" + dataFineValidita +
						", idTassonomia=" + idTassonomia +
						", livelloPdc='" + livelloPdc + '\'' +
						", numeroAccertamento=" + numeroAccertamento +
						", numeroArticolo=" + numeroArticolo +
						", numeroCapitolo='" + numeroCapitolo + '\'' +
						", titolo='" + titolo + '\'' +
						", categoria='" + categoria + '\'' +
						", tipologia='" + tipologia + '\'' +
						", flagElementiMultibeneficiario=" + flagElementiMultibeneficiario +
						", flagAssociaRifContSecondario=" + flagAssociaRifContSecondario +
						", flagAssociaRifContPrimario=" + flagAssociaRifContPrimario +
						", idRifContabileSecondarioAssociato=" + idRifContabileSecondarioAssociato +
						", datoSpecificoRiscossione='" + datoSpecificoRiscossione + '\'' +
						", descrizioneDatoSpecificoRiscossione='" + descrizioneDatoSpecificoRiscossione + '\'' +
						", codiceTributo='" + codiceTributo + '\'' +
						'}';
	}
}
