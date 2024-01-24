/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.dto;

import static it.csi.epay.epaywsosrv.util.Util.date2strdatetime;
import static it.csi.epay.epaywsosrv.util.Util.quote;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import it.csi.epay.epaywsosrv.enumeration.StatoRichiestaEnum;
import it.csi.epay.epaywsosrv.enumeration.TipoRichiestaEnum;

/** dto facade <-> business <-> persistence */
public class RichiestaLightDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String messageUUID;
	private String idMessaggio;
	private String codFiscaleEnte;
	private TipoRichiestaEnum tipoRichiestaEnum;
	private StatoRichiestaEnum statoRichiestaEnum;
	private Boolean pagamentoSpontaneo;
	private String codVersamento;
	private Date dataInvioAlDestinatario;
	private String codEsito;
	private Integer numTotaleElementi;
	private BigDecimal importoTotale;

	public RichiestaLightDto(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public String getMessageUUID() {
		return messageUUID;
	}

	public void setMessageUUID(String messageUUID) {
		this.messageUUID = messageUUID;
	}

	public String getCodFiscaleEnte() {
		return codFiscaleEnte;
	}

	public void setCodFiscaleEnte(String codFiscaleEnte) {
		this.codFiscaleEnte = codFiscaleEnte;
	}

	public TipoRichiestaEnum getTipoRichiestaEnum() {
		return tipoRichiestaEnum;
	}

	public void setTipoRichiestaEnum(TipoRichiestaEnum tipoRichiestaEnum) {
		this.tipoRichiestaEnum = tipoRichiestaEnum;
	}

	public StatoRichiestaEnum getStatoRichiestaEnum() {
		return statoRichiestaEnum;
	}

	public void setStatoRichiestaEnum(StatoRichiestaEnum statoRichiestaEnum) {
		this.statoRichiestaEnum = statoRichiestaEnum;
	}

	public Boolean getPagamentoSpontaneo() {
		return pagamentoSpontaneo;
	}

	public void setPagamentoSpontaneo(Boolean pagamentoSpontaneo) {
		this.pagamentoSpontaneo = pagamentoSpontaneo;
	}

	public String getCodVersamento() {
		return codVersamento;
	}

	public void setCodVersamento(String codVersamento) {
		this.codVersamento = codVersamento;
	}

	public Date getDataInvioAlDestinatario() {
		return dataInvioAlDestinatario;
	}

	public void setDataInvioAlDestinatario(Date dataInvioAlDestinatario) {
		this.dataInvioAlDestinatario = dataInvioAlDestinatario;
	}

	public String getCodEsito() {
		return codEsito;
	}

	public void setCodEsito(String codEsito) {
		this.codEsito = codEsito;
	}

	public Integer getNumTotaleElementi() {
		return numTotaleElementi;
	}

	public void setNumTotaleElementi(Integer numTotaleElementi) {
		this.numTotaleElementi = numTotaleElementi;
	}

	public BigDecimal getImportoTotale() {
		return importoTotale;
	}

	public void setImportoTotale(BigDecimal importoTotale) {
		this.importoTotale = importoTotale;
	}

	public String getIdMessaggio() {
		return idMessaggio;
	}

	public void setIdMessaggio(String idMessaggio) {
		this.idMessaggio = idMessaggio;
	}

	@Override
	public String toString() {
		final String COMMA = ", ";
		StringBuilder s = new StringBuilder();
		s.append("{ ");
		s.append("id:").append(id).append(COMMA);
		s.append("idMessage:").append(quote(idMessaggio)).append(COMMA);
		s.append("messageUUID:").append(quote(messageUUID)).append(COMMA);
		s.append("codFiscaleEnte:").append(quote(codFiscaleEnte)).append(COMMA);
		s.append("tipoRichiestaEnum:").append(tipoRichiestaEnum).append(COMMA);
		s.append("statoRichiestaEnum:").append(statoRichiestaEnum).append(COMMA);
		s.append("pagamentoSpontaneo:").append(pagamentoSpontaneo).append(COMMA);
		s.append("codVersamento:").append(quote(codVersamento)).append(COMMA);
		s.append("dataInvioAlDestinatario:").append(date2strdatetime(dataInvioAlDestinatario)).append(COMMA);
		s.append("codEsito:").append(quote(codEsito)).append(COMMA);
		s.append("numTotaleElementi:").append(numTotaleElementi).append(COMMA);
		s.append("importoTotale:").append(importoTotale);
		s.append(" }");
		return s.toString();
	}
}
