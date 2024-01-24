/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.presentation.action;

import java.io.ByteArrayInputStream;

import javax.ejb.EJB;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import it.csi.epay.epaypaweb.business.interf.GestioneFlussiBusiness;
import it.csi.epay.epaypaweb.enumeration.TipoFormatoFileEnum;

public class DownloadFlussoAction extends EpaypawebBaseAction {
	private static final long serialVersionUID = 1L;

	@EJB
	protected GestioneFlussiBusiness gestioneFlussiBusiness;

	protected Long idFlusso;
	protected TipoFormatoFileEnum tipoFormato;
	protected String filename;
	protected ByteArrayInputStream inputStream;
	protected String pleaseWaitTokenValue;

	public Long getIdFlusso() {
		return idFlusso;
	}

	public void setIdFlusso(Long idFlusso) {
		this.idFlusso = idFlusso;
	}

	public TipoFormatoFileEnum getTipoFormato() {
		return tipoFormato;
	}

	public void setTipoFormato(TipoFormatoFileEnum tipoFormato) {
		this.tipoFormato = tipoFormato;
	}

	public String getFilename() {
		return filename;
	}

	public ByteArrayInputStream getInputStream() {
		return inputStream;
	}

	public String getPleaseWaitTokenValue() {
		return pleaseWaitTokenValue;
	}

	public void setPleaseWaitTokenValue(String pleaseWaitTokenValue) {
		this.pleaseWaitTokenValue = pleaseWaitTokenValue;
	}

	protected void setFileDownloadTokenCookie(String pleaseWaitTokenValue) {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.addCookie(new Cookie("pleaseWaitToken", pleaseWaitTokenValue));
	}

}
