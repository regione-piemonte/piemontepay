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
 * logica di business associata alla Schermata [cpInformativePsp]
 */
public class CpInformativePspModel extends BaseSessionAwareDTO {

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

	public void setAppDatainformativePsp(
			it.csi.mdp.mdpboweb.dto.nsbackoffice.InformativePsp value) {
		getSession().put("appDatainformativePsp", value);
	}

	public it.csi.mdp.mdpboweb.dto.nsbackoffice.InformativePsp getAppDatainformativePsp() {
		return (it.csi.mdp.mdpboweb.dto.nsbackoffice.InformativePsp) (getSession()
				.get("appDatainformativePsp"));
	}

	public void setAppDataisPostBack(java.lang.String value) {
		getSession().put("appDataisPostBack", value);
	}

	public java.lang.String getAppDataisPostBack() {
		return (java.lang.String) (getSession().get("appDataisPostBack"));
	}

	public void setAppDataselettoreInformativePsp(java.lang.String value) {
		getSession().put("appDataselettoreInformativePsp", value);
	}

	public java.lang.String getAppDataselettoreInformativePsp() {
		return (java.lang.String) (getSession()
				.get("appDataselettoreInformativePsp"));
	}

	public void setAppDatauserInfoExt(
			it.csi.mdp.mdpboweb.dto.nsbackoffice.UserInfoExt value) {
		getSession().put("appDatauserInfoExt", value);
	}

	public it.csi.mdp.mdpboweb.dto.nsbackoffice.UserInfoExt getAppDatauserInfoExt() {
		return (it.csi.mdp.mdpboweb.dto.nsbackoffice.UserInfoExt) (getSession()
				.get("appDatauserInfoExt"));
	}

	public void setAppDatalistaInformativePsp(
			java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.InformativePsp> value) {
		getSession().put("appDatalistaInformativePsp", value);
	}

	public java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.InformativePsp> getAppDatalistaInformativePsp() {
		return (java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.InformativePsp>) (getSession()
				.get("appDatalistaInformativePsp"));
	}

	public void setAppDatafiltroRicercaInformativePsp(
			it.csi.mdp.mdpboweb.dto.nsbackoffice.InformativePsp value) {
		getSession().put("appDatafiltroRicercaInformativePsp", value);
	}

	public it.csi.mdp.mdpboweb.dto.nsbackoffice.InformativePsp getAppDatafiltroRicercaInformativePsp() {
		return (it.csi.mdp.mdpboweb.dto.nsbackoffice.InformativePsp) (getSession()
				.get("appDatafiltroRicercaInformativePsp"));
	}

	public void setAppDatalistaTipoversamento(
			java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.TipoVersamento> value) {
		getSession().put("appDatalistaTipoversamento", value);
	}

	public java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.TipoVersamento> getAppDatalistaTipoversamento() {
		return (java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.TipoVersamento>) (getSession()
				.get("appDatalistaTipoversamento"));
	}

	////////////////////////////////////////////////////////////////////
	/// campi per widget semplici
	////////////////////////////////////////////////////////////////////

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
