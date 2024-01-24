/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayservices.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;


/**
 *
 */
@SuppressWarnings ( "unused" )
public class GetIuvMultibeneficiarioChiamanteEsternoInput extends GetIuvCommonChiamanteEsternoInput implements Serializable {

	private static final long serialVersionUID = 1L;

	private String tipoPagamento;

	@JsonProperty ( "importoTotaleEntePrimario" )
	private BigDecimal importoPrincipale;

	@JsonProperty ( "importoTotaleEntiSecondari" )
	private BigDecimal importoSecondarioAltroEnte;

	private BigDecimal importoTotale;

	private Integer numeroPosizioniDebitorie;

	@JsonProperty ( "componentiPagamentoEntiSecondari" )
	private List<ComponentePagamentoEnteSecondaroInput> componentiPagamentoEntiSecondari;

	@JsonProperty ( "componentiPagamentoEntePrimario" )
	private List<AccessoChiamanteEsternoSincronoComponentePagamentoInput> componentiPagamento;

	public BigDecimal getImportoPrincipale () {
		return importoPrincipale;
	}

	public void setImportoPrincipale ( BigDecimal importoPrincipale ) {
		this.importoPrincipale = importoPrincipale;
	}

	public BigDecimal getImportoSecondarioAltroEnte () {
		return importoSecondarioAltroEnte;
	}

	public void setImportoSecondarioAltroEnte ( BigDecimal importoSecondarioAltroEnte ) {
		this.importoSecondarioAltroEnte = importoSecondarioAltroEnte;
	}

	public BigDecimal getImportoTotale () {
		return importoTotale;
	}

	public void setImportoTotale ( BigDecimal importoTotale ) {
		this.importoTotale = importoTotale;
	}

	public List<ComponentePagamentoEnteSecondaroInput> getComponentiPagamentoEntiSecondari () {
		return componentiPagamentoEntiSecondari;
	}

	public void setComponentiPagamentoEntiSecondari (
					List<ComponentePagamentoEnteSecondaroInput> componentiPagamentoEntiSecondari ) {
		this.componentiPagamentoEntiSecondari = componentiPagamentoEntiSecondari;
	}

	/**
	 * @return the numeroPosizioniDebitorie
	 */
	public Integer getNumeroPosizioniDebitorie () {
		return numeroPosizioniDebitorie;
	}

	/**
	 * @param numeroPosizioniDebitorie the numeroPosizioniDebitorie to set
	 */
	public void setNumeroPosizioniDebitorie ( Integer numeroPosizioniDebitorie ) {
		this.numeroPosizioniDebitorie = numeroPosizioniDebitorie;
	}

	/**
	 * @return the componentiPagamento
	 */
	public List<AccessoChiamanteEsternoSincronoComponentePagamentoInput> getComponentiPagamento () {
		return componentiPagamento;
	}

	/**
	 * @param componentiPagamento the componentiPagamento to set
	 */
	public void setComponentiPagamento ( List<AccessoChiamanteEsternoSincronoComponentePagamentoInput> componentiPagamento ) {
		this.componentiPagamento = componentiPagamento;
	}

	/**
	 * @return the tipoPagamento
	 */
	public String getTipoPagamento () {
		return tipoPagamento;
	}

	/**
	 * @param tipoPagamento the tipoPagamento to set
	 */
	public void setTipoPagamento ( String tipoPagamento ) {
		this.tipoPagamento = tipoPagamento;
	}

}
