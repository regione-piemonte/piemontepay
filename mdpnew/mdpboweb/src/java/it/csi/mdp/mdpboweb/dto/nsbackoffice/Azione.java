/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.dto.nsbackoffice;

public class Azione implements java.io.Serializable {

	/// Field [idAzione]
	private java.lang.String _idAzione = null;

	public void setIdAzione(java.lang.String val) {
		_idAzione = val;
	}

	public java.lang.String getIdAzione() {
		return _idAzione;
	}

	/// Field [descrAzione]
	private java.lang.String _descrAzione = null;

	public void setDescrAzione(java.lang.String val) {
		_descrAzione = val;
	}

	public java.lang.String getDescrAzione() {
		return _descrAzione;
	}

	// il serial version uid e' fisso in quanto la classe in oggetto e' serializzabile
	// solo per la clusterizzazione della sessione web e non viene scambiata con altre
	// componenti.
	private static final long serialVersionUID = 1L;

	/**
	 * Costruttore vuoto del DTO.
	 */
	public Azione() {
		super();

	}

	public String toString() {
		/*PROTECTED REGION ID(R-743886824) ENABLED START*/
		/// inserire qui la logica desiderata per la rappresenatazione a stringa
		return super.toString();
		/*PROTECTED REGION END*/
	}
}
