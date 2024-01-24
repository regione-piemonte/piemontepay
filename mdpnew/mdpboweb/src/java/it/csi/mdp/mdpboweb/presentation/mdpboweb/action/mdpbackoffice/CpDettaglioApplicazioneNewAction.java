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
 * CpDettaglioApplicazioneNewAction Action Class.
 *
 * @author GuiGen
 */
@Validation()
@Conversion()
public class CpDettaglioApplicazioneNewAction extends BaseAction
		implements
			Preparable {

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
		return it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioApplicazioneNewModel.class;
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
	 * Gestione dell'evento CLICKED sul widget [btnSalvaApplicazione]
	 */
	@MethodProtection(level = "REJECT_SAME")
	public String handleBtnSalvaApplicazione_CLICKED()
			throws CommandExecutionException {
		ICommand action = initCommand("btnSalvaApplicazione", "CLICKED");
		if (log.isDebugEnabled()) {
			log.debug("[CpDettaglioApplicazioneNewAction::handleBtnSalvaApplicazione_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpDettaglioApplicazioneNewAction::handleBtnSalvaApplicazione_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpDettaglioApplicazioneNewAction::handleBtnSalvaApplicazione_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpDettaglioApplicazioneNewAction::handleBtnSalvaApplicazione_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpDettaglioApplicazioneNewAction::handleBtnSalvaApplicazione_CLICKED] returning default result [SUCCESS]");
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
	 * Gestione della validazione
	 */
	public void validate() {
		/*PROTECTED REGION ID(R-603803432) ENABLED START*/
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
				log.error("[CpDettaglioApplicazioneNewAction::isWidgetDisabled] errore durante verifica->disable");
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
				log.error("[CpDettaglioApplicazioneNewAction::isWidgetVisible] errore durante verifica->hide");
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
		/// NOP Command begin
		NOPCommand act_onRefresh_resokActionstep_0 = new NOPCommand();
		/// NOP Command end

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
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioApplicazioneNewModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpDettaglioApplicazioneNewAction",
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

		new DummyUISecConstraint("cpDettaglioApplicazioneNew", "mnuView",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] mnuView_constraints = new UISecConstraint[]{mnuView_defaultVisib_ctr};
		UISecConstraint mnuView_ctr = new ComplexUISecConstraint(
				mnuView_constraints);
		allConstraints.put("mnuView", mnuView_ctr);

		// constraint fittizio per tfIdApplicazione
		UISecConstraint tfIdApplicazione_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazioneNew",
				"tfIdApplicazione", UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR,
				true, true);

		UISecConstraint[] tfIdApplicazione_constraints = new UISecConstraint[]{tfIdApplicazione_defaultVisib_ctr};
		UISecConstraint tfIdApplicazione_ctr = new ComplexUISecConstraint(
				tfIdApplicazione_constraints);
		allConstraints.put("tfIdApplicazione", tfIdApplicazione_ctr);

		// constraint fittizio per tfNomeApplicazione
		UISecConstraint tfNomeApplicazione_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazioneNew",
				"tfNomeApplicazione",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tfNomeApplicazione_constraints = new UISecConstraint[]{tfNomeApplicazione_defaultVisib_ctr};
		UISecConstraint tfNomeApplicazione_ctr = new ComplexUISecConstraint(
				tfNomeApplicazione_constraints);
		allConstraints.put("tfNomeApplicazione", tfNomeApplicazione_ctr);

		// constraint fittizio per tfReferente
		UISecConstraint tfReferente_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazioneNew", "tfReferente",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tfReferente_constraints = new UISecConstraint[]{tfReferente_defaultVisib_ctr};
		UISecConstraint tfReferente_ctr = new ComplexUISecConstraint(
				tfReferente_constraints);
		allConstraints.put("tfReferente", tfReferente_ctr);

		// constraint fittizio per tfCliente
		UISecConstraint tfCliente_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazioneNew", "tfCliente",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tfCliente_constraints = new UISecConstraint[]{tfCliente_defaultVisib_ctr};
		UISecConstraint tfCliente_ctr = new ComplexUISecConstraint(
				tfCliente_constraints);
		allConstraints.put("tfCliente", tfCliente_ctr);

		// constraint fittizio per tfProgetto
		UISecConstraint tfProgetto_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazioneNew", "tfProgetto",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tfProgetto_constraints = new UISecConstraint[]{tfProgetto_defaultVisib_ctr};
		UISecConstraint tfProgetto_ctr = new ComplexUISecConstraint(
				tfProgetto_constraints);
		allConstraints.put("tfProgetto", tfProgetto_ctr);

		// constraint fittizio per tfEmailEsercente
		UISecConstraint tfEmailEsercente_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazioneNew",
				"tfEmailEsercente", UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR,
				true, true);

		UISecConstraint[] tfEmailEsercente_constraints = new UISecConstraint[]{tfEmailEsercente_defaultVisib_ctr};
		UISecConstraint tfEmailEsercente_ctr = new ComplexUISecConstraint(
				tfEmailEsercente_constraints);
		allConstraints.put("tfEmailEsercente", tfEmailEsercente_ctr);

		// constraint fittizio per tfnumeroVerde
		UISecConstraint tfnumeroVerde_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazioneNew", "tfnumeroVerde",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tfnumeroVerde_constraints = new UISecConstraint[]{tfnumeroVerde_defaultVisib_ctr};
		UISecConstraint tfnumeroVerde_ctr = new ComplexUISecConstraint(
				tfnumeroVerde_constraints);
		allConstraints.put("tfnumeroVerde", tfnumeroVerde_ctr);

		// constraint fittizio per taNote
		UISecConstraint taNote_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazioneNew", "taNote",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] taNote_constraints = new UISecConstraint[]{taNote_defaultVisib_ctr};
		UISecConstraint taNote_ctr = new ComplexUISecConstraint(
				taNote_constraints);
		allConstraints.put("taNote", taNote_ctr);

		// constraint fittizio per btnSalvaApplicazione
		UISecConstraint btnSalvaApplicazione_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazioneNew",
				"btnSalvaApplicazione",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btnSalvaApplicazione_constraints = new UISecConstraint[]{btnSalvaApplicazione_defaultVisib_ctr};
		UISecConstraint btnSalvaApplicazione_ctr = new ComplexUISecConstraint(
				btnSalvaApplicazione_constraints);
		allConstraints.put("btnSalvaApplicazione", btnSalvaApplicazione_ctr);
		return allConstraints;
	}

	protected Map<String, UISecConstraint> getPageONOFFUIConstraints() {
		Map<String, UISecConstraint> allConstraints = new HashMap<String, UISecConstraint>();

		// constraint fittizio per mnuView
		UISecConstraint mnuView_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazioneNew", "mnuView",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] mnuView_constraints = new UISecConstraint[]{mnuView_defaultOnoff_ctr};
		UISecConstraint mnuView_ctr = new ComplexUISecConstraint(
				mnuView_constraints);
		allConstraints.put("mnuView", mnuView_ctr);

		// constraint fittizio per tfIdApplicazione
		UISecConstraint tfIdApplicazione_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazioneNew",
				"tfIdApplicazione", UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR,
				true, true);

		UISecConstraint[] tfIdApplicazione_constraints = new UISecConstraint[]{tfIdApplicazione_defaultOnoff_ctr};
		UISecConstraint tfIdApplicazione_ctr = new ComplexUISecConstraint(
				tfIdApplicazione_constraints);
		allConstraints.put("tfIdApplicazione", tfIdApplicazione_ctr);

		// constraint fittizio per tfNomeApplicazione
		UISecConstraint tfNomeApplicazione_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazioneNew",
				"tfNomeApplicazione",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tfNomeApplicazione_constraints = new UISecConstraint[]{tfNomeApplicazione_defaultOnoff_ctr};
		UISecConstraint tfNomeApplicazione_ctr = new ComplexUISecConstraint(
				tfNomeApplicazione_constraints);
		allConstraints.put("tfNomeApplicazione", tfNomeApplicazione_ctr);

		// constraint fittizio per tfReferente
		UISecConstraint tfReferente_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazioneNew", "tfReferente",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tfReferente_constraints = new UISecConstraint[]{tfReferente_defaultOnoff_ctr};
		UISecConstraint tfReferente_ctr = new ComplexUISecConstraint(
				tfReferente_constraints);
		allConstraints.put("tfReferente", tfReferente_ctr);

		// constraint fittizio per tfCliente
		UISecConstraint tfCliente_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazioneNew", "tfCliente",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tfCliente_constraints = new UISecConstraint[]{tfCliente_defaultOnoff_ctr};
		UISecConstraint tfCliente_ctr = new ComplexUISecConstraint(
				tfCliente_constraints);
		allConstraints.put("tfCliente", tfCliente_ctr);

		// constraint fittizio per tfProgetto
		UISecConstraint tfProgetto_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazioneNew", "tfProgetto",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tfProgetto_constraints = new UISecConstraint[]{tfProgetto_defaultOnoff_ctr};
		UISecConstraint tfProgetto_ctr = new ComplexUISecConstraint(
				tfProgetto_constraints);
		allConstraints.put("tfProgetto", tfProgetto_ctr);

		// constraint fittizio per tfEmailEsercente
		UISecConstraint tfEmailEsercente_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazioneNew",
				"tfEmailEsercente", UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR,
				true, true);

		UISecConstraint[] tfEmailEsercente_constraints = new UISecConstraint[]{tfEmailEsercente_defaultOnoff_ctr};
		UISecConstraint tfEmailEsercente_ctr = new ComplexUISecConstraint(
				tfEmailEsercente_constraints);
		allConstraints.put("tfEmailEsercente", tfEmailEsercente_ctr);

		// constraint fittizio per tfnumeroVerde
		UISecConstraint tfnumeroVerde_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazioneNew", "tfnumeroVerde",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tfnumeroVerde_constraints = new UISecConstraint[]{tfnumeroVerde_defaultOnoff_ctr};
		UISecConstraint tfnumeroVerde_ctr = new ComplexUISecConstraint(
				tfnumeroVerde_constraints);
		allConstraints.put("tfnumeroVerde", tfnumeroVerde_ctr);

		// constraint fittizio per taNote
		UISecConstraint taNote_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazioneNew", "taNote",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] taNote_constraints = new UISecConstraint[]{taNote_defaultOnoff_ctr};
		UISecConstraint taNote_ctr = new ComplexUISecConstraint(
				taNote_constraints);
		allConstraints.put("taNote", taNote_ctr);

		// constraint fittizio per btnSalvaApplicazione
		UISecConstraint btnSalvaApplicazione_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioApplicazioneNew",
				"btnSalvaApplicazione",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btnSalvaApplicazione_constraints = new UISecConstraint[]{btnSalvaApplicazione_defaultOnoff_ctr};
		UISecConstraint btnSalvaApplicazione_ctr = new ComplexUISecConstraint(
				btnSalvaApplicazione_constraints);
		allConstraints.put("btnSalvaApplicazione", btnSalvaApplicazione_ctr);
		return allConstraints;
	}

	/**  */
	void dumpModel(boolean pre) {
		log.debug("[CpDettaglioApplicazioneNewAction::dumpmodel] START");

		log.debug("[CpDettaglioApplicazioneNewAction::dumpmodel] #### DUMP del model della action "
				+ this.getClass()
				+ (pre ? " prima dell'azione" : " dopo l'azione"));
		log.debug("[CpDettaglioApplicazioneNewAction::dumpmodel] [a] campi pubblici del model");
		try {
			java.beans.BeanInfo bi = java.beans.Introspector.getBeanInfo(this
					.getClass());
			java.beans.PropertyDescriptor[] props = bi.getPropertyDescriptors();
			for (int i = 0; i < props.length; i++) {
				java.beans.PropertyDescriptor pd = props[i];
				java.lang.reflect.Method m = pd.getReadMethod();
				if (m != null) {
					Object pval = m.invoke(this, new Object[]{});
					log.debug("[CpDettaglioApplicazioneNewAction::dumpmodel] "
							+ pd.getName() + ":" + pval);
				}
			}
		} catch (IllegalAccessException e) {
			log.error("[CpDettaglioApplicazioneNewAction::dumpmodel] Errore nel dump"
					+ e + ", ignoro");
		} catch (InvocationTargetException e) {
			log.error("[CpDettaglioApplicazioneNewAction::dumpmodel] Errore nel dump"
					+ e + ", ignoro");
		} catch (IntrospectionException e) {
			log.error("[CpDettaglioApplicazioneNewAction::dumpmodel] Errore nel dump"
					+ e + ", ignoro");
		}
		log.debug("[CpDettaglioApplicazioneNewAction::dumpmodel] [b] stato dei widget");
		Object cpWidgetStatus = session.get("cpDettaglioApplicazioneNew");
		log.debug("[CpDettaglioApplicazioneNewAction::dumpmodel] "
				+ cpWidgetStatus);
		log.debug("[CpDettaglioApplicazioneNewAction::dumpmodel] [c] sessione");
		log.debug("[CpDettaglioApplicazioneNewAction::dumpmodel] " + session);
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

		// contiene i comandi del widget btnSalvaApplicazione per ogni Ev.H.
		HashMap<String, ICommand> cmds4btnSalvaApplicazioneByEvh = new HashMap<String, ICommand>();

		cmds4btnSalvaApplicazioneByEvh.put("CLICKED",
				initCommandBtnSalvaApplicazione_CLICKED());
		cmdsByWidget
				.put("btnSalvaApplicazione", cmds4btnSalvaApplicazioneByEvh);

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

	private ICommand initCommandBtnSalvaApplicazione_CLICKED() {
		// ExecCommand begin
		String[] resultNames4salvaApplicazione = new String[]{"Ok", "Ko"};

		ICommand[] actionsForResults4salvaApplicazione = new ICommand[2];
		/// Jump Command begin
		JumpCommand act_actions_clicked_btnSalvaApplicazione_resokAction_0 = new JumpCommand(
				"cpDettaglioApplicazione", null, false);
		/// Jump Command end
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
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioApplicazioneNewModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpDettaglioApplicazioneNewAction",
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
