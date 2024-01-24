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
 * logica di business associata alla Schermata [cpRicercaEnti]
 */
public class CpRicercaEntiModel extends BaseSessionAwareDTO {

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

	public void setAppDatauserInfoExt(
			it.csi.mdp.mdpboweb.dto.nsbackoffice.UserInfoExt value) {
		getSession().put("appDatauserInfoExt", value);
	}

	public it.csi.mdp.mdpboweb.dto.nsbackoffice.UserInfoExt getAppDatauserInfoExt() {
		return (it.csi.mdp.mdpboweb.dto.nsbackoffice.UserInfoExt) (getSession()
				.get("appDatauserInfoExt"));
	}

	public void setAppDataenti(it.csi.mdp.mdpboweb.dto.nsbackoffice.Enti value) {
		getSession().put("appDataenti", value);
	}

	public it.csi.mdp.mdpboweb.dto.nsbackoffice.Enti getAppDataenti() {
		return (it.csi.mdp.mdpboweb.dto.nsbackoffice.Enti) (getSession()
				.get("appDataenti"));
	}

	public void setAppDataricercaEnti(
			it.csi.mdp.mdpboweb.dto.nsbackoffice.Enti value) {
		getSession().put("appDataricercaEnti", value);
	}

	public it.csi.mdp.mdpboweb.dto.nsbackoffice.Enti getAppDataricercaEnti() {
		return (it.csi.mdp.mdpboweb.dto.nsbackoffice.Enti) (getSession()
				.get("appDataricercaEnti"));
	}

	public void setAppDatalistaEnti(
			java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Enti> value) {
		getSession().put("appDatalistaEnti", value);
	}

	public java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Enti> getAppDatalistaEnti() {
		return (java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Enti>) (getSession()
				.get("appDatalistaEnti"));
	}

	public void setAppDataselettoreIdEnte(java.lang.String value) {
		getSession().put("appDataselettoreIdEnte", value);
	}

	public java.lang.String getAppDataselettoreIdEnte() {
		return (java.lang.String) (getSession().get("appDataselettoreIdEnte"));
	}

	public void setAppDatalistaStatiAttivazioneEnti(
			java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.StatoAttivazioneEnti> value) {
		getSession().put("appDatalistaStatiAttivazioneEnti", value);
	}

	public java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.StatoAttivazioneEnti> getAppDatalistaStatiAttivazioneEnti() {
		return (java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.StatoAttivazioneEnti>) (getSession()
				.get("appDatalistaStatiAttivazioneEnti"));
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
