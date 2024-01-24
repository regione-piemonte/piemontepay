/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.model.codiceversamento;

import it.csi.epay.epaypacatalogweb.model.GenericVO;

import java.util.List;


public class RisultatoRicercaCodiceVersamentoVO extends GenericVO {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String descrizione;

	private String codiceTematica;

	private String descrizioneTematica;

	private String codiceMacrotipo;

	private String descrizioneMacrotipo;

	private String codiceVoceEntrata;

	private Long idVoceEntrata;

	private String descrizioneVoceEntrata;

	private String codiceTipoPagamento;

	private String descrizioneTipoPagamento;

	private String iban;

	private String bic;

	private Boolean flagCodiceCorrentePostaleTesoreria;

	private Boolean flagCodiceCorrentePostaleAppoggio;
	private Boolean flagPresenzaBollettinoPostale;

	private String ibanAppoggio;

	private String bicAppoggio;
	private String email;

	private Boolean flagInvioFlussi;

	private Boolean tematicaEsclusa;

	private List<RisultatoRicercaCodiceVersamentoVO> codiciVersamentoCollegati;

	private Boolean inserimentoConsentito;

	private Boolean fattura;

	private Long idPadre;

	private String multibeneficiario;

	@Override
	public String toString() {
		return "RisultatoRicercaCodiceVersamentoVO [id=" + id + ", descrizione=" + descrizione + ", codiceTematica=" + codiceTematica + ", descrizioneTematica="
				+ descrizioneTematica + ", codiceMacrotipo=" + codiceMacrotipo + ", descrizioneMacrotipo=" + descrizioneMacrotipo + ", codiceVoceEntrata="
				+ codiceVoceEntrata + ", idVoceEntrata=" + idVoceEntrata + ", descrizioneVoceEntrata=" + descrizioneVoceEntrata + ", codiceTipoPagamento="
				+ codiceTipoPagamento + ", descrizioneTipoPagamento=" + descrizioneTipoPagamento + ", iban=" + iban + ", bic=" + bic + ", email=" + email
				+ ", flagInvioFlussi=" + flagInvioFlussi + ", codiciVersamentoCollegati=" + codiciVersamentoCollegati + ", inserimentoConsentito="
				+ inserimentoConsentito + "]";
	}

	public Boolean getInserimentoConsentito() {
		return inserimentoConsentito;
	}

	public void setInserimentoConsentito(Boolean inserimentoConsentito) {
		this.inserimentoConsentito = inserimentoConsentito;
	}

	public List<RisultatoRicercaCodiceVersamentoVO> getCodiciVersamentoCollegati() {
		return codiciVersamentoCollegati;
	}

	public void setCodiciVersamentoCollegati(List<RisultatoRicercaCodiceVersamentoVO> codiciVersamentoCollegati) {
		this.codiciVersamentoCollegati = codiciVersamentoCollegati;
	}

	public String getBic() {
		return bic;
	}

	public void setBic(String bic) {
		this.bic = bic;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getFlagInvioFlussi() {
		return flagInvioFlussi;
	}

	public void setFlagInvioFlussi(Boolean flagInvioFlussi) {
		this.flagInvioFlussi = flagInvioFlussi;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String getDescrizione() {
		return descrizione;
	}

	@Override
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getCodiceVoceEntrata() {
		return codiceVoceEntrata;
	}

	public void setCodiceVoceEntrata(String codiceVoceEntrata) {
		this.codiceVoceEntrata = codiceVoceEntrata;
	}

	public String getCodiceTematica() {
		return codiceTematica;
	}

	public void setCodiceTematica(String codiceTematica) {
		this.codiceTematica = codiceTematica;
	}

	public String getDescrizioneTematica() {
		return descrizioneTematica;
	}

	public void setDescrizioneTematica(String descrizioneTematica) {
		this.descrizioneTematica = descrizioneTematica;
	}

	public String getCodiceMacrotipo() {
		return codiceMacrotipo;
	}

	public void setCodiceMacrotipo(String codiceMacrotipo) {
		this.codiceMacrotipo = codiceMacrotipo;
	}

	public String getDescrizioneMacrotipo() {
		return descrizioneMacrotipo;
	}

	public void setDescrizioneMacrotipo(String descrizioneMacrotipo) {
		this.descrizioneMacrotipo = descrizioneMacrotipo;
	}

	public Long getIdVoceEntrata() {
		return idVoceEntrata;
	}

	public void setIdVoceEntrata(Long idVoceEntrata) {
		this.idVoceEntrata = idVoceEntrata;
	}

	public String getDescrizioneVoceEntrata() {
		return descrizioneVoceEntrata;
	}

	public void setDescrizioneVoceEntrata(String descrizioneVoceEntrata) {
		this.descrizioneVoceEntrata = descrizioneVoceEntrata;
	}

	public String getCodiceTipoPagamento() {
		return codiceTipoPagamento;
	}

	public void setCodiceTipoPagamento(String codiceTipoPagamento) {
		this.codiceTipoPagamento = codiceTipoPagamento;
	}

	public String getDescrizioneTipoPagamento() {
		return descrizioneTipoPagamento;
	}

	public void setDescrizioneTipoPagamento(String descrizioneTipoPagamento) {
		this.descrizioneTipoPagamento = descrizioneTipoPagamento;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}


	public Boolean getTematicaEsclusa() {
		return tematicaEsclusa;
	}


	public void setTematicaEsclusa(Boolean tematicaEsclusa) {
		this.tematicaEsclusa = tematicaEsclusa;
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

	public Boolean getFlagPresenzaBollettinoPostale() {
		return flagPresenzaBollettinoPostale;
	}

	public void setFlagPresenzaBollettinoPostale(Boolean flagPresenzaBollettinoPostale) {
		this.flagPresenzaBollettinoPostale = flagPresenzaBollettinoPostale;
	}

	public Boolean getFattura() {
		return fattura;
	}

	public void setFattura(Boolean fattura) {
		this.fattura = fattura;
	}

	public Long getIdPadre() {
		return idPadre;
	}

	public void setIdPadre(Long idPadre) {
		this.idPadre = idPadre;
	}

	public String getMultibeneficiario() {
		return multibeneficiario;
	}

	public void setMultibeneficiario(String multibeneficiario) {
		this.multibeneficiario = multibeneficiario;
	}
}
