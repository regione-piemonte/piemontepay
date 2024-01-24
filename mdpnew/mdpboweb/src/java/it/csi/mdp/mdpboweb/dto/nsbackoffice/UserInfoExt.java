/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.dto.nsbackoffice;

public class UserInfoExt extends it.csi.mdp.mdpboweb.dto.common.UserInfo
		implements
			java.io.Serializable {

	/// Field [PwBck]
	private java.lang.String _PwBck = null;

	public void setPwBck(java.lang.String val) {
		_PwBck = val;
	}

	public java.lang.String getPwBck() {
		return _PwBck;
	}

	// il serial version uid e' fisso in quanto la classe in oggetto e' serializzabile
	// solo per la clusterizzazione della sessione web e non viene scambiata con altre
	// componenti.
	private static final long serialVersionUID = 1L;

	/**
	 * Costruttore vuoto del DTO.
	 */
	public UserInfoExt() {
		super();

	}

	public String toString() {
		/*PROTECTED REGION ID(R-2129904162) ENABLED START*/
		/// inserire qui la logica desiderata per la rappresenatazione a stringa
		return super.toString();
		/*PROTECTED REGION END*/
	}
}
