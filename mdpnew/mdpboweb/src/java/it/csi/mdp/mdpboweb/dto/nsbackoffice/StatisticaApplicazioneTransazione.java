/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.dto.nsbackoffice;

public class StatisticaApplicazioneTransazione implements java.io.Serializable {

	/// Field [applicationId]
	private java.lang.String _applicationId = null;

	public void setApplicationId(java.lang.String val) {
		_applicationId = val;
	}

	public java.lang.String getApplicationId() {
		return _applicationId;
	}

	/// Field [applicationname]
	private java.lang.String _applicationname = null;

	public void setApplicationname(java.lang.String val) {
		_applicationname = val;
	}

	public java.lang.String getApplicationname() {
		return _applicationname;
	}

	/// Field [notInitialized]
	private int _notInitialized = 0;

	public void setNotInitialized(int val) {
		_notInitialized = val;
	}

	public int getNotInitialized() {
		return _notInitialized;
	}

	/// Field [initialized]
	private int _initialized = 0;

	public void setInitialized(int val) {
		_initialized = val;
	}

	public int getInitialized() {
		return _initialized;
	}

	/// Field [configured]
	private int _configured = 0;

	public void setConfigured(int val) {
		_configured = val;
	}

	public int getConfigured() {
		return _configured;
	}

	/// Field [started]
	private int _started = 0;

	public void setStarted(int val) {
		_started = val;
	}

	public int getStarted() {
		return _started;
	}

	/// Field [successful]
	private int _successful = 0;

	public void setSuccessful(int val) {
		_successful = val;
	}

	public int getSuccessful() {
		return _successful;
	}

	/// Field [unsuccessful]
	private int _unsuccessful = 0;

	public void setUnsuccessful(int val) {
		_unsuccessful = val;
	}

	public int getUnsuccessful() {
		return _unsuccessful;
	}

	/// Field [canceled]
	private int _canceled = 0;

	public void setCanceled(int val) {
		_canceled = val;
	}

	public int getCanceled() {
		return _canceled;
	}

	/// Field [refunded]
	private int _refunded = 0;

	public void setRefunded(int val) {
		_refunded = val;
	}

	public int getRefunded() {
		return _refunded;
	}

	/// Field [toBeConfirmed]
	private int _toBeConfirmed = 0;

	public void setToBeConfirmed(int val) {
		_toBeConfirmed = val;
	}

	public int getToBeConfirmed() {
		return _toBeConfirmed;
	}

	/// Field [attesaRt]
	private int _attesaRt = 0;

	public void setAttesaRt(int val) {
		_attesaRt = val;
	}

	public int getAttesaRt() {
		return _attesaRt;
	}

	/// Field [totForAppId]
	private int _totForAppId = 0;

	public void setTotForAppId(int val) {
		_totForAppId = val;
	}

	public int getTotForAppId() {
		return _totForAppId;
	}

	// il serial version uid e' fisso in quanto la classe in oggetto e' serializzabile
	// solo per la clusterizzazione della sessione web e non viene scambiata con altre
	// componenti.
	private static final long serialVersionUID = 1L;

	/**
	 * Costruttore vuoto del DTO.
	 */
	public StatisticaApplicazioneTransazione() {
		super();

	}

	public String toString() {
		/*PROTECTED REGION ID(R-1746370456) ENABLED START*/
		/// inserire qui la logica desiderata per la rappresenatazione a stringa
		return super.toString();
		/*PROTECTED REGION END*/
	}
}
