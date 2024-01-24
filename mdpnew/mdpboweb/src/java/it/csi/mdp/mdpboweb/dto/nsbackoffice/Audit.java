/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.dto.nsbackoffice;

public class Audit implements java.io.Serializable {

	/// Field [cercaDataInizio]
	private java.lang.String _cercaDataInizio = null;

	public void setCercaDataInizio(java.lang.String val) {
		_cercaDataInizio = val;
	}

	public java.lang.String getCercaDataInizio() {
		return _cercaDataInizio;
	}

	/// Field [cercaDataFine]
	private java.lang.String _cercaDataFine = null;

	public void setCercaDataFine(java.lang.String val) {
		_cercaDataFine = val;
	}

	public java.lang.String getCercaDataFine() {
		return _cercaDataFine;
	}

	/// Field [dataAudit]
	private java.lang.String _dataAudit = null;

	public void setDataAudit(java.lang.String val) {
		_dataAudit = val;
	}

	public java.lang.String getDataAudit() {
		return _dataAudit;
	}

	/// Field [descrUser]
	private java.lang.String _descrUser = null;

	public void setDescrUser(java.lang.String val) {
		_descrUser = val;
	}

	public java.lang.String getDescrUser() {
		return _descrUser;
	}

	/// Field [descrOperazione]
	private java.lang.String _descrOperazione = null;

	public void setDescrOperazione(java.lang.String val) {
		_descrOperazione = val;
	}

	public java.lang.String getDescrOperazione() {
		return _descrOperazione;
	}

	/// Field [descrApplicazione]
	private java.lang.String _descrApplicazione = null;

	public void setDescrApplicazione(java.lang.String val) {
		_descrApplicazione = val;
	}

	public java.lang.String getDescrApplicazione() {
		return _descrApplicazione;
	}

	/// Field [idTransazione]
	private java.lang.String _idTransazione = null;

	public void setIdTransazione(java.lang.String val) {
		_idTransazione = val;
	}

	public java.lang.String getIdTransazione() {
		return _idTransazione;
	}

	/// Field [codfisc]
	private java.lang.String _codfisc = null;

	public void setCodfisc(java.lang.String val) {
		_codfisc = val;
	}

	public java.lang.String getCodfisc() {
		return _codfisc;
	}

	/// Field [idaction]
	private java.lang.String _idaction = null;

	public void setIdaction(java.lang.String val) {
		_idaction = val;
	}

	public java.lang.String getIdaction() {
		return _idaction;
	}

	// il serial version uid e' fisso in quanto la classe in oggetto e' serializzabile
	// solo per la clusterizzazione della sessione web e non viene scambiata con altre
	// componenti.
	private static final long serialVersionUID = 1L;

	/**
	 * Costruttore vuoto del DTO.
	 */
	public Audit() {
		super();

	}

	public String toString() {
		/*PROTECTED REGION ID(R1849071089) ENABLED START*/
		/// inserire qui la logica desiderata per la rappresenatazione a stringa
		return super.toString();
		/*PROTECTED REGION END*/
	}
}
