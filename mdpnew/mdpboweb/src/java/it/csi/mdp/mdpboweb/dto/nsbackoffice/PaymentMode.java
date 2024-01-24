/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.dto.nsbackoffice;

public class PaymentMode implements java.io.Serializable {

	/// Field [idPayment]
	private java.lang.String _idPayment = null;

	public void setIdPayment(java.lang.String val) {
		_idPayment = val;
	}

	public java.lang.String getIdPayment() {
		return _idPayment;
	}

	/// Field [descrPayment]
	private java.lang.String _descrPayment = null;

	public void setDescrPayment(java.lang.String val) {
		_descrPayment = val;
	}

	public java.lang.String getDescrPayment() {
		return _descrPayment;
	}

	/// Field [dataInizioValidita]
	private java.lang.String _dataInizioValidita = null;

	public void setDataInizioValidita(java.lang.String val) {
		_dataInizioValidita = val;
	}

	public java.lang.String getDataInizioValidita() {
		return _dataInizioValidita;
	}

	/// Field [dataFineValidita]
	private java.lang.String _dataFineValidita = null;

	public void setDataFineValidita(java.lang.String val) {
		_dataFineValidita = val;
	}

	public java.lang.String getDataFineValidita() {
		return _dataFineValidita;
	}

	// il serial version uid e' fisso in quanto la classe in oggetto e' serializzabile
	// solo per la clusterizzazione della sessione web e non viene scambiata con altre
	// componenti.
	private static final long serialVersionUID = 1L;

	/**
	 * Costruttore vuoto del DTO.
	 */
	public PaymentMode() {
		super();

	}

	public String toString() {
		/*PROTECTED REGION ID(R289042783) ENABLED START*/
		/// inserire qui la logica desiderata per la rappresenatazione a stringa
		return super.toString();
		/*PROTECTED REGION END*/
	}
}
