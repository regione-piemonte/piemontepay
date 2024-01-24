/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto.common;

import java.io.Serializable;

public class PrincipalDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String ipAddress;

	private Long idUtente;
	
	private String codiceFiscaleUtente;
	
	private String codiceApplicazione;

	public PrincipalDto(String ipAddress, Long idUtente, String codiceFiscaleUtente,String codiceApplicazione) {
		super();
		this.ipAddress = ipAddress;
		this.idUtente = idUtente;
		this.codiceFiscaleUtente = codiceFiscaleUtente;
		this.setCodiceApplicazione(codiceApplicazione);
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getCodiceFiscaleUtente() {
		return codiceFiscaleUtente;
	}

	public void setCodiceFiscaleUtente(String codiceFiscaleUtente) {
		this.codiceFiscaleUtente = codiceFiscaleUtente;
	}

	public String getCodiceApplicazione() {
		return codiceApplicazione;
	}

	public void setCodiceApplicazione(String codiceApplicazione) {
		this.codiceApplicazione = codiceApplicazione;
	}

	public Long getIdUtente() {
		return idUtente;
	}

	public void setIdUtente(Long idUtente) {
		this.idUtente = idUtente;
	}

	@Override
	public String toString() {
		return "PrincipalDto [ipAddress=" + ipAddress + ", idUtente=" + idUtente + ", codiceFiscaleUtente="
				+ codiceFiscaleUtente + ", codiceApplicazione=" + codiceApplicazione + "]";
	}
}
