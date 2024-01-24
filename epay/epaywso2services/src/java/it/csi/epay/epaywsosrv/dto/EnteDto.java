/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.dto;

import static it.csi.epay.epaywsosrv.util.Util.date2strdatetime;
import static it.csi.epay.epaywsosrv.util.Util.quote;

import java.io.Serializable;
import java.util.Date;

public class EnteDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String codFiscale;
	private String denominazione;
	private Date dataInizioValidita;
	private Date dataFineValidita;

	public EnteDto(Integer id, String codFiscale) {
		this.id = id;
		this.codFiscale = codFiscale;
	}

	public Integer getId() {
		return id;
	}

	public String getCodFiscale() {
		return codFiscale;
	}

	public String getDenominazione() {
		return denominazione;
	}

	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}

	public Date getDataInizioValidita() {
		return dataInizioValidita;
	}

	public void setDataInizioValidita(Date dataInizioValidita) {
		this.dataInizioValidita = dataInizioValidita;
	}

	public Date getDataFineValidita() {
		return dataFineValidita;
	}

	public void setDataFineValidita(Date dataFineValidita) {
		this.dataFineValidita = dataFineValidita;
	}

	@Override
	public String toString() {
		final String COMMA = ", ";
		StringBuilder s = new StringBuilder();
		s.append("{ ");
		s.append("id:").append(id).append(COMMA);
		s.append("codFiscale:").append(quote(codFiscale)).append(COMMA);
		s.append("denominazione:").append(quote(denominazione)).append(COMMA);
		s.append("dataInizioValidita:").append(date2strdatetime(dataInizioValidita)).append(COMMA);
		s.append("dataFineValidita:").append(date2strdatetime(dataFineValidita));
		s.append(" }");
		return s.toString();
	}

}
