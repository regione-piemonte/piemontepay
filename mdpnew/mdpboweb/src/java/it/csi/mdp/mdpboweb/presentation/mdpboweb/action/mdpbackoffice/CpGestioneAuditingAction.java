/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.presentation.mdpboweb.action.mdpbackoffice;

import java.util.*;

import java.lang.reflect.InvocationTargetException;
import java.beans.IntrospectionException;

import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.validator.annotations.*;
import com.opensymphony.xwork2.conversion.annotations.*;
import com.opensymphony.xwork2.ActionContext;

import it.csi.mdp.mdpboweb.dto.*;

import it.csi.mdp.mdpboweb.presentation.mdpboweb.security.*;

import it.csi.mdp.mdpboweb.business.*;

import it.csi.mdp.mdpboweb.presentation.mdpboweb.action.*;

import it.csi.mdp.mdpboweb.presentation.mdpboweb.interceptor.MethodProtection;

import it.csi.mdp.mdpboweb.presentation.mdpboweb.interceptor.FatClientOnly;
import it.csi.mdp.mdpboweb.presentation.uiutils.*;
import flexjson.JSON;

/**
 * CpGestioneAuditingAction Action Class.
 *
 * @author GuiGen
 */
@Validation()
@Conversion()
public class CpGestioneAuditingAction extends BaseAction implements Preparable {

	// Table tAuditItems

	private java.util.ArrayList<java.lang.String> _widg_tAuditItems;

	public void setWidg_tAuditItems(java.util.ArrayList<java.lang.String> value) {
		_widg_tAuditItems = value;
	}

	public java.util.ArrayList<java.lang.String> getWidg_tAuditItems() {
		return _widg_tAuditItems;
	}

	public void setAppDatacurrentUser(
			it.csi.mdp.mdpboweb.dto.common.UserInfo value) {
		getSession().put("appDatacurrentUser", value);
	}

	public it.csi.mdp.mdpboweb.dto.common.UserInfo getAppDatacurrentUser() {
		return (it.csi.mdp.mdpboweb.dto.common.UserInfo) (getSession()
				.get("appDatacurrentUser"));
	}

	public void setAppDataappForAudit(
			java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Applicazione> value) {
		getSession().put("appDataappForAudit", value);
	}

	public java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Applicazione> getAppDataappForAudit() {
		return (java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Applicazione>) (getSession()
				.get("appDataappForAudit"));
	}

	public void setAppDataappForAuditFiltered(
			java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Applicazione> value) {
		getSession().put("appDataappForAuditFiltered", value);
	}

	public java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Applicazione> getAppDataappForAuditFiltered() {
		return (java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Applicazione>) (getSession()
				.get("appDataappForAuditFiltered"));
	}

	public void setAppDataapplicazione(
			it.csi.mdp.mdpboweb.dto.nsbackoffice.Applicazione value) {
		getSession().put("appDataapplicazione", value);
	}

	public it.csi.mdp.mdpboweb.dto.nsbackoffice.Applicazione getAppDataapplicazione() {
		return (it.csi.mdp.mdpboweb.dto.nsbackoffice.Applicazione) (getSession()
				.get("appDataapplicazione"));
	}

	public void setAppDataapplicazioni(
			java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Applicazione> value) {
		getSession().put("appDataapplicazioni", value);
	}

	public java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Applicazione> getAppDataapplicazioni() {
		return (java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Applicazione>) (getSession()
				.get("appDataapplicazioni"));
	}

	public void setAppDataassociazioneGW_MP(
			it.csi.mdp.mdpboweb.dto.nsbackoffice.AssociazioneGW_PM value) {
		getSession().put("appDataassociazioneGW_MP", value);
	}

	public it.csi.mdp.mdpboweb.dto.nsbackoffice.AssociazioneGW_PM getAppDataassociazioneGW_MP() {
		return (it.csi.mdp.mdpboweb.dto.nsbackoffice.AssociazioneGW_PM) (getSession()
				.get("appDataassociazioneGW_MP"));
	}

	public void setAppDataassociazioniGW_MP(
			java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.AssociazioneGW_PM> value) {
		getSession().put("appDataassociazioniGW_MP", value);
	}

	public java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.AssociazioneGW_PM> getAppDataassociazioniGW_MP() {
		return (java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.AssociazioneGW_PM>) (getSession()
				.get("appDataassociazioniGW_MP"));
	}

	public void setAppDataaudit(it.csi.mdp.mdpboweb.dto.nsbackoffice.Audit value) {
		getSession().put("appDataaudit", value);
	}

	public it.csi.mdp.mdpboweb.dto.nsbackoffice.Audit getAppDataaudit() {
		return (it.csi.mdp.mdpboweb.dto.nsbackoffice.Audit) (getSession()
				.get("appDataaudit"));
	}

	public void setAppDataaudities(
			java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Audit> value) {
		getSession().put("appDataaudities", value);
	}

	public java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Audit> getAppDataaudities() {
		return (java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Audit>) (getSession()
				.get("appDataaudities"));
	}

	public void setAppDataazione(
			it.csi.mdp.mdpboweb.dto.nsbackoffice.Azione value) {
		getSession().put("appDataazione", value);
	}

	public it.csi.mdp.mdpboweb.dto.nsbackoffice.Azione getAppDataazione() {
		return (it.csi.mdp.mdpboweb.dto.nsbackoffice.Azione) (getSession()
				.get("appDataazione"));
	}

	public void setAppDataazioni(
			java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Azione> value) {
		getSession().put("appDataazioni", value);
	}

	public java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Azione> getAppDataazioni() {
		return (java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Azione>) (getSession()
				.get("appDataazioni"));
	}

	public void setAppDataazioniForAudit(
			java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Azione> value) {
		getSession().put("appDataazioniForAudit", value);
	}

	public java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Azione> getAppDataazioniForAudit() {
		return (java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Azione>) (getSession()
				.get("appDataazioniForAudit"));
	}

	public void setAppDataazioniForAuditFiltered(
			java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Azione> value) {
		getSession().put("appDataazioniForAuditFiltered", value);
	}

	public java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Azione> getAppDataazioniForAuditFiltered() {
		return (java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Azione>) (getSession()
				.get("appDataazioniForAuditFiltered"));
	}

	public void setAppDatachkAbilitaAssGW_MP(java.lang.String value) {
		getSession().put("appDatachkAbilitaAssGW_MP", value);
	}

	public java.lang.String getAppDatachkAbilitaAssGW_MP() {
		return (java.lang.String) (getSession()
				.get("appDatachkAbilitaAssGW_MP"));
	}

	public void setAppDataextraAttributes(
			java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.ExtraAttribute> value) {
		getSession().put("appDataextraAttributes", value);
	}

	public java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.ExtraAttribute> getAppDataextraAttributes() {
		return (java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.ExtraAttribute>) (getSession()
				.get("appDataextraAttributes"));
	}

	public void setAppDatafilteredApplication(
			java.util.ArrayList<java.lang.String> value) {
		getSession().put("appDatafilteredApplication", value);
	}

	public java.util.ArrayList<java.lang.String> getAppDatafilteredApplication() {
		return (java.util.ArrayList<java.lang.String>) (getSession()
				.get("appDatafilteredApplication"));
	}

	public void setAppDatafilteredAzioni(
			java.util.ArrayList<java.lang.String> value) {
		getSession().put("appDatafilteredAzioni", value);
	}

	public java.util.ArrayList<java.lang.String> getAppDatafilteredAzioni() {
		return (java.util.ArrayList<java.lang.String>) (getSession()
				.get("appDatafilteredAzioni"));
	}

	public void setAppDatagateway(
			it.csi.mdp.mdpboweb.dto.nsbackoffice.Gateway value) {
		getSession().put("appDatagateway", value);
	}

	public it.csi.mdp.mdpboweb.dto.nsbackoffice.Gateway getAppDatagateway() {
		return (it.csi.mdp.mdpboweb.dto.nsbackoffice.Gateway) (getSession()
				.get("appDatagateway"));
	}

	public void setAppDatagateways(
			java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Gateway> value) {
		getSession().put("appDatagateways", value);
	}

	public java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Gateway> getAppDatagateways() {
		return (java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Gateway>) (getSession()
				.get("appDatagateways"));
	}

	public void setAppDataisPostBack(java.lang.String value) {
		getSession().put("appDataisPostBack", value);
	}

	public java.lang.String getAppDataisPostBack() {
		return (java.lang.String) (getSession().get("appDataisPostBack"));
	}

	public void setAppDatalastWhereClause(java.lang.String value) {
		getSession().put("appDatalastWhereClause", value);
	}

	public java.lang.String getAppDatalastWhereClause() {
		return (java.lang.String) (getSession().get("appDatalastWhereClause"));
	}

	public void setAppDatanuovaAssociazioneGW_MP(
			it.csi.mdp.mdpboweb.dto.nsbackoffice.AssociazioneGW_PM value) {
		getSession().put("appDatanuovaAssociazioneGW_MP", value);
	}

	public it.csi.mdp.mdpboweb.dto.nsbackoffice.AssociazioneGW_PM getAppDatanuovaAssociazioneGW_MP() {
		return (it.csi.mdp.mdpboweb.dto.nsbackoffice.AssociazioneGW_PM) (getSession()
				.get("appDatanuovaAssociazioneGW_MP"));
	}

	public void setAppDatanuovoExtraAttribute(
			it.csi.mdp.mdpboweb.dto.nsbackoffice.ExtraAttribute value) {
		getSession().put("appDatanuovoExtraAttribute", value);
	}

	public it.csi.mdp.mdpboweb.dto.nsbackoffice.ExtraAttribute getAppDatanuovoExtraAttribute() {
		return (it.csi.mdp.mdpboweb.dto.nsbackoffice.ExtraAttribute) (getSession()
				.get("appDatanuovoExtraAttribute"));
	}

	public void setAppDatapaymentMode(
			it.csi.mdp.mdpboweb.dto.nsbackoffice.PaymentMode value) {
		getSession().put("appDatapaymentMode", value);
	}

	public it.csi.mdp.mdpboweb.dto.nsbackoffice.PaymentMode getAppDatapaymentMode() {
		return (it.csi.mdp.mdpboweb.dto.nsbackoffice.PaymentMode) (getSession()
				.get("appDatapaymentMode"));
	}

	public void setAppDatapaymentModes(
			java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.PaymentMode> value) {
		getSession().put("appDatapaymentModes", value);
	}

	public java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.PaymentMode> getAppDatapaymentModes() {
		return (java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.PaymentMode>) (getSession()
				.get("appDatapaymentModes"));
	}

	public void setAppDataricercaAudit(
			it.csi.mdp.mdpboweb.dto.nsbackoffice.Audit value) {
		getSession().put("appDataricercaAudit", value);
	}

	public it.csi.mdp.mdpboweb.dto.nsbackoffice.Audit getAppDataricercaAudit() {
		return (it.csi.mdp.mdpboweb.dto.nsbackoffice.Audit) (getSession()
				.get("appDataricercaAudit"));
	}

	public void setAppDataricercaTransazione(
			it.csi.mdp.mdpboweb.dto.nsbackoffice.Transazione value) {
		getSession().put("appDataricercaTransazione", value);
	}

	public it.csi.mdp.mdpboweb.dto.nsbackoffice.Transazione getAppDataricercaTransazione() {
		return (it.csi.mdp.mdpboweb.dto.nsbackoffice.Transazione) (getSession()
				.get("appDataricercaTransazione"));
	}

	public void setAppDataselettoreApp1(java.lang.String value) {
		getSession().put("appDataselettoreApp1", value);
	}

	public java.lang.String getAppDataselettoreApp1() {
		return (java.lang.String) (getSession().get("appDataselettoreApp1"));
	}

	public void setAppDataselettoreApp2(java.lang.String value) {
		getSession().put("appDataselettoreApp2", value);
	}

	public java.lang.String getAppDataselettoreApp2() {
		return (java.lang.String) (getSession().get("appDataselettoreApp2"));
	}

	public void setAppDataselettoreIdTransazione(java.lang.String value) {
		getSession().put("appDataselettoreIdTransazione", value);
	}

	public java.lang.String getAppDataselettoreIdTransazione() {
		return (java.lang.String) (getSession()
				.get("appDataselettoreIdTransazione"));
	}

	public void setAppDataselettoreOperazione(java.lang.String value) {
		getSession().put("appDataselettoreOperazione", value);
	}

	public java.lang.String getAppDataselettoreOperazione() {
		return (java.lang.String) (getSession()
				.get("appDataselettoreOperazione"));
	}

	public void setAppDataselettoreChiaveAttr(java.lang.String value) {
		getSession().put("appDataselettoreChiaveAttr", value);
	}

	public java.lang.String getAppDataselettoreChiaveAttr() {
		return (java.lang.String) (getSession()
				.get("appDataselettoreChiaveAttr"));
	}

	public void setAppDataselettoreIdApplicazione(java.lang.String value) {
		getSession().put("appDataselettoreIdApplicazione", value);
	}

	public java.lang.String getAppDataselettoreIdApplicazione() {
		return (java.lang.String) (getSession()
				.get("appDataselettoreIdApplicazione"));
	}

	public void setAppDataselettoreIdGateway(java.lang.String value) {
		getSession().put("appDataselettoreIdGateway", value);
	}

	public java.lang.String getAppDataselettoreIdGateway() {
		return (java.lang.String) (getSession()
				.get("appDataselettoreIdGateway"));
	}

	public void setAppDataselettoreIdPaymentMode(java.lang.String value) {
		getSession().put("appDataselettoreIdPaymentMode", value);
	}

	public java.lang.String getAppDataselettoreIdPaymentMode() {
		return (java.lang.String) (getSession()
				.get("appDataselettoreIdPaymentMode"));
	}

	public void setAppDataselettoreAzione1(java.lang.String value) {
		getSession().put("appDataselettoreAzione1", value);
	}

	public java.lang.String getAppDataselettoreAzione1() {
		return (java.lang.String) (getSession().get("appDataselettoreAzione1"));
	}

	public void setAppDataselettoreItemAssociazioneGW_MP(java.lang.String value) {
		getSession().put("appDataselettoreItemAssociazioneGW_MP", value);
	}

	public java.lang.String getAppDataselettoreItemAssociazioneGW_MP() {
		return (java.lang.String) (getSession()
				.get("appDataselettoreItemAssociazioneGW_MP"));
	}

	public void setAppDataselettoreAzione2(java.lang.String value) {
		getSession().put("appDataselettoreAzione2", value);
	}

	public java.lang.String getAppDataselettoreAzione2() {
		return (java.lang.String) (getSession().get("appDataselettoreAzione2"));
	}

	public void setAppDatastatiTransazione(
			java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.StatoTransazione> value) {
		getSession().put("appDatastatiTransazione", value);
	}

	public java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.StatoTransazione> getAppDatastatiTransazione() {
		return (java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.StatoTransazione>) (getSession()
				.get("appDatastatiTransazione"));
	}

	public void setAppDataselettoreUtente1(java.lang.String value) {
		getSession().put("appDataselettoreUtente1", value);
	}

	public java.lang.String getAppDataselettoreUtente1() {
		return (java.lang.String) (getSession().get("appDataselettoreUtente1"));
	}

	public void setAppDatatransazione(
			it.csi.mdp.mdpboweb.dto.nsbackoffice.Transazione value) {
		getSession().put("appDatatransazione", value);
	}

	public it.csi.mdp.mdpboweb.dto.nsbackoffice.Transazione getAppDatatransazione() {
		return (it.csi.mdp.mdpboweb.dto.nsbackoffice.Transazione) (getSession()
				.get("appDatatransazione"));
	}

	public void setAppDatastatoTransazione(
			it.csi.mdp.mdpboweb.dto.nsbackoffice.StatoTransazione value) {
		getSession().put("appDatastatoTransazione", value);
	}

	public it.csi.mdp.mdpboweb.dto.nsbackoffice.StatoTransazione getAppDatastatoTransazione() {
		return (it.csi.mdp.mdpboweb.dto.nsbackoffice.StatoTransazione) (getSession()
				.get("appDatastatoTransazione"));
	}

	public void setAppDataselettoreUtente2(java.lang.String value) {
		getSession().put("appDataselettoreUtente2", value);
	}

	public java.lang.String getAppDataselettoreUtente2() {
		return (java.lang.String) (getSession().get("appDataselettoreUtente2"));
	}

	public void setAppDatatransazioni(
			java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Transazione> value) {
		getSession().put("appDatatransazioni", value);
	}

	public java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Transazione> getAppDatatransazioni() {
		return (java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Transazione>) (getSession()
				.get("appDatatransazioni"));
	}

	public void setAppDatautente(
			it.csi.mdp.mdpboweb.dto.nsbackoffice.Utente value) {
		getSession().put("appDatautente", value);
	}

	public it.csi.mdp.mdpboweb.dto.nsbackoffice.Utente getAppDatautente() {
		return (it.csi.mdp.mdpboweb.dto.nsbackoffice.Utente) (getSession()
				.get("appDatautente"));
	}

	public void setAppDatautenti(
			java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Utente> value) {
		getSession().put("appDatautenti", value);
	}

	public java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Utente> getAppDatautenti() {
		return (java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Utente>) (getSession()
				.get("appDatautenti"));
	}

	public void setAppDatautentiForAudit(
			java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Utente> value) {
		getSession().put("appDatautentiForAudit", value);
	}

	public java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Utente> getAppDatautentiForAudit() {
		return (java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Utente>) (getSession()
				.get("appDatautentiForAudit"));
	}

	public void setAppDatautentiForAuditFiltered(
			java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Utente> value) {
		getSession().put("appDatautentiForAuditFiltered", value);
	}

	public java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Utente> getAppDatautentiForAuditFiltered() {
		return (java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Utente>) (getSession()
				.get("appDatautentiForAuditFiltered"));
	}

	public void setAppDatafilteredUtenti(
			java.util.ArrayList<java.lang.String> value) {
		getSession().put("appDatafilteredUtenti", value);
	}

	public java.util.ArrayList<java.lang.String> getAppDatafilteredUtenti() {
		return (java.util.ArrayList<java.lang.String>) (getSession()
				.get("appDatafilteredUtenti"));
	}

	public void setAppDatauserInfoExt(
			it.csi.mdp.mdpboweb.dto.nsbackoffice.UserInfoExt value) {
		getSession().put("appDatauserInfoExt", value);
	}

	public it.csi.mdp.mdpboweb.dto.nsbackoffice.UserInfoExt getAppDatauserInfoExt() {
		return (it.csi.mdp.mdpboweb.dto.nsbackoffice.UserInfoExt) (getSession()
				.get("appDatauserInfoExt"));
	}

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

	/**
	 * classe model associata
	 */
	public Class modelClass() {
		return it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneAuditingModel.class;
	}

	/**
	 * I singoli eventi sui singoli widget sono gestiti dai metodi specifici
	 * @return SUCCESS.
	 */
	@SkipValidation
	public String execute() throws CommandExecutionException {
		// esegue eventuali comandi di clear appdata
		ICommand clearCmd = (ICommand) session
				.get(PENDING_CLEAR_COMMAND_ATTRIBUTE);
		if (clearCmd != null) {
			clearCmd.doCommand(this);
			session.remove(PENDING_CLEAR_COMMAND_ATTRIBUTE);
		}
		return SUCCESS;
	}

	//////////////////////////////////////////////////////////////////////////////////
	/// metodi specifici per la gestione del singolo tipo di evento sul singolo widget
	/// contenuto nel contentPanel
	/// metodo: handle<nomeWidget>_<NOME_EVENTO>
	/// es: handletreeVoci_CLICKED
	//////////////////////////////////////////////////////////////////////////////////

	/**
	 * Gestione dell'evento CLICKED sul widget [btnAddAppFiltro]
	 */
	@MethodProtection(level = "REJECT_SAME")
	public String handleBtnAddAppFiltro_CLICKED()
			throws CommandExecutionException {
		ICommand action = initCommand("btnAddAppFiltro", "CLICKED");
		if (log.isDebugEnabled()) {
			log.debug("[CpGestioneAuditingAction::handleBtnAddAppFiltro_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpGestioneAuditingAction::handleBtnAddAppFiltro_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpGestioneAuditingAction::handleBtnAddAppFiltro_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpGestioneAuditingAction::handleBtnAddAppFiltro_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpGestioneAuditingAction::handleBtnAddAppFiltro_CLICKED] returning default result [SUCCESS]");
			}
			return SUCCESS;

		}
	}

	/**
	 * Gestione dell'evento CLICKED sul widget [btnEliminAppFiltro]
	 */
	@MethodProtection(level = "REJECT_SAME")
	public String handleBtnEliminAppFiltro_CLICKED()
			throws CommandExecutionException {
		ICommand action = initCommand("btnEliminAppFiltro", "CLICKED");
		if (log.isDebugEnabled()) {
			log.debug("[CpGestioneAuditingAction::handleBtnEliminAppFiltro_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpGestioneAuditingAction::handleBtnEliminAppFiltro_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpGestioneAuditingAction::handleBtnEliminAppFiltro_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpGestioneAuditingAction::handleBtnEliminAppFiltro_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpGestioneAuditingAction::handleBtnEliminAppFiltro_CLICKED] returning default result [SUCCESS]");
			}
			return SUCCESS;

		}
	}

	/**
	 * Gestione dell'evento CLICKED sul widget [btnAddAzioneFiltro]
	 */
	@MethodProtection(level = "REJECT_SAME")
	public String handleBtnAddAzioneFiltro_CLICKED()
			throws CommandExecutionException {
		ICommand action = initCommand("btnAddAzioneFiltro", "CLICKED");
		if (log.isDebugEnabled()) {
			log.debug("[CpGestioneAuditingAction::handleBtnAddAzioneFiltro_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpGestioneAuditingAction::handleBtnAddAzioneFiltro_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpGestioneAuditingAction::handleBtnAddAzioneFiltro_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpGestioneAuditingAction::handleBtnAddAzioneFiltro_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpGestioneAuditingAction::handleBtnAddAzioneFiltro_CLICKED] returning default result [SUCCESS]");
			}
			return SUCCESS;

		}
	}

	/**
	 * Gestione dell'evento CLICKED sul widget [btnEliminAzioneFiltro]
	 */
	@MethodProtection(level = "REJECT_SAME")
	public String handleBtnEliminAzioneFiltro_CLICKED()
			throws CommandExecutionException {
		ICommand action = initCommand("btnEliminAzioneFiltro", "CLICKED");
		if (log.isDebugEnabled()) {
			log.debug("[CpGestioneAuditingAction::handleBtnEliminAzioneFiltro_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpGestioneAuditingAction::handleBtnEliminAzioneFiltro_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpGestioneAuditingAction::handleBtnEliminAzioneFiltro_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpGestioneAuditingAction::handleBtnEliminAzioneFiltro_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpGestioneAuditingAction::handleBtnEliminAzioneFiltro_CLICKED] returning default result [SUCCESS]");
			}
			return SUCCESS;

		}
	}

	/**
	 * Gestione dell'evento CLICKED sul widget [btnAddUtenteFiltro]
	 */
	@MethodProtection(level = "REJECT_SAME")
	public String handleBtnAddUtenteFiltro_CLICKED()
			throws CommandExecutionException {
		ICommand action = initCommand("btnAddUtenteFiltro", "CLICKED");
		if (log.isDebugEnabled()) {
			log.debug("[CpGestioneAuditingAction::handleBtnAddUtenteFiltro_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpGestioneAuditingAction::handleBtnAddUtenteFiltro_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpGestioneAuditingAction::handleBtnAddUtenteFiltro_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpGestioneAuditingAction::handleBtnAddUtenteFiltro_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpGestioneAuditingAction::handleBtnAddUtenteFiltro_CLICKED] returning default result [SUCCESS]");
			}
			return SUCCESS;

		}
	}

	/**
	 * Gestione dell'evento CLICKED sul widget [btnEliminUtenteFiltro]
	 */
	@MethodProtection(level = "REJECT_SAME")
	public String handleBtnEliminUtenteFiltro_CLICKED()
			throws CommandExecutionException {
		ICommand action = initCommand("btnEliminUtenteFiltro", "CLICKED");
		if (log.isDebugEnabled()) {
			log.debug("[CpGestioneAuditingAction::handleBtnEliminUtenteFiltro_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpGestioneAuditingAction::handleBtnEliminUtenteFiltro_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpGestioneAuditingAction::handleBtnEliminUtenteFiltro_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpGestioneAuditingAction::handleBtnEliminUtenteFiltro_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpGestioneAuditingAction::handleBtnEliminUtenteFiltro_CLICKED] returning default result [SUCCESS]");
			}
			return SUCCESS;

		}
	}

	/**
	 * Gestione dell'evento CLICKED sul widget [btnCerca]
	 */
	@MethodProtection(level = "REJECT_SAME")
	public String handleBtnCerca_CLICKED() throws CommandExecutionException {
		ICommand action = initCommand("btnCerca", "CLICKED");
		if (log.isDebugEnabled()) {
			log.debug("[CpGestioneAuditingAction::handleBtnCerca_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpGestioneAuditingAction::handleBtnCerca_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpGestioneAuditingAction::handleBtnCerca_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpGestioneAuditingAction::handleBtnCerca_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpGestioneAuditingAction::handleBtnCerca_CLICKED] returning default result [SUCCESS]");
			}
			return SUCCESS;

		}
	}

	//////////////////////////////////////////////////////////////////////////////////
	/// metodo di data providing sull'intero ContentPanel
	/// metodo: provide_CPDATA
	//////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////
	/// metodi di data providing sui widget dotati di multi data binding
	/// contenuti nel contentPanel
	/// metodo: provide<nomeWidget>_<tipologia dati>
	/// es: provideCbComuni_DATASET
	//////////////////////////////////////////////////////////////////////////////////

	/**
	 * Prepara l'input stream referenziato dal result "provideData" con i valori relativi 
	 * al data-binding relativo al dataset DATASET del widget cbAppToAdd.
	 * @return sempre il result "provideData"
	 */
	@SkipValidation
	@FatClientOnly
	public String provideCbAppToAdd_DATASET() throws CommandExecutionException {

		// reperisce il dato da serializzare
		com.opensymphony.xwork2.ActionContext ctx = com.opensymphony.xwork2.ActionContext
				.getContext();
		Object dataToProvide = ctx.getValueStack().findValue(
				"appDataappForAudit");

		// serializza
		flexjson.JSONSerializer serializer = new flexjson.JSONSerializer();

		String response = serializer.exclude("*.class").deepSerialize(
				dataToProvide);
		// imposta l'input stream di risposta
		java.io.ByteArrayInputStream bais;
		try {
			bais = new java.io.ByteArrayInputStream(response.getBytes("UTF-8"));
			dataProviderStream = bais;
			// risposta
			return "provideData";
		} catch (java.io.UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new CommandExecutionException("errore provide Data", e);
		}
	}

	/**
	 * Prepara l'input stream referenziato dal result "provideData" con i valori relativi 
	 * al data-binding relativo al dataset DATASET del widget cbAppFiltered.
	 * @return sempre il result "provideData"
	 */
	@SkipValidation
	@FatClientOnly
	public String provideCbAppFiltered_DATASET()
			throws CommandExecutionException {

		// reperisce il dato da serializzare
		com.opensymphony.xwork2.ActionContext ctx = com.opensymphony.xwork2.ActionContext
				.getContext();
		Object dataToProvide = ctx.getValueStack().findValue(
				"appDataappForAuditFiltered");

		// serializza
		flexjson.JSONSerializer serializer = new flexjson.JSONSerializer();

		String response = serializer.exclude("*.class").deepSerialize(
				dataToProvide);
		// imposta l'input stream di risposta
		java.io.ByteArrayInputStream bais;
		try {
			bais = new java.io.ByteArrayInputStream(response.getBytes("UTF-8"));
			dataProviderStream = bais;
			// risposta
			return "provideData";
		} catch (java.io.UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new CommandExecutionException("errore provide Data", e);
		}
	}

	/**
	 * Prepara l'input stream referenziato dal result "provideData" con i valori relativi 
	 * al data-binding relativo al dataset DATASET del widget cbAzioneToAdd.
	 * @return sempre il result "provideData"
	 */
	@SkipValidation
	@FatClientOnly
	public String provideCbAzioneToAdd_DATASET()
			throws CommandExecutionException {

		// reperisce il dato da serializzare
		com.opensymphony.xwork2.ActionContext ctx = com.opensymphony.xwork2.ActionContext
				.getContext();
		Object dataToProvide = ctx.getValueStack().findValue(
				"appDataazioniForAudit");

		// serializza
		flexjson.JSONSerializer serializer = new flexjson.JSONSerializer();

		String response = serializer.exclude("*.class").deepSerialize(
				dataToProvide);
		// imposta l'input stream di risposta
		java.io.ByteArrayInputStream bais;
		try {
			bais = new java.io.ByteArrayInputStream(response.getBytes("UTF-8"));
			dataProviderStream = bais;
			// risposta
			return "provideData";
		} catch (java.io.UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new CommandExecutionException("errore provide Data", e);
		}
	}

	/**
	 * Prepara l'input stream referenziato dal result "provideData" con i valori relativi 
	 * al data-binding relativo al dataset DATASET del widget cbAzioneFiltered.
	 * @return sempre il result "provideData"
	 */
	@SkipValidation
	@FatClientOnly
	public String provideCbAzioneFiltered_DATASET()
			throws CommandExecutionException {

		// reperisce il dato da serializzare
		com.opensymphony.xwork2.ActionContext ctx = com.opensymphony.xwork2.ActionContext
				.getContext();
		Object dataToProvide = ctx.getValueStack().findValue(
				"appDataazioniForAuditFiltered");

		// serializza
		flexjson.JSONSerializer serializer = new flexjson.JSONSerializer();

		String response = serializer.exclude("*.class").deepSerialize(
				dataToProvide);
		// imposta l'input stream di risposta
		java.io.ByteArrayInputStream bais;
		try {
			bais = new java.io.ByteArrayInputStream(response.getBytes("UTF-8"));
			dataProviderStream = bais;
			// risposta
			return "provideData";
		} catch (java.io.UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new CommandExecutionException("errore provide Data", e);
		}
	}

	/**
	 * Prepara l'input stream referenziato dal result "provideData" con i valori relativi 
	 * al data-binding relativo al dataset DATASET del widget cbUtenteToAdd.
	 * @return sempre il result "provideData"
	 */
	@SkipValidation
	@FatClientOnly
	public String provideCbUtenteToAdd_DATASET()
			throws CommandExecutionException {

		// reperisce il dato da serializzare
		com.opensymphony.xwork2.ActionContext ctx = com.opensymphony.xwork2.ActionContext
				.getContext();
		Object dataToProvide = ctx.getValueStack().findValue(
				"appDatautentiForAudit");

		// serializza
		flexjson.JSONSerializer serializer = new flexjson.JSONSerializer();

		String response = serializer.exclude("*.class").deepSerialize(
				dataToProvide);
		// imposta l'input stream di risposta
		java.io.ByteArrayInputStream bais;
		try {
			bais = new java.io.ByteArrayInputStream(response.getBytes("UTF-8"));
			dataProviderStream = bais;
			// risposta
			return "provideData";
		} catch (java.io.UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new CommandExecutionException("errore provide Data", e);
		}
	}

	/**
	 * Prepara l'input stream referenziato dal result "provideData" con i valori relativi 
	 * al data-binding relativo al dataset DATASET del widget cbUtenteFiltered.
	 * @return sempre il result "provideData"
	 */
	@SkipValidation
	@FatClientOnly
	public String provideCbUtenteFiltered_DATASET()
			throws CommandExecutionException {

		// reperisce il dato da serializzare
		com.opensymphony.xwork2.ActionContext ctx = com.opensymphony.xwork2.ActionContext
				.getContext();
		Object dataToProvide = ctx.getValueStack().findValue(
				"appDatautentiForAuditFiltered");

		// serializza
		flexjson.JSONSerializer serializer = new flexjson.JSONSerializer();

		String response = serializer.exclude("*.class").deepSerialize(
				dataToProvide);
		// imposta l'input stream di risposta
		java.io.ByteArrayInputStream bais;
		try {
			bais = new java.io.ByteArrayInputStream(response.getBytes("UTF-8"));
			dataProviderStream = bais;
			// risposta
			return "provideData";
		} catch (java.io.UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new CommandExecutionException("errore provide Data", e);
		}
	}

	/**
	 * Prepara l'input stream referenziato dal result "provideData" con i valori relativi 
	 * al data-binding relativo al dataset DATASET del widget tAuditItems.
	 * @return sempre il result "provideData"
	 */
	@SkipValidation
	@FatClientOnly
	public String provideTAuditItems_DATASET() throws CommandExecutionException {

		// reperisce il dato da serializzare
		com.opensymphony.xwork2.ActionContext ctx = com.opensymphony.xwork2.ActionContext
				.getContext();
		Object dataToProvide = ctx.getValueStack().findValue("appDataaudities");

		if (isTableClearStatus("cpGestioneAuditing_tAuditItems")) {
			tableStatus.setClearStatus(true);
		}
		TableDataArranger arranger = new TableDataArranger();
		if (dataToProvide != null) {
			dataToProvide = arranger.arrange((List) dataToProvide, tableStatus,
					filter);
		}

		// serializza
		flexjson.JSONSerializer serializer = new flexjson.JSONSerializer();

		String response = serializer.exclude("*.class").deepSerialize(
				dataToProvide);
		// imposta l'input stream di risposta
		java.io.ByteArrayInputStream bais;
		try {
			bais = new java.io.ByteArrayInputStream(response.getBytes("UTF-8"));
			dataProviderStream = bais;
			// risposta
			return "provideData";
		} catch (java.io.UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new CommandExecutionException("errore provide Data", e);
		}
	}

	/**
	 * Gestione della validazione
	 */
	public void validate() {
		/*PROTECTED REGION ID(R-496919473) ENABLED START*/
		/* Inserire la validazione */
		/*PROTECTED REGION END*/
	}

	protected Map<String, UISecConstraint> allVisibilityConstraints = null;
	protected Map<String, UISecConstraint> allOnOffConstraints = null;

	/**
	 * Metodo di preparazione della schermata/action
	 */
	public void prepare() throws CommandExecutionException {
		super.prepare();

		// cancellazione eventuale degli errori di conversione non desiderati
		clearConversionErrorsIfSkipValidation();

		// caricamento struttura di constraints
		if (allVisibilityConstraints == null)
			allVisibilityConstraints = getPageVisibilityUIConstraints();
		if (allOnOffConstraints == null)
			allOnOffConstraints = getPageONOFFUIConstraints();

		// comandi eseguiti ad ogni refresh solo se non sono in modalita' fatclient
		ActionContext ctx = ActionContext.getContext();
		String methodName = ctx.getActionInvocation().getProxy().getMethod();

		if (methodName.startsWith("execute")
				|| (!methodName.startsWith("exec") && !methodName
						.startsWith("provide"))) {
			// comandi eseguiti ad ogni refresh solo per i metodi non fatclient
			ICommand cmd = initOnRefreshCommand();
			cmd.doCommand(this);
		}

	}

	// ridefinizione dei metodi di verifica visibilita'/validazione
	// per supportare i security check

	public boolean isWidgetDisabled(String cpName, String widgShortName) {
		UISecConstraint ctr = allOnOffConstraints.get(widgShortName);
		if (ctr != null) {
			try {
				return !ctr.verifyConstraint(session,
						UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR,
						getSpringSecurityHelper());
			} catch (BEException ex) {
				log.error("[CpGestioneAuditingAction::isWidgetDisabled] errore durante verifica->disable");
				return true; // forzo la DISABILITAZIONE
			}
		} else
			return super.isWidgetDisabled(cpName, widgShortName);
	}

	public boolean isWidgetVisible(String cpName, String widgShortName) {
		UISecConstraint ctr = allVisibilityConstraints.get(widgShortName);
		if (ctr != null) {
			try {
				return ctr.verifyConstraint(session,
						UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR,
						getSpringSecurityHelper());
			} catch (BEException ex) {
				log.error("[CpGestioneAuditingAction::isWidgetVisible] errore durante verifica->hide");
				return false; // forzo l'invisibilita'
			}
		} else
			return super.isWidgetVisible(cpName, widgShortName);
	}

	/**
	 * inizializza la struttura dei command da eseguire al refresh del pannello
	 */
	private ICommand initOnRefreshCommand() {
		// ExecCommand begin
		String[] resultNames4refreshPanel = new String[]{"Ok_Iniziale"};

		ICommand[] actionsForResults4refreshPanel = new ICommand[1];
		// SequenceCommand begin
		// Screen State Command begin
		//prepara i nomi dei componenti target da abilitare/disabilitare
		String[] act_onRefresh_resok_inizialeActionstep_0_on = new String[]{
				"mnuView", "cercaDataInizio", "cercaDataFine", "idTransazione",
				"cbAppToAdd", "btnAddAppFiltro", "cbAzioneToAdd",
				"btnAddAzioneFiltro", "cbUtenteToAdd", "btnAddUtenteFiltro",
				"btnCerca", "cbUtenteFiltered", "cbAzioneFiltered",
				"cbAppFiltered", "btnEliminAppFiltro", "btnEliminAzioneFiltro",
				"btnEliminUtenteFiltro", "tAuditItems"};

		String[] act_onRefresh_resok_inizialeActionstep_0_off = new String[]{};

		String[] act_onRefresh_resok_inizialeActionstep_0_show = new String[]{
				"mnuView", "cercaDataInizio", "cercaDataFine", "idTransazione",
				"cbAppToAdd", "btnAddAppFiltro", "cbAzioneToAdd",
				"btnAddAzioneFiltro", "cbUtenteToAdd", "btnAddUtenteFiltro",
				"btnCerca", "cbAppFiltered", "btnEliminAppFiltro",
				"cbAzioneFiltered", "btnEliminAzioneFiltro",
				"cbUtenteFiltered", "btnEliminUtenteFiltro", "tAuditItems"};

		String[] act_onRefresh_resok_inizialeActionstep_0_hide = new String[]{};

		ScreenStateCommand act_onRefresh_resok_inizialeActionstep_0 = new ScreenStateCommand(
				"cpGestioneAuditing", "INIZIALE",
				act_onRefresh_resok_inizialeActionstep_0_on,
				act_onRefresh_resok_inizialeActionstep_0_off,
				act_onRefresh_resok_inizialeActionstep_0_show,
				act_onRefresh_resok_inizialeActionstep_0_hide);
		//Screen State Command end

		ICommand[] act_onRefresh_resok_inizialeAction_0_steps = new ICommand[]{act_onRefresh_resok_inizialeActionstep_0};
		SequenceCommand act_onRefresh_resok_inizialeAction_0 = new SequenceCommand(
				act_onRefresh_resok_inizialeAction_0_steps);
		// SequenceCommand end
		actionsForResults4refreshPanel[0] = act_onRefresh_resok_inizialeAction_0;

		ExecCommand act_onRefresh_1 = new ExecCommand(resultNames4refreshPanel,
				actionsForResults4refreshPanel) {
			public ExecResults doLogic(Object theModel)
					throws CommandExecutionException {
				it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch(
						it.csi.mdp.mdpboweb.util.Constants.APPLICATION_CODE);
				ExecResults result = null;
				try {
					watcher.start();
					result = getSpringBackEnd()
							.refreshPanel(
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneAuditingModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpGestioneAuditingAction",
							"readOne()", "chiamata verso BackEnd",
							"refreshPanel");
					return result;
				} catch (BEException e) {
					throw new CommandExecutionException(
							"errore non gestito nell'esecuzione del metodo [refreshPanel]:"
									+ e.getMessage(), e);
				}

			}

			public void storeAppData(ExecResults res, BaseAction strutsAction) {
				// nothing to store...
			}
		};
		// Exec Action end
		return act_onRefresh_1;
	}

	protected Map<String, UISecConstraint> getPageVisibilityUIConstraints() {
		Map<String, UISecConstraint> allConstraints = new HashMap<String, UISecConstraint>();

		// constraint fittizio per mnuView
		UISecConstraint mnuView_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneAuditing", "mnuView",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] mnuView_constraints = new UISecConstraint[]{mnuView_defaultVisib_ctr};
		UISecConstraint mnuView_ctr = new ComplexUISecConstraint(
				mnuView_constraints);
		allConstraints.put("mnuView", mnuView_ctr);

		// constraint fittizio per cercaDataInizio
		UISecConstraint cercaDataInizio_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneAuditing", "cercaDataInizio",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] cercaDataInizio_constraints = new UISecConstraint[]{cercaDataInizio_defaultVisib_ctr};
		UISecConstraint cercaDataInizio_ctr = new ComplexUISecConstraint(
				cercaDataInizio_constraints);
		allConstraints.put("cercaDataInizio", cercaDataInizio_ctr);

		// constraint fittizio per cercaDataFine
		UISecConstraint cercaDataFine_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneAuditing", "cercaDataFine",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] cercaDataFine_constraints = new UISecConstraint[]{cercaDataFine_defaultVisib_ctr};
		UISecConstraint cercaDataFine_ctr = new ComplexUISecConstraint(
				cercaDataFine_constraints);
		allConstraints.put("cercaDataFine", cercaDataFine_ctr);

		// constraint fittizio per idTransazione
		UISecConstraint idTransazione_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneAuditing", "idTransazione",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] idTransazione_constraints = new UISecConstraint[]{idTransazione_defaultVisib_ctr};
		UISecConstraint idTransazione_ctr = new ComplexUISecConstraint(
				idTransazione_constraints);
		allConstraints.put("idTransazione", idTransazione_ctr);

		// constraint fittizio per cbAppToAdd
		UISecConstraint cbAppToAdd_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneAuditing", "cbAppToAdd",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] cbAppToAdd_constraints = new UISecConstraint[]{cbAppToAdd_defaultVisib_ctr};
		UISecConstraint cbAppToAdd_ctr = new ComplexUISecConstraint(
				cbAppToAdd_constraints);
		allConstraints.put("cbAppToAdd", cbAppToAdd_ctr);

		// constraint fittizio per btnAddAppFiltro
		UISecConstraint btnAddAppFiltro_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneAuditing", "btnAddAppFiltro",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btnAddAppFiltro_constraints = new UISecConstraint[]{btnAddAppFiltro_defaultVisib_ctr};
		UISecConstraint btnAddAppFiltro_ctr = new ComplexUISecConstraint(
				btnAddAppFiltro_constraints);
		allConstraints.put("btnAddAppFiltro", btnAddAppFiltro_ctr);

		// constraint fittizio per cbAppFiltered
		UISecConstraint cbAppFiltered_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneAuditing", "cbAppFiltered",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] cbAppFiltered_constraints = new UISecConstraint[]{cbAppFiltered_defaultVisib_ctr};
		UISecConstraint cbAppFiltered_ctr = new ComplexUISecConstraint(
				cbAppFiltered_constraints);
		allConstraints.put("cbAppFiltered", cbAppFiltered_ctr);

		// constraint fittizio per btnEliminAppFiltro
		UISecConstraint btnEliminAppFiltro_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneAuditing", "btnEliminAppFiltro",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btnEliminAppFiltro_constraints = new UISecConstraint[]{btnEliminAppFiltro_defaultVisib_ctr};
		UISecConstraint btnEliminAppFiltro_ctr = new ComplexUISecConstraint(
				btnEliminAppFiltro_constraints);
		allConstraints.put("btnEliminAppFiltro", btnEliminAppFiltro_ctr);

		// constraint fittizio per cbAzioneToAdd
		UISecConstraint cbAzioneToAdd_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneAuditing", "cbAzioneToAdd",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] cbAzioneToAdd_constraints = new UISecConstraint[]{cbAzioneToAdd_defaultVisib_ctr};
		UISecConstraint cbAzioneToAdd_ctr = new ComplexUISecConstraint(
				cbAzioneToAdd_constraints);
		allConstraints.put("cbAzioneToAdd", cbAzioneToAdd_ctr);

		// constraint fittizio per btnAddAzioneFiltro
		UISecConstraint btnAddAzioneFiltro_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneAuditing", "btnAddAzioneFiltro",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btnAddAzioneFiltro_constraints = new UISecConstraint[]{btnAddAzioneFiltro_defaultVisib_ctr};
		UISecConstraint btnAddAzioneFiltro_ctr = new ComplexUISecConstraint(
				btnAddAzioneFiltro_constraints);
		allConstraints.put("btnAddAzioneFiltro", btnAddAzioneFiltro_ctr);

		// constraint fittizio per cbAzioneFiltered
		UISecConstraint cbAzioneFiltered_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneAuditing", "cbAzioneFiltered",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] cbAzioneFiltered_constraints = new UISecConstraint[]{cbAzioneFiltered_defaultVisib_ctr};
		UISecConstraint cbAzioneFiltered_ctr = new ComplexUISecConstraint(
				cbAzioneFiltered_constraints);
		allConstraints.put("cbAzioneFiltered", cbAzioneFiltered_ctr);

		// constraint fittizio per btnEliminAzioneFiltro
		UISecConstraint btnEliminAzioneFiltro_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneAuditing", "btnEliminAzioneFiltro",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btnEliminAzioneFiltro_constraints = new UISecConstraint[]{btnEliminAzioneFiltro_defaultVisib_ctr};
		UISecConstraint btnEliminAzioneFiltro_ctr = new ComplexUISecConstraint(
				btnEliminAzioneFiltro_constraints);
		allConstraints.put("btnEliminAzioneFiltro", btnEliminAzioneFiltro_ctr);

		// constraint fittizio per cbUtenteToAdd
		UISecConstraint cbUtenteToAdd_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneAuditing", "cbUtenteToAdd",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] cbUtenteToAdd_constraints = new UISecConstraint[]{cbUtenteToAdd_defaultVisib_ctr};
		UISecConstraint cbUtenteToAdd_ctr = new ComplexUISecConstraint(
				cbUtenteToAdd_constraints);
		allConstraints.put("cbUtenteToAdd", cbUtenteToAdd_ctr);

		// constraint fittizio per btnAddUtenteFiltro
		UISecConstraint btnAddUtenteFiltro_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneAuditing", "btnAddUtenteFiltro",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btnAddUtenteFiltro_constraints = new UISecConstraint[]{btnAddUtenteFiltro_defaultVisib_ctr};
		UISecConstraint btnAddUtenteFiltro_ctr = new ComplexUISecConstraint(
				btnAddUtenteFiltro_constraints);
		allConstraints.put("btnAddUtenteFiltro", btnAddUtenteFiltro_ctr);

		// constraint fittizio per cbUtenteFiltered
		UISecConstraint cbUtenteFiltered_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneAuditing", "cbUtenteFiltered",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] cbUtenteFiltered_constraints = new UISecConstraint[]{cbUtenteFiltered_defaultVisib_ctr};
		UISecConstraint cbUtenteFiltered_ctr = new ComplexUISecConstraint(
				cbUtenteFiltered_constraints);
		allConstraints.put("cbUtenteFiltered", cbUtenteFiltered_ctr);

		// constraint fittizio per btnEliminUtenteFiltro
		UISecConstraint btnEliminUtenteFiltro_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneAuditing", "btnEliminUtenteFiltro",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btnEliminUtenteFiltro_constraints = new UISecConstraint[]{btnEliminUtenteFiltro_defaultVisib_ctr};
		UISecConstraint btnEliminUtenteFiltro_ctr = new ComplexUISecConstraint(
				btnEliminUtenteFiltro_constraints);
		allConstraints.put("btnEliminUtenteFiltro", btnEliminUtenteFiltro_ctr);

		// constraint fittizio per btnCerca
		UISecConstraint btnCerca_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneAuditing", "btnCerca",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btnCerca_constraints = new UISecConstraint[]{btnCerca_defaultVisib_ctr};
		UISecConstraint btnCerca_ctr = new ComplexUISecConstraint(
				btnCerca_constraints);
		allConstraints.put("btnCerca", btnCerca_ctr);

		// constraint fittizio per tAuditItems
		UISecConstraint tAuditItems_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneAuditing", "tAuditItems",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tAuditItems_constraints = new UISecConstraint[]{tAuditItems_defaultVisib_ctr};
		UISecConstraint tAuditItems_ctr = new ComplexUISecConstraint(
				tAuditItems_constraints);
		allConstraints.put("tAuditItems", tAuditItems_ctr);
		return allConstraints;
	}

	protected Map<String, UISecConstraint> getPageONOFFUIConstraints() {
		Map<String, UISecConstraint> allConstraints = new HashMap<String, UISecConstraint>();

		// constraint fittizio per mnuView
		UISecConstraint mnuView_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneAuditing", "mnuView",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] mnuView_constraints = new UISecConstraint[]{mnuView_defaultOnoff_ctr};
		UISecConstraint mnuView_ctr = new ComplexUISecConstraint(
				mnuView_constraints);
		allConstraints.put("mnuView", mnuView_ctr);

		// constraint fittizio per cercaDataInizio
		UISecConstraint cercaDataInizio_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneAuditing", "cercaDataInizio",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] cercaDataInizio_constraints = new UISecConstraint[]{cercaDataInizio_defaultOnoff_ctr};
		UISecConstraint cercaDataInizio_ctr = new ComplexUISecConstraint(
				cercaDataInizio_constraints);
		allConstraints.put("cercaDataInizio", cercaDataInizio_ctr);

		// constraint fittizio per cercaDataFine
		UISecConstraint cercaDataFine_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneAuditing", "cercaDataFine",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] cercaDataFine_constraints = new UISecConstraint[]{cercaDataFine_defaultOnoff_ctr};
		UISecConstraint cercaDataFine_ctr = new ComplexUISecConstraint(
				cercaDataFine_constraints);
		allConstraints.put("cercaDataFine", cercaDataFine_ctr);

		// constraint fittizio per idTransazione
		UISecConstraint idTransazione_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneAuditing", "idTransazione",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] idTransazione_constraints = new UISecConstraint[]{idTransazione_defaultOnoff_ctr};
		UISecConstraint idTransazione_ctr = new ComplexUISecConstraint(
				idTransazione_constraints);
		allConstraints.put("idTransazione", idTransazione_ctr);

		// constraint fittizio per cbAppToAdd
		UISecConstraint cbAppToAdd_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneAuditing", "cbAppToAdd",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] cbAppToAdd_constraints = new UISecConstraint[]{cbAppToAdd_defaultOnoff_ctr};
		UISecConstraint cbAppToAdd_ctr = new ComplexUISecConstraint(
				cbAppToAdd_constraints);
		allConstraints.put("cbAppToAdd", cbAppToAdd_ctr);

		// constraint fittizio per btnAddAppFiltro
		UISecConstraint btnAddAppFiltro_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneAuditing", "btnAddAppFiltro",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btnAddAppFiltro_constraints = new UISecConstraint[]{btnAddAppFiltro_defaultOnoff_ctr};
		UISecConstraint btnAddAppFiltro_ctr = new ComplexUISecConstraint(
				btnAddAppFiltro_constraints);
		allConstraints.put("btnAddAppFiltro", btnAddAppFiltro_ctr);

		// constraint fittizio per cbAppFiltered
		UISecConstraint cbAppFiltered_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneAuditing", "cbAppFiltered",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] cbAppFiltered_constraints = new UISecConstraint[]{cbAppFiltered_defaultOnoff_ctr};
		UISecConstraint cbAppFiltered_ctr = new ComplexUISecConstraint(
				cbAppFiltered_constraints);
		allConstraints.put("cbAppFiltered", cbAppFiltered_ctr);

		// constraint fittizio per btnEliminAppFiltro
		UISecConstraint btnEliminAppFiltro_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneAuditing", "btnEliminAppFiltro",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btnEliminAppFiltro_constraints = new UISecConstraint[]{btnEliminAppFiltro_defaultOnoff_ctr};
		UISecConstraint btnEliminAppFiltro_ctr = new ComplexUISecConstraint(
				btnEliminAppFiltro_constraints);
		allConstraints.put("btnEliminAppFiltro", btnEliminAppFiltro_ctr);

		// constraint fittizio per cbAzioneToAdd
		UISecConstraint cbAzioneToAdd_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneAuditing", "cbAzioneToAdd",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] cbAzioneToAdd_constraints = new UISecConstraint[]{cbAzioneToAdd_defaultOnoff_ctr};
		UISecConstraint cbAzioneToAdd_ctr = new ComplexUISecConstraint(
				cbAzioneToAdd_constraints);
		allConstraints.put("cbAzioneToAdd", cbAzioneToAdd_ctr);

		// constraint fittizio per btnAddAzioneFiltro
		UISecConstraint btnAddAzioneFiltro_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneAuditing", "btnAddAzioneFiltro",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btnAddAzioneFiltro_constraints = new UISecConstraint[]{btnAddAzioneFiltro_defaultOnoff_ctr};
		UISecConstraint btnAddAzioneFiltro_ctr = new ComplexUISecConstraint(
				btnAddAzioneFiltro_constraints);
		allConstraints.put("btnAddAzioneFiltro", btnAddAzioneFiltro_ctr);

		// constraint fittizio per cbAzioneFiltered
		UISecConstraint cbAzioneFiltered_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneAuditing", "cbAzioneFiltered",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] cbAzioneFiltered_constraints = new UISecConstraint[]{cbAzioneFiltered_defaultOnoff_ctr};
		UISecConstraint cbAzioneFiltered_ctr = new ComplexUISecConstraint(
				cbAzioneFiltered_constraints);
		allConstraints.put("cbAzioneFiltered", cbAzioneFiltered_ctr);

		// constraint fittizio per btnEliminAzioneFiltro
		UISecConstraint btnEliminAzioneFiltro_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneAuditing", "btnEliminAzioneFiltro",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btnEliminAzioneFiltro_constraints = new UISecConstraint[]{btnEliminAzioneFiltro_defaultOnoff_ctr};
		UISecConstraint btnEliminAzioneFiltro_ctr = new ComplexUISecConstraint(
				btnEliminAzioneFiltro_constraints);
		allConstraints.put("btnEliminAzioneFiltro", btnEliminAzioneFiltro_ctr);

		// constraint fittizio per cbUtenteToAdd
		UISecConstraint cbUtenteToAdd_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneAuditing", "cbUtenteToAdd",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] cbUtenteToAdd_constraints = new UISecConstraint[]{cbUtenteToAdd_defaultOnoff_ctr};
		UISecConstraint cbUtenteToAdd_ctr = new ComplexUISecConstraint(
				cbUtenteToAdd_constraints);
		allConstraints.put("cbUtenteToAdd", cbUtenteToAdd_ctr);

		// constraint fittizio per btnAddUtenteFiltro
		UISecConstraint btnAddUtenteFiltro_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneAuditing", "btnAddUtenteFiltro",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btnAddUtenteFiltro_constraints = new UISecConstraint[]{btnAddUtenteFiltro_defaultOnoff_ctr};
		UISecConstraint btnAddUtenteFiltro_ctr = new ComplexUISecConstraint(
				btnAddUtenteFiltro_constraints);
		allConstraints.put("btnAddUtenteFiltro", btnAddUtenteFiltro_ctr);

		// constraint fittizio per cbUtenteFiltered
		UISecConstraint cbUtenteFiltered_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneAuditing", "cbUtenteFiltered",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] cbUtenteFiltered_constraints = new UISecConstraint[]{cbUtenteFiltered_defaultOnoff_ctr};
		UISecConstraint cbUtenteFiltered_ctr = new ComplexUISecConstraint(
				cbUtenteFiltered_constraints);
		allConstraints.put("cbUtenteFiltered", cbUtenteFiltered_ctr);

		// constraint fittizio per btnEliminUtenteFiltro
		UISecConstraint btnEliminUtenteFiltro_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneAuditing", "btnEliminUtenteFiltro",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btnEliminUtenteFiltro_constraints = new UISecConstraint[]{btnEliminUtenteFiltro_defaultOnoff_ctr};
		UISecConstraint btnEliminUtenteFiltro_ctr = new ComplexUISecConstraint(
				btnEliminUtenteFiltro_constraints);
		allConstraints.put("btnEliminUtenteFiltro", btnEliminUtenteFiltro_ctr);

		// constraint fittizio per btnCerca
		UISecConstraint btnCerca_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneAuditing", "btnCerca",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btnCerca_constraints = new UISecConstraint[]{btnCerca_defaultOnoff_ctr};
		UISecConstraint btnCerca_ctr = new ComplexUISecConstraint(
				btnCerca_constraints);
		allConstraints.put("btnCerca", btnCerca_ctr);

		// constraint fittizio per tAuditItems
		UISecConstraint tAuditItems_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneAuditing", "tAuditItems",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tAuditItems_constraints = new UISecConstraint[]{tAuditItems_defaultOnoff_ctr};
		UISecConstraint tAuditItems_ctr = new ComplexUISecConstraint(
				tAuditItems_constraints);
		allConstraints.put("tAuditItems", tAuditItems_ctr);
		return allConstraints;
	}

	/**  */
	void dumpModel(boolean pre) {
		log.debug("[CpGestioneAuditingAction::dumpmodel] START");

		log.debug("[CpGestioneAuditingAction::dumpmodel] #### DUMP del model della action "
				+ this.getClass()
				+ (pre ? " prima dell'azione" : " dopo l'azione"));
		log.debug("[CpGestioneAuditingAction::dumpmodel] [a] campi pubblici del model");
		try {
			java.beans.BeanInfo bi = java.beans.Introspector.getBeanInfo(this
					.getClass());
			java.beans.PropertyDescriptor[] props = bi.getPropertyDescriptors();
			for (int i = 0; i < props.length; i++) {
				java.beans.PropertyDescriptor pd = props[i];
				java.lang.reflect.Method m = pd.getReadMethod();
				if (m != null) {
					Object pval = m.invoke(this, new Object[]{});
					log.debug("[CpGestioneAuditingAction::dumpmodel] "
							+ pd.getName() + ":" + pval);
				}
			}
		} catch (IllegalAccessException e) {
			log.error("[CpGestioneAuditingAction::dumpmodel] Errore nel dump"
					+ e + ", ignoro");
		} catch (InvocationTargetException e) {
			log.error("[CpGestioneAuditingAction::dumpmodel] Errore nel dump"
					+ e + ", ignoro");
		} catch (IntrospectionException e) {
			log.error("[CpGestioneAuditingAction::dumpmodel] Errore nel dump"
					+ e + ", ignoro");
		}
		log.debug("[CpGestioneAuditingAction::dumpmodel] [b] stato dei widget");
		Object cpWidgetStatus = session.get("cpGestioneAuditing");
		log.debug("[CpGestioneAuditingAction::dumpmodel] " + cpWidgetStatus);
		log.debug("[CpGestioneAuditingAction::dumpmodel] [c] sessione");
		log.debug("[CpGestioneAuditingAction::dumpmodel] " + session);
	}

	static final String PENDING_CLEAR_COMMAND_ATTRIBUTE = "_pending_clear_command_";
	/**
	 *	Metodo per la rimozione dalla sessione degli application data a scope
	 *  SAME_PAGE. 
	 */
	public void clearPageScopedAppData(String targetContentPanelName) {
		// nothing to clear...
	}

	/**
	 * inizializza la struttura dei command da eseguire per ciascun event handler 
	 * di ciascun widget
	 */
	private ICommand initCommand(String sourceWidget, String eventType) {
		HashMap<String, HashMap<String, ICommand>> cmdsByWidget = new HashMap<String, HashMap<String, ICommand>>();

		// contiene i comandi del widget btnAddAppFiltro per ogni Ev.H.
		HashMap<String, ICommand> cmds4btnAddAppFiltroByEvh = new HashMap<String, ICommand>();

		cmds4btnAddAppFiltroByEvh.put("CLICKED",
				initCommandBtnAddAppFiltro_CLICKED());
		cmdsByWidget.put("btnAddAppFiltro", cmds4btnAddAppFiltroByEvh);
		// contiene i comandi del widget btnEliminAppFiltro per ogni Ev.H.
		HashMap<String, ICommand> cmds4btnEliminAppFiltroByEvh = new HashMap<String, ICommand>();

		cmds4btnEliminAppFiltroByEvh.put("CLICKED",
				initCommandBtnEliminAppFiltro_CLICKED());
		cmdsByWidget.put("btnEliminAppFiltro", cmds4btnEliminAppFiltroByEvh);
		// contiene i comandi del widget btnAddAzioneFiltro per ogni Ev.H.
		HashMap<String, ICommand> cmds4btnAddAzioneFiltroByEvh = new HashMap<String, ICommand>();

		cmds4btnAddAzioneFiltroByEvh.put("CLICKED",
				initCommandBtnAddAzioneFiltro_CLICKED());
		cmdsByWidget.put("btnAddAzioneFiltro", cmds4btnAddAzioneFiltroByEvh);
		// contiene i comandi del widget btnEliminAzioneFiltro per ogni Ev.H.
		HashMap<String, ICommand> cmds4btnEliminAzioneFiltroByEvh = new HashMap<String, ICommand>();

		cmds4btnEliminAzioneFiltroByEvh.put("CLICKED",
				initCommandBtnEliminAzioneFiltro_CLICKED());
		cmdsByWidget.put("btnEliminAzioneFiltro",
				cmds4btnEliminAzioneFiltroByEvh);
		// contiene i comandi del widget btnAddUtenteFiltro per ogni Ev.H.
		HashMap<String, ICommand> cmds4btnAddUtenteFiltroByEvh = new HashMap<String, ICommand>();

		cmds4btnAddUtenteFiltroByEvh.put("CLICKED",
				initCommandBtnAddUtenteFiltro_CLICKED());
		cmdsByWidget.put("btnAddUtenteFiltro", cmds4btnAddUtenteFiltroByEvh);
		// contiene i comandi del widget btnEliminUtenteFiltro per ogni Ev.H.
		HashMap<String, ICommand> cmds4btnEliminUtenteFiltroByEvh = new HashMap<String, ICommand>();

		cmds4btnEliminUtenteFiltroByEvh.put("CLICKED",
				initCommandBtnEliminUtenteFiltro_CLICKED());
		cmdsByWidget.put("btnEliminUtenteFiltro",
				cmds4btnEliminUtenteFiltroByEvh);
		// contiene i comandi del widget btnCerca per ogni Ev.H.
		HashMap<String, ICommand> cmds4btnCercaByEvh = new HashMap<String, ICommand>();

		cmds4btnCercaByEvh.put("CLICKED", initCommandBtnCerca_CLICKED());
		cmdsByWidget.put("btnCerca", cmds4btnCercaByEvh);

		ICommand ris = cmdsByWidget.get(sourceWidget).get(eventType);
		if (ris != null)
			return ris;
		else
			return new ICommand() { // NOP..
				public String doCommand(BaseAction strutsAction)
						throws CommandExecutionException {
					return null;
				}
			};
	}

	private ICommand initCommandBtnAddAppFiltro_CLICKED() {
		// ExecCommand begin
		String[] resultNames4addApplicazioneFiltro = new String[]{"Ok", "Ko"};

		ICommand[] actionsForResults4addApplicazioneFiltro = new ICommand[2];
		// SequenceCommand begin
		/// NOP Command begin
		NOPCommand act_actions_clicked_btnAddAppFiltro_resokActionstep_0 = new NOPCommand();
		/// NOP Command end

		ICommand[] act_actions_clicked_btnAddAppFiltro_resokAction_0_steps = new ICommand[]{act_actions_clicked_btnAddAppFiltro_resokActionstep_0};
		SequenceCommand act_actions_clicked_btnAddAppFiltro_resokAction_0 = new SequenceCommand(
				act_actions_clicked_btnAddAppFiltro_resokAction_0_steps);
		// SequenceCommand end
		actionsForResults4addApplicazioneFiltro[0] = act_actions_clicked_btnAddAppFiltro_resokAction_0;
		// SequenceCommand begin
		/// NOP Command begin
		NOPCommand act_actions_clicked_btnAddAppFiltro_reskoActionstep_0 = new NOPCommand();
		/// NOP Command end

		ICommand[] act_actions_clicked_btnAddAppFiltro_reskoAction_1_steps = new ICommand[]{act_actions_clicked_btnAddAppFiltro_reskoActionstep_0};
		SequenceCommand act_actions_clicked_btnAddAppFiltro_reskoAction_1 = new SequenceCommand(
				act_actions_clicked_btnAddAppFiltro_reskoAction_1_steps);
		// SequenceCommand end
		actionsForResults4addApplicazioneFiltro[1] = act_actions_clicked_btnAddAppFiltro_reskoAction_1;

		ExecCommand act_actions_clicked_btnAddAppFiltro_1 = new ExecCommand(
				resultNames4addApplicazioneFiltro,
				actionsForResults4addApplicazioneFiltro) {
			public ExecResults doLogic(Object theModel)
					throws CommandExecutionException {
				it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch(
						it.csi.mdp.mdpboweb.util.Constants.APPLICATION_CODE);
				ExecResults result = null;
				try {
					watcher.start();
					result = getSpringBackEnd()
							.addApplicazioneFiltro(
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneAuditingModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpGestioneAuditingAction",
							"readOne()", "chiamata verso BackEnd",
							"addApplicazioneFiltro");
					return result;
				} catch (BEException e) {
					throw new CommandExecutionException(
							"errore non gestito nell'esecuzione del metodo [addApplicazioneFiltro]:"
									+ e.getMessage(), e);
				}

			}

			public void storeAppData(ExecResults res, BaseAction strutsAction) {
				// nothing to store...
			}
		};
		// Exec Action end
		return act_actions_clicked_btnAddAppFiltro_1;
	}

	private ICommand initCommandBtnEliminAppFiltro_CLICKED() {
		// ExecCommand begin
		String[] resultNames4eliminaApplicazioneFiltro = new String[]{"Ok",
				"Ko"};

		ICommand[] actionsForResults4eliminaApplicazioneFiltro = new ICommand[2];
		// SequenceCommand begin
		/// NOP Command begin
		NOPCommand act_actions_clicked_btnEliminAppFiltro_resokActionstep_0 = new NOPCommand();
		/// NOP Command end

		ICommand[] act_actions_clicked_btnEliminAppFiltro_resokAction_0_steps = new ICommand[]{act_actions_clicked_btnEliminAppFiltro_resokActionstep_0};
		SequenceCommand act_actions_clicked_btnEliminAppFiltro_resokAction_0 = new SequenceCommand(
				act_actions_clicked_btnEliminAppFiltro_resokAction_0_steps);
		// SequenceCommand end
		actionsForResults4eliminaApplicazioneFiltro[0] = act_actions_clicked_btnEliminAppFiltro_resokAction_0;
		// SequenceCommand begin
		/// NOP Command begin
		NOPCommand act_actions_clicked_btnEliminAppFiltro_reskoActionstep_0 = new NOPCommand();
		/// NOP Command end

		ICommand[] act_actions_clicked_btnEliminAppFiltro_reskoAction_1_steps = new ICommand[]{act_actions_clicked_btnEliminAppFiltro_reskoActionstep_0};
		SequenceCommand act_actions_clicked_btnEliminAppFiltro_reskoAction_1 = new SequenceCommand(
				act_actions_clicked_btnEliminAppFiltro_reskoAction_1_steps);
		// SequenceCommand end
		actionsForResults4eliminaApplicazioneFiltro[1] = act_actions_clicked_btnEliminAppFiltro_reskoAction_1;

		ExecCommand act_actions_clicked_btnEliminAppFiltro_1 = new ExecCommand(
				resultNames4eliminaApplicazioneFiltro,
				actionsForResults4eliminaApplicazioneFiltro) {
			public ExecResults doLogic(Object theModel)
					throws CommandExecutionException {
				it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch(
						it.csi.mdp.mdpboweb.util.Constants.APPLICATION_CODE);
				ExecResults result = null;
				try {
					watcher.start();
					result = getSpringBackEnd()
							.eliminaApplicazioneFiltro(
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneAuditingModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpGestioneAuditingAction",
							"readOne()", "chiamata verso BackEnd",
							"eliminaApplicazioneFiltro");
					return result;
				} catch (BEException e) {
					throw new CommandExecutionException(
							"errore non gestito nell'esecuzione del metodo [eliminaApplicazioneFiltro]:"
									+ e.getMessage(), e);
				}

			}

			public void storeAppData(ExecResults res, BaseAction strutsAction) {
				// nothing to store...
			}
		};
		// Exec Action end
		return act_actions_clicked_btnEliminAppFiltro_1;
	}

	private ICommand initCommandBtnAddAzioneFiltro_CLICKED() {
		// ExecCommand begin
		String[] resultNames4addAzioneFiltro = new String[]{"Ok", "Ko"};

		ICommand[] actionsForResults4addAzioneFiltro = new ICommand[2];
		// SequenceCommand begin
		/// NOP Command begin
		NOPCommand act_actions_clicked_btnAddAzioneFiltro_resokActionstep_0 = new NOPCommand();
		/// NOP Command end

		ICommand[] act_actions_clicked_btnAddAzioneFiltro_resokAction_0_steps = new ICommand[]{act_actions_clicked_btnAddAzioneFiltro_resokActionstep_0};
		SequenceCommand act_actions_clicked_btnAddAzioneFiltro_resokAction_0 = new SequenceCommand(
				act_actions_clicked_btnAddAzioneFiltro_resokAction_0_steps);
		// SequenceCommand end
		actionsForResults4addAzioneFiltro[0] = act_actions_clicked_btnAddAzioneFiltro_resokAction_0;
		// SequenceCommand begin
		/// NOP Command begin
		NOPCommand act_actions_clicked_btnAddAzioneFiltro_reskoActionstep_0 = new NOPCommand();
		/// NOP Command end

		ICommand[] act_actions_clicked_btnAddAzioneFiltro_reskoAction_1_steps = new ICommand[]{act_actions_clicked_btnAddAzioneFiltro_reskoActionstep_0};
		SequenceCommand act_actions_clicked_btnAddAzioneFiltro_reskoAction_1 = new SequenceCommand(
				act_actions_clicked_btnAddAzioneFiltro_reskoAction_1_steps);
		// SequenceCommand end
		actionsForResults4addAzioneFiltro[1] = act_actions_clicked_btnAddAzioneFiltro_reskoAction_1;

		ExecCommand act_actions_clicked_btnAddAzioneFiltro_1 = new ExecCommand(
				resultNames4addAzioneFiltro, actionsForResults4addAzioneFiltro) {
			public ExecResults doLogic(Object theModel)
					throws CommandExecutionException {
				it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch(
						it.csi.mdp.mdpboweb.util.Constants.APPLICATION_CODE);
				ExecResults result = null;
				try {
					watcher.start();
					result = getSpringBackEnd()
							.addAzioneFiltro(
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneAuditingModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpGestioneAuditingAction",
							"readOne()", "chiamata verso BackEnd",
							"addAzioneFiltro");
					return result;
				} catch (BEException e) {
					throw new CommandExecutionException(
							"errore non gestito nell'esecuzione del metodo [addAzioneFiltro]:"
									+ e.getMessage(), e);
				}

			}

			public void storeAppData(ExecResults res, BaseAction strutsAction) {
				// nothing to store...
			}
		};
		// Exec Action end
		return act_actions_clicked_btnAddAzioneFiltro_1;
	}

	private ICommand initCommandBtnEliminAzioneFiltro_CLICKED() {
		// ExecCommand begin
		String[] resultNames4eliminaAzioneFiltro = new String[]{"Ok", "Ko"};

		ICommand[] actionsForResults4eliminaAzioneFiltro = new ICommand[2];
		/// NOP Command begin
		NOPCommand act_actions_clicked_btnEliminAzioneFiltro_resokAction_0 = new NOPCommand();
		/// NOP Command end
		actionsForResults4eliminaAzioneFiltro[0] = act_actions_clicked_btnEliminAzioneFiltro_resokAction_0;
		/// NOP Command begin
		NOPCommand act_actions_clicked_btnEliminAzioneFiltro_reskoAction_1 = new NOPCommand();
		/// NOP Command end
		actionsForResults4eliminaAzioneFiltro[1] = act_actions_clicked_btnEliminAzioneFiltro_reskoAction_1;

		ExecCommand act_actions_clicked_btnEliminAzioneFiltro_1 = new ExecCommand(
				resultNames4eliminaAzioneFiltro,
				actionsForResults4eliminaAzioneFiltro) {
			public ExecResults doLogic(Object theModel)
					throws CommandExecutionException {
				it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch(
						it.csi.mdp.mdpboweb.util.Constants.APPLICATION_CODE);
				ExecResults result = null;
				try {
					watcher.start();
					result = getSpringBackEnd()
							.eliminaAzioneFiltro(
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneAuditingModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpGestioneAuditingAction",
							"readOne()", "chiamata verso BackEnd",
							"eliminaAzioneFiltro");
					return result;
				} catch (BEException e) {
					throw new CommandExecutionException(
							"errore non gestito nell'esecuzione del metodo [eliminaAzioneFiltro]:"
									+ e.getMessage(), e);
				}

			}

			public void storeAppData(ExecResults res, BaseAction strutsAction) {
				// nothing to store...
			}
		};
		// Exec Action end
		return act_actions_clicked_btnEliminAzioneFiltro_1;
	}

	private ICommand initCommandBtnAddUtenteFiltro_CLICKED() {
		// ExecCommand begin
		String[] resultNames4addUtenteFiltro = new String[]{"Ok", "Ko"};

		ICommand[] actionsForResults4addUtenteFiltro = new ICommand[2];
		// SequenceCommand begin
		/// NOP Command begin
		NOPCommand act_actions_clicked_btnAddUtenteFiltro_resokActionstep_0 = new NOPCommand();
		/// NOP Command end

		ICommand[] act_actions_clicked_btnAddUtenteFiltro_resokAction_0_steps = new ICommand[]{act_actions_clicked_btnAddUtenteFiltro_resokActionstep_0};
		SequenceCommand act_actions_clicked_btnAddUtenteFiltro_resokAction_0 = new SequenceCommand(
				act_actions_clicked_btnAddUtenteFiltro_resokAction_0_steps);
		// SequenceCommand end
		actionsForResults4addUtenteFiltro[0] = act_actions_clicked_btnAddUtenteFiltro_resokAction_0;
		// SequenceCommand begin
		/// NOP Command begin
		NOPCommand act_actions_clicked_btnAddUtenteFiltro_reskoActionstep_0 = new NOPCommand();
		/// NOP Command end

		ICommand[] act_actions_clicked_btnAddUtenteFiltro_reskoAction_1_steps = new ICommand[]{act_actions_clicked_btnAddUtenteFiltro_reskoActionstep_0};
		SequenceCommand act_actions_clicked_btnAddUtenteFiltro_reskoAction_1 = new SequenceCommand(
				act_actions_clicked_btnAddUtenteFiltro_reskoAction_1_steps);
		// SequenceCommand end
		actionsForResults4addUtenteFiltro[1] = act_actions_clicked_btnAddUtenteFiltro_reskoAction_1;

		ExecCommand act_actions_clicked_btnAddUtenteFiltro_1 = new ExecCommand(
				resultNames4addUtenteFiltro, actionsForResults4addUtenteFiltro) {
			public ExecResults doLogic(Object theModel)
					throws CommandExecutionException {
				it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch(
						it.csi.mdp.mdpboweb.util.Constants.APPLICATION_CODE);
				ExecResults result = null;
				try {
					watcher.start();
					result = getSpringBackEnd()
							.addUtenteFiltro(
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneAuditingModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpGestioneAuditingAction",
							"readOne()", "chiamata verso BackEnd",
							"addUtenteFiltro");
					return result;
				} catch (BEException e) {
					throw new CommandExecutionException(
							"errore non gestito nell'esecuzione del metodo [addUtenteFiltro]:"
									+ e.getMessage(), e);
				}

			}

			public void storeAppData(ExecResults res, BaseAction strutsAction) {
				// nothing to store...
			}
		};
		// Exec Action end
		return act_actions_clicked_btnAddUtenteFiltro_1;
	}

	private ICommand initCommandBtnEliminUtenteFiltro_CLICKED() {
		// ExecCommand begin
		String[] resultNames4eliminaUtenteFiltro = new String[]{"Ok", "Ko"};

		ICommand[] actionsForResults4eliminaUtenteFiltro = new ICommand[2];
		/// NOP Command begin
		NOPCommand act_actions_clicked_btnEliminUtenteFiltro_resokAction_0 = new NOPCommand();
		/// NOP Command end
		actionsForResults4eliminaUtenteFiltro[0] = act_actions_clicked_btnEliminUtenteFiltro_resokAction_0;
		/// NOP Command begin
		NOPCommand act_actions_clicked_btnEliminUtenteFiltro_reskoAction_1 = new NOPCommand();
		/// NOP Command end
		actionsForResults4eliminaUtenteFiltro[1] = act_actions_clicked_btnEliminUtenteFiltro_reskoAction_1;

		ExecCommand act_actions_clicked_btnEliminUtenteFiltro_1 = new ExecCommand(
				resultNames4eliminaUtenteFiltro,
				actionsForResults4eliminaUtenteFiltro) {
			public ExecResults doLogic(Object theModel)
					throws CommandExecutionException {
				it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch(
						it.csi.mdp.mdpboweb.util.Constants.APPLICATION_CODE);
				ExecResults result = null;
				try {
					watcher.start();
					result = getSpringBackEnd()
							.eliminaUtenteFiltro(
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneAuditingModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpGestioneAuditingAction",
							"readOne()", "chiamata verso BackEnd",
							"eliminaUtenteFiltro");
					return result;
				} catch (BEException e) {
					throw new CommandExecutionException(
							"errore non gestito nell'esecuzione del metodo [eliminaUtenteFiltro]:"
									+ e.getMessage(), e);
				}

			}

			public void storeAppData(ExecResults res, BaseAction strutsAction) {
				// nothing to store...
			}
		};
		// Exec Action end
		return act_actions_clicked_btnEliminUtenteFiltro_1;
	}

	private ICommand initCommandBtnCerca_CLICKED() {
		// ExecCommand begin
		String[] resultNames4cerca = new String[]{"Ok_No_Results",
				"Ok_Some_Results"};

		ICommand[] actionsForResults4cerca = new ICommand[2];
		// SequenceCommand begin
		/// NOP Command begin
		NOPCommand act_actions_clicked_btnCerca_resok_no_resultsActionstep_0 = new NOPCommand();
		/// NOP Command end

		ICommand[] act_actions_clicked_btnCerca_resok_no_resultsAction_0_steps = new ICommand[]{act_actions_clicked_btnCerca_resok_no_resultsActionstep_0};
		SequenceCommand act_actions_clicked_btnCerca_resok_no_resultsAction_0 = new SequenceCommand(
				act_actions_clicked_btnCerca_resok_no_resultsAction_0_steps);
		// SequenceCommand end
		actionsForResults4cerca[0] = act_actions_clicked_btnCerca_resok_no_resultsAction_0;
		// SequenceCommand begin
		/// NOP Command begin
		NOPCommand act_actions_clicked_btnCerca_resok_some_resultsActionstep_0 = new NOPCommand();
		/// NOP Command end
		// Screen State Command begin
		//prepara i nomi dei componenti target da abilitare/disabilitare
		String[] act_actions_clicked_btnCerca_resok_some_resultsActionstep_1_on = new String[]{};

		String[] act_actions_clicked_btnCerca_resok_some_resultsActionstep_1_off = new String[]{};

		String[] act_actions_clicked_btnCerca_resok_some_resultsActionstep_1_show = new String[]{};

		String[] act_actions_clicked_btnCerca_resok_some_resultsActionstep_1_hide = new String[]{};

		ScreenStateCommand act_actions_clicked_btnCerca_resok_some_resultsActionstep_1 = new ScreenStateCommand(
				"cpGestioneAuditing",
				"??",
				act_actions_clicked_btnCerca_resok_some_resultsActionstep_1_on,
				act_actions_clicked_btnCerca_resok_some_resultsActionstep_1_off,
				act_actions_clicked_btnCerca_resok_some_resultsActionstep_1_show,
				act_actions_clicked_btnCerca_resok_some_resultsActionstep_1_hide);
		//Screen State Command end

		ICommand[] act_actions_clicked_btnCerca_resok_some_resultsAction_1_steps = new ICommand[]{
				act_actions_clicked_btnCerca_resok_some_resultsActionstep_0,
				act_actions_clicked_btnCerca_resok_some_resultsActionstep_1};
		SequenceCommand act_actions_clicked_btnCerca_resok_some_resultsAction_1 = new SequenceCommand(
				act_actions_clicked_btnCerca_resok_some_resultsAction_1_steps);
		// SequenceCommand end
		actionsForResults4cerca[1] = act_actions_clicked_btnCerca_resok_some_resultsAction_1;

		ExecCommand act_actions_clicked_btnCerca_1 = new ExecCommand(
				resultNames4cerca, actionsForResults4cerca) {
			public ExecResults doLogic(Object theModel)
					throws CommandExecutionException {
				it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch(
						it.csi.mdp.mdpboweb.util.Constants.APPLICATION_CODE);
				ExecResults result = null;
				try {
					watcher.start();
					result = getSpringBackEnd()
							.cerca((it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneAuditingModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpGestioneAuditingAction",
							"readOne()", "chiamata verso BackEnd", "cerca");
					return result;
				} catch (BEException e) {
					throw new CommandExecutionException(
							"errore non gestito nell'esecuzione del metodo [cerca]:"
									+ e.getMessage(), e);
				}

			}

			public void storeAppData(ExecResults res, BaseAction strutsAction) {
				// nothing to store...
			}
		};
		// Exec Action end
		return act_actions_clicked_btnCerca_1;
	}

	@SkipValidation
	public String handleChangeTab() {
		if (this.hasActionErrors() || this.hasFieldErrors() || this.hasErrors())
			return INPUT;
		else {
			session.put(selectedTabKey, selectedTabValue);
			return SUCCESS;
		}
	}

}
