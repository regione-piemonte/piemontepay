/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.dto.nsbackoffice;

public class StatoAttivazioneEnti implements java.io.Serializable {

	/// Field [attivo]
	private java.lang.String _attivo = null;

	public void setAttivo(java.lang.String val) {
		_attivo = val;
	}

	public java.lang.String getAttivo() {
		return _attivo;
	}

	/// Field [stato]
	private java.lang.String _stato = null;

	public void setStato(java.lang.String val) {
		_stato = val;
	}

	public java.lang.String getStato() {
		return _stato;
	}

	// il serial version uid e' fisso in quanto la classe in oggetto e' serializzabile
	// solo per la clusterizzazione della sessione web e non viene scambiata con altre
	// componenti.
	private static final long serialVersionUID = 1L;

	/**
	 * Costruttore vuoto del DTO.
	 */
	public StatoAttivazioneEnti() {
		super();

	}

	public String toString() {
		/*PROTECTED REGION ID(R-1261858167) ENABLED START*/
		/// inserire qui la logica desiderata per la rappresenatazione a stringa
		return super.toString();
		/*PROTECTED REGION END*/
	}
}
