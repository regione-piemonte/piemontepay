/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.dto.nsbackoffice;

public class FlussoRiversamento implements java.io.Serializable {

	/// Field [id]
	private int _id = 0;

	public void setId(int val) {
		_id = val;
	}

	public int getId() {
		return _id;
	}

	/// Field [identificativopsp]
	private java.lang.String _identificativopsp = null;

	public void setIdentificativopsp(java.lang.String val) {
		_identificativopsp = val;
	}

	public java.lang.String getIdentificativopsp() {
		return _identificativopsp;
	}

	/// Field [identificativoflusso]
	private java.lang.String _identificativoflusso = null;

	public void setIdentificativoflusso(java.lang.String val) {
		_identificativoflusso = val;
	}

	public java.lang.String getIdentificativoflusso() {
		return _identificativoflusso;
	}

	/// Field [versioneoggetto]
	private java.lang.String _versioneoggetto = null;

	public void setVersioneoggetto(java.lang.String val) {
		_versioneoggetto = val;
	}

	public java.lang.String getVersioneoggetto() {
		return _versioneoggetto;
	}

	/// Field [identificativounivocoregolamento]
	private java.lang.String _identificativounivocoregolamento = null;

	public void setIdentificativounivocoregolamento(java.lang.String val) {
		_identificativounivocoregolamento = val;
	}

	public java.lang.String getIdentificativounivocoregolamento() {
		return _identificativounivocoregolamento;
	}

	/// Field [identificativoistitutomittente]
	private java.lang.String _identificativoistitutomittente = null;

	public void setIdentificativoistitutomittente(java.lang.String val) {
		_identificativoistitutomittente = val;
	}

	public java.lang.String getIdentificativoistitutomittente() {
		return _identificativoistitutomittente;
	}

	/// Field [identificativoistitutoricevente]
	private java.lang.String _identificativoistitutoricevente = null;

	public void setIdentificativoistitutoricevente(java.lang.String val) {
		_identificativoistitutoricevente = val;
	}

	public java.lang.String getIdentificativoistitutoricevente() {
		return _identificativoistitutoricevente;
	}

	/// Field [numerototalepagamenti]
	private int _numerototalepagamenti = 0;

	public void setNumerototalepagamenti(int val) {
		_numerototalepagamenti = val;
	}

	public int getNumerototalepagamenti() {
		return _numerototalepagamenti;
	}

	/// Field [importototalepagamenti]
	private double _importototalepagamenti = 0;

	public void setImportototalepagamenti(double val) {
		_importototalepagamenti = val;
	}

	public double getImportototalepagamenti() {
		return _importototalepagamenti;
	}

	/// Field [dataoraflusso]
	private java.lang.String _dataoraflusso = null;

	public void setDataoraflusso(java.lang.String val) {
		_dataoraflusso = val;
	}

	public java.lang.String getDataoraflusso() {
		return _dataoraflusso;
	}

	/// Field [dataregolamento]
	private java.lang.String _dataregolamento = null;

	public void setDataregolamento(java.lang.String val) {
		_dataregolamento = val;
	}

	public java.lang.String getDataregolamento() {
		return _dataregolamento;
	}

	/// Field [datainserimento]
	private java.lang.String _datainserimento = null;

	public void setDatainserimento(java.lang.String val) {
		_datainserimento = val;
	}

	public java.lang.String getDatainserimento() {
		return _datainserimento;
	}

	/// Field [datamodifica]
	private java.lang.String _datamodifica = null;

	public void setDatamodifica(java.lang.String val) {
		_datamodifica = val;
	}

	public java.lang.String getDatamodifica() {
		return _datamodifica;
	}

	/// Field [xmlflusso]
	private java.lang.String _xmlflusso = null;

	public void setXmlflusso(java.lang.String val) {
		_xmlflusso = val;
	}

	public java.lang.String getXmlflusso() {
		return _xmlflusso;
	}

	/// Field [denominazionemittente]
	private java.lang.String _denominazionemittente = null;

	public void setDenominazionemittente(java.lang.String val) {
		_denominazionemittente = val;
	}

	public java.lang.String getDenominazionemittente() {
		return _denominazionemittente;
	}

	/// Field [denominazionericevente]
	private java.lang.String _denominazionericevente = null;

	public void setDenominazionericevente(java.lang.String val) {
		_denominazionericevente = val;
	}

	public java.lang.String getDenominazionericevente() {
		return _denominazionericevente;
	}

	/// Field [flussoAcquisito]
	private java.lang.String _flussoAcquisito = null;

	public void setFlussoAcquisito(java.lang.String val) {
		_flussoAcquisito = val;
	}

	public java.lang.String getFlussoAcquisito() {
		return _flussoAcquisito;
	}

	// il serial version uid e' fisso in quanto la classe in oggetto e' serializzabile
	// solo per la clusterizzazione della sessione web e non viene scambiata con altre
	// componenti.
	private static final long serialVersionUID = 1L;

	/**
	 * Costruttore vuoto del DTO.
	 */
	public FlussoRiversamento() {
		super();

	}

	public String toString() {
		/*PROTECTED REGION ID(R939164185) ENABLED START*/
		/// inserire qui la logica desiderata per la rappresenatazione a stringa
		return super.toString();
		/*PROTECTED REGION END*/
	}
}
