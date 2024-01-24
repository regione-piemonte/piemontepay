/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto;

import static it.csi.epay.epaypaweb.util.Util.date2strdatetime;
import static it.csi.epay.epaypaweb.util.Util.quote;

import java.io.Serializable;
import java.util.Date;

/** dto facade <-> business <-> persistence */
public class FlussoDto extends FlussoLightDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer idEnte;
	private String utenteInserimento;
	private Date dataInoltro; // valorizzato solo in lettura

	public FlussoDto() {
		super();
	}

	public FlussoDto(Long id) {
		super(id);
	}
	
	public Integer getIdEnte() {
		return idEnte;
	}

	public void setIdEnte(Integer idEnte) {
		this.idEnte = idEnte;
	}

	public String getUtenteInserimento() {
		return utenteInserimento;
	}

	public void setUtenteInserimento(String utenteInserimento) {
		this.utenteInserimento = utenteInserimento;
	}

	public Date getDataInoltro() {
		return dataInoltro;
	}

	public void setDataInoltro(Date dataInoltro) {
		this.dataInoltro = dataInoltro;
	}

	@Override
	public String toString() {
		final String COMMA = ", ";
		StringBuilder s = new StringBuilder();
		s.append("{ ");
		s.append(super.toSuperString()).append(COMMA);
		s.append("idEnte:").append(idEnte).append(COMMA);
		s.append("utenteInserimento:").append(quote(utenteInserimento)).append(COMMA);
		s.append("dataInoltro:").append(date2strdatetime(dataInoltro));
		s.append(" }");
		return s.toString();
	}

}
