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
 * CpEliminaParametroConfigurazioneBOAction Action Class.
 *
 * @author GuiGen
 */
@Validation()
@Conversion()
public class CpEliminaParametroConfigurazioneBOAction extends BaseAction
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
		return it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpEliminaParametroConfigurazioneBOModel.class;
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
			log.debug("[CpEliminaParametroConfigurazioneBOAction::handleBtnIndietro_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpEliminaParametroConfigurazioneBOAction::handleBtnIndietro_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpEliminaParametroConfigurazioneBOAction::handleBtnIndietro_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpEliminaParametroConfigurazioneBOAction::handleBtnIndietro_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpEliminaParametroConfigurazioneBOAction::handleBtnIndietro_CLICKED] returning default result [SUCCESS]");
			}
			return SUCCESS;

		}
	}

	/**
	 * Gestione dell'evento CLICKED sul widget [btnElimina]
	 */
	@MethodProtection(level = "REJECT_SAME")
	public String handleBtnElimina_CLICKED() throws CommandExecutionException {
		ICommand action = initCommand("btnElimina", "CLICKED");
		if (log.isDebugEnabled()) {
			log.debug("[CpEliminaParametroConfigurazioneBOAction::handleBtnElimina_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpEliminaParametroConfigurazioneBOAction::handleBtnElimina_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpEliminaParametroConfigurazioneBOAction::handleBtnElimina_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpEliminaParametroConfigurazioneBOAction::handleBtnElimina_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpEliminaParametroConfigurazioneBOAction::handleBtnElimina_CLICKED] returning default result [SUCCESS]");
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
		/*PROTECTED REGION ID(R545844392) ENABLED START*/
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
				log.error("[CpEliminaParametroConfigurazioneBOAction::isWidgetDisabled] errore durante verifica->disable");
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
				log.error("[CpEliminaParametroConfigurazioneBOAction::isWidgetVisible] errore durante verifica->hide");
				return false; // forzo l'invisibilita'
			}
		} else
			return super.isWidgetVisible(cpName, widgShortName);
	}

	protected Map<String, UISecConstraint> getPageVisibilityUIConstraints() {
		Map<String, UISecConstraint> allConstraints = new HashMap<String, UISecConstraint>();

		// constraint fittizio per mnuView
		UISecConstraint mnuView_defaultVisib_ctr =

		new DummyUISecConstraint("cpEliminaParametroConfigurazioneBO",
				"mnuView", UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true,
				true);

		UISecConstraint[] mnuView_constraints = new UISecConstraint[]{mnuView_defaultVisib_ctr};
		UISecConstraint mnuView_ctr = new ComplexUISecConstraint(
				mnuView_constraints);
		allConstraints.put("mnuView", mnuView_ctr);

		// constraint fittizio per ptConfermaEliminaParametro
		UISecConstraint ptConfermaEliminaParametro_defaultVisib_ctr =

		new DummyUISecConstraint("cpEliminaParametroConfigurazioneBO",
				"ptConfermaEliminaParametro",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] ptConfermaEliminaParametro_constraints = new UISecConstraint[]{ptConfermaEliminaParametro_defaultVisib_ctr};
		UISecConstraint ptConfermaEliminaParametro_ctr = new ComplexUISecConstraint(
				ptConfermaEliminaParametro_constraints);
		allConstraints.put("ptConfermaEliminaParametro",
				ptConfermaEliminaParametro_ctr);

		// constraint fittizio per ptChiave
		UISecConstraint ptChiave_defaultVisib_ctr =

		new DummyUISecConstraint("cpEliminaParametroConfigurazioneBO",
				"ptChiave", UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true,
				true);

		UISecConstraint[] ptChiave_constraints = new UISecConstraint[]{ptChiave_defaultVisib_ctr};
		UISecConstraint ptChiave_ctr = new ComplexUISecConstraint(
				ptChiave_constraints);
		allConstraints.put("ptChiave", ptChiave_ctr);

		// constraint fittizio per ptValore
		UISecConstraint ptValore_defaultVisib_ctr =

		new DummyUISecConstraint("cpEliminaParametroConfigurazioneBO",
				"ptValore", UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true,
				true);

		UISecConstraint[] ptValore_constraints = new UISecConstraint[]{ptValore_defaultVisib_ctr};
		UISecConstraint ptValore_ctr = new ComplexUISecConstraint(
				ptValore_constraints);
		allConstraints.put("ptValore", ptValore_ctr);

		// constraint fittizio per ptDescrizione
		UISecConstraint ptDescrizione_defaultVisib_ctr =

		new DummyUISecConstraint("cpEliminaParametroConfigurazioneBO",
				"ptDescrizione", UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR,
				true, true);

		UISecConstraint[] ptDescrizione_constraints = new UISecConstraint[]{ptDescrizione_defaultVisib_ctr};
		UISecConstraint ptDescrizione_ctr = new ComplexUISecConstraint(
				ptDescrizione_constraints);
		allConstraints.put("ptDescrizione", ptDescrizione_ctr);

		// constraint fittizio per btnIndietro
		UISecConstraint btnIndietro_defaultVisib_ctr =

		new DummyUISecConstraint("cpEliminaParametroConfigurazioneBO",
				"btnIndietro", UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR,
				true, true);

		UISecConstraint[] btnIndietro_constraints = new UISecConstraint[]{btnIndietro_defaultVisib_ctr};
		UISecConstraint btnIndietro_ctr = new ComplexUISecConstraint(
				btnIndietro_constraints);
		allConstraints.put("btnIndietro", btnIndietro_ctr);

		// constraint fittizio per btnElimina
		UISecConstraint btnElimina_defaultVisib_ctr =

		new DummyUISecConstraint("cpEliminaParametroConfigurazioneBO",
				"btnElimina", UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true,
				true);

		UISecConstraint[] btnElimina_constraints = new UISecConstraint[]{btnElimina_defaultVisib_ctr};
		UISecConstraint btnElimina_ctr = new ComplexUISecConstraint(
				btnElimina_constraints);
		allConstraints.put("btnElimina", btnElimina_ctr);
		return allConstraints;
	}

	protected Map<String, UISecConstraint> getPageONOFFUIConstraints() {
		Map<String, UISecConstraint> allConstraints = new HashMap<String, UISecConstraint>();

		// constraint fittizio per mnuView
		UISecConstraint mnuView_defaultOnoff_ctr =

		new DummyUISecConstraint("cpEliminaParametroConfigurazioneBO",
				"mnuView", UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true,
				true);

		UISecConstraint[] mnuView_constraints = new UISecConstraint[]{mnuView_defaultOnoff_ctr};
		UISecConstraint mnuView_ctr = new ComplexUISecConstraint(
				mnuView_constraints);
		allConstraints.put("mnuView", mnuView_ctr);

		// constraint fittizio per ptConfermaEliminaParametro
		UISecConstraint ptConfermaEliminaParametro_defaultOnoff_ctr =

		new DummyUISecConstraint("cpEliminaParametroConfigurazioneBO",
				"ptConfermaEliminaParametro",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] ptConfermaEliminaParametro_constraints = new UISecConstraint[]{ptConfermaEliminaParametro_defaultOnoff_ctr};
		UISecConstraint ptConfermaEliminaParametro_ctr = new ComplexUISecConstraint(
				ptConfermaEliminaParametro_constraints);
		allConstraints.put("ptConfermaEliminaParametro",
				ptConfermaEliminaParametro_ctr);

		// constraint fittizio per ptChiave
		UISecConstraint ptChiave_defaultOnoff_ctr =

		new DummyUISecConstraint("cpEliminaParametroConfigurazioneBO",
				"ptChiave", UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true,
				true);

		UISecConstraint[] ptChiave_constraints = new UISecConstraint[]{ptChiave_defaultOnoff_ctr};
		UISecConstraint ptChiave_ctr = new ComplexUISecConstraint(
				ptChiave_constraints);
		allConstraints.put("ptChiave", ptChiave_ctr);

		// constraint fittizio per ptValore
		UISecConstraint ptValore_defaultOnoff_ctr =

		new DummyUISecConstraint("cpEliminaParametroConfigurazioneBO",
				"ptValore", UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true,
				true);

		UISecConstraint[] ptValore_constraints = new UISecConstraint[]{ptValore_defaultOnoff_ctr};
		UISecConstraint ptValore_ctr = new ComplexUISecConstraint(
				ptValore_constraints);
		allConstraints.put("ptValore", ptValore_ctr);

		// constraint fittizio per ptDescrizione
		UISecConstraint ptDescrizione_defaultOnoff_ctr =

		new DummyUISecConstraint("cpEliminaParametroConfigurazioneBO",
				"ptDescrizione", UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR,
				true, true);

		UISecConstraint[] ptDescrizione_constraints = new UISecConstraint[]{ptDescrizione_defaultOnoff_ctr};
		UISecConstraint ptDescrizione_ctr = new ComplexUISecConstraint(
				ptDescrizione_constraints);
		allConstraints.put("ptDescrizione", ptDescrizione_ctr);

		// constraint fittizio per btnIndietro
		UISecConstraint btnIndietro_defaultOnoff_ctr =

		new DummyUISecConstraint("cpEliminaParametroConfigurazioneBO",
				"btnIndietro", UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR,
				true, true);

		UISecConstraint[] btnIndietro_constraints = new UISecConstraint[]{btnIndietro_defaultOnoff_ctr};
		UISecConstraint btnIndietro_ctr = new ComplexUISecConstraint(
				btnIndietro_constraints);
		allConstraints.put("btnIndietro", btnIndietro_ctr);

		// constraint fittizio per btnElimina
		UISecConstraint btnElimina_defaultOnoff_ctr =

		new DummyUISecConstraint("cpEliminaParametroConfigurazioneBO",
				"btnElimina", UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true,
				true);

		UISecConstraint[] btnElimina_constraints = new UISecConstraint[]{btnElimina_defaultOnoff_ctr};
		UISecConstraint btnElimina_ctr = new ComplexUISecConstraint(
				btnElimina_constraints);
		allConstraints.put("btnElimina", btnElimina_ctr);
		return allConstraints;
	}

	/**  */
	void dumpModel(boolean pre) {
		log.debug("[CpEliminaParametroConfigurazioneBOAction::dumpmodel] START");

		log.debug("[CpEliminaParametroConfigurazioneBOAction::dumpmodel] #### DUMP del model della action "
				+ this.getClass()
				+ (pre ? " prima dell'azione" : " dopo l'azione"));
		log.debug("[CpEliminaParametroConfigurazioneBOAction::dumpmodel] [a] campi pubblici del model");
		try {
			java.beans.BeanInfo bi = java.beans.Introspector.getBeanInfo(this
					.getClass());
			java.beans.PropertyDescriptor[] props = bi.getPropertyDescriptors();
			for (int i = 0; i < props.length; i++) {
				java.beans.PropertyDescriptor pd = props[i];
				java.lang.reflect.Method m = pd.getReadMethod();
				if (m != null) {
					Object pval = m.invoke(this, new Object[]{});
					log.debug("[CpEliminaParametroConfigurazioneBOAction::dumpmodel] "
							+ pd.getName() + ":" + pval);
				}
			}
		} catch (IllegalAccessException e) {
			log.error("[CpEliminaParametroConfigurazioneBOAction::dumpmodel] Errore nel dump"
					+ e + ", ignoro");
		} catch (InvocationTargetException e) {
			log.error("[CpEliminaParametroConfigurazioneBOAction::dumpmodel] Errore nel dump"
					+ e + ", ignoro");
		} catch (IntrospectionException e) {
			log.error("[CpEliminaParametroConfigurazioneBOAction::dumpmodel] Errore nel dump"
					+ e + ", ignoro");
		}
		log.debug("[CpEliminaParametroConfigurazioneBOAction::dumpmodel] [b] stato dei widget");
		Object cpWidgetStatus = session
				.get("cpEliminaParametroConfigurazioneBO");
		log.debug("[CpEliminaParametroConfigurazioneBOAction::dumpmodel] "
				+ cpWidgetStatus);
		log.debug("[CpEliminaParametroConfigurazioneBOAction::dumpmodel] [c] sessione");
		log.debug("[CpEliminaParametroConfigurazioneBOAction::dumpmodel] "
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
		// contiene i comandi del widget btnElimina per ogni Ev.H.
		HashMap<String, ICommand> cmds4btnEliminaByEvh = new HashMap<String, ICommand>();

		cmds4btnEliminaByEvh.put("CLICKED", initCommandBtnElimina_CLICKED());
		cmdsByWidget.put("btnElimina", cmds4btnEliminaByEvh);

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
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpEliminaParametroConfigurazioneBOModel) theModel);
					watcher.stop();
					watcher.dumpElapsed(
							"CpEliminaParametroConfigurazioneBOAction",
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

	private ICommand initCommandBtnElimina_CLICKED() {
		// ExecCommand begin
		String[] resultNames4salva = new String[]{"Ok", "Ko"};

		ICommand[] actionsForResults4salva = new ICommand[2];
		// SequenceCommand begin
		/// Clear AppData Command begin
		String[] act_actions_clicked_btnElimina_resokActionstep_0_appdataToBeRemovedFromSession = new String[]{"selettoreParametroConf"};

		ClearAppDataCommand act_actions_clicked_btnElimina_resokActionstep_0 = new ClearAppDataCommand(
				act_actions_clicked_btnElimina_resokActionstep_0_appdataToBeRemovedFromSession);

		/// Clear AppData Command end
		/// Jump Command begin
		JumpCommand act_actions_clicked_btnElimina_resokActionstep_1 = new JumpCommand(
				"cpRicercaParametriConfigurazioneBO", null, false);
		/// Jump Command end

		ICommand[] act_actions_clicked_btnElimina_resokAction_0_steps = new ICommand[]{
				act_actions_clicked_btnElimina_resokActionstep_0,
				act_actions_clicked_btnElimina_resokActionstep_1};
		SequenceCommand act_actions_clicked_btnElimina_resokAction_0 = new SequenceCommand(
				act_actions_clicked_btnElimina_resokAction_0_steps);
		// SequenceCommand end
		actionsForResults4salva[0] = act_actions_clicked_btnElimina_resokAction_0;
		// SequenceCommand begin
		/// NOP Command begin
		NOPCommand act_actions_clicked_btnElimina_reskoActionstep_0 = new NOPCommand();
		/// NOP Command end

		ICommand[] act_actions_clicked_btnElimina_reskoAction_1_steps = new ICommand[]{act_actions_clicked_btnElimina_reskoActionstep_0};
		SequenceCommand act_actions_clicked_btnElimina_reskoAction_1 = new SequenceCommand(
				act_actions_clicked_btnElimina_reskoAction_1_steps);
		// SequenceCommand end
		actionsForResults4salva[1] = act_actions_clicked_btnElimina_reskoAction_1;

		ExecCommand act_actions_clicked_btnElimina_1 = new ExecCommand(
				resultNames4salva, actionsForResults4salva) {
			public ExecResults doLogic(Object theModel)
					throws CommandExecutionException {
				it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch(
						it.csi.mdp.mdpboweb.util.Constants.APPLICATION_CODE);
				ExecResults result = null;
				try {
					watcher.start();
					result = getSpringBackEnd()
							.salva((it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpEliminaParametroConfigurazioneBOModel) theModel);
					watcher.stop();
					watcher.dumpElapsed(
							"CpEliminaParametroConfigurazioneBOAction",
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
		return act_actions_clicked_btnElimina_1;
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
