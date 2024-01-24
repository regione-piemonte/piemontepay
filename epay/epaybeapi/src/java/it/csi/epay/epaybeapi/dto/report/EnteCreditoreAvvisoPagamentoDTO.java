/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.dto.report;

import java.io.Serializable;

public class EnteCreditoreAvvisoPagamentoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String cfEnte;

    private String enteCreditore;

    private String settoreEnte;

    private String infoEnte;

    private byte [] ecLogo;

    private String cbill;

    private String numeroCCPostale;

    private String intestatarioCCPostale;

    private String autorizzazione;

	public String getCfEnte() {
		return cfEnte;
	}

	public void setCfEnte(String cfEnte) {
		this.cfEnte = cfEnte;
	}

	public String getEnteCreditore() {
		return enteCreditore;
	}

	public void setEnteCreditore(String enteCreditore) {
		this.enteCreditore = enteCreditore;
	}

	public String getSettoreEnte() {
		return settoreEnte;
	}

	public void setSettoreEnte(String settoreEnte) {
		this.settoreEnte = settoreEnte;
	}

	public String getInfoEnte() {
		return infoEnte;
	}

	public void setInfoEnte(String infoEnte) {
		this.infoEnte = infoEnte;
	}

	public byte[] getEcLogo() {
		return ecLogo;
	}

	public void setEcLogo(byte[] ecLogo) {
		this.ecLogo = ecLogo;
	}

	public String getCbill() {
		return cbill;
	}

	public void setCbill(String cbill) {
		this.cbill = cbill;
	}

	public String getNumeroCCPostale() {
		return numeroCCPostale;
	}

	public void setNumeroCCPostale(String numeroCCPostale) {
		this.numeroCCPostale = numeroCCPostale;
	}

	public String getIntestatarioCCPostale() {
		return intestatarioCCPostale;
	}

	public void setIntestatarioCCPostale(String intestatarioCCPostale) {
		this.intestatarioCCPostale = intestatarioCCPostale;
	}

	public String getAutorizzazione() {
		return autorizzazione;
	}

	public void setAutorizzazione(String autorizzazione) {
		this.autorizzazione = autorizzazione;
	}

}
