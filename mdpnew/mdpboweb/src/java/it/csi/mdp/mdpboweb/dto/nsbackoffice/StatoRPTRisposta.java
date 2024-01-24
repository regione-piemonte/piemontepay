/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.dto.nsbackoffice;

public class StatoRPTRisposta implements java.io.Serializable {

	/// Field [esito]
	private java.lang.String _esito = null;

	public void setEsito(java.lang.String val) {
		_esito = val;
	}

	public java.lang.String getEsito() {
		return _esito;
	}

	/// Field [statoAttuale]
	private java.lang.String _statoAttuale = null;

	public void setStatoAttuale(java.lang.String val) {
		_statoAttuale = val;
	}

	public java.lang.String getStatoAttuale() {
		return _statoAttuale;
	}

	/// Field [urlPagamento]
	private java.lang.String _urlPagamento = null;

	public void setUrlPagamento(java.lang.String val) {
		_urlPagamento = val;
	}

	public java.lang.String getUrlPagamento() {
		return _urlPagamento;
	}

	/// Field [redirect]
	private int _redirect = 0;

	public void setRedirect(int val) {
		_redirect = val;
	}

	public int getRedirect() {
		return _redirect;
	}

	/// Field [listaSingoloStatoRPT]
	private java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.SingoloStatoRPT> _listaSingoloStatoRPT = new java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.SingoloStatoRPT>();

	public void setListaSingoloStatoRPT(
			java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.SingoloStatoRPT> val) {
		_listaSingoloStatoRPT = val;
	}

	public java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.SingoloStatoRPT> getListaSingoloStatoRPT() {
		return _listaSingoloStatoRPT;
	}

	/// Field [listaSingoloStatoVersamento]
	private java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.SingoloStatoVersamento> _listaSingoloStatoVersamento = new java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.SingoloStatoVersamento>();

	public void setListaSingoloStatoVersamento(
			java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.SingoloStatoVersamento> val) {
		_listaSingoloStatoVersamento = val;
	}

	public java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.SingoloStatoVersamento> getListaSingoloStatoVersamento() {
		return _listaSingoloStatoVersamento;
	}

	// il serial version uid e' fisso in quanto la classe in oggetto e' serializzabile
	// solo per la clusterizzazione della sessione web e non viene scambiata con altre
	// componenti.
	private static final long serialVersionUID = 1L;

	/**
	 * Costruttore vuoto del DTO.
	 */
	public StatoRPTRisposta() {
		super();

	}

	public String toString() {
		/*PROTECTED REGION ID(R-1881739742) ENABLED START*/
		/// inserire qui la logica desiderata per la rappresenatazione a stringa
		return super.toString();
		/*PROTECTED REGION END*/
	}
}
