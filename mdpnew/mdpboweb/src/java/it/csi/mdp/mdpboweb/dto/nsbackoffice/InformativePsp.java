/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.dto.nsbackoffice;

public class InformativePsp implements java.io.Serializable {

	/// Field [idinformativapsp]
	private int _idinformativapsp = 0;

	public void setIdinformativapsp(int val) {
		_idinformativapsp = val;
	}

	public int getIdinformativapsp() {
		return _idinformativapsp;
	}

	/// Field [identificativoflusso]
	private java.lang.String _identificativoflusso = null;

	public void setIdentificativoflusso(java.lang.String val) {
		_identificativoflusso = val;
	}

	public java.lang.String getIdentificativoflusso() {
		return _identificativoflusso;
	}

	/// Field [identificativopsp]
	private java.lang.String _identificativopsp = null;

	public void setIdentificativopsp(java.lang.String val) {
		_identificativopsp = val;
	}

	public java.lang.String getIdentificativopsp() {
		return _identificativopsp;
	}

	/// Field [ragionesociale]
	private java.lang.String _ragionesociale = null;

	public void setRagionesociale(java.lang.String val) {
		_ragionesociale = val;
	}

	public java.lang.String getRagionesociale() {
		return _ragionesociale;
	}

	/// Field [datapubblicazione]
	private java.lang.String _datapubblicazione = null;

	public void setDatapubblicazione(java.lang.String val) {
		_datapubblicazione = val;
	}

	public java.lang.String getDatapubblicazione() {
		return _datapubblicazione;
	}

	/// Field [datainiziovalidita]
	private java.lang.String _datainiziovalidita = null;

	public void setDatainiziovalidita(java.lang.String val) {
		_datainiziovalidita = val;
	}

	public java.lang.String getDatainiziovalidita() {
		return _datainiziovalidita;
	}

	/// Field [urlinformazionipsp]
	private java.lang.String _urlinformazionipsp = null;

	public void setUrlinformazionipsp(java.lang.String val) {
		_urlinformazionipsp = val;
	}

	public java.lang.String getUrlinformazionipsp() {
		return _urlinformazionipsp;
	}

	/// Field [stornopagamento]
	private int _stornopagamento = 0;

	public void setStornopagamento(int val) {
		_stornopagamento = val;
	}

	public int getStornopagamento() {
		return _stornopagamento;
	}

	/// Field [identificativointermediario]
	private java.lang.String _identificativointermediario = null;

	public void setIdentificativointermediario(java.lang.String val) {
		_identificativointermediario = val;
	}

	public java.lang.String getIdentificativointermediario() {
		return _identificativointermediario;
	}

	/// Field [identificativocanale]
	private java.lang.String _identificativocanale = null;

	public void setIdentificativocanale(java.lang.String val) {
		_identificativocanale = val;
	}

	public java.lang.String getIdentificativocanale() {
		return _identificativocanale;
	}

	/// Field [tipoversamento]
	private java.lang.String _tipoversamento = null;

	public void setTipoversamento(java.lang.String val) {
		_tipoversamento = val;
	}

	public java.lang.String getTipoversamento() {
		return _tipoversamento;
	}

	/// Field [modellopagamento]
	private int _modellopagamento = 0;

	public void setModellopagamento(int val) {
		_modellopagamento = val;
	}

	public int getModellopagamento() {
		return _modellopagamento;
	}

	/// Field [priorita]
	private int _priorita = 0;

	public void setPriorita(int val) {
		_priorita = val;
	}

	public int getPriorita() {
		return _priorita;
	}

	/// Field [disponibilitaservizio]
	private java.lang.String _disponibilitaservizio = null;

	public void setDisponibilitaservizio(java.lang.String val) {
		_disponibilitaservizio = val;
	}

	public java.lang.String getDisponibilitaservizio() {
		return _disponibilitaservizio;
	}

	/// Field [descrizioneservizio]
	private java.lang.String _descrizioneservizio = null;

	public void setDescrizioneservizio(java.lang.String val) {
		_descrizioneservizio = val;
	}

	public java.lang.String getDescrizioneservizio() {
		return _descrizioneservizio;
	}

	/// Field [condizionieconomichemassime]
	private java.lang.String _condizionieconomichemassime = null;

	public void setCondizionieconomichemassime(java.lang.String val) {
		_condizionieconomichemassime = val;
	}

	public java.lang.String getCondizionieconomichemassime() {
		return _condizionieconomichemassime;
	}

	/// Field [urlinformazionicanale]
	private java.lang.String _urlinformazionicanale = null;

	public void setUrlinformazionicanale(java.lang.String val) {
		_urlinformazionicanale = val;
	}

	public java.lang.String getUrlinformazionicanale() {
		return _urlinformazionicanale;
	}

	/// Field [datainserimento]
	private java.lang.String _datainserimento = null;

	public void setDatainserimento(java.lang.String val) {
		_datainserimento = val;
	}

	public java.lang.String getDatainserimento() {
		return _datainserimento;
	}

	/// Field [ordinamento]
	private int _ordinamento = 0;

	public void setOrdinamento(int val) {
		_ordinamento = val;
	}

	public int getOrdinamento() {
		return _ordinamento;
	}

	/// Field [statoinserimento]
	private java.lang.String _statoinserimento = null;

	public void setStatoinserimento(java.lang.String val) {
		_statoinserimento = val;
	}

	public java.lang.String getStatoinserimento() {
		return _statoinserimento;
	}

	/// Field [origine]
	private java.lang.String _origine = null;

	public void setOrigine(java.lang.String val) {
		_origine = val;
	}

	public java.lang.String getOrigine() {
		return _origine;
	}

	// il serial version uid e' fisso in quanto la classe in oggetto e' serializzabile
	// solo per la clusterizzazione della sessione web e non viene scambiata con altre
	// componenti.
	private static final long serialVersionUID = 1L;

	/**
	 * Costruttore vuoto del DTO.
	 */
	public InformativePsp() {
		super();

	}

	public String toString() {
		/*PROTECTED REGION ID(R1085567283) ENABLED START*/
		/// inserire qui la logica desiderata per la rappresenatazione a stringa
		return super.toString();
		/*PROTECTED REGION END*/
	}
}
