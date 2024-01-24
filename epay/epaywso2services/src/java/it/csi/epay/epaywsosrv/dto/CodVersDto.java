/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.dto;

import static it.csi.epay.epaywsosrv.util.Util.date2strdatetime;
import static it.csi.epay.epaywsosrv.util.Util.quote;

import java.io.Serializable;
import java.util.Date;

import it.csi.epay.epaywsosrv.util.Util;

public class CodVersDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String cod;
	private String des;
	private Integer idApp;
	private Date dataInizioValidita;
	private Date dataFineValidita;

	public CodVersDto(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public Integer getIdApp() {
		return idApp;
	}

	public void setIdApp(Integer idApp) {
		this.idApp = idApp;
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
	public boolean equals(Object that) {
		if (that == null)
			return false;
		
		if (that == this)
			return true;
		
		if (that instanceof CodVersDto)
			return Util.areEquals(((CodVersDto) that).id, this.id);
		
		return false;
	}

	public int hashCode() {
		return 1;
	}

	@Override
	public String toString() {
		final String COMMA = ", ";
		StringBuilder s = new StringBuilder();
		s.append("{ ");
		s.append("id:").append(id).append(COMMA);
		s.append("cod:").append(quote(cod)).append(COMMA);
		s.append("des:").append(quote(des)).append(COMMA);
		s.append("idApp:").append(idApp).append(COMMA);
		s.append("dataInizioValidita:").append(date2strdatetime(dataInizioValidita)).append(COMMA);
		s.append("dataFineValidita:").append(date2strdatetime(dataFineValidita));
		s.append(" }");
		return s.toString();
	}

}
