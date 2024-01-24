/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.model.codiceversamento;

import java.io.Serializable;


public class RicercaCodiceVersamentoFiltroVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String descrizione;

	private String codiceMacrotipo;

	private String codiceTematica;

	private String codiceVoceEntrata;

	private String descrizioneVoceEntrata;

	private String iban;

	private Boolean flagCodiceCorrentePostaleAppoggio;

	private Boolean flagCodiceCorrentePostaleTesoreria;

	private Boolean flagPresenzaBollettinoPostale;

	private Boolean codiceMultibeneficiario;

	private String codiceMultibeneficiarioCheckbox;

	@Override
	public String toString () {
		return "RicercaCodiceVersamentoFiltroVO [id=" + id + ", descrizione=" + descrizione + ", codiceMacrotipo=" + codiceMacrotipo + ", codiceTematica="
						+ codiceTematica + ", codiceVoceEntrata=" + codiceVoceEntrata + ", descrizioneVoceEntrata=" + descrizioneVoceEntrata + ", iban=" + iban + "]";
	}

	public Long getId () {
		return id;
	}

	public void setId ( Long id ) {
		this.id = id;
	}

	public String getDescrizione () {
		return descrizione;
	}

	public void setDescrizione ( String descrizione ) {
		this.descrizione = descrizione;
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

	public String getIban () {
		return iban;
	}

	public void setIban ( String iban ) {
		this.iban = iban;
	}


	/**
	 * @return the flagCodiceCorrentePostaleAppoggio
	 */
	public Boolean getFlagCodiceCorrentePostaleAppoggio() {
		return flagCodiceCorrentePostaleAppoggio;
	}

	/**
	 * @param flagCodiceCorrentePostaleAppoggio the flagCodiceCorrentePostaleAppoggio to set
	 */
	public void setFlagCodiceCorrentePostaleAppoggio(Boolean flagCodiceCorrentePostaleAppoggio) {
		this.flagCodiceCorrentePostaleAppoggio = flagCodiceCorrentePostaleAppoggio;
	}

	public Boolean getFlagPresenzaBollettinoPostale () {
		return flagPresenzaBollettinoPostale;
	}

	public void setFlagPresenzaBollettinoPostale ( Boolean flagPresenzaBollettinoPostale ) {
		this.flagPresenzaBollettinoPostale = flagPresenzaBollettinoPostale;
	}

	/**
	 * @return the flagCodiceCorrentePostaleTesoreria
	 */
	public Boolean getFlagCodiceCorrentePostaleTesoreria() {
		return flagCodiceCorrentePostaleTesoreria;
	}

	/**
	 * @param flagCodiceCorrentePostaleTesoreria the flagCodiceCorrentePostaleTesoreria to set
	 */
	public void setFlagCodiceCorrentePostaleTesoreria(Boolean flagCodiceCorrentePostaleTesoreria) {
		this.flagCodiceCorrentePostaleTesoreria = flagCodiceCorrentePostaleTesoreria;
	}

	public Boolean getCodiceMultibeneficiario () {
		return codiceMultibeneficiario;
	}

	public void setCodiceMultibeneficiario ( Boolean codiceMultibeneficiario ) {
		this.codiceMultibeneficiario = codiceMultibeneficiario;
	}

	public String getCodiceMultibeneficiarioCheckbox () {
		return codiceMultibeneficiarioCheckbox;
	}

	public void setCodiceMultibeneficiarioCheckbox( String codiceMultibeneficiarioCheckbox ) {
		this.codiceMultibeneficiarioCheckbox = codiceMultibeneficiarioCheckbox;
	}
}
