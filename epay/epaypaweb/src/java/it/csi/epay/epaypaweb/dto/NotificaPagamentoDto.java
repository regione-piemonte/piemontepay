/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto;

import static it.csi.epay.epaypaweb.util.Util.date2strdatetime;
import static it.csi.epay.epaypaweb.util.Util.quote;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/** dto facade <-> business <-> persistence */
public class NotificaPagamentoDto extends NotificaPagamentoLightDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long idFlusso;

	// dati della notifica di pagamento
	private Integer annoRiferimento;
	private Date dataScadenza;
	private String note;
	private String codAvviso;

	// dati del soggetto versante
	private SoggettoAnagraficoDto soggettoVersante;

	// dati della transazione psp
	private String idPsp;
	private String ragioneSocialePsp;
	private String codTipoVersamento;
	private String desTipoVersamento;
	private String idFlussoRendicontazionePsp;
	private Date dataAvvioTransazione;
	private String iur;
	private Date dataOraAutorizzazione;
	private String tipoSicurezza;
	private BigDecimal importoTransato;
	private BigDecimal importoCommissioni;
	private String datiSpecificiRiscossione;

	public NotificaPagamentoDto() {
		super();
	}

	public NotificaPagamentoDto(Long id) {
		super(id);
	}

	public Long getIdFlusso() {
		return idFlusso;
	}

	public void setIdFlusso(Long idFlusso) {
		this.idFlusso = idFlusso;
	}

	public Integer getAnnoRiferimento() {
		return annoRiferimento;
	}

	public void setAnnoRiferimento(Integer annoRiferimento) {
		this.annoRiferimento = annoRiferimento;
	}

	public Date getDataScadenza() {
		return dataScadenza;
	}

	public void setDataScadenza(Date dataScadenza) {
		this.dataScadenza = dataScadenza;
	}

	public SoggettoAnagraficoDto getSoggettoVersante() {
		return soggettoVersante;
	}

	public void setSoggettoVersante(SoggettoAnagraficoDto soggettoVersante) {
		this.soggettoVersante = soggettoVersante;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getCodAvviso() {
		return codAvviso;
	}

	public void setCodAvviso(String codAvviso) {
		this.codAvviso = codAvviso;
	}

	public String getIdPsp() {
		return idPsp;
	}

	public void setIdPsp(String idPsp) {
		this.idPsp = idPsp;
	}

	public String getRagioneSocialePsp() {
		return ragioneSocialePsp;
	}

	public void setRagioneSocialePsp(String ragioneSocialePsp) {
		this.ragioneSocialePsp = ragioneSocialePsp;
	}

	public String getCodTipoVersamento() {
		return codTipoVersamento;
	}

	public void setCodTipoVersamento(String codTipoVersamento) {
		this.codTipoVersamento = codTipoVersamento;
	}

	public String getDesTipoVersamento() {
		return desTipoVersamento;
	}

	public void setDesTipoVersamento(String desTipoVersamento) {
		this.desTipoVersamento = desTipoVersamento;
	}

	public String getIdFlussoRendicontazionePsp() {
		return idFlussoRendicontazionePsp;
	}

	public void setIdFlussoRendicontazionePsp(String idFlussoRendicontazionePsp) {
		this.idFlussoRendicontazionePsp = idFlussoRendicontazionePsp;
	}

	public String getIUR() {
		return iur;
	}

	public void setIUR(String iur) {
		this.iur = iur;
	}

	public Date getDataOraAutorizzazione() {
		return dataOraAutorizzazione;
	}

	public void setDataOraAutorizzazione(Date dataOraAutorizzazione) {
		this.dataOraAutorizzazione = dataOraAutorizzazione;
	}

	public String getTipoSicurezza() {
		return tipoSicurezza;
	}

	public void setTipoSicurezza(String tipoSicurezza) {
		this.tipoSicurezza = tipoSicurezza;
	}

	public BigDecimal getImportoTransato() {
		return importoTransato;
	}

	public void setImportoTransato(BigDecimal importoTransato) {
		this.importoTransato = importoTransato;
	}

	public BigDecimal getImportoCommissioni() {
		return importoCommissioni;
	}

	public void setImportoCommissioni(BigDecimal importoCommissioni) {
		this.importoCommissioni = importoCommissioni;
	}

	public Date getDataAvvioTransazione() {
		return dataAvvioTransazione;
	}

	public void setDataAvvioTransazione(Date dataAvvioTransazione) {
		this.dataAvvioTransazione = dataAvvioTransazione;
	}

	public String getDatiSpecificiRiscossione() {
		return datiSpecificiRiscossione;
	}

	public void setDatiSpecificiRiscossione(String datiSpecificiRiscossione) {
		this.datiSpecificiRiscossione = datiSpecificiRiscossione;
	}

	@Override
	public String toString() {
		final String COMMA = ", ";
		StringBuilder s = new StringBuilder();
		s.append("{ ");
		s.append("idFlusso:").append(idFlusso).append(COMMA);
		s.append("notificaPagamentoDto:{ ");
		s.append(super.toSuperString()).append(COMMA);
		s.append("annoRiferimento:").append(annoRiferimento).append(COMMA);
		s.append("dataScadenza:").append(date2strdatetime(dataScadenza)).append(COMMA);
		s.append("note:").append(quote(note)).append(COMMA);
		s.append("codAvviso:").append(quote(codAvviso));
		s.append(" }").append(COMMA);
		s.append("soggettoVersanteDto:").append(soggettoVersante).append(COMMA);
		s.append("transazionePspDto:{ ");
		s.append("idPsp:").append(quote(idPsp)).append(COMMA);
		s.append("ragioneSocialePsp:").append(quote(ragioneSocialePsp)).append(COMMA);
		s.append("codTipoVersamento:").append(quote(codTipoVersamento)).append(COMMA);
		s.append("desTipoVersamento:").append(quote(desTipoVersamento)).append(COMMA);
		s.append("idFlussoRendicontazionePsp:").append(quote(idFlussoRendicontazionePsp)).append(COMMA);
		s.append("dataOraTransazione:").append(date2strdatetime(dataAvvioTransazione)).append(COMMA);
		s.append("iur:").append(quote(iur)).append(COMMA);
		s.append("dataOraAutorizzazione:").append(date2strdatetime(dataOraAutorizzazione)).append(COMMA);
		s.append("tipoSicurezza:").append(quote(tipoSicurezza)).append(COMMA);
		s.append("importoTransato:").append(importoTransato).append(COMMA);
		s.append("importoCommissioni:").append(importoCommissioni);
		s.append(" } }");
		return s.toString();
	}

}
