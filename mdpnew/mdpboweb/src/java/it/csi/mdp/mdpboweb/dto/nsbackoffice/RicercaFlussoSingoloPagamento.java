/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.dto.nsbackoffice;

public class RicercaFlussoSingoloPagamento
		extends
			it.csi.mdp.mdpboweb.dto.nsbackoffice.FlussoSingoloPagamento
		implements
			java.io.Serializable {

	/// Field [dataregolamentoDA]
	private java.lang.String _dataregolamentoDA = null;

	public void setDataregolamentoDA(java.lang.String val) {
		_dataregolamentoDA = val;
	}

	public java.lang.String getDataregolamentoDA() {
		return _dataregolamentoDA;
	}

	/// Field [dataregolamentoA]
	private java.lang.String _dataregolamentoA = null;

	public void setDataregolamentoA(java.lang.String val) {
		_dataregolamentoA = val;
	}

	public java.lang.String getDataregolamentoA() {
		return _dataregolamentoA;
	}

	// il serial version uid e' fisso in quanto la classe in oggetto e' serializzabile
	// solo per la clusterizzazione della sessione web e non viene scambiata con altre
	// componenti.
	private static final long serialVersionUID = 1L;

	/**
	 * Costruttore vuoto del DTO.
	 */
	public RicercaFlussoSingoloPagamento() {
		super();

	}

	public String toString() {
		/*PROTECTED REGION ID(R1909812048) ENABLED START*/
		/// inserire qui la logica desiderata per la rappresenatazione a stringa
		return super.toString();
		/*PROTECTED REGION END*/
	}
}
