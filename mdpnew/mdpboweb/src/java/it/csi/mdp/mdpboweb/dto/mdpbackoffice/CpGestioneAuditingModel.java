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
 * logica di business associata alla Schermata [cpGestioneAuditing]
 */
public class CpGestioneAuditingModel extends BaseSessionAwareDTO {

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

	////////////////////////////////////////////////////////////////////
	/// campi per widget semplici
	////////////////////////////////////////////////////////////////////

	// Table tAuditItems

	private java.util.ArrayList<java.lang.String> _widg_tAuditItems;

	public void setWidg_tAuditItems(java.util.ArrayList<java.lang.String> value) {
		_widg_tAuditItems = value;
	}

	public java.util.ArrayList<java.lang.String> getWidg_tAuditItems() {
		return _widg_tAuditItems;
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
