/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.dto.nsbackoffice;

public class StatoTransazione implements java.io.Serializable {

	/// Field [codStato]
	private long _codStato = 0;

	public void setCodStato(long val) {
		_codStato = val;
	}

	public long getCodStato() {
		return _codStato;
	}

	/// Field [descrStato]
	private java.lang.String _descrStato = null;

	public void setDescrStato(java.lang.String val) {
		_descrStato = val;
	}

	public java.lang.String getDescrStato() {
		return _descrStato;
	}

	// il serial version uid e' fisso in quanto la classe in oggetto e' serializzabile
	// solo per la clusterizzazione della sessione web e non viene scambiata con altre
	// componenti.
	private static final long serialVersionUID = 1L;

	/**
	 * Costruttore vuoto del DTO.
	 */
	public StatoTransazione() {
		super();

	}

	public String toString() {
		/*PROTECTED REGION ID(R315943077) ENABLED START*/
		/// inserire qui la logica desiderata per la rappresenatazione a stringa
		return super.toString();
		/*PROTECTED REGION END*/
	}
}
