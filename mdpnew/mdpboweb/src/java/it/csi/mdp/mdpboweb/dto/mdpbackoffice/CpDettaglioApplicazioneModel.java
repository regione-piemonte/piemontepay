/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.dto.mdpbackoffice;

import java.io.File;
import java.util.*;
import it.csi.mdp.mdpboweb.dto.*;
import it.csi.mdp.mdpboweb.dto.common.*;
import it.csi.mdp.mdpboweb.dto.nsbackoffice.*;

import it.csi.mdp.mdpboweb.presentation.uiutils.*;
import flexjson.JSON;
import com.opensymphony.xwork2.conversion.annotations.*;
/**
 * Questo DTO incapsula tutto il contenuto informativo necessario all'esecuzione della
 * logica di business associata alla Schermata [cpDettaglioApplicazione]
 */
public class CpDettaglioApplicazioneModel extends BaseSessionAwareDTO {

	// il serial version uid e' fisso in quanto la classe in oggetto e' serializzabile
	// solo per la clusterizzazione della sessione web e non viene scambiata con altre
	// componenti.
	private static final long serialVersionUID = 1L;

	////////////////////////////////////////////////////////////////////
	/// application data
	////////////////////////////////////////////////////////////////////

	public void setAppDataapplicazione(
			it.csi.mdp.mdpboweb.dto.nsbackoffice.Applicazione value) {
		getSession().put("appDataapplicazione", value);
	}

	public it.csi.mdp.mdpboweb.dto.nsbackoffice.Applicazione getAppDataapplicazione() {
		return (it.csi.mdp.mdpboweb.dto.nsbackoffice.Applicazione) (getSession()
				.get("appDataapplicazione"));
	}

	public void setAppDataisPostBack(java.lang.String value) {
		getSession().put("appDataisPostBack", value);
	}

	public java.lang.String getAppDataisPostBack() {
		return (java.lang.String) (getSession().get("appDataisPostBack"));
	}

	public void setAppDatacurrentUser(
			it.csi.mdp.mdpboweb.dto.common.UserInfo value) {
		getSession().put("appDatacurrentUser", value);
	}

	public it.csi.mdp.mdpboweb.dto.common.UserInfo getAppDatacurrentUser() {
		return (it.csi.mdp.mdpboweb.dto.common.UserInfo) (getSession()
				.get("appDatacurrentUser"));
	}

	public void setAppDataextraAttributes(
			java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.ExtraAttribute> value) {
		getSession().put("appDataextraAttributes", value);
	}

	public java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.ExtraAttribute> getAppDataextraAttributes() {
		return (java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.ExtraAttribute>) (getSession()
				.get("appDataextraAttributes"));
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

	public void setAppDatapaymentModes(
			java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.PaymentMode> value) {
		getSession().put("appDatapaymentModes", value);
	}

	public java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.PaymentMode> getAppDatapaymentModes() {
		return (java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.PaymentMode>) (getSession()
				.get("appDatapaymentModes"));
	}

	public void setAppDatagateways(
			java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Gateway> value) {
		getSession().put("appDatagateways", value);
	}

	public java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Gateway> getAppDatagateways() {
		return (java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Gateway>) (getSession()
				.get("appDatagateways"));
	}

	public void setAppDataselettoreItemAssociazioneGW_MP(java.lang.String value) {
		getSession().put("appDataselettoreItemAssociazioneGW_MP", value);
	}

	public java.lang.String getAppDataselettoreItemAssociazioneGW_MP() {
		return (java.lang.String) (getSession()
				.get("appDataselettoreItemAssociazioneGW_MP"));
	}

	public void setAppDatanuovoExtraAttribute(
			it.csi.mdp.mdpboweb.dto.nsbackoffice.ExtraAttribute value) {
		getSession().put("appDatanuovoExtraAttribute", value);
	}

	public it.csi.mdp.mdpboweb.dto.nsbackoffice.ExtraAttribute getAppDatanuovoExtraAttribute() {
		return (it.csi.mdp.mdpboweb.dto.nsbackoffice.ExtraAttribute) (getSession()
				.get("appDatanuovoExtraAttribute"));
	}

	public void setAppDataselettoreChiaveAttr(java.lang.String value) {
		getSession().put("appDataselettoreChiaveAttr", value);
	}

	public java.lang.String getAppDataselettoreChiaveAttr() {
		return (java.lang.String) (getSession()
				.get("appDataselettoreChiaveAttr"));
	}

	public void setAppDatagateway(
			it.csi.mdp.mdpboweb.dto.nsbackoffice.Gateway value) {
		getSession().put("appDatagateway", value);
	}

	public it.csi.mdp.mdpboweb.dto.nsbackoffice.Gateway getAppDatagateway() {
		return (it.csi.mdp.mdpboweb.dto.nsbackoffice.Gateway) (getSession()
				.get("appDatagateway"));
	}

	public void setAppDatapaymentMode(
			it.csi.mdp.mdpboweb.dto.nsbackoffice.PaymentMode value) {
		getSession().put("appDatapaymentMode", value);
	}

	public it.csi.mdp.mdpboweb.dto.nsbackoffice.PaymentMode getAppDatapaymentMode() {
		return (it.csi.mdp.mdpboweb.dto.nsbackoffice.PaymentMode) (getSession()
				.get("appDatapaymentMode"));
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

	public void setAppDatadivisa(
			it.csi.mdp.mdpboweb.dto.nsbackoffice.Divisa value) {
		getSession().put("appDatadivisa", value);
	}

	public it.csi.mdp.mdpboweb.dto.nsbackoffice.Divisa getAppDatadivisa() {
		return (it.csi.mdp.mdpboweb.dto.nsbackoffice.Divisa) (getSession()
				.get("appDatadivisa"));
	}

	public void setAppDatadivise(
			java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Divisa> value) {
		getSession().put("appDatadivise", value);
	}

	public java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Divisa> getAppDatadivise() {
		return (java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Divisa>) (getSession()
				.get("appDatadivise"));
	}

	public void setAppDataselettoreIdTipologiaCommissione(java.lang.String value) {
		getSession().put("appDataselettoreIdTipologiaCommissione", value);
	}

	public java.lang.String getAppDataselettoreIdTipologiaCommissione() {
		return (java.lang.String) (getSession()
				.get("appDataselettoreIdTipologiaCommissione"));
	}

	public void setAppDatatipologiaCommissioni(
			java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.TipologiaCommissione> value) {
		getSession().put("appDatatipologiaCommissioni", value);
	}

	public java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.TipologiaCommissione> getAppDatatipologiaCommissioni() {
		return (java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.TipologiaCommissione>) (getSession()
				.get("appDatatipologiaCommissioni"));
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

	private it.csi.mdp.mdpboweb.dto.nsbackoffice.IbanEnteApplication _appDataibanEnteApplication = null;

	public void setAppDataibanEnteApplication(
			it.csi.mdp.mdpboweb.dto.nsbackoffice.IbanEnteApplication value) {
		_appDataibanEnteApplication = value;
	}

	public it.csi.mdp.mdpboweb.dto.nsbackoffice.IbanEnteApplication getAppDataibanEnteApplication() {
		return _appDataibanEnteApplication;
	}

	public void setAppDatalistaIbanEnteApplication(
			java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.IbanEnteApplication> value) {
		getSession().put("appDatalistaIbanEnteApplication", value);
	}

	public java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.IbanEnteApplication> getAppDatalistaIbanEnteApplication() {
		return (java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.IbanEnteApplication>) (getSession()
				.get("appDatalistaIbanEnteApplication"));
	}

	public void setAppDataselettoreIbanEnteApplication(java.lang.String value) {
		getSession().put("appDataselettoreIbanEnteApplication", value);
	}

	public java.lang.String getAppDataselettoreIbanEnteApplication() {
		return (java.lang.String) (getSession()
				.get("appDataselettoreIbanEnteApplication"));
	}

	public void setAppDatalistaTipoversamento(
			java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.TipoVersamento> value) {
		getSession().put("appDatalistaTipoversamento", value);
	}

	public java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.TipoVersamento> getAppDatalistaTipoversamento() {
		return (java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.TipoVersamento>) (getSession()
				.get("appDatalistaTipoversamento"));
	}

	public void setAppDatalistaInformativePsp(
			java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.InformativePsp> value) {
		getSession().put("appDatalistaInformativePsp", value);
	}

	public java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.InformativePsp> getAppDatalistaInformativePsp() {
		return (java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.InformativePsp>) (getSession()
				.get("appDatalistaInformativePsp"));
	}

	public void setAppDatalistaAttivazione(
			java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.KeyValue> value) {
		getSession().put("appDatalistaAttivazione", value);
	}

	public java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.KeyValue> getAppDatalistaAttivazione() {
		return (java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.KeyValue>) (getSession()
				.get("appDatalistaAttivazione"));
	}

	public void setAppDatakeyValue(
			it.csi.mdp.mdpboweb.dto.nsbackoffice.KeyValue value) {
		getSession().put("appDatakeyValue", value);
	}

	public it.csi.mdp.mdpboweb.dto.nsbackoffice.KeyValue getAppDatakeyValue() {
		return (it.csi.mdp.mdpboweb.dto.nsbackoffice.KeyValue) (getSession()
				.get("appDatakeyValue"));
	}

	////////////////////////////////////////////////////////////////////
	/// campi per widget semplici
	////////////////////////////////////////////////////////////////////

	// FileUpload widg_txFile
	private File _widg_txFile; // The actual file
	private String _widg_txFileContentType; // The content type of the file 
	private String _widg_txFileFileName; // The uploaded file name and path 

	public void setWidg_txFile(File value) {
		_widg_txFile = value;
	}
	@JSON(include = false)
	public File getWidg_txFile() {
		return _widg_txFile;
	}

	public void setWidg_txFileContentType(String value) {
		_widg_txFileContentType = value;
	}
	@JSON(include = false)
	public String getWidg_txFileContentType() {
		return _widg_txFileContentType;
	}

	public void setWidg_txFileFileName(String value) {
		_widg_txFileFileName = value;
	}
	@JSON(include = false)
	public String getWidg_txFileFileName() {
		return _widg_txFileFileName;
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
