/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto;

import static it.csi.epay.epaypaweb.util.Util.date2strdatetime;
import static it.csi.epay.epaypaweb.util.Util.quote;

import java.io.Serializable;
import java.util.Date;

import it.csi.epay.epaypaweb.enumeration.TipoMailEnum;

public class InvioMailDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String to;
	private String cc;
	private String bcc;
	private TipoMailEnum tipoMailEnum;
	private Date dataInserimento;
	private Long idFlusso;
	private Date dataUltimoTentativo;
	private String esitoUltimoTentativo;
	private Boolean erroreInvio;

	public InvioMailDto() {
	}

	public InvioMailDto(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public TipoMailEnum getTipoMailEnum() {
		return tipoMailEnum;
	}

	public void setTipoMailEnum(TipoMailEnum tipoMailEnum) {
		this.tipoMailEnum = tipoMailEnum;
	}

	public Date getDataInserimento() {
		return dataInserimento;
	}

	public void setDataInserimento(Date dataInserimento) {
		this.dataInserimento = dataInserimento;
	}

	public Long getIdFlusso() {
		return idFlusso;
	}

	public void setIdFlusso(Long idFlusso) {
		this.idFlusso = idFlusso;
	}

	public Date getDataUltimoTentativo() {
		return dataUltimoTentativo;
	}

	public void setDataUltimoTentativo(Date dataUltimoTentativo) {
		this.dataUltimoTentativo = dataUltimoTentativo;
	}

	public String getEsitoUltimoTentativo() {
		return esitoUltimoTentativo;
	}

	public void setEsitoUltimoTentativo(String esitoUltimoTentativo) {
		this.esitoUltimoTentativo = esitoUltimoTentativo;
	}

	public Boolean getErroreInvio() {
		return erroreInvio;
	}

	public void setErroreInvio(Boolean erroreInvio) {
		this.erroreInvio = erroreInvio;
	}

	public String getBcc() {
		return bcc;
	}

	public void setBcc(String bcc) {
		this.bcc = bcc;
	}

	@Override
	public String toString() {
		final String COMMA = ", ";
		StringBuilder s = new StringBuilder();
		s.append("{ ");
		s.append("id:").append(id).append(COMMA);
		s.append("to:").append(quote(to)).append(COMMA);
		s.append("cc:").append(quote(cc)).append(COMMA);
		s.append("bcc:").append(quote(bcc)).append(COMMA);
		s.append("tipoMailEnum:").append(tipoMailEnum).append(COMMA);
		s.append("dataInserimento:").append(date2strdatetime(dataInserimento)).append(COMMA);
		s.append("idFlusso:").append(idFlusso).append(COMMA);
		s.append("dataUltimoTentativo:").append(date2strdatetime(dataUltimoTentativo)).append(COMMA);
		s.append("esitoUltimoTentativo:").append(quote(esitoUltimoTentativo)).append(COMMA);
		s.append("erroreInvio:").append(erroreInvio);
		s.append(" }");
		return s.toString();
	}

}
