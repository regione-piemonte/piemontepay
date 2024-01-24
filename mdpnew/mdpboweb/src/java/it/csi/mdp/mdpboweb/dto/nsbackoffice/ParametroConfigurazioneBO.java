/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.dto.nsbackoffice;

public class ParametroConfigurazioneBO implements java.io.Serializable {

	/// Field [idParametro]
	private java.lang.String _idParametro = null;

	public void setIdParametro(java.lang.String val) {
		_idParametro = val;
	}

	public java.lang.String getIdParametro() {
		return _idParametro;
	}

	/// Field [descrParametro]
	private java.lang.String _descrParametro = null;

	public void setDescrParametro(java.lang.String val) {
		_descrParametro = val;
	}

	public java.lang.String getDescrParametro() {
		return _descrParametro;
	}

	/// Field [valore]
	private java.lang.String _valore = null;

	public void setValore(java.lang.String val) {
		_valore = val;
	}

	public java.lang.String getValore() {
		return _valore;
	}

	// il serial version uid e' fisso in quanto la classe in oggetto e' serializzabile
	// solo per la clusterizzazione della sessione web e non viene scambiata con altre
	// componenti.
	private static final long serialVersionUID = 1L;

	/**
	 * Costruttore vuoto del DTO.
	 */
	public ParametroConfigurazioneBO() {
		super();

	}

	public String toString() {
		/*PROTECTED REGION ID(R581660337) ENABLED START*/
		/// inserire qui la logica desiderata per la rappresenatazione a stringa
		return super.toString();
		/*PROTECTED REGION END*/
	}
}
