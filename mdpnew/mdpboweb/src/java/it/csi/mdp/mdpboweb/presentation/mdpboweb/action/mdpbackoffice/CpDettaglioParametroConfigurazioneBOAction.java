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
 * CpDettaglioParametroConfigurazioneBOAction Action Class.
 *
 * @author GuiGen
 */
@Validation()
@Conversion()
public class CpDettaglioParametroConfigurazioneBOAction extends BaseAction
		implements
			Preparable {

	public void setAppDatacurrentUser(
			it.csi.mdp.mdpboweb.dto.common.UserInfo value) {
		getSession().put("appDatacurrentUser", value);
	}

	public it.csi.mdp.mdpboweb.dto.common.UserInfo getAppDatacurrentUser() {
		return (it.csi.mdp.mdpboweb.dto.common.UserInfo) (getSession()
				.get("appDatacurrentUser"));
	}

	public void setAppDataparametriConfigurazioneBO(
			java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.ParametroConfigurazioneBO> value) {
		getSession().put("appDataparametriConfigurazioneBO", value);
	}

	public java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.ParametroConfigurazioneBO> getAppDataparametriConfigurazioneBO() {
		return (java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.ParametroConfigurazioneBO>) (getSession()
				.get("appDataparametriConfigurazioneBO"));
	}

	public void setAppDataparametroConfigurazioneBO(
			it.csi.mdp.mdpboweb.dto.nsbackoffice.ParametroConfigurazioneBO value) {
		getSession().put("appDataparametroConfigurazioneBO", value);
	}

	public it.csi.mdp.mdpboweb.dto.nsbackoffice.ParametroConfigurazioneBO getAppDataparametroConfigurazioneBO() {
		return (it.csi.mdp.mdpboweb.dto.nsbackoffice.ParametroConfigurazioneBO) (getSession()
				.get("appDataparametroConfigurazioneBO"));
	}

	public void setAppDataselettoreParametroConf(java.lang.String value) {
		getSession().put("appDataselettoreParametroConf", value);
	}

	public java.lang.String getAppDataselettoreParametroConf() {
		return (java.lang.String) (getSession()
				.get("appDataselettoreParametroConf"));
	}

	public void setAppDataselettoreOperazione(java.lang.String value) {
		getSession().put("appDataselettoreOperazione", value);
	}

	public java.lang.String getAppDataselettoreOperazione() {
		return (java.lang.String) (getSession()
				.get("appDataselettoreOperazione"));
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
		return it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioParametroConfigurazioneBOModel.class;
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
			log.debug("[CpDettaglioParametroConfigurazioneBOAction::handleBtnIndietro_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpDettaglioParametroConfigurazioneBOAction::handleBtnIndietro_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpDettaglioParametroConfigurazioneBOAction::handleBtnIndietro_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpDettaglioParametroConfigurazioneBOAction::handleBtnIndietro_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpDettaglioParametroConfigurazioneBOAction::handleBtnIndietro_CLICKED] returning default result [SUCCESS]");
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
			log.debug("[CpDettaglioParametroConfigurazioneBOAction::handleBtnSalva_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpDettaglioParametroConfigurazioneBOAction::handleBtnSalva_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpDettaglioParametroConfigurazioneBOAction::handleBtnSalva_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpDettaglioParametroConfigurazioneBOAction::handleBtnSalva_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpDettaglioParametroConfigurazioneBOAction::handleBtnSalva_CLICKED] returning default result [SUCCESS]");
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
		/*PROTECTED REGION ID(R1042582658) ENABLED START*/
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
				log.error("[CpDettaglioParametroConfigurazioneBOAction::isWidgetDisabled] errore durante verifica->disable");
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
				log.error("[CpDettaglioParametroConfigurazioneBOAction::isWidgetVisible] errore durante verifica->hide");
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
		String[] resultNames4refreshPanel = new String[]{"Ok_Insert",
				"Ok_Modifica"};

		ICommand[] actionsForResults4refreshPanel = new ICommand[2];
		// SequenceCommand begin
		// Screen State Command begin
		//prepara i nomi dei componenti target da abilitare/disabilitare
		String[] act_onRefresh_resok_insertActionstep_0_on = new String[]{
				"mnuView", "tfChiave", "tfValore", "tfDescrizione",
				"btnIndietro", "btnSalva"};

		String[] act_onRefresh_resok_insertActionstep_0_off = new String[]{
				"ptDatiObbligatori", "ptChiave"};

		String[] act_onRefresh_resok_insertActionstep_0_show = new String[]{
				"mnuView", "tfChiave", "tfValore", "tfDescrizione",
				"btnIndietro", "btnSalva"};

		String[] act_onRefresh_resok_insertActionstep_0_hide = new String[]{
				"ptDatiObbligatori", "ptChiave"};

		ScreenStateCommand act_onRefresh_resok_insertActionstep_0 = new ScreenStateCommand(
				"cpDettaglioParametroConfigurazioneBO", "INSERIMENTO",
				act_onRefresh_resok_insertActionstep_0_on,
				act_onRefresh_resok_insertActionstep_0_off,
				act_onRefresh_resok_insertActionstep_0_show,
				act_onRefresh_resok_insertActionstep_0_hide);
		//Screen State Command end

		ICommand[] act_onRefresh_resok_insertAction_0_steps = new ICommand[]{act_onRefresh_resok_insertActionstep_0};
		SequenceCommand act_onRefresh_resok_insertAction_0 = new SequenceCommand(
				act_onRefresh_resok_insertAction_0_steps);
		// SequenceCommand end
		actionsForResults4refreshPanel[0] = act_onRefresh_resok_insertAction_0;
		// SequenceCommand begin
		// Screen State Command begin
		//prepara i nomi dei componenti target da abilitare/disabilitare
		String[] act_onRefresh_resok_modificaActionstep_0_on = new String[]{
				"mnuView", "ptDatiObbligatori", "ptChiave", "tfValore",
				"tfDescrizione", "btnIndietro", "btnSalva"};

		String[] act_onRefresh_resok_modificaActionstep_0_off = new String[]{"tfChiave"};

		String[] act_onRefresh_resok_modificaActionstep_0_show = new String[]{
				"mnuView", "ptDatiObbligatori", "ptChiave", "tfValore",
				"tfDescrizione", "btnIndietro", "btnSalva"};

		String[] act_onRefresh_resok_modificaActionstep_0_hide = new String[]{"tfChiave"};

		ScreenStateCommand act_onRefresh_resok_modificaActionstep_0 = new ScreenStateCommand(
				"cpDettaglioParametroConfigurazioneBO", "MODIFICA",
				act_onRefresh_resok_modificaActionstep_0_on,
				act_onRefresh_resok_modificaActionstep_0_off,
				act_onRefresh_resok_modificaActionstep_0_show,
				act_onRefresh_resok_modificaActionstep_0_hide);
		//Screen State Command end

		ICommand[] act_onRefresh_resok_modificaAction_1_steps = new ICommand[]{act_onRefresh_resok_modificaActionstep_0};
		SequenceCommand act_onRefresh_resok_modificaAction_1 = new SequenceCommand(
				act_onRefresh_resok_modificaAction_1_steps);
		// SequenceCommand end
		actionsForResults4refreshPanel[1] = act_onRefresh_resok_modificaAction_1;

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
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioParametroConfigurazioneBOModel) theModel);
					watcher.stop();
					watcher.dumpElapsed(
							"CpDettaglioParametroConfigurazioneBOAction",
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

		new DummyUISecConstraint("cpDettaglioParametroConfigurazioneBO",
				"mnuView", UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true,
				true);

		UISecConstraint[] mnuView_constraints = new UISecConstraint[]{mnuView_defaultVisib_ctr};
		UISecConstraint mnuView_ctr = new ComplexUISecConstraint(
				mnuView_constraints);
		allConstraints.put("mnuView", mnuView_ctr);

		// constraint fittizio per ptDatiObbligatori
		UISecConstraint ptDatiObbligatori_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioParametroConfigurazioneBO",
				"ptDatiObbligatori",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] ptDatiObbligatori_constraints = new UISecConstraint[]{ptDatiObbligatori_defaultVisib_ctr};
		UISecConstraint ptDatiObbligatori_ctr = new ComplexUISecConstraint(
				ptDatiObbligatori_constraints);
		allConstraints.put("ptDatiObbligatori", ptDatiObbligatori_ctr);

		// constraint fittizio per ptChiave
		UISecConstraint ptChiave_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioParametroConfigurazioneBO",
				"ptChiave", UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true,
				true);

		UISecConstraint[] ptChiave_constraints = new UISecConstraint[]{ptChiave_defaultVisib_ctr};
		UISecConstraint ptChiave_ctr = new ComplexUISecConstraint(
				ptChiave_constraints);
		allConstraints.put("ptChiave", ptChiave_ctr);

		// constraint fittizio per tfChiave
		UISecConstraint tfChiave_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioParametroConfigurazioneBO",
				"tfChiave", UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true,
				true);

		UISecConstraint[] tfChiave_constraints = new UISecConstraint[]{tfChiave_defaultVisib_ctr};
		UISecConstraint tfChiave_ctr = new ComplexUISecConstraint(
				tfChiave_constraints);
		allConstraints.put("tfChiave", tfChiave_ctr);

		// constraint fittizio per tfValore
		UISecConstraint tfValore_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioParametroConfigurazioneBO",
				"tfValore", UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true,
				true);

		UISecConstraint[] tfValore_constraints = new UISecConstraint[]{tfValore_defaultVisib_ctr};
		UISecConstraint tfValore_ctr = new ComplexUISecConstraint(
				tfValore_constraints);
		allConstraints.put("tfValore", tfValore_ctr);

		// constraint fittizio per tfDescrizione
		UISecConstraint tfDescrizione_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioParametroConfigurazioneBO",
				"tfDescrizione", UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR,
				true, true);

		UISecConstraint[] tfDescrizione_constraints = new UISecConstraint[]{tfDescrizione_defaultVisib_ctr};
		UISecConstraint tfDescrizione_ctr = new ComplexUISecConstraint(
				tfDescrizione_constraints);
		allConstraints.put("tfDescrizione", tfDescrizione_ctr);

		// constraint fittizio per btnIndietro
		UISecConstraint btnIndietro_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioParametroConfigurazioneBO",
				"btnIndietro", UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR,
				true, true);

		UISecConstraint[] btnIndietro_constraints = new UISecConstraint[]{btnIndietro_defaultVisib_ctr};
		UISecConstraint btnIndietro_ctr = new ComplexUISecConstraint(
				btnIndietro_constraints);
		allConstraints.put("btnIndietro", btnIndietro_ctr);

		// constraint fittizio per btnSalva
		UISecConstraint btnSalva_defaultVisib_ctr =

		new DummyUISecConstraint("cpDettaglioParametroConfigurazioneBO",
				"btnSalva", UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true,
				true);

		UISecConstraint[] btnSalva_constraints = new UISecConstraint[]{btnSalva_defaultVisib_ctr};
		UISecConstraint btnSalva_ctr = new ComplexUISecConstraint(
				btnSalva_constraints);
		allConstraints.put("btnSalva", btnSalva_ctr);
		return allConstraints;
	}

	protected Map<String, UISecConstraint> getPageONOFFUIConstraints() {
		Map<String, UISecConstraint> allConstraints = new HashMap<String, UISecConstraint>();

		// constraint fittizio per mnuView
		UISecConstraint mnuView_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioParametroConfigurazioneBO",
				"mnuView", UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true,
				true);

		UISecConstraint[] mnuView_constraints = new UISecConstraint[]{mnuView_defaultOnoff_ctr};
		UISecConstraint mnuView_ctr = new ComplexUISecConstraint(
				mnuView_constraints);
		allConstraints.put("mnuView", mnuView_ctr);

		// constraint fittizio per ptDatiObbligatori
		UISecConstraint ptDatiObbligatori_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioParametroConfigurazioneBO",
				"ptDatiObbligatori",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] ptDatiObbligatori_constraints = new UISecConstraint[]{ptDatiObbligatori_defaultOnoff_ctr};
		UISecConstraint ptDatiObbligatori_ctr = new ComplexUISecConstraint(
				ptDatiObbligatori_constraints);
		allConstraints.put("ptDatiObbligatori", ptDatiObbligatori_ctr);

		// constraint fittizio per ptChiave
		UISecConstraint ptChiave_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioParametroConfigurazioneBO",
				"ptChiave", UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true,
				true);

		UISecConstraint[] ptChiave_constraints = new UISecConstraint[]{ptChiave_defaultOnoff_ctr};
		UISecConstraint ptChiave_ctr = new ComplexUISecConstraint(
				ptChiave_constraints);
		allConstraints.put("ptChiave", ptChiave_ctr);

		// constraint fittizio per tfChiave
		UISecConstraint tfChiave_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioParametroConfigurazioneBO",
				"tfChiave", UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true,
				true);

		UISecConstraint[] tfChiave_constraints = new UISecConstraint[]{tfChiave_defaultOnoff_ctr};
		UISecConstraint tfChiave_ctr = new ComplexUISecConstraint(
				tfChiave_constraints);
		allConstraints.put("tfChiave", tfChiave_ctr);

		// constraint fittizio per tfValore
		UISecConstraint tfValore_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioParametroConfigurazioneBO",
				"tfValore", UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true,
				true);

		UISecConstraint[] tfValore_constraints = new UISecConstraint[]{tfValore_defaultOnoff_ctr};
		UISecConstraint tfValore_ctr = new ComplexUISecConstraint(
				tfValore_constraints);
		allConstraints.put("tfValore", tfValore_ctr);

		// constraint fittizio per tfDescrizione
		UISecConstraint tfDescrizione_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioParametroConfigurazioneBO",
				"tfDescrizione", UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR,
				true, true);

		UISecConstraint[] tfDescrizione_constraints = new UISecConstraint[]{tfDescrizione_defaultOnoff_ctr};
		UISecConstraint tfDescrizione_ctr = new ComplexUISecConstraint(
				tfDescrizione_constraints);
		allConstraints.put("tfDescrizione", tfDescrizione_ctr);

		// constraint fittizio per btnIndietro
		UISecConstraint btnIndietro_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioParametroConfigurazioneBO",
				"btnIndietro", UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR,
				true, true);

		UISecConstraint[] btnIndietro_constraints = new UISecConstraint[]{btnIndietro_defaultOnoff_ctr};
		UISecConstraint btnIndietro_ctr = new ComplexUISecConstraint(
				btnIndietro_constraints);
		allConstraints.put("btnIndietro", btnIndietro_ctr);

		// constraint fittizio per btnSalva
		UISecConstraint btnSalva_defaultOnoff_ctr =

		new DummyUISecConstraint("cpDettaglioParametroConfigurazioneBO",
				"btnSalva", UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true,
				true);

		UISecConstraint[] btnSalva_constraints = new UISecConstraint[]{btnSalva_defaultOnoff_ctr};
		UISecConstraint btnSalva_ctr = new ComplexUISecConstraint(
				btnSalva_constraints);
		allConstraints.put("btnSalva", btnSalva_ctr);
		return allConstraints;
	}

	/**  */
	void dumpModel(boolean pre) {
		log.debug("[CpDettaglioParametroConfigurazioneBOAction::dumpmodel] START");

		log.debug("[CpDettaglioParametroConfigurazioneBOAction::dumpmodel] #### DUMP del model della action "
				+ this.getClass()
				+ (pre ? " prima dell'azione" : " dopo l'azione"));
		log.debug("[CpDettaglioParametroConfigurazioneBOAction::dumpmodel] [a] campi pubblici del model");
		try {
			java.beans.BeanInfo bi = java.beans.Introspector.getBeanInfo(this
					.getClass());
			java.beans.PropertyDescriptor[] props = bi.getPropertyDescriptors();
			for (int i = 0; i < props.length; i++) {
				java.beans.PropertyDescriptor pd = props[i];
				java.lang.reflect.Method m = pd.getReadMethod();
				if (m != null) {
					Object pval = m.invoke(this, new Object[]{});
					log.debug("[CpDettaglioParametroConfigurazioneBOAction::dumpmodel] "
							+ pd.getName() + ":" + pval);
				}
			}
		} catch (IllegalAccessException e) {
			log.error("[CpDettaglioParametroConfigurazioneBOAction::dumpmodel] Errore nel dump"
					+ e + ", ignoro");
		} catch (InvocationTargetException e) {
			log.error("[CpDettaglioParametroConfigurazioneBOAction::dumpmodel] Errore nel dump"
					+ e + ", ignoro");
		} catch (IntrospectionException e) {
			log.error("[CpDettaglioParametroConfigurazioneBOAction::dumpmodel] Errore nel dump"
					+ e + ", ignoro");
		}
		log.debug("[CpDettaglioParametroConfigurazioneBOAction::dumpmodel] [b] stato dei widget");
		Object cpWidgetStatus = session
				.get("cpDettaglioParametroConfigurazioneBO");
		log.debug("[CpDettaglioParametroConfigurazioneBOAction::dumpmodel] "
				+ cpWidgetStatus);
		log.debug("[CpDettaglioParametroConfigurazioneBOAction::dumpmodel] [c] sessione");
		log.debug("[CpDettaglioParametroConfigurazioneBOAction::dumpmodel] "
				+ session);
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
		// contiene i comandi del widget btnSalva per ogni Ev.H.
		HashMap<String, ICommand> cmds4btnSalvaByEvh = new HashMap<String, ICommand>();

		cmds4btnSalvaByEvh.put("CLICKED", initCommandBtnSalva_CLICKED());
		cmdsByWidget.put("btnSalva", cmds4btnSalvaByEvh);

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
		String[] resultNames4indietro = new String[]{"Ok", "Ko"};

		ICommand[] actionsForResults4indietro = new ICommand[2];
		// SequenceCommand begin
		/// Jump Command begin
		JumpCommand act_actions_clicked_btnIndietro_resokActionstep_0 = new JumpCommand(
				"cpRicercaParametriConfigurazioneBO", null, false);
		/// Jump Command end

		ICommand[] act_actions_clicked_btnIndietro_resokAction_0_steps = new ICommand[]{act_actions_clicked_btnIndietro_resokActionstep_0};
		SequenceCommand act_actions_clicked_btnIndietro_resokAction_0 = new SequenceCommand(
				act_actions_clicked_btnIndietro_resokAction_0_steps);
		// SequenceCommand end
		actionsForResults4indietro[0] = act_actions_clicked_btnIndietro_resokAction_0;
		// SequenceCommand begin
		/// NOP Command begin
		NOPCommand act_actions_clicked_btnIndietro_reskoActionstep_0 = new NOPCommand();
		/// NOP Command end

		ICommand[] act_actions_clicked_btnIndietro_reskoAction_1_steps = new ICommand[]{act_actions_clicked_btnIndietro_reskoActionstep_0};
		SequenceCommand act_actions_clicked_btnIndietro_reskoAction_1 = new SequenceCommand(
				act_actions_clicked_btnIndietro_reskoAction_1_steps);
		// SequenceCommand end
		actionsForResults4indietro[1] = act_actions_clicked_btnIndietro_reskoAction_1;

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
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioParametroConfigurazioneBOModel) theModel);
					watcher.stop();
					watcher.dumpElapsed(
							"CpDettaglioParametroConfigurazioneBOAction",
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
		String[] resultNames4salva = new String[]{"Ok", "Ko"};

		ICommand[] actionsForResults4salva = new ICommand[2];
		// SequenceCommand begin
		/// Clear AppData Command begin
		String[] act_actions_clicked_btnSalva_resokActionstep_0_appdataToBeRemovedFromSession = new String[]{"selettoreParametroConf"};

		ClearAppDataCommand act_actions_clicked_btnSalva_resokActionstep_0 = new ClearAppDataCommand(
				act_actions_clicked_btnSalva_resokActionstep_0_appdataToBeRemovedFromSession);

		/// Clear AppData Command end
		/// Jump Command begin
		JumpCommand act_actions_clicked_btnSalva_resokActionstep_1 = new JumpCommand(
				"cpRicercaParametriConfigurazioneBO", null, false);
		/// Jump Command end

		ICommand[] act_actions_clicked_btnSalva_resokAction_0_steps = new ICommand[]{
				act_actions_clicked_btnSalva_resokActionstep_0,
				act_actions_clicked_btnSalva_resokActionstep_1};
		SequenceCommand act_actions_clicked_btnSalva_resokAction_0 = new SequenceCommand(
				act_actions_clicked_btnSalva_resokAction_0_steps);
		// SequenceCommand end
		actionsForResults4salva[0] = act_actions_clicked_btnSalva_resokAction_0;
		// SequenceCommand begin
		/// NOP Command begin
		NOPCommand act_actions_clicked_btnSalva_reskoActionstep_0 = new NOPCommand();
		/// NOP Command end

		ICommand[] act_actions_clicked_btnSalva_reskoAction_1_steps = new ICommand[]{act_actions_clicked_btnSalva_reskoActionstep_0};
		SequenceCommand act_actions_clicked_btnSalva_reskoAction_1 = new SequenceCommand(
				act_actions_clicked_btnSalva_reskoAction_1_steps);
		// SequenceCommand end
		actionsForResults4salva[1] = act_actions_clicked_btnSalva_reskoAction_1;

		ExecCommand act_actions_clicked_btnSalva_1 = new ExecCommand(
				resultNames4salva, actionsForResults4salva) {
			public ExecResults doLogic(Object theModel)
					throws CommandExecutionException {
				it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch(
						it.csi.mdp.mdpboweb.util.Constants.APPLICATION_CODE);
				ExecResults result = null;
				try {
					watcher.start();
					result = getSpringBackEnd()
							.salva((it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpDettaglioParametroConfigurazioneBOModel) theModel);
					watcher.stop();
					watcher.dumpElapsed(
							"CpDettaglioParametroConfigurazioneBOAction",
							"readOne()", "chiamata verso BackEnd", "salva");
					return result;
				} catch (BEException e) {
					throw new CommandExecutionException(
							"errore non gestito nell'esecuzione del metodo [salva]:"
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
