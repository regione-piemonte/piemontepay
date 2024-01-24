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
 * CpGestioneGW_PMAction Action Class.
 *
 * @author GuiGen
 */
@Validation()
@Conversion()
public class CpGestioneGW_PMAction extends BaseAction implements Preparable {

	public void setAppDataselettoreIdPaymentMode(java.lang.String value) {
		getSession().put("appDataselettoreIdPaymentMode", value);
	}

	public java.lang.String getAppDataselettoreIdPaymentMode() {
		return (java.lang.String) (getSession()
				.get("appDataselettoreIdPaymentMode"));
	}

	public void setAppDataselettoreIdGateway(java.lang.String value) {
		getSession().put("appDataselettoreIdGateway", value);
	}

	public java.lang.String getAppDataselettoreIdGateway() {
		return (java.lang.String) (getSession()
				.get("appDataselettoreIdGateway"));
	}

	public void setAppDataselettoreOperazione(java.lang.String value) {
		getSession().put("appDataselettoreOperazione", value);
	}

	public java.lang.String getAppDataselettoreOperazione() {
		return (java.lang.String) (getSession()
				.get("appDataselettoreOperazione"));
	}

	public void setAppDatacurrentUser(
			it.csi.mdp.mdpboweb.dto.common.UserInfo value) {
		getSession().put("appDatacurrentUser", value);
	}

	public it.csi.mdp.mdpboweb.dto.common.UserInfo getAppDatacurrentUser() {
		return (it.csi.mdp.mdpboweb.dto.common.UserInfo) (getSession()
				.get("appDatacurrentUser"));
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

	public void setAppDataselettoreItemAssociazioneGW_MP(java.lang.String value) {
		getSession().put("appDataselettoreItemAssociazioneGW_MP", value);
	}

	public java.lang.String getAppDataselettoreItemAssociazioneGW_MP() {
		return (java.lang.String) (getSession()
				.get("appDataselettoreItemAssociazioneGW_MP"));
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

	public void setAppDataselettoreIdGateway2(java.lang.String value) {
		getSession().put("appDataselettoreIdGateway2", value);
	}

	public java.lang.String getAppDataselettoreIdGateway2() {
		return (java.lang.String) (getSession()
				.get("appDataselettoreIdGateway2"));
	}

	public void setAppDatalingue(
			java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Lingua> value) {
		getSession().put("appDatalingue", value);
	}

	public java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Lingua> getAppDatalingue() {
		return (java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Lingua>) (getSession()
				.get("appDatalingue"));
	}

	public void setAppDatalingueClone(
			java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Lingua> value) {
		getSession().put("appDatalingueClone", value);
	}

	public java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Lingua> getAppDatalingueClone() {
		return (java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.Lingua>) (getSession()
				.get("appDatalingueClone"));
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

	public void setAppDataextraAttributesNuovoGateway(
			java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.ExtraAttribute> value) {
		getSession().put("appDataextraAttributesNuovoGateway", value);
	}

	public java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.ExtraAttribute> getAppDataextraAttributesNuovoGateway() {
		return (java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.ExtraAttribute>) (getSession()
				.get("appDataextraAttributesNuovoGateway"));
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
		return it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneGW_PMModel.class;
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
	 * Gestione dell'evento CLICKED sul widget [btnNuovoGateway]
	 */
	@MethodProtection(level = "REJECT_SAME")
	public String handleBtnNuovoGateway_CLICKED()
			throws CommandExecutionException {
		ICommand action = initCommand("btnNuovoGateway", "CLICKED");
		if (log.isDebugEnabled()) {
			log.debug("[CpGestioneGW_PMAction::handleBtnNuovoGateway_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpGestioneGW_PMAction::handleBtnNuovoGateway_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpGestioneGW_PMAction::handleBtnNuovoGateway_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpGestioneGW_PMAction::handleBtnNuovoGateway_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpGestioneGW_PMAction::handleBtnNuovoGateway_CLICKED] returning default result [SUCCESS]");
			}
			return SUCCESS;

		}
	}

	/**
	 * Gestione dell'evento CLICKED sul widget [btnClonaGateway]
	 */
	@MethodProtection(level = "REJECT_SAME")
	public String handleBtnClonaGateway_CLICKED()
			throws CommandExecutionException {
		ICommand action = initCommand("btnClonaGateway", "CLICKED");
		if (log.isDebugEnabled()) {
			log.debug("[CpGestioneGW_PMAction::handleBtnClonaGateway_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpGestioneGW_PMAction::handleBtnClonaGateway_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpGestioneGW_PMAction::handleBtnClonaGateway_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpGestioneGW_PMAction::handleBtnClonaGateway_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpGestioneGW_PMAction::handleBtnClonaGateway_CLICKED] returning default result [SUCCESS]");
			}
			return SUCCESS;

		}
	}

	/**
	 * Gestione dell'evento CLICKED sul widget [btnModificaGateway]
	 */
	@MethodProtection(level = "REJECT_SAME")
	public String handleBtnModificaGateway_CLICKED()
			throws CommandExecutionException {
		ICommand action = initCommand("btnModificaGateway", "CLICKED");
		if (log.isDebugEnabled()) {
			log.debug("[CpGestioneGW_PMAction::handleBtnModificaGateway_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpGestioneGW_PMAction::handleBtnModificaGateway_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpGestioneGW_PMAction::handleBtnModificaGateway_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpGestioneGW_PMAction::handleBtnModificaGateway_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpGestioneGW_PMAction::handleBtnModificaGateway_CLICKED] returning default result [SUCCESS]");
			}
			return SUCCESS;

		}
	}

	/**
	 * Gestione dell'evento CLICKED sul widget [btnCancellaGateway]
	 */
	@MethodProtection(level = "REJECT_SAME")
	public String handleBtnCancellaGateway_CLICKED()
			throws CommandExecutionException {
		ICommand action = initCommand("btnCancellaGateway", "CLICKED");
		if (log.isDebugEnabled()) {
			log.debug("[CpGestioneGW_PMAction::handleBtnCancellaGateway_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpGestioneGW_PMAction::handleBtnCancellaGateway_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpGestioneGW_PMAction::handleBtnCancellaGateway_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpGestioneGW_PMAction::handleBtnCancellaGateway_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpGestioneGW_PMAction::handleBtnCancellaGateway_CLICKED] returning default result [SUCCESS]");
			}
			return SUCCESS;

		}
	}

	/**
	 * Gestione dell'evento CLICKED sul widget [btnNuovoPaymentMode]
	 */
	@MethodProtection(level = "REJECT_SAME")
	public String handleBtnNuovoPaymentMode_CLICKED()
			throws CommandExecutionException {
		ICommand action = initCommand("btnNuovoPaymentMode", "CLICKED");
		if (log.isDebugEnabled()) {
			log.debug("[CpGestioneGW_PMAction::handleBtnNuovoPaymentMode_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpGestioneGW_PMAction::handleBtnNuovoPaymentMode_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpGestioneGW_PMAction::handleBtnNuovoPaymentMode_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpGestioneGW_PMAction::handleBtnNuovoPaymentMode_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpGestioneGW_PMAction::handleBtnNuovoPaymentMode_CLICKED] returning default result [SUCCESS]");
			}
			return SUCCESS;

		}
	}

	/**
	 * Gestione dell'evento CLICKED sul widget [btnModificaPaymentMode]
	 */
	@MethodProtection(level = "REJECT_SAME")
	public String handleBtnModificaPaymentMode_CLICKED()
			throws CommandExecutionException {
		ICommand action = initCommand("btnModificaPaymentMode", "CLICKED");
		if (log.isDebugEnabled()) {
			log.debug("[CpGestioneGW_PMAction::handleBtnModificaPaymentMode_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpGestioneGW_PMAction::handleBtnModificaPaymentMode_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpGestioneGW_PMAction::handleBtnModificaPaymentMode_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpGestioneGW_PMAction::handleBtnModificaPaymentMode_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpGestioneGW_PMAction::handleBtnModificaPaymentMode_CLICKED] returning default result [SUCCESS]");
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
	 * al data-binding relativo al dataset DATASET del widget tbListaGateways.
	 * @return sempre il result "provideData"
	 */
	@SkipValidation
	@FatClientOnly
	public String provideTbListaGateways_DATASET()
			throws CommandExecutionException {

		// reperisce il dato da serializzare
		com.opensymphony.xwork2.ActionContext ctx = com.opensymphony.xwork2.ActionContext
				.getContext();
		Object dataToProvide = ctx.getValueStack().findValue("appDatagateways");

		if (isTableClearStatus("cpGestioneGW_PM_tbListaGateways")) {
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
	 * al data-binding relativo al dataset DATASET del widget tbListaPaymentModes.
	 * @return sempre il result "provideData"
	 */
	@SkipValidation
	@FatClientOnly
	public String provideTbListaPaymentModes_DATASET()
			throws CommandExecutionException {

		// reperisce il dato da serializzare
		com.opensymphony.xwork2.ActionContext ctx = com.opensymphony.xwork2.ActionContext
				.getContext();
		Object dataToProvide = ctx.getValueStack().findValue(
				"appDatapaymentModes");

		if (isTableClearStatus("cpGestioneGW_PM_tbListaPaymentModes")) {
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
		/*PROTECTED REGION ID(R1854911100) ENABLED START*/
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
				log.error("[CpGestioneGW_PMAction::isWidgetDisabled] errore durante verifica->disable");
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
				log.error("[CpGestioneGW_PMAction::isWidgetVisible] errore durante verifica->hide");
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
		String[] resultNames4refreshPanel = new String[]{"Ok", "Ko"};

		ICommand[] actionsForResults4refreshPanel = new ICommand[2];
		/// NOP Command begin
		NOPCommand act_onRefresh_resokAction_0 = new NOPCommand();
		/// NOP Command end
		actionsForResults4refreshPanel[0] = act_onRefresh_resokAction_0;
		/// NOP Command begin
		NOPCommand act_onRefresh_reskoAction_1 = new NOPCommand();
		/// NOP Command end
		actionsForResults4refreshPanel[1] = act_onRefresh_reskoAction_1;

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
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneGW_PMModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpGestioneGW_PMAction", "readOne()",
							"chiamata verso BackEnd", "refreshPanel");
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

		new DummyUISecConstraint("cpGestioneGW_PM", "mnuView",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] mnuView_constraints = new UISecConstraint[]{mnuView_defaultVisib_ctr};
		UISecConstraint mnuView_ctr = new ComplexUISecConstraint(
				mnuView_constraints);
		allConstraints.put("mnuView", mnuView_ctr);

		// constraint fittizio per tbListaGateways
		UISecConstraint tbListaGateways_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneGW_PM", "tbListaGateways",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tbListaGateways_constraints = new UISecConstraint[]{tbListaGateways_defaultVisib_ctr};
		UISecConstraint tbListaGateways_ctr = new ComplexUISecConstraint(
				tbListaGateways_constraints);
		allConstraints.put("tbListaGateways", tbListaGateways_ctr);

		// constraint fittizio per btnNuovoGateway
		UISecConstraint btnNuovoGateway_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneGW_PM", "btnNuovoGateway",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btnNuovoGateway_constraints = new UISecConstraint[]{btnNuovoGateway_defaultVisib_ctr};
		UISecConstraint btnNuovoGateway_ctr = new ComplexUISecConstraint(
				btnNuovoGateway_constraints);
		allConstraints.put("btnNuovoGateway", btnNuovoGateway_ctr);

		// constraint fittizio per btnClonaGateway
		UISecConstraint btnClonaGateway_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneGW_PM", "btnClonaGateway",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btnClonaGateway_constraints = new UISecConstraint[]{btnClonaGateway_defaultVisib_ctr};
		UISecConstraint btnClonaGateway_ctr = new ComplexUISecConstraint(
				btnClonaGateway_constraints);
		allConstraints.put("btnClonaGateway", btnClonaGateway_ctr);

		// constraint fittizio per btnModificaGateway
		UISecConstraint btnModificaGateway_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneGW_PM", "btnModificaGateway",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btnModificaGateway_constraints = new UISecConstraint[]{btnModificaGateway_defaultVisib_ctr};
		UISecConstraint btnModificaGateway_ctr = new ComplexUISecConstraint(
				btnModificaGateway_constraints);
		allConstraints.put("btnModificaGateway", btnModificaGateway_ctr);

		// constraint fittizio per btnCancellaGateway
		UISecConstraint btnCancellaGateway_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneGW_PM", "btnCancellaGateway",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btnCancellaGateway_constraints = new UISecConstraint[]{btnCancellaGateway_defaultVisib_ctr};
		UISecConstraint btnCancellaGateway_ctr = new ComplexUISecConstraint(
				btnCancellaGateway_constraints);
		allConstraints.put("btnCancellaGateway", btnCancellaGateway_ctr);

		// constraint fittizio per tbListaPaymentModes
		UISecConstraint tbListaPaymentModes_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneGW_PM", "tbListaPaymentModes",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tbListaPaymentModes_constraints = new UISecConstraint[]{tbListaPaymentModes_defaultVisib_ctr};
		UISecConstraint tbListaPaymentModes_ctr = new ComplexUISecConstraint(
				tbListaPaymentModes_constraints);
		allConstraints.put("tbListaPaymentModes", tbListaPaymentModes_ctr);

		// constraint fittizio per btnNuovoPaymentMode
		UISecConstraint btnNuovoPaymentMode_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneGW_PM", "btnNuovoPaymentMode",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btnNuovoPaymentMode_constraints = new UISecConstraint[]{btnNuovoPaymentMode_defaultVisib_ctr};
		UISecConstraint btnNuovoPaymentMode_ctr = new ComplexUISecConstraint(
				btnNuovoPaymentMode_constraints);
		allConstraints.put("btnNuovoPaymentMode", btnNuovoPaymentMode_ctr);

		// constraint fittizio per btnModificaPaymentMode
		UISecConstraint btnModificaPaymentMode_defaultVisib_ctr =

		new DummyUISecConstraint("cpGestioneGW_PM", "btnModificaPaymentMode",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btnModificaPaymentMode_constraints = new UISecConstraint[]{btnModificaPaymentMode_defaultVisib_ctr};
		UISecConstraint btnModificaPaymentMode_ctr = new ComplexUISecConstraint(
				btnModificaPaymentMode_constraints);
		allConstraints
				.put("btnModificaPaymentMode", btnModificaPaymentMode_ctr);
		return allConstraints;
	}

	protected Map<String, UISecConstraint> getPageONOFFUIConstraints() {
		Map<String, UISecConstraint> allConstraints = new HashMap<String, UISecConstraint>();

		// constraint fittizio per mnuView
		UISecConstraint mnuView_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneGW_PM", "mnuView",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] mnuView_constraints = new UISecConstraint[]{mnuView_defaultOnoff_ctr};
		UISecConstraint mnuView_ctr = new ComplexUISecConstraint(
				mnuView_constraints);
		allConstraints.put("mnuView", mnuView_ctr);

		// constraint fittizio per tbListaGateways
		UISecConstraint tbListaGateways_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneGW_PM", "tbListaGateways",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tbListaGateways_constraints = new UISecConstraint[]{tbListaGateways_defaultOnoff_ctr};
		UISecConstraint tbListaGateways_ctr = new ComplexUISecConstraint(
				tbListaGateways_constraints);
		allConstraints.put("tbListaGateways", tbListaGateways_ctr);

		// constraint fittizio per btnNuovoGateway
		UISecConstraint btnNuovoGateway_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneGW_PM", "btnNuovoGateway",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btnNuovoGateway_constraints = new UISecConstraint[]{btnNuovoGateway_defaultOnoff_ctr};
		UISecConstraint btnNuovoGateway_ctr = new ComplexUISecConstraint(
				btnNuovoGateway_constraints);
		allConstraints.put("btnNuovoGateway", btnNuovoGateway_ctr);

		// constraint fittizio per btnClonaGateway
		UISecConstraint btnClonaGateway_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneGW_PM", "btnClonaGateway",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btnClonaGateway_constraints = new UISecConstraint[]{btnClonaGateway_defaultOnoff_ctr};
		UISecConstraint btnClonaGateway_ctr = new ComplexUISecConstraint(
				btnClonaGateway_constraints);
		allConstraints.put("btnClonaGateway", btnClonaGateway_ctr);

		// constraint fittizio per btnModificaGateway
		UISecConstraint btnModificaGateway_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneGW_PM", "btnModificaGateway",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btnModificaGateway_constraints = new UISecConstraint[]{btnModificaGateway_defaultOnoff_ctr};
		UISecConstraint btnModificaGateway_ctr = new ComplexUISecConstraint(
				btnModificaGateway_constraints);
		allConstraints.put("btnModificaGateway", btnModificaGateway_ctr);

		// constraint fittizio per btnCancellaGateway
		UISecConstraint btnCancellaGateway_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneGW_PM", "btnCancellaGateway",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btnCancellaGateway_constraints = new UISecConstraint[]{btnCancellaGateway_defaultOnoff_ctr};
		UISecConstraint btnCancellaGateway_ctr = new ComplexUISecConstraint(
				btnCancellaGateway_constraints);
		allConstraints.put("btnCancellaGateway", btnCancellaGateway_ctr);

		// constraint fittizio per tbListaPaymentModes
		UISecConstraint tbListaPaymentModes_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneGW_PM", "tbListaPaymentModes",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tbListaPaymentModes_constraints = new UISecConstraint[]{tbListaPaymentModes_defaultOnoff_ctr};
		UISecConstraint tbListaPaymentModes_ctr = new ComplexUISecConstraint(
				tbListaPaymentModes_constraints);
		allConstraints.put("tbListaPaymentModes", tbListaPaymentModes_ctr);

		// constraint fittizio per btnNuovoPaymentMode
		UISecConstraint btnNuovoPaymentMode_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneGW_PM", "btnNuovoPaymentMode",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btnNuovoPaymentMode_constraints = new UISecConstraint[]{btnNuovoPaymentMode_defaultOnoff_ctr};
		UISecConstraint btnNuovoPaymentMode_ctr = new ComplexUISecConstraint(
				btnNuovoPaymentMode_constraints);
		allConstraints.put("btnNuovoPaymentMode", btnNuovoPaymentMode_ctr);

		// constraint fittizio per btnModificaPaymentMode
		UISecConstraint btnModificaPaymentMode_defaultOnoff_ctr =

		new DummyUISecConstraint("cpGestioneGW_PM", "btnModificaPaymentMode",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btnModificaPaymentMode_constraints = new UISecConstraint[]{btnModificaPaymentMode_defaultOnoff_ctr};
		UISecConstraint btnModificaPaymentMode_ctr = new ComplexUISecConstraint(
				btnModificaPaymentMode_constraints);
		allConstraints
				.put("btnModificaPaymentMode", btnModificaPaymentMode_ctr);
		return allConstraints;
	}

	/**  */
	void dumpModel(boolean pre) {
		log.debug("[CpGestioneGW_PMAction::dumpmodel] START");

		log.debug("[CpGestioneGW_PMAction::dumpmodel] #### DUMP del model della action "
				+ this.getClass()
				+ (pre ? " prima dell'azione" : " dopo l'azione"));
		log.debug("[CpGestioneGW_PMAction::dumpmodel] [a] campi pubblici del model");
		try {
			java.beans.BeanInfo bi = java.beans.Introspector.getBeanInfo(this
					.getClass());
			java.beans.PropertyDescriptor[] props = bi.getPropertyDescriptors();
			for (int i = 0; i < props.length; i++) {
				java.beans.PropertyDescriptor pd = props[i];
				java.lang.reflect.Method m = pd.getReadMethod();
				if (m != null) {
					Object pval = m.invoke(this, new Object[]{});
					log.debug("[CpGestioneGW_PMAction::dumpmodel] "
							+ pd.getName() + ":" + pval);
				}
			}
		} catch (IllegalAccessException e) {
			log.error("[CpGestioneGW_PMAction::dumpmodel] Errore nel dump" + e
					+ ", ignoro");
		} catch (InvocationTargetException e) {
			log.error("[CpGestioneGW_PMAction::dumpmodel] Errore nel dump" + e
					+ ", ignoro");
		} catch (IntrospectionException e) {
			log.error("[CpGestioneGW_PMAction::dumpmodel] Errore nel dump" + e
					+ ", ignoro");
		}
		log.debug("[CpGestioneGW_PMAction::dumpmodel] [b] stato dei widget");
		Object cpWidgetStatus = session.get("cpGestioneGW_PM");
		log.debug("[CpGestioneGW_PMAction::dumpmodel] " + cpWidgetStatus);
		log.debug("[CpGestioneGW_PMAction::dumpmodel] [c] sessione");
		log.debug("[CpGestioneGW_PMAction::dumpmodel] " + session);
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

		// contiene i comandi del widget btnNuovoGateway per ogni Ev.H.
		HashMap<String, ICommand> cmds4btnNuovoGatewayByEvh = new HashMap<String, ICommand>();

		cmds4btnNuovoGatewayByEvh.put("CLICKED",
				initCommandBtnNuovoGateway_CLICKED());
		cmdsByWidget.put("btnNuovoGateway", cmds4btnNuovoGatewayByEvh);
		// contiene i comandi del widget btnClonaGateway per ogni Ev.H.
		HashMap<String, ICommand> cmds4btnClonaGatewayByEvh = new HashMap<String, ICommand>();

		cmds4btnClonaGatewayByEvh.put("CLICKED",
				initCommandBtnClonaGateway_CLICKED());
		cmdsByWidget.put("btnClonaGateway", cmds4btnClonaGatewayByEvh);
		// contiene i comandi del widget btnModificaGateway per ogni Ev.H.
		HashMap<String, ICommand> cmds4btnModificaGatewayByEvh = new HashMap<String, ICommand>();

		cmds4btnModificaGatewayByEvh.put("CLICKED",
				initCommandBtnModificaGateway_CLICKED());
		cmdsByWidget.put("btnModificaGateway", cmds4btnModificaGatewayByEvh);
		// contiene i comandi del widget btnCancellaGateway per ogni Ev.H.
		HashMap<String, ICommand> cmds4btnCancellaGatewayByEvh = new HashMap<String, ICommand>();

		cmds4btnCancellaGatewayByEvh.put("CLICKED",
				initCommandBtnCancellaGateway_CLICKED());
		cmdsByWidget.put("btnCancellaGateway", cmds4btnCancellaGatewayByEvh);
		// contiene i comandi del widget btnNuovoPaymentMode per ogni Ev.H.
		HashMap<String, ICommand> cmds4btnNuovoPaymentModeByEvh = new HashMap<String, ICommand>();

		cmds4btnNuovoPaymentModeByEvh.put("CLICKED",
				initCommandBtnNuovoPaymentMode_CLICKED());
		cmdsByWidget.put("btnNuovoPaymentMode", cmds4btnNuovoPaymentModeByEvh);
		// contiene i comandi del widget btnModificaPaymentMode per ogni Ev.H.
		HashMap<String, ICommand> cmds4btnModificaPaymentModeByEvh = new HashMap<String, ICommand>();

		cmds4btnModificaPaymentModeByEvh.put("CLICKED",
				initCommandBtnModificaPaymentMode_CLICKED());
		cmdsByWidget.put("btnModificaPaymentMode",
				cmds4btnModificaPaymentModeByEvh);

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

	private ICommand initCommandBtnNuovoGateway_CLICKED() {
		// ExecCommand begin
		String[] resultNames4nuovoGateway = new String[]{"Ok"};

		ICommand[] actionsForResults4nuovoGateway = new ICommand[1];
		// SequenceCommand begin
		/// Clear AppData Command begin
		String[] act_actions_clicked_btnNuovoGateway_resokActionstep_0_appdataToBeRemovedFromSession = new String[]{"isPostBack"};

		ClearAppDataCommand act_actions_clicked_btnNuovoGateway_resokActionstep_0 = new ClearAppDataCommand(
				act_actions_clicked_btnNuovoGateway_resokActionstep_0_appdataToBeRemovedFromSession);

		/// Clear AppData Command end
		/// Jump Command begin
		JumpCommand act_actions_clicked_btnNuovoGateway_resokActionstep_1 = new JumpCommand(
				"cpDettaglioGatewayNew", null, false);
		/// Jump Command end

		ICommand[] act_actions_clicked_btnNuovoGateway_resokAction_0_steps = new ICommand[]{
				act_actions_clicked_btnNuovoGateway_resokActionstep_0,
				act_actions_clicked_btnNuovoGateway_resokActionstep_1};
		SequenceCommand act_actions_clicked_btnNuovoGateway_resokAction_0 = new SequenceCommand(
				act_actions_clicked_btnNuovoGateway_resokAction_0_steps);
		// SequenceCommand end
		actionsForResults4nuovoGateway[0] = act_actions_clicked_btnNuovoGateway_resokAction_0;

		ExecCommand act_actions_clicked_btnNuovoGateway_1 = new ExecCommand(
				resultNames4nuovoGateway, actionsForResults4nuovoGateway) {
			public ExecResults doLogic(Object theModel)
					throws CommandExecutionException {
				it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch(
						it.csi.mdp.mdpboweb.util.Constants.APPLICATION_CODE);
				ExecResults result = null;
				try {
					watcher.start();
					result = getSpringBackEnd()
							.nuovoGateway(
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneGW_PMModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpGestioneGW_PMAction", "readOne()",
							"chiamata verso BackEnd", "nuovoGateway");
					return result;
				} catch (BEException e) {
					throw new CommandExecutionException(
							"errore non gestito nell'esecuzione del metodo [nuovoGateway]:"
									+ e.getMessage(), e);
				}

			}

			public void storeAppData(ExecResults res, BaseAction strutsAction) {
				// nothing to store...
			}
		};
		// Exec Action end
		return act_actions_clicked_btnNuovoGateway_1;
	}

	private ICommand initCommandBtnClonaGateway_CLICKED() {
		// ExecCommand begin
		String[] resultNames4clonaGateway = new String[]{"Ok", "KO"};

		ICommand[] actionsForResults4clonaGateway = new ICommand[2];
		// SequenceCommand begin
		/// Clear AppData Command begin
		String[] act_actions_clicked_btnClonaGateway_resokActionstep_0_appdataToBeRemovedFromSession = new String[]{
				"isPostBack", "selettoreIdGateway", "lingue", "lingueClone",
				"divise", "diviseClone", "extraAttributesNuovoGateway",
				"extraAttributes", "nuovoExtraAttribute"};

		ClearAppDataCommand act_actions_clicked_btnClonaGateway_resokActionstep_0 = new ClearAppDataCommand(
				act_actions_clicked_btnClonaGateway_resokActionstep_0_appdataToBeRemovedFromSession);

		/// Clear AppData Command end
		/// Jump Command begin
		JumpCommand act_actions_clicked_btnClonaGateway_resokActionstep_1 = new JumpCommand(
				"cpDettaglioGatewayNewClone", null, false);
		/// Jump Command end

		ICommand[] act_actions_clicked_btnClonaGateway_resokAction_0_steps = new ICommand[]{
				act_actions_clicked_btnClonaGateway_resokActionstep_0,
				act_actions_clicked_btnClonaGateway_resokActionstep_1};
		SequenceCommand act_actions_clicked_btnClonaGateway_resokAction_0 = new SequenceCommand(
				act_actions_clicked_btnClonaGateway_resokAction_0_steps);
		// SequenceCommand end
		actionsForResults4clonaGateway[0] = act_actions_clicked_btnClonaGateway_resokAction_0;
		// SequenceCommand begin
		/// NOP Command begin
		NOPCommand act_actions_clicked_btnClonaGateway_reskoActionstep_0 = new NOPCommand();
		/// NOP Command end

		ICommand[] act_actions_clicked_btnClonaGateway_reskoAction_1_steps = new ICommand[]{act_actions_clicked_btnClonaGateway_reskoActionstep_0};
		SequenceCommand act_actions_clicked_btnClonaGateway_reskoAction_1 = new SequenceCommand(
				act_actions_clicked_btnClonaGateway_reskoAction_1_steps);
		// SequenceCommand end
		actionsForResults4clonaGateway[1] = act_actions_clicked_btnClonaGateway_reskoAction_1;

		ExecCommand act_actions_clicked_btnClonaGateway_1 = new ExecCommand(
				resultNames4clonaGateway, actionsForResults4clonaGateway) {
			public ExecResults doLogic(Object theModel)
					throws CommandExecutionException {
				it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch(
						it.csi.mdp.mdpboweb.util.Constants.APPLICATION_CODE);
				ExecResults result = null;
				try {
					watcher.start();
					result = getSpringBackEnd()
							.clonaGateway(
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneGW_PMModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpGestioneGW_PMAction", "readOne()",
							"chiamata verso BackEnd", "clonaGateway");
					return result;
				} catch (BEException e) {
					throw new CommandExecutionException(
							"errore non gestito nell'esecuzione del metodo [clonaGateway]:"
									+ e.getMessage(), e);
				}

			}

			public void storeAppData(ExecResults res, BaseAction strutsAction) {
				// nothing to store...
			}
		};
		// Exec Action end
		return act_actions_clicked_btnClonaGateway_1;
	}

	private ICommand initCommandBtnModificaGateway_CLICKED() {
		// ExecCommand begin
		String[] resultNames4modificaGateway = new String[]{"Ok", "Ko"};

		ICommand[] actionsForResults4modificaGateway = new ICommand[2];
		// SequenceCommand begin
		/// Clear AppData Command begin
		String[] act_actions_clicked_btnModificaGateway_resokActionstep_0_appdataToBeRemovedFromSession = new String[]{
				"isPostBack", "associazioneGW_MP"};

		ClearAppDataCommand act_actions_clicked_btnModificaGateway_resokActionstep_0 = new ClearAppDataCommand(
				act_actions_clicked_btnModificaGateway_resokActionstep_0_appdataToBeRemovedFromSession);

		/// Clear AppData Command end
		/// Jump Command begin
		JumpCommand act_actions_clicked_btnModificaGateway_resokActionstep_1 = new JumpCommand(
				"cpDettaglioGateway", null, false);
		/// Jump Command end

		ICommand[] act_actions_clicked_btnModificaGateway_resokAction_0_steps = new ICommand[]{
				act_actions_clicked_btnModificaGateway_resokActionstep_0,
				act_actions_clicked_btnModificaGateway_resokActionstep_1};
		SequenceCommand act_actions_clicked_btnModificaGateway_resokAction_0 = new SequenceCommand(
				act_actions_clicked_btnModificaGateway_resokAction_0_steps);
		// SequenceCommand end
		actionsForResults4modificaGateway[0] = act_actions_clicked_btnModificaGateway_resokAction_0;
		// SequenceCommand begin
		/// NOP Command begin
		NOPCommand act_actions_clicked_btnModificaGateway_reskoActionstep_0 = new NOPCommand();
		/// NOP Command end

		ICommand[] act_actions_clicked_btnModificaGateway_reskoAction_1_steps = new ICommand[]{act_actions_clicked_btnModificaGateway_reskoActionstep_0};
		SequenceCommand act_actions_clicked_btnModificaGateway_reskoAction_1 = new SequenceCommand(
				act_actions_clicked_btnModificaGateway_reskoAction_1_steps);
		// SequenceCommand end
		actionsForResults4modificaGateway[1] = act_actions_clicked_btnModificaGateway_reskoAction_1;

		ExecCommand act_actions_clicked_btnModificaGateway_1 = new ExecCommand(
				resultNames4modificaGateway, actionsForResults4modificaGateway) {
			public ExecResults doLogic(Object theModel)
					throws CommandExecutionException {
				it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch(
						it.csi.mdp.mdpboweb.util.Constants.APPLICATION_CODE);
				ExecResults result = null;
				try {
					watcher.start();
					result = getSpringBackEnd()
							.modificaGateway(
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneGW_PMModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpGestioneGW_PMAction", "readOne()",
							"chiamata verso BackEnd", "modificaGateway");
					return result;
				} catch (BEException e) {
					throw new CommandExecutionException(
							"errore non gestito nell'esecuzione del metodo [modificaGateway]:"
									+ e.getMessage(), e);
				}

			}

			public void storeAppData(ExecResults res, BaseAction strutsAction) {
				// nothing to store...
			}
		};
		// Exec Action end
		return act_actions_clicked_btnModificaGateway_1;
	}

	private ICommand initCommandBtnCancellaGateway_CLICKED() {
		// ExecCommand begin
		String[] resultNames4cancellaGateway = new String[]{"Ok", "Ko"};

		ICommand[] actionsForResults4cancellaGateway = new ICommand[2];
		/// NOP Command begin
		NOPCommand act_actions_clicked_btnCancellaGateway_resokAction_0 = new NOPCommand();
		/// NOP Command end
		actionsForResults4cancellaGateway[0] = act_actions_clicked_btnCancellaGateway_resokAction_0;
		/// NOP Command begin
		NOPCommand act_actions_clicked_btnCancellaGateway_reskoAction_1 = new NOPCommand();
		/// NOP Command end
		actionsForResults4cancellaGateway[1] = act_actions_clicked_btnCancellaGateway_reskoAction_1;

		ExecCommand act_actions_clicked_btnCancellaGateway_1 = new ExecCommand(
				resultNames4cancellaGateway, actionsForResults4cancellaGateway) {
			public ExecResults doLogic(Object theModel)
					throws CommandExecutionException {
				it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch(
						it.csi.mdp.mdpboweb.util.Constants.APPLICATION_CODE);
				ExecResults result = null;
				try {
					watcher.start();
					result = getSpringBackEnd()
							.cancellaGateway(
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneGW_PMModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpGestioneGW_PMAction", "readOne()",
							"chiamata verso BackEnd", "cancellaGateway");
					return result;
				} catch (BEException e) {
					throw new CommandExecutionException(
							"errore non gestito nell'esecuzione del metodo [cancellaGateway]:"
									+ e.getMessage(), e);
				}

			}

			public void storeAppData(ExecResults res, BaseAction strutsAction) {
				// nothing to store...
			}
		};
		// Exec Action end
		return act_actions_clicked_btnCancellaGateway_1;
	}

	private ICommand initCommandBtnNuovoPaymentMode_CLICKED() {
		// ExecCommand begin
		String[] resultNames4nuovoPaymentMode = new String[]{"Ok"};

		ICommand[] actionsForResults4nuovoPaymentMode = new ICommand[1];
		// SequenceCommand begin
		/// Jump Command begin
		JumpCommand act_actions_clicked_btnNuovoPaymentMode_resokActionstep_0 = new JumpCommand(
				"cpDettaglioPaymentMode", null, false);
		/// Jump Command end

		ICommand[] act_actions_clicked_btnNuovoPaymentMode_resokAction_0_steps = new ICommand[]{act_actions_clicked_btnNuovoPaymentMode_resokActionstep_0};
		SequenceCommand act_actions_clicked_btnNuovoPaymentMode_resokAction_0 = new SequenceCommand(
				act_actions_clicked_btnNuovoPaymentMode_resokAction_0_steps);
		// SequenceCommand end
		actionsForResults4nuovoPaymentMode[0] = act_actions_clicked_btnNuovoPaymentMode_resokAction_0;

		ExecCommand act_actions_clicked_btnNuovoPaymentMode_1 = new ExecCommand(
				resultNames4nuovoPaymentMode,
				actionsForResults4nuovoPaymentMode) {
			public ExecResults doLogic(Object theModel)
					throws CommandExecutionException {
				it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch(
						it.csi.mdp.mdpboweb.util.Constants.APPLICATION_CODE);
				ExecResults result = null;
				try {
					watcher.start();
					result = getSpringBackEnd()
							.nuovoPaymentMode(
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneGW_PMModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpGestioneGW_PMAction", "readOne()",
							"chiamata verso BackEnd", "nuovoPaymentMode");
					return result;
				} catch (BEException e) {
					throw new CommandExecutionException(
							"errore non gestito nell'esecuzione del metodo [nuovoPaymentMode]:"
									+ e.getMessage(), e);
				}

			}

			public void storeAppData(ExecResults res, BaseAction strutsAction) {
				// nothing to store...
			}
		};
		// Exec Action end
		return act_actions_clicked_btnNuovoPaymentMode_1;
	}

	private ICommand initCommandBtnModificaPaymentMode_CLICKED() {
		// ExecCommand begin
		String[] resultNames4modificaPaymentMode = new String[]{"Ok", "Ko"};

		ICommand[] actionsForResults4modificaPaymentMode = new ICommand[2];
		// SequenceCommand begin
		/// Clear AppData Command begin
		String[] act_actions_clicked_btnModificaPaymentMode_resokActionstep_0_appdataToBeRemovedFromSession = new String[]{"isPostBack"};

		ClearAppDataCommand act_actions_clicked_btnModificaPaymentMode_resokActionstep_0 = new ClearAppDataCommand(
				act_actions_clicked_btnModificaPaymentMode_resokActionstep_0_appdataToBeRemovedFromSession);

		/// Clear AppData Command end
		/// Jump Command begin
		JumpCommand act_actions_clicked_btnModificaPaymentMode_resokActionstep_1 = new JumpCommand(
				"cpDettaglioPaymentMode", null, false);
		/// Jump Command end

		ICommand[] act_actions_clicked_btnModificaPaymentMode_resokAction_0_steps = new ICommand[]{
				act_actions_clicked_btnModificaPaymentMode_resokActionstep_0,
				act_actions_clicked_btnModificaPaymentMode_resokActionstep_1};
		SequenceCommand act_actions_clicked_btnModificaPaymentMode_resokAction_0 = new SequenceCommand(
				act_actions_clicked_btnModificaPaymentMode_resokAction_0_steps);
		// SequenceCommand end
		actionsForResults4modificaPaymentMode[0] = act_actions_clicked_btnModificaPaymentMode_resokAction_0;
		// SequenceCommand begin
		/// NOP Command begin
		NOPCommand act_actions_clicked_btnModificaPaymentMode_reskoActionstep_0 = new NOPCommand();
		/// NOP Command end

		ICommand[] act_actions_clicked_btnModificaPaymentMode_reskoAction_1_steps = new ICommand[]{act_actions_clicked_btnModificaPaymentMode_reskoActionstep_0};
		SequenceCommand act_actions_clicked_btnModificaPaymentMode_reskoAction_1 = new SequenceCommand(
				act_actions_clicked_btnModificaPaymentMode_reskoAction_1_steps);
		// SequenceCommand end
		actionsForResults4modificaPaymentMode[1] = act_actions_clicked_btnModificaPaymentMode_reskoAction_1;

		ExecCommand act_actions_clicked_btnModificaPaymentMode_1 = new ExecCommand(
				resultNames4modificaPaymentMode,
				actionsForResults4modificaPaymentMode) {
			public ExecResults doLogic(Object theModel)
					throws CommandExecutionException {
				it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch(
						it.csi.mdp.mdpboweb.util.Constants.APPLICATION_CODE);
				ExecResults result = null;
				try {
					watcher.start();
					result = getSpringBackEnd()
							.modificaPaymentMode(
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpGestioneGW_PMModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpGestioneGW_PMAction", "readOne()",
							"chiamata verso BackEnd", "modificaPaymentMode");
					return result;
				} catch (BEException e) {
					throw new CommandExecutionException(
							"errore non gestito nell'esecuzione del metodo [modificaPaymentMode]:"
									+ e.getMessage(), e);
				}

			}

			public void storeAppData(ExecResults res, BaseAction strutsAction) {
				// nothing to store...
			}
		};
		// Exec Action end
		return act_actions_clicked_btnModificaPaymentMode_1;
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
