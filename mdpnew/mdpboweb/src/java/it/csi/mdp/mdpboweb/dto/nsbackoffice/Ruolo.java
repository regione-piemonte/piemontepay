/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.dto.nsbackoffice;

public class Ruolo implements java.io.Serializable {

	/// Field [idRuolo]
	private java.lang.String _idRuolo = null;

	public void setIdRuolo(java.lang.String val) {
		_idRuolo = val;
	}

	public java.lang.String getIdRuolo() {
		return _idRuolo;
	}

	/// Field [descrRuolo]
	private java.lang.String _descrRuolo = null;

	public void setDescrRuolo(java.lang.String val) {
		_descrRuolo = val;
	}

	public java.lang.String getDescrRuolo() {
		return _descrRuolo;
	}

	// il serial version uid e' fisso in quanto la classe in oggetto e' serializzabile
	// solo per la clusterizzazione della sessione web e non viene scambiata con altre
	// componenti.
	private static final long serialVersionUID = 1L;

	/**
	 * Costruttore vuoto del DTO.
	 */
	public Ruolo() {
		super();

	}

	public String toString() {
		/*PROTECTED REGION ID(R-1572611291) ENABLED START*/
		/// inserire qui la logica desiderata per la rappresenatazione a stringa
		return super.toString();
		/*PROTECTED REGION END*/
	}
}
