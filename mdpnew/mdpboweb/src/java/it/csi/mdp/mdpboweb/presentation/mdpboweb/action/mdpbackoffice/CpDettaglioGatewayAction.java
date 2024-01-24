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
 * CpDettaglioGatewayAction Action Class.
 *
 * @author GuiGen
 */
@Validation()
@Conversion()
public class CpDettaglioGatewayAction extends BaseAction implements Preparable {

	public void setAppDatacurrentUser(
			it.csi.mdp.mdpboweb.dto.common.UserInfo value) {
		getSession().put("appDatacurrentUser", value);
	}

	public it.csi.mdp.mdpboweb.dto.common.UserInfo getAppDatacurrentUser() {
		return (it.csi.mdp.mdpboweb.dto.common.UserInfo) (getSession()
				.get("appDatacurrentUser"));
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

	public void setAppDataselettoreIdGateway(java.lang.String value) {
		getSession().put("appDataselettoreIdGateway", value);
	}

	public java.lang.String getAppDataselettoreIdGateway() {
		return (java.lang.String) (getSession()
				.get("appDataselettoreIdGateway"));
	}

	public void setAppDataAzioneGateway(java.lang.String value) {
		getSession().put("appDataAzioneGateway", value);
	}

	public java.lang.String getAppDataAzioneGateway() {
		return (java.lang.String) (getSession().get("appDataAzioneGateway"));
	}

	public void setAppDatauserInfoExt(
			it.csi.mdp.mdpboweb.dto.nsbackoffice.UserInfoExt value) {
		getSession().put("appDatauserInfoExt", value);
	}

	public it.csi.mdp.mdpboweb.dto.nsbackoffice.UserInfoExt getAppDatauserInfoExt() {
		return (it.csi.mdp.mdpboweb.dto.nsbackoffice.UserInfoExt) (getSession()
				.get("appDatauserInfoExt"));
	}

	public void setAppDataselettoreItemAssociazioneGW_MP(java.lang.String value) {
		getSession().put("appDataselettoreItemAssociazioneGW_MP", value);
	}

	public java.lang.String getAppDataselettoreItemAssociazioneGW_MP() {
		return (java.lang.String) (getSession()
				.get("appDataselettoreItemAssociazioneGW_MP"));
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

	public void setAppDataselettoreIdGateway2(java.lang.String value) {
		getSession().put("appDataselettoreIdGateway2", value);
	}

	public java.lang.String getAppDataselettoreIdGateway2() {
		return (java.lang.String) (getSession()
				.get("appDataselettoreIdGateway2"));
	}

	public void setAppDataselettoreIdLingua(java.lang.String value) {
		getSession().put("appDataselettoreIdLingua", value);
	}

	public java.lang.String getAppDataselettoreIdLingua() {
		return (java.lang.String) (getSession().get("appDataselettoreIdLingua"));
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

	public void setAppDataselettoreIdDivisa(java.lang.String value) {
		getSession().put("appDataselettoreIdDivisa", value);
	}

	public java.lang.String getAppDataselettoreIdDivisa() {
		return (java.lang.String) (getSession().get("appDataselettoreIdDivisa"));
	}

	public void setAppDatalingue(
			java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Lingua> value) {
		getSession().put("appDatalingue", value);
	}

	public java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Lingua> getAppDatalingue() {
		return (java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Lingua>) (getSession()
				.get("appDatalingue"));
	}

	public void setAppDatadivise(
			java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Divisa> value) {
		getSession().put("appDatadivise", value);
	}

	public java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Divisa> getAppDatadivise() {
		return (java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Divisa>) (getSession()
				.get("appDatadivise"));
	}

	public void setAppDatadiviseClone(
			java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Divisa> value) {
		getSession().put("appDatadiviseClone", value);
	}

	public java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Divisa> getAppDatadiviseClone() {
		return (java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Divisa>) (getSession()
				.get("appDatadiviseClone"));
	}

	public void setAppDatalingueClone(
			java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Lingua> value) {
		getSession().put("appDatalingueClone", value);
	}

	public java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Lingua> getAppDatalingueClone() {
		return (java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Lingua>) (getSession()
				.get("appDatalingueClone"));
	}

	public void setAppDataextraAttributesNuovoGateway(
			java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.ExtraAttribute> value) {
		getSession().put("appDataextraAttributesNuovoGateway", value);
	}

	public java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.ExtraAttribute> getAppDataextraAttributesNuovoGateway() {
		return (java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.ExtraAttribute>) (getSession()
				.get("appDataextraAttributesNuovoGateway"));
	}

	public void setAppDataselettoreChiaveAttr(java.lang.String value) {
		getSession().put("appDataselettoreChiaveAttr", value);
	}

	public java.lang.String getAppDataselettoreChiaveAttr() {
		return (java.lang.String) (getSession()
				.get("appDataselettoreChiaveAttr"));
	}

	public void setAppDatanuovoExtraAttribute(
			it.csi.mdp.mdpboweb.dto.nsbackoffice.ExtraAttribute value) {
		getSession().put("appDatanuovoExtraAttribute", value);
	}

	public it.csi.mdp.mdpboweb.dto.nsbackoffice.ExtraAttribute getAppDatanuovoExtraAttribute() {
		return (it.csi.mdp.mdpboweb.dto.nsbackoffice.ExtraAttribute) (getSession()
				.get("appDatanuovoExtraAttribute"));
	}

	public void setAppDataextraAttributes(
			java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.ExtraAttribute> value) {
		getSession().put("appDataextraAttributes", value);
	}

	public java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.ExtraAttribute> getAppDataextraAttributes() {
		return (java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.ExtraAttribute>) (getSession()
				.get("appDataextraAttributes"));
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
		return it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioGatewayModel.class;
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
	 * Gestione dell'evento CLICKED sul widget [btnAggiungiAttributo]
	 */
	@MethodProtection(level = "REJECT_SAME")
	public String handleBtnAggiungiAttributo_CLICKED()
			throws CommandExecutionException {
		ICommand action = initCommand("btnAggiungiAttributo", "CLICKED");
		if (log.isDebugEnabled()) {
			log.debug("[CpDettaglioGatewayAction::handleBtnAggiungiAttributo_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpDettaglioGatewayAction::handleBtnAggiungiAttributo_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpDettaglioGatewayAction::handleBtnAggiungiAttributo_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpDettaglioGatewayAction::handleBtnAggiungiAttributo_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpDettaglioGatewayAction::handleBtnAggiungiAttributo_CLICKED] returning default result [SUCCESS]");
			}
			return SUCCESS;

		}
	}

	/**
	 * Gestione dell'evento CLICKED sul widget [btnIndietro]
	 */
	@MethodProtection(level = "REJECT_SAME")
	public String handleBtnIndietro_CLICKED() throws CommandExecutionException {
		ICommand action = initCommand("btnIndietro", "CLICKED");
		if (log.isDebugEnabled()) {
			log.debug("[CpDettaglioGatewayAction::handleBtnIndietro_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpDettaglioGatewayAction::handleBtnIndietro_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpDettaglioGatewayAction::handleBtnIndietro_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpDettaglioGatewayAction::handleBtnIndietro_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpDettaglioGatewayAction::handleBtnIndietro_CLICKED] returning default result [SUCCESS]");
			}
			return SUCCESS;

		}
	}

	/**
	 * Gestione dell'evento CLICKED sul widget [btnSalva]
	 */
	@MethodProtection(level = "REJECT_SAME")
	public String handleBtnSalva_CLICKED() throws CommandExecutionException {
		ICommand action = initCommand("btnSalva", "CLICKED");
		if (log.isDebugEnabled()) {
			log.debug("[CpDettaglioGatewayAction::handleBtnSalva_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpDettaglioGatewayAction::handleBtnSalva_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpDettaglioGatewayAction::handleBtnSalva_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpDettaglioGatewayAction::handleBtnSalva_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpDettaglioGatewayAction::handleBtnSalva_CLICKED] returning default result [SUCCESS]");
			}
			return SUCCESS;

		}
	}

	/**
	 * Gestione dell'evento CLICKED sul widget [btnCaricaAssociazioneSelezionataGW_MP]
	 */
	@MethodProtection(level = "REJECT_SAME")
	public String handleBtnCaricaAssociazioneSelezionataGW_MP_CLICKED()
			throws CommandExecutionException {
		ICommand action = initCommand("btnCaricaAssociazioneSelezionataGW_MP",
				"CLICKED");
		if (log.isDebugEnabled()) {
			log.debug("[CpDettaglioGatewayAction::handleBtnCaricaAssociazioneSelezionataGW_MP_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpDettaglioGatewayAction::handleBtnCaricaAssociazioneSelezionataGW_MP_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpDettaglioGatewayAction::handleBtnCaricaAssociazioneSelezionataGW_MP_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpDettaglioGatewayAction::handleBtnCaricaAssociazioneSelezionataGW_MP_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpDettaglioGatewayAction::handleBtnCaricaAssociazioneSelezionataGW_MP_CLICKED] returning default result [SUCCESS]");
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
			log.debug("[CpDettaglioGatewayAction::handleBtnSalvaAssociazioneGW_MP_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpDettaglioGatewayAction::handleBtnSalvaAssociazioneGW_MP_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpDettaglioGatewayAction::handleBtnSalvaAssociazioneGW_MP_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpDettaglioGatewayAction::handleBtnSalvaAssociazioneGW_MP_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpDettaglioGatewayAction::handleBtnSalvaAssociazioneGW_MP_CLICKED] returning default result [SUCCESS]");
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

		if (isTableClearStatus("cpDettaglioGateway_tbListaAttributi")) {
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
	 * al data-binding relativo al dataset DATASET del widget tLingue.
	 * @return sempre il result "provideData"
	 */
	@SkipValidation
	@FatClientOnly
	public String provideTLingue_DATASET() throws CommandExecutionException {

		// reperisce il dato da serializzare
		com.opensymphony.xwork2.ActionContext ctx = com.opensymphony.xwork2.ActionContext
				.getContext();
		Object dataToProvide = ctx.getValueStack().findValue("appDatalingue");

		if (isTableClearStatus("cpDettaglioGateway_tLingue")) {
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
	 * al data-binding relativo al dataset DATASET del widget tDivise.
	 * @return sempre il result "provideData"
	 */
	@SkipValidation
	@FatClientOnly
	public String provideTDivise_DATASET() throws CommandExecutionException {

		// reperisce il dato da serializzare
		com.opensymphony.xwork2.ActionContext ctx = com.opensymphony.xwork2.ActionContext
				.getContext();
		Object dataToProvide = ctx.getValueStack().findValue("appDatadivise");

		if (isTableClearStatus("cpDettaglioGateway_tDivise")) {
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

		if (isTableClearStatus("cpDettaglioGateway_tbListaAssociazioni")) {
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
	 * al data-binding relativo al dataset DATASET del widget cbPaymentModesMp.
	 * @return sempre il result "provideData"
	 */
	@SkipValidation
	@FatClientOnly
	public String provideCbPaymentModesMp_DATASET()
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
	 * Gestione della validazione
	 */
	public void validate() {
		/*PROTECTED REGION ID(R1224013881) ENABLED START*/
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
				log.error("[CpDettaglioGatewayAction::isWidgetDisabled] errore durante verifica->disable");
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
				log.error("[CpDettaglioGatewayAction::isWidgetVisible] errore durante verifica->hide");
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
		String[] resultNames4refreshPanel = new String[]{"MODIFICA", "RESULT",
				"NORESULT"};

		ICommand[] actionsForResults4refreshPanel = new ICommand[3];
		// SequenceCommand begin
		// Screen State Command begin
		//prepara i nomi dei componenti target da abilitare/disabilitare
		String[] act_onRefresh_resmodificaActionstep_0_on = new String[]{
				"mnuView", "tfIdGateway", "tfDescrGateway",
				"tfProviderGateway", "tfServiceJNDI", "btnIndietro",
				"btnSalva", "tLingue", "tDivise", "tbListaAssociazioni",
				"btnCaricaAssociazioneSelezionataGW_MP", "cbPaymentModesMp",
				"tfDefaultPaymentUrl", "tfReturnUrl", "tfReceiptUrl",
				"tfKolUrl", "chkAbilitazione", "tfcontextpg", "tfhttpport",
				"tfmdpgatewaypage", "btnSalvaAssociazioneGW_MP",
				"tbListaAttributi", "tfIdGatewayAss", "tfDescrGatewayAss",
				"chkVerificaEsito"};

		String[] act_onRefresh_resmodificaActionstep_0_off = new String[]{"btnAggiungiAttributo"};

		String[] act_onRefresh_resmodificaActionstep_0_show = new String[]{
				"mnuView", "tfIdGateway", "tfDescrGateway",
				"tfProviderGateway", "tfServiceJNDI", "btnIndietro",
				"btnSalva", "tLingue", "tDivise", "tbListaAssociazioni",
				"btnCaricaAssociazioneSelezionataGW_MP", "cbPaymentModesMp",
				"tfDefaultPaymentUrl", "tfReturnUrl", "tfReceiptUrl",
				"tfKolUrl", "chkAbilitazione", "tfcontextpg", "tfhttpport",
				"tfmdpgatewaypage", "btnSalvaAssociazioneGW_MP",
				"tbListaAttributi", "tfIdGatewayAss", "tfDescrGatewayAss",
				"chkVerificaEsito"};

		String[] act_onRefresh_resmodificaActionstep_0_hide = new String[]{"btnAggiungiAttributo"};

		ScreenStateCommand act_onRefresh_resmodificaActionstep_0 = new ScreenStateCommand(
				"cpDettaglioGateway", "MODIFICA",
				act_onRefresh_resmodificaActionstep_0_on,
				act_onRefresh_resmodificaActionstep_0_off,
				act_onRefresh_resmodificaActionstep_0_show,
				act_onRefresh_resmodificaActionstep_0_hide);
		//Screen State Command end

		ICommand[] act_onRefresh_resmodificaAction_0_steps = new ICommand[]{act_onRefresh_resmodificaActionstep_0};
		SequenceCommand act_onRefresh_resmodificaAction_0 = new SequenceCommand(
				act_onRefresh_resmodificaAction_0_steps);
		// SequenceCommand end
		actionsForResults4refreshPanel[0] = act_onRefresh_resmodificaAction_0;
		// Screen State Command begin
		//prepara i nomi dei componenti target da abilitare/disabilitare
		String[] act_onRefresh_resresultAction_1_on = new String[]{"mnuView",
				"tfIdGateway", "tfDescrGateway", "tfProviderGateway",
				"tfServiceJNDI", "tbListaAttributi", "tLingue", "tDivise",
				"btnIndietro", "btnSalva", "tbListaAssociazioni",
				"btnCaricaAssociazioneSelezionataGW_MP", "cbPaymentModesMp",
				"tfDefaultPaymentUrl", "tfReturnUrl", "tfReceiptUrl",
				"tfKolUrl", "chkAbilitazione", "tfcontextpg", "tfhttpport",
				"tfmdpgatewaypage", "btnSalvaAssociazioneGW_MP",
				"tfIdGatewayAss", "tfDescrGatewayAss", "chkVerificaEsito"};

		String[] act_onRefresh_resresultAction_1_off = new String[]{"btnAggiungiAttributo"};

		String[] act_onRefresh_resresultAction_1_show = new String[]{"mnuView",
				"tfIdGateway", "tfDescrGateway", "tfProviderGateway",
				"tfServiceJNDI", "tbListaAttributi", "tLingue", "tDivise",
				"btnIndietro", "btnSalva", "tbListaAssociazioni",
				"btnCaricaAssociazioneSelezionataGW_MP", "cbPaymentModesMp",
				"tfDefaultPaymentUrl", "tfReturnUrl", "tfReceiptUrl",
				"tfKolUrl", "chkAbilitazione", "tfcontextpg", "tfhttpport",
				"tfmdpgatewaypage", "btnSalvaAssociazioneGW_MP",
				"tfIdGatewayAss", "tfDescrGatewayAss", "chkVerificaEsito"};

		String[] act_onRefresh_resresultAction_1_hide = new String[]{"btnAggiungiAttributo"};

		ScreenStateCommand act_onRefresh_resresultAction_1 = new ScreenStateCommand(
				"cpDettaglioGateway", "RESULT",
				act_onRefresh_resresultAction_1_on,
				act_onRefresh_resresultAction_1_off,
				act_onRefresh_resresultAction_1_show,
				act_onRefresh_resresultAction_1_hide);
		//Screen State Command end
		actionsForResults4refreshPanel[1] = act_onRefresh_resresultAction_1;
		// Screen State Command begin
		//prepara i nomi dei componenti target da abilitare/disabilitare
		String[] act_onRefresh_resnoresultAction_2_on = new String[]{"mnuView",
				"tfIdGateway", "tfDescrGateway", "tfProviderGateway",
				"tfServiceJNDI", "tLingue", "tDivise", "btnIndietro",
				"btnSalva", "tbListaAssociazioni",
				"btnCaricaAssociazioneSelezionataGW_MP", "cbPaymentModesMp",
				"tfDefaultPaymentUrl", "tfReturnUrl", "tfReceiptUrl",
				"tfKolUrl", "chkAbilitazione", "tfcontextpg", "tfhttpport",
				"tfmdpgatewaypage", "btnSalvaAssociazioneGW_MP",
				"tfIdGatewayAss", "tfDescrGatewayAss", "chkVerificaEsito"};

		String[] act_onRefresh_resnoresultAction_2_off = new String[]{
				"tbListaAttributi", "btnAggiungiAttributo"};

		String[] act_onRefresh_resnoresultAction_2_show = new String[]{
				"mnuView", "tfIdGateway", "tfDescrGateway",
				"tfProviderGateway", "tfServiceJNDI", "tLingue", "tDivise",
				"btnIndietro", "btnSalva", "tbListaAssociazioni",
				"btnCaricaAssociazioneSelezionataGW_MP", "cbPaymentModesMp",
				"tfDefaultPaymentUrl", "tfReturnUrl", "tfReceiptUrl",
				"tfKolUrl", "chkAbilitazione", "tfcontextpg", "tfhttpport",
				"tfmdpgatewaypage", "btnSalvaAssociazioneGW_MP",
				"tfIdGatewayAss", "tfDescrGatewayAss", "chkVerificaEsito"};

		String[] act_onRefresh_resnoresultAction_2_hide = new String[]{
				"tbListaAttributi", "btnAggiungiAttributo"};

		ScreenStateCommand act_onRefresh_resnoresultAction_2 = new ScreenStateCommand(
				"cpDettaglioGateway", "NO_RESULT",
				act_onRefresh_resnoresultAction_2_on,
				act_onRefresh_resnoresultAction_2_off,
				act_onRefresh_resnoresultAction_2_show,
				act_onRefresh_resnoresultAction_2_hide);
		//Screen State Command end
		actionsForResults4refreshPanel[2] = act_onRefresh_resnoresultAction_2;

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
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioGatewayModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpDettaglioGatewayAction",
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

		new DummyUISecConstraint("cpDettaglioGateway", "mnuView",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] mnuView_constraints = new UISecConstraint[]{mnuView_defaultVisib_ctr};
		UISecConstraint mnuView_ctr = new ComplexUISecConstraint(
				mnuView_constraints);
		allConstraints.put("mnuView", mnuView_ctr);

		// constraint fittizio per tfIdGateway
		UISecConstraint tfIdGateway_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioGateway", "tfIdGateway",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tfIdGateway_constraints = new UISecConstraint[]{tfIdGateway_defaultVisib_ctr};
		UISecConstraint tfIdGateway_ctr = new ComplexUISecConstraint(
				tfIdGateway_constraints);
		allConstraints.put("tfIdGateway", tfIdGateway_ctr);

		// constraint fittizio per tfDescrGateway
		UISecConstraint tfDescrGateway_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioGateway", "tfDescrGateway",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tfDescrGateway_constraints = new UISecConstraint[]{tfDescrGateway_defaultVisib_ctr};
		UISecConstraint tfDescrGateway_ctr = new ComplexUISecConstraint(
				tfDescrGateway_constraints);
		allConstraints.put("tfDescrGateway", tfDescrGateway_ctr);

		// constraint fittizio per tfProviderGateway
		UISecConstraint tfProviderGateway_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioGateway", "tfProviderGateway",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tfProviderGateway_constraints = new UISecConstraint[]{tfProviderGateway_defaultVisib_ctr};
		UISecConstraint tfProviderGateway_ctr = new ComplexUISecConstraint(
				tfProviderGateway_constraints);
		allConstraints.put("tfProviderGateway", tfProviderGateway_ctr);

		// constraint fittizio per tfServiceJNDI
		UISecConstraint tfServiceJNDI_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioGateway", "tfServiceJNDI",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tfServiceJNDI_constraints = new UISecConstraint[]{tfServiceJNDI_defaultVisib_ctr};
		UISecConstraint tfServiceJNDI_ctr = new ComplexUISecConstraint(
				tfServiceJNDI_constraints);
		allConstraints.put("tfServiceJNDI", tfServiceJNDI_ctr);

		// constraint fittizio per tbListaAttributi
		UISecConstraint tbListaAttributi_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioGateway", "tbListaAttributi",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tbListaAttributi_constraints = new UISecConstraint[]{tbListaAttributi_defaultVisib_ctr};
		UISecConstraint tbListaAttributi_ctr = new ComplexUISecConstraint(
				tbListaAttributi_constraints);
		allConstraints.put("tbListaAttributi", tbListaAttributi_ctr);

		// constraint fittizio per btnAggiungiAttributo
		UISecConstraint btnAggiungiAttributo_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioGateway", "btnAggiungiAttributo",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btnAggiungiAttributo_constraints = new UISecConstraint[]{btnAggiungiAttributo_defaultVisib_ctr};
		UISecConstraint btnAggiungiAttributo_ctr = new ComplexUISecConstraint(
				btnAggiungiAttributo_constraints);
		allConstraints.put("btnAggiungiAttributo", btnAggiungiAttributo_ctr);

		// constraint fittizio per tLingue
		UISecConstraint tLingue_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioGateway", "tLingue",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tLingue_constraints = new UISecConstraint[]{tLingue_defaultVisib_ctr};
		UISecConstraint tLingue_ctr = new ComplexUISecConstraint(
				tLingue_constraints);
		allConstraints.put("tLingue", tLingue_ctr);

		// constraint fittizio per tDivise
		UISecConstraint tDivise_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioGateway", "tDivise",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tDivise_constraints = new UISecConstraint[]{tDivise_defaultVisib_ctr};
		UISecConstraint tDivise_ctr = new ComplexUISecConstraint(
				tDivise_constraints);
		allConstraints.put("tDivise", tDivise_ctr);

		// constraint fittizio per btnIndietro
		UISecConstraint btnIndietro_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioGateway", "btnIndietro",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btnIndietro_constraints = new UISecConstraint[]{btnIndietro_defaultVisib_ctr};
		UISecConstraint btnIndietro_ctr = new ComplexUISecConstraint(
				btnIndietro_constraints);
		allConstraints.put("btnIndietro", btnIndietro_ctr);

		// constraint fittizio per btnSalva
		UISecConstraint btnSalva_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioGateway", "btnSalva",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btnSalva_constraints = new UISecConstraint[]{btnSalva_defaultVisib_ctr};
		UISecConstraint btnSalva_ctr = new ComplexUISecConstraint(
				btnSalva_constraints);
		allConstraints.put("btnSalva", btnSalva_ctr);

		// constraint fittizio per tbListaAssociazioni
		UISecConstraint tbListaAssociazioni_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioGateway", "tbListaAssociazioni",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tbListaAssociazioni_constraints = new UISecConstraint[]{tbListaAssociazioni_defaultVisib_ctr};
		UISecConstraint tbListaAssociazioni_ctr = new ComplexUISecConstraint(
				tbListaAssociazioni_constraints);
		allConstraints.put("tbListaAssociazioni", tbListaAssociazioni_ctr);

		// constraint fittizio per btnCaricaAssociazioneSelezionataGW_MP
		UISecConstraint btnCaricaAssociazioneSelezionataGW_MP_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioGateway",
				"btnCaricaAssociazioneSelezionataGW_MP",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btnCaricaAssociazioneSelezionataGW_MP_constraints = new UISecConstraint[]{btnCaricaAssociazioneSelezionataGW_MP_defaultVisib_ctr};
		UISecConstraint btnCaricaAssociazioneSelezionataGW_MP_ctr = new ComplexUISecConstraint(
				btnCaricaAssociazioneSelezionataGW_MP_constraints);
		allConstraints.put("btnCaricaAssociazioneSelezionataGW_MP",
				btnCaricaAssociazioneSelezionataGW_MP_ctr);

		// constraint fittizio per tfIdGatewayAss
		UISecConstraint tfIdGatewayAss_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioGateway", "tfIdGatewayAss",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tfIdGatewayAss_constraints = new UISecConstraint[]{tfIdGatewayAss_defaultVisib_ctr};
		UISecConstraint tfIdGatewayAss_ctr = new ComplexUISecConstraint(
				tfIdGatewayAss_constraints);
		allConstraints.put("tfIdGatewayAss", tfIdGatewayAss_ctr);

		// constraint fittizio per tfDescrGatewayAss
		UISecConstraint tfDescrGatewayAss_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioGateway", "tfDescrGatewayAss",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tfDescrGatewayAss_constraints = new UISecConstraint[]{tfDescrGatewayAss_defaultVisib_ctr};
		UISecConstraint tfDescrGatewayAss_ctr = new ComplexUISecConstraint(
				tfDescrGatewayAss_constraints);
		allConstraints.put("tfDescrGatewayAss", tfDescrGatewayAss_ctr);

		// constraint fittizio per cbPaymentModesMp
		UISecConstraint cbPaymentModesMp_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioGateway", "cbPaymentModesMp",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] cbPaymentModesMp_constraints = new UISecConstraint[]{cbPaymentModesMp_defaultVisib_ctr};
		UISecConstraint cbPaymentModesMp_ctr = new ComplexUISecConstraint(
				cbPaymentModesMp_constraints);
		allConstraints.put("cbPaymentModesMp", cbPaymentModesMp_ctr);

		// constraint fittizio per tfDefaultPaymentUrl
		UISecConstraint tfDefaultPaymentUrl_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioGateway", "tfDefaultPaymentUrl",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tfDefaultPaymentUrl_constraints = new UISecConstraint[]{tfDefaultPaymentUrl_defaultVisib_ctr};
		UISecConstraint tfDefaultPaymentUrl_ctr = new ComplexUISecConstraint(
				tfDefaultPaymentUrl_constraints);
		allConstraints.put("tfDefaultPaymentUrl", tfDefaultPaymentUrl_ctr);

		// constraint fittizio per tfReturnUrl
		UISecConstraint tfReturnUrl_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioGateway", "tfReturnUrl",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tfReturnUrl_constraints = new UISecConstraint[]{tfReturnUrl_defaultVisib_ctr};
		UISecConstraint tfReturnUrl_ctr = new ComplexUISecConstraint(
				tfReturnUrl_constraints);
		allConstraints.put("tfReturnUrl", tfReturnUrl_ctr);

		// constraint fittizio per tfReceiptUrl
		UISecConstraint tfReceiptUrl_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioGateway", "tfReceiptUrl",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tfReceiptUrl_constraints = new UISecConstraint[]{tfReceiptUrl_defaultVisib_ctr};
		UISecConstraint tfReceiptUrl_ctr = new ComplexUISecConstraint(
				tfReceiptUrl_constraints);
		allConstraints.put("tfReceiptUrl", tfReceiptUrl_ctr);

		// constraint fittizio per tfKolUrl
		UISecConstraint tfKolUrl_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioGateway", "tfKolUrl",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tfKolUrl_constraints = new UISecConstraint[]{tfKolUrl_defaultVisib_ctr};
		UISecConstraint tfKolUrl_ctr = new ComplexUISecConstraint(
				tfKolUrl_constraints);
		allConstraints.put("tfKolUrl", tfKolUrl_ctr);

		// constraint fittizio per chkAbilitazione
		UISecConstraint chkAbilitazione_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioGateway", "chkAbilitazione",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] chkAbilitazione_constraints = new UISecConstraint[]{chkAbilitazione_defaultVisib_ctr};
		UISecConstraint chkAbilitazione_ctr = new ComplexUISecConstraint(
				chkAbilitazione_constraints);
		allConstraints.put("chkAbilitazione", chkAbilitazione_ctr);

		// constraint fittizio per tfcontextpg
		UISecConstraint tfcontextpg_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioGateway", "tfcontextpg",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tfcontextpg_constraints = new UISecConstraint[]{tfcontextpg_defaultVisib_ctr};
		UISecConstraint tfcontextpg_ctr = new ComplexUISecConstraint(
				tfcontextpg_constraints);
		allConstraints.put("tfcontextpg", tfcontextpg_ctr);

		// constraint fittizio per tfhttpport
		UISecConstraint tfhttpport_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioGateway", "tfhttpport",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tfhttpport_constraints = new UISecConstraint[]{tfhttpport_defaultVisib_ctr};
		UISecConstraint tfhttpport_ctr = new ComplexUISecConstraint(
				tfhttpport_constraints);
		allConstraints.put("tfhttpport", tfhttpport_ctr);

		// constraint fittizio per tfmdpgatewaypage
		UISecConstraint tfmdpgatewaypage_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioGateway", "tfmdpgatewaypage",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tfmdpgatewaypage_constraints = new UISecConstraint[]{tfmdpgatewaypage_defaultVisib_ctr};
		UISecConstraint tfmdpgatewaypage_ctr = new ComplexUISecConstraint(
				tfmdpgatewaypage_constraints);
		allConstraints.put("tfmdpgatewaypage", tfmdpgatewaypage_ctr);

		// constraint fittizio per chkVerificaEsito
		UISecConstraint chkVerificaEsito_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioGateway", "chkVerificaEsito",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] chkVerificaEsito_constraints = new UISecConstraint[]{chkVerificaEsito_defaultVisib_ctr};
		UISecConstraint chkVerificaEsito_ctr = new ComplexUISecConstraint(
				chkVerificaEsito_constraints);
		allConstraints.put("chkVerificaEsito", chkVerificaEsito_ctr);

		// constraint fittizio per btnSalvaAssociazioneGW_MP
		UISecConstraint btnSalvaAssociazioneGW_MP_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioGateway",
				"btnSalvaAssociazioneGW_MP",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btnSalvaAssociazioneGW_MP_constraints = new UISecConstraint[]{btnSalvaAssociazioneGW_MP_defaultVisib_ctr};
		UISecConstraint btnSalvaAssociazioneGW_MP_ctr = new ComplexUISecConstraint(
				btnSalvaAssociazioneGW_MP_constraints);
		allConstraints.put("btnSalvaAssociazioneGW_MP",
				btnSalvaAssociazioneGW_MP_ctr);
		return allConstraints;
	}

	protected Map<String, UISecConstraint> getPageONOFFUIConstraints() {
		Map<String, UISecConstraint> allConstraints = new HashMap<String, UISecConstraint>();

		// constraint fittizio per mnuView
		UISecConstraint mnuView_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioGateway", "mnuView",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] mnuView_constraints = new UISecConstraint[]{mnuView_defaultOnoff_ctr};
		UISecConstraint mnuView_ctr = new ComplexUISecConstraint(
				mnuView_constraints);
		allConstraints.put("mnuView", mnuView_ctr);

		// constraint fittizio per tfIdGateway
		UISecConstraint tfIdGateway_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioGateway", "tfIdGateway",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tfIdGateway_constraints = new UISecConstraint[]{tfIdGateway_defaultOnoff_ctr};
		UISecConstraint tfIdGateway_ctr = new ComplexUISecConstraint(
				tfIdGateway_constraints);
		allConstraints.put("tfIdGateway", tfIdGateway_ctr);

		// constraint fittizio per tfDescrGateway
		UISecConstraint tfDescrGateway_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioGateway", "tfDescrGateway",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tfDescrGateway_constraints = new UISecConstraint[]{tfDescrGateway_defaultOnoff_ctr};
		UISecConstraint tfDescrGateway_ctr = new ComplexUISecConstraint(
				tfDescrGateway_constraints);
		allConstraints.put("tfDescrGateway", tfDescrGateway_ctr);

		// constraint fittizio per tfProviderGateway
		UISecConstraint tfProviderGateway_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioGateway", "tfProviderGateway",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tfProviderGateway_constraints = new UISecConstraint[]{tfProviderGateway_defaultOnoff_ctr};
		UISecConstraint tfProviderGateway_ctr = new ComplexUISecConstraint(
				tfProviderGateway_constraints);
		allConstraints.put("tfProviderGateway", tfProviderGateway_ctr);

		// constraint fittizio per tfServiceJNDI
		UISecConstraint tfServiceJNDI_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioGateway", "tfServiceJNDI",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tfServiceJNDI_constraints = new UISecConstraint[]{tfServiceJNDI_defaultOnoff_ctr};
		UISecConstraint tfServiceJNDI_ctr = new ComplexUISecConstraint(
				tfServiceJNDI_constraints);
		allConstraints.put("tfServiceJNDI", tfServiceJNDI_ctr);

		// constraint fittizio per tbListaAttributi
		UISecConstraint tbListaAttributi_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioGateway", "tbListaAttributi",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tbListaAttributi_constraints = new UISecConstraint[]{tbListaAttributi_defaultOnoff_ctr};
		UISecConstraint tbListaAttributi_ctr = new ComplexUISecConstraint(
				tbListaAttributi_constraints);
		allConstraints.put("tbListaAttributi", tbListaAttributi_ctr);

		// constraint fittizio per btnAggiungiAttributo
		UISecConstraint btnAggiungiAttributo_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioGateway", "btnAggiungiAttributo",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btnAggiungiAttributo_constraints = new UISecConstraint[]{btnAggiungiAttributo_defaultOnoff_ctr};
		UISecConstraint btnAggiungiAttributo_ctr = new ComplexUISecConstraint(
				btnAggiungiAttributo_constraints);
		allConstraints.put("btnAggiungiAttributo", btnAggiungiAttributo_ctr);

		// constraint fittizio per tLingue
		UISecConstraint tLingue_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioGateway", "tLingue",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tLingue_constraints = new UISecConstraint[]{tLingue_defaultOnoff_ctr};
		UISecConstraint tLingue_ctr = new ComplexUISecConstraint(
				tLingue_constraints);
		allConstraints.put("tLingue", tLingue_ctr);

		// constraint fittizio per tDivise
		UISecConstraint tDivise_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioGateway", "tDivise",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tDivise_constraints = new UISecConstraint[]{tDivise_defaultOnoff_ctr};
		UISecConstraint tDivise_ctr = new ComplexUISecConstraint(
				tDivise_constraints);
		allConstraints.put("tDivise", tDivise_ctr);

		// constraint fittizio per btnIndietro
		UISecConstraint btnIndietro_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioGateway", "btnIndietro",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btnIndietro_constraints = new UISecConstraint[]{btnIndietro_defaultOnoff_ctr};
		UISecConstraint btnIndietro_ctr = new ComplexUISecConstraint(
				btnIndietro_constraints);
		allConstraints.put("btnIndietro", btnIndietro_ctr);

		// constraint fittizio per btnSalva
		UISecConstraint btnSalva_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioGateway", "btnSalva",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btnSalva_constraints = new UISecConstraint[]{btnSalva_defaultOnoff_ctr};
		UISecConstraint btnSalva_ctr = new ComplexUISecConstraint(
				btnSalva_constraints);
		allConstraints.put("btnSalva", btnSalva_ctr);

		// constraint fittizio per tbListaAssociazioni
		UISecConstraint tbListaAssociazioni_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioGateway", "tbListaAssociazioni",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tbListaAssociazioni_constraints = new UISecConstraint[]{tbListaAssociazioni_defaultOnoff_ctr};
		UISecConstraint tbListaAssociazioni_ctr = new ComplexUISecConstraint(
				tbListaAssociazioni_constraints);
		allConstraints.put("tbListaAssociazioni", tbListaAssociazioni_ctr);

		// constraint fittizio per btnCaricaAssociazioneSelezionataGW_MP
		UISecConstraint btnCaricaAssociazioneSelezionataGW_MP_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioGateway",
				"btnCaricaAssociazioneSelezionataGW_MP",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btnCaricaAssociazioneSelezionataGW_MP_constraints = new UISecConstraint[]{btnCaricaAssociazioneSelezionataGW_MP_defaultOnoff_ctr};
		UISecConstraint btnCaricaAssociazioneSelezionataGW_MP_ctr = new ComplexUISecConstraint(
				btnCaricaAssociazioneSelezionataGW_MP_constraints);
		allConstraints.put("btnCaricaAssociazioneSelezionataGW_MP",
				btnCaricaAssociazioneSelezionataGW_MP_ctr);

		// constraint fittizio per tfIdGatewayAss
		UISecConstraint tfIdGatewayAss_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioGateway", "tfIdGatewayAss",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tfIdGatewayAss_constraints = new UISecConstraint[]{tfIdGatewayAss_defaultOnoff_ctr};
		UISecConstraint tfIdGatewayAss_ctr = new ComplexUISecConstraint(
				tfIdGatewayAss_constraints);
		allConstraints.put("tfIdGatewayAss", tfIdGatewayAss_ctr);

		// constraint fittizio per tfDescrGatewayAss
		UISecConstraint tfDescrGatewayAss_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioGateway", "tfDescrGatewayAss",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tfDescrGatewayAss_constraints = new UISecConstraint[]{tfDescrGatewayAss_defaultOnoff_ctr};
		UISecConstraint tfDescrGatewayAss_ctr = new ComplexUISecConstraint(
				tfDescrGatewayAss_constraints);
		allConstraints.put("tfDescrGatewayAss", tfDescrGatewayAss_ctr);

		// constraint fittizio per cbPaymentModesMp
		UISecConstraint cbPaymentModesMp_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioGateway", "cbPaymentModesMp",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] cbPaymentModesMp_constraints = new UISecConstraint[]{cbPaymentModesMp_defaultOnoff_ctr};
		UISecConstraint cbPaymentModesMp_ctr = new ComplexUISecConstraint(
				cbPaymentModesMp_constraints);
		allConstraints.put("cbPaymentModesMp", cbPaymentModesMp_ctr);

		// constraint fittizio per tfDefaultPaymentUrl
		UISecConstraint tfDefaultPaymentUrl_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioGateway", "tfDefaultPaymentUrl",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tfDefaultPaymentUrl_constraints = new UISecConstraint[]{tfDefaultPaymentUrl_defaultOnoff_ctr};
		UISecConstraint tfDefaultPaymentUrl_ctr = new ComplexUISecConstraint(
				tfDefaultPaymentUrl_constraints);
		allConstraints.put("tfDefaultPaymentUrl", tfDefaultPaymentUrl_ctr);

		// constraint fittizio per tfReturnUrl
		UISecConstraint tfReturnUrl_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioGateway", "tfReturnUrl",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tfReturnUrl_constraints = new UISecConstraint[]{tfReturnUrl_defaultOnoff_ctr};
		UISecConstraint tfReturnUrl_ctr = new ComplexUISecConstraint(
				tfReturnUrl_constraints);
		allConstraints.put("tfReturnUrl", tfReturnUrl_ctr);

		// constraint fittizio per tfReceiptUrl
		UISecConstraint tfReceiptUrl_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioGateway", "tfReceiptUrl",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tfReceiptUrl_constraints = new UISecConstraint[]{tfReceiptUrl_defaultOnoff_ctr};
		UISecConstraint tfReceiptUrl_ctr = new ComplexUISecConstraint(
				tfReceiptUrl_constraints);
		allConstraints.put("tfReceiptUrl", tfReceiptUrl_ctr);

		// constraint fittizio per tfKolUrl
		UISecConstraint tfKolUrl_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioGateway", "tfKolUrl",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tfKolUrl_constraints = new UISecConstraint[]{tfKolUrl_defaultOnoff_ctr};
		UISecConstraint tfKolUrl_ctr = new ComplexUISecConstraint(
				tfKolUrl_constraints);
		allConstraints.put("tfKolUrl", tfKolUrl_ctr);

		// constraint fittizio per chkAbilitazione
		UISecConstraint chkAbilitazione_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioGateway", "chkAbilitazione",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] chkAbilitazione_constraints = new UISecConstraint[]{chkAbilitazione_defaultOnoff_ctr};
		UISecConstraint chkAbilitazione_ctr = new ComplexUISecConstraint(
				chkAbilitazione_constraints);
		allConstraints.put("chkAbilitazione", chkAbilitazione_ctr);

		// constraint fittizio per tfcontextpg
		UISecConstraint tfcontextpg_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioGateway", "tfcontextpg",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tfcontextpg_constraints = new UISecConstraint[]{tfcontextpg_defaultOnoff_ctr};
		UISecConstraint tfcontextpg_ctr = new ComplexUISecConstraint(
				tfcontextpg_constraints);
		allConstraints.put("tfcontextpg", tfcontextpg_ctr);

		// constraint fittizio per tfhttpport
		UISecConstraint tfhttpport_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioGateway", "tfhttpport",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tfhttpport_constraints = new UISecConstraint[]{tfhttpport_defaultOnoff_ctr};
		UISecConstraint tfhttpport_ctr = new ComplexUISecConstraint(
				tfhttpport_constraints);
		allConstraints.put("tfhttpport", tfhttpport_ctr);

		// constraint fittizio per tfmdpgatewaypage
		UISecConstraint tfmdpgatewaypage_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioGateway", "tfmdpgatewaypage",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tfmdpgatewaypage_constraints = new UISecConstraint[]{tfmdpgatewaypage_defaultOnoff_ctr};
		UISecConstraint tfmdpgatewaypage_ctr = new ComplexUISecConstraint(
				tfmdpgatewaypage_constraints);
		allConstraints.put("tfmdpgatewaypage", tfmdpgatewaypage_ctr);

		// constraint fittizio per chkVerificaEsito
		UISecConstraint chkVerificaEsito_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioGateway", "chkVerificaEsito",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] chkVerificaEsito_constraints = new UISecConstraint[]{chkVerificaEsito_defaultOnoff_ctr};
		UISecConstraint chkVerificaEsito_ctr = new ComplexUISecConstraint(
				chkVerificaEsito_constraints);
		allConstraints.put("chkVerificaEsito", chkVerificaEsito_ctr);

		// constraint fittizio per btnSalvaAssociazioneGW_MP
		UISecConstraint btnSalvaAssociazioneGW_MP_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioGateway",
				"btnSalvaAssociazioneGW_MP",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btnSalvaAssociazioneGW_MP_constraints = new UISecConstraint[]{btnSalvaAssociazioneGW_MP_defaultOnoff_ctr};
		UISecConstraint btnSalvaAssociazioneGW_MP_ctr = new ComplexUISecConstraint(
				btnSalvaAssociazioneGW_MP_constraints);
		allConstraints.put("btnSalvaAssociazioneGW_MP",
				btnSalvaAssociazioneGW_MP_ctr);
		return allConstraints;
	}

	/**  */
	void dumpModel(boolean pre) {
		log.debug("[CpDettaglioGatewayAction::dumpmodel] START");

		log.debug("[CpDettaglioGatewayAction::dumpmodel] #### DUMP del model della action "
				+ this.getClass()
				+ (pre ? " prima dell'azione" : " dopo l'azione"));
		log.debug("[CpDettaglioGatewayAction::dumpmodel] [a] campi pubblici del model");
		try {
			java.beans.BeanInfo bi = java.beans.Introspector.getBeanInfo(this
					.getClass());
			java.beans.PropertyDescriptor[] props = bi.getPropertyDescriptors();
			for (int i = 0; i < props.length; i++) {
				java.beans.PropertyDescriptor pd = props[i];
				java.lang.reflect.Method m = pd.getReadMethod();
				if (m != null) {
					Object pval = m.invoke(this, new Object[]{});
					log.debug("[CpDettaglioGatewayAction::dumpmodel] "
							+ pd.getName() + ":" + pval);
				}
			}
		} catch (IllegalAccessException e) {
			log.error("[CpDettaglioGatewayAction::dumpmodel] Errore nel dump"
					+ e + ", ignoro");
		} catch (InvocationTargetException e) {
			log.error("[CpDettaglioGatewayAction::dumpmodel] Errore nel dump"
					+ e + ", ignoro");
		} catch (IntrospectionException e) {
			log.error("[CpDettaglioGatewayAction::dumpmodel] Errore nel dump"
					+ e + ", ignoro");
		}
		log.debug("[CpDettaglioGatewayAction::dumpmodel] [b] stato dei widget");
		Object cpWidgetStatus = session.get("cpDettaglioGateway");
		log.debug("[CpDettaglioGatewayAction::dumpmodel] " + cpWidgetStatus);
		log.debug("[CpDettaglioGatewayAction::dumpmodel] [c] sessione");
		log.debug("[CpDettaglioGatewayAction::dumpmodel] " + session);
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

		// contiene i comandi del widget btnAggiungiAttributo per ogni Ev.H.
		HashMap<String, ICommand> cmds4btnAggiungiAttributoByEvh = new HashMap<String, ICommand>();

		cmds4btnAggiungiAttributoByEvh.put("CLICKED",
				initCommandBtnAggiungiAttributo_CLICKED());
		cmdsByWidget
				.put("btnAggiungiAttributo", cmds4btnAggiungiAttributoByEvh);
		// contiene i comandi del widget btnIndietro per ogni Ev.H.
		HashMap<String, ICommand> cmds4btnIndietroByEvh = new HashMap<String, ICommand>();

		cmds4btnIndietroByEvh.put("CLICKED", initCommandBtnIndietro_CLICKED());
		cmdsByWidget.put("btnIndietro", cmds4btnIndietroByEvh);
		// contiene i comandi del widget btnSalva per ogni Ev.H.
		HashMap<String, ICommand> cmds4btnSalvaByEvh = new HashMap<String, ICommand>();

		cmds4btnSalvaByEvh.put("CLICKED", initCommandBtnSalva_CLICKED());
		cmdsByWidget.put("btnSalva", cmds4btnSalvaByEvh);
		// contiene i comandi del widget btnCaricaAssociazioneSelezionataGW_MP per ogni Ev.H.
		HashMap<String, ICommand> cmds4btnCaricaAssociazioneSelezionataGW_MPByEvh = new HashMap<String, ICommand>();

		cmds4btnCaricaAssociazioneSelezionataGW_MPByEvh.put("CLICKED",
				initCommandBtnCaricaAssociazioneSelezionataGW_MP_CLICKED());
		cmdsByWidget.put("btnCaricaAssociazioneSelezionataGW_MP",
				cmds4btnCaricaAssociazioneSelezionataGW_MPByEvh);
		// contiene i comandi del widget btnSalvaAssociazioneGW_MP per ogni Ev.H.
		HashMap<String, ICommand> cmds4btnSalvaAssociazioneGW_MPByEvh = new HashMap<String, ICommand>();

		cmds4btnSalvaAssociazioneGW_MPByEvh.put("CLICKED",
				initCommandBtnSalvaAssociazioneGW_MP_CLICKED());
		cmdsByWidget.put("btnSalvaAssociazioneGW_MP",
				cmds4btnSalvaAssociazioneGW_MPByEvh);

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

	private ICommand initCommandBtnAggiungiAttributo_CLICKED() {
		// ExecCommand begin
		String[] resultNames4aggiungiAttributo = new String[]{"Ok"};

		ICommand[] actionsForResults4aggiungiAttributo = new ICommand[1];
		// SequenceCommand begin
		// Screen State Command begin
		//prepara i nomi dei componenti target da abilitare/disabilitare
		String[] act_actions_clicked_btnAggiungiAttributo_resokActionstep_0_on = new String[]{
				"mnuView", "tfIdGateway", "tfDescrGateway",
				"tfProviderGateway", "tfServiceJNDI", "tbListaAttributi",
				"txtNome", "txtDescrizione", "btnEliminaAttributo",
				"btnAggiungiAttributo", "btnIndietro", "btnSalva"};

		String[] act_actions_clicked_btnAggiungiAttributo_resokActionstep_0_off = new String[]{
				"mnuView", "tfIdGateway", "tfDescrGateway",
				"tfProviderGateway", "tfServiceJNDI", "tbListaAttributi",
				"btnAggiungiAttributo", "tLingue", "tDivise", "btnIndietro",
				"btnSalva", "tbListaAssociazioni",
				"btnCaricaAssociazioneSelezionataGW_MP", "tfIdGatewayAss",
				"tfDescrGatewayAss", "cbPaymentModesMp", "tfDefaultPaymentUrl",
				"tfReturnUrl", "tfReceiptUrl", "tfKolUrl", "chkAbilitazione",
				"tfcontextpg", "tfhttpport", "tfmdpgatewaypage",
				"chkVerificaEsito", "btnSalvaAssociazioneGW_MP"};

		String[] act_actions_clicked_btnAggiungiAttributo_resokActionstep_0_show = new String[]{
				"mnuView", "tfIdGateway", "tfDescrGateway",
				"tfProviderGateway", "tfServiceJNDI", "tbListaAttributi",
				"txtNome", "txtDescrizione", "btnEliminaAttributo",
				"btnAggiungiAttributo", "btnIndietro", "btnSalva"};

		String[] act_actions_clicked_btnAggiungiAttributo_resokActionstep_0_hide = new String[]{
				"mnuView", "tfIdGateway", "tfDescrGateway",
				"tfProviderGateway", "tfServiceJNDI", "tbListaAttributi",
				"btnAggiungiAttributo", "tLingue", "tDivise", "btnIndietro",
				"btnSalva", "tbListaAssociazioni",
				"btnCaricaAssociazioneSelezionataGW_MP", "tfIdGatewayAss",
				"tfDescrGatewayAss", "cbPaymentModesMp", "tfDefaultPaymentUrl",
				"tfReturnUrl", "tfReceiptUrl", "tfKolUrl", "chkAbilitazione",
				"tfcontextpg", "tfhttpport", "tfmdpgatewaypage",
				"chkVerificaEsito", "btnSalvaAssociazioneGW_MP"};

		ScreenStateCommand act_actions_clicked_btnAggiungiAttributo_resokActionstep_0 = new ScreenStateCommand(
				"cpDettaglioGateway",
				"RESULT",
				act_actions_clicked_btnAggiungiAttributo_resokActionstep_0_on,
				act_actions_clicked_btnAggiungiAttributo_resokActionstep_0_off,
				act_actions_clicked_btnAggiungiAttributo_resokActionstep_0_show,
				act_actions_clicked_btnAggiungiAttributo_resokActionstep_0_hide);
		//Screen State Command end

		ICommand[] act_actions_clicked_btnAggiungiAttributo_resokAction_0_steps = new ICommand[]{act_actions_clicked_btnAggiungiAttributo_resokActionstep_0};
		SequenceCommand act_actions_clicked_btnAggiungiAttributo_resokAction_0 = new SequenceCommand(
				act_actions_clicked_btnAggiungiAttributo_resokAction_0_steps);
		// SequenceCommand end
		actionsForResults4aggiungiAttributo[0] = act_actions_clicked_btnAggiungiAttributo_resokAction_0;

		ExecCommand act_actions_clicked_btnAggiungiAttributo_1 = new ExecCommand(
				resultNames4aggiungiAttributo,
				actionsForResults4aggiungiAttributo) {
			public ExecResults doLogic(Object theModel)
					throws CommandExecutionException {
				it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch(
						it.csi.mdp.mdpboweb.util.Constants.APPLICATION_CODE);
				ExecResults result = null;
				try {
					watcher.start();
					result = getSpringBackEnd()
							.aggiungiAttributo(
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioGatewayModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpDettaglioGatewayAction",
							"readOne()", "chiamata verso BackEnd",
							"aggiungiAttributo");
					return result;
				} catch (BEException e) {
					throw new CommandExecutionException(
							"errore non gestito nell'esecuzione del metodo [aggiungiAttributo]:"
									+ e.getMessage(), e);
				}

			}

			public void storeAppData(ExecResults res, BaseAction strutsAction) {
				// nothing to store...
			}
		};
		// Exec Action end
		return act_actions_clicked_btnAggiungiAttributo_1;
	}

	private ICommand initCommandBtnIndietro_CLICKED() {
		// ExecCommand begin
		String[] resultNames4indietro = new String[]{"Ok"};

		ICommand[] actionsForResults4indietro = new ICommand[1];
		// SequenceCommand begin
		/// Jump Command begin
		JumpCommand act_actions_clicked_btnIndietro_resokActionstep_0 = new JumpCommand(
				"cpGestioneGW_PM", null, false);
		/// Jump Command end

		ICommand[] act_actions_clicked_btnIndietro_resokAction_0_steps = new ICommand[]{act_actions_clicked_btnIndietro_resokActionstep_0};
		SequenceCommand act_actions_clicked_btnIndietro_resokAction_0 = new SequenceCommand(
				act_actions_clicked_btnIndietro_resokAction_0_steps);
		// SequenceCommand end
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
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioGatewayModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpDettaglioGatewayAction",
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

	private ICommand initCommandBtnSalva_CLICKED() {
		// ExecCommand begin
		String[] resultNames4salvaGateway = new String[]{"Ok", "Ko"};

		ICommand[] actionsForResults4salvaGateway = new ICommand[2];
		// SequenceCommand begin
		/// Clear AppData Command begin
		String[] act_actions_clicked_btnSalva_resokActionstep_0_appdataToBeRemovedFromSession = new String[]{"isPostBack"};

		ClearAppDataCommand act_actions_clicked_btnSalva_resokActionstep_0 = new ClearAppDataCommand(
				act_actions_clicked_btnSalva_resokActionstep_0_appdataToBeRemovedFromSession);

		/// Clear AppData Command end
		/// Jump Command begin
		JumpCommand act_actions_clicked_btnSalva_resokActionstep_1 = new JumpCommand(
				"cpGestioneGW_PM", null, false);
		/// Jump Command end

		ICommand[] act_actions_clicked_btnSalva_resokAction_0_steps = new ICommand[]{
				act_actions_clicked_btnSalva_resokActionstep_0,
				act_actions_clicked_btnSalva_resokActionstep_1};
		SequenceCommand act_actions_clicked_btnSalva_resokAction_0 = new SequenceCommand(
				act_actions_clicked_btnSalva_resokAction_0_steps);
		// SequenceCommand end
		actionsForResults4salvaGateway[0] = act_actions_clicked_btnSalva_resokAction_0;
		// SequenceCommand begin
		/// NOP Command begin
		NOPCommand act_actions_clicked_btnSalva_reskoActionstep_0 = new NOPCommand();
		/// NOP Command end

		ICommand[] act_actions_clicked_btnSalva_reskoAction_1_steps = new ICommand[]{act_actions_clicked_btnSalva_reskoActionstep_0};
		SequenceCommand act_actions_clicked_btnSalva_reskoAction_1 = new SequenceCommand(
				act_actions_clicked_btnSalva_reskoAction_1_steps);
		// SequenceCommand end
		actionsForResults4salvaGateway[1] = act_actions_clicked_btnSalva_reskoAction_1;

		ExecCommand act_actions_clicked_btnSalva_1 = new ExecCommand(
				resultNames4salvaGateway, actionsForResults4salvaGateway) {
			public ExecResults doLogic(Object theModel)
					throws CommandExecutionException {
				it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch(
						it.csi.mdp.mdpboweb.util.Constants.APPLICATION_CODE);
				ExecResults result = null;
				try {
					watcher.start();
					result = getSpringBackEnd()
							.salvaGateway(
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioGatewayModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpDettaglioGatewayAction",
							"readOne()", "chiamata verso BackEnd",
							"salvaGateway");
					return result;
				} catch (BEException e) {
					throw new CommandExecutionException(
							"errore non gestito nell'esecuzione del metodo [salvaGateway]:"
									+ e.getMessage(), e);
				}

			}

			public void storeAppData(ExecResults res, BaseAction strutsAction) {
				// nothing to store...
			}
		};
		// Exec Action end
		return act_actions_clicked_btnSalva_1;
	}

	private ICommand initCommandBtnCaricaAssociazioneSelezionataGW_MP_CLICKED() {
		// ExecCommand begin
		String[] resultNames4CaricaAssociazioneSelezionataGW_MP = new String[]{
				"Ok", "KO"};

		ICommand[] actionsForResults4CaricaAssociazioneSelezionataGW_MP = new ICommand[2];
		// SequenceCommand begin
		/// NOP Command begin
		NOPCommand act_actions_clicked_btnCaricaAssociazioneSelezionataGW_MP_resokActionstep_0 = new NOPCommand();
		/// NOP Command end

		ICommand[] act_actions_clicked_btnCaricaAssociazioneSelezionataGW_MP_resokAction_0_steps = new ICommand[]{act_actions_clicked_btnCaricaAssociazioneSelezionataGW_MP_resokActionstep_0};
		SequenceCommand act_actions_clicked_btnCaricaAssociazioneSelezionataGW_MP_resokAction_0 = new SequenceCommand(
				act_actions_clicked_btnCaricaAssociazioneSelezionataGW_MP_resokAction_0_steps);
		// SequenceCommand end
		actionsForResults4CaricaAssociazioneSelezionataGW_MP[0] = act_actions_clicked_btnCaricaAssociazioneSelezionataGW_MP_resokAction_0;
		/// NOP Command begin
		NOPCommand act_actions_clicked_btnCaricaAssociazioneSelezionataGW_MP_reskoAction_1 = new NOPCommand();
		/// NOP Command end
		actionsForResults4CaricaAssociazioneSelezionataGW_MP[1] = act_actions_clicked_btnCaricaAssociazioneSelezionataGW_MP_reskoAction_1;

		ExecCommand act_actions_clicked_btnCaricaAssociazioneSelezionataGW_MP_1 = new ExecCommand(
				resultNames4CaricaAssociazioneSelezionataGW_MP,
				actionsForResults4CaricaAssociazioneSelezionataGW_MP) {
			public ExecResults doLogic(Object theModel)
					throws CommandExecutionException {
				it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch(
						it.csi.mdp.mdpboweb.util.Constants.APPLICATION_CODE);
				ExecResults result = null;
				try {
					watcher.start();
					result = getSpringBackEnd()
							.CaricaAssociazioneSelezionataGW_MP(
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioGatewayModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpDettaglioGatewayAction",
							"readOne()", "chiamata verso BackEnd",
							"CaricaAssociazioneSelezionataGW_MP");
					return result;
				} catch (BEException e) {
					throw new CommandExecutionException(
							"errore non gestito nell'esecuzione del metodo [CaricaAssociazioneSelezionataGW_MP]:"
									+ e.getMessage(), e);
				}

			}

			public void storeAppData(ExecResults res, BaseAction strutsAction) {
				// nothing to store...
			}
		};
		// Exec Action end
		return act_actions_clicked_btnCaricaAssociazioneSelezionataGW_MP_1;
	}

	private ICommand initCommandBtnSalvaAssociazioneGW_MP_CLICKED() {
		// ExecCommand begin
		String[] resultNames4salvaAssociazioneGW_MP = new String[]{"Ok", "Ko"};

		ICommand[] actionsForResults4salvaAssociazioneGW_MP = new ICommand[2];
		// SequenceCommand begin
		/// Clear AppData Command begin
		String[] act_actions_clicked_btnSalvaAssociazioneGW_MP_resokActionstep_0_appdataToBeRemovedFromSession = new String[]{"isPostBack"};

		ClearAppDataCommand act_actions_clicked_btnSalvaAssociazioneGW_MP_resokActionstep_0 = new ClearAppDataCommand(
				act_actions_clicked_btnSalvaAssociazioneGW_MP_resokActionstep_0_appdataToBeRemovedFromSession);

		/// Clear AppData Command end

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
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioGatewayModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpDettaglioGatewayAction",
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
