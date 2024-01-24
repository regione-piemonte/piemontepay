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
 * CpVerificaFlussiAction Action Class.
 *
 * @author GuiGen
 */
@Validation()
@Conversion()
public class CpVerificaFlussiAction extends BaseAction implements Preparable {

	// Table tbRiepilogo

	private java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.FlussoRiversamento> _widg_tbRiepilogo;

	public void setWidg_tbRiepilogo(
			java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.FlussoRiversamento> value) {
		_widg_tbRiepilogo = value;
	}

	public java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.FlussoRiversamento> getWidg_tbRiepilogo() {
		return _widg_tbRiepilogo;
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

	public void setAppDataflussoRiversamento(
			it.csi.mdp.mdpboweb.dto.nsbackoffice.FlussoRiversamento value) {
		getSession().put("appDataflussoRiversamento", value);
	}

	public it.csi.mdp.mdpboweb.dto.nsbackoffice.FlussoRiversamento getAppDataflussoRiversamento() {
		return (it.csi.mdp.mdpboweb.dto.nsbackoffice.FlussoRiversamento) (getSession()
				.get("appDataflussoRiversamento"));
	}

	public void setAppDatalistaFlussoRiversamento(
			java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.FlussoRiversamento> value) {
		getSession().put("appDatalistaFlussoRiversamento", value);
	}

	public java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.FlussoRiversamento> getAppDatalistaFlussoRiversamento() {
		return (java.util.ArrayList<it.csi.mdp.mdpboweb.dto.nsbackoffice.FlussoRiversamento>) (getSession()
				.get("appDatalistaFlussoRiversamento"));
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
		return it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpVerificaFlussiModel.class;
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
	 * Gestione dell'evento CLICKED sul widget [btIndietro]
	 */
	@MethodProtection(level = "REJECT_SAME")
	public String handleBtIndietro_CLICKED() throws CommandExecutionException {
		ICommand action = initCommand("btIndietro", "CLICKED");
		if (log.isDebugEnabled()) {
			log.debug("[CpVerificaFlussiAction::handleBtIndietro_CLICKED] dump model before");
			dumpModel(false);
		}

		String result = action.doCommand(this);
		if (result != null) {
			if (log.isDebugEnabled()) {
				log.debug("[CpVerificaFlussiAction::handleBtIndietro_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpVerificaFlussiAction::handleBtIndietro_CLICKED] returning result ["
						+ result + "]");
			}
			return result;

		} else {
			if (log.isDebugEnabled()) {
				log.debug("[CpVerificaFlussiAction::handleBtIndietro_CLICKED] dump model after");
				dumpModel(false);
				log.debug("[CpVerificaFlussiAction::handleBtIndietro_CLICKED] returning default result [SUCCESS]");
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
	 * al data-binding relativo al dataset DATASET del widget tbRiepilogo.
	 * @return sempre il result "provideData"
	 */
	@SkipValidation
	@FatClientOnly
	public String provideTbRiepilogo_DATASET() throws CommandExecutionException {

		// reperisce il dato da serializzare
		com.opensymphony.xwork2.ActionContext ctx = com.opensymphony.xwork2.ActionContext
				.getContext();
		Object dataToProvide = ctx.getValueStack().findValue(
				"appDatalistaFlussoRiversamento");

		if (isTableClearStatus("cpVerificaFlussi_tbRiepilogo")) {
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
		/*PROTECTED REGION ID(R1971300497) ENABLED START*/
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
				log.error("[CpVerificaFlussiAction::isWidgetDisabled] errore durante verifica->disable");
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
				log.error("[CpVerificaFlussiAction::isWidgetVisible] errore durante verifica->hide");
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
		String[] resultNames4refreshPanel = new String[]{"INIZIALE"};

		ICommand[] actionsForResults4refreshPanel = new ICommand[1];
		// Screen State Command begin
		//prepara i nomi dei componenti target da abilitare/disabilitare
		String[] act_onRefresh_resinizialeAction_0_on = new String[]{
				"tbRiepilogo", "btIndietro", "mnuView"};

		String[] act_onRefresh_resinizialeAction_0_off = new String[]{};

		String[] act_onRefresh_resinizialeAction_0_show = new String[]{
				"tbRiepilogo", "btIndietro", "mnuView"};

		String[] act_onRefresh_resinizialeAction_0_hide = new String[]{};

		ScreenStateCommand act_onRefresh_resinizialeAction_0 = new ScreenStateCommand(
				"cpVerificaFlussi", "INIZIALE",
				act_onRefresh_resinizialeAction_0_on,
				act_onRefresh_resinizialeAction_0_off,
				act_onRefresh_resinizialeAction_0_show,
				act_onRefresh_resinizialeAction_0_hide);
		//Screen State Command end
		actionsForResults4refreshPanel[0] = act_onRefresh_resinizialeAction_0;

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
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpVerificaFlussiModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpVerificaFlussiAction", "readOne()",
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

		new DummyUISecConstraint("cpVerificaFlussi", "mnuView",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] mnuView_constraints = new UISecConstraint[]{mnuView_defaultVisib_ctr};
		UISecConstraint mnuView_ctr = new ComplexUISecConstraint(
				mnuView_constraints);
		allConstraints.put("mnuView", mnuView_ctr);

		// constraint fittizio per tbRiepilogo
		UISecConstraint tbRiepilogo_defaultVisib_ctr =

		new DummyUISecConstraint("cpVerificaFlussi", "tbRiepilogo",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tbRiepilogo_constraints = new UISecConstraint[]{tbRiepilogo_defaultVisib_ctr};
		UISecConstraint tbRiepilogo_ctr = new ComplexUISecConstraint(
				tbRiepilogo_constraints);
		allConstraints.put("tbRiepilogo", tbRiepilogo_ctr);

		// constraint fittizio per btIndietro
		UISecConstraint btIndietro_defaultVisib_ctr =

		new DummyUISecConstraint("cpVerificaFlussi", "btIndietro",
				UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btIndietro_constraints = new UISecConstraint[]{btIndietro_defaultVisib_ctr};
		UISecConstraint btIndietro_ctr = new ComplexUISecConstraint(
				btIndietro_constraints);
		allConstraints.put("btIndietro", btIndietro_ctr);
		return allConstraints;
	}

	protected Map<String, UISecConstraint> getPageONOFFUIConstraints() {
		Map<String, UISecConstraint> allConstraints = new HashMap<String, UISecConstraint>();

		// constraint fittizio per mnuView
		UISecConstraint mnuView_defaultOnoff_ctr =

		new DummyUISecConstraint("cpVerificaFlussi", "mnuView",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] mnuView_constraints = new UISecConstraint[]{mnuView_defaultOnoff_ctr};
		UISecConstraint mnuView_ctr = new ComplexUISecConstraint(
				mnuView_constraints);
		allConstraints.put("mnuView", mnuView_ctr);

		// constraint fittizio per tbRiepilogo
		UISecConstraint tbRiepilogo_defaultOnoff_ctr =

		new DummyUISecConstraint("cpVerificaFlussi", "tbRiepilogo",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] tbRiepilogo_constraints = new UISecConstraint[]{tbRiepilogo_defaultOnoff_ctr};
		UISecConstraint tbRiepilogo_ctr = new ComplexUISecConstraint(
				tbRiepilogo_constraints);
		allConstraints.put("tbRiepilogo", tbRiepilogo_ctr);

		// constraint fittizio per btIndietro
		UISecConstraint btIndietro_defaultOnoff_ctr =

		new DummyUISecConstraint("cpVerificaFlussi", "btIndietro",
				UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR, true, true);

		UISecConstraint[] btIndietro_constraints = new UISecConstraint[]{btIndietro_defaultOnoff_ctr};
		UISecConstraint btIndietro_ctr = new ComplexUISecConstraint(
				btIndietro_constraints);
		allConstraints.put("btIndietro", btIndietro_ctr);
		return allConstraints;
	}

	/**  */
	void dumpModel(boolean pre) {
		log.debug("[CpVerificaFlussiAction::dumpmodel] START");

		log.debug("[CpVerificaFlussiAction::dumpmodel] #### DUMP del model della action "
				+ this.getClass()
				+ (pre ? " prima dell'azione" : " dopo l'azione"));
		log.debug("[CpVerificaFlussiAction::dumpmodel] [a] campi pubblici del model");
		try {
			java.beans.BeanInfo bi = java.beans.Introspector.getBeanInfo(this
					.getClass());
			java.beans.PropertyDescriptor[] props = bi.getPropertyDescriptors();
			for (int i = 0; i < props.length; i++) {
				java.beans.PropertyDescriptor pd = props[i];
				java.lang.reflect.Method m = pd.getReadMethod();
				if (m != null) {
					Object pval = m.invoke(this, new Object[]{});
					log.debug("[CpVerificaFlussiAction::dumpmodel] "
							+ pd.getName() + ":" + pval);
				}
			}
		} catch (IllegalAccessException e) {
			log.error("[CpVerificaFlussiAction::dumpmodel] Errore nel dump" + e
					+ ", ignoro");
		} catch (InvocationTargetException e) {
			log.error("[CpVerificaFlussiAction::dumpmodel] Errore nel dump" + e
					+ ", ignoro");
		} catch (IntrospectionException e) {
			log.error("[CpVerificaFlussiAction::dumpmodel] Errore nel dump" + e
					+ ", ignoro");
		}
		log.debug("[CpVerificaFlussiAction::dumpmodel] [b] stato dei widget");
		Object cpWidgetStatus = session.get("cpVerificaFlussi");
		log.debug("[CpVerificaFlussiAction::dumpmodel] " + cpWidgetStatus);
		log.debug("[CpVerificaFlussiAction::dumpmodel] [c] sessione");
		log.debug("[CpVerificaFlussiAction::dumpmodel] " + session);
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

		// contiene i comandi del widget btIndietro per ogni Ev.H.
		HashMap<String, ICommand> cmds4btIndietroByEvh = new HashMap<String, ICommand>();

		cmds4btIndietroByEvh.put("CLICKED", initCommandBtIndietro_CLICKED());
		cmdsByWidget.put("btIndietro", cmds4btIndietroByEvh);

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

	private ICommand initCommandBtIndietro_CLICKED() {
		// ExecCommand begin
		String[] resultNames4goToFlussi = new String[]{"ok"};

		ICommand[] actionsForResults4goToFlussi = new ICommand[1];
		/// Jump Command begin
		JumpCommand act_actions_clicked_btIndietro_resokAction_0 = new JumpCommand(
				"cpFlussoRiversamento", null, false);
		/// Jump Command end
		actionsForResults4goToFlussi[0] = act_actions_clicked_btIndietro_resokAction_0;

		ExecCommand act_actions_clicked_btIndietro_1 = new ExecCommand(
				resultNames4goToFlussi, actionsForResults4goToFlussi) {
			public ExecResults doLogic(Object theModel)
					throws CommandExecutionException {
				it.csi.util.performance.StopWatch watcher = new it.csi.util.performance.StopWatch(
						it.csi.mdp.mdpboweb.util.Constants.APPLICATION_CODE);
				ExecResults result = null;
				try {
					watcher.start();
					result = getSpringBackEnd()
							.goToFlussi(
									(it.csi.mdp.mdpboweb.dto.mdpbackoffice.CpVerificaFlussiModel) theModel);
					watcher.stop();
					watcher.dumpElapsed("CpVerificaFlussiAction", "readOne()",
							"chiamata verso BackEnd", "goToFlussi");
					return result;
				} catch (BEException e) {
					throw new CommandExecutionException(
							"errore non gestito nell'esecuzione del metodo [goToFlussi]:"
									+ e.getMessage(), e);
				}

			}

			public void storeAppData(ExecResults res, BaseAction strutsAction) {
				// nothing to store...
			}
		};
		// Exec Action end
		return act_actions_clicked_btIndietro_1;
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
