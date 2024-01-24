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
public class RichiestaBaseDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private String codFiscaleEnte;
	private String denominazioneEnte;
	private TipoRichiestaEnum tipoRichiestaEnum;
	private StatoRichiestaEnum statoRichiestaEnum;
	private String idMessaggio;
	private String codFiscaleEnteOrigine;
	private Boolean pagamentoSpontaneo;
	private String codVersamento;
	private String messageUUID; // chiave applicativa
	private String contenutoMessaggio;
	private Date dataInserimentoRichiesta;
	private Date dataUltimaVariazione;
	private Integer numTotaleElementi;
	private BigDecimal importoTotale;
	private BigDecimal dimMessaggioMB;
	private String idPSP;
	private String idFlussoRendicontazione;

	public String getIdPSP() {
		return idPSP;
	}

	public void setIdPSP(String idPSP) {
		this.idPSP = idPSP;
	}

	public String getIdFlussoRendicontazione() {
		return idFlussoRendicontazione;
	}

	public void setIdFlussoRendicontazione(String idFlussoRendicontazione) {
		this.idFlussoRendicontazione = idFlussoRendicontazione;
	}

	public String getCodFiscaleEnte() {
		return codFiscaleEnte;
	}

	public void setCodFiscaleEnte(String codFiscaleEnte) {
		this.codFiscaleEnte = codFiscaleEnte;
	}

	public String getDenominazioneEnte() {
		return denominazioneEnte;
	}

	public void setDenominazioneEnte(String denominazioneEnte) {
		this.denominazioneEnte = denominazioneEnte;
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

	public String getIdMessaggio() {
		return idMessaggio;
	}

	public void setIdMessaggio(String idMessaggio) {
		this.idMessaggio = idMessaggio;
	}

	public String getCodFiscaleEnteOrigine() {
		return codFiscaleEnteOrigine;
	}

	public void setCodFiscaleEnteOrigine(String codFiscaleEnteOrigine) {
		this.codFiscaleEnteOrigine = codFiscaleEnteOrigine;
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

	public String getMessageUUID() {
		return messageUUID;
	}

	public void setMessageUUID(String messageUUID) {
		this.messageUUID = messageUUID;
	}

	public String getContenutoMessaggio() {
		return contenutoMessaggio;
	}

	public void setContenutoMessaggio(String contenutoMessaggio) {
		this.contenutoMessaggio = contenutoMessaggio;
	}

	public Date getDataInserimentoRichiesta() {
		return dataInserimentoRichiesta;
	}

	public void setDataInserimentoRichiesta(Date dataInserimentoRichiesta) {
		this.dataInserimentoRichiesta = dataInserimentoRichiesta;
	}

	public Date getDataUltimaVariazione() {
		return dataUltimaVariazione;
	}

	public void setDataUltimaVariazione(Date dataUltimaVariazione) {
		this.dataUltimaVariazione = dataUltimaVariazione;
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

	public BigDecimal getDimMessaggioMB() {
		return dimMessaggioMB;
	}

	public void setDimMessaggioMB(BigDecimal dimMessaggioMB) {
		this.dimMessaggioMB = dimMessaggioMB;
	}

	@Override
	public String toString() {
		final String COMMA = ", ";
		StringBuilder s = new StringBuilder();
		s.append("{ ");
		s.append("codFiscaleEnte:").append(quote(codFiscaleEnte)).append(COMMA);
		s.append("denominazioneEnte:").append(quote(denominazioneEnte)).append(COMMA);
		s.append("tipoRichiestaEnum:").append(tipoRichiestaEnum).append(COMMA);
		s.append("statoRichiestaEnum:").append(statoRichiestaEnum).append(COMMA);
		s.append("idMessaggio:").append(quote(idMessaggio)).append(COMMA);
		s.append("codFiscaleEnteOrigine:").append(quote(codFiscaleEnteOrigine)).append(COMMA);
		s.append("pagamentoSpontaneo:").append(pagamentoSpontaneo).append(COMMA);
		s.append("codVersamento:").append(quote(codVersamento)).append(COMMA);
		s.append("messageUUID:").append(quote(messageUUID)).append(COMMA);
		s.append("contenutoMessaggio:").append(contenutoMessaggio).append(COMMA);
		s.append("dataInserimentoRichiesta:").append(date2strdatetime(dataInserimentoRichiesta)).append(COMMA);
		s.append("dataUltimaVariazione:").append(date2strdatetime(dataUltimaVariazione)).append(COMMA);
		s.append("numTotaleElementi:").append(numTotaleElementi).append(COMMA);
		s.append("importoTotale:").append(importoTotale).append(COMMA);
		s.append("dimMessaggioMB:").append(dimMessaggioMB).append(COMMA);
		s.append("idPSP:").append(idPSP).append(COMMA);
		s.append("idFlussoRendicontazione:").append(idFlussoRendicontazione);
		s.append(" }");
		return s.toString();
	}

}
