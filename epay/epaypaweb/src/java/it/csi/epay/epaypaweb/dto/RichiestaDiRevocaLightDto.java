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
public class RichiestaDiRevocaLightDto extends ElementoFlussoDto implements Serializable {
	private static final long serialVersionUID = 1L;

	// dati della notifica di pagamento
	private String idPosizioneDebitoria;
	private String iuv;
	private BigDecimal importoPagato;
	private String desCausaleVersamento;
	private Date dataEsitoPagamento;
	private SoggettoAnagraficoDto soggettoDebitore;

	public RichiestaDiRevocaLightDto() {
		super();
	}

	public RichiestaDiRevocaLightDto(Long id) {
		super(id);
	}

	public String getIdPosizioneDebitoria() {
		return idPosizioneDebitoria;
	}

	public void setIdPosizioneDebitoria(String idPosizioneDebitoria) {
		this.idPosizioneDebitoria = idPosizioneDebitoria;
	}

	public String getIUV() {
		return iuv;
	}

	public void setIUV(String iuv) {
		this.iuv = iuv;
	}

	public BigDecimal getImportoPagato() {
		return importoPagato;
	}

	public void setImportoPagato(BigDecimal importoPagato) {
		this.importoPagato = importoPagato;
	}

	public String getDesCausaleVersamento() {
		return desCausaleVersamento;
	}

	public void setDesCausaleVersamento(String desCausaleVersamento) {
		this.desCausaleVersamento = desCausaleVersamento;
	}

	public Date getDataEsitoPagamento() {
		return dataEsitoPagamento;
	}

	public void setDataEsitoPagamento(Date dataEsitoPagamento) {
		this.dataEsitoPagamento = dataEsitoPagamento;
	}

	public SoggettoAnagraficoDto getSoggettoDebitore() {
		return soggettoDebitore;
	}

	public void setSoggettoDebitore(SoggettoAnagraficoDto soggettoDebitore) {
		this.soggettoDebitore = soggettoDebitore;
	}

	@Override
	public String toString() {
		return "{ " + toSuperString() + " }";
	}

	public String toSuperString() {
		final String COMMA = ", ";
		StringBuilder s = new StringBuilder();
		s.append(super.toSuperString()).append(COMMA);
		s.append("idPosizioneDebitoria:").append(quote(idPosizioneDebitoria)).append(COMMA);
		s.append("iuv:").append(quote(iuv)).append(COMMA);
		s.append("importoPagato:").append(importoPagato).append(COMMA);
		s.append("desCausaleVersamento:").append(quote(desCausaleVersamento)).append(COMMA);
		s.append("dataEsitoPagamento:").append(date2strdatetime(dataEsitoPagamento)).append(COMMA);
		s.append("soggettoDebitoreDto:").append(soggettoDebitore);
		return s.toString();
	}

}
