/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.dto.nsbackoffice;

public class KeyValue implements java.io.Serializable {

	/// Field [key]
	private java.lang.String _key = null;

	public void setKey(java.lang.String val) {
		_key = val;
	}

	public java.lang.String getKey() {
		return _key;
	}

	/// Field [value]
	private java.lang.String _value = null;

	public void setValue(java.lang.String val) {
		_value = val;
	}

	public java.lang.String getValue() {
		return _value;
	}

	// il serial version uid e' fisso in quanto la classe in oggetto e' serializzabile
	// solo per la clusterizzazione della sessione web e non viene scambiata con altre
	// componenti.
	private static final long serialVersionUID = 1L;

	/**
	 * Costruttore vuoto del DTO.
	 */
	public KeyValue() {
		super();

	}

	public String toString() {
		/*PROTECTED REGION ID(R-789029932) ENABLED START*/
		/// inserire qui la logica desiderata per la rappresenatazione a stringa
		return super.toString();
		/*PROTECTED REGION END*/
	}
}
