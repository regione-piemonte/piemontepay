/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.presentation.mdpboweb.action.mdpbackoffice;

import java.io.File;
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
 * CpDettaglioApplicazioneAction Action Class.
 *
 * @author GuiGen
 */
@Validation()
@Conversion()
public class CpDettaglioApplicazioneAction extends BaseAction
		implements
			Preparable {

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
		return it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioApplicazioneModel.class;
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
	 * Gestione dell'evento CLICKED sul widget [btnIndietro]
	 */
	@MethodProtection(level = "REJECT_SAME")
	public String handleBtnIndietro_CLICKED() throws CommandExecutionException {
		ICommand action = initCommand("btnIndietro", "CLICKED");
		if (log.isDebugEnabled()) {
			log.debug("[CpDettaglioApplicazioneAction::handleBtnIndietro_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpDettaglioApplicazioneAction::handleBtnIndietro_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpDettaglioApplicazioneAction::handleBtnIndietro_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpDettaglioApplicazioneAction::handleBtnIndietro_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpDettaglioApplicazioneAction::handleBtnIndietro_CLICKED] returning default result [SUCCESS]");
			}
			return SUCCESS;

		}
	}

	/**
	 * Gestione dell'evento CLICKED sul widget [btnSalvaApplicazione]
	 */
	@MethodProtection(level = "REJECT_SAME")
	public String handleBtnSalvaApplicazione_CLICKED()
			throws CommandExecutionException {
		ICommand action = initCommand("btnSalvaApplicazione", "CLICKED");
		if (log.isDebugEnabled()) {
			log.debug("[CpDettaglioApplicazioneAction::handleBtnSalvaApplicazione_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpDettaglioApplicazioneAction::handleBtnSalvaApplicazione_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpDettaglioApplicazioneAction::handleBtnSalvaApplicazione_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpDettaglioApplicazioneAction::handleBtnSalvaApplicazione_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpDettaglioApplicazioneAction::handleBtnSalvaApplicazione_CLICKED] returning default result [SUCCESS]");
			}
			return SUCCESS;

		}
	}

	/**
	 * Gestione dell'evento CLICKED sul widget [btnUpdAssGW_MP]
	 */
	@MethodProtection(level = "REJECT_SAME")
	public String handleBtnUpdAssGW_MP_CLICKED()
			throws CommandExecutionException {
		ICommand action = initCommand("btnUpdAssGW_MP", "CLICKED");
		if (log.isDebugEnabled()) {
			log.debug("[CpDettaglioApplicazioneAction::handleBtnUpdAssGW_MP_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpDettaglioApplicazioneAction::handleBtnUpdAssGW_MP_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpDettaglioApplicazioneAction::handleBtnUpdAssGW_MP_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpDettaglioApplicazioneAction::handleBtnUpdAssGW_MP_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpDettaglioApplicazioneAction::handleBtnUpdAssGW_MP_CLICKED] returning default result [SUCCESS]");
			}
			return SUCCESS;

		}
	}

	/**
	 * Gestione dell'evento VALUE_CHANGED sul widget [cbGateways]
	 */
	@MethodProtection(level = "REJECT_SAME")
	public String handleCbGateways_VALUE_CHANGED()
			throws CommandExecutionException {
		ICommand action = initCommand("cbGateways", "VALUE_CHANGED");
		if (log.isDebugEnabled()) {
			log.debug("[CpDettaglioApplicazioneAction::handleCbGateways_VALUE_CHANGED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpDettaglioApplicazioneAction::handleCbGateways_VALUE_CHANGED] dump model after");
				dumpModel(false);
				log.debug("[CpDettaglioApplicazioneAction::handleCbGateways_VALUE_CHANGED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpDettaglioApplicazioneAction::handleCbGateways_VALUE_CHANGED] dump model after");
				dumpModel(false);
				log.debug("[CpDettaglioApplicazioneAction::handleCbGateways_VALUE_CHANGED] returning default result [SUCCESS]");
			}
			return SUCCESS;

		}
	}

	/**
	 * Gestione dell'evento CLICKED sul widget [btnCaricaAllegato]
	 */
	@MethodProtection(level = "REJECT_SAME")
	public String handleBtnCaricaAllegato_CLICKED()
			throws CommandExecutionException {
		ICommand action = initCommand("btnCaricaAllegato", "CLICKED");
		if (log.isDebugEnabled()) {
			log.debug("[CpDettaglioApplicazioneAction::handleBtnCaricaAllegato_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpDettaglioApplicazioneAction::handleBtnCaricaAllegato_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpDettaglioApplicazioneAction::handleBtnCaricaAllegato_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpDettaglioApplicazioneAction::handleBtnCaricaAllegato_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpDettaglioApplicazioneAction::handleBtnCaricaAllegato_CLICKED] returning default result [SUCCESS]");
			}
			return SUCCESS;

		}
	}

	/**
	 * Gestione dell'evento CLICKED sul widget [btnCaricaAttributo]
	 */
	@MethodProtection(level = "REJECT_SAME")
	public String handleBtnCaricaAttributo_CLICKED()
			throws CommandExecutionException {
		ICommand action = initCommand("btnCaricaAttributo", "CLICKED");
		if (log.isDebugEnabled()) {
			log.debug("[CpDettaglioApplicazioneAction::handleBtnCaricaAttributo_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpDettaglioApplicazioneAction::handleBtnCaricaAttributo_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpDettaglioApplicazioneAction::handleBtnCaricaAttributo_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpDettaglioApplicazioneAction::handleBtnCaricaAttributo_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpDettaglioApplicazioneAction::handleBtnCaricaAttributo_CLICKED] returning default result [SUCCESS]");
			}
			return SUCCESS;

		}
	}

	/**
	 * Gestione dell'evento CLICKED sul widget [btnCompletaiAttributo]
	 */
	@MethodProtection(level = "REJECT_SAME")
	public String handleBtnCompletaiAttributo_CLICKED()
			throws CommandExecutionException {
		ICommand action = initCommand("btnCompletaiAttributo", "CLICKED");
		if (log.isDebugEnabled()) {
			log.debug("[CpDettaglioApplicazioneAction::handleBtnCompletaiAttributo_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpDettaglioApplicazioneAction::handleBtnCompletaiAttributo_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpDettaglioApplicazioneAction::handleBtnCompletaiAttributo_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpDettaglioApplicazioneAction::handleBtnCompletaiAttributo_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpDettaglioApplicazioneAction::handleBtnCompletaiAttributo_CLICKED] returning default result [SUCCESS]");
			}
			return SUCCESS;

		}
	}

	/**
	 * Gestione dell'evento CLICKED sul widget [btnSalvaAssociazioneGW_MP]
	 */
	@MethodProtection(level = "REJECT_SAME")
	public String handleBtnSalvaAssociazioneGW_MP_CLICKED()
			throws CommandExecutionException {
		ICommand action = initCommand("btnSalvaAssociazioneGW_MP", "CLICKED");
		if (log.isDebugEnabled()) {
			log.debug("[CpDettaglioApplicazioneAction::handleBtnSalvaAssociazioneGW_MP_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpDettaglioApplicazioneAction::handleBtnSalvaAssociazioneGW_MP_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpDettaglioApplicazioneAction::handleBtnSalvaAssociazioneGW_MP_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpDettaglioApplicazioneAction::handleBtnSalvaAssociazioneGW_MP_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpDettaglioApplicazioneAction::handleBtnSalvaAssociazioneGW_MP_CLICKED] returning default result [SUCCESS]");
			}
			return SUCCESS;

		}
	}

	/**
	 * Gestione dell'evento CLICKED sul widget [ctAssocia]
	 */
	@MethodProtection(level = "REJECT_SAME")
	public String handleCtAssocia_CLICKED() throws CommandExecutionException {
		ICommand action = initCommand("ctAssocia", "CLICKED");
		if (log.isDebugEnabled()) {
			log.debug("[CpDettaglioApplicazioneAction::handleCtAssocia_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpDettaglioApplicazioneAction::handleCtAssocia_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpDettaglioApplicazioneAction::handleCtAssocia_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpDettaglioApplicazioneAction::handleCtAssocia_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpDettaglioApplicazioneAction::handleCtAssocia_CLICKED] returning default result [SUCCESS]");
			}
			return SUCCESS;

		}
	}

	/**
	 * Gestione dell'evento CLICKED sul widget [btdisattiva]
	 */
	@MethodProtection(level = "REJECT_SAME")
	public String handleBtdisattiva_CLICKED() throws CommandExecutionException {
		ICommand action = initCommand("btdisattiva", "CLICKED");
		if (log.isDebugEnabled()) {
			log.debug("[CpDettaglioApplicazioneAction::handleBtdisattiva_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpDettaglioApplicazioneAction::handleBtdisattiva_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpDettaglioApplicazioneAction::handleBtdisattiva_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpDettaglioApplicazioneAction::handleBtdisattiva_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpDettaglioApplicazioneAction::handleBtdisattiva_CLICKED] returning default result [SUCCESS]");
			}
			return SUCCESS;

		}
	}

	/**
	 * Gestione dell'evento CLICKED sul widget [btmodifica]
	 */
	@MethodProtection(level = "REJECT_SAME")
	public String handleBtmodifica_CLICKED() throws CommandExecutionException {
		ICommand action = initCommand("btmodifica", "CLICKED");
		if (log.isDebugEnabled()) {
			log.debug("[CpDettaglioApplicazioneAction::handleBtmodifica_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpDettaglioApplicazioneAction::handleBtmodifica_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpDettaglioApplicazioneAction::handleBtmodifica_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpDettaglioApplicazioneAction::handleBtmodifica_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpDettaglioApplicazioneAction::handleBtmodifica_CLICKED] returning default result [SUCCESS]");
			}
			return SUCCESS;

		}
	}

	/**
	 * Gestione dell'evento CLICKED sul widget [btSalva]
	 */
	@MethodProtection(level = "REJECT_SAME")
	public String handleBtSalva_CLICKED() throws CommandExecutionException {
		ICommand action = initCommand("btSalva", "CLICKED");
		if (log.isDebugEnabled()) {
			log.debug("[CpDettaglioApplicazioneAction::handleBtSalva_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpDettaglioApplicazioneAction::handleBtSalva_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpDettaglioApplicazioneAction::handleBtSalva_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpDettaglioApplicazioneAction::handleBtSalva_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpDettaglioApplicazioneAction::handleBtSalva_CLICKED] returning default result [SUCCESS]");
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
	 * al data-binding relativo al dataset DATASET del widget tbListaAssociazioni.
	 * @return sempre il result "provideData"
	 */
	@SkipValidation
	@FatClientOnly
	public String provideTbListaAssociazioni_DATASET()
			throws CommandExecutionException {

		// reperisce il dato da serializzare
		com.opensymphony.xwork2.ActionContext ctx = com.opensymphony.xwork2.ActionContext
				.getContext();
		Object dataToProvide = ctx.getValueStack().findValue(
				"appDataassociazioniGW_MP");

		if (isTableClearStatus("cpDettaglioApplicazione_tbListaAssociazioni")) {
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
	 * Prepara l'input stream referenziato dal result "provideData" con i valori relativi 
	 * al data-binding relativo al dataset DATASET del widget cbGateways.
	 * @return sempre il result "provideData"
	 */
	@SkipValidation
	@FatClientOnly
	public String provideCbGateways_DATASET() throws CommandExecutionException {

		// reperisce il dato da serializzare
		com.opensymphony.xwork2.ActionContext ctx = com.opensymphony.xwork2.ActionContext
				.getContext();
		Object dataToProvide = ctx.getValueStack().findValue("appDatagateways");

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
	 * al data-binding relativo al dataset DATASET del widget cbPaymentModes.
	 * @return sempre il result "provideData"
	 */
	@SkipValidation
	@FatClientOnly
	public String provideCbPaymentModes_DATASET()
			throws CommandExecutionException {

		// reperisce il dato da serializzare
		com.opensymphony.xwork2.ActionContext ctx = com.opensymphony.xwork2.ActionContext
				.getContext();
		Object dataToProvide = ctx.getValueStack().findValue(
				"appDatapaymentModes");

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
	 * al data-binding relativo al dataset DATASET del widget cbtipologiaComm.
	 * @return sempre il result "provideData"
	 */
	@SkipValidation
	@FatClientOnly
	public String provideCbtipologiaComm_DATASET()
			throws CommandExecutionException {

		// reperisce il dato da serializzare
		com.opensymphony.xwork2.ActionContext ctx = com.opensymphony.xwork2.ActionContext
				.getContext();
		Object dataToProvide = ctx.getValueStack().findValue(
				"appDatatipologiaCommissioni");

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
	 * al data-binding relativo al dataset DATASET del widget tbListaAttributi.
	 * @return sempre il result "provideData"
	 */
	@SkipValidation
	@FatClientOnly
	public String provideTbListaAttributi_DATASET()
			throws CommandExecutionException {

		// reperisce il dato da serializzare
		com.opensymphony.xwork2.ActionContext ctx = com.opensymphony.xwork2.ActionContext
				.getContext();
		Object dataToProvide = ctx.getValueStack().findValue(
				"appDataextraAttributes");

		if (isTableClearStatus("cpDettaglioApplicazione_tbListaAttributi")) {
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
	 * Prepara l'input stream referenziato dal result "provideData" con i valori relativi 
	 * al data-binding relativo al dataset DATASET del widget cbEnteId.
	 * @return sempre il result "provideData"
	 */
	@SkipValidation
	@FatClientOnly
	public String provideCbEnteId_DATASET() throws CommandExecutionException {

		// reperisce il dato da serializzare
		com.opensymphony.xwork2.ActionContext ctx = com.opensymphony.xwork2.ActionContext
				.getContext();
		Object dataToProvide = ctx.getValueStack()
				.findValue("appDatalistaEnti");

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
	 * al data-binding relativo al dataset DATASET del widget tbIbanEnteApplicatio.
	 * @return sempre il result "provideData"
	 */
	@SkipValidation
	@FatClientOnly
	public String provideTbIbanEnteApplicatio_DATASET()
			throws CommandExecutionException {

		// reperisce il dato da serializzare
		com.opensymphony.xwork2.ActionContext ctx = com.opensymphony.xwork2.ActionContext
				.getContext();
		Object dataToProvide = ctx.getValueStack().findValue(
				"appDatalistaIbanEnteApplication");

		if (isTableClearStatus("cpDettaglioApplicazione_tbIbanEnteApplicatio")) {
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
	 * Prepara l'input stream referenziato dal result "provideData" con i valori relativi 
	 * al data-binding relativo al dataset DATASET del widget cbListaTipoVersamento.
	 * @return sempre il result "provideData"
	 */
	@SkipValidation
	@FatClientOnly
	public String provideCbListaTipoVersamento_DATASET()
			throws CommandExecutionException {

		// reperisce il dato da serializzare
		com.opensymphony.xwork2.ActionContext ctx = com.opensymphony.xwork2.ActionContext
				.getContext();
		Object dataToProvide = ctx.getValueStack().findValue(
				"appDatalistaTipoversamento");

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
	 * al data-binding relativo al dataset DATASET del widget cbIdentificativoPsp.
	 * @return sempre il result "provideData"
	 */
	@SkipValidation
	@FatClientOnly
	public String provideCbIdentificativoPsp_DATASET()
			throws CommandExecutionException {

		// reperisce il dato da serializzare
		com.opensymphony.xwork2.ActionContext ctx = com.opensymphony.xwork2.ActionContext
				.getContext();
		Object dataToProvide = ctx.getValueStack().findValue(
				"appDatalistaInformativePsp");

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
	 * al data-binding relativo al dataset DATASET del widget cbAttivazione.
	 * @return sempre il result "provideData"
	 */
	@SkipValidation
	@FatClientOnly
	public String provideCbAttivazione_DATASET()
			throws CommandExecutionException {

		// reperisce il dato da serializzare
		com.opensymphony.xwork2.ActionContext ctx = com.opensymphony.xwork2.ActionContext
				.getContext();
		Object dataToProvide = ctx.getValueStack().findValue(
				"appDatalistaAttivazione");

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
		/*PROTECTED REGION ID(R-518895098) ENABLED START*/
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
				log.error("[CpDettaglioApplicazioneAction::isWidgetDisabled] errore durante verifica->disable");
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
				log.error("[CpDettaglioApplicazioneAction::isWidgetVisible] errore durante verifica->hide");
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
		String[] resultNames4refreshPanel = new String[]{"Ok"};

		ICommand[] actionsForResults4refreshPanel = new ICommand[1];
		// SequenceCommand begin
		// Screen State Command begin
		//prepara i nomi dei componenti target da abilitare/disabilitare
		String[] act_onRefresh_resokActionstep_0_on = new String[]{"mnuView",
				"tfIdApplicazioneLab", "tfNomeApplicazione", "tfReferente",
				"tfCliente", "tfProgetto", "tfEmailEsercente", "taNote",
				"txFile", "btnSalvaApplicazione", "tbListaAssociazioni",
				"btnUpdAssGW_MP", "cbGateways", "cbPaymentModes",
				"chkAbilitato", "tfIdEsercente", "tfPasswordEsercente",
				"tfMacAvvio", "cbtipologiaComm", "impMin", "impMax",
				"tfApplicationurlresponseko", "tfApplicationurlresponseok",
				"tfApplicationurlback", "chkMailacquirenteok",
				"chkMailacquirenteko", "chkMailesercenteok",
				"chkMailesercenteko", "chkMailsistemaok", "chkMailsistemako",
				"btnSalvaAssociazioneGW_MP", "tbListaAttributi", "txtChiave",
				"txtNome", "tfValore", "txtDescrizione", "btnCaricaAttributo",
				"btnCompletaiAttributo", "tfSoglia", "tfNumeroVerde",
				"btnCaricaAllegato", "txCodIstat", "txEnteId", "txPartitaIva",
				"txDescrizioneEnte", "cbEnteId", "ctAssocia",
				"tbIbanEnteApplicatio", "btdisattiva", "btmodifica",
				"txIdApplication", "txIdEnte", "cbListaTipoVersamento",
				"cbIdentificativoPsp", "cbAttivazione", "txIban",
				"clDataInizioValidita", "cLdataFineValidita", "btSalva", "hId",
				"hIbanOld"};

		String[] act_onRefresh_resokActionstep_0_off = new String[]{
				"btnIndietro", "txIdApplicazione"};

		String[] act_onRefresh_resokActionstep_0_show = new String[]{"mnuView",
				"tfIdApplicazioneLab", "tfNomeApplicazione", "tfReferente",
				"tfCliente", "tfProgetto", "tfEmailEsercente", "taNote",
				"txFile", "btnSalvaApplicazione", "tbListaAssociazioni",
				"btnUpdAssGW_MP", "cbGateways", "cbPaymentModes",
				"chkAbilitato", "tfIdEsercente", "tfPasswordEsercente",
				"tfMacAvvio", "cbtipologiaComm", "impMin", "impMax",
				"tfApplicationurlresponseko", "tfApplicationurlresponseok",
				"tfApplicationurlback", "chkMailacquirenteok",
				"chkMailacquirenteko", "chkMailesercenteok",
				"chkMailesercenteko", "chkMailsistemaok", "chkMailsistemako",
				"btnSalvaAssociazioneGW_MP", "tbListaAttributi", "txtChiave",
				"txtNome", "tfValore", "txtDescrizione", "btnCaricaAttributo",
				"btnCompletaiAttributo", "tfSoglia", "tfNumeroVerde",
				"btnCaricaAllegato", "txCodIstat", "txEnteId", "txPartitaIva",
				"txDescrizioneEnte", "cbEnteId", "ctAssocia",
				"tbIbanEnteApplicatio", "btdisattiva", "btmodifica",
				"txIdApplication", "txIdEnte", "cbListaTipoVersamento",
				"cbIdentificativoPsp", "cbAttivazione", "txIban",
				"clDataInizioValidita", "cLdataFineValidita", "btSalva", "hId",
				"hIbanOld"};

		String[] act_onRefresh_resokActionstep_0_hide = new String[]{
				"btnIndietro", "txIdApplicazione"};

		ScreenStateCommand act_onRefresh_resokActionstep_0 = new ScreenStateCommand(
				"cpDettaglioApplicazione", "IMPORTOFISSOSCAGLIONI",
				act_onRefresh_resokActionstep_0_on,
				act_onRefresh_resokActionstep_0_off,
				act_onRefresh_resokActionstep_0_show,
				act_onRefresh_resokActionstep_0_hide);
		//Screen State Command end

		ICommand[] act_onRefresh_resokAction_0_steps = new ICommand[]{act_onRefresh_resokActionstep_0};
		SequenceCommand act_onRefresh_resokAction_0 = new SequenceCommand(
				act_onRefresh_resokAction_0_steps);
		// SequenceCommand end
		actionsForResults4refreshPanel[0] = act_onRefresh_resokAction_0;

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
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioApplicazioneModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpDettaglioApplicazioneAction",
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

		new DummyUISecConstraint("cpDettaglioApplicazione", "mnuView",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] mnuView_constraints = new UISecConstraint[]{mnuView_defaultVisib_ctr};
		UISecConstraint mnuView_ctr = new ComplexUISecConstraint(
				mnuView_constraints);
		allConstraints.put("mnuView", mnuView_ctr);

		// constraint fittizio per tfIdApplicazioneLab
		UISecConstraint tfIdApplicazioneLab_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione",
				"tfIdApplicazioneLab",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tfIdApplicazioneLab_constraints = new UISecConstraint[]{tfIdApplicazioneLab_defaultVisib_ctr};
		UISecConstraint tfIdApplicazioneLab_ctr = new ComplexUISecConstraint(
				tfIdApplicazioneLab_constraints);
		allConstraints.put("tfIdApplicazioneLab", tfIdApplicazioneLab_ctr);

		// constraint fittizio per tfNomeApplicazione
		UISecConstraint tfNomeApplicazione_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione",
				"tfNomeApplicazione",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tfNomeApplicazione_constraints = new UISecConstraint[]{tfNomeApplicazione_defaultVisib_ctr};
		UISecConstraint tfNomeApplicazione_ctr = new ComplexUISecConstraint(
				tfNomeApplicazione_constraints);
		allConstraints.put("tfNomeApplicazione", tfNomeApplicazione_ctr);

		// constraint fittizio per tfReferente
		UISecConstraint tfReferente_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione", "tfReferente",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tfReferente_constraints = new UISecConstraint[]{tfReferente_defaultVisib_ctr};
		UISecConstraint tfReferente_ctr = new ComplexUISecConstraint(
				tfReferente_constraints);
		allConstraints.put("tfReferente", tfReferente_ctr);

		// constraint fittizio per tfCliente
		UISecConstraint tfCliente_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione", "tfCliente",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tfCliente_constraints = new UISecConstraint[]{tfCliente_defaultVisib_ctr};
		UISecConstraint tfCliente_ctr = new ComplexUISecConstraint(
				tfCliente_constraints);
		allConstraints.put("tfCliente", tfCliente_ctr);

		// constraint fittizio per tfProgetto
		UISecConstraint tfProgetto_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione", "tfProgetto",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tfProgetto_constraints = new UISecConstraint[]{tfProgetto_defaultVisib_ctr};
		UISecConstraint tfProgetto_ctr = new ComplexUISecConstraint(
				tfProgetto_constraints);
		allConstraints.put("tfProgetto", tfProgetto_ctr);

		// constraint fittizio per tfEmailEsercente
		UISecConstraint tfEmailEsercente_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione", "tfEmailEsercente",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tfEmailEsercente_constraints = new UISecConstraint[]{tfEmailEsercente_defaultVisib_ctr};
		UISecConstraint tfEmailEsercente_ctr = new ComplexUISecConstraint(
				tfEmailEsercente_constraints);
		allConstraints.put("tfEmailEsercente", tfEmailEsercente_ctr);

		// constraint fittizio per tfNumeroVerde
		UISecConstraint tfNumeroVerde_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione", "tfNumeroVerde",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tfNumeroVerde_constraints = new UISecConstraint[]{tfNumeroVerde_defaultVisib_ctr};
		UISecConstraint tfNumeroVerde_ctr = new ComplexUISecConstraint(
				tfNumeroVerde_constraints);
		allConstraints.put("tfNumeroVerde", tfNumeroVerde_ctr);

		// constraint fittizio per taNote
		UISecConstraint taNote_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione", "taNote",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] taNote_constraints = new UISecConstraint[]{taNote_defaultVisib_ctr};
		UISecConstraint taNote_ctr = new ComplexUISecConstraint(
				taNote_constraints);
		allConstraints.put("taNote", taNote_ctr);

		// constraint fittizio per btnIndietro
		UISecConstraint btnIndietro_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione", "btnIndietro",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btnIndietro_constraints = new UISecConstraint[]{btnIndietro_defaultVisib_ctr};
		UISecConstraint btnIndietro_ctr = new ComplexUISecConstraint(
				btnIndietro_constraints);
		allConstraints.put("btnIndietro", btnIndietro_ctr);

		// constraint fittizio per btnSalvaApplicazione
		UISecConstraint btnSalvaApplicazione_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione",
				"btnSalvaApplicazione",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btnSalvaApplicazione_constraints = new UISecConstraint[]{btnSalvaApplicazione_defaultVisib_ctr};
		UISecConstraint btnSalvaApplicazione_ctr = new ComplexUISecConstraint(
				btnSalvaApplicazione_constraints);
		allConstraints.put("btnSalvaApplicazione", btnSalvaApplicazione_ctr);

		// constraint fittizio per tbListaAssociazioni
		UISecConstraint tbListaAssociazioni_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione",
				"tbListaAssociazioni",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tbListaAssociazioni_constraints = new UISecConstraint[]{tbListaAssociazioni_defaultVisib_ctr};
		UISecConstraint tbListaAssociazioni_ctr = new ComplexUISecConstraint(
				tbListaAssociazioni_constraints);
		allConstraints.put("tbListaAssociazioni", tbListaAssociazioni_ctr);

		// constraint fittizio per btnUpdAssGW_MP
		UISecConstraint btnUpdAssGW_MP_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione", "btnUpdAssGW_MP",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btnUpdAssGW_MP_constraints = new UISecConstraint[]{btnUpdAssGW_MP_defaultVisib_ctr};
		UISecConstraint btnUpdAssGW_MP_ctr = new ComplexUISecConstraint(
				btnUpdAssGW_MP_constraints);
		allConstraints.put("btnUpdAssGW_MP", btnUpdAssGW_MP_ctr);

		// constraint fittizio per txIdApplicazione
		UISecConstraint txIdApplicazione_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione", "txIdApplicazione",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txIdApplicazione_constraints = new UISecConstraint[]{txIdApplicazione_defaultVisib_ctr};
		UISecConstraint txIdApplicazione_ctr = new ComplexUISecConstraint(
				txIdApplicazione_constraints);
		allConstraints.put("txIdApplicazione", txIdApplicazione_ctr);

		// constraint fittizio per cbGateways
		UISecConstraint cbGateways_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione", "cbGateways",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] cbGateways_constraints = new UISecConstraint[]{cbGateways_defaultVisib_ctr};
		UISecConstraint cbGateways_ctr = new ComplexUISecConstraint(
				cbGateways_constraints);
		allConstraints.put("cbGateways", cbGateways_ctr);

		// constraint fittizio per cbPaymentModes
		UISecConstraint cbPaymentModes_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione", "cbPaymentModes",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] cbPaymentModes_constraints = new UISecConstraint[]{cbPaymentModes_defaultVisib_ctr};
		UISecConstraint cbPaymentModes_ctr = new ComplexUISecConstraint(
				cbPaymentModes_constraints);
		allConstraints.put("cbPaymentModes", cbPaymentModes_ctr);

		// constraint fittizio per txCodIstat
		UISecConstraint txCodIstat_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione", "txCodIstat",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txCodIstat_constraints = new UISecConstraint[]{txCodIstat_defaultVisib_ctr};
		UISecConstraint txCodIstat_ctr = new ComplexUISecConstraint(
				txCodIstat_constraints);
		allConstraints.put("txCodIstat", txCodIstat_ctr);

		// constraint fittizio per chkAbilitato
		UISecConstraint chkAbilitato_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione", "chkAbilitato",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] chkAbilitato_constraints = new UISecConstraint[]{chkAbilitato_defaultVisib_ctr};
		UISecConstraint chkAbilitato_ctr = new ComplexUISecConstraint(
				chkAbilitato_constraints);
		allConstraints.put("chkAbilitato", chkAbilitato_ctr);

		// constraint fittizio per tfIdEsercente
		UISecConstraint tfIdEsercente_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione", "tfIdEsercente",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tfIdEsercente_constraints = new UISecConstraint[]{tfIdEsercente_defaultVisib_ctr};
		UISecConstraint tfIdEsercente_ctr = new ComplexUISecConstraint(
				tfIdEsercente_constraints);
		allConstraints.put("tfIdEsercente", tfIdEsercente_ctr);

		// constraint fittizio per tfPasswordEsercente
		UISecConstraint tfPasswordEsercente_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione",
				"tfPasswordEsercente",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tfPasswordEsercente_constraints = new UISecConstraint[]{tfPasswordEsercente_defaultVisib_ctr};
		UISecConstraint tfPasswordEsercente_ctr = new ComplexUISecConstraint(
				tfPasswordEsercente_constraints);
		allConstraints.put("tfPasswordEsercente", tfPasswordEsercente_ctr);

		// constraint fittizio per tfMacAvvio
		UISecConstraint tfMacAvvio_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione", "tfMacAvvio",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tfMacAvvio_constraints = new UISecConstraint[]{tfMacAvvio_defaultVisib_ctr};
		UISecConstraint tfMacAvvio_ctr = new ComplexUISecConstraint(
				tfMacAvvio_constraints);
		allConstraints.put("tfMacAvvio", tfMacAvvio_ctr);

		// constraint fittizio per cbtipologiaComm
		UISecConstraint cbtipologiaComm_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione", "cbtipologiaComm",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] cbtipologiaComm_constraints = new UISecConstraint[]{cbtipologiaComm_defaultVisib_ctr};
		UISecConstraint cbtipologiaComm_ctr = new ComplexUISecConstraint(
				cbtipologiaComm_constraints);
		allConstraints.put("cbtipologiaComm", cbtipologiaComm_ctr);

		// constraint fittizio per impMin
		UISecConstraint impMin_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione", "impMin",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] impMin_constraints = new UISecConstraint[]{impMin_defaultVisib_ctr};
		UISecConstraint impMin_ctr = new ComplexUISecConstraint(
				impMin_constraints);
		allConstraints.put("impMin", impMin_ctr);

		// constraint fittizio per impMax
		UISecConstraint impMax_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione", "impMax",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] impMax_constraints = new UISecConstraint[]{impMax_defaultVisib_ctr};
		UISecConstraint impMax_ctr = new ComplexUISecConstraint(
				impMax_constraints);
		allConstraints.put("impMax", impMax_ctr);

		// constraint fittizio per tfSoglia
		UISecConstraint tfSoglia_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione", "tfSoglia",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tfSoglia_constraints = new UISecConstraint[]{tfSoglia_defaultVisib_ctr};
		UISecConstraint tfSoglia_ctr = new ComplexUISecConstraint(
				tfSoglia_constraints);
		allConstraints.put("tfSoglia", tfSoglia_ctr);

		// constraint fittizio per tfApplicationurlresponseko
		UISecConstraint tfApplicationurlresponseko_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione",
				"tfApplicationurlresponseko",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tfApplicationurlresponseko_constraints = new UISecConstraint[]{tfApplicationurlresponseko_defaultVisib_ctr};
		UISecConstraint tfApplicationurlresponseko_ctr = new ComplexUISecConstraint(
				tfApplicationurlresponseko_constraints);
		allConstraints.put("tfApplicationurlresponseko",
				tfApplicationurlresponseko_ctr);

		// constraint fittizio per tfApplicationurlresponseok
		UISecConstraint tfApplicationurlresponseok_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione",
				"tfApplicationurlresponseok",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tfApplicationurlresponseok_constraints = new UISecConstraint[]{tfApplicationurlresponseok_defaultVisib_ctr};
		UISecConstraint tfApplicationurlresponseok_ctr = new ComplexUISecConstraint(
				tfApplicationurlresponseok_constraints);
		allConstraints.put("tfApplicationurlresponseok",
				tfApplicationurlresponseok_ctr);

		// constraint fittizio per tfApplicationurlback
		UISecConstraint tfApplicationurlback_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione",
				"tfApplicationurlback",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tfApplicationurlback_constraints = new UISecConstraint[]{tfApplicationurlback_defaultVisib_ctr};
		UISecConstraint tfApplicationurlback_ctr = new ComplexUISecConstraint(
				tfApplicationurlback_constraints);
		allConstraints.put("tfApplicationurlback", tfApplicationurlback_ctr);

		// constraint fittizio per chkMailacquirenteok
		UISecConstraint chkMailacquirenteok_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione",
				"chkMailacquirenteok",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] chkMailacquirenteok_constraints = new UISecConstraint[]{chkMailacquirenteok_defaultVisib_ctr};
		UISecConstraint chkMailacquirenteok_ctr = new ComplexUISecConstraint(
				chkMailacquirenteok_constraints);
		allConstraints.put("chkMailacquirenteok", chkMailacquirenteok_ctr);

		// constraint fittizio per chkMailacquirenteko
		UISecConstraint chkMailacquirenteko_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione",
				"chkMailacquirenteko",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] chkMailacquirenteko_constraints = new UISecConstraint[]{chkMailacquirenteko_defaultVisib_ctr};
		UISecConstraint chkMailacquirenteko_ctr = new ComplexUISecConstraint(
				chkMailacquirenteko_constraints);
		allConstraints.put("chkMailacquirenteko", chkMailacquirenteko_ctr);

		// constraint fittizio per chkMailesercenteok
		UISecConstraint chkMailesercenteok_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione",
				"chkMailesercenteok",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] chkMailesercenteok_constraints = new UISecConstraint[]{chkMailesercenteok_defaultVisib_ctr};
		UISecConstraint chkMailesercenteok_ctr = new ComplexUISecConstraint(
				chkMailesercenteok_constraints);
		allConstraints.put("chkMailesercenteok", chkMailesercenteok_ctr);

		// constraint fittizio per chkMailesercenteko
		UISecConstraint chkMailesercenteko_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione",
				"chkMailesercenteko",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] chkMailesercenteko_constraints = new UISecConstraint[]{chkMailesercenteko_defaultVisib_ctr};
		UISecConstraint chkMailesercenteko_ctr = new ComplexUISecConstraint(
				chkMailesercenteko_constraints);
		allConstraints.put("chkMailesercenteko", chkMailesercenteko_ctr);

		// constraint fittizio per chkMailsistemaok
		UISecConstraint chkMailsistemaok_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione", "chkMailsistemaok",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] chkMailsistemaok_constraints = new UISecConstraint[]{chkMailsistemaok_defaultVisib_ctr};
		UISecConstraint chkMailsistemaok_ctr = new ComplexUISecConstraint(
				chkMailsistemaok_constraints);
		allConstraints.put("chkMailsistemaok", chkMailsistemaok_ctr);

		// constraint fittizio per chkMailsistemako
		UISecConstraint chkMailsistemako_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione", "chkMailsistemako",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] chkMailsistemako_constraints = new UISecConstraint[]{chkMailsistemako_defaultVisib_ctr};
		UISecConstraint chkMailsistemako_ctr = new ComplexUISecConstraint(
				chkMailsistemako_constraints);
		allConstraints.put("chkMailsistemako", chkMailsistemako_ctr);

		// constraint fittizio per txFile
		UISecConstraint txFile_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione", "txFile",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txFile_constraints = new UISecConstraint[]{txFile_defaultVisib_ctr};
		UISecConstraint txFile_ctr = new ComplexUISecConstraint(
				txFile_constraints);
		allConstraints.put("txFile", txFile_ctr);

		// constraint fittizio per btnCaricaAllegato
		UISecConstraint btnCaricaAllegato_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione",
				"btnCaricaAllegato",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btnCaricaAllegato_constraints = new UISecConstraint[]{btnCaricaAllegato_defaultVisib_ctr};
		UISecConstraint btnCaricaAllegato_ctr = new ComplexUISecConstraint(
				btnCaricaAllegato_constraints);
		allConstraints.put("btnCaricaAllegato", btnCaricaAllegato_ctr);

		// constraint fittizio per tbListaAttributi
		UISecConstraint tbListaAttributi_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione", "tbListaAttributi",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tbListaAttributi_constraints = new UISecConstraint[]{tbListaAttributi_defaultVisib_ctr};
		UISecConstraint tbListaAttributi_ctr = new ComplexUISecConstraint(
				tbListaAttributi_constraints);
		allConstraints.put("tbListaAttributi", tbListaAttributi_ctr);

		// constraint fittizio per txtChiave
		UISecConstraint txtChiave_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione", "txtChiave",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txtChiave_constraints = new UISecConstraint[]{txtChiave_defaultVisib_ctr};
		UISecConstraint txtChiave_ctr = new ComplexUISecConstraint(
				txtChiave_constraints);
		allConstraints.put("txtChiave", txtChiave_ctr);

		// constraint fittizio per txtNome
		UISecConstraint txtNome_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione", "txtNome",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txtNome_constraints = new UISecConstraint[]{txtNome_defaultVisib_ctr};
		UISecConstraint txtNome_ctr = new ComplexUISecConstraint(
				txtNome_constraints);
		allConstraints.put("txtNome", txtNome_ctr);

		// constraint fittizio per tfValore
		UISecConstraint tfValore_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione", "tfValore",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tfValore_constraints = new UISecConstraint[]{tfValore_defaultVisib_ctr};
		UISecConstraint tfValore_ctr = new ComplexUISecConstraint(
				tfValore_constraints);
		allConstraints.put("tfValore", tfValore_ctr);

		// constraint fittizio per txtDescrizione
		UISecConstraint txtDescrizione_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione", "txtDescrizione",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txtDescrizione_constraints = new UISecConstraint[]{txtDescrizione_defaultVisib_ctr};
		UISecConstraint txtDescrizione_ctr = new ComplexUISecConstraint(
				txtDescrizione_constraints);
		allConstraints.put("txtDescrizione", txtDescrizione_ctr);

		// constraint fittizio per btnCaricaAttributo
		UISecConstraint btnCaricaAttributo_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione",
				"btnCaricaAttributo",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btnCaricaAttributo_constraints = new UISecConstraint[]{btnCaricaAttributo_defaultVisib_ctr};
		UISecConstraint btnCaricaAttributo_ctr = new ComplexUISecConstraint(
				btnCaricaAttributo_constraints);
		allConstraints.put("btnCaricaAttributo", btnCaricaAttributo_ctr);

		// constraint fittizio per btnCompletaiAttributo
		UISecConstraint btnCompletaiAttributo_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione",
				"btnCompletaiAttributo",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btnCompletaiAttributo_constraints = new UISecConstraint[]{btnCompletaiAttributo_defaultVisib_ctr};
		UISecConstraint btnCompletaiAttributo_ctr = new ComplexUISecConstraint(
				btnCompletaiAttributo_constraints);
		allConstraints.put("btnCompletaiAttributo", btnCompletaiAttributo_ctr);

		// constraint fittizio per btnSalvaAssociazioneGW_MP
		UISecConstraint btnSalvaAssociazioneGW_MP_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione",
				"btnSalvaAssociazioneGW_MP",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btnSalvaAssociazioneGW_MP_constraints = new UISecConstraint[]{btnSalvaAssociazioneGW_MP_defaultVisib_ctr};
		UISecConstraint btnSalvaAssociazioneGW_MP_ctr = new ComplexUISecConstraint(
				btnSalvaAssociazioneGW_MP_constraints);
		allConstraints.put("btnSalvaAssociazioneGW_MP",
				btnSalvaAssociazioneGW_MP_ctr);

		// constraint fittizio per txEnteId
		UISecConstraint txEnteId_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione", "txEnteId",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txEnteId_constraints = new UISecConstraint[]{txEnteId_defaultVisib_ctr};
		UISecConstraint txEnteId_ctr = new ComplexUISecConstraint(
				txEnteId_constraints);
		allConstraints.put("txEnteId", txEnteId_ctr);

		// constraint fittizio per txPartitaIva
		UISecConstraint txPartitaIva_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione", "txPartitaIva",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txPartitaIva_constraints = new UISecConstraint[]{txPartitaIva_defaultVisib_ctr};
		UISecConstraint txPartitaIva_ctr = new ComplexUISecConstraint(
				txPartitaIva_constraints);
		allConstraints.put("txPartitaIva", txPartitaIva_ctr);

		// constraint fittizio per txDescrizioneEnte
		UISecConstraint txDescrizioneEnte_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione",
				"txDescrizioneEnte",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txDescrizioneEnte_constraints = new UISecConstraint[]{txDescrizioneEnte_defaultVisib_ctr};
		UISecConstraint txDescrizioneEnte_ctr = new ComplexUISecConstraint(
				txDescrizioneEnte_constraints);
		allConstraints.put("txDescrizioneEnte", txDescrizioneEnte_ctr);

		// constraint fittizio per cbEnteId
		UISecConstraint cbEnteId_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione", "cbEnteId",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] cbEnteId_constraints = new UISecConstraint[]{cbEnteId_defaultVisib_ctr};
		UISecConstraint cbEnteId_ctr = new ComplexUISecConstraint(
				cbEnteId_constraints);
		allConstraints.put("cbEnteId", cbEnteId_ctr);

		// constraint fittizio per ctAssocia
		UISecConstraint ctAssocia_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione", "ctAssocia",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] ctAssocia_constraints = new UISecConstraint[]{ctAssocia_defaultVisib_ctr};
		UISecConstraint ctAssocia_ctr = new ComplexUISecConstraint(
				ctAssocia_constraints);
		allConstraints.put("ctAssocia", ctAssocia_ctr);

		// constraint fittizio per tbIbanEnteApplicatio
		UISecConstraint tbIbanEnteApplicatio_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione",
				"tbIbanEnteApplicatio",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tbIbanEnteApplicatio_constraints = new UISecConstraint[]{tbIbanEnteApplicatio_defaultVisib_ctr};
		UISecConstraint tbIbanEnteApplicatio_ctr = new ComplexUISecConstraint(
				tbIbanEnteApplicatio_constraints);
		allConstraints.put("tbIbanEnteApplicatio", tbIbanEnteApplicatio_ctr);

		// constraint fittizio per btdisattiva
		UISecConstraint btdisattiva_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione", "btdisattiva",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btdisattiva_constraints = new UISecConstraint[]{btdisattiva_defaultVisib_ctr};
		UISecConstraint btdisattiva_ctr = new ComplexUISecConstraint(
				btdisattiva_constraints);
		allConstraints.put("btdisattiva", btdisattiva_ctr);

		// constraint fittizio per btmodifica
		UISecConstraint btmodifica_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione", "btmodifica",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btmodifica_constraints = new UISecConstraint[]{btmodifica_defaultVisib_ctr};
		UISecConstraint btmodifica_ctr = new ComplexUISecConstraint(
				btmodifica_constraints);
		allConstraints.put("btmodifica", btmodifica_ctr);

		// constraint fittizio per txIdApplication
		UISecConstraint txIdApplication_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione", "txIdApplication",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txIdApplication_constraints = new UISecConstraint[]{txIdApplication_defaultVisib_ctr};
		UISecConstraint txIdApplication_ctr = new ComplexUISecConstraint(
				txIdApplication_constraints);
		allConstraints.put("txIdApplication", txIdApplication_ctr);

		// constraint fittizio per txIdEnte
		UISecConstraint txIdEnte_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione", "txIdEnte",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txIdEnte_constraints = new UISecConstraint[]{txIdEnte_defaultVisib_ctr};
		UISecConstraint txIdEnte_ctr = new ComplexUISecConstraint(
				txIdEnte_constraints);
		allConstraints.put("txIdEnte", txIdEnte_ctr);

		// constraint fittizio per cbListaTipoVersamento
		UISecConstraint cbListaTipoVersamento_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione",
				"cbListaTipoVersamento",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] cbListaTipoVersamento_constraints = new UISecConstraint[]{cbListaTipoVersamento_defaultVisib_ctr};
		UISecConstraint cbListaTipoVersamento_ctr = new ComplexUISecConstraint(
				cbListaTipoVersamento_constraints);
		allConstraints.put("cbListaTipoVersamento", cbListaTipoVersamento_ctr);

		// constraint fittizio per cbIdentificativoPsp
		UISecConstraint cbIdentificativoPsp_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione",
				"cbIdentificativoPsp",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] cbIdentificativoPsp_constraints = new UISecConstraint[]{cbIdentificativoPsp_defaultVisib_ctr};
		UISecConstraint cbIdentificativoPsp_ctr = new ComplexUISecConstraint(
				cbIdentificativoPsp_constraints);
		allConstraints.put("cbIdentificativoPsp", cbIdentificativoPsp_ctr);

		// constraint fittizio per cbAttivazione
		UISecConstraint cbAttivazione_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione", "cbAttivazione",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] cbAttivazione_constraints = new UISecConstraint[]{cbAttivazione_defaultVisib_ctr};
		UISecConstraint cbAttivazione_ctr = new ComplexUISecConstraint(
				cbAttivazione_constraints);
		allConstraints.put("cbAttivazione", cbAttivazione_ctr);

		// constraint fittizio per txIban
		UISecConstraint txIban_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione", "txIban",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txIban_constraints = new UISecConstraint[]{txIban_defaultVisib_ctr};
		UISecConstraint txIban_ctr = new ComplexUISecConstraint(
				txIban_constraints);
		allConstraints.put("txIban", txIban_ctr);

		// constraint fittizio per clDataInizioValidita
		UISecConstraint clDataInizioValidita_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione",
				"clDataInizioValidita",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] clDataInizioValidita_constraints = new UISecConstraint[]{clDataInizioValidita_defaultVisib_ctr};
		UISecConstraint clDataInizioValidita_ctr = new ComplexUISecConstraint(
				clDataInizioValidita_constraints);
		allConstraints.put("clDataInizioValidita", clDataInizioValidita_ctr);

		// constraint fittizio per cLdataFineValidita
		UISecConstraint cLdataFineValidita_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione",
				"cLdataFineValidita",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] cLdataFineValidita_constraints = new UISecConstraint[]{cLdataFineValidita_defaultVisib_ctr};
		UISecConstraint cLdataFineValidita_ctr = new ComplexUISecConstraint(
				cLdataFineValidita_constraints);
		allConstraints.put("cLdataFineValidita", cLdataFineValidita_ctr);

		// constraint fittizio per btSalva
		UISecConstraint btSalva_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione", "btSalva",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btSalva_constraints = new UISecConstraint[]{btSalva_defaultVisib_ctr};
		UISecConstraint btSalva_ctr = new ComplexUISecConstraint(
				btSalva_constraints);
		allConstraints.put("btSalva", btSalva_ctr);

		// constraint fittizio per hId
		UISecConstraint hId_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione", "hId",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] hId_constraints = new UISecConstraint[]{hId_defaultVisib_ctr};
		UISecConstraint hId_ctr = new ComplexUISecConstraint(hId_constraints);
		allConstraints.put("hId", hId_ctr);

		// constraint fittizio per hIbanOld
		UISecConstraint hIbanOld_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione", "hIbanOld",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] hIbanOld_constraints = new UISecConstraint[]{hIbanOld_defaultVisib_ctr};
		UISecConstraint hIbanOld_ctr = new ComplexUISecConstraint(
				hIbanOld_constraints);
		allConstraints.put("hIbanOld", hIbanOld_ctr);
		return allConstraints;
	}

	protected Map<String, UISecConstraint> getPageONOFFUIConstraints() {
		Map<String, UISecConstraint> allConstraints = new HashMap<String, UISecConstraint>();

		// constraint fittizio per mnuView
		UISecConstraint mnuView_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione", "mnuView",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] mnuView_constraints = new UISecConstraint[]{mnuView_defaultOnoff_ctr};
		UISecConstraint mnuView_ctr = new ComplexUISecConstraint(
				mnuView_constraints);
		allConstraints.put("mnuView", mnuView_ctr);

		// constraint fittizio per tfIdApplicazioneLab
		UISecConstraint tfIdApplicazioneLab_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione",
				"tfIdApplicazioneLab",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tfIdApplicazioneLab_constraints = new UISecConstraint[]{tfIdApplicazioneLab_defaultOnoff_ctr};
		UISecConstraint tfIdApplicazioneLab_ctr = new ComplexUISecConstraint(
				tfIdApplicazioneLab_constraints);
		allConstraints.put("tfIdApplicazioneLab", tfIdApplicazioneLab_ctr);

		// constraint fittizio per tfNomeApplicazione
		UISecConstraint tfNomeApplicazione_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione",
				"tfNomeApplicazione",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tfNomeApplicazione_constraints = new UISecConstraint[]{tfNomeApplicazione_defaultOnoff_ctr};
		UISecConstraint tfNomeApplicazione_ctr = new ComplexUISecConstraint(
				tfNomeApplicazione_constraints);
		allConstraints.put("tfNomeApplicazione", tfNomeApplicazione_ctr);

		// constraint fittizio per tfReferente
		UISecConstraint tfReferente_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione", "tfReferente",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tfReferente_constraints = new UISecConstraint[]{tfReferente_defaultOnoff_ctr};
		UISecConstraint tfReferente_ctr = new ComplexUISecConstraint(
				tfReferente_constraints);
		allConstraints.put("tfReferente", tfReferente_ctr);

		// constraint fittizio per tfCliente
		UISecConstraint tfCliente_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione", "tfCliente",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tfCliente_constraints = new UISecConstraint[]{tfCliente_defaultOnoff_ctr};
		UISecConstraint tfCliente_ctr = new ComplexUISecConstraint(
				tfCliente_constraints);
		allConstraints.put("tfCliente", tfCliente_ctr);

		// constraint fittizio per tfProgetto
		UISecConstraint tfProgetto_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione", "tfProgetto",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tfProgetto_constraints = new UISecConstraint[]{tfProgetto_defaultOnoff_ctr};
		UISecConstraint tfProgetto_ctr = new ComplexUISecConstraint(
				tfProgetto_constraints);
		allConstraints.put("tfProgetto", tfProgetto_ctr);

		// constraint fittizio per tfEmailEsercente
		UISecConstraint tfEmailEsercente_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione", "tfEmailEsercente",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tfEmailEsercente_constraints = new UISecConstraint[]{tfEmailEsercente_defaultOnoff_ctr};
		UISecConstraint tfEmailEsercente_ctr = new ComplexUISecConstraint(
				tfEmailEsercente_constraints);
		allConstraints.put("tfEmailEsercente", tfEmailEsercente_ctr);

		// constraint fittizio per tfNumeroVerde
		UISecConstraint tfNumeroVerde_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione", "tfNumeroVerde",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tfNumeroVerde_constraints = new UISecConstraint[]{tfNumeroVerde_defaultOnoff_ctr};
		UISecConstraint tfNumeroVerde_ctr = new ComplexUISecConstraint(
				tfNumeroVerde_constraints);
		allConstraints.put("tfNumeroVerde", tfNumeroVerde_ctr);

		// constraint fittizio per taNote
		UISecConstraint taNote_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione", "taNote",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] taNote_constraints = new UISecConstraint[]{taNote_defaultOnoff_ctr};
		UISecConstraint taNote_ctr = new ComplexUISecConstraint(
				taNote_constraints);
		allConstraints.put("taNote", taNote_ctr);

		// constraint fittizio per btnIndietro
		UISecConstraint btnIndietro_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione", "btnIndietro",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btnIndietro_constraints = new UISecConstraint[]{btnIndietro_defaultOnoff_ctr};
		UISecConstraint btnIndietro_ctr = new ComplexUISecConstraint(
				btnIndietro_constraints);
		allConstraints.put("btnIndietro", btnIndietro_ctr);

		// constraint fittizio per btnSalvaApplicazione
		UISecConstraint btnSalvaApplicazione_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione",
				"btnSalvaApplicazione",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btnSalvaApplicazione_constraints = new UISecConstraint[]{btnSalvaApplicazione_defaultOnoff_ctr};
		UISecConstraint btnSalvaApplicazione_ctr = new ComplexUISecConstraint(
				btnSalvaApplicazione_constraints);
		allConstraints.put("btnSalvaApplicazione", btnSalvaApplicazione_ctr);

		// constraint fittizio per tbListaAssociazioni
		UISecConstraint tbListaAssociazioni_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione",
				"tbListaAssociazioni",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tbListaAssociazioni_constraints = new UISecConstraint[]{tbListaAssociazioni_defaultOnoff_ctr};
		UISecConstraint tbListaAssociazioni_ctr = new ComplexUISecConstraint(
				tbListaAssociazioni_constraints);
		allConstraints.put("tbListaAssociazioni", tbListaAssociazioni_ctr);

		// constraint fittizio per btnUpdAssGW_MP
		UISecConstraint btnUpdAssGW_MP_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione", "btnUpdAssGW_MP",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btnUpdAssGW_MP_constraints = new UISecConstraint[]{btnUpdAssGW_MP_defaultOnoff_ctr};
		UISecConstraint btnUpdAssGW_MP_ctr = new ComplexUISecConstraint(
				btnUpdAssGW_MP_constraints);
		allConstraints.put("btnUpdAssGW_MP", btnUpdAssGW_MP_ctr);

		// constraint fittizio per txIdApplicazione
		UISecConstraint txIdApplicazione_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione", "txIdApplicazione",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txIdApplicazione_constraints = new UISecConstraint[]{txIdApplicazione_defaultOnoff_ctr};
		UISecConstraint txIdApplicazione_ctr = new ComplexUISecConstraint(
				txIdApplicazione_constraints);
		allConstraints.put("txIdApplicazione", txIdApplicazione_ctr);

		// constraint fittizio per cbGateways
		UISecConstraint cbGateways_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione", "cbGateways",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] cbGateways_constraints = new UISecConstraint[]{cbGateways_defaultOnoff_ctr};
		UISecConstraint cbGateways_ctr = new ComplexUISecConstraint(
				cbGateways_constraints);
		allConstraints.put("cbGateways", cbGateways_ctr);

		// constraint fittizio per cbPaymentModes
		UISecConstraint cbPaymentModes_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione", "cbPaymentModes",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] cbPaymentModes_constraints = new UISecConstraint[]{cbPaymentModes_defaultOnoff_ctr};
		UISecConstraint cbPaymentModes_ctr = new ComplexUISecConstraint(
				cbPaymentModes_constraints);
		allConstraints.put("cbPaymentModes", cbPaymentModes_ctr);

		// constraint fittizio per txCodIstat
		UISecConstraint txCodIstat_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione", "txCodIstat",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txCodIstat_constraints = new UISecConstraint[]{txCodIstat_defaultOnoff_ctr};
		UISecConstraint txCodIstat_ctr = new ComplexUISecConstraint(
				txCodIstat_constraints);
		allConstraints.put("txCodIstat", txCodIstat_ctr);

		// constraint fittizio per chkAbilitato
		UISecConstraint chkAbilitato_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione", "chkAbilitato",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] chkAbilitato_constraints = new UISecConstraint[]{chkAbilitato_defaultOnoff_ctr};
		UISecConstraint chkAbilitato_ctr = new ComplexUISecConstraint(
				chkAbilitato_constraints);
		allConstraints.put("chkAbilitato", chkAbilitato_ctr);

		// constraint fittizio per tfIdEsercente
		UISecConstraint tfIdEsercente_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione", "tfIdEsercente",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tfIdEsercente_constraints = new UISecConstraint[]{tfIdEsercente_defaultOnoff_ctr};
		UISecConstraint tfIdEsercente_ctr = new ComplexUISecConstraint(
				tfIdEsercente_constraints);
		allConstraints.put("tfIdEsercente", tfIdEsercente_ctr);

		// constraint fittizio per tfPasswordEsercente
		UISecConstraint tfPasswordEsercente_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione",
				"tfPasswordEsercente",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tfPasswordEsercente_constraints = new UISecConstraint[]{tfPasswordEsercente_defaultOnoff_ctr};
		UISecConstraint tfPasswordEsercente_ctr = new ComplexUISecConstraint(
				tfPasswordEsercente_constraints);
		allConstraints.put("tfPasswordEsercente", tfPasswordEsercente_ctr);

		// constraint fittizio per tfMacAvvio
		UISecConstraint tfMacAvvio_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione", "tfMacAvvio",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tfMacAvvio_constraints = new UISecConstraint[]{tfMacAvvio_defaultOnoff_ctr};
		UISecConstraint tfMacAvvio_ctr = new ComplexUISecConstraint(
				tfMacAvvio_constraints);
		allConstraints.put("tfMacAvvio", tfMacAvvio_ctr);

		// constraint fittizio per cbtipologiaComm
		UISecConstraint cbtipologiaComm_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione", "cbtipologiaComm",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] cbtipologiaComm_constraints = new UISecConstraint[]{cbtipologiaComm_defaultOnoff_ctr};
		UISecConstraint cbtipologiaComm_ctr = new ComplexUISecConstraint(
				cbtipologiaComm_constraints);
		allConstraints.put("cbtipologiaComm", cbtipologiaComm_ctr);

		// constraint fittizio per impMin
		UISecConstraint impMin_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione", "impMin",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] impMin_constraints = new UISecConstraint[]{impMin_defaultOnoff_ctr};
		UISecConstraint impMin_ctr = new ComplexUISecConstraint(
				impMin_constraints);
		allConstraints.put("impMin", impMin_ctr);

		// constraint fittizio per impMax
		UISecConstraint impMax_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione", "impMax",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] impMax_constraints = new UISecConstraint[]{impMax_defaultOnoff_ctr};
		UISecConstraint impMax_ctr = new ComplexUISecConstraint(
				impMax_constraints);
		allConstraints.put("impMax", impMax_ctr);

		// constraint fittizio per tfSoglia
		UISecConstraint tfSoglia_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione", "tfSoglia",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tfSoglia_constraints = new UISecConstraint[]{tfSoglia_defaultOnoff_ctr};
		UISecConstraint tfSoglia_ctr = new ComplexUISecConstraint(
				tfSoglia_constraints);
		allConstraints.put("tfSoglia", tfSoglia_ctr);

		// constraint fittizio per tfApplicationurlresponseko
		UISecConstraint tfApplicationurlresponseko_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione",
				"tfApplicationurlresponseko",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tfApplicationurlresponseko_constraints = new UISecConstraint[]{tfApplicationurlresponseko_defaultOnoff_ctr};
		UISecConstraint tfApplicationurlresponseko_ctr = new ComplexUISecConstraint(
				tfApplicationurlresponseko_constraints);
		allConstraints.put("tfApplicationurlresponseko",
				tfApplicationurlresponseko_ctr);

		// constraint fittizio per tfApplicationurlresponseok
		UISecConstraint tfApplicationurlresponseok_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione",
				"tfApplicationurlresponseok",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tfApplicationurlresponseok_constraints = new UISecConstraint[]{tfApplicationurlresponseok_defaultOnoff_ctr};
		UISecConstraint tfApplicationurlresponseok_ctr = new ComplexUISecConstraint(
				tfApplicationurlresponseok_constraints);
		allConstraints.put("tfApplicationurlresponseok",
				tfApplicationurlresponseok_ctr);

		// constraint fittizio per tfApplicationurlback
		UISecConstraint tfApplicationurlback_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione",
				"tfApplicationurlback",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tfApplicationurlback_constraints = new UISecConstraint[]{tfApplicationurlback_defaultOnoff_ctr};
		UISecConstraint tfApplicationurlback_ctr = new ComplexUISecConstraint(
				tfApplicationurlback_constraints);
		allConstraints.put("tfApplicationurlback", tfApplicationurlback_ctr);

		// constraint fittizio per chkMailacquirenteok
		UISecConstraint chkMailacquirenteok_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione",
				"chkMailacquirenteok",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] chkMailacquirenteok_constraints = new UISecConstraint[]{chkMailacquirenteok_defaultOnoff_ctr};
		UISecConstraint chkMailacquirenteok_ctr = new ComplexUISecConstraint(
				chkMailacquirenteok_constraints);
		allConstraints.put("chkMailacquirenteok", chkMailacquirenteok_ctr);

		// constraint fittizio per chkMailacquirenteko
		UISecConstraint chkMailacquirenteko_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione",
				"chkMailacquirenteko",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] chkMailacquirenteko_constraints = new UISecConstraint[]{chkMailacquirenteko_defaultOnoff_ctr};
		UISecConstraint chkMailacquirenteko_ctr = new ComplexUISecConstraint(
				chkMailacquirenteko_constraints);
		allConstraints.put("chkMailacquirenteko", chkMailacquirenteko_ctr);

		// constraint fittizio per chkMailesercenteok
		UISecConstraint chkMailesercenteok_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione",
				"chkMailesercenteok",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] chkMailesercenteok_constraints = new UISecConstraint[]{chkMailesercenteok_defaultOnoff_ctr};
		UISecConstraint chkMailesercenteok_ctr = new ComplexUISecConstraint(
				chkMailesercenteok_constraints);
		allConstraints.put("chkMailesercenteok", chkMailesercenteok_ctr);

		// constraint fittizio per chkMailesercenteko
		UISecConstraint chkMailesercenteko_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione",
				"chkMailesercenteko",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] chkMailesercenteko_constraints = new UISecConstraint[]{chkMailesercenteko_defaultOnoff_ctr};
		UISecConstraint chkMailesercenteko_ctr = new ComplexUISecConstraint(
				chkMailesercenteko_constraints);
		allConstraints.put("chkMailesercenteko", chkMailesercenteko_ctr);

		// constraint fittizio per chkMailsistemaok
		UISecConstraint chkMailsistemaok_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione", "chkMailsistemaok",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] chkMailsistemaok_constraints = new UISecConstraint[]{chkMailsistemaok_defaultOnoff_ctr};
		UISecConstraint chkMailsistemaok_ctr = new ComplexUISecConstraint(
				chkMailsistemaok_constraints);
		allConstraints.put("chkMailsistemaok", chkMailsistemaok_ctr);

		// constraint fittizio per chkMailsistemako
		UISecConstraint chkMailsistemako_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione", "chkMailsistemako",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] chkMailsistemako_constraints = new UISecConstraint[]{chkMailsistemako_defaultOnoff_ctr};
		UISecConstraint chkMailsistemako_ctr = new ComplexUISecConstraint(
				chkMailsistemako_constraints);
		allConstraints.put("chkMailsistemako", chkMailsistemako_ctr);

		// constraint fittizio per txFile
		UISecConstraint txFile_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione", "txFile",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txFile_constraints = new UISecConstraint[]{txFile_defaultOnoff_ctr};
		UISecConstraint txFile_ctr = new ComplexUISecConstraint(
				txFile_constraints);
		allConstraints.put("txFile", txFile_ctr);

		// constraint fittizio per btnCaricaAllegato
		UISecConstraint btnCaricaAllegato_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione",
				"btnCaricaAllegato",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btnCaricaAllegato_constraints = new UISecConstraint[]{btnCaricaAllegato_defaultOnoff_ctr};
		UISecConstraint btnCaricaAllegato_ctr = new ComplexUISecConstraint(
				btnCaricaAllegato_constraints);
		allConstraints.put("btnCaricaAllegato", btnCaricaAllegato_ctr);

		// constraint fittizio per tbListaAttributi
		UISecConstraint tbListaAttributi_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione", "tbListaAttributi",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tbListaAttributi_constraints = new UISecConstraint[]{tbListaAttributi_defaultOnoff_ctr};
		UISecConstraint tbListaAttributi_ctr = new ComplexUISecConstraint(
				tbListaAttributi_constraints);
		allConstraints.put("tbListaAttributi", tbListaAttributi_ctr);

		// constraint fittizio per txtChiave
		UISecConstraint txtChiave_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione", "txtChiave",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txtChiave_constraints = new UISecConstraint[]{txtChiave_defaultOnoff_ctr};
		UISecConstraint txtChiave_ctr = new ComplexUISecConstraint(
				txtChiave_constraints);
		allConstraints.put("txtChiave", txtChiave_ctr);

		// constraint fittizio per txtNome
		UISecConstraint txtNome_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione", "txtNome",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txtNome_constraints = new UISecConstraint[]{txtNome_defaultOnoff_ctr};
		UISecConstraint txtNome_ctr = new ComplexUISecConstraint(
				txtNome_constraints);
		allConstraints.put("txtNome", txtNome_ctr);

		// constraint fittizio per tfValore
		UISecConstraint tfValore_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione", "tfValore",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tfValore_constraints = new UISecConstraint[]{tfValore_defaultOnoff_ctr};
		UISecConstraint tfValore_ctr = new ComplexUISecConstraint(
				tfValore_constraints);
		allConstraints.put("tfValore", tfValore_ctr);

		// constraint fittizio per txtDescrizione
		UISecConstraint txtDescrizione_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione", "txtDescrizione",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txtDescrizione_constraints = new UISecConstraint[]{txtDescrizione_defaultOnoff_ctr};
		UISecConstraint txtDescrizione_ctr = new ComplexUISecConstraint(
				txtDescrizione_constraints);
		allConstraints.put("txtDescrizione", txtDescrizione_ctr);

		// constraint fittizio per btnCaricaAttributo
		UISecConstraint btnCaricaAttributo_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione",
				"btnCaricaAttributo",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btnCaricaAttributo_constraints = new UISecConstraint[]{btnCaricaAttributo_defaultOnoff_ctr};
		UISecConstraint btnCaricaAttributo_ctr = new ComplexUISecConstraint(
				btnCaricaAttributo_constraints);
		allConstraints.put("btnCaricaAttributo", btnCaricaAttributo_ctr);

		// constraint fittizio per btnCompletaiAttributo
		UISecConstraint btnCompletaiAttributo_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione",
				"btnCompletaiAttributo",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btnCompletaiAttributo_constraints = new UISecConstraint[]{btnCompletaiAttributo_defaultOnoff_ctr};
		UISecConstraint btnCompletaiAttributo_ctr = new ComplexUISecConstraint(
				btnCompletaiAttributo_constraints);
		allConstraints.put("btnCompletaiAttributo", btnCompletaiAttributo_ctr);

		// constraint fittizio per btnSalvaAssociazioneGW_MP
		UISecConstraint btnSalvaAssociazioneGW_MP_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione",
				"btnSalvaAssociazioneGW_MP",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btnSalvaAssociazioneGW_MP_constraints = new UISecConstraint[]{btnSalvaAssociazioneGW_MP_defaultOnoff_ctr};
		UISecConstraint btnSalvaAssociazioneGW_MP_ctr = new ComplexUISecConstraint(
				btnSalvaAssociazioneGW_MP_constraints);
		allConstraints.put("btnSalvaAssociazioneGW_MP",
				btnSalvaAssociazioneGW_MP_ctr);

		// constraint fittizio per txEnteId
		UISecConstraint txEnteId_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione", "txEnteId",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txEnteId_constraints = new UISecConstraint[]{txEnteId_defaultOnoff_ctr};
		UISecConstraint txEnteId_ctr = new ComplexUISecConstraint(
				txEnteId_constraints);
		allConstraints.put("txEnteId", txEnteId_ctr);

		// constraint fittizio per txPartitaIva
		UISecConstraint txPartitaIva_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione", "txPartitaIva",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txPartitaIva_constraints = new UISecConstraint[]{txPartitaIva_defaultOnoff_ctr};
		UISecConstraint txPartitaIva_ctr = new ComplexUISecConstraint(
				txPartitaIva_constraints);
		allConstraints.put("txPartitaIva", txPartitaIva_ctr);

		// constraint fittizio per txDescrizioneEnte
		UISecConstraint txDescrizioneEnte_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione",
				"txDescrizioneEnte",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txDescrizioneEnte_constraints = new UISecConstraint[]{txDescrizioneEnte_defaultOnoff_ctr};
		UISecConstraint txDescrizioneEnte_ctr = new ComplexUISecConstraint(
				txDescrizioneEnte_constraints);
		allConstraints.put("txDescrizioneEnte", txDescrizioneEnte_ctr);

		// constraint fittizio per cbEnteId
		UISecConstraint cbEnteId_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione", "cbEnteId",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] cbEnteId_constraints = new UISecConstraint[]{cbEnteId_defaultOnoff_ctr};
		UISecConstraint cbEnteId_ctr = new ComplexUISecConstraint(
				cbEnteId_constraints);
		allConstraints.put("cbEnteId", cbEnteId_ctr);

		// constraint fittizio per ctAssocia
		UISecConstraint ctAssocia_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione", "ctAssocia",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] ctAssocia_constraints = new UISecConstraint[]{ctAssocia_defaultOnoff_ctr};
		UISecConstraint ctAssocia_ctr = new ComplexUISecConstraint(
				ctAssocia_constraints);
		allConstraints.put("ctAssocia", ctAssocia_ctr);

		// constraint fittizio per tbIbanEnteApplicatio
		UISecConstraint tbIbanEnteApplicatio_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione",
				"tbIbanEnteApplicatio",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tbIbanEnteApplicatio_constraints = new UISecConstraint[]{tbIbanEnteApplicatio_defaultOnoff_ctr};
		UISecConstraint tbIbanEnteApplicatio_ctr = new ComplexUISecConstraint(
				tbIbanEnteApplicatio_constraints);
		allConstraints.put("tbIbanEnteApplicatio", tbIbanEnteApplicatio_ctr);

		// constraint fittizio per btdisattiva
		UISecConstraint btdisattiva_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione", "btdisattiva",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btdisattiva_constraints = new UISecConstraint[]{btdisattiva_defaultOnoff_ctr};
		UISecConstraint btdisattiva_ctr = new ComplexUISecConstraint(
				btdisattiva_constraints);
		allConstraints.put("btdisattiva", btdisattiva_ctr);

		// constraint fittizio per btmodifica
		UISecConstraint btmodifica_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione", "btmodifica",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btmodifica_constraints = new UISecConstraint[]{btmodifica_defaultOnoff_ctr};
		UISecConstraint btmodifica_ctr = new ComplexUISecConstraint(
				btmodifica_constraints);
		allConstraints.put("btmodifica", btmodifica_ctr);

		// constraint fittizio per txIdApplication
		UISecConstraint txIdApplication_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione", "txIdApplication",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txIdApplication_constraints = new UISecConstraint[]{txIdApplication_defaultOnoff_ctr};
		UISecConstraint txIdApplication_ctr = new ComplexUISecConstraint(
				txIdApplication_constraints);
		allConstraints.put("txIdApplication", txIdApplication_ctr);

		// constraint fittizio per txIdEnte
		UISecConstraint txIdEnte_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione", "txIdEnte",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txIdEnte_constraints = new UISecConstraint[]{txIdEnte_defaultOnoff_ctr};
		UISecConstraint txIdEnte_ctr = new ComplexUISecConstraint(
				txIdEnte_constraints);
		allConstraints.put("txIdEnte", txIdEnte_ctr);

		// constraint fittizio per cbListaTipoVersamento
		UISecConstraint cbListaTipoVersamento_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione",
				"cbListaTipoVersamento",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] cbListaTipoVersamento_constraints = new UISecConstraint[]{cbListaTipoVersamento_defaultOnoff_ctr};
		UISecConstraint cbListaTipoVersamento_ctr = new ComplexUISecConstraint(
				cbListaTipoVersamento_constraints);
		allConstraints.put("cbListaTipoVersamento", cbListaTipoVersamento_ctr);

		// constraint fittizio per cbIdentificativoPsp
		UISecConstraint cbIdentificativoPsp_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione",
				"cbIdentificativoPsp",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] cbIdentificativoPsp_constraints = new UISecConstraint[]{cbIdentificativoPsp_defaultOnoff_ctr};
		UISecConstraint cbIdentificativoPsp_ctr = new ComplexUISecConstraint(
				cbIdentificativoPsp_constraints);
		allConstraints.put("cbIdentificativoPsp", cbIdentificativoPsp_ctr);

		// constraint fittizio per cbAttivazione
		UISecConstraint cbAttivazione_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione", "cbAttivazione",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] cbAttivazione_constraints = new UISecConstraint[]{cbAttivazione_defaultOnoff_ctr};
		UISecConstraint cbAttivazione_ctr = new ComplexUISecConstraint(
				cbAttivazione_constraints);
		allConstraints.put("cbAttivazione", cbAttivazione_ctr);

		// constraint fittizio per txIban
		UISecConstraint txIban_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione", "txIban",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] txIban_constraints = new UISecConstraint[]{txIban_defaultOnoff_ctr};
		UISecConstraint txIban_ctr = new ComplexUISecConstraint(
				txIban_constraints);
		allConstraints.put("txIban", txIban_ctr);

		// constraint fittizio per clDataInizioValidita
		UISecConstraint clDataInizioValidita_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione",
				"clDataInizioValidita",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] clDataInizioValidita_constraints = new UISecConstraint[]{clDataInizioValidita_defaultOnoff_ctr};
		UISecConstraint clDataInizioValidita_ctr = new ComplexUISecConstraint(
				clDataInizioValidita_constraints);
		allConstraints.put("clDataInizioValidita", clDataInizioValidita_ctr);

		// constraint fittizio per cLdataFineValidita
		UISecConstraint cLdataFineValidita_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione",
				"cLdataFineValidita",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] cLdataFineValidita_constraints = new UISecConstraint[]{cLdataFineValidita_defaultOnoff_ctr};
		UISecConstraint cLdataFineValidita_ctr = new ComplexUISecConstraint(
				cLdataFineValidita_constraints);
		allConstraints.put("cLdataFineValidita", cLdataFineValidita_ctr);

		// constraint fittizio per btSalva
		UISecConstraint btSalva_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione", "btSalva",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btSalva_constraints = new UISecConstraint[]{btSalva_defaultOnoff_ctr};
		UISecConstraint btSalva_ctr = new ComplexUISecConstraint(
				btSalva_constraints);
		allConstraints.put("btSalva", btSalva_ctr);

		// constraint fittizio per hId
		UISecConstraint hId_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione", "hId",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] hId_constraints = new UISecConstraint[]{hId_defaultOnoff_ctr};
		UISecConstraint hId_ctr = new ComplexUISecConstraint(hId_constraints);
		allConstraints.put("hId", hId_ctr);

		// constraint fittizio per hIbanOld
		UISecConstraint hIbanOld_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazione", "hIbanOld",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] hIbanOld_constraints = new UISecConstraint[]{hIbanOld_defaultOnoff_ctr};
		UISecConstraint hIbanOld_ctr = new ComplexUISecConstraint(
				hIbanOld_constraints);
		allConstraints.put("hIbanOld", hIbanOld_ctr);
		return allConstraints;
	}

	/**  */
	void dumpModel(boolean pre) {
		log.debug("[CpDettaglioApplicazioneAction::dumpmodel] START");

		log.debug("[CpDettaglioApplicazioneAction::dumpmodel] #### DUMP del model della action "
				+ this.getClass()
				+ (pre ? " prima dell'azione" : " dopo l'azione"));
		log.debug("[CpDettaglioApplicazioneAction::dumpmodel] [a] campi pubblici del model");
		try {
			java.beans.BeanInfo bi = java.beans.Introspector.getBeanInfo(this
					.getClass());
			java.beans.PropertyDescriptor[] props = bi.getPropertyDescriptors();
			for (int i = 0; i < props.length; i++) {
				java.beans.PropertyDescriptor pd = props[i];
				java.lang.reflect.Method m = pd.getReadMethod();
				if (m != null) {
					Object pval = m.invoke(this, new Object[]{});
					log.debug("[CpDettaglioApplicazioneAction::dumpmodel] "
							+ pd.getName() + ":" + pval);
				}
			}
		} catch (IllegalAccessException e) {
			log.error("[CpDettaglioApplicazioneAction::dumpmodel] Errore nel dump"
					+ e + ", ignoro");
		} catch (InvocationTargetException e) {
			log.error("[CpDettaglioApplicazioneAction::dumpmodel] Errore nel dump"
					+ e + ", ignoro");
		} catch (IntrospectionException e) {
			log.error("[CpDettaglioApplicazioneAction::dumpmodel] Errore nel dump"
					+ e + ", ignoro");
		}
		log.debug("[CpDettaglioApplicazioneAction::dumpmodel] [b] stato dei widget");
		Object cpWidgetStatus = session.get("cpDettaglioApplicazione");
		log.debug("[CpDettaglioApplicazioneAction::dumpmodel] "
				+ cpWidgetStatus);
		log.debug("[CpDettaglioApplicazioneAction::dumpmodel] [c] sessione");
		log.debug("[CpDettaglioApplicazioneAction::dumpmodel] " + session);
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

		// contiene i comandi del widget btnIndietro per ogni Ev.H.
		HashMap<String, ICommand> cmds4btnIndietroByEvh = new HashMap<String, ICommand>();

		cmds4btnIndietroByEvh.put("CLICKED", initCommandBtnIndietro_CLICKED());
		cmdsByWidget.put("btnIndietro", cmds4btnIndietroByEvh);
		// contiene i comandi del widget btnSalvaApplicazione per ogni Ev.H.
		HashMap<String, ICommand> cmds4btnSalvaApplicazioneByEvh = new HashMap<String, ICommand>();

		cmds4btnSalvaApplicazioneByEvh.put("CLICKED",
				initCommandBtnSalvaApplicazione_CLICKED());
		cmdsByWidget
				.put("btnSalvaApplicazione", cmds4btnSalvaApplicazioneByEvh);
		// contiene i comandi del widget btnUpdAssGW_MP per ogni Ev.H.
		HashMap<String, ICommand> cmds4btnUpdAssGW_MPByEvh = new HashMap<String, ICommand>();

		cmds4btnUpdAssGW_MPByEvh.put("CLICKED",
				initCommandBtnUpdAssGW_MP_CLICKED());
		cmdsByWidget.put("btnUpdAssGW_MP", cmds4btnUpdAssGW_MPByEvh);
		// contiene i comandi del widget cbGateways per ogni Ev.H.
		HashMap<String, ICommand> cmds4cbGatewaysByEvh = new HashMap<String, ICommand>();

		cmds4cbGatewaysByEvh.put("VALUE_CHANGED",
				initCommandCbGateways_VALUE_CHANGED());
		cmdsByWidget.put("cbGateways", cmds4cbGatewaysByEvh);
		// contiene i comandi del widget btnCaricaAllegato per ogni Ev.H.
		HashMap<String, ICommand> cmds4btnCaricaAllegatoByEvh = new HashMap<String, ICommand>();

		cmds4btnCaricaAllegatoByEvh.put("CLICKED",
				initCommandBtnCaricaAllegato_CLICKED());
		cmdsByWidget.put("btnCaricaAllegato", cmds4btnCaricaAllegatoByEvh);
		// contiene i comandi del widget btnCaricaAttributo per ogni Ev.H.
		HashMap<String, ICommand> cmds4btnCaricaAttributoByEvh = new HashMap<String, ICommand>();

		cmds4btnCaricaAttributoByEvh.put("CLICKED",
				initCommandBtnCaricaAttributo_CLICKED());
		cmdsByWidget.put("btnCaricaAttributo", cmds4btnCaricaAttributoByEvh);
		// contiene i comandi del widget btnCompletaiAttributo per ogni Ev.H.
		HashMap<String, ICommand> cmds4btnCompletaiAttributoByEvh = new HashMap<String, ICommand>();

		cmds4btnCompletaiAttributoByEvh.put("CLICKED",
				initCommandBtnCompletaiAttributo_CLICKED());
		cmdsByWidget.put("btnCompletaiAttributo",
				cmds4btnCompletaiAttributoByEvh);
		// contiene i comandi del widget btnSalvaAssociazioneGW_MP per ogni Ev.H.
		HashMap<String, ICommand> cmds4btnSalvaAssociazioneGW_MPByEvh = new HashMap<String, ICommand>();

		cmds4btnSalvaAssociazioneGW_MPByEvh.put("CLICKED",
				initCommandBtnSalvaAssociazioneGW_MP_CLICKED());
		cmdsByWidget.put("btnSalvaAssociazioneGW_MP",
				cmds4btnSalvaAssociazioneGW_MPByEvh);
		// contiene i comandi del widget ctAssocia per ogni Ev.H.
		HashMap<String, ICommand> cmds4ctAssociaByEvh = new HashMap<String, ICommand>();

		cmds4ctAssociaByEvh.put("CLICKED", initCommandCtAssocia_CLICKED());
		cmdsByWidget.put("ctAssocia", cmds4ctAssociaByEvh);
		// contiene i comandi del widget btdisattiva per ogni Ev.H.
		HashMap<String, ICommand> cmds4btdisattivaByEvh = new HashMap<String, ICommand>();

		cmds4btdisattivaByEvh.put("CLICKED", initCommandBtdisattiva_CLICKED());
		cmdsByWidget.put("btdisattiva", cmds4btdisattivaByEvh);
		// contiene i comandi del widget btmodifica per ogni Ev.H.
		HashMap<String, ICommand> cmds4btmodificaByEvh = new HashMap<String, ICommand>();

		cmds4btmodificaByEvh.put("CLICKED", initCommandBtmodifica_CLICKED());
		cmdsByWidget.put("btmodifica", cmds4btmodificaByEvh);
		// contiene i comandi del widget btSalva per ogni Ev.H.
		HashMap<String, ICommand> cmds4btSalvaByEvh = new HashMap<String, ICommand>();

		cmds4btSalvaByEvh.put("CLICKED", initCommandBtSalva_CLICKED());
		cmdsByWidget.put("btSalva", cmds4btSalvaByEvh);

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

	private ICommand initCommandBtnIndietro_CLICKED() {
		// ExecCommand begin
		String[] resultNames4indietro = new String[]{"Ok"};

		ICommand[] actionsForResults4indietro = new ICommand[1];
		/// Jump Command begin
		JumpCommand act_actions_clicked_btnIndietro_resokAction_0 = new JumpCommand(
				"cpGestioneApplicazioni", null, false);
		/// Jump Command end
		actionsForResults4indietro[0] = act_actions_clicked_btnIndietro_resokAction_0;

		ExecCommand act_actions_clicked_btnIndietro_1 = new ExecCommand(
				resultNames4indietro, actionsForResults4indietro) {
			public ExecResults doLogic(Object theModel)
					throws CommandExecutionException {
				it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch(
						it.csi.mdp.mdpboweb.util.Constants.APPLICATION_CODE);
				ExecResults result = null;
				try {
					watcher.start();
					result = getSpringBackEnd()
							.indietro(
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioApplicazioneModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpDettaglioApplicazioneAction",
							"readOne()", "chiamata verso BackEnd", "indietro");
					return result;
				} catch (BEException e) {
					throw new CommandExecutionException(
							"errore non gestito nell'esecuzione del metodo [indietro]:"
									+ e.getMessage(), e);
				}

			}

			public void storeAppData(ExecResults res, BaseAction strutsAction) {
				// nothing to store...
			}
		};
		// Exec Action end
		return act_actions_clicked_btnIndietro_1;
	}

	private ICommand initCommandBtnSalvaApplicazione_CLICKED() {
		// ExecCommand begin
		String[] resultNames4salvaApplicazione = new String[]{"Ok", "Ko"};

		ICommand[] actionsForResults4salvaApplicazione = new ICommand[2];
		/// NOP Command begin
		NOPCommand act_actions_clicked_btnSalvaApplicazione_resokAction_0 = new NOPCommand();
		/// NOP Command end
		actionsForResults4salvaApplicazione[0] = act_actions_clicked_btnSalvaApplicazione_resokAction_0;
		/// NOP Command begin
		NOPCommand act_actions_clicked_btnSalvaApplicazione_reskoAction_1 = new NOPCommand();
		/// NOP Command end
		actionsForResults4salvaApplicazione[1] = act_actions_clicked_btnSalvaApplicazione_reskoAction_1;

		ExecCommand act_actions_clicked_btnSalvaApplicazione_1 = new ExecCommand(
				resultNames4salvaApplicazione,
				actionsForResults4salvaApplicazione) {
			public ExecResults doLogic(Object theModel)
					throws CommandExecutionException {
				it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch(
						it.csi.mdp.mdpboweb.util.Constants.APPLICATION_CODE);
				ExecResults result = null;
				try {
					watcher.start();
					result = getSpringBackEnd()
							.salvaApplicazione(
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioApplicazioneModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpDettaglioApplicazioneAction",
							"readOne()", "chiamata verso BackEnd",
							"salvaApplicazione");
					return result;
				} catch (BEException e) {
					throw new CommandExecutionException(
							"errore non gestito nell'esecuzione del metodo [salvaApplicazione]:"
									+ e.getMessage(), e);
				}

			}

			public void storeAppData(ExecResults res, BaseAction strutsAction) {
				// nothing to store...
			}
		};
		// Exec Action end
		return act_actions_clicked_btnSalvaApplicazione_1;
	}

	private ICommand initCommandBtnUpdAssGW_MP_CLICKED() {
		// ExecCommand begin
		String[] resultNames4modificaAssociazioneGW_MP = new String[]{"Ok"};

		ICommand[] actionsForResults4modificaAssociazioneGW_MP = new ICommand[1];
		// SequenceCommand begin
		/// NOP Command begin
		NOPCommand act_actions_clicked_btnUpdAssGW_MP_resokActionstep_0 = new NOPCommand();
		/// NOP Command end

		ICommand[] act_actions_clicked_btnUpdAssGW_MP_resokAction_0_steps = new ICommand[]{act_actions_clicked_btnUpdAssGW_MP_resokActionstep_0};
		SequenceCommand act_actions_clicked_btnUpdAssGW_MP_resokAction_0 = new SequenceCommand(
				act_actions_clicked_btnUpdAssGW_MP_resokAction_0_steps);
		// SequenceCommand end
		actionsForResults4modificaAssociazioneGW_MP[0] = act_actions_clicked_btnUpdAssGW_MP_resokAction_0;

		ExecCommand act_actions_clicked_btnUpdAssGW_MP_1 = new ExecCommand(
				resultNames4modificaAssociazioneGW_MP,
				actionsForResults4modificaAssociazioneGW_MP) {
			public ExecResults doLogic(Object theModel)
					throws CommandExecutionException {
				it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch(
						it.csi.mdp.mdpboweb.util.Constants.APPLICATION_CODE);
				ExecResults result = null;
				try {
					watcher.start();
					result = getSpringBackEnd()
							.modificaAssociazioneGW_MP(
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioApplicazioneModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpDettaglioApplicazioneAction",
							"readOne()", "chiamata verso BackEnd",
							"modificaAssociazioneGW_MP");
					return result;
				} catch (BEException e) {
					throw new CommandExecutionException(
							"errore non gestito nell'esecuzione del metodo [modificaAssociazioneGW_MP]:"
									+ e.getMessage(), e);
				}

			}

			public void storeAppData(ExecResults res, BaseAction strutsAction) {
				// nothing to store...
			}
		};
		// Exec Action end
		return act_actions_clicked_btnUpdAssGW_MP_1;
	}

	private ICommand initCommandCbGateways_VALUE_CHANGED() {
		// ExecCommand begin
		String[] resultNames4caricaComboModalitaPagamento = new String[]{"Ok"};

		ICommand[] actionsForResults4caricaComboModalitaPagamento = new ICommand[1];
		// SequenceCommand begin
		/// Refresh Command begin
		String[] act_actions_value_changed_cbGateways_resokActionstep_0_panels = new String[]{"wpGWMP"};
		String[] act_actions_value_changed_cbGateways_resokActionstep_0_widgets = new String[]{"cbPaymentModes"};
		RefreshViewCommand act_actions_value_changed_cbGateways_resokActionstep_0 = new RefreshViewCommand(
				"cpDettaglioApplicazione",
				act_actions_value_changed_cbGateways_resokActionstep_0_panels,
				act_actions_value_changed_cbGateways_resokActionstep_0_widgets);

		/// Refresh Command end

		ICommand[] act_actions_value_changed_cbGateways_resokAction_0_steps = new ICommand[]{act_actions_value_changed_cbGateways_resokActionstep_0};
		SequenceCommand act_actions_value_changed_cbGateways_resokAction_0 = new SequenceCommand(
				act_actions_value_changed_cbGateways_resokAction_0_steps);
		// SequenceCommand end
		actionsForResults4caricaComboModalitaPagamento[0] = act_actions_value_changed_cbGateways_resokAction_0;

		ExecCommand act_actions_value_changed_cbGateways_1 = new ExecCommand(
				resultNames4caricaComboModalitaPagamento,
				actionsForResults4caricaComboModalitaPagamento) {
			public ExecResults doLogic(Object theModel)
					throws CommandExecutionException {
				it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch(
						it.csi.mdp.mdpboweb.util.Constants.APPLICATION_CODE);
				ExecResults result = null;
				try {
					watcher.start();
					result = getSpringBackEnd()
							.caricaComboModalitaPagamento(
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioApplicazioneModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpDettaglioApplicazioneAction",
							"readOne()", "chiamata verso BackEnd",
							"caricaComboModalitaPagamento");
					return result;
				} catch (BEException e) {
					throw new CommandExecutionException(
							"errore non gestito nell'esecuzione del metodo [caricaComboModalitaPagamento]:"
									+ e.getMessage(), e);
				}

			}

			public void storeAppData(ExecResults res, BaseAction strutsAction) {
				// cast della action al tipo specifico per poter utilizzare direttamente
				// le property
				CpDettaglioApplicazioneAction specificAction = (CpDettaglioApplicazioneAction) strutsAction;

				// store del dato paymentModes
				//java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.PaymentMode> _appDatapaymentModes = 
				//	(java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.PaymentMode>)res.getAppDatapaymentModes();
				// il dato paymentModes e' salvato in un attributo della action corrente
				//specificAction.setAppDatapaymentModes(_appDatapaymentModes);
				// il dato paymentModes e' salvato in sessione
				strutsAction.getSession().put("appDatapaymentModes",
						specificAction.getAppDatapaymentModes());
			}
		};
		// Exec Action end
		return act_actions_value_changed_cbGateways_1;
	}

	private ICommand initCommandBtnCaricaAllegato_CLICKED() {
		// ExecCommand begin
		String[] resultNames4caricaAllegato = new String[]{"Ok"};

		ICommand[] actionsForResults4caricaAllegato = new ICommand[1];
		// SequenceCommand begin
		/// NOP Command begin
		NOPCommand act_actions_clicked_btnCaricaAllegato_resokActionstep_0 = new NOPCommand();
		/// NOP Command end

		ICommand[] act_actions_clicked_btnCaricaAllegato_resokAction_0_steps = new ICommand[]{act_actions_clicked_btnCaricaAllegato_resokActionstep_0};
		SequenceCommand act_actions_clicked_btnCaricaAllegato_resokAction_0 = new SequenceCommand(
				act_actions_clicked_btnCaricaAllegato_resokAction_0_steps);
		// SequenceCommand end
		actionsForResults4caricaAllegato[0] = act_actions_clicked_btnCaricaAllegato_resokAction_0;

		ExecCommand act_actions_clicked_btnCaricaAllegato_1 = new ExecCommand(
				resultNames4caricaAllegato, actionsForResults4caricaAllegato) {
			public ExecResults doLogic(Object theModel)
					throws CommandExecutionException {
				it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch(
						it.csi.mdp.mdpboweb.util.Constants.APPLICATION_CODE);
				ExecResults result = null;
				try {
					watcher.start();
					result = getSpringBackEnd()
							.caricaAllegato(
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioApplicazioneModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpDettaglioApplicazioneAction",
							"readOne()", "chiamata verso BackEnd",
							"caricaAllegato");
					return result;
				} catch (BEException e) {
					throw new CommandExecutionException(
							"errore non gestito nell'esecuzione del metodo [caricaAllegato]:"
									+ e.getMessage(), e);
				}

			}

			public void storeAppData(ExecResults res, BaseAction strutsAction) {
				// nothing to store...
			}
		};
		// Exec Action end
		return act_actions_clicked_btnCaricaAllegato_1;
	}

	private ICommand initCommandBtnCaricaAttributo_CLICKED() {
		// ExecCommand begin
		String[] resultNames4caricaAttributoSelezionato = new String[]{"Ok"};

		ICommand[] actionsForResults4caricaAttributoSelezionato = new ICommand[1];
		// SequenceCommand begin
		/// NOP Command begin
		NOPCommand act_actions_clicked_btnCaricaAttributo_resokActionstep_0 = new NOPCommand();
		/// NOP Command end

		ICommand[] act_actions_clicked_btnCaricaAttributo_resokAction_0_steps = new ICommand[]{act_actions_clicked_btnCaricaAttributo_resokActionstep_0};
		SequenceCommand act_actions_clicked_btnCaricaAttributo_resokAction_0 = new SequenceCommand(
				act_actions_clicked_btnCaricaAttributo_resokAction_0_steps);
		// SequenceCommand end
		actionsForResults4caricaAttributoSelezionato[0] = act_actions_clicked_btnCaricaAttributo_resokAction_0;

		ExecCommand act_actions_clicked_btnCaricaAttributo_1 = new ExecCommand(
				resultNames4caricaAttributoSelezionato,
				actionsForResults4caricaAttributoSelezionato) {
			public ExecResults doLogic(Object theModel)
					throws CommandExecutionException {
				it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch(
						it.csi.mdp.mdpboweb.util.Constants.APPLICATION_CODE);
				ExecResults result = null;
				try {
					watcher.start();
					result = getSpringBackEnd()
							.caricaAttributoSelezionato(
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioApplicazioneModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpDettaglioApplicazioneAction",
							"readOne()", "chiamata verso BackEnd",
							"caricaAttributoSelezionato");
					return result;
				} catch (BEException e) {
					throw new CommandExecutionException(
							"errore non gestito nell'esecuzione del metodo [caricaAttributoSelezionato]:"
									+ e.getMessage(), e);
				}

			}

			public void storeAppData(ExecResults res, BaseAction strutsAction) {
				// nothing to store...
			}
		};
		// Exec Action end
		return act_actions_clicked_btnCaricaAttributo_1;
	}

	private ICommand initCommandBtnCompletaiAttributo_CLICKED() {
		// ExecCommand begin
		String[] resultNames4completaAttributo = new String[]{"Ok"};

		ICommand[] actionsForResults4completaAttributo = new ICommand[1];
		// SequenceCommand begin
		/// NOP Command begin
		NOPCommand act_actions_clicked_btnCompletaiAttributo_resokActionstep_0 = new NOPCommand();
		/// NOP Command end

		ICommand[] act_actions_clicked_btnCompletaiAttributo_resokAction_0_steps = new ICommand[]{act_actions_clicked_btnCompletaiAttributo_resokActionstep_0};
		SequenceCommand act_actions_clicked_btnCompletaiAttributo_resokAction_0 = new SequenceCommand(
				act_actions_clicked_btnCompletaiAttributo_resokAction_0_steps);
		// SequenceCommand end
		actionsForResults4completaAttributo[0] = act_actions_clicked_btnCompletaiAttributo_resokAction_0;

		ExecCommand act_actions_clicked_btnCompletaiAttributo_1 = new ExecCommand(
				resultNames4completaAttributo,
				actionsForResults4completaAttributo) {
			public ExecResults doLogic(Object theModel)
					throws CommandExecutionException {
				it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch(
						it.csi.mdp.mdpboweb.util.Constants.APPLICATION_CODE);
				ExecResults result = null;
				try {
					watcher.start();
					result = getSpringBackEnd()
							.completaAttributo(
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioApplicazioneModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpDettaglioApplicazioneAction",
							"readOne()", "chiamata verso BackEnd",
							"completaAttributo");
					return result;
				} catch (BEException e) {
					throw new CommandExecutionException(
							"errore non gestito nell'esecuzione del metodo [completaAttributo]:"
									+ e.getMessage(), e);
				}

			}

			public void storeAppData(ExecResults res, BaseAction strutsAction) {
				// nothing to store...
			}
		};
		// Exec Action end
		return act_actions_clicked_btnCompletaiAttributo_1;
	}

	private ICommand initCommandBtnSalvaAssociazioneGW_MP_CLICKED() {
		// ExecCommand begin
		String[] resultNames4salvaAssociazioneGW_MP = new String[]{"Ok", "KO"};

		ICommand[] actionsForResults4salvaAssociazioneGW_MP = new ICommand[2];
		// SequenceCommand begin
		/// NOP Command begin
		NOPCommand act_actions_clicked_btnSalvaAssociazioneGW_MP_resokActionstep_0 = new NOPCommand();
		/// NOP Command end

		ICommand[] act_actions_clicked_btnSalvaAssociazioneGW_MP_resokAction_0_steps = new ICommand[]{act_actions_clicked_btnSalvaAssociazioneGW_MP_resokActionstep_0};
		SequenceCommand act_actions_clicked_btnSalvaAssociazioneGW_MP_resokAction_0 = new SequenceCommand(
				act_actions_clicked_btnSalvaAssociazioneGW_MP_resokAction_0_steps);
		// SequenceCommand end
		actionsForResults4salvaAssociazioneGW_MP[0] = act_actions_clicked_btnSalvaAssociazioneGW_MP_resokAction_0;
		// SequenceCommand begin
		/// NOP Command begin
		NOPCommand act_actions_clicked_btnSalvaAssociazioneGW_MP_reskoActionstep_0 = new NOPCommand();
		/// NOP Command end

		ICommand[] act_actions_clicked_btnSalvaAssociazioneGW_MP_reskoAction_1_steps = new ICommand[]{act_actions_clicked_btnSalvaAssociazioneGW_MP_reskoActionstep_0};
		SequenceCommand act_actions_clicked_btnSalvaAssociazioneGW_MP_reskoAction_1 = new SequenceCommand(
				act_actions_clicked_btnSalvaAssociazioneGW_MP_reskoAction_1_steps);
		// SequenceCommand end
		actionsForResults4salvaAssociazioneGW_MP[1] = act_actions_clicked_btnSalvaAssociazioneGW_MP_reskoAction_1;

		ExecCommand act_actions_clicked_btnSalvaAssociazioneGW_MP_1 = new ExecCommand(
				resultNames4salvaAssociazioneGW_MP,
				actionsForResults4salvaAssociazioneGW_MP) {
			public ExecResults doLogic(Object theModel)
					throws CommandExecutionException {
				it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch(
						it.csi.mdp.mdpboweb.util.Constants.APPLICATION_CODE);
				ExecResults result = null;
				try {
					watcher.start();
					result = getSpringBackEnd()
							.salvaAssociazioneGW_MP(
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioApplicazioneModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpDettaglioApplicazioneAction",
							"readOne()", "chiamata verso BackEnd",
							"salvaAssociazioneGW_MP");
					return result;
				} catch (BEException e) {
					throw new CommandExecutionException(
							"errore non gestito nell'esecuzione del metodo [salvaAssociazioneGW_MP]:"
									+ e.getMessage(), e);
				}

			}

			public void storeAppData(ExecResults res, BaseAction strutsAction) {
				// nothing to store...
			}
		};
		// Exec Action end
		return act_actions_clicked_btnSalvaAssociazioneGW_MP_1;
	}

	private ICommand initCommandCtAssocia_CLICKED() {
		// ExecCommand begin
		String[] resultNames4associaApplicazioneEnte = new String[]{"ok"};

		ICommand[] actionsForResults4associaApplicazioneEnte = new ICommand[1];
		/// NOP Command begin
		NOPCommand act_actions_clicked_ctAssocia_resokAction_0 = new NOPCommand();
		/// NOP Command end
		actionsForResults4associaApplicazioneEnte[0] = act_actions_clicked_ctAssocia_resokAction_0;

		ExecCommand act_actions_clicked_ctAssocia_1 = new ExecCommand(
				resultNames4associaApplicazioneEnte,
				actionsForResults4associaApplicazioneEnte) {
			public ExecResults doLogic(Object theModel)
					throws CommandExecutionException {
				it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch(
						it.csi.mdp.mdpboweb.util.Constants.APPLICATION_CODE);
				ExecResults result = null;
				try {
					watcher.start();
					result = getSpringBackEnd()
							.associaApplicazioneEnte(
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioApplicazioneModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpDettaglioApplicazioneAction",
							"readOne()", "chiamata verso BackEnd",
							"associaApplicazioneEnte");
					return result;
				} catch (BEException e) {
					throw new CommandExecutionException(
							"errore non gestito nell'esecuzione del metodo [associaApplicazioneEnte]:"
									+ e.getMessage(), e);
				}

			}

			public void storeAppData(ExecResults res, BaseAction strutsAction) {
				// nothing to store...
			}
		};
		// Exec Action end
		return act_actions_clicked_ctAssocia_1;
	}

	private ICommand initCommandBtdisattiva_CLICKED() {
		// ExecCommand begin
		String[] resultNames4disattivaIban = new String[]{"ok"};

		ICommand[] actionsForResults4disattivaIban = new ICommand[1];
		/// NOP Command begin
		NOPCommand act_actions_clicked_btdisattiva_resokAction_0 = new NOPCommand();
		/// NOP Command end
		actionsForResults4disattivaIban[0] = act_actions_clicked_btdisattiva_resokAction_0;

		ExecCommand act_actions_clicked_btdisattiva_1 = new ExecCommand(
				resultNames4disattivaIban, actionsForResults4disattivaIban) {
			public ExecResults doLogic(Object theModel)
					throws CommandExecutionException {
				it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch(
						it.csi.mdp.mdpboweb.util.Constants.APPLICATION_CODE);
				ExecResults result = null;
				try {
					watcher.start();
					result = getSpringBackEnd()
							.disattivaIban(
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioApplicazioneModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpDettaglioApplicazioneAction",
							"readOne()", "chiamata verso BackEnd",
							"disattivaIban");
					return result;
				} catch (BEException e) {
					throw new CommandExecutionException(
							"errore non gestito nell'esecuzione del metodo [disattivaIban]:"
									+ e.getMessage(), e);
				}

			}

			public void storeAppData(ExecResults res, BaseAction strutsAction) {
				// nothing to store...
			}
		};
		// Exec Action end
		return act_actions_clicked_btdisattiva_1;
	}

	private ICommand initCommandBtmodifica_CLICKED() {
		// ExecCommand begin
		String[] resultNames4modificaIban = new String[]{"ok"};

		ICommand[] actionsForResults4modificaIban = new ICommand[1];
		/// NOP Command begin
		NOPCommand act_actions_clicked_btmodifica_resokAction_0 = new NOPCommand();
		/// NOP Command end
		actionsForResults4modificaIban[0] = act_actions_clicked_btmodifica_resokAction_0;

		ExecCommand act_actions_clicked_btmodifica_1 = new ExecCommand(
				resultNames4modificaIban, actionsForResults4modificaIban) {
			public ExecResults doLogic(Object theModel)
					throws CommandExecutionException {
				it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch(
						it.csi.mdp.mdpboweb.util.Constants.APPLICATION_CODE);
				ExecResults result = null;
				try {
					watcher.start();
					result = getSpringBackEnd()
							.modificaIban(
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioApplicazioneModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpDettaglioApplicazioneAction",
							"readOne()", "chiamata verso BackEnd",
							"modificaIban");
					return result;
				} catch (BEException e) {
					throw new CommandExecutionException(
							"errore non gestito nell'esecuzione del metodo [modificaIban]:"
									+ e.getMessage(), e);
				}

			}

			public void storeAppData(ExecResults res, BaseAction strutsAction) {
				// nothing to store...
			}
		};
		// Exec Action end
		return act_actions_clicked_btmodifica_1;
	}

	private ICommand initCommandBtSalva_CLICKED() {
		// ExecCommand begin
		String[] resultNames4salvaIban = new String[]{"ok", "ko"};

		ICommand[] actionsForResults4salvaIban = new ICommand[2];
		/// NOP Command begin
		NOPCommand act_actions_clicked_btSalva_resokAction_0 = new NOPCommand();
		/// NOP Command end
		actionsForResults4salvaIban[0] = act_actions_clicked_btSalva_resokAction_0;
		/// NOP Command begin
		NOPCommand act_actions_clicked_btSalva_reskoAction_1 = new NOPCommand();
		/// NOP Command end
		actionsForResults4salvaIban[1] = act_actions_clicked_btSalva_reskoAction_1;

		ExecCommand act_actions_clicked_btSalva_1 = new ExecCommand(
				resultNames4salvaIban, actionsForResults4salvaIban) {
			public ExecResults doLogic(Object theModel)
					throws CommandExecutionException {
				it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch(
						it.csi.mdp.mdpboweb.util.Constants.APPLICATION_CODE);
				ExecResults result = null;
				try {
					watcher.start();
					result = getSpringBackEnd()
							.salvaIban(
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioApplicazioneModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpDettaglioApplicazioneAction",
							"readOne()", "chiamata verso BackEnd", "salvaIban");
					return result;
				} catch (BEException e) {
					throw new CommandExecutionException(
							"errore non gestito nell'esecuzione del metodo [salvaIban]:"
									+ e.getMessage(), e);
				}

			}

			public void storeAppData(ExecResults res, BaseAction strutsAction) {
				// nothing to store...
			}
		};
		// Exec Action end
		return act_actions_clicked_btSalva_1;
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
