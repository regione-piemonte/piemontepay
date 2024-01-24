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
 * logica di business associata alla Schermata [cpDettaglioGruppo]
 */
public class CpDettaglioGruppoModel extends BaseSessionAwareDTO {

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

	public void setAppDataselettoreIdRuolo(java.lang.String value) {
		getSession().put("appDataselettoreIdRuolo", value);
	}

	public java.lang.String getAppDataselettoreIdRuolo() {
		return (java.lang.String) (getSession().get("appDataselettoreIdRuolo"));
	}

	public void setAppDataappForGroup(
			java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Applicazione> value) {
		getSession().put("appDataappForGroup", value);
	}

	public java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Applicazione> getAppDataappForGroup() {
		return (java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Applicazione>) (getSession()
				.get("appDataappForGroup"));
	}

	public void setAppDataappForGroupFiltered(
			java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Applicazione> value) {
		getSession().put("appDataappForGroupFiltered", value);
	}

	public java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Applicazione> getAppDataappForGroupFiltered() {
		return (java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Applicazione>) (getSession()
				.get("appDataappForGroupFiltered"));
	}

	public void setAppDataselettoreAppGroup(java.lang.String value) {
		getSession().put("appDataselettoreAppGroup", value);
	}

	public java.lang.String getAppDataselettoreAppGroup() {
		return (java.lang.String) (getSession().get("appDataselettoreAppGroup"));
	}

	public void setAppDatafilteredApplicationGroup(
			java.util.ArrayList<java.lang.String> value) {
		getSession().put("appDatafilteredApplicationGroup", value);
	}

	public java.util.ArrayList<java.lang.String> getAppDatafilteredApplicationGroup() {
		return (java.util.ArrayList<java.lang.String>) (getSession()
				.get("appDatafilteredApplicationGroup"));
	}

	public void setAppDatauserInfoExt(
			it.csi.mdp.mdpboweb.dto.nsbackoffice.UserInfoExt value) {
		getSession().put("appDatauserInfoExt", value);
	}

	public it.csi.mdp.mdpboweb.dto.nsbackoffice.UserInfoExt getAppDatauserInfoExt() {
		return (it.csi.mdp.mdpboweb.dto.nsbackoffice.UserInfoExt) (getSession()
				.get("appDatauserInfoExt"));
	}

	public void setAppDatagruppo(
			it.csi.mdp.mdpboweb.dto.nsbackoffice.Gruppo value) {
		getSession().put("appDatagruppo", value);
	}

	public it.csi.mdp.mdpboweb.dto.nsbackoffice.Gruppo getAppDatagruppo() {
		return (it.csi.mdp.mdpboweb.dto.nsbackoffice.Gruppo) (getSession()
				.get("appDatagruppo"));
	}

	public void setAppDataruoli(
			java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Ruolo> value) {
		getSession().put("appDataruoli", value);
	}

	public java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Ruolo> getAppDataruoli() {
		return (java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Ruolo>) (getSession()
				.get("appDataruoli"));
	}

	public void setAppDataruolo(it.csi.mdp.mdpboweb.dto.nsbackoffice.Ruolo value) {
		getSession().put("appDataruolo", value);
	}

	public it.csi.mdp.mdpboweb.dto.nsbackoffice.Ruolo getAppDataruolo() {
		return (it.csi.mdp.mdpboweb.dto.nsbackoffice.Ruolo) (getSession()
				.get("appDataruolo"));
	}

	public void setAppDataapplicazioni(
			java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Applicazione> value) {
		getSession().put("appDataapplicazioni", value);
	}

	public java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Applicazione> getAppDataapplicazioni() {
		return (java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Applicazione>) (getSession()
				.get("appDataapplicazioni"));
	}

	public void setAppDataisPostBack(java.lang.String value) {
		getSession().put("appDataisPostBack", value);
	}

	public java.lang.String getAppDataisPostBack() {
		return (java.lang.String) (getSession().get("appDataisPostBack"));
	}

	public void setAppDataselettoreGruppo(java.lang.String value) {
		getSession().put("appDataselettoreGruppo", value);
	}

	public java.lang.String getAppDataselettoreGruppo() {
		return (java.lang.String) (getSession().get("appDataselettoreGruppo"));
	}

	public void setAppDatautentiDelGruppo(
			java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Utente> value) {
		getSession().put("appDatautentiDelGruppo", value);
	}

	public java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Utente> getAppDatautentiDelGruppo() {
		return (java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Utente>) (getSession()
				.get("appDatautentiDelGruppo"));
	}

	////////////////////////////////////////////////////////////////////
	/// campi per widget semplici
	////////////////////////////////////////////////////////////////////

	// Table tbListaUtenti

	private java.util.ArrayList<java.lang.String> _widg_tbListaUtenti;

	public void setWidg_tbListaUtenti(
			java.util.ArrayList<java.lang.String> value) {
		_widg_tbListaUtenti = value;
	}

	public java.util.ArrayList<java.lang.String> getWidg_tbListaUtenti() {
		return _widg_tbListaUtenti;
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
