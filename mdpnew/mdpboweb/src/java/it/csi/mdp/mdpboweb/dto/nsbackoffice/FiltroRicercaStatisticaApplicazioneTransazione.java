/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.dto.nsbackoffice;

public class FiltroRicercaStatisticaApplicazioneTransazione
		implements
			java.io.Serializable {

	/// Field [applicationId]
	private java.lang.String _applicationId = null;

	public void setApplicationId(java.lang.String val) {
		_applicationId = val;
	}

	public java.lang.String getApplicationId() {
		return _applicationId;
	}

	/// Field [dataDa]
	private java.lang.String _dataDa = null;

	public void setDataDa(java.lang.String val) {
		_dataDa = val;
	}

	public java.lang.String getDataDa() {
		return _dataDa;
	}

	/// Field [dataA]
	private java.lang.String _dataA = null;

	public void setDataA(java.lang.String val) {
		_dataA = val;
	}

	public java.lang.String getDataA() {
		return _dataA;
	}

	// il serial version uid e' fisso in quanto la classe in oggetto e' serializzabile
	// solo per la clusterizzazione della sessione web e non viene scambiata con altre
	// componenti.
	private static final long serialVersionUID = 1L;

	/**
	 * Costruttore vuoto del DTO.
	 */
	public FiltroRicercaStatisticaApplicazioneTransazione() {
		super();

	}

	public String toString() {
		/*PROTECTED REGION ID(R-1611395227) ENABLED START*/
		/// inserire qui la logica desiderata per la rappresenatazione a stringa
		return super.toString();
		/*PROTECTED REGION END*/
	}
}
