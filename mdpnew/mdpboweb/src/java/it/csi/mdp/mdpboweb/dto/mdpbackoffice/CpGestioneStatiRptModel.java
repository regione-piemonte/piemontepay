/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.dto.mdpbackoffice;

import java.util.*;
import it.csi.mdp.mdpboweb.dto.*;
import it.csi.mdp.mdpboweb.dto.common.*;
import it.csi.mdp.mdpboweb.dto.nsbackoffice.*;

import it.csi.mdp.mdpboweb.presentation.uiutils.*;
import flexjson.JSON;
import com.opensymphony.xwork2.conversion.annotations.*;
/**
 * Questo DTO incapsula tutto il contenuto informativo necessario all'esecuzione della
 * logica di business associata alla Schermata [cpGestioneStatiRpt]
 */
public class CpGestioneStatiRptModel extends BaseSessionAwareDTO {

	// il serial version uid e' fisso in quanto la classe in oggetto e' serializzabile
	// solo per la clusterizzazione della sessione web e non viene scambiata con altre
	// componenti.
	private static final long serialVersionUID = 1L;

	////////////////////////////////////////////////////////////////////
	/// application data
	////////////////////////////////////////////////////////////////////

	public void setAppDatacurrentUser(
			it.csi.mdp.mdpboweb.dto.common.UserInfo value) {
		getSession().put("appDatacurrentUser", value);
	}

	public it.csi.mdp.mdpboweb.dto.common.UserInfo getAppDatacurrentUser() {
		return (it.csi.mdp.mdpboweb.dto.common.UserInfo) (getSession()
				.get("appDatacurrentUser"));
	}

	public void setAppDataisPostBack(java.lang.String value) {
		getSession().put("appDataisPostBack", value);
	}

	public java.lang.String getAppDataisPostBack() {
		return (java.lang.String) (getSession().get("appDataisPostBack"));
	}

	public void setAppDatalistaSingoloStatoRpt(
			java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.SingoloStatoRPT> value) {
		getSession().put("appDatalistaSingoloStatoRpt", value);
	}

	public java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.SingoloStatoRPT> getAppDatalistaSingoloStatoRpt() {
		return (java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.SingoloStatoRPT>) (getSession()
				.get("appDatalistaSingoloStatoRpt"));
	}

	public void setAppDatalistaSingoloStatoVersamento(
			java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.SingoloStatoVersamento> value) {
		getSession().put("appDatalistaSingoloStatoVersamento", value);
	}

	public java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.SingoloStatoVersamento> getAppDatalistaSingoloStatoVersamento() {
		return (java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.SingoloStatoVersamento>) (getSession()
				.get("appDatalistaSingoloStatoVersamento"));
	}

	public void setAppDataselettoreIdRPT(java.lang.String value) {
		getSession().put("appDataselettoreIdRPT", value);
	}

	public java.lang.String getAppDataselettoreIdRPT() {
		return (java.lang.String) (getSession().get("appDataselettoreIdRPT"));
	}

	public void setAppDatasingoloStatoVersamento(
			it.csi.mdp.mdpboweb.dto.nsbackoffice.SingoloStatoVersamento value) {
		getSession().put("appDatasingoloStatoVersamento", value);
	}

	public it.csi.mdp.mdpboweb.dto.nsbackoffice.SingoloStatoVersamento getAppDatasingoloStatoVersamento() {
		return (it.csi.mdp.mdpboweb.dto.nsbackoffice.SingoloStatoVersamento) (getSession()
				.get("appDatasingoloStatoVersamento"));
	}

	public void setAppDatastatoRPTRisposta(
			it.csi.mdp.mdpboweb.dto.nsbackoffice.StatoRPTRisposta value) {
		getSession().put("appDatastatoRPTRisposta", value);
	}

	public it.csi.mdp.mdpboweb.dto.nsbackoffice.StatoRPTRisposta getAppDatastatoRPTRisposta() {
		return (it.csi.mdp.mdpboweb.dto.nsbackoffice.StatoRPTRisposta) (getSession()
				.get("appDatastatoRPTRisposta"));
	}

	public void setAppDatasingoloStatoRPT(
			it.csi.mdp.mdpboweb.dto.nsbackoffice.SingoloStatoRPT value) {
		getSession().put("appDatasingoloStatoRPT", value);
	}

	public it.csi.mdp.mdpboweb.dto.nsbackoffice.SingoloStatoRPT getAppDatasingoloStatoRPT() {
		return (it.csi.mdp.mdpboweb.dto.nsbackoffice.SingoloStatoRPT) (getSession()
				.get("appDatasingoloStatoRPT"));
	}

	public void setAppDatauserInfoExt(
			it.csi.mdp.mdpboweb.dto.nsbackoffice.UserInfoExt value) {
		getSession().put("appDatauserInfoExt", value);
	}

	public it.csi.mdp.mdpboweb.dto.nsbackoffice.UserInfoExt getAppDatauserInfoExt() {
		return (it.csi.mdp.mdpboweb.dto.nsbackoffice.UserInfoExt) (getSession()
				.get("appDatauserInfoExt"));
	}

	////////////////////////////////////////////////////////////////////
	/// campi per widget semplici
	////////////////////////////////////////////////////////////////////

	// Table tbListaSingoloStatoRpt

	private java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.SingoloStatoRPT> _widg_tbListaSingoloStatoRpt;

	public void setWidg_tbListaSingoloStatoRpt(
			java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.SingoloStatoRPT> value) {
		_widg_tbListaSingoloStatoRpt = value;
	}

	public java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.SingoloStatoRPT> getWidg_tbListaSingoloStatoRpt() {
		return _widg_tbListaSingoloStatoRpt;
	}

	// Table tbListaSingoloStatoVersamento

	private java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.SingoloStatoVersamento> _widg_tbListaSingoloStatoVersamento;

	public void setWidg_tbListaSingoloStatoVersamento(
			java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.SingoloStatoVersamento> value) {
		_widg_tbListaSingoloStatoVersamento = value;
	}

	public java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.SingoloStatoVersamento> getWidg_tbListaSingoloStatoVersamento() {
		return _widg_tbListaSingoloStatoVersamento;
	}

	////////////////////////////////////////////////////////////////////
	/// ulteriori campi comuni 
	////////////////////////////////////////////////////////////////////

	/////////////////////////////////////////
	/// property comuni a tutte le action
	/////////////////////////////////////////

	// riga selezionata in una eventuale tabella
	// NOTA: l'id della cella selezionata e' data da idRiga + idColonna
	private String _idRiga = "";

	public void setIdRiga(String val) {
		_idRiga = val;
	}

	public String getIdRiga() {
		return _idRiga;
	}

	// colonna selezionata in una eventuale tabella
	// NOTA: l'id della cella selezionata e' data da idRiga + idColonna
	private String _idColonna = "";

	public void setIdColonna(String val) {
		_idColonna = val;
	}

	public String getIdColonna() {
		return _idColonna;
	}

	/// parametri per cambio TAB

	private String selectedTabKey;

	public void setSelectedTabKey(String value) {
		selectedTabKey = value;
	}

	public String getSelectedTabKey() {
		return selectedTabKey;
	}

	private String selectedTabValue;

	public void setSelectedTabValue(String value) {
		selectedTabValue = value;
	}

	public String getSelectedTabValue() {
		return selectedTabValue;
	}

	/**
	 * @generated
	 */
	private List<TableFilter> filter;

	/**
	 * @generated
	 */
	public List<TableFilter> getFilter() {
		return filter;
	}

	/**
	 * @generated
	 */
	public void setFilter(List<TableFilter> filter) {
		this.filter = filter;
	}

	/**
	 * @generated
	 */
	private TableStatus tableStatus;

	/**
	 * @generated
	 */
	public TableStatus getTableStatus() {
		return tableStatus;
	}

	/**
	 * @generated
	 */
	public void setTableStatus(TableStatus tableStatus) {
		this.tableStatus = tableStatus;
	}

}
