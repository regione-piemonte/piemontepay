/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.dto.nsbackoffice;

public class GruppoApplicazione implements java.io.Serializable {

	/// Field [idgroup]
	private java.lang.String _idgroup = null;

	public void setIdgroup(java.lang.String val) {
		_idgroup = val;
	}

	public java.lang.String getIdgroup() {
		return _idgroup;
	}

	/// Field [idapp]
	private java.lang.String _idapp = null;

	public void setIdapp(java.lang.String val) {
		_idapp = val;
	}

	public java.lang.String getIdapp() {
		return _idapp;
	}

	// il serial version uid e' fisso in quanto la classe in oggetto e' serializzabile
	// solo per la clusterizzazione della sessione web e non viene scambiata con altre
	// componenti.
	private static final long serialVersionUID = 1L;

	/**
	 * Costruttore vuoto del DTO.
	 */
	public GruppoApplicazione() {
		super();

	}

	public String toString() {
		/*PROTECTED REGION ID(R2013858818) ENABLED START*/
		/// inserire qui la logica desiderata per la rappresenatazione a stringa
		return super.toString();
		/*PROTECTED REGION END*/
	}
}
