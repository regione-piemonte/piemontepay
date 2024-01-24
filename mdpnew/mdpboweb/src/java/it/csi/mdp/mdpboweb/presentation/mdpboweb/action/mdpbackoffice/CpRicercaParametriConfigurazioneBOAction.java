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
 * CpRicercaParametriConfigurazioneBOAction Action Class.
 *
 * @author GuiGen
 */
@Validation()
@Conversion()
public class CpRicercaParametriConfigurazioneBOAction extends BaseAction
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
		return it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpRicercaParametriConfigurazioneBOModel.class;
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
	 * Gestione dell'evento CLICKED sul widget [btnNuovo]
	 */
	@MethodProtection(level = "REJECT_SAME")
	public String handleBtnNuovo_CLICKED() throws CommandExecutionException {
		ICommand action = initCommand("btnNuovo", "CLICKED");
		if (log.isDebugEnabled()) {
			log.debug("[CpRicercaParametriConfigurazioneBOAction::handleBtnNuovo_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpRicercaParametriConfigurazioneBOAction::handleBtnNuovo_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpRicercaParametriConfigurazioneBOAction::handleBtnNuovo_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpRicercaParametriConfigurazioneBOAction::handleBtnNuovo_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpRicercaParametriConfigurazioneBOAction::handleBtnNuovo_CLICKED] returning default result [SUCCESS]");
			}
			return SUCCESS;

		}
	}

	/**
	 * Gestione dell'evento CLICKED sul widget [btnModifica]
	 */
	@MethodProtection(level = "REJECT_SAME")
	public String handleBtnModifica_CLICKED() throws CommandExecutionException {
		ICommand action = initCommand("btnModifica", "CLICKED");
		if (log.isDebugEnabled()) {
			log.debug("[CpRicercaParametriConfigurazioneBOAction::handleBtnModifica_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpRicercaParametriConfigurazioneBOAction::handleBtnModifica_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpRicercaParametriConfigurazioneBOAction::handleBtnModifica_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpRicercaParametriConfigurazioneBOAction::handleBtnModifica_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpRicercaParametriConfigurazioneBOAction::handleBtnModifica_CLICKED] returning default result [SUCCESS]");
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
			log.debug("[CpRicercaParametriConfigurazioneBOAction::handleBtnElimina_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpRicercaParametriConfigurazioneBOAction::handleBtnElimina_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpRicercaParametriConfigurazioneBOAction::handleBtnElimina_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpRicercaParametriConfigurazioneBOAction::handleBtnElimina_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpRicercaParametriConfigurazioneBOAction::handleBtnElimina_CLICKED] returning default result [SUCCESS]");
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
	 * al data-binding relativo al dataset DATASET del widget tdParametriConf.
	 * @return sempre il result "provideData"
	 */
	@SkipValidation
	@FatClientOnly
	public String provideTdParametriConf_DATASET()
			throws CommandExecutionException {

		// reperisce il dato da serializzare
		com.opensymphony.xwork2.ActionContext ctx = com.opensymphony.xwork2.ActionContext
				.getContext();
		Object dataToProvide = ctx.getValueStack().findValue(
				"appDataparametriConfigurazioneBO");

		if (isTableClearStatus("cpRicercaParametriConfigurazioneBO_tdParametriConf")) {
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
		/*PROTECTED REGION ID(R-1056933804) ENABLED START*/
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
				log.error("[CpRicercaParametriConfigurazioneBOAction::isWidgetDisabled] errore durante verifica->disable");
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
				log.error("[CpRicercaParametriConfigurazioneBOAction::isWidgetVisible] errore durante verifica->hide");
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
		String[] resultNames4refreshPanel = new String[]{"Ok_No_Results",
				"Ok_Some_Results"};

		ICommand[] actionsForResults4refreshPanel = new ICommand[2];
		// SequenceCommand begin
		/// NOP Command begin
		NOPCommand act_onRefresh_resok_no_resultsActionstep_0 = new NOPCommand();
		/// NOP Command end

		ICommand[] act_onRefresh_resok_no_resultsAction_0_steps = new ICommand[]{act_onRefresh_resok_no_resultsActionstep_0};
		SequenceCommand act_onRefresh_resok_no_resultsAction_0 = new SequenceCommand(
				act_onRefresh_resok_no_resultsAction_0_steps);
		// SequenceCommand end
		actionsForResults4refreshPanel[0] = act_onRefresh_resok_no_resultsAction_0;
		// SequenceCommand begin
		/// NOP Command begin
		NOPCommand act_onRefresh_resok_some_resultsActionstep_0 = new NOPCommand();
		/// NOP Command end

		ICommand[] act_onRefresh_resok_some_resultsAction_1_steps = new ICommand[]{act_onRefresh_resok_some_resultsActionstep_0};
		SequenceCommand act_onRefresh_resok_some_resultsAction_1 = new SequenceCommand(
				act_onRefresh_resok_some_resultsAction_1_steps);
		// SequenceCommand end
		actionsForResults4refreshPanel[1] = act_onRefresh_resok_some_resultsAction_1;

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
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpRicercaParametriConfigurazioneBOModel) theModel);
					watcher.stop();
					watcher.dumpElapsed(
							"CpRicercaParametriConfigurazioneBOAction",
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

		new DummyUISecConstraint("cpRicercaParametriConfigurazioneBO",
				"mnuView", UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true,
				true);

		UISecConstraint[] mnuView_constraints = new UISecConstraint[]{mnuView_defaultVisib_ctr};
		UISecConstraint mnuView_ctr = new ComplexUISecConstraint(
				mnuView_constraints);
		allConstraints.put("mnuView", mnuView_ctr);

		// constraint fittizio per tdParametriConf
		UISecConstraint tdParametriConf_defaultVisib_ctr =

		new DummyUISecConstraint("cpRicercaParametriConfigurazioneBO",
				"tdParametriConf", UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR,
				true, true);

		UISecConstraint[] tdParametriConf_constraints = new UISecConstraint[]{tdParametriConf_defaultVisib_ctr};
		UISecConstraint tdParametriConf_ctr = new ComplexUISecConstraint(
				tdParametriConf_constraints);
		allConstraints.put("tdParametriConf", tdParametriConf_ctr);

		// constraint fittizio per btnNuovo
		UISecConstraint btnNuovo_defaultVisib_ctr =

		new DummyUISecConstraint("cpRicercaParametriConfigurazioneBO",
				"btnNuovo", UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true,
				true);

		UISecConstraint[] btnNuovo_constraints = new UISecConstraint[]{btnNuovo_defaultVisib_ctr};
		UISecConstraint btnNuovo_ctr = new ComplexUISecConstraint(
				btnNuovo_constraints);
		allConstraints.put("btnNuovo", btnNuovo_ctr);

		// constraint fittizio per btnModifica
		UISecConstraint btnModifica_defaultVisib_ctr =

		new DummyUISecConstraint("cpRicercaParametriConfigurazioneBO",
				"btnModifica", UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR,
				true, true);

		UISecConstraint[] btnModifica_constraints = new UISecConstraint[]{btnModifica_defaultVisib_ctr};
		UISecConstraint btnModifica_ctr = new ComplexUISecConstraint(
				btnModifica_constraints);
		allConstraints.put("btnModifica", btnModifica_ctr);

		// constraint fittizio per btnElimina
		UISecConstraint btnElimina_defaultVisib_ctr =

		new DummyUISecConstraint("cpRicercaParametriConfigurazioneBO",
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

		new DummyUISecConstraint("cpRicercaParametriConfigurazioneBO",
				"mnuView", UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true,
				true);

		UISecConstraint[] mnuView_constraints = new UISecConstraint[]{mnuView_defaultOnoff_ctr};
		UISecConstraint mnuView_ctr = new ComplexUISecConstraint(
				mnuView_constraints);
		allConstraints.put("mnuView", mnuView_ctr);

		// constraint fittizio per tdParametriConf
		UISecConstraint tdParametriConf_defaultOnoff_ctr =

		new DummyUISecConstraint("cpRicercaParametriConfigurazioneBO",
				"tdParametriConf", UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR,
				true, true);

		UISecConstraint[] tdParametriConf_constraints = new UISecConstraint[]{tdParametriConf_defaultOnoff_ctr};
		UISecConstraint tdParametriConf_ctr = new ComplexUISecConstraint(
				tdParametriConf_constraints);
		allConstraints.put("tdParametriConf", tdParametriConf_ctr);

		// constraint fittizio per btnNuovo
		UISecConstraint btnNuovo_defaultOnoff_ctr =

		new DummyUISecConstraint("cpRicercaParametriConfigurazioneBO",
				"btnNuovo", UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true,
				true);

		UISecConstraint[] btnNuovo_constraints = new UISecConstraint[]{btnNuovo_defaultOnoff_ctr};
		UISecConstraint btnNuovo_ctr = new ComplexUISecConstraint(
				btnNuovo_constraints);
		allConstraints.put("btnNuovo", btnNuovo_ctr);

		// constraint fittizio per btnModifica
		UISecConstraint btnModifica_defaultOnoff_ctr =

		new DummyUISecConstraint("cpRicercaParametriConfigurazioneBO",
				"btnModifica", UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR,
				true, true);

		UISecConstraint[] btnModifica_constraints = new UISecConstraint[]{btnModifica_defaultOnoff_ctr};
		UISecConstraint btnModifica_ctr = new ComplexUISecConstraint(
				btnModifica_constraints);
		allConstraints.put("btnModifica", btnModifica_ctr);

		// constraint fittizio per btnElimina
		UISecConstraint btnElimina_defaultOnoff_ctr =

		new DummyUISecConstraint("cpRicercaParametriConfigurazioneBO",
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
		log.debug("[CpRicercaParametriConfigurazioneBOAction::dumpmodel] START");

		log.debug("[CpRicercaParametriConfigurazioneBOAction::dumpmodel] #### DUMP del model della action "
				+ this.getClass()
				+ (pre ? " prima dell'azione" : " dopo l'azione"));
		log.debug("[CpRicercaParametriConfigurazioneBOAction::dumpmodel] [a] campi pubblici del model");
		try {
			java.beans.BeanInfo bi = java.beans.Introspector.getBeanInfo(this
					.getClass());
			java.beans.PropertyDescriptor[] props = bi.getPropertyDescriptors();
			for (int i = 0; i < props.length; i++) {
				java.beans.PropertyDescriptor pd = props[i];
				java.lang.reflect.Method m = pd.getReadMethod();
				if (m != null) {
					Object pval = m.invoke(this, new Object[]{});
					log.debug("[CpRicercaParametriConfigurazioneBOAction::dumpmodel] "
							+ pd.getName() + ":" + pval);
				}
			}
		} catch (IllegalAccessException e) {
			log.error("[CpRicercaParametriConfigurazioneBOAction::dumpmodel] Errore nel dump"
					+ e + ", ignoro");
		} catch (InvocationTargetException e) {
			log.error("[CpRicercaParametriConfigurazioneBOAction::dumpmodel] Errore nel dump"
					+ e + ", ignoro");
		} catch (IntrospectionException e) {
			log.error("[CpRicercaParametriConfigurazioneBOAction::dumpmodel] Errore nel dump"
					+ e + ", ignoro");
		}
		log.debug("[CpRicercaParametriConfigurazioneBOAction::dumpmodel] [b] stato dei widget");
		Object cpWidgetStatus = session
				.get("cpRicercaParametriConfigurazioneBO");
		log.debug("[CpRicercaParametriConfigurazioneBOAction::dumpmodel] "
				+ cpWidgetStatus);
		log.debug("[CpRicercaParametriConfigurazioneBOAction::dumpmodel] [c] sessione");
		log.debug("[CpRicercaParametriConfigurazioneBOAction::dumpmodel] "
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

		// contiene i comandi del widget btnNuovo per ogni Ev.H.
		HashMap<String, ICommand> cmds4btnNuovoByEvh = new HashMap<String, ICommand>();

		cmds4btnNuovoByEvh.put("CLICKED", initCommandBtnNuovo_CLICKED());
		cmdsByWidget.put("btnNuovo", cmds4btnNuovoByEvh);
		// contiene i comandi del widget btnModifica per ogni Ev.H.
		HashMap<String, ICommand> cmds4btnModificaByEvh = new HashMap<String, ICommand>();

		cmds4btnModificaByEvh.put("CLICKED", initCommandBtnModifica_CLICKED());
		cmdsByWidget.put("btnModifica", cmds4btnModificaByEvh);
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

	private ICommand initCommandBtnNuovo_CLICKED() {
		// ExecCommand begin
		String[] resultNames4nuovo = new String[]{"Ok", "Ko"};

		ICommand[] actionsForResults4nuovo = new ICommand[2];
		// SequenceCommand begin
		/// Clear AppData Command begin
		String[] act_actions_clicked_btnNuovo_resokActionstep_0_appdataToBeRemovedFromSession = new String[]{"selettoreParametroConf"};

		ClearAppDataCommand act_actions_clicked_btnNuovo_resokActionstep_0 = new ClearAppDataCommand(
				act_actions_clicked_btnNuovo_resokActionstep_0_appdataToBeRemovedFromSession);

		/// Clear AppData Command end
		/// Jump Command begin
		JumpCommand act_actions_clicked_btnNuovo_resokActionstep_1 = new JumpCommand(
				"cpDettaglioParametroConfigurazioneBO", null, false);
		/// Jump Command end

		ICommand[] act_actions_clicked_btnNuovo_resokAction_0_steps = new ICommand[]{
				act_actions_clicked_btnNuovo_resokActionstep_0,
				act_actions_clicked_btnNuovo_resokActionstep_1};
		SequenceCommand act_actions_clicked_btnNuovo_resokAction_0 = new SequenceCommand(
				act_actions_clicked_btnNuovo_resokAction_0_steps);
		// SequenceCommand end
		actionsForResults4nuovo[0] = act_actions_clicked_btnNuovo_resokAction_0;
		// SequenceCommand begin
		/// NOP Command begin
		NOPCommand act_actions_clicked_btnNuovo_reskoActionstep_0 = new NOPCommand();
		/// NOP Command end

		ICommand[] act_actions_clicked_btnNuovo_reskoAction_1_steps = new ICommand[]{act_actions_clicked_btnNuovo_reskoActionstep_0};
		SequenceCommand act_actions_clicked_btnNuovo_reskoAction_1 = new SequenceCommand(
				act_actions_clicked_btnNuovo_reskoAction_1_steps);
		// SequenceCommand end
		actionsForResults4nuovo[1] = act_actions_clicked_btnNuovo_reskoAction_1;

		ExecCommand act_actions_clicked_btnNuovo_1 = new ExecCommand(
				resultNames4nuovo, actionsForResults4nuovo) {
			public ExecResults doLogic(Object theModel)
					throws CommandExecutionException {
				it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch(
						it.csi.mdp.mdpboweb.util.Constants.APPLICATION_CODE);
				ExecResults result = null;
				try {
					watcher.start();
					result = getSpringBackEnd()
							.nuovo((it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpRicercaParametriConfigurazioneBOModel) theModel);
					watcher.stop();
					watcher.dumpElapsed(
							"CpRicercaParametriConfigurazioneBOAction",
							"readOne()", "chiamata verso BackEnd", "nuovo");
					return result;
				} catch (BEException e) {
					throw new CommandExecutionException(
							"errore non gestito nell'esecuzione del metodo [nuovo]:"
									+ e.getMessage(), e);
				}

			}

			public void storeAppData(ExecResults res, BaseAction strutsAction) {
				// nothing to store...
			}
		};
		// Exec Action end
		return act_actions_clicked_btnNuovo_1;
	}

	private ICommand initCommandBtnModifica_CLICKED() {
		// ExecCommand begin
		String[] resultNames4modifica = new String[]{"Ok", "Ko"};

		ICommand[] actionsForResults4modifica = new ICommand[2];
		// SequenceCommand begin
		/// Clear AppData Command begin
		String[] act_actions_clicked_btnModifica_resokActionstep_0_appdataToBeRemovedFromSession = new String[]{"selettoreParametroConf"};

		ClearAppDataCommand act_actions_clicked_btnModifica_resokActionstep_0 = new ClearAppDataCommand(
				act_actions_clicked_btnModifica_resokActionstep_0_appdataToBeRemovedFromSession);

		/// Clear AppData Command end
		/// Jump Command begin
		JumpCommand act_actions_clicked_btnModifica_resokActionstep_1 = new JumpCommand(
				"cpDettaglioParametroConfigurazioneBO", null, false);
		/// Jump Command end

		ICommand[] act_actions_clicked_btnModifica_resokAction_0_steps = new ICommand[]{
				act_actions_clicked_btnModifica_resokActionstep_0,
				act_actions_clicked_btnModifica_resokActionstep_1};
		SequenceCommand act_actions_clicked_btnModifica_resokAction_0 = new SequenceCommand(
				act_actions_clicked_btnModifica_resokAction_0_steps);
		// SequenceCommand end
		actionsForResults4modifica[0] = act_actions_clicked_btnModifica_resokAction_0;
		// SequenceCommand begin
		/// NOP Command begin
		NOPCommand act_actions_clicked_btnModifica_reskoActionstep_0 = new NOPCommand();
		/// NOP Command end

		ICommand[] act_actions_clicked_btnModifica_reskoAction_1_steps = new ICommand[]{act_actions_clicked_btnModifica_reskoActionstep_0};
		SequenceCommand act_actions_clicked_btnModifica_reskoAction_1 = new SequenceCommand(
				act_actions_clicked_btnModifica_reskoAction_1_steps);
		// SequenceCommand end
		actionsForResults4modifica[1] = act_actions_clicked_btnModifica_reskoAction_1;

		ExecCommand act_actions_clicked_btnModifica_1 = new ExecCommand(
				resultNames4modifica, actionsForResults4modifica) {
			public ExecResults doLogic(Object theModel)
					throws CommandExecutionException {
				it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch(
						it.csi.mdp.mdpboweb.util.Constants.APPLICATION_CODE);
				ExecResults result = null;
				try {
					watcher.start();
					result = getSpringBackEnd()
							.modifica(
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpRicercaParametriConfigurazioneBOModel) theModel);
					watcher.stop();
					watcher.dumpElapsed(
							"CpRicercaParametriConfigurazioneBOAction",
							"readOne()", "chiamata verso BackEnd", "modifica");
					return result;
				} catch (BEException e) {
					throw new CommandExecutionException(
							"errore non gestito nell'esecuzione del metodo [modifica]:"
									+ e.getMessage(), e);
				}

			}

			public void storeAppData(ExecResults res, BaseAction strutsAction) {
				// nothing to store...
			}
		};
		// Exec Action end
		return act_actions_clicked_btnModifica_1;
	}

	private ICommand initCommandBtnElimina_CLICKED() {
		// ExecCommand begin
		String[] resultNames4elimina = new String[]{"Ok", "Ko"};

		ICommand[] actionsForResults4elimina = new ICommand[2];
		// SequenceCommand begin
		/// Clear AppData Command begin
		String[] act_actions_clicked_btnElimina_resokActionstep_0_appdataToBeRemovedFromSession = new String[]{"selettoreParametroConf"};

		ClearAppDataCommand act_actions_clicked_btnElimina_resokActionstep_0 = new ClearAppDataCommand(
				act_actions_clicked_btnElimina_resokActionstep_0_appdataToBeRemovedFromSession);

		/// Clear AppData Command end
		/// Jump Command begin
		JumpCommand act_actions_clicked_btnElimina_resokActionstep_1 = new JumpCommand(
				"cpEliminaParametroConfigurazioneBO", null, false);
		/// Jump Command end

		ICommand[] act_actions_clicked_btnElimina_resokAction_0_steps = new ICommand[]{
				act_actions_clicked_btnElimina_resokActionstep_0,
				act_actions_clicked_btnElimina_resokActionstep_1};
		SequenceCommand act_actions_clicked_btnElimina_resokAction_0 = new SequenceCommand(
				act_actions_clicked_btnElimina_resokAction_0_steps);
		// SequenceCommand end
		actionsForResults4elimina[0] = act_actions_clicked_btnElimina_resokAction_0;
		// SequenceCommand begin
		/// NOP Command begin
		NOPCommand act_actions_clicked_btnElimina_reskoActionstep_0 = new NOPCommand();
		/// NOP Command end

		ICommand[] act_actions_clicked_btnElimina_reskoAction_1_steps = new ICommand[]{act_actions_clicked_btnElimina_reskoActionstep_0};
		SequenceCommand act_actions_clicked_btnElimina_reskoAction_1 = new SequenceCommand(
				act_actions_clicked_btnElimina_reskoAction_1_steps);
		// SequenceCommand end
		actionsForResults4elimina[1] = act_actions_clicked_btnElimina_reskoAction_1;

		ExecCommand act_actions_clicked_btnElimina_1 = new ExecCommand(
				resultNames4elimina, actionsForResults4elimina) {
			public ExecResults doLogic(Object theModel)
					throws CommandExecutionException {
				it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch(
						it.csi.mdp.mdpboweb.util.Constants.APPLICATION_CODE);
				ExecResults result = null;
				try {
					watcher.start();
					result = getSpringBackEnd()
							.elimina(
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpRicercaParametriConfigurazioneBOModel) theModel);
					watcher.stop();
					watcher.dumpElapsed(
							"CpRicercaParametriConfigurazioneBOAction",
							"readOne()", "chiamata verso BackEnd", "elimina");
					return result;
				} catch (BEException e) {
					throw new CommandExecutionException(
							"errore non gestito nell'esecuzione del metodo [elimina]:"
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
