/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import static it.csi.epay.epaypaweb.util.Util.date2strdatetime;
import static it.csi.epay.epaypaweb.util.Util.quote;

/** dto facade <-> business <-> persistence */
public class PosizioneDebitoriaLightDto extends ElementoFlussoDto implements Serializable {
	private static final long serialVersionUID = 1L;

	// dati della posizione debitoria light
	private String iuv;
	private BigDecimal importoTotale;
	private String desCausaleVersamento;
	private Date dataScadenza;
	private SoggettoAnagraficoDto soggettoDebitore;

	public PosizioneDebitoriaLightDto() {
		super();
	}

	public PosizioneDebitoriaLightDto(Long id) {
		super(id);
	}

	public String getIUV() {
		return iuv;
	}

	public void setIUV(String iuv) {
		this.iuv = iuv;
	}

	public BigDecimal getImportoTotale() {
		return importoTotale;
	}

	public void setImportoTotale(BigDecimal importoTotale) {
		this.importoTotale = importoTotale;
	}

	public String getDesCausaleVersamento() {
		return desCausaleVersamento;
	}

	public void setDesCausaleVersamento(String desCausaleVersamento) {
		this.desCausaleVersamento = desCausaleVersamento;
	}

	public Date getDataScadenza() {
		return dataScadenza;
	}

	public void setDataScadenza(Date dataScadenza) {
		this.dataScadenza = dataScadenza;
	}

	public SoggettoAnagraficoDto getSoggettoDebitore() {
		return soggettoDebitore;
	}

	public void setSoggettoDebitore(SoggettoAnagraficoDto soggettoDebitore) {
		this.soggettoDebitore = soggettoDebitore;
	}

	public String getIuv () {
		return iuv;
	}

	public void setIuv ( String iuv ) {
		this.iuv = iuv;
	}

	@Override
	public String toSuperString() {
		final String COMMA = ", ";
		StringBuilder s = new StringBuilder();
		s.append(super.toSuperString()).append(COMMA);
		s.append("iuv:").append(quote(iuv)).append(COMMA);
		s.append("importoTotale:").append(importoTotale).append(COMMA);
		s.append("desCausaleVersamento:").append(quote(desCausaleVersamento)).append(COMMA);
		s.append("dataScadenza:").append(date2strdatetime(dataScadenza)).append(COMMA);
		s.append("soggettoDebitoreDto:").append(soggettoDebitore);
		return s.toString();
	}

}
