/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayweb.frontend.models;

public class Ricevuta {
	private byte[] data;
	
	public Ricevuta(byte[] ricevuta) {
		super();
		this.setData(ricevuta);
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] ricevuta) {
		this.data = ricevuta;
	}
}
