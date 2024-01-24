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
public class AvvisoScadutoDto extends ElementoFlussoDto implements Serializable {
	private static final long serialVersionUID = 1L;

	// dati dell'avviso scaduto
	private Long idFlusso;
	private String iuv;
	private BigDecimal importo;
	private Date dataScadenza;

	public AvvisoScadutoDto() {
		super();
	}

	public AvvisoScadutoDto(Long id) {
		super(id);
	}

	public Long getIdFlusso() {
		return idFlusso;
	}

	public void setIdFlusso(Long idFlusso) {
		this.idFlusso = idFlusso;
	}

	public String getIUV() {
		return iuv;
	}

	public void setIUV(String iuv) {
		this.iuv = iuv;
	}

	public Date getDataScadenza() {
		return dataScadenza;
	}

	public void setDataScadenza(Date dataScadenza) {
		this.dataScadenza = dataScadenza;
	}

	public BigDecimal getImporto() {
		return importo;
	}

	public void setImporto(BigDecimal importo) {
		this.importo = importo;
	}

	@Override
	public String toString() {
		return "{ " + toSuperString() + " }";
	}

	public String toSuperString() {
		final String COMMA = ", ";
		StringBuilder s = new StringBuilder();
		s.append(super.toSuperString()).append(COMMA);
		s.append("idFlusso:").append(idFlusso).append(COMMA);
		s.append("iuv:").append(quote(iuv)).append(COMMA);
		s.append("dataScadenza:").append(date2strdatetime(dataScadenza)).append(COMMA);
		s.append("importo:").append(importo);
		return s.toString();
	}

}
