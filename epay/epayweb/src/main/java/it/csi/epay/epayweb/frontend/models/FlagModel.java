/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayweb.frontend.models;

public  class FlagModel extends _Model{

	private static final long serialVersionUID = -7100295290494721205L;
	
	private boolean donazione;

	public boolean isDonazione() {
		return donazione;
	}

	public void setDonazione(boolean donazione) {
		this.donazione = donazione;
	}
	

}
