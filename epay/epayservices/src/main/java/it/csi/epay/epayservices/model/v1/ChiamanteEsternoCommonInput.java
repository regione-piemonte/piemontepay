/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.model.v1;

import java.io.Serializable;


public abstract class ChiamanteEsternoCommonInput extends AccessoChiamanteEsternoSincronoSplitInput implements Serializable {

	private static final long serialVersionUID = -859244360547561114L;

	private String codiceFiscaleEnte;

	public String getCodiceFiscaleEnte () {
		return codiceFiscaleEnte;
	}

	public void setCodiceFiscaleEnte ( String codiceFiscaleEnte ) {
		this.codiceFiscaleEnte = codiceFiscaleEnte;
	}

	@Override public String toString () {
		return "StatoPosizioneDebitoriaInput{" +
						"codiceFiscaleEnte='" + codiceFiscaleEnte + '\'' +
						'}';
	}
}
